package com.lepshee.whop.models.checkout

import com.lepshee.whop.models.common.Money
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

/** Request body for `POST /checkout_configurations`. */
@Serializable
data class CreateCheckoutConfigurationRequest(
    /** Inline plan configuration for the checkout session. */
    val plan: CheckoutPlan,
    /** Arbitrary checkout metadata preserved by Whop when supported by the endpoint. */
    val metadata: Map<String, JsonElement> = emptyMap(),
)

/** Inline plan attributes used when creating a checkout configuration. */
@Serializable
data class CheckoutPlan(
    /** Company that owns the checkout plan. */
    @SerialName("company_id") val companyId: String,
    /** ISO currency code such as `usd`. */
    val currency: String,
    /** Whether the checkout is one-time or renewal-based. */
    @SerialName("plan_type") val planType: PlanType,
    /** Initial price in major currency units, matching Whop's OpenAPI schema. */
    @SerialName("initial_price") val initialPrice: Double? = null,
) {
    init {
        require(companyId.isNotBlank()) { "Company ID must not be blank." }
        require(currency.isNotBlank()) { "Currency must not be blank." }
        require(initialPrice == null || initialPrice >= 0.0) { "Initial price must not be negative." }
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
    val metadata: Map<String, JsonElement> = emptyMap(),
    /** Hosted purchase URL for the checkout. */
    @SerialName("purchase_url") val purchaseUrl: String? = null,
    /** Reserved escape hatch for future raw payload support. */
    val raw: JsonObject? = null,
)
