# Whop Kotlin Library Implementation Guidelines

This document defines the implementation direction for `whop-kt`, a Kotlin/JVM library for Whop payments, checkouts, products, memberships, webhooks, OAuth, and app authentication. It combines documented Whop behavior with implementation patterns from established payment SDKs such as Stripe Java, Square Java SDK, Adyen Java API Library, and PayPal Java Server SDK.

## 1. Current project baseline

The repository is currently a minimal single-module Kotlin/JVM Gradle project:

- Root project: `whop-kt`
- Kotlin plugin: `kotlin("jvm")` version `2.3.20`
- JVM toolchain: `22`
- Package currently scaffolded as `dev.lepshee`
- Only source file: `src/main/kotlin/Main.kt`
- No `README.md`, `LICENSE`, `CHANGELOG.md`, `docs/`, or `src/test/kotlin` existed before this guideline

Before implementing SDK code, convert the project from a runnable starter into a publishable library.

Recommended initial cleanup:

1. Remove the IntelliJ template `Main.kt`.
2. Create a library package such as `dev.lepshee.whop` or `io.github.<owner>.whop`.
3. Add `src/test/kotlin`.
4. Add `README.md`, `LICENSE`, `CHANGELOG.md`, and Maven publishing metadata.
5. Add Ktor client and kotlinx.serialization dependencies for the default transport.
6. Keep the core SDK independent from Spring Boot; add Spring support through optional modules.

## 2. Whop facts the library must respect

These points are based on Whop documentation and should be treated as product constraints.

### 2.1 API and authentication

- Whop REST API base URL is `https://api.whop.com/api/v1`.
- API calls use bearer authentication.
- Supported auth contexts include:
  - Company API key for server-to-server company operations.
  - App API key for apps installed on companies.
  - OAuth token for user-scoped delegated access.
  - Short-lived access tokens for embedded/web/mobile component use.
- OAuth uses OAuth 2.1, PKCE, and OIDC-style user info flows.
- OAuth endpoints include:
  - `https://api.whop.com/oauth/authorize`
  - `https://api.whop.com/oauth/token`
  - `https://api.whop.com/oauth/userinfo`
  - `https://api.whop.com/oauth/revoke`
- OAuth access tokens expire after roughly one hour; refresh tokens rotate.
- If a flow is company-scoped, preserve the company context across refresh.

### 2.2 Checkout and payments

- Checkout configurations should be created server-side.
- Checkout configuration creation is the right primitive for embedded checkout flows.
- `payment.succeeded` webhooks should be used for fulfillment.
- Off-session payment creation is asynchronous; do not model payment creation as immediate successful fulfillment.
- Any money-moving or fulfillment-triggering operation should support an idempotency key where Whop supports one or where the request layer can pass one.

### 2.3 Webhooks

- Whop follows the Standard Webhooks signing model.
- Webhook signatures must be verified before trusting or parsing event payloads.
- Webhook handlers should be idempotent because delivery is at least once.
- Event order is not guaranteed.
- Handlers should return a 2xx response quickly and offload slow fulfillment work.
- Important event categories include payments, memberships, entries, refunds, and disputes.

### 2.4 App authentication and access checks

- Whop app iframes receive a short-lived user token through `x-whop-user-token` on same-origin requests.
- The SDK should provide a verifier for iframe user tokens.
- Access checks should remain explicit; a helper can wrap Whop's `checkAccess` API but should not hide authorization decisions from the caller.
- `checkAccess` style responses should be modeled with clear access states such as `customer`, `admin`, and `no_access`.

### 2.5 Errors and pagination

- Whop API errors are documented as structured objects containing fields such as `type`, `message`, `code`, and `param`.
- List endpoints use cursor pagination patterns with inputs such as `after`, `before`, `first`, and `last`, and response page metadata.

## 3. Design principles

Follow these rules throughout the library:

1. **Kotlin-first API, JVM-compatible internals**  
   Expose idiomatic Kotlin types, `suspend` functions, sealed errors, nullable types where meaningful, and coroutine-friendly pagination.

2. **One configured client**  
   Mirror mature SDKs: one `WhopClient` owns auth, base URL, JSON, HTTP client, timeout, retry, logging, and resource services.

3. **Domain resource modules**  
   Group API operations by Whop resource: `payments`, `checkoutConfigurations`, `plans`, `products`, `memberships`, `webhooks`, `users`, `companies`, `transfers`, and `oauth`.

