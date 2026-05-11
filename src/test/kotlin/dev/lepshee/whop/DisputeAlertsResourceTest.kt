package dev.lepshee.whop

import dev.lepshee.whop.models.common.Direction
import dev.lepshee.whop.models.disputealerts.DisputeAlertDisputeStatus
import dev.lepshee.whop.models.disputealerts.DisputeAlertType
import dev.lepshee.whop.models.memberships.MembershipStatus
import dev.lepshee.whop.models.payments.BillingReason
import dev.lepshee.whop.resources.ListDisputeAlertsRequest
import dev.lepshee.whop.transport.KtorWhopHttpTransport
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.HttpRequestData
import io.ktor.http.HttpHeaders
import io.ktor.http.headersOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class DisputeAlertsResourceTest {
    @Test
    fun `dispute alerts list and retrieve use documented paths filters and decode nested shapes`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val page =
                whop.disputeAlerts.list(
                    ListDisputeAlertsRequest(
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
            val retrieved = whop.disputeAlerts.retrieve("dalt_123")

            assertEquals("dalt_123", page.data.single().id)
            assertEquals(DisputeAlertType.DisputeRdr, page.data.single().alertType)
            assertEquals(
                "pay_123",
                page.data
                    .single()
                    .payment
                    ?.id,
            )
            assertEquals(
                "disp_123",
                page.data
                    .single()
                    .dispute
                    ?.id,
            )
            assertEquals("/api/v1/dispute_alerts", requests[0].url.encodedPath)
            assertEquals("biz_123", requests[0].url.parameters["company_id"])
            assertEquals("cursor_0", requests[0].url.parameters["after"])
            assertEquals("cursor_before", requests[0].url.parameters["before"])
            assertEquals("10", requests[0].url.parameters["first"])
            assertEquals("20", requests[0].url.parameters["last"])
            assertEquals("desc", requests[0].url.parameters["direction"])
            assertEquals("2023-12-31T00:00:00Z", requests[0].url.parameters["created_before"])
            assertEquals("2023-12-01T00:00:00Z", requests[0].url.parameters["created_after"])
            assertEquals("/api/v1/dispute_alerts/dalt_123", requests[1].url.encodedPath)
            assertEquals(BillingReason.SubscriptionCycle, retrieved.payment?.billingReason)
            assertEquals(MembershipStatus.Active, retrieved.payment?.membership?.status)
            assertEquals(DisputeAlertDisputeStatus.NeedsResponse, retrieved.dispute?.status)
            assertEquals("visa", retrieved.payment?.cardBrand)
            assertEquals("card", retrieved.payment?.paymentMethodType)
        }

    @Test
    fun `dispute alerts auto paging follows cursors`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            assertEquals(
                listOf("dalt_123", "dalt_456"),
                whop.disputeAlerts
                    .listAutoPaging(ListDisputeAlertsRequest("biz_123"))
                    .toList()
                    .map { it.id },
            )
            assertTrue(requests.any { it.url.parameters["after"] == "dalt_cursor" })
        }

    @Test
    fun `dispute alert validation rejects invalid inputs and path parameters`() =
        runTest {
            val whop = clientWithEngine(recordingEngine(mutableListOf()))

            assertFailsWith<IllegalArgumentException> { ListDisputeAlertsRequest(companyId = " ") }
            assertFailsWith<IllegalArgumentException> { ListDisputeAlertsRequest(companyId = "biz_123", first = 0) }
            assertFailsWith<IllegalArgumentException> { ListDisputeAlertsRequest(companyId = "biz_123", last = -1) }
            assertFailsWith<IllegalArgumentException> { ListDisputeAlertsRequest(companyId = "biz_123", after = "") }
            assertFailsWith<IllegalArgumentException> { ListDisputeAlertsRequest(companyId = "biz_123", createdBefore = " ") }
            assertFailsWith<IllegalArgumentException> { whop.disputeAlerts.retrieve("dalt_123/other") }
            assertFailsWith<IllegalArgumentException> { whop.disputeAlerts.retrieve("dalt_123%2Fother") }
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
            path == "/api/v1/dispute_alerts" -> disputeAlertPageJson(after)
            path.startsWith("/api/v1/dispute_alerts/") -> disputeAlertJson("dalt_123")
            else -> "{}"
        }

    private fun disputeAlertPageJson(after: String?): String =
        if (after == null || after == "cursor_0") {
            pageJson(disputeAlertListItemJson("dalt_123"), true, "dalt_cursor")
        } else {
            pageJson(disputeAlertListItemJson("dalt_456"), false, null)
        }

    private fun pageJson(
        item: String,
        hasNext: Boolean,
        cursor: String?,
    ): String =
        """
        {"data":[$item],"page_info":{"has_next_page":$hasNext,"end_cursor":${cursor?.let { "\"$it\"" } ?: "null"}}}
        """.trimIndent()

    private fun disputeAlertListItemJson(id: String): String =
        """
        {
          "id":"$id",
          "alert_type":"dispute_rdr",
          "amount":20.0,
          "currency":"usd",
          "created_at":"2023-12-01T05:00:00.401Z",
          "transaction_date":null,
          "charge_for_alert":true,
          "payment":{"id":"pay_123"},
          "dispute":{"id":"disp_123"}
        }
        """.trimIndent()

    private fun disputeAlertJson(id: String): String =
        """
        {
          "id":"$id",
          "alert_type":"dispute",
          "amount":20.0,
          "currency":"usd",
          "created_at":"2023-12-01T05:00:00.401Z",
          "transaction_date":"2023-12-01T04:00:00.000Z",
          "charge_for_alert":false,
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
            "billing_reason":"subscription_cycle",
            "card_brand":"visa",
            "card_last4":"4242",
            "user":{"id":"user_123","name":null,"username":"johndoe42","email":"john@example.com"},
            "member":{"id":"member_123","phone":null},
            "membership":{"id":"mem_123","status":"active"}
          },
          "dispute":{
            "id":"disp_123",
            "amount":20.0,
            "currency":"usd",
            "status":"needs_response",
            "reason":null,
            "created_at":null
          }
        }
        """.trimIndent()

    private fun clientWithEngine(engine: MockEngine): WhopClient {
        val config = WhopClientConfig(apiKey = "test_api_key")
        val httpClient = HttpClient(engine) { expectSuccess = false }
        return WhopClient(config, KtorWhopHttpTransport(config, httpClient))
    }

    private companion object {
        val jsonHeaders = headersOf(HttpHeaders.ContentType, "application/json")
    }
}
