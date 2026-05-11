package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.common.Direction
import dev.lepshee.whop.models.setupintents.SetupIntent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for setup intents that save payment methods for later use. */
class SetupIntentsResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun list(
        request: ListSetupIntentsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<SetupIntent> = http.get("setup_intents", request.toQueryParameters(), options)

    suspend fun retrieve(
        setupIntentId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): SetupIntent {
        val id = pathParameter(setupIntentId, "Setup intent ID")
        return http.get("setup_intents/$id", options = options)
    }

    fun listAutoPaging(
        request: ListSetupIntentsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<SetupIntent> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

data class ListSetupIntentsRequest(
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
