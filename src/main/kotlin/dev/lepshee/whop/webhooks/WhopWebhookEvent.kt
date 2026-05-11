package dev.lepshee.whop.webhooks

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

/** Raw Standard Webhooks-style event envelope returned by Whop. */
@Serializable
data class WhopWebhookEnvelope(
    /** Dot-separated event type, such as `payment.succeeded`. */
    val type: String,
    /** Event timestamp when included in the JSON body. */
    val timestamp: String? = null,
    /** Event-specific payload. */
    val data: JsonElement,
)

/** Verified Whop webhook event returned by [WhopWebhookVerifier]. */
sealed class WhopWebhookEvent {
    /** Unique delivery ID from the `webhook-id` header. */
    abstract val id: String

    /** Dot-separated Whop event type. */
    abstract val type: String

    /** Unix timestamp from the `webhook-timestamp` header. */
    abstract val timestamp: Long

    /** Raw event data payload. */
    abstract val data: JsonElement

    /** Payment succeeded event. Use this to trigger fulfillment after payment confirmation. */
    data class PaymentSucceeded(
        override val id: String,
        override val timestamp: Long,
        override val data: JsonElement,
    ) : WhopWebhookEvent() {
        override val type: String = TYPE

        companion object {
            /** Whop event type string. */
            const val TYPE = "payment.succeeded"
        }
    }

    /** Membership activated event. */
    data class MembershipActivated(
        override val id: String,
        override val timestamp: Long,
        override val data: JsonElement,
    ) : WhopWebhookEvent() {
        override val type: String = TYPE

        companion object {
            /** Whop event type string. */
            const val TYPE = "membership.activated"
        }
    }

    /** Membership deactivated event. */
    data class MembershipDeactivated(
        override val id: String,
        override val timestamp: Long,
        override val data: JsonElement,
    ) : WhopWebhookEvent() {
        override val type: String = TYPE

        companion object {
            /** Whop event type string. */
            const val TYPE = "membership.deactivated"
        }
    }

    /** Entry created event. */
    data class EntryCreated(
        override val id: String,
        override val timestamp: Long,
        override val data: JsonElement,
    ) : WhopWebhookEvent() {
        override val type: String = TYPE

        companion object {
            /** Whop event type string. */
            const val TYPE = "entry.created"
        }
    }

    /** Refund created event. */
    data class RefundCreated(
        override val id: String,
        override val timestamp: Long,
        override val data: JsonElement,
    ) : WhopWebhookEvent() {
        override val type: String = TYPE

        companion object {
            /** Whop event type string. */
            const val TYPE = "refund.created"
        }
    }

    /** Dispute created event. */
    data class DisputeCreated(
        override val id: String,
        override val timestamp: Long,
        override val data: JsonElement,
    ) : WhopWebhookEvent() {
        override val type: String = TYPE

        companion object {
            /** Whop event type string. */
            const val TYPE = "dispute.created"
        }
    }

    /** Fallback for valid, verified event types not yet modeled by this SDK. */
    data class Generic(
        override val id: String,
        override val type: String,
        override val timestamp: Long,
        override val data: JsonElement,
        /** Full decoded envelope for callers that need fields beyond [data]. */
        val envelope: WhopWebhookEnvelope,
    ) : WhopWebhookEvent()
}

@Serializable
internal data class WhopWebhookObjectEnvelope(
    val type: String,
    val timestamp: String? = null,
    @SerialName("data") val data: JsonObject,
)
