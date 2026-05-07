package com.lepshee.whop

/**
 * Per-request overrides applied on top of [WhopClientConfig].
 *
 * Use this for OAuth/user-scoped calls, idempotent mutating operations, integration headers,
 * or endpoint-specific timeout overrides.
 */
data class WhopRequestOptions(
    /** Optional bearer token override for this request only. */
    val apiKey: String? = null,
    /** Optional idempotency key for safe retry/deduplication of mutating requests. */
    val idempotencyKey: String? = null,
    /** Optional timeout override in milliseconds for this request only. */
    val timeoutMillis: Long? = null,
    /** Additional HTTP headers for integration-specific needs. */
    val headers: Map<String, String> = emptyMap(),
    /** Whether the SDK should send an Authorization bearer header for this request. */
    val includeAuth: Boolean = true,
) {
    init {
        require(apiKey == null || apiKey.isNotBlank()) { "Whop request API key override must not be blank." }
        require(idempotencyKey == null || idempotencyKey.isNotBlank()) { "Whop idempotency key must not be blank." }
        require(timeoutMillis == null || timeoutMillis > 0) { "Whop request timeout must be positive." }
    }
}
