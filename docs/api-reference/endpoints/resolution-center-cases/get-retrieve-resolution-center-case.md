# GET /resolution_center_cases/{id} — Retrieve resolution center case

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/resolution_center_cases/{id}`
- **Operation ID:** `retrieveResolutionCenterCase`
- **Tags:** `Resolution center cases`
- **Required bearer scopes:** `payment:resolution_center_case:read`

## Description

Retrieves the details of an existing resolution center case.

Required permissions:
 - `payment:resolution_center_case:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `id` | path | yes | `string` | The unique identifier of the resolution center case. | reso_xxxxxxxxxxxxx |

## Request body

_No request body._

## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `ResolutionCenterCase` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/ResolutionCenterCase"
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

const resolutionCenterCase = await client.resolutionCenterCases.retrieve('reso_xxxxxxxxxxxxx');

console.log(resolutionCenterCase.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
resolution_center_case = client.resolution_center_cases.retrieve(
    "reso_xxxxxxxxxxxxx",
)
print(resolution_center_case.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

resolution_center_case = whop.resolution_center_cases.retrieve("reso_xxxxxxxxxxxxx")

puts(resolution_center_case)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  Currencies:
    type: string
    enum:
    - usd
    - sgd
    - inr
    - aud
    - brl
    - cad
    - dkk
    - eur
    - nok
    - gbp
    - sek
    - chf
    - hkd
    - huf
    - jpy
    - mxn
    - myr
    - pln
    - czk
    - nzd
    - aed
    - eth
    - ape
    - cop
    - ron
    - thb
    - bgn
    - idr
    - dop
    - php
    - try
    - krw
    - twd
    - vnd
    - pkr
    - clp
    - uyu
    - ars
    - zar
    - dzd
    - tnd
    - mad
    - kes
    - kwd
    - jod
    - all
    - xcd
    - amd
    - bsd
    - bhd
    - bob
    - bam
    - khr
    - crc
    - xof
    - egp
    - etb
    - gmd
    - ghs
    - gtq
    - gyd
    - ils
    - jmd
    - mop
    - mga
    - mur
    - mdl
    - mnt
    - nad
    - ngn
    - mkd
    - omr
    - pyg
    - pen
    - qar
    - rwf
    - sar
    - rsd
    - lkr
    - tzs
    - ttd
    - uzs
    - rub
    - btc
    - cny
    - usdt
    - kzt
    description: The available currencies on the platform
  ResolutionCenterCase:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the resolution.
        example: reso_xxxxxxxxxxxxx
      status:
        "$ref": "#/components/schemas/ResolutionCenterCaseStatuses"
        description: The current status of the resolution case, indicating which party
          needs to respond or if the case is closed.
      issue:
        "$ref": "#/components/schemas/ResolutionCenterCaseIssueTypes"
        description: The category of the dispute.
      created_at:
        type: string
        format: date-time
        description: The datetime the resolution was created.
        example: '2023-12-01T05:00:00.401Z'
      updated_at:
        type: string
        format: date-time
        description: The datetime the resolution was last updated.
        example: '2023-12-01T05:00:00.401Z'
      due_date:
        type:
        - string
        - 'null'
        format: date-time
        description: The deadline by which the next response is required. Null if
          no deadline is currently active. As an ISO 8601 timestamp.
        example: '2023-12-01T05:00:00.401Z'
      customer_appealed:
        type: boolean
        description: Whether the customer has filed an appeal after the initial resolution
          decision.
      merchant_appealed:
        type: boolean
        description: Whether the merchant has filed an appeal after the initial resolution
          decision.
      customer_response_actions:
        type: array
        items:
          "$ref": "#/components/schemas/ResolutionCenterCaseCustomerResponses"
        description: The list of actions currently available to the customer.
      merchant_response_actions:
        type: array
        items:
          "$ref": "#/components/schemas/ResolutionCenterCaseMerchantResponses"
        description: The list of actions currently available to the merchant.
      company:
        type:
        - object
        - 'null'
        properties:
          id:
            type: string
            description: The unique identifier for the company.
            example: biz_xxxxxxxxxxxxxx
          title:
            type: string
            description: The display name of the company shown to customers.
            example: Pickaxe
        required:
        - id
        - title
        description: The company involved in this resolution case. Null if the company
          no longer exists.
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
            description: The user's display name shown on their public profile.
            example: John Doe
          username:
            type: string
            description: The user's unique username shown on their public profile.
            example: johndoe42
        required:
        - id
        - name
        - username
        description: The customer (buyer) who filed this resolution case.
      platform_response_actions:
        type: array
        items:
          "$ref": "#/components/schemas/ResolutionCenterCasePlatformResponses"
        description: The list of actions currently available to the Whop platform
          for moderating this resolution.
      payment:
        type: object
        properties:
          id:
            type: string
            description: The unique identifier for the payment.
            example: pay_xxxxxxxxxxxxxx
          currency:
            oneOf:
            - "$ref": "#/components/schemas/Currencies"
            - type: 'null'
            description: The three-letter ISO currency code for this payment (e.g.,
              'usd', 'eur').
          created_at:
            type: string
            format: date-time
            description: The datetime the payment was created.
            example: '2023-12-01T05:00:00.401Z'
          paid_at:
            type:
            - string
            - 'null'
            format: date-time
            description: The time at which this payment was successfully collected.
              Null if the payment has not yet succeeded. As an ISO 8601 timestamp.
            example: '2023-12-01T05:00:00.401Z'
          total:
            type: number
            description: The total amount charged to the customer for this payment,
              including taxes and after any discounts. In the currency specified by
              the currency field.
            example: 6.9
          subtotal:
            type:
            - number
            - 'null'
            description: The payment amount before taxes and discounts are applied.
              In the currency specified by the currency field.
            example: 6.9
        required:
        - id
        - currency
        - created_at
        - paid_at
        - total
        - subtotal
        description: The payment record that is the subject of this resolution case.
      member:
        type:
        - object
        - 'null'
        properties:
          id:
            type: string
            description: The unique identifier for the extra public member.
        required:
        - id
        description: The membership record associated with the disputed payment. Null
          if the membership no longer exists.
      resolution_events:
        type: array
        items:
          type: object
          properties:
            id:
              type: string
              description: The unique identifier for the resolution event.
              example: revt_xxxxxxxxxxxxx
            action:
              "$ref": "#/components/schemas/ResolutionCenterCaseActions"
              description: The type of action recorded in this event.
            reporter_type:
              "$ref": "#/components/schemas/ResolutionCenterCaseReporters"
              description: The party who performed this action.
            details:
              type:
              - string
              - 'null'
              description: The message body or additional context provided with this
                resolution event. Null if no details were included.
              example: I did not authorize this purchase.
            created_at:
              type: string
              format: date-time
              description: The datetime the resolution event was created.
              example: '2023-12-01T05:00:00.401Z'
          required:
          - id
          - action
          - reporter_type
          - details
          - created_at
          description: A resolution event is a message or action within a resolution
            case, such as a response, escalation, or status change.
        description: The most recent 50 messages, actions, and status changes that
          have occurred during this resolution case.
    required:
    - id
    - status
    - issue
    - created_at
    - updated_at
    - due_date
    - customer_appealed
    - merchant_appealed
    - customer_response_actions
    - merchant_response_actions
    - company
    - user
    - platform_response_actions
    - payment
    - member
    - resolution_events
    description: A resolution center case is a dispute or support case between a user
      and a company, tracking the issue, status, and outcome.
  ResolutionCenterCaseActions:
    type: string
    enum:
    - created
    - responded
    - accepted
    - denied
    - appealed
    - withdrew
    - requested_more_info
    - escalated
    - dispute_opened
    - dispute_customer_won
    - dispute_merchant_won
    description: The different types of actions for a resolution event
  ResolutionCenterCaseCustomerResponses:
    type: string
    enum:
    - respond
    - appeal
    - withdraw
    description: The types of responses a customer can make to a resolution.
  ResolutionCenterCaseIssueTypes:
    type: string
    enum:
    - forgot_to_cancel
    - item_not_received
    - significantly_not_as_described
    - unauthorized_transaction
    - product_unacceptable
    description: The different types of issues a resolution can be
  ResolutionCenterCaseMerchantResponses:
    type: string
    enum:
    - accept
    - deny
    - request_more_info
    - appeal
    - respond
    description: The types of responses a merchant can make to a resolution.
  ResolutionCenterCasePlatformResponses:
    type: string
    enum:
    - request_buyer_info
    - request_merchant_info
    - merchant_wins
    - platform_refund
    - merchant_refund
    description: The types of responses the platform can make to a resolution.
  ResolutionCenterCaseReporters:
    type: string
    enum:
    - merchant
    - customer
    - platform
    - system
    description: The different types of reporters for a resolution event
  ResolutionCenterCaseStatuses:
    type: string
    enum:
    - merchant_response_needed
    - customer_response_needed
    - merchant_info_needed
    - customer_info_needed
    - under_platform_review
    - customer_won
    - merchant_won
    - customer_withdrew
    description: The statuses a resolution object can have
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
  "/resolution_center_cases/{id}":
    get:
      tags:
      - Resolution center cases
      operationId: retrieveResolutionCenterCase
      summary: Retrieve resolution center case
      description: |-
        Retrieves the details of an existing resolution center case.

        Required permissions:
         - `payment:resolution_center_case:read`
      parameters:
      - name: id
        in: path
        required: true
        description: The unique identifier of the resolution center case.
        schema:
          type: string
          example: reso_xxxxxxxxxxxxx
      x-group-title: Payins
      security:
      - bearerAuth:
        - payment:resolution_center_case:read
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/ResolutionCenterCase"
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

          const resolutionCenterCase = await client.resolutionCenterCases.retrieve('reso_xxxxxxxxxxxxx');

          console.log(resolutionCenterCase.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          resolution_center_case = client.resolution_center_cases.retrieve(
              "reso_xxxxxxxxxxxxx",
          )
          print(resolution_center_case.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          resolution_center_case = whop.resolution_center_cases.retrieve("reso_xxxxxxxxxxxxx")

          puts(resolution_center_case)
components:
  schemas:
    Currencies:
      type: string
      enum:
      - usd
      - sgd
      - inr
      - aud
      - brl
      - cad
      - dkk
      - eur
      - nok
      - gbp
      - sek
      - chf
      - hkd
      - huf
      - jpy
      - mxn
      - myr
      - pln
      - czk
      - nzd
      - aed
      - eth
      - ape
      - cop
      - ron
      - thb
      - bgn
      - idr
      - dop
      - php
      - try
      - krw
      - twd
      - vnd
      - pkr
      - clp
      - uyu
      - ars
      - zar
      - dzd
      - tnd
      - mad
      - kes
      - kwd
      - jod
      - all
      - xcd
      - amd
      - bsd
      - bhd
      - bob
      - bam
      - khr
      - crc
      - xof
      - egp
      - etb
      - gmd
      - ghs
      - gtq
      - gyd
      - ils
      - jmd
      - mop
      - mga
      - mur
      - mdl
      - mnt
      - nad
      - ngn
      - mkd
      - omr
      - pyg
      - pen
      - qar
      - rwf
      - sar
      - rsd
      - lkr
      - tzs
      - ttd
      - uzs
      - rub
      - btc
      - cny
      - usdt
      - kzt
      description: The available currencies on the platform
    ResolutionCenterCase:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the resolution.
          example: reso_xxxxxxxxxxxxx
        status:
          "$ref": "#/components/schemas/ResolutionCenterCaseStatuses"
          description: The current status of the resolution case, indicating which
            party needs to respond or if the case is closed.
        issue:
          "$ref": "#/components/schemas/ResolutionCenterCaseIssueTypes"
          description: The category of the dispute.
        created_at:
          type: string
          format: date-time
          description: The datetime the resolution was created.
          example: '2023-12-01T05:00:00.401Z'
        updated_at:
          type: string
          format: date-time
          description: The datetime the resolution was last updated.
          example: '2023-12-01T05:00:00.401Z'
        due_date:
          type:
          - string
          - 'null'
          format: date-time
          description: The deadline by which the next response is required. Null if
            no deadline is currently active. As an ISO 8601 timestamp.
          example: '2023-12-01T05:00:00.401Z'
        customer_appealed:
          type: boolean
          description: Whether the customer has filed an appeal after the initial
            resolution decision.
        merchant_appealed:
          type: boolean
          description: Whether the merchant has filed an appeal after the initial
            resolution decision.
        customer_response_actions:
          type: array
          items:
            "$ref": "#/components/schemas/ResolutionCenterCaseCustomerResponses"
          description: The list of actions currently available to the customer.
        merchant_response_actions:
          type: array
          items:
            "$ref": "#/components/schemas/ResolutionCenterCaseMerchantResponses"
          description: The list of actions currently available to the merchant.
        company:
          type:
          - object
          - 'null'
          properties:
            id:
              type: string
              description: The unique identifier for the company.
              example: biz_xxxxxxxxxxxxxx
            title:
              type: string
              description: The display name of the company shown to customers.
              example: Pickaxe
          required:
          - id
          - title
          description: The company involved in this resolution case. Null if the company
            no longer exists.
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
              description: The user's display name shown on their public profile.
              example: John Doe
            username:
              type: string
              description: The user's unique username shown on their public profile.
              example: johndoe42
          required:
          - id
          - name
          - username
          description: The customer (buyer) who filed this resolution case.
        platform_response_actions:
          type: array
          items:
            "$ref": "#/components/schemas/ResolutionCenterCasePlatformResponses"
          description: The list of actions currently available to the Whop platform
            for moderating this resolution.
        payment:
          type: object
          properties:
            id:
              type: string
              description: The unique identifier for the payment.
              example: pay_xxxxxxxxxxxxxx
            currency:
              oneOf:
              - "$ref": "#/components/schemas/Currencies"
              - type: 'null'
              description: The three-letter ISO currency code for this payment (e.g.,
                'usd', 'eur').
            created_at:
              type: string
              format: date-time
              description: The datetime the payment was created.
              example: '2023-12-01T05:00:00.401Z'
            paid_at:
              type:
              - string
              - 'null'
              format: date-time
              description: The time at which this payment was successfully collected.
                Null if the payment has not yet succeeded. As an ISO 8601 timestamp.
              example: '2023-12-01T05:00:00.401Z'
            total:
              type: number
              description: The total amount charged to the customer for this payment,
                including taxes and after any discounts. In the currency specified
                by the currency field.
              example: 6.9
            subtotal:
              type:
              - number
              - 'null'
              description: The payment amount before taxes and discounts are applied.
                In the currency specified by the currency field.
              example: 6.9
          required:
          - id
          - currency
          - created_at
          - paid_at
          - total
          - subtotal
          description: The payment record that is the subject of this resolution case.
        member:
          type:
          - object
          - 'null'
          properties:
            id:
              type: string
              description: The unique identifier for the extra public member.
          required:
          - id
          description: The membership record associated with the disputed payment.
            Null if the membership no longer exists.
        resolution_events:
          type: array
          items:
            type: object
            properties:
              id:
                type: string
                description: The unique identifier for the resolution event.
                example: revt_xxxxxxxxxxxxx
              action:
                "$ref": "#/components/schemas/ResolutionCenterCaseActions"
                description: The type of action recorded in this event.
              reporter_type:
                "$ref": "#/components/schemas/ResolutionCenterCaseReporters"
                description: The party who performed this action.
              details:
                type:
                - string
                - 'null'
                description: The message body or additional context provided with
                  this resolution event. Null if no details were included.
                example: I did not authorize this purchase.
              created_at:
                type: string
                format: date-time
                description: The datetime the resolution event was created.
                example: '2023-12-01T05:00:00.401Z'
            required:
            - id
            - action
            - reporter_type
            - details
            - created_at
            description: A resolution event is a message or action within a resolution
              case, such as a response, escalation, or status change.
          description: The most recent 50 messages, actions, and status changes that
            have occurred during this resolution case.
      required:
      - id
      - status
      - issue
      - created_at
      - updated_at
      - due_date
      - customer_appealed
      - merchant_appealed
      - customer_response_actions
      - merchant_response_actions
      - company
      - user
      - platform_response_actions
      - payment
      - member
      - resolution_events
      description: A resolution center case is a dispute or support case between a
        user and a company, tracking the issue, status, and outcome.
    ResolutionCenterCaseActions:
      type: string
      enum:
      - created
      - responded
      - accepted
      - denied
      - appealed
      - withdrew
      - requested_more_info
      - escalated
      - dispute_opened
      - dispute_customer_won
      - dispute_merchant_won
      description: The different types of actions for a resolution event
    ResolutionCenterCaseCustomerResponses:
      type: string
      enum:
      - respond
      - appeal
      - withdraw
      description: The types of responses a customer can make to a resolution.
    ResolutionCenterCaseIssueTypes:
      type: string
      enum:
      - forgot_to_cancel
      - item_not_received
      - significantly_not_as_described
      - unauthorized_transaction
      - product_unacceptable
      description: The different types of issues a resolution can be
    ResolutionCenterCaseMerchantResponses:
      type: string
      enum:
      - accept
      - deny
      - request_more_info
      - appeal
      - respond
      description: The types of responses a merchant can make to a resolution.
    ResolutionCenterCasePlatformResponses:
      type: string
      enum:
      - request_buyer_info
      - request_merchant_info
      - merchant_wins
      - platform_refund
      - merchant_refund
      description: The types of responses the platform can make to a resolution.
    ResolutionCenterCaseReporters:
      type: string
      enum:
      - merchant
      - customer
      - platform
      - system
      description: The different types of reporters for a resolution event
    ResolutionCenterCaseStatuses:
      type: string
      enum:
      - merchant_response_needed
      - customer_response_needed
      - merchant_info_needed
      - customer_info_needed
      - under_platform_review
      - customer_won
      - merchant_won
      - customer_withdrew
      description: The statuses a resolution object can have
```
