package dev.lepshee.whop

import dev.lepshee.whop.webhooks.WhopWebhookEvent
import dev.lepshee.whop.webhooks.WhopWebhookHeaders
import dev.lepshee.whop.webhooks.WhopWebhookVerificationException
import dev.lepshee.whop.webhooks.WhopWebhookVerifier
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset
import java.util.Base64
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertIs

class WhopWebhookVerifierTest {
    private val secretBytes = "test-webhook-secret".toByteArray(Charsets.UTF_8)
    private val secret = "whsec_${Base64.getEncoder().encodeToString(secretBytes)}"
    private val now = 1_700_000_000L
    private val clock = Clock.fixed(Instant.ofEpochSecond(now), ZoneOffset.UTC)

    @Test
    fun `valid signature verifies and parses typed event`() {
        val payload = """{"type":"payment.succeeded","timestamp":"2026-05-07T12:00:00Z","data":{"id":"pay_123"}}"""
        val headers = signedHeaders(id = "evt_123", timestamp = now, payload = payload)

        val event = WhopWebhookVerifier(secret, clock).verifyAndParse(payload, headers)

        val paymentSucceeded = assertIs<WhopWebhookEvent.PaymentSucceeded>(event)
        assertEquals("evt_123", paymentSucceeded.id)
        assertEquals(now, paymentSucceeded.timestamp)
        assertEquals("payment.succeeded", paymentSucceeded.type)
    }

    @Test
    fun `unknown event verifies and returns generic fallback`() {
        val payload = """{"type":"payment.pending","data":{"id":"pay_123"}}"""
        val headers = signedHeaders(id = "evt_123", timestamp = now, payload = payload)

        val event = WhopWebhookVerifier(secret, clock).verifyAndParse(payload, headers)

        val generic = assertIs<WhopWebhookEvent.Generic>(event)
        assertEquals("payment.pending", generic.type)
    }

    @Test
    fun `invalid signature is rejected before event parsing`() {
        val payload = "not-json"
        val headers = signedHeaders(id = "evt_123", timestamp = now, payload = payload).toMutableMap()
        headers[WhopWebhookHeaders.SIGNATURE] = "v1,${Base64.getEncoder().encodeToString("bad".toByteArray())}"

        assertFailsWith<WhopWebhookVerificationException> {
            WhopWebhookVerifier(secret, clock).verifyAndParse(payload, headers)
        }
    }

    @Test
    fun `malformed json with valid signature is wrapped as verification exception`() {
        val payload = "not-json"
        val headers = signedHeaders(id = "evt_123", timestamp = now, payload = payload)

        assertFailsWith<WhopWebhookVerificationException> {
            WhopWebhookVerifier(secret, clock).verifyAndParse(payload, headers)
        }
    }

    @Test
    fun `stale timestamp is rejected`() {
        val payload = """{"type":"payment.succeeded","data":{"id":"pay_123"}}"""
        val staleTimestamp = now - WhopWebhookVerifier.DEFAULT_TOLERANCE_SECONDS - 1
        val headers = signedHeaders(id = "evt_123", timestamp = staleTimestamp, payload = payload)

        assertFailsWith<WhopWebhookVerificationException> {
            WhopWebhookVerifier(secret, clock).verifyAndParse(payload, headers)
        }
    }

    @Test
    fun `future timestamp outside tolerance is rejected`() {
        val payload = """{"type":"payment.succeeded","data":{"id":"pay_123"}}"""
        val futureTimestamp = now + WhopWebhookVerifier.DEFAULT_TOLERANCE_SECONDS + 1
        val headers = signedHeaders(id = "evt_123", timestamp = futureTimestamp, payload = payload)

        assertFailsWith<WhopWebhookVerificationException> {
            WhopWebhookVerifier(secret, clock).verifyAndParse(payload, headers)
        }
    }

    @Test
    fun `timestamp at tolerance boundary is accepted`() {
        val payload = """{"type":"membership.activated","data":{"id":"mem_123"}}"""
        val boundaryTimestamp = now - WhopWebhookVerifier.DEFAULT_TOLERANCE_SECONDS
        val headers = signedHeaders(id = "evt_123", timestamp = boundaryTimestamp, payload = payload)

        val event = WhopWebhookVerifier(secret, clock).verifyAndParse(payload, headers)

        assertIs<WhopWebhookEvent.MembershipActivated>(event)
    }

