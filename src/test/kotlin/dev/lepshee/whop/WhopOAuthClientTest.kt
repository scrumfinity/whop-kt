package dev.lepshee.whop

import dev.lepshee.whop.oauth.AuthorizationUrlRequest
import dev.lepshee.whop.oauth.ExchangeCodeRequest
import dev.lepshee.whop.oauth.OAuthTokenResponse
import dev.lepshee.whop.oauth.Pkce
import dev.lepshee.whop.oauth.RefreshTokenRequest
import dev.lepshee.whop.oauth.RevokeTokenRequest
import dev.lepshee.whop.transport.KtorWhopHttpTransport
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.HttpRequestData
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.TextContent
import io.ktor.http.headersOf
import kotlinx.coroutines.test.runTest
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class WhopOAuthClientTest {
    @Test
    fun `pkce challenge matches RFC S256 example`() {
        val verifier = "dBjftJeZ4CVP-mB92K27uhbUJU1p1r_wW1gFWFOEjXk"

        val challenge = Pkce.challengeFor(verifier)

        assertEquals("E9Melhoa2OwvFrEMTJguCHaoeK1t8URWbuGJSstw-cM", challenge)
    }

    @Test
    fun `pkce validates verifier length and characters`() {
        assertFailsWith<IllegalArgumentException> { Pkce.challengeFor("short") }
        assertFailsWith<IllegalArgumentException> { Pkce.challengeFor("a".repeat(129)) }
        assertFailsWith<IllegalArgumentException> { Pkce.challengeFor("a".repeat(42) + "!") }

        val generated = Pkce.generate(byteLength = 96)
        assertEquals(128, generated.codeVerifier.length)
        assertFailsWith<IllegalArgumentException> { Pkce.generate(byteLength = 97) }
    }

    @Test
    fun `authorization url includes encoded PKCE OIDC and company parameters`() {
        val whop = clientWithEngine(MockEngine { respond("{}") })

        val url =
            whop.oauth.authorizationUrl(
                AuthorizationUrlRequest(
                    clientId = "client_123",
                    redirectUri = "https://example.com/oauth/callback",
                    scope = "openid profile email",
                    state = "state 123",
                    nonce = "nonce_123",
                    codeChallenge = "challenge_123",
                    companyId = "biz_123",
                ),
            )

        assertTrue(url.startsWith("https://api.whop.com/oauth/authorize?"))
        assertTrue(url.contains("response_type=code"))
        assertTrue(url.contains("client_id=client_123"))
        assertTrue(url.contains("redirect_uri=https%3A%2F%2Fexample.com%2Foauth%2Fcallback"))
        assertTrue(url.contains("scope=openid%20profile%20email"))
        assertTrue(url.contains("state=state%20123"))
        assertTrue(url.contains("nonce=nonce_123"))
        assertTrue(url.contains("code_challenge=challenge_123"))
        assertTrue(url.contains("code_challenge_method=S256"))
        assertTrue(url.contains("company_id=biz_123"))
    }

    @Test
    fun `exchange code posts JSON to oauth token without API key auth`() =
        runTest {
            val capturedRequests = mutableListOf<HttpRequestData>()
            val whop =
                clientWithEngine(
                    MockEngine { request ->
                        capturedRequests += request
                        respond(
                            content =
                                "{\"access_token\":\"access_123\",\"refresh_token\":\"refresh_123\"," +
                                    "\"id_token\":\"id_123\",\"token_type\":\"Bearer\",\"expires_in\":3600}",
                            headers = headersOf(HttpHeaders.ContentType, "application/json"),
                        )
                    },
                )

            val response =
                whop.oauth.exchangeCode(
                    ExchangeCodeRequest(
                        code = "code_123",
                        redirectUri = "https://example.com/callback",
                        clientId = "client_123",
                        codeVerifier = "verifier_123",
                    ),
                )

            assertEquals("access_123", response.accessToken)
            assertEquals("refresh_123", response.refreshToken)
            assertEquals("/oauth/token", capturedRequests.single().url.encodedPath)
            assertEquals("POST", capturedRequests.single().method.value)
            assertTrue(capturedRequests.single().bodyContentType().startsWith("application/json"))
            assertNull(capturedRequests.single().headers[HttpHeaders.Authorization])
            val body = capturedRequests.single().bodyText()
            assertEquals(
                """{"code":"code_123","redirect_uri":"https://example.com/callback","client_id":"client_123","code_verifier":"verifier_123","grant_type":"authorization_code"}""",
                body,
            )
        }

    @Test
    fun `refresh preserves company context and exposes expiry helper`() =
        runTest {
            val capturedRequests = mutableListOf<HttpRequestData>()
            val whop =
                clientWithEngine(
                    MockEngine { request ->
                        capturedRequests += request
                        respond(
                            content =
                                "{\"access_token\":\"access_456\",\"refresh_token\":\"refresh_456\"," +
                                    "\"token_type\":\"Bearer\",\"expires_in\":3600}",
                            headers = headersOf(HttpHeaders.ContentType, "application/json"),
                        )
                    },
                )

            val response =
                whop.oauth.refresh(
                    RefreshTokenRequest(
                        refreshToken = "refresh_123",
                        clientId = "client_123",
                        companyId = "biz_123",
                    ),
                )

            assertEquals("access_456", response.accessToken)
            assertEquals("POST", capturedRequests.single().method.value)
            assertTrue(capturedRequests.single().bodyContentType().startsWith("application/json"))
            assertNull(capturedRequests.single().headers[HttpHeaders.Authorization])
            assertEquals(
                """{"refresh_token":"refresh_123","client_id":"client_123","company_id":"biz_123","grant_type":"refresh_token"}""",
                capturedRequests.single().bodyText(),
            )
            val clock = Clock.fixed(Instant.ofEpochSecond(response.obtainedAtEpochSeconds + 3_301), ZoneOffset.UTC)
            assertTrue(response.expiresWithin(clock = clock))
            assertFalse(response.expiresWithin(bufferSeconds = 0, clock = clock))
        }

    @Test
    fun `token response honors obtained at from Whop`() {
        val response =
            OAuthTokenResponse(
                accessToken = "access_123",
                tokenType = "Bearer",
                expiresIn = 3_600,
                obtainedAtEpochSeconds = 100,
            )

        val nearExpiry = Clock.fixed(Instant.ofEpochSecond(3_401), ZoneOffset.UTC)
        val notNearExpiry = Clock.fixed(Instant.ofEpochSecond(3_399), ZoneOffset.UTC)

        assertTrue(response.expiresWithin(clock = nearExpiry))
        assertFalse(response.expiresWithin(clock = notNearExpiry))
    }

    @Test
    fun `userinfo sends oauth access token to oauth userinfo endpoint`() =
        runTest {
            val capturedRequests = mutableListOf<HttpRequestData>()
            val whop =
                clientWithEngine(
                    MockEngine { request ->
                        capturedRequests += request
                        respond(
                            content = """{"sub":"user_123","email":"hello@example.com","email_verified":true}""",
                            headers = headersOf(HttpHeaders.ContentType, "application/json"),
                        )
                    },
                )

            val userInfo = whop.oauth.userInfo("oauth_access_123", WhopRequestOptions(includeAuth = false))

            assertEquals("user_123", userInfo.sub)
            assertEquals("hello@example.com", userInfo.email)
            assertEquals("/oauth/userinfo", capturedRequests.single().url.encodedPath)
            assertEquals("Bearer oauth_access_123", capturedRequests.single().headers[HttpHeaders.Authorization])
        }

    @Test
    fun `revoke posts token JSON without decoding response body`() =
        runTest {
            val capturedRequests = mutableListOf<HttpRequestData>()
            val whop =
                clientWithEngine(
                    MockEngine { request ->
                        capturedRequests += request
                        respond(content = "", status = HttpStatusCode.NoContent)
                    },
                )

            whop.oauth.revoke(RevokeTokenRequest(token = "refresh_123", clientId = "client_123"))

            assertEquals("/oauth/revoke", capturedRequests.single().url.encodedPath)
            assertEquals("POST", capturedRequests.single().method.value)
            assertTrue(capturedRequests.single().bodyContentType().startsWith("application/json"))
            assertNull(capturedRequests.single().headers[HttpHeaders.Authorization])
            assertEquals("""{"token":"refresh_123","client_id":"client_123"}""", capturedRequests.single().bodyText())
        }

    private fun clientWithEngine(engine: MockEngine): WhopClient {
        val config = WhopClientConfig(apiKey = "test_api_key")
        val httpClient = HttpClient(engine) { expectSuccess = false }
        return WhopClient(config, KtorWhopHttpTransport(config, httpClient))
    }

    private fun HttpRequestData.bodyText(): String = (body as TextContent).text

    private fun HttpRequestData.bodyContentType(): String = (body as TextContent).contentType.toString()
}
