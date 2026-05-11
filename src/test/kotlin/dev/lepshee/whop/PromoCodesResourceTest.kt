package dev.lepshee.whop

import dev.lepshee.whop.models.promocodes.CreatePromoCodeRequest
import dev.lepshee.whop.models.promocodes.PromoCodeStatus
import dev.lepshee.whop.models.promocodes.PromoDuration
import dev.lepshee.whop.models.promocodes.PromoType
import dev.lepshee.whop.resources.ListPromoCodesRequest
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

class PromoCodesResourceTest {
    @Test
    fun `promo code endpoints send documented paths filters body and decode`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val page =
                whop.promoCodes.list(
                    ListPromoCodesRequest(
                        companyId = "biz_123",
                        after = "cursor_0",
                        before = "cursor_before",
                        first = 10,
                        last = 20,
                        productIds = listOf("prod_123", "prod_456"),
                        planIds = listOf("plan_123", "plan_456"),
                        status = PromoCodeStatus.Active,
                        createdBefore = "2024-02-01T00:00:00Z",
                        createdAfter = "2024-01-01T00:00:00Z",
                    ),
                )
            val created =
                whop.promoCodes.create(
                    CreatePromoCodeRequest(
                        amountOff = 25.0,
                        baseCurrency = "usd",
                        code = "SAVE25",
                        companyId = "biz_123",
                        newUsersOnly = true,
                        promoDurationMonths = 3,
                        promoType = PromoType.Percentage,
                        churnedUsersOnly = false,
                        existingMembershipsOnly = false,
                        expiresAt = "2024-12-31T00:00:00Z",
                        onePerCustomer = true,
                        planIds = listOf("plan_123"),
                        productId = "prod_123",
                        stock = 100,
                        unlimitedStock = false,
                    ),
                )
            val retrieved = whop.promoCodes.retrieve("promo_123")
            val deleted = whop.promoCodes.delete("promo_123")

