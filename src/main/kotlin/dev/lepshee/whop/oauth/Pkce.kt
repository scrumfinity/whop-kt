package dev.lepshee.whop.oauth

import java.security.MessageDigest
import java.security.SecureRandom
import java.util.Base64

/** PKCE code verifier and S256 code challenge pair. */
data class PkcePair(
    /** High-entropy verifier that must be stored until the OAuth callback. */
    val codeVerifier: String,
    /** S256 challenge sent in the authorization URL. */
    val codeChallenge: String,
)

/** Utilities for generating OAuth 2.1 PKCE values. */
object Pkce {
    /** Minimum PKCE verifier length from RFC 7636. */
    const val MIN_VERIFIER_LENGTH = 43

    /** Maximum PKCE verifier length from RFC 7636. */
    const val MAX_VERIFIER_LENGTH = 128

    private val random = SecureRandom()
    private val base64UrlEncoder = Base64.getUrlEncoder().withoutPadding()
    private val verifierPattern = Regex("^[A-Za-z0-9._~-]+$")

    /** Generates a random verifier and matching S256 challenge. */
    fun generate(byteLength: Int = 32): PkcePair {
        require(byteLength >= 32) { "PKCE verifier entropy must be at least 32 bytes." }
        require(byteLength <= 96) { "PKCE verifier entropy must not exceed 96 bytes." }
        val bytes = ByteArray(byteLength)
        random.nextBytes(bytes)
        val verifier = base64UrlEncoder.encodeToString(bytes)
        return PkcePair(codeVerifier = verifier, codeChallenge = challengeFor(verifier))
    }

    /** Computes the S256 challenge for an existing verifier. */
    fun challengeFor(codeVerifier: String): String {
        validateVerifier(codeVerifier)
        val digest = MessageDigest.getInstance("SHA-256").digest(codeVerifier.toByteArray(Charsets.US_ASCII))
        return base64UrlEncoder.encodeToString(digest)
    }

    /** Validates a PKCE verifier against RFC 7636 length and character rules. */
    fun validateVerifier(codeVerifier: String) {
        require(codeVerifier.length in MIN_VERIFIER_LENGTH..MAX_VERIFIER_LENGTH) {
            "PKCE code verifier must be between $MIN_VERIFIER_LENGTH and $MAX_VERIFIER_LENGTH characters."
        }
        require(verifierPattern.matches(codeVerifier)) {
            "PKCE code verifier contains invalid characters."
        }
    }
}
