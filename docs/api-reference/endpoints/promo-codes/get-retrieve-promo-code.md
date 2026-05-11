# GET /promo_codes/{id} — Retrieve promo code

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/promo_codes/{id}`
- **Operation ID:** `retrievePromoCode`
- **Tags:** `Promo codes`
- **Required bearer scopes:** `promo_code:basic:read`, `access_pass:basic:read`

## Description

Retrieves the details of an existing promo code.

Required permissions:
 - `promo_code:basic:read`
 - `access_pass:basic:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `id` | path | yes | `string` | The unique identifier of the promo code. | promo_xxxxxxxxxxxx |

## Request body

_No request body._

## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `PromoCode` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/PromoCode"
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

const promoCode = await client.promoCodes.retrieve('promo_xxxxxxxxxxxx');

console.log(promoCode.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
promo_code = client.promo_codes.retrieve(
    "promo_xxxxxxxxxxxx",
)
print(promo_code.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

promo_code = whop.promo_codes.retrieve("promo_xxxxxxxxxxxx")

puts(promo_code)
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
  PromoCode:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the promo code.
        example: promo_xxxxxxxxxxxx
      amount_off:
        type: number
        description: 'The discount amount. Interpretation depends on promo_type: if
          ''percentage'', this is the percentage (e.g., 20 means 20% off); if ''flat_amount'',
          this is dollars off (e.g., 10.00 means $10.00 off).'
        example: 6.9
      currency:
        "$ref": "#/components/schemas/Currencies"
        description: The monetary currency of the promo code.
      churned_users_only:
        type: boolean
        description: Restricts promo use to only users who have churned from the company
          before.
      code:
        type:
        - string
        - 'null'
        description: The specific code used to apply the promo at checkout.
      created_at:
        type: string
        format: date-time
        description: The datetime the promo code was created.
        example: '2023-12-01T05:00:00.401Z'
      existing_memberships_only:
        type: boolean
        description: Restricts promo use to only be applied to already purchased memberships.
      duration:
        oneOf:
        - "$ref": "#/components/schemas/PromoDurations"
        - type: 'null'
        description: The duration of the promo.
      expires_at:
        type:
        - string
        - 'null'
        format: date-time
        description: The date/time of when the promo expires.
        example: '2023-12-01T05:00:00.401Z'
      new_users_only:
        type: boolean
        description: Restricts promo use to only users who have never purchased from
          the company before.
      promo_duration_months:
        type:
        - integer
        - 'null'
        description: The number of months the promo is applied for.
        example: 42
      one_per_customer:
        type: boolean
        description: Restricts promo use to only be applied once per customer.
      product:
        type:
        - object
        - 'null'
        properties:
          id:
            type: string
            description: The unique identifier for the product.
            example: prod_xxxxxxxxxxxxx
          title:
            type: string
            description: The display name of the product shown to customers on the
              product page and in search results.
            example: Pickaxe Analytics
        required:
        - id
        - title
        description: The product this promo code applies to
      promo_type:
        "$ref": "#/components/schemas/PromoTypes"
        description: The type (% or flat amount) of the promo.
      status:
        "$ref": "#/components/schemas/PromoCodeStatus"
        description: Indicates if the promo code is live or disabled.
      stock:
        type: integer
        description: The quantity limit on the number of uses.
        example: 42
      unlimited_stock:
        type: boolean
        description: Whether or not the promo code has unlimited stock.
      uses:
        type: integer
        description: The amount of times the promo codes has been used.
        example: 42
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
        description: The company for the promo code.
    required:
    - id
    - amount_off
    - currency
    - churned_users_only
    - code
    - created_at
    - existing_memberships_only
    - duration
    - expires_at
    - new_users_only
    - promo_duration_months
    - one_per_customer
    - product
    - promo_type
    - status
    - stock
    - unlimited_stock
    - uses
    - company
    description: A promo code applies a discount to a plan during checkout. Promo
      codes can be percentage-based or fixed-amount, and can have usage limits and
      expiration dates.
  PromoCodeStatus:
    type: string
    enum:
    - active
    - inactive
    - archived
    description: Statuses for promo codes
  PromoDurations:
    type: string
    enum:
    - forever
    - once
    - repeating
    description: The duration setting for the promo code
  PromoTypes:
    type: string
    enum:
    - percentage
    - flat_amount
    description: The type of promo code used to discount a plan
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
  "/promo_codes/{id}":
    get:
      tags:
      - Promo codes
      operationId: retrievePromoCode
      summary: Retrieve promo code
      description: |-
        Retrieves the details of an existing promo code.

        Required permissions:
         - `promo_code:basic:read`
         - `access_pass:basic:read`
      parameters:
      - name: id
        in: path
        required: true
        description: The unique identifier of the promo code.
        schema:
          type: string
          example: promo_xxxxxxxxxxxx
      x-group-title: Payins
      security:
      - bearerAuth:
        - promo_code:basic:read
        - access_pass:basic:read
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/PromoCode"
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

          const promoCode = await client.promoCodes.retrieve('promo_xxxxxxxxxxxx');

          console.log(promoCode.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          promo_code = client.promo_codes.retrieve(
              "promo_xxxxxxxxxxxx",
          )
          print(promo_code.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          promo_code = whop.promo_codes.retrieve("promo_xxxxxxxxxxxx")

          puts(promo_code)
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
    PromoCode:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the promo code.
          example: promo_xxxxxxxxxxxx
        amount_off:
          type: number
          description: 'The discount amount. Interpretation depends on promo_type:
            if ''percentage'', this is the percentage (e.g., 20 means 20% off); if
            ''flat_amount'', this is dollars off (e.g., 10.00 means $10.00 off).'
          example: 6.9
        currency:
          "$ref": "#/components/schemas/Currencies"
          description: The monetary currency of the promo code.
        churned_users_only:
          type: boolean
          description: Restricts promo use to only users who have churned from the
            company before.
        code:
          type:
          - string
          - 'null'
          description: The specific code used to apply the promo at checkout.
        created_at:
          type: string
          format: date-time
          description: The datetime the promo code was created.
          example: '2023-12-01T05:00:00.401Z'
        existing_memberships_only:
          type: boolean
          description: Restricts promo use to only be applied to already purchased
            memberships.
        duration:
          oneOf:
          - "$ref": "#/components/schemas/PromoDurations"
          - type: 'null'
          description: The duration of the promo.
        expires_at:
          type:
          - string
          - 'null'
          format: date-time
          description: The date/time of when the promo expires.
          example: '2023-12-01T05:00:00.401Z'
        new_users_only:
          type: boolean
          description: Restricts promo use to only users who have never purchased
            from the company before.
        promo_duration_months:
          type:
          - integer
          - 'null'
          description: The number of months the promo is applied for.
          example: 42
        one_per_customer:
          type: boolean
          description: Restricts promo use to only be applied once per customer.
        product:
          type:
          - object
          - 'null'
          properties:
            id:
              type: string
              description: The unique identifier for the product.
              example: prod_xxxxxxxxxxxxx
            title:
              type: string
              description: The display name of the product shown to customers on the
                product page and in search results.
              example: Pickaxe Analytics
          required:
          - id
          - title
          description: The product this promo code applies to
        promo_type:
          "$ref": "#/components/schemas/PromoTypes"
          description: The type (% or flat amount) of the promo.
        status:
          "$ref": "#/components/schemas/PromoCodeStatus"
          description: Indicates if the promo code is live or disabled.
        stock:
          type: integer
          description: The quantity limit on the number of uses.
          example: 42
        unlimited_stock:
          type: boolean
          description: Whether or not the promo code has unlimited stock.
        uses:
          type: integer
          description: The amount of times the promo codes has been used.
          example: 42
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
          description: The company for the promo code.
      required:
      - id
      - amount_off
      - currency
      - churned_users_only
      - code
      - created_at
      - existing_memberships_only
      - duration
      - expires_at
      - new_users_only
      - promo_duration_months
      - one_per_customer
      - product
      - promo_type
      - status
      - stock
      - unlimited_stock
      - uses
      - company
      description: A promo code applies a discount to a plan during checkout. Promo
        codes can be percentage-based or fixed-amount, and can have usage limits and
        expiration dates.
    PromoCodeStatus:
      type: string
      enum:
      - active
      - inactive
      - archived
      description: Statuses for promo codes
    PromoDurations:
      type: string
      enum:
      - forever
      - once
      - repeating
      description: The duration setting for the promo code
    PromoTypes:
      type: string
      enum:
      - percentage
      - flat_amount
      description: The type of promo code used to discount a plan
```
