package com.lepshee.whop

import com.lepshee.whop.blocking.WhopBlockingClient
import com.lepshee.whop.blocking.blocking
import com.lepshee.whop.transport.KtorWhopHttpTransport
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.headersOf
import kotlin.test.Test
import kotlin.test.assertEquals

class WhopBlockingClientTest {
    @Test
    fun `blocking facade delegates to coroutine client`() {
        val blocking = WhopBlockingClient(config(), transportWithPayment())

        val payment = blocking.retrievePayment("pay_123")

        assertEquals("pay_123", payment.id)
    }

    @Test
    fun `coroutine client can create blocking facade`() {
        val client = WhopClient(config(), transportWithPayment())

        val payment = client.blocking().retrievePayment("pay_123")

        assertEquals("pay_123", payment.id)
    }

    private fun config(): WhopClientConfig = WhopClientConfig(apiKey = "test_api_key")

    private fun transportWithPayment(): KtorWhopHttpTransport {
        val config = config()
        val client =
            HttpClient(
                MockEngine {
                    respond(
                        content = """{"id":"pay_123","status":"paid"}""",
                        headers = headersOf(HttpHeaders.ContentType, "application/json"),
                    )
                },
            ) { expectSuccess = false }
        return KtorWhopHttpTransport(config, client)
    }
}
