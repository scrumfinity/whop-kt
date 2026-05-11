package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.JsonObject

/** Operations for payout-account verifications. */
class VerificationsResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun list(
        request: ListVerificationsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<JsonObject> = http.get("verifications", request.toQueryParameters(), options)

    suspend fun retrieve(
        verificationId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): JsonObject {
        val id = pathParameter(verificationId, "Verification ID")
        return http.get("verifications/$id", options = options)
    }

    fun listAutoPaging(
        request: ListVerificationsRequest,
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

data class ListVerificationsRequest(
    val payoutAccountId: String,
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
) {
    init {
        require(payoutAccountId.isNotBlank()) { "Payout account ID must not be blank." }
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            add("payout_account_id" to payoutAccountId)
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
        }
}
