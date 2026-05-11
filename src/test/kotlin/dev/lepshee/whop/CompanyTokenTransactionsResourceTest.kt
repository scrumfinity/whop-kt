package dev.lepshee.whop

import dev.lepshee.whop.models.companytokentransactions.CompanyTokenTransactionType
import dev.lepshee.whop.models.companytokentransactions.CreateCompanyTokenTransactionRequest
import dev.lepshee.whop.resources.ListCompanyTokenTransactionsRequest
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

class CompanyTokenTransactionsResourceTest {
    @Test
    fun `company token transactions list create retrieve and decode`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val page =
                whop.companyTokenTransactions.list(
                    ListCompanyTokenTransactionsRequest(
                        companyId = "biz_123",
                        after = "cursor_0",
                        before = "cursor_before",
                        first = 10,
                        last = 20,
                        userId = "user_123",
                        transactionType = CompanyTokenTransactionType.Transfer,
                    ),
                )
            val created =
                whop.companyTokenTransactions.create(
                    CreateCompanyTokenTransactionRequest.transfer(
                        amount = 25.0,
                        companyId = "biz_123",
                        userId = "user_123",
                        destinationUserId = "user_456",
                        description = "Reward transfer",
                        idempotencyKey = "ctt_idem_123",
                    ),
                )
            val retrieved = whop.companyTokenTransactions.retrieve("ctt_123")

            assertEquals("ctt_123", page.data.single().id)
            assertEquals(CompanyTokenTransactionType.Transfer, page.data.single().transactionType)
            assertEquals("johndoe42", created.user.username)
            assertEquals("Acme", retrieved.company.title)
            assertEquals("member_123", retrieved.member.id)
            assertEquals("/api/v1/company_token_transactions", requests[0].url.encodedPath)
            assertEquals("biz_123", requests[0].url.parameters["company_id"])
            assertEquals("cursor_0", requests[0].url.parameters["after"])
            assertEquals("cursor_before", requests[0].url.parameters["before"])
            assertEquals("10", requests[0].url.parameters["first"])
            assertEquals("20", requests[0].url.parameters["last"])
            assertEquals("user_123", requests[0].url.parameters["user_id"])
            assertEquals("transfer", requests[0].url.parameters["transaction_type"])
            assertEquals("POST", requests[1].method.value)
            assertTrue(requests[1].bodyText().contains("transaction_type"))
            assertTrue(requests[1].bodyText().contains("transfer"))
            assertTrue(requests[1].bodyText().contains("destination_user_id"))
            assertTrue(requests[1].bodyText().contains("idempotency_key"))
            assertEquals("/api/v1/company_token_transactions/ctt_123", requests[2].url.encodedPath)
        }

    @Test
    fun `company token transactions auto paging follows cursors`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            assertEquals(
                listOf("ctt_123", "ctt_456"),
                whop.companyTokenTransactions
                    .listAutoPaging(ListCompanyTokenTransactionsRequest("biz_123"))
                    .toList()
                    .map { it.id },
            )
            assertTrue(requests.any { it.url.parameters["after"] == "ctt_cursor" })
        }

    @Test
    fun `company token transaction request factories serialize documented variants`() {
        val add = CreateCompanyTokenTransactionRequest.add(5.0, "biz_123", "user_123")
        val subtract = CreateCompanyTokenTransactionRequest.subtract(6.0, "biz_123", "user_123")
        val transfer = CreateCompanyTokenTransactionRequest.transfer(7.0, "biz_123", "user_123", "user_456")

        assertEquals(CompanyTokenTransactionType.Add, add.transactionType)
        assertEquals(CompanyTokenTransactionType.Subtract, subtract.transactionType)
        assertEquals(CompanyTokenTransactionType.Transfer, transfer.transactionType)
        assertEquals("user_456", transfer.destinationUserId)
    }

    @Test
    fun `company token transaction validation rejects invalid inputs and path parameters`() =
        runTest {
            val whop = clientWithEngine(recordingEngine(mutableListOf()))

            assertFailsWith<IllegalArgumentException> { ListCompanyTokenTransactionsRequest(companyId = " ") }
            assertFailsWith<IllegalArgumentException> { ListCompanyTokenTransactionsRequest(companyId = "biz_123", first = 0) }
            assertFailsWith<IllegalArgumentException> { ListCompanyTokenTransactionsRequest(companyId = "biz_123", userId = "") }
            assertFailsWith<IllegalArgumentException> { CreateCompanyTokenTransactionRequest.add(0.0, "biz_123", "user_123") }
            assertFailsWith<IllegalArgumentException> {
                CreateCompanyTokenTransactionRequest.add(
                    Double.POSITIVE_INFINITY,
                    "biz_123",
                    "user_123",
                )
            }
            assertFailsWith<IllegalArgumentException> { CreateCompanyTokenTransactionRequest.add(1.0, " ", "user_123") }
            assertFailsWith<IllegalArgumentException> { CreateCompanyTokenTransactionRequest.add(1.0, "biz_123", "") }
            assertFailsWith<IllegalArgumentException> {
                CreateCompanyTokenTransactionRequest(
                    amount = 1.0,
                    companyId = "biz_123",
                    transactionType = CompanyTokenTransactionType.Transfer,
                    userId = "user_123",
                )
            }
            assertFailsWith<IllegalArgumentException> {
                CreateCompanyTokenTransactionRequest.add(1.0, "biz_123", "user_123").copy(destinationUserId = "user_456")
            }
            assertFailsWith<IllegalArgumentException> { whop.companyTokenTransactions.retrieve("ctt_123/other") }
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
            path == "/api/v1/company_token_transactions" && method == "GET" -> transactionPageJson(after)
            path == "/api/v1/company_token_transactions" -> transactionJson("ctt_123")
            path.startsWith("/api/v1/company_token_transactions/") -> transactionJson("ctt_123")
            else -> "{}"
        }

    private fun transactionPageJson(after: String?): String =
        if (after == null || after == "cursor_0") {
            pageJson(transactionJson("ctt_123"), true, "ctt_cursor")
        } else {
            pageJson(transactionJson("ctt_456"), false, null)
        }

    private fun pageJson(
        item: String,
        hasNext: Boolean,
        cursor: String?,
    ): String =
        """
        {"data":[$item],"page_info":{"has_next_page":$hasNext,"end_cursor":${cursor?.let { "\"$it\"" } ?: "null"}}}
        """.trimIndent()

    private fun transactionJson(id: String): String =
        """
        {
          "id":"$id",
          "transaction_type":"transfer",
          "amount":25.0,
          "description":null,
          "created_at":"2023-12-01T05:00:00.401Z",
          "linked_transaction_id":null,
          "idempotency_key":"ctt_idem_123",
          "user":{"id":"user_123","name":null,"username":"johndoe42"},
          "member":{"id":"member_123"},
          "company":{"id":"biz_123","title":"Acme","route":"acme"}
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
