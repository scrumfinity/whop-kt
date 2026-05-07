package com.lepshee.whop

import com.lepshee.whop.auth.IframeTokenVerificationException
import com.lepshee.whop.auth.IframeTokenVerifier
import com.lepshee.whop.auth.WhopJwksProvider
import kotlinx.serialization.json.Json
import java.security.KeyPairGenerator
import java.security.Signature
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset
import java.util.Base64
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

class IframeTokenVerifierTest {
    private val keyPair = KeyPairGenerator.getInstance("RSA").apply { initialize(2048) }.generateKeyPair()
    private val now = 1_700_000_000L
    private val clock = Clock.fixed(Instant.ofEpochSecond(now), ZoneOffset.UTC)
    private val verifier =
        IframeTokenVerifier(
            jwksProvider = WhopJwksProvider { keyId -> if (keyId == "kid_123") keyPair.public else null },
            clock = clock,
        )

    @Test
    fun `valid iframe token verifies user id expiry and key id`() {
        val token = signedToken(claims = mapOf("sub" to "user_123", "exp" to now + 60))

        val user = verifier.verify(mapOf("X-Whop-User-Token" to token))

        assertEquals("user_123", user.userId)
        assertEquals("kid_123", user.keyId)
        assertEquals(now + 60, user.expiresAtEpochSeconds)
    }

    @Test
    fun `verify or null returns null for missing token`() {
        assertNull(verifier.verifyOrNull(emptyMap()))
    }

    @Test
    fun `missing token throws verification exception`() {
        assertFailsWith<IframeTokenVerificationException> {
            verifier.verify(emptyMap())
        }
    }

    @Test
    fun `expired iframe token is rejected`() {
        val token = signedToken(claims = mapOf("sub" to "user_123", "exp" to now - 1))

        assertFailsWith<IframeTokenVerificationException> {
            verifier.verify(mapOf(IframeTokenVerifier.HEADER_NAME to token))
        }
    }

    @Test
    fun `invalid signature is rejected`() {
        val token = signedToken(claims = mapOf("sub" to "user_123", "exp" to now + 60))
        val parts = token.split('.')
        val tamperedClaims = base64Url("""{"sub":"user_456","exp":${now + 60}}""".toByteArray(Charsets.UTF_8))
        val tampered = "${parts[0]}.$tamperedClaims.${parts[2]}"

        assertFailsWith<IframeTokenVerificationException> {
            verifier.verify(mapOf(IframeTokenVerifier.HEADER_NAME to tampered))
        }
    }

    @Test
    fun `missing key is rejected`() {
        val token = signedToken(keyId = "missing", claims = mapOf("sub" to "user_123", "exp" to now + 60))

        assertFailsWith<IframeTokenVerificationException> {
            verifier.verify(mapOf(IframeTokenVerifier.HEADER_NAME to token))
        }
    }

    @Test
    fun `malformed token is rejected`() {
        assertFailsWith<IframeTokenVerificationException> {
            verifier.verify(mapOf(IframeTokenVerifier.HEADER_NAME to "not-a-jwt"))
        }
    }

    @Test
    fun `unsupported algorithm is rejected`() {
        val token = signedToken(algorithm = "HS256", claims = mapOf("sub" to "user_123", "exp" to now + 60))

        assertFailsWith<IframeTokenVerificationException> {
            verifier.verify(mapOf(IframeTokenVerifier.HEADER_NAME to token))
        }
    }

    @Test
    fun `missing exp is rejected`() {
        val token = signedToken(claims = mapOf("sub" to "user_123"))

        assertFailsWith<IframeTokenVerificationException> {
            verifier.verify(mapOf(IframeTokenVerifier.HEADER_NAME to token))
        }
    }

    @Test
    fun `missing user id is rejected`() {
        val token = signedToken(claims = mapOf("exp" to now + 60))

        assertFailsWith<IframeTokenVerificationException> {
            verifier.verify(mapOf(IframeTokenVerifier.HEADER_NAME to token))
        }
    }

    @Test
    fun `user id fallback claims are supported`() {
        val userIdToken = signedToken(claims = mapOf("user_id" to "user_from_user_id", "exp" to now + 60))
        val idToken = signedToken(claims = mapOf("id" to "user_from_id", "exp" to now + 60))

        assertEquals("user_from_user_id", verifier.verify(mapOf(IframeTokenVerifier.HEADER_NAME to userIdToken)).userId)
        assertEquals("user_from_id", verifier.verify(mapOf(IframeTokenVerifier.HEADER_NAME to idToken)).userId)
    }

    @Test
    fun `malformed signature is wrapped as verification exception`() {
        val token = signedToken(claims = mapOf("sub" to "user_123", "exp" to now + 60))
        val parts = token.split('.')
        val malformed = "${parts[0]}.${parts[1]}.not@base64"

        assertFailsWith<IframeTokenVerificationException> {
            verifier.verify(mapOf(IframeTokenVerifier.HEADER_NAME to malformed))
        }
    }

    private fun signedToken(
        keyId: String = "kid_123",
        algorithm: String = "RS256",
        claims: Map<String, Any>,
    ): String {
        val headerJson =
            Json.encodeToString(
                kotlinx.serialization.json.JsonObject
                    .serializer(),
                kotlinx.serialization.json.buildJsonObject {
                    put("alg", kotlinx.serialization.json.JsonPrimitive(algorithm))
                    put("typ", kotlinx.serialization.json.JsonPrimitive("JWT"))
                    put("kid", kotlinx.serialization.json.JsonPrimitive(keyId))
                },
            )
        val claimsJson =
            kotlinx.serialization.json
                .buildJsonObject {
                    claims.forEach { (name, value) ->
                        when (value) {
                            is Number -> put(name, kotlinx.serialization.json.JsonPrimitive(value))
                            is Boolean -> put(name, kotlinx.serialization.json.JsonPrimitive(value))
                            else -> put(name, kotlinx.serialization.json.JsonPrimitive(value.toString()))
                        }
                    }
                }.toString()
        val encodedHeader = base64Url(headerJson.toByteArray(Charsets.UTF_8))
        val encodedClaims = base64Url(claimsJson.toByteArray(Charsets.UTF_8))
        val signature = Signature.getInstance("SHA256withRSA")
        signature.initSign(keyPair.private)
        signature.update("$encodedHeader.$encodedClaims".toByteArray(Charsets.US_ASCII))
        return "$encodedHeader.$encodedClaims.${base64Url(signature.sign())}"
    }

    private fun base64Url(bytes: ByteArray): String = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes)
}
