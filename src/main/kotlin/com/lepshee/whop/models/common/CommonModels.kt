package com.lepshee.whop.models.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Sort direction for list endpoints. */
@Serializable
enum class Direction {
    @SerialName("asc")
    Asc,

    @SerialName("desc")
    Desc,
}

/** Product or plan visibility values. */
@Serializable
enum class Visibility {
    @SerialName("visible")
    Visible,

    @SerialName("hidden")
    Hidden,

    @SerialName("archived")
    Archived,

    @SerialName("quick_link")
    QuickLink,
}

/** Visibility filters accepted by list endpoints. */
@Serializable
enum class VisibilityFilter {
    @SerialName("visible")
    Visible,

    @SerialName("hidden")
    Hidden,

    @SerialName("archived")
    Archived,

    @SerialName("quick_link")
    QuickLink,

    @SerialName("all")
    All,

    @SerialName("not_quick_link")
    NotQuickLink,

    @SerialName("not_archived")
    NotArchived,
}
