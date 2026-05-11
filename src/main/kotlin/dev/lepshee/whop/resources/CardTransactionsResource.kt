package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.cards.CardTransaction
import dev.lepshee.whop.models.cards.CardTransactionStatus
import dev.lepshee.whop.models.common.Direction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for issued-card transactions. */
class CardTransactionsResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun list(
        request: ListCardTransactionsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<CardTransaction> = http.get("card_transactions", request.toQueryParameters(), options)

    suspend fun retrieve(
        cardTransactionId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): CardTransaction {
        val id = pathParameter(cardTransactionId, "Card transaction ID")
        return http.get("card_transactions/$id", options = options)
    }

    fun listAutoPaging(
        request: ListCardTransactionsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<CardTransaction> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

data class ListCardTransactionsRequest(
    val companyId: String,
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val cardId: String? = null,
    val createdAfter: String? = null,
    val createdBefore: String? = null,
    val direction: Direction? = null,
    val status: CardTransactionStatus? = null,
) {
    init {
        require(companyId.isNotBlank()) { "Company ID must not be blank." }
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
        require(cardId == null || cardId.isNotBlank()) { "Card ID must not be blank." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            add("company_id" to companyId)
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
            cardId?.let { add("card_id" to it) }
            createdAfter?.let { add("created_after" to it) }
            createdBefore?.let { add("created_before" to it) }
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
            status?.let { add("status" to it.serialValue) }
        }
}

private val CardTransactionStatus.serialValue: String get() =
    when (this) {
        CardTransactionStatus.Pending -> "pending"
        CardTransactionStatus.Completed -> "completed"
        CardTransactionStatus.Reversed -> "reversed"
        CardTransactionStatus.Declined -> "declined"
    }
