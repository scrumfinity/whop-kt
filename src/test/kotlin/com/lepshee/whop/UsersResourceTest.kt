package com.lepshee.whop

import com.lepshee.whop.models.users.AccessLevel
import com.lepshee.whop.transport.KtorWhopHttpTransport
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.HttpRequestData
import io.ktor.http.HttpHeaders
import io.ktor.http.headersOf
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class UsersResourceTest {
    @Test
    fun `check access sends official path and parses access level`() =
        runTest {
            val capturedRequests = mutableListOf<HttpRequestData>()
            val config = WhopClientConfig(apiKey = "test_api_key")
            val whop =
                WhopClient(
                    config,
                    KtorWhopHttpTransport(
                        config,
                        HttpClient(
                            MockEngine { request ->
                                capturedRequests += request
                                respond(
                                    content = """{"has_access":true,"access_level":"customer"}""",
                                    headers = headersOf(HttpHeaders.ContentType, "application/json"),
                                )
                            },
                        ) { expectSuccess = false },
                    ),
                )

            val access = whop.users.checkAccess(userId = "user_123", resourceId = "prod_123")

            assertEquals(true, access.hasAccess)
            assertEquals(AccessLevel.Customer, access.accessLevel)
            assertEquals("/api/v1/users/user_123/access/prod_123", capturedRequests.single().url.encodedPath)
            assertEquals("Bearer test_api_key", capturedRequests.single().headers[HttpHeaders.Authorization])
        }

    @Test
    fun `check access parses all documented access levels`() =
        runTest {
            val responses =
                ArrayDeque(
                    listOf(
                        """{"has_access":true,"access_level":"admin"}""",
                        """{"has_access":false,"access_level":"no_access"}""",
                    ),
                )
            val config = WhopClientConfig(apiKey = "test_api_key")
            val whop =
                WhopClient(
                    config,
                    KtorWhopHttpTransport(
                        config,
                        HttpClient(
                            MockEngine {
                                respond(
                                    content = responses.removeFirst(),
                                    headers = headersOf(HttpHeaders.ContentType, "application/json"),
                                )
                            },
                        ) { expectSuccess = false },
                    ),
                )

            val admin = whop.users.checkAccess(userId = "user_123", resourceId = "biz_123")
            val noAccess = whop.users.checkAccess(userId = "user_123", resourceId = "exp_123")

            assertEquals(true, admin.hasAccess)
            assertEquals(AccessLevel.Admin, admin.accessLevel)
            assertEquals(false, noAccess.hasAccess)
            assertEquals(AccessLevel.NoAccess, noAccess.accessLevel)
        }
}
