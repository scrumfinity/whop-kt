package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.common.Direction
import dev.lepshee.whop.models.withdrawals.CreateWithdrawalRequest
import dev.lepshee.whop.models.withdrawals.Withdrawal
import dev.lepshee.whop.models.withdrawals.WithdrawalListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for withdrawing ledger account funds. */
class WithdrawalsResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    /** Lists withdrawals for a company using cursor pagination. */
    suspend fun list(
        request: ListWithdrawalsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<WithdrawalListItem> = http.get("withdrawals", request.toQueryParameters(), options)

    /** Creates a withdrawal request. */
    suspend fun create(
        request: CreateWithdrawalRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Withdrawal = http.post("withdrawals", request, options)

    /** Retrieves a withdrawal by ID. */
    suspend fun retrieve(
        withdrawalId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Withdrawal {
        val id = pathParameter(withdrawalId, "Withdrawal ID")
        return http.get("withdrawals/$id", options = options)
    }

    /** Streams withdrawals across forward pages by following `page_info.end_cursor`. */
    fun listAutoPaging(
        request: ListWithdrawalsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<WithdrawalListItem> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

/** Cursor, sorting, and date filters for listing withdrawals. */
data class ListWithdrawalsRequest(
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
