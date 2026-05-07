package com.lepshee.whop.resources

import com.lepshee.whop.WhopRequestOptions
import com.lepshee.whop.core.WhopHttpExecutor
import com.lepshee.whop.core.WhopPage
import com.lepshee.whop.models.checkout.CheckoutConfiguration
import com.lepshee.whop.models.checkout.CreateCheckoutConfigurationRequest
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
        require(checkoutConfigurationId.isNotBlank()) { "Checkout configuration ID must not be blank." }
        return http.get("checkout_configurations/$checkoutConfigurationId", options = options)
    }

    /** Lists checkout configurations for the supplied filters. */
    suspend fun list(
        request: ListCheckoutConfigurationsRequest = ListCheckoutConfigurationsRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<CheckoutConfiguration> =
        http.get(
            path = "checkout_configurations",
            queryParameters = request.toQueryParameters(),
            options = options,
        )

    /** Streams all forward pages by repeatedly following `page_info.end_cursor`. */
    fun listAutoPaging(
        request: ListCheckoutConfigurationsRequest = ListCheckoutConfigurationsRequest(),
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
    val companyId: String? = null,
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
) {
    init {
        require(companyId == null || companyId.isNotBlank()) { "Company ID must not be blank." }
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            companyId?.let { add("company_id" to it) }
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
        }
}
