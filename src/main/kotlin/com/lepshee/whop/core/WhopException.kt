package com.lepshee.whop.core

/** Base class for all SDK exceptions raised while calling Whop. */
sealed class WhopException(
    message: String,
    cause: Throwable? = null,
) : RuntimeException(message, cause) {
    /** HTTP status code when a response was received. */
    abstract val statusCode: Int?

    /** Whop request ID, when provided by response headers or body. */
    abstract val requestId: String?
}

/** Exception raised for non-2xx Whop API responses. */
data class WhopApiException(
    override val statusCode: Int,
    override val requestId: String?,
    /** Structured Whop error payload. */
    val error: WhopError,
    /** Raw response body preserved for diagnostics. */
    val responseBody: String,
) : WhopException(error.message)

/** Exception raised when the SDK cannot connect to Whop or read a response. */
data class WhopConnectionException(
    override val requestId: String? = null,
    override val statusCode: Int? = null,
    override val cause: Throwable,
) : WhopException("Failed to connect to Whop", cause)
