package com.lepshee.whop.resources

import com.lepshee.whop.WhopRequestOptions
import com.lepshee.whop.core.WhopHttpExecutor
import com.lepshee.whop.core.WhopPage
import com.lepshee.whop.models.common.Direction
import com.lepshee.whop.models.companies.Company
import com.lepshee.whop.models.companies.CreateCompanyRequest
import com.lepshee.whop.models.companies.UpdateCompanyRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for Whop companies. */
class CompaniesResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun create(
        request: CreateCompanyRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Company {
        require(request.title.isNotBlank()) { "Company title must not be blank." }
        return http.post("companies", request, options)
    }

    suspend fun retrieve(
        companyId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Company {
        require(companyId.isNotBlank()) { "Company ID must not be blank." }
        return http.get("companies/$companyId", options = options)
    }

    suspend fun update(
        companyId: String,
        request: UpdateCompanyRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Company {
        require(companyId.isNotBlank()) { "Company ID must not be blank." }
        return http.patch("companies/$companyId", request, options)
    }

    suspend fun list(
        request: ListCompaniesRequest = ListCompaniesRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<Company> = http.get("companies", request.toQueryParameters(), options)

    fun listAutoPaging(
        request: ListCompaniesRequest = ListCompaniesRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<Company> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

data class ListCompaniesRequest(
    val parentCompanyId: String? = null,
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val direction: Direction? = null,
    val createdBefore: String? = null,
    val createdAfter: String? = null,
) {
    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            parentCompanyId?.let { add("parent_company_id" to it) }
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
            direction?.let { add("direction" to if (it == Direction.Asc) "asc" else "desc") }
            createdBefore?.let { add("created_before" to it) }
            createdAfter?.let { add("created_after" to it) }
        }
}
