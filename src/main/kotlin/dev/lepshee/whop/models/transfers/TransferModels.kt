package dev.lepshee.whop.models.transfers

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

/** Transfer object returned by Whop. */
@Serializable
data class Transfer(
    val id: String,
    val amount: Double? = null,
    val currency: String? = null,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("fee_amount") val feeAmount: Double? = null,
    val notes: String? = null,
    val metadata: JsonObject? = null,
    @SerialName("origin_ledger_account_id") val originLedgerAccountId: String? = null,
    @SerialName("destination_ledger_account_id") val destinationLedgerAccountId: String? = null,
    val origin: JsonObject? = null,
    val destination: JsonObject? = null,
)

@Serializable
data class CreateTransferRequest(
    val amount: Double,
    val currency: String,
    @SerialName("destination_id") val destinationId: String,
    @SerialName("origin_id") val originId: String,
    @SerialName("idempotence_key") val idempotenceKey: String? = null,
    val metadata: JsonObject? = null,
    val notes: String? = null,
) {
    init {
        require(amount > 0.0) { "Transfer amount must be positive." }
        require(currency.isNotBlank()) { "Currency must not be blank." }
        require(destinationId.isNotBlank()) { "Destination ID must not be blank." }
        require(originId.isNotBlank()) { "Origin ID must not be blank." }
        require(idempotenceKey == null || idempotenceKey.isNotBlank()) { "Idempotence key must not be blank." }
        require(notes == null || notes.length <= 50) { "Transfer notes must be 50 characters or fewer." }
    }
}

@Serializable
enum class TransferOrder {
    @SerialName("amount")
    Amount,

    @SerialName("created_at")
    CreatedAt,
}
