package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.common.Direction
import dev.lepshee.whop.models.resolutioncentercases.ResolutionCenterCase
import dev.lepshee.whop.models.resolutioncentercases.ResolutionCenterCaseListItem
import dev.lepshee.whop.models.resolutioncentercases.ResolutionCenterCaseStatus
import dev.lepshee.whop.models.resolutioncentercases.serialValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for resolution center cases. */
class ResolutionCenterCasesResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun list(
        request: ListResolutionCenterCasesRequest =
            ListResolutionCenterCasesRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<ResolutionCenterCaseListItem> = http.get("resolution_center_cases", request.toQueryParameters(), options)

    suspend fun retrieve(
        resolutionCenterCaseId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): ResolutionCenterCase {
        val id = pathParameter(resolutionCenterCaseId, "Resolution center case ID")
        return http.get("resolution_center_cases/$id", options = options)
    }

    fun listAutoPaging(
        request: ListResolutionCenterCasesRequest =
            ListResolutionCenterCasesRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<ResolutionCenterCaseListItem> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

data class ListResolutionCenterCasesRequest(
    val companyId: String? = null,
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val direction: Direction? = null,
    val statuses: List<ResolutionCenterCaseStatus> = emptyList(),
    val createdBefore: String? = null,
    val createdAfter: String? = null,
) {
    init {
        require(companyId == null || companyId.isNotBlank()) { "Company ID must not be blank." }
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
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
            statuses.forEach { add("statuses" to it.serialValue) }
            createdBefore?.let { add("created_before" to it) }
            createdAfter?.let { add("created_after" to it) }
        }
}
