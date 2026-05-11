package dev.lepshee.whop.auth

import dev.lepshee.whop.core.JsonConfig
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive
import java.security.PublicKey
import java.security.Signature
import java.time.Clock
import java.util.Base64

/**
 * Provides public keys for verifying Whop iframe user tokens.
 *
 * Whop's public docs do not publish a stable JWKS discovery URL, issuer, or audience contract.
 * Callers own key retrieval, caching, cache expiry, and key rotation for this provider.
 */
fun interface WhopJwksProvider {
    /** Returns the public key for [keyId], or null when the key is unknown. */
    fun publicKeyFor(keyId: String?): PublicKey?
}

/** Verified user identity from an `x-whop-user-token` iframe request header. */
data class VerifiedWhopUser(
    /** User ID extracted from documented/known JWT subject-style claims. */
    val userId: String,
    /** JWT key ID from the token header, when present. */
    val keyId: String? = null,
    /** Expiration epoch seconds from the JWT `exp` claim. */
    val expiresAtEpochSeconds: Long,
    /** Full decoded JWT claims for callers that need additional fields. */
    val claims: JsonObject,
)

/** Thrown when an iframe user token is missing, malformed, expired, or fails signature verification. */
class IframeTokenVerificationException(
    message: String,
    cause: Throwable? = null,
) : RuntimeException(message, cause)

/**
 * Verifies Whop iframe user tokens from the `x-whop-user-token` header.
 *
 * Whop's public docs do not publish a JWKS endpoint or issuer/audience contract, so this verifier
 * accepts a caller-provided [WhopJwksProvider]. It verifies RS256 signatures and expiration, then
 * returns the user identity. A verified user token is not a product access decision; call
 * `whop.users.checkAccess(...)` before gating paid or admin content.
 */
class IframeTokenVerifier(
    private val jwksProvider: WhopJwksProvider,
    private val clock: Clock = Clock.systemUTC(),
) {
    /** Verifies the token header and throws when verification fails. */
    fun verify(headers: Map<String, String>): VerifiedWhopUser {
        val token =
            headers.firstHeaderValue(HEADER_NAME)
                ?: throw IframeTokenVerificationException("Missing $HEADER_NAME header.")
        return verifyToken(token)
    }

    /** Verifies the token header and returns null instead of throwing when verification fails. */
    fun verifyOrNull(headers: Map<String, String>): VerifiedWhopUser? = runCatching { verify(headers) }.getOrNull()

    /** Verifies a raw JWT token and returns the verified user. */
    fun verifyToken(token: String): VerifiedWhopUser {
        val parts = token.split('.')
        if (parts.size != 3) throw IframeTokenVerificationException("Iframe token must be a compact JWT.")

        val header = decodeJson<JwtHeader>(parts[0], "JWT header")
        if (header.alg != "RS256") throw IframeTokenVerificationException("Unsupported iframe token algorithm: ${header.alg}.")

        val claims = decodeJson<JsonObject>(parts[1], "JWT claims")
        val expiresAt =
            claims["exp"]?.jsonPrimitive?.contentOrNull?.toLongOrNull()
                ?: throw IframeTokenVerificationException("Iframe token is missing a valid exp claim.")
        if (clock.instant().epochSecond >= expiresAt) throw IframeTokenVerificationException("Iframe token has expired.")

        val key =
            jwksProvider.publicKeyFor(header.kid)
                ?: throw IframeTokenVerificationException("No public key found for iframe token key ID.")
        verifySignature(parts[0], parts[1], parts[2], key)

        val userId =
            claims.firstString("sub") ?: claims.firstString("user_id") ?: claims.firstString("id")
                ?: throw IframeTokenVerificationException("Iframe token does not include a user identifier claim.")
        return VerifiedWhopUser(userId = userId, keyId = header.kid, expiresAtEpochSeconds = expiresAt, claims = claims)
    }

    private fun verifySignature(
        encodedHeader: String,
        encodedClaims: String,
        encodedSignature: String,
        key: PublicKey,
    ) {
        val valid =
            runCatching {
                val verifier = Signature.getInstance("SHA256withRSA")
                verifier.initVerify(key)
                verifier.update("$encodedHeader.$encodedClaims".toByteArray(Charsets.US_ASCII))
                verifier.verify(base64UrlDecoder.decode(encodedSignature))
            }.getOrElse { throw IframeTokenVerificationException("Iframe token signature verification failed.", it) }
        if (!valid) throw IframeTokenVerificationException("Iframe token signature verification failed.")
    }

    private inline fun <reified T> decodeJson(
        encoded: String,
        label: String,
    ): T =
        runCatching {
            JsonConfig.whopJson.decodeFromString<T>(String(base64UrlDecoder.decode(encoded), Charsets.UTF_8))
        }.getOrElse { throw IframeTokenVerificationException("Iframe token has an invalid $label.", it) }

    private fun JsonObject.firstString(vararg names: String): String? =
        names.firstNotNullOfOrNull { name -> this[name]?.jsonPrimitive?.contentOrNull?.takeIf(String::isNotBlank) }

    private fun Map<String, String>.firstHeaderValue(name: String): String? =
        entries.firstOrNull { (key) -> key.equals(name, ignoreCase = true) }?.value

    companion object {
        /** Header Whop sends to same-origin iframe application requests. */
        const val HEADER_NAME = "x-whop-user-token"

        private val base64UrlDecoder = Base64.getUrlDecoder()
    }
}

@Serializable
private data class JwtHeader(
    val alg: String,
    val typ: String? = null,
    val kid: String? = null,
)