    @Test
    fun `invalid timestamp header is rejected`() {
        val payload = """{"type":"payment.succeeded","data":{"id":"pay_123"}}"""
        val headers = signedHeaders(id = "evt_123", timestamp = now, payload = payload).toMutableMap()
        headers[WhopWebhookHeaders.TIMESTAMP] = "not-a-timestamp"

        assertFailsWith<WhopWebhookVerificationException> {
            WhopWebhookVerifier(secret, clock).verifyAndParse(payload, headers)
        }
    }

    @Test
    fun `missing signature header is rejected`() {
        val payload = """{"type":"payment.succeeded","data":{"id":"pay_123"}}"""

        val headers =
            mapOf(
                WhopWebhookHeaders.ID to "evt_123",
                WhopWebhookHeaders.TIMESTAMP to now.toString(),
            )

        assertFailsWith<WhopWebhookVerificationException> {
            WhopWebhookVerifier(secret, clock).verifyAndParse(payload, headers)
        }
    }

    @Test
    fun `multiple signatures accepts any valid v1 signature`() {
        val payload = """{"type":"entry.created","data":{"id":"entry_123"}}"""
        val validSignature = signature(id = "evt_123", timestamp = now, payload = payload)
        val invalidSignature = Base64.getEncoder().encodeToString("bad".toByteArray())
        val headers =
            mapOf(
                WhopWebhookHeaders.ID to "evt_123",
                WhopWebhookHeaders.TIMESTAMP to now.toString(),
                WhopWebhookHeaders.SIGNATURE to "v1,$invalidSignature v1,$validSignature",
            )

        val event = WhopWebhookVerifier(secret, clock).verifyAndParse(payload, headers)

        assertIs<WhopWebhookEvent.EntryCreated>(event)
    }

    @Test
    fun `case insensitive headers are accepted`() {
        val payload = """{"type":"refund.created","data":{"id":"refund_123"}}"""
        val headers =
            mapOf(
                "Webhook-Id" to "evt_123",
                "Webhook-Timestamp" to now.toString(),
                "Webhook-Signature" to "v1,${signature("evt_123", now, payload)}",
            )

        val event = WhopWebhookVerifier(secret, clock).verifyAndParse(payload, headers)

        assertIs<WhopWebhookEvent.RefundCreated>(event)
    }

    @Test
    fun `signature without v1 version is rejected`() {
        val payload = """{"type":"payment.succeeded","data":{"id":"pay_123"}}"""
        val headers = signedHeaders(id = "evt_123", timestamp = now, payload = payload).toMutableMap()
        headers[WhopWebhookHeaders.SIGNATURE] = signature("evt_123", now, payload)

        assertFailsWith<WhopWebhookVerificationException> {
            WhopWebhookVerifier(secret, clock).verifyAndParse(payload, headers)
        }
    }

    @Test
    fun `unprefixed base64 secret is accepted`() {
        val payload = """{"type":"dispute.created","data":{"id":"dp_123"}}"""
        val headers = signedHeaders(id = "evt_123", timestamp = now, payload = payload)
        val unprefixedSecret = Base64.getEncoder().encodeToString(secretBytes)

        val event = WhopWebhookVerifier(unprefixedSecret, clock).verifyAndParse(payload, headers)

        assertIs<WhopWebhookEvent.DisputeCreated>(event)
    }

    private fun signedHeaders(
        id: String,
        timestamp: Long,
        payload: String,
    ): Map<String, String> =
        mapOf(
            WhopWebhookHeaders.ID to id,
            WhopWebhookHeaders.TIMESTAMP to timestamp.toString(),
            WhopWebhookHeaders.SIGNATURE to "v1,${signature(id, timestamp, payload)}",
        )

    private fun signature(
        id: String,
        timestamp: Long,
        payload: String,
    ): String {
        val signedPayload = "$id.$timestamp.$payload"
        val mac = Mac.getInstance("HmacSHA256")
        mac.init(SecretKeySpec(secretBytes, "HmacSHA256"))
        return Base64.getEncoder().encodeToString(mac.doFinal(signedPayload.toByteArray(Charsets.UTF_8)))
    }
}
