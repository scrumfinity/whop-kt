package com.lepshee.whop.transport

import com.lepshee.whop.WhopClientConfig
import com.lepshee.whop.core.JsonConfig
import com.lepshee.whop.core.WhopHttpMethod
import com.lepshee.whop.core.WhopHttpRequest
import com.lepshee.whop.core.WhopHttpResponse
import com.lepshee.whop.core.WhopHttpTransport
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.timeout
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.Url
import io.ktor.http.appendPathSegments
import io.ktor.http.content.TextContent
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json

/** Default Ktor/CIO implementation of [WhopHttpTransport]. */
class KtorWhopHttpTransport(
    private val config: WhopClientConfig,
    private val client: HttpClient = defaultHttpClient(config),
) : WhopHttpTransport {
    /** Executes an SDK HTTP request using Ktor and returns a transport-neutral response. */
    override suspend fun execute(request: WhopHttpRequest): WhopHttpResponse {
        val response =
            client.request {
                method = request.method.toKtorMethod()
                url {
                    takeFrom(Url(request.baseUrlOverride ?: config.baseUrl))
                    appendPathSegments(
                        request.path
                            .trimStart('/')
                            .split('/')
                            .filter(String::isNotBlank),
                    )
                    request.queryParameters.forEach { (name, value) -> parameters.append(name, value) }
                }
                request.timeoutMillis?.let { timeout { requestTimeoutMillis = it } }
                request.headers.forEach { (name, value) -> header(name, value) }
                request.body?.let { setBody(TextContent(it, ContentType.Application.Json)) }
            }

        return WhopHttpResponse(
            statusCode = response.status.value,
            headers = response.headers.entries().associate { it.key to it.value },
            body = response.bodyAsText(),
        )
    }

    /** Closes the underlying Ktor [HttpClient]. */
    override fun close() {
        client.close()
    }

    private fun WhopHttpMethod.toKtorMethod(): HttpMethod =
        when (this) {
            WhopHttpMethod.Get -> HttpMethod.Get
            WhopHttpMethod.Post -> HttpMethod.Post
            WhopHttpMethod.Put -> HttpMethod.Put
            WhopHttpMethod.Patch -> HttpMethod.Patch
            WhopHttpMethod.Delete -> HttpMethod.Delete
        }

    companion object {
        /** Creates the default reusable Ktor client used by [KtorWhopHttpTransport]. */
        fun defaultHttpClient(config: WhopClientConfig): HttpClient =
            HttpClient(CIO) {
                expectSuccess = false
                install(ContentNegotiation) {
                    json(JsonConfig.whopJson)
                }
                install(HttpTimeout) {
                    requestTimeoutMillis = config.timeoutMillis
                }
            }
    }
}
