package dev.lepshee.whop

import dev.lepshee.whop.models.authorizedusers.AuthorizedUserRole
import dev.lepshee.whop.models.authorizedusers.CreateAuthorizedUserRequest
import dev.lepshee.whop.models.common.Direction
import dev.lepshee.whop.models.entries.EntriesOrder
import dev.lepshee.whop.models.entries.EntryStatus
import dev.lepshee.whop.models.leads.CreateLeadRequest
import dev.lepshee.whop.models.leads.UpdateLeadRequest
import dev.lepshee.whop.resources.ListAuthorizedUsersRequest
import dev.lepshee.whop.resources.ListEntriesRequest
import dev.lepshee.whop.resources.ListLeadsRequest
import dev.lepshee.whop.transport.KtorWhopHttpTransport
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.HttpRequestData
import io.ktor.http.HttpHeaders
import io.ktor.http.content.TextContent
import io.ktor.http.headersOf
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class IdentityAndLeadResourceTest {
    @Test
    fun `authorized users endpoints use documented paths queries and bodies`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            whop.authorizedUsers.list(
                ListAuthorizedUsersRequest(
                    companyId = "biz_123",
                    userId = "user_123",
                    role = AuthorizedUserRole.SalesManager,
                    first = 10,
                ),
            )
            whop.authorizedUsers.create(
                CreateAuthorizedUserRequest(
                    companyId = "biz_123",
                    role = AuthorizedUserRole.Moderator,
                    userId = "user_456",
                    sendEmails = true,
                ),
            )
            whop.authorizedUsers.retrieve("ausr_123")
            whop.authorizedUsers.delete("ausr_123", companyId = "biz_123")

            assertEquals("/api/v1/authorized_users", requests[0].url.encodedPath)
            assertEquals("biz_123", requests[0].url.parameters["company_id"])
            assertEquals("user_123", requests[0].url.parameters["user_id"])
            assertEquals("sales_manager", requests[0].url.parameters["role"])
            assertEquals("10", requests[0].url.parameters["first"])
            assertEquals("POST", requests[1].method.value)
            assertTrue(requests[1].bodyText().contains("send_emails"))
            assertTrue(requests[1].bodyText().contains("moderator"))
            assertEquals("/api/v1/authorized_users/ausr_123", requests[2].url.encodedPath)
            assertEquals("DELETE", requests[3].method.value)
            assertEquals("biz_123", requests[3].url.parameters["company_id"])
        }

    @Test
    fun `leads endpoints use documented paths queries and bodies`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            whop.leads.list(ListLeadsRequest(companyId = "biz_123", productIds = listOf("prod_1", "prod_2")))
            whop.leads.create(CreateLeadRequest(companyId = "biz_123", productId = "prod_1", referrer = "https://example.com"))
            whop.leads.retrieve("lead_123")
            whop.leads.update("lead_123", UpdateLeadRequest(referrer = "https://example.com/updated"))

            assertEquals("/api/v1/leads", requests[0].url.encodedPath)
            assertEquals("biz_123", requests[0].url.parameters["company_id"])
            assertEquals(listOf("prod_1", "prod_2"), requests[0].url.parameters.getAll("product_ids"))
            assertEquals("POST", requests[1].method.value)
            assertTrue(requests[1].bodyText().contains("product_id"))
            assertEquals("/api/v1/leads/lead_123", requests[2].url.encodedPath)
            assertEquals("PATCH", requests[3].method.value)
            assertTrue(requests[3].bodyText().contains("updated"))
        }

    @Test
    fun `entries endpoints use documented paths filters and bodyless actions`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            whop.entries.list(
                ListEntriesRequest(
                    companyId = "biz_123",
                    direction = Direction.Asc,
                    order = EntriesOrder.CreatedAt,
                    statuses = listOf(EntryStatus.Pending, EntryStatus.Approved),
                ),
            )
            whop.entries.retrieve("entry_123")
            whop.entries.approve("entry_123")
            whop.entries.deny("entry_123")

            assertEquals("/api/v1/entries", requests[0].url.encodedPath)
            assertEquals("asc", requests[0].url.parameters["direction"])
            assertEquals("created_at", requests[0].url.parameters["order"])
            assertEquals(listOf("pending", "approved"), requests[0].url.parameters.getAll("statuses"))
            assertEquals("/api/v1/entries/entry_123", requests[1].url.encodedPath)
            assertEquals("/api/v1/entries/entry_123/approve", requests[2].url.encodedPath)
            assertEquals("POST", requests[2].method.value)
            assertFalse(requests[2].body is TextContent)
            assertEquals("/api/v1/entries/entry_123/deny", requests[3].url.encodedPath)
            assertFalse(requests[3].body is TextContent)
        }

    private fun recordingEngine(requests: MutableList<HttpRequestData>): MockEngine =
        MockEngine { request ->
            requests += request
            respond(defaultJsonResponse(request), headers = jsonHeaders)
        }

    private fun defaultJsonResponse(request: HttpRequestData): String =
        when {
            request.method.value == "DELETE" -> "true"
            request.url.encodedPath.endsWith("/approve") -> """{"job_id":"job_123"}"""
            request.url.encodedPath == "/api/v1/authorized_users" && request.method.value == "GET" ->
                """{"data":[${authorizedUserJson()}],"page_info":{"has_next_page":false}}"""
            request.url.encodedPath.contains("authorized_users") -> authorizedUserJson()
            request.url.encodedPath == "/api/v1/leads" && request.method.value == "GET" ->
                """{"data":[${leadJson()}],"page_info":{"has_next_page":false}}"""
            request.url.encodedPath.contains("leads") -> leadJson()
            request.url.encodedPath == "/api/v1/entries" ->
                """{"data":[${entryJson()}],"page_info":{"has_next_page":false}}"""
            request.url.encodedPath.contains("entries") -> entryJson()
            else -> "{}"
        }

    private fun authorizedUserJson(): String =
        """{"id":"ausr_123","role":"admin","user":{"id":"user_123","username":"dean","name":null,"email":null},"company":{"id":"biz_123","title":"Acme"}}"""

    private fun leadJson(): String =
        """{"id":"lead_123","created_at":"2023-12-01T05:00:00.401Z","updated_at":"2023-12-01T05:00:00.401Z","referrer":null,"metadata":null,"user":{"id":"user_123","username":"dean","name":null,"email":null},"product":null,"member":null}"""

    private fun entryJson(): String =
        """{"id":"entry_123","plan":null,"product":null,"status":"pending","user":{"id":"user_123","username":"dean","name":null,"email":null},"created_at":null,"custom_field_responses":null}"""

    private fun clientWithEngine(engine: MockEngine): WhopClient {
        val config = WhopClientConfig(apiKey = "test_api_key")
        val httpClient = HttpClient(engine) { expectSuccess = false }
        return WhopClient(config, KtorWhopHttpTransport(config, httpClient))
    }

    private fun HttpRequestData.bodyText(): String = (body as TextContent).text

    private companion object {
        val jsonHeaders = headersOf(HttpHeaders.ContentType, "application/json")
    }
}
