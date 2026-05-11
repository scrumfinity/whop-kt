package dev.lepshee.whop.models.ads

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class AdCampaignListItem(
    val id: String,
    val title: String,
    val status: AdCampaignStatus,
    @SerialName("target_country_codes") val targetCountryCodes: List<String> = emptyList(),
    @SerialName("daily_budget") val dailyBudget: Double? = null,
    val platform: AdCampaignPlatform? = null,
    @SerialName("available_budget") val availableBudget: Double,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("paused_until") val pausedUntil: String? = null,
    @SerialName("clicks_count") val clicksCount: Int,
    @SerialName("impressions_count") val impressionsCount: Int,
    @SerialName("purchases_count") val purchasesCount: Int,
    @SerialName("remaining_balance") val remainingBalance: Double,
    val revenue: Double,
    @SerialName("return_on_ad_spend") val returnOnAdSpend: Double,
    @SerialName("todays_spend") val todaysSpend: Double,
    @SerialName("total_credits") val totalCredits: Double,
    @SerialName("total_spend") val totalSpend: Double,
    val product: AdCampaignProduct? = null,
)

@Serializable
data class AdCampaign(
    val id: String,
    val title: String,
    val status: AdCampaignStatus,
    @SerialName("target_country_codes") val targetCountryCodes: List<String> = emptyList(),
    @SerialName("daily_budget") val dailyBudget: Double? = null,
    val platform: AdCampaignPlatform? = null,
    @SerialName("available_budget") val availableBudget: Double,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("paused_until") val pausedUntil: String? = null,
    @SerialName("clicks_count") val clicksCount: Int,
    @SerialName("impressions_count") val impressionsCount: Int,
    @SerialName("purchases_count") val purchasesCount: Int,
    @SerialName("remaining_balance") val remainingBalance: Double,
    val revenue: Double,
    @SerialName("return_on_ad_spend") val returnOnAdSpend: Double,
    @SerialName("todays_spend") val todaysSpend: Double,
    @SerialName("total_credits") val totalCredits: Double,
    @SerialName("total_spend") val totalSpend: Double,
    val product: AdCampaignProduct? = null,
    @SerialName("created_by_user") val createdByUser: JsonObject,
    @SerialName("payment_method") val paymentMethod: JsonObject? = null,
    @SerialName("billing_ledger_account") val billingLedgerAccount: JsonObject? = null,
    val config: JsonObject? = null,
)

@Serializable
data class AdCampaignProduct(
    val id: String,
    val title: String,
    val route: String,
)

@Serializable
data class CreateAdCampaignRequest(
    @SerialName("company_id") val companyId: String,
    val config: JsonObject,
    val platform: AdCampaignPlatform,
    val title: String,
    @SerialName("ad_creative_set_ids") val adCreativeSetIds: List<String>? = null,
    val budget: Double? = null,
    @SerialName("budget_type") val budgetType: AdBudgetType? = null,
    @SerialName("daily_budget") val dailyBudget: Double? = null,
    @SerialName("product_id") val productId: String? = null,
    @SerialName("target_country_codes") val targetCountryCodes: List<String>? = null,
) {
    init {
        require(companyId.isNotBlank()) { "Company ID must not be blank." }
        require(title.isNotBlank()) { "Ad campaign title must not be blank." }
        require(adCreativeSetIds == null || adCreativeSetIds.all(String::isNotBlank)) {
            "Ad creative set IDs must not be blank."
        }
        require(budget == null || budget >= 0.0) { "Ad campaign budget must not be negative." }
        require(dailyBudget == null || dailyBudget >= 0.0) { "Ad campaign daily budget must not be negative." }
        require(productId == null || productId.isNotBlank()) { "Product ID must not be blank." }
        require(targetCountryCodes == null || targetCountryCodes.all(String::isNotBlank)) {
            "Target country codes must not be blank."
        }
    }
}

@Serializable
data class UpdateAdCampaignRequest(
    @SerialName("ad_creative_set_ids") val adCreativeSetIds: List<String>? = null,
    val budget: Double? = null,
    @SerialName("budget_type") val budgetType: AdBudgetType? = null,
    val config: JsonObject? = null,
    @SerialName("daily_budget") val dailyBudget: Double? = null,
    @SerialName("product_id") val productId: String? = null,
    @SerialName("target_country_codes") val targetCountryCodes: List<String>? = null,
    val title: String? = null,
) {
    init {
        require(adCreativeSetIds == null || adCreativeSetIds.all(String::isNotBlank)) {
            "Ad creative set IDs must not be blank."
        }
        require(budget == null || budget >= 0.0) { "Ad campaign budget must not be negative." }
        require(dailyBudget == null || dailyBudget >= 0.0) { "Ad campaign daily budget must not be negative." }
        require(productId == null || productId.isNotBlank()) { "Product ID must not be blank." }
        require(targetCountryCodes == null || targetCountryCodes.all(String::isNotBlank)) {
            "Target country codes must not be blank."
        }
        require(title == null || title.isNotBlank()) { "Ad campaign title must not be blank." }
    }
}

@Serializable
data class AdGroupListItem(
    val id: String,
    val name: String? = null,
    val status: ExternalAdGroupStatus,
    @SerialName("daily_budget") val dailyBudget: Double? = null,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("platform_config") val platformConfig: JsonObject? = null,
    val config: JsonObject? = null,
)

