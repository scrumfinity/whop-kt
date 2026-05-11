package dev.lepshee.whop.models.topups

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Request body for `POST /topups`. */
@Serializable
data class CreateTopupRequest(
    /** Amount to add to the company balance in the specified currency. */
    val amount: Double,
    /** Company to add funds to. */
    @SerialName("company_id") val companyId: String,
    /** Currency for the top-up amount, such as `usd`. */
    val currency: String,
    /** Stored payment method to charge for the top-up. */
    @SerialName("payment_method_id") val paymentMethodId: String,
) {
    init {
        require(amount.isFinite() && amount > 0.0) { "Top-up amount must be positive." }
        require(companyId.isNotBlank()) { "Company ID must not be blank." }
        require(currency.isNotBlank()) { "Currency must not be blank." }
        require(paymentMethodId.isNotBlank()) { "Payment method ID must not be blank." }
    }
}

/** Top-up payment returned by Whop. */
@Serializable
data class Topup(
    /** Unique top-up payment ID. */
    val id: String,
    /** Current receipt lifecycle status. */
    val status: TopupStatus? = null,
    /** Creation timestamp. */
    @SerialName("created_at") val createdAt: String,
    /** Successful payment timestamp, when collected. */
    @SerialName("paid_at") val paidAt: String? = null,
    /** Top-up currency, when returned. */
    val currency: String? = null,
    /** Total amount shown to the creator. */
    val total: Double? = null,
    /** Failure reason when the top-up fails. */
    @SerialName("failure_message") val failureMessage: String? = null,
)

/** Receipt status values returned by top-ups. */
@Serializable
enum class TopupStatus {
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
