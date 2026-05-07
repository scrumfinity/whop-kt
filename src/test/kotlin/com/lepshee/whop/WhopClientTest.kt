package com.lepshee.whop

import com.lepshee.whop.core.WhopApiException
import com.lepshee.whop.models.checkout.CheckoutPlan
import com.lepshee.whop.models.checkout.CreateCheckoutConfigurationRequest
import com.lepshee.whop.models.checkout.PlanType
import com.lepshee.whop.models.common.Direction
import com.lepshee.whop.models.common.Visibility
import com.lepshee.whop.models.common.VisibilityFilter
import com.lepshee.whop.models.plans.CheckoutShape
import com.lepshee.whop.models.plans.CheckoutStyling
import com.lepshee.whop.models.plans.CreatePlanRequest
import com.lepshee.whop.models.plans.PlanOrder
import com.lepshee.whop.models.plans.UpdatePlanRequest
import com.lepshee.whop.models.products.CreateProductRequest
import com.lepshee.whop.models.products.CustomCta
import com.lepshee.whop.models.products.ProductOrder
import com.lepshee.whop.models.products.ProductPlanOptions
import com.lepshee.whop.models.products.ProductPlanType
import com.lepshee.whop.models.products.ProductType
import com.lepshee.whop.models.products.ReleaseMethod
import com.lepshee.whop.models.products.StorePageConfig
import com.lepshee.whop.models.products.UpdateProductRequest
import com.lepshee.whop.resources.ListCheckoutConfigurationsRequest
import com.lepshee.whop.resources.ListPlansRequest
import com.lepshee.whop.resources.ListProductsRequest
import com.lepshee.whop.transport.KtorWhopHttpTransport
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondError
import io.ktor.client.request.HttpRequestData
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.TextContent
import io.ktor.http.headersOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.JsonPrimitive
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class WhopClientTest {
    @Test
    fun `create checkout configuration sends auth idempotency and json body`() =
        runTest {
            val capturedRequests = mutableListOf<HttpRequestData>()
            val engine =
                MockEngine { request ->
                    capturedRequests += request
                    respond(
                        content =
                            """
                            {"id":"chc_123","company_id":"biz_123","purchase_url":"https://whop.com/checkout/chc_123","ignored":"value"}
                            """.trimIndent(),
                        status = HttpStatusCode.OK,
                        headers = headersOf(HttpHeaders.ContentType, "application/json"),
                    )
                }
            val whop = clientWithEngine(engine)

            val response =
                whop.checkoutConfigurations.create(
                    CreateCheckoutConfigurationRequest(
                        plan =
                            CheckoutPlan(
                                companyId = "biz_123",
                                currency = "usd",
                                planType = PlanType.OneTime,
                                initialPrice = 20.0,
                            ),
                        metadata = mapOf("order_id" to JsonPrimitive("order_123")),
                    ),
                    options = WhopRequestOptions(idempotencyKey = "order_123_checkout"),
                )

            assertEquals("chc_123", response.id)
            assertEquals("biz_123", response.companyId)
            assertEquals("https://whop.com/checkout/chc_123", response.purchaseUrl)
            assertEquals("/api/v1/checkout_configurations", capturedRequests.single().url.encodedPath)
            assertEquals("Bearer test_api_key", capturedRequests.single().headers[HttpHeaders.Authorization])
            assertEquals("order_123_checkout", capturedRequests.single().headers["Idempotency-Key"])
            val body = capturedRequests.single().bodyText()
            assertTrue(body.contains("company_id"))
            assertTrue(body.contains("initial_price"))
            assertTrue(body.contains("one_time"))
        }

    @Test
    fun `renewal checkout plan serializes official OpenAPI enum value`() =
        runTest {
            val capturedRequests = mutableListOf<HttpRequestData>()
            val engine =
                MockEngine { request ->
                    capturedRequests += request
                    respond(
                        content = """{"id":"chc_123"}""",
                        headers = headersOf(HttpHeaders.ContentType, "application/json"),
                    )
                }
            val whop = clientWithEngine(engine)

            whop.checkoutConfigurations.create(
                CreateCheckoutConfigurationRequest(
                    plan =
                        CheckoutPlan(
                            companyId = "biz_123",
                            currency = "usd",
                            planType = PlanType.Renewal,
                            initialPrice = 9.99,
                        ),
                ),
            )

            assertTrue(capturedRequests.single().bodyText().contains("renewal"))
        }

    @Test
    fun `api errors preserve status request id and response body`() =
        runTest {
            val engine =
                MockEngine {
                    respondError(
                        status = HttpStatusCode.Unauthorized,
                        content = """{"error":{"type":"authentication_error","message":"Invalid API key","code":"invalid_api_key"}}""",
                        headers = headersOf("x-request-id", "req_123"),
                    )
                }
            val whop = clientWithEngine(engine)

            val exception =
                assertFailsWith<WhopApiException> {
                    whop.checkoutConfigurations.retrieve("chc_123")
                }

            assertEquals(401, exception.statusCode)
            assertEquals("req_123", exception.requestId)
            assertEquals("Invalid API key", exception.error.message)
            assertTrue(exception.responseBody.contains("invalid_api_key"))
        }

    @Test
    fun `api errors use body request id when header is absent`() =
        runTest {
            val engine =
                MockEngine {
                    respondError(
                        status = HttpStatusCode.BadRequest,
                        content = """{"request_id":"req_body_123","message":"Bad request","code":"bad_request"}""",
                    )
                }
            val whop = clientWithEngine(engine)

            val exception =
                assertFailsWith<WhopApiException> {
                    whop.checkoutConfigurations.retrieve("chc_123")
                }

            assertEquals("req_body_123", exception.requestId)
            assertEquals("Bad request", exception.error.message)
        }

    @Test
    fun `get requests retry retryable server errors`() =
        runTest {
            var attempts = 0
            val engine =
                MockEngine {
                    attempts += 1
                    if (attempts == 1) {
                        respondError(HttpStatusCode.InternalServerError, content = "{}")
                    } else {
                        respond(
                            content = """{"id":"chc_123"}""",
                            headers = headersOf(HttpHeaders.ContentType, "application/json"),
                        )
                    }
                }
            val whop = clientWithEngine(engine)

            val checkout = whop.checkoutConfigurations.retrieve("chc_123")

            assertEquals("chc_123", checkout.id)
            assertEquals(2, attempts)
        }

    @Test
    fun `idempotent mutating requests retry retryable server errors`() =
        runTest {
            var attempts = 0
            val engine =
                MockEngine {
                    attempts += 1
                    if (attempts == 1) {
                        respondError(HttpStatusCode.TooManyRequests, content = "{}")
                    } else {
                        respond(
                            content = """{"id":"chc_123"}""",
                            headers = headersOf(HttpHeaders.ContentType, "application/json"),
                        )
                    }
                }
            val whop = clientWithEngine(engine)

            val checkout =
                whop.checkoutConfigurations.create(
                    CreateCheckoutConfigurationRequest(
                        plan =
                            CheckoutPlan(
                                companyId = "biz_123",
                                currency = "usd",
                                planType = PlanType.OneTime,
                                initialPrice = 20.0,
                            ),
                    ),
                    options = WhopRequestOptions(idempotencyKey = "checkout_123"),
                )

            assertEquals("chc_123", checkout.id)
            assertEquals(2, attempts)
        }

    @Test
    fun `non idempotent mutating requests do not retry retryable server errors`() =
        runTest {
            var attempts = 0
            val engine =
                MockEngine {
                    attempts += 1
                    respondError(HttpStatusCode.InternalServerError, content = "{}")
                }
            val whop = clientWithEngine(engine)

            assertFailsWith<WhopApiException> {
                whop.checkoutConfigurations.create(
                    CreateCheckoutConfigurationRequest(
                        plan =
                            CheckoutPlan(
                                companyId = "biz_123",
                                currency = "usd",
                                planType = PlanType.OneTime,
                                initialPrice = 20.0,
                            ),
                    ),
                )
            }

            assertEquals(1, attempts)
        }

    @Test
    fun `list auto paging follows cursors until no next page`() =
        runTest {
            val engine =
                MockEngine { request ->
                    if (request.url.parameters["after"] == null) {
                        respond(
                            content =
                                """
                                {"data":[{"id":"chc_1"}],"page_info":{"has_next_page":true,"end_cursor":"cursor_1"}}
                                """.trimIndent(),
                            headers = headersOf(HttpHeaders.ContentType, "application/json"),
                        )
                    } else {
                        respond(
                            content =
                                """
                                {"data":[{"id":"chc_2"}],"page_info":{"has_next_page":false,"end_cursor":"cursor_2"}}
                                """.trimIndent(),
                            headers = headersOf(HttpHeaders.ContentType, "application/json"),
                        )
                    }
                }
            val whop = clientWithEngine(engine)

            val ids =
                whop.checkoutConfigurations
                    .listAutoPaging(ListCheckoutConfigurationsRequest(first = 1))
                    .toList()
                    .map { it.id }

            assertEquals(listOf("chc_1", "chc_2"), ids)
        }

    @Test
    fun `create product sends official snake case payload`() =
        runTest {
            val capturedRequests = mutableListOf<HttpRequestData>()
            val engine =
                MockEngine { request ->
                    capturedRequests += request
                    respond(
                        content = """{"id":"prod_123","title":"VIP","visibility":"visible"}""",
                        headers = headersOf(HttpHeaders.ContentType, "application/json"),
                    )
                }
            val whop = clientWithEngine(engine)

            val product =
                whop.products.create(
                    CreateProductRequest(
                        companyId = "biz_123",
                        title = "VIP",
                        visibility = Visibility.Visible,
                        customCta = CustomCta.GetAccess,
                        planOptions =
                            ProductPlanOptions(
                                baseCurrency = "usd",
                                initialPrice = 20.0,
                                planType = ProductPlanType.OneTime,
                                releaseMethod = ReleaseMethod.BuyNow,
                            ),
                    ),
                )

            assertEquals("prod_123", product.id)
            assertEquals("/api/v1/products", capturedRequests.single().url.encodedPath)
            val body = capturedRequests.single().bodyText()
            assertTrue(body.contains("company_id"))
            assertTrue(body.contains("custom_cta"))
            assertTrue(body.contains("get_access"))
            assertTrue(body.contains("plan_options"))
            assertTrue(body.contains("base_currency"))
        }

    @Test
    fun `update product uses patch path and store page config payload`() =
        runTest {
            val capturedRequests = mutableListOf<HttpRequestData>()
            val engine =
                MockEngine { request ->
                    capturedRequests += request
                    respond(
                        content = """{"id":"prod_123","title":"VIP Plus"}""",
                        headers = headersOf(HttpHeaders.ContentType, "application/json"),
                    )
                }
            val whop = clientWithEngine(engine)

            val product =
                whop.products.update(
                    "prod_123",
                    UpdateProductRequest(
                        title = "VIP Plus",
                        storePageConfig = StorePageConfig(customCta = "Subscribe now", showPrice = true),
                    ),
                )

            assertEquals("prod_123", product.id)
            assertEquals("PATCH", capturedRequests.single().method.value)
            assertEquals("/api/v1/products/prod_123", capturedRequests.single().url.encodedPath)
            val body = capturedRequests.single().bodyText()
            assertTrue(body.contains("store_page_config"))
            assertTrue(body.contains("show_price"))
            assertTrue(body.contains("Subscribe now"))
            assertTrue(!body.contains("metadata"))
        }

    @Test
    fun `list products sends required company and enum filters`() =
        runTest {
            val capturedRequests = mutableListOf<HttpRequestData>()
            val engine =
                MockEngine { request ->
                    capturedRequests += request
                    respond(
                        content = """{"data":[{"id":"prod_123","title":"VIP"}],"page_info":{"has_next_page":false}}""",
                        headers = headersOf(HttpHeaders.ContentType, "application/json"),
                    )
                }
            val whop = clientWithEngine(engine)

            val page =
                whop.products.list(
                    ListProductsRequest(
                        companyId = "biz_123",
                        productTypes = listOf(ProductType.Regular, ProductType.ApiOnly),
                        visibilities = listOf(VisibilityFilter.NotArchived, VisibilityFilter.Visible),
                        order = ProductOrder.CreatedAt,
                        direction = Direction.Desc,
                        first = 10,
                    ),
                )

            assertEquals("prod_123", page.data.single().id)
            val parameters = capturedRequests.single().url.parameters
            assertEquals("biz_123", parameters["company_id"])
            assertEquals("created_at", parameters["order"])
            assertEquals("desc", parameters["direction"])
            assertEquals(listOf("regular", "api_only"), parameters.getAll("product_types"))
            assertEquals(listOf("not_archived", "visible"), parameters.getAll("visibilities"))
            assertTrue(
                capturedRequests
                    .single()
                    .url.encodedQuery
                    .contains("product_types=regular&product_types=api_only"),
            )
        }

    @Test
    fun `create plan sends required product company and styling payload`() =
        runTest {
            val capturedRequests = mutableListOf<HttpRequestData>()
            val engine =
                MockEngine { request ->
                    capturedRequests += request
                    respond(
                        content = """{"id":"plan_123","plan_type":"renewal","purchase_url":"https://whop.com/checkout/plan_123"}""",
                        headers = headersOf(HttpHeaders.ContentType, "application/json"),
                    )
                }
            val whop = clientWithEngine(engine)

            val plan =
                whop.plans.create(
                    CreatePlanRequest(
                        companyId = "biz_123",
                        productId = "prod_123",
                        currency = "usd",
                        planType = com.lepshee.whop.models.plans.PlanType.Renewal,
                        releaseMethod = ReleaseMethod.BuyNow,
                        renewalPrice = 29.0,
                        checkoutStyling = CheckoutStyling(borderStyle = CheckoutShape.Pill),
                    ),
                    options = WhopRequestOptions(idempotencyKey = "plan_create_123"),
                )

            assertEquals("plan_123", plan.id)
            assertEquals("https://whop.com/checkout/plan_123", plan.purchaseUrl)
            assertEquals("/api/v1/plans", capturedRequests.single().url.encodedPath)
            assertEquals("plan_create_123", capturedRequests.single().headers["Idempotency-Key"])
            val body = capturedRequests.single().bodyText()
            assertTrue(body.contains("company_id"))
            assertTrue(body.contains("product_id"))
            assertTrue(body.contains("renewal_price"))
            assertTrue(body.contains("checkout_styling"))
            assertTrue(body.contains("pill"))
            assertTrue(!body.contains("metadata"))
        }

    @Test
    fun `update plan and list plan use official paths and filters`() =
        runTest {
            val capturedRequests = mutableListOf<HttpRequestData>()
            val engine =
                MockEngine { request ->
                    capturedRequests += request
                    if (request.method.value == "PATCH") {
                        respond(
                            content = """{"id":"plan_123","title":"Monthly"}""",
                            headers = headersOf(HttpHeaders.ContentType, "application/json"),
                        )
                    } else {
                        respond(
                            content = """{"data":[{"id":"plan_123","title":"Monthly"}],"page_info":{"has_next_page":false}}""",
                            headers = headersOf(HttpHeaders.ContentType, "application/json"),
                        )
                    }
                }
            val whop = clientWithEngine(engine)

            whop.plans.update("plan_123", UpdatePlanRequest(title = "Monthly", strikeThroughRenewalPrice = 49.0))
            val page =
                whop.plans.list(
                    ListPlansRequest(
                        companyId = "biz_123",
                        planTypes = listOf(com.lepshee.whop.models.plans.PlanType.Renewal, com.lepshee.whop.models.plans.PlanType.OneTime),
                        releaseMethods = listOf(ReleaseMethod.BuyNow, ReleaseMethod.Waitlist),
                        productIds = listOf("prod_123", "prod_456"),
                        order = PlanOrder.CreatedAt,
                        direction = Direction.Asc,
                    ),
                )

            assertEquals("PATCH", capturedRequests.first().method.value)
            assertEquals("/api/v1/plans/plan_123", capturedRequests.first().url.encodedPath)
            assertTrue(capturedRequests.first().bodyText().contains("strike_through_renewal_price"))
            assertEquals("plan_123", page.data.single().id)
            val parameters = capturedRequests.last().url.parameters
            assertEquals("biz_123", parameters["company_id"])
            assertEquals("created_at", parameters["order"])
            assertEquals("asc", parameters["direction"])
            assertEquals(listOf("renewal", "one_time"), parameters.getAll("plan_types"))
            assertEquals(listOf("buy_now", "waitlist"), parameters.getAll("release_methods"))
            assertEquals(listOf("prod_123", "prod_456"), parameters.getAll("product_ids"))
            assertTrue(
                capturedRequests
                    .last()
                    .url.encodedQuery
                    .contains("plan_types=renewal&plan_types=one_time"),
            )
        }

    private fun clientWithEngine(engine: MockEngine): WhopClient {
        val config = WhopClientConfig(apiKey = "test_api_key")
        val httpClient =
            HttpClient(engine) {
                expectSuccess = false
            }
        return WhopClient(config, KtorWhopHttpTransport(config, httpClient))
    }

    private fun HttpRequestData.bodyText(): String = (body as TextContent).text
}
