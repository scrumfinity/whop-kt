package dev.lepshee.whop

import dev.lepshee.whop.models.accesstokens.CreateAccessTokenRequest
import dev.lepshee.whop.models.appbuilds.AppBuildPlatform
import dev.lepshee.whop.models.appbuilds.AppBuildStatus
import dev.lepshee.whop.models.appbuilds.CreateAppBuildRequest
import dev.lepshee.whop.models.apps.AppOrder
import dev.lepshee.whop.models.apps.AppStatus
import dev.lepshee.whop.models.apps.AppType
import dev.lepshee.whop.models.apps.AppValidScope
import dev.lepshee.whop.models.apps.AppViewType
import dev.lepshee.whop.models.apps.CreateAppRequest
import dev.lepshee.whop.models.apps.UpdateAppRequest
import dev.lepshee.whop.models.common.Direction
import dev.lepshee.whop.resources.ListAppBuildsRequest
import dev.lepshee.whop.resources.ListAppsRequest
import dev.lepshee.whop.transport.KtorWhopHttpTransport
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.HttpRequestData
import io.ktor.http.HttpHeaders
import io.ktor.http.content.OutgoingContent
import io.ktor.http.content.TextContent
import io.ktor.http.headersOf
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import dev.lepshee.whop.models.appbuilds.FileInputWithId as AppBuildFileInputWithId
import dev.lepshee.whop.models.apps.FileInputWithId as AppFileInputWithId

class AppDeveloperResourceTest {
    @Test
    fun `app endpoints send documented paths filters and bodies`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val page =
                whop.apps.list(
                    ListAppsRequest(
                        companyId = "biz_123",
                        appType = AppType.B2BApp,
                        viewType = AppViewType.Dashboard,
                        order = AppOrder.CreatedAt,
                        direction = Direction.Desc,
                        query = "analytics",
                        verifiedAppsOnly = true,
                        first = 10,
                    ),
                )
            val created =
                whop.apps.create(
                    CreateAppRequest(
                        companyId = "biz_123",
                        name = "Kotlin App",
                        baseUrl = "https://example.com",
                        icon = AppFileInputWithId("file_123"),
                        redirectUris = listOf("https://example.com/oauth/callback"),
                    ),
                )
            whop.apps.retrieve("app_123")
            whop.apps.update(
                "app_123",
                UpdateAppRequest(
                    name = "Updated App",
                    requiredScopes = listOf(AppValidScope.ReadUser),
                    status = AppStatus.Live,
                ),
            )

