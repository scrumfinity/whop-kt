package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.common.Direction
import dev.lepshee.whop.models.disputes.Dispute
import dev.lepshee.whop.models.disputes.DisputeListItem
import dev.lepshee.whop.models.disputes.UpdateDisputeEvidenceRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for payment disputes and dispute evidence. */
class DisputesResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun list(
        request: ListDisputesRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<DisputeListItem> = http.get("disputes", request.toQueryParameters(), options)

    suspend fun retrieve(
        disputeId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Dispute {
        val id = pathParameter(disputeId, "Dispute ID")
        return http.get("disputes/$id", options = options)
    }

    suspend fun submitEvidence(
        disputeId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Dispute {
        val id = pathParameter(disputeId, "Dispute ID")
        return http.post("disputes/$id/submit_evidence", options)
    }

    suspend fun updateEvidence(
        disputeId: String,
        request: UpdateDisputeEvidenceRequest = UpdateDisputeEvidenceRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Dispute {
        val id = pathParameter(disputeId, "Dispute ID")
        return http.post("disputes/$id/update_evidence", request, options)
    }

    fun listAutoPaging(
        request: ListDisputesRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<DisputeListItem> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

data class ListDisputesRequest(
    val companyId: String,
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val direction: Direction? = null,
    val createdBefore: String? = null,
    val createdAfter: String? = null,
) {
    init {
        require(companyId.isNotBlank()) { "Company ID must not be blank." }
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
        require(createdBefore == null || createdBefore.isNotBlank()) { "Created-before timestamp must not be blank." }
        require(createdAfter == null || createdAfter.isNotBlank()) { "Created-after timestamp must not be blank." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            add("company_id" to companyId)
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
            direction?.let { add("direction" to if (it == Direction.Asc) "asc" else "desc") }
            createdBefore?.let { add("created_before" to it) }
            createdAfter?.let { add("created_after" to it) }
        }
}
