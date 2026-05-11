package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.common.Direction
import dev.lepshee.whop.models.common.VisibilityFilter
import dev.lepshee.whop.models.plans.CreatePlanRequest
import dev.lepshee.whop.models.plans.Plan
import dev.lepshee.whop.models.plans.PlanOrder
import dev.lepshee.whop.models.plans.PlanType
import dev.lepshee.whop.models.plans.UpdatePlanRequest
import dev.lepshee.whop.models.products.ReleaseMethod
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for Whop pricing plans. */
class PlansResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    /** Creates a pricing plan for a product. */
    suspend fun create(
        request: CreatePlanRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Plan = http.post("plans", request, options)

    /** Retrieves a plan by ID. */
    suspend fun retrieve(
        planId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Plan {
        val id = pathParameter(planId, "Plan ID")
        return http.get("plans/$id", options = options)
    }

    /** Updates mutable plan fields. */
    suspend fun update(
        planId: String,
        request: UpdatePlanRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Plan {
        val id = pathParameter(planId, "Plan ID")
        return http.patch("plans/$id", request, options)
    }

    /** Deletes a plan by ID and returns Whop's boolean deletion result. */
    suspend fun delete(
        planId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Boolean {
        val id = pathParameter(planId, "Plan ID")
        return http.delete("plans/$id", options = options)
    }

    /** Lists plans for a company using Whop cursor pagination. */
    suspend fun list(
        request: ListPlansRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<Plan> =
        http.get(
            path = "plans",
            queryParameters = request.toQueryParameters(),
            options = options,
        )

    /** Streams plans across forward pages by following `page_info.end_cursor`. */
    fun listAutoPaging(
        request: ListPlansRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<Plan> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

/** Query parameters for listing plans. */
data class ListPlansRequest(
    val companyId: String,
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val direction: Direction? = null,
    val order: PlanOrder? = null,
    val releaseMethods: List<ReleaseMethod> = emptyList(),
    val visibilities: List<VisibilityFilter> = emptyList(),
    val planTypes: List<PlanType> = emptyList(),
    val productIds: List<String> = emptyList(),
    val createdBefore: String? = null,
    val createdAfter: String? = null,
) {
    init {
        require(companyId.isNotBlank()) { "Company ID must not be blank." }
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
        require(productIds.none(String::isBlank)) { "Product IDs must not be blank." }
        require(createdBefore == null || createdBefore.isNotBlank()) { "Created-before value must not be blank." }
        require(createdAfter == null || createdAfter.isNotBlank()) { "Created-after value must not be blank." }
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
            releaseMethods.forEach { add("release_methods" to it.serialValue) }
            visibilities.forEach { add("visibilities" to it.serialValue) }
            planTypes.forEach { add("plan_types" to it.serialValue) }
            productIds.forEach { add("product_ids" to it) }
            createdBefore?.let { add("created_before" to it) }
            createdAfter?.let { add("created_after" to it) }
        }
}

private val Direction.serialValue: String
    get() =
        when (this) {
            Direction.Asc -> "asc"
            Direction.Desc -> "desc"
        }

private val PlanOrder.serialValue: String
    get() =
        when (this) {
            PlanOrder.Id -> "id"
            PlanOrder.ActiveMembersCount -> "active_members_count"
            PlanOrder.CreatedAt -> "created_at"
            PlanOrder.InternalNotes -> "internal_notes"
            PlanOrder.ExpiresAt -> "expires_at"
        }

private val ReleaseMethod.serialValue: String
    get() =
        when (this) {
            ReleaseMethod.BuyNow -> "buy_now"
            ReleaseMethod.Waitlist -> "waitlist"
        }

private val VisibilityFilter.serialValue: String
    get() =
        when (this) {
            VisibilityFilter.Visible -> "visible"
            VisibilityFilter.Hidden -> "hidden"
            VisibilityFilter.Archived -> "archived"
            VisibilityFilter.QuickLink -> "quick_link"
            VisibilityFilter.All -> "all"
            VisibilityFilter.NotQuickLink -> "not_quick_link"
            VisibilityFilter.NotArchived -> "not_archived"
        }

private val PlanType.serialValue: String
    get() =
        when (this) {
            PlanType.Renewal -> "renewal"
            PlanType.OneTime -> "one_time"
        }
