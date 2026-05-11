package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.ads.AdCampaign
import dev.lepshee.whop.models.ads.AdCampaignListItem
import dev.lepshee.whop.models.ads.AdCampaignStatus
import dev.lepshee.whop.models.ads.CreateAdCampaignRequest
import dev.lepshee.whop.models.ads.UpdateAdCampaignRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for ad campaigns. */
class AdCampaignsResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun create(
        request: CreateAdCampaignRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): AdCampaign = http.post("ad_campaigns", request, options)

    suspend fun list(
        request: ListAdCampaignsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<AdCampaignListItem> = http.get("ad_campaigns", request.toQueryParameters(), options)

    suspend fun retrieve(
        adCampaignId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): AdCampaign {
        val id = pathParameter(adCampaignId, "Ad campaign ID")
        return http.get("ad_campaigns/$id", options = options)
    }

    suspend fun update(
        adCampaignId: String,
        request: UpdateAdCampaignRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): AdCampaign {
        val id = pathParameter(adCampaignId, "Ad campaign ID")
        return http.patch("ad_campaigns/$id", request, options)
    }

    suspend fun pause(
        adCampaignId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): AdCampaign {
        val id = pathParameter(adCampaignId, "Ad campaign ID")
        return http.post("ad_campaigns/$id/pause", options)
    }

    suspend fun unpause(
        adCampaignId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): AdCampaign {
        val id = pathParameter(adCampaignId, "Ad campaign ID")
        return http.post("ad_campaigns/$id/unpause", options)
    }

    fun listAutoPaging(
        request: ListAdCampaignsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<AdCampaignListItem> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

data class ListAdCampaignsRequest(
    val companyId: String,
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val status: AdCampaignStatus? = null,
    val createdBefore: String? = null,
    val createdAfter: String? = null,
    val query: String? = null,
) {
    init {
        require(companyId.isNotBlank()) { "Company ID must not be blank." }
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
        require(createdBefore == null || createdBefore.isNotBlank()) { "Created-before value must not be blank." }
        require(createdAfter == null || createdAfter.isNotBlank()) { "Created-after value must not be blank." }
        require(query == null || query.isNotBlank()) { "Ad campaign query must not be blank." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            add("company_id" to companyId)
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
            status?.let { add("status" to it.serialValue) }
            createdBefore?.let { add("created_before" to it) }
            createdAfter?.let { add("created_after" to it) }
            query?.let { add("query" to it) }
        }
}

internal val AdCampaignStatus.serialValue: String get() =
    when (this) {
        AdCampaignStatus.Active -> "active"
        AdCampaignStatus.Paused -> "paused"
        AdCampaignStatus.Inactive -> "inactive"
        AdCampaignStatus.Stale -> "stale"
        AdCampaignStatus.PendingRefund -> "pending_refund"
        AdCampaignStatus.PaymentFailed -> "payment_failed"
        AdCampaignStatus.Draft -> "draft"
        AdCampaignStatus.InReview -> "in_review"
        AdCampaignStatus.Flagged -> "flagged"
        AdCampaignStatus.Importing -> "importing"
        AdCampaignStatus.Imported -> "imported"
    }
