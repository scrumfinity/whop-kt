package dev.lepshee.whop.models.appbuilds

import dev.lepshee.whop.models.apps.AppViewType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppBuildListItem(
    val id: String,
    val platform: dev.lepshee.whop.models.appbuilds.AppBuildPlatform,
    @SerialName("file_url") val fileUrl: String,
    @SerialName("created_at") val createdAt: String,
    val status: dev.lepshee.whop.models.appbuilds.AppBuildStatus,
    val checksum: String,
    @SerialName("supported_app_view_types") val supportedAppViewTypes: List<dev.lepshee.whop.models.apps.AppViewType> = emptyList(),
    @SerialName("review_message") val reviewMessage: String? = null,
    @SerialName("is_production") val isProduction: Boolean,
)

@Serializable
data class AppBuild(
    val id: String,
    val platform: dev.lepshee.whop.models.appbuilds.AppBuildPlatform,
    @SerialName("file_url") val fileUrl: String,
    @SerialName("created_at") val createdAt: String,
    val status: dev.lepshee.whop.models.appbuilds.AppBuildStatus,
    val checksum: String,
    @SerialName("supported_app_view_types") val supportedAppViewTypes: List<dev.lepshee.whop.models.apps.AppViewType> = emptyList(),
    @SerialName("review_message") val reviewMessage: String? = null,
    @SerialName("is_production") val isProduction: Boolean,
)

@Serializable
data class CreateAppBuildRequest(
    val attachment: dev.lepshee.whop.models.appbuilds.FileInputWithId,
    val checksum: String,
    val platform: dev.lepshee.whop.models.appbuilds.AppBuildPlatform,
    @SerialName("ai_prompt_id") val aiPromptId: String? = null,
    @SerialName("app_id") val appId: String? = null,
    @SerialName("supported_app_view_types") val supportedAppViewTypes: List<dev.lepshee.whop.models.apps.AppViewType>? = null,
) {
    init {
        require(checksum.isNotBlank()) { "App build checksum must not be blank." }
        require(aiPromptId == null || aiPromptId.isNotBlank()) { "AI prompt ID must not be blank." }
        require(appId == null || appId.isNotBlank()) { "App ID must not be blank." }
    }
}

@Serializable
data class FileInputWithId(
    val id: String,
) {
    init {
        require(id.isNotBlank()) { "File ID must not be blank." }
    }
}

@Serializable
enum class AppBuildPlatform {
    @SerialName("ios")
    Ios,

    @SerialName("android")
    Android,

    @SerialName("web")
    Web,
}

@Serializable
enum class AppBuildStatus {
    @SerialName("draft")
    Draft,

    @SerialName("pending")
    Pending,

    @SerialName("approved")
    Approved,

    @SerialName("rejected")
    Rejected,
}
