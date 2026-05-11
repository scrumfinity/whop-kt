package dev.lepshee.whop.models.messaging

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class ChatChannel(
    val id: String,
    val experience: JsonObject,
    @SerialName("ban_media") val banMedia: Boolean,
    @SerialName("ban_urls") val banUrls: Boolean,
    @SerialName("who_can_post") val whoCanPost: WhoCanPost,
    @SerialName("who_can_react") val whoCanReact: WhoCanReact,
    @SerialName("user_posts_cooldown_seconds") val userPostsCooldownSeconds: Int? = null,
    @SerialName("banned_words") val bannedWords: List<String> = emptyList(),
)

@Serializable
data class AiChatListItem(
    val id: String,
    val title: String? = null,
    @SerialName("notification_preference") val notificationPreference: AiChatNotificationPreference,
    @SerialName("message_count") val messageCount: Int,
    @SerialName("blended_token_usage") val blendedTokenUsage: String,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("last_message_at") val lastMessageAt: String? = null,
    val user: AiChatUser,
)

@Serializable
data class AiChat(
    val id: String,
    val title: String? = null,
    @SerialName("notification_preference") val notificationPreference: AiChatNotificationPreference,
    @SerialName("message_count") val messageCount: Int,
    @SerialName("blended_token_usage") val blendedTokenUsage: String,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("last_message_at") val lastMessageAt: String? = null,
    val user: AiChatUser,
)

@Serializable
data class AiChatUser(
    val id: String,
)

@Serializable
data class CreateAiChatRequest(
    @SerialName("message_text") val messageText: String,
    @SerialName("current_company_id") val currentCompanyId: String? = null,
    @SerialName("message_attachments") val messageAttachments: List<AiChatMessageAttachmentInput>? = null,
    @SerialName("message_source") val messageSource: AiChatMessageSource? = null,
    @SerialName("suggestion_type") val suggestionType: String? = null,
    val title: String? = null,
) {
    init {
        require(messageText.isNotBlank()) { "AI chat message text must not be blank." }
        require(currentCompanyId == null || currentCompanyId.isNotBlank()) { "Current company ID must not be blank." }
        require(suggestionType == null || suggestionType.isNotBlank()) { "AI chat suggestion type must not be blank." }
        require(title == null || title.isNotBlank()) { "AI chat title must not be blank." }
    }
}

@Serializable
data class UpdateAiChatRequest(
    @SerialName("current_company_id") val currentCompanyId: String? = null,
    @SerialName("notification_preference") val notificationPreference: AiChatNotificationPreference? = null,
    val title: String? = null,
) {
    init {
        require(currentCompanyId == null || currentCompanyId.isNotBlank()) { "Current company ID must not be blank." }
        require(title == null || title.isNotBlank()) { "AI chat title must not be blank." }
    }
}

@Serializable
data class AiChatMessageAttachmentInput(
    val id: String,
) {
    init {
        require(id.isNotBlank()) { "Attachment file ID must not be blank." }
    }
}

@Serializable
data class SupportChannelListItem(
    val id: String,
    @SerialName("company_id") val companyId: String? = null,
    @SerialName("custom_name") val customName: String? = null,
    @SerialName("customer_user") val customerUser: SupportChannelCustomerUser? = null,
    @SerialName("resolved_at") val resolvedAt: String? = null,
    @SerialName("last_message_at") val lastMessageAt: String? = null,
)

@Serializable
data class SupportChannel(
    val id: String,
    @SerialName("company_id") val companyId: String? = null,
    @SerialName("custom_name") val customName: String? = null,
    @SerialName("customer_user") val customerUser: SupportChannelCustomerUser? = null,
    @SerialName("resolved_at") val resolvedAt: String? = null,
    @SerialName("last_message_at") val lastMessageAt: String? = null,
)

@Serializable
data class SupportChannelCustomerUser(
    val id: String,
    val name: String? = null,
    val username: String,
)

@Serializable
data class CreateSupportChannelRequest(
    @SerialName("company_id") val companyId: String,
    @SerialName("user_id") val userId: String,
    @SerialName("custom_name") val customName: String? = null,
) {
    init {
        require(companyId.isNotBlank()) { "Company ID must not be blank." }
        require(userId.isNotBlank()) { "User ID must not be blank." }
        require(customName == null || customName.isNotBlank()) { "Support channel custom name must not be blank." }
    }
}

