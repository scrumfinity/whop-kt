package com.lepshee.whop.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Structured error object returned by Whop API responses. */
@Serializable
data class WhopError(
    /** Error category, such as authentication or validation. */
    val type: String? = null,
    /** Human-readable error message. */
    val message: String,
    /** Machine-readable error code when available. */
    val code: String? = null,
    /** Field or parameter associated with the error when available. */
    val param: String? = null,
)

@Serializable
internal data class WhopErrorEnvelope(
    val error: WhopError? = null,
    val type: String? = null,
    val message: String? = null,
    val code: String? = null,
    val param: String? = null,
    @SerialName("request_id") val requestId: String? = null,
) {
    fun toWhopError(fallbackMessage: String): WhopError =
        error ?: WhopError(
            type = type,
            message = message ?: fallbackMessage,
            code = code,
            param = param,
        )
}
