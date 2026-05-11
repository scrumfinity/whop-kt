package dev.lepshee.whop

import dev.lepshee.whop.models.common.Direction
import dev.lepshee.whop.models.messaging.AiChatMessageAttachmentInput
import dev.lepshee.whop.models.messaging.AiChatMessageSource
import dev.lepshee.whop.models.messaging.AiChatNotificationPreference
import dev.lepshee.whop.models.messaging.CreateAiChatRequest
import dev.lepshee.whop.models.messaging.CreateDmChannelRequest
import dev.lepshee.whop.models.messaging.CreateDmMemberRequest
import dev.lepshee.whop.models.messaging.CreateMessageRequest
import dev.lepshee.whop.models.messaging.CreateSupportChannelRequest
import dev.lepshee.whop.models.messaging.DmMemberNotificationPreference
import dev.lepshee.whop.models.messaging.DmMemberStatus
import dev.lepshee.whop.models.messaging.MessageAttachmentInput
import dev.lepshee.whop.models.messaging.MessageChannelOrder
import dev.lepshee.whop.models.messaging.MessagePollInput
import dev.lepshee.whop.models.messaging.MessagePollOptionInput
import dev.lepshee.whop.models.messaging.SupportChannelView
import dev.lepshee.whop.models.messaging.UpdateAiChatRequest
import dev.lepshee.whop.models.messaging.UpdateChatChannelRequest
import dev.lepshee.whop.models.messaging.UpdateDmChannelRequest
import dev.lepshee.whop.models.messaging.UpdateDmMemberRequest
import dev.lepshee.whop.models.messaging.UpdateMessageRequest
import dev.lepshee.whop.models.messaging.WhoCanPost
import dev.lepshee.whop.models.messaging.WhoCanReact
import dev.lepshee.whop.resources.ListAiChatsRequest
import dev.lepshee.whop.resources.ListChatChannelsRequest
import dev.lepshee.whop.resources.ListDmChannelsRequest
import dev.lepshee.whop.resources.ListDmMembersRequest
import dev.lepshee.whop.resources.ListMessagesRequest
import dev.lepshee.whop.resources.ListSupportChannelsRequest
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
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class MessagingResourceTest {
    @Test
    fun `chat channel endpoints send documented paths filters and update body`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val page =
                whop.chatChannels.list(
                    ListChatChannelsRequest(companyId = "biz_123", productId = "prod_123", first = 10),
                )
            whop.chatChannels.retrieve("exp_123")
            val updated =
                whop.chatChannels.update(
                    "chat_123",
                    UpdateChatChannelRequest(
                        banMedia = true,
                        bannedWords = listOf("spam"),
                        whoCanPost = WhoCanPost.Admins,
                        whoCanReact = WhoCanReact.NoOne,
                    ),
                )

            assertEquals("chat_123", page.data.single().id)
            assertTrue(updated.banMedia)
            assertEquals("/api/v1/chat_channels", requests[0].url.encodedPath)
            assertEquals("biz_123", requests[0].url.parameters["company_id"])
            assertEquals("prod_123", requests[0].url.parameters["product_id"])
            assertEquals("10", requests[0].url.parameters["first"])
            assertEquals("/api/v1/chat_channels/exp_123", requests[1].url.encodedPath)
            assertEquals("PATCH", requests[2].method.value)
            assertEquals("/api/v1/chat_channels/chat_123", requests[2].url.encodedPath)
            assertTrue(requests[2].bodyText().contains("ban_media"))
            assertTrue(requests[2].bodyText().contains("no_one"))
        }

    @Test
    fun `ai chat endpoints send documented paths filters bodies and delete`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val page =
                whop.aiChats.list(
                    ListAiChatsRequest(
                        after = "cursor_1",
                        first = 5,
                        onlyActiveCrons = true,
                    ),
                )
            val created =
                whop.aiChats.create(
                    CreateAiChatRequest(
                        messageText = "Analyze revenue",
                        currentCompanyId = "biz_123",
                        messageAttachments = listOf(AiChatMessageAttachmentInput(id = "file_123")),
                        messageSource = AiChatMessageSource.Suggestion,
                        suggestionType = "revenue",
                        title = "Revenue Help",
                    ),
                )
            whop.aiChats.retrieve("aich_123")
            whop.aiChats.update(
                "aich_123",
                UpdateAiChatRequest(
                    currentCompanyId = "biz_456",
                    notificationPreference = AiChatNotificationPreference.None,
                    title = "Muted Revenue Help",
                ),
            )
            val deleted = whop.aiChats.delete("aich_123")

            assertEquals("aich_123", page.data.single().id)
            assertEquals("aich_123", created.id)
            assertTrue(deleted)
            assertEquals("/api/v1/ai_chats", requests[0].url.encodedPath)
            assertEquals("cursor_1", requests[0].url.parameters["after"])
            assertEquals("5", requests[0].url.parameters["first"])
            assertEquals("true", requests[0].url.parameters["only_active_crons"])
            assertEquals("POST", requests[1].method.value)
            assertTrue(requests[1].bodyText().contains("message_text"))
            assertTrue(requests[1].bodyText().contains("current_company_id"))
            assertTrue(requests[1].bodyText().contains("message_attachments"))
            assertTrue(requests[1].bodyText().contains("suggestion"))
            assertTrue(requests[1].bodyText().contains("suggestion_type"))
            assertEquals("/api/v1/ai_chats/aich_123", requests[2].url.encodedPath)
            assertEquals("PATCH", requests[3].method.value)
            assertTrue(requests[3].bodyText().contains("notification_preference"))
            assertTrue(requests[3].bodyText().contains("none"))
            assertEquals("DELETE", requests[4].method.value)
            assertEquals("/api/v1/ai_chats/aich_123", requests[4].url.encodedPath)
        }

    @Test
    fun `support channel endpoints send documented paths filters and body`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val page =
                whop.supportChannels.list(
                    ListSupportChannelsRequest(
                        companyId = "biz_123",
                        view = SupportChannelView.Customer,
                        open = true,
                        direction = Direction.Asc,
                        order = MessageChannelOrder.LastPostSentAt,
                        first = 6,
                    ),
                )
            val created =
                whop.supportChannels.create(
                    CreateSupportChannelRequest(
                        companyId = "biz_123",
                        userId = "user_123",
                        customName = "VIP Support",
                    ),
                )
            whop.supportChannels.retrieve("sup_123")

            assertEquals("sup_123", page.data.single().id)
            assertEquals("sup_123", created.id)
            assertEquals("/api/v1/support_channels", requests[0].url.encodedPath)
            assertEquals("biz_123", requests[0].url.parameters["company_id"])
            assertEquals("customer", requests[0].url.parameters["view"])
            assertEquals("true", requests[0].url.parameters["open"])
            assertEquals("asc", requests[0].url.parameters["direction"])
            assertEquals("last_post_sent_at", requests[0].url.parameters["order"])
            assertEquals("6", requests[0].url.parameters["first"])
            assertEquals("POST", requests[1].method.value)
            assertTrue(requests[1].bodyText().contains("company_id"))
            assertTrue(requests[1].bodyText().contains("user_id"))
            assertTrue(requests[1].bodyText().contains("custom_name"))
            assertEquals("/api/v1/support_channels/sup_123", requests[2].url.encodedPath)
        }

    @Test
    fun `dm channel endpoints send documented paths filters bodies and delete`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val page = whop.dmChannels.list(ListDmChannelsRequest(companyId = "biz_123", first = 5))
            val created =
                whop.dmChannels.create(
                    CreateDmChannelRequest(
                        withUserIds = listOf("user_123", "dean"),
                        companyId = "biz_123",
                        customName = "Project Chat",
                    ),
                )
            whop.dmChannels.retrieve("dms_123")
            whop.dmChannels.update("dms_123", UpdateDmChannelRequest(customName = "New Name"))
            val deleted = whop.dmChannels.delete("dms_123")

            assertEquals("dms_123", page.data.single().id)
            assertEquals("dms_123", created.id)
            assertTrue(deleted)
            assertEquals("/api/v1/dm_channels", requests[0].url.encodedPath)
            assertEquals("biz_123", requests[0].url.parameters["company_id"])
            assertEquals("5", requests[0].url.parameters["first"])
            assertEquals("POST", requests[1].method.value)
            assertTrue(requests[1].bodyText().contains("with_user_ids"))
            assertTrue(requests[1].bodyText().contains("Project Chat"))
            assertEquals("/api/v1/dm_channels/dms_123", requests[2].url.encodedPath)
            assertEquals("PATCH", requests[3].method.value)
            assertTrue(requests[3].bodyText().contains("custom_name"))
            assertEquals("DELETE", requests[4].method.value)
            assertEquals("/api/v1/dm_channels/dms_123", requests[4].url.encodedPath)
        }

    @Test
    fun `dm member endpoints send documented paths filters bodies and delete`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val page = whop.dmMembers.list(ListDmMembersRequest(channelId = "dms_123", first = 7))
            val created = whop.dmMembers.create(CreateDmMemberRequest(channelId = "dms_123", userId = "user_123"))
            whop.dmMembers.retrieve("dmem_123")
            whop.dmMembers.update(
                "dmem_123",
                UpdateDmMemberRequest(
                    notificationPreference = DmMemberNotificationPreference.Mentions,
                    status = DmMemberStatus.Hidden,
                ),
            )
            val deleted = whop.dmMembers.delete("dmem_123")

            assertEquals("dmem_123", page.data.single().id)
            assertEquals("dmem_123", created.id)
            assertTrue(deleted)
            assertEquals("/api/v1/dm_members", requests[0].url.encodedPath)
            assertEquals("dms_123", requests[0].url.parameters["channel_id"])
            assertEquals("7", requests[0].url.parameters["first"])
            assertEquals("POST", requests[1].method.value)
            assertTrue(requests[1].bodyText().contains("channel_id"))
            assertTrue(requests[1].bodyText().contains("user_id"))
            assertEquals("/api/v1/dm_members/dmem_123", requests[2].url.encodedPath)
            assertEquals("PATCH", requests[3].method.value)
            assertTrue(requests[3].bodyText().contains("mentions"))
            assertTrue(requests[3].bodyText().contains("hidden"))
            assertEquals("DELETE", requests[4].method.value)
        }

    @Test
    fun `message endpoints send documented paths filters bodies and delete`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val page =
                whop.messages.list(
                    ListMessagesRequest(
                        channelId = "exp_123",
                        first = 10,
                        direction = Direction.Desc,
                    ),
                )
            val created =
                whop.messages.create(
                    CreateMessageRequest(
                        channelId = "exp_123",
                        content = "Hello **world**",
                        attachments = listOf(MessageAttachmentInput(id = "file_123")),
                        autoDetectLinks = true,
                        poll = MessagePollInput(options = listOf(MessagePollOptionInput(id = "1", text = "Yes"))),
                        replyingToMessageId = "msg_parent",
                    ),
                )
            whop.messages.retrieve("msg_123")
            whop.messages.update(
                "msg_123",
                UpdateMessageRequest(
                    content = "Updated **world**",
                    isPinned = true,
                    attachments = listOf(MessageAttachmentInput(id = "file_456")),
                ),
            )
            val deleted = whop.messages.delete("msg_123")

            assertEquals("msg_123", page.data.single().id)
            assertEquals("msg_123", created.id)
            assertTrue(deleted)
            assertEquals("/api/v1/messages", requests[0].url.encodedPath)
            assertEquals("exp_123", requests[0].url.parameters["channel_id"])
            assertEquals("10", requests[0].url.parameters["first"])
            assertEquals("desc", requests[0].url.parameters["direction"])
            assertEquals("POST", requests[1].method.value)
            assertTrue(requests[1].bodyText().contains("channel_id"))
            assertTrue(requests[1].bodyText().contains("auto_detect_links"))
            assertTrue(requests[1].bodyText().contains("replying_to_message_id"))
            assertTrue(requests[1].bodyText().contains("file_123"))
            assertTrue(requests[1].bodyText().contains("Yes"))
            assertEquals("/api/v1/messages/msg_123", requests[2].url.encodedPath)
            assertEquals("PATCH", requests[3].method.value)
            assertTrue(requests[3].bodyText().contains("is_pinned"))
            assertTrue(requests[3].bodyText().contains("file_456"))
            assertEquals("DELETE", requests[4].method.value)
            assertEquals("/api/v1/messages/msg_123", requests[4].url.encodedPath)
        }

    @Test
    fun `message update allows empty attachments replacement list`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            whop.messages.update("msg_123", UpdateMessageRequest(attachments = emptyList()))

            assertEquals("PATCH", requests.single().method.value)
            assertEquals("/api/v1/messages/msg_123", requests.single().url.encodedPath)
            assertTrue(requests.single().bodyText().contains("\"attachments\":[]"))
        }

    @Test
    fun `messaging path parameters reject path injection characters`() =
        runTest {
            val whop = clientWithEngine(recordingEngine(mutableListOf()))

            assertFailsWith<IllegalArgumentException> { whop.chatChannels.retrieve("chat_123/other") }
            assertFailsWith<IllegalArgumentException> { whop.aiChats.retrieve("aich_123/other") }
            assertFailsWith<IllegalArgumentException> { whop.supportChannels.retrieve("sup_123%2Fother") }
            assertFailsWith<IllegalArgumentException> { whop.dmChannels.retrieve("dms_123%2Fother") }
            assertFailsWith<IllegalArgumentException> { whop.dmMembers.retrieve("../dmem_123") }
            assertFailsWith<IllegalArgumentException> { whop.messages.retrieve("msg_123/other") }
        }

    @Test
    fun `messaging request validation rejects invalid inputs`() {
        assertFailsWith<IllegalArgumentException> { ListChatChannelsRequest(companyId = " ") }
        assertFailsWith<IllegalArgumentException> { UpdateChatChannelRequest(bannedWords = listOf("spam", "")) }
        assertFailsWith<IllegalArgumentException> { ListAiChatsRequest(first = 0) }
        assertFailsWith<IllegalArgumentException> { CreateAiChatRequest(messageText = " ") }
        assertFailsWith<IllegalArgumentException> { CreateAiChatRequest(messageText = "Hello", title = " ") }
        assertFailsWith<IllegalArgumentException> { AiChatMessageAttachmentInput(id = "") }
        assertFailsWith<IllegalArgumentException> { ListSupportChannelsRequest(companyId = " ") }
        assertFailsWith<IllegalArgumentException> { ListSupportChannelsRequest(last = 0) }
        assertFailsWith<IllegalArgumentException> { CreateSupportChannelRequest(companyId = "biz_123", userId = " ") }
        assertFailsWith<IllegalArgumentException> {
            CreateSupportChannelRequest(companyId = "biz_123", userId = "user_123", customName = " ")
        }
        assertFailsWith<IllegalArgumentException> { CreateDmChannelRequest(withUserIds = emptyList()) }
        assertFailsWith<IllegalArgumentException> { CreateDmMemberRequest(channelId = "dms_123", userId = " ") }
        assertFailsWith<IllegalArgumentException> { ListDmMembersRequest(channelId = "dms_123", first = 0) }
        assertFailsWith<IllegalArgumentException> { ListMessagesRequest(channelId = " ") }
        assertFailsWith<IllegalArgumentException> { CreateMessageRequest(channelId = "exp_123", content = " ") }
        assertFailsWith<IllegalArgumentException> { MessageAttachmentInput(id = "") }
        assertFailsWith<IllegalArgumentException> { MessagePollInput(options = emptyList()) }
        assertFailsWith<IllegalArgumentException> { MessagePollOptionInput(id = "1", text = "") }
        assertFailsWith<IllegalArgumentException> { UpdateMessageRequest(content = " ") }
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
            method == "DELETE" -> "true"
            path == "/api/v1/chat_channels" -> pageJson(chatChannelJson())
            path.startsWith("/api/v1/chat_channels/") -> chatChannelJson()
            path == "/api/v1/ai_chats" && method == "GET" -> pageJson(aiChatJson())
            path == "/api/v1/ai_chats" || path.startsWith("/api/v1/ai_chats/") -> aiChatJson()
            path == "/api/v1/support_channels" && method == "GET" -> pageJson(supportChannelJson())
            path == "/api/v1/support_channels" || path.startsWith("/api/v1/support_channels/") -> supportChannelJson()
            path == "/api/v1/dm_channels" && method == "GET" -> pageJson(dmChannelJson())
            path == "/api/v1/dm_channels" || path.startsWith("/api/v1/dm_channels/") -> dmChannelJson()
            path == "/api/v1/dm_members" && method == "GET" -> pageJson(dmMemberJson())
            path == "/api/v1/dm_members" || path.startsWith("/api/v1/dm_members/") -> dmMemberJson()
            path == "/api/v1/messages" && method == "GET" -> pageJson(messageJson())
            path == "/api/v1/messages" || path.startsWith("/api/v1/messages/") -> messageJson()
            else -> "{}"
        }

    private fun pageJson(item: String): String =
        """
        {"data":[$item],"page_info":{"has_next_page":false}}
        """.trimIndent()

    private fun chatChannelJson(): String =
        """
        {
          "id":"chat_123",
          "experience":{"id":"exp_123","name":"Trading Signals"},
          "ban_media":true,
          "ban_urls":false,
          "who_can_post":"admins",
          "who_can_react":"no_one",
          "user_posts_cooldown_seconds":30,
          "banned_words":["spam"]
        }
        """.trimIndent()

    private fun aiChatJson(): String =
        """
        {
          "id":"aich_123",
          "title":"Revenue Help",
          "notification_preference":"all",
          "message_count":3,
          "blended_token_usage":"1727606400000",
          "created_at":"2023-12-01T05:00:00.401Z",
          "updated_at":"2023-12-02T05:00:00.401Z",
          "last_message_at":"2023-12-02T06:00:00.401Z",
          "user":{"id":"user_123"}
        }
        """.trimIndent()

    private fun supportChannelJson(): String =
        """
        {
          "id":"sup_123",
          "company_id":"biz_123",
          "custom_name":"VIP Support",
          "customer_user":{"id":"user_123","name":null,"username":"dean"},
          "resolved_at":null,
          "last_message_at":"2023-12-01T05:00:00.401Z"
        }
        """.trimIndent()

    private fun dmChannelJson(): String =
        """
        {
          "id":"dms_123",
          "created_at":"1727606400000",
          "name":"Project Chat",
          "last_message_at":"2023-12-01T05:00:00.401Z"
        }
        """.trimIndent()

    private fun dmMemberJson(): String =
        """
        {
          "id":"dmem_123",
          "user_id":"user_123",
          "channel_id":"dms_123",
          "status":"accepted",
          "last_viewed_at":"1727606400000",
          "notification_preference":"all"
        }
        """.trimIndent()

    private fun messageJson(): String =
        """
        {
          "id":"msg_123",
          "content":"Hello **world**",
          "created_at":"2023-12-01T05:00:00.401Z",
          "updated_at":"2023-12-02T05:00:00.401Z",
          "poll":{"options":[{"id":"1","text":"Yes"}]},
          "replying_to_message_id":null,
          "is_edited":false,
          "is_pinned":true,
          "message_type":"regular",
          "mentions":["user_456"],
          "mentions_everyone":false,
          "user":{"id":"user_123","username":"dean","name":null},
          "view_count":42,
          "reaction_counts":[{"emoji":":heart:","count":2}],
          "poll_votes":[{"option_id":"1","count":1}]
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
