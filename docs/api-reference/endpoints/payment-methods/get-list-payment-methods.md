# GET /payment_methods — List payment methods

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/payment_methods`
- **Operation ID:** `listPaymentMethod`
- **Tags:** `Payment methods`
- **Required bearer scopes:** `member:payment_methods:read`

## Description

Returns a paginated list of payment methods for a member or company, with optional filtering by creation date. A payment method is a stored representation of how a customer intends to pay, such as a card, bank account, or digital wallet.

Required permissions:
 - `member:payment_methods:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `after` | query | no | `string \| null` | Returns the elements in the list that come after the specified cursor. |  |
| `before` | query | no | `string \| null` | Returns the elements in the list that come before the specified cursor. |  |
| `first` | query | no | `integer \| null` | Returns the first _n_ elements from the list. | 42 |
| `last` | query | no | `integer \| null` | Returns the last _n_ elements from the list. | 42 |
| `member_id` | query | no | `string \| null` | The unique identifier of the member to list payment methods for. | mber_xxxxxxxxxxxxx |
| `company_id` | query | no | `string \| null` | The unique identifier of the company. Provide either this or member_id, not both. | biz_xxxxxxxxxxxxxx |
| `direction` | query | no | `Direction \| null` | The sort direction for ordering results, either ascending or descending. |  |
| `created_before` | query | no | `string \| null / date-time` | Only return payment methods created before this timestamp. | 2023-12-01T05:00:00.401Z |
| `created_after` | query | no | `string \| null / date-time` | Only return payment methods created after this timestamp. | 2023-12-01T05:00:00.401Z |

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
      "$ref": "#/components/schemas/PaymentMethodListItem"
    description: A list of nodes.
  page_info:
    "$ref": "#/components/schemas/PageInfo"
    description: Information to aid in pagination.
required:
- data
- page_info
description: The connection type for PaymentMethodInterface.
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
for await (const paymentMethodListResponse of client.paymentMethods.list()) {
  console.log(paymentMethodListResponse);
}
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
page = client.payment_methods.list()
page = page.data[0]
print(page)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

page = whop.payment_methods.list

