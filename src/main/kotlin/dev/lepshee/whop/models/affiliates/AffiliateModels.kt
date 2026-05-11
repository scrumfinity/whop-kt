package dev.lepshee.whop.models.affiliates

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AffiliateListItem(
    val id: String,
    val status: AffiliateStatus? = null,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("total_referrals_count") val totalReferralsCount: Int,
    @SerialName("total_referral_earnings_usd") val totalReferralEarningsUsd: String,
    @SerialName("total_overrides_count") val totalOverridesCount: Int,
    @SerialName("customer_retention_rate") val customerRetentionRate: String,
    @SerialName("customer_retention_rate_ninety_days") val customerRetentionRateNinetyDays: String,
    @SerialName("monthly_recurring_revenue_usd") val monthlyRecurringRevenueUsd: String,
    @SerialName("total_revenue_usd") val totalRevenueUsd: String,
    @SerialName("active_members_count") val activeMembersCount: Int,
    val user: AffiliateUser,
    val company: AffiliateCompany,
)

@Serializable
data class Affiliate(
    val id: String,
    val status: AffiliateStatus? = null,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("total_referrals_count") val totalReferralsCount: Int,
    @SerialName("total_referral_earnings_usd") val totalReferralEarningsUsd: String,
    @SerialName("total_overrides_count") val totalOverridesCount: Int,
    @SerialName("customer_retention_rate") val customerRetentionRate: String,
    @SerialName("customer_retention_rate_ninety_days") val customerRetentionRateNinetyDays: String,
    @SerialName("monthly_recurring_revenue_usd") val monthlyRecurringRevenueUsd: String,
    @SerialName("total_revenue_usd") val totalRevenueUsd: String,
    @SerialName("active_members_count") val activeMembersCount: Int,
    val user: AffiliateUser,
    val company: AffiliateCompany,
)

@Serializable
data class AffiliateUser(
    val id: String,
    val name: String? = null,
    val username: String? = null,
)

@Serializable
data class AffiliateCompany(
    val id: String,
    val title: String,
)

@Serializable
data class CreateAffiliateRequest(
    @SerialName("company_id") val companyId: String,
    @SerialName("user_identifier") val userIdentifier: String,
) {
    init {
        require(companyId.isNotBlank()) { "Company ID must not be blank." }
        require(userIdentifier.isNotBlank()) { "User identifier must not be blank." }
    }
}

@Serializable
data class AffiliateOverride(
    val id: String,
    @SerialName("override_type") val overrideType: AffiliateOverrideRole,
    @SerialName("commission_type") val commissionType: AffiliatePayoutType,
    @SerialName("commission_value") val commissionValue: Double,
    @SerialName("applies_to_payments") val appliesToPayments: AffiliateAppliesToPayments? = null,
    @SerialName("plan_id") val planId: String? = null,
    @SerialName("product_id") val productId: String? = null,
    @SerialName("applies_to_products") val appliesToProducts: AffiliateAppliesToProducts? = null,
    @SerialName("revenue_basis") val revenueBasis: AffiliateRevenueBasis? = null,
    @SerialName("product_direct_link") val productDirectLink: String? = null,
    @SerialName("checkout_direct_link") val checkoutDirectLink: String? = null,
    @SerialName("total_referral_earnings_usd") val totalReferralEarningsUsd: Double,
)

@Serializable
data class CreateStandardAffiliateOverrideRequest(
    val id: String,
    @SerialName("commission_value") val commissionValue: Double,
    @SerialName("plan_id") val planId: String,
    @SerialName("applies_to_payments") val appliesToPayments: AffiliateAppliesToPayments? = null,
    @SerialName("commission_type") val commissionType: AffiliatePayoutType? = null,
    @SerialName(
        "override_type",
    ) val overrideType: AffiliateOverrideRole = AffiliateOverrideRole.Standard,
) {
    init {
        require(id.isNotBlank()) { "Affiliate ID must not be blank." }
        require(commissionValue >= 0.0) { "Commission value must not be negative." }
        require(
            commissionType != AffiliatePayoutType.Percentage ||
                commissionValue in 1.0..100.0,
        ) {
            "Percentage commission value must be between 1 and 100."
        }
        require(planId.isNotBlank()) { "Plan ID must not be blank." }
        require(overrideType == AffiliateOverrideRole.Standard) {
            "Standard affiliate override type must be standard."
        }
    }
}

@Serializable
data class CreateRevShareAffiliateOverrideRequest(
    val id: String,
    @SerialName("commission_value") val commissionValue: Double,
    @SerialName("product_id") val productId: String? = null,
    @SerialName("revenue_basis") val revenueBasis: AffiliateRevenueBasis? = null,
    @SerialName("commission_type") val commissionType: AffiliatePayoutType? = null,
    @SerialName(
        "override_type",
    ) val overrideType: AffiliateOverrideRole = AffiliateOverrideRole.RevShare,
) {
    init {
        require(id.isNotBlank()) { "Affiliate ID must not be blank." }
        require(commissionValue >= 0.0) { "Commission value must not be negative." }
        require(
            commissionType != AffiliatePayoutType.Percentage ||
                commissionValue in 1.0..100.0,
        ) {
            "Percentage commission value must be between 1 and 100."
        }
        require(productId == null || productId.isNotBlank()) { "Product ID must not be blank." }
        require(overrideType == AffiliateOverrideRole.RevShare) {
            "Rev-share affiliate override type must be rev_share."
        }
    }
}

@Serializable
data class UpdateAffiliateOverrideRequest(
    @SerialName("applies_to_payments") val appliesToPayments: AffiliateAppliesToPayments? = null,
    @SerialName("commission_type") val commissionType: AffiliatePayoutType? = null,
    @SerialName("commission_value") val commissionValue: Double? = null,
    @SerialName("revenue_basis") val revenueBasis: AffiliateRevenueBasis? = null,
) {
    init {
        require(commissionValue == null || commissionValue >= 0.0) { "Commission value must not be negative." }
        require(
            commissionType != AffiliatePayoutType.Percentage ||
                commissionValue == null ||
                commissionValue in 1.0..100.0,
        ) {
            "Percentage commission value must be between 1 and 100."
        }
    }
}

@Serializable
enum class AffiliateStatus {
    @SerialName("active")
    Active,

    @SerialName("archived")
    Archived,

    @SerialName("deleted")
    Deleted,
}

@Serializable
enum class AffiliateSortableColumn {
    @SerialName("id")
    Id,

    @SerialName("created_at")
    CreatedAt,

    @SerialName("cached_total_referrals")
    CachedTotalReferrals,

    @SerialName("cached_total_rewards")
    CachedTotalRewards,
}

@Serializable
enum class AffiliateOverrideRole {
    @SerialName("standard")
    Standard,

    @SerialName("rev_share")
    RevShare,
}

@Serializable
enum class AffiliatePayoutType {
    @SerialName("percentage")
    Percentage,

    @SerialName("flat_fee")
    FlatFee,
}

@Serializable
enum class AffiliateAppliesToPayments {
    @SerialName("first_payment")
    FirstPayment,

    @SerialName("all_payments")
    AllPayments,
}

@Serializable
enum class AffiliateAppliesToProducts {
    @SerialName("single_product")
    SingleProduct,

    @SerialName("all_products")
    AllProducts,
}

@Serializable
enum class AffiliateRevenueBasis {
    @SerialName("pre_fees")
    PreFees,

    @SerialName("post_fees")
    PostFees,
}
