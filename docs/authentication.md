# Authentication

`whop-kt` supports three authentication patterns:

1. **Company/App API key** through `WhopClientConfig(apiKey = ...)` for server-to-server REST calls.
2. **OAuth access token** through `WhopRequestOptions(apiKey = accessToken)` or `whop.oauth.userInfo(...)` for user-delegated calls.
3. **Iframe user token** through `IframeTokenVerifier` for Whop app iframe requests.

## API keys

```kotlin
val whop = WhopClient(WhopClientConfig(apiKey = System.getenv("WHOP_API_KEY")))
```

The SDK does not read environment variables on each request. Use `WhopClient.fromEnvironment()` only as a construction-time convenience.

## Per-request auth override

```kotlin
whop.payments.retrieve(
    "pay_...",
    options = WhopRequestOptions(apiKey = oauthAccessToken),
)
```

## OAuth callback state

Store the generated OAuth `state`, `nonce`, and PKCE verifier in your application session. Verify callback state before exchanging the authorization code:

```kotlin
val callback = OAuthCallback(code = callbackCode, state = callbackState)
callback.requireState(expectedState)
```

## Spring Boot configuration

The optional Spring Boot starter reads `whop.*` configuration and creates a `WhopClient` bean only when `whop.api-key` is configured:

```yaml
whop:
  api-key: ${WHOP_API_KEY}
  timeout: 30s
  max-retries: 2
```

## Iframe user token

Whop sends `x-whop-user-token` only to same-origin iframe app requests. Verify it before trusting the user identity, then call `users.checkAccess` for authorization.

```kotlin
val user = iframeTokenVerifier.verify(headers)
val access = whop.users.checkAccess(user.userId, "exp_...")
```

Verified identity is not the same as product or admin access.
