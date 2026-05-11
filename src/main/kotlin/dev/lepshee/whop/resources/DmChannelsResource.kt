package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.messaging.CreateDmChannelRequest
import dev.lepshee.whop.models.messaging.DmChannel
import dev.lepshee.whop.models.messaging.UpdateDmChannelRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for direct-message channels. */
class DmChannelsResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun create(
        request: CreateDmChannelRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): DmChannel = http.post("dm_channels", request, options)

    suspend fun list(
        request: ListDmChannelsRequest = ListDmChannelsRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<DmChannel> = http.get("dm_channels", request.toQueryParameters(), options)

    suspend fun retrieve(
        dmChannelId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): DmChannel {
        val id = pathParameter(dmChannelId, "DM channel ID")
        return http.get("dm_channels/$id", options = options)
    }

    suspend fun update(
        dmChannelId: String,
        request: UpdateDmChannelRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): DmChannel {
        val id = pathParameter(dmChannelId, "DM channel ID")
        return http.patch("dm_channels/$id", request, options)
    }

    suspend fun delete(
        dmChannelId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Boolean {
        val id = pathParameter(dmChannelId, "DM channel ID")
        return http.delete("dm_channels/$id", options = options)
    }

    fun listAutoPaging(
        request: ListDmChannelsRequest = ListDmChannelsRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<DmChannel> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

data class ListDmChannelsRequest(
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val companyId: String? = null,
) {
    init {
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
        require(companyId == null || companyId.isNotBlank()) { "Company ID must not be blank." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
            companyId?.let { add("company_id" to it) }
        }
}
