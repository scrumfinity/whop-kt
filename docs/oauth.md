# OAuth

Whop OAuth uses OAuth 2.1, PKCE, and OIDC-style userinfo.

## Authorization URL

```kotlin
val pkce = Pkce.generate()
val url = whop.oauth.authorizationUrl(
    AuthorizationUrlRequest(
        clientId = "client_...",
        redirectUri = "https://example.com/callback",
        state = state,
        nonce = nonce,
        codeChallenge = pkce.codeChallenge,
    ),
)
```

Store `state`, `nonce`, and `codeVerifier`; verify `state` on callback.

## Code exchange

```kotlin
val tokens = whop.oauth.exchangeCode(
    ExchangeCodeRequest(
        code = code,
        redirectUri = "https://example.com/callback",
        clientId = "client_...",
        codeVerifier = pkce.codeVerifier,
    ),
)
```

## Refresh

```kotlin
if (tokens.expiresWithin()) {
    val refreshed = whop.oauth.refresh(
        RefreshTokenRequest(
            refreshToken = tokens.refreshToken!!,
            clientId = "client_...",
            companyId = companyId,
        ),
    )
}
```

Use the same `companyId` on refresh if authorization was company-scoped.

## Revoke

```kotlin
whop.oauth.revoke(RevokeTokenRequest(token = refreshToken, clientId = "client_..."))
```

Revoke refresh tokens on logout. Access tokens cannot be server-revoked.
