package dev.lepshee.whop.models.forums

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForumPostListItem(
    val id: String,
    val title: String? = null,
    val content: String? = null,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("is_edited") val isEdited: Boolean,
    @SerialName("is_poster_admin") val isPosterAdmin: Boolean,
    @SerialName("is_pinned") val isPinned: Boolean,
    @SerialName("parent_id") val parentId: String? = null,
    val user: ForumPostUser,
    val attachments: List<ForumPostAttachment> = emptyList(),
    @SerialName("view_count") val viewCount: Int? = null,
    @SerialName("like_count") val likeCount: Int? = null,
    @SerialName("comment_count") val commentCount: Int,
)

@Serializable
data class ForumPost(
    val id: String,
    val title: String? = null,
    val content: String? = null,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("is_edited") val isEdited: Boolean,
    @SerialName("is_poster_admin") val isPosterAdmin: Boolean,
    @SerialName("is_pinned") val isPinned: Boolean,
    @SerialName("parent_id") val parentId: String? = null,
    val user: ForumPostUser,
    val attachments: List<ForumPostAttachment> = emptyList(),
    @SerialName("view_count") val viewCount: Int? = null,
    @SerialName("like_count") val likeCount: Int? = null,
    @SerialName("comment_count") val commentCount: Int,
)

@Serializable
data class ForumPostUser(
    val id: String,
    val username: String,
    val name: String? = null,
)

@Serializable
data class ForumPostAttachment(
    val id: String,
    val filename: String? = null,
    @SerialName("content_type") val contentType: String? = null,
    val url: String? = null,
)

@Serializable
data class CreateForumPostRequest(
    @SerialName("experience_id") val experienceId: String,
    @SerialName("company_id") val companyId: String? = null,
    val content: String? = null,
    @SerialName("rich_content") val richContent: String? = null,
    val title: String? = null,
    val attachments: List<ForumPostAttachmentInput>? = null,
    val poll: ForumPostPollInput? = null,
    @SerialName("parent_id") val parentId: String? = null,
    val pinned: Boolean? = null,
    @SerialName("paywall_amount") val paywallAmount: Double? = null,
    @SerialName("paywall_currency") val paywallCurrency: String? = null,
    @SerialName("is_mention") val isMention: Boolean? = null,
    val visibility: ForumPostVisibilityTypes? = null,
) {
    init {
        require(experienceId.isNotBlank()) { "Experience ID must not be blank." }
        require(companyId == null || companyId.isNotBlank()) { "Company ID must not be blank." }
        require(experienceId != "public" || companyId != null) { "Company ID is required for public forum posts." }
        require(content == null || content.isNotBlank()) { "Forum post content must not be blank." }
        require(richContent == null || richContent.isNotBlank()) { "Forum post rich content must not be blank." }
        require(title == null || title.isNotBlank()) { "Forum post title must not be blank." }
        require(parentId == null || parentId.isNotBlank()) { "Parent post ID must not be blank." }
        require(paywallAmount == null || paywallAmount > 0.0) { "Paywall amount must be positive." }
        require(paywallCurrency == null || paywallCurrency.isNotBlank()) { "Paywall currency must not be blank." }
    }
}

@Serializable
data class UpdateForumPostRequest(
    val attachments: List<ForumPostAttachmentInput>? = null,
    val content: String? = null,
    @SerialName("is_pinned") val isPinned: Boolean? = null,
    val title: String? = null,
    val visibility: ForumPostVisibilityTypes? = null,
) {
    init {
        require(content == null || content.isNotBlank()) { "Forum post content must not be blank." }
        require(title == null || title.isNotBlank()) { "Forum post title must not be blank." }
    }
}

@Serializable
data class ForumPostAttachmentInput(
    val id: String,
) {
    init {
        require(id.isNotBlank()) { "Attachment file ID must not be blank." }
    }
}

@Serializable
data class ForumPostPollInput(
    val options: List<ForumPostPollOptionInput>,
) {
    init {
        require(options.isNotEmpty()) { "Poll options must not be empty." }
    }
}

@Serializable
data class ForumPostPollOptionInput(
    val id: String,
    val text: String,
) {
    init {
        require(id.isNotBlank()) { "Poll option ID must not be blank." }
        require(text.isNotBlank()) { "Poll option text must not be blank." }
    }
}

@Serializable
enum class ForumPostVisibilityTypes {
    @SerialName("members_only")
    MembersOnly,

    @SerialName("globally_visible")
    GloballyVisible,
}
