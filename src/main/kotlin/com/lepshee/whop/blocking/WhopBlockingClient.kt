package com.lepshee.whop.blocking

import com.lepshee.whop.WhopClient
import com.lepshee.whop.WhopClientConfig
import com.lepshee.whop.WhopRequestOptions
import com.lepshee.whop.core.WhopHttpTransport
import com.lepshee.whop.models.checkout.CheckoutConfiguration
import com.lepshee.whop.models.checkout.CreateCheckoutConfigurationRequest
import com.lepshee.whop.models.companies.Company
import com.lepshee.whop.models.companies.CreateCompanyRequest
import com.lepshee.whop.models.memberships.Membership
import com.lepshee.whop.models.payments.Payment
import com.lepshee.whop.models.products.Product
import com.lepshee.whop.models.transfers.CreateTransferRequest
import com.lepshee.whop.models.transfers.Transfer
import com.lepshee.whop.resources.ListPaymentsRequest
import kotlinx.coroutines.runBlocking
import java.io.Closeable

/**
 * Blocking facade for Java and Spring MVC applications that cannot call suspend functions directly.
 *
 * Prefer [WhopClient] in coroutine-aware Kotlin applications. This wrapper is intentionally thin and
 * delegates all behavior to the underlying coroutine client.
 */
class WhopBlockingClient(
    /** Underlying coroutine client for advanced operations not wrapped by this facade. */
    val client: WhopClient,
) : Closeable {
    constructor(config: WhopClientConfig) : this(WhopClient(config))

    constructor(config: WhopClientConfig, transport: WhopHttpTransport) : this(WhopClient(config, transport))

    /** Runs an arbitrary suspend block against the underlying [WhopClient]. */
    fun <T> execute(block: suspend (WhopClient) -> T): T = runBlocking { block(client) }

    fun createCheckoutConfiguration(
        request: CreateCheckoutConfigurationRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): CheckoutConfiguration = execute { it.checkoutConfigurations.create(request, options) }

    fun retrievePayment(
        paymentId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Payment = execute { it.payments.retrieve(paymentId, options) }

    fun listPayments(
        request: ListPaymentsRequest = ListPaymentsRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): List<Payment> = execute { it.payments.list(request, options).data }

    fun retrieveProduct(
        productId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Product = execute { it.products.retrieve(productId, options) }

    fun retrieveMembership(
        membershipId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Membership = execute { it.memberships.retrieve(membershipId, options) }

    fun createCompany(
        request: CreateCompanyRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Company = execute { it.companies.create(request, options) }

    fun createTransfer(
        request: CreateTransferRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Transfer = execute { it.transfers.create(request, options) }

    override fun close() {
        client.close()
    }
}

/** Creates a blocking facade over this coroutine client. */
fun WhopClient.blocking(): WhopBlockingClient = WhopBlockingClient(this)