@Serializable
data class AdGroup(
    val id: String,
    val name: String? = null,
    val status: ExternalAdGroupStatus,
    @SerialName("daily_budget") val dailyBudget: Double? = null,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("platform_config") val platformConfig: JsonObject? = null,
    val config: JsonObject? = null,
    @SerialName("ad_campaign") val adCampaign: AdGroupCampaignSummary,
)

@Serializable
data class AdGroupCampaignSummary(
    val id: String,
    val title: String,
    val platform: AdCampaignPlatform? = null,
    val status: AdCampaignStatus,
)

@Serializable
data class CreateAdGroupRequest(
    @SerialName("campaign_id") val campaignId: String,
    val budget: Double? = null,
    @SerialName("budget_type") val budgetType: AdBudgetType? = null,
    val config: JsonObject? = null,
    @SerialName("daily_budget") val dailyBudget: Double? = null,
    val name: String? = null,
    @SerialName("platform_config") val platformConfig: JsonObject? = null,
) {
    init {
        require(campaignId.isNotBlank()) { "Ad campaign ID must not be blank." }
        require(budget == null || budget >= 0.0) { "Ad group budget must not be negative." }
        require(dailyBudget == null || dailyBudget >= 0.0) { "Ad group daily budget must not be negative." }
        require(name == null || name.isNotBlank()) { "Ad group name must not be blank." }
    }
}

@Serializable
data class UpdateAdGroupRequest(
    val budget: Double? = null,
    @SerialName("budget_type") val budgetType: AdBudgetType? = null,
    val config: JsonObject? = null,
    @SerialName("daily_budget") val dailyBudget: Double? = null,
    val name: String? = null,
    @SerialName("platform_config") val platformConfig: JsonObject? = null,
) {
    init {
        require(budget == null || budget >= 0.0) { "Ad group budget must not be negative." }
        require(dailyBudget == null || dailyBudget >= 0.0) { "Ad group daily budget must not be negative." }
        require(name == null || name.isNotBlank()) { "Ad group name must not be blank." }
    }
}

@Serializable
data class AdListItem(
    val id: String,
    val status: ExternalAdStatus,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("platform_config") val platformConfig: JsonObject? = null,
)

@Serializable
data class Ad(
    val id: String,
    val status: ExternalAdStatus,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("platform_config") val platformConfig: JsonObject? = null,
    @SerialName("external_ad_group") val externalAdGroup: AdGroupSummary,
    @SerialName("external_ad_creative_set") val externalAdCreativeSet: AdCreativeSetSummary? = null,
)

@Serializable
data class AdGroupSummary(
    val id: String,
    val name: String? = null,
    val status: ExternalAdGroupStatus,
)

@Serializable
data class AdCreativeSetSummary(
    val id: String,
)

@Serializable
data class CreateAdRequest(
    @SerialName("ad_group_id") val adGroupId: String,
    @SerialName("creative_set_id") val creativeSetId: String? = null,
    @SerialName("existing_instagram_media_id") val existingInstagramMediaId: String? = null,
    @SerialName("existing_post_id") val existingPostId: String? = null,
    @SerialName("platform_config") val platformConfig: JsonObject? = null,
    val status: ExternalAdStatus? = null,
) {
    init {
        require(adGroupId.isNotBlank()) { "Ad group ID must not be blank." }
        require(creativeSetId == null || creativeSetId.isNotBlank()) { "Creative set ID must not be blank." }
        require(existingInstagramMediaId == null || existingInstagramMediaId.isNotBlank()) {
            "Existing Instagram media ID must not be blank."
        }
        require(existingPostId == null || existingPostId.isNotBlank()) { "Existing post ID must not be blank." }
    }
}

@Serializable
enum class AdCampaignPlatform {
    @SerialName("meta")
    Meta,

    @SerialName("tiktok")
    Tiktok,
}

@Serializable
enum class AdCampaignStatus {
    @SerialName("active")
    Active,

    @SerialName("paused")
    Paused,

    @SerialName("inactive")
    Inactive,

    @SerialName("stale")
    Stale,

    @SerialName("pending_refund")
    PendingRefund,

    @SerialName("payment_failed")
    PaymentFailed,

    @SerialName("draft")
    Draft,

    @SerialName("in_review")
    InReview,

    @SerialName("flagged")
    Flagged,

    @SerialName("importing")
    Importing,

    @SerialName("imported")
    Imported,
}

@Serializable
enum class AdBudgetType {
    @SerialName("daily")
    Daily,

    @SerialName("lifetime")
    Lifetime,
}

@Serializable
enum class ExternalAdGroupStatus {
    @SerialName("active")
    Active,

    @SerialName("paused")
    Paused,

    @SerialName("inactive")
    Inactive,

    @SerialName("in_review")
    InReview,

    @SerialName("rejected")
    Rejected,

    @SerialName("flagged")
    Flagged,
}

@Serializable
enum class ExternalAdStatus {
    @SerialName("active")
    Active,

    @SerialName("paused")
    Paused,

    @SerialName("inactive")
    Inactive,

    @SerialName("in_review")
    InReview,

    @SerialName("rejected")
    Rejected,

    @SerialName("flagged")
    Flagged,
}
