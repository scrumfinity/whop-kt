package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.promocodes.CreatePromoCodeRequest
import dev.lepshee.whop.models.promocodes.PromoCode
import dev.lepshee.whop.models.promocodes.PromoCodeListItem
import dev.lepshee.whop.models.promocodes.PromoCodeStatus
import dev.lepshee.whop.models.promocodes.serialValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for checkout promo codes. */
class PromoCodesResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun list(
        request: ListPromoCodesRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<PromoCodeListItem> = http.get("promo_codes", request.toQueryParameters(), options)

    suspend fun create(
        request: CreatePromoCodeRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): PromoCode = http.post("promo_codes", request, options)

    suspend fun retrieve(
        promoCodeId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): PromoCode {
        val id = pathParameter(promoCodeId, "Promo code ID")
        return http.get("promo_codes/$id", options = options)
    }

    suspend fun delete(
        promoCodeId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Boolean {
        val id = pathParameter(promoCodeId, "Promo code ID")
        return http.delete("promo_codes/$id", options = options)
    }

    fun listAutoPaging(
        request: ListPromoCodesRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<PromoCodeListItem> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

data class ListPromoCodesRequest(
    val companyId: String,
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val productIds: List<String> = emptyList(),
    val planIds: List<String> = emptyList(),
    val status: PromoCodeStatus? = null,
    val createdBefore: String? = null,
    val createdAfter: String? = null,
) {
    init {
        require(companyId.isNotBlank()) { "Company ID must not be blank." }
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
        require(productIds.all(String::isNotBlank)) { "Product IDs must not be blank." }
        require(planIds.all(String::isNotBlank)) { "Plan IDs must not be blank." }
        require(createdBefore == null || createdBefore.isNotBlank()) { "Created-before timestamp must not be blank." }
        require(createdAfter == null || createdAfter.isNotBlank()) { "Created-after timestamp must not be blank." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            add("company_id" to companyId)
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
            productIds.forEach { add("product_ids" to it) }
            planIds.forEach { add("plan_ids" to it) }
            status?.let { add("status" to it.serialValue) }
            createdBefore?.let { add("created_before" to it) }
            createdAfter?.let { add("created_after" to it) }
        }
}
