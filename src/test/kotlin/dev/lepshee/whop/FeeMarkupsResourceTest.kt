package dev.lepshee.whop

import dev.lepshee.whop.core.JsonConfig
import dev.lepshee.whop.models.feemarkups.CreateFeeMarkupRequest
import dev.lepshee.whop.models.feemarkups.FeeMarkupType
import dev.lepshee.whop.resources.ListFeeMarkupsRequest
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
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class FeeMarkupsResourceTest {
    @Test
    fun `fee markups list create delete and decode`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val page =
                whop.feeMarkups.list(
                    ListFeeMarkupsRequest(
                        companyId = "biz_123",
                        after = "cursor_0",
                        before = "cursor_before",
                        first = 10,
                        last = 20,
                    ),
                )
            val created =
                whop.feeMarkups.create(
                    CreateFeeMarkupRequest(
                        companyId = "biz_123",
                        feeType = FeeMarkupType.CryptoWithdrawalMarkup,
                        fixedFeeUsd = CreateFeeMarkupRequest.amount(6.9),
                        percentageFee = CreateFeeMarkupRequest.clear,
                        metadata = buildJsonObject { put("source", "finance") },
                        notes = CreateFeeMarkupRequest.notes("Approved by finance"),
                    ),
                )
            val deleted = whop.feeMarkups.delete("fm_123")

            assertEquals("fm_123", page.data.single().id)
            assertEquals(FeeMarkupType.CryptoWithdrawalMarkup, page.data.single().feeType)
            assertEquals(6.9, created.fixedFeeUsd)
            assertTrue(deleted)
            assertEquals("/api/v1/fee_markups", requests[0].url.encodedPath)
            assertEquals("biz_123", requests[0].url.parameters["company_id"])
            assertEquals("cursor_0", requests[0].url.parameters["after"])
            assertEquals("cursor_before", requests[0].url.parameters["before"])
            assertEquals("10", requests[0].url.parameters["first"])
            assertEquals("20", requests[0].url.parameters["last"])
            assertEquals("POST", requests[1].method.value)
            assertEquals("/api/v1/fee_markups", requests[1].url.encodedPath)
            assertTrue(requests[1].bodyText().contains("company_id"))
            assertTrue(requests[1].bodyText().contains("crypto_withdrawal_markup"))
            assertTrue(requests[1].bodyText().contains("\"percentage_fee\":null"), requests[1].bodyText())
            assertTrue(requests[1].bodyText().contains("metadata"))
            assertEquals("DELETE", requests[2].method.value)
            assertEquals("/api/v1/fee_markups/fm_123", requests[2].url.encodedPath)
        }

    @Test
    fun `fee markups auto paging follows cursors`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            assertEquals(
                listOf("fm_123", "fm_456"),
                whop.feeMarkups
                    .listAutoPaging(ListFeeMarkupsRequest("biz_123"))
                    .toList()
                    .map { it.id },
            )
            assertTrue(requests.any { it.url.parameters["after"] == "fm_cursor" })
        }

    @Test
    fun `fee markup request can explicitly clear nullable upsert fields`() {
        val json =
            JsonConfig.whopJson.encodeToString(
                CreateFeeMarkupRequest(
                    companyId = "biz_123",
                    feeType = FeeMarkupType.BankWireWithdrawalMarkup,
                    fixedFeeUsd = CreateFeeMarkupRequest.clear,
                    metadata = CreateFeeMarkupRequest.clear,
                    notes = CreateFeeMarkupRequest.clear,
                ),
            )

        assertTrue(json.contains("\"fixed_fee_usd\":null"), json)
        assertTrue(json.contains("\"metadata\":null"), json)
        assertTrue(json.contains("\"notes\":null"), json)
    }

    @Test
    fun `fee markup validation rejects invalid inputs and path parameters`() =
        runTest {
            val whop = clientWithEngine(recordingEngine(mutableListOf()))

            assertFailsWith<IllegalArgumentException> { ListFeeMarkupsRequest(companyId = " ") }
            assertFailsWith<IllegalArgumentException> { ListFeeMarkupsRequest(companyId = "biz_123", after = "") }
            assertFailsWith<IllegalArgumentException> { ListFeeMarkupsRequest(companyId = "biz_123", first = 0) }
            assertFailsWith<IllegalArgumentException> {
                CreateFeeMarkupRequest(" ", FeeMarkupType.CryptoWithdrawalMarkup)
            }
            assertFailsWith<IllegalArgumentException> { CreateFeeMarkupRequest.amount(Double.NaN) }
            assertFailsWith<IllegalArgumentException> {
                CreateFeeMarkupRequest(
                    companyId = "biz_123",
                    feeType = FeeMarkupType.CryptoWithdrawalMarkup,
                    fixedFeeUsd = JsonPrimitive("6.9"),
                )
            }
            assertFailsWith<IllegalArgumentException> {
                CreateFeeMarkupRequest(
                    companyId = "biz_123",
                    feeType = FeeMarkupType.CryptoWithdrawalMarkup,
                    fixedFeeUsd = CreateFeeMarkupRequest.amount(50.1),
                )
            }
            assertFailsWith<IllegalArgumentException> {
                CreateFeeMarkupRequest(
                    companyId = "biz_123",
                    feeType = FeeMarkupType.CryptoWithdrawalMarkup,
                    percentageFee = CreateFeeMarkupRequest.amount(25.1),
                )
            }
            assertFailsWith<IllegalArgumentException> {
                CreateFeeMarkupRequest(
                    companyId = "biz_123",
                    feeType = FeeMarkupType.CryptoWithdrawalMarkup,
                    notes = CreateFeeMarkupRequest.notes(""),
                )
            }
            assertFailsWith<IllegalArgumentException> { whop.feeMarkups.delete("fm_123/other") }
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
            path == "/api/v1/fee_markups" && method == "GET" -> feeMarkupPageJson(after)
            path == "/api/v1/fee_markups" -> feeMarkupJson("fm_123")
            path.startsWith("/api/v1/fee_markups/") && method == "DELETE" -> "true"
            else -> "{}"
        }

    private fun feeMarkupPageJson(after: String?): String =
        if (after == null || after == "cursor_0") {
            pageJson(feeMarkupJson("fm_123"), true, "fm_cursor")
        } else {
            pageJson(feeMarkupJson("fm_456"), false, null)
        }

    private fun pageJson(
        item: String,
        hasNext: Boolean,
        cursor: String?,
    ): String =
        """
        {"data":[$item],"page_info":{"has_next_page":$hasNext,"end_cursor":${cursor?.let { "\"$it\"" } ?: "null"}}}
        """.trimIndent()

    private fun feeMarkupJson(id: String): String =
        """
        {
          "id":"$id",
          "fee_type":"crypto_withdrawal_markup",
          "percentage_fee":null,
          "fixed_fee_usd":6.9,
          "notes":"Approved by finance",
          "created_at":"2023-12-01T05:00:00.401Z",
          "updated_at":"2023-12-02T05:00:00.401Z"
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
