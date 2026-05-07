# Webhooks

Whop webhooks follow the Standard Webhooks signing model.

## Required headers

- `webhook-id`
- `webhook-timestamp`
- `webhook-signature`

The verifier signs `{webhook-id}.{webhook-timestamp}.{raw_body}` with HMAC-SHA256 and accepts `v1,<base64-signature>` values.

```kotlin
val verifier = WhopWebhookVerifier(System.getenv("WHOP_WEBHOOK_SECRET"))
val event = verifier.verifyAndParse(rawBody, headers)
```

## Handling guidance

- Verify before parsing or trusting the payload.
- Return 2xx quickly.
- Queue slow fulfillment work.
- Deduplicate with `event.id`.
- Treat event order as non-deterministic.

## Common event types

- `payment.succeeded`
- `membership.activated`
- `membership.deactivated`
- `entry.created`
- `refund.created`
- `dispute.created`

Unknown verified events are returned as `WhopWebhookEvent.Generic`.
