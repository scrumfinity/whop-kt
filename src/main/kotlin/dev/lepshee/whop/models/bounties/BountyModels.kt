package dev.lepshee.whop.models.bounties

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Request body for `POST /bounties`. */
@Serializable
data class CreateBountyRequest(
    /** Amount paid to each approved submission. */
    @SerialName("base_unit_amount") val baseUnitAmount: Double,
    /** Currency for the bounty pool funding amount. */
    val currency: String,
    /** Bounty description. */
    val description: String,
    /** Bounty title. */
    val title: String,
    /** Number of submissions that can be approved before the bounty closes. */
    @SerialName("accepted_submissions_limit") val acceptedSubmissionsLimit: Int? = null,
    /** ISO3166 country codes where this bounty is visible. Empty means globally visible. */
    @SerialName("allowed_country_codes") val allowedCountryCodes: List<String>? = null,
    /** Optional experience to scope the bounty to. */
    @SerialName("experience_id") val experienceId: String? = null,
    /** User or company account whose balance funds this bounty pool. */
    @SerialName("origin_account_id") val originAccountId: String? = null,
    /** Optional markdown body for the anchor forum post. */
    @SerialName("post_markdown_content") val postMarkdownContent: String? = null,
    /** Optional title for the anchor forum post. */
    @SerialName("post_title") val postTitle: String? = null,
) {
    init {
        require(baseUnitAmount.isFinite() && baseUnitAmount > 0.0) { "Bounty base unit amount must be positive." }
        require(currency.isNotBlank()) { "Currency must not be blank." }
        require(description.isNotBlank()) { "Bounty description must not be blank." }
        require(title.isNotBlank()) { "Bounty title must not be blank." }
        require(acceptedSubmissionsLimit == null || acceptedSubmissionsLimit > 0) {
            "Accepted submissions limit must be positive."
        }
        require(allowedCountryCodes == null || allowedCountryCodes.all(String::isNotBlank)) {
            "Allowed country codes must not be blank."
        }
        require(experienceId == null || experienceId.isNotBlank()) { "Experience ID must not be blank." }
        require(originAccountId == null || originAccountId.isNotBlank()) { "Origin account ID must not be blank." }
        require(postMarkdownContent == null || postMarkdownContent.isNotBlank()) { "Post markdown content must not be blank." }
        require(postTitle == null || postTitle.isNotBlank()) { "Post title must not be blank." }
    }
}

/** Bounty item returned by list endpoints. */
@Serializable
data class BountyListItem(
    /** Unique bounty ID. */
    val id: String,
    /** Bounty title. */
    val title: String,
    /** Bounty description. */
    val description: String,
    /** Current lifecycle status. */
    val status: dev.lepshee.whop.models.bounties.BountyStatus,
    /** Total amount currently funded in the bounty pool. */
    @SerialName("total_available") val totalAvailable: Double,
    /** Total amount paid out for this bounty. */
    @SerialName("total_paid") val totalPaid: Double,
    /** Bounty currency. */
    val currency: String,
    /** Underlying bounty implementation type. */
    @SerialName("bounty_type") val bountyType: dev.lepshee.whop.models.bounties.BountyType,
    /** Number of watcher votes required before a submission can resolve. */
    @SerialName("vote_threshold") val voteThreshold: Int,
    /** Creation timestamp. */
    @SerialName("created_at") val createdAt: String,
    /** Last update timestamp. */
    @SerialName("updated_at") val updatedAt: String,
)

/** Bounty returned by create and retrieve endpoints. */
@Serializable
data class Bounty(
    /** Unique bounty ID. */
    val id: String,
    /** Bounty title. */
    val title: String,
    /** Bounty description. */
    val description: String,
    /** Current lifecycle status. */
    val status: dev.lepshee.whop.models.bounties.BountyStatus,
    /** Total amount currently funded in the bounty pool. */
    @SerialName("total_available") val totalAvailable: Double,
    /** Total amount paid out for this bounty. */
    @SerialName("total_paid") val totalPaid: Double,
    /** Bounty currency. */
    val currency: String,
    /** Underlying bounty implementation type. */
    @SerialName("bounty_type") val bountyType: dev.lepshee.whop.models.bounties.BountyType,
    /** Number of watcher votes required before a submission can resolve. */
    @SerialName("vote_threshold") val voteThreshold: Int,
    /** Creation timestamp. */
    @SerialName("created_at") val createdAt: String,
    /** Last update timestamp. */
    @SerialName("updated_at") val updatedAt: String,
)

/** Bounty lifecycle statuses. */
@Serializable
enum class BountyStatus {
    @SerialName("published")
    Published,

    @SerialName("archived")
    Archived,
}

/** Bounty implementation types. */
@Serializable
enum class BountyType {
    @SerialName("classic")
    Classic,

    @SerialName("user_funded")
    UserFunded,

    @SerialName("workforce")
    Workforce,
}

internal val dev.lepshee.whop.models.bounties.BountyStatus.serialValue: String get() =
    when (this) {
        dev.lepshee.whop.models.bounties.BountyStatus.Published -> "published"
        dev.lepshee.whop.models.bounties.BountyStatus.Archived -> "archived"
    }
