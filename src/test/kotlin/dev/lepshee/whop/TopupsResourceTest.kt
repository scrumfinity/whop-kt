package dev.lepshee.whop

import dev.lepshee.whop.models.topups.CreateTopupRequest
import dev.lepshee.whop.models.topups.TopupStatus
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

class TopupsResourceTest {
    @Test
    fun `topups create sends documented body and decodes response`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val topup =
                whop.topups.create(
                    CreateTopupRequest(
                        amount = 6.9,
                        companyId = "biz_123",
                        currency = "usd",
                        paymentMethodId = "payt_123",
                    ),
                )

            assertEquals("pay_123", topup.id)
            assertEquals(TopupStatus.Paid, topup.status)
            assertEquals("2023-12-01T05:00:00.401Z", topup.createdAt)
            assertEquals("usd", topup.currency)
            assertEquals(6.9, topup.total)
            assertEquals(null, topup.failureMessage)
            assertEquals("POST", requests.single().method.value)
            assertEquals("/api/v1/topups", requests.single().url.encodedPath)
            assertTrue(requests.single().bodyText().contains("\"amount\":6.9"))
            assertTrue(requests.single().bodyText().contains("company_id"))
            assertTrue(requests.single().bodyText().contains("currency"))
            assertTrue(requests.single().bodyText().contains("payment_method_id"))
        }

    @Test
    fun `topup request validation rejects invalid inputs`() {
        assertFailsWith<IllegalArgumentException> { CreateTopupRequest(0.0, "biz_123", "usd", "payt_123") }
        assertFailsWith<IllegalArgumentException> { CreateTopupRequest(Double.POSITIVE_INFINITY, "biz_123", "usd", "payt_123") }
        assertFailsWith<IllegalArgumentException> { CreateTopupRequest(1.0, " ", "usd", "payt_123") }
        assertFailsWith<IllegalArgumentException> { CreateTopupRequest(1.0, "biz_123", " ", "payt_123") }
        assertFailsWith<IllegalArgumentException> { CreateTopupRequest(1.0, "biz_123", "usd", " ") }
    }

    private fun recordingEngine(requests: MutableList<HttpRequestData>): MockEngine =
        MockEngine { request ->
            requests += request
            respond(topupJson(), headers = jsonHeaders)
        }

    private fun topupJson(): String =
        """
        {
          "id":"pay_123",
          "status":"paid",
          "created_at":"2023-12-01T05:00:00.401Z",
          "paid_at":"2023-12-01T05:00:00.401Z",
          "currency":"usd",
          "total":6.9,
          "failure_message":null
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
