package dev.lepshee.whop.models.authorizedusers

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class AuthorizedUser(
    val id: String,
    val role: AuthorizedUserRole,
    val user: JsonObject,
    val company: JsonObject,
)

@Serializable
data class CreateAuthorizedUserRequest(
    @SerialName("company_id") val companyId: String,
    val role: AuthorizedUserRole,
    @SerialName("user_id") val userId: String,
    @SerialName("send_emails") val sendEmails: Boolean? = null,
) {
    init {
        require(companyId.isNotBlank()) { "Company ID must not be blank." }
        require(userId.isNotBlank()) { "User ID must not be blank." }
    }
}

@Serializable
enum class AuthorizedUserRole {
    @SerialName("owner")
    Owner,

    @SerialName("admin")
    Admin,

    @SerialName("sales_manager")
    SalesManager,

    @SerialName("moderator")
    Moderator,

    @SerialName("advertiser")
    Advertiser,

    @SerialName("app_manager")
    AppManager,

    @SerialName("support")
    Support,

    @SerialName("manager")
    Manager,

    @SerialName("custom")
    Custom,
}
