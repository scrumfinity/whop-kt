package com.lepshee.whop

import com.lepshee.whop.resources.ListProductsRequest
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertTrue

class WhopIntegrationTest {
    @Test
    fun `list products against Whop API`() =
        runTest {
            val apiKey = requiredEnvironment("WHOP_API_KEY")
            val companyId = requiredEnvironment("WHOP_COMPANY_ID")

            WhopClient(WhopClientConfig(apiKey = apiKey)).use { whop ->
                val page = whop.products.list(ListProductsRequest(companyId = companyId, first = 1))

                assertTrue(page.data.size <= 1)
            }
        }

    private fun requiredEnvironment(name: String): String =
        System.getenv(name)
            ?: error("Environment variable $name is required when WHOP_INTEGRATION_TESTS=true.")
}
