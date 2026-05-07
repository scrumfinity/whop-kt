package com.lepshee.whop.models.products

import com.lepshee.whop.models.common.Money
import com.lepshee.whop.models.common.Visibility
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Request body for `POST /products`. */
@Serializable
data class CreateProductRequest(
    /** Company that owns the product. */
    @SerialName("company_id") val companyId: String,
    /** Public product title. */
    val title: String,
    /** Optional product description. */
    val description: String? = null,
    /** Optional short headline shown on product surfaces. */
    val headline: String? = null,
    /** Product visibility state. */
    val visibility: Visibility? = null,
    /** Optional product route/slug. */
    val route: String? = null,
    /** Optional call-to-action label enum for supported top-level CTA values. */
    @SerialName("custom_cta") val customCta: CustomCta? = null,
    /** Optional custom CTA destination URL. */
    @SerialName("custom_cta_url") val customCtaUrl: String? = null,
    /** URL Whop redirects buyers to after purchase. */
    @SerialName("redirect_purchase_url") val redirectPurchaseUrl: String? = null,
    /** Whether checkout should collect a shipping address. */
    @SerialName("collect_shipping_address") val collectShippingAddress: Boolean? = null,
    /** Whether Whop should send the welcome message for this product. */
    @SerialName("send_welcome_message") val sendWelcomeMessage: Boolean? = null,
    /** Experience IDs to attach to the product. */
    @SerialName("experience_ids") val experienceIds: List<String>? = null,
    /** Optional inline plan options to create with the product. */
    @SerialName("plan_options") val planOptions: ProductPlanOptions? = null,
) {
    init {
        require(companyId.isNotBlank()) { "Company ID must not be blank." }
        require(title.isNotBlank()) { "Product title must not be blank." }
        require(route == null || route.isNotBlank()) { "Product route must not be blank." }
    }
}

/** Request body for `PATCH /products/{id}`. */
@Serializable
data class UpdateProductRequest(
    /** New product title. */
    val title: String? = null,
    /** New product description. */
    val description: String? = null,
    /** New product headline. */
    val headline: String? = null,
    /** New product visibility. */
    val visibility: Visibility? = null,
    /** New product route/slug. */
    val route: String? = null,
    /** New top-level CTA enum value. */
    @SerialName("custom_cta") val customCta: CustomCta? = null,
    /** New top-level CTA URL. */
    @SerialName("custom_cta_url") val customCtaUrl: String? = null,
    /** New redirect URL after purchase. */
    @SerialName("redirect_purchase_url") val redirectPurchaseUrl: String? = null,
    /** Whether checkout should collect shipping details. */
    @SerialName("collect_shipping_address") val collectShippingAddress: Boolean? = null,
    /** Whether Whop should send welcome messages. */
    @SerialName("send_welcome_message") val sendWelcomeMessage: Boolean? = null,
    /** Store page presentation settings. */
    @SerialName("store_page_config") val storePageConfig: StorePageConfig? = null,
    /** Product gallery image file references. */
    @SerialName("gallery_images") val galleryImages: List<FileReference>? = null,
) {
    init {
        require(title == null || title.isNotBlank()) { "Product title must not be blank." }
        require(route == null || route.isNotBlank()) { "Product route must not be blank." }
    }
}

/** Inline plan options accepted when creating a product. */
@Serializable
data class ProductPlanOptions(
    /** Base currency for the inline plan. */
    @SerialName("base_currency") val baseCurrency: String? = null,
    /** Billing interval in days for renewal plans. */
    @SerialName("billing_period") val billingPeriod: Int? = null,
    /** Initial price in major currency units. */
    @SerialName("initial_price") val initialPrice: Double? = null,
    /** Renewal price in major currency units. */
    @SerialName("renewal_price") val renewalPrice: Double? = null,
    /** Inline plan type. */
    @SerialName("plan_type") val planType: ProductPlanType? = null,
    /** Inline plan release method. */
    @SerialName("release_method") val releaseMethod: ReleaseMethod? = null,
    /** Inline plan visibility. */
    val visibility: Visibility? = null,
) {
    init {
        require(baseCurrency == null || baseCurrency.isNotBlank()) { "Base currency must not be blank." }
        require(billingPeriod == null || billingPeriod > 0) { "Billing period must be positive." }
        require(initialPrice == null || initialPrice >= 0.0) { "Initial price must not be negative." }
        require(renewalPrice == null || renewalPrice >= 0.0) { "Renewal price must not be negative." }
    }

    companion object {
        /** Creates one-time inline product plan options from integer-safe minor-unit money. */
        fun oneTime(
            price: Money,
            releaseMethod: ReleaseMethod? = null,
            minorUnitsPerMajor: Long = 100,
        ): ProductPlanOptions =
            ProductPlanOptions(
                baseCurrency = price.currency,
                initialPrice = price.toMajorUnits(minorUnitsPerMajor),
                planType = ProductPlanType.OneTime,
                releaseMethod = releaseMethod,
            )

        /** Creates renewal inline product plan options from integer-safe minor-unit money. */
        fun renewal(
            renewalPrice: Money,
            releaseMethod: ReleaseMethod? = null,
            minorUnitsPerMajor: Long = 100,
        ): ProductPlanOptions =
            ProductPlanOptions(
                baseCurrency = renewalPrice.currency,
                renewalPrice = renewalPrice.toMajorUnits(minorUnitsPerMajor),
                planType = ProductPlanType.Renewal,
                releaseMethod = releaseMethod,
            )
    }
}

