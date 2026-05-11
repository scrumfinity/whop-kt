package dev.lepshee.whop.models.cards

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CardTransaction(
    val id: String,
    @SerialName("usd_amount") val usdAmount: Double? = null,
    @SerialName("authorization_method") val authorizationMethod: String? = null,
    @SerialName("created_at") val createdAt: String,
    @SerialName("card_id") val cardId: String,
    @SerialName("cashback_usd_amount") val cashbackUsdAmount: Double? = null,
    val currency: String? = null,
    @SerialName("declined_reason") val declinedReason: String? = null,
    val international: Boolean,
    @SerialName("local_amount") val localAmount: Double? = null,
    val memo: String? = null,
    @SerialName("merchant_category") val merchantCategory: String? = null,
    @SerialName("merchant_category_code") val merchantCategoryCode: String? = null,
    @SerialName("merchant_icon_url") val merchantIconUrl: String? = null,
    @SerialName("merchant_name") val merchantName: String? = null,
    @SerialName("posted_at") val postedAt: String? = null,
    val status: dev.lepshee.whop.models.cards.CardTransactionStatus,
    @SerialName("transaction_type") val transactionType: String,
)

@Serializable
enum class CardTransactionStatus {
    @SerialName("pending")
    Pending,

    @SerialName("completed")
    Completed,

    @SerialName("reversed")
    Reversed,

    @SerialName("declined")
    Declined,
}
