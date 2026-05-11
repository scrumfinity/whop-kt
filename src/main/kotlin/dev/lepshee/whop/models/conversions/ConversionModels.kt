package dev.lepshee.whop.models.conversions

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Request body for `POST /conversions`. */
@Serializable
data class CreateConversionRequest(
    /** Company to associate with this event. */
    @SerialName("company_id") val companyId: String,
    /** Type of conversion or engagement event. */
    @SerialName("event_name") val eventName: ConversionEventName,
    /** Channel where the event originated. */
    @SerialName("action_source") val actionSource: ConversionActionSource? = null,
    /** Tracking and attribution context. */
    val context: ConversionContext? = null,
    /** ISO currency code for the event value. */
    val currency: String? = null,
    /** Custom event name when [eventName] is [ConversionEventName.Custom]. */
    @SerialName("custom_name") val customName: String? = null,
    /** Client-provided deduplication ID. */
    @SerialName("event_id") val eventId: String? = null,
    /** Event timestamp. */
    @SerialName("event_time") val eventTime: String? = null,
    /** Plan associated with the event. */
    @SerialName("plan_id") val planId: String? = null,
    /** Product associated with the event. */
    @SerialName("product_id") val productId: String? = null,
    /** Referring URL. */
    @SerialName("referrer_url") val referrerUrl: String? = null,
    /** URL where the event occurred. */
    val url: String? = null,
    /** User identity and profile data. */
    val user: ConversionUser? = null,
    /** Monetary value associated with the event. */
    val value: Double? = null,
) {
    init {
        require(companyId.isNotBlank()) { "Company ID must not be blank." }
        require(currency == null || currency.isNotBlank()) { "Currency must not be blank." }
        require(customName == null || customName.isNotBlank()) { "Custom event name must not be blank." }
        require(eventId == null || eventId.isNotBlank()) { "Event ID must not be blank." }
        require(eventTime == null || eventTime.isNotBlank()) { "Event time must not be blank." }
        require(planId == null || planId.isNotBlank()) { "Plan ID must not be blank." }
        require(productId == null || productId.isNotBlank()) { "Product ID must not be blank." }
        require(referrerUrl == null || referrerUrl.isNotBlank()) { "Referrer URL must not be blank." }
        require(url == null || url.isNotBlank()) { "URL must not be blank." }
        require(value == null || value.isFinite()) { "Conversion value must be finite." }
    }
}

/** Response body for a tracked conversion event. */
@Serializable
data class Conversion(
    /** Unique conversion ID. */
    val id: String,
)

/** Tracking and attribution context for conversion events. */
@Serializable
data class ConversionContext(
    val fbclid: String? = null,
    val fbp: String? = null,
    val ga: String? = null,
    val gclid: String? = null,
    @SerialName("ig_sid") val igSid: String? = null,
    val ttclid: String? = null,
    val ttp: String? = null,
    @SerialName("ip_address") val ipAddress: String? = null,
    @SerialName("user_agent") val userAgent: String? = null,
    @SerialName("ad_campaign_id") val adCampaignId: String? = null,
    @SerialName("ad_id") val adId: String? = null,
    @SerialName("ad_set_id") val adSetId: String? = null,
    @SerialName("utm_campaign") val utmCampaign: String? = null,
    @SerialName("utm_content") val utmContent: String? = null,
    @SerialName("utm_id") val utmId: String? = null,
    @SerialName("utm_medium") val utmMedium: String? = null,
    @SerialName("utm_source") val utmSource: String? = null,
    @SerialName("utm_term") val utmTerm: String? = null,
)

/** User identity and profile data for conversion events. */
@Serializable
data class ConversionUser(
    @SerialName("anonymous_id") val anonymousId: String? = null,
    @SerialName("external_id") val externalId: String? = null,
    @SerialName("member_id") val memberId: String? = null,
    @SerialName("membership_id") val membershipId: String? = null,
    @SerialName("user_id") val userId: String? = null,
    val birthdate: String? = null,
    val city: String? = null,
    val country: String? = null,
    val email: String? = null,
    @SerialName("first_name") val firstName: String? = null,
    val gender: ConversionGender? = null,
    @SerialName("last_name") val lastName: String? = null,
    val name: String? = null,
    val phone: String? = null,
    @SerialName("postal_code") val postalCode: String? = null,
    val state: String? = null,
    val username: String? = null,
)

@Serializable
enum class ConversionActionSource {
    @SerialName("email")
    Email,

    @SerialName("website")
    Website,

    @SerialName("app")
    App,

    @SerialName("phone_call")
    PhoneCall,

    @SerialName("chat")
    Chat,

    @SerialName("physical_store")
    PhysicalStore,

    @SerialName("system_generated")
    SystemGenerated,

    @SerialName("business_messaging")
    BusinessMessaging,

    @SerialName("other")
    Other,
}

@Serializable
enum class ConversionEventName {
    @SerialName("lead")
    Lead,

    @SerialName("submit_application")
    SubmitApplication,

    @SerialName("contact")
    Contact,

    @SerialName("complete_registration")
    CompleteRegistration,

    @SerialName("schedule")
    Schedule,

    @SerialName("custom")
    Custom,
}

@Serializable
enum class ConversionGender {
    @SerialName("male")
    Male,

    @SerialName("female")
    Female,
}
