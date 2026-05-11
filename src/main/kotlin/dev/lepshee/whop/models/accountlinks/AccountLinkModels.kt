package dev.lepshee.whop.models.accountlinks

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountLink(
    val url: String,
    @SerialName("expires_at") val expiresAt: String,
)

@Serializable
data class CreateAccountLinkRequest(
    @SerialName("company_id") val companyId: String,
    @SerialName("refresh_url") val refreshUrl: String,
    @SerialName("return_url") val returnUrl: String,
    @SerialName("use_case") val useCase: AccountLinkUseCase,
) {
    init {
        require(companyId.isNotBlank()) { "Company ID must not be blank." }
        require(refreshUrl.isNotBlank()) { "Refresh URL must not be blank." }
        require(returnUrl.isNotBlank()) { "Return URL must not be blank." }
    }
}

@Serializable
enum class AccountLinkUseCase {
    @SerialName("account_onboarding")
    AccountOnboarding,

    @SerialName("payouts_portal")
    PayoutsPortal,
}
