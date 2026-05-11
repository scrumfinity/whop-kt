package dev.lepshee.whop.models.notifications

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateNotificationRequest(
    val content: String,
    val title: String,
    @SerialName("company_id") val companyId: String? = null,
    @SerialName("experience_id") val experienceId: String? = null,
    @SerialName("user_ids") val userIds: List<String>? = null,
    @SerialName("icon_user_id") val iconUserId: String? = null,
    val subtitle: String? = null,
    @SerialName("rest_path") val restPath: String? = null,
) {
    init {
        require(content.isNotBlank()) { "Notification content must not be blank." }
        require(title.isNotBlank()) { "Notification title must not be blank." }
        require((companyId == null) != (experienceId == null)) { "Provide exactly one of companyId or experienceId." }
        require(companyId == null || companyId.isNotBlank()) { "Company ID must not be blank." }
        require(experienceId == null || experienceId.isNotBlank()) { "Experience ID must not be blank." }
        require(userIds == null || userIds.all(String::isNotBlank)) { "User IDs must not be blank." }
        require(iconUserId == null || iconUserId.isNotBlank()) { "Icon user ID must not be blank." }
        require(subtitle == null || subtitle.isNotBlank()) { "Subtitle must not be blank." }
        require(restPath == null || restPath.isNotBlank()) { "Rest path must not be blank." }
    }
}

@Serializable
data class NotificationResult(
    val success: Boolean,
)
