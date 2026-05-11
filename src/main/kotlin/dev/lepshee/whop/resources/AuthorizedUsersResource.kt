package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.authorizedusers.AuthorizedUser
import dev.lepshee.whop.models.authorizedusers.AuthorizedUserRole
import dev.lepshee.whop.models.authorizedusers.CreateAuthorizedUserRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for company authorized users. */
class AuthorizedUsersResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun create(
        request: CreateAuthorizedUserRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): AuthorizedUser = http.post("authorized_users", request, options)

    suspend fun list(
        request: ListAuthorizedUsersRequest =
            ListAuthorizedUsersRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<AuthorizedUser> = http.get("authorized_users", request.toQueryParameters(), options)

    suspend fun retrieve(
        authorizedUserId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): AuthorizedUser {
        val id = pathParameter(authorizedUserId, "Authorized user ID")
        return http.get("authorized_users/$id", options = options)
    }

    suspend fun delete(
        authorizedUserId: String,
        companyId: String? = null,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Boolean {
        val id = pathParameter(authorizedUserId, "Authorized user ID")
        require(companyId == null || companyId.isNotBlank()) { "Company ID must not be blank." }
        return http.delete(
            path = "authorized_users/$id",
            queryParameters = buildList { companyId?.let { add("company_id" to it) } },
            options = options,
        )
    }

    fun listAutoPaging(
        request: ListAuthorizedUsersRequest =
            ListAuthorizedUsersRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<AuthorizedUser> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

data class ListAuthorizedUsersRequest(
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val companyId: String? = null,
    val userId: String? = null,
    val role: AuthorizedUserRole? = null,
    val createdBefore: String? = null,
    val createdAfter: String? = null,
) {
    init {
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
        require(companyId == null || companyId.isNotBlank()) { "Company ID must not be blank." }
        require(userId == null || userId.isNotBlank()) { "User ID must not be blank." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
            companyId?.let { add("company_id" to it) }
            userId?.let { add("user_id" to it) }
            role?.let { add("role" to it.serialValue) }
            createdBefore?.let { add("created_before" to it) }
            createdAfter?.let { add("created_after" to it) }
        }
}

internal val AuthorizedUserRole.serialValue: String get() =
    when (this) {
        AuthorizedUserRole.Owner -> "owner"
        AuthorizedUserRole.Admin -> "admin"
        AuthorizedUserRole.SalesManager -> "sales_manager"
        AuthorizedUserRole.Moderator -> "moderator"
        AuthorizedUserRole.Advertiser -> "advertiser"
        AuthorizedUserRole.AppManager -> "app_manager"
        AuthorizedUserRole.Support -> "support"
        AuthorizedUserRole.Manager -> "manager"
        AuthorizedUserRole.Custom -> "custom"
    }
