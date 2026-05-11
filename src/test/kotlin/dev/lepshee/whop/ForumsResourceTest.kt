package dev.lepshee.whop

import dev.lepshee.whop.models.forums.CreateForumPostRequest
import dev.lepshee.whop.models.forums.ForumEmailNotificationPreferences
import dev.lepshee.whop.models.forums.ForumFileInput
import dev.lepshee.whop.models.forums.ForumPostAttachmentInput
import dev.lepshee.whop.models.forums.ForumPostPollInput
import dev.lepshee.whop.models.forums.ForumPostPollOptionInput
import dev.lepshee.whop.models.forums.ForumPostVisibilityTypes
import dev.lepshee.whop.models.forums.ForumWhoCanCommentTypes
import dev.lepshee.whop.models.forums.ForumWhoCanPostTypes
import dev.lepshee.whop.models.forums.UpdateForumPostRequest
import dev.lepshee.whop.models.forums.UpdateForumRequest
import dev.lepshee.whop.models.reactions.CreateReactionRequest
import dev.lepshee.whop.models.reactions.DeleteReactionRequest
import dev.lepshee.whop.resources.ListForumPostsRequest
import dev.lepshee.whop.resources.ListForumsRequest
import dev.lepshee.whop.resources.ListReactionsRequest
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
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class ForumsResourceTest {
    @Test
    fun `forum endpoints send documented paths query params body and decode`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val page =
                whop.forums.list(
                    ListForumsRequest(
                        companyId = "biz_123",
                        after = "cursor_0",
                        before = "cursor_before",
                        first = 10,
                        last = 20,
                        productId = "prod_123",
                    ),
                )
            val retrieved = whop.forums.retrieve("exp_123")
            val updated =
                whop.forums.update(
                    "forum_123",
                    UpdateForumRequest(
                        bannedWords = listOf("spam"),
                        bannerImage = buildJsonObject { put("id", "file_123") },
                        emailNotificationPreference = ForumEmailNotificationPreferences.OnlyWeeklySummary,
                        whoCanComment = ForumWhoCanCommentTypes.Admins,
                        whoCanPost = ForumWhoCanPostTypes.Admins,
                    ),
                )

            assertEquals("forum_123", page.data.single().id)
            assertEquals(ForumWhoCanPostTypes.Everyone, retrieved.whoCanPost)
            assertEquals("Trading Signals", updated.experience.name)
            assertEquals("/api/v1/forums", requests[0].url.encodedPath)
            assertEquals("biz_123", requests[0].url.parameters["company_id"])
            assertEquals("cursor_0", requests[0].url.parameters["after"])
            assertEquals("cursor_before", requests[0].url.parameters["before"])
            assertEquals("10", requests[0].url.parameters["first"])
            assertEquals("20", requests[0].url.parameters["last"])
            assertEquals("prod_123", requests[0].url.parameters["product_id"])
            assertEquals("GET", requests[1].method.value)
            assertEquals("/api/v1/forums/exp_123", requests[1].url.encodedPath)
            assertEquals("PATCH", requests[2].method.value)
            assertEquals("/api/v1/forums/forum_123", requests[2].url.encodedPath)
            assertTrue(requests[2].bodyText().contains("banned_words"))
            assertTrue(requests[2].bodyText().contains("banner_image"))
            assertTrue(requests[2].bodyText().contains("file_123"))
            assertTrue(requests[2].bodyText().contains("only_weekly_summary"))
            assertTrue(requests[2].bodyText().contains("admins"))
        }

    @Test
    fun `forum update can explicitly clear banner image`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            whop.forums.update("forum_123", UpdateForumRequest(bannerImage = JsonNull))

            assertTrue(requests.single().bodyText().contains("\"banner_image\":null"), requests.single().bodyText())
        }

    @Test
    fun `forum post endpoints send documented paths query params body and decode`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val page =
                whop.forumPosts.list(
                    ListForumPostsRequest(
                        experienceId = "exp_123",
                        after = "cursor_0",
                        before = "cursor_before",
                        first = 5,
                        last = 6,
                        includeBountyAnchors = true,
                        parentId = "post_parent",
                        pinned = true,
                    ),
                )
            val created =
                whop.forumPosts.create(
                    CreateForumPostRequest(
                        experienceId = "public",
                        companyId = "biz_123",
                        content = "Markdown body",
                        richContent = "{\"type\":\"doc\"}",
                        title = "Paywalled Update",
                        attachments = listOf(ForumPostAttachmentInput(id = "file_123")),
                        poll = ForumPostPollInput(listOf(ForumPostPollOptionInput(id = "1", text = "Yes"))),
                        parentId = "post_parent",
                        pinned = true,
                        paywallAmount = 6.9,
                        paywallCurrency = "kzt",
                        isMention = true,
                        visibility = ForumPostVisibilityTypes.GloballyVisible,
                    ),
                )
            whop.forumPosts.retrieve("post_123")
            whop.forumPosts.update(
                "post_123",
                UpdateForumPostRequest(
                    attachments = listOf(ForumPostAttachmentInput(id = "file_456")),
                    content = "Updated body",
                    isPinned = false,
                    title = "Updated title",
                    visibility = ForumPostVisibilityTypes.MembersOnly,
                ),
            )

            assertEquals("post_123", page.data.single().id)
            assertEquals("document.pdf", created.attachments.single().filename)
            assertEquals(42, created.viewCount)
            assertEquals("/api/v1/forum_posts", requests[0].url.encodedPath)
            assertEquals("exp_123", requests[0].url.parameters["experience_id"])
            assertEquals("true", requests[0].url.parameters["include_bounty_anchors"])
            assertEquals("post_parent", requests[0].url.parameters["parent_id"])
            assertEquals("true", requests[0].url.parameters["pinned"])
            assertEquals("POST", requests[1].method.value)
            assertTrue(requests[1].bodyText().contains("experience_id"))
            assertTrue(requests[1].bodyText().contains("company_id"))
            assertTrue(requests[1].bodyText().contains("rich_content"))
            assertTrue(requests[1].bodyText().contains("paywall_currency"))
            assertTrue(requests[1].bodyText().contains("kzt"))
            assertTrue(requests[1].bodyText().contains("globally_visible"))
            assertEquals("/api/v1/forum_posts/post_123", requests[2].url.encodedPath)
            assertEquals("PATCH", requests[3].method.value)
            assertTrue(requests[3].bodyText().contains("is_pinned"))
            assertTrue(requests[3].bodyText().contains("members_only"))
        }

    @Test
    fun `reaction endpoints send documented paths query params body delete query and decode`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val page = whop.reactions.list(ListReactionsRequest("post_123", after = "cursor_0", first = 10, last = 20))
            val created = whop.reactions.create(CreateReactionRequest("post_123", emoji = ":heart:", pollOptionId = "1"))
            whop.reactions.retrieve("reac_123")
            val deleted = whop.reactions.delete("post_123", DeleteReactionRequest(emoji = ":heart:"))

            assertEquals("reac_123", page.data.single().id)
            assertEquals("johndoe42", created.user.username)
            assertTrue(deleted)
            assertEquals("/api/v1/reactions", requests[0].url.encodedPath)
            assertEquals("post_123", requests[0].url.parameters["resource_id"])
            assertEquals("cursor_0", requests[0].url.parameters["after"])
            assertEquals("10", requests[0].url.parameters["first"])
            assertEquals("20", requests[0].url.parameters["last"])
            assertEquals("POST", requests[1].method.value)
            assertTrue(requests[1].bodyText().contains("resource_id"))
            assertTrue(requests[1].bodyText().contains("poll_option_id"))
            assertEquals("/api/v1/reactions/reac_123", requests[2].url.encodedPath)
            assertEquals("DELETE", requests[3].method.value)
            assertEquals("/api/v1/reactions/post_123", requests[3].url.encodedPath)
            assertEquals(":heart:", requests[3].url.parameters["emoji"])
        }

    @Test
    fun `forum resource auto paging follows cursors`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            assertEquals(
                listOf("forum_123", "forum_456"),
                whop.forums
                    .listAutoPaging(ListForumsRequest("biz_123"))
                    .toList()
                    .map { it.id },
            )
            assertEquals(
                listOf("post_123", "post_456"),
                whop.forumPosts
                    .listAutoPaging(ListForumPostsRequest("exp_123"))
                    .toList()
                    .map { it.id },
            )
            assertEquals(
                listOf("reac_123", "reac_456"),
                whop.reactions
                    .listAutoPaging(ListReactionsRequest("post_123"))
                    .toList()
                    .map { it.id },
            )
            assertTrue(requests.any { it.url.parameters["after"] == "forum_cursor" })
            assertTrue(requests.any { it.url.parameters["after"] == "post_cursor" })
            assertTrue(requests.any { it.url.parameters["after"] == "reaction_cursor" })
        }

    @Test
    fun `forum path parameters reject path injection characters`() =
        runTest {
            val whop = clientWithEngine(recordingEngine(mutableListOf()))

            assertFailsWith<IllegalArgumentException> { whop.forums.retrieve("forum_123/other") }
            assertFailsWith<IllegalArgumentException> { whop.forums.update("forum_123%2Fother") }
            assertFailsWith<IllegalArgumentException> { whop.forumPosts.retrieve("../post_123") }
            assertFailsWith<IllegalArgumentException> { whop.forumPosts.update("post_123?admin=true") }
            assertFailsWith<IllegalArgumentException> { whop.reactions.retrieve("reac_123#fragment") }
            assertFailsWith<IllegalArgumentException> { whop.reactions.delete(".") }
        }

    @Test
    fun `forum request validation rejects invalid inputs`() {
        assertFailsWith<IllegalArgumentException> { ListForumsRequest(companyId = " ") }
        assertFailsWith<IllegalArgumentException> { ListForumsRequest(companyId = "biz_123", first = 0) }
        assertFailsWith<IllegalArgumentException> { UpdateForumRequest(bannedWords = listOf("spam", "")) }
        assertFailsWith<IllegalArgumentException> { ForumFileInput(id = " ") }
        assertFailsWith<IllegalArgumentException> { ListForumPostsRequest(experienceId = "") }
        assertFailsWith<IllegalArgumentException> { ListForumPostsRequest(experienceId = "exp_123", parentId = " ") }
        assertFailsWith<IllegalArgumentException> { CreateForumPostRequest(experienceId = " ") }
        assertFailsWith<IllegalArgumentException> { CreateForumPostRequest(experienceId = "public") }
        assertFailsWith<IllegalArgumentException> { CreateForumPostRequest(experienceId = "exp_123", richContent = "") }
        assertFailsWith<IllegalArgumentException> { CreateForumPostRequest(experienceId = "exp_123", paywallCurrency = " ") }
        assertFailsWith<IllegalArgumentException> { ForumPostAttachmentInput(id = "") }
        assertFailsWith<IllegalArgumentException> { ForumPostPollInput(emptyList()) }
        assertFailsWith<IllegalArgumentException> { ForumPostPollOptionInput(id = "1", text = " ") }
        assertFailsWith<IllegalArgumentException> { UpdateForumPostRequest(title = "") }
        assertFailsWith<IllegalArgumentException> { ListReactionsRequest(resourceId = " ") }
        assertFailsWith<IllegalArgumentException> { ListReactionsRequest(resourceId = "post_123", last = 0) }
        assertFailsWith<IllegalArgumentException> { CreateReactionRequest(resourceId = "post_123", emoji = "") }
        assertFailsWith<IllegalArgumentException> { CreateReactionRequest(resourceId = "post_123", pollOptionId = " ") }
        assertFailsWith<IllegalArgumentException> { DeleteReactionRequest(emoji = "") }
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
            method == "DELETE" -> "true"
            path == "/api/v1/forums" -> forumPageJson(after)
            path.startsWith("/api/v1/forums/") -> forumJson("forum_123")
            path == "/api/v1/forum_posts" && method == "GET" -> forumPostPageJson(after)
            path == "/api/v1/forum_posts" || path.startsWith("/api/v1/forum_posts/") -> forumPostJson("post_123")
            path == "/api/v1/reactions" && method == "GET" -> reactionPageJson(after)
            path == "/api/v1/reactions" || path.startsWith("/api/v1/reactions/") -> reactionJson("reac_123")
            else -> "{}"
        }

    private fun forumPageJson(after: String?): String =
        if (after == null ||
            after == "cursor_0"
        ) {
            pageJson(forumJson("forum_123"), true, "forum_cursor")
        } else {
            pageJson(forumJson("forum_456"), false, null)
        }

    private fun forumPostPageJson(after: String?): String =
        if (after == null ||
            after == "cursor_0"
        ) {
            pageJson(forumPostJson("post_123"), true, "post_cursor")
        } else {
            pageJson(forumPostJson("post_456"), false, null)
        }

    private fun reactionPageJson(after: String?): String =
        if (after == null ||
            after == "cursor_0"
        ) {
            pageJson(reactionJson("reac_123"), true, "reaction_cursor")
        } else {
            pageJson(reactionJson("reac_456"), false, null)
        }

    private fun pageJson(
        item: String,
        hasNext: Boolean,
        cursor: String?,
    ): String =
        """
        {"data":[$item],"page_info":{"has_next_page":$hasNext,"end_cursor":${cursor?.let { "\"$it\"" } ?: "null"}}}
        """.trimIndent()

    private fun forumJson(id: String): String =
        """
        {
          "id":"$id",
          "experience":{"id":"exp_123","name":"Trading Signals"},
          "who_can_post":"everyone",
          "who_can_comment":"admins",
          "email_notification_preference":"all_admin_posts"
        }
        """.trimIndent()

    private fun forumPostJson(id: String): String =
        """
        {
          "id":"$id",
          "title":"Weekly Market Analysis",
          "content":"## My Strategy",
          "created_at":"2023-12-01T05:00:00.401Z",
          "updated_at":"2023-12-02T05:00:00.401Z",
          "is_edited":true,
          "is_poster_admin":false,
          "is_pinned":true,
          "parent_id":null,
          "user":{"id":"user_123","username":"johndoe42","name":"John Doe"},
          "attachments":[{"id":"file_123","filename":"document.pdf","content_type":"application/pdf","url":"https://media.whop.com/document.pdf"}],
          "view_count":42,
          "like_count":7,
          "comment_count":3
        }
        """.trimIndent()

    private fun reactionJson(id: String): String =
        """
        {
          "id":"$id",
          "resource_id":"post_123",
          "user":{"id":"user_123","username":"johndoe42","name":null},
          "emoji":":heart:"
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