puts(page)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
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
  PaymentMethodListItem:
    type: object
    oneOf:
    - type: object
      properties:
        typename:
          type: string
          const: BasePaymentMethod
          description: The typename of this object
        id:
          type: string
          description: Represents a unique identifier that is Base64 obfuscated. It
            is often used to refetch an object or as key for a cache. The ID type
            appears in a JSON response as a String; however, it is not intended to
            be human-readable. When expected as an input type, any string (such as
            `"VXNlci0xMA=="`) or integer (such as `4`) input value will be accepted
            as an ID.
          example: payt_xxxxxxxxxxxxx
        created_at:
          type: string
          format: date-time
          description: The time of the event in ISO 8601 UTC format with millisecond
            precision
          example: '2023-12-01T05:00:00.401Z'
        payment_method_type:
          "$ref": "#/components/schemas/PaymentMethodTypes"
          description: The type of payment instrument stored on file (e.g., card,
            us_bank_account, cashapp, ideal, sepa_debit).
      required:
      - typename
      - id
      - created_at
      - payment_method_type
      description: A saved payment method with no type-specific details available.
      title: BasePaymentMethod
    - type: object
      properties:
        typename:
          type: string
          const: CardPaymentMethod
          description: The typename of this object
        id:
          type: string
          description: Represents a unique identifier that is Base64 obfuscated. It
            is often used to refetch an object or as key for a cache. The ID type
            appears in a JSON response as a String; however, it is not intended to
            be human-readable. When expected as an input type, any string (such as
            `"VXNlci0xMA=="`) or integer (such as `4`) input value will be accepted
            as an ID.
        created_at:
          type: string
          format: date-time
          description: The time of the event in ISO 8601 UTC format with millisecond
            precision
          example: '2023-12-01T05:00:00.401Z'
        payment_method_type:
          "$ref": "#/components/schemas/PaymentMethodTypes"
          description: The type of payment instrument stored on file (e.g., card,
            us_bank_account, cashapp, ideal, sepa_debit).
        card:
          type: object
          properties:
            brand:
              oneOf:
              - "$ref": "#/components/schemas/CardBrands"
              - type: 'null'
              description: The card network (e.g., visa, mastercard, amex). Null if
                the brand could not be determined.
            last4:
              type:
              - string
              - 'null'
              description: The last four digits of the card number. Null if not available.
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
          description: The card-specific details for this payment method, including
            brand, last four digits, and expiration.
      required:
      - typename
      - id
      - created_at
      - payment_method_type
      - card
      description: A saved card payment method, including brand, last four digits,
        and expiration details.
      title: CardPaymentMethod
    - type: object
      properties:
        typename:
          type: string
          const: UsBankAccountPaymentMethod
          description: The typename of this object
        id:
          type: string
          description: Represents a unique identifier that is Base64 obfuscated. It
            is often used to refetch an object or as key for a cache. The ID type
            appears in a JSON response as a String; however, it is not intended to
            be human-readable. When expected as an input type, any string (such as
            `"VXNlci0xMA=="`) or integer (such as `4`) input value will be accepted
            as an ID.
        created_at:
          type: string
          format: date-time
          description: The time of the event in ISO 8601 UTC format with millisecond
            precision
          example: '2023-12-01T05:00:00.401Z'
        payment_method_type:
          "$ref": "#/components/schemas/PaymentMethodTypes"
          description: The type of payment instrument stored on file (e.g., card,
            us_bank_account, cashapp, ideal, sepa_debit).
        us_bank_account:
          type: object
          properties:
            bank_name:
              type: string
              description: The name of the financial institution holding the account.
              example: Chase
            last4:
              type: string
              description: The last four digits of the bank account number.
              example: '6789'
            account_type:
              type: string
              description: The type of bank account (e.g., checking, savings).
              example: checking
          required:
          - bank_name
          - last4
          - account_type
          description: The bank account-specific details for this payment method,
            including bank name and last four digits.
      required:
      - typename
      - id
      - created_at
      - payment_method_type
      - us_bank_account
      description: A saved US bank account payment method, including bank name, last
        four digits, and account type.
      title: UsBankAccountPaymentMethod
    - type: object
      properties:
        typename:
          type: string
          const: CashappPaymentMethod
          description: The typename of this object
        id:
          type: string
          description: Represents a unique identifier that is Base64 obfuscated. It
            is often used to refetch an object or as key for a cache. The ID type
            appears in a JSON response as a String; however, it is not intended to
            be human-readable. When expected as an input type, any string (such as
            `"VXNlci0xMA=="`) or integer (such as `4`) input value will be accepted
            as an ID.
        created_at:
          type: string
          format: date-time
          description: The time of the event in ISO 8601 UTC format with millisecond
            precision
          example: '2023-12-01T05:00:00.401Z'
        payment_method_type:
          "$ref": "#/components/schemas/PaymentMethodTypes"
          description: The type of payment instrument stored on file (e.g., card,
            us_bank_account, cashapp, ideal, sepa_debit).
        cashapp:
          type: object
          properties:
            buyer_id:
              type:
              - string
              - 'null'
              description: The unique and immutable identifier assigned by Cash App
                to the buyer. Null if not available.
              example: BUYER_abc123
            cashtag:
              type:
              - string
              - 'null'
              description: The public cashtag handle of the buyer on Cash App. Null
                if not available.
              example: "$jacksmith"
          required:
          - buyer_id
          - cashtag
          description: The Cash App-specific details for this payment method, including
            cashtag and buyer ID.
      required:
      - typename
      - id
      - created_at
      - payment_method_type
      - cashapp
      description: A saved Cash App payment method, including the buyer's cashtag
        and unique identifier.
      title: CashappPaymentMethod
    - type: object
      properties:
        typename:
          type: string
          const: IdealPaymentMethod
          description: The typename of this object
        id:
          type: string
          description: Represents a unique identifier that is Base64 obfuscated. It
            is often used to refetch an object or as key for a cache. The ID type
            appears in a JSON response as a String; however, it is not intended to
            be human-readable. When expected as an input type, any string (such as
            `"VXNlci0xMA=="`) or integer (such as `4`) input value will be accepted
            as an ID.
        created_at:
          type: string
          format: date-time
          description: The time of the event in ISO 8601 UTC format with millisecond
            precision
          example: '2023-12-01T05:00:00.401Z'
        payment_method_type:
          "$ref": "#/components/schemas/PaymentMethodTypes"
          description: The type of payment instrument stored on file (e.g., card,
            us_bank_account, cashapp, ideal, sepa_debit).
        ideal:
          type: object
          properties:
            bank:
              type:
              - string
              - 'null'
              description: The name of the customer's bank used for the iDEAL transaction.
                Null if not available.
              example: ing
            bic:
              type:
              - string
              - 'null'
              description: The Bank Identifier Code (BIC/SWIFT) of the customer's
                bank. Null if not available.
              example: INGBNL2A
          required:
          - bank
          - bic
          description: The iDEAL-specific details for this payment method, including
            bank name and BIC.
      required:
      - typename
      - id
      - created_at
      - payment_method_type
      - ideal
      description: A saved iDEAL payment method, including the customer's bank name
        and BIC code.
      title: IdealPaymentMethod
    - type: object
      properties:
        typename:
          type: string
          const: SepaDebitPaymentMethod
          description: The typename of this object
        id:
          type: string
          description: Represents a unique identifier that is Base64 obfuscated. It
            is often used to refetch an object or as key for a cache. The ID type
            appears in a JSON response as a String; however, it is not intended to
            be human-readable. When expected as an input type, any string (such as
            `"VXNlci0xMA=="`) or integer (such as `4`) input value will be accepted
            as an ID.
        created_at:
          type: string
          format: date-time
          description: The time of the event in ISO 8601 UTC format with millisecond
            precision
          example: '2023-12-01T05:00:00.401Z'
        payment_method_type:
          "$ref": "#/components/schemas/PaymentMethodTypes"
          description: The type of payment instrument stored on file (e.g., card,
            us_bank_account, cashapp, ideal, sepa_debit).
        sepa_debit:
          type: object
          properties:
            bank_code:
              type:
              - string
              - 'null'
              description: The bank code of the financial institution associated with
                this SEPA account. Null if not available.
              example: '37040044'
            branch_code:
              type:
              - string
              - 'null'
              description: The branch code of the financial institution associated
                with this SEPA account. Null if not available.
              example: '0532'
            country:
              type:
              - string
              - 'null'
              description: The two-letter ISO country code where the bank account
                is located. Null if not available.
              example: DE
            last4:
              type:
              - string
              - 'null'
              description: The last four digits of the IBAN associated with this SEPA
                account. Null if not available.
              example: '3000'
          required:
          - bank_code
          - branch_code
          - country
          - last4
          description: The SEPA Direct Debit-specific details for this payment method,
            including bank code and last four IBAN digits.
      required:
      - typename
      - id
      - created_at
      - payment_method_type
      - sepa_debit
      description: A saved SEPA Direct Debit payment method, including the bank code,
        country, and last four IBAN digits.
      title: SepaDebitPaymentMethod
    discriminator:
      propertyName: typename
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
  "/payment_methods":
    get:
      tags:
      - Payment methods
      operationId: listPaymentMethod
      summary: List payment methods
      description: |-
        Returns a paginated list of payment methods for a member or company, with optional filtering by creation date. A payment method is a stored representation of how a customer intends to pay, such as a card, bank account, or digital wallet.

        Required permissions:
         - `member:payment_methods:read`
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
      - name: member_id
        in: query
        required: false
        schema:
          type:
          - string
          - 'null'
          description: The unique identifier of the member to list payment methods
            for.
          example: mber_xxxxxxxxxxxxx
        explode: true
        style: form
      - name: company_id
        in: query
        required: false
        schema:
          type:
          - string
          - 'null'
          description: The unique identifier of the company. Provide either this or
            member_id, not both.
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
      - name: created_before
        in: query
        required: false
        schema:
          type:
          - string
          - 'null'
          format: date-time
          description: Only return payment methods created before this timestamp.
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
          description: Only return payment methods created after this timestamp.
          example: '2023-12-01T05:00:00.401Z'
        explode: true
        style: form
      x-group-title: Payins
      security:
      - bearerAuth:
        - member:payment_methods:read
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
                      "$ref": "#/components/schemas/PaymentMethodListItem"
                    description: A list of nodes.
                  page_info:
                    "$ref": "#/components/schemas/PageInfo"
                    description: Information to aid in pagination.
                required:
                - data
                - page_info
                description: The connection type for PaymentMethodInterface.
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
          for await (const paymentMethodListResponse of client.paymentMethods.list()) {
            console.log(paymentMethodListResponse);
          }
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          page = client.payment_methods.list()
          page = page.data[0]
          print(page)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          page = whop.payment_methods.list

          puts(page)
