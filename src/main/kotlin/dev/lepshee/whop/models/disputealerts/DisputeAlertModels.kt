package dev.lepshee.whop.models.disputealerts

import dev.lepshee.whop.models.memberships.MembershipStatus
import dev.lepshee.whop.models.payments.BillingReason
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DisputeAlertListItem(
    val id: String,
    @SerialName("alert_type") val alertType: DisputeAlertType,
    val amount: Double,
    val currency: String,
    @SerialName("created_at") val createdAt: String,
    @SerialName("transaction_date") val transactionDate: String? = null,
    @SerialName("charge_for_alert") val chargeForAlert: Boolean,
    val payment: DisputeAlertRelatedPayment? = null,
    val dispute: DisputeAlertRelatedDispute? = null,
)

@Serializable
data class DisputeAlert(
    val id: String,
    @SerialName("alert_type") val alertType: DisputeAlertType,
    val amount: Double,
    val currency: String,
    @SerialName("created_at") val createdAt: String,
    @SerialName("transaction_date") val transactionDate: String? = null,
    @SerialName("charge_for_alert") val chargeForAlert: Boolean,
    val payment: DisputeAlertPayment? = null,
    val dispute: DisputeAlertDispute? = null,
)

@Serializable
data class DisputeAlertRelatedPayment(
    val id: String,
)

@Serializable
data class DisputeAlertRelatedDispute(
    val id: String,
)

@Serializable
data class DisputeAlertPayment(
    val id: String,
    val total: Double? = null,
    val subtotal: Double? = null,
    @SerialName("usd_total") val usdTotal: Double? = null,
    val currency: String? = null,
    @SerialName("created_at") val createdAt: String,
    @SerialName("paid_at") val paidAt: String? = null,
    @SerialName("dispute_alerted_at") val disputeAlertedAt: String? = null,
    @SerialName("payment_method_type") val paymentMethodType: String? = null,
    @SerialName("billing_reason") val billingReason: BillingReason? = null,
    @SerialName("card_brand") val cardBrand: String? = null,
    @SerialName("card_last4") val cardLast4: String? = null,
    val user: DisputeAlertUser? = null,
    val member: DisputeAlertMember? = null,
    val membership: DisputeAlertMembership? = null,
)

@Serializable
data class DisputeAlertUser(
    val id: String,
    val name: String? = null,
    val username: String,
    val email: String? = null,
)

@Serializable
data class DisputeAlertMember(
    val id: String,
    val phone: String? = null,
)

@Serializable
data class DisputeAlertMembership(
    val id: String,
    val status: MembershipStatus,
)

@Serializable
data class DisputeAlertDispute(
    val id: String,
    val amount: Double,
    val currency: String,
    val status: DisputeAlertDisputeStatus,
    val reason: String? = null,
    @SerialName("created_at") val createdAt: String? = null,
)

@Serializable
enum class DisputeAlertType {
    @SerialName("dispute")
    Dispute,

    @SerialName("dispute_rdr")
    DisputeRdr,

    @SerialName("fraud")
    Fraud,
}

@Serializable
enum class DisputeAlertDisputeStatus {
    @SerialName("warning_needs_response")
    WarningNeedsResponse,

    @SerialName("warning_under_review")
    WarningUnderReview,

    @SerialName("warning_closed")
    WarningClosed,

    @SerialName("needs_response")
    NeedsResponse,

    @SerialName("under_review")
    UnderReview,

    @SerialName("won")
    Won,

    @SerialName("lost")
    Lost,

    @SerialName("closed")
    Closed,

    @SerialName("other")
    Other,
}
