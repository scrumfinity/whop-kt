package dev.lepshee.whop

import dev.lepshee.whop.models.bounties.BountyStatus
import dev.lepshee.whop.models.bounties.BountyType
import dev.lepshee.whop.models.bounties.CreateBountyRequest
import dev.lepshee.whop.models.common.Direction
import dev.lepshee.whop.resources.ListBountiesRequest
import dev.lepshee.whop.transport.KtorWhopHttpTransport
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.HttpRequestData
import io.ktor.http.HttpHeaders
import io.ktor.http.content.TextContent
import io.ktor.http.headersOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class BountiesResourceTest {
    @Test
    fun `bounty endpoints send documented paths filters body and decode`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val page =
                whop.bounties.list(
                    ListBountiesRequest(
                        after = "cursor_0",
                        before = "cursor_before",
                        first = 10,
                        last = 20,
                        experienceId = "exp_123",
                        status = BountyStatus.Published,
                        direction = Direction.Asc,
                    ),
                )
            val created =
                whop.bounties.create(
                    CreateBountyRequest(
                        baseUnitAmount = 6.9,
                        currency = "usd",
                        description = "Review five onboarding videos.",
                        title = "Video review bounty",
                        acceptedSubmissionsLimit = 3,
                        allowedCountryCodes = listOf("US", "CA"),
                        experienceId = "exp_123",
                        originAccountId = "biz_123",
                        postMarkdownContent = "## Instructions",
                        postTitle = "Help review videos",
                    ),
                )
            val retrieved = whop.bounties.retrieve("bnty_123")

            assertEquals("bnty_123", page.data.single().id)
            assertEquals(BountyStatus.Published, page.data.single().status)
            assertEquals(BountyType.Workforce, page.data.single().bountyType)
            assertEquals("/api/v1/bounties", requests[0].url.encodedPath)
            assertEquals("cursor_0", requests[0].url.parameters["after"])
            assertEquals("cursor_before", requests[0].url.parameters["before"])
            assertEquals("10", requests[0].url.parameters["first"])
            assertEquals("20", requests[0].url.parameters["last"])
            assertEquals("exp_123", requests[0].url.parameters["experience_id"])
            assertEquals("published", requests[0].url.parameters["status"])
            assertEquals("asc", requests[0].url.parameters["direction"])
            assertEquals("POST", requests[1].method.value)
            assertEquals("/api/v1/bounties", requests[1].url.encodedPath)
            assertTrue(requests[1].bodyText().contains("base_unit_amount"))
            assertTrue(requests[1].bodyText().contains("allowed_country_codes"))
            assertTrue(requests[1].bodyText().contains("origin_account_id"))
            assertTrue(requests[1].bodyText().contains("post_markdown_content"))
            assertEquals("bnty_123", created.id)
            assertEquals(42, created.voteThreshold)
            assertEquals("/api/v1/bounties/bnty_123", requests[2].url.encodedPath)
            assertEquals("Video review bounty", retrieved.title)
        }

    @Test
    fun `bounties auto paging follows cursors`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            assertEquals(
                listOf("bnty_123", "bnty_456"),
                whop.bounties
                    .listAutoPaging()
                    .toList()
                    .map { it.id },
            )
            assertTrue(requests.any { it.url.parameters["after"] == "bnty_cursor" })
        }

    @Test
    fun `bounty validation rejects invalid inputs and path parameters`() =
        runTest {
            val whop = clientWithEngine(recordingEngine(mutableListOf()))

            assertFailsWith<IllegalArgumentException> { ListBountiesRequest(after = "") }
            assertFailsWith<IllegalArgumentException> { ListBountiesRequest(first = 0) }
            assertFailsWith<IllegalArgumentException> { ListBountiesRequest(experienceId = " ") }
            assertFailsWith<IllegalArgumentException> { CreateBountyRequest(0.0, "usd", "Description", "Title") }
            assertFailsWith<IllegalArgumentException> { CreateBountyRequest(Double.NaN, "usd", "Description", "Title") }
            assertFailsWith<IllegalArgumentException> { CreateBountyRequest(1.0, " ", "Description", "Title") }
            assertFailsWith<IllegalArgumentException> { CreateBountyRequest(1.0, "usd", " ", "Title") }
            assertFailsWith<IllegalArgumentException> { CreateBountyRequest(1.0, "usd", "Description", " ") }
            assertFailsWith<IllegalArgumentException> { CreateBountyRequest(1.0, "usd", "Description", "Title", 0) }
            assertFailsWith<IllegalArgumentException> {
                CreateBountyRequest(1.0, "usd", "Description", "Title", allowedCountryCodes = listOf("US", ""))
            }
            assertFailsWith<IllegalArgumentException> { whop.bounties.retrieve("bnty_123/other") }
            assertFailsWith<IllegalArgumentException> { whop.bounties.retrieve("bnty_123%2Fother") }
        }

    private fun recordingEngine(requests: MutableList<HttpRequestData>): MockEngine =
        MockEngine { request ->
            requests += request
            respond(
                defaultJsonResponse(request.url.encodedPath, request.method.value, request.url.parameters["after"]),
                headers = jsonHeaders,
            )
        }

    private fun defaultJsonResponse(
        path: String,
        method: String,
        after: String?,
    ): String =
        when {
            path == "/api/v1/bounties" && method == "GET" -> bountyPageJson(after)
            path == "/api/v1/bounties" -> bountyJson("bnty_123")
            path.startsWith("/api/v1/bounties/") -> bountyJson("bnty_123")
            else -> "{}"
        }

    private fun bountyPageJson(after: String?): String =
        if (after == null || after == "cursor_0") {
            pageJson(bountyJson("bnty_123"), true, "bnty_cursor")
        } else {
            pageJson(bountyJson("bnty_456"), false, null)
        }

    private fun pageJson(
        item: String,
        hasNext: Boolean,
        cursor: String?,
    ): String =
        """
        {"data":[$item],"page_info":{"has_next_page":$hasNext,"end_cursor":${cursor?.let { "\"$it\"" } ?: "null"}}}
        """.trimIndent()

    private fun bountyJson(id: String): String =
        """
        {
          "id":"$id",
          "title":"Video review bounty",
          "description":"Review five onboarding videos.",
          "status":"published",
          "total_available":20.7,
          "total_paid":6.9,
          "currency":"usd",
          "bounty_type":"workforce",
          "vote_threshold":42,
          "created_at":"2023-12-01T05:00:00.401Z",
          "updated_at":"2023-12-02T05:00:00.401Z"
        }
        """.trimIndent()

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
