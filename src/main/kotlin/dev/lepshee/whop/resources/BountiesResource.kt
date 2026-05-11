package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.bounties.Bounty
import dev.lepshee.whop.models.bounties.BountyListItem
import dev.lepshee.whop.models.bounties.BountyStatus
import dev.lepshee.whop.models.bounties.CreateBountyRequest
import dev.lepshee.whop.models.bounties.serialValue
import dev.lepshee.whop.models.common.Direction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for workforce bounties. */
class BountiesResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    /** Lists bounties using cursor pagination. */
    suspend fun list(
        request: ListBountiesRequest =
            ListBountiesRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<BountyListItem> = http.get("bounties", request.toQueryParameters(), options)

    /** Creates a workforce bounty. */
    suspend fun create(
        request: CreateBountyRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Bounty = http.post("bounties", request, options)

    /** Retrieves a bounty by ID. */
    suspend fun retrieve(
        bountyId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Bounty {
        val id = pathParameter(bountyId, "Bounty ID")
        return http.get("bounties/$id", options = options)
    }

    /** Streams bounties across forward pages by following `page_info.end_cursor`. */
    fun listAutoPaging(
        request: ListBountiesRequest =
            ListBountiesRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<BountyListItem> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

/** Cursor and filter options for listing bounties. */
data class ListBountiesRequest(
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val experienceId: String? = null,
    val status: BountyStatus? = null,
    val direction: Direction? = null,
) {
    init {
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
        require(experienceId == null || experienceId.isNotBlank()) { "Experience ID must not be blank." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
            experienceId?.let { add("experience_id" to it) }
            status?.let { add("status" to it.serialValue) }
            direction?.let {
                add(
                    "direction" to
                        if (it ==
                            Direction.Asc
                        ) {
                            "asc"
                        } else {
                            "desc"
                        },
                )
            }
        }
}
