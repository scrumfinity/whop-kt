package com.lepshee.whop.models.users

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Response from Whop's explicit user access-check endpoint. */
@Serializable
data class AccessCheck(
    /** Whether the user has access to the requested resource. */
    @SerialName("has_access") val hasAccess: Boolean,
    /** Access level Whop assigned for the requested resource. */
    @SerialName("access_level") val accessLevel: AccessLevel,
)

/** Whop access levels returned by user access checks. */
@Serializable
enum class AccessLevel {
    /** User has customer/member access. */
    @SerialName("customer")
    Customer,

    /** User has administrative access. */
    @SerialName("admin")
    Admin,

    /** User has no access. */
    @SerialName("no_access")
    NoAccess,
}