/** Store page display configuration for product updates. */
@Serializable
data class StorePageConfig(
    /** Custom CTA text for the store page. */
    @SerialName("custom_cta") val customCta: String? = null,
    /** Whether the store page should show pricing. */
    @SerialName("show_price") val showPrice: Boolean? = null,
) {
    init {
        require(customCta == null || customCta.isNotBlank()) { "Store page custom CTA must not be blank." }
    }
}

/** Reference to an existing Whop file object. */
@Serializable
data class FileReference(
    /** Existing file ID. */
    val id: String,
) {
    init {
        require(id.isNotBlank()) { "File ID must not be blank." }
    }
}

/** Product object returned by Whop. */
@Serializable
data class Product(
    /** Unique product ID. */
    val id: String,
    /** Product title. */
    val title: String? = null,
    /** Product visibility state. */
    val visibility: Visibility? = null,
    /** Product headline. */
    val headline: String? = null,
    /** Whether Whop has verified this product. */
    val verified: Boolean? = null,
    /** Creation timestamp. */
    @SerialName("created_at") val createdAt: String? = null,
    /** Last update timestamp. */
    @SerialName("updated_at") val updatedAt: String? = null,
    /** Number of product members. */
    @SerialName("member_count") val memberCount: Int? = null,
    /** Product route/slug. */
    val route: String? = null,
    /** Product description. */
    val description: String? = null,
    /** Product call-to-action value. */
    @SerialName("custom_cta") val customCta: CustomCta? = null,
    /** Product call-to-action URL. */
    @SerialName("custom_cta_url") val customCtaUrl: String? = null,
    /** External identifier when returned by Whop. */
    @SerialName("external_identifier") val externalIdentifier: String? = null,
)

/** Product type filters accepted by product list endpoints. */
@Serializable
enum class ProductType {
    @SerialName("regular")
    Regular,

    @SerialName("app")
    App,

    @SerialName("experience_upsell")
    ExperienceUpsell,

    @SerialName("api_only")
    ApiOnly,
}

/** Sort fields accepted by product list endpoints. */
@Serializable
enum class ProductOrder {
    @SerialName("active_memberships_count")
    ActiveMembershipsCount,

    @SerialName("created_at")
    CreatedAt,

    @SerialName("usd_gmv")
    UsdGmv,

    @SerialName("usd_gmv_30_days")
    UsdGmv30Days,
}

/** Supported top-level product call-to-action values. */
@Serializable
enum class CustomCta {
    @SerialName("get_access")
    GetAccess,

    @SerialName("join")
    Join,

    @SerialName("order_now")
    OrderNow,

    @SerialName("shop_now")
    ShopNow,

    @SerialName("call_now")
    CallNow,

    @SerialName("donate_now")
    DonateNow,

    @SerialName("contact_us")
    ContactUs,

    @SerialName("sign_up")
    SignUp,

    @SerialName("subscribe")
    Subscribe,

    @SerialName("purchase")
    Purchase,

    @SerialName("get_offer")
    GetOffer,

    @SerialName("apply_now")
    ApplyNow,

    @SerialName("complete_order")
    CompleteOrder,
}

/** Plan type values accepted inside product plan options. */
@Serializable
enum class ProductPlanType {
    @SerialName("renewal")
    Renewal,

    @SerialName("one_time")
    OneTime,
}

/** How a plan or product is released to buyers. */
@Serializable
enum class ReleaseMethod {
    @SerialName("buy_now")
    BuyNow,

    @SerialName("waitlist")
    Waitlist,
}
