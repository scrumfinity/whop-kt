package com.lepshee.whop.core

/** Describes when a transport may retry failed requests. */
data class RetryPolicy(
    /** Maximum number of retry attempts. */
    val maxRetries: Int,
) {
    init {
        require(maxRetries >= 0) { "Maximum retries must not be negative." }
    }

    /** Returns true when retrying this request would be considered safe. */
    fun canRetry(
        method: WhopHttpMethod,
        statusCode: Int,
        hasIdempotencyKey: Boolean,
    ): Boolean {
        val retryableStatus = statusCode == 429 || statusCode in 500..599
        if (!retryableStatus) return false

        return when (method) {
            WhopHttpMethod.Get, WhopHttpMethod.Delete -> true
            WhopHttpMethod.Post, WhopHttpMethod.Put, WhopHttpMethod.Patch -> hasIdempotencyKey
        }
    }
}
