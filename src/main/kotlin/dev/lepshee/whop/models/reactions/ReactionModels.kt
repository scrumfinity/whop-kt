package dev.lepshee.whop.models.reactions

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReactionListItem(
    val id: String,
    @SerialName("resource_id") val resourceId: String,
    val user: ReactionUser,
    val emoji: String? = null,
)

@Serializable
data class Reaction(
    val id: String,
    @SerialName("resource_id") val resourceId: String,
    val user: ReactionUser,
    val emoji: String? = null,
)

@Serializable
data class ReactionUser(
    val id: String,
    val username: String,
    val name: String? = null,
)

@Serializable
data class CreateReactionRequest(
    @SerialName("resource_id") val resourceId: String,
    val emoji: String? = null,
    @SerialName("poll_option_id") val pollOptionId: String? = null,
) {
    init {
        require(resourceId.isNotBlank()) { "Resource ID must not be blank." }
        require(emoji == null || emoji.isNotBlank()) { "Reaction emoji must not be blank." }
        require(pollOptionId == null || pollOptionId.isNotBlank()) { "Poll option ID must not be blank." }
    }
}

data class DeleteReactionRequest(
    val emoji: String? = null,
) {
    init {
        require(emoji == null || emoji.isNotBlank()) { "Reaction emoji must not be blank." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            emoji?.let { add("emoji" to it) }
        }
}
