# POST /topups — Create topup

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `POST`
- **Path:** `/topups`
- **Operation ID:** `createTopup`
- **Tags:** `Topups`
- **Required bearer scopes:** `payment:charge`

## Description

Add funds to a company's platform balance by charging a stored payment method. Top-ups have no fees or taxes and do not count as revenue.

Required permissions:
 - `payment:charge`

## Parameters

_No path, query, header, or cookie parameters._

## Request body

Required: **yes**
Content types: `application/json`

### `application/json` request schema

```yaml
type: object
properties:
  amount:
    type: number
    description: The amount to add to the balance in the specified currency. For example,
      50.00 for $50.00 USD.
    example: 6.9
  company_id:
    type: string
    description: The unique identifier of the company to add funds to, starting with
      'biz_'.
    example: biz_xxxxxxxxxxxxxx
  currency:
    "$ref": "#/components/schemas/Currencies"
    description: The currency for the top-up amount, such as 'usd'.
  payment_method_id:
    type: string
    description: The unique identifier of the stored payment method to charge for
      the top-up.
    example: payt_xxxxxxxxxxxxx
required:
- amount
- company_id
- currency
- payment_method_id
description: Parameters for CreateTopup
```


## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `Topup` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/Topup"
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

const topup = await client.topups.create({
  amount: 6.9,
  company_id: 'biz_xxxxxxxxxxxxxx',
  currency: 'usd',
  payment_method_id: 'payt_xxxxxxxxxxxxx',
});

console.log(topup.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
topup = client.topups.create(
    amount=6.9,
    company_id="biz_xxxxxxxxxxxxxx",
    currency="usd",
    payment_method_id="payt_xxxxxxxxxxxxx",
)
print(topup.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

topup = whop.topups.create(
  amount: 6.9,
  company_id: "biz_xxxxxxxxxxxxxx",
  currency: :usd,
  payment_method_id: "payt_xxxxxxxxxxxxx"
)

puts(topup)
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
  ReceiptStatus:
    type: string
    enum:
    - draft
    - open
    - paid
    - pending
    - uncollectible
    - unresolved
    - void
    description: The status of a receipt
  Topup:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the payment.
        example: pay_xxxxxxxxxxxxxx
      status:
        oneOf:
        - "$ref": "#/components/schemas/ReceiptStatus"
        - type: 'null'
        description: The current lifecycle state of this payment (e.g., 'draft', 'open',
          'paid', 'void').
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
        description: The time at which this payment was successfully collected. Null
          if the payment has not yet succeeded. As an ISO 8601 timestamp.
        example: '2023-12-01T05:00:00.401Z'
      currency:
        oneOf:
        - "$ref": "#/components/schemas/Currencies"
        - type: 'null'
        description: The three-letter ISO currency code for this payment (e.g., 'usd',
          'eur').
      total:
        type:
        - number
        - 'null'
        description: The total to show to the creator (excluding buyer fees).
        example: 6.9
      failure_message:
        type:
        - string
        - 'null'
        description: If the payment failed, the reason for the failure.
    required:
    - id
    - status
    - created_at
    - paid_at
    - currency
    - total
    - failure_message
    description: A payment represents a completed or attempted charge. Payments track
      the amount, status, currency, and payment method used.
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
  "/topups":
    post:
      tags:
      - Topups
      operationId: createTopup
      summary: Create topup
      description: |-
        Add funds to a company's platform balance by charging a stored payment method. Top-ups have no fees or taxes and do not count as revenue.

        Required permissions:
         - `payment:charge`
      parameters: []
      x-group-title: Payouts
      security:
      - bearerAuth:
        - payment:charge
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/Topup"
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
        required: true
        content:
          application/json:
            schema:
              type: object
              properties:
                amount:
                  type: number
                  description: The amount to add to the balance in the specified currency.
                    For example, 50.00 for $50.00 USD.
                  example: 6.9
                company_id:
                  type: string
                  description: The unique identifier of the company to add funds to,
                    starting with 'biz_'.
                  example: biz_xxxxxxxxxxxxxx
                currency:
                  "$ref": "#/components/schemas/Currencies"
                  description: The currency for the top-up amount, such as 'usd'.
                payment_method_id:
                  type: string
                  description: The unique identifier of the stored payment method
                    to charge for the top-up.
                  example: payt_xxxxxxxxxxxxx
              required:
              - amount
              - company_id
              - currency
              - payment_method_id
              description: Parameters for CreateTopup
      x-codeSamples:
      - lang: JavaScript
        source: |-
          import Whop from '@whop/sdk';

          const client = new Whop({
            apiKey: process.env['WHOP_API_KEY'], // This is the default and can be omitted
          });

          const topup = await client.topups.create({
            amount: 6.9,
            company_id: 'biz_xxxxxxxxxxxxxx',
            currency: 'usd',
            payment_method_id: 'payt_xxxxxxxxxxxxx',
          });

          console.log(topup.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          topup = client.topups.create(
              amount=6.9,
              company_id="biz_xxxxxxxxxxxxxx",
              currency="usd",
              payment_method_id="payt_xxxxxxxxxxxxx",
          )
          print(topup.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          topup = whop.topups.create(
            amount: 6.9,
            company_id: "biz_xxxxxxxxxxxxxx",
            currency: :usd,
            payment_method_id: "payt_xxxxxxxxxxxxx"
          )

          puts(topup)
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
    ReceiptStatus:
      type: string
      enum:
      - draft
      - open
      - paid
      - pending
      - uncollectible
      - unresolved
      - void
      description: The status of a receipt
    Topup:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the payment.
          example: pay_xxxxxxxxxxxxxx
        status:
          oneOf:
          - "$ref": "#/components/schemas/ReceiptStatus"
          - type: 'null'
          description: The current lifecycle state of this payment (e.g., 'draft',
            'open', 'paid', 'void').
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
        currency:
          oneOf:
          - "$ref": "#/components/schemas/Currencies"
          - type: 'null'
          description: The three-letter ISO currency code for this payment (e.g.,
            'usd', 'eur').
        total:
          type:
          - number
          - 'null'
          description: The total to show to the creator (excluding buyer fees).
          example: 6.9
        failure_message:
          type:
          - string
          - 'null'
          description: If the payment failed, the reason for the failure.
      required:
      - id
      - status
      - created_at
      - paid_at
      - currency
      - total
      - failure_message
      description: A payment represents a completed or attempted charge. Payments
        track the amount, status, currency, and payment method used.
```
