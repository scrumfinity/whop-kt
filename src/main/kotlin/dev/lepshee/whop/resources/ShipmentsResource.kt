package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.shipments.CreateShipmentRequest
import dev.lepshee.whop.models.shipments.Shipment
import dev.lepshee.whop.models.shipments.ShipmentListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for Whop shipments. */
class ShipmentsResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    /** Lists shipments using cursor pagination. */
    suspend fun list(
        request: ListShipmentsRequest =
            ListShipmentsRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<ShipmentListItem> = http.get("shipments", request.toQueryParameters(), options)

    /** Creates a shipment for a payment. */
    suspend fun create(
        request: CreateShipmentRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Shipment = http.post("shipments", request, options)

    /** Retrieves a shipment by ID. */
    suspend fun retrieve(
        shipmentId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Shipment {
        val id = pathParameter(shipmentId, "Shipment ID")
        return http.get("shipments/$id", options = options)
    }

    /** Streams shipments across forward pages by following `page_info.end_cursor`. */
    fun listAutoPaging(
        request: ListShipmentsRequest =
            ListShipmentsRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<ShipmentListItem> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

/** Cursor and filter options for listing shipments. */
data class ListShipmentsRequest(
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val paymentId: String? = null,
    val companyId: String? = null,
    val userId: String? = null,
) {
    init {
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
        require(paymentId == null || paymentId.isNotBlank()) { "Payment ID must not be blank." }
        require(companyId == null || companyId.isNotBlank()) { "Company ID must not be blank." }
        require(userId == null || userId.isNotBlank()) { "User ID must not be blank." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
            paymentId?.let { add("payment_id" to it) }
            companyId?.let { add("company_id" to it) }
            userId?.let { add("user_id" to it) }
        }
}