4. **Typed request and response models**  
   Prefer `@Serializable data class` models. Avoid maps for documented fields, but provide explicit escape hatches for metadata and future unknown fields.

5. **Safe payment semantics**  
   Do not imply fulfillment from request success. For asynchronous payment flows, model the result as accepted/pending until confirmed by webhook or retrieval.

6. **Verification helpers are first-class**  
   Provide dedicated helpers for webhook signature verification, OAuth PKCE, and iframe user-token verification.

7. **Testability by construction**  
   Inject the HTTP engine/client and clock. This makes retry behavior, OAuth expiry, webhook timestamps, and pagination testable.

8. **Spring Boot-compatible, not Spring Boot-coupled**  
   The core SDK must not expose Spring types. Spring Boot applications should get first-class support through optional autoconfiguration and starter modules.

## 4. Proposed package structure

```text
src/main/kotlin/dev/lepshee/whop/
  WhopClient.kt
  WhopClientConfig.kt
  WhopRequestOptions.kt
  WhopEnvironment.kt

  auth/
    WhopApiKey.kt
    OAuthClient.kt
    OAuthModels.kt
    Pkce.kt
    IframeTokenVerifier.kt

  core/
    HttpExecutor.kt
    JsonConfig.kt
    WhopHeaders.kt
    WhopError.kt
    WhopException.kt
    Pagination.kt
    RetryPolicy.kt
    IdempotencyKey.kt

  resources/
    PaymentsResource.kt
    CheckoutConfigurationsResource.kt
    PlansResource.kt
    ProductsResource.kt
    MembershipsResource.kt
    WebhooksResource.kt
    UsersResource.kt
    CompaniesResource.kt
    TransfersResource.kt

  models/
    payments/
    checkout/
    plans/
    products/
    memberships/
    webhooks/
    users/
    companies/
    transfers/

  webhooks/
    StandardWebhookVerifier.kt
    WhopWebhookEvent.kt
    WhopWebhookHeaders.kt
```

Recommended multi-module expansion once Spring support starts:

```text
whop-core/                         # Framework-neutral public SDK API and models
whop-http-ktor/                    # Default Ktor transport implementation
whop-http-spring-webclient/        # Spring WebClient transport adapter
whop-http-spring-restclient/       # Spring RestClient transport adapter for blocking apps
whop-spring-boot-autoconfigure/    # AutoConfiguration, properties, conditional beans
whop-spring-boot-starter/          # Convenience dependency for Boot users
```

Do not put Spring Boot dependencies in `whop-core`. Keep the Spring modules optional so Ktor, CLI, Android-server, Micronaut, Quarkus, and plain JVM users do not inherit Spring.

If the library later targets Kotlin Multiplatform, move common models and pure verification logic into `commonMain`, then keep Ktor/JVM engines in platform-specific source sets.

## 5. Gradle and dependency direction

Recommended baseline dependencies:

```kotlin
dependencies {
    api("io.ktor:ktor-client-core:<version>")
    implementation("io.ktor:ktor-client-cio:<version>")
    implementation("io.ktor:ktor-client-content-negotiation:<version>")
    implementation("io.ktor:ktor-serialization-kotlinx-json:<version>")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:<version>")

    testImplementation(kotlin("test"))
    testImplementation("io.ktor:ktor-client-mock:<version>")
}
```

Recommended Gradle plugins:

```kotlin
plugins {
    kotlin("jvm") version "2.3.20"
    kotlin("plugin.serialization") version "2.3.20"
    `java-library`
    `maven-publish`
    signing
}
```

Use `java-library` so consumers get proper `api` versus `implementation` dependency boundaries. Use `maven-publish` and `signing` early so the public API is shaped with publishing in mind.

Spring Boot compatibility baseline:

- Target Spring Boot 3.x compatibility.
- Keep Java compatibility at **Java 17 or newer** for Spring Boot modules, because Spring Boot 3 requires Java 17+.
- Use `jakarta.*` APIs in Spring modules; do not introduce `javax.*` dependencies.
- Do not apply the Spring Boot Gradle plugin to the core SDK module.
- In Spring modules, prefer dependency management through Spring Boot's BOM rather than hardcoding Spring dependency versions.
- Mark Spring-only dependencies as `compileOnly`, `optional`, or implementation details of the starter/autoconfigure modules. They must not leak from core.

## 5.1 Spring Boot compatibility requirements

Spring Boot support is a product requirement, but it should be implemented as integration modules rather than by making the SDK itself a Spring library.

### Core SDK rules

