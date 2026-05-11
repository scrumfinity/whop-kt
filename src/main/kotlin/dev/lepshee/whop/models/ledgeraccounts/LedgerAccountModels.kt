package dev.lepshee.whop.models.ledgeraccounts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class LedgerAccount(
    val id: String,
    val balances: List<LedgerAccountBalance>,
    @SerialName("transfer_fee") val transferFee: Double? = null,
    @SerialName("payout_account_details") val payoutAccountDetails: JsonObject? = null,
    @SerialName("payments_approval_status") val paymentsApprovalStatus: String? = null,
    @SerialName("ledger_type") val ledgerType: LedgerType,
    val owner: JsonObject,
)

@Serializable
data class LedgerAccountBalance(
    val balance: Double,
    val currency: String,
    @SerialName("pending_balance") val pendingBalance: Double,
    @SerialName("reserve_balance") val reserveBalance: Double,
)

@Serializable
enum class LedgerType {
    @SerialName("primary")
    Primary,

    @SerialName("pool")
    Pool,
}
