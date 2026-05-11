package dev.lepshee.whop.models.webhooks

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Webhook endpoint registered with Whop. */
@Serializable
data class WebhookEndpoint(
    val id: String,
    val url: String,
    val enabled: Boolean? = null,
    val events: List<WebhookEvent> = emptyList(),
    @SerialName("api_version") val apiVersion: WebhookApiVersion? = null,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("child_resource_events") val childResourceEvents: Boolean? = null,
    @SerialName("testable_events") val testableEvents: List<String> = emptyList(),
    @SerialName("resource_id") val resourceId: String? = null,
    @SerialName("webhook_secret") val webhookSecret: String? = null,
)

@Serializable
enum class WebhookApiVersion {
    @SerialName("v1")
    V1,

    @SerialName("v2")
    V2,

    @SerialName("v5")
    V5,
}

@Serializable
data class CreateWebhookRequest(
    val url: String,
    @SerialName("api_version") val apiVersion: WebhookApiVersion? = null,
    @SerialName("child_resource_events") val childResourceEvents: Boolean? = null,
    val enabled: Boolean? = null,
    val events: List<WebhookEvent>? = null,
    @SerialName("resource_id") val resourceId: String? = null,
) {
    init {
        require(url.isNotBlank()) { "Webhook URL must not be blank." }
        require(resourceId == null || resourceId.isNotBlank()) { "Resource ID must not be blank." }
    }
}

@Serializable
data class UpdateWebhookRequest(
    val url: String? = null,
    @SerialName("api_version") val apiVersion: WebhookApiVersion? = null,
    @SerialName("child_resource_events") val childResourceEvents: Boolean? = null,
    val enabled: Boolean? = null,
    val events: List<WebhookEvent>? = null,
) {
    init {
        require(url == null || url.isNotBlank()) { "Webhook URL must not be blank." }
    }
}

@Serializable
enum class WebhookEvent {
    @SerialName("invoice.created")
    InvoiceCreated,

    @SerialName("invoice.marked_uncollectible")
    InvoiceMarkedUncollectible,

    @SerialName("invoice.paid")
    InvoicePaid,

    @SerialName("invoice.past_due")
    InvoicePastDue,

    @SerialName("invoice.voided")
    InvoiceVoided,

    @SerialName("membership.activated")
    MembershipActivated,

    @SerialName("membership.deactivated")
    MembershipDeactivated,

    @SerialName("entry.created")
    EntryCreated,

    @SerialName("entry.approved")
    EntryApproved,

    @SerialName("entry.denied")
    EntryDenied,

    @SerialName("entry.deleted")
    EntryDeleted,

    @SerialName("setup_intent.requires_action")
    SetupIntentRequiresAction,

    @SerialName("setup_intent.succeeded")
    SetupIntentSucceeded,

    @SerialName("setup_intent.canceled")
    SetupIntentCanceled,

    @SerialName("withdrawal.created")
    WithdrawalCreated,

    @SerialName("withdrawal.updated")
    WithdrawalUpdated,

    @SerialName("course_lesson_interaction.completed")
    CourseLessonInteractionCompleted,

    @SerialName("payout_method.created")
    PayoutMethodCreated,

    @SerialName("verification.succeeded")
    VerificationSucceeded,

    @SerialName("payout_account.status_updated")
    PayoutAccountStatusUpdated,

    @SerialName("resolution_center_case.created")
    ResolutionCenterCaseCreated,

    @SerialName("resolution_center_case.updated")
    ResolutionCenterCaseUpdated,

    @SerialName("resolution_center_case.decided")
    ResolutionCenterCaseDecided,

    @SerialName("payment.created")
    PaymentCreated,

    @SerialName("payment.succeeded")
    PaymentSucceeded,

    @SerialName("payment.failed")
    PaymentFailed,

    @SerialName("payment.pending")
    PaymentPending,

    @SerialName("dispute.created")
    DisputeCreated,

    @SerialName("dispute.updated")
    DisputeUpdated,

    @SerialName("refund.created")
    RefundCreated,

    @SerialName("refund.updated")
    RefundUpdated,

    @SerialName("dispute_alert.created")
    DisputeAlertCreated,

    @SerialName("membership.cancel_at_period_end_changed")
    MembershipCancelAtPeriodEndChanged,
}
