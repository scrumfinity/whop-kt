package dev.lepshee.whop.spring.boot.autoconfigure

import org.springframework.boot.context.properties.ConfigurationProperties
import java.time.Duration

/** Configuration properties for Spring Boot Whop client auto-configuration. */
@ConfigurationProperties("whop")
data class WhopProperties(
    /** Whop API key used by the auto-configured client. */
    val apiKey: String? = null,
    /** Whop REST API base URL. */
    val baseUrl: String = "https://api.whop.com/api/v1",
    /** Optional Whop app ID. */
    val appId: String? = null,
    /** Optional Standard Webhooks signing secret. */
    val webhookSecret: String? = null,
    /** Default request timeout. */
    val timeout: Duration = Duration.ofSeconds(30),
    /** Maximum retry attempts for safe retryable failures. */
    val maxRetries: Int = 2,
)
