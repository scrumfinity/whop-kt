package dev.lepshee.whop.models.memberships

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

/** Membership object returned by Whop. */
@Serializable
data class Membership(
    val id: String,
    val status: MembershipStatus? = null,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("joined_at") val joinedAt: String? = null,
    @SerialName("updated_at") val updatedAt: String? = null,
    @SerialName("manage_url") val manageUrl: String? = null,
    @SerialName("license_key") val licenseKey: String? = null,
    val metadata: JsonObject? = null,
    val member: JsonObject? = null,
    val user: JsonObject? = null,
    val company: JsonObject? = null,
    val plan: JsonObject? = null,
    val product: JsonObject? = null,
    @SerialName("renewal_period_start") val renewalPeriodStart: String? = null,
    @SerialName("renewal_period_end") val renewalPeriodEnd: String? = null,
    @SerialName("cancel_at_period_end") val cancelAtPeriodEnd: Boolean? = null,
    @SerialName("cancel_option") val cancelOption: MembershipCancelOption? = null,
    @SerialName("cancellation_reason") val cancellationReason: String? = null,
    @SerialName("canceled_at") val canceledAt: String? = null,
    val currency: String? = null,
    @SerialName("promo_code") val promoCode: JsonObject? = null,
    @SerialName("payment_collection_paused") val paymentCollectionPaused: Boolean? = null,
    @SerialName("checkout_configuration_id") val checkoutConfigurationId: String? = null,
    @SerialName("custom_field_responses") val customFieldResponses: List<JsonObject> = emptyList(),
)

@Serializable
enum class MembershipStatus {
    @SerialName("trialing")
    Trialing,

    @SerialName("active")
    Active,

    @SerialName("past_due")
    PastDue,

    @SerialName("completed")
    Completed,

    @SerialName("canceled")
    Canceled,

    @SerialName("expired")
    Expired,

    @SerialName("unresolved")
    Unresolved,

    @SerialName("drafted")
    Drafted,

    @SerialName("canceling")
    Canceling,
}

@Serializable
enum class MembershipOrder {
    @SerialName("id")
    Id,

    @SerialName("created_at")
    CreatedAt,

    @SerialName("status")
    Status,

    @SerialName("canceled_at")
    CanceledAt,

    @SerialName("date_joined")
    DateJoined,

    @SerialName("total_spend")
    TotalSpend,
}

@Serializable
enum class MembershipCancelOption {
    @SerialName("too_expensive")
    TooExpensive,

    @SerialName("switching")
    Switching,

    @SerialName("missing_features")
    MissingFeatures,

    @SerialName("technical_issues")
    TechnicalIssues,

    @SerialName("bad_experience")
    BadExperience,

    @SerialName("other")
    Other,

    @SerialName("testing")
    Testing,
}

@Serializable
enum class MembershipCancellationMode {
    @SerialName("at_period_end")
    AtPeriodEnd,

    @SerialName("immediate")
    Immediate,
}

@Serializable
data class UpdateMembershipRequest(
    val metadata: JsonElement? = null,
)

@Serializable
data class CancelMembershipRequest(
    @SerialName("cancellation_mode") val cancellationMode: MembershipCancellationMode? = null,
)

@Serializable
data class AddFreeDaysMembershipRequest(
    @SerialName("free_days") val freeDays: Int,
) {
    init {
        require(freeDays > 0) { "Free days must be positive." }
    }
}

@Serializable
data class PauseMembershipRequest(
    @SerialName("void_payments") val voidPayments: Boolean? = null,
)
