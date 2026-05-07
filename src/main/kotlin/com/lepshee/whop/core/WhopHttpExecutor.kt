package com.lepshee.whop.core

import com.lepshee.whop.WhopClientConfig
import com.lepshee.whop.WhopRequestOptions
import kotlinx.serialization.KSerializer
import kotlinx.serialization.serializer
import java.io.Closeable
import kotlin.coroutines.cancellation.CancellationException

/**
 * Internal request executor that applies SDK auth/options, serializes request bodies, and parses responses.
 *
 * Resource classes use this instead of depending on a concrete HTTP client.
 */
class WhopHttpExecutor(
    private val config: WhopClientConfig,
    private val transport: WhopHttpTransport,
) : Closeable {
    private val retryPolicy = RetryPolicy(config.maxRetries)

    /** Executes a GET request and decodes the response into [Response]. */
    suspend inline fun <reified Response : Any> get(
        path: String,
        queryParameters: List<Pair<String, String>> = emptyList(),
        options: WhopRequestOptions = WhopRequestOptions(),
        baseUrlOverride: String? = null,
    ): Response =
        execute(
            responseSerializer = serializer(),
            request =
                WhopHttpRequest(
                    method = WhopHttpMethod.Get,
                    path = path,
                    baseUrlOverride = baseUrlOverride,
                    queryParameters = queryParameters,
                ),
            options = options,
        )

    /** Executes a POST request with a JSON body and decodes the response into [Response]. */
    suspend inline fun <reified Request : Any, reified Response : Any> post(
        path: String,
        body: Request,
        options: WhopRequestOptions = WhopRequestOptions(),
        baseUrlOverride: String? = null,
    ): Response =
        execute(
            responseSerializer = serializer(),
            request =
                WhopHttpRequest(
                    method = WhopHttpMethod.Post,
                    path = path,
                    baseUrlOverride = baseUrlOverride,
                    body = JsonConfig.whopJson.encodeToString(serializer<Request>(), body),
                ),
            options = options,
        )

    /** Executes a POST request with a JSON body and does not attempt to decode a response body. */
    suspend inline fun <reified Request : Any> postNoContent(
        path: String,
        body: Request,
        options: WhopRequestOptions = WhopRequestOptions(),
        baseUrlOverride: String? = null,
    ) {
        executeNoContent(
            request =
                WhopHttpRequest(
                    method = WhopHttpMethod.Post,
                    path = path,
                    baseUrlOverride = baseUrlOverride,
                    body = JsonConfig.whopJson.encodeToString(serializer<Request>(), body),
                ),
            options = options,
        )
    }

    /** Executes a DELETE request and decodes the response into [Response]. */
    suspend inline fun <reified Response : Any> delete(
        path: String,
        options: WhopRequestOptions = WhopRequestOptions(),
        baseUrlOverride: String? = null,
    ): Response =
        execute(
            responseSerializer = serializer(),
            request =
                WhopHttpRequest(
                    method = WhopHttpMethod.Delete,
                    path = path,
                    baseUrlOverride = baseUrlOverride,
                ),
            options = options,
        )

    /** Executes a PATCH request with a JSON body and decodes the response into [Response]. */
    suspend inline fun <reified Request : Any, reified Response : Any> patch(
        path: String,
        body: Request,
        options: WhopRequestOptions = WhopRequestOptions(),
        baseUrlOverride: String? = null,
    ): Response =
        execute(
            responseSerializer = serializer(),
            request =
                WhopHttpRequest(
                    method = WhopHttpMethod.Patch,
                    path = path,
                    baseUrlOverride = baseUrlOverride,
                    body = JsonConfig.whopJson.encodeToString(serializer<Request>(), body),
                ),
            options = options,
        )

    /** Executes a prepared request and maps non-2xx responses to [WhopApiException]. */
    suspend fun <Response : Any> execute(
        responseSerializer: KSerializer<Response>,
        request: WhopHttpRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Response {
        val response = executeWithRetries(request.withConfiguredHeaders(options))

        if (response.statusCode !in 200..299) {
            throw response.toApiException()
        }

        return JsonConfig.whopJson.decodeFromString(responseSerializer, response.body)
    }

    /** Executes a prepared request and validates only the HTTP status code. */
    suspend fun executeNoContent(
        request: WhopHttpRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ) {
        val response = executeWithRetries(request.withConfiguredHeaders(options))

        if (response.statusCode !in 200..299) {
            throw response.toApiException()
        }
    }

    private suspend fun executeWithRetries(request: WhopHttpRequest): WhopHttpResponse {
        var attempt = 0
        while (true) {
            try {
                val response = transport.execute(request)
                if (attempt < retryPolicy.maxRetries &&
                    retryPolicy.canRetry(
                        method = request.method,
                        statusCode = response.statusCode,
                        hasIdempotencyKey = request.hasIdempotencyKey,
                    )
                ) {
                    attempt += 1
                    continue
                }
                return response
            } catch (cause: CancellationException) {
                throw cause
            } catch (cause: Exception) {
                if (attempt < retryPolicy.maxRetries && retryPolicy.canRetryConnectionFailure(request)) {
                    attempt += 1
                    continue
                }
                throw WhopConnectionException(cause = cause)
            }
        }
    }

    private fun WhopHttpRequest.withConfiguredHeaders(options: WhopRequestOptions): WhopHttpRequest {
        val configuredHeaders =
            buildMap {
                if (options.includeAuth) put(WhopHeaders.AUTHORIZATION, "Bearer ${options.apiKey ?: config.apiKey}")
                put(WhopHeaders.USER_AGENT, config.userAgent)
                if (body != null) put(WhopHeaders.CONTENT_TYPE, "application/json")
                if (options.idempotencyKey != null) put(WhopHeaders.IDEMPOTENCY_KEY, options.idempotencyKey)
                putAll(headers)
                putAll(options.headers)
            }

        return copy(headers = configuredHeaders, timeoutMillis = options.timeoutMillis ?: timeoutMillis ?: config.timeoutMillis)
    }

    private fun RetryPolicy.canRetryConnectionFailure(request: WhopHttpRequest): Boolean =
        when (request.method) {
            WhopHttpMethod.Get, WhopHttpMethod.Delete -> maxRetries > 0
            WhopHttpMethod.Post, WhopHttpMethod.Put, WhopHttpMethod.Patch -> maxRetries > 0 && request.hasIdempotencyKey
        }

    private val WhopHttpRequest.hasIdempotencyKey: Boolean
        get() = headers.containsKey(WhopHeaders.IDEMPOTENCY_KEY)

    private fun WhopHttpResponse.toApiException(): WhopApiException {
        val envelope = runCatching { JsonConfig.whopJson.decodeFromString<WhopErrorEnvelope>(body) }.getOrNull()
        val requestId = headers.firstHeaderValue(WhopHeaders.REQUEST_ID) ?: envelope?.requestId
        val error =
            envelope?.toWhopError("Whop API request failed with status $statusCode.")
                ?: WhopError(message = "Whop API request failed with status $statusCode.")

        return WhopApiException(
            statusCode = statusCode,
            requestId = requestId,
            error = error,
            responseBody = body,
        )
    }

    private fun Map<String, List<String>>.firstHeaderValue(name: String): String? =
        entries.firstOrNull { (key) -> key.equals(name, ignoreCase = true) }?.value?.firstOrNull()

    override fun close() {
        transport.close()
    }
}
