# GET /plans — List plans

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/plans`
- **Operation ID:** `listPlan`
- **Tags:** `Plans`
- **Required bearer scopes:** `plan:basic:read`

## Description

Returns a paginated list of plans belonging to a company, with optional filtering by visibility, type, release method, and product.

Required permissions:
 - `plan:basic:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `after` | query | no | `string \| null` | Returns the elements in the list that come after the specified cursor. |  |
| `before` | query | no | `string \| null` | Returns the elements in the list that come before the specified cursor. |  |
| `first` | query | no | `integer \| null` | Returns the first _n_ elements from the list. | 42 |
| `last` | query | no | `integer \| null` | Returns the last _n_ elements from the list. | 42 |
| `company_id` | query | yes | `string` | The unique identifier of the company to list plans for. | biz_xxxxxxxxxxxxxx |
| `direction` | query | no | `Direction \| null` | The sort direction for results. Defaults to descending. |  |
| `order` | query | no | `PlansOrder \| null` | The field to sort results by. Defaults to created_at. |  |
| `release_methods` | query | no | `array<ReleaseMethod>` | Filter to only plans matching these release methods. |  |
| `visibilities` | query | no | `array<VisibilityFilter>` | Filter to only plans matching these visibility states. |  |
| `plan_types` | query | no | `array<PlanTypes>` | Filter to only plans matching these billing types. |  |
| `product_ids` | query | no | `array<string>` | Filter to only plans belonging to these product identifiers. |  |
| `created_before` | query | no | `string \| null / date-time` | Only return plans created before this timestamp. | 2023-12-01T05:00:00.401Z |
| `created_after` | query | no | `string \| null / date-time` | Only return plans created after this timestamp. | 2023-12-01T05:00:00.401Z |

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
      "$ref": "#/components/schemas/PlanListItem"
    description: A list of nodes.
  page_info:
    "$ref": "#/components/schemas/PageInfo"
    description: Information to aid in pagination.
