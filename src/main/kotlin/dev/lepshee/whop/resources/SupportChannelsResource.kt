package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.common.Direction
import dev.lepshee.whop.models.messaging.CreateSupportChannelRequest
import dev.lepshee.whop.models.messaging.MessageChannelOrder
import dev.lepshee.whop.models.messaging.SupportChannel
import dev.lepshee.whop.models.messaging.SupportChannelListItem
import dev.lepshee.whop.models.messaging.SupportChannelView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for company support channels. */
class SupportChannelsResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun create(
        request: CreateSupportChannelRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): SupportChannel = http.post("support_channels", request, options)

    suspend fun list(
        request: ListSupportChannelsRequest = ListSupportChannelsRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<SupportChannelListItem> = http.get("support_channels", request.toQueryParameters(), options)

    suspend fun retrieve(
        supportChannelId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): SupportChannel {
        val id = pathParameter(supportChannelId, "Support channel ID")
        return http.get("support_channels/$id", options = options)
    }

    fun listAutoPaging(
        request: ListSupportChannelsRequest = ListSupportChannelsRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<SupportChannelListItem> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

data class ListSupportChannelsRequest(
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val companyId: String? = null,
    val view: SupportChannelView? = null,
    val open: Boolean? = null,
    val direction: Direction? = null,
    val order: MessageChannelOrder? = null,
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
            view?.let { add("view" to it.serialValue) }
            open?.let { add("open" to it.toString()) }
            direction?.let { add("direction" to it.serialValue) }
            order?.let { add("order" to it.serialValue) }
        }
}

private val Direction.serialValue: String get() =
    when (this) {
        Direction.Asc -> "asc"
        Direction.Desc -> "desc"
    }

private val SupportChannelView.serialValue: String get() =
    when (this) {
        SupportChannelView.All -> "all"
        SupportChannelView.Admin -> "admin"
        SupportChannelView.Customer -> "customer"
    }

private val MessageChannelOrder.serialValue: String get() =
    when (this) {
        MessageChannelOrder.CreatedAt -> "created_at"
        MessageChannelOrder.LastPostSentAt -> "last_post_sent_at"
    }
