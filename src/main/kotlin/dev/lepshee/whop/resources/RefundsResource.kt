package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.common.Direction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.JsonObject

/** Operations for Whop refunds. */
class RefundsResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun list(
        request: ListRefundsRequest =
            ListRefundsRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<JsonObject> = http.get("refunds", request.toQueryParameters(), options)

    suspend fun retrieve(
        refundId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): JsonObject {
        val id = pathParameter(refundId, "Refund ID")
        return http.get("refunds/$id", options = options)
    }

    fun listAutoPaging(
        request: ListRefundsRequest =
            ListRefundsRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<JsonObject> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

data class ListRefundsRequest(
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val paymentId: String? = null,
    val companyId: String? = null,
    val userId: String? = null,
    val direction: Direction? = null,
    val createdBefore: String? = null,
    val createdAfter: String? = null,
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
            createdBefore?.let { add("created_before" to it) }
            createdAfter?.let { add("created_after" to it) }
        }
}
