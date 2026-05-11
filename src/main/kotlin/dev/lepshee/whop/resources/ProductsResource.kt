package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.common.Direction
import dev.lepshee.whop.models.common.VisibilityFilter
import dev.lepshee.whop.models.products.CreateProductRequest
import dev.lepshee.whop.models.products.Product
import dev.lepshee.whop.models.products.ProductOrder
import dev.lepshee.whop.models.products.ProductType
import dev.lepshee.whop.models.products.UpdateProductRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for Whop products, the top-level containers for plans and experiences. */
class ProductsResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    /** Creates a new product for a company. */
    suspend fun create(
        request: CreateProductRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Product = http.post("products", request, options)

    /** Retrieves a product by ID. */
    suspend fun retrieve(
        productId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Product {
        val id = pathParameter(productId, "Product ID")
        return http.get("products/$id", options = options)
    }

    /** Updates mutable product fields. */
    suspend fun update(
        productId: String,
        request: UpdateProductRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Product {
        val id = pathParameter(productId, "Product ID")
        return http.patch("products/$id", request, options)
    }

    /** Deletes a product by ID and returns Whop's boolean deletion result. */
    suspend fun delete(
        productId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Boolean {
        val id = pathParameter(productId, "Product ID")
        return http.delete("products/$id", options = options)
    }

    /** Lists products for a company using Whop cursor pagination. */
    suspend fun list(
        request: ListProductsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<Product> =
        http.get(
            path = "products",
            queryParameters = request.toQueryParameters(),
            options = options,
        )

    /** Streams products across forward pages by following `page_info.end_cursor`. */
    fun listAutoPaging(
        request: ListProductsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<Product> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

/** Query parameters for listing products. */
data class ListProductsRequest(
    val companyId: String,
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val productTypes: List<ProductType> = emptyList(),
    val visibilities: List<VisibilityFilter> = emptyList(),
    val order: ProductOrder? = null,
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
            productTypes.forEach { add("product_types" to it.serialValue) }
            visibilities.forEach { add("visibilities" to it.serialValue) }
            order?.let { add("order" to it.serialValue) }
            direction?.let { add("direction" to it.serialValue) }
            createdBefore?.let { add("created_before" to it) }
            createdAfter?.let { add("created_after" to it) }
        }
}

private val ProductType.serialValue: String
    get() =
        when (this) {
            ProductType.Regular -> "regular"
            ProductType.App -> "app"
            ProductType.ExperienceUpsell -> "experience_upsell"
            ProductType.ApiOnly -> "api_only"
        }

private val VisibilityFilter.serialValue: String
    get() =
        when (this) {
            VisibilityFilter.Visible -> "visible"
            VisibilityFilter.Hidden -> "hidden"
            VisibilityFilter.Archived -> "archived"
            VisibilityFilter.QuickLink -> "quick_link"
            VisibilityFilter.All -> "all"
            VisibilityFilter.NotQuickLink -> "not_quick_link"
            VisibilityFilter.NotArchived -> "not_archived"
        }

private val ProductOrder.serialValue: String
    get() =
        when (this) {
            ProductOrder.ActiveMembershipsCount -> "active_memberships_count"
            ProductOrder.CreatedAt -> "created_at"
            ProductOrder.UsdGmv -> "usd_gmv"
            ProductOrder.UsdGmv30Days -> "usd_gmv_30_days"
        }

private val Direction.serialValue: String
    get() =
        when (this) {
            Direction.Asc -> "asc"
            Direction.Desc -> "desc"
        }
