# PATCH /webhooks/{id} — Update webhook

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `PATCH`
- **Path:** `/webhooks/{id}`
- **Operation ID:** `updateWebhook`
- **Tags:** `Webhooks`
- **Required bearer scopes:** `developer:manage_webhook`

## Description

Updates a webhook

Required permissions:
 - `developer:manage_webhook`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `id` | path | yes | `string` | The ID of the Webhook to update | hook_xxxxxxxxxxxxx |

## Request body

Required: **no**
Content types: `application/json`

### `application/json` request schema

```yaml
type: object
properties:
  api_version:
    oneOf:
    - "$ref": "#/components/schemas/ApiVersion"
    - type: 'null'
    description: The API version for this webhook
  child_resource_events:
    type:
    - boolean
    - 'null'
    description: Whether or not to send events for child resources.
  enabled:
    type:
    - boolean
    - 'null'
    description: Whether or not the webhook is enabled.
  events:
    type:
    - array
    - 'null'
    items:
      "$ref": "#/components/schemas/WebhookEvent"
    description: The events to send the webhook for.
  url:
    type:
    - string
    - 'null'
    description: The URL to send the webhook to.
    example: https://example.com/path
required: []
description: Parameters for UpdateWebhook
```


## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `Webhook` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/Webhook"
```

### `400` response schemas

#### `application/json`

```yaml
type: object
properties:
  error:
    type: object
    properties:
      type:
        type: string
      message:
        type: string
      code:
        type:
        - string
        - 'null'
        description: A short string indicating the specific error code, e.g. 'parameter_missing',
          'parameter_invalid', 'invalid_json'
      param:
        type:
        - string
        - 'null'
        description: The parameter that caused the error, if applicable
    required:
    - type
    - message
required:
- error
```

Example:

```yaml
error:
  type: invalid_request_error
  code: parameter_invalid
  message: Invalid request parameters.
  param: null
```

### `401` response schemas

#### `application/json`

```yaml
type: object
properties:
  error:
    type: object
    properties:
      type:
        type: string
      message:
        type: string
      code:
        type:
        - string
        - 'null'
        description: A short string indicating the specific error code, e.g. 'parameter_missing',
          'parameter_invalid', 'invalid_json'
      param:
        type:
        - string
        - 'null'
        description: The parameter that caused the error, if applicable
    required:
    - type
    - message
required:
- error
```

Example:

```yaml
error:
  type: unauthorized
  message: Invalid or missing API key
```

### `403` response schemas

#### `application/json`

```yaml
type: object
properties:
  error:
    type: object
    properties:
      type:
        type: string
      message:
        type: string
      code:
        type:
        - string
        - 'null'
        description: A short string indicating the specific error code, e.g. 'parameter_missing',
          'parameter_invalid', 'invalid_json'
      param:
        type:
        - string
        - 'null'
        description: The parameter that caused the error, if applicable
    required:
    - type
    - message
required:
- error
```

Example:

```yaml
error:
  type: forbidden
  message: You do not have permission to access this resource
```

### `404` response schemas

#### `application/json`

```yaml
type: object
properties:
  error:
    type: object
    properties:
      type:
        type: string
      message:
        type: string
      code:
        type:
        - string
        - 'null'
        description: A short string indicating the specific error code, e.g. 'parameter_missing',
          'parameter_invalid', 'invalid_json'
      param:
        type:
        - string
        - 'null'
        description: The parameter that caused the error, if applicable
    required:
    - type
    - message
required:
- error
```

Example:

```yaml
error:
  type: not_found
  message: Resource not found
```

### `422` response schemas

#### `application/json`

```yaml
type: object
properties:
  error:
    type: object
    properties:
      type:
        type: string
      message:
        type: string
      code:
        type:
        - string
        - 'null'
        description: A short string indicating the specific error code, e.g. 'parameter_missing',
          'parameter_invalid', 'invalid_json'
      param:
        type:
        - string
        - 'null'
        description: The parameter that caused the error, if applicable
    required:
    - type
    - message