- `whop-core` must not import `org.springframework.*`, `jakarta.*`, `reactor.*`, or servlet classes.
- `WhopClient` should depend on a small transport abstraction, not directly on Ktor, WebClient, or RestClient.
- Public request/response models should remain plain Kotlin serialization-friendly data classes.
- Public APIs should be usable from Kotlin coroutine code and Java code.
- Provide a blocking facade if Spring MVC users should avoid calling `runBlocking` themselves.

Suggested transport abstraction:

```kotlin
interface WhopHttpTransport : Closeable {
    suspend fun execute(request: WhopHttpRequest): WhopHttpResponse
}
```

### Spring Boot autoconfigure module

Create `whop-spring-boot-autoconfigure` with:

```text
src/main/kotlin/dev/lepshee/whop/spring/boot/autoconfigure/
  WhopAutoConfiguration.kt
  WhopProperties.kt
  WhopWebClientAutoConfiguration.kt
  WhopRestClientAutoConfiguration.kt

src/main/resources/META-INF/spring/
  org.springframework.boot.autoconfigure.AutoConfiguration.imports
```

Autoconfiguration rules:

- Use `@AutoConfiguration`.
- Use `@EnableConfigurationProperties(WhopProperties::class)`.
- Use `@ConditionalOnClass` to activate WebClient or RestClient transport only when those classes are on the classpath.
- Use `@ConditionalOnMissingBean` so applications can override `WhopClient`, transport, retry policy, JSON codec, or verifier beans.
- Avoid component scanning. Register beans explicitly.
- Avoid `@ConfigurationPropertiesScan` requirements for consumers; the starter should work by being on the classpath.
- Do not fail startup when `whop.api-key` is missing unless the application actually requests an authenticated `WhopClient` bean. Prefer a clear validation error in the auto-configured client bean.

Example properties shape:

```kotlin
@ConfigurationProperties("whop")
data class WhopProperties(
    val apiKey: String? = null,
    val baseUrl: String = "https://api.whop.com/api/v1",
    val appId: String? = null,
    val webhookSecret: String? = null,
    val timeout: Duration = Duration.ofSeconds(30),
    val maxRetries: Int = 2,
    val transport: Transport = Transport.KTOR,
) {
    enum class Transport { KTOR, WEBCLIENT, RESTCLIENT }
}
```

Recommended Spring configuration keys:

```yaml
whop:
  api-key: ${WHOP_API_KEY:}
  webhook-secret: ${WHOP_WEBHOOK_SECRET:}
  base-url: https://api.whop.com/api/v1
  timeout: 30s
  max-retries: 2
  transport: webclient
```

### Spring Boot starter module

Create `whop-spring-boot-starter` as a thin convenience module that depends on:

- `whop-core`
- one default transport adapter, preferably `whop-http-spring-webclient` for reactive applications or `whop-http-spring-restclient` for blocking applications
- `whop-spring-boot-autoconfigure`

The starter should not contain implementation code. It should only assemble dependencies.

### WebClient, RestClient, and Ktor support

Support all three without forcing all three:

- **Ktor transport**: best default for non-Spring Kotlin users.
- **WebClient transport**: best for reactive Spring WebFlux applications.
- **RestClient transport**: best for blocking Spring MVC applications on Spring Framework 6.1+ / Spring Boot 3.2+.

In Spring modules, consume Spring-managed builders instead of constructing everything manually:

- `WebClient.Builder` for WebFlux users.
- `RestClient.Builder` for blocking Spring users.

This lets applications keep their existing observability, proxy, TLS, codec, and HTTP client customizations.

### Jackson and kotlinx.serialization coexistence

Spring Boot applications usually expect Jackson, while the Kotlin SDK may prefer kotlinx.serialization. Design for coexistence:

- Keep Whop model serialization internal to the SDK transport unless models intentionally expose serializers.
- Do not require applications to replace their Spring Boot `ObjectMapper`.
- Do not force Jackson into non-Spring users if kotlinx.serialization is enough.
- If a Spring adapter uses Jackson, isolate it in the Spring adapter module.
- Add tests proving a Boot app can use Whop while its controllers still use Jackson normally.

### Spring Boot tests

Add tests for Spring support once modules exist:

- `ApplicationContextRunner` tests for autoconfiguration conditions.
- `@SpringBootTest` tests for end-to-end bean wiring.
- `@JsonTest` or focused codec tests if Jackson integration is added.
- Tests proving user-defined `WhopClient`, transport, or properties override auto-configured defaults.
- Tests with missing API key to verify the failure message is actionable.

