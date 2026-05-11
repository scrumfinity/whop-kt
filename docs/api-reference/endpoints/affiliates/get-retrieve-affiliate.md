# GET /affiliates/{id} — Retrieve affiliate

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/affiliates/{id}`
- **Operation ID:** `retrieveAffiliate`
- **Tags:** `Affiliates`
- **Required bearer scopes:** `affiliate:basic:read`

## Description

Retrieves the details of an existing affiliate.

Required permissions:
 - `affiliate:basic:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `id` | path | yes | `string` | The unique identifier of the affiliate. | aff_xxxxxxxxxxxxxx |

## Request body

_No request body._

## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `Affiliate` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/Affiliate"
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

const affiliate = await client.affiliates.retrieve('aff_xxxxxxxxxxxxxx');

console.log(affiliate.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
affiliate = client.affiliates.retrieve(
    "aff_xxxxxxxxxxxxxx",
)
print(affiliate.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

affiliate = whop.affiliates.retrieve("aff_xxxxxxxxxxxxxx")

puts(affiliate)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  Affiliate:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the affiliate.
        example: aff_xxxxxxxxxxxxxx
      status:
        oneOf:
        - "$ref": "#/components/schemas/Status"
        - type: 'null'
        description: The status of the affiliate
      created_at:
        type: string
        format: date-time
        description: The datetime the affiliate was created.
        example: '2023-12-01T05:00:00.401Z'
      updated_at:
        type: string
        format: date-time
        description: The datetime the affiliate was last updated.
        example: '2023-12-01T05:00:00.401Z'
      total_referrals_count:
        type: integer
        description: The total referrals of the affiliate
        example: 42
      total_referral_earnings_usd:
        type: string
        description: The total commission earnings paid to this affiliate, formatted
          as a USD currency string
      total_overrides_count:
        type: integer
        description: The total count of all overrides for this affiliate
        example: 42
      customer_retention_rate:
        type: string
        description: The percentage of referred customers who are still active members
      customer_retention_rate_ninety_days:
        type: string
        description: The percentage of referred customers who remained active over
          the last 90 days
      monthly_recurring_revenue_usd:
        type: string
        description: The monthly recurring revenue generated by this affiliate's referrals,
          formatted as a USD currency string
      total_revenue_usd:
        type: string
        description: The total revenue generated from this affiliate's referrals,
          formatted as a USD currency string
      active_members_count:
        type: integer
        description: The total active members of the affiliate
        example: 42
      user:
        type: object
        properties:
          id:
            type: string
            description: The unique identifier for the user.
            example: user_xxxxxxxxxxxxx
          name:
            type:
            - string
            - 'null'
            description: The display name set on the user's Whop profile. Null if
              the user has not set a name.
            example: Jane Smith
          username:
            type:
            - string
            - 'null'
            description: The unique username chosen by the user for their Whop profile.
              Null if the user has not set a username.
            example: janesmith
        required:
        - id
        - name
        - username
        description: The user attached to this affiliate
      company:
        type: object
        properties:
          id:
            type: string
            description: The unique identifier for the company.
            example: biz_xxxxxxxxxxxxxx
          title:
            type: string
            description: The written name of the company.
        required:
        - id
        - title
        description: The company attached to this affiliate
    required:
    - id
    - status
    - created_at
    - updated_at
    - total_referrals_count
    - total_referral_earnings_usd
    - total_overrides_count
    - customer_retention_rate
    - customer_retention_rate_ninety_days
    - monthly_recurring_revenue_usd
    - total_revenue_usd
    - active_members_count
    - user
    - company
    description: An affiliate tracks a user's referral performance and commission
      earnings for a company, including retention rates, revenue metrics, and payout
      configurations.
  Status:
    type: string
    enum:
    - active
    - archived
    - deleted
    description: Statuses for resources
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
  "/affiliates/{id}":
    get:
      tags:
      - Affiliates
      operationId: retrieveAffiliate
      summary: Retrieve affiliate
      description: |-
        Retrieves the details of an existing affiliate.

        Required permissions:
         - `affiliate:basic:read`
      parameters:
      - name: id
        in: path
        required: true
        description: The unique identifier of the affiliate.
        schema:
          type: string
          example: aff_xxxxxxxxxxxxxx
      x-group-title: CRM
      security:
      - bearerAuth:
        - affiliate:basic:read
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/Affiliate"
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
      x-codeSamples:
      - lang: JavaScript
        source: |-
          import Whop from '@whop/sdk';

          const client = new Whop({
            apiKey: process.env['WHOP_API_KEY'], // This is the default and can be omitted
          });

          const affiliate = await client.affiliates.retrieve('aff_xxxxxxxxxxxxxx');

          console.log(affiliate.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          affiliate = client.affiliates.retrieve(
              "aff_xxxxxxxxxxxxxx",
          )
          print(affiliate.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          affiliate = whop.affiliates.retrieve("aff_xxxxxxxxxxxxxx")

          puts(affiliate)
components:
  schemas:
    Affiliate:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the affiliate.
          example: aff_xxxxxxxxxxxxxx
        status:
          oneOf:
          - "$ref": "#/components/schemas/Status"
          - type: 'null'
          description: The status of the affiliate
        created_at:
          type: string
          format: date-time
          description: The datetime the affiliate was created.
          example: '2023-12-01T05:00:00.401Z'
        updated_at:
          type: string
          format: date-time
          description: The datetime the affiliate was last updated.
          example: '2023-12-01T05:00:00.401Z'
        total_referrals_count:
          type: integer
          description: The total referrals of the affiliate
          example: 42
        total_referral_earnings_usd:
          type: string
          description: The total commission earnings paid to this affiliate, formatted
            as a USD currency string
        total_overrides_count:
          type: integer
          description: The total count of all overrides for this affiliate
          example: 42
        customer_retention_rate:
          type: string
          description: The percentage of referred customers who are still active members
        customer_retention_rate_ninety_days:
          type: string
          description: The percentage of referred customers who remained active over
            the last 90 days
        monthly_recurring_revenue_usd:
          type: string
          description: The monthly recurring revenue generated by this affiliate's
            referrals, formatted as a USD currency string
        total_revenue_usd:
          type: string
          description: The total revenue generated from this affiliate's referrals,
            formatted as a USD currency string
        active_members_count:
          type: integer
          description: The total active members of the affiliate
          example: 42
        user:
          type: object
          properties:
            id:
              type: string
              description: The unique identifier for the user.
              example: user_xxxxxxxxxxxxx
            name:
              type:
              - string
              - 'null'
              description: The display name set on the user's Whop profile. Null if
                the user has not set a name.
              example: Jane Smith
            username:
              type:
              - string
              - 'null'
              description: The unique username chosen by the user for their Whop profile.
                Null if the user has not set a username.
              example: janesmith
          required:
          - id
          - name
          - username
          description: The user attached to this affiliate
        company:
          type: object
          properties:
            id:
              type: string
              description: The unique identifier for the company.
              example: biz_xxxxxxxxxxxxxx
            title:
              type: string
              description: The written name of the company.
          required:
          - id
          - title
          description: The company attached to this affiliate
      required:
      - id
      - status
      - created_at
      - updated_at
      - total_referrals_count
      - total_referral_earnings_usd
      - total_overrides_count
      - customer_retention_rate
      - customer_retention_rate_ninety_days
      - monthly_recurring_revenue_usd
      - total_revenue_usd
      - active_members_count
      - user
      - company
      description: An affiliate tracks a user's referral performance and commission
        earnings for a company, including retention rates, revenue metrics, and payout
        configurations.
    Status:
      type: string
      enum:
      - active
      - archived
      - deleted
      description: Statuses for resources
```
