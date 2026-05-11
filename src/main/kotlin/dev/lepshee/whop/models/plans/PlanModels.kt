package dev.lepshee.whop.models.plans

import dev.lepshee.whop.models.common.Money
import dev.lepshee.whop.models.common.Visibility
import dev.lepshee.whop.models.products.FileReference
import dev.lepshee.whop.models.products.ReleaseMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

/** Request body for `POST /plans`. */
@Serializable
data class CreatePlanRequest(
    /** Company that owns the plan. */
    @SerialName("company_id") val companyId: String,
    /** Product this plan belongs to. */
    @SerialName("product_id") val productId: String,
    /** Optional plan title. */
    val title: String? = null,
    /** Optional plan description. */
    val description: String? = null,
    /** ISO currency code such as `usd`. */
    val currency: String? = null,
    /** Whether this is one-time or renewal based. */
    @SerialName("plan_type") val planType: PlanType? = null,
    /** How this plan is released to buyers. */
    @SerialName("release_method") val releaseMethod: ReleaseMethod? = null,
    /** Billing interval in days for renewal plans. */
    @SerialName("billing_period") val billingPeriod: Int? = null,
    /** Initial price in major currency units. */
    @SerialName("initial_price") val initialPrice: Double? = null,
    /** Renewal price in major currency units. */
    @SerialName("renewal_price") val renewalPrice: Double? = null,
    /** Number of trial days before billing. */
    @SerialName("trial_period_days") val trialPeriodDays: Int? = null,
    /** Number of days until membership expiration. */
    @SerialName("expiration_days") val expirationDays: Int? = null,
    /** Purchasable stock quantity. */
    val stock: Int? = null,
    /** Whether stock is unlimited. */
    @SerialName("unlimited_stock") val unlimitedStock: Boolean? = null,
    /** Plan visibility state. */
    val visibility: Visibility? = null,
    /** Private notes visible to the business. */
    @SerialName("internal_notes") val internalNotes: String? = null,
    /** Optional plan image file reference. */
    val image: FileReference? = null,
    /** Optional payment method enablement settings. */
    @SerialName("payment_method_configuration") val paymentMethodConfiguration: PaymentMethodConfiguration? = null,
    /** Optional checkout styling overrides. */
    @SerialName("checkout_styling") val checkoutStyling: CheckoutStyling? = null,
    /** Whether adaptive local-currency pricing is enabled. */
    @SerialName("adaptive_pricing_enabled") val adaptivePricingEnabled: Boolean? = null,
    /** Custom checkout fields collected for this plan. */
    @SerialName("custom_fields") val customFields: List<CustomField>? = null,
    /** Legacy payment method control payload from Whop docs. */
    @SerialName("legacy_payment_method_controls") val legacyPaymentMethodControls: Boolean? = null,
    /** Tax override for this plan. */
    @SerialName("override_tax_type") val overrideTaxType: TaxType? = null,
    /** Required split-pay installments. */
    @SerialName("split_pay_required_payments") val splitPayRequiredPayments: Int? = null,
) {
    init {
        require(companyId.isNotBlank()) { "Company ID must not be blank." }
        require(productId.isNotBlank()) { "Product ID must not be blank." }
        validatePlanNumbers()
    }

    companion object {
        /** Creates a one-time plan request from integer-safe minor-unit money. */
        fun oneTime(
            companyId: String,
            productId: String,
            price: Money,
            releaseMethod: ReleaseMethod? = null,
            minorUnitsPerMajor: Long = 100,
        ): CreatePlanRequest =
            CreatePlanRequest(
                companyId = companyId,
                productId = productId,
                currency = price.currency,
                planType = PlanType.OneTime,
                releaseMethod = releaseMethod,
                initialPrice = price.toMajorUnits(minorUnitsPerMajor),
            )

        /** Creates a renewal plan request from integer-safe minor-unit money. */
        fun renewal(
            companyId: String,
            productId: String,
            price: Money,
            releaseMethod: ReleaseMethod? = null,
            minorUnitsPerMajor: Long = 100,
        ): CreatePlanRequest =
            CreatePlanRequest(
                companyId = companyId,
                productId = productId,
                currency = price.currency,
                planType = PlanType.Renewal,
                releaseMethod = releaseMethod,
                renewalPrice = price.toMajorUnits(minorUnitsPerMajor),
            )
    }
}