### Spring Boot user experience target

Spring users should be able to write:

```kotlin
@Service
class BillingService(
    private val whop: WhopClient,
) {
    suspend fun createCheckout(request: CreateCheckoutConfigurationRequest) =
        whop.checkoutConfigurations.create(request)
}
```

And configure it with only:

```yaml
whop:
  api-key: ${WHOP_API_KEY}
```

## 6. Client configuration

Create a single client configuration object:

```kotlin
data class WhopClientConfig(
    val apiKey: String,
    val baseUrl: String = "https://api.whop.com/api/v1",
    val appId: String? = null,
    val webhookSecret: String? = null,
    val timeoutMillis: Long = 30_000,
    val maxRetries: Int = 2,
    val userAgent: String = "whop-kt/<version>",
)
```

Guidelines:

- Do not read environment variables implicitly inside every request.
- It is acceptable to provide a convenience constructor like `WhopClient.fromEnvironment()`.
- Keep `baseUrl` configurable for tests and future sandbox/proxy use.
- Do not log API keys, OAuth tokens, webhook secrets, or user tokens.
- Prefer one reusable `HttpClient`; do not create a new client per request.

## 7. Request options

Add per-request options similar to Stripe, Adyen, and Whop's official TypeScript SDK:

```kotlin
data class WhopRequestOptions(
    val apiKey: String? = null,
    val idempotencyKey: String? = null,
    val timeoutMillis: Long? = null,
    val headers: Map<String, String> = emptyMap(),
)
```

Use cases:

- Override auth for OAuth-user-scoped calls.
- Pass idempotency keys for mutating payment operations.
- Add integration-specific headers without exposing the raw HTTP layer.
- Override timeout for known long-running endpoints.

## 8. HTTP and serialization layer

Recommended Ktor setup:

```kotlin
val json = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
    explicitNulls = false
}

val client = HttpClient(CIO) {
    expectSuccess = false
    install(ContentNegotiation) {
        json(json)
    }
    install(HttpRequestRetry) {
        retryOnServerErrors(maxRetries = config.maxRetries)
        exponentialDelay()
    }
    defaultRequest {
        url(config.baseUrl)
        header(HttpHeaders.Authorization, "Bearer ${config.apiKey}")
        header(HttpHeaders.UserAgent, config.userAgent)
    }
}
```

Rules:

- Use `expectSuccess = false` and parse Whop error responses yourself.
- Retry only safe failures: network failures, timeouts, `429`, and selected `5xx` responses.
- Do not retry non-idempotent mutating requests unless the caller provided an idempotency key.
- Preserve response headers and request IDs in exceptions.

## 9. Resource API shape

Use resource classes with `suspend` methods:

```kotlin
class PaymentsResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun create(
        request: CreatePaymentRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Payment

    suspend fun retrieve(
        paymentId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Payment

    suspend fun list(
        request: ListPaymentsRequest = ListPaymentsRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<Payment>

    fun listAutoPaging(
        request: ListPaymentsRequest = ListPaymentsRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<Payment>
}
```

Avoid overloading heavily in Kotlin. Prefer default parameters and request data classes.

## 10. Pagination

Represent cursor pages explicitly:

```kotlin
@Serializable
data class WhopPage<T>(
    val data: List<T>,
    val pageInfo: PageInfo,
)

@Serializable
data class PageInfo(
    val hasNextPage: Boolean = false,
    val hasPreviousPage: Boolean = false,
    val startCursor: String? = null,
    val endCursor: String? = null,
)
```

Provide:

- `list(...)` for a single page.
- `listAutoPaging(...): Flow<T>` for coroutine-friendly iteration.
- A test ensuring auto-pagination stops when `hasNextPage` is false.

## 11. Error model

Use a sealed exception hierarchy:

```kotlin
sealed class WhopException(
    message: String,
    cause: Throwable? = null,
) : RuntimeException(message, cause) {
    abstract val statusCode: Int?
    abstract val requestId: String?
}

data class WhopApiException(
    override val statusCode: Int,
    override val requestId: String?,
    val error: WhopError,
) : WhopException(error.message)

data class WhopConnectionException(
    override val requestId: String? = null,
    override val statusCode: Int? = null,
    override val cause: Throwable,
) : WhopException("Failed to connect to Whop", cause)
```

Map common statuses:

- `400` -> bad request / invalid params
- `401` -> authentication failure
- `403` -> permission or access failure
- `404` -> resource not found
- `422` -> validation failure
- `429` -> rate limited
- `5xx` -> Whop server error

