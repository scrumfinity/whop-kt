package dev.lepshee.whop

import dev.lepshee.whop.core.JsonConfig
import dev.lepshee.whop.models.common.Direction
import dev.lepshee.whop.models.disputes.DisputeStatus
import dev.lepshee.whop.models.disputes.UpdateDisputeEvidenceRequest
import dev.lepshee.whop.models.memberships.MembershipStatus
import dev.lepshee.whop.models.payments.BillingReason
import dev.lepshee.whop.resources.ListDisputesRequest
import dev.lepshee.whop.transport.KtorWhopHttpTransport
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.HttpRequestData
import io.ktor.http.HttpHeaders
import io.ktor.http.content.TextContent
import io.ktor.http.headersOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.encodeToString
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class DisputesResourceTest {
    @Test
    fun `dispute endpoints send documented paths filters bodies and decode`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val page =
                whop.disputes.list(
                    ListDisputesRequest(
                        companyId = "biz_123",
                        after = "cursor_0",
                        before = "cursor_before",
                        first = 10,
                        last = 20,
                        direction = Direction.Desc,
                        createdBefore = "2023-12-31T00:00:00Z",
                        createdAfter = "2023-12-01T00:00:00Z",
                    ),
                )
            val retrieved = whop.disputes.retrieve("dspt_123")
            whop.disputes.submitEvidence("dspt_123")
            whop.disputes.updateEvidence(
                "dspt_123",
                UpdateDisputeEvidenceRequest(
                    customerEmailAddress = UpdateDisputeEvidenceRequest.text("customer@example.com"),
                    customerName = UpdateDisputeEvidenceRequest.text("Jane Doe"),
                    productDescription = UpdateDisputeEvidenceRequest.text("Premium analytics dashboard"),
                    customerCommunicationAttachment = UpdateDisputeEvidenceRequest.file("file_123"),
                ),
            )

            assertEquals("dspt_123", page.data.single().id)
            assertEquals(DisputeStatus.NeedsResponse, page.data.single().status)
            assertEquals(
                "pay_123",
                page.data
                    .single()
                    .payment
                    ?.id,
            )
            assertEquals("/api/v1/disputes", requests[0].url.encodedPath)
            assertEquals("biz_123", requests[0].url.parameters["company_id"])
            assertEquals("cursor_0", requests[0].url.parameters["after"])
            assertEquals("cursor_before", requests[0].url.parameters["before"])
            assertEquals("10", requests[0].url.parameters["first"])
            assertEquals("20", requests[0].url.parameters["last"])
            assertEquals("desc", requests[0].url.parameters["direction"])
            assertEquals("2023-12-31T00:00:00Z", requests[0].url.parameters["created_before"])
            assertEquals("2023-12-01T00:00:00Z", requests[0].url.parameters["created_after"])
            assertEquals("/api/v1/disputes/dspt_123", requests[1].url.encodedPath)
            assertEquals(BillingReason.OneTime, retrieved.payment?.billingReason)
            assertEquals(MembershipStatus.Active, retrieved.payment?.membership?.status)
            assertEquals("document.pdf", retrieved.customerCommunicationAttachment?.filename)
            assertEquals("POST", requests[2].method.value)
            assertEquals("/api/v1/disputes/dspt_123/submit_evidence", requests[2].url.encodedPath)
            assertEquals("POST", requests[3].method.value)
            assertEquals("/api/v1/disputes/dspt_123/update_evidence", requests[3].url.encodedPath)
            assertTrue(requests[3].bodyText().contains("customer_email_address"))
            assertTrue(requests[3].bodyText().contains("customer_communication_attachment"))
            assertTrue(requests[3].bodyText().contains("file_123"))
        }

    @Test
    fun `update dispute evidence can explicitly clear nullable fields`() {
        val body =
            JsonConfig.whopJson.encodeToString(
                UpdateDisputeEvidenceRequest(
                    customerName = UpdateDisputeEvidenceRequest.clear,
                    refundPolicyAttachment = UpdateDisputeEvidenceRequest.clear,
                ),
            )

        assertTrue(body.contains("\"customer_name\":null"))
        assertTrue(body.contains("\"refund_policy_attachment\":null"))
    }

    @Test
    fun `disputes auto paging follows cursors`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            assertEquals(
                listOf("dspt_123", "dspt_456"),
                whop.disputes
                    .listAutoPaging(ListDisputesRequest("biz_123"))
                    .toList()
                    .map { it.id },
            )
            assertTrue(requests.any { it.url.parameters["after"] == "dspt_cursor" })
        }

    @Test
    fun `dispute validation rejects invalid inputs and path parameters`() =
        runTest {
            val whop = clientWithEngine(recordingEngine(mutableListOf()))

            assertFailsWith<IllegalArgumentException> { ListDisputesRequest(companyId = " ") }
            assertFailsWith<IllegalArgumentException> { ListDisputesRequest(companyId = "biz_123", first = 0) }
            assertFailsWith<IllegalArgumentException> { ListDisputesRequest(companyId = "biz_123", last = -1) }
            assertFailsWith<IllegalArgumentException> { ListDisputesRequest(companyId = "biz_123", after = "") }
            assertFailsWith<IllegalArgumentException> { ListDisputesRequest(companyId = "biz_123", createdAfter = " ") }
            assertFailsWith<IllegalArgumentException> { UpdateDisputeEvidenceRequest(customerName = UpdateDisputeEvidenceRequest.text("")) }
            assertFailsWith<IllegalArgumentException> { UpdateDisputeEvidenceRequest.file(" ") }
            assertFailsWith<IllegalArgumentException> { whop.disputes.retrieve("dspt_123/other") }
            assertFailsWith<IllegalArgumentException> { whop.disputes.submitEvidence("dspt_123%2Fother") }
            assertFailsWith<IllegalArgumentException> { whop.disputes.updateEvidence(".") }
        }

    private fun recordingEngine(requests: MutableList<HttpRequestData>): MockEngine =
        MockEngine { request ->
            requests += request
            respond(
                defaultJsonResponse(request.url.encodedPath, request.url.parameters["after"]),
                headers = jsonHeaders,
            )
        }

    private fun defaultJsonResponse(
        path: String,
        after: String?,
    ): String =
        when {
            path == "/api/v1/disputes" -> disputePageJson(after)
            path.startsWith("/api/v1/disputes/") -> disputeJson("dspt_123")
            else -> "{}"
        }

    private fun disputePageJson(after: String?): String =
        if (after == null || after == "cursor_0") {
            pageJson(disputeListItemJson("dspt_123"), true, "dspt_cursor")
        } else {
            pageJson(disputeListItemJson("dspt_456"), false, null)
        }

    private fun pageJson(
        item: String,
        hasNext: Boolean,
        cursor: String?,
    ): String =
        """
        {"data":[$item],"page_info":{"has_next_page":$hasNext,"end_cursor":${cursor?.let { "\"$it\"" } ?: "null"}}}
        """.trimIndent()

    private fun disputeListItemJson(id: String): String =
        """
        {
          "id":"$id",
          "amount":20.0,
          "currency":"usd",
          "status":"needs_response",
          "editable":true,
          "created_at":"2023-12-01T05:00:00.401Z",
          "visa_rdr":false,
          "needs_response_by":null,
          "reason":"Product Not Received",
          "plan":{"id":"plan_123"},
          "product":{"id":"prod_123","title":"Pickaxe Analytics"},
          "company":{"id":"biz_123","title":"Acme"},
          "payment":{"id":"pay_123"}
        }
        """.trimIndent()

    private fun disputeJson(id: String): String =
        """
        {
          "id":"$id",
          "amount":20.0,
          "currency":"usd",
          "status":"needs_response",
          "editable":true,
          "created_at":"2023-12-01T05:00:00.401Z",
          "visa_rdr":false,
          "needs_response_by":"2023-12-10T05:00:00.401Z",
          "reason":"Product Not Received",
          "plan":{"id":"plan_123"},
          "product":{"id":"prod_123","title":"Pickaxe Analytics"},
          "company":{"id":"biz_123","title":"Acme"},
          "payment":{
            "id":"pay_123",
            "total":20.0,
            "subtotal":19.0,
            "usd_total":20.0,
            "currency":"usd",
            "created_at":"2023-12-01T03:00:00.000Z",
            "paid_at":"2023-12-01T03:05:00.000Z",
            "dispute_alerted_at":null,
            "payment_method_type":"card",
            "billing_reason":"one_time",
            "card_brand":"visa",
            "card_last4":"4242",
            "user":{"id":"user_123","name":null,"username":"johndoe42","email":"john@example.com"},
            "member":{"id":"member_123","phone":null},
            "membership":{"id":"mem_123","status":"active"}
          },
          "access_activity_log":"192.168.1.1 - 2024-01-15 12:00:00 UTC",
          "billing_address":"123 Main St",
          "cancellation_policy_disclosure":"All sales are final.",
          "customer_email_address":"customer@example.com",
          "customer_name":"Jane Doe",
          "notes":"Customer used the product.",
          "product_description":"Premium analytics dashboard",
          "refund_policy_disclosure":"Refunds available within 14 days.",
          "refund_refusal_explanation":"Outside refund window.",
          "service_date":"2024-01-15",
          "cancellation_policy_attachment":null,
          "customer_communication_attachment":{"id":"file_123","filename":"document.pdf","content_type":"application/pdf","url":"https://media.whop.com/document.pdf"},
          "refund_policy_attachment":null,
          "uncategorized_attachment":null
        }
        """.trimIndent()

    private fun clientWithEngine(engine: MockEngine): WhopClient {
        val config = WhopClientConfig(apiKey = "test_api_key")
        val httpClient = HttpClient(engine) { expectSuccess = false }
        return WhopClient(config, KtorWhopHttpTransport(config, httpClient))
    }

    private fun HttpRequestData.bodyText(): String = (body as TextContent).text

    private companion object {
        val jsonHeaders = headersOf(HttpHeaders.ContentType, "application/json")
    }
}
