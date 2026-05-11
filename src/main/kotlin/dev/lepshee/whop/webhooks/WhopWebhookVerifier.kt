package dev.lepshee.whop.webhooks

import dev.lepshee.whop.core.JsonConfig
import java.security.MessageDigest
import java.time.Clock
import java.util.Base64
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

/**
 * Verifies Whop webhooks using the Standard Webhooks signing model.
 *
 * Verification uses `webhook-id`, `webhook-timestamp`, and `webhook-signature` headers, signs
 * `{id}.{timestamp}.{payload}`, rejects stale timestamps, compares signatures in constant time,
 * and only parses JSON after a signature has been accepted.
 */
class WhopWebhookVerifier(
    private val secret: String,
    private val clock: Clock = Clock.systemUTC(),
    private val toleranceSeconds: Long = DEFAULT_TOLERANCE_SECONDS,
) {
    init {
        require(secret.isNotBlank()) { "Webhook secret must not be blank." }
        require(toleranceSeconds > 0) { "Webhook timestamp tolerance must be positive." }
    }

    /**
     * Verifies a raw webhook payload and parses it into a typed [WhopWebhookEvent].
     *
     * Pass the exact raw request body bytes converted to a string without reformatting JSON. Re-encoding
     * or pretty-printing the body will change the signature input and fail verification.
     */
    fun verifyAndParse(
        payload: String,
        headers: Map<String, String>,
    ): WhopWebhookEvent {
        val id =
            headers.firstHeaderValue(WhopWebhookHeaders.ID)
                ?: throw WhopWebhookVerificationException("Missing ${WhopWebhookHeaders.ID} header.")
        val timestampHeader =
            headers.firstHeaderValue(WhopWebhookHeaders.TIMESTAMP)
                ?: throw WhopWebhookVerificationException("Missing ${WhopWebhookHeaders.TIMESTAMP} header.")
        val signatureHeader =
            headers.firstHeaderValue(WhopWebhookHeaders.SIGNATURE)
                ?: throw WhopWebhookVerificationException("Missing ${WhopWebhookHeaders.SIGNATURE} header.")
        val timestamp =
            timestampHeader.toLongOrNull()
                ?: throw WhopWebhookVerificationException("Invalid ${WhopWebhookHeaders.TIMESTAMP} header.")

        verifyTimestamp(timestamp)
        verifySignature(id = id, timestamp = timestampHeader, payload = payload, signatureHeader = signatureHeader)

        val envelope =
            runCatching { JsonConfig.whopJson.decodeFromString<WhopWebhookEnvelope>(payload) }
                .getOrElse { throw WhopWebhookVerificationException("Webhook payload is not a valid Whop event.", it) }
        return envelope.toEvent(id = id, timestamp = timestamp)
    }

    private fun verifyTimestamp(timestamp: Long) {
        val now = clock.instant().epochSecond
        if (kotlin.math.abs(now - timestamp) > toleranceSeconds) {
            throw WhopWebhookVerificationException("Webhook timestamp is outside the allowed tolerance.")
        }
    }

    private fun verifySignature(
        id: String,
        timestamp: String,
        payload: String,
        signatureHeader: String,
    ) {
        val signedPayload = "$id.$timestamp.$payload"
        val expected = hmacSha256(signedPayload)
        val signatures =
            signatureHeader
                .split(' ')
                .mapNotNull { it.trim().takeIf(String::isNotEmpty) }
                .mapNotNull { signature -> signature.removePrefix("v1,").takeIf { it != signature } }

        if (signatures.isEmpty()) {
            throw WhopWebhookVerificationException("No v1 webhook signatures were provided.")
        }

        val matched =
            signatures.any { signature ->
                runCatching { MessageDigest.isEqual(expected, Base64.getDecoder().decode(signature)) }.getOrDefault(false)
            }
        if (!matched) {
            throw WhopWebhookVerificationException("Webhook signature verification failed.")
        }
    }

    private fun hmacSha256(payload: String): ByteArray {
        val mac = Mac.getInstance("HmacSHA256")
        mac.init(SecretKeySpec(decodedSecret(), "HmacSHA256"))
        return mac.doFinal(payload.toByteArray(Charsets.UTF_8))
    }

    private fun decodedSecret(): ByteArray {
        val normalized = secret.removePrefix("whsec_")
        return runCatching { Base64.getDecoder().decode(normalized) }
            .getOrElse { throw WhopWebhookVerificationException("Webhook secret must be base64 encoded.") }
    }

    private fun WhopWebhookEnvelope.toEvent(
        id: String,
        timestamp: Long,
    ): WhopWebhookEvent =
        when (type) {
            WhopWebhookEvent.PaymentSucceeded.TYPE -> WhopWebhookEvent.PaymentSucceeded(id, timestamp, data)
            WhopWebhookEvent.MembershipActivated.TYPE -> WhopWebhookEvent.MembershipActivated(id, timestamp, data)
            WhopWebhookEvent.MembershipDeactivated.TYPE -> WhopWebhookEvent.MembershipDeactivated(id, timestamp, data)
            WhopWebhookEvent.EntryCreated.TYPE -> WhopWebhookEvent.EntryCreated(id, timestamp, data)
            WhopWebhookEvent.RefundCreated.TYPE -> WhopWebhookEvent.RefundCreated(id, timestamp, data)
            WhopWebhookEvent.DisputeCreated.TYPE -> WhopWebhookEvent.DisputeCreated(id, timestamp, data)
            else -> WhopWebhookEvent.Generic(id = id, type = type, timestamp = timestamp, data = data, envelope = this)
        }

    private fun Map<String, String>.firstHeaderValue(name: String): String? =
        entries.firstOrNull { (key) -> key.equals(name, ignoreCase = true) }?.value

    companion object {
        /** Default replay tolerance used by the official Standard Webhooks verifier. */
        const val DEFAULT_TOLERANCE_SECONDS: Long = 300
    }
}

/** Raised when a webhook is missing headers, stale, malformed, or has an invalid signature. */
class WhopWebhookVerificationException(
    message: String,
    cause: Throwable? = null,
) : RuntimeException(message, cause)
