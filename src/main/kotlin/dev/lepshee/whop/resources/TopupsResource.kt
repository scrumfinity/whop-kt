package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.models.topups.CreateTopupRequest
import dev.lepshee.whop.models.topups.Topup

/** Operations for adding funds to a company's platform balance. */
class TopupsResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    /** Creates a top-up by charging a stored payment method. */
    suspend fun create(
        request: CreateTopupRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Topup = http.post("topups", request, options)
}
