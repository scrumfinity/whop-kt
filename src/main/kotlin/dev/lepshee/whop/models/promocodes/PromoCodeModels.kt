package dev.lepshee.whop.models.promocodes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Request body for `POST /promo_codes`. */
@Serializable
data class CreatePromoCodeRequest(
    @SerialName("amount_off") val amountOff: Double,
    @SerialName("base_currency") val baseCurrency: String,
    val code: String,
    @SerialName("company_id") val companyId: String,
    @SerialName("new_users_only") val newUsersOnly: Boolean,
    @SerialName("promo_duration_months") val promoDurationMonths: Int,
    @SerialName("promo_type") val promoType: PromoType,
    @SerialName("churned_users_only") val churnedUsersOnly: Boolean? = null,
    @SerialName("existing_memberships_only") val existingMembershipsOnly: Boolean? = null,
    @SerialName("expires_at") val expiresAt: String? = null,
    @SerialName("one_per_customer") val onePerCustomer: Boolean? = null,
    @SerialName("plan_ids") val planIds: List<String>? = null,
    @SerialName("product_id") val productId: String? = null,
    val stock: Int? = null,
    @SerialName("unlimited_stock") val unlimitedStock: Boolean? = null,
) {
    init {
        require(amountOff.isFinite() && amountOff > 0.0) { "Promo code amount off must be positive." }
        require(baseCurrency.isNotBlank()) { "Base currency must not be blank." }
        require(code.isNotBlank()) { "Promo code must not be blank." }
        require(companyId.isNotBlank()) { "Company ID must not be blank." }
        require(promoDurationMonths > 0) { "Promo duration months must be positive." }
        require(expiresAt == null || expiresAt.isNotBlank()) { "Expiration timestamp must not be blank." }
        require(planIds == null || planIds.all(String::isNotBlank)) { "Plan IDs must not be blank." }
        require(productId == null || productId.isNotBlank()) { "Product ID must not be blank." }
        require(stock == null || stock >= 0) { "Promo code stock must not be negative." }
    }
}

@Serializable
data class PromoCodeListItem(
    val id: String,
    @SerialName("amount_off") val amountOff: Double,
    val currency: String,
    @SerialName("churned_users_only") val churnedUsersOnly: Boolean,
    val code: String? = null,
    @SerialName("created_at") val createdAt: String,
    @SerialName("existing_memberships_only") val existingMembershipsOnly: Boolean,
    val duration: PromoDuration? = null,
    @SerialName("expires_at") val expiresAt: String? = null,
    @SerialName("new_users_only") val newUsersOnly: Boolean,
    @SerialName("promo_duration_months") val promoDurationMonths: Int? = null,
    @SerialName("one_per_customer") val onePerCustomer: Boolean,
    val product: PromoCodeProduct? = null,
    @SerialName("promo_type") val promoType: PromoType,
    val status: PromoCodeStatus,
    val stock: Int,
    @SerialName("unlimited_stock") val unlimitedStock: Boolean,
    val uses: Int,
)

@Serializable
data class PromoCode(
    val id: String,
    @SerialName("amount_off") val amountOff: Double,
    val currency: String,
    @SerialName("churned_users_only") val churnedUsersOnly: Boolean,
    val code: String? = null,
    @SerialName("created_at") val createdAt: String,
    @SerialName("existing_memberships_only") val existingMembershipsOnly: Boolean,
    val duration: PromoDuration? = null,
    @SerialName("expires_at") val expiresAt: String? = null,
    @SerialName("new_users_only") val newUsersOnly: Boolean,
    @SerialName("promo_duration_months") val promoDurationMonths: Int? = null,
    @SerialName("one_per_customer") val onePerCustomer: Boolean,
    val product: PromoCodeProduct? = null,
    @SerialName("promo_type") val promoType: PromoType,
    val status: PromoCodeStatus,
    val stock: Int,
    @SerialName("unlimited_stock") val unlimitedStock: Boolean,
    val uses: Int,
    val company: PromoCodeCompany,
)

@Serializable
data class PromoCodeProduct(
    val id: String,
    val title: String,
)

@Serializable
data class PromoCodeCompany(
    val id: String,
    val title: String,
)

@Serializable
enum class PromoCodeStatus {
    @SerialName("active")
    Active,

    @SerialName("inactive")
    Inactive,

    @SerialName("archived")
    Archived,
}

@Serializable
enum class PromoDuration {
    @SerialName("forever")
    Forever,

    @SerialName("once")
    Once,

    @SerialName("repeating")
    Repeating,
}

@Serializable
enum class PromoType {
    @SerialName("percentage")
    Percentage,

    @SerialName("flat_amount")
    FlatAmount,
}

internal val PromoCodeStatus.serialValue: String get() =
    when (this) {
        PromoCodeStatus.Active -> "active"
        PromoCodeStatus.Inactive -> "inactive"
        PromoCodeStatus.Archived -> "archived"
    }
