package com.lepshee.whop.core

import java.io.Closeable

/** Minimal HTTP abstraction used by the SDK core. */
interface WhopHttpTransport : Closeable {
    /** Executes a fully prepared HTTP request and returns the raw response. */
    suspend fun execute(request: WhopHttpRequest): WhopHttpResponse
}

/** Transport-neutral HTTP request used internally by resource classes. */
data class WhopHttpRequest(
    val method: WhopHttpMethod,
    val path: String,
    /** Absolute base URL override for endpoints outside the REST API base path. */
    val baseUrlOverride: String? = null,
    /** Query parameters. Repeated names are preserved for OpenAPI form/explode arrays. */
    val queryParameters: List<Pair<String, String>> = emptyList(),
    val headers: Map<String, String> = emptyMap(),
    val body: String? = null,
    val timeoutMillis: Long? = null,
)

/** Transport-neutral HTTP response used internally by the SDK. */
data class WhopHttpResponse(
    val statusCode: Int,
    val headers: Map<String, List<String>> = emptyMap(),
    val body: String,
)

/** HTTP methods supported by the SDK transport abstraction. */
enum class WhopHttpMethod {
    Get,
    Post,
    Put,
    Patch,
    Delete,
}
