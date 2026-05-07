package com.lepshee.whop.models.payments

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
    /** Reserved escape hatch for future raw payload support. */
    val raw: JsonObject? = null,
)

@Serializable
data class CreatePaymentRequest(
    @SerialName("company_id") val companyId: String,
    @SerialName("member_id") val memberId: String,
    @SerialName("payment_method_id") val paymentMethodId: String,
    @SerialName("plan_id") val planId: String? = null,
    val plan: JsonObject? = null,
    val metadata: JsonObject? = null,
)

@Serializable
data class RefundPaymentRequest(
    @SerialName("partial_amount") val partialAmount: Long? = null,
)

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