required:
- error
```

Example:

```yaml
error:
  type: verification_required
  message: Verification is required to complete this request
```

### `500` response schemas

#### `application/json`

```yaml
type: object
properties:
  error:
    type: object
    properties:
      type:
        type: string
      message:
        type: string
      code:
        type:
        - string
        - 'null'
        description: A short string indicating the specific error code, e.g. 'parameter_missing',
          'parameter_invalid', 'invalid_json'
      param:
        type:
        - string
        - 'null'
        description: The parameter that caused the error, if applicable
    required:
    - type
    - message
required:
- error
```

Example:

```yaml
error:
  type: internal_server_error
  message: An unexpected error occurred
```


## SDK examples

### JavaScript

```javascript
import Whop from '@whop/sdk';

const client = new Whop({
  apiKey: process.env['WHOP_API_KEY'], // This is the default and can be omitted
});

const webhook = await client.webhooks.update('hook_xxxxxxxxxxxxx');

console.log(webhook.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
webhook = client.webhooks.update(
    id="hook_xxxxxxxxxxxxx",
)
print(webhook.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

webhook = whop.webhooks.update("hook_xxxxxxxxxxxxx")

puts(webhook)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  ApiVersion:
    type: string
    enum:
    - v1
    - v2
    - v5
    description: The different API versions
  Webhook:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the webhook.
        example: hook_xxxxxxxxxxxxx
      url:
        type: string
        description: The destination URL where webhook payloads are delivered via
          HTTP POST.
        example: https://example.com/path
      enabled:
        type: boolean
        description: Whether this webhook endpoint is currently active and receiving
          events.
      events:
        type: array
        items:
          "$ref": "#/components/schemas/WebhookEvent"
        description: The list of event types this webhook is subscribed to.
      api_version:
        "$ref": "#/components/schemas/ApiVersion"
        description: The API version used to format payloads sent to this webhook
          endpoint.
      created_at:
        type: string
        format: date-time
        description: The datetime the webhook was created.
        example: '2023-12-01T05:00:00.401Z'
      child_resource_events:
        type: boolean
        description: Whether events are sent for child resources. For example, if
          the webhook is on a company, enabling this sends events only from the company's
          sub-merchants (child companies).
      testable_events:
        type: array
        items:
          "$ref": "#/components/schemas/WebhookEvent"
        description: The subset of subscribed event types that support sending test
          payloads.
      resource_id:
        type: string
        description: The ID of the resource (company or product) this webhook is attached
          to.
    required:
    - id
    - url
    - enabled
    - events
    - api_version
    - created_at
    - child_resource_events
    - testable_events
    - resource_id
    description: A webhook endpoint that receives event notifications for a company
      via HTTP POST.
  WebhookEvent:
    type: string
    enum:
    - invoice.created
    - invoice.marked_uncollectible
    - invoice.paid
    - invoice.past_due
    - invoice.voided
    - membership.activated
    - membership.deactivated
    - entry.created
    - entry.approved
    - entry.denied
    - entry.deleted
    - setup_intent.requires_action
    - setup_intent.succeeded
    - setup_intent.canceled
    - withdrawal.created
    - withdrawal.updated
    - course_lesson_interaction.completed
    - payout_method.created
    - verification.succeeded
    - payout_account.status_updated
    - resolution_center_case.created
    - resolution_center_case.updated
    - resolution_center_case.decided
    - payment.created
    - payment.succeeded
    - payment.failed
    - payment.pending
    - dispute.created
    - dispute.updated
    - refund.created
    - refund.updated
    - dispute_alert.created
    - membership.cancel_at_period_end_changed
    description: The different event types available
```

## Complete OpenAPI excerpt

```yaml
openapi: 3.1.0
info:
  title: Whop API
  description: The Whop REST API. Please see https://docs.whop.com/developer/api/getting-started
    for more details.
  termsOfService: https://whop.com/tos-developer-api/
  version: 1.0.0
servers:
- url: https://api.whop.com/api/v1
  description: Production Whop API
paths:
  "/webhooks/{id}":
    patch:
      tags:
      - Webhooks
      operationId: updateWebhook
      summary: Update webhook
      description: |-
        Updates a webhook

        Required permissions:
         - `developer:manage_webhook`
      parameters:
      - name: id
        in: path
        required: true
        description: The ID of the Webhook to update
        schema:
          type: string
          example: hook_xxxxxxxxxxxxx
      x-group-title: Developer
      security:
      - bearerAuth:
        - developer:manage_webhook
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/Webhook"
        '400':
          description: Bad request
          content:
            application/json:
              schema:
                type: object
                properties:
                  error:
                    type: object
                    properties:
                      type:
                        type: string
                      message:
                        type: string
                      code:
                        type:
                        - string
                        - 'null'
                        description: A short string indicating the specific error
                          code, e.g. 'parameter_missing', 'parameter_invalid', 'invalid_json'
                      param:
                        type:
                        - string
                        - 'null'
                        description: The parameter that caused the error, if applicable
                    required:
                    - type
                    - message
                required:
                - error
              example:
                error:
                  type: invalid_request_error
                  code: parameter_invalid
                  message: Invalid request parameters.
                  param: null
        '401':
          description: Unauthorized
          content:
            application/json:
              schema:
                type: object
                properties:
                  error:
                    type: object
                    properties:
                      type:
                        type: string
                      message:
                        type: string
                      code:
                        type:
                        - string
                        - 'null'
                        description: A short string indicating the specific error
                          code, e.g. 'parameter_missing', 'parameter_invalid', 'invalid_json'
                      param:
                        type:
                        - string
                        - 'null'
                        description: The parameter that caused the error, if applicable
                    required:
                    - type
                    - message
                required:
                - error
              example:
                error:
                  type: unauthorized
                  message: Invalid or missing API key
        '403':
          description: Forbidden
          content:
            application/json:
              schema:
                type: object
                properties:
                  error:
                    type: object
                    properties:
                      type:
                        type: string
                      message:
                        type: string
                      code:
                        type:
                        - string
                        - 'null'
                        description: A short string indicating the specific error
                          code, e.g. 'parameter_missing', 'parameter_invalid', 'invalid_json'
                      param:
                        type:
                        - string
                        - 'null'
                        description: The parameter that caused the error, if applicable
                    required:
                    - type
                    - message
                required:
                - error
              example:
                error:
                  type: forbidden
                  message: You do not have permission to access this resource
        '404':
          description: Not found
          content:
            application/json:
              schema:
                type: object
                properties:
                  error:
                    type: object
                    properties:
                      type:
                        type: string
                      message:
                        type: string
                      code:
                        type:
                        - string
                        - 'null'
                        description: A short string indicating the specific error
                          code, e.g. 'parameter_missing', 'parameter_invalid', 'invalid_json'
                      param:
                        type:
                        - string
                        - 'null'
                        description: The parameter that caused the error, if applicable
                    required:
                    - type
                    - message
                required:
                - error
              example:
                error:
                  type: not_found
                  message: Resource not found
        '422':
          description: Verification required
          content:
            application/json:
              schema:
                type: object
                properties:
                  error:
                    type: object
                    properties:
                      type:
                        type: string
                      message:
                        type: string
                      code:
                        type:
                        - string
                        - 'null'
                        description: A short string indicating the specific error
                          code, e.g. 'parameter_missing', 'parameter_invalid', 'invalid_json'
                      param:
                        type:
                        - string
                        - 'null'
                        description: The parameter that caused the error, if applicable
                    required:
                    - type
                    - message
                required:
                - error
              example:
                error:
        '500':
          description: Internal server error
          content:
            application/json:
              schema:
                type: object
                properties:
                  error:
                    type: object
                    properties:
                      type:
                        type: string
                      message:
                        type: string
                      code:
                        type:
                        - string
                        - 'null'
                        description: A short string indicating the specific error
                          code, e.g. 'parameter_missing', 'parameter_invalid', 'invalid_json'
                      param:
                        type:
                        - string
                        - 'null'
                        description: The parameter that caused the error, if applicable
                    required:
                    - type
                    - message
                required:
                - error
              example:
                error:
                  type: internal_server_error
                  message: An unexpected error occurred
      requestBody:
        required: false
        content:
          application/json:
            schema:
              type: object
              properties:
                api_version:
                  oneOf:
                  - "$ref": "#/components/schemas/ApiVersion"
                  - type: 'null'
                  description: The API version for this webhook
                child_resource_events:
                  type:
                  - boolean
                  - 'null'
                  description: Whether or not to send events for child resources.
                enabled:
                  type:
                  - boolean
                  - 'null'
                  description: Whether or not the webhook is enabled.
                events:
                  type:
                  - array
                  - 'null'
                  items:
                    "$ref": "#/components/schemas/WebhookEvent"
                  description: The events to send the webhook for.
                url:
                  type:
                  - string
                  - 'null'
                  description: The URL to send the webhook to.
                  example: https://example.com/path
              required: []
              description: Parameters for UpdateWebhook
      x-codeSamples:
      - lang: JavaScript
        source: |-
          import Whop from '@whop/sdk';

          const client = new Whop({
            apiKey: process.env['WHOP_API_KEY'], // This is the default and can be omitted
          });

          const webhook = await client.webhooks.update('hook_xxxxxxxxxxxxx');

          console.log(webhook.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          webhook = client.webhooks.update(
              id="hook_xxxxxxxxxxxxx",
          )
          print(webhook.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          webhook = whop.webhooks.update("hook_xxxxxxxxxxxxx")

          puts(webhook)
components:
  schemas:
    ApiVersion:
      type: string
      enum:
      - v1
      - v2
      - v5
      description: The different API versions
    Webhook:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the webhook.
          example: hook_xxxxxxxxxxxxx
        url:
          type: string
          description: The destination URL where webhook payloads are delivered via
            HTTP POST.
          example: https://example.com/path
        enabled:
          type: boolean
          description: Whether this webhook endpoint is currently active and receiving
            events.
        events:
          type: array
          items:
            "$ref": "#/components/schemas/WebhookEvent"
          description: The list of event types this webhook is subscribed to.
        api_version:
          "$ref": "#/components/schemas/ApiVersion"
          description: The API version used to format payloads sent to this webhook
            endpoint.
        created_at:
          type: string
          format: date-time
          description: The datetime the webhook was created.
          example: '2023-12-01T05:00:00.401Z'
        child_resource_events:
          type: boolean
          description: Whether events are sent for child resources. For example, if
            the webhook is on a company, enabling this sends events only from the
            company's sub-merchants (child companies).
        testable_events:
          type: array
          items:
            "$ref": "#/components/schemas/WebhookEvent"
          description: The subset of subscribed event types that support sending test
            payloads.
        resource_id:
          type: string
          description: The ID of the resource (company or product) this webhook is
            attached to.
      required:
      - id
      - url
      - enabled
      - events
      - api_version
      - created_at
      - child_resource_events
      - testable_events
      - resource_id
      description: A webhook endpoint that receives event notifications for a company
        via HTTP POST.
    WebhookEvent:
      type: string
      enum:
      - invoice.created
      - invoice.marked_uncollectible
      - invoice.paid
      - invoice.past_due
      - invoice.voided
      - membership.activated
      - membership.deactivated
      - entry.created
      - entry.approved
      - entry.denied
      - entry.deleted
      - setup_intent.requires_action
      - setup_intent.succeeded
      - setup_intent.canceled
      - withdrawal.created
      - withdrawal.updated
      - course_lesson_interaction.completed
      - payout_method.created
      - verification.succeeded
      - payout_account.status_updated
      - resolution_center_case.created
      - resolution_center_case.updated
      - resolution_center_case.decided
      - payment.created
      - payment.succeeded
      - payment.failed
      - payment.pending
      - dispute.created
      - dispute.updated
      - refund.created
      - refund.updated
      - dispute_alert.created
      - membership.cancel_at_period_end_changed
      description: The different event types available
```
