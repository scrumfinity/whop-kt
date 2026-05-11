package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.JsonObject

/** Operations for product reviews. */
class ReviewsResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun list(
        request: ListReviewsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<JsonObject> = http.get("reviews", request.toQueryParameters(), options)

    suspend fun retrieve(
        reviewId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): JsonObject {
        val id = pathParameter(reviewId, "Review ID")
        return http.get("reviews/$id", options = options)
    }

    fun listAutoPaging(
        request: ListReviewsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<JsonObject> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

data class ListReviewsRequest(
    val productId: String,
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val minStars: Int? = null,
    val maxStars: Int? = null,
    val createdBefore: String? = null,
    val createdAfter: String? = null,
) {
    init {
        require(productId.isNotBlank()) { "Product ID must not be blank." }
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
        require(minStars == null || minStars in 1..5) { "Minimum stars must be between 1 and 5." }
        require(maxStars == null || maxStars in 1..5) { "Maximum stars must be between 1 and 5." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            add("product_id" to productId)
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
            minStars?.let { add("min_stars" to it.toString()) }
            maxStars?.let { add("max_stars" to it.toString()) }
            createdBefore?.let { add("created_before" to it) }
            createdAfter?.let { add("created_after" to it) }
        }
}
