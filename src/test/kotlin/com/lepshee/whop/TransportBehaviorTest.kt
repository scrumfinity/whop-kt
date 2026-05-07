package com.lepshee.whop

import com.lepshee.whop.core.WhopConnectionException
import com.lepshee.whop.core.WhopHttpRequest
import com.lepshee.whop.core.WhopHttpResponse
import com.lepshee.whop.core.WhopHttpTransport
import com.lepshee.whop.models.checkout.CheckoutPlan
import com.lepshee.whop.models.checkout.CreateCheckoutConfigurationRequest
import com.lepshee.whop.models.checkout.PlanType
import kotlinx.coroutines.test.runTest
import java.io.IOException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class TransportBehaviorTest {
    @Test
    fun `request timeout option reaches transport`() =
        runTest {
            val transport = RecordingTransport(WhopHttpResponse(200, body = "{\"id\":\"chc_123\"}"))
            val whop = WhopClient(WhopClientConfig(apiKey = "test_api_key"), transport)

            whop.checkoutConfigurations.retrieve("chc_123", WhopRequestOptions(timeoutMillis = 12_345))

            assertEquals(12_345, transport.requests.single().timeoutMillis)
        }

    @Test
    fun `get connection failures retry within configured budget`() =
        runTest {
            val transport = FlakyTransport(failuresBeforeSuccess = 1)
            val whop = WhopClient(WhopClientConfig(apiKey = "test_api_key", maxRetries = 2), transport)

            val checkout = whop.checkoutConfigurations.retrieve("chc_123")

            assertEquals("chc_123", checkout.id)
            assertEquals(2, transport.attempts)
        }

    @Test
    fun `non idempotent mutation connection failures do not retry`() =
        runTest {
            val transport = FlakyTransport(failuresBeforeSuccess = 1)
            val whop = WhopClient(WhopClientConfig(apiKey = "test_api_key", maxRetries = 2), transport)

            assertFailsWith<WhopConnectionException> {
                whop.checkoutConfigurations.create(
                    CreateCheckoutConfigurationRequest(
                        CheckoutPlan("biz_123", "usd", PlanType.OneTime, 20.0),
                    ),
                )
            }
            assertEquals(1, transport.attempts)
        }

    private class RecordingTransport(
        private val response: WhopHttpResponse,
    ) : WhopHttpTransport {
        val requests = mutableListOf<WhopHttpRequest>()

        override suspend fun execute(request: WhopHttpRequest): WhopHttpResponse {
            requests += request
            return response
        }

        override fun close() = Unit
    }

    private class FlakyTransport(
        private val failuresBeforeSuccess: Int,
    ) : WhopHttpTransport {
        var attempts = 0

        override suspend fun execute(request: WhopHttpRequest): WhopHttpResponse {
            attempts += 1
            if (attempts <= failuresBeforeSuccess) throw IOException("network failed")
            return WhopHttpResponse(200, body = "{\"id\":\"chc_123\"}")
        }

        override fun close() = Unit
    }
}
