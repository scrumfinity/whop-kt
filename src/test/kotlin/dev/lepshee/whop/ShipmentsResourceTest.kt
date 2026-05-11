package dev.lepshee.whop

import dev.lepshee.whop.models.shipments.CreateShipmentRequest
import dev.lepshee.whop.models.shipments.ShipmentStatus
import dev.lepshee.whop.models.shipments.ShipmentSubstatus
import dev.lepshee.whop.resources.ListShipmentsRequest
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

class ShipmentsResourceTest {
    @Test
    fun `shipment endpoints send documented paths filters body and decode`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val page =
                whop.shipments.list(
                    ListShipmentsRequest(
                        after = "cursor_0",
                        before = "cursor_before",
                        first = 10,
                        last = 20,
                        paymentId = "pay_123",
                        companyId = "biz_123",
                        userId = "user_123",
                    ),
                )
            val created =
                whop.shipments.create(
                    CreateShipmentRequest(
                        companyId = "biz_123",
                        paymentId = "pay_123",
                        trackingCode = "9400111899223456789012",
                    ),
                )
            val retrieved = whop.shipments.retrieve("ship_123")

            assertEquals("ship_123", page.data.single().id)
            assertEquals(ShipmentStatus.InTransit, page.data.single().status)
            assertEquals(ShipmentSubstatus.DepartedFacility, page.data.single().substatus)
            assertEquals("usps", page.data.single().carrier)
            assertEquals(
                "pay_123",
                page.data
                    .single()
                    .payment
                    ?.id,
            )
            assertEquals("/api/v1/shipments", requests[0].url.encodedPath)
            assertEquals("cursor_0", requests[0].url.parameters["after"])
            assertEquals("cursor_before", requests[0].url.parameters["before"])
            assertEquals("10", requests[0].url.parameters["first"])
            assertEquals("20", requests[0].url.parameters["last"])
            assertEquals("pay_123", requests[0].url.parameters["payment_id"])
            assertEquals("biz_123", requests[0].url.parameters["company_id"])
            assertEquals("user_123", requests[0].url.parameters["user_id"])
            assertEquals("POST", requests[1].method.value)
            assertEquals("/api/v1/shipments", requests[1].url.encodedPath)
            assertTrue(requests[1].bodyText().contains("company_id"))
            assertTrue(requests[1].bodyText().contains("payment_id"))
            assertTrue(requests[1].bodyText().contains("tracking_code"))
            assertEquals(ShipmentStatus.Delivered, created.status)
            assertEquals(null, created.substatus)
            assertEquals("/api/v1/shipments/ship_123", requests[2].url.encodedPath)
            assertEquals("ship_123", retrieved.id)
            assertEquals("Priority", retrieved.service)
        }

    @Test
    fun `shipments auto paging follows cursors`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            assertEquals(
                listOf("ship_123", "ship_456"),
                whop.shipments
                    .listAutoPaging(ListShipmentsRequest(companyId = "biz_123"))
                    .toList()
                    .map { it.id },
            )
            assertTrue(requests.any { it.url.parameters["after"] == "ship_cursor" })
        }

    @Test
    fun `shipment validation rejects invalid inputs and path parameters`() =
        runTest {
            val whop = clientWithEngine(recordingEngine(mutableListOf()))

            assertFailsWith<IllegalArgumentException> { ListShipmentsRequest(after = "") }
            assertFailsWith<IllegalArgumentException> { ListShipmentsRequest(first = 0) }
            assertFailsWith<IllegalArgumentException> { ListShipmentsRequest(last = -1) }
            assertFailsWith<IllegalArgumentException> { ListShipmentsRequest(paymentId = " ") }
            assertFailsWith<IllegalArgumentException> { ListShipmentsRequest(companyId = " ") }
            assertFailsWith<IllegalArgumentException> { ListShipmentsRequest(userId = " ") }
            assertFailsWith<IllegalArgumentException> { CreateShipmentRequest(" ", "pay_123", "track_123") }
            assertFailsWith<IllegalArgumentException> { CreateShipmentRequest("biz_123", " ", "track_123") }
            assertFailsWith<IllegalArgumentException> { CreateShipmentRequest("biz_123", "pay_123", " ") }
            assertFailsWith<IllegalArgumentException> { whop.shipments.retrieve("ship_123/other") }
            assertFailsWith<IllegalArgumentException> { whop.shipments.retrieve("ship_123%2Fother") }
        }

    private fun recordingEngine(requests: MutableList<HttpRequestData>): MockEngine =
        MockEngine { request ->
            requests += request
            respond(
                defaultJsonResponse(request.method.value, request.url.encodedPath, request.url.parameters["after"]),
                headers = jsonHeaders,
            )
        }

    private fun defaultJsonResponse(
        method: String,
        path: String,
        after: String?,
    ): String =
        when {
            method == "GET" && path == "/api/v1/shipments" -> shipmentPageJson(after)
            method == "POST" && path == "/api/v1/shipments" -> shipmentJson("ship_123", "delivered", null)
            path.startsWith("/api/v1/shipments/") -> shipmentJson("ship_123", "in_transit", "departed_facility")
            else -> "{}"
        }

    private fun shipmentPageJson(after: String?): String =
        if (after == null || after == "cursor_0") {
            pageJson(shipmentJson("ship_123", "in_transit", "departed_facility"), true, "ship_cursor")
        } else {
            pageJson(shipmentJson("ship_456", "delivered", null), false, null)
        }

    private fun pageJson(
        item: String,
        hasNext: Boolean,
        cursor: String?,
    ): String =
        """
        {"data":[$item],"page_info":{"has_next_page":$hasNext,"end_cursor":${cursor?.let { "\"$it\"" } ?: "null"}}}
        """.trimIndent()

    private fun shipmentJson(
        id: String,
        status: String,
        substatus: String?,
    ): String =
        """
        {
          "id":"$id",
          "created_at":"2023-12-01T05:00:00.401Z",
          "status":"$status",
          "substatus":${substatus?.let { "\"$it\"" } ?: "null"},
          "tracking_code":"9400111899223456789012",
          "updated_at":"2023-12-02T05:00:00.401Z",
          "carrier":"usps",
          "service":"Priority",
          "delivery_estimate":"2023-12-04T05:00:00.401Z",
          "payment":{"id":"pay_123"}
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
