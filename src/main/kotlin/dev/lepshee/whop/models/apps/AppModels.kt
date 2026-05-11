package dev.lepshee.whop.models.apps

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class AppListItem(
    val id: String,
    val name: String,
    val description: String? = null,
    val status: AppStatus,
    @SerialName("base_url") val baseUrl: String? = null,
    @SerialName("domain_id") val domainId: String? = null,
    val verified: Boolean,
    @SerialName("app_type") val appType: AppType,
    val origin: String? = null,
    @SerialName("experience_path") val experiencePath: String? = null,
    @SerialName("discover_path") val discoverPath: String? = null,
    @SerialName("dashboard_path") val dashboardPath: String? = null,
    @SerialName("skills_path") val skillsPath: String? = null,
    @SerialName("openapi_path") val openapiPath: String? = null,
    val company: JsonObject,
    val icon: JsonObject? = null,
    val creator: JsonObject,
)

@Serializable
data class App(
    val id: String,
    val name: String,
    val description: String? = null,
    val status: AppStatus,
    @SerialName("base_url") val baseUrl: String? = null,
    @SerialName("domain_id") val domainId: String? = null,
    val verified: Boolean,
    @SerialName("app_type") val appType: AppType,
    val origin: String? = null,
    @SerialName("experience_path") val experiencePath: String? = null,
    @SerialName("discover_path") val discoverPath: String? = null,
    @SerialName("dashboard_path") val dashboardPath: String? = null,
    @SerialName("skills_path") val skillsPath: String? = null,
    @SerialName("openapi_path") val openapiPath: String? = null,
    val company: JsonObject,
    val icon: JsonObject? = null,
    val creator: JsonObject,
    @SerialName("requested_permissions") val requestedPermissions: List<JsonObject> = emptyList(),
    val stats: JsonObject? = null,
    @SerialName("api_key") val apiKey: JsonObject? = null,
    @SerialName("redirect_uris") val redirectUris: List<String> = emptyList(),
)

@Serializable
data class CreateAppRequest(
    @SerialName("company_id") val companyId: String,
    val name: String,
    @SerialName("base_url") val baseUrl: String? = null,
    val icon: FileInputWithId? = null,
    @SerialName("redirect_uris") val redirectUris: List<String>? = null,
) {
    init {
        require(companyId.isNotBlank()) { "Company ID must not be blank." }
        require(name.isNotBlank()) { "App name must not be blank." }
        require(baseUrl == null || baseUrl.isNotBlank()) { "App base URL must not be blank." }
        require(redirectUris == null || redirectUris.all(String::isNotBlank)) { "Redirect URIs must not be blank." }
    }
}

@Serializable
data class UpdateAppRequest(
    @SerialName("app_store_description") val appStoreDescription: String? = null,
    @SerialName("app_type") val appType: AppType? = null,
    @SerialName("base_url") val baseUrl: String? = null,
    @SerialName("dashboard_path") val dashboardPath: String? = null,
    val description: String? = null,
    @SerialName("discover_path") val discoverPath: String? = null,
    @SerialName("experience_path") val experiencePath: String? = null,
    val icon: FileInputWithId? = null,
    val name: String? = null,
    @SerialName("oauth_client_type") val oauthClientType: AppOauthClientType? = null,
    @SerialName("openapi_path") val openapiPath: String? = null,
    @SerialName("redirect_uris") val redirectUris: List<String>? = null,
    @SerialName("required_scopes") val requiredScopes: List<AppValidScope>? = null,
    @SerialName("skills_path") val skillsPath: String? = null,
    val status: AppStatus? = null,
) {
    init {
        require(appStoreDescription == null || appStoreDescription.isNotBlank()) { "App store description must not be blank." }
        require(baseUrl == null || baseUrl.isNotBlank()) { "App base URL must not be blank." }
        require(dashboardPath == null || dashboardPath.isNotBlank()) { "Dashboard path must not be blank." }
        require(description == null || description.isNotBlank()) { "App description must not be blank." }
        require(discoverPath == null || discoverPath.isNotBlank()) { "Discover path must not be blank." }
        require(experiencePath == null || experiencePath.isNotBlank()) { "Experience path must not be blank." }
        require(name == null || name.isNotBlank()) { "App name must not be blank." }
        require(openapiPath == null || openapiPath.isNotBlank()) { "OpenAPI path must not be blank." }
        require(redirectUris == null || redirectUris.all(String::isNotBlank)) { "Redirect URIs must not be blank." }
        require(skillsPath == null || skillsPath.isNotBlank()) { "Skills path must not be blank." }
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
enum class AppStatus {
    @SerialName("live")
    Live,

    @SerialName("unlisted")
    Unlisted,

    @SerialName("hidden")
    Hidden,
}

@Serializable
enum class AppType {
    @SerialName("b2b_app")
    B2BApp,

    @SerialName("b2c_app")
    B2CApp,

    @SerialName("company_app")
    CompanyApp,

    @SerialName("component")
    Component,
}

@Serializable
enum class AppViewType {
    @SerialName("hub")
    Hub,

    @SerialName("discover")
    Discover,

    @SerialName("dash")
    Dash,

    @SerialName("dashboard")
    Dashboard,

    @SerialName("analytics")
    Analytics,

    @SerialName("skills")
    Skills,

    @SerialName("openapi")
    Openapi,
}

@Serializable
enum class AppOauthClientType {
    @SerialName("public")
    Public,

    @SerialName("confidential")
    Confidential,
}

@Serializable
enum class AppValidScope {
    @SerialName("read_user")
    ReadUser,
}

enum class AppOrder {
    CreatedAt,
    DiscoverableAt,
    TotalInstallsLast30Days,
    TotalInstallsLast7Days,
    TimeSpent,
    TimeSpentLast24Hours,
    DailyActiveUsers,
    AiPromptCount,
    TotalAiCostUsd,
    TotalAiTokens,
    LastAiPromptAt,
    AiAverageRating,
}
