package dev.lepshee.whop

import dev.lepshee.whop.models.companies.CreateChildCompanyApiKeyRequest
import dev.lepshee.whop.models.memberships.AddFreeDaysMembershipRequest
import dev.lepshee.whop.models.memberships.PauseMembershipRequest
import dev.lepshee.whop.models.users.UpdateUserRequest
import dev.lepshee.whop.models.webhooks.UpdateWebhookRequest
import dev.lepshee.whop.models.webhooks.WebhookEvent
import dev.lepshee.whop.resources.ListPaymentFeesRequest
import dev.lepshee.whop.resources.ListPaymentMethodsRequest
import dev.lepshee.whop.resources.ListPayoutMethodsRequest
import dev.lepshee.whop.resources.ListRefundsRequest
import dev.lepshee.whop.resources.ListReviewsRequest
import dev.lepshee.whop.resources.ListVerificationsRequest
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
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class ResourceExpansionTest {
    @Test
    fun `completed existing resources use documented endpoints`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            whop.products.delete("prod_123")
            whop.plans.delete("plan_123")
            whop.webhooks.retrieve("hook_123")
            whop.webhooks.update("hook_123", UpdateWebhookRequest(enabled = true, events = listOf(WebhookEvent.PaymentSucceeded)))
            whop.companies.createChildCompanyApiKey("biz_parent", CreateChildCompanyApiKeyRequest("biz_child"))
            whop.payments.listFees("pay_123", ListPaymentFeesRequest(first = 10))
            whop.payments.retry("pay_123")
            whop.payments.void("pay_123")
            whop.memberships.addFreeDays("mem_123", AddFreeDaysMembershipRequest(7))
            whop.memberships.pause("mem_123", PauseMembershipRequest(voidPayments = true))
            whop.memberships.resume("mem_123")
            whop.memberships.uncancel("mem_123")

            assertEquals("/api/v1/products/prod_123", requests[0].url.encodedPath)
            assertEquals("DELETE", requests[0].method.value)
            assertEquals("/api/v1/plans/plan_123", requests[1].url.encodedPath)
            assertEquals("/api/v1/webhooks/hook_123", requests[2].url.encodedPath)
            assertEquals("PATCH", requests[3].method.value)
            assertTrue(requests[3].bodyText().contains("payment.succeeded"))
            assertEquals("/api/v1/companies/biz_parent/api_keys", requests[4].url.encodedPath)
            assertTrue(requests[4].bodyText().contains("child_company_id"))
            assertEquals("/api/v1/payments/pay_123/fees", requests[5].url.encodedPath)
            assertEquals("10", requests[5].url.parameters["first"])
            assertEquals("/api/v1/payments/pay_123/retry", requests[6].url.encodedPath)
            assertFalse(requests[6].body is TextContent)
            assertEquals("/api/v1/payments/pay_123/void", requests[7].url.encodedPath)
            assertFalse(requests[7].body is TextContent)
            assertEquals("/api/v1/memberships/mem_123/add_free_days", requests[8].url.encodedPath)
            assertTrue(requests[8].bodyText().contains("free_days"))
            assertEquals("/api/v1/memberships/mem_123/pause", requests[9].url.encodedPath)
            assertTrue(requests[9].bodyText().contains("void_payments"))
            assertEquals("/api/v1/memberships/mem_123/resume", requests[10].url.encodedPath)
            assertFalse(requests[10].body is TextContent)
            assertEquals("/api/v1/memberships/mem_123/uncancel", requests[11].url.encodedPath)
            assertFalse(requests[11].body is TextContent)
        }

    @Test
    fun `new read only resources send documented paths and filters`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            whop.refunds.list(ListRefundsRequest(companyId = "biz_123", paymentId = "pay_123"))
            whop.refunds.retrieve("rfnd_123")
            whop.paymentMethods.list(ListPaymentMethodsRequest(companyId = "biz_123"))
            whop.paymentMethods.retrieve("payt_123", companyId = "biz_123")
            whop.payoutMethods.list(ListPayoutMethodsRequest(companyId = "biz_123"))
            whop.payoutMethods.retrieve("potk_123")
            whop.payoutAccounts.retrieve("poact_123")
            whop.verifications.list(ListVerificationsRequest(payoutAccountId = "poact_123"))
            whop.verifications.retrieve("verf_123")
            whop.reviews.list(ListReviewsRequest(productId = "prod_123", minStars = 4))
            whop.reviews.retrieve("rev_123")

            assertEquals("/api/v1/refunds", requests[0].url.encodedPath)
            assertEquals("biz_123", requests[0].url.parameters["company_id"])
            assertEquals("pay_123", requests[0].url.parameters["payment_id"])
            assertEquals("/api/v1/refunds/rfnd_123", requests[1].url.encodedPath)
            assertEquals("/api/v1/payment_methods", requests[2].url.encodedPath)
            assertEquals("/api/v1/payment_methods/payt_123", requests[3].url.encodedPath)
            assertEquals("biz_123", requests[3].url.parameters["company_id"])
            assertEquals("/api/v1/payout_methods", requests[4].url.encodedPath)
            assertEquals("biz_123", requests[4].url.parameters["company_id"])
            assertEquals("/api/v1/payout_methods/potk_123", requests[5].url.encodedPath)
            assertEquals("/api/v1/payout_accounts/poact_123", requests[6].url.encodedPath)
            assertEquals("/api/v1/verifications", requests[7].url.encodedPath)
            assertEquals("poact_123", requests[7].url.parameters["payout_account_id"])
            assertEquals("/api/v1/verifications/verf_123", requests[8].url.encodedPath)
            assertEquals("/api/v1/reviews", requests[9].url.encodedPath)
            assertEquals("prod_123", requests[9].url.parameters["product_id"])
            assertEquals("4", requests[9].url.parameters["min_stars"])
            assertEquals("/api/v1/reviews/rev_123", requests[10].url.encodedPath)
        }

    @Test
    fun `path parameters reject path injection characters`() =
        runTest {
            val whop = clientWithEngine(recordingEngine(mutableListOf()))

            assertFailsWith<IllegalArgumentException> { whop.payments.retrieve("pay_123/refund") }
            assertFailsWith<IllegalArgumentException> { whop.users.checkAccess("user_123", "prod_123/../biz_123") }
            assertFailsWith<IllegalArgumentException> { whop.memberships.resume("mem_123%2Fcancel") }
            assertFailsWith<IllegalArgumentException> { whop.webhooks.retrieve("hook_123?x=1") }
        }

    @Test
    fun `users support nullable names bio updates and auto paging`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val engine =
                MockEngine { request ->
                    requests += request
                    val content =
                        when (request.url.encodedPath) {
                            "/api/v1/users" ->
                                """{"data":[{"id":"user_123","username":"dean","name":null,"bio":"hello"}],"page_info":{"has_next_page":false}}"""
                            "/api/v1/users/user_123" -> """{"id":"user_123","username":"dean","name":null,"bio":"hello"}"""
                            else -> defaultJsonResponse(request.url.encodedPath)
                        }
                    respond(content, headers = jsonHeaders)
                }
            val whop = clientWithEngine(engine)

            val user = whop.users.retrieve("user_123")
            whop.users.update("user_123", UpdateUserRequest(bio = "updated"))
            val pagedUsernames = mutableListOf<String>()
            whop.users.listAutoPaging().collect { pagedUsernames += it.username }

            assertNull(user.name)
            assertEquals("hello", user.bio)
            assertTrue(requests[1].bodyText().contains("bio"))
            assertEquals(listOf("dean"), pagedUsernames)
        }

    private fun recordingEngine(requests: MutableList<HttpRequestData>): MockEngine =
        MockEngine { request ->
            requests += request
            val content = if (request.method.value == "DELETE") "true" else defaultJsonResponse(request.url.encodedPath)
            respond(content, headers = jsonHeaders)
        }

    private fun defaultJsonResponse(path: String): String =
        if (path.contains("webhooks")) {
            """{"id":"hook_123","url":"https://example.com/webhooks"}"""
        } else if (
            path.endsWith("/fees") ||
            path.endsWith("refunds") ||
            path.endsWith("payment_methods") ||
            path.endsWith("payout_methods") ||
            path.endsWith("verifications") ||
            path.endsWith("reviews")
        ) {
            """{"data":[{"id":"obj_123"}],"page_info":{"has_next_page":false}}"""
        } else {
            """{"id":"obj_123","username":"dean","name":"Dean"}"""
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
