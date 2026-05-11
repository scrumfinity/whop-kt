package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.ledgeraccounts.LedgerAccount

/** Operations for Whop ledger accounts. */
class LedgerAccountsResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun retrieve(
        ledgerAccountId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): LedgerAccount {
        val id = pathParameter(ledgerAccountId, "Ledger account ID")
        return http.get("ledger_accounts/$id", options = options)
    }
}
