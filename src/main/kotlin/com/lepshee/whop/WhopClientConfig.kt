package com.lepshee.whop

/**
 * Immutable configuration for a [WhopClient].
 *
 * Keep API keys and webhook secrets in secure application configuration and pass them explicitly here.
 */
data class WhopClientConfig(
    /** Bearer token used for default API authentication. */
    val apiKey: String,
    /** Base REST API URL. Defaults to Whop production and is configurable for tests/proxies. */
    val baseUrl: String = WhopEnvironment.Production.baseUrl,
    /** Optional Whop app ID for app-specific integrations. */
    val appId: String? = null,
    /** Optional webhook signing secret retained in configuration for applications that wire their own verifier. */
    val webhookSecret: String? = null,
    /** Default request timeout in milliseconds. */
    val timeoutMillis: Long = 30_000,
    /** Default retry budget reserved for transports that implement safe retries. */
    val maxRetries: Int = 2,
    /** User agent sent with SDK requests. */
    val userAgent: String = "whop-kt/1.0-SNAPSHOT",
) {
    init {
        require(apiKey.isNotBlank()) { "Whop API key must not be blank." }
        require(baseUrl.isNotBlank()) { "Whop base URL must not be blank." }
        require(timeoutMillis > 0) { "Whop timeout must be positive." }
        require(maxRetries >= 0) { "Whop max retries must not be negative." }
        require(userAgent.isNotBlank()) { "Whop user agent must not be blank." }
    }
}
