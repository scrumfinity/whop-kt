package dev.lepshee.whop.core

/** HTTP header names used by Whop requests and responses. */
object WhopHeaders {
    /** Bearer token authentication header. */
    const val AUTHORIZATION = "Authorization"

    /** Request content type header. */
    const val CONTENT_TYPE = "Content-Type"

    /** Idempotency key header for safe mutating operations. */
    const val IDEMPOTENCY_KEY = "Idempotency-Key"

    /** Whop request ID response header. */
    const val REQUEST_ID = "x-request-id"

    /** SDK user-agent header. */
    const val USER_AGENT = "User-Agent"
}
