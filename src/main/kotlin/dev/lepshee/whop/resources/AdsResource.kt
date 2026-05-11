package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.ads.Ad
import dev.lepshee.whop.models.ads.AdListItem
import dev.lepshee.whop.models.ads.CreateAdRequest
import dev.lepshee.whop.models.ads.ExternalAdStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for ads. */
class AdsResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun create(
        request: CreateAdRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Ad = http.post("ads", request, options)

    suspend fun list(
        request: ListAdsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<AdListItem> = http.get("ads", request.toQueryParameters(), options)

    suspend fun retrieve(
        adId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Ad {
        val id = pathParameter(adId, "Ad ID")
        return http.get("ads/$id", options = options)
    }

    fun listAutoPaging(
        request: ListAdsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<AdListItem> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

data class ListAdsRequest(
    val adGroupId: String? = null,
    val campaignId: String? = null,
    val companyId: String? = null,
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val createdAfter: String? = null,
    val createdBefore: String? = null,
    val status: ExternalAdStatus? = null,
) {
    init {
        require(listOfNotNull(adGroupId, campaignId, companyId).size == 1) {
            "Specify exactly one of ad group ID, campaign ID, or company ID."
        }
        require(adGroupId == null || adGroupId.isNotBlank()) { "Ad group ID must not be blank." }
        require(campaignId == null || campaignId.isNotBlank()) { "Ad campaign ID must not be blank." }
        require(companyId == null || companyId.isNotBlank()) { "Company ID must not be blank." }
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
        require(createdBefore == null || createdBefore.isNotBlank()) { "Created-before value must not be blank." }
        require(createdAfter == null || createdAfter.isNotBlank()) { "Created-after value must not be blank." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            adGroupId?.let { add("ad_group_id" to it) }
            campaignId?.let { add("campaign_id" to it) }
            companyId?.let { add("company_id" to it) }
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
            createdAfter?.let { add("created_after" to it) }
            createdBefore?.let { add("created_before" to it) }
            status?.let { add("status" to it.serialValue) }
        }
}

internal val ExternalAdStatus.serialValue: String get() =
    when (this) {
        ExternalAdStatus.Active -> "active"
        ExternalAdStatus.Paused -> "paused"
        ExternalAdStatus.Inactive -> "inactive"
        ExternalAdStatus.InReview -> "in_review"
        ExternalAdStatus.Rejected -> "rejected"
        ExternalAdStatus.Flagged -> "flagged"
    }