@Serializable
data class UpdateChatChannelRequest(
    @SerialName("ban_media") val banMedia: Boolean? = null,
    @SerialName("ban_urls") val banUrls: Boolean? = null,
    @SerialName("banned_words") val bannedWords: List<String>? = null,
    @SerialName("user_posts_cooldown_seconds") val userPostsCooldownSeconds: Int? = null,
    @SerialName("who_can_post") val whoCanPost: WhoCanPost? = null,
    @SerialName("who_can_react") val whoCanReact: WhoCanReact? = null,
) {
    init {
        require(bannedWords == null || bannedWords.all(String::isNotBlank)) { "Banned words must not be blank." }
        require(userPostsCooldownSeconds == null || userPostsCooldownSeconds >= 0) {
            "User post cooldown seconds must not be negative."
        }
    }
}

@Serializable
data class DmChannel(
    val id: String,
    @SerialName("created_at") val createdAt: String,
    val name: String? = null,
    @SerialName("last_message_at") val lastMessageAt: String? = null,
)

@Serializable
data class CreateDmChannelRequest(
    @SerialName("with_user_ids") val withUserIds: List<String>,
    @SerialName("company_id") val companyId: String? = null,
    @SerialName("custom_name") val customName: String? = null,
) {
    init {
        require(withUserIds.isNotEmpty()) { "DM channel user IDs must not be empty." }
        require(withUserIds.all(String::isNotBlank)) { "DM channel user IDs must not be blank." }
        require(companyId == null || companyId.isNotBlank()) { "Company ID must not be blank." }
        require(customName == null || customName.isNotBlank()) { "DM channel custom name must not be blank." }
    }
}

@Serializable
data class UpdateDmChannelRequest(
    @SerialName("custom_name") val customName: String? = null,
) {
    init {
        require(customName == null || customName.isNotBlank()) { "DM channel custom name must not be blank." }
    }
}

@Serializable
data class DmMember(
    val id: String,
    @SerialName("user_id") val userId: String,
    @SerialName("channel_id") val channelId: String,
    val status: DmMemberStatus,
    @SerialName("last_viewed_at") val lastViewedAt: String? = null,
    @SerialName("notification_preference") val notificationPreference: DmMemberNotificationPreference? = null,
)

@Serializable
data class CreateDmMemberRequest(
    @SerialName("channel_id") val channelId: String,
    @SerialName("user_id") val userId: String,
) {
    init {
        require(channelId.isNotBlank()) { "DM channel ID must not be blank." }
        require(userId.isNotBlank()) { "User ID must not be blank." }
    }
}

@Serializable
data class UpdateDmMemberRequest(
    @SerialName("notification_preference") val notificationPreference: DmMemberNotificationPreference? = null,
    val status: DmMemberStatus? = null,
)

@Serializable
data class MessageListItem(
    val id: String,
    val content: String? = null,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
    val poll: MessagePoll? = null,
    @SerialName("replying_to_message_id") val replyingToMessageId: String? = null,
    @SerialName("is_edited") val isEdited: Boolean,
    @SerialName("is_pinned") val isPinned: Boolean,
    @SerialName("message_type") val messageType: MessageType,
    val mentions: List<String> = emptyList(),
    @SerialName("mentions_everyone") val mentionsEveryone: Boolean,
    val user: MessageAuthor,
    @SerialName("view_count") val viewCount: Int? = null,
    @SerialName("reaction_counts") val reactionCounts: List<MessageReactionCount> = emptyList(),
    @SerialName("poll_votes") val pollVotes: List<MessagePollVote> = emptyList(),
)

