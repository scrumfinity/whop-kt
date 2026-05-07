# whop-kt

Kotlin/JVM SDK for the Whop API. The library provides coroutine-friendly resource clients, typed request/response models, Standard Webhooks verification, OAuth + PKCE helpers, iframe user-token verification, and explicit access checks.

## Installation

The project is not published yet. For local development, depend on the module directly or publish to a local Maven repository after configuring coordinates.

```kotlin
dependencies {
    implementation("com.lepshee:whop-kt:1.0-SNAPSHOT")
}
```

## Quickstart

```kotlin
val whop = WhopClient(
    WhopClientConfig(apiKey = System.getenv("WHOP_API_KEY")),
)
```

Reuse one `WhopClient` for many requests. Close it when your application shuts down.

## Checkout example

```kotlin
val checkout = whop.checkoutConfigurations.create(
    CreateCheckoutConfigurationRequest(
        plan = CheckoutPlan(
            companyId = "biz_...",
            currency = "usd",
            planType = PlanType.OneTime,
            initialPrice = 20.0,
        ),
    ),
    options = WhopRequestOptions(idempotencyKey = "order_123_checkout"),
)

println(checkout.purchaseUrl)
```

Create checkout configurations server-side. Use `payment.succeeded` webhooks for fulfillment.

## Webhook verification example

```kotlin
val verifier = WhopWebhookVerifier(System.getenv("WHOP_WEBHOOK_SECRET"))
val event = verifier.verifyAndParse(rawBody, requestHeaders)

when (event) {
    is WhopWebhookEvent.PaymentSucceeded -> fulfill(event.data)
    is WhopWebhookEvent.MembershipActivated -> grantAccess(event.data)
    else -> logger.info("Unhandled Whop event: ${event.type}")
}
```

Deduplicate webhook deliveries using `event.id` (`webhook-id`).

## OAuth example

```kotlin
val pkce = Pkce.generate()
val authUrl = whop.oauth.authorizationUrl(
    AuthorizationUrlRequest(
        clientId = "client_...",
        redirectUri = "https://example.com/oauth/callback",
        state = state,
        nonce = nonce,
        codeChallenge = pkce.codeChallenge,
    ),
)

val tokens = whop.oauth.exchangeCode(
    ExchangeCodeRequest(
        code = callbackCode,
        redirectUri = "https://example.com/oauth/callback",
        clientId = "client_...",
        codeVerifier = pkce.codeVerifier,
    ),
)
```

Store `state`, `nonce`, and `codeVerifier` securely until callback handling. Verify `state` before exchanging the code.

```kotlin
val callback = OAuthCallback(code = callbackCode, state = callbackState)
callback.requireState(expectedState)
```

## Blocking usage

Coroutine-aware Kotlin applications should use `WhopClient` directly. Java or blocking Spring MVC code can use the thin blocking facade:

```kotlin
val whop = WhopBlockingClient(WhopClientConfig(apiKey = System.getenv("WHOP_API_KEY")))
val payment = whop.retrievePayment("pay_...")
```

## Spring Boot usage

Spring Boot 3 applications can use the optional starter module. It auto-configures `WhopClient` when `whop.api-key` is present and backs off when applications define their own `WhopClient` or `WhopHttpTransport` bean.

```yaml
whop:
  api-key: ${WHOP_API_KEY}
```

## Error handling example

```kotlin
try {
    whop.products.retrieve("prod_...")
} catch (error: WhopApiException) {
    println("Whop error ${error.statusCode}: ${error.error.message}")
    println("Request ID: ${error.requestId}")
}
```

Non-2xx API responses preserve status, request ID, parsed error payload, and raw response body.

## Pagination example

```kotlin
whop.products.listAutoPaging(
    ListProductsRequest(companyId = "biz_...", first = 25),
).collect { product ->
    println(product.id)
}
```

Auto-paging follows forward cursors using `page_info.end_cursor`.

## App auth example

```kotlin
val verifiedUser = iframeTokenVerifier.verify(requestHeaders)
val access = whop.users.checkAccess(verifiedUser.userId, "prod_...")

if (!access.hasAccess) {
    error("Access denied")
}
```

Whop's public docs do not publish JWKS discovery details, so `IframeTokenVerifier` requires a caller-provided public-key provider.

## Verification

```bash
./gradlew test
./gradlew build
```

Default tests do not require real Whop secrets.

Public API changes are checked with Kotlin ABI validation:

```bash
./gradlew checkKotlinAbi
./gradlew updateKotlinAbi # only for intentional public API changes
```

API documentation is generated with Dokka:

```bash
./gradlew dokkaGenerate
```

Opt-in integration tests can be run with real Whop credentials:

```bash
WHOP_INTEGRATION_TESTS=true \
WHOP_API_KEY=... \
WHOP_COMPANY_ID=biz_... \
./gradlew integrationTest
```
