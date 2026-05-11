package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.affiliates.Affiliate
import dev.lepshee.whop.models.affiliates.AffiliateListItem
import dev.lepshee.whop.models.affiliates.AffiliateSortableColumn
import dev.lepshee.whop.models.affiliates.AffiliateStatus
import dev.lepshee.whop.models.affiliates.CreateAffiliateRequest
import dev.lepshee.whop.models.common.Direction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for affiliate records and their commission overrides. */
class AffiliatesResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    /** Nested affiliate override operations. */
    val overrides: AffiliateOverridesResource =
        AffiliateOverridesResource(http)

    suspend fun create(
        request: CreateAffiliateRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Affiliate = http.post("affiliates", request, options)

    suspend fun list(
        request: ListAffiliatesRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<AffiliateListItem> = http.get("affiliates", request.toQueryParameters(), options)

    suspend fun retrieve(
        affiliateId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Affiliate {
        val id = pathParameter(affiliateId, "Affiliate ID")
        return http.get("affiliates/$id", options = options)
    }

    suspend fun archive(
        affiliateId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Boolean {
        val id = pathParameter(affiliateId, "Affiliate ID")
        return http.post("affiliates/$id/archive", options)
    }

    suspend fun unarchive(
        affiliateId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Boolean {
        val id = pathParameter(affiliateId, "Affiliate ID")
        return http.post("affiliates/$id/unarchive", options)
    }

    fun listAutoPaging(
        request: ListAffiliatesRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<AffiliateListItem> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

data class ListAffiliatesRequest(
    val companyId: String,
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val direction: Direction? = null,
    val order: AffiliateSortableColumn? = null,
    val query: String? = null,
    val status: AffiliateStatus? = null,
) {
    init {
        require(companyId.isNotBlank()) { "Company ID must not be blank." }
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
        require(query == null || query.isNotBlank()) { "Affiliate query must not be blank." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            add("company_id" to companyId)
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
            direction?.let { add("direction" to it.serialValue) }
            order?.let { add("order" to it.serialValue) }
            query?.let { add("query" to it) }
            status?.let { add("status" to it.serialValue) }
        }
}

private val Direction.serialValue: String get() =
    when (this) {
        Direction.Asc -> "asc"
        Direction.Desc -> "desc"
    }

private val AffiliateSortableColumn.serialValue: String get() =
    when (this) {
        AffiliateSortableColumn.Id -> "id"
        AffiliateSortableColumn.CreatedAt -> "created_at"
        AffiliateSortableColumn.CachedTotalReferrals -> "cached_total_referrals"
        AffiliateSortableColumn.CachedTotalRewards -> "cached_total_rewards"
    }

private val AffiliateStatus.serialValue: String get() =
    when (this) {
        AffiliateStatus.Active -> "active"
        AffiliateStatus.Archived -> "archived"
        AffiliateStatus.Deleted -> "deleted"
    }
