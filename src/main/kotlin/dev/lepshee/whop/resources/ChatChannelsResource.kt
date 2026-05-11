package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.messaging.ChatChannel
import dev.lepshee.whop.models.messaging.UpdateChatChannelRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for experience chat channels. */
class ChatChannelsResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun list(
        request: ListChatChannelsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<ChatChannel> = http.get("chat_channels", request.toQueryParameters(), options)

    suspend fun retrieve(
        chatChannelId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): ChatChannel {
        val id = pathParameter(chatChannelId, "Chat channel ID")
        return http.get("chat_channels/$id", options = options)
    }

    suspend fun update(
        chatChannelId: String,
        request: UpdateChatChannelRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): ChatChannel {
        val id = pathParameter(chatChannelId, "Chat channel ID")
        return http.patch("chat_channels/$id", request, options)
    }

    fun listAutoPaging(
        request: ListChatChannelsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<ChatChannel> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

data class ListChatChannelsRequest(
    val companyId: String,
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val productId: String? = null,
) {
    init {
        require(companyId.isNotBlank()) { "Company ID must not be blank." }
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
        require(productId == null || productId.isNotBlank()) { "Product ID must not be blank." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            add("company_id" to companyId)
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
            productId?.let { add("product_id" to it) }
        }
}
