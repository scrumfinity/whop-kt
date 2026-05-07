package com.lepshee.whop

import com.lepshee.whop.models.common.Direction
import com.lepshee.whop.models.companies.CreateCompanyRequest
import com.lepshee.whop.models.memberships.CancelMembershipRequest
import com.lepshee.whop.models.memberships.MembershipCancellationMode
import com.lepshee.whop.models.memberships.MembershipStatus
import com.lepshee.whop.models.payments.CreatePaymentRequest
import com.lepshee.whop.models.payments.PaymentStatus
import com.lepshee.whop.models.payments.RefundPaymentRequest
import com.lepshee.whop.models.transfers.CreateTransferRequest
import com.lepshee.whop.models.webhooks.CreateWebhookRequest
import com.lepshee.whop.models.webhooks.WebhookApiVersion
import com.lepshee.whop.resources.ListMembershipsRequest
import com.lepshee.whop.resources.ListPaymentsRequest
import com.lepshee.whop.resources.ListWebhooksRequest
import com.lepshee.whop.transport.KtorWhopHttpTransport
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
import kotlin.test.assertTrue

class ResourceCompletionTest {
    @Test
    fun `memberships list and cancel use documented paths and fields`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop =
                clientWithEngine(
                    MockEngine { request ->
                        requests += request
                        respond("""{"id":"mem_123","status":"active"}""", headers = jsonHeaders)
                    },
                )

            whop.memberships.list(
                ListMembershipsRequest(
                    companyId = "biz_123",
                    productIds = listOf("prod_123", "prod_456"),
                    statuses = listOf(MembershipStatus.Active, MembershipStatus.Canceling),
                    direction = Direction.Desc,
                ),
            )
            whop.memberships.cancel("mem_123", CancelMembershipRequest(MembershipCancellationMode.Immediate))

            assertEquals("/api/v1/memberships", requests.first().url.encodedPath)
            assertEquals(
                listOf("prod_123", "prod_456"),
                requests
                    .first()
                    .url.parameters
                    .getAll("product_ids"),
            )
            assertEquals(
                listOf("active", "canceling"),
                requests
                    .first()
                    .url.parameters
                    .getAll("statuses"),
            )
            assertEquals("POST", requests.last().method.value)
            assertEquals("/api/v1/memberships/mem_123/cancel", requests.last().url.encodedPath)
            assertTrue(requests.last().bodyText().contains("cancellation_mode"))
            assertTrue(requests.last().bodyText().contains("immediate"))
        }

    @Test
    fun `webhooks use documented management endpoints`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop =
                clientWithEngine(
                    MockEngine { request ->
                        requests += request
                        val content =
                            if (request.method.value ==
                                "DELETE"
                            ) {
                                "true"
                            } else {
                                """{"id":"wh_123","url":"https://example.com/webhooks"}"""
                            }
                        respond(content, headers = jsonHeaders)
                    },
                )

            whop.webhooks.create(
                CreateWebhookRequest(
                    url = "https://example.com/webhooks",
                    apiVersion = WebhookApiVersion.V5,
                    events = listOf("payment.succeeded"),
                ),
            )
            whop.webhooks.list(ListWebhooksRequest(companyId = "biz_123", first = 10))
            val deleted = whop.webhooks.delete("wh_123")

            assertEquals("/api/v1/webhooks", requests[0].url.encodedPath)
            assertTrue(requests[0].bodyText().contains("api_version"))
            assertTrue(requests[0].bodyText().contains("payment.succeeded"))
            assertEquals("biz_123", requests[1].url.parameters["company_id"])
            assertEquals("DELETE", requests[2].method.value)
            assertEquals("/api/v1/webhooks/wh_123", requests[2].url.encodedPath)
            assertTrue(deleted)
        }

    @Test
    fun `companies transfers and payments expose documented core endpoints`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop =
                clientWithEngine(
                    MockEngine { request ->
                        requests += request
                        val content =
                            when {
                                request.url.encodedPath.contains("companies") -> """{"id":"biz_123","title":"Acme"}"""
                                request.url.encodedPath.contains("transfers") -> """{"id":"tr_123","amount":1000,"currency":"usd"}"""
                                else -> """{"id":"pay_123","status":"paid"}"""
                            }
                        respond(content, headers = jsonHeaders)
                    },
                )

            whop.companies.create(CreateCompanyRequest(title = "Acme"))
            whop.transfers.create(
                CreateTransferRequest(
                    amount = 1000,
                    currency = "usd",
                    originId = "biz_origin",
                    destinationId = "biz_destination",
                    idempotenceKey = "transfer_123",
                ),
            )
            whop.payments.create(
                CreatePaymentRequest(
                    companyId = "biz_123",
                    memberId = "mem_123",
                    paymentMethodId = "pm_123",
                    planId = "plan_123",
                ),
            )
            whop.payments.list(ListPaymentsRequest(companyId = "biz_123", statuses = listOf(PaymentStatus.Paid)))
            whop.payments.refund("pay_123", RefundPaymentRequest(partialAmount = 500))

            assertEquals("/api/v1/companies", requests[0].url.encodedPath)
            assertTrue(requests[0].bodyText().contains("Acme"))
            assertEquals("/api/v1/transfers", requests[1].url.encodedPath)
            assertTrue(requests[1].bodyText().contains("idempotence_key"))
            assertEquals("/api/v1/payments", requests[2].url.encodedPath)
            assertTrue(requests[2].bodyText().contains("payment_method_id"))
            assertEquals(listOf("paid"), requests[3].url.parameters.getAll("statuses"))
            assertEquals("/api/v1/payments/pay_123/refund", requests[4].url.encodedPath)
            assertTrue(requests[4].bodyText().contains("partial_amount"))
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
