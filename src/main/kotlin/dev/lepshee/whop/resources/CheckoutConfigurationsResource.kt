package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.checkout.CheckoutConfiguration
import dev.lepshee.whop.models.checkout.CreateCheckoutConfigurationRequest
import dev.lepshee.whop.models.common.Direction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for creating and reading Whop checkout configurations. */
class CheckoutConfigurationsResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    /**
     * Creates a checkout configuration for a hosted or embedded checkout flow.
     *
     * Checkout configurations should be created server-side and fulfillment should be confirmed by webhooks.
     */
    suspend fun create(
        request: CreateCheckoutConfigurationRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): CheckoutConfiguration = http.post("checkout_configurations", request, options)

    /** Retrieves a checkout configuration by ID. */
    suspend fun retrieve(
        checkoutConfigurationId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): CheckoutConfiguration {
        val id = pathParameter(checkoutConfigurationId, "Checkout configuration ID")
        return http.get("checkout_configurations/$id", options = options)
    }

    /** Lists checkout configurations for the supplied filters. */
    suspend fun list(
        request: ListCheckoutConfigurationsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<CheckoutConfiguration> =
        http.get(
            path = "checkout_configurations",
            queryParameters = request.toQueryParameters(),
            options = options,
        )

    /** Streams all forward pages by repeatedly following `page_info.end_cursor`. */
    fun listAutoPaging(
        request: ListCheckoutConfigurationsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<CheckoutConfiguration> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

/** Cursor and filter options for listing checkout configurations. */
data class ListCheckoutConfigurationsRequest(
    val companyId: String,
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val direction: Direction? = null,
    val planId: String? = null,
    val createdBefore: String? = null,
    val createdAfter: String? = null,
) {
    init {
        require(companyId.isNotBlank()) { "Company ID must not be blank." }
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
        require(planId == null || planId.isNotBlank()) { "Plan ID must not be blank." }
        require(createdBefore == null || createdBefore.isNotBlank()) { "Created-before value must not be blank." }
        require(createdAfter == null || createdAfter.isNotBlank()) { "Created-after value must not be blank." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            add("company_id" to companyId)
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
            direction?.let { add("direction" to if (it == Direction.Asc) "asc" else "desc") }
            planId?.let { add("plan_id" to it) }
            createdBefore?.let { add("created_before" to it) }
            createdAfter?.let { add("created_after" to it) }
        }
}