components:
  schemas:
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
    PaymentMethodListItem:
      type: object
      oneOf:
      - type: object
        properties:
          typename:
            type: string
            const: BasePaymentMethod
            description: The typename of this object
          id:
            type: string
            description: Represents a unique identifier that is Base64 obfuscated.
              It is often used to refetch an object or as key for a cache. The ID
              type appears in a JSON response as a String; however, it is not intended
              to be human-readable. When expected as an input type, any string (such
              as `"VXNlci0xMA=="`) or integer (such as `4`) input value will be accepted
              as an ID.
            example: payt_xxxxxxxxxxxxx
          created_at:
            type: string
            format: date-time
            description: The time of the event in ISO 8601 UTC format with millisecond
              precision
            example: '2023-12-01T05:00:00.401Z'
          payment_method_type:
            "$ref": "#/components/schemas/PaymentMethodTypes"
            description: The type of payment instrument stored on file (e.g., card,
              us_bank_account, cashapp, ideal, sepa_debit).
        required:
        - typename
        - id
        - created_at
        - payment_method_type
        description: A saved payment method with no type-specific details available.
        title: BasePaymentMethod
      - type: object
        properties:
          typename:
            type: string
            const: CardPaymentMethod
            description: The typename of this object
          id:
            type: string
            description: Represents a unique identifier that is Base64 obfuscated.
              It is often used to refetch an object or as key for a cache. The ID
              type appears in a JSON response as a String; however, it is not intended
              to be human-readable. When expected as an input type, any string (such
              as `"VXNlci0xMA=="`) or integer (such as `4`) input value will be accepted
              as an ID.
          created_at:
            type: string
            format: date-time
            description: The time of the event in ISO 8601 UTC format with millisecond
              precision
            example: '2023-12-01T05:00:00.401Z'
          payment_method_type:
            "$ref": "#/components/schemas/PaymentMethodTypes"
            description: The type of payment instrument stored on file (e.g., card,
              us_bank_account, cashapp, ideal, sepa_debit).
          card:
            type: object
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
            description: The card-specific details for this payment method, including
              brand, last four digits, and expiration.
        required:
        - typename
        - id
        - created_at
        - payment_method_type
        - card
        description: A saved card payment method, including brand, last four digits,
          and expiration details.
        title: CardPaymentMethod
      - type: object
        properties:
          typename:
            type: string
            const: UsBankAccountPaymentMethod
            description: The typename of this object
          id:
            type: string
            description: Represents a unique identifier that is Base64 obfuscated.
              It is often used to refetch an object or as key for a cache. The ID
              type appears in a JSON response as a String; however, it is not intended
              to be human-readable. When expected as an input type, any string (such
              as `"VXNlci0xMA=="`) or integer (such as `4`) input value will be accepted
              as an ID.
          created_at:
            type: string
            format: date-time
            description: The time of the event in ISO 8601 UTC format with millisecond
              precision
            example: '2023-12-01T05:00:00.401Z'
          payment_method_type:
            "$ref": "#/components/schemas/PaymentMethodTypes"
            description: The type of payment instrument stored on file (e.g., card,
              us_bank_account, cashapp, ideal, sepa_debit).
          us_bank_account:
            type: object
            properties:
              bank_name:
                type: string
                description: The name of the financial institution holding the account.
                example: Chase
              last4:
                type: string
                description: The last four digits of the bank account number.
                example: '6789'
              account_type:
                type: string
                description: The type of bank account (e.g., checking, savings).
                example: checking
            required:
            - bank_name
            - last4
            - account_type
            description: The bank account-specific details for this payment method,
              including bank name and last four digits.
        required:
        - typename
        - id
        - created_at
        - payment_method_type
        - us_bank_account
        description: A saved US bank account payment method, including bank name,
          last four digits, and account type.
        title: UsBankAccountPaymentMethod
      - type: object
        properties:
          typename:
            type: string
            const: CashappPaymentMethod
            description: The typename of this object
          id:
            type: string
            description: Represents a unique identifier that is Base64 obfuscated.
              It is often used to refetch an object or as key for a cache. The ID
              type appears in a JSON response as a String; however, it is not intended
              to be human-readable. When expected as an input type, any string (such
              as `"VXNlci0xMA=="`) or integer (such as `4`) input value will be accepted
              as an ID.
          created_at:
            type: string
            format: date-time
            description: The time of the event in ISO 8601 UTC format with millisecond
              precision
            example: '2023-12-01T05:00:00.401Z'
          payment_method_type:
            "$ref": "#/components/schemas/PaymentMethodTypes"
            description: The type of payment instrument stored on file (e.g., card,
              us_bank_account, cashapp, ideal, sepa_debit).
          cashapp:
            type: object
            properties:
              buyer_id:
                type:
                - string
                - 'null'
                description: The unique and immutable identifier assigned by Cash
                  App to the buyer. Null if not available.
                example: BUYER_abc123
              cashtag:
                type:
                - string
                - 'null'
                description: The public cashtag handle of the buyer on Cash App. Null
                  if not available.
                example: "$jacksmith"
            required:
            - buyer_id
            - cashtag
            description: The Cash App-specific details for this payment method, including
              cashtag and buyer ID.
        required:
        - typename
        - id
        - created_at
        - payment_method_type
        - cashapp
        description: A saved Cash App payment method, including the buyer's cashtag
          and unique identifier.
        title: CashappPaymentMethod
      - type: object
        properties:
          typename:
            type: string
            const: IdealPaymentMethod
            description: The typename of this object
          id:
            type: string
            description: Represents a unique identifier that is Base64 obfuscated.
              It is often used to refetch an object or as key for a cache. The ID
              type appears in a JSON response as a String; however, it is not intended
              to be human-readable. When expected as an input type, any string (such
              as `"VXNlci0xMA=="`) or integer (such as `4`) input value will be accepted
              as an ID.
          created_at:
            type: string
            format: date-time
            description: The time of the event in ISO 8601 UTC format with millisecond
              precision
            example: '2023-12-01T05:00:00.401Z'
          payment_method_type:
            "$ref": "#/components/schemas/PaymentMethodTypes"
            description: The type of payment instrument stored on file (e.g., card,
              us_bank_account, cashapp, ideal, sepa_debit).
          ideal:
            type: object
            properties:
              bank:
                type:
                - string
                - 'null'
                description: The name of the customer's bank used for the iDEAL transaction.
                  Null if not available.
                example: ing
              bic:
                type:
                - string
                - 'null'
                description: The Bank Identifier Code (BIC/SWIFT) of the customer's
                  bank. Null if not available.
                example: INGBNL2A
            required:
            - bank
            - bic
            description: The iDEAL-specific details for this payment method, including
              bank name and BIC.
        required:
        - typename
        - id
        - created_at
        - payment_method_type
        - ideal
        description: A saved iDEAL payment method, including the customer's bank name
          and BIC code.
        title: IdealPaymentMethod
      - type: object
        properties:
          typename:
            type: string
            const: SepaDebitPaymentMethod
            description: The typename of this object
          id:
            type: string
            description: Represents a unique identifier that is Base64 obfuscated.
              It is often used to refetch an object or as key for a cache. The ID
              type appears in a JSON response as a String; however, it is not intended
              to be human-readable. When expected as an input type, any string (such
              as `"VXNlci0xMA=="`) or integer (such as `4`) input value will be accepted
              as an ID.
          created_at:
            type: string
            format: date-time
            description: The time of the event in ISO 8601 UTC format with millisecond
              precision
            example: '2023-12-01T05:00:00.401Z'
          payment_method_type:
            "$ref": "#/components/schemas/PaymentMethodTypes"
            description: The type of payment instrument stored on file (e.g., card,
              us_bank_account, cashapp, ideal, sepa_debit).
          sepa_debit:
            type: object
            properties:
              bank_code:
                type:
                - string
                - 'null'
                description: The bank code of the financial institution associated
                  with this SEPA account. Null if not available.
                example: '37040044'
              branch_code:
                type:
                - string
                - 'null'
                description: The branch code of the financial institution associated
                  with this SEPA account. Null if not available.
                example: '0532'
              country:
                type:
                - string
                - 'null'
                description: The two-letter ISO country code where the bank account
                  is located. Null if not available.
                example: DE
              last4:
                type:
                - string
                - 'null'
                description: The last four digits of the IBAN associated with this
                  SEPA account. Null if not available.
                example: '3000'
            required:
            - bank_code
            - branch_code
            - country
            - last4
            description: The SEPA Direct Debit-specific details for this payment method,
              including bank code and last four IBAN digits.
        required:
        - typename
        - id
        - created_at
        - payment_method_type
        - sepa_debit
        description: A saved SEPA Direct Debit payment method, including the bank
          code, country, and last four IBAN digits.
        title: SepaDebitPaymentMethod
      discriminator:
        propertyName: typename
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
```
