package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.common.Direction
import dev.lepshee.whop.models.invoices.CreateInvoiceRequest
import dev.lepshee.whop.models.invoices.Invoice
import dev.lepshee.whop.models.invoices.InvoiceCollectionMethod
import dev.lepshee.whop.models.invoices.InvoiceListItem
import dev.lepshee.whop.models.invoices.InvoiceStatus
import dev.lepshee.whop.models.invoices.InvoicesSortableColumn
import dev.lepshee.whop.models.invoices.serialValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for Whop invoices and invoice lifecycle actions. */
class InvoicesResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun list(
        request: ListInvoicesRequest =
            ListInvoicesRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<InvoiceListItem> = http.get("invoices", request.toQueryParameters(), options)

    suspend fun create(
        request: CreateInvoiceRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Invoice = http.post("invoices", request, options)

    suspend fun retrieve(
        invoiceIdOrToken: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Invoice {
        val id = pathParameter(invoiceIdOrToken, "Invoice ID or token")
        return http.get("invoices/$id", options = options)
    }

    suspend fun update(
        invoiceId: String,
        request: dev.lepshee.whop.models.invoices.UpdateInvoiceRequest =
            dev.lepshee.whop.models.invoices
                .UpdateInvoiceRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Invoice {
        val id = pathParameter(invoiceId, "Invoice ID")
        return http.patch("invoices/$id", request, options)
    }

    suspend fun delete(
        invoiceId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Boolean {
        val id = pathParameter(invoiceId, "Invoice ID")
        return http.delete("invoices/$id", options = options)
    }

    suspend fun markPaid(
        invoiceId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Boolean {
        val id = pathParameter(invoiceId, "Invoice ID")
        return http.post("invoices/$id/mark_paid", options)
    }

    suspend fun markUncollectible(
        invoiceId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Boolean {
        val id = pathParameter(invoiceId, "Invoice ID")
        return http.post("invoices/$id/mark_uncollectible", options)
    }

    suspend fun void(
        invoiceId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Boolean {
        val id = pathParameter(invoiceId, "Invoice ID")
        return http.post("invoices/$id/void", options)
    }

    fun listAutoPaging(
        request: ListInvoicesRequest =
            ListInvoicesRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<InvoiceListItem> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

data class ListInvoicesRequest(
    val companyId: String? = null,
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val direction: Direction? = null,
    val productIds: List<String> = emptyList(),
    val collectionMethods: List<InvoiceCollectionMethod> = emptyList(),
    val statuses: List<InvoiceStatus> = emptyList(),
    val order: InvoicesSortableColumn? = null,
    val createdBefore: String? = null,
    val createdAfter: String? = null,
) {
    init {
        require(companyId == null || companyId.isNotBlank()) { "Company ID must not be blank." }
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
        require(productIds.none { it.isBlank() }) { "Product IDs must not be blank." }
        require(createdBefore == null || createdBefore.isNotBlank()) { "Created-before timestamp must not be blank." }
        require(createdAfter == null || createdAfter.isNotBlank()) { "Created-after timestamp must not be blank." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            companyId?.let { add("company_id" to it) }
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
            productIds.forEach { add("product_ids" to it) }
            collectionMethods.forEach { add("collection_methods" to it.serialValue) }
            statuses.forEach { add("statuses" to it.serialValue) }
            order?.let { add("order" to it.serialValue) }
            createdBefore?.let { add("created_before" to it) }
            createdAfter?.let { add("created_after" to it) }
        }
}
