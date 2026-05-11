package dev.lepshee.whop.models.common

import kotlinx.serialization.Serializable

/** Monetary amount represented in minor units for SDK models that need integer-safe money. */
@Serializable
data class Money(
    /** ISO currency code such as `usd`. */
    val currency: String,
    /** Amount in minor currency units, such as cents for USD. */
    val amount: Long,
) {
    init {
        require(currency.isNotBlank()) { "Currency must not be blank." }
        require(amount >= 0) { "Amount must not be negative." }
    }

    /** Converts minor units to major units for Whop endpoints whose OpenAPI schema accepts decimal prices. */
    fun toMajorUnits(minorUnitsPerMajor: Long = 100): Double {
        require(minorUnitsPerMajor > 0) { "Minor units per major unit must be positive." }
        return amount.toDouble() / minorUnitsPerMajor.toDouble()
    }
}
