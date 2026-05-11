package dev.lepshee.whop.models.users

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

/** User profile returned by Whop user endpoints. */
@Serializable
data class User(
    val id: String? = null,
    val username: String,
    val name: String? = null,
    val bio: String? = null,
    @SerialName("profile_picture") val profilePicture: JsonObject? = null,
)

@Serializable
data class UpdateUserRequest(
    @SerialName("company_id") val companyId: String? = null,
    val bio: String? = null,
    val name: String? = null,
    @SerialName("profile_picture") val profilePicture: JsonObject? = null,
    val username: String? = null,
) {
    init {
        require(companyId == null || companyId.isNotBlank()) { "Company ID must not be blank." }
        require(bio == null || bio.isNotBlank()) { "User bio must not be blank." }
        require(name == null || name.isNotBlank()) { "User name must not be blank." }
        require(username == null || username.isNotBlank()) { "Username must not be blank." }
        require(username == null || username.length <= 42) { "Username must be 42 characters or fewer." }
    }
}

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
