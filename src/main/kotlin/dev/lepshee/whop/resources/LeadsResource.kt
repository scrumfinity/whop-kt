package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.leads.CreateLeadRequest
import dev.lepshee.whop.models.leads.Lead
import dev.lepshee.whop.models.leads.UpdateLeadRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for Whop leads. */
class LeadsResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun create(
        request: dev.lepshee.whop.models.leads.CreateLeadRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): dev.lepshee.whop.models.leads.Lead = http.post("leads", request, options)

    suspend fun list(
        request: dev.lepshee.whop.resources.ListLeadsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<dev.lepshee.whop.models.leads.Lead> = http.get("leads", request.toQueryParameters(), options)

    suspend fun retrieve(
        leadId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): dev.lepshee.whop.models.leads.Lead {
        val id = pathParameter(leadId, "Lead ID")
        return http.get("leads/$id", options = options)
    }

    suspend fun update(
        leadId: String,
        request: dev.lepshee.whop.models.leads.UpdateLeadRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): dev.lepshee.whop.models.leads.Lead {
        val id = pathParameter(leadId, "Lead ID")
        return http.patch("leads/$id", request, options)
    }

    fun listAutoPaging(
        request: dev.lepshee.whop.resources.ListLeadsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<dev.lepshee.whop.models.leads.Lead> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

data class ListLeadsRequest(
    val companyId: String,
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val createdAfter: String? = null,
    val createdBefore: String? = null,
    val productIds: List<String> = emptyList(),
) {
    init {
        require(companyId.isNotBlank()) { "Company ID must not be blank." }
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
        require(productIds.all(String::isNotBlank)) { "Product IDs must not be blank." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            add("company_id" to companyId)
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
            createdAfter?.let { add("created_after" to it) }
            createdBefore?.let { add("created_before" to it) }
            productIds.forEach { add("product_ids" to it) }
        }
}
