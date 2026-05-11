package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.messaging.CreateDmMemberRequest
import dev.lepshee.whop.models.messaging.DmMember
import dev.lepshee.whop.models.messaging.UpdateDmMemberRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for direct-message channel members. */
class DmMembersResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun create(
        request: CreateDmMemberRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): DmMember = http.post("dm_members", request, options)

    suspend fun list(
        request: ListDmMembersRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<DmMember> = http.get("dm_members", request.toQueryParameters(), options)

    suspend fun retrieve(
        dmMemberId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): DmMember {
        val id = pathParameter(dmMemberId, "DM member ID")
        return http.get("dm_members/$id", options = options)
    }

    suspend fun update(
        dmMemberId: String,
        request: UpdateDmMemberRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): DmMember {
        val id = pathParameter(dmMemberId, "DM member ID")
        return http.patch("dm_members/$id", request, options)
    }

    suspend fun delete(
        dmMemberId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Boolean {
        val id = pathParameter(dmMemberId, "DM member ID")
        return http.delete("dm_members/$id", options = options)
    }

    fun listAutoPaging(
        request: ListDmMembersRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<DmMember> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

data class ListDmMembersRequest(
    val channelId: String,
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
) {
    init {
        require(channelId.isNotBlank()) { "DM channel ID must not be blank." }
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
        }
}
