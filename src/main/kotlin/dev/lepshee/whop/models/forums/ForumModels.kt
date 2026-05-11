package dev.lepshee.whop.models.forums

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class ForumListItem(
    val id: String,
    val experience: ForumExperienceSummary,
    @SerialName("who_can_post") val whoCanPost: ForumWhoCanPostTypes,
    @SerialName("who_can_comment") val whoCanComment: ForumWhoCanCommentTypes,
    @SerialName("email_notification_preference") val emailNotificationPreference: ForumEmailNotificationPreferences,
)

@Serializable
data class Forum(
    val id: String,
    val experience: ForumExperienceSummary,
    @SerialName("who_can_post") val whoCanPost: ForumWhoCanPostTypes,
    @SerialName("who_can_comment") val whoCanComment: ForumWhoCanCommentTypes,
    @SerialName("email_notification_preference") val emailNotificationPreference: ForumEmailNotificationPreferences,
)

@Serializable
data class ForumExperienceSummary(
    val id: String,
    val name: String,
)

@Serializable
data class UpdateForumRequest(
    @SerialName("banned_words") val bannedWords: List<String>? = null,
    @SerialName("banner_image") val bannerImage: JsonElement? = null,
    @SerialName("email_notification_preference") val emailNotificationPreference: ForumEmailNotificationPreferences? = null,
    @SerialName("who_can_comment") val whoCanComment: ForumWhoCanCommentTypes? = null,
    @SerialName("who_can_post") val whoCanPost: ForumWhoCanPostTypes? = null,
) {
    init {
        require(bannedWords == null || bannedWords.all(String::isNotBlank)) { "Banned words must not be blank." }
    }
}

@Serializable
data class ForumFileInput(
    val id: String,
) {
    init {
        require(id.isNotBlank()) { "Forum file ID must not be blank." }
    }
}

@Serializable
enum class ForumWhoCanPostTypes {
    @SerialName("everyone")
    Everyone,

    @SerialName("admins")
    Admins,
}

@Serializable
enum class ForumWhoCanCommentTypes {
    @SerialName("everyone")
    Everyone,

    @SerialName("admins")
    Admins,
}

@Serializable
enum class ForumEmailNotificationPreferences {
    @SerialName("all_admin_posts")
    AllAdminPosts,

    @SerialName("only_weekly_summary")
    OnlyWeeklySummary,

    @SerialName("none")
    None,
}