Always include:

- HTTP status
- request ID header, if present
- parsed Whop error object
- raw response body for debugging if parsing fails

## 12. Webhook verification

Make webhook verification a dedicated, dependency-light API:

```kotlin
class WhopWebhookVerifier(
    private val secret: String,
    private val clock: Clock = Clock.System,
) {
    fun verifyAndParse(
        payload: String,
        headers: Map<String, String>,
    ): WhopWebhookEvent
}
```

Rules:

- Verify the Standard Webhooks signature before JSON parsing.
- Reject missing signature headers.
- Reject timestamps outside tolerance.
- Use constant-time signature comparison.
- Return typed event objects where event schemas are known.
- Preserve a generic event fallback for unknown event types.
- Expose the webhook ID/header so applications can deduplicate events.

Application guidance to document in README:

- Store webhook secrets in environment variables.
- Return 2xx quickly.
- Queue fulfillment work.
- Deduplicate by webhook ID.
- Treat event order as non-deterministic.

## 13. OAuth and PKCE

Provide OAuth helpers but do not hide storage or session policy from users.

Recommended API:

```kotlin
class WhopOAuthClient internal constructor(
    private val http: WhopHttpExecutor,
) {
    fun authorizationUrl(request: AuthorizationUrlRequest): String

    suspend fun exchangeCode(
        request: ExchangeCodeRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): OAuthTokenResponse

    suspend fun refresh(
        request: RefreshTokenRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): OAuthTokenResponse

    suspend fun revoke(
        request: RevokeTokenRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    )
}
```

Rules:

- Generate PKCE `code_verifier` and `code_challenge` helpers.
- Require callers to verify `state`; do not silently skip it.
- Include `obtainedAt` or helper methods for expiry checks.
- Refresh with a five-minute safety buffer.
- Document secure token storage, but do not prescribe application storage.

## 14. Iframe user-token verification

Provide a verifier for `x-whop-user-token`:

```kotlin
class IframeTokenVerifier(
    private val jwksProvider: WhopJwksProvider,
    private val clock: Clock = Clock.System,
) {
    suspend fun verify(headers: Map<String, String>): VerifiedWhopUser?
}
```

Rules:

- Read only the expected header.
- Verify signature and expiry.
- Cache JWKS keys with expiry.
- Provide throwing and nullable variants if useful:
  - `verify(...)`
  - `verifyOrNull(...)`
- Do not treat a verified user token as product access; still call access checks where needed.

## 15. Model design

Use Kotlin serialization:

```kotlin
@Serializable
data class CreateCheckoutConfigurationRequest(
    @SerialName("company_id") val companyId: String,
    val plan: CheckoutPlan,
    val metadata: Map<String, String> = emptyMap(),
)
```

Rules:

- Use `@SerialName` for snake_case API fields.
- Keep public Kotlin names camelCase.
- Use enums only when the value set is stable; otherwise use value classes or strings.
- Use `Map<String, String>` or `JsonObject` for metadata depending on Whop schema constraints.
- Set `ignoreUnknownKeys = true` to survive additive API changes.
- Avoid exposing Ktor types from public models.

## 16. Testing strategy

Add tests before implementing broad endpoint coverage.

Minimum test categories:

1. **HTTP request construction**
   - Auth header is set.
   - Base URL is respected.
   - Query parameters serialize correctly.
   - Idempotency key is sent only when provided.

2. **Response parsing**
   - Success responses parse into typed models.
   - Unknown JSON fields are ignored.
   - Missing required fields fail clearly.

3. **Error parsing**
   - Whop error payload maps to `WhopApiException`.
   - Status, request ID, and body are preserved.

4. **Pagination**
   - Single page works.
   - Auto-pagination follows cursors.
   - Auto-pagination stops correctly.

5. **Retries**
   - Retries happen for network failures and configured retryable statuses.
   - Mutating calls without idempotency are not retried unsafely.

6. **Webhooks**
   - Valid signatures pass.
   - Invalid signatures fail.
   - Missing headers fail.
   - Timestamp tolerance is enforced.
   - Duplicate webhook IDs can be surfaced to the caller.

7. **OAuth**
   - Authorization URL includes PKCE and state.
   - Callback exchange sends correct fields.
   - Refresh handles rotated refresh tokens.
   - Revoke sends the correct request.

Use Ktor `MockEngine` for unit tests. Add optional integration tests behind environment variables such as `WHOP_API_KEY`, but never require secrets for default CI.

