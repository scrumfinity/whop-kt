package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.users.AccessCheck
import dev.lepshee.whop.models.users.UpdateUserRequest
import dev.lepshee.whop.models.users.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** User operations, including explicit authorization checks. */
class UsersResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    /** Lists/searches users by name or username. */
    suspend fun list(
        request: ListUsersRequest = ListUsersRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<User> = http.get("users", request.toQueryParameters(), options)

    /** Streams users across forward pages by following `page_info.end_cursor`. */
    fun listAutoPaging(
        request: ListUsersRequest = ListUsersRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<User> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }

    /** Retrieves a user by ID, tag, username, or `me`. */
    suspend fun retrieve(
        userId: String,
        companyId: String? = null,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): User {
        val id = pathParameter(userId, "User ID")
        require(companyId == null || companyId.isNotBlank()) { "Company ID must not be blank." }
        return http.get(
            path = "users/$id",
            queryParameters = buildList { companyId?.let { add("company_id" to it) } },
            options = options,
        )
    }

    /** Updates a user profile or company-specific profile override. */
    suspend fun update(
        userId: String,
        request: UpdateUserRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): User {
        val id = pathParameter(userId, "User ID")
        return http.patch("users/$id", request, options)
    }

    /**
     * Checks whether a user has access to a company, product, or experience resource.
     *
     * Resource IDs commonly use `biz_`, `prod_`, or `exp_` prefixes. This method does not hide
     * authorization decisions; callers should branch on [AccessCheck.hasAccess] and
     * [AccessCheck.accessLevel] explicitly.
     */
    suspend fun checkAccess(
        userId: String,
        resourceId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): AccessCheck {
        val userPathId = pathParameter(userId, "User ID")
        val resourcePathId = pathParameter(resourceId, "Resource ID")
        return http.get("users/$userPathId/access/$resourcePathId", options = options)
    }
}

data class ListUsersRequest(
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val query: String? = null,
) {
    init {
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
            query?.let { add("query" to it) }
        }
}
