package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.messaging.AiChat
import dev.lepshee.whop.models.messaging.AiChatListItem
import dev.lepshee.whop.models.messaging.CreateAiChatRequest
import dev.lepshee.whop.models.messaging.UpdateAiChatRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for AI chat threads. */
class AiChatsResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun create(
        request: CreateAiChatRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): AiChat = http.post("ai_chats", request, options)

    suspend fun list(
        request: ListAiChatsRequest =
            ListAiChatsRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<AiChatListItem> = http.get("ai_chats", request.toQueryParameters(), options)

    suspend fun retrieve(
        aiChatId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): AiChat {
        val id = pathParameter(aiChatId, "AI chat ID")
        return http.get("ai_chats/$id", options = options)
    }

    suspend fun update(
        aiChatId: String,
        request: UpdateAiChatRequest =
            UpdateAiChatRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): AiChat {
        val id = pathParameter(aiChatId, "AI chat ID")
        return http.patch("ai_chats/$id", request, options)
    }

    suspend fun delete(
        aiChatId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Boolean {
        val id = pathParameter(aiChatId, "AI chat ID")
        return http.delete("ai_chats/$id", options = options)
    }

    fun listAutoPaging(
        request: ListAiChatsRequest =
            ListAiChatsRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<AiChatListItem> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

data class ListAiChatsRequest(
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val onlyActiveCrons: Boolean? = null,
) {
    init {
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
            onlyActiveCrons?.let { add("only_active_crons" to it.toString()) }
        }
}
