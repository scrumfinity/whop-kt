package dev.lepshee.whop.oauth

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import java.net.URLEncoder

/** OAuth 2.1 + PKCE helper for Whop user-delegated authentication. */
class WhopOAuthClient internal constructor(
    private val http: WhopHttpExecutor,
    private val oauthBaseUrl: String = DEFAULT_OAUTH_BASE_URL,
) {
    /** Builds the Whop authorization URL. Callers must store and later verify `state`. */
    fun authorizationUrl(request: AuthorizationUrlRequest): String {
        val queryParameters =
            buildList {
                add("response_type" to "code")
                add("client_id" to request.clientId)
                add("redirect_uri" to request.redirectUri)
                add("scope" to request.scope)
                add("state" to request.state)
                add("nonce" to request.nonce)
                add("code_challenge" to request.codeChallenge)
                add("code_challenge_method" to "S256")
                request.companyId?.let { add("company_id" to it) }
            }
        return "$oauthBaseUrl/authorize?${queryParameters.toQueryString()}"
    }

    /** Exchanges an authorization code for access, refresh, and optional ID tokens. */
    suspend fun exchangeCode(
        request: ExchangeCodeRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): OAuthTokenResponse =
        http.post(
            path = "token",
            body = request,
            options = options.copy(includeAuth = false),
            baseUrlOverride = oauthBaseUrl,
        )

    /** Refreshes an OAuth access token. Pass the same company ID used during authorization, if any. */
    suspend fun refresh(
        request: RefreshTokenRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): OAuthTokenResponse =
        http.post(
            path = "token",
            body = request,
            options = options.copy(includeAuth = false),
            baseUrlOverride = oauthBaseUrl,
        )

    /** Retrieves OIDC user information for an OAuth access token. */
    suspend fun userInfo(
        accessToken: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): OAuthUserInfo {
        require(accessToken.isNotBlank()) { "Access token must not be blank." }
        return http.get(
            path = "userinfo",
            options = options.copy(apiKey = accessToken, includeAuth = true),
            baseUrlOverride = oauthBaseUrl,
        )
    }

    /** Revokes a refresh token. Access tokens cannot be server-revoked. */
    suspend fun revoke(
        request: RevokeTokenRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ) {
        http.postNoContent("revoke", request, options.copy(includeAuth = false), baseUrlOverride = oauthBaseUrl)
    }

    private fun List<Pair<String, String>>.toQueryString(): String =
        joinToString("&") { (name, value) ->
            "${name.urlEncode()}=${value.urlEncode()}"
        }

    private fun String.urlEncode(): String = URLEncoder.encode(this, Charsets.UTF_8.name()).replace("+", "%20")

    companion object {
        /** Whop OAuth base URL. */
        const val DEFAULT_OAUTH_BASE_URL = "https://api.whop.com/oauth"
    }
}
