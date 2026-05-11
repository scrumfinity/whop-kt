package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.appbuilds.AppBuild
import dev.lepshee.whop.models.appbuilds.AppBuildListItem
import dev.lepshee.whop.models.appbuilds.AppBuildPlatform
import dev.lepshee.whop.models.appbuilds.AppBuildStatus
import dev.lepshee.whop.models.appbuilds.CreateAppBuildRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for app builds. */
class AppBuildsResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun create(
        request: CreateAppBuildRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): AppBuild = http.post("app_builds", request, options)

    suspend fun list(
        request: ListAppBuildsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<AppBuildListItem> = http.get("app_builds", request.toQueryParameters(), options)

    suspend fun retrieve(
        appBuildId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): AppBuild {
        val id = pathParameter(appBuildId, "App build ID")
        return http.get("app_builds/$id", options = options)
    }

    suspend fun promote(
        appBuildId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): AppBuild {
        val id = pathParameter(appBuildId, "App build ID")
        return http.post("app_builds/$id/promote", options)
    }

    fun listAutoPaging(
        request: ListAppBuildsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<AppBuildListItem> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

data class ListAppBuildsRequest(
    val appId: String,
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val platform: AppBuildPlatform? = null,
    val status: AppBuildStatus? = null,
    val createdBefore: String? = null,
    val createdAfter: String? = null,
) {
    init {
        require(appId.isNotBlank()) { "App ID must not be blank." }
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
        require(createdBefore == null || createdBefore.isNotBlank()) { "Created-before value must not be blank." }
        require(createdAfter == null || createdAfter.isNotBlank()) { "Created-after value must not be blank." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            add("app_id" to appId)
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
            platform?.let { add("platform" to it.serialValue) }
            status?.let { add("status" to it.serialValue) }
            createdBefore?.let { add("created_before" to it) }
            createdAfter?.let { add("created_after" to it) }
        }
}

private val AppBuildPlatform.serialValue: String get() =
    when (this) {
        AppBuildPlatform.Ios -> "ios"
        AppBuildPlatform.Android -> "android"
        AppBuildPlatform.Web -> "web"
    }

private val AppBuildStatus.serialValue: String get() =
    when (this) {
        AppBuildStatus.Draft -> "draft"
        AppBuildStatus.Pending -> "pending"
        AppBuildStatus.Approved -> "approved"
        AppBuildStatus.Rejected -> "rejected"
    }