            assertEquals("app_123", page.data.single().id)
            assertEquals("app_123", created.id)
            assertEquals("/api/v1/apps", requests[0].url.encodedPath)
            assertEquals("biz_123", requests[0].url.parameters["company_id"])
            assertEquals("b2b_app", requests[0].url.parameters["app_type"])
            assertEquals("dashboard", requests[0].url.parameters["view_type"])
            assertEquals("created_at", requests[0].url.parameters["order"])
            assertEquals("desc", requests[0].url.parameters["direction"])
            assertEquals("analytics", requests[0].url.parameters["query"])
            assertEquals("true", requests[0].url.parameters["verified_apps_only"])
            assertEquals("10", requests[0].url.parameters["first"])
            assertEquals("POST", requests[1].method.value)
            assertTrue(requests[1].bodyText().contains("company_id"))
            assertTrue(requests[1].bodyText().contains("redirect_uris"))
            assertEquals("/api/v1/apps/app_123", requests[2].url.encodedPath)
            assertEquals("PATCH", requests[3].method.value)
            assertTrue(requests[3].bodyText().contains("required_scopes"))
            assertTrue(requests[3].bodyText().contains("read_user"))
        }

    @Test
    fun `app build endpoints send documented paths filters bodies and promote action`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val page =
                whop.appBuilds.list(
                    ListAppBuildsRequest(
                        appId = "app_123",
                        platform = AppBuildPlatform.Web,
                        status = AppBuildStatus.Pending,
                        createdAfter = "2023-12-01T05:00:00.401Z",
                        first = 5,
                    ),
                )
            val created =
                whop.appBuilds.create(
                    CreateAppBuildRequest(
                        attachment = AppBuildFileInputWithId("file_123"),
                        checksum = "sha256:abc",
                        platform = AppBuildPlatform.Web,
                        appId = "app_123",
                        supportedAppViewTypes = listOf(AppViewType.Dashboard),
                    ),
                )
            whop.appBuilds.retrieve("apbu_123")
            val promoted = whop.appBuilds.promote("apbu_123")

            assertEquals("apbu_123", page.data.single().id)
            assertEquals("apbu_123", created.id)
            assertEquals("apbu_123", promoted.id)
            assertEquals("/api/v1/app_builds", requests[0].url.encodedPath)
            assertEquals("app_123", requests[0].url.parameters["app_id"])
            assertEquals("web", requests[0].url.parameters["platform"])
            assertEquals("pending", requests[0].url.parameters["status"])
            assertEquals("2023-12-01T05:00:00.401Z", requests[0].url.parameters["created_after"])
            assertEquals("5", requests[0].url.parameters["first"])
            assertEquals("POST", requests[1].method.value)
            assertTrue(requests[1].bodyText().contains("attachment"))
            assertTrue(requests[1].bodyText().contains("supported_app_view_types"))
            assertEquals("/api/v1/app_builds/apbu_123", requests[2].url.encodedPath)
            assertEquals("/api/v1/app_builds/apbu_123/promote", requests[3].url.encodedPath)
            assertEquals("POST", requests[3].method.value)
            assertNoTextBody(requests[3])
        }

    @Test
    fun `access token endpoint sends documented body and parses response`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val token =
                whop.accessTokens.create(
                    CreateAccessTokenRequest(
                        companyId = "biz_123",
                        expiresAt = "2026-06-01T00:00:00.000Z",
                        scopedActions = listOf("chat:read", "dms:read"),
                    ),
                )

            assertEquals("tok_123", token.token)
            assertEquals("2026-06-01T00:00:00.000Z", token.expiresAt)
            assertEquals("/api/v1/access_tokens", requests.single().url.encodedPath)
            assertEquals("POST", requests.single().method.value)
            assertTrue(requests.single().bodyText().contains("company_id"))
            assertTrue(requests.single().bodyText().contains("scoped_actions"))
        }

    @Test
    fun `app developer path parameters reject path injection characters`() =
        runTest {
            val whop = clientWithEngine(recordingEngine(mutableListOf()))

            assertFailsWith<IllegalArgumentException> { whop.apps.retrieve("app_123/other") }
            assertFailsWith<IllegalArgumentException> { whop.appBuilds.retrieve("apbu_123%2Fother") }
            assertFailsWith<IllegalArgumentException> { whop.appBuilds.promote("../apbu_123") }
        }

    @Test
    fun `app developer request validation rejects invalid inputs`() {
        assertFailsWith<IllegalArgumentException> { CreateAppRequest(companyId = " ", name = "App") }
        assertFailsWith<IllegalArgumentException> { UpdateAppRequest(name = "") }
        assertFailsWith<IllegalArgumentException> { ListAppBuildsRequest(appId = "app_123", first = 0) }
        assertFailsWith<IllegalArgumentException> {
            CreateAppBuildRequest(
                attachment = AppBuildFileInputWithId("file_123"),
                checksum = "",
                platform = AppBuildPlatform.Web,
            )
        }
        assertFailsWith<IllegalArgumentException> { CreateAccessTokenRequest(scopedActions = listOf("chat:read", "")) }
    }

    private fun recordingEngine(requests: MutableList<HttpRequestData>): MockEngine =
        MockEngine { request ->
            requests += request
            respond(defaultJsonResponse(request.url.encodedPath, request.method.value), headers = jsonHeaders)
        }

    private fun defaultJsonResponse(
        path: String,
        method: String,
    ): String =
        when {
            path == "/api/v1/apps" && method == "GET" -> pageJson(appJson())
            path.startsWith("/api/v1/apps/") -> appJson()
            path == "/api/v1/apps" -> appJson()
            path == "/api/v1/app_builds" && method == "GET" -> pageJson(appBuildJson())
            path.startsWith("/api/v1/app_builds/") -> appBuildJson()
            path == "/api/v1/app_builds" -> appBuildJson()
            path == "/api/v1/access_tokens" -> accessTokenJson()
            else -> "{}"
        }

    private fun pageJson(item: String): String =
        """
        {"data":[$item],"page_info":{"has_next_page":false}}
        """.trimIndent()

    private fun appJson(): String =
        """
        {
          "id":"app_123",
          "name":"Kotlin App",
          "description":"Test app",
          "status":"live",
          "base_url":"https://example.com",
          "domain_id":"dom_123",
          "verified":true,
          "app_type":"b2b_app",
          "origin":null,
          "experience_path":"/experiences/[experienceId]",
          "discover_path":null,
          "dashboard_path":"/dashboard/[companyId]",
          "skills_path":null,
          "openapi_path":null,
          "company":{"id":"biz_123","title":"Company"},
          "icon":{"url":"https://example.com/icon.png"},
          "creator":{"id":"user_123","name":null,"username":"dean"},
          "requested_permissions":[],
          "stats":null,
          "api_key":{"id":"key_123","token":"sk_test","created_at":"2023-12-01T05:00:00.401Z"},
          "redirect_uris":["https://example.com/oauth/callback"]
        }
        """.trimIndent()

    private fun appBuildJson(): String =
        """
        {
          "id":"apbu_123",
          "platform":"web",
          "file_url":"https://example.com/build.zip",
          "created_at":"2023-12-01T05:00:00.401Z",
          "status":"pending",
          "checksum":"sha256:abc",
          "supported_app_view_types":["dashboard"],
          "review_message":null,
          "is_production":false
        }
        """.trimIndent()

    private fun accessTokenJson(): String =
        """
        {"token":"tok_123","expires_at":"2026-06-01T00:00:00.000Z"}
        """.trimIndent()

    private fun clientWithEngine(engine: MockEngine): WhopClient {
        val config = WhopClientConfig(apiKey = "test_api_key")
        val httpClient = HttpClient(engine) { expectSuccess = false }
        return WhopClient(config, KtorWhopHttpTransport(config, httpClient))
    }

    private fun HttpRequestData.bodyText(): String = (body as TextContent).text

    private fun assertNoTextBody(request: HttpRequestData) {
        assertFalse(request.body is TextContent)
        assertTrue(request.body is OutgoingContent.NoContent)
    }

    private companion object {
        val jsonHeaders = headersOf(HttpHeaders.ContentType, "application/json")
    }
}
