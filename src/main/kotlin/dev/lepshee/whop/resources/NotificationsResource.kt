package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.models.notifications.CreateNotificationRequest
import dev.lepshee.whop.models.notifications.NotificationResult

/** Operations for sending Whop push notifications. */
class NotificationsResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun create(
        request: CreateNotificationRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): NotificationResult = http.post("notifications", request, options)
}
