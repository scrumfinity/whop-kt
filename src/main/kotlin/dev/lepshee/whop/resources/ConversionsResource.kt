package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.models.conversions.Conversion
import dev.lepshee.whop.models.conversions.CreateConversionRequest

/** Operations for tracking conversion and engagement events. */
class ConversionsResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    /** Tracks a conversion or engagement event for a company. */
    suspend fun create(
        request: CreateConversionRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Conversion = http.post("conversions", request, options)
}
