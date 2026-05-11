# GET /refunds — List refunds

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/refunds`
- **Operation ID:** `listRefund`
- **Tags:** `Refunds`
- **Required bearer scopes:** `payment:basic:read`

## Description

Returns a paginated list of refunds, with optional filtering by payment, company, user, and creation date.

Required permissions:
 - `payment:basic:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `after` | query | no | `string \| null` | Returns the elements in the list that come after the specified cursor. |  |
| `before` | query | no | `string \| null` | Returns the elements in the list that come before the specified cursor. |  |
| `first` | query | no | `integer \| null` | Returns the first _n_ elements from the list. | 42 |
| `last` | query | no | `integer \| null` | Returns the last _n_ elements from the list. | 42 |
| `payment_id` | query | no | `string \| null` | Filter refunds to only those associated with this specific payment. | pay_xxxxxxxxxxxxxx |
| `company_id` | query | no | `string \| null` | Filter refunds to only those belonging to this company. | biz_xxxxxxxxxxxxxx |
| `user_id` | query | no | `string \| null` | Filter refunds to only those associated with this specific user. | user_xxxxxxxxxxxxx |
| `direction` | query | no | `Direction \| null` | The sort direction for ordering results, either ascending or descending. |  |
| `created_before` | query | no | `string \| null / date-time` | Only return refunds created before this timestamp. | 2023-12-01T05:00:00.401Z |
| `created_after` | query | no | `string \| null / date-time` | Only return refunds created after this timestamp. | 2023-12-01T05:00:00.401Z |

## Request body

_No request body._

## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `object` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
type: object
properties:
  data:
    type: array
    items:
      "$ref": "#/components/schemas/RefundListItem"
    description: A list of nodes.
  page_info:
    "$ref": "#/components/schemas/PageInfo"
    description: Information to aid in pagination.
required:
- data
- page_info
description: The connection type for Refund.
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

