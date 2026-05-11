package dev.lepshee.whop

import dev.lepshee.whop.models.conversions.ConversionActionSource
import dev.lepshee.whop.models.conversions.ConversionContext
import dev.lepshee.whop.models.conversions.ConversionEventName
import dev.lepshee.whop.models.conversions.ConversionGender
import dev.lepshee.whop.models.conversions.ConversionUser
import dev.lepshee.whop.models.conversions.CreateConversionRequest
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

class ConversionsResourceTest {
    @Test
    fun `conversions create sends documented body and decodes response`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val conversion =
                whop.conversions.create(
                    CreateConversionRequest(
                        companyId = "biz_123",
                        eventName = ConversionEventName.CompleteRegistration,
                        actionSource = ConversionActionSource.Website,
                        context =
                            ConversionContext(
                                gclid = "gclid_123",
                                ipAddress = "203.0.113.10",
                                userAgent = "Mozilla/5.0",
                                utmCampaign = "spring",
                            ),
                        currency = "usd",
                        eventId = "evt_123",
                        eventTime = "2024-01-01T00:00:00Z",
                        productId = "prod_123",
                        referrerUrl = "https://example.com/referrer",
                        url = "https://example.com/signup",
                        user =
                            ConversionUser(
                                email = "jane@example.com",
                                firstName = "Jane",
                                gender = ConversionGender.Female,
                                userId = "user_123",
                            ),
                        value = 6.9,
                    ),
                )

            assertEquals("cnv_123", conversion.id)
            assertEquals("POST", requests.single().method.value)
            assertEquals("/api/v1/conversions", requests.single().url.encodedPath)
            assertTrue(requests.single().bodyText().contains("company_id"))
            assertTrue(requests.single().bodyText().contains("complete_registration"))
            assertTrue(requests.single().bodyText().contains("action_source"))
            assertTrue(requests.single().bodyText().contains("ip_address"))
            assertTrue(requests.single().bodyText().contains("utm_campaign"))
            assertTrue(requests.single().bodyText().contains("referrer_url"))
            assertTrue(requests.single().bodyText().contains("first_name"))
        }

    @Test
    fun `conversion request validation rejects invalid inputs`() {
        assertFailsWith<IllegalArgumentException> { CreateConversionRequest(" ", ConversionEventName.Lead) }
        assertFailsWith<IllegalArgumentException> { CreateConversionRequest("biz_123", ConversionEventName.Lead, currency = " ") }
        assertFailsWith<IllegalArgumentException> { CreateConversionRequest("biz_123", ConversionEventName.Custom, customName = " ") }
        assertFailsWith<IllegalArgumentException> { CreateConversionRequest("biz_123", ConversionEventName.Lead, eventId = "") }
        assertFailsWith<IllegalArgumentException> { CreateConversionRequest("biz_123", ConversionEventName.Lead, eventTime = " ") }
        assertFailsWith<IllegalArgumentException> { CreateConversionRequest("biz_123", ConversionEventName.Lead, planId = " ") }
        assertFailsWith<IllegalArgumentException> { CreateConversionRequest("biz_123", ConversionEventName.Lead, productId = " ") }
        assertFailsWith<IllegalArgumentException> { CreateConversionRequest("biz_123", ConversionEventName.Lead, referrerUrl = " ") }
        assertFailsWith<IllegalArgumentException> { CreateConversionRequest("biz_123", ConversionEventName.Lead, url = " ") }
        assertFailsWith<IllegalArgumentException> { CreateConversionRequest("biz_123", ConversionEventName.Lead, value = Double.NaN) }
    }

    private fun recordingEngine(requests: MutableList<HttpRequestData>): MockEngine =
        MockEngine { request ->
            requests += request
            respond("""{"id":"cnv_123"}""", headers = jsonHeaders)
        }

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
