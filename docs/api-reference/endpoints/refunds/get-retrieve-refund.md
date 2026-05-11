# GET /refunds/{id} — Retrieve refund

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/refunds/{id}`
- **Operation ID:** `retrieveRefund`
- **Tags:** `Refunds`
- **Required bearer scopes:** `payment:basic:read`, `member:email:read`, `member:basic:read`, `member:phone:read`

## Description

Retrieves the details of an existing refund.

Required permissions:
 - `payment:basic:read`
 - `member:email:read`
 - `member:basic:read`
 - `member:phone:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `id` | path | yes | `string` | The unique identifier of the refund. | rf_xxxxxxxxxxxxxxx |

## Request body

_No request body._

## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `Refund` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/Refund"
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

const refund = await client.refunds.retrieve('rf_xxxxxxxxxxxxxxx');

console.log(refund.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
refund = client.refunds.retrieve(
    "rf_xxxxxxxxxxxxxxx",
)
print(refund.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

refund = whop.refunds.retrieve("rf_xxxxxxxxxxxxxxx")

puts(refund)
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
  ReceiptTaxBehaviors:
    type: string
    enum:
    - exclusive
    - inclusive
    - unspecified
    - unable_to_collect
    description: The type of tax inclusivity applied to the receipt, for determining
      whether the tax is included in the final price, or paid on top.
  Refund:
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
            description: The total in USD to show to the creator (excluding buyer
              fees).
            example: 6.9
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
          dispute_alerted_at:
            type:
            - string
            - 'null'
            format: date-time
            description: When an alert came in that this transaction will be disputed
            example: '2023-12-01T05:00:00.401Z'
          payment_method_type:
            oneOf:
            - "$ref": "#/components/schemas/PaymentMethodTypes"
            - type: 'null'
            description: The type of payment instrument used for this payment (e.g.,
              card, Cash App, iDEAL, Klarna, crypto). Null when the processor does
              not supply a type.
          billing_reason:
            oneOf:
            - "$ref": "#/components/schemas/BillingReasons"
            - type: 'null'
            description: The machine-readable reason this charge was created, such
              as initial subscription purchase, renewal cycle, or one-time payment.
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
          tax_refunded_amount:
            type:
            - number
            - 'null'
            description: The amount of tax that has been refunded (if applicable).
            example: 6.9
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
        required:
        - id
        - total
        - subtotal
        - usd_total
        - currency
        - created_at
        - paid_at
        - dispute_alerted_at
        - payment_method_type
        - billing_reason
        - card_brand
        - card_last4
        - tax_amount
        - tax_behavior
        - tax_refunded_amount
        - user
        - member
        - membership
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
  "/refunds/{id}":
    get:
      tags:
      - Refunds
      operationId: retrieveRefund
      summary: Retrieve refund
      description: |-
        Retrieves the details of an existing refund.

        Required permissions:
         - `payment:basic:read`
         - `member:email:read`
         - `member:basic:read`
         - `member:phone:read`
      parameters:
      - name: id
        in: path
        required: true
        description: The unique identifier of the refund.
        schema:
          type: string
          example: rf_xxxxxxxxxxxxxxx
      x-group-title: Payins
      security:
      - bearerAuth:
        - payment:basic:read
        - member:email:read
        - member:basic:read
        - member:phone:read
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/Refund"
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

          const refund = await client.refunds.retrieve('rf_xxxxxxxxxxxxxxx');

          console.log(refund.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          refund = client.refunds.retrieve(
              "rf_xxxxxxxxxxxxxxx",
          )
          print(refund.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          refund = whop.refunds.retrieve("rf_xxxxxxxxxxxxxxx")

          puts(refund)
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
    ReceiptTaxBehaviors:
      type: string
      enum:
      - exclusive
      - inclusive
      - unspecified
      - unable_to_collect
      description: The type of tax inclusivity applied to the receipt, for determining
        whether the tax is included in the final price, or paid on top.
    Refund:
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
              description: The total in USD to show to the creator (excluding buyer
                fees).
              example: 6.9
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
            dispute_alerted_at:
              type:
              - string
              - 'null'
              format: date-time
              description: When an alert came in that this transaction will be disputed
              example: '2023-12-01T05:00:00.401Z'
            payment_method_type:
              oneOf:
              - "$ref": "#/components/schemas/PaymentMethodTypes"
              - type: 'null'
              description: The type of payment instrument used for this payment (e.g.,
                card, Cash App, iDEAL, Klarna, crypto). Null when the processor does
                not supply a type.
            billing_reason:
              oneOf:
              - "$ref": "#/components/schemas/BillingReasons"
              - type: 'null'
              description: The machine-readable reason this charge was created, such
                as initial subscription purchase, renewal cycle, or one-time payment.
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
              description: The type of tax inclusivity applied to the payment, for
                determining whether the tax is included in the final price, or paid
                on top.
            tax_refunded_amount:
              type:
              - number
              - 'null'
              description: The amount of tax that has been refunded (if applicable).
              example: 6.9
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
          required:
          - id
          - total
          - subtotal
          - usd_total
          - currency
          - created_at
          - paid_at
          - dispute_alerted_at
          - payment_method_type
          - billing_reason
          - card_brand
          - card_last4
          - tax_amount
          - tax_behavior
          - tax_refunded_amount
          - user
          - member
          - membership
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
