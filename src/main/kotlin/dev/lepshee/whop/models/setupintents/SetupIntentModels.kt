package dev.lepshee.whop.models.setupintents

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class SetupIntent(
    val id: String,
    val status: SetupIntentStatus,
    @SerialName("created_at") val createdAt: String,
    @SerialName("error_message") val errorMessage: String? = null,
    val company: JsonObject? = null,
    @SerialName("checkout_configuration") val checkoutConfiguration: JsonObject? = null,
    val member: JsonObject? = null,
    @SerialName("payment_method") val paymentMethod: JsonObject? = null,
    val metadata: JsonObject? = null,
)

@Serializable
enum class SetupIntentStatus {
    @SerialName("processing")
    Processing,

    @SerialName("succeeded")
    Succeeded,

    @SerialName("canceled")
    Canceled,

    @SerialName("requires_action")
    RequiresAction,
}