            assertEquals("promo_123", page.data.single().id)
            assertEquals(PromoCodeStatus.Active, page.data.single().status)
            assertEquals(PromoDuration.Repeating, page.data.single().duration)
            assertEquals("/api/v1/promo_codes", requests[0].url.encodedPath)
            assertEquals("biz_123", requests[0].url.parameters["company_id"])
            assertEquals("cursor_0", requests[0].url.parameters["after"])
            assertEquals("cursor_before", requests[0].url.parameters["before"])
            assertEquals("10", requests[0].url.parameters["first"])
            assertEquals("20", requests[0].url.parameters["last"])
            assertEquals(listOf("prod_123", "prod_456"), requests[0].url.parameters.getAll("product_ids"))
            assertEquals(listOf("plan_123", "plan_456"), requests[0].url.parameters.getAll("plan_ids"))
            assertEquals("active", requests[0].url.parameters["status"])
            assertEquals("2024-02-01T00:00:00Z", requests[0].url.parameters["created_before"])
            assertEquals("2024-01-01T00:00:00Z", requests[0].url.parameters["created_after"])
            assertEquals("POST", requests[1].method.value)
            assertEquals("/api/v1/promo_codes", requests[1].url.encodedPath)
            assertTrue(requests[1].bodyText().contains("amount_off"))
            assertTrue(requests[1].bodyText().contains("base_currency"))
            assertTrue(requests[1].bodyText().contains("new_users_only"))
            assertTrue(requests[1].bodyText().contains("promo_duration_months"))
            assertTrue(requests[1].bodyText().contains("promo_type"))
            assertEquals("SAVE25", created.code)
            assertEquals("biz_123", created.company.id)
            assertEquals("/api/v1/promo_codes/promo_123", requests[2].url.encodedPath)
            assertEquals("SAVE25", retrieved.code)
            assertEquals("DELETE", requests[3].method.value)
            assertEquals("/api/v1/promo_codes/promo_123", requests[3].url.encodedPath)
            assertTrue(deleted)
        }

    @Test
    fun `promo codes auto paging follows cursors`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            assertEquals(
                listOf("promo_123", "promo_456"),
                whop.promoCodes
                    .listAutoPaging(ListPromoCodesRequest(companyId = "biz_123"))
                    .toList()
                    .map { it.id },
            )
            assertTrue(requests.any { it.url.parameters["after"] == "promo_cursor" })
        }

    @Test
    fun `promo code validation rejects invalid inputs and path parameters`() =
        runTest {
            val whop = clientWithEngine(recordingEngine(mutableListOf()))

            assertFailsWith<IllegalArgumentException> { ListPromoCodesRequest(companyId = " ") }
            assertFailsWith<IllegalArgumentException> { ListPromoCodesRequest(companyId = "biz_123", after = "") }
            assertFailsWith<IllegalArgumentException> { ListPromoCodesRequest(companyId = "biz_123", before = " ") }
            assertFailsWith<IllegalArgumentException> { ListPromoCodesRequest(companyId = "biz_123", first = 0) }
            assertFailsWith<IllegalArgumentException> { ListPromoCodesRequest(companyId = "biz_123", last = 0) }
            assertFailsWith<IllegalArgumentException> { ListPromoCodesRequest(companyId = "biz_123", productIds = listOf("prod_123", "")) }
            assertFailsWith<IllegalArgumentException> { ListPromoCodesRequest(companyId = "biz_123", planIds = listOf(" ")) }
            assertFailsWith<IllegalArgumentException> { ListPromoCodesRequest(companyId = "biz_123", createdBefore = " ") }
            assertFailsWith<IllegalArgumentException> { ListPromoCodesRequest(companyId = "biz_123", createdAfter = " ") }
            assertFailsWith<IllegalArgumentException> {
                CreatePromoCodeRequest(
                    0.0,
                    "usd",
                    "SAVE25",
                    "biz_123",
                    true,
                    3,
                    PromoType.Percentage,
                )
            }
            assertFailsWith<IllegalArgumentException> {
                CreatePromoCodeRequest(Double.POSITIVE_INFINITY, "usd", "SAVE25", "biz_123", true, 3, PromoType.Percentage)
            }
            assertFailsWith<IllegalArgumentException> {
                CreatePromoCodeRequest(
                    25.0,
                    " ",
                    "SAVE25",
                    "biz_123",
                    true,
                    3,
                    PromoType.Percentage,
                )
            }
            assertFailsWith<IllegalArgumentException> { CreatePromoCodeRequest(25.0, "usd", " ", "biz_123", true, 3, PromoType.Percentage) }
            assertFailsWith<IllegalArgumentException> { CreatePromoCodeRequest(25.0, "usd", "SAVE25", " ", true, 3, PromoType.Percentage) }
            assertFailsWith<IllegalArgumentException> {
                CreatePromoCodeRequest(
                    25.0,
                    "usd",
                    "SAVE25",
                    "biz_123",
                    true,
                    0,
                    PromoType.Percentage,
                )
            }
            assertFailsWith<IllegalArgumentException> {
                CreatePromoCodeRequest(25.0, "usd", "SAVE25", "biz_123", true, 3, PromoType.Percentage, expiresAt = " ")
            }
            assertFailsWith<IllegalArgumentException> {
                CreatePromoCodeRequest(25.0, "usd", "SAVE25", "biz_123", true, 3, PromoType.Percentage, planIds = listOf(""))
            }
            assertFailsWith<IllegalArgumentException> {
                CreatePromoCodeRequest(25.0, "usd", "SAVE25", "biz_123", true, 3, PromoType.Percentage, productId = " ")
            }
            assertFailsWith<IllegalArgumentException> {
                CreatePromoCodeRequest(25.0, "usd", "SAVE25", "biz_123", true, 3, PromoType.Percentage, stock = -1)
            }
            assertFailsWith<IllegalArgumentException> { whop.promoCodes.retrieve("promo_123/other") }
            assertFailsWith<IllegalArgumentException> { whop.promoCodes.delete("promo_123%2Fother") }
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
            path == "/api/v1/promo_codes" && method == "GET" -> promoCodePageJson(after)
            path == "/api/v1/promo_codes" -> promoCodeJson("promo_123")
            path.startsWith("/api/v1/promo_codes/") && method == "DELETE" -> "true"
            path.startsWith("/api/v1/promo_codes/") -> promoCodeJson("promo_123")
            else -> "{}"
        }

    private fun promoCodePageJson(after: String?): String =
        if (after == null || after == "cursor_0") {
            pageJson(promoCodeListItemJson("promo_123"), true, "promo_cursor")
        } else {
            pageJson(promoCodeListItemJson("promo_456"), false, null)
        }

    private fun pageJson(
        item: String,
        hasNext: Boolean,
        cursor: String?,
    ): String =
        """
        {"data":[$item],"page_info":{"has_next_page":$hasNext,"end_cursor":${cursor?.let { "\"$it\"" } ?: "null"}}}
        """.trimIndent()

    private fun promoCodeListItemJson(id: String): String =
        """
        {
          "id":"$id",
          "amount_off":25.0,
          "currency":"usd",
          "churned_users_only":false,
          "code":"SAVE25",
          "created_at":"2024-01-01T00:00:00Z",
          "existing_memberships_only":false,
          "duration":"repeating",
          "expires_at":"2024-12-31T00:00:00Z",
          "new_users_only":true,
          "promo_duration_months":3,
          "one_per_customer":true,
          "product":{"id":"prod_123","title":"VIP"},
          "promo_type":"percentage",
          "status":"active",
          "stock":100,
          "unlimited_stock":false,
          "uses":2
        }
        """.trimIndent()

    private fun promoCodeJson(id: String): String =
        """
        ${promoCodeListItemJson(id).dropLast(2)},
          "company":{"id":"biz_123","title":"Acme"}
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
