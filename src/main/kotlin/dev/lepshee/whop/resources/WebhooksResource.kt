package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.webhooks.CreateWebhookRequest
import dev.lepshee.whop.models.webhooks.UpdateWebhookRequest
import dev.lepshee.whop.models.webhooks.WebhookEndpoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for managing Whop webhook endpoints. */
class WebhooksResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    /** Creates a webhook endpoint. The create response includes the webhook secret. */
    suspend fun create(
        request: CreateWebhookRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WebhookEndpoint {
        require(request.url.isNotBlank()) { "Webhook URL must not be blank." }
        return http.post("webhooks", request, options)
    }

    /** Lists webhook endpoints for a company. */
    suspend fun list(
        request: ListWebhooksRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<WebhookEndpoint> = http.get("webhooks", request.toQueryParameters(), options)

    /** Retrieves a webhook endpoint by ID. */
    suspend fun retrieve(
        webhookId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WebhookEndpoint {
        val id = pathParameter(webhookId, "Webhook ID")
        return http.get("webhooks/$id", options = options)
    }

    /** Updates a webhook endpoint. */
    suspend fun update(
        webhookId: String,
        request: UpdateWebhookRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WebhookEndpoint {
        val id = pathParameter(webhookId, "Webhook ID")
        return http.patch("webhooks/$id", request, options)
    }

    /** Streams webhook endpoints across forward pages. */
    fun listAutoPaging(
        request: ListWebhooksRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<WebhookEndpoint> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }

    /** Deletes a webhook endpoint and returns Whop's boolean deletion result. */
    suspend fun delete(
        webhookId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Boolean {
        val id = pathParameter(webhookId, "Webhook ID")
        return http.delete("webhooks/$id", options = options)
    }
}

data class ListWebhooksRequest(
    val companyId: String,
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
) {
    init {
        require(companyId.isNotBlank()) { "Company ID must not be blank." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            add("company_id" to companyId)
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
        }
}
