package dev.lepshee.whop.models.feemarkups

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.doubleOrNull

@Serializable
data class FeeMarkupListItem(
    val id: String,
    @SerialName("fee_type") val feeType: FeeMarkupType,
    @SerialName("percentage_fee") val percentageFee: Double? = null,
    @SerialName("fixed_fee_usd") val fixedFeeUsd: Double? = null,
    val notes: String? = null,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
)

@Serializable
data class FeeMarkup(
    val id: String,
    @SerialName("fee_type") val feeType: FeeMarkupType,
    @SerialName("percentage_fee") val percentageFee: Double? = null,
    @SerialName("fixed_fee_usd") val fixedFeeUsd: Double? = null,
    val notes: String? = null,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
)

@Serializable
data class CreateFeeMarkupRequest(
    @SerialName("company_id") val companyId: String,
    @SerialName("fee_type") val feeType: FeeMarkupType,
    @SerialName("fixed_fee_usd") val fixedFeeUsd: JsonElement? = null,
    @SerialName("percentage_fee") val percentageFee: JsonElement? = null,
    val metadata: JsonElement? = null,
    val notes: JsonElement? = null,
) {
    init {
        require(companyId.isNotBlank()) { "Company ID must not be blank." }
        requireOptionalNumber(fixedFeeUsd, "Fixed fee USD", 0.0, 50.0)
        requireOptionalNumber(percentageFee, "Percentage fee", 0.0, 25.0)
        requireOptionalMetadata(metadata)
        requireOptionalText(notes, "Notes")
    }

    companion object {
        fun amount(value: Double): JsonPrimitive {
            require(value.isFinite()) { "Fee markup amount must be finite." }
            return JsonPrimitive(value)
        }

        fun notes(value: String): JsonPrimitive {
            require(value.isNotBlank()) { "Fee markup notes must not be blank." }
            return JsonPrimitive(value)
        }

        val clear: JsonNull = JsonNull
    }
}

@Serializable
enum class FeeMarkupType {
    @SerialName("crypto_withdrawal_markup")
    CryptoWithdrawalMarkup,

    @SerialName("rtp_withdrawal_markup")
    RtpWithdrawalMarkup,

    @SerialName("next_day_bank_withdrawal_markup")
    NextDayBankWithdrawalMarkup,

    @SerialName("bank_wire_withdrawal_markup")
    BankWireWithdrawalMarkup,

    @SerialName("digital_wallet_withdrawal_markup")
    DigitalWalletWithdrawalMarkup,
}

private fun requireOptionalNumber(
    value: JsonElement?,
    fieldName: String,
    minimum: Double,
    maximum: Double,
) {
    if (value == null || value == JsonNull) return
    val primitive = value as? JsonPrimitive
    val number = primitive?.takeUnless { it.isString }?.doubleOrNull
    require(number != null && number.isFinite() && number >= minimum && number <= maximum) {
        "$fieldName must be a finite number between $minimum and $maximum."
    }
}

private fun requireOptionalMetadata(value: JsonElement?) {
    require(value == null || value == JsonNull || value is JsonObject) { "Metadata must be a JSON object." }
}

private fun requireOptionalText(
    value: JsonElement?,
    fieldName: String,
) {
    if (value == null || value == JsonNull) return
    require(value is JsonPrimitive && value.isString && value.content.isNotBlank()) { "$fieldName must not be blank." }
}
