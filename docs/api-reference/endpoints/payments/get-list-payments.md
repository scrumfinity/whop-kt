# GET /payments — List payments

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/payments`
- **Operation ID:** `listPayment`
- **Tags:** `Payments`
- **Required bearer scopes:** `payment:basic:read`, `plan:basic:read`, `access_pass:basic:read`, `member:email:read`, `member:basic:read`, `member:phone:read`, `promo_code:basic:read`

## Description

Returns a paginated list of payments for the actor in context, with optional filtering by product, plan, status, billing reason, currency, and creation date.

Required permissions:
 - `payment:basic:read`
 - `plan:basic:read`
 - `access_pass:basic:read`
 - `member:email:read`
 - `member:basic:read`
 - `member:phone:read`
 - `promo_code:basic:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `after` | query | no | `string \| null` | Returns the elements in the list that come after the specified cursor. |  |
| `before` | query | no | `string \| null` | Returns the elements in the list that come before the specified cursor. |  |
| `first` | query | no | `integer \| null` | Returns the first _n_ elements from the list. | 42 |
| `last` | query | no | `integer \| null` | Returns the last _n_ elements from the list. | 42 |
| `company_id` | query | no | `string \| null` | The unique identifier of the company to list payments for. | biz_xxxxxxxxxxxxxx |
| `direction` | query | no | `Direction \| null` | The sort direction for ordering results, either ascending or descending. |  |
| `order` | query | no | `ReceiptV2Order \| null` | The field to order results by, such as creation date. |  |
| `product_ids` | query | no | `array<string>` | Filter payments to only those associated with these specific product identifiers. |  |
| `billing_reasons` | query | no | `array<BillingReasons>` | Filter payments by their billing reason. |  |
| `currencies` | query | no | `array<Currencies>` | Filter payments by their currency code. |  |
| `plan_ids` | query | no | `array<string>` | Filter payments to only those associated with these specific plan identifiers. |  |
| `statuses` | query | no | `array<ReceiptStatus>` | Filter payments by their current status. |  |
| `substatuses` | query | no | `array<FriendlyReceiptStatus>` | Filter payments by their current substatus for more granular filtering. |  |
| `include_free` | query | no | `boolean \| null` | Whether to include payments with a zero amount. |  |
| `created_before` | query | no | `string \| null / date-time` | Only return payments created before this timestamp. | 2023-12-01T05:00:00.401Z |
| `created_after` | query | no | `string \| null / date-time` | Only return payments created after this timestamp. | 2023-12-01T05:00:00.401Z |
| `updated_before` | query | no | `string \| null / date-time` | Only return payments last updated before this timestamp. | 2023-12-01T05:00:00.401Z |
| `updated_after` | query | no | `string \| null / date-time` | Only return payments last updated after this timestamp. | 2023-12-01T05:00:00.401Z |
| `query` | query | no | `string \| null` | Search payments by user ID, membership ID, user email, name, or username. Email filtering requires the member:email:read permission. |  |

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
      "$ref": "#/components/schemas/PaymentListItem"
    description: A list of nodes.
  page_info:
    "$ref": "#/components/schemas/PageInfo"
    description: Information to aid in pagination.
