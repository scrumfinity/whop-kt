package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.affiliates.AffiliateAppliesToPayments
import dev.lepshee.whop.models.affiliates.AffiliateOverride
import dev.lepshee.whop.models.affiliates.AffiliateOverrideRole
import dev.lepshee.whop.models.affiliates.AffiliatePayoutType
import dev.lepshee.whop.models.affiliates.AffiliateRevenueBasis
import dev.lepshee.whop.models.affiliates.CreateRevShareAffiliateOverrideRequest
import dev.lepshee.whop.models.affiliates.CreateStandardAffiliateOverrideRequest
import dev.lepshee.whop.models.affiliates.UpdateAffiliateOverrideRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for commission overrides scoped to affiliates. */
class AffiliateOverridesResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun create(
        affiliateId: String,
        request: CreateStandardAffiliateOverrideRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): AffiliateOverride {
        val id = pathParameter(affiliateId, "Affiliate ID")
        require(request.id == affiliateId) { "Affiliate override request ID must match the affiliate path ID." }
        return http.post("affiliates/$id/overrides", request, options)
    }

    suspend fun create(
        affiliateId: String,
        request: CreateRevShareAffiliateOverrideRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): AffiliateOverride {
        val id = pathParameter(affiliateId, "Affiliate ID")
        require(request.id == affiliateId) { "Affiliate override request ID must match the affiliate path ID." }
        return http.post("affiliates/$id/overrides", request, options)
    }

    suspend fun list(
        affiliateId: String,
        request: ListAffiliateOverridesRequest =
            ListAffiliateOverridesRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<AffiliateOverride> {
        val id = pathParameter(affiliateId, "Affiliate ID")
        return http.get("affiliates/$id/overrides", request.toQueryParameters(), options)
    }

    suspend fun retrieve(
        affiliateId: String,
        overrideId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): AffiliateOverride {
        val id = pathParameter(affiliateId, "Affiliate ID")
        val validatedOverrideId = pathParameter(overrideId, "Affiliate override ID")
        return http.get("affiliates/$id/overrides/$validatedOverrideId", options = options)
    }

    suspend fun update(
        affiliateId: String,
        overrideId: String,
        request: UpdateAffiliateOverrideRequest =
            UpdateAffiliateOverrideRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): AffiliateOverride {
        val id = pathParameter(affiliateId, "Affiliate ID")
        val validatedOverrideId = pathParameter(overrideId, "Affiliate override ID")
        return http.patch("affiliates/$id/overrides/$validatedOverrideId", request, options)
    }

    suspend fun delete(
        affiliateId: String,
        overrideId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Boolean {
        val id = pathParameter(affiliateId, "Affiliate ID")
        val validatedOverrideId = pathParameter(overrideId, "Affiliate override ID")
        return http.delete("affiliates/$id/overrides/$validatedOverrideId", options = options)
    }

    fun listAutoPaging(
        affiliateId: String,
        request: ListAffiliateOverridesRequest =
            ListAffiliateOverridesRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<AffiliateOverride> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(affiliateId, request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

data class ListAffiliateOverridesRequest(
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val overrideType: AffiliateOverrideRole? = null,
) {
    init {
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
            overrideType?.let { add("override_type" to it.serialValue) }
        }
}

internal val AffiliateOverrideRole.serialValue: String get() =
    when (this) {
        AffiliateOverrideRole.Standard -> "standard"
        AffiliateOverrideRole.RevShare -> "rev_share"
    }

internal val AffiliatePayoutType.serialValue: String get() =
    when (this) {
        AffiliatePayoutType.Percentage -> "percentage"
        AffiliatePayoutType.FlatFee -> "flat_fee"
    }

internal val AffiliateAppliesToPayments.serialValue: String get() =
    when (this) {
        AffiliateAppliesToPayments.FirstPayment -> "first_payment"
        AffiliateAppliesToPayments.AllPayments -> "all_payments"
    }

internal val AffiliateRevenueBasis.serialValue: String get() =
    when (this) {
        AffiliateRevenueBasis.PreFees -> "pre_fees"
        AffiliateRevenueBasis.PostFees -> "post_fees"
    }