required:
- data
- page_info
description: The connection type for PublicPlan.
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
for await (const planListResponse of client.plans.list({ company_id: 'biz_xxxxxxxxxxxxxx' })) {
  console.log(planListResponse.id);
}
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
page = client.plans.list(
    company_id="biz_xxxxxxxxxxxxxx",
)
page = page.data[0]
print(page.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

page = whop.plans.list(company_id: "biz_xxxxxxxxxxxxxx")

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
  PaymentMethodTypes:
    type: string
    enum:
    - acss_debit
    - affirm
    - afterpay_clearpay
    - alipay
    - alma
    - amazon_pay
    - apple
    - apple_pay
    - au_bank_transfer
    - au_becs_debit
    - bacs_debit
    - bancolombia
    - bancontact
    - billie
    - bizum
    - blik
    - boleto
    - bre_b
    - ca_bank_transfer
    - capchase_pay
    - card
    - card_installments_three
    - card_installments_six
    - card_installments_twelve
    - cashapp
    - claritypay
    - coinbase
    - crypto
    - custom
    - customer_balance
    - demo_pay
    - efecty
    - eps
    - eu_bank_transfer
    - fpx
    - gb_bank_transfer
    - giropay
    - google_pay
    - gopay
    - grabpay
    - id_bank_transfer
    - ideal
    - interac
    - kakao_pay
    - klarna
    - klarna_pay_now
    - konbini
    - kr_card
    - kr_market
    - kriya
    - kueski
    - link
    - mb_way
    - m_pesa
    - mercado_pago
    - mobilepay
    - mondu
    - multibanco
    - naver_pay
    - nequi
    - netbanking
    - ng_bank
    - ng_bank_transfer
    - ng_card
    - ng_market
    - ng_ussd
    - ng_wallet
    - nz_bank_account
    - oxxo
    - p24
    - pago_efectivo
    - pse
    - pay_by_bank
    - payco
    - paynow
    - paypal
    - paypay
    - payto
    - pix
    - platform_balance
    - promptpay
    - qris
    - rechnung
    - revolut_pay
    - samsung_pay
    - satispay
    - scalapay
    - sencillito
    - sepa_debit
    - sequra
    - servipag
    - sezzle
    - shop_pay
    - shopeepay
    - sofort
    - south_korea_market
    - spei
    - splitit
    - sunbit
    - swish
    - tamara
    - twint
    - upi
    - us_bank_account
    - us_bank_transfer
    - venmo
    - vipps
    - webpay
    - wechat_pay
    - yape
    - zip
    - coinflow
    - unknown
    description: The different types of payment methods that can be used.
  PlanListItem:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the plan.
        example: plan_xxxxxxxxxxxxx
      created_at:
        type: string
        format: date-time
        description: The datetime the plan was created.
        example: '2023-12-01T05:00:00.401Z'
      updated_at:
        type: string
        format: date-time
        description: The datetime the plan was last updated.
        example: '2023-12-01T05:00:00.401Z'
      visibility:
        "$ref": "#/components/schemas/Visibility"
        description: Controls whether the plan is visible to customers. When set to
          'hidden', the plan is only accessible via direct link.
      plan_type:
        "$ref": "#/components/schemas/PlanTypes"
        description: 'The billing model for this plan: ''renewal'' for recurring subscriptions
          or ''one_time'' for single payments.'
      release_method:
        "$ref": "#/components/schemas/ReleaseMethod"
        description: 'The method used to sell this plan: ''buy_now'' for immediate
          purchase or ''waitlist'' for waitlist-based access.'
      currency:
        "$ref": "#/components/schemas/Currencies"
        description: The currency used for all prices on this plan (e.g., 'usd', 'eur').
          All monetary amounts on the plan are denominated in this currency.
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
        description: The company that sells this plan. Null for standalone invoice
          plans not linked to a company.
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
        description: The product that this plan belongs to. Null for standalone one-off
          purchases not linked to a product.
      invoice:
        type:
        - object
        - 'null'
        properties:
          id:
            type: string
            description: The unique identifier for the invoice.
            example: inv_xxxxxxxxxxxxxx
        required:
        - id
        description: The invoice this plan was generated for. Null if the plan was
          not created for a specific invoice.
      billing_period:
        type:
        - integer
        - 'null'
        description: The number of days between each recurring charge. Null for one-time
          plans. For example, 30 for monthly or 365 for annual billing.
        example: 42
      title:
        type:
        - string
        - 'null'
        description: The display name of the plan shown to customers on the product
          page and at checkout. Maximum 30 characters. Null if no title has been set.
        example: Pro Monthly
      description:
        type:
        - string
        - 'null'
        description: A text description of the plan visible to customers. Maximum
          1000 characters. Null if no description is set.
        example: Monthly access to all premium analytics dashboards and data exports.
      purchase_url:
        type: string
        description: The full URL where customers can purchase this plan directly,
          bypassing the product page.
        example: https://whop.com/pickaxe-analytics/checkout/plan_abc123
      expiration_days:
        type:
        - integer
        - 'null'
        description: The number of days until the membership expires (for expiration-based
          plans). For example, 365 for a one-year access pass.
        example: 42
      initial_price:
        type: number
        description: The initial purchase price in the plan's base_currency (e.g.,
          49.99 for $49.99). For one-time plans, this is the full price. For renewal
          plans, this is charged on top of the first renewal_price.
        example: 6.9
      renewal_price:
        type: number
        description: The recurring price charged every billing_period in the plan's
          base_currency (e.g., 9.99 for $9.99/period). Zero for one-time plans.
        example: 6.9
      trial_period_days:
        type:
        - integer
        - 'null'
        description: The number of free trial days before the first charge on a renewal
          plan. Null if no trial is configured or the current user has already used
          a trial for this plan.
        example: 42
      member_count:
        type:
        - integer
        - 'null'
        description: The number of users who currently hold an active membership through
          this plan. Only visible to authorized team members.
        example: 42
      internal_notes:
        type:
        - string
        - 'null'
        description: Private notes visible only to the company owner and team members.
          Not shown to customers. Null if no notes have been added.
        example: Black Friday 2024 promo plan - expires Dec 1
      stock:
        type:
        - integer
        - 'null'
        description: The number of units available for purchase. Only visible to authorized
          team members. Null if the requester lacks permission.
        example: 42
      unlimited_stock:
        type: boolean
        description: When true, the plan has unlimited stock (stock field is ignored).
          When false, purchases are limited by the stock field.
      split_pay_required_payments:
        type:
        - integer
        - 'null'
        description: The total number of installment payments required before the
          subscription pauses. Null if split pay is not configured. Must be greater
          than 1.
        example: 42
      adaptive_pricing_enabled:
        type: boolean
        description: Whether the creator has turned on adaptive pricing for this plan.
          Raw setting — does not check processor compatibility or feature flags.
      payment_method_configuration:
        type:
        - object
        - 'null'
        properties:
          enabled:
            type: array
            items:
              "$ref": "#/components/schemas/PaymentMethodTypes"
            description: An array of payment method identifiers that are explicitly
              enabled. This means these payment methods will be shown on checkout.
              Example use case is to only enable a specific payment method like cashapp,
              or extending the platform defaults with additional methods.
          disabled:
            type: array
            items:
              "$ref": "#/components/schemas/PaymentMethodTypes"
            description: An array of payment method identifiers that are explicitly
              disabled. Only applies if the include_platform_defaults is true.
          include_platform_defaults:
            type: boolean
            description: Whether Whop's platform default payment method enablement
              settings are included in this configuration. The full list of default
              payment methods can be found in the documentation at docs.whop.com/payments.
        required:
        - enabled
        - disabled
        - include_platform_defaults
        description: The explicit payment method configuration specifying which payment
          methods are enabled or disabled for this plan. Null if the plan uses default
          settings.
    required:
    - id
    - created_at
    - updated_at
    - visibility
    - plan_type
    - release_method
    - currency
    - company
    - product
    - invoice
    - billing_period
    - title
    - description
    - purchase_url
    - expiration_days
    - initial_price
    - renewal_price
    - trial_period_days
    - member_count
    - internal_notes
    - stock
    - unlimited_stock
    - split_pay_required_payments
    - adaptive_pricing_enabled
    - payment_method_configuration
    description: A plan defines pricing and billing terms for a checkout. Plans can
      optionally belong to a product, where they represent different pricing options
      such as one-time payments, recurring subscriptions, or free trials.
  PlanTypes:
    type: string
    enum:
    - renewal
    - one_time
    description: The type of plan that can be attached to a product
  PlansOrder:
    type: string
    enum:
    - id
    - active_members_count
    - created_at
    - internal_notes
    - expires_at
    description: The ways a relation of Plans can be ordered
  ReleaseMethod:
    type: string
    enum:
    - buy_now
    - waitlist
    description: The methods of how a plan can be released.
  Visibility:
    type: string
    enum:
    - visible
    - hidden
    - archived
    - quick_link
    description: Visibility of a resource
  VisibilityFilter:
    type: string
    enum:
    - visible
    - hidden
    - archived
    - quick_link
    - all
    - not_quick_link
    - not_archived
    description: The different levels of visibility for resources
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
  "/plans":
    get:
      tags:
      - Plans
      operationId: listPlan
      summary: List plans
      description: |-
        Returns a paginated list of plans belonging to a company, with optional filtering by visibility, type, release method, and product.

        Required permissions:
         - `plan:basic:read`
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
          description: The unique identifier of the company to list plans for.
          example: biz_xxxxxxxxxxxxxx
        explode: true
        style: form
      - name: direction
        in: query
        required: false
        schema:
          oneOf:
          - "$ref": "#/components/schemas/Direction"
          - type: 'null'
          description: The sort direction for results. Defaults to descending.
        explode: true
        style: form
      - name: order
        in: query
        required: false
        schema:
          oneOf:
          - "$ref": "#/components/schemas/PlansOrder"
          - type: 'null'
          description: The field to sort results by. Defaults to created_at.
        explode: true
        style: form
      - name: release_methods
        in: query
        required: false
        schema:
          type:
          - array
          - 'null'
          items:
            "$ref": "#/components/schemas/ReleaseMethod"
          description: Filter to only plans matching these release methods.
        explode: true
        style: form
      - name: visibilities
        in: query
        required: false
        schema:
          type:
          - array
          - 'null'
          items:
            "$ref": "#/components/schemas/VisibilityFilter"
          description: Filter to only plans matching these visibility states.
        explode: true
        style: form
      - name: plan_types
        in: query
        required: false
        schema:
          type:
          - array
          - 'null'
          items:
            "$ref": "#/components/schemas/PlanTypes"
          description: Filter to only plans matching these billing types.
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
          description: Filter to only plans belonging to these product identifiers.
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
          description: Only return plans created before this timestamp.
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
          description: Only return plans created after this timestamp.
          example: '2023-12-01T05:00:00.401Z'
        explode: true
        style: form
      x-group-title: Payins
      security:
      - bearerAuth:
        - plan:basic:read
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
                      "$ref": "#/components/schemas/PlanListItem"
                    description: A list of nodes.
                  page_info:
                    "$ref": "#/components/schemas/PageInfo"
                    description: Information to aid in pagination.
                required:
                - data
                - page_info
                description: The connection type for PublicPlan.
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
          for await (const planListResponse of client.plans.list({ company_id: 'biz_xxxxxxxxxxxxxx' })) {
            console.log(planListResponse.id);
          }
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          page = client.plans.list(
              company_id="biz_xxxxxxxxxxxxxx",
          )
          page = page.data[0]
          print(page.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          page = whop.plans.list(company_id: "biz_xxxxxxxxxxxxxx")

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
    PaymentMethodTypes:
      type: string
      enum:
      - acss_debit
      - affirm
      - afterpay_clearpay
      - alipay
      - alma
      - amazon_pay
      - apple
      - apple_pay
      - au_bank_transfer
      - au_becs_debit
      - bacs_debit
      - bancolombia
      - bancontact
      - billie
      - bizum
      - blik
      - boleto
      - bre_b
      - ca_bank_transfer
      - capchase_pay
      - card
      - card_installments_three
      - card_installments_six
      - card_installments_twelve
      - cashapp
      - claritypay
      - coinbase
      - crypto
      - custom
      - customer_balance
      - demo_pay
      - efecty
      - eps
      - eu_bank_transfer
      - fpx
      - gb_bank_transfer
      - giropay
      - google_pay
      - gopay
      - grabpay
      - id_bank_transfer
      - ideal
      - interac
      - kakao_pay
      - klarna
      - klarna_pay_now
      - konbini
      - kr_card
      - kr_market
      - kriya
      - kueski
      - link
      - mb_way
      - m_pesa
      - mercado_pago
      - mobilepay
      - mondu
      - multibanco
      - naver_pay
      - nequi
      - netbanking
      - ng_bank
      - ng_bank_transfer
      - ng_card
      - ng_market
      - ng_ussd
      - ng_wallet
      - nz_bank_account
      - oxxo
      - p24
      - pago_efectivo
      - pse
      - pay_by_bank
      - payco
      - paynow
      - paypal
      - paypay
      - payto
      - pix
      - platform_balance
      - promptpay
      - qris
      - rechnung
      - revolut_pay
      - samsung_pay
      - satispay
      - scalapay
      - sencillito
      - sepa_debit
      - sequra
      - servipag
      - sezzle
      - shop_pay
      - shopeepay
      - sofort
      - south_korea_market
      - spei
      - splitit
      - sunbit
      - swish
      - tamara
      - twint
      - upi
      - us_bank_account
      - us_bank_transfer
      - venmo
      - vipps
      - webpay
      - wechat_pay
      - yape
      - zip
      - coinflow
      - unknown
      description: The different types of payment methods that can be used.
    PlanListItem:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the plan.
          example: plan_xxxxxxxxxxxxx
        created_at:
          type: string
          format: date-time
          description: The datetime the plan was created.
          example: '2023-12-01T05:00:00.401Z'
        updated_at:
          type: string
          format: date-time
          description: The datetime the plan was last updated.
          example: '2023-12-01T05:00:00.401Z'
        visibility:
          "$ref": "#/components/schemas/Visibility"
          description: Controls whether the plan is visible to customers. When set
            to 'hidden', the plan is only accessible via direct link.
        plan_type:
          "$ref": "#/components/schemas/PlanTypes"
          description: 'The billing model for this plan: ''renewal'' for recurring
            subscriptions or ''one_time'' for single payments.'
        release_method:
          "$ref": "#/components/schemas/ReleaseMethod"
          description: 'The method used to sell this plan: ''buy_now'' for immediate
            purchase or ''waitlist'' for waitlist-based access.'
        currency:
          "$ref": "#/components/schemas/Currencies"
          description: The currency used for all prices on this plan (e.g., 'usd',
            'eur'). All monetary amounts on the plan are denominated in this currency.
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
          description: The company that sells this plan. Null for standalone invoice
            plans not linked to a company.
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
          description: The product that this plan belongs to. Null for standalone
            one-off purchases not linked to a product.
        invoice:
          type:
          - object
          - 'null'
          properties:
            id:
              type: string
              description: The unique identifier for the invoice.
              example: inv_xxxxxxxxxxxxxx
          required:
          - id
          description: The invoice this plan was generated for. Null if the plan was
            not created for a specific invoice.
        billing_period:
          type:
          - integer
          - 'null'
          description: The number of days between each recurring charge. Null for
            one-time plans. For example, 30 for monthly or 365 for annual billing.
          example: 42
        title:
          type:
          - string
          - 'null'
          description: The display name of the plan shown to customers on the product
            page and at checkout. Maximum 30 characters. Null if no title has been
            set.
          example: Pro Monthly
        description:
          type:
          - string
          - 'null'
          description: A text description of the plan visible to customers. Maximum
            1000 characters. Null if no description is set.
          example: Monthly access to all premium analytics dashboards and data exports.
        purchase_url:
          type: string
          description: The full URL where customers can purchase this plan directly,
            bypassing the product page.
          example: https://whop.com/pickaxe-analytics/checkout/plan_abc123
        expiration_days:
          type:
          - integer
          - 'null'
          description: The number of days until the membership expires (for expiration-based
            plans). For example, 365 for a one-year access pass.
          example: 42
        initial_price:
          type: number
          description: The initial purchase price in the plan's base_currency (e.g.,
            49.99 for $49.99). For one-time plans, this is the full price. For renewal
            plans, this is charged on top of the first renewal_price.
          example: 6.9
        renewal_price:
          type: number
          description: The recurring price charged every billing_period in the plan's
            base_currency (e.g., 9.99 for $9.99/period). Zero for one-time plans.
          example: 6.9
        trial_period_days:
          type:
          - integer
          - 'null'
          description: The number of free trial days before the first charge on a
            renewal plan. Null if no trial is configured or the current user has already
            used a trial for this plan.
          example: 42
        member_count:
          type:
          - integer
          - 'null'
          description: The number of users who currently hold an active membership
            through this plan. Only visible to authorized team members.
          example: 42
        internal_notes:
          type:
          - string
          - 'null'
          description: Private notes visible only to the company owner and team members.
            Not shown to customers. Null if no notes have been added.
          example: Black Friday 2024 promo plan - expires Dec 1
        stock:
          type:
          - integer
          - 'null'
          description: The number of units available for purchase. Only visible to
            authorized team members. Null if the requester lacks permission.
          example: 42
        unlimited_stock:
          type: boolean
          description: When true, the plan has unlimited stock (stock field is ignored).
            When false, purchases are limited by the stock field.
        split_pay_required_payments:
          type:
          - integer
          - 'null'
          description: The total number of installment payments required before the
            subscription pauses. Null if split pay is not configured. Must be greater
            than 1.
          example: 42
        adaptive_pricing_enabled:
          type: boolean
          description: Whether the creator has turned on adaptive pricing for this
            plan. Raw setting — does not check processor compatibility or feature
            flags.
        payment_method_configuration:
          type:
          - object
          - 'null'
          properties:
            enabled:
              type: array
              items:
                "$ref": "#/components/schemas/PaymentMethodTypes"
              description: An array of payment method identifiers that are explicitly
                enabled. This means these payment methods will be shown on checkout.
                Example use case is to only enable a specific payment method like
                cashapp, or extending the platform defaults with additional methods.
            disabled:
              type: array
              items:
                "$ref": "#/components/schemas/PaymentMethodTypes"
              description: An array of payment method identifiers that are explicitly
                disabled. Only applies if the include_platform_defaults is true.
            include_platform_defaults:
              type: boolean
              description: Whether Whop's platform default payment method enablement
                settings are included in this configuration. The full list of default
                payment methods can be found in the documentation at docs.whop.com/payments.
          required:
          - enabled
          - disabled
          - include_platform_defaults
          description: The explicit payment method configuration specifying which
            payment methods are enabled or disabled for this plan. Null if the plan
            uses default settings.
      required:
      - id
      - created_at
      - updated_at
      - visibility
      - plan_type
      - release_method
      - currency
      - company
      - product
      - invoice
      - billing_period
      - title
      - description
      - purchase_url
      - expiration_days
      - initial_price
      - renewal_price
      - trial_period_days
      - member_count
      - internal_notes
      - stock
      - unlimited_stock
      - split_pay_required_payments
      - adaptive_pricing_enabled
      - payment_method_configuration
      description: A plan defines pricing and billing terms for a checkout. Plans
        can optionally belong to a product, where they represent different pricing
        options such as one-time payments, recurring subscriptions, or free trials.
    PlanTypes:
      type: string
      enum:
      - renewal
      - one_time
      description: The type of plan that can be attached to a product
    PlansOrder:
      type: string
      enum:
      - id
      - active_members_count
      - created_at
      - internal_notes
      - expires_at
      description: The ways a relation of Plans can be ordered
    ReleaseMethod:
      type: string
      enum:
      - buy_now
      - waitlist
      description: The methods of how a plan can be released.
    Visibility:
      type: string
      enum:
      - visible
      - hidden
      - archived
      - quick_link
      description: Visibility of a resource
    VisibilityFilter:
      type: string
      enum:
      - visible
      - hidden
      - archived
      - quick_link
      - all
      - not_quick_link
      - not_archived
      description: The different levels of visibility for resources
```
