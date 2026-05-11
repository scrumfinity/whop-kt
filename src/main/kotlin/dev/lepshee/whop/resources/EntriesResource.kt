package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.common.Direction
import dev.lepshee.whop.models.entries.AsyncJob
import dev.lepshee.whop.models.entries.EntriesOrder
import dev.lepshee.whop.models.entries.Entry
import dev.lepshee.whop.models.entries.EntryStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for waitlist entries. */
class EntriesResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun list(
        request: ListEntriesRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<Entry> = http.get("entries", request.toQueryParameters(), options)

    suspend fun retrieve(
        entryId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Entry {
        val id = pathParameter(entryId, "Entry ID")
        return http.get("entries/$id", options = options)
    }

    suspend fun approve(
        entryId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): AsyncJob {
        val id = pathParameter(entryId, "Entry ID")
        return http.post("entries/$id/approve", options)
    }

    suspend fun deny(
        entryId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Entry {
        val id = pathParameter(entryId, "Entry ID")
        return http.post("entries/$id/deny", options)
    }

    fun listAutoPaging(
        request: ListEntriesRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<Entry> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

data class ListEntriesRequest(
    val companyId: String,
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val direction: Direction? = null,
    val order: EntriesOrder? = null,
    val productIds: List<String> = emptyList(),
    val planIds: List<String> = emptyList(),
    val statuses: List<EntryStatus> = emptyList(),
    val createdBefore: String? = null,
    val createdAfter: String? = null,
) {
    init {
        require(companyId.isNotBlank()) { "Company ID must not be blank." }
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
        require(productIds.all(String::isNotBlank)) { "Product IDs must not be blank." }
        require(planIds.all(String::isNotBlank)) { "Plan IDs must not be blank." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            add("company_id" to companyId)
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
            direction?.let {
                add(
                    "direction" to
                        if (it ==
                            dev.lepshee.whop.models.common.Direction.Asc
                        ) {
                            "asc"
                        } else {
                            "desc"
                        },
                )
            }
            order?.let { add("order" to it.serialValue) }
            productIds.forEach { add("product_ids" to it) }
            planIds.forEach { add("plan_ids" to it) }
            statuses.forEach { add("statuses" to it.serialValue) }
            createdBefore?.let { add("created_before" to it) }
            createdAfter?.let { add("created_after" to it) }
        }
}

private val EntriesOrder.serialValue: String get() =
    when (this) {
        EntriesOrder.Id -> "id"
        EntriesOrder.CreatedAt -> "created_at"
    }

private val EntryStatus.serialValue: String get() =
    when (this) {
        EntryStatus.Drafted -> "drafted"
        EntryStatus.Pending -> "pending"
        EntryStatus.Approved -> "approved"
        EntryStatus.Denied -> "denied"
        EntryStatus.Any -> "any"
    }
