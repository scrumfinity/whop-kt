package dev.lepshee.whop

import dev.lepshee.whop.models.accountlinks.AccountLinkUseCase
import dev.lepshee.whop.models.accountlinks.CreateAccountLinkRequest
import dev.lepshee.whop.models.cards.CardTransactionStatus
import dev.lepshee.whop.models.common.Direction
import dev.lepshee.whop.models.notifications.CreateNotificationRequest
import dev.lepshee.whop.resources.ListCardTransactionsRequest
import dev.lepshee.whop.resources.ListSetupIntentsRequest
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

class LinksNotificationsCardsResourceTest {
    @Test
    fun `account links and notifications send documented create bodies`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val link =
                whop.accountLinks.create(
                    CreateAccountLinkRequest(
                        companyId = "biz_123",
                        refreshUrl = "https://example.com/refresh",
                        returnUrl = "https://example.com/return",
                        useCase = AccountLinkUseCase.PayoutsPortal,
                    ),
                )
            val notification =
                whop.notifications.create(
                    CreateNotificationRequest(
                        companyId = "biz_123",
                        content = "Hello",
                        title = "Greeting",
                        userIds = listOf("user_123"),
                    ),
                )

            assertEquals("https://example.com/account", link.url)
            assertTrue(notification.success)
            assertEquals("/api/v1/account_links", requests[0].url.encodedPath)
            assertEquals("POST", requests[0].method.value)
            assertTrue(requests[0].bodyText().contains("payouts_portal"))
            assertEquals("/api/v1/notifications", requests[1].url.encodedPath)
            assertTrue(requests[1].bodyText().contains("company_id"))
            assertTrue(requests[1].bodyText().contains("user_ids"))
        }

    @Test
    fun `setup intents use documented paths and filters`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val page =
                whop.setupIntents.list(
                    ListSetupIntentsRequest(
                        companyId = "biz_123",
                        direction = Direction.Asc,
                        first = 10,
                    ),
                )
            whop.setupIntents.retrieve("sint_123")

            assertEquals("sint_123", page.data.single().id)
            assertEquals("/api/v1/setup_intents", requests[0].url.encodedPath)
            assertEquals("biz_123", requests[0].url.parameters["company_id"])
            assertEquals("asc", requests[0].url.parameters["direction"])
            assertEquals("10", requests[0].url.parameters["first"])
            assertEquals("/api/v1/setup_intents/sint_123", requests[1].url.encodedPath)
        }

    @Test
    fun `card transactions use documented paths and filters`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val page =
                whop.cardTransactions.list(
                    ListCardTransactionsRequest(
                        companyId = "biz_123",
                        cardId = "card_123",
                        direction = Direction.Desc,
                        status = CardTransactionStatus.Completed,
                    ),
                )
            whop.cardTransactions.retrieve("citx_123")

            assertEquals("citx_123", page.data.single().id)
            assertEquals("/api/v1/card_transactions", requests[0].url.encodedPath)
            assertEquals("biz_123", requests[0].url.parameters["company_id"])
            assertEquals("card_123", requests[0].url.parameters["card_id"])
            assertEquals("desc", requests[0].url.parameters["direction"])
            assertEquals("completed", requests[0].url.parameters["status"])
            assertEquals("/api/v1/card_transactions/citx_123", requests[1].url.encodedPath)
        }

    @Test
    fun `new request validation rejects invalid targets and path parameters`() =
        runTest {
            val whop = clientWithEngine(recordingEngine(mutableListOf()))

            assertFailsWith<IllegalArgumentException> {
                CreateNotificationRequest(content = "Hello", title = "Greeting")
            }
            assertFailsWith<IllegalArgumentException> {
                CreateNotificationRequest(content = "Hello", title = "Greeting", companyId = "biz_123", experienceId = "exp_123")
            }
            assertFailsWith<IllegalArgumentException> { whop.setupIntents.retrieve("sint_123/other") }
            assertFailsWith<IllegalArgumentException> { whop.cardTransactions.retrieve("citx_123%2Fother") }
        }

    private fun recordingEngine(requests: MutableList<HttpRequestData>): MockEngine =
        MockEngine { request ->
            requests += request
            respond(defaultJsonResponse(request.url.encodedPath), headers = jsonHeaders)
        }

    private fun defaultJsonResponse(path: String): String =
        when {
            path == "/api/v1/account_links" ->
                """{"url":"https://example.com/account","expires_at":"2023-12-01T05:00:00.401Z"}"""
            path == "/api/v1/notifications" -> """{"success":true}"""
            path == "/api/v1/setup_intents" ->
                """{"data":[${setupIntentJson()}],"page_info":{"has_next_page":false}}"""
            path.startsWith("/api/v1/setup_intents/") -> setupIntentJson()
            path == "/api/v1/card_transactions" ->
                """{"data":[${cardTransactionJson()}],"page_info":{"has_next_page":false}}"""
            path.startsWith("/api/v1/card_transactions/") -> cardTransactionJson()
            else -> "{}"
        }

    private fun setupIntentJson(): String =
        """{"id":"sint_123","status":"succeeded","created_at":"2023-12-01T05:00:00.401Z","error_message":null,"company":null,"checkout_configuration":null,"member":null,"payment_method":null,"metadata":null}"""

    private fun cardTransactionJson(): String =
        """{"id":"citx_123","usd_amount":10.0,"authorization_method":"ecommerce","created_at":"2023-12-01T05:00:00.401Z","card_id":"card_123","cashback_usd_amount":0.1,"currency":"USD","declined_reason":null,"international":false,"local_amount":10.0,"memo":null,"merchant_category":"Software","merchant_category_code":"5734","merchant_icon_url":null,"merchant_name":"Acme","posted_at":null,"status":"completed","transaction_type":"spend"}"""

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
