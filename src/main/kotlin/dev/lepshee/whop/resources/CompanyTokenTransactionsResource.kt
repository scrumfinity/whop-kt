package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.companytokentransactions.CompanyTokenTransaction
import dev.lepshee.whop.models.companytokentransactions.CompanyTokenTransactionListItem
import dev.lepshee.whop.models.companytokentransactions.CompanyTokenTransactionType
import dev.lepshee.whop.models.companytokentransactions.CreateCompanyTokenTransactionRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for company token balance transactions. */
class CompanyTokenTransactionsResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun list(
        request: ListCompanyTokenTransactionsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<CompanyTokenTransactionListItem> = http.get("company_token_transactions", request.toQueryParameters(), options)

    suspend fun create(
        request: CreateCompanyTokenTransactionRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): CompanyTokenTransaction = http.post("company_token_transactions", request, options)

    suspend fun retrieve(
        companyTokenTransactionId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): CompanyTokenTransaction {
        val id = pathParameter(companyTokenTransactionId, "Company token transaction ID")
        return http.get("company_token_transactions/$id", options = options)
    }

    fun listAutoPaging(
        request: ListCompanyTokenTransactionsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<CompanyTokenTransactionListItem> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

data class ListCompanyTokenTransactionsRequest(
    val companyId: String,
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val userId: String? = null,
    val transactionType: CompanyTokenTransactionType? = null,
) {
    init {
        require(companyId.isNotBlank()) { "Company ID must not be blank." }
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
        require(userId == null || userId.isNotBlank()) { "User ID must not be blank." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            add("company_id" to companyId)
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
            userId?.let { add("user_id" to it) }
            transactionType?.let { add("transaction_type" to it.serialValue) }
        }
}

private val CompanyTokenTransactionType.serialValue: String
    get() =
        when (this) {
            CompanyTokenTransactionType.Add -> "add"
            CompanyTokenTransactionType.Subtract -> "subtract"
            CompanyTokenTransactionType.Transfer -> "transfer"
        }
