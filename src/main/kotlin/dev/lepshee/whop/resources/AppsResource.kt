package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.apps.App
import dev.lepshee.whop.models.apps.AppListItem
import dev.lepshee.whop.models.apps.AppOrder
import dev.lepshee.whop.models.apps.AppType
import dev.lepshee.whop.models.apps.AppViewType
import dev.lepshee.whop.models.apps.CreateAppRequest
import dev.lepshee.whop.models.apps.UpdateAppRequest
import dev.lepshee.whop.models.common.Direction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for Whop apps. */
class AppsResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun create(
        request: CreateAppRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): App = http.post("apps", request, options)

    suspend fun list(
        request: ListAppsRequest =
            ListAppsRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<AppListItem> = http.get("apps", request.toQueryParameters(), options)

    suspend fun retrieve(
        appId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): App {
        val id = pathParameter(appId, "App ID")
        return http.get("apps/$id", options = options)
    }

    suspend fun update(
        appId: String,
        request: UpdateAppRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): App {
        val id = pathParameter(appId, "App ID")
        return http.patch("apps/$id", request, options)
    }

    fun listAutoPaging(
        request: ListAppsRequest =
            ListAppsRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<AppListItem> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

data class ListAppsRequest(
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val appType: AppType? = null,
    val companyId: String? = null,
    val direction: Direction? = null,
    val order: AppOrder? = null,
    val query: String? = null,
    val verifiedAppsOnly: Boolean? = null,
    val viewType: AppViewType? = null,
) {
    init {
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
        require(companyId == null || companyId.isNotBlank()) { "Company ID must not be blank." }
        require(query == null || query.isNotBlank()) { "Query must not be blank." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
            appType?.let { add("app_type" to it.serialValue) }
            companyId?.let { add("company_id" to it) }
            direction?.let { add("direction" to it.serialValue) }
            order?.let { add("order" to it.serialValue) }
            query?.let { add("query" to it) }
            verifiedAppsOnly?.let { add("verified_apps_only" to it.toString()) }
            viewType?.let { add("view_type" to it.serialValue) }
        }
}

internal val AppType.serialValue: String get() =
    when (this) {
        AppType.B2BApp -> "b2b_app"
        AppType.B2CApp -> "b2c_app"
        AppType.CompanyApp -> "company_app"
        AppType.Component -> "component"
    }

internal val AppViewType.serialValue: String get() =
    when (this) {
        AppViewType.Hub -> "hub"
        AppViewType.Discover -> "discover"
        AppViewType.Dash -> "dash"
        AppViewType.Dashboard -> "dashboard"
        AppViewType.Analytics -> "analytics"
        AppViewType.Skills -> "skills"
        AppViewType.Openapi -> "openapi"
    }

private val Direction.serialValue: String get() =
    when (this) {
        Direction.Asc -> "asc"
        Direction.Desc -> "desc"
    }

private val AppOrder.serialValue: String get() =
    when (this) {
        AppOrder.CreatedAt -> "created_at"
        AppOrder.DiscoverableAt -> "discoverable_at"
        AppOrder.TotalInstallsLast30Days -> "total_installs_last_30_days"
        AppOrder.TotalInstallsLast7Days -> "total_installs_last_7_days"
        AppOrder.TimeSpent -> "time_spent"
        AppOrder.TimeSpentLast24Hours -> "time_spent_last_24_hours"
        AppOrder.DailyActiveUsers -> "daily_active_users"
        AppOrder.AiPromptCount -> "ai_prompt_count"
        AppOrder.TotalAiCostUsd -> "total_ai_cost_usd"
        AppOrder.TotalAiTokens -> "total_ai_tokens"
        AppOrder.LastAiPromptAt -> "last_ai_prompt_at"
        AppOrder.AiAverageRating -> "ai_average_rating"
    }
