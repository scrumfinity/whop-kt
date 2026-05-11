package dev.lepshee.whop

import dev.lepshee.whop.models.ads.AdBudgetType
import dev.lepshee.whop.models.ads.AdCampaignPlatform
import dev.lepshee.whop.models.ads.AdCampaignStatus
import dev.lepshee.whop.models.ads.CreateAdCampaignRequest
import dev.lepshee.whop.models.ads.CreateAdGroupRequest
import dev.lepshee.whop.models.ads.CreateAdRequest
import dev.lepshee.whop.models.ads.ExternalAdGroupStatus
import dev.lepshee.whop.models.ads.ExternalAdStatus
import dev.lepshee.whop.models.ads.UpdateAdCampaignRequest
import dev.lepshee.whop.models.ads.UpdateAdGroupRequest
import dev.lepshee.whop.resources.ListAdCampaignsRequest
import dev.lepshee.whop.resources.ListAdGroupsRequest
import dev.lepshee.whop.resources.ListAdsRequest
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
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AdsMarketingResourceTest {
    @Test
    fun `ad campaign endpoints send documented paths filters bodies and actions`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val page =
                whop.adCampaigns.list(
                    ListAdCampaignsRequest(
                        companyId = "biz_123",
                        status = AdCampaignStatus.Active,
                        createdBefore = "2023-12-01T05:00:00.401Z",
                        createdAfter = "2023-11-01T05:00:00.401Z",
                        query = "launch",
                        first = 10,
                    ),
                )
            val created =
                whop.adCampaigns.create(
                    CreateAdCampaignRequest(
                        companyId = "biz_123",
                        config = jsonConfig("objective" to "sales"),
                        platform = AdCampaignPlatform.Meta,
                        title = "Launch campaign",
                        adCreativeSetIds = listOf("adcs_123"),
                        budgetType = AdBudgetType.Daily,
                        dailyBudget = 25.0,
                        productId = "prod_123",
                        targetCountryCodes = listOf("US"),
                    ),
                )
            whop.adCampaigns.retrieve("adcamp_123")
            whop.adCampaigns.update(
                "adcamp_123",
                UpdateAdCampaignRequest(title = "Updated campaign", budget = 50.0),
            )
            val paused = whop.adCampaigns.pause("adcamp_123")
            whop.adCampaigns.unpause("adcamp_123")

            assertEquals("adcamp_123", page.data.single().id)
            assertEquals("adcamp_123", created.id)
            assertEquals("adcamp_123", paused.id)
            assertEquals("/api/v1/ad_campaigns", requests[0].url.encodedPath)
            assertEquals("biz_123", requests[0].url.parameters["company_id"])
            assertEquals("active", requests[0].url.parameters["status"])
            assertEquals("2023-12-01T05:00:00.401Z", requests[0].url.parameters["created_before"])
            assertEquals("2023-11-01T05:00:00.401Z", requests[0].url.parameters["created_after"])
            assertEquals("launch", requests[0].url.parameters["query"])
            assertEquals("10", requests[0].url.parameters["first"])
            assertEquals("POST", requests[1].method.value)
            assertTrue(requests[1].bodyText().contains("company_id"))
            assertTrue(requests[1].bodyText().contains("ad_creative_set_ids"))
            assertTrue(requests[1].bodyText().contains("daily_budget"))
            assertEquals("/api/v1/ad_campaigns/adcamp_123", requests[2].url.encodedPath)
            assertEquals("PATCH", requests[3].method.value)
            assertTrue(requests[3].bodyText().contains("Updated campaign"))
            assertEquals("/api/v1/ad_campaigns/adcamp_123/pause", requests[4].url.encodedPath)
            assertNoTextBody(requests[4])
            assertEquals("/api/v1/ad_campaigns/adcamp_123/unpause", requests[5].url.encodedPath)
            assertNoTextBody(requests[5])
        }

    @Test
    fun `ad group endpoints send documented paths filters bodies and delete`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val page =
                whop.adGroups.list(
                    ListAdGroupsRequest(
                        campaignId = "adcamp_123",
                        status = ExternalAdGroupStatus.Paused,
                        query = "retargeting",
                        createdAfter = "2023-11-01T05:00:00.401Z",
                        first = 5,
                    ),
                )
            val created =
                whop.adGroups.create(
                    CreateAdGroupRequest(
                        campaignId = "adcamp_123",
                        name = "Retargeting",
                        budgetType = AdBudgetType.Lifetime,
                        budget = 100.0,
                        config = jsonConfig("optimization_goal" to "conversions"),
                        platformConfig = jsonConfig("typename" to "MetaAdGroupPlatformConfigType", "status" to "ACTIVE"),
                    ),
                )
            whop.adGroups.retrieve("adgrp_123")
            whop.adGroups.update("adgrp_123", UpdateAdGroupRequest(name = "Updated ad group"))
            val deleted = whop.adGroups.delete("adgrp_123")

            assertEquals("adgrp_123", page.data.single().id)
            assertEquals("adgrp_123", created.id)
            assertTrue(deleted)
            assertEquals("/api/v1/ad_groups", requests[0].url.encodedPath)
            assertEquals("adcamp_123", requests[0].url.parameters["campaign_id"])
            assertEquals("paused", requests[0].url.parameters["status"])
            assertEquals("retargeting", requests[0].url.parameters["query"])
            assertEquals("2023-11-01T05:00:00.401Z", requests[0].url.parameters["created_after"])
            assertEquals("5", requests[0].url.parameters["first"])
            assertEquals("POST", requests[1].method.value)
            assertTrue(requests[1].bodyText().contains("campaign_id"))
            assertTrue(requests[1].bodyText().contains("platform_config"))
            assertTrue(requests[1].bodyText().contains("ACTIVE"))
            assertEquals("/api/v1/ad_groups/adgrp_123", requests[2].url.encodedPath)
            assertEquals("PATCH", requests[3].method.value)
            assertTrue(requests[3].bodyText().contains("Updated ad group"))
            assertEquals("DELETE", requests[4].method.value)
            assertEquals("/api/v1/ad_groups/adgrp_123", requests[4].url.encodedPath)
        }

    @Test
    fun `ad endpoints send documented paths filters and bodies`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val page =
                whop.ads.list(
                    ListAdsRequest(
                        adGroupId = "adgrp_123",
                        status = ExternalAdStatus.InReview,
                        createdBefore = "2023-12-01T05:00:00.401Z",
                        first = 3,
                    ),
                )
            val created =
                whop.ads.create(
                    CreateAdRequest(
                        adGroupId = "adgrp_123",
                        creativeSetId = "adcs_123",
                        existingPostId = "post_123",
                        platformConfig = jsonConfig("name" to "Ad creative"),
                        status = ExternalAdStatus.Active,
                    ),
                )
            whop.ads.retrieve("xad_123")

            assertEquals("xad_123", page.data.single().id)
            assertEquals("xad_123", created.id)
            assertEquals("/api/v1/ads", requests[0].url.encodedPath)
            assertEquals("adgrp_123", requests[0].url.parameters["ad_group_id"])
            assertEquals("in_review", requests[0].url.parameters["status"])
            assertEquals("2023-12-01T05:00:00.401Z", requests[0].url.parameters["created_before"])
            assertEquals("3", requests[0].url.parameters["first"])
            assertEquals("POST", requests[1].method.value)
            assertTrue(requests[1].bodyText().contains("ad_group_id"))
            assertTrue(requests[1].bodyText().contains("creative_set_id"))
            assertTrue(requests[1].bodyText().contains("platform_config"))
            assertEquals("/api/v1/ads/xad_123", requests[2].url.encodedPath)
        }

    @Test
    fun `ads marketing path parameters reject path injection characters`() =
        runTest {
            val whop = clientWithEngine(recordingEngine(mutableListOf()))

            assertFailsWith<IllegalArgumentException> { whop.adCampaigns.retrieve("adcamp_123/other") }
            assertFailsWith<IllegalArgumentException> { whop.adCampaigns.pause("../adcamp_123") }
            assertFailsWith<IllegalArgumentException> { whop.adGroups.retrieve("adgrp_123%2Fother") }
            assertFailsWith<IllegalArgumentException> { whop.adGroups.delete("adgrp_123?other") }
            assertFailsWith<IllegalArgumentException> { whop.ads.retrieve("xad_123/other") }
        }

    @Test
    fun `ads marketing request validation rejects invalid inputs`() {
        assertFailsWith<IllegalArgumentException> {
            ListAdGroupsRequest(campaignId = "adcamp_123", companyId = "biz_123")
        }
        assertFailsWith<IllegalArgumentException> { ListAdGroupsRequest() }
        assertFailsWith<IllegalArgumentException> { ListAdsRequest(adGroupId = "adgrp_123", campaignId = "adcamp_123") }
        assertFailsWith<IllegalArgumentException> { ListAdsRequest() }
        assertFailsWith<IllegalArgumentException> {
            CreateAdCampaignRequest(companyId = " ", config = jsonConfig(), platform = AdCampaignPlatform.Meta, title = "Launch")
        }
        assertFailsWith<IllegalArgumentException> {
            CreateAdGroupRequest(campaignId = "adcamp_123", budget = -1.0)
        }
        assertFailsWith<IllegalArgumentException> { CreateAdRequest(adGroupId = "adgrp_123", creativeSetId = "") }
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
            path == "/api/v1/ad_campaigns" && method == "GET" -> pageJson(adCampaignListItemJson())
            path.startsWith("/api/v1/ad_campaigns/") -> adCampaignJson()
            path == "/api/v1/ad_campaigns" -> adCampaignJson()
            path == "/api/v1/ad_groups" && method == "GET" -> pageJson(adGroupListItemJson())
            path.startsWith("/api/v1/ad_groups/") && method == "DELETE" -> "true"
            path.startsWith("/api/v1/ad_groups/") -> adGroupJson()
            path == "/api/v1/ad_groups" -> adGroupJson()
            path == "/api/v1/ads" && method == "GET" -> pageJson(adListItemJson())
            path.startsWith("/api/v1/ads/") -> adJson()
            path == "/api/v1/ads" -> adJson()
            else -> "{}"
        }

    private fun pageJson(item: String): String =
        """
        {"data":[$item],"page_info":{"has_next_page":false}}
        """.trimIndent()

    private fun adCampaignListItemJson(): String =
        """
        {
          "id":"adcamp_123",
          "title":"Launch campaign",
          "status":"active",
          "target_country_codes":["US"],
          "daily_budget":25.0,
          "platform":"meta",
          "available_budget":20.0,
          "created_at":"2023-12-01T05:00:00.401Z",
          "updated_at":"2023-12-02T05:00:00.401Z",
          "paused_until":null,
          "clicks_count":1,
          "impressions_count":2,
          "purchases_count":3,
          "remaining_balance":10.0,
          "revenue":100.0,
          "return_on_ad_spend":4.0,
          "todays_spend":5.0,
          "total_credits":50.0,
          "total_spend":30.0,
          "product":{"id":"prod_123","title":"Product","route":"product"}
        }
        """.trimIndent()

    private fun adCampaignJson(): String =
        """
        ${adCampaignListItemJson().dropLast(2)},
          "created_by_user":{"id":"user_123","name":null,"username":"dean"},
          "payment_method":null,
          "billing_ledger_account":null,
          "config":{"objective":"sales"}
        }
        """.trimIndent()

    private fun adGroupJson(): String =
        """
        {
          "id":"adgrp_123",
          "name":"Retargeting",
          "status":"paused",
          "daily_budget":12.0,
          "created_at":"2023-12-01T05:00:00.401Z",
          "updated_at":"2023-12-02T05:00:00.401Z",
          "platform_config":{"typename":"MetaAdGroupPlatformConfigType","platform":"meta"},
          "config":{"optimization_goal":"conversions"},
          "ad_campaign":{"id":"adcamp_123","title":"Launch campaign","platform":"meta","status":"active"}
        }
        """.trimIndent()

    private fun adGroupListItemJson(): String =
        """
        {
          "id":"adgrp_123",
          "name":"Retargeting",
          "status":"paused",
          "daily_budget":12.0,
          "created_at":"2023-12-01T05:00:00.401Z",
          "updated_at":"2023-12-02T05:00:00.401Z",
          "platform_config":{"typename":"MetaAdGroupPlatformConfigType","platform":"meta"},
          "config":{"optimization_goal":"conversions"}
        }
        """.trimIndent()

    private fun adListItemJson(): String =
        """
        {
          "id":"xad_123",
          "status":"active",
          "created_at":"2023-12-01T05:00:00.401Z",
          "updated_at":"2023-12-02T05:00:00.401Z",
          "platform_config":{"typename":"MetaAdPlatformConfigType","platform":"meta"}
        }
        """.trimIndent()

    private fun adJson(): String =
        """
        ${adListItemJson().dropLast(2)},
          "external_ad_group":{"id":"adgrp_123","name":"Retargeting","status":"paused"},
          "external_ad_creative_set":{"id":"adcs_123"}
        }
        """.trimIndent()

    private fun jsonConfig(vararg entries: Pair<String, String>): JsonObject =
        JsonObject(entries.associate { (key, value) -> key to JsonPrimitive(value) })

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
