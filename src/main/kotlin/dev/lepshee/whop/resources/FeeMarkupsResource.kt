package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.feemarkups.CreateFeeMarkupRequest
import dev.lepshee.whop.models.feemarkups.FeeMarkup
import dev.lepshee.whop.models.feemarkups.FeeMarkupListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for company fee markup configuration. */
class FeeMarkupsResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun list(
        request: ListFeeMarkupsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<FeeMarkupListItem> = http.get("fee_markups", request.toQueryParameters(), options)

    suspend fun create(
        request: CreateFeeMarkupRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): FeeMarkup = http.post("fee_markups", request, options)

    suspend fun delete(
        feeMarkupId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Boolean {
        val id = pathParameter(feeMarkupId, "Fee markup ID")
        return http.delete("fee_markups/$id", options = options)
    }

    fun listAutoPaging(
        request: ListFeeMarkupsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<FeeMarkupListItem> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

data class ListFeeMarkupsRequest(
    val companyId: String,
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
) {
    init {
        require(companyId.isNotBlank()) { "Company ID must not be blank." }
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            add("company_id" to companyId)
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
        }
}
