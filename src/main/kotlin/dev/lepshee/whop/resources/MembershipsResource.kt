package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.common.Direction
import dev.lepshee.whop.models.memberships.AddFreeDaysMembershipRequest
import dev.lepshee.whop.models.memberships.CancelMembershipRequest
import dev.lepshee.whop.models.memberships.Membership
import dev.lepshee.whop.models.memberships.MembershipCancelOption
import dev.lepshee.whop.models.memberships.MembershipOrder
import dev.lepshee.whop.models.memberships.MembershipStatus
import dev.lepshee.whop.models.memberships.PauseMembershipRequest
import dev.lepshee.whop.models.memberships.UpdateMembershipRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for Whop memberships. */
class MembershipsResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    /** Retrieves a membership by membership ID or license key. */
    suspend fun retrieve(
        id: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Membership {
        val membershipId = pathParameter(id, "Membership ID or license key")
        return http.get("memberships/$membershipId", options = options)
    }

    /** Lists memberships using cursor pagination. */
    suspend fun list(
        request: ListMembershipsRequest = ListMembershipsRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<Membership> = http.get("memberships", request.toQueryParameters(), options)

    /** Streams memberships across forward pages by following `page_info.end_cursor`. */
    fun listAutoPaging(
        request: ListMembershipsRequest = ListMembershipsRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<Membership> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }

    /** Updates mutable membership metadata. */
    suspend fun update(
        id: String,
        request: UpdateMembershipRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Membership {
        val membershipId = pathParameter(id, "Membership ID")
        return http.patch("memberships/$membershipId", request, options)
    }

    /** Cancels a membership immediately or at period end, depending on [request]. */
    suspend fun cancel(
        id: String,
        request: CancelMembershipRequest = CancelMembershipRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Membership {
        val membershipId = pathParameter(id, "Membership ID")
        return http.post("memberships/$membershipId/cancel", request, options)
    }

    /** Adds free access days to a membership. */
    suspend fun addFreeDays(
        id: String,
        request: AddFreeDaysMembershipRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Membership {
        val membershipId = pathParameter(id, "Membership ID")
        return http.post("memberships/$membershipId/add_free_days", request, options)
    }

    /** Pauses a membership. */
    suspend fun pause(
        id: String,
        request: PauseMembershipRequest = PauseMembershipRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Membership {
        val membershipId = pathParameter(id, "Membership ID")
        return http.post("memberships/$membershipId/pause", request, options)
    }

    /** Resumes a paused membership. */
    suspend fun resume(
        id: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Membership {
        val membershipId = pathParameter(id, "Membership ID")
        return http.post("memberships/$membershipId/resume", options)
    }

    /** Removes a scheduled cancellation from a membership. */
    suspend fun uncancel(
        id: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Membership {
        val membershipId = pathParameter(id, "Membership ID")
        return http.post("memberships/$membershipId/uncancel", options)
    }
}

/** Cursor and filter options for listing memberships. */
data class ListMembershipsRequest(
    val companyId: String? = null,
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val direction: Direction? = null,
    val order: MembershipOrder? = null,
    val productIds: List<String> = emptyList(),
    val planIds: List<String> = emptyList(),
    val userIds: List<String> = emptyList(),
    val promoCodeIds: List<String> = emptyList(),
    val statuses: List<MembershipStatus> = emptyList(),
    val cancelOptions: List<MembershipCancelOption> = emptyList(),
    val createdBefore: String? = null,
    val createdAfter: String? = null,
) {
    init {
        require(companyId == null || companyId.isNotBlank()) { "Company ID must not be blank." }
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            companyId?.let { add("company_id" to it) }
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
            direction?.let { add("direction" to it.serialValue) }
            order?.let { add("order" to it.serialValue) }
            productIds.forEach { add("product_ids" to it) }
            planIds.forEach { add("plan_ids" to it) }
            userIds.forEach { add("user_ids" to it) }
            promoCodeIds.forEach { add("promo_code_ids" to it) }
            statuses.forEach { add("statuses" to it.serialValue) }
            cancelOptions.forEach { add("cancel_options" to it.serialValue) }
            createdBefore?.let { add("created_before" to it) }
            createdAfter?.let { add("created_after" to it) }
        }
}

private val Direction.serialValue: String get() =
    when (this) {
        Direction.Asc -> "asc"
        Direction.Desc -> "desc"
    }
private val MembershipOrder.serialValue: String get() =
    when (this) {
        MembershipOrder.Id -> "id"
        MembershipOrder.CreatedAt -> "created_at"
        MembershipOrder.Status -> "status"
        MembershipOrder.CanceledAt -> "canceled_at"
        MembershipOrder.DateJoined -> "date_joined"
        MembershipOrder.TotalSpend -> "total_spend"
    }
private val MembershipStatus.serialValue: String get() =
    when (this) {
        MembershipStatus.Trialing -> "trialing"
        MembershipStatus.Active -> "active"
        MembershipStatus.PastDue -> "past_due"
        MembershipStatus.Completed -> "completed"
        MembershipStatus.Canceled -> "canceled"
        MembershipStatus.Expired -> "expired"
        MembershipStatus.Unresolved -> "unresolved"
        MembershipStatus.Drafted -> "drafted"
        MembershipStatus.Canceling -> "canceling"
    }
private val MembershipCancelOption.serialValue: String get() =
    when (this) {
        MembershipCancelOption.TooExpensive -> "too_expensive"
        MembershipCancelOption.Switching -> "switching"
        MembershipCancelOption.MissingFeatures -> "missing_features"
        MembershipCancelOption.TechnicalIssues -> "technical_issues"
        MembershipCancelOption.BadExperience -> "bad_experience"
        MembershipCancelOption.Other -> "other"
        MembershipCancelOption.Testing -> "testing"
    }
