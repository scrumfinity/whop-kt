# POST /plans — Create plan

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `POST`
- **Path:** `/plans`
- **Operation ID:** `createPlan`
- **Tags:** `Plans`
- **Required bearer scopes:** `plan:create`, `access_pass:basic:read`, `plan:basic:read`

## Description

Create a new pricing plan for a product. The plan defines the billing interval, price, and availability for customers.

Required permissions:
 - `plan:create`
 - `access_pass:basic:read`
 - `plan:basic:read`

## Parameters

_No path, query, header, or cookie parameters._

## Request body

Required: **yes**
Content types: `application/json`

### `application/json` request schema

```yaml
type: object
properties:
  adaptive_pricing_enabled:
    type:
    - boolean
    - 'null'
    description: Whether this plan accepts local currency payments via adaptive pricing.
  billing_period:
    type:
    - integer
    - 'null'
    description: The number of days between recurring charges. For example, 30 for
      monthly or 365 for yearly.
    example: 42
  company_id:
    type: string
    description: The unique identifier of the company to create this plan for.
    example: biz_xxxxxxxxxxxxxx
  currency:
    oneOf:
    - "$ref": "#/components/schemas/Currencies"
    - type: 'null'
    description: The three-letter ISO currency code for the plan's pricing. Defaults
      to USD.
  custom_fields:
    type:
    - array
    - 'null'
    items:
      type: object
      properties:
        field_type:
          "$ref": "#/components/schemas/CustomFieldTypes"
          description: The type of the custom field.
        id:
          type:
          - string
          - 'null'
          description: The ID of the custom field (if being updated)
        name:
          type: string
          description: The name of the custom field.
        order:
          type:
          - integer
          - 'null'
          description: The order of the field.
          example: 42
        placeholder:
          type:
          - string
          - 'null'
          description: The placeholder value of the field.
        required:
          type:
          - boolean
          - 'null'
          description: Whether or not the field is required.
      required:
      - field_type
      - name
    description: An array of custom field definitions to collect from customers at
      checkout.
  description:
    type:
    - string
    - 'null'
    description: A text description of the plan displayed to customers on the product
      page.
  expiration_days:
    type:
    - integer
    - 'null'
    description: The number of days until the membership expires and access is revoked.
      Used for expiration-based plans.
    example: 42
  image:
    type:
    - object
    - 'null'
    properties:
      id:
        type: string
        description: The ID of an existing file object.
    required:
    - id
    description: An image displayed on the product page to represent this plan.
    title: FileInputWithId
  initial_price:
    type:
    - number
    - 'null'
    description: The amount charged on the first purchase. For one-time plans, this
      is the full price. For recurring plans, this is an additional charge on top
      of the renewal price. Provided in the plan's currency (e.g., 10.43 for $10.43).
    example: 6.9
  internal_notes:
    type:
    - string
    - 'null'
    description: Private notes visible only to the business owner. Not shown to customers.
  legacy_payment_method_controls:
    type:
    - boolean
    - 'null'
    description: Whether this plan uses legacy payment method controls.
  override_tax_type:
    oneOf:
    - "$ref": "#/components/schemas/TaxTypes"
    - type: 'null'
    description: Override the default tax classification for this specific plan.
  payment_method_configuration:
    type:
    - object
    - 'null'
    properties:
      disabled:
        type: array
        items:
          "$ref": "#/components/schemas/PaymentMethodTypes"
        description: An array of payment method identifiers that are explicitly disabled.
          Only applies if the include_platform_defaults is true.
      enabled:
        type: array
        items:
          "$ref": "#/components/schemas/PaymentMethodTypes"
        description: An array of payment method identifiers that are explicitly enabled.
          This means these payment methods will be shown on checkout. Example use
          case is to only enable a specific payment method like cashapp, or extending
          the platform defaults with additional methods.
      include_platform_defaults:
        type:
        - boolean
        - 'null'
        description: Whether Whop's platform default payment method enablement settings
          are included in this configuration. The full list of default payment methods
          can be found in the documentation at docs.whop.com/payments.
    required:
    - disabled
    - enabled
    description: Explicit payment method configuration for the plan. When not provided,
      the company's defaults apply.
  plan_type:
    oneOf:
    - "$ref": "#/components/schemas/PlanTypes"
    - type: 'null'
    description: The billing type of the plan, such as one_time or recurring.
  product_id:
    type: string
    description: The unique identifier of the product to attach this plan to.
    example: prod_xxxxxxxxxxxxx
  release_method:
    oneOf:
    - "$ref": "#/components/schemas/ReleaseMethod"
    - type: 'null'
    description: The method used to sell this plan (e.g., buy_now, waitlist, application).
  renewal_price:
    type:
    - number
    - 'null'
    description: The amount charged each billing period for recurring plans. Provided
      in the plan's currency (e.g., 10.43 for $10.43).
    example: 6.9
  split_pay_required_payments:
    type:
    - integer
    - 'null'
    description: The number of installment payments required before the subscription
      pauses.
    example: 42
  stock:
    type:
    - integer
    - 'null'
    description: The maximum number of units available for purchase. Ignored when
      unlimited_stock is true.
    example: 42
  title:
    type:
    - string
    - 'null'
    description: The display name of the plan shown to customers on the product page.
  trial_period_days:
    type:
    - integer
    - 'null'
    description: The number of free trial days before the first charge on a recurring
      plan.
    example: 42
  unlimited_stock:
    type:
    - boolean
    - 'null'
    description: Whether the plan has unlimited stock. When true, the stock field
      is ignored. Defaults to true.
  visibility:
    oneOf:
    - "$ref": "#/components/schemas/Visibility"
    - type: 'null'
    description: Whether the plan is visible to customers or hidden from public view.
  checkout_styling:
    type:
    - object
    - 'null'
    properties:
      background_color:
        type:
        - string
        - 'null'
        description: 'A hex color code for the checkout page background, applied to
          the order summary panel (e.g. #F4F4F5).'
      border_style:
        oneOf:
        - "$ref": "#/components/schemas/CheckoutShapes"
        - type: 'null'
        description: The border style for buttons and inputs.
      button_color:
        type:
        - string
        - 'null'
        description: 'A hex color code for the button color (e.g. #FF5733).'
      font_family:
        oneOf:
        - "$ref": "#/components/schemas/CheckoutFonts"
        - type: 'null'
        description: The font family for the checkout page.
    required: []
    description: Checkout styling overrides for this plan. Pass null to inherit from
      the company default.
required:
- company_id
- product_id
description: Parameters for CreatePlan
```


## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `Plan` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/Plan"
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

const plan = await client.plans.create({
  company_id: 'biz_xxxxxxxxxxxxxx',
  product_id: 'prod_xxxxxxxxxxxxx',
});

console.log(plan.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
plan = client.plans.create(
    company_id="biz_xxxxxxxxxxxxxx",
    product_id="prod_xxxxxxxxxxxxx",
)
print(plan.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

plan = whop.plans.create(company_id: "biz_xxxxxxxxxxxxxx", product_id: "prod_xxxxxxxxxxxxx")

puts(plan)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  CheckoutFonts:
    type: string
    enum:
    - system
    - roboto
    - open_sans
    description: The different font families available for checkout pages.
  CheckoutShapes:
    type: string
    enum:
    - rounded
    - pill
    - rectangular
    description: The different border-radius styles available for checkout pages.
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
  CustomFieldTypes:
    type: string
    const: text
    description: The type of the custom field.
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
  Plan:
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
      tax_type:
        "$ref": "#/components/schemas/TaxTypes"
        description: 'How tax is handled for this plan: ''inclusive'' (tax included
          in price), ''exclusive'' (tax added at checkout), or ''unspecified'' (tax
          not configured).'
      collect_tax:
        type: boolean
        description: Whether tax is collected on purchases of this plan, based on
          the company's tax configuration.
      custom_fields:
        type: array
        items:
          type: object
          properties:
            id:
              type: string
              description: The unique identifier for the custom field.
              example: field_xxxxxxxxxxxx
            field_type:
              "$ref": "#/components/schemas/CustomFieldTypes"
              description: What type of input field to use.
            name:
              type: string
              description: The title/header of the custom field.
            order:
              type:
              - integer
              - 'null'
              description: How the custom field should be ordered when rendered on
                the checkout page.
              example: 42
            placeholder:
              type:
              - string
              - 'null'
              description: An example response displayed in the input field.
            required:
              type: boolean
              description: Whether or not the custom field is required.
          required:
          - id
          - field_type
          - name
          - order
          - placeholder
          - required
          description: An object representing a custom field for a plan.
        description: Custom input fields displayed on the checkout form that collect
          additional information from the buyer.
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
    - tax_type
    - collect_tax
    - custom_fields
    description: A plan defines pricing and billing terms for a checkout. Plans can
      optionally belong to a product, where they represent different pricing options
      such as one-time payments, recurring subscriptions, or free trials.
  PlanTypes:
    type: string
    enum:
    - renewal
    - one_time
    description: The type of plan that can be attached to a product
  ReleaseMethod:
    type: string
    enum:
    - buy_now
    - waitlist
    description: The methods of how a plan can be released.
  TaxTypes:
    type: string
    enum:
    - inclusive
    - exclusive
    - unspecified
    description: Whether or not the tax is included in a plan's price (or if it hasn't
      been set up)
  Visibility:
    type: string
    enum:
    - visible
    - hidden
    - archived
    - quick_link
    description: Visibility of a resource
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
    post:
      tags:
      - Plans
      operationId: createPlan
      summary: Create plan
      description: |-
        Create a new pricing plan for a product. The plan defines the billing interval, price, and availability for customers.

        Required permissions:
         - `plan:create`
         - `access_pass:basic:read`
         - `plan:basic:read`
      parameters: []
      x-group-title: Payins
      security:
      - bearerAuth:
        - plan:create
        - access_pass:basic:read
        - plan:basic:read
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/Plan"
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
                adaptive_pricing_enabled:
                  type:
                  - boolean
                  - 'null'
                  description: Whether this plan accepts local currency payments via
                    adaptive pricing.
                billing_period:
                  type:
                  - integer
                  - 'null'
                  description: The number of days between recurring charges. For example,
                    30 for monthly or 365 for yearly.
                  example: 42
                company_id:
                  type: string
                  description: The unique identifier of the company to create this
                    plan for.
                  example: biz_xxxxxxxxxxxxxx
                currency:
                  oneOf:
                  - "$ref": "#/components/schemas/Currencies"
                  - type: 'null'
                  description: The three-letter ISO currency code for the plan's pricing.
                    Defaults to USD.
                custom_fields:
                  type:
                  - array
                  - 'null'
                  items:
                    type: object
                    properties:
                      field_type:
                        "$ref": "#/components/schemas/CustomFieldTypes"
                        description: The type of the custom field.
                      id:
                        type:
                        - string
                        - 'null'
                        description: The ID of the custom field (if being updated)
                      name:
                        type: string
                        description: The name of the custom field.
                      order:
                        type:
                        - integer
                        - 'null'
                        description: The order of the field.
                        example: 42
                      placeholder:
                        type:
                        - string
                        - 'null'
                        description: The placeholder value of the field.
                      required:
                        type:
                        - boolean
                        - 'null'
                        description: Whether or not the field is required.
                    required:
                    - field_type
                    - name
                  description: An array of custom field definitions to collect from
                    customers at checkout.
                description:
                  type:
                  - string
                  - 'null'
                  description: A text description of the plan displayed to customers
                    on the product page.
                expiration_days:
                  type:
                  - integer
                  - 'null'
                  description: The number of days until the membership expires and
                    access is revoked. Used for expiration-based plans.
                  example: 42
                image:
                  type:
                  - object
                  - 'null'
                  properties:
                    id:
                      type: string
                      description: The ID of an existing file object.
                  required:
                  - id
                  description: An image displayed on the product page to represent
                    this plan.
                  title: FileInputWithId
                initial_price:
                  type:
                  - number
                  - 'null'
                  description: The amount charged on the first purchase. For one-time
                    plans, this is the full price. For recurring plans, this is an
                    additional charge on top of the renewal price. Provided in the
                    plan's currency (e.g., 10.43 for $10.43).
                  example: 6.9
                internal_notes:
                  type:
                  - string
                  - 'null'
                  description: Private notes visible only to the business owner. Not
                    shown to customers.
                legacy_payment_method_controls:
                  type:
                  - boolean
                  - 'null'
                  description: Whether this plan uses legacy payment method controls.
                override_tax_type:
                  oneOf:
                  - "$ref": "#/components/schemas/TaxTypes"
                  - type: 'null'
                  description: Override the default tax classification for this specific
                    plan.
                payment_method_configuration:
                  type:
                  - object
                  - 'null'
                  properties:
                    disabled:
                      type: array
                      items:
                        "$ref": "#/components/schemas/PaymentMethodTypes"
                      description: An array of payment method identifiers that are
                        explicitly disabled. Only applies if the include_platform_defaults
                        is true.
                    enabled:
                      type: array
                      items:
                        "$ref": "#/components/schemas/PaymentMethodTypes"
                      description: An array of payment method identifiers that are
                        explicitly enabled. This means these payment methods will
                        be shown on checkout. Example use case is to only enable a
                        specific payment method like cashapp, or extending the platform
                        defaults with additional methods.
                    include_platform_defaults:
                      type:
                      - boolean
                      - 'null'
                      description: Whether Whop's platform default payment method
                        enablement settings are included in this configuration. The
                        full list of default payment methods can be found in the documentation
                        at docs.whop.com/payments.
                  required:
                  - disabled
                  - enabled
                  description: Explicit payment method configuration for the plan.
                    When not provided, the company's defaults apply.
                plan_type:
                  oneOf:
                  - "$ref": "#/components/schemas/PlanTypes"
                  - type: 'null'
                  description: The billing type of the plan, such as one_time or recurring.
                product_id:
                  type: string
                  description: The unique identifier of the product to attach this
                    plan to.
                  example: prod_xxxxxxxxxxxxx
                release_method:
                  oneOf:
                  - "$ref": "#/components/schemas/ReleaseMethod"
                  - type: 'null'
                  description: The method used to sell this plan (e.g., buy_now, waitlist,
                    application).
                renewal_price:
                  type:
                  - number
                  - 'null'
                  description: The amount charged each billing period for recurring
                    plans. Provided in the plan's currency (e.g., 10.43 for $10.43).
                  example: 6.9
                split_pay_required_payments:
                  type:
                  - integer
                  - 'null'
                  description: The number of installment payments required before
                    the subscription pauses.
                  example: 42
                stock:
                  type:
                  - integer
                  - 'null'
                  description: The maximum number of units available for purchase.
                    Ignored when unlimited_stock is true.
                  example: 42
                title:
                  type:
                  - string
                  - 'null'
                  description: The display name of the plan shown to customers on
                    the product page.
                trial_period_days:
                  type:
                  - integer
                  - 'null'
                  description: The number of free trial days before the first charge
                    on a recurring plan.
                  example: 42
                unlimited_stock:
                  type:
                  - boolean
                  - 'null'
                  description: Whether the plan has unlimited stock. When true, the
                    stock field is ignored. Defaults to true.
                visibility:
                  oneOf:
                  - "$ref": "#/components/schemas/Visibility"
                  - type: 'null'
                  description: Whether the plan is visible to customers or hidden
                    from public view.
                checkout_styling:
                  type:
                  - object
                  - 'null'
                  properties:
                    background_color:
                      type:
                      - string
                      - 'null'
                      description: 'A hex color code for the checkout page background,
                        applied to the order summary panel (e.g. #F4F4F5).'
                    border_style:
                      oneOf:
                      - "$ref": "#/components/schemas/CheckoutShapes"
                      - type: 'null'
                      description: The border style for buttons and inputs.
                    button_color:
                      type:
                      - string
                      - 'null'
                      description: 'A hex color code for the button color (e.g. #FF5733).'
                    font_family:
                      oneOf:
                      - "$ref": "#/components/schemas/CheckoutFonts"
                      - type: 'null'
                      description: The font family for the checkout page.
                  required: []
                  description: Checkout styling overrides for this plan. Pass null
                    to inherit from the company default.
              required:
              - company_id
              - product_id
              description: Parameters for CreatePlan
      x-codeSamples:
      - lang: JavaScript
        source: |-
          import Whop from '@whop/sdk';

          const client = new Whop({
            apiKey: process.env['WHOP_API_KEY'], // This is the default and can be omitted
          });

          const plan = await client.plans.create({
            company_id: 'biz_xxxxxxxxxxxxxx',
            product_id: 'prod_xxxxxxxxxxxxx',
          });

          console.log(plan.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          plan = client.plans.create(
              company_id="biz_xxxxxxxxxxxxxx",
              product_id="prod_xxxxxxxxxxxxx",
          )
          print(plan.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          plan = whop.plans.create(company_id: "biz_xxxxxxxxxxxxxx", product_id: "prod_xxxxxxxxxxxxx")

          puts(plan)
components:
  schemas:
    CheckoutFonts:
      type: string
      enum:
      - system
      - roboto
      - open_sans
      description: The different font families available for checkout pages.
    CheckoutShapes:
      type: string
      enum:
      - rounded
      - pill
      - rectangular
      description: The different border-radius styles available for checkout pages.
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
    CustomFieldTypes:
      type: string
      const: text
      description: The type of the custom field.
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
    Plan:
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
        tax_type:
          "$ref": "#/components/schemas/TaxTypes"
          description: 'How tax is handled for this plan: ''inclusive'' (tax included
            in price), ''exclusive'' (tax added at checkout), or ''unspecified'' (tax
            not configured).'
        collect_tax:
          type: boolean
          description: Whether tax is collected on purchases of this plan, based on
            the company's tax configuration.
        custom_fields:
          type: array
          items:
            type: object
            properties:
              id:
                type: string
                description: The unique identifier for the custom field.
                example: field_xxxxxxxxxxxx
              field_type:
                "$ref": "#/components/schemas/CustomFieldTypes"
                description: What type of input field to use.
              name:
                type: string
                description: The title/header of the custom field.
              order:
                type:
                - integer
                - 'null'
                description: How the custom field should be ordered when rendered
                  on the checkout page.
                example: 42
              placeholder:
                type:
                - string
                - 'null'
                description: An example response displayed in the input field.
              required:
                type: boolean
                description: Whether or not the custom field is required.
            required:
            - id
            - field_type
            - name
            - order
            - placeholder
            - required
            description: An object representing a custom field for a plan.
          description: Custom input fields displayed on the checkout form that collect
            additional information from the buyer.
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
      - tax_type
      - collect_tax
      - custom_fields
      description: A plan defines pricing and billing terms for a checkout. Plans
        can optionally belong to a product, where they represent different pricing
        options such as one-time payments, recurring subscriptions, or free trials.
    PlanTypes:
      type: string
      enum:
      - renewal
      - one_time
      description: The type of plan that can be attached to a product
    ReleaseMethod:
      type: string
      enum:
      - buy_now
      - waitlist
      description: The methods of how a plan can be released.
    TaxTypes:
      type: string
      enum:
      - inclusive
      - exclusive
      - unspecified
      description: Whether or not the tax is included in a plan's price (or if it
        hasn't been set up)
    Visibility:
      type: string
      enum:
      - visible
      - hidden
      - archived
      - quick_link
      description: Visibility of a resource
```
