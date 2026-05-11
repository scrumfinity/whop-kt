package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.ads.AdGroup
import dev.lepshee.whop.models.ads.AdGroupListItem
import dev.lepshee.whop.models.ads.CreateAdGroupRequest
import dev.lepshee.whop.models.ads.ExternalAdGroupStatus
import dev.lepshee.whop.models.ads.UpdateAdGroupRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for ad groups. */
class AdGroupsResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun create(
        request: CreateAdGroupRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): AdGroup = http.post("ad_groups", request, options)

    suspend fun list(
        request: ListAdGroupsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<AdGroupListItem> = http.get("ad_groups", request.toQueryParameters(), options)

    suspend fun retrieve(
        adGroupId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): AdGroup {
        val id = pathParameter(adGroupId, "Ad group ID")
        return http.get("ad_groups/$id", options = options)
    }

    suspend fun update(
        adGroupId: String,
        request: UpdateAdGroupRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): AdGroup {
        val id = pathParameter(adGroupId, "Ad group ID")
        return http.patch("ad_groups/$id", request, options)
    }

    suspend fun delete(
        adGroupId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Boolean {
        val id = pathParameter(adGroupId, "Ad group ID")
        return http.delete("ad_groups/$id", options = options)
    }

    fun listAutoPaging(
        request: ListAdGroupsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<AdGroupListItem> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

data class ListAdGroupsRequest(
    val campaignId: String? = null,
    val companyId: String? = null,
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val createdAfter: String? = null,
    val createdBefore: String? = null,
    val query: String? = null,
    val status: ExternalAdGroupStatus? = null,
) {
    init {
        require(listOfNotNull(campaignId, companyId).size == 1) {
            "Specify exactly one of campaign ID or company ID."
        }
        require(campaignId == null || campaignId.isNotBlank()) { "Ad campaign ID must not be blank." }
        require(companyId == null || companyId.isNotBlank()) { "Company ID must not be blank." }
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
        require(createdBefore == null || createdBefore.isNotBlank()) { "Created-before value must not be blank." }
        require(createdAfter == null || createdAfter.isNotBlank()) { "Created-after value must not be blank." }
        require(query == null || query.isNotBlank()) { "Ad group query must not be blank." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            campaignId?.let { add("campaign_id" to it) }
            companyId?.let { add("company_id" to it) }
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
            createdAfter?.let { add("created_after" to it) }
            createdBefore?.let { add("created_before" to it) }
            query?.let { add("query" to it) }
            status?.let { add("status" to it.serialValue) }
        }
}

internal val ExternalAdGroupStatus.serialValue: String get() =
    when (this) {
        ExternalAdGroupStatus.Active -> "active"
        ExternalAdGroupStatus.Paused -> "paused"
        ExternalAdGroupStatus.Inactive -> "inactive"
        ExternalAdGroupStatus.InReview -> "in_review"
        ExternalAdGroupStatus.Rejected -> "rejected"
        ExternalAdGroupStatus.Flagged -> "flagged"
    }
