package com.lepshee.whop.models.companies

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
data class UpdateCompanyRequest(
    val title: String? = null,
    val description: String? = null,
    val route: String? = null,
    @SerialName("target_audience") val targetAudience: String? = null,
    @SerialName("send_customer_emails") val sendCustomerEmails: Boolean? = null,
    val logo: ImageReference? = null,
    @SerialName("banner_image") val bannerImage: ImageReference? = null,
)
