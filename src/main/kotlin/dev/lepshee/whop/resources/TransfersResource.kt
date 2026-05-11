package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.common.Direction
import dev.lepshee.whop.models.transfers.CreateTransferRequest
import dev.lepshee.whop.models.transfers.Transfer
import dev.lepshee.whop.models.transfers.TransferOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for Whop transfers. */
class TransfersResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun create(
        request: CreateTransferRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Transfer {
        require(request.amount > 0) { "Transfer amount must be positive." }
        require(request.currency.isNotBlank()) { "Transfer currency must not be blank." }
        require(request.destinationId.isNotBlank()) { "Transfer destination ID must not be blank." }
        require(request.originId.isNotBlank()) { "Transfer origin ID must not be blank." }
        return http.post("transfers", request, options)
    }

    suspend fun retrieve(
        transferId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Transfer {
        val id = pathParameter(transferId, "Transfer ID")
        return http.get("transfers/$id", options = options)
    }

    suspend fun list(
        request: ListTransfersRequest = ListTransfersRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<Transfer> = http.get("transfers", request.toQueryParameters(), options)

    fun listAutoPaging(
        request: ListTransfersRequest = ListTransfersRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<Transfer> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

data class ListTransfersRequest(
    val originId: String? = null,
    val destinationId: String? = null,
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val order: TransferOrder? = null,
    val direction: Direction? = null,
    val createdBefore: String? = null,
    val createdAfter: String? = null,
) {
    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            originId?.let { add("origin_id" to it) }
            destinationId?.let { add("destination_id" to it) }
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
            order?.let { add("order" to if (it == TransferOrder.Amount) "amount" else "created_at") }
            direction?.let { add("direction" to if (it == Direction.Asc) "asc" else "desc") }
            createdBefore?.let { add("created_before" to it) }
            createdAfter?.let { add("created_after" to it) }
        }
}
