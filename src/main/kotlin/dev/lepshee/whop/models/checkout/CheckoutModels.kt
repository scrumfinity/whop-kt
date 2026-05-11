package dev.lepshee.whop.models.checkout

import dev.lepshee.whop.models.common.Money
import dev.lepshee.whop.models.plans.CheckoutStyling
import dev.lepshee.whop.models.plans.CustomField
import dev.lepshee.whop.models.plans.PaymentMethodConfiguration
import dev.lepshee.whop.models.plans.TaxType
import dev.lepshee.whop.models.products.FileReference
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

/** Request body for `POST /checkout_configurations`. */
@Serializable
data class CreateCheckoutConfigurationRequest(
    /** Inline plan configuration for the checkout session. */
    val plan: CheckoutPlan? = null,
    /** Existing plan ID to create a checkout configuration for. */
    @SerialName("plan_id") val planId: String? = null,
    /** Company ID used when creating a setup-mode checkout configuration. */
    @SerialName("company_id") val companyId: String? = null,
    /** Checkout mode. Defaults to Whop's payment mode when omitted. */
    val mode: CheckoutMode? = null,
    /** Affiliate code to attribute the checkout to. */
    @SerialName("affiliate_code") val affiliateCode: String? = null,
    /** Whether the checkout should allow promo codes. */
    @SerialName("allow_promo_codes") val allowPromoCodes: Boolean? = null,
    /** Session-specific checkout styling overrides. */
    @SerialName("checkout_styling") val checkoutStyling: CheckoutStyling? = null,
    /** Currency used for setup-mode payment method availability. */
    val currency: String? = null,
    /** Arbitrary checkout metadata preserved by Whop when supported by the endpoint. */
    val metadata: Map<String, JsonElement>? = null,
    /** Session-specific payment method configuration. */
    @SerialName(
        "payment_method_configuration",
    ) val paymentMethodConfiguration: PaymentMethodConfiguration? = null,
    /** URL to redirect the user to after checkout completes. */
    @SerialName("redirect_url") val redirectUrl: String? = null,
    /** URL where checkout was initiated. */
    @SerialName("source_url") val sourceUrl: String? = null,
) {
    init {
        val usesInlinePlan = plan != null
        val usesExistingPlan = planId != null
        val usesSetupMode = mode == CheckoutMode.SETUP
        require(listOf(usesInlinePlan, usesExistingPlan, usesSetupMode).count { it } == 1) {
            "Checkout configuration requires exactly one of inline plan, planId, or setup mode."
        }
        require(planId == null || planId.isNotBlank()) { "Plan ID must not be blank." }
        require(companyId == null || companyId.isNotBlank()) { "Company ID must not be blank." }
        require(!usesSetupMode || companyId != null) { "Setup-mode checkout requires companyId." }
        require(affiliateCode == null || affiliateCode.isNotBlank()) { "Affiliate code must not be blank." }
        require(currency == null || currency.isNotBlank()) { "Currency must not be blank." }
        require(redirectUrl == null || redirectUrl.isNotBlank()) { "Redirect URL must not be blank." }
        require(sourceUrl == null || sourceUrl.isNotBlank()) { "Source URL must not be blank." }
    }
}

/** Checkout creation modes documented by Whop. */
@Serializable
enum class CheckoutMode {
    @SerialName("payment")
    PAYMENT,

    @SerialName("setup")
    SETUP,
}

