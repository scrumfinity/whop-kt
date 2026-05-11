package dev.lepshee.whop.webhooks

/** Standard Webhooks header names used by Whop webhook deliveries. */
object WhopWebhookHeaders {
    /** Unique webhook delivery ID. Use this for application-level deduplication. */
    const val ID = "webhook-id"

    /** Unix timestamp, in seconds, included in the signed payload. */
    const val TIMESTAMP = "webhook-timestamp"

    /** Space-delimited signatures such as `v1,<base64-hmac-sha256>`. */
    const val SIGNATURE = "webhook-signature"
}