/** Request body for `PATCH /plans/{id}`. */
@Serializable
data class UpdatePlanRequest(
    /** New plan title. */
    val title: String? = null,
    /** New plan description. */
    val description: String? = null,
    /** New ISO currency code. */
    val currency: String? = null,
    /** New plan type. */
    @SerialName("plan_type") val planType: PlanType? = null,
    /** New release method. */
    @SerialName("release_method") val releaseMethod: ReleaseMethod? = null,
    /** New billing interval in days. */
    @SerialName("billing_period") val billingPeriod: Int? = null,
    /** New initial price in major currency units. */
    @SerialName("initial_price") val initialPrice: Double? = null,
    /** New renewal price in major currency units. */
    @SerialName("renewal_price") val renewalPrice: Double? = null,
    /** Optional displayed strike-through renewal price. */
    @SerialName("strike_through_renewal_price") val strikeThroughRenewalPrice: Double? = null,
    /** New trial period in days. */
    @SerialName("trial_period_days") val trialPeriodDays: Int? = null,
    /** New expiration period in days. */
    @SerialName("expiration_days") val expirationDays: Int? = null,
    /** New finite stock count. */
    val stock: Int? = null,
    /** Whether the plan should have unlimited stock. */
    @SerialName("unlimited_stock") val unlimitedStock: Boolean? = null,
    /** New visibility state. */
    val visibility: Visibility? = null,
    /** New internal notes. */
    @SerialName("internal_notes") val internalNotes: String? = null,
    /** New image file reference. */
    val image: FileReference? = null,
    /** New payment method configuration. */
    @SerialName("payment_method_configuration") val paymentMethodConfiguration: PaymentMethodConfiguration? = null,
    /** New checkout styling overrides. */
    @SerialName("checkout_styling") val checkoutStyling: CheckoutStyling? = null,
    /** Whether adaptive local-currency pricing is enabled. */
    @SerialName("adaptive_pricing_enabled") val adaptivePricingEnabled: Boolean? = null,
    /** Custom checkout fields collected for this plan. */
    @SerialName("custom_fields") val customFields: List<CustomField>? = null,
    /** Legacy payment method control payload from Whop docs. */
    @SerialName("legacy_payment_method_controls") val legacyPaymentMethodControls: Boolean? = null,
    /** Optional cancellation-discount offer payload. */
    @SerialName("offer_cancel_discount") val offerCancelDiscount: Boolean? = null,
    /** Tax override for this plan. */
    @SerialName("override_tax_type") val overrideTaxType: TaxType? = null,
    /** Optional displayed strike-through initial price. */
    @SerialName("strike_through_initial_price") val strikeThroughInitialPrice: Double? = null,
) {
    init {
        require(title == null || title.isNotBlank()) { "Plan title must not be blank." }
        require(currency == null || currency.isNotBlank()) { "Currency must not be blank." }
        require(billingPeriod == null || billingPeriod > 0) { "Billing period must be positive." }
        require(initialPrice == null || initialPrice >= 0.0) { "Initial price must not be negative." }
        require(renewalPrice == null || renewalPrice >= 0.0) { "Renewal price must not be negative." }
        require(strikeThroughRenewalPrice == null || strikeThroughRenewalPrice >= 0.0) {
            "Strike-through renewal price must not be negative."
        }
        require(strikeThroughInitialPrice == null || strikeThroughInitialPrice >= 0.0) {
            "Strike-through initial price must not be negative."
        }
        require(trialPeriodDays == null || trialPeriodDays >= 0) { "Trial period days must not be negative." }
        require(expirationDays == null || expirationDays > 0) { "Expiration days must be positive." }
        require(stock == null || stock >= 0) { "Stock must not be negative." }
    }
}

private fun CreatePlanRequest.validatePlanNumbers() {
    require(title == null || title.isNotBlank()) { "Plan title must not be blank." }
    require(currency == null || currency.isNotBlank()) { "Currency must not be blank." }
    require(billingPeriod == null || billingPeriod > 0) { "Billing period must be positive." }
    require(initialPrice == null || initialPrice >= 0.0) { "Initial price must not be negative." }
    require(renewalPrice == null || renewalPrice >= 0.0) { "Renewal price must not be negative." }
    require(splitPayRequiredPayments == null || splitPayRequiredPayments > 0) { "Split-pay required payments must be positive." }
    require(trialPeriodDays == null || trialPeriodDays >= 0) { "Trial period days must not be negative." }
    require(expirationDays == null || expirationDays > 0) { "Expiration days must be positive." }
    require(stock == null || stock >= 0) { "Stock must not be negative." }
}

/** Payment method configuration for checkout/payment method display. */
@Serializable
data class PaymentMethodConfiguration(
    /** Explicitly enabled payment method identifiers. */
    val enabled: List<String> = emptyList(),
    /** Explicitly disabled payment method identifiers. */
    val disabled: List<String> = emptyList(),
    /** Whether Whop platform defaults should be included. */
    @SerialName("include_platform_defaults") val includePlatformDefaults: Boolean? = null,
)

/** Custom checkout field configuration. */
@Serializable
data class CustomField(
    @SerialName("field_type") val fieldType: CustomFieldType,
    val name: String,
    val id: String? = null,
    val order: Int? = null,
    val placeholder: String? = null,
    val required: Boolean? = null,
) {
    init {
        require(name.isNotBlank()) { "Custom field name must not be blank." }
        require(id == null || id.isNotBlank()) { "Custom field ID must not be blank." }
        require(order == null || order >= 0) { "Custom field order must not be negative." }
    }
}

