package dev.lepshee.whop.models.leads

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class Lead(
    val id: String,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
    val referrer: String? = null,
    val metadata: JsonObject? = null,
    val user: JsonObject,
    val product: JsonObject? = null,
    val member: JsonObject? = null,
)

@Serializable
data class CreateLeadRequest(
    @SerialName("company_id") val companyId: String,
    val metadata: JsonObject? = null,
    @SerialName("product_id") val productId: String? = null,
    val referrer: String? = null,
    @SerialName("user_id") val userId: String? = null,
) {
    init {
        require(companyId.isNotBlank()) { "Company ID must not be blank." }
        require(productId == null || productId.isNotBlank()) { "Product ID must not be blank." }
        require(referrer == null || referrer.isNotBlank()) { "Referrer must not be blank." }
        require(userId == null || userId.isNotBlank()) { "User ID must not be blank." }
    }
}

@Serializable
data class UpdateLeadRequest(
    val metadata: JsonObject? = null,
    val referrer: String? = null,
) {
    init {
        require(referrer == null || referrer.isNotBlank()) { "Referrer must not be blank." }
    }
}
