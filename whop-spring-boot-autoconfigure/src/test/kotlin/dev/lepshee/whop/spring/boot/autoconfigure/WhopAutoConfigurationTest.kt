package dev.lepshee.whop.spring.boot.autoconfigure

import dev.lepshee.whop.WhopClient
import dev.lepshee.whop.WhopClientConfig
import dev.lepshee.whop.core.WhopHttpRequest
import dev.lepshee.whop.core.WhopHttpResponse
import dev.lepshee.whop.core.WhopHttpTransport
import dev.lepshee.whop.spring.boot.autoconfigure.WhopAutoConfiguration
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.test.context.runner.ApplicationContextRunner
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertSame

class WhopAutoConfigurationTest {
    private val contextRunner = ApplicationContextRunner()
        .withConfiguration(AutoConfigurations.of(WhopAutoConfiguration::class.java))

    @Test
    fun `does not create client without api key`() {
        contextRunner.run { context ->
            assertFalse(context.containsBean("whopClient"))
        }
    }

    @Test
    fun `creates client from properties`() {
        contextRunner
            .withPropertyValues(
                "whop.api-key=test_api_key",
                "whop.base-url=https://example.com/api/v1",
                "whop.timeout=5s",
                "whop.max-retries=3",
            ).run { context ->
                val config = context.getBean(WhopClientConfig::class.java)
                val client = context.getBean(WhopClient::class.java)

                assertEquals("test_api_key", config.apiKey)
                assertEquals("https://example.com/api/v1", config.baseUrl)
                assertEquals(5_000, config.timeoutMillis)
                assertSame(config, client.config)
            }
    }

    @Test
    fun `user defined client wins`() {
        val customClient = WhopClient(WhopClientConfig(apiKey = "custom"), NoopTransport)

        contextRunner
            .withBean(WhopClient::class.java, { customClient })
            .withPropertyValues("whop.api-key=test_api_key")
            .run { context ->
                assertSame(customClient, context.getBean(WhopClient::class.java))
            }
    }

    private object NoopTransport : WhopHttpTransport {
        override suspend fun execute(request: WhopHttpRequest): WhopHttpResponse = WhopHttpResponse(200, body = "{}")

        override fun close() = Unit
    }
}
