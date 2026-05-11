package dev.lepshee.whop.models.accesstokens

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccessToken(
    val token: String,
    @SerialName("expires_at") val expiresAt: String,
)

@Serializable
data class CreateAccessTokenRequest(
    @SerialName("company_id") val companyId: String? = null,
    @SerialName("expires_at") val expiresAt: String? = null,
    @SerialName("scoped_actions") val scopedActions: List<String>? = null,
    @SerialName("user_id") val userId: String? = null,
) {
    init {
        require(companyId == null || companyId.isNotBlank()) { "Company ID must not be blank." }
        require(expiresAt == null || expiresAt.isNotBlank()) { "Expiration timestamp must not be blank." }
        require(scopedActions == null || scopedActions.all(String::isNotBlank)) { "Scoped actions must not be blank." }
        require(userId == null || userId.isNotBlank()) { "User ID must not be blank." }
    }
}
