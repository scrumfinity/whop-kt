package dev.lepshee.whop

import dev.lepshee.whop.models.common.Direction
import dev.lepshee.whop.models.resolutioncentercases.ResolutionCenterCaseAction
import dev.lepshee.whop.models.resolutioncentercases.ResolutionCenterCaseCustomerResponse
import dev.lepshee.whop.models.resolutioncentercases.ResolutionCenterCaseIssueType
import dev.lepshee.whop.models.resolutioncentercases.ResolutionCenterCaseMerchantResponse
import dev.lepshee.whop.models.resolutioncentercases.ResolutionCenterCasePlatformResponse
import dev.lepshee.whop.models.resolutioncentercases.ResolutionCenterCaseReporter
import dev.lepshee.whop.models.resolutioncentercases.ResolutionCenterCaseStatus
import dev.lepshee.whop.resources.ListResolutionCenterCasesRequest
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

class ResolutionCenterCasesResourceTest {
    @Test
    fun `resolution center cases list retrieve paths filters and decode`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val page =
                whop.resolutionCenterCases.list(
                    ListResolutionCenterCasesRequest(
                        companyId = "biz_123",
                        after = "cursor_0",
                        before = "cursor_before",
                        first = 10,
                        last = 20,
                        direction = Direction.Desc,
                        statuses = listOf(ResolutionCenterCaseStatus.MerchantResponseNeeded),
                        createdBefore = "2024-01-31T00:00:00Z",
                        createdAfter = "2024-01-01T00:00:00Z",
                    ),
                )
            val retrieved = whop.resolutionCenterCases.retrieve("reso_123")

            assertEquals("reso_123", page.data.single().id)
            assertEquals(ResolutionCenterCaseStatus.MerchantResponseNeeded, page.data.single().status)
            assertEquals(ResolutionCenterCaseIssueType.ItemNotReceived, page.data.single().issue)
            assertEquals(
                ResolutionCenterCaseCustomerResponse.Respond,
                page.data
                    .single()
                    .customerResponseActions
                    .single(),
            )
            assertEquals(
                ResolutionCenterCaseMerchantResponse.RequestMoreInfo,
                page.data
                    .single()
                    .merchantResponseActions
                    .single(),
            )
            assertEquals("/api/v1/resolution_center_cases", requests[0].url.encodedPath)
            assertEquals("biz_123", requests[0].url.parameters["company_id"])
            assertEquals("cursor_0", requests[0].url.parameters["after"])
            assertEquals("cursor_before", requests[0].url.parameters["before"])
            assertEquals("10", requests[0].url.parameters["first"])
            assertEquals("20", requests[0].url.parameters["last"])
            assertEquals("desc", requests[0].url.parameters["direction"])
            assertEquals(listOf("merchant_response_needed"), requests[0].url.parameters.getAll("statuses"))
            assertEquals("2024-01-31T00:00:00Z", requests[0].url.parameters["created_before"])
            assertEquals("2024-01-01T00:00:00Z", requests[0].url.parameters["created_after"])
            assertEquals("/api/v1/resolution_center_cases/reso_123", requests[1].url.encodedPath)
            assertEquals(ResolutionCenterCasePlatformResponse.RequestBuyerInfo, retrieved.platformResponseActions.single())
            assertEquals("pay_123", retrieved.payment.id)
            assertEquals(20.0, retrieved.payment.total)
            assertEquals("member_123", retrieved.member?.id)
            assertEquals(ResolutionCenterCaseAction.Responded, retrieved.resolutionEvents.single().action)
            assertEquals(ResolutionCenterCaseReporter.Customer, retrieved.resolutionEvents.single().reporterType)
        }

    @Test
    fun `resolution center cases auto paging follows cursors`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            assertEquals(
                listOf("reso_123", "reso_456"),
                whop.resolutionCenterCases
                    .listAutoPaging(ListResolutionCenterCasesRequest(companyId = "biz_123"))
                    .toList()
                    .map { it.id },
            )
            assertTrue(requests.any { it.url.parameters["after"] == "reso_cursor" })
        }

    @Test
    fun `resolution center case validation rejects invalid inputs and path parameters`() =
        runTest {
            val whop = clientWithEngine(recordingEngine(mutableListOf()))

            assertFailsWith<IllegalArgumentException> { ListResolutionCenterCasesRequest(companyId = " ") }
            assertFailsWith<IllegalArgumentException> { ListResolutionCenterCasesRequest(first = 0) }
            assertFailsWith<IllegalArgumentException> { ListResolutionCenterCasesRequest(after = "") }
            assertFailsWith<IllegalArgumentException> { ListResolutionCenterCasesRequest(createdAfter = " ") }
            assertFailsWith<IllegalArgumentException> { whop.resolutionCenterCases.retrieve("reso_123/other") }
            assertFailsWith<IllegalArgumentException> { whop.resolutionCenterCases.retrieve("reso_123%2Fother") }
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
            path == "/api/v1/resolution_center_cases" -> resolutionCenterCasePageJson(after)
            path.startsWith("/api/v1/resolution_center_cases/") -> resolutionCenterCaseJson("reso_123")
            else -> "{}"
        }

    private fun resolutionCenterCasePageJson(after: String?): String =
        if (after == null || after == "cursor_0") {
            pageJson(resolutionCenterCaseListItemJson("reso_123"), true, "reso_cursor")
        } else {
            pageJson(resolutionCenterCaseListItemJson("reso_456"), false, null)
        }

    private fun pageJson(
        item: String,
        hasNext: Boolean,
        cursor: String?,
    ): String =
        """
        {"data":[$item],"page_info":{"has_next_page":$hasNext,"end_cursor":${cursor?.let { "\"$it\"" } ?: "null"}}}
        """.trimIndent()

    private fun resolutionCenterCaseListItemJson(id: String): String =
        """
        {
          "id":"$id",
          "status":"merchant_response_needed",
          "issue":"item_not_received",
          "created_at":"2024-01-01T00:00:00Z",
          "updated_at":"2024-01-02T00:00:00Z",
          "due_date":null,
          "customer_appealed":false,
          "merchant_appealed":false,
          "customer_response_actions":["respond"],
          "merchant_response_actions":["request_more_info"],
          "company":{"id":"biz_123","title":"Acme"},
          "user":{"id":"user_123","name":null,"username":"jane"},
          "payment":{"id":"pay_123"}
        }
        """.trimIndent()

    private fun resolutionCenterCaseJson(id: String): String =
        """
        {
          "id":"$id",
          "status":"merchant_response_needed",
          "issue":"item_not_received",
          "created_at":"2024-01-01T00:00:00Z",
          "updated_at":"2024-01-02T00:00:00Z",
          "due_date":"2024-01-10T00:00:00Z",
          "customer_appealed":false,
          "merchant_appealed":false,
          "customer_response_actions":["respond"],
          "merchant_response_actions":["request_more_info"],
          "company":{"id":"biz_123","title":"Acme"},
          "user":{"id":"user_123","name":null,"username":"jane"},
          "platform_response_actions":["request_buyer_info"],
          "payment":{"id":"pay_123","currency":"usd","created_at":"2024-01-01T00:00:00Z","paid_at":null,"total":20.0,"subtotal":19.0},
          "member":{"id":"member_123"},
          "resolution_events":[{"id":"revt_123","action":"responded","reporter_type":"customer","details":"Package never arrived","created_at":"2024-01-02T00:00:00Z"}]
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
