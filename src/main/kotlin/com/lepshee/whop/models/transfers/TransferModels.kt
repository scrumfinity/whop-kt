package com.lepshee.whop.models.transfers

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

/** Transfer object returned by Whop. */
@Serializable
data class Transfer(
    val id: String,
    val amount: Long? = null,
    val currency: String? = null,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("fee_amount") val feeAmount: Long? = null,
    val notes: String? = null,
    val metadata: JsonObject? = null,
    @SerialName("origin_ledger_account_id") val originLedgerAccountId: String? = null,
    @SerialName("destination_ledger_account_id") val destinationLedgerAccountId: String? = null,
    val origin: JsonObject? = null,
    val destination: JsonObject? = null,
)

@Serializable
data class CreateTransferRequest(
    val amount: Long,
    val currency: String,
    @SerialName("destination_id") val destinationId: String,
    @SerialName("origin_id") val originId: String,
    @SerialName("idempotence_key") val idempotenceKey: String? = null,
    val metadata: JsonObject? = null,
    val notes: String? = null,
)

@Serializable
enum class TransferOrder {
    @SerialName("amount")
    Amount,

    @SerialName("created_at")
    CreatedAt,
}
