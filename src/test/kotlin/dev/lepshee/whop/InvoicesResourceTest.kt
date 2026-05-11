package dev.lepshee.whop

import dev.lepshee.whop.core.JsonConfig
import dev.lepshee.whop.models.common.Direction
import dev.lepshee.whop.models.invoices.CreateInvoiceRequest
import dev.lepshee.whop.models.invoices.InvoiceBillingAddress
import dev.lepshee.whop.models.invoices.InvoiceCollectionMethod
import dev.lepshee.whop.models.invoices.InvoiceLineItem
import dev.lepshee.whop.models.invoices.InvoicePlanRequest
import dev.lepshee.whop.models.invoices.InvoiceProductInput
import dev.lepshee.whop.models.invoices.InvoiceStatus
import dev.lepshee.whop.models.invoices.InvoicesSortableColumn
import dev.lepshee.whop.models.invoices.UpdateInvoiceRequest
import dev.lepshee.whop.resources.ListInvoicesRequest
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
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.JsonPrimitive
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class InvoicesResourceTest {
    @Test
    fun `invoice endpoints send documented paths filters bodies actions and decode`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val page =
                whop.invoices.list(
                    ListInvoicesRequest(
                        companyId = "biz_123",
                        after = "cursor_0",
                        before = "cursor_before",
                        first = 10,
                        last = 20,
                        direction = Direction.Desc,
                        productIds = listOf("prod_123"),
                        collectionMethods = listOf(InvoiceCollectionMethod.SendInvoice),
                        statuses = listOf(InvoiceStatus.Open),
                        order = InvoicesSortableColumn.DueDate,
                        createdBefore = "2024-01-31T00:00:00Z",
                        createdAfter = "2024-01-01T00:00:00Z",
                    ),
                )
            val created = whop.invoices.create(createInvoiceRequest())
            val retrieved = whop.invoices.retrieve("inv_123")
            val updated =
                whop.invoices.update(
                    "inv_123",
                    UpdateInvoiceRequest(
                        customerName = UpdateInvoiceRequest.text("Jane Buyer"),
                        collectionMethod = UpdateInvoiceRequest.collectionMethod(InvoiceCollectionMethod.ChargeAutomatically),
                    ),
                )
            val deleted = whop.invoices.delete("inv_123")
            val paid = whop.invoices.markPaid("inv_123")
            val uncollectible = whop.invoices.markUncollectible("inv_123")
            val voided = whop.invoices.void("inv_123")

            assertEquals("inv_123", page.data.single().id)
            assertEquals(InvoiceStatus.Open, created.status)
            assertEquals("plan_123", retrieved.currentPlan.id)
            assertEquals("#0001", updated.number)
            assertTrue(deleted)
            assertTrue(paid)
            assertTrue(uncollectible)
            assertTrue(voided)
            assertEquals("/api/v1/invoices", requests[0].url.encodedPath)
            assertEquals("biz_123", requests[0].url.parameters["company_id"])
            assertEquals("cursor_0", requests[0].url.parameters["after"])
            assertEquals("cursor_before", requests[0].url.parameters["before"])
            assertEquals("10", requests[0].url.parameters["first"])
            assertEquals("20", requests[0].url.parameters["last"])
            assertEquals("desc", requests[0].url.parameters["direction"])
            assertEquals(listOf("prod_123"), requests[0].url.parameters.getAll("product_ids"))
            assertEquals(listOf("send_invoice"), requests[0].url.parameters.getAll("collection_methods"))
            assertEquals(listOf("open"), requests[0].url.parameters.getAll("statuses"))
            assertEquals("due_date", requests[0].url.parameters["order"])
            assertEquals("2024-01-31T00:00:00Z", requests[0].url.parameters["created_before"])
            assertEquals("2024-01-01T00:00:00Z", requests[0].url.parameters["created_after"])
            assertEquals("POST", requests[1].method.value)
            assertTrue(requests[1].bodyText().contains("collection_method"), requests[1].bodyText())
            assertTrue(requests[1].bodyText().contains("send_invoice"), requests[1].bodyText())
            assertTrue(requests[1].bodyText().contains("line_items"), requests[1].bodyText())
            assertEquals("/api/v1/invoices/inv_123", requests[2].url.encodedPath)
            assertEquals("PATCH", requests[3].method.value)
            assertTrue(requests[3].bodyText().contains("charge_automatically"), requests[3].bodyText())
            assertEquals("DELETE", requests[4].method.value)
            assertEquals("/api/v1/invoices/inv_123", requests[4].url.encodedPath)
            assertEquals("POST", requests[5].method.value)
            assertEquals("/api/v1/invoices/inv_123/mark_paid", requests[5].url.encodedPath)
            assertEquals("/api/v1/invoices/inv_123/mark_uncollectible", requests[6].url.encodedPath)
            assertEquals("/api/v1/invoices/inv_123/void", requests[7].url.encodedPath)
        }

    @Test
    fun `invoices auto paging follows cursors`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            assertEquals(
                listOf("inv_123", "inv_456"),
                whop.invoices
                    .listAutoPaging(ListInvoicesRequest(companyId = "biz_123"))
                    .toList()
                    .map { it.id },
            )
            assertTrue(requests.any { it.url.parameters["after"] == "inv_cursor" })
        }

    @Test
    fun `invoice requests can explicitly clear documented nullable fields`() {
        val json =
            JsonConfig.whopJson.encodeToString(
                UpdateInvoiceRequest(
                    customerName = UpdateInvoiceRequest.clear,
                    billingAddress = UpdateInvoiceRequest.clear,
                    collectionMethod = UpdateInvoiceRequest.clear,
                ),
            )

        assertTrue(json.contains("\"customer_name\":null"), json)
        assertTrue(json.contains("\"billing_address\":null"), json)
        assertTrue(json.contains("\"collection_method\":null"), json)
    }

    @Test
    fun `invoice validation rejects invalid inputs and path parameters`() =
        runTest {
            val whop = clientWithEngine(recordingEngine(mutableListOf()))

            assertFailsWith<IllegalArgumentException> { ListInvoicesRequest(companyId = " ") }
            assertFailsWith<IllegalArgumentException> { ListInvoicesRequest(first = 0) }
            assertFailsWith<IllegalArgumentException> { ListInvoicesRequest(productIds = listOf("prod_123", "")) }
            assertFailsWith<IllegalArgumentException> { InvoiceProductInput(" ") }
            assertFailsWith<IllegalArgumentException> { InvoicePlanRequest(initialPrice = -1.0) }
            assertFailsWith<IllegalArgumentException> { InvoiceLineItem("", unitPrice = 1.0) }
            assertFailsWith<IllegalArgumentException> { InvoiceLineItem("Setup", quantity = 0.0, unitPrice = 1.0) }
            assertFailsWith<IllegalArgumentException> { InvoiceLineItem("Setup", unitPrice = -1.0) }
            assertFailsWith<IllegalArgumentException> {
                CreateInvoiceRequest(
                    companyId = "biz_123",
                    plan = InvoicePlanRequest(initialPrice = 20.0),
                    collectionMethod = InvoiceCollectionMethod.SendInvoice,
                    product = InvoiceProductInput("VIP"),
                    customerName = CreateInvoiceRequest.text("Jane Buyer"),
                    emailAddress = CreateInvoiceRequest.text("jane@example.com"),
                )
            }
            assertFailsWith<IllegalArgumentException> {
                CreateInvoiceRequest(
                    companyId = "biz_123",
                    plan = InvoicePlanRequest(initialPrice = 20.0),
                    collectionMethod = InvoiceCollectionMethod.SendInvoice,
                    product = InvoiceProductInput("VIP"),
                    dueDate = CreateInvoiceRequest.text("2024-02-01T00:00:00Z"),
                    customerName = CreateInvoiceRequest.text("Jane Buyer"),
                )
            }
            assertFailsWith<IllegalArgumentException> {
                CreateInvoiceRequest(
                    companyId = "biz_123",
                    plan = InvoicePlanRequest(initialPrice = 20.0),
                    collectionMethod = InvoiceCollectionMethod.ChargeAutomatically,
                    product = InvoiceProductInput("VIP"),
                    dueDate = CreateInvoiceRequest.text("2024-02-01T00:00:00Z"),
                    memberId = CreateInvoiceRequest.text("mber_123"),
                )
            }
            assertFailsWith<IllegalArgumentException> {
                CreateInvoiceRequest(
                    companyId = "biz_123",
                    plan = InvoicePlanRequest(initialPrice = 20.0),
                    collectionMethod = InvoiceCollectionMethod.SendInvoice,
                    product = InvoiceProductInput("VIP"),
                    dueDate = CreateInvoiceRequest.text("2024-02-01T00:00:00Z"),
                    memberId = CreateInvoiceRequest.text("mber_123"),
                    lineItems = emptyList(),
                )
            }
            assertFailsWith<IllegalArgumentException> {
                CreateInvoiceRequest(
                    companyId = "biz_123",
                    plan = InvoicePlanRequest(initialPrice = 20.0),
                    collectionMethod = InvoiceCollectionMethod.SendInvoice,
                    product = InvoiceProductInput("VIP"),
                    productId = "prod_123",
                )
            }
            assertFailsWith<IllegalArgumentException> {
                CreateInvoiceRequest(
                    companyId = "biz_123",
                    plan = InvoicePlanRequest(initialPrice = 20.0),
                    collectionMethod = InvoiceCollectionMethod.SendInvoice,
                )
            }
            assertFailsWith<IllegalArgumentException> {
                CreateInvoiceRequest(
                    companyId = "biz_123",
                    plan = InvoicePlanRequest(initialPrice = 20.0),
                    collectionMethod = InvoiceCollectionMethod.SendInvoice,
                    product = InvoiceProductInput("VIP"),
                    billingAddress =
                        CreateInvoiceRequest.billingAddress(
                            InvoiceBillingAddress(line1 = CreateInvoiceRequest.text("1 Main")),
                        ),
                    mailingAddressId = CreateInvoiceRequest.text("ma_123"),
                )
            }
            assertFailsWith<IllegalArgumentException> { UpdateInvoiceRequest(customerName = JsonPrimitive("")) }
            assertFailsWith<IllegalArgumentException> { UpdateInvoiceRequest(chargeBuyerFee = JsonPrimitive("true")) }
            assertFailsWith<IllegalArgumentException> { whop.invoices.retrieve("inv_123/other") }
            assertFailsWith<IllegalArgumentException> { whop.invoices.markPaid("inv_123%2Fother") }

            CreateInvoiceRequest(
                companyId = "biz_123",
                plan = InvoicePlanRequest(initialPrice = 20.0),
                collectionMethod = InvoiceCollectionMethod.SendInvoice,
                product = InvoiceProductInput("VIP"),
                saveAsDraft = CreateInvoiceRequest.bool(true),
            )
        }

    private fun createInvoiceRequest(): CreateInvoiceRequest =
        CreateInvoiceRequest(
            companyId = "biz_123",
            plan = InvoicePlanRequest(initialPrice = 20.0),
            collectionMethod = InvoiceCollectionMethod.SendInvoice,
            product = InvoiceProductInput("VIP", productTaxCodeId = CreateInvoiceRequest.clear),
            customerName = CreateInvoiceRequest.text("Jane Buyer"),
            emailAddress = CreateInvoiceRequest.text("jane@example.com"),
            dueDate = CreateInvoiceRequest.text("2024-02-01T00:00:00Z"),
            billingAddress = CreateInvoiceRequest.billingAddress(InvoiceBillingAddress(line1 = CreateInvoiceRequest.text("1 Main"))),
            lineItems = listOf(InvoiceLineItem("VIP", quantity = 1.0, unitPrice = 20.0)),
            saveAsDraft = CreateInvoiceRequest.bool(false),
        )

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
            path == "/api/v1/invoices" && method == "GET" -> invoicePageJson(after)
            path == "/api/v1/invoices" && method == "POST" -> invoiceJson("inv_123")
            path == "/api/v1/invoices/inv_123" && method == "GET" -> invoiceJson("inv_123")
            path == "/api/v1/invoices/inv_123" && method == "PATCH" -> invoiceJson("inv_123")
            path == "/api/v1/invoices/inv_123" && method == "DELETE" -> "true"
            path.startsWith("/api/v1/invoices/inv_123/") && method == "POST" -> "true"
            else -> "{}"
        }

    private fun invoicePageJson(after: String?): String =
        if (after == null || after == "cursor_0") {
            pageJson(invoiceJson("inv_123"), true, "inv_cursor")
        } else {
            pageJson(invoiceJson("inv_456"), false, null)
        }

    private fun pageJson(
        item: String,
        hasNext: Boolean,
        cursor: String?,
    ): String =
        """
        {"data":[$item],"page_info":{"has_next_page":$hasNext,"end_cursor":${cursor?.let { "\"$it\"" } ?: "null"}}}
        """.trimIndent()

    private fun invoiceJson(id: String): String =
        """
        {
          "id":"$id",
          "created_at":"2023-12-01T05:00:00.401Z",
          "status":"open",
          "number":"#0001",
          "due_date":"2024-02-01T00:00:00Z",
          "email_address":"jane@example.com",
          "fetch_invoice_token":"token_123",
          "current_plan":{"id":"plan_123","formatted_price":"${'$'}20.00","currency":"usd"},
          "user":{"id":"user_123","name":null,"username":"jane"}
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
