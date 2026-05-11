package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.pathParameter
import kotlinx.serialization.json.JsonObject

/** Operations for payout accounts. */
class PayoutAccountsResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun retrieve(
        payoutAccountId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): JsonObject {
        val id = pathParameter(payoutAccountId, "Payout account ID")
        return http.get("payout_accounts/$id", options = options)
    }
}
