package dev.lepshee.whop.models.experiences

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Request body for `POST /experiences`. */
@Serializable
data class CreateExperienceRequest(
    /** App that powers the experience. */
    @SerialName("app_id") val appId: String,
    /** Company that owns the experience. */
    @SerialName("company_id") val companyId: String,
    /** Whether the experience is publicly accessible without a membership. */
    @SerialName("is_public") val isPublic: Boolean? = null,
    /** Optional logo file reference. */
    val logo: ExperienceLogoInput? = null,
    /** Optional display name. */
    val name: String? = null,
    /** Optional section to place the experience in. */
    @SerialName("section_id") val sectionId: String? = null,
) {
    init {
        require(appId.isNotBlank()) { "App ID must not be blank." }
        require(companyId.isNotBlank()) { "Company ID must not be blank." }
        require(name == null || name.isNotBlank()) { "Experience name must not be blank." }
        require(sectionId == null || sectionId.isNotBlank()) { "Section ID must not be blank." }
    }
}

/** Request body for `PATCH /experiences/{id}`. */
@Serializable
data class UpdateExperienceRequest(
    /** New access level for the experience. */
    @SerialName("access_level") val accessLevel: ExperienceAccessLevel? = null,
    /** Whether the experience is publicly accessible without a membership. */
    @SerialName("is_public") val isPublic: Boolean? = null,
    /** New logo file reference. */
    val logo: ExperienceLogoInput? = null,
    /** New display name. */
    val name: String? = null,
    /** New section ordering value. */
    val order: String? = null,
    /** New section for the experience. */
    @SerialName("section_id") val sectionId: String? = null,
) {
    init {
        require(name == null || name.isNotBlank()) { "Experience name must not be blank." }
        require(order == null || order.isNotBlank()) { "Experience order must not be blank." }
        require(sectionId == null || sectionId.isNotBlank()) { "Section ID must not be blank." }
    }
}

/** Request body for attach and detach experience actions. */
@Serializable
data class ExperienceProductRequest(
    /** Product to attach to or detach from. */
    @SerialName("product_id") val productId: String,
) {
    init {
        require(productId.isNotBlank()) { "Product ID must not be blank." }
    }
}

/** Request body for `POST /experiences/{id}/duplicate`. */
@Serializable
data class DuplicateExperienceRequest(
    /** Optional display name for the duplicated experience. */
    val name: String? = null,
) {
    init {
        require(name == null || name.isNotBlank()) { "Experience name must not be blank." }
    }
}

/** Existing file reference used when setting an experience logo. */
@Serializable
data class ExperienceLogoInput(
    /** Existing file ID. */
    val id: String,
) {
    init {
        require(id.isNotBlank()) { "Logo file ID must not be blank." }
    }
}

/** Experience object returned by Whop. */
@Serializable
data class Experience(
    /** Unique experience ID. */
    val id: String,
    /** Display name shown in product navigation. */
    val name: String,
    /** Sort position within its section. */
    val order: String? = null,
    /** Whether the experience is publicly visible. */
    @SerialName("is_public") val isPublic: Boolean,
    /** Creation timestamp. */
    @SerialName("created_at") val createdAt: String,
    /** App that powers this experience. */
    val app: ExperienceAppSummary,
    /** Custom logo image. */
    val image: ExperienceImage? = null,
    /** Company that owns this experience. */
    val company: ExperienceCompanySummary,
    /** Products this experience is attached to. */
    val products: List<ExperienceProductSummary> = emptyList(),
)

/** List item returned by `GET /experiences`. */
@Serializable
data class ExperienceListItem(
    /** Unique experience ID. */
    val id: String,
    /** Display name shown in product navigation. */
    val name: String,
    /** Sort position within its section. */
    val order: String? = null,
    /** Whether the experience is publicly visible. */
    @SerialName("is_public") val isPublic: Boolean,
    /** Creation timestamp. */
    @SerialName("created_at") val createdAt: String,
    /** App that powers this experience. */
    val app: ExperienceAppSummary,
    /** Custom logo image. */
    val image: ExperienceImage? = null,
    /** Company that owns this experience. */
    val company: ExperienceCompanySummary,
)

/** App summary nested in experience responses. */
@Serializable
data class ExperienceAppSummary(
    /** Unique app ID. */
    val id: String,
    /** App display name. */
    val name: String,
    /** App icon image. */
    val icon: ExperienceImage? = null,
)

/** Image payload nested in experience responses. */
@Serializable
data class ExperienceImage(
    /** Optimized image URL. */
    val url: String? = null,
)

/** Company summary nested in experience responses. */
@Serializable
data class ExperienceCompanySummary(
    /** Unique company ID. */
    val id: String,
    /** Company display title. */
    val title: String,
    /** Company route slug. */
    val route: String,
)

/** Product summary nested in full experience responses. */
@Serializable
data class ExperienceProductSummary(
    /** Unique product ID. */
    val id: String,
    /** Product route slug. */
    val route: String,
    /** Product display title. */
    val title: String,
)

/** Access level values accepted by experience updates. */
@Serializable
enum class ExperienceAccessLevel {
    @SerialName("public")
    Public,

    @SerialName("private")
    Private,
}
