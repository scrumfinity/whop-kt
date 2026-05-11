package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.models.accesstokens.AccessToken
import dev.lepshee.whop.models.accesstokens.CreateAccessTokenRequest

/** Operations for creating scoped access tokens. */
class AccessTokensResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun create(
        request: CreateAccessTokenRequest =
            CreateAccessTokenRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): AccessToken = http.post("access_tokens", request, options)
}
