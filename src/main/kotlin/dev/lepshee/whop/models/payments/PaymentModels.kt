package dev.lepshee.whop.models.payments

import dev.lepshee.whop.models.common.Visibility
import dev.lepshee.whop.models.plans.PlanType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

/** Payment object returned by Whop. */
@Serializable
data class Payment(
    /** Unique payment ID. */
    val id: String,
    /** Current payment status, if included by the API response. */
    val status: String? = null,
    /** Friendly payment status/substatus returned by Whop. */
    val substatus: String? = null,
    /** Whether this payment can be refunded. */
    val refundable: Boolean? = null,
    /** Whether this payment can be retried. */
    val retryable: Boolean? = null,
    /** Whether this payment can be voided. */
    val voidable: Boolean? = null,
    /** Creation timestamp. */
    @SerialName("created_at") val createdAt: String? = null,
    /** Last update timestamp. */
    @SerialName("updated_at") val updatedAt: String? = null,
    /** Payment completion timestamp. */
    @SerialName("paid_at") val paidAt: String? = null,
    /** Payment currency. */
    val currency: String? = null,
    /** Final payment amount in major currency units when returned. */
    @SerialName("final_amount") val finalAmount: Double? = null,
    /** Subtotal amount in major currency units when returned. */
    val subtotal: Double? = null,
    /** Total amount in major currency units when returned. */
    val total: Double? = null,
    /** Tax amount in major currency units when returned. */
    @SerialName("tax_amount") val taxAmount: Double? = null,
    /** Refunded amount in major currency units when returned. */
    @SerialName("refunded_amount") val refundedAmount: Double? = null,
    /** Custom metadata returned by Whop. */
    val metadata: JsonObject? = null,
    /** Associated plan payload. */
    val plan: JsonObject? = null,
    /** Associated product payload. */
    val product: JsonObject? = null,
    /** Associated user payload. */
    val user: JsonObject? = null,
    /** Associated membership payload. */
    val membership: JsonObject? = null,
    /** Associated member payload. */
    val member: JsonObject? = null,
    /** Payment method payload. */
    @SerialName("payment_method") val paymentMethod: JsonObject? = null,
    /** Associated company payload. */
    val company: JsonObject? = null,
    /** Promo code payload when present. */
    @SerialName("promo_code") val promoCode: JsonObject? = null,
    /** Dispute payloads when present. */
    val disputes: List<JsonObject>? = null,
    /** Reserved escape hatch for future raw payload support. */
    val raw: JsonObject? = null,
)

@Serializable
data class CreatePaymentRequest(
    @SerialName("company_id") val companyId: String,
    @SerialName("member_id") val memberId: String,
    @SerialName("payment_method_id") val paymentMethodId: String,
    @SerialName("plan_id") val planId: String? = null,
    val plan: CreatePaymentPlan? = null,
    val metadata: JsonObject? = null,
) {
    init {
        require(companyId.isNotBlank()) { "Company ID must not be blank." }
        require(memberId.isNotBlank()) { "Member ID must not be blank." }
        require(paymentMethodId.isNotBlank()) { "Payment method ID must not be blank." }
        require((planId == null) != (plan == null)) { "Payment requires exactly one of planId or inline plan." }
        require(planId == null || planId.isNotBlank()) { "Plan ID must not be blank." }
    }
}

/** Inline plan accepted by `POST /payments` when not charging an existing plan. */
@Serializable
data class CreatePaymentPlan(
    /** Required currency for Whop to create or find a matching payment plan. */
    val currency: String,
    /** Optional application fee amount in major currency units. */
    @SerialName("application_fee_amount") val applicationFeeAmount: Double? = null,
    /** Billing interval in days for renewal plans. */
    @SerialName("billing_period") val billingPeriod: Int? = null,
    /** Optional plan description. */
    val description: String? = null,
    /** Expiration period in days for expiration-based plans. */
    @SerialName("expiration_days") val expirationDays: Int? = null,
    /** Whether Whop should force creation of a new plan. */
    @SerialName("force_create_new_plan") val forceCreateNewPlan: Boolean? = null,
    /** Initial charge in major currency units. */
    @SerialName("initial_price") val initialPrice: Double? = null,
    /** Internal notes for the generated plan. */
    @SerialName("internal_notes") val internalNotes: String? = null,
    /** Whether this is a one-time or renewal plan. */
    @SerialName("plan_type") val planType: PlanType? = null,
    /** Inline product to find or create for the generated plan. */
    val product: CreatePaymentProduct? = null,
    /** Existing product ID for the generated plan. */
    @SerialName("product_id") val productId: String? = null,
    /** Recurring charge in major currency units. */
    @SerialName("renewal_price") val renewalPrice: Double? = null,
    /** Public title for the generated plan. */
    val title: String? = null,
    /** Trial period in days before the renewal plan charges. */
    @SerialName("trial_period_days") val trialPeriodDays: Int? = null,
    /** Visibility of the generated plan. */
    val visibility: Visibility? = null,
) {
    init {
        require(currency.isNotBlank()) { "Currency must not be blank." }
        require(applicationFeeAmount == null || applicationFeeAmount >= 0.0) { "Application fee amount must not be negative." }
        require(billingPeriod == null || billingPeriod > 0) { "Billing period must be positive." }
        require(expirationDays == null || expirationDays > 0) { "Expiration days must be positive." }
        require(initialPrice == null || initialPrice >= 0.0) { "Initial price must not be negative." }
        require(productId == null || productId.isNotBlank()) { "Product ID must not be blank." }
        require(renewalPrice == null || renewalPrice >= 0.0) { "Renewal price must not be negative." }
        require(title == null || title.isNotBlank()) { "Plan title must not be blank." }
        require(trialPeriodDays == null || trialPeriodDays >= 0) { "Trial period days must not be negative." }
    }
}

