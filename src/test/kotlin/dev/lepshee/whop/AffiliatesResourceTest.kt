package dev.lepshee.whop

import dev.lepshee.whop.models.affiliates.AffiliateAppliesToPayments
import dev.lepshee.whop.models.affiliates.AffiliateOverrideRole
import dev.lepshee.whop.models.affiliates.AffiliatePayoutType
import dev.lepshee.whop.models.affiliates.AffiliateRevenueBasis
import dev.lepshee.whop.models.affiliates.AffiliateSortableColumn
import dev.lepshee.whop.models.affiliates.AffiliateStatus
import dev.lepshee.whop.models.affiliates.CreateAffiliateRequest
import dev.lepshee.whop.models.affiliates.CreateRevShareAffiliateOverrideRequest
import dev.lepshee.whop.models.affiliates.CreateStandardAffiliateOverrideRequest
import dev.lepshee.whop.models.affiliates.UpdateAffiliateOverrideRequest
import dev.lepshee.whop.models.common.Direction
import dev.lepshee.whop.resources.ListAffiliateOverridesRequest
import dev.lepshee.whop.resources.ListAffiliatesRequest
import dev.lepshee.whop.transport.KtorWhopHttpTransport
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.HttpRequestData
import io.ktor.http.HttpHeaders
import io.ktor.http.content.OutgoingContent
import io.ktor.http.content.TextContent
import io.ktor.http.headersOf
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AffiliatesResourceTest {
    @Test
    fun `affiliate endpoints send documented paths filters bodies and actions`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val page =
                whop.affiliates.list(
                    ListAffiliatesRequest(
                        companyId = "biz_123",
                        first = 10,
                        direction = Direction.Asc,
                        order = AffiliateSortableColumn.CachedTotalReferrals,
                        query = "dean",
                        status = AffiliateStatus.Active,
                    ),
                )
            val created =
                whop.affiliates.create(
                    CreateAffiliateRequest(
                        companyId = "biz_123",
                        userIdentifier = "dean",
                    ),
                )
            whop.affiliates.retrieve("aff_123")
            val archived = whop.affiliates.archive("aff_123")
            val unarchived = whop.affiliates.unarchive("aff_123")

            assertEquals("aff_123", page.data.single().id)
            assertEquals("aff_123", created.id)
            assertTrue(archived)
            assertTrue(unarchived)
            assertEquals("/api/v1/affiliates", requests[0].url.encodedPath)
            assertEquals("biz_123", requests[0].url.parameters["company_id"])
            assertEquals("10", requests[0].url.parameters["first"])
            assertEquals("asc", requests[0].url.parameters["direction"])
            assertEquals("cached_total_referrals", requests[0].url.parameters["order"])
            assertEquals("dean", requests[0].url.parameters["query"])
            assertEquals("active", requests[0].url.parameters["status"])
            assertEquals("POST", requests[1].method.value)
            assertTrue(requests[1].bodyText().contains("company_id"))
            assertTrue(requests[1].bodyText().contains("user_identifier"))
            assertEquals("/api/v1/affiliates/aff_123", requests[2].url.encodedPath)
            assertEquals("/api/v1/affiliates/aff_123/archive", requests[3].url.encodedPath)
            assertNoTextBody(requests[3])
            assertEquals("/api/v1/affiliates/aff_123/unarchive", requests[4].url.encodedPath)
            assertNoTextBody(requests[4])
        }

    @Test
    fun `affiliate override endpoints send nested paths filters bodies and delete`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val page =
                whop.affiliates.overrides.list(
                    "aff_123",
                    ListAffiliateOverridesRequest(
                        first = 5,
                        overrideType = AffiliateOverrideRole.Standard,
                    ),
                )
            val standard =
                whop.affiliates.overrides.create(
                    "aff_123",
                    CreateStandardAffiliateOverrideRequest(
                        id = "aff_123",
                        commissionValue = 6.9,
                        planId = "plan_123",
                        appliesToPayments = AffiliateAppliesToPayments.AllPayments,
                        commissionType = AffiliatePayoutType.Percentage,
                    ),
                )
            val revShare =
                whop.affiliates.overrides.create(
                    "aff_123",
                    CreateRevShareAffiliateOverrideRequest(
                        id = "aff_123",
                        commissionValue = 12.5,
                        productId = "prod_123",
                        revenueBasis = AffiliateRevenueBasis.PostFees,
                        commissionType = AffiliatePayoutType.Percentage,
                    ),
                )
            whop.affiliates.overrides.retrieve("aff_123", "affov_123")
            whop.affiliates.overrides.update(
                "aff_123",
                "affov_123",
                UpdateAffiliateOverrideRequest(commissionValue = 8.0),
            )
            val deleted = whop.affiliates.overrides.delete("aff_123", "affov_123")

            assertEquals("affov_123", page.data.single().id)
            assertEquals("affov_123", standard.id)
            assertEquals("affov_123", revShare.id)
            assertTrue(deleted)
            assertEquals("/api/v1/affiliates/aff_123/overrides", requests[0].url.encodedPath)
            assertEquals("5", requests[0].url.parameters["first"])
            assertEquals("standard", requests[0].url.parameters["override_type"])
            assertEquals("POST", requests[1].method.value)
            assertTrue(requests[1].bodyText().contains("override_type"))
            assertTrue(requests[1].bodyText().contains("standard"))
            assertTrue(requests[1].bodyText().contains("plan_id"))
            assertTrue(requests[1].bodyText().contains("all_payments"))
            assertEquals("POST", requests[2].method.value)
            assertTrue(requests[2].bodyText().contains("rev_share"))
            assertTrue(requests[2].bodyText().contains("product_id"))
            assertTrue(requests[2].bodyText().contains("post_fees"))
            assertEquals("/api/v1/affiliates/aff_123/overrides/affov_123", requests[3].url.encodedPath)
            assertEquals("PATCH", requests[4].method.value)
            assertTrue(requests[4].bodyText().contains("commission_value"))
            assertEquals("DELETE", requests[5].method.value)
            assertEquals("/api/v1/affiliates/aff_123/overrides/affov_123", requests[5].url.encodedPath)
        }

    @Test
    fun `affiliate path parameters reject path injection characters`() =
        runTest {
            val whop = clientWithEngine(recordingEngine(mutableListOf()))

            assertFailsWith<IllegalArgumentException> { whop.affiliates.retrieve("aff_123/other") }
            assertFailsWith<IllegalArgumentException> { whop.affiliates.archive("../aff_123") }
            assertFailsWith<IllegalArgumentException> { whop.affiliates.overrides.list("aff_123?other") }
            assertFailsWith<IllegalArgumentException> { whop.affiliates.overrides.retrieve("aff_123", "affov_123%2Fother") }
            assertFailsWith<IllegalArgumentException> { whop.affiliates.overrides.delete("aff_123/other", "affov_123") }
        }

    @Test
    fun `affiliate request validation rejects invalid inputs`() {
        assertFailsWith<IllegalArgumentException> { ListAffiliatesRequest(companyId = " ") }
        assertFailsWith<IllegalArgumentException> { ListAffiliatesRequest(companyId = "biz_123", first = 0) }
        assertFailsWith<IllegalArgumentException> { ListAffiliateOverridesRequest(before = "") }
        assertFailsWith<IllegalArgumentException> { CreateAffiliateRequest(companyId = "biz_123", userIdentifier = " ") }
        assertFailsWith<IllegalArgumentException> {
            CreateStandardAffiliateOverrideRequest(id = "aff_123", commissionValue = -1.0, planId = "plan_123")
        }
        assertFailsWith<IllegalArgumentException> {
            CreateStandardAffiliateOverrideRequest(id = "aff_123", commissionValue = 1.0, planId = "")
        }
        assertFailsWith<IllegalArgumentException> {
            CreateStandardAffiliateOverrideRequest(
                id = "aff_123",
                commissionValue = 101.0,
                planId = "plan_123",
                commissionType = AffiliatePayoutType.Percentage,
            )
        }
        assertFailsWith<IllegalArgumentException> {
            CreateRevShareAffiliateOverrideRequest(id = "aff_123", commissionValue = 1.0, productId = "")
        }
        assertFailsWith<IllegalArgumentException> {
            CreateRevShareAffiliateOverrideRequest(
                id = "aff_123",
                commissionValue = 0.5,
                commissionType = AffiliatePayoutType.Percentage,
            )
        }
        assertFailsWith<IllegalArgumentException> { UpdateAffiliateOverrideRequest(commissionValue = -0.1) }
    }

    @Test
    fun `affiliate override create rejects mismatched path and body affiliate ids`() =
        runTest {
            val whop = clientWithEngine(recordingEngine(mutableListOf()))

            assertFailsWith<IllegalArgumentException> {
                whop.affiliates.overrides.create(
                    "aff_123",
                    CreateStandardAffiliateOverrideRequest(
                        id = "aff_other",
                        commissionValue = 6.9,
                        planId = "plan_123",
                    ),
                )
            }
        }

    private fun recordingEngine(requests: MutableList<HttpRequestData>): MockEngine =
        MockEngine { request ->
            requests += request
            respond(defaultJsonResponse(request.url.encodedPath, request.method.value), headers = jsonHeaders)
        }

    private fun defaultJsonResponse(
        path: String,
        method: String,
    ): String =
        when {
            path == "/api/v1/affiliates" && method == "GET" -> pageJson(affiliateJson())
            path == "/api/v1/affiliates" -> affiliateJson()
            path.endsWith("/archive") -> "true"
            path.endsWith("/unarchive") -> "true"
            path == "/api/v1/affiliates/aff_123/overrides" && method == "GET" -> pageJson(overrideJson())
            path.startsWith("/api/v1/affiliates/aff_123/overrides/") && method == "DELETE" -> "true"
            path.contains("/overrides") -> overrideJson()
            path.startsWith("/api/v1/affiliates/") -> affiliateJson()
            else -> "{}"
        }

    private fun pageJson(item: String): String =
        """
        {"data":[$item],"page_info":{"has_next_page":false}}
        """.trimIndent()

    private fun affiliateJson(): String =
        """
        {
          "id":"aff_123",
          "status":"active",
          "created_at":"2023-12-01T05:00:00.401Z",
          "updated_at":"2023-12-02T05:00:00.401Z",
          "total_referrals_count":42,
          "total_referral_earnings_usd":"${'$'}12.34",
          "total_overrides_count":2,
          "customer_retention_rate":"50%",
          "customer_retention_rate_ninety_days":"40%",
          "monthly_recurring_revenue_usd":"${'$'}100.00",
          "total_revenue_usd":"${'$'}500.00",
          "active_members_count":7,
          "user":{"id":"user_123","name":null,"username":"dean"},
          "company":{"id":"biz_123","title":"Company"}
        }
        """.trimIndent()

    private fun overrideJson(): String =
        """
        {
          "id":"affov_123",
          "override_type":"standard",
          "commission_type":"percentage",
          "commission_value":6.9,
          "applies_to_payments":"all_payments",
          "plan_id":"plan_123",
          "product_id":null,
          "applies_to_products":null,
          "revenue_basis":"post_fees",
          "product_direct_link":null,
          "checkout_direct_link":null,
          "total_referral_earnings_usd":25.0
        }
        """.trimIndent()

    private fun clientWithEngine(engine: MockEngine): WhopClient {
        val config = WhopClientConfig(apiKey = "test_api_key")
        val httpClient = HttpClient(engine) { expectSuccess = false }
        return WhopClient(config, KtorWhopHttpTransport(config, httpClient))
    }

    private fun HttpRequestData.bodyText(): String = (body as TextContent).text

    private fun assertNoTextBody(request: HttpRequestData) {
        assertFalse(request.body is TextContent)
        assertTrue(request.body is OutgoingContent.NoContent)
    }

    private companion object {
        val jsonHeaders = headersOf(HttpHeaders.ContentType, "application/json")
    }
}