/** Inline plan attributes used when creating a checkout configuration. */
@Serializable
data class CheckoutPlan(
    /** Company that owns the checkout plan. */
    @SerialName("company_id") val companyId: String,
    /** ISO currency code such as `usd`. */
    val currency: String,
    /** Whether the checkout is one-time or renewal-based. */
    @SerialName("plan_type") val planType: PlanType? = null,
    /** Initial price in major currency units, matching Whop's OpenAPI schema. */
    @SerialName("initial_price") val initialPrice: Double? = null,
    /** Whether adaptive local-currency pricing is enabled. */
    @SerialName("adaptive_pricing_enabled") val adaptivePricingEnabled: Boolean? = null,
    /** Application fee collected by a parent platform in major currency units. */
    @SerialName("application_fee_amount") val applicationFeeAmount: Double? = null,
    /** Billing interval in days for renewal plans. */
    @SerialName("billing_period") val billingPeriod: Int? = null,
    /** Custom checkout fields collected for this inline plan. */
    @SerialName("custom_fields") val customFields: List<CustomField>? = null,
    /** Inline plan description. */
    val description: String? = null,
    /** Number of days until memberships expire for expiration plans. */
    @SerialName("expiration_days") val expirationDays: Int? = null,
    /** Whether Whop should force creation of a new plan. */
    @SerialName("force_create_new_plan") val forceCreateNewPlan: Boolean? = null,
    /** Image for the inline plan. */
    val image: FileReference? = null,
    /** Private internal notes. */
    @SerialName("internal_notes") val internalNotes: String? = null,
    /** Tax override for this inline plan. */
    @SerialName("override_tax_type") val overrideTaxType: TaxType? = null,
    /** Payment method settings for this inline plan. */
    @SerialName(
        "payment_method_configuration",
    ) val paymentMethodConfiguration: PaymentMethodConfiguration? = null,
    /** Inline product attributes, when creating/finding a product for the plan. */
    val product: JsonObject? = null,
    /** Existing product ID for the inline plan. */
    @SerialName("product_id") val productId: String? = null,
    /** Release method for this inline plan. */
    @SerialName("release_method") val releaseMethod: dev.lepshee.whop.models.products.ReleaseMethod? = null,
    /** Renewal price in major currency units. */
    @SerialName("renewal_price") val renewalPrice: Double? = null,
    /** Required split-pay installments. */
    @SerialName("split_pay_required_payments") val splitPayRequiredPayments: Int? = null,
    /** Finite stock amount. */
    val stock: Int? = null,
    /** Inline plan title. */
    val title: String? = null,
    /** Trial period in days. */
    @SerialName("trial_period_days") val trialPeriodDays: Int? = null,
    /** Inline plan visibility. */
    val visibility: dev.lepshee.whop.models.common.Visibility? = null,
) {
    init {
        require(companyId.isNotBlank()) { "Company ID must not be blank." }
        require(currency.isNotBlank()) { "Currency must not be blank." }
        require(initialPrice == null || initialPrice >= 0.0) { "Initial price must not be negative." }
        require(applicationFeeAmount == null || applicationFeeAmount >= 0.0) { "Application fee amount must not be negative." }
        require(billingPeriod == null || billingPeriod > 0) { "Billing period must be positive." }
        require(expirationDays == null || expirationDays > 0) { "Expiration days must be positive." }
        require(productId == null || productId.isNotBlank()) { "Product ID must not be blank." }
        require(renewalPrice == null || renewalPrice >= 0.0) { "Renewal price must not be negative." }
        require(splitPayRequiredPayments == null || splitPayRequiredPayments > 0) { "Split-pay required payments must be positive." }
        require(stock == null || stock >= 0) { "Stock must not be negative." }
        require(title == null || title.isNotBlank()) { "Plan title must not be blank." }
        require(trialPeriodDays == null || trialPeriodDays >= 0) { "Trial period days must not be negative." }
    }

    companion object {
        /** Creates a checkout plan from integer-safe minor-unit money. */
        fun oneTime(
            companyId: String,
            price: Money,
            minorUnitsPerMajor: Long = 100,
        ): CheckoutPlan =
            CheckoutPlan(
                companyId = companyId,
                currency = price.currency,
                planType = PlanType.OneTime,
                initialPrice = price.toMajorUnits(minorUnitsPerMajor),
            )

        /** Creates a renewal checkout plan from integer-safe minor-unit money. */
        fun renewal(
            companyId: String,
            price: Money,
            minorUnitsPerMajor: Long = 100,
        ): CheckoutPlan =
            CheckoutPlan(
                companyId = companyId,
                currency = price.currency,
                planType = PlanType.Renewal,
                initialPrice = price.toMajorUnits(minorUnitsPerMajor),
            )
    }
}

/** Checkout plan type values accepted by Whop. */
@Serializable
enum class PlanType {
    @SerialName("one_time")
    OneTime,

    @SerialName("renewal")
    Renewal,
}

/** Checkout configuration returned by Whop. */
@Serializable
data class CheckoutConfiguration(
    /** Unique checkout configuration ID. */
    val id: String,
    /** Company ID associated with the checkout configuration when returned. */
    @SerialName("company_id") val companyId: String? = null,
    /** Metadata returned by Whop. */
    val metadata: Map<String, JsonElement>? = null,
    /** Hosted purchase URL for the checkout. */
    @SerialName("purchase_url") val purchaseUrl: String? = null,
    /** Checkout mode returned by Whop. */
    val mode: CheckoutMode? = null,
    /** Checkout currency when returned. */
    val currency: String? = null,
    /** Inline checkout plan payload returned by Whop. */
    val plan: JsonObject? = null,
    /** Affiliate code attached to the checkout. */
    @SerialName("affiliate_code") val affiliateCode: String? = null,
    /** URL Whop redirects users to after checkout. */
    @SerialName("redirect_url") val redirectUrl: String? = null,
    /** Whether promo codes are allowed for the checkout. */
    @SerialName("allow_promo_codes") val allowPromoCodes: Boolean? = null,
    /** Checkout-level payment method configuration. */
    @SerialName(
        "payment_method_configuration",
    ) val paymentMethodConfiguration: PaymentMethodConfiguration? = null,
    /** Reserved escape hatch for future raw payload support. */
    val raw: JsonObject? = null,
)
