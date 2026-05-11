# PATCH /invoices/{id} — Update invoice

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `PATCH`
- **Path:** `/invoices/{id}`
- **Operation ID:** `updateInvoice`
- **Tags:** `Invoices`
- **Required bearer scopes:** `invoice:update`

## Description

Update a draft invoice's details.

Required permissions:
 - `invoice:update`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `id` | path | yes | `string` | The unique identifier of the invoice to update. | inv_xxxxxxxxxxxxxx |

## Request body

Required: **no**
Content types: `application/json`

### `application/json` request schema

```yaml
type: object
properties:
  automatically_finalizes_at:
    type:
    - string
    - 'null'
    format: date-time
    description: The date and time when the invoice will be automatically finalized.
      For charge_automatically, triggers an automatic charge. For send_invoice, sends
      the invoice email at the specified time.
    example: '2023-12-01T05:00:00.401Z'
  billing_address:
    type:
    - object
    - 'null'
    properties:
      city:
        type:
        - string
        - 'null'
        description: The city of the address.
      country:
        type:
        - string
        - 'null'
        description: The country of the address.
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
      name:
        type:
        - string
        - 'null'
        description: The name of the customer.
      phone:
        type:
        - string
        - 'null'
        description: The phone number of the customer.
      postal_code:
        type:
        - string
        - 'null'
        description: The postal code of the address.
      state:
        type:
        - string
        - 'null'
        description: The state of the address.
      tax_id_type:
        oneOf:
        - "$ref": "#/components/schemas/TaxIdentifierTypes"
        - type: 'null'
        description: The type of tax identifier.
      tax_id_value:
        type:
        - string
        - 'null'
        description: The value of the tax identifier.
    required: []
    description: Inline billing address to create or update a mailing address for
      this invoice.
  charge_buyer_fee:
    type:
    - boolean
    - 'null'
    description: Whether to charge the customer a buyer fee on this invoice.
  collection_method:
    oneOf:
    - "$ref": "#/components/schemas/InvoiceCollectionMethods"
    - type: 'null'
    description: How the invoice should be collected.
  customer_name:
    type:
    - string
    - 'null'
    description: The name of the customer.
  due_date:
    type:
    - string
    - 'null'
    format: date-time
    description: The date by which the invoice must be paid.
    example: '2023-12-01T05:00:00.401Z'
  email_address:
    type:
    - string
    - 'null'
    description: The email address of the customer.
  line_items:
    type:
    - array
    - 'null'
    items:
      type: object
      properties:
        label:
          type: string
          description: The label or description for this line item.
        quantity:
          type:
          - number
          - 'null'
          description: The quantity of this line item. Defaults to 1.
          example: 6.9
        unit_price:
          type: number
          description: 'The unit price for this line item. Provided as a number in
            the specified currency. Eg: 10.43 for $10.43'
          example: 6.9
      required:
      - label
      - unit_price
      description: A single line item to include on the invoice, with a label, quantity,
        and unit price.
    description: Line items that break down the invoice total.
  mailing_address_id:
    type:
    - string
    - 'null'
    description: The unique identifier of an existing mailing address to attach.
    example: ma_xxxxxxxxxxxxxxx
  member_id:
    type:
    - string
    - 'null'
    description: The unique identifier of a member to assign as the customer.
    example: mber_xxxxxxxxxxxxx
  payment_method_id:
    type:
    - string
    - 'null'
    description: The unique identifier of the payment method to charge.
    example: payt_xxxxxxxxxxxxx
  plan:
    type:
    - object
    - 'null'
    properties:
      billing_period:
        type:
        - integer
        - 'null'
        description: The interval in days at which the plan charges (renewal plans).
        example: 42
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
        description: An array of custom field objects.
      description:
        type:
        - string
        - 'null'
        description: The description of the plan.
      expiration_days:
        type:
        - integer
        - 'null'
        description: The number of days until the membership expires and revokes access
          (expiration plans). For example, 365 for a one-year access period.
        example: 42
      initial_price:
        type:
        - number
        - 'null'
        description: 'An additional amount charged upon first purchase. Use only if
          a one time payment OR you want to charge an additional amount on top of
          the renewal price. Provided as a number in the specified currency. Eg: 10.43
          for $10.43'
        example: 6.9
      internal_notes:
        type:
        - string
        - 'null'
        description: A personal description or notes section for the business.
      legacy_payment_method_controls:
        type:
        - boolean
        - 'null'
        description: Whether this plan uses legacy payment method controls
      payment_method_configuration:
        type:
        - object
        - 'null'
        properties:
          disabled:
            type: array
            items:
              "$ref": "#/components/schemas/PaymentMethodTypes"
            description: An array of payment method identifiers that are explicitly
              disabled. Only applies if the include_platform_defaults is true.
          enabled:
            type: array
            items:
              "$ref": "#/components/schemas/PaymentMethodTypes"
            description: An array of payment method identifiers that are explicitly
              enabled. This means these payment methods will be shown on checkout.
              Example use case is to only enable a specific payment method like cashapp,
              or extending the platform defaults with additional methods.
          include_platform_defaults:
            type:
            - boolean
            - 'null'
            description: Whether Whop's platform default payment method enablement
              settings are included in this configuration. The full list of default
              payment methods can be found in the documentation at docs.whop.com/payments.
        required:
        - disabled
        - enabled
        description: The explicit payment method configuration for the plan. If not
          provided, the platform or company's defaults will apply.
      plan_type:
        oneOf:
        - "$ref": "#/components/schemas/PlanTypes"
        - type: 'null'
        description: Indicates if the plan is a one time payment or recurring.
      release_method:
        oneOf:
        - "$ref": "#/components/schemas/ReleaseMethod"
        - type: 'null'
        description: This is the release method the business uses to sell this plan.
      renewal_price:
        type:
        - number
        - 'null'
        description: 'The amount the customer is charged every billing period. Use
          only if a recurring payment. Provided as a number in the specified currency.
          Eg: 10.43 for $10.43'
        example: 6.9
      stock:
        type:
        - integer
        - 'null'
        description: The number of units available for purchase.
        example: 42
      trial_period_days:
        type:
        - integer
        - 'null'
        description: The number of free trial days added before a renewal plan.
        example: 42
      unlimited_stock:
        type:
        - boolean
        - 'null'
        description: When true, the plan has unlimited stock (stock field is ignored).
          When false, purchases are limited by the stock field.
      visibility:
        oneOf:
        - "$ref": "#/components/schemas/Visibility"
        - type: 'null'
        description: Shows or hides the plan from public/business view.
    required: []
    description: Updated plan attributes.
  product_id:
    type:
    - string
    - 'null'
    description: The unique identifier of an existing product to attach to this invoice.
      Only allowed while the invoice is still a draft.
    example: prod_xxxxxxxxxxxxx
  subscription_billing_anchor_at:
    type:
    - string
    - 'null'
    format: date-time
    description: The date that defines when the subscription billing cycle should
      start.
    example: '2023-12-01T05:00:00.401Z'
required: []
description: Parameters for UpdateInvoice
```


## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `Invoice` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/Invoice"
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

const invoice = await client.invoices.update('inv_xxxxxxxxxxxxxx');

console.log(invoice.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
invoice = client.invoices.update(
    id="inv_xxxxxxxxxxxxxx",
)
print(invoice.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

invoice = whop.invoices.update("inv_xxxxxxxxxxxxxx")

puts(invoice)
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
  CustomFieldTypes:
    type: string
    const: text
    description: The type of the custom field.
  Invoice:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the invoice.
        example: inv_xxxxxxxxxxxxxx
      created_at:
        type: string
        format: date-time
        description: The datetime the invoice was created.
        example: '2023-12-01T05:00:00.401Z'
      status:
        "$ref": "#/components/schemas/InvoiceStatuses"
        description: The current payment status of the invoice, such as draft, open,
          paid, or void.
      number:
        type: string
        description: The sequential invoice number for display purposes.
        example: "#0001"
      due_date:
        type:
        - string
        - 'null'
        format: date-time
        description: The deadline by which payment is expected. Null if the invoice
          is collected automatically.
        example: '2023-12-01T05:00:00.401Z'
      email_address:
        type:
        - string
        - 'null'
        description: The email address of the customer this invoice is addressed to.
          Null if no email is on file.
        example: customer@example.com
      fetch_invoice_token:
        type: string
        description: A signed token that allows fetching invoice data publicly without
          authentication.
        example: eyJhbGciOiJIUzI1NiJ9...
      current_plan:
        type: object
        properties:
          id:
            type: string
            description: The unique identifier for the plan.
            example: plan_xxxxxxxxxxxxx
          formatted_price:
            type: string
            description: The formatted price (including currency) for the plan.
            example: "$10.00"
          currency:
            "$ref": "#/components/schemas/Currencies"
            description: The currency used for all prices on this plan (e.g., 'usd',
              'eur'). All monetary amounts on the plan are denominated in this currency.
        required:
        - id
        - formatted_price
        - currency
        description: The plan that this invoice charges for.
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
        required:
        - id
        - name
        - username
        description: The user this invoice is addressed to. Null if the user account
          has been removed.
    required:
    - id
    - created_at
    - status
    - number
    - due_date
    - email_address
    - fetch_invoice_token
    - current_plan
    - user
    description: An invoice represents an itemized bill sent by a company to a customer
      for a specific product and plan, tracking the amount owed, due date, and payment
      status.
  InvoiceCollectionMethods:
    type: string
    enum:
    - send_invoice
    - charge_automatically
    description: The method of collection for an invoice.
  InvoiceStatuses:
    type: string
    enum:
    - draft
    - open
    - paid
    - past_due
    - uncollectible
    - void
    description: The different statuses an invoice can be in
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
  TaxIdentifierTypes:
    type: string
    enum:
    - ad_nrt
    - ao_tin
    - ar_cuit
    - am_tin
    - aw_tin
    - au_abn
    - au_arn
    - eu_vat
    - az_tin
    - bs_tin
    - bh_vat
    - bd_bin
    - bb_tin
    - by_tin
    - bj_ifu
    - bo_tin
    - ba_tin
    - br_cnpj
    - br_cpf
    - bg_uic
    - bf_ifu
    - kh_tin
    - cm_niu
    - ca_bn
    - ca_gst_hst
    - ca_pst_bc
    - ca_pst_mb
    - ca_pst_sk
    - ca_qst
    - cv_nif
    - cl_tin
    - cn_tin
    - co_nit
    - cd_nif
    - cr_tin
    - hr_oib
    - do_rcn
    - ec_ruc
    - eg_tin
    - sv_nit
    - et_tin
    - eu_oss_vat
    - ge_vat
    - de_stn
    - gb_vat
    - gn_nif
    - hk_br
    - hu_tin
    - is_vat
    - in_gst
    - id_npwp
    - il_vat
    - jp_cn
    - jp_rn
    - jp_trn
    - kz_bin
    - ke_pin
    - kg_tin
    - la_tin
    - li_uid
    - li_vat
    - my_frp
    - my_itn
    - my_sst
    - mr_nif
    - mx_rfc
    - md_vat
    - me_pib
    - ma_vat
    - np_pan
    - nz_gst
    - ng_tin
    - mk_vat
    - no_vat
    - no_voec
    - om_vat
    - pe_ruc
    - ph_tin
    - pl_nip
    - ro_tin
    - ru_inn
    - ru_kpp
    - sa_vat
    - sn_ninea
    - rs_pib
    - sg_gst
    - sg_uen
    - si_tin
    - za_vat
    - kr_brn
    - es_cif
    - ch_uid
    - ch_vat
    - tw_vat
    - tj_tin
    - tz_vat
    - th_vat
    - tr_tin
    - ug_tin
    - ua_vat
    - ae_trn
    - us_ein
    - uy_ruc
    - uz_tin
    - uz_vat
    - ve_rif
    - vn_tin
    - zm_tin
    - zw_tin
    - sr_fin
    - xi_vat
    description: The type of tax identifier
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
  "/invoices/{id}":
    patch:
      tags:
      - Invoices
      operationId: updateInvoice
      summary: Update invoice
      description: |-
        Update a draft invoice's details.

        Required permissions:
         - `invoice:update`
      parameters:
      - name: id
        in: path
        required: true
        description: The unique identifier of the invoice to update.
        schema:
          type: string
          example: inv_xxxxxxxxxxxxxx
      x-group-title: Payins
      security:
      - bearerAuth:
        - invoice:update
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/Invoice"
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
        required: false
        content:
          application/json:
            schema:
              type: object
              properties:
                automatically_finalizes_at:
                  type:
                  - string
                  - 'null'
                  format: date-time
                  description: The date and time when the invoice will be automatically
                    finalized. For charge_automatically, triggers an automatic charge.
                    For send_invoice, sends the invoice email at the specified time.
                  example: '2023-12-01T05:00:00.401Z'
                billing_address:
                  type:
                  - object
                  - 'null'
                  properties:
                    city:
                      type:
                      - string
                      - 'null'
                      description: The city of the address.
                    country:
                      type:
                      - string
                      - 'null'
                      description: The country of the address.
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
                    name:
                      type:
                      - string
                      - 'null'
                      description: The name of the customer.
                    phone:
                      type:
                      - string
                      - 'null'
                      description: The phone number of the customer.
                    postal_code:
                      type:
                      - string
                      - 'null'
                      description: The postal code of the address.
                    state:
                      type:
                      - string
                      - 'null'
                      description: The state of the address.
                    tax_id_type:
                      oneOf:
                      - "$ref": "#/components/schemas/TaxIdentifierTypes"
                      - type: 'null'
                      description: The type of tax identifier.
                    tax_id_value:
                      type:
                      - string
                      - 'null'
                      description: The value of the tax identifier.
                  required: []
                  description: Inline billing address to create or update a mailing
                    address for this invoice.
                charge_buyer_fee:
                  type:
                  - boolean
                  - 'null'
                  description: Whether to charge the customer a buyer fee on this
                    invoice.
                collection_method:
                  oneOf:
                  - "$ref": "#/components/schemas/InvoiceCollectionMethods"
                  - type: 'null'
                  description: How the invoice should be collected.
                customer_name:
                  type:
                  - string
                  - 'null'
                  description: The name of the customer.
                due_date:
                  type:
                  - string
                  - 'null'
                  format: date-time
                  description: The date by which the invoice must be paid.
                  example: '2023-12-01T05:00:00.401Z'
                email_address:
                  type:
                  - string
                  - 'null'
                  description: The email address of the customer.
                line_items:
                  type:
                  - array
                  - 'null'
                  items:
                    type: object
                    properties:
                      label:
                        type: string
                        description: The label or description for this line item.
                      quantity:
                        type:
                        - number
                        - 'null'
                        description: The quantity of this line item. Defaults to 1.
                        example: 6.9
                      unit_price:
                        type: number
                        description: 'The unit price for this line item. Provided
                          as a number in the specified currency. Eg: 10.43 for $10.43'
                        example: 6.9
                    required:
                    - label
                    - unit_price
                    description: A single line item to include on the invoice, with
                      a label, quantity, and unit price.
                  description: Line items that break down the invoice total.
                mailing_address_id:
                  type:
                  - string
                  - 'null'
                  description: The unique identifier of an existing mailing address
                    to attach.
                  example: ma_xxxxxxxxxxxxxxx
                member_id:
                  type:
                  - string
                  - 'null'
                  description: The unique identifier of a member to assign as the
                    customer.
                  example: mber_xxxxxxxxxxxxx
                payment_method_id:
                  type:
                  - string
                  - 'null'
                  description: The unique identifier of the payment method to charge.
                  example: payt_xxxxxxxxxxxxx
                plan:
                  type:
                  - object
                  - 'null'
                  properties:
                    billing_period:
                      type:
                      - integer
                      - 'null'
                      description: The interval in days at which the plan charges
                        (renewal plans).
                      example: 42
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
                      description: An array of custom field objects.
                    description:
                      type:
                      - string
                      - 'null'
                      description: The description of the plan.
                    expiration_days:
                      type:
                      - integer
                      - 'null'
                      description: The number of days until the membership expires
                        and revokes access (expiration plans). For example, 365 for
                        a one-year access period.
                      example: 42
                    initial_price:
                      type:
                      - number
                      - 'null'
                      description: 'An additional amount charged upon first purchase.
                        Use only if a one time payment OR you want to charge an additional
                        amount on top of the renewal price. Provided as a number in
                        the specified currency. Eg: 10.43 for $10.43'
                      example: 6.9
                    internal_notes:
                      type:
                      - string
                      - 'null'
                      description: A personal description or notes section for the
                        business.
                    legacy_payment_method_controls:
                      type:
                      - boolean
                      - 'null'
                      description: Whether this plan uses legacy payment method controls
                    payment_method_configuration:
                      type:
                      - object
                      - 'null'
                      properties:
                        disabled:
                          type: array
                          items:
                            "$ref": "#/components/schemas/PaymentMethodTypes"
                          description: An array of payment method identifiers that
                            are explicitly disabled. Only applies if the include_platform_defaults
                            is true.
                        enabled:
                          type: array
                          items:
                            "$ref": "#/components/schemas/PaymentMethodTypes"
                          description: An array of payment method identifiers that
                            are explicitly enabled. This means these payment methods
                            will be shown on checkout. Example use case is to only
                            enable a specific payment method like cashapp, or extending
                            the platform defaults with additional methods.
                        include_platform_defaults:
                          type:
                          - boolean
                          - 'null'
                          description: Whether Whop's platform default payment method
                            enablement settings are included in this configuration.
                            The full list of default payment methods can be found
                            in the documentation at docs.whop.com/payments.
                      required:
                      - disabled
                      - enabled
                      description: The explicit payment method configuration for the
                        plan. If not provided, the platform or company's defaults
                        will apply.
                    plan_type:
                      oneOf:
                      - "$ref": "#/components/schemas/PlanTypes"
                      - type: 'null'
                      description: Indicates if the plan is a one time payment or
                        recurring.
                    release_method:
                      oneOf:
                      - "$ref": "#/components/schemas/ReleaseMethod"
                      - type: 'null'
                      description: This is the release method the business uses to
                        sell this plan.
                    renewal_price:
                      type:
                      - number
                      - 'null'
                      description: 'The amount the customer is charged every billing
                        period. Use only if a recurring payment. Provided as a number
                        in the specified currency. Eg: 10.43 for $10.43'
                      example: 6.9
                    stock:
                      type:
                      - integer
                      - 'null'
                      description: The number of units available for purchase.
                      example: 42
                    trial_period_days:
                      type:
                      - integer
                      - 'null'
                      description: The number of free trial days added before a renewal
                        plan.
                      example: 42
                    unlimited_stock:
                      type:
                      - boolean
                      - 'null'
                      description: When true, the plan has unlimited stock (stock
                        field is ignored). When false, purchases are limited by the
                        stock field.
                    visibility:
                      oneOf:
                      - "$ref": "#/components/schemas/Visibility"
                      - type: 'null'
                      description: Shows or hides the plan from public/business view.
                  required: []
                  description: Updated plan attributes.
                product_id:
                  type:
                  - string
                  - 'null'
                  description: The unique identifier of an existing product to attach
                    to this invoice. Only allowed while the invoice is still a draft.
                  example: prod_xxxxxxxxxxxxx
                subscription_billing_anchor_at:
                  type:
                  - string
                  - 'null'
                  format: date-time
                  description: The date that defines when the subscription billing
                    cycle should start.
                  example: '2023-12-01T05:00:00.401Z'
              required: []
              description: Parameters for UpdateInvoice
      x-codeSamples:
      - lang: JavaScript
        source: |-
          import Whop from '@whop/sdk';

          const client = new Whop({
            apiKey: process.env['WHOP_API_KEY'], // This is the default and can be omitted
          });

          const invoice = await client.invoices.update('inv_xxxxxxxxxxxxxx');

          console.log(invoice.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          invoice = client.invoices.update(
              id="inv_xxxxxxxxxxxxxx",
          )
          print(invoice.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          invoice = whop.invoices.update("inv_xxxxxxxxxxxxxx")

          puts(invoice)
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
    CustomFieldTypes:
      type: string
      const: text
      description: The type of the custom field.
    Invoice:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the invoice.
          example: inv_xxxxxxxxxxxxxx
        created_at:
          type: string
          format: date-time
          description: The datetime the invoice was created.
          example: '2023-12-01T05:00:00.401Z'
        status:
          "$ref": "#/components/schemas/InvoiceStatuses"
          description: The current payment status of the invoice, such as draft, open,
            paid, or void.
        number:
          type: string
          description: The sequential invoice number for display purposes.
          example: "#0001"
        due_date:
          type:
          - string
          - 'null'
          format: date-time
          description: The deadline by which payment is expected. Null if the invoice
            is collected automatically.
          example: '2023-12-01T05:00:00.401Z'
        email_address:
          type:
          - string
          - 'null'
          description: The email address of the customer this invoice is addressed
            to. Null if no email is on file.
          example: customer@example.com
        fetch_invoice_token:
          type: string
          description: A signed token that allows fetching invoice data publicly without
            authentication.
          example: eyJhbGciOiJIUzI1NiJ9...
        current_plan:
          type: object
          properties:
            id:
              type: string
              description: The unique identifier for the plan.
              example: plan_xxxxxxxxxxxxx
            formatted_price:
              type: string
              description: The formatted price (including currency) for the plan.
              example: "$10.00"
            currency:
              "$ref": "#/components/schemas/Currencies"
              description: The currency used for all prices on this plan (e.g., 'usd',
                'eur'). All monetary amounts on the plan are denominated in this currency.
          required:
          - id
          - formatted_price
          - currency
          description: The plan that this invoice charges for.
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
          required:
          - id
          - name
          - username
          description: The user this invoice is addressed to. Null if the user account
            has been removed.
      required:
      - id
      - created_at
      - status
      - number
      - due_date
      - email_address
      - fetch_invoice_token
      - current_plan
      - user
      description: An invoice represents an itemized bill sent by a company to a customer
        for a specific product and plan, tracking the amount owed, due date, and payment
        status.
    InvoiceCollectionMethods:
      type: string
      enum:
      - send_invoice
      - charge_automatically
      description: The method of collection for an invoice.
    InvoiceStatuses:
      type: string
      enum:
      - draft
      - open
      - paid
      - past_due
      - uncollectible
      - void
      description: The different statuses an invoice can be in
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
    TaxIdentifierTypes:
      type: string
      enum:
      - ad_nrt
      - ao_tin
      - ar_cuit
      - am_tin
      - aw_tin
      - au_abn
      - au_arn
      - eu_vat
      - az_tin
      - bs_tin
      - bh_vat
      - bd_bin
      - bb_tin
      - by_tin
      - bj_ifu
      - bo_tin
      - ba_tin
      - br_cnpj
      - br_cpf
      - bg_uic
      - bf_ifu
      - kh_tin
      - cm_niu
      - ca_bn
      - ca_gst_hst
      - ca_pst_bc
      - ca_pst_mb
      - ca_pst_sk
      - ca_qst
      - cv_nif
      - cl_tin
      - cn_tin
      - co_nit
      - cd_nif
      - cr_tin
      - hr_oib
      - do_rcn
      - ec_ruc
      - eg_tin
      - sv_nit
      - et_tin
      - eu_oss_vat
      - ge_vat
      - de_stn
      - gb_vat
      - gn_nif
      - hk_br
      - hu_tin
      - is_vat
      - in_gst
      - id_npwp
      - il_vat
      - jp_cn
      - jp_rn
      - jp_trn
      - kz_bin
      - ke_pin
      - kg_tin
      - la_tin
      - li_uid
      - li_vat
      - my_frp
      - my_itn
      - my_sst
      - mr_nif
      - mx_rfc
      - md_vat
      - me_pib
      - ma_vat
      - np_pan
      - nz_gst
      - ng_tin
      - mk_vat
      - no_vat
      - no_voec
      - om_vat
      - pe_ruc
      - ph_tin
      - pl_nip
      - ro_tin
      - ru_inn
      - ru_kpp
      - sa_vat
      - sn_ninea
      - rs_pib
      - sg_gst
      - sg_uen
      - si_tin
      - za_vat
      - kr_brn
      - es_cif
      - ch_uid
      - ch_vat
      - tw_vat
      - tj_tin
      - tz_vat
      - th_vat
      - tr_tin
      - ug_tin
      - ua_vat
      - ae_trn
      - us_ein
      - uy_ruc
      - uz_tin
      - uz_vat
      - ve_rif
      - vn_tin
      - zm_tin
      - zw_tin
      - sr_fin
      - xi_vat
      description: The type of tax identifier
    Visibility:
      type: string
      enum:
      - visible
      - hidden
      - archived
      - quick_link
      description: Visibility of a resource
```
