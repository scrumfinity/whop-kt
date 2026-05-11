package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.common.Direction
import dev.lepshee.whop.models.memberships.MembershipStatus
import dev.lepshee.whop.models.users.AccessLevel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.JsonObject

/** Operations for company members. */
class MembersResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun list(
        request: ListMembersRequest =
            ListMembersRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<JsonObject> = http.get("members", request.toQueryParameters(), options)

    suspend fun retrieve(
        memberId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): JsonObject {
        val id = pathParameter(memberId, "Member ID")
        return http.get("members/$id", options = options)
    }

    fun listAutoPaging(
        request: ListMembersRequest =
            ListMembersRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<JsonObject> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

data class ListMembersRequest(
    val companyId: String? = null,
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val direction: Direction? = null,
    val order: MemberOrder? = null,
    val createdBefore: String? = null,
    val createdAfter: String? = null,
    val accessLevel: AccessLevel? = null,
    val productIds: List<String> = emptyList(),
    val planIds: List<String> = emptyList(),
    val userIds: List<String> = emptyList(),
    val statuses: List<MembershipStatus> = emptyList(),
    val promoCodeIds: List<String> = emptyList(),
    val mostRecentActions: List<String> = emptyList(),
    val query: String? = null,
) {
    init {
        require(companyId == null || companyId.isNotBlank()) { "Company ID must not be blank." }
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
        require(productIds.all(String::isNotBlank)) { "Product IDs must not be blank." }
        require(planIds.all(String::isNotBlank)) { "Plan IDs must not be blank." }
        require(userIds.all(String::isNotBlank)) { "User IDs must not be blank." }
        require(promoCodeIds.all(String::isNotBlank)) { "Promo code IDs must not be blank." }
        require(mostRecentActions.all(String::isNotBlank)) { "Most recent actions must not be blank." }
        require(query == null || query.isNotBlank()) { "Query must not be blank." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            companyId?.let { add("company_id" to it) }
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
            direction?.let {
                add(
                    "direction" to
                        if (it ==
                            Direction.Asc
                        ) {
                            "asc"
                        } else {
                            "desc"
                        },
                )
            }
            order?.let { add("order" to it.serialValue) }
            createdBefore?.let { add("created_before" to it) }
            createdAfter?.let { add("created_after" to it) }
            accessLevel?.let { add("access_level" to it.serialValue) }
            productIds.forEach { add("product_ids" to it) }
            planIds.forEach { add("plan_ids" to it) }
            userIds.forEach { add("user_ids" to it) }
            statuses.forEach { add("statuses" to it.serialValue) }
            promoCodeIds.forEach { add("promo_code_ids" to it) }
            mostRecentActions.forEach { add("most_recent_actions" to it) }
            query?.let { add("query" to it) }
        }
}

enum class MemberOrder {
    CreatedAt,
    TotalSpend,
    Username,
}

private val MemberOrder.serialValue: String get() =
    when (this) {
        MemberOrder.CreatedAt -> "created_at"
        MemberOrder.TotalSpend -> "total_spend"
        MemberOrder.Username -> "username"
    }

private val AccessLevel.serialValue: String get() =
    when (this) {
        AccessLevel.Customer -> "customer"
        AccessLevel.Admin -> "admin"
        AccessLevel.NoAccess -> "no_access"
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