required:
- data
- page_info
description: The connection type for Receipt.
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
for await (const paymentListResponse of client.payments.list()) {
  console.log(paymentListResponse.id);
}
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
page = client.payments.list()
page = page.data[0]
print(page.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

page = whop.payments.list

puts(page)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  BillingReasons:
    type: string
    enum:
    - subscription_create
    - subscription_cycle
    - subscription_update
    - one_time
    - manual
    - subscription
    description: The reason why a specific payment was billed
  CardBrands:
    type: string
    enum:
    - mastercard
    - visa
    - amex
    - discover
    - unionpay
    - jcb
    - diners
    - link
    - troy
    - visadankort
    - visabancontact
    - china_union_pay
    - rupay
    - jcbrupay
    - elo
    - maestro
    - tarjeta_naranja
    - cirrus
    - nspk_mir
    - verve
    - ebt
    - private_label
    - local_brand
    - uatp
    - wexcard
    - uzcard
    - meeza
    - hrg_store_card
    - girocard
    - fuel_card
    - dankort
    - carnet
    - atm_card
    - china_union_payuzcard
    - codensa
    - cabal
    - hipercard
    - jcblankapay
    - cmi
    - unknown
    description: Possible card brands that a payment token can have
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
  FriendlyReceiptStatus:
    type: string
    enum:
    - succeeded
    - pending
    - failed
    - past_due
    - canceled
    - price_too_low
    - uncollectible
    - refunded
    - auto_refunded
    - partially_refunded
    - dispute_warning
    - dispute_needs_response
    - dispute_warning_needs_response
    - resolution_needs_response
    - dispute_under_review
    - dispute_warning_under_review
    - resolution_under_review
    - dispute_won
    - dispute_warning_closed
    - resolution_won
    - dispute_lost
    - dispute_closed
    - resolution_lost
    - drafted
    - incomplete
    - unresolved
    - open_dispute
    - open_resolution
    description: The friendly status of a payment. This is a derived status that provides
      a human-readable summary of the payment state, combining the underlying status
      and substatus fields.
  MembershipStatus:
    type: string
    enum:
    - trialing
    - active
    - past_due
    - completed
    - canceled
    - expired
    - unresolved
    - drafted
    - canceling
    description: The status of a membership
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
  PaymentListItem:
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
      substatus:
        "$ref": "#/components/schemas/FriendlyReceiptStatus"
        description: The friendly status of the payment.
      refundable:
        type: boolean
        description: True only for payments that are `paid`, have not been fully refunded,
          and were processed by a payment processor that allows refunds.
      retryable:
        type: boolean
        description: True when the payment status is `open` and its membership is
          in one of the retry-eligible states (`active`, `trialing`, `completed`,
          or `past_due`); otherwise false. Used to decide if Whop can attempt the
          charge again.
      voidable:
        type: boolean
        description: True when the payment is tied to a membership in `past_due`,
          the payment status is `open`, and the processor allows voiding payments;
          otherwise false.
      created_at:
        type: string
        format: date-time
        description: The datetime the payment was created.
        example: '2023-12-01T05:00:00.401Z'
      updated_at:
        type: string
        format: date-time
        description: The datetime the payment was last updated.
        example: '2023-12-01T05:00:00.401Z'
      paid_at:
        type:
        - string
        - 'null'
        format: date-time
        description: The time at which this payment was successfully collected. Null
          if the payment has not yet succeeded. As an ISO 8601 timestamp.
        example: '2023-12-01T05:00:00.401Z'
      last_payment_attempt:
        type:
        - string
        - 'null'
        format: date-time
        description: The time of the last payment attempt.
        example: '2023-12-01T05:00:00.401Z'
      next_payment_attempt:
        type:
        - string
        - 'null'
        format: date-time
        description: The time of the next schedule payment retry.
        example: '2023-12-01T05:00:00.401Z'
      dispute_alerted_at:
        type:
        - string
        - 'null'
        format: date-time
        description: When an alert came in that this transaction will be disputed
        example: '2023-12-01T05:00:00.401Z'
      refunded_at:
        type:
        - string
        - 'null'
        format: date-time
        description: When the payment was refunded (if applicable).
        example: '2023-12-01T05:00:00.401Z'
      plan:
        type:
        - object
        - 'null'
        properties:
          id:
            type: string
            description: The unique identifier for the plan.
            example: plan_xxxxxxxxxxxxx
          internal_notes:
            type:
            - string
            - 'null'
            description: A personal description or notes section for the business.
        required:
        - id
        - internal_notes
        description: The plan attached to this payment.
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
          route:
            type: string
            description: The URL slug used in the product's public link (e.g., 'my-product'
              in whop.com/company/my-product).
            example: pickaxe-analytics
        required:
        - id
        - title
        - route
        description: The product this payment was made for
      user:
        type:
        - object
        - 'null'
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
          email:
            type:
            - string
            - 'null'
            description: The user's email address. Requires the member:email:read
              permission to access. Null if not authorized.
            example: john.doe@example.com
        required:
        - id
        - name
        - username
        - email
        description: The user that made this payment.
      membership:
        type:
        - object
        - 'null'
        properties:
          id:
            type: string
            description: The unique identifier for the membership.
            example: mem_xxxxxxxxxxxxxx
          status:
            "$ref": "#/components/schemas/MembershipStatus"
            description: The state of the membership.
        required:
        - id
        - status
        description: The membership attached to this payment.
      member:
        type:
        - object
        - 'null'
        properties:
          id:
            type: string
            description: The unique identifier for the company member.
          phone:
            type:
            - string
            - 'null'
            description: The phone number for the member, if available.
        required:
        - id
        - phone
        description: The member attached to this payment.
      payment_method:
        type:
        - object
        - 'null'
        properties:
          id:
            type: string
            description: The unique identifier for the payment token.
            example: payt_xxxxxxxxxxxxx
          created_at:
            type: string
            format: date-time
            description: The datetime the payment token was created.
            example: '2023-12-01T05:00:00.401Z'
          payment_method_type:
            "$ref": "#/components/schemas/PaymentMethodTypes"
            description: The payment method type of the payment method
          card:
            type:
            - object
            - 'null'
            properties:
              brand:
                oneOf:
                - "$ref": "#/components/schemas/CardBrands"
                - type: 'null'
                description: The card network (e.g., visa, mastercard, amex). Null
                  if the brand could not be determined.
              last4:
                type:
                - string
                - 'null'
                description: The last four digits of the card number. Null if not
                  available.
                example: '4242'
              exp_month:
                type:
                - integer
                - 'null'
                description: The two-digit expiration month of the card (1-12). Null
                  if not available.
                minimum: 1
                maximum: 12
                example: 12
              exp_year:
                type:
                - integer
                - 'null'
                description: The two-digit expiration year of the card (e.g., 27 for
                  2027). Null if not available.
                example: 42
            required:
            - brand
            - last4
            - exp_month
            - exp_year
            description: The card data associated with the payment method, if its
              a debit or credit card.
        required:
        - id
        - created_at
        - payment_method_type
        - card
        description: The tokenized payment method reference used for this payment.
          Null if no token was used.
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
            description: The written name of the company.
          route:
            type: string
            description: The slug/route of the company on the Whop site.
        required:
        - id
        - title
        - route
        description: The company for the payment.
      promo_code:
        type:
        - object
        - 'null'
        properties:
          id:
            type: string
            description: The unique identifier for the promo code.
            example: promo_xxxxxxxxxxxx
          code:
            type:
            - string
            - 'null'
            description: The specific code used to apply the promo at checkout.
          amount_off:
            type: number
            description: 'The discount amount. Interpretation depends on promo_type:
              if ''percentage'', this is the percentage (e.g., 20 means 20% off);
              if ''flat_amount'', this is dollars off (e.g., 10.00 means $10.00 off).'
            example: 6.9
          base_currency:
            "$ref": "#/components/schemas/Currencies"
            description: The monetary currency of the promo code.
          promo_type:
            "$ref": "#/components/schemas/PromoTypes"
            description: The type (% or flat amount) of the promo.
          number_of_intervals:
            type:
            - integer
            - 'null'
            description: The number of months the promo is applied for.
            example: 42
        required:
        - id
        - code
        - amount_off
        - base_currency
        - promo_type
        - number_of_intervals
        description: The promo code used for this payment.
      currency:
        oneOf:
        - "$ref": "#/components/schemas/Currencies"
        - type: 'null'
        description: The three-letter ISO currency code for this payment (e.g., 'usd',
          'eur').
      settlement_currency:
        type: string
        description: The currency in which the creator receives payouts and fees are
          charged (e.g., 'usd', 'eur'). For multi-currency payments this differs from
          the payment currency.
      total:
        type:
        - number
        - 'null'
        description: The total to show to the creator (excluding buyer fees).
        example: 6.9
      subtotal:
        type:
        - number
        - 'null'
        description: The subtotal to show to the creator (excluding buyer fees).
        example: 6.9
      usd_total:
        type:
        - number
        - 'null'
        description: The total in USD to show to the creator (excluding buyer fees).
        example: 6.9
      refunded_amount:
        type:
        - number
        - 'null'
        description: The payment refund amount(if applicable).
        example: 6.9
      auto_refunded:
        type: boolean
        description: Whether this payment was auto refunded or not
      amount_after_fees:
        type: number
        description: How much the payment is for after fees
        example: 6.9
      application_fee:
        type:
        - object
        - 'null'
        properties:
          id:
            type: string
            description: The unique identifier for the application fee.
            example: apfee_xxxxxxxxxxxx
          amount:
            type: number
            description: The application fee amount.
            example: 6.9
          amount_captured:
            type: number
            description: The amount of the application fee that has been captured.
            example: 6.9
          amount_refunded:
            type: number
            description: The amount of the application fee that has been refunded.
            example: 6.9
          currency:
            "$ref": "#/components/schemas/Currencies"
            description: The currency of the application fee.
          created_at:
            type: string
            format: date-time
            description: The datetime the application fee was created.
            example: '2023-12-01T05:00:00.401Z'
        required:
        - id
        - amount
        - amount_captured
        - amount_refunded
        - currency
        - created_at
        description: The application fee charged on this payment.
      card_brand:
        oneOf:
        - "$ref": "#/components/schemas/CardBrands"
        - type: 'null'
        description: Card network reported by the processor (e.g., 'visa', 'mastercard',
          'amex'). Present only when the payment method type is 'card'.
      card_last4:
        type:
        - string
        - 'null'
        description: The last four digits of the card used to make this payment. Null
          if the payment was not made with a card.
        example: '4242'
      billing_address:
        type:
        - object
        - 'null'
        properties:
          name:
            type:
            - string
            - 'null'
            description: The name of the customer.
          line1:
            type:
            - string
            - 'null'
            description: The line 1 of the address.
          line2:
            type:
            - string
            - 'null'
            description: The line 2 of the address.
          city:
            type:
            - string
            - 'null'
            description: The city of the address.
          state:
            type:
            - string
            - 'null'
            description: The state of the address.
          postal_code:
            type:
            - string
            - 'null'
            description: The postal code of the address.
          country:
            type:
            - string
            - 'null'
            description: The country of the address.
        required:
        - name
        - line1
        - line2
        - city
        - state
        - postal_code
        - country
        description: The address of the user who made the payment.
      payment_method_type:
        oneOf:
        - "$ref": "#/components/schemas/PaymentMethodTypes"
        - type: 'null'
        description: The type of payment instrument used for this payment (e.g., card,
          Cash App, iDEAL, Klarna, crypto). Null when the processor does not supply
          a type.
      billing_reason:
        oneOf:
        - "$ref": "#/components/schemas/BillingReasons"
        - type: 'null'
        description: The machine-readable reason this charge was created, such as
          initial subscription purchase, renewal cycle, or one-time payment.
      payments_failed:
        type:
        - integer
        - 'null'
        description: The number of failed payment attempts for the payment.
        example: 42
      tax_amount:
        type:
        - number
        - 'null'
        description: The calculated amount of the sales/VAT tax (if applicable).
        example: 6.9
      tax_behavior:
        oneOf:
        - "$ref": "#/components/schemas/ReceiptTaxBehaviors"
        - type: 'null'
        description: The type of tax inclusivity applied to the payment, for determining
          whether the tax is included in the final price, or paid on top.
      failure_message:
        type:
        - string
        - 'null'
        description: If the payment failed, the reason for the failure.
      metadata:
        type:
        - object
        - 'null'
        additionalProperties: true
        description: The custom metadata stored on this payment. This will be copied
          over to the checkout configuration for which this payment was made
      checkout_configuration_id:
        type:
        - string
        - 'null'
        description: The ID of the checkout session/configuration that produced this
          payment, if any. Use this to map payments back to the checkout configuration
          that created them.
    required:
    - id
    - status
    - substatus
    - refundable
    - retryable
    - voidable
    - created_at
    - updated_at
    - paid_at
    - last_payment_attempt
    - next_payment_attempt
    - dispute_alerted_at
    - refunded_at
    - plan
    - product
    - user
    - membership
    - member
    - payment_method
    - company
    - promo_code
    - currency
    - settlement_currency
    - total
    - subtotal
    - usd_total
    - refunded_amount
    - auto_refunded
    - amount_after_fees
    - application_fee
    - card_brand
    - card_last4
    - billing_address
    - payment_method_type
    - billing_reason
    - payments_failed
    - tax_amount
    - tax_behavior
    - failure_message
    - metadata
    - checkout_configuration_id
    description: A payment represents a completed or attempted charge. Payments track
      the amount, status, currency, and payment method used.
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
  PromoTypes:
    type: string
    enum:
    - percentage
    - flat_amount
    description: The type of promo code used to discount a plan
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
  ReceiptTaxBehaviors:
    type: string
    enum:
    - exclusive
    - inclusive
    - unspecified
    - unable_to_collect
    description: The type of tax inclusivity applied to the receipt, for determining
      whether the tax is included in the final price, or paid on top.
  ReceiptV2Order:
    type: string
    enum:
    - final_amount
    - created_at
    - paid_at
    description: The order to sort the results by.
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
  "/payments":
    get:
      tags:
      - Payments
      operationId: listPayment
      summary: List payments
      description: |-
        Returns a paginated list of payments for the actor in context, with optional filtering by product, plan, status, billing reason, currency, and creation date.

        Required permissions:
         - `payment:basic:read`
         - `plan:basic:read`
         - `access_pass:basic:read`
         - `member:email:read`
         - `member:basic:read`
         - `member:phone:read`
         - `promo_code:basic:read`
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
        required: false
        schema:
          type:
          - string
          - 'null'
          description: The unique identifier of the company to list payments for.
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
          description: The sort direction for ordering results, either ascending or
            descending.
        explode: true
        style: form
      - name: order
        in: query
        required: false
        schema:
          oneOf:
          - "$ref": "#/components/schemas/ReceiptV2Order"
          - type: 'null'
          description: The field to order results by, such as creation date.
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
          description: Filter payments to only those associated with these specific
            product identifiers.
        explode: true
        style: form
      - name: billing_reasons
        in: query
        required: false
        schema:
          type:
          - array
          - 'null'
          items:
            "$ref": "#/components/schemas/BillingReasons"
          description: Filter payments by their billing reason.
        explode: true
        style: form
      - name: currencies
        in: query
        required: false
        schema:
          type:
          - array
          - 'null'
          items:
            "$ref": "#/components/schemas/Currencies"
          description: Filter payments by their currency code.
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
          description: Filter payments to only those associated with these specific
            plan identifiers.
        explode: true
        style: form
      - name: statuses
        in: query
        required: false
        schema:
          type:
          - array
          - 'null'
          items:
            "$ref": "#/components/schemas/ReceiptStatus"
          description: Filter payments by their current status.
        explode: true
        style: form
      - name: substatuses
        in: query
        required: false
        schema:
          type:
          - array
          - 'null'
          items:
            "$ref": "#/components/schemas/FriendlyReceiptStatus"
          description: Filter payments by their current substatus for more granular
            filtering.
        explode: true
        style: form
      - name: include_free
        in: query
        required: false
        schema:
          type:
          - boolean
          - 'null'
          description: Whether to include payments with a zero amount.
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
          description: Only return payments created before this timestamp.
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
          description: Only return payments created after this timestamp.
          example: '2023-12-01T05:00:00.401Z'
        explode: true
        style: form
      - name: updated_before
        in: query
        required: false
        schema:
          type:
          - string
          - 'null'
          format: date-time
          description: Only return payments last updated before this timestamp.
          example: '2023-12-01T05:00:00.401Z'
        explode: true
        style: form
      - name: updated_after
        in: query
        required: false
        schema:
          type:
          - string
          - 'null'
          format: date-time
          description: Only return payments last updated after this timestamp.
          example: '2023-12-01T05:00:00.401Z'
        explode: true
        style: form
      - name: query
        in: query
        required: false
        schema:
          type:
          - string
          - 'null'
          description: Search payments by user ID, membership ID, user email, name,
            or username. Email filtering requires the member:email:read permission.
        explode: true
        style: form
      x-group-title: Payins
      security:
      - bearerAuth:
        - payment:basic:read
        - plan:basic:read
        - access_pass:basic:read
        - member:email:read
        - member:basic:read
        - member:phone:read
        - promo_code:basic:read
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
                      "$ref": "#/components/schemas/PaymentListItem"
                    description: A list of nodes.
                  page_info:
                    "$ref": "#/components/schemas/PageInfo"
                    description: Information to aid in pagination.
                required:
                - data
                - page_info
                description: The connection type for Receipt.
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
          for await (const paymentListResponse of client.payments.list()) {
            console.log(paymentListResponse.id);
          }
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          page = client.payments.list()
          page = page.data[0]
          print(page.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          page = whop.payments.list

          puts(page)
components:
  schemas:
    BillingReasons:
      type: string
      enum:
      - subscription_create
      - subscription_cycle
      - subscription_update
      - one_time
      - manual
      - subscription
      description: The reason why a specific payment was billed
    CardBrands:
      type: string
      enum:
      - mastercard
      - visa
      - amex
      - discover
      - unionpay
      - jcb
      - diners
      - link
      - troy
      - visadankort
      - visabancontact
      - china_union_pay
      - rupay
      - jcbrupay
      - elo
      - maestro
      - tarjeta_naranja
      - cirrus
      - nspk_mir
      - verve
      - ebt
      - private_label
      - local_brand
      - uatp
      - wexcard
      - uzcard
      - meeza
      - hrg_store_card
      - girocard
      - fuel_card
      - dankort
      - carnet
      - atm_card
      - china_union_payuzcard
      - codensa
      - cabal
      - hipercard
      - jcblankapay
      - cmi
      - unknown
      description: Possible card brands that a payment token can have
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
    FriendlyReceiptStatus:
      type: string
      enum:
      - succeeded
      - pending
      - failed
      - past_due
      - canceled
      - price_too_low
      - uncollectible
      - refunded
      - auto_refunded
      - partially_refunded
      - dispute_warning
      - dispute_needs_response
      - dispute_warning_needs_response
      - resolution_needs_response
      - dispute_under_review
      - dispute_warning_under_review
      - resolution_under_review
      - dispute_won
      - dispute_warning_closed
      - resolution_won
      - dispute_lost
      - dispute_closed
      - resolution_lost
      - drafted
      - incomplete
      - unresolved
      - open_dispute
      - open_resolution
      description: The friendly status of a payment. This is a derived status that
        provides a human-readable summary of the payment state, combining the underlying
        status and substatus fields.
    MembershipStatus:
      type: string
      enum:
      - trialing
      - active
      - past_due
      - completed
      - canceled
      - expired
      - unresolved
      - drafted
      - canceling
      description: The status of a membership
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
    PaymentListItem:
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
        substatus:
          "$ref": "#/components/schemas/FriendlyReceiptStatus"
          description: The friendly status of the payment.
        refundable:
          type: boolean
          description: True only for payments that are `paid`, have not been fully
            refunded, and were processed by a payment processor that allows refunds.
        retryable:
          type: boolean
          description: True when the payment status is `open` and its membership is
            in one of the retry-eligible states (`active`, `trialing`, `completed`,
            or `past_due`); otherwise false. Used to decide if Whop can attempt the
            charge again.
        voidable:
          type: boolean
          description: True when the payment is tied to a membership in `past_due`,
            the payment status is `open`, and the processor allows voiding payments;
            otherwise false.
        created_at:
          type: string
          format: date-time
          description: The datetime the payment was created.
          example: '2023-12-01T05:00:00.401Z'
        updated_at:
          type: string
          format: date-time
          description: The datetime the payment was last updated.
          example: '2023-12-01T05:00:00.401Z'
        paid_at:
          type:
          - string
          - 'null'
          format: date-time
          description: The time at which this payment was successfully collected.
            Null if the payment has not yet succeeded. As an ISO 8601 timestamp.
          example: '2023-12-01T05:00:00.401Z'
        last_payment_attempt:
          type:
          - string
          - 'null'
          format: date-time
          description: The time of the last payment attempt.
          example: '2023-12-01T05:00:00.401Z'
        next_payment_attempt:
          type:
          - string
          - 'null'
          format: date-time
          description: The time of the next schedule payment retry.
          example: '2023-12-01T05:00:00.401Z'
        dispute_alerted_at:
          type:
          - string
          - 'null'
          format: date-time
          description: When an alert came in that this transaction will be disputed
          example: '2023-12-01T05:00:00.401Z'
        refunded_at:
          type:
          - string
          - 'null'
          format: date-time
          description: When the payment was refunded (if applicable).
          example: '2023-12-01T05:00:00.401Z'
        plan:
          type:
          - object
          - 'null'
          properties:
            id:
              type: string
              description: The unique identifier for the plan.
              example: plan_xxxxxxxxxxxxx
            internal_notes:
              type:
              - string
              - 'null'
              description: A personal description or notes section for the business.
          required:
          - id
          - internal_notes
          description: The plan attached to this payment.
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
            route:
              type: string
              description: The URL slug used in the product's public link (e.g., 'my-product'
                in whop.com/company/my-product).
              example: pickaxe-analytics
          required:
          - id
          - title
          - route
          description: The product this payment was made for
        user:
          type:
          - object
          - 'null'
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
            email:
              type:
              - string
              - 'null'
              description: The user's email address. Requires the member:email:read
                permission to access. Null if not authorized.
              example: john.doe@example.com
          required:
          - id
          - name
          - username
          - email
          description: The user that made this payment.
        membership:
          type:
          - object
          - 'null'
          properties:
            id:
              type: string
              description: The unique identifier for the membership.
              example: mem_xxxxxxxxxxxxxx
            status:
              "$ref": "#/components/schemas/MembershipStatus"
              description: The state of the membership.
          required:
          - id
          - status
          description: The membership attached to this payment.
        member:
          type:
          - object
          - 'null'
          properties:
            id:
              type: string
              description: The unique identifier for the company member.
            phone:
              type:
              - string
              - 'null'
              description: The phone number for the member, if available.
          required:
          - id
          - phone
          description: The member attached to this payment.
        payment_method:
          type:
          - object
          - 'null'
          properties:
            id:
              type: string
              description: The unique identifier for the payment token.
              example: payt_xxxxxxxxxxxxx
            created_at:
              type: string
              format: date-time
              description: The datetime the payment token was created.
              example: '2023-12-01T05:00:00.401Z'
            payment_method_type:
              "$ref": "#/components/schemas/PaymentMethodTypes"
              description: The payment method type of the payment method
            card:
              type:
              - object
              - 'null'
              properties:
                brand:
                  oneOf:
                  - "$ref": "#/components/schemas/CardBrands"
                  - type: 'null'
                  description: The card network (e.g., visa, mastercard, amex). Null
                    if the brand could not be determined.
                last4:
                  type:
                  - string
                  - 'null'
                  description: The last four digits of the card number. Null if not
                    available.
                  example: '4242'
                exp_month:
                  type:
                  - integer
                  - 'null'
                  description: The two-digit expiration month of the card (1-12).
                    Null if not available.
                  minimum: 1
                  maximum: 12
                  example: 12
                exp_year:
                  type:
                  - integer
                  - 'null'
                  description: The two-digit expiration year of the card (e.g., 27
                    for 2027). Null if not available.
                  example: 42
              required:
              - brand
              - last4
              - exp_month
              - exp_year
              description: The card data associated with the payment method, if its
                a debit or credit card.
          required:
          - id
          - created_at
          - payment_method_type
          - card
          description: The tokenized payment method reference used for this payment.
            Null if no token was used.
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
              description: The written name of the company.
            route:
              type: string
              description: The slug/route of the company on the Whop site.
          required:
          - id
          - title
          - route
          description: The company for the payment.
        promo_code:
          type:
          - object
          - 'null'
          properties:
            id:
              type: string
              description: The unique identifier for the promo code.
              example: promo_xxxxxxxxxxxx
            code:
              type:
              - string
              - 'null'
              description: The specific code used to apply the promo at checkout.
            amount_off:
              type: number
              description: 'The discount amount. Interpretation depends on promo_type:
                if ''percentage'', this is the percentage (e.g., 20 means 20% off);
                if ''flat_amount'', this is dollars off (e.g., 10.00 means $10.00
                off).'
              example: 6.9
            base_currency:
              "$ref": "#/components/schemas/Currencies"
              description: The monetary currency of the promo code.
            promo_type:
              "$ref": "#/components/schemas/PromoTypes"
              description: The type (% or flat amount) of the promo.
            number_of_intervals:
              type:
              - integer
              - 'null'
              description: The number of months the promo is applied for.
              example: 42
          required:
          - id
          - code
          - amount_off
          - base_currency
          - promo_type
          - number_of_intervals
          description: The promo code used for this payment.
        currency:
          oneOf:
          - "$ref": "#/components/schemas/Currencies"
          - type: 'null'
          description: The three-letter ISO currency code for this payment (e.g.,
            'usd', 'eur').
        settlement_currency:
          type: string
          description: The currency in which the creator receives payouts and fees
            are charged (e.g., 'usd', 'eur'). For multi-currency payments this differs
            from the payment currency.
        total:
          type:
          - number
          - 'null'
          description: The total to show to the creator (excluding buyer fees).
          example: 6.9
        subtotal:
          type:
          - number
          - 'null'
          description: The subtotal to show to the creator (excluding buyer fees).
          example: 6.9
        usd_total:
          type:
          - number
          - 'null'
          description: The total in USD to show to the creator (excluding buyer fees).
          example: 6.9
        refunded_amount:
          type:
          - number
          - 'null'
          description: The payment refund amount(if applicable).
          example: 6.9
        auto_refunded:
          type: boolean
          description: Whether this payment was auto refunded or not
        amount_after_fees:
          type: number
          description: How much the payment is for after fees
          example: 6.9
        application_fee:
          type:
          - object
          - 'null'
          properties:
            id:
              type: string
              description: The unique identifier for the application fee.
              example: apfee_xxxxxxxxxxxx
            amount:
              type: number
              description: The application fee amount.
              example: 6.9
            amount_captured:
              type: number
              description: The amount of the application fee that has been captured.
              example: 6.9
            amount_refunded:
              type: number
              description: The amount of the application fee that has been refunded.
              example: 6.9
            currency:
              "$ref": "#/components/schemas/Currencies"
              description: The currency of the application fee.
            created_at:
              type: string
              format: date-time
              description: The datetime the application fee was created.
              example: '2023-12-01T05:00:00.401Z'
          required:
          - id
          - amount
          - amount_captured
          - amount_refunded
          - currency
          - created_at
          description: The application fee charged on this payment.
        card_brand:
          oneOf:
          - "$ref": "#/components/schemas/CardBrands"
          - type: 'null'
          description: Card network reported by the processor (e.g., 'visa', 'mastercard',
            'amex'). Present only when the payment method type is 'card'.
        card_last4:
          type:
          - string
          - 'null'
          description: The last four digits of the card used to make this payment.
            Null if the payment was not made with a card.
          example: '4242'
        billing_address:
          type:
          - object
          - 'null'
          properties:
            name:
              type:
              - string
              - 'null'
              description: The name of the customer.
            line1:
              type:
              - string
              - 'null'
              description: The line 1 of the address.
            line2:
              type:
              - string
              - 'null'
              description: The line 2 of the address.
            city:
              type:
              - string
              - 'null'
              description: The city of the address.
            state:
              type:
              - string
              - 'null'
              description: The state of the address.
            postal_code:
              type:
              - string
              - 'null'
              description: The postal code of the address.
            country:
              type:
              - string
              - 'null'
              description: The country of the address.
          required:
          - name
          - line1
          - line2
          - city
          - state
          - postal_code
          - country
          description: The address of the user who made the payment.
        payment_method_type:
          oneOf:
          - "$ref": "#/components/schemas/PaymentMethodTypes"
          - type: 'null'
          description: The type of payment instrument used for this payment (e.g.,
            card, Cash App, iDEAL, Klarna, crypto). Null when the processor does not
            supply a type.
        billing_reason:
          oneOf:
          - "$ref": "#/components/schemas/BillingReasons"
          - type: 'null'
          description: The machine-readable reason this charge was created, such as
            initial subscription purchase, renewal cycle, or one-time payment.
        payments_failed:
          type:
          - integer
          - 'null'
          description: The number of failed payment attempts for the payment.
          example: 42
        tax_amount:
          type:
          - number
          - 'null'
          description: The calculated amount of the sales/VAT tax (if applicable).
          example: 6.9
        tax_behavior:
          oneOf:
          - "$ref": "#/components/schemas/ReceiptTaxBehaviors"
          - type: 'null'
          description: The type of tax inclusivity applied to the payment, for determining
            whether the tax is included in the final price, or paid on top.
        failure_message:
          type:
          - string
          - 'null'
          description: If the payment failed, the reason for the failure.
        metadata:
          type:
          - object
          - 'null'
          additionalProperties: true
          description: The custom metadata stored on this payment. This will be copied
            over to the checkout configuration for which this payment was made
        checkout_configuration_id:
          type:
          - string
          - 'null'
          description: The ID of the checkout session/configuration that produced
            this payment, if any. Use this to map payments back to the checkout configuration
            that created them.
      required:
      - id
      - status
      - substatus
      - refundable
      - retryable
      - voidable
      - created_at
      - updated_at
      - paid_at
      - last_payment_attempt
      - next_payment_attempt
      - dispute_alerted_at
      - refunded_at
      - plan
      - product
      - user
      - membership
      - member
      - payment_method
      - company
      - promo_code
      - currency
      - settlement_currency
      - total
      - subtotal
      - usd_total
      - refunded_amount
      - auto_refunded
      - amount_after_fees
      - application_fee
      - card_brand
      - card_last4
      - billing_address
      - payment_method_type
      - billing_reason
      - payments_failed
      - tax_amount
      - tax_behavior
      - failure_message
      - metadata
      - checkout_configuration_id
      description: A payment represents a completed or attempted charge. Payments
        track the amount, status, currency, and payment method used.
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
    PromoTypes:
      type: string
      enum:
      - percentage
      - flat_amount
      description: The type of promo code used to discount a plan
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
    ReceiptTaxBehaviors:
      type: string
      enum:
      - exclusive
      - inclusive
      - unspecified
      - unable_to_collect
      description: The type of tax inclusivity applied to the receipt, for determining
        whether the tax is included in the final price, or paid on top.
    ReceiptV2Order:
      type: string
      enum:
      - final_amount
      - created_at
      - paid_at
      description: The order to sort the results by.
```