/** Custom field types documented by Whop. */
@Serializable
enum class CustomFieldType {
    @SerialName("text")
    Text,
}

/** Tax override types documented by Whop. */
@Serializable
enum class TaxType {
    @SerialName("inclusive")
    Inclusive,

    @SerialName("exclusive")
    Exclusive,

    @SerialName("unspecified")
    Unspecified,
}

/** Styling overrides for Whop checkout pages. */
@Serializable
data class CheckoutStyling(
    /** Checkout background color, usually a hex color code. */
    @SerialName("background_color") val backgroundColor: String? = null,
    /** Border shape for controls. */
    @SerialName("border_style") val borderStyle: CheckoutShape? = null,
    /** Button color, usually a hex color code. */
    @SerialName("button_color") val buttonColor: String? = null,
    /** Checkout font family. */
    @SerialName("font_family") val fontFamily: CheckoutFont? = null,
)

/** Plan object returned by Whop. */
@Serializable
data class Plan(
    /** Unique plan ID. */
    val id: String,
    /** Plan title. */
    val title: String? = null,
    /** Plan description. */
    val description: String? = null,
    /** Plan visibility state. */
    val visibility: Visibility? = null,
    /** Plan billing type. */
    @SerialName("plan_type") val planType: PlanType? = null,
    /** How this plan is released to buyers. */
    @SerialName("release_method") val releaseMethod: ReleaseMethod? = null,
    /** ISO currency code. */
    val currency: String? = null,
    /** Creation timestamp. */
    @SerialName("created_at") val createdAt: String? = null,
    /** Last update timestamp. */
    @SerialName("updated_at") val updatedAt: String? = null,
    /** Billing interval in days. */
    @SerialName("billing_period") val billingPeriod: Int? = null,
    /** Hosted purchase URL for this plan. */
    @SerialName("purchase_url") val purchaseUrl: String? = null,
    /** Expiration period in days. */
    @SerialName("expiration_days") val expirationDays: Int? = null,
    /** Initial price in major currency units. */
    @SerialName("initial_price") val initialPrice: Double? = null,
    /** Renewal price in major currency units. */
    @SerialName("renewal_price") val renewalPrice: Double? = null,
    /** Trial period in days. */
    @SerialName("trial_period_days") val trialPeriodDays: Int? = null,
    /** Number of members on this plan. */
    @SerialName("member_count") val memberCount: Int? = null,
    /** Business-only notes. */
    @SerialName("internal_notes") val internalNotes: String? = null,
    /** Finite stock count. */
    val stock: Int? = null,
    /** Whether the plan has unlimited stock. */
    @SerialName("unlimited_stock") val unlimitedStock: Boolean? = null,
    /** Payment method display configuration. */
    @SerialName("payment_method_configuration") val paymentMethodConfiguration: PaymentMethodConfiguration? = null,
    /** Associated company payload. */
    val company: JsonObject? = null,
    /** Associated product payload. */
    val product: JsonObject? = null,
    /** Associated invoice payload. */
    val invoice: JsonObject? = null,
    /** Required split-pay installments. */
    @SerialName("split_pay_required_payments") val splitPayRequiredPayments: Int? = null,
    /** Whether adaptive local-currency pricing is enabled. */
    @SerialName("adaptive_pricing_enabled") val adaptivePricingEnabled: Boolean? = null,
    /** Tax classification returned by Whop. */
    @SerialName("tax_type") val taxType: TaxType? = null,
    /** Whether tax collection is enabled. */
    @SerialName("collect_tax") val collectTax: Boolean? = null,
    /** Custom checkout fields returned by Whop. */
    @SerialName("custom_fields") val customFields: List<CustomField> = emptyList(),
)

/** Whop plan type values. */
@Serializable
enum class PlanType {
    @SerialName("renewal")
    Renewal,

    @SerialName("one_time")
    OneTime,
}

/** Sort fields accepted by plan list endpoints. */
@Serializable
enum class PlanOrder {
    @SerialName("id")
    Id,

    @SerialName("active_members_count")
    ActiveMembersCount,

    @SerialName("created_at")
    CreatedAt,

    @SerialName("internal_notes")
    InternalNotes,

    @SerialName("expires_at")
    ExpiresAt,
}

/** Checkout shape options. */
@Serializable
enum class CheckoutShape {
    @SerialName("rounded")
    Rounded,

    @SerialName("pill")
    Pill,

    @SerialName("rectangular")
    Rectangular,
}

/** Checkout font options. */
@Serializable
enum class CheckoutFont {
    @SerialName("system")
    System,

    @SerialName("roboto")
    Roboto,

    @SerialName("open_sans")
    OpenSans,
}
