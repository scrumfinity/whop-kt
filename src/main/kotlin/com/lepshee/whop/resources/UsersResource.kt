package com.lepshee.whop.resources

import com.lepshee.whop.WhopRequestOptions
import com.lepshee.whop.core.WhopHttpExecutor
import com.lepshee.whop.models.users.AccessCheck

/** User operations, including explicit authorization checks. */
class UsersResource internal constructor(
    private val http: WhopHttpExecutor,
) {
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
        require(userId.isNotBlank()) { "User ID must not be blank." }
        require(resourceId.isNotBlank()) { "Resource ID must not be blank." }
        return http.get("users/$userId/access/$resourceId", options = options)
    }
}
