package dev.lepshee.whop

import dev.lepshee.whop.models.common.Direction
import dev.lepshee.whop.models.withdrawals.CreateWithdrawalRequest
import dev.lepshee.whop.models.withdrawals.WithdrawalFeeType
import dev.lepshee.whop.models.withdrawals.WithdrawalSpeed
import dev.lepshee.whop.models.withdrawals.WithdrawalStatus
import dev.lepshee.whop.resources.ListWithdrawalsRequest
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
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class WithdrawalsResourceTest {
    @Test
    fun `withdrawals list create retrieve and decode`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val page =
                whop.withdrawals.list(
                    ListWithdrawalsRequest(
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
            val created =
                whop.withdrawals.create(
                    CreateWithdrawalRequest(
                        amount = 6.9,
                        companyId = "biz_123",
                        currency = "usd",
                        payoutMethodId = "pout_123",
                        platformCoversFees = true,
                        statementDescriptor = "WHOP123",
                    ),
                )
            val retrieved = whop.withdrawals.retrieve("wdrl_123")

            assertEquals("wdrl_123", page.data.single().id)
            assertEquals(WithdrawalStatus.Completed, page.data.single().status)
            assertEquals(WithdrawalFeeType.Exclusive, page.data.single().feeType)
            assertEquals(WithdrawalSpeed.Instant, page.data.single().speed)
            assertEquals("/api/v1/withdrawals", requests[0].url.encodedPath)
            assertEquals("biz_123", requests[0].url.parameters["company_id"])
            assertEquals("cursor_0", requests[0].url.parameters["after"])
            assertEquals("cursor_before", requests[0].url.parameters["before"])
            assertEquals("10", requests[0].url.parameters["first"])
            assertEquals("20", requests[0].url.parameters["last"])
            assertEquals("desc", requests[0].url.parameters["direction"])
            assertEquals("2023-12-31T00:00:00Z", requests[0].url.parameters["created_before"])
            assertEquals("2023-12-01T00:00:00Z", requests[0].url.parameters["created_after"])
            assertEquals("POST", requests[1].method.value)
            assertEquals("/api/v1/withdrawals", requests[1].url.encodedPath)
            assertTrue(requests[1].bodyText().contains("company_id"))
            assertTrue(requests[1].bodyText().contains("payout_method_id"))
            assertTrue(requests[1].bodyText().contains("platform_covers_fees"))
            assertTrue(requests[1].bodyText().contains("statement_descriptor"))
            assertEquals("ldgr_123", created.ledgerAccount.id)
            assertEquals("potk_123", created.payoutToken?.id)
            assertEquals("account_closed", created.errorCode)
            assertEquals("Destination account is closed.", created.errorMessage)
            assertEquals("/api/v1/withdrawals/wdrl_123", requests[2].url.encodedPath)
            assertEquals("021000021234567", retrieved.traceCode)
        }

    @Test
    fun `withdrawals auto paging follows cursors`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            assertEquals(
                listOf("wdrl_123", "wdrl_456"),
                whop.withdrawals
                    .listAutoPaging(ListWithdrawalsRequest("biz_123"))
                    .toList()
                    .map { it.id },
            )
            assertTrue(requests.any { it.url.parameters["after"] == "wdrl_cursor" })
        }

    @Test
    fun `withdrawal validation rejects invalid inputs and path parameters`() =
        runTest {
            val whop = clientWithEngine(recordingEngine(mutableListOf()))

            assertFailsWith<IllegalArgumentException> { ListWithdrawalsRequest(companyId = " ") }
            assertFailsWith<IllegalArgumentException> { ListWithdrawalsRequest(companyId = "biz_123", after = "") }
            assertFailsWith<IllegalArgumentException> { ListWithdrawalsRequest(companyId = "biz_123", first = 0) }
            assertFailsWith<IllegalArgumentException> { ListWithdrawalsRequest(companyId = "biz_123", createdAfter = " ") }
            assertFailsWith<IllegalArgumentException> { CreateWithdrawalRequest(0.0, "biz_123", "usd") }
            assertFailsWith<IllegalArgumentException> { CreateWithdrawalRequest(Double.NaN, "biz_123", "usd") }
            assertFailsWith<IllegalArgumentException> { CreateWithdrawalRequest(1.0, " ", "usd") }
            assertFailsWith<IllegalArgumentException> { CreateWithdrawalRequest(1.0, "biz_123", " ") }
            assertFailsWith<IllegalArgumentException> { CreateWithdrawalRequest(1.0, "biz_123", "usd", payoutMethodId = " ") }
            assertFailsWith<IllegalArgumentException> { CreateWithdrawalRequest(1.0, "biz_123", "usd", statementDescriptor = "bad!") }
            assertFailsWith<IllegalArgumentException> { CreateWithdrawalRequest(1.0, "biz_123", "usd", statementDescriptor = "abcd") }
            assertFailsWith<IllegalArgumentException> { whop.withdrawals.retrieve("wdrl_123/other") }
            assertFailsWith<IllegalArgumentException> { whop.withdrawals.retrieve("wdrl_123%2Fother") }
        }

    private fun recordingEngine(requests: MutableList<HttpRequestData>): MockEngine =
        MockEngine { request ->
            requests += request
            respond(
                defaultJsonResponse(request.url.encodedPath, request.method.value, request.url.parameters["after"]),
                headers = jsonHeaders,
            )
        }

    private fun defaultJsonResponse(
        path: String,
        method: String,
        after: String?,
    ): String =
        when {
            path == "/api/v1/withdrawals" && method == "GET" -> withdrawalPageJson(after)
            path == "/api/v1/withdrawals" -> withdrawalJson("wdrl_123")
            path.startsWith("/api/v1/withdrawals/") -> withdrawalJson("wdrl_123")
            else -> "{}"
        }

    private fun withdrawalPageJson(after: String?): String =
        if (after == null || after == "cursor_0") {
            pageJson(withdrawalListItemJson("wdrl_123"), true, "wdrl_cursor")
        } else {
            pageJson(withdrawalListItemJson("wdrl_456"), false, null)
        }

    private fun pageJson(
        item: String,
        hasNext: Boolean,
        cursor: String?,
    ): String =
        """
        {"data":[$item],"page_info":{"has_next_page":$hasNext,"end_cursor":${cursor?.let { "\"$it\"" } ?: "null"}}}
        """.trimIndent()

    private fun withdrawalListItemJson(id: String): String =
        """
        {
          "id":"$id",
          "status":"completed",
          "amount":6.9,
          "currency":"usd",
          "fee_amount":0.5,
          "fee_type":"exclusive",
          "speed":"instant",
          "created_at":"2023-12-01T05:00:00.401Z",
          "markup_fee":0.25
        }
        """.trimIndent()

    private fun withdrawalJson(id: String): String =
        """
        {
          "id":"$id",
          "status":"completed",
          "amount":6.9,
          "currency":"usd",
          "fee_amount":0.5,
          "fee_type":"exclusive",
          "speed":"instant",
          "created_at":"2023-12-01T05:00:00.401Z",
          "markup_fee":0.25,
          "ledger_account":{"id":"ldgr_123","company_id":"biz_123"},
          "payout_token":{"id":"potk_123","payer_name":"Acme Corp LLC","nickname":"Main account","destination_currency_code":"USD","created_at":"2023-11-01T05:00:00.401Z"},
          "error_code":"account_closed",
          "error_message":"Destination account is closed.",
          "estimated_availability":"2023-12-03T05:00:00.401Z",
          "trace_code":"021000021234567"
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
