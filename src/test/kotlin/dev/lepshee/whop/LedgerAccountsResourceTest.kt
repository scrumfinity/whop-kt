package dev.lepshee.whop

import dev.lepshee.whop.models.ledgeraccounts.LedgerType
import dev.lepshee.whop.transport.KtorWhopHttpTransport
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.HttpRequestData
import io.ktor.http.HttpHeaders
import io.ktor.http.headersOf
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class LedgerAccountsResourceTest {
    @Test
    fun `ledger account retrieve sends documented path and decodes`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val ledgerAccount = whop.ledgerAccounts.retrieve("ldgr_123")

            assertEquals("ldgr_123", ledgerAccount.id)
            assertEquals(LedgerType.Primary, ledgerAccount.ledgerType)
            assertEquals("approved", ledgerAccount.paymentsApprovalStatus)
            assertEquals(25.0, ledgerAccount.transferFee)
            assertEquals("usd", ledgerAccount.balances.single().currency)
            assertEquals(100.0, ledgerAccount.balances.single().balance)
            assertEquals(
                "poact_123",
                ledgerAccount.payoutAccountDetails
                    ?.get("id")
                    ?.toString()
                    ?.trim('"'),
            )
            assertEquals("Company", ledgerAccount.owner["typename"]?.toString()?.trim('"'))
            assertEquals("/api/v1/ledger_accounts/ldgr_123", requests.single().url.encodedPath)
        }

    @Test
    fun `ledger account retrieve rejects unsafe path parameters`() =
        runTest {
            val whop = clientWithEngine(recordingEngine(mutableListOf()))

            assertFailsWith<IllegalArgumentException> { whop.ledgerAccounts.retrieve("ldgr_123/other") }
            assertFailsWith<IllegalArgumentException> { whop.ledgerAccounts.retrieve("ldgr_123%2Fother") }
        }

    private fun recordingEngine(requests: MutableList<HttpRequestData>): MockEngine =
        MockEngine { request ->
            requests += request
            respond(ledgerAccountJson(), headers = jsonHeaders)
        }

    private fun ledgerAccountJson(): String =
        """
        {
          "id":"ldgr_123",
          "balances":[{"balance":100.0,"currency":"usd","pending_balance":10.0,"reserve_balance":5.0}],
          "transfer_fee":25.0,
          "payout_account_details":{"id":"poact_123","status":"connected","business_name":"Acme"},
          "payments_approval_status":"approved",
          "ledger_type":"primary",
          "owner":{"typename":"Company","id":"biz_123","route":"acme","title":"Acme"}
        }
        """.trimIndent()

    private fun clientWithEngine(engine: MockEngine): WhopClient {
        val config = WhopClientConfig(apiKey = "test_api_key")
        val httpClient = HttpClient(engine) { expectSuccess = false }
        return WhopClient(config, KtorWhopHttpTransport(config, httpClient))
    }

    private companion object {
        val jsonHeaders = headersOf(HttpHeaders.ContentType, "application/json")
    }
}
