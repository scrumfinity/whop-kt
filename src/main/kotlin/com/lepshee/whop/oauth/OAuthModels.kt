package com.lepshee.whop.oauth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.Clock
import java.time.Instant

/** Request for building a Whop OAuth authorization URL. */
data class AuthorizationUrlRequest(
    /** OAuth client ID. */
    val clientId: String,
    /** Redirect URI registered for the Whop app. */
    val redirectUri: String,
    /** Space-delimited scopes. Defaults to OIDC user profile scopes from Whop docs. */
    val scope: String = "openid profile email",
    /** Caller-generated CSRF token. Callers must verify it on callback. */
    val state: String,
    /** Caller-generated OIDC nonce. */
    val nonce: String,
    /** S256 PKCE code challenge. */
    val codeChallenge: String,
    /** Optional company ID for company-scoped delegated tokens. */
    val companyId: String? = null,
) {
    init {
        require(clientId.isNotBlank()) { "OAuth client ID must not be blank." }
        require(redirectUri.isNotBlank()) { "OAuth redirect URI must not be blank." }
        require(scope.isNotBlank()) { "OAuth scope must not be blank." }
        require(state.isNotBlank()) { "OAuth state must not be blank." }
        require(nonce.isNotBlank()) { "OAuth nonce must not be blank." }
        require(codeChallenge.isNotBlank()) { "PKCE code challenge must not be blank." }
        require(companyId == null || companyId.isNotBlank()) { "Company ID must not be blank." }
    }
}

/** OAuth callback values used to validate state before exchanging an authorization code. */
data class OAuthCallback(
    val code: String,
    val state: String,
) {
    init {
        require(code.isNotBlank()) { "OAuth callback code must not be blank." }
        require(state.isNotBlank()) { "OAuth callback state must not be blank." }
    }

    /** Verifies the callback state matches the value stored before redirecting the user. */
    fun requireState(expectedState: String): OAuthCallback {
        require(expectedState.isNotBlank()) { "Expected OAuth state must not be blank." }
        require(state == expectedState) { "OAuth callback state does not match the expected state." }
        return this
    }
}

/** Request body for exchanging an authorization code for tokens. */
@Serializable
data class ExchangeCodeRequest(
    val code: String,
    @SerialName("redirect_uri") val redirectUri: String,
    @SerialName("client_id") val clientId: String,
    @SerialName("code_verifier") val codeVerifier: String,
    @SerialName("grant_type") val grantType: String = "authorization_code",
) {
    init {
        require(code.isNotBlank()) { "OAuth code must not be blank." }
        require(redirectUri.isNotBlank()) { "OAuth redirect URI must not be blank." }
        require(clientId.isNotBlank()) { "OAuth client ID must not be blank." }
        require(codeVerifier.isNotBlank()) { "PKCE code verifier must not be blank." }
    }
}

/** Request body for refreshing OAuth tokens. */
@Serializable
data class RefreshTokenRequest(
    @SerialName("refresh_token") val refreshToken: String,
    @SerialName("client_id") val clientId: String,
    @SerialName("company_id") val companyId: String? = null,
    @SerialName("grant_type") val grantType: String = "refresh_token",
) {
    init {
        require(refreshToken.isNotBlank()) { "Refresh token must not be blank." }
        require(clientId.isNotBlank()) { "OAuth client ID must not be blank." }
        require(companyId == null || companyId.isNotBlank()) { "Company ID must not be blank." }
    }
}

/** Request body for revoking a refresh token on logout. */
@Serializable
data class RevokeTokenRequest(
    val token: String,
    @SerialName("client_id") val clientId: String,
) {
    init {
        require(token.isNotBlank()) { "Token to revoke must not be blank." }
        require(clientId.isNotBlank()) { "OAuth client ID must not be blank." }
    }
}

/** OAuth token response returned by Whop token endpoints. */
@Serializable
data class OAuthTokenResponse(
    /** Bearer access token used for API calls and userinfo. */
    @SerialName("access_token") val accessToken: String,
    /** Rotating refresh token, when returned by Whop. Store securely. */
    @SerialName("refresh_token") val refreshToken: String? = null,
    /** OIDC ID token returned when `openid` was requested. */
    @SerialName("id_token") val idToken: String? = null,
    /** Token type, normally `Bearer`. */
    @SerialName("token_type") val tokenType: String,
    /** Access token lifetime in seconds. */
    @SerialName("expires_in") val expiresIn: Long,
    /** Epoch seconds when this response was obtained; used for expiry checks. */
    @SerialName("obtained_at") val obtainedAtEpochSeconds: Long = Instant.now().epochSecond,
) {
    /** Returns true when the token expires within [bufferSeconds]. */
    fun expiresWithin(
        bufferSeconds: Long = REFRESH_SAFETY_BUFFER_SECONDS,
        clock: Clock = Clock.systemUTC(),
    ): Boolean {
        require(bufferSeconds >= 0) { "Expiry buffer must not be negative." }
        val expiresAt = obtainedAtEpochSeconds + expiresIn
        return clock.instant().epochSecond + bufferSeconds >= expiresAt
    }

    companion object {
        /** Whop recommends refreshing with a five-minute safety buffer. */
        const val REFRESH_SAFETY_BUFFER_SECONDS: Long = 300
    }
}

/** OIDC userinfo response returned by Whop. */
@Serializable
data class OAuthUserInfo(
    /** Stable OIDC subject/user identifier. */
    val sub: String,
    /** User display name, included when `profile` scope was granted. */
    val name: String? = null,
    /** Preferred username, included when `profile` scope was granted. */
    @SerialName("preferred_username") val preferredUsername: String? = null,
    /** Profile picture URL, included when `profile` scope was granted. */
    val picture: String? = null,
    /** Email address, included when `email` scope was granted. */
    val email: String? = null,
    /** Email verification state, included when `email` scope was granted. */
    @SerialName("email_verified") val emailVerified: Boolean? = null,
)

@Serializable
internal data class EmptyOAuthResponse(
    val ok: Boolean = true,
)
