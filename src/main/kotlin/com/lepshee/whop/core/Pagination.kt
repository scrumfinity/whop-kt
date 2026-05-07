package com.lepshee.whop.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** A cursor-paginated Whop API response. */
@Serializable
data class WhopPage<T>(
    /** Items returned for the current page. */
    val data: List<T> = emptyList(),
    /** Cursor metadata for navigating adjacent pages. */
    @SerialName("page_info") val pageInfo: PageInfo = PageInfo(),
)

/** Cursor metadata returned by Whop list endpoints. */
@Serializable
data class PageInfo(
    /** True when another page exists after [endCursor]. */
    @SerialName("has_next_page") val hasNextPage: Boolean = false,
    /** True when another page exists before [startCursor]. */
    @SerialName("has_previous_page") val hasPreviousPage: Boolean = false,
    /** Cursor for the first item in the current page. */
    @SerialName("start_cursor") val startCursor: String? = null,
    /** Cursor for the last item in the current page. */
    @SerialName("end_cursor") val endCursor: String? = null,
)
