package com.lepshee.whop

import com.lepshee.whop.core.WhopHttpExecutor
import com.lepshee.whop.core.WhopHttpTransport
import com.lepshee.whop.oauth.WhopOAuthClient
import com.lepshee.whop.resources.CheckoutConfigurationsResource
import com.lepshee.whop.resources.CompaniesResource
import com.lepshee.whop.resources.MembershipsResource
import com.lepshee.whop.resources.PaymentsResource
import com.lepshee.whop.resources.PlansResource
import com.lepshee.whop.resources.ProductsResource
import com.lepshee.whop.resources.TransfersResource
import com.lepshee.whop.resources.UsersResource
import com.lepshee.whop.resources.WebhooksResource
import com.lepshee.whop.transport.KtorWhopHttpTransport
import java.io.Closeable

/**
 * Entry point for the Whop Kotlin SDK.
 *
 * A client owns the configured authentication, HTTP transport, JSON handling, and resource groups.
 * Reuse one [WhopClient] for many requests instead of constructing a client per operation.
 */
class WhopClient(
    /** Static client configuration used for default authentication and transport behavior. */
    val config: WhopClientConfig,
    transport: WhopHttpTransport = KtorWhopHttpTransport(config),
) : Closeable {
    private val executor = WhopHttpExecutor(config, transport)

    /** Checkout configuration operations for embedded and hosted checkout flows. */
    val checkoutConfigurations: CheckoutConfigurationsResource = CheckoutConfigurationsResource(executor)

    /** Payment operations. Payment creation/fulfillment semantics should be confirmed with webhooks. */
    val payments: PaymentsResource = PaymentsResource(executor)

    /** Product catalog operations. */
    val products: ProductsResource = ProductsResource(executor)

    /** Pricing plan operations. */
    val plans: PlansResource = PlansResource(executor)

    /** User operations, including explicit access checks. */
    val users: UsersResource = UsersResource(executor)

    /** Membership operations. */
    val memberships: MembershipsResource = MembershipsResource(executor)

    /** Webhook endpoint management operations. */
    val webhooks: WebhooksResource = WebhooksResource(executor)

    /** Company operations. */
    val companies: CompaniesResource = CompaniesResource(executor)

    /** Transfer operations. */
    val transfers: TransfersResource = TransfersResource(executor)

    /** OAuth 2.1 and PKCE helper operations for user-delegated authentication. */
    val oauth: WhopOAuthClient = WhopOAuthClient(executor)

    /** Closes the underlying HTTP transport and releases network resources. */
    override fun close() {
        executor.close()
    }

    companion object {
        /**
         * Creates a client using an API key from an environment variable.
         *
         * This convenience method reads the environment once during client construction; request execution
         * does not read environment variables implicitly.
         */
        fun fromEnvironment(
            variableName: String = "WHOP_API_KEY",
            baseUrl: String = WhopEnvironment.Production.baseUrl,
        ): WhopClient {
            val apiKey =
                System.getenv(variableName)
                    ?: error("Environment variable $variableName is required to create a WhopClient.")
            return WhopClient(WhopClientConfig(apiKey = apiKey, baseUrl = baseUrl))
        }
    }
}
