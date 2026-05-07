package com.lepshee.whop.models.memberships

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
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
    val metadata: JsonObject,
)

@Serializable
data class CancelMembershipRequest(
    @SerialName("cancellation_mode") val cancellationMode: MembershipCancellationMode,
)