@Serializable
data class Message(
    val id: String,
    val content: String? = null,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
    val poll: MessagePoll? = null,
    @SerialName("replying_to_message_id") val replyingToMessageId: String? = null,
    @SerialName("is_edited") val isEdited: Boolean,
    @SerialName("is_pinned") val isPinned: Boolean,
    @SerialName("message_type") val messageType: MessageType,
    val mentions: List<String> = emptyList(),
    @SerialName("mentions_everyone") val mentionsEveryone: Boolean,
    val user: MessageAuthor,
    @SerialName("view_count") val viewCount: Int? = null,
    @SerialName("reaction_counts") val reactionCounts: List<MessageReactionCount> = emptyList(),
    @SerialName("poll_votes") val pollVotes: List<MessagePollVote> = emptyList(),
)

@Serializable
data class MessagePoll(
    val options: List<MessagePollOption>? = null,
)

@Serializable
data class MessagePollOption(
    val id: String,
    val text: String,
)

@Serializable
data class MessageAuthor(
    val id: String,
    val username: String,
    val name: String? = null,
)

@Serializable
data class MessageReactionCount(
    val emoji: String? = null,
    val count: Int,
)

@Serializable
data class MessagePollVote(
    @SerialName("option_id") val optionId: String? = null,
    val count: Int,
)

@Serializable
data class CreateMessageRequest(
    @SerialName("channel_id") val channelId: String,
    val content: String,
    val attachments: List<MessageAttachmentInput>? = null,
    @SerialName("auto_detect_links") val autoDetectLinks: Boolean? = null,
    val poll: MessagePollInput? = null,
    @SerialName("replying_to_message_id") val replyingToMessageId: String? = null,
) {
    init {
        require(channelId.isNotBlank()) { "Channel ID must not be blank." }
        require(content.isNotBlank()) { "Message content must not be blank." }
        require(replyingToMessageId == null || replyingToMessageId.isNotBlank()) { "Replying-to message ID must not be blank." }
    }
}

@Serializable
data class UpdateMessageRequest(
    val attachments: List<MessageAttachmentInput>? = null,
    val content: String? = null,
    @SerialName("is_pinned") val isPinned: Boolean? = null,
) {
    init {
        require(content == null || content.isNotBlank()) { "Message content must not be blank." }
    }
}

@Serializable
data class MessageAttachmentInput(
    val id: String,
) {
    init {
        require(id.isNotBlank()) { "Attachment file ID must not be blank." }
    }
}

@Serializable
data class MessagePollInput(
    val options: List<MessagePollOptionInput>,
) {
    init {
        require(options.isNotEmpty()) { "Poll options must not be empty." }
    }
}

@Serializable
data class MessagePollOptionInput(
    val id: String,
    val text: String,
) {
    init {
        require(id.isNotBlank()) { "Poll option ID must not be blank." }
        require(text.isNotBlank()) { "Poll option text must not be blank." }
    }
}

@Serializable
enum class WhoCanPost {
    @SerialName("everyone")
    Everyone,

    @SerialName("admins")
    Admins,
}

@Serializable
enum class WhoCanReact {
    @SerialName("everyone")
    Everyone,

    @SerialName("no_one")
    NoOne,
}

@Serializable
enum class DmMemberStatus {
    @SerialName("requested")
    Requested,

    @SerialName("accepted")
    Accepted,

    @SerialName("hidden")
    Hidden,

    @SerialName("closed")
    Closed,

    @SerialName("archived")
    Archived,
}

@Serializable
enum class DmMemberNotificationPreference {
    @SerialName("all")
    All,

    @SerialName("mentions")
    Mentions,

    @SerialName("none")
    None,
}

@Serializable
enum class MessageType {
    @SerialName("regular")
    Regular,

    @SerialName("system")
    System,

    @SerialName("automated")
    Automated,
}

@Serializable
enum class AiChatMessageSource {
    @SerialName("manual")
    Manual,

    @SerialName("suggestion")
    Suggestion,

    @SerialName("link")
    Link,
}

@Serializable
enum class AiChatNotificationPreference {
    @SerialName("all")
    All,

    @SerialName("none")
    None,
}

@Serializable
enum class SupportChannelView {
    @SerialName("all")
    All,

    @SerialName("admin")
    Admin,

    @SerialName("customer")
    Customer,
}

@Serializable
enum class MessageChannelOrder {
    @SerialName("created_at")
    CreatedAt,

    @SerialName("last_post_sent_at")
    LastPostSentAt,
}