// Automatically fetches more pages as needed.
for await (const refundListResponse of client.refunds.list()) {
  console.log(refundListResponse.id);
}
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
page = client.refunds.list()
page = page.data[0]
print(page.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

page = whop.refunds.list

puts(page)
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
  Direction:
    type: string
    enum:
    - asc
    - desc
    description: The direction of the sort.
  PageInfo:
    type: object
    properties:
      end_cursor:
        type:
        - string
        - 'null'
        description: When paginating forwards, the cursor to continue.
      start_cursor:
        type:
        - string
        - 'null'
        description: When paginating backwards, the cursor to continue.
      has_next_page:
        type: boolean
        description: When paginating forwards, are there more items?
      has_previous_page:
        type: boolean
        description: When paginating backwards, are there more items?
    required:
    - end_cursor
    - start_cursor
    - has_next_page
    - has_previous_page
    description: Information about pagination in a connection.
  PaymentProviders:
    type: string
    enum:
    - stripe
    - coinbase
    - paypal
    - apple
    - sezzle
    - splitit
    - platform_balance
    - multi_psp
    - adyen
    - claritypay
    - checkout_dot_com
    - airwallex
    - coinflow
    - sequra
    - dlocal
    description: The different payment providers.
  RefundListItem:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the refund.
        example: rf_xxxxxxxxxxxxxxx
      amount:
        type: number
        description: The refunded amount as a decimal in the specified currency, such
          as 10.43 for $10.43 USD.
        example: 6.9
      currency:
        "$ref": "#/components/schemas/Currencies"
        description: The three-letter ISO currency code for the refunded amount.
      status:
        "$ref": "#/components/schemas/RefundStatuses"
        description: The current processing status of the refund, such as pending,
          succeeded, or failed.
      created_at:
        type: string
        format: date-time
        description: The datetime the refund was created.
        example: '2023-12-01T05:00:00.401Z'
      provider:
        "$ref": "#/components/schemas/PaymentProviders"
        description: The payment provider that processed the refund.
      provider_created_at:
        type:
        - string
        - 'null'
        format: date-time
        description: The timestamp when the refund was created in the payment provider's
          system. Null if not available from the provider.
        example: '2023-12-01T05:00:00.401Z'
      reference_status:
        oneOf:
        - "$ref": "#/components/schemas/RefundReferenceStatuses"
        - type: 'null'
        description: The availability status of the refund tracking reference from
          the payment processor. Null if no reference was provided.
      reference_type:
        oneOf:
        - "$ref": "#/components/schemas/RefundReferenceTypes"
        - type: 'null'
        description: The type of tracking reference provided by the payment processor,
          such as an acquirer reference number. Null if no reference was provided.
      reference_value:
        type:
        - string
        - 'null'
        description: The tracking reference value from the payment processor, used
          to trace the refund through banking networks. Null if no reference was provided.
        example: '74850120752'
      payment:
        type:
        - object
        - 'null'
        properties:
          id:
            type: string
            description: The unique identifier for the payment.
            example: pay_xxxxxxxxxxxxxx
        required:
        - id
        description: The original payment that this refund was issued against. Null
          if the payment is no longer available.
    required:
    - id
    - amount
    - currency
    - status
    - created_at
    - provider
    - provider_created_at
    - reference_status
    - reference_type
    - reference_value
    - payment
    description: A refund represents a full or partial reversal of a payment, including
      the amount, status, and payment provider.
  RefundReferenceStatuses:
    type: string
    enum:
    - available
    - pending
    - unavailable
    description: The status of the refund reference.
  RefundReferenceTypes:
    type: string
    enum:
    - acquirer_reference_number
    - retrieval_reference_number
    - system_trace_audit_number
    description: The type of refund reference that was made available by the payment
      provider.
  RefundStatuses:
    type: string
    enum:
    - pending
    - requires_action
    - succeeded
    - failed
    - canceled
    description: The different statuses for a Refund object
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
  "/refunds":
    get:
      tags:
      - Refunds
      operationId: listRefund
      summary: List refunds
      description: |-
        Returns a paginated list of refunds, with optional filtering by payment, company, user, and creation date.

        Required permissions:
         - `payment:basic:read`
      parameters:
      - name: after
        in: query
        required: false
        schema:
          type:
          - string
          - 'null'
          description: Returns the elements in the list that come after the specified
            cursor.
        explode: true
        style: form
      - name: before
        in: query
        required: false
        schema:
          type:
          - string
          - 'null'
          description: Returns the elements in the list that come before the specified
            cursor.
        explode: true
        style: form
      - name: first
        in: query
        required: false
        schema:
          type:
          - integer
          - 'null'
          description: Returns the first _n_ elements from the list.
          example: 42
        explode: true
        style: form
      - name: last
        in: query
        required: false
        schema:
          type:
          - integer
          - 'null'
          description: Returns the last _n_ elements from the list.
          example: 42
        explode: true
        style: form
      - name: payment_id
        in: query
        required: false
        schema:
          type:
          - string
          - 'null'
          description: Filter refunds to only those associated with this specific
            payment.
          example: pay_xxxxxxxxxxxxxx
        explode: true
        style: form
      - name: company_id
        in: query
        required: false
        schema:
          type:
          - string
          - 'null'
          description: Filter refunds to only those belonging to this company.
          example: biz_xxxxxxxxxxxxxx
        explode: true
        style: form
      - name: user_id
        in: query
        required: false
        schema:
          type:
          - string
          - 'null'
          description: Filter refunds to only those associated with this specific
            user.
          example: user_xxxxxxxxxxxxx
        explode: true
        style: form
      - name: direction
        in: query
        required: false
        schema:
          oneOf:
          - "$ref": "#/components/schemas/Direction"
          - type: 'null'
          description: The sort direction for ordering results, either ascending or
            descending.
        explode: true
        style: form
      - name: created_before
        in: query
        required: false
        schema:
          type:
          - string
          - 'null'
          format: date-time
          description: Only return refunds created before this timestamp.
          example: '2023-12-01T05:00:00.401Z'
        explode: true
        style: form
      - name: created_after
        in: query
        required: false
        schema:
          type:
          - string
          - 'null'
          format: date-time
          description: Only return refunds created after this timestamp.
          example: '2023-12-01T05:00:00.401Z'
        explode: true
        style: form
      x-group-title: Payins
      security:
      - bearerAuth:
        - payment:basic:read
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                type: object
                properties:
                  data:
                    type: array
                    items:
                      "$ref": "#/components/schemas/RefundListItem"
                    description: A list of nodes.
                  page_info:
                    "$ref": "#/components/schemas/PageInfo"
                    description: Information to aid in pagination.
                required:
                - data
                - page_info
                description: The connection type for Refund.
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

          // Automatically fetches more pages as needed.
          for await (const refundListResponse of client.refunds.list()) {
            console.log(refundListResponse.id);
          }
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          page = client.refunds.list()
          page = page.data[0]
          print(page.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          page = whop.refunds.list

          puts(page)
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
    Direction:
      type: string
      enum:
      - asc
      - desc
      description: The direction of the sort.
    PageInfo:
      type: object
      properties:
        end_cursor:
          type:
          - string
          - 'null'
          description: When paginating forwards, the cursor to continue.
        start_cursor:
          type:
          - string
          - 'null'
          description: When paginating backwards, the cursor to continue.
        has_next_page:
          type: boolean
          description: When paginating forwards, are there more items?
        has_previous_page:
          type: boolean
          description: When paginating backwards, are there more items?
      required:
      - end_cursor
      - start_cursor
      - has_next_page
      - has_previous_page
      description: Information about pagination in a connection.
    PaymentProviders:
      type: string
      enum:
      - stripe
      - coinbase
      - paypal
      - apple
      - sezzle
      - splitit
      - platform_balance
      - multi_psp
      - adyen
      - claritypay
      - checkout_dot_com
      - airwallex
      - coinflow
      - sequra
      - dlocal
      description: The different payment providers.
    RefundListItem:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the refund.
          example: rf_xxxxxxxxxxxxxxx
        amount:
          type: number
          description: The refunded amount as a decimal in the specified currency,
            such as 10.43 for $10.43 USD.
          example: 6.9
        currency:
          "$ref": "#/components/schemas/Currencies"
          description: The three-letter ISO currency code for the refunded amount.
        status:
          "$ref": "#/components/schemas/RefundStatuses"
          description: The current processing status of the refund, such as pending,
            succeeded, or failed.
        created_at:
          type: string
          format: date-time
          description: The datetime the refund was created.
          example: '2023-12-01T05:00:00.401Z'
        provider:
          "$ref": "#/components/schemas/PaymentProviders"
          description: The payment provider that processed the refund.
        provider_created_at:
          type:
          - string
          - 'null'
          format: date-time
          description: The timestamp when the refund was created in the payment provider's
            system. Null if not available from the provider.
          example: '2023-12-01T05:00:00.401Z'
        reference_status:
          oneOf:
          - "$ref": "#/components/schemas/RefundReferenceStatuses"
          - type: 'null'
          description: The availability status of the refund tracking reference from
            the payment processor. Null if no reference was provided.
        reference_type:
          oneOf:
          - "$ref": "#/components/schemas/RefundReferenceTypes"
          - type: 'null'
          description: The type of tracking reference provided by the payment processor,
            such as an acquirer reference number. Null if no reference was provided.
        reference_value:
          type:
          - string
          - 'null'
          description: The tracking reference value from the payment processor, used
            to trace the refund through banking networks. Null if no reference was
            provided.
          example: '74850120752'
        payment:
          type:
          - object
          - 'null'
          properties:
            id:
              type: string
              description: The unique identifier for the payment.
              example: pay_xxxxxxxxxxxxxx
          required:
          - id
          description: The original payment that this refund was issued against. Null
            if the payment is no longer available.
      required:
      - id
      - amount
      - currency
      - status
      - created_at
      - provider
      - provider_created_at
      - reference_status
      - reference_type
      - reference_value
      - payment
      description: A refund represents a full or partial reversal of a payment, including
        the amount, status, and payment provider.
    RefundReferenceStatuses:
      type: string
      enum:
      - available
      - pending
      - unavailable
      description: The status of the refund reference.
    RefundReferenceTypes:
      type: string
      enum:
      - acquirer_reference_number
      - retrieval_reference_number
      - system_trace_audit_number
      description: The type of refund reference that was made available by the payment
        provider.
    RefundStatuses:
      type: string
      enum:
      - pending
      - requires_action
      - succeeded
      - failed
      - canceled
      description: The different statuses for a Refund object
```