## 17. Documentation deliverables

Before publishing a first release, add:

- `README.md`
  - Installation
  - Quickstart
  - Checkout example
  - Webhook verification example
  - OAuth example
  - Error handling example
  - Pagination example
- `docs/authentication.md`
- `docs/webhooks.md`
- `docs/oauth.md`
- `docs/pagination.md`
- `docs/testing.md`
- `CHANGELOG.md`
- `LICENSE`

## 18. Suggested implementation phases

### Phase 1: Foundation

- Convert project to library.
- Add Ktor and serialization dependencies.
- Create `WhopClient`, `WhopClientConfig`, `WhopRequestOptions`, and `WhopException`.
- Add MockEngine tests.

### Phase 2: Core REST resources

- Implement `checkoutConfigurations`.
- Implement `payments`.
- Implement `products` and `plans`.
- Implement cursor pagination.
- Add error parsing.

### Phase 3: Webhooks

- Implement Standard Webhooks verification.
- Add typed event parsing.
- Add examples for common events such as `payment.succeeded` and `membership.activated`.

### Phase 4: OAuth and app auth

- Implement PKCE helpers.
- Implement OAuth token exchange, refresh, revoke, and user info.
- Implement iframe user-token verification.
- Implement access-check helpers.

### Phase 5: Publishing and hardening

- Add Maven publishing.
- Add signing.
- Add CI.
- Add binary compatibility checks.
- Add API documentation generation.
- Add integration tests behind opt-in environment variables.

## 19. API ergonomics example

Target usage should feel like this:

```kotlin
val whop = WhopClient(
    WhopClientConfig(apiKey = System.getenv("WHOP_API_KEY")),
)

val checkout = whop.checkoutConfigurations.create(
    CreateCheckoutConfigurationRequest(
        companyId = "biz_...",
        plan = CheckoutPlan(
            planType = PlanType.ONE_TIME,
            initialPrice = Money(currency = "usd", amount = 20_00),
        ),
        metadata = mapOf("order_id" to "order_123"),
    ),
    options = WhopRequestOptions(idempotencyKey = "order_123_checkout"),
)
```

Webhook usage:

```kotlin
val event = whop.webhooks.verifyAndParse(
    payload = rawBody,
    headers = requestHeaders,
)

when (event) {
    is WhopWebhookEvent.PaymentSucceeded -> fulfill(event.payment)
    is WhopWebhookEvent.MembershipActivated -> grantAccess(event.membership)
    else -> logger.info("Unhandled Whop event: ${event.type}")
}
```

## 20. Release checklist

- [ ] No hardcoded API keys, OAuth tokens, or webhook secrets.
- [ ] Public API uses Kotlin-friendly names and typed request models.
- [ ] All request models have serialization tests.
- [ ] All error paths preserve status, request ID, and response body.
- [ ] Mutating operations support idempotency where applicable.
- [ ] Webhook verifier checks signatures before parsing.
- [ ] OAuth helpers require state and PKCE handling.
- [ ] Iframe token verifier checks signature and expiry.
- [ ] Pagination has single-page and auto-page tests.
- [ ] README includes checkout, webhook, OAuth, pagination, and error examples.
- [ ] CI runs tests without real Whop secrets.
- [ ] Publishing metadata is complete.

## 21. References used

Whop documentation:

- Whop API getting started: `https://docs.whop.com/developer/api/getting-started`
- Whop API reference: `https://docs.whop.com/api-reference/getting-started`
- Whop accept payments guide: `https://docs.whop.com/developer/guides/accept-payments`
- Whop webhooks guide: `https://docs.whop.com/developer/guides/webhooks`
- Whop OAuth guide: `https://docs.whop.com/developer/guides/oauth`
- Whop authentication guide: `https://docs.whop.com/developer/guides/authentication`
- Whop docs navigation: `https://docs.whop.com/llms.txt`

SDK implementation references:

- Stripe Java: `https://github.com/stripe/stripe-java`
- Square Java SDK: `https://github.com/square/square-java-sdk`
- Adyen Java API Library: `https://github.com/Adyen/adyen-java-api-library`
- PayPal Java Server SDK: `https://github.com/paypal/PayPal-Java-Server-SDK`
- Whop TypeScript SDK: `https://github.com/whopio/whopsdk-typescript`
- Ktor documentation snippets: `https://github.com/ktorio/ktor-documentation`
- kotlinx.serialization: `https://github.com/Kotlin/kotlinx.serialization`
