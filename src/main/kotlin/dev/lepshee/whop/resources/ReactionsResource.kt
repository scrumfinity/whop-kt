package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.reactions.CreateReactionRequest
import dev.lepshee.whop.models.reactions.DeleteReactionRequest
import dev.lepshee.whop.models.reactions.Reaction
import dev.lepshee.whop.models.reactions.ReactionListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for emoji reactions and forum poll votes. */
class ReactionsResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun list(
        request: dev.lepshee.whop.resources.ListReactionsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<ReactionListItem> = http.get("reactions", request.toQueryParameters(), options)

    suspend fun create(
        request: CreateReactionRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Reaction = http.post("reactions", request, options)

    suspend fun retrieve(
        reactionId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Reaction {
        val id = pathParameter(reactionId, "Reaction ID")
        return http.get("reactions/$id", options = options)
    }

    suspend fun delete(
        reactionId: String,
        request: DeleteReactionRequest = DeleteReactionRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Boolean {
        val id = pathParameter(reactionId, "Reaction ID")
        return http.delete("reactions/$id", request.toQueryParameters(), options)
    }

    fun listAutoPaging(
        request: dev.lepshee.whop.resources.ListReactionsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<ReactionListItem> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

data class ListReactionsRequest(
    val resourceId: String,
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
) {
    init {
        require(resourceId.isNotBlank()) { "Resource ID must not be blank." }
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            add("resource_id" to resourceId)
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
        }
}
