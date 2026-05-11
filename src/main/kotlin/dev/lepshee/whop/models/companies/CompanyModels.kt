package dev.lepshee.whop.models.companies

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

/** Company object returned by Whop. */
@Serializable
data class Company(
    val id: String,
    val title: String? = null,
    val description: String? = null,
    val verified: Boolean? = null,
    @SerialName("send_customer_emails") val sendCustomerEmails: Boolean? = null,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("updated_at") val updatedAt: String? = null,
    @SerialName("member_count") val memberCount: Int? = null,
    val route: String? = null,
    val metadata: JsonObject? = null,
    @SerialName("target_audience") val targetAudience: String? = null,
    val logo: JsonObject? = null,
    @SerialName("owner_user") val ownerUser: JsonObject? = null,
    @SerialName("social_links") val socialLinks: List<JsonObject> = emptyList(),
    @SerialName("published_reviews_count") val publishedReviewsCount: Int? = null,
    @SerialName("affiliate_instructions") val affiliateInstructions: String? = null,
    @SerialName("featured_affiliate_product") val featuredAffiliateProduct: JsonObject? = null,
)

@Serializable
data class ImageReference(
    val id: String,
)

@Serializable
data class CreateCompanyRequest(
    val title: String,
    val description: String? = null,
    val email: String? = null,
    val logo: ImageReference? = null,
    val metadata: JsonObject? = null,
    @SerialName("parent_company_id") val parentCompanyId: String? = null,
    @SerialName("send_customer_emails") val sendCustomerEmails: Boolean? = null,
)

@Serializable
data class CreateChildCompanyApiKeyRequest(
    @SerialName("child_company_id") val childCompanyId: String,
) {
    init {
        require(childCompanyId.isNotBlank()) { "Child company ID must not be blank." }
    }
}

@Serializable
data class CompanyApiKey(
    val id: String,
    val name: String? = null,
    @SerialName("secret_key") val secretKey: String? = null,
)

@Serializable
data class UpdateCompanyRequest(
    val title: String? = null,
    val description: String? = null,
    val route: String? = null,
    @SerialName("target_audience") val targetAudience: String? = null,
    @SerialName("send_customer_emails") val sendCustomerEmails: Boolean? = null,
    @SerialName("affiliate_application_required") val affiliateApplicationRequired: Boolean? = null,
    @SerialName("affiliate_instructions") val affiliateInstructions: String? = null,
    @SerialName("featured_affiliate_product_id") val featuredAffiliateProductId: String? = null,
    val logo: ImageReference? = null,
    @SerialName("banner_image") val bannerImage: ImageReference? = null,
    @SerialName("social_links") val socialLinks: List<SocialLink>? = null,
) {
    init {
        require(title == null || title.isNotBlank()) { "Company title must not be blank." }
        require(route == null || route.isNotBlank()) { "Company route must not be blank." }
        require(featuredAffiliateProductId == null || featuredAffiliateProductId.isNotBlank()) {
            "Featured affiliate product ID must not be blank."
        }
    }
}

@Serializable
data class SocialLink(
    val url: String,
    val website: SocialLinkWebsite,
    val image: ImageReference? = null,
    val order: String? = null,
    val title: String? = null,
    @SerialName("website_order") val websiteOrder: String? = null,
) {
    init {
        require(url.isNotBlank()) { "Social link URL must not be blank." }
        require(order == null || order.isNotBlank()) { "Social link order must not be blank." }
        require(title == null || title.isNotBlank()) { "Social link title must not be blank." }
        require(websiteOrder == null || websiteOrder.isNotBlank()) { "Social link website order must not be blank." }
    }
}

@Serializable
enum class SocialLinkWebsite {
    @SerialName("x")
    X,

    @SerialName("instagram")
    INSTAGRAM,

    @SerialName("facebook")
    FACEBOOK,

    @SerialName("tiktok")
    TIKTOK,

    @SerialName("youtube")
    YOUTUBE,

    @SerialName("linkedin")
    LINKEDIN,

    @SerialName("twitch")
    TWITCH,

    @SerialName("website")
    WEBSITE,

    @SerialName("custom")
    CUSTOM,
}
