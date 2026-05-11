package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.common.Direction
import dev.lepshee.whop.models.messaging.CreateMessageRequest
import dev.lepshee.whop.models.messaging.Message
import dev.lepshee.whop.models.messaging.MessageListItem
import dev.lepshee.whop.models.messaging.UpdateMessageRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for messages in experience chats, direct messages, and group chats. */
class MessagesResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun create(
        request: CreateMessageRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Message = http.post("messages", request, options)

    suspend fun list(
        request: ListMessagesRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<MessageListItem> = http.get("messages", request.toQueryParameters(), options)

    suspend fun retrieve(
        messageId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Message {
        val id = pathParameter(messageId, "Message ID")
        return http.get("messages/$id", options = options)
    }

    suspend fun update(
        messageId: String,
        request: UpdateMessageRequest =
            UpdateMessageRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Message {
        val id = pathParameter(messageId, "Message ID")
        return http.patch("messages/$id", request, options)
    }

    suspend fun delete(
        messageId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Boolean {
        val id = pathParameter(messageId, "Message ID")
        return http.delete("messages/$id", options = options)
    }

    fun listAutoPaging(
        request: ListMessagesRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<MessageListItem> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

data class ListMessagesRequest(
    val channelId: String,
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val direction: Direction? = null,
) {
    init {
        require(channelId.isNotBlank()) { "Channel ID must not be blank." }
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            add("channel_id" to channelId)
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
            direction?.let { add("direction" to it.serialValue) }
        }
}

private val Direction.serialValue: String get() =
    when (this) {
        Direction.Asc -> "asc"
        Direction.Desc -> "desc"
    }