/** Inline product accepted inside `CreatePaymentPlan`. */
@Serializable
data class CreatePaymentProduct(
    /** Unique external identifier used to find or create the product. */
    @SerialName("external_identifier") val externalIdentifier: String,
    /** Product title. */
    val title: String,
    /** Whether checkout should collect shipping information. */
    @SerialName("collect_shipping_address") val collectShippingAddress: Boolean? = null,
    /** Custom statement descriptor shown by payment processors. */
    @SerialName("custom_statement_descriptor") val customStatementDescriptor: String? = null,
    /** Product description. */
    val description: String? = null,
    /** Global affiliate revenue percentage. */
    @SerialName("global_affiliate_percentage") val globalAffiliatePercentage: Double? = null,
    /** Global affiliate status value. */
    @SerialName("global_affiliate_status") val globalAffiliateStatus: String? = null,
    /** Product headline. */
    val headline: String? = null,
    /** Product tax code ID. */
    @SerialName("product_tax_code_id") val productTaxCodeId: String? = null,
    /** URL to redirect customers to after purchase. */
    @SerialName("redirect_purchase_url") val redirectPurchaseUrl: String? = null,
    /** Product route. */
    val route: String? = null,
    /** Product visibility. */
    val visibility: Visibility? = null,
) {
    init {
        require(externalIdentifier.isNotBlank()) { "External identifier must not be blank." }
        require(title.isNotBlank()) { "Product title must not be blank." }
        require(customStatementDescriptor == null || customStatementDescriptor.isValidStatementDescriptor()) {
            "Custom statement descriptor must be 5-22 characters, contain a letter, and exclude <, >, \\, ', and \"."
        }
        require(globalAffiliatePercentage == null || globalAffiliatePercentage >= 0.0) {
            "Global affiliate percentage must not be negative."
        }
        require(productTaxCodeId == null || productTaxCodeId.isNotBlank()) { "Product tax code ID must not be blank." }
        require(redirectPurchaseUrl == null || redirectPurchaseUrl.isNotBlank()) { "Redirect purchase URL must not be blank." }
        require(route == null || route.isNotBlank()) { "Product route must not be blank." }
    }
}

@Serializable
data class RefundPaymentRequest(
    @SerialName("partial_amount") val partialAmount: Double? = null,
)

/** Fee line item returned by `GET /payments/{id}/fees`. */
@Serializable
data class PaymentFee(
    val name: String? = null,
    val amount: Double? = null,
    val currency: String? = null,
    val type: String? = null,
    val metadata: JsonObject? = null,
)

private fun String.isValidStatementDescriptor(): Boolean =
    length in 5..22 && any(Char::isLetter) && none { it in setOf('<', '>', '\\', '\'', '"') }

@Serializable
enum class PaymentStatus {
    @SerialName("draft")
    Draft,

    @SerialName("open")
    Open,

    @SerialName("paid")
    Paid,

    @SerialName("pending")
    Pending,

    @SerialName("uncollectible")
    Uncollectible,

    @SerialName("unresolved")
    Unresolved,

    @SerialName("void")
    Void,
}

@Serializable
enum class PaymentOrder {
    @SerialName("final_amount")
    FinalAmount,

    @SerialName("created_at")
    CreatedAt,

    @SerialName("paid_at")
    PaidAt,
}

@Serializable
enum class BillingReason {
    @SerialName("subscription_create")
    SubscriptionCreate,

    @SerialName("subscription_cycle")
    SubscriptionCycle,

    @SerialName("subscription_update")
    SubscriptionUpdate,

    @SerialName("one_time")
    OneTime,

    @SerialName("manual")
    Manual,

    @SerialName("subscription")
    Subscription,
}

@Serializable
enum class FriendlyReceiptStatus {
    @SerialName("succeeded")
    Succeeded,

    @SerialName("pending")
    Pending,

    @SerialName("failed")
    Failed,

    @SerialName("past_due")
    PastDue,

    @SerialName("canceled")
    Canceled,

    @SerialName("price_too_low")
    PriceTooLow,

    @SerialName("uncollectible")
    Uncollectible,

    @SerialName("refunded")
    Refunded,

    @SerialName("auto_refunded")
    AutoRefunded,

    @SerialName("partially_refunded")
    PartiallyRefunded,

    @SerialName("dispute_warning")
    DisputeWarning,

    @SerialName("dispute_needs_response")
    DisputeNeedsResponse,

    @SerialName("dispute_warning_needs_response")
    DisputeWarningNeedsResponse,

    @SerialName("resolution_needs_response")
    ResolutionNeedsResponse,

    @SerialName("dispute_under_review")
    DisputeUnderReview,

    @SerialName("dispute_warning_under_review")
    DisputeWarningUnderReview,

    @SerialName("resolution_under_review")
    ResolutionUnderReview,

    @SerialName("dispute_won")
    DisputeWon,

    @SerialName("dispute_warning_closed")
    DisputeWarningClosed,

    @SerialName("resolution_won")
    ResolutionWon,

    @SerialName("dispute_lost")
    DisputeLost,

    @SerialName("dispute_closed")
    DisputeClosed,

    @SerialName("resolution_lost")
    ResolutionLost,

    @SerialName("drafted")
    Drafted,

    @SerialName("incomplete")
    Incomplete,

    @SerialName("unresolved")
    Unresolved,

    @SerialName("open_dispute")
    OpenDispute,

    @SerialName("open_resolution")
    OpenResolution,
}
