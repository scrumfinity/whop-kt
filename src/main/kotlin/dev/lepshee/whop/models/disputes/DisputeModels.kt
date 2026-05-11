package dev.lepshee.whop.models.disputes

import dev.lepshee.whop.models.memberships.MembershipStatus
import dev.lepshee.whop.models.payments.BillingReason
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

@Serializable
data class DisputeListItem(
    val id: String,
    val amount: Double,
    val currency: String,
    val status: DisputeStatus,
    val editable: Boolean? = null,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("visa_rdr") val visaRdr: Boolean,
    @SerialName("needs_response_by") val needsResponseBy: String? = null,
    val reason: String? = null,
    val plan: DisputePlan? = null,
    val product: DisputeProduct? = null,
    val company: DisputeCompany? = null,
    val payment: DisputeRelatedPayment? = null,
)

@Serializable
data class Dispute(
    val id: String,
    val amount: Double,
    val currency: String,
    val status: DisputeStatus,
    val editable: Boolean? = null,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("visa_rdr") val visaRdr: Boolean,
    @SerialName("needs_response_by") val needsResponseBy: String? = null,
    val reason: String? = null,
    val plan: DisputePlan? = null,
    val product: DisputeProduct? = null,
    val company: DisputeCompany? = null,
    val payment: DisputePayment? = null,
    @SerialName("access_activity_log") val accessActivityLog: String? = null,
    @SerialName("billing_address") val billingAddress: String? = null,
    @SerialName("cancellation_policy_disclosure") val cancellationPolicyDisclosure: String? = null,
    @SerialName("customer_email_address") val customerEmailAddress: String? = null,
    @SerialName("customer_name") val customerName: String? = null,
    val notes: String? = null,
    @SerialName("product_description") val productDescription: String? = null,
    @SerialName("refund_policy_disclosure") val refundPolicyDisclosure: String? = null,
    @SerialName("refund_refusal_explanation") val refundRefusalExplanation: String? = null,
    @SerialName("service_date") val serviceDate: String? = null,
    @SerialName("cancellation_policy_attachment") val cancellationPolicyAttachment: DisputeAttachment? = null,
    @SerialName("customer_communication_attachment") val customerCommunicationAttachment: DisputeAttachment? = null,
    @SerialName("refund_policy_attachment") val refundPolicyAttachment: DisputeAttachment? = null,
    @SerialName("uncategorized_attachment") val uncategorizedAttachment: DisputeAttachment? = null,
)

@Serializable
data class DisputePlan(
    val id: String,
)

@Serializable
data class DisputeProduct(
    val id: String,
    val title: String,
)

@Serializable
data class DisputeCompany(
    val id: String,
    val title: String,
)

@Serializable
data class DisputeRelatedPayment(
    val id: String,
)

@Serializable
data class DisputePayment(
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
    val user: DisputeUser? = null,
    val member: DisputeMember? = null,
    val membership: DisputeMembership? = null,
)

@Serializable
data class DisputeUser(
    val id: String,
    val name: String? = null,
    val username: String,
    val email: String? = null,
)

@Serializable
data class DisputeMember(
    val id: String,
    val phone: String? = null,
)

@Serializable
data class DisputeMembership(
    val id: String,
    val status: MembershipStatus,
)

@Serializable
data class DisputeAttachment(
    val id: String,
    val filename: String? = null,
    @SerialName("content_type") val contentType: String? = null,
    val url: String? = null,
)

@Serializable
data class UpdateDisputeEvidenceRequest(
    @SerialName("access_activity_log") val accessActivityLog: JsonElement? = null,
    @SerialName("billing_address") val billingAddress: JsonElement? = null,
    @SerialName("cancellation_policy_disclosure") val cancellationPolicyDisclosure: JsonElement? = null,
    @SerialName("customer_email_address") val customerEmailAddress: JsonElement? = null,
    @SerialName("customer_name") val customerName: JsonElement? = null,
    val notes: JsonElement? = null,
    @SerialName("product_description") val productDescription: JsonElement? = null,
    @SerialName("refund_policy_disclosure") val refundPolicyDisclosure: JsonElement? = null,
    @SerialName("refund_refusal_explanation") val refundRefusalExplanation: JsonElement? = null,
    @SerialName("service_date") val serviceDate: JsonElement? = null,
    @SerialName("cancellation_policy_attachment") val cancellationPolicyAttachment: JsonElement? = null,
    @SerialName("customer_communication_attachment") val customerCommunicationAttachment: JsonElement? = null,
    @SerialName("refund_policy_attachment") val refundPolicyAttachment: JsonElement? = null,
    @SerialName("uncategorized_attachment") val uncategorizedAttachment: JsonElement? = null,
) {
    init {
        requireOptionalText(accessActivityLog, "Access activity log")
        requireOptionalText(billingAddress, "Billing address")
        requireOptionalText(cancellationPolicyDisclosure, "Cancellation policy disclosure")
        requireOptionalText(customerEmailAddress, "Customer email address")
        requireOptionalText(customerName, "Customer name")
        requireOptionalText(notes, "Notes")
        requireOptionalText(productDescription, "Product description")
        requireOptionalText(refundPolicyDisclosure, "Refund policy disclosure")
        requireOptionalText(refundRefusalExplanation, "Refund refusal explanation")
        requireOptionalText(serviceDate, "Service date")
    }

    companion object {
        fun text(value: String): JsonPrimitive {
            require(value.isNotBlank()) { "Dispute evidence text values must not be blank." }
            return JsonPrimitive(value)
        }

        fun file(fileId: String): JsonElement {
            require(fileId.isNotBlank()) { "Dispute evidence file ID must not be blank." }
            return buildJsonObject { put("id", fileId) }
        }

        val clear: JsonNull = JsonNull
    }
}

@Serializable
data class DisputeEvidenceFileInput(
    val id: String,
) {
    init {
        require(id.isNotBlank()) { "Dispute evidence file ID must not be blank." }
    }
}

private fun requireOptionalText(
    value: JsonElement?,
    fieldName: String,
) {
    if (value is JsonPrimitive && value.isString) {
        require(value.content.isNotBlank()) { "$fieldName must not be blank." }
    }
}

@Serializable
enum class DisputeStatus {
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
