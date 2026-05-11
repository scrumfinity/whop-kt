package dev.lepshee.whop.models.resolutioncentercases

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResolutionCenterCaseListItem(
    val id: String,
    val status: ResolutionCenterCaseStatus,
    val issue: ResolutionCenterCaseIssueType,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("due_date") val dueDate: String? = null,
    @SerialName("customer_appealed") val customerAppealed: Boolean,
    @SerialName("merchant_appealed") val merchantAppealed: Boolean,
    @SerialName("customer_response_actions") val customerResponseActions: List<ResolutionCenterCaseCustomerResponse>,
    @SerialName("merchant_response_actions") val merchantResponseActions: List<ResolutionCenterCaseMerchantResponse>,
    val company: ResolutionCenterCaseCompany? = null,
    val user: ResolutionCenterCaseUser,
    val payment: ResolutionCenterCaseRelatedPayment,
)

@Serializable
data class ResolutionCenterCase(
    val id: String,
    val status: ResolutionCenterCaseStatus,
    val issue: ResolutionCenterCaseIssueType,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("due_date") val dueDate: String? = null,
    @SerialName("customer_appealed") val customerAppealed: Boolean,
    @SerialName("merchant_appealed") val merchantAppealed: Boolean,
    @SerialName("customer_response_actions") val customerResponseActions: List<ResolutionCenterCaseCustomerResponse>,
    @SerialName("merchant_response_actions") val merchantResponseActions: List<ResolutionCenterCaseMerchantResponse>,
    val company: ResolutionCenterCaseCompany? = null,
    val user: ResolutionCenterCaseUser,
    @SerialName("platform_response_actions") val platformResponseActions: List<ResolutionCenterCasePlatformResponse>,
    val payment: ResolutionCenterCasePayment,
    val member: ResolutionCenterCaseMember? = null,
    @SerialName("resolution_events") val resolutionEvents: List<ResolutionCenterCaseEvent>,
)

@Serializable
data class ResolutionCenterCaseCompany(
    val id: String,
    val title: String,
)

@Serializable
data class ResolutionCenterCaseUser(
    val id: String,
    val name: String? = null,
    val username: String,
)

@Serializable
data class ResolutionCenterCaseRelatedPayment(
    val id: String,
)

@Serializable
data class ResolutionCenterCasePayment(
    val id: String,
    val currency: String? = null,
    @SerialName("created_at") val createdAt: String,
    @SerialName("paid_at") val paidAt: String? = null,
    val total: Double,
    val subtotal: Double? = null,
)

@Serializable
data class ResolutionCenterCaseMember(
    val id: String,
)

@Serializable
data class ResolutionCenterCaseEvent(
    val id: String,
    val action: ResolutionCenterCaseAction,
    @SerialName("reporter_type") val reporterType: ResolutionCenterCaseReporter,
    val details: String? = null,
    @SerialName("created_at") val createdAt: String,
)

@Serializable
enum class ResolutionCenterCaseStatus {
    @SerialName("merchant_response_needed")
    MerchantResponseNeeded,

    @SerialName("customer_response_needed")
    CustomerResponseNeeded,

    @SerialName("merchant_info_needed")
    MerchantInfoNeeded,

    @SerialName("customer_info_needed")
    CustomerInfoNeeded,

    @SerialName("under_platform_review")
    UnderPlatformReview,

    @SerialName("customer_won")
    CustomerWon,

    @SerialName("merchant_won")
    MerchantWon,

    @SerialName("customer_withdrew")
    CustomerWithdrew,
}

@Serializable
enum class ResolutionCenterCaseIssueType {
    @SerialName("forgot_to_cancel")
    ForgotToCancel,

    @SerialName("item_not_received")
    ItemNotReceived,

    @SerialName("significantly_not_as_described")
    SignificantlyNotAsDescribed,

    @SerialName("unauthorized_transaction")
    UnauthorizedTransaction,

    @SerialName("product_unacceptable")
    ProductUnacceptable,
}

@Serializable
enum class ResolutionCenterCaseCustomerResponse {
    @SerialName("respond")
    Respond,

    @SerialName("appeal")
    Appeal,

    @SerialName("withdraw")
    Withdraw,
}

@Serializable
enum class ResolutionCenterCaseMerchantResponse {
    @SerialName("accept")
    Accept,

    @SerialName("deny")
    Deny,

    @SerialName("request_more_info")
    RequestMoreInfo,

    @SerialName("appeal")
    Appeal,

    @SerialName("respond")
    Respond,
}

@Serializable
enum class ResolutionCenterCasePlatformResponse {
    @SerialName("request_buyer_info")
    RequestBuyerInfo,

    @SerialName("request_merchant_info")
    RequestMerchantInfo,

    @SerialName("merchant_wins")
    MerchantWins,

    @SerialName("platform_refund")
    PlatformRefund,

    @SerialName("merchant_refund")
    MerchantRefund,
}

@Serializable
enum class ResolutionCenterCaseAction {
    @SerialName("created")
    Created,

    @SerialName("responded")
    Responded,

    @SerialName("accepted")
    Accepted,

    @SerialName("denied")
    Denied,

    @SerialName("appealed")
    Appealed,

    @SerialName("withdrew")
    Withdrew,

    @SerialName("requested_more_info")
    RequestedMoreInfo,

    @SerialName("escalated")
    Escalated,

    @SerialName("dispute_opened")
    DisputeOpened,

    @SerialName("dispute_customer_won")
    DisputeCustomerWon,

    @SerialName("dispute_merchant_won")
    DisputeMerchantWon,
}

@Serializable
enum class ResolutionCenterCaseReporter {
    @SerialName("merchant")
    Merchant,

    @SerialName("customer")
    Customer,

    @SerialName("platform")
    Platform,

    @SerialName("system")
    System,
}

internal val ResolutionCenterCaseStatus.serialValue: String get() =
    when (this) {
        ResolutionCenterCaseStatus.MerchantResponseNeeded -> "merchant_response_needed"
        ResolutionCenterCaseStatus.CustomerResponseNeeded -> "customer_response_needed"
        ResolutionCenterCaseStatus.MerchantInfoNeeded -> "merchant_info_needed"
        ResolutionCenterCaseStatus.CustomerInfoNeeded -> "customer_info_needed"
        ResolutionCenterCaseStatus.UnderPlatformReview -> "under_platform_review"
        ResolutionCenterCaseStatus.CustomerWon -> "customer_won"
        ResolutionCenterCaseStatus.MerchantWon -> "merchant_won"
        ResolutionCenterCaseStatus.CustomerWithdrew -> "customer_withdrew"
    }
