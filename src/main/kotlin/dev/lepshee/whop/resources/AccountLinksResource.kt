package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.models.accountlinks.AccountLink
import dev.lepshee.whop.models.accountlinks.CreateAccountLinkRequest

/** Operations for sub-merchant hosted account links. */
class AccountLinksResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun create(
        request: dev.lepshee.whop.models.accountlinks.CreateAccountLinkRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): dev.lepshee.whop.models.accountlinks.AccountLink = http.post("account_links", request, options)
}
