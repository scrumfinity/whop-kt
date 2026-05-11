package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.common.Direction
import dev.lepshee.whop.models.payments.BillingReason
import dev.lepshee.whop.models.payments.CreatePaymentRequest
import dev.lepshee.whop.models.payments.FriendlyReceiptStatus
import dev.lepshee.whop.models.payments.Payment
import dev.lepshee.whop.models.payments.PaymentFee
import dev.lepshee.whop.models.payments.PaymentOrder
import dev.lepshee.whop.models.payments.PaymentStatus
import dev.lepshee.whop.models.payments.RefundPaymentRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for Whop payments. */
class PaymentsResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    /** Creates an asynchronous payment. Confirm fulfillment using payment webhooks or retrieval. */
    suspend fun create(
        request: CreatePaymentRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Payment {
        require(request.companyId.isNotBlank()) { "Company ID must not be blank." }
        require(request.memberId.isNotBlank()) { "Member ID must not be blank." }
        require(request.paymentMethodId.isNotBlank()) { "Payment method ID must not be blank." }
        require(request.planId != null || request.plan != null) { "Payment requires either planId or inline plan." }
        return http.post("payments", request, options)
    }

    /** Retrieves a payment by ID. Use payment webhooks for fulfillment decisions. */
    suspend fun retrieve(
        paymentId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Payment {
        val id = pathParameter(paymentId, "Payment ID")
        return http.get("payments/$id", options = options)
    }

    /** Lists payments using cursor pagination. */
    suspend fun list(
        request: ListPaymentsRequest = ListPaymentsRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<Payment> = http.get("payments", request.toQueryParameters(), options)

    /** Streams payments across forward pages by following `page_info.end_cursor`. */
    fun listAutoPaging(
        request: ListPaymentsRequest = ListPaymentsRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<Payment> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }

    /** Refunds a payment. Omit [RefundPaymentRequest.partialAmount] for a full refund. */
    suspend fun refund(
        paymentId: String,
        request: RefundPaymentRequest = RefundPaymentRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Payment {
        val id = pathParameter(paymentId, "Payment ID")
        require(request.partialAmount == null || request.partialAmount > 0) { "Partial refund amount must be positive." }
        return http.post("payments/$id/refund", request, options)
    }

    /** Lists fee line items for a payment. */
    suspend fun listFees(
        paymentId: String,
        request: ListPaymentFeesRequest = ListPaymentFeesRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<PaymentFee> {
        val id = pathParameter(paymentId, "Payment ID")
        return http.get("payments/$id/fees", request.toQueryParameters(), options)
    }

    /** Retries a retryable payment. */
    suspend fun retry(
        paymentId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Payment {
        val id = pathParameter(paymentId, "Payment ID")
        return http.post("payments/$id/retry", options)
    }

    /** Voids a voidable payment. */
    suspend fun void(
        paymentId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Payment {
        val id = pathParameter(paymentId, "Payment ID")
        return http.post("payments/$id/void", options)
    }
}

/** Cursor options for payment fee lists. */
data class ListPaymentFeesRequest(
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
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
        }
}

/** Cursor and filter options for listing payments. */
data class ListPaymentsRequest(
    val companyId: String? = null,
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val productIds: List<String> = emptyList(),
    val planIds: List<String> = emptyList(),
    val statuses: List<PaymentStatus> = emptyList(),
    val billingReasons: List<BillingReason> = emptyList(),
    val substatuses: List<FriendlyReceiptStatus> = emptyList(),
    val currencies: List<String> = emptyList(),
    val includeFree: Boolean? = null,
    val query: String? = null,
    val direction: Direction? = null,
    val order: PaymentOrder? = null,
    val createdBefore: String? = null,
    val createdAfter: String? = null,
    val updatedBefore: String? = null,
    val updatedAfter: String? = null,
) {
    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            companyId?.let { add("company_id" to it) }
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
            productIds.forEach { add("product_ids" to it) }
            planIds.forEach { add("plan_ids" to it) }
            statuses.forEach { add("statuses" to it.serialValue) }
            billingReasons.forEach { add("billing_reasons" to it.serialValue) }
            substatuses.forEach { add("substatuses" to it.serialValue) }
            currencies.forEach { add("currencies" to it) }
            includeFree?.let { add("include_free" to it.toString()) }
            query?.let { add("query" to it) }
            direction?.let { add("direction" to if (it == Direction.Asc) "asc" else "desc") }
            order?.let { add("order" to it.serialValue) }
            createdBefore?.let { add("created_before" to it) }
            createdAfter?.let { add("created_after" to it) }
            updatedBefore?.let { add("updated_before" to it) }
            updatedAfter?.let { add("updated_after" to it) }
        }
}

private val PaymentStatus.serialValue: String get() =
    when (this) {
        PaymentStatus.Draft -> "draft"
        PaymentStatus.Open -> "open"
        PaymentStatus.Paid -> "paid"
        PaymentStatus.Pending -> "pending"
        PaymentStatus.Uncollectible -> "uncollectible"
        PaymentStatus.Unresolved -> "unresolved"
        PaymentStatus.Void -> "void"
    }
private val PaymentOrder.serialValue: String get() =
    when (this) {
        PaymentOrder.FinalAmount -> "final_amount"
        PaymentOrder.CreatedAt -> "created_at"
        PaymentOrder.PaidAt -> "paid_at"
    }
private val BillingReason.serialValue: String get() =
    when (this) {
        BillingReason.SubscriptionCreate -> "subscription_create"
        BillingReason.SubscriptionCycle -> "subscription_cycle"
        BillingReason.SubscriptionUpdate -> "subscription_update"
        BillingReason.OneTime -> "one_time"
        BillingReason.Manual -> "manual"
        BillingReason.Subscription -> "subscription"
    }
private val FriendlyReceiptStatus.serialValue: String get() =
    when (this) {
        FriendlyReceiptStatus.Succeeded -> "succeeded"
        FriendlyReceiptStatus.Pending -> "pending"
        FriendlyReceiptStatus.Failed -> "failed"
        FriendlyReceiptStatus.PastDue -> "past_due"
        FriendlyReceiptStatus.Canceled -> "canceled"
        FriendlyReceiptStatus.PriceTooLow -> "price_too_low"
        FriendlyReceiptStatus.Uncollectible -> "uncollectible"
        FriendlyReceiptStatus.Refunded -> "refunded"
        FriendlyReceiptStatus.AutoRefunded -> "auto_refunded"
        FriendlyReceiptStatus.PartiallyRefunded -> "partially_refunded"
        FriendlyReceiptStatus.DisputeWarning -> "dispute_warning"
        FriendlyReceiptStatus.DisputeNeedsResponse -> "dispute_needs_response"
        FriendlyReceiptStatus.DisputeWarningNeedsResponse -> "dispute_warning_needs_response"
        FriendlyReceiptStatus.ResolutionNeedsResponse -> "resolution_needs_response"
        FriendlyReceiptStatus.DisputeUnderReview -> "dispute_under_review"
        FriendlyReceiptStatus.DisputeWarningUnderReview -> "dispute_warning_under_review"
        FriendlyReceiptStatus.ResolutionUnderReview -> "resolution_under_review"
        FriendlyReceiptStatus.DisputeWon -> "dispute_won"
        FriendlyReceiptStatus.DisputeWarningClosed -> "dispute_warning_closed"
        FriendlyReceiptStatus.ResolutionWon -> "resolution_won"
        FriendlyReceiptStatus.DisputeLost -> "dispute_lost"
        FriendlyReceiptStatus.DisputeClosed -> "dispute_closed"
        FriendlyReceiptStatus.ResolutionLost -> "resolution_lost"
        FriendlyReceiptStatus.Drafted -> "drafted"
        FriendlyReceiptStatus.Incomplete -> "incomplete"
        FriendlyReceiptStatus.Unresolved -> "unresolved"
        FriendlyReceiptStatus.OpenDispute -> "open_dispute"
        FriendlyReceiptStatus.OpenResolution -> "open_resolution"
    }
