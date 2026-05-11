# GET /promo_codes — List promo codes

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/promo_codes`
- **Operation ID:** `listPromoCode`
- **Tags:** `Promo codes`
- **Required bearer scopes:** `promo_code:basic:read`, `access_pass:basic:read`

## Description

Returns a paginated list of promo codes belonging to a company, with optional filtering by product, plan, and status.

Required permissions:
 - `promo_code:basic:read`
 - `access_pass:basic:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `after` | query | no | `string \| null` | Returns the elements in the list that come after the specified cursor. |  |
| `before` | query | no | `string \| null` | Returns the elements in the list that come before the specified cursor. |  |
| `first` | query | no | `integer \| null` | Returns the first _n_ elements from the list. | 42 |
| `last` | query | no | `integer \| null` | Returns the last _n_ elements from the list. | 42 |
| `company_id` | query | yes | `string` | The unique identifier of the company to list promo codes for. | biz_xxxxxxxxxxxxxx |
| `product_ids` | query | no | `array<string>` | Filter to only promo codes scoped to these product identifiers. |  |
| `plan_ids` | query | no | `array<string>` | Filter to only promo codes scoped to these plan identifiers. |  |
| `status` | query | no | `PromoCodeStatus \| null` | Filter to only promo codes matching this status. |  |
| `created_before` | query | no | `string \| null / date-time` | Only return promo codes created before this timestamp. | 2023-12-01T05:00:00.401Z |
| `created_after` | query | no | `string \| null / date-time` | Only return promo codes created after this timestamp. | 2023-12-01T05:00:00.401Z |

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
      "$ref": "#/components/schemas/PromoCodeListItem"
    description: A list of nodes.
  page_info:
    "$ref": "#/components/schemas/PageInfo"
    description: Information to aid in pagination.
required:
- data
- page_info
description: The connection type for PromoCode.
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
for await (const promoCodeListResponse of client.promoCodes.list({
  company_id: 'biz_xxxxxxxxxxxxxx',
})) {
  console.log(promoCodeListResponse.id);
}
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
page = client.promo_codes.list(
    company_id="biz_xxxxxxxxxxxxxx",
)
page = page.data[0]
print(page.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

page = whop.promo_codes.list(company_id: "biz_xxxxxxxxxxxxxx")

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
  PromoCodeListItem:
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
  "/promo_codes":
    get:
      tags:
      - Promo codes
      operationId: listPromoCode
      summary: List promo codes
      description: |-
        Returns a paginated list of promo codes belonging to a company, with optional filtering by product, plan, and status.

        Required permissions:
         - `promo_code:basic:read`
         - `access_pass:basic:read`
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
      - name: company_id
        in: query
        required: true
        schema:
          type: string
          description: The unique identifier of the company to list promo codes for.
          example: biz_xxxxxxxxxxxxxx
        explode: true
        style: form
      - name: product_ids
        in: query
        required: false
        schema:
          type:
          - array
          - 'null'
          items:
            type: string
            description: Represents a unique identifier that is Base64 obfuscated.
              It is often used to refetch an object or as key for a cache. The ID
              type appears in a JSON response as a String; however, it is not intended
              to be human-readable. When expected as an input type, any string (such
              as `"VXNlci0xMA=="`) or integer (such as `4`) input value will be accepted
              as an ID.
          description: Filter to only promo codes scoped to these product identifiers.
        explode: true
        style: form
      - name: plan_ids
        in: query
        required: false
        schema:
          type:
          - array
          - 'null'
          items:
            type: string
            description: Represents a unique identifier that is Base64 obfuscated.
              It is often used to refetch an object or as key for a cache. The ID
              type appears in a JSON response as a String; however, it is not intended
              to be human-readable. When expected as an input type, any string (such
              as `"VXNlci0xMA=="`) or integer (such as `4`) input value will be accepted
              as an ID.
          description: Filter to only promo codes scoped to these plan identifiers.
        explode: true
        style: form
      - name: status
        in: query
        required: false
        schema:
          oneOf:
          - "$ref": "#/components/schemas/PromoCodeStatus"
          - type: 'null'
          description: Filter to only promo codes matching this status.
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
          description: Only return promo codes created before this timestamp.
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
          description: Only return promo codes created after this timestamp.
          example: '2023-12-01T05:00:00.401Z'
        explode: true
        style: form
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
                type: object
                properties:
                  data:
                    type: array
                    items:
                      "$ref": "#/components/schemas/PromoCodeListItem"
                    description: A list of nodes.
                  page_info:
                    "$ref": "#/components/schemas/PageInfo"
                    description: Information to aid in pagination.
                required:
                - data
                - page_info
                description: The connection type for PromoCode.
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
          for await (const promoCodeListResponse of client.promoCodes.list({
            company_id: 'biz_xxxxxxxxxxxxxx',
          })) {
            console.log(promoCodeListResponse.id);
          }
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          page = client.promo_codes.list(
              company_id="biz_xxxxxxxxxxxxxx",
          )
          page = page.data[0]
          print(page.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          page = whop.promo_codes.list(company_id: "biz_xxxxxxxxxxxxxx")

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
    PromoCodeListItem:
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
