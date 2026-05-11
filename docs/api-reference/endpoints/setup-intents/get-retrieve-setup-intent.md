# GET /setup_intents/{id} — Retrieve setup intent

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/setup_intents/{id}`
- **Operation ID:** `retrieveSetupIntent`
- **Tags:** `Setup intents`
- **Required bearer scopes:** `payment:setup_intent:read`, `member:basic:read`, `member:email:read`

## Description

Retrieves the details of an existing setup intent.

Required permissions:
 - `payment:setup_intent:read`
 - `member:basic:read`
 - `member:email:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `id` | path | yes | `string` | The unique identifier of the setup intent. | sint_xxxxxxxxxxxxx |

## Request body

_No request body._

## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `SetupIntent` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/SetupIntent"
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

const setupIntent = await client.setupIntents.retrieve('sint_xxxxxxxxxxxxx');

console.log(setupIntent.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
setup_intent = client.setup_intents.retrieve(
    "sint_xxxxxxxxxxxxx",
)
print(setup_intent.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

setup_intent = whop.setup_intents.retrieve("sint_xxxxxxxxxxxxx")

puts(setup_intent)
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
  SetupIntent:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the setup intent.
        example: sint_xxxxxxxxxxxxx
      status:
        "$ref": "#/components/schemas/SetupIntentStatuses"
        description: The current status of the setup intent.
      created_at:
        type: string
        format: date-time
        description: The datetime the setup intent was created.
        example: '2023-12-01T05:00:00.401Z'
      error_message:
        type:
        - string
        - 'null'
        description: A human-readable error message explaining why the setup intent
          failed. Null if no error occurred.
        example: Your card was declined.
      company:
        type:
        - object
        - 'null'
        properties:
          id:
            type: string
            description: The unique identifier for the company.
            example: biz_xxxxxxxxxxxxxx
        required:
        - id
        description: The company that initiated this setup intent. Null if the company
          has been deleted.
      checkout_configuration:
        type:
        - object
        - 'null'
        properties:
          id:
            type: string
            description: The unique identifier for the checkout session.
            example: ch_xxxxxxxxxxxxxxx
        required:
        - id
        description: The checkout session configuration associated with this setup
          intent. Null if no checkout session was used.
      member:
        type:
        - object
        - 'null'
        properties:
          id:
            type: string
            description: The unique identifier for the company member.
          user:
            type:
            - object
            - 'null'
            properties:
              id:
                type: string
                description: The unique identifier for the company member user.
              email:
                type:
                - string
                - 'null'
                description: The digital mailing address of the user.
              name:
                type:
                - string
                - 'null'
                description: The user's full name.
              username:
                type: string
                description: The whop username.
            required:
            - id
            - email
            - name
            - username
            description: The user for this member, if any.
        required:
        - id
        - user
        description: The company member associated with this setup intent. Null if
          the user is not a member.
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
          mailing_address:
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
            description: The mailing address associated with the payment method's
              user
        required:
        - id
        - created_at
        - payment_method_type
        - card
        - mailing_address
        description: The saved payment method created by this setup intent. Null if
          the setup has not completed successfully.
      metadata:
        type:
        - object
        - 'null'
        additionalProperties: true
        description: Custom key-value pairs attached to this setup intent. Null if
          no metadata was provided.
    required:
    - id
    - status
    - created_at
    - error_message
    - company
    - checkout_configuration
    - member
    - payment_method
    - metadata
    description: A setup intent allows a user to save a payment method for future
      use without making an immediate purchase.
  SetupIntentStatuses:
    type: string
    enum:
    - processing
    - succeeded
    - canceled
    - requires_action
    description: The status of the setup intent.
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
  "/setup_intents/{id}":
    get:
      tags:
      - Setup intents
      operationId: retrieveSetupIntent
      summary: Retrieve setup intent
      description: |-
        Retrieves the details of an existing setup intent.

        Required permissions:
         - `payment:setup_intent:read`
         - `member:basic:read`
         - `member:email:read`
      parameters:
      - name: id
        in: path
        required: true
        description: The unique identifier of the setup intent.
        schema:
          type: string
          example: sint_xxxxxxxxxxxxx
      x-group-title: Payins
      security:
      - bearerAuth:
        - payment:setup_intent:read
        - member:basic:read
        - member:email:read
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/SetupIntent"
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

          const setupIntent = await client.setupIntents.retrieve('sint_xxxxxxxxxxxxx');

          console.log(setupIntent.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          setup_intent = client.setup_intents.retrieve(
              "sint_xxxxxxxxxxxxx",
          )
          print(setup_intent.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          setup_intent = whop.setup_intents.retrieve("sint_xxxxxxxxxxxxx")

          puts(setup_intent)
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
    SetupIntent:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the setup intent.
          example: sint_xxxxxxxxxxxxx
        status:
          "$ref": "#/components/schemas/SetupIntentStatuses"
          description: The current status of the setup intent.
        created_at:
          type: string
          format: date-time
          description: The datetime the setup intent was created.
          example: '2023-12-01T05:00:00.401Z'
        error_message:
          type:
          - string
          - 'null'
          description: A human-readable error message explaining why the setup intent
            failed. Null if no error occurred.
          example: Your card was declined.
        company:
          type:
          - object
          - 'null'
          properties:
            id:
              type: string
              description: The unique identifier for the company.
              example: biz_xxxxxxxxxxxxxx
          required:
          - id
          description: The company that initiated this setup intent. Null if the company
            has been deleted.
        checkout_configuration:
          type:
          - object
          - 'null'
          properties:
            id:
              type: string
              description: The unique identifier for the checkout session.
              example: ch_xxxxxxxxxxxxxxx
          required:
          - id
          description: The checkout session configuration associated with this setup
            intent. Null if no checkout session was used.
        member:
          type:
          - object
          - 'null'
          properties:
            id:
              type: string
              description: The unique identifier for the company member.
            user:
              type:
              - object
              - 'null'
              properties:
                id:
                  type: string
                  description: The unique identifier for the company member user.
                email:
                  type:
                  - string
                  - 'null'
                  description: The digital mailing address of the user.
                name:
                  type:
                  - string
                  - 'null'
                  description: The user's full name.
                username:
                  type: string
                  description: The whop username.
              required:
              - id
              - email
              - name
              - username
              description: The user for this member, if any.
          required:
          - id
          - user
          description: The company member associated with this setup intent. Null
            if the user is not a member.
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
            mailing_address:
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
              description: The mailing address associated with the payment method's
                user
          required:
          - id
          - created_at
          - payment_method_type
          - card
          - mailing_address
          description: The saved payment method created by this setup intent. Null
            if the setup has not completed successfully.
        metadata:
          type:
          - object
          - 'null'
          additionalProperties: true
          description: Custom key-value pairs attached to this setup intent. Null
            if no metadata was provided.
      required:
      - id
      - status
      - created_at
      - error_message
      - company
      - checkout_configuration
      - member
      - payment_method
      - metadata
      description: A setup intent allows a user to save a payment method for future
        use without making an immediate purchase.
    SetupIntentStatuses:
      type: string
      enum:
      - processing
      - succeeded
      - canceled
      - requires_action
      description: The status of the setup intent.
```
