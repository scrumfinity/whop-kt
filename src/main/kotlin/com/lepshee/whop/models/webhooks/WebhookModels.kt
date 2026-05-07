package com.lepshee.whop.models.webhooks

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Webhook endpoint registered with Whop. */
@Serializable
data class WebhookEndpoint(
    val id: String,
    val url: String,
    val enabled: Boolean? = null,
    val events: List<String> = emptyList(),
    @SerialName("api_version") val apiVersion: WebhookApiVersion? = null,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("child_resource_events") val childResourceEvents: List<String> = emptyList(),
    @SerialName("testable_events") val testableEvents: List<String> = emptyList(),
    @SerialName("resource_id") val resourceId: String? = null,
    @SerialName("webhook_secret") val webhookSecret: String? = null,
)

@Serializable
enum class WebhookApiVersion {
    @SerialName("v1")
    V1,

    @SerialName("v2")
    V2,

    @SerialName("v5")
    V5,
}

@Serializable
data class CreateWebhookRequest(
    val url: String,
    @SerialName("api_version") val apiVersion: WebhookApiVersion? = null,
    @SerialName("child_resource_events") val childResourceEvents: List<String> = emptyList(),
    val enabled: Boolean? = null,
    val events: List<String> = emptyList(),
    @SerialName("resource_id") val resourceId: String? = null,
)
