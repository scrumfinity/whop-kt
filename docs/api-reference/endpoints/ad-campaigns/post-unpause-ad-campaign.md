# POST /ad_campaigns/{id}/unpause — Unpause ad campaign

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `POST`
- **Path:** `/ad_campaigns/{id}/unpause`
- **Operation ID:** `unpauseAdCampaign`
- **Tags:** `Ad campaigns`
- **Required bearer scopes:** `ad_campaign:update`, `access_pass:basic:read`, `company:balance:read`

## Description

Resumes a paused ad campaign.

Required permissions:
 - `ad_campaign:update`
 - `access_pass:basic:read`
 - `company:balance:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `id` | path | yes | `string` | The unique identifier of the ad campaign to unpause. | adcamp_xxxxxxxxxxx |

## Request body

_No request body._

## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `AdCampaign` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/AdCampaign"
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

const response = await client.adCampaigns.unpause('adcamp_xxxxxxxxxxx');

console.log(response.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
response = client.ad_campaigns.unpause(
    "adcamp_xxxxxxxxxxx",
)
print(response.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

response = whop.ad_campaigns.unpause("adcamp_xxxxxxxxxxx")

puts(response)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  AdCampaign:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the ad campaign.
        example: adcamp_xxxxxxxxxxx
      title:
        type: string
        description: The title of the ad campaign
      status:
        "$ref": "#/components/schemas/AdCampaignStatuses"
        description: Current status of the campaign (active, paused, or inactive)
      target_country_codes:
        type: array
        items:
          type: string
          description: Represents textual data as UTF-8 character sequences. This
            type is most often used by GraphQL to represent free-form human-readable
            text.
        description: Array of ISO3166 country codes for territory targeting
      daily_budget:
        type:
        - number
        - 'null'
        description: Effective daily budget in dollars — sum of ad group budgets when
          set, otherwise campaign-level daily budget
        example: 6.9
      platform:
        oneOf:
        - "$ref": "#/components/schemas/AdCampaignPlatforms"
        - type: 'null'
        description: The external platform this campaign is running on (e.g., meta,
          tiktok).
      available_budget:
        type: number
        description: Available budget in dollars, capped at daily budget minus today's
          spend for daily campaigns
        example: 6.9
      created_at:
        type: string
        format: date-time
        description: The datetime the ad campaign was created.
        example: '2023-12-01T05:00:00.401Z'
      updated_at:
        type: string
        format: date-time
        description: The datetime the ad campaign was last updated.
        example: '2023-12-01T05:00:00.401Z'
      paused_until:
        type:
        - string
        - 'null'
        format: date-time
        description: If temporarily paused, the timestamp when the campaign will auto-resume
        example: '2023-12-01T05:00:00.401Z'
      clicks_count:
        type: integer
        description: Number of clicks
        example: 42
      impressions_count:
        type: integer
        description: Number of impressions (views)
        example: 42
      purchases_count:
        type: integer
        description: Number of purchases
        example: 42
      remaining_balance:
        type: number
        description: Remaining balance in dollars
        example: 6.9
      revenue:
        type: number
        description: Total revenue generated from users who converted through this
          campaign
        example: 6.9
      return_on_ad_spend:
        type: number
        description: Return on Ad Spend (ROAS) percentage - revenue generated divided
          by ad spend
        example: 6.9
      todays_spend:
        type: number
        description: Amount spent today in dollars
        example: 6.9
      total_credits:
        type: number
        description: Total credits added to the campaign in dollars
        example: 6.9
      total_spend:
        type: number
        description: Total amount spent on conversions in dollars
        example: 6.9
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
        description: The access pass being promoted. Null for campaigns that don't
          target a specific product.
      created_by_user:
        type: object
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
        description: The user who created the campaign
      payment_method:
        type:
        - object
        - 'null'
        oneOf:
        - type:
          - object
          - 'null'
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
                as `"VXNlci0xMA=="`) or integer (such as `4`) input value will be
                accepted as an ID.
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
        - type:
          - object
          - 'null'
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
                as `"VXNlci0xMA=="`) or integer (such as `4`) input value will be
                accepted as an ID.
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
        - type:
          - object
          - 'null'
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
                as `"VXNlci0xMA=="`) or integer (such as `4`) input value will be
                accepted as an ID.
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
        - type:
          - object
          - 'null'
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
                as `"VXNlci0xMA=="`) or integer (such as `4`) input value will be
                accepted as an ID.
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
                  description: The public cashtag handle of the buyer on Cash App.
                    Null if not available.
                  example: "$jacksmith"
              required:
              - buyer_id
              - cashtag
              description: The Cash App-specific details for this payment method,
                including cashtag and buyer ID.
          required:
          - typename
          - id
          - created_at
          - payment_method_type
          - cashapp
          description: A saved Cash App payment method, including the buyer's cashtag
            and unique identifier.
          title: CashappPaymentMethod
        - type:
          - object
          - 'null'
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
                as `"VXNlci0xMA=="`) or integer (such as `4`) input value will be
                accepted as an ID.
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
                  description: The name of the customer's bank used for the iDEAL
                    transaction. Null if not available.
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
          description: A saved iDEAL payment method, including the customer's bank
            name and BIC code.
          title: IdealPaymentMethod
        - type:
          - object
          - 'null'
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
                as `"VXNlci0xMA=="`) or integer (such as `4`) input value will be
                accepted as an ID.
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
              description: The SEPA Direct Debit-specific details for this payment
                method, including bank code and last four IBAN digits.
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
        description: The payment method used for daily billing (null if using platform
          balance)
      billing_ledger_account:
        type:
        - object
        - 'null'
        properties:
          id:
            type: string
            description: The unique identifier for the ledger account.
            example: ldgr_xxxxxxxxxxxxx
        required:
        - id
        description: The ledger account being charged for platform balance billing
          (null if using card)
      config:
        type:
        - object
        - 'null'
        properties:
          objective:
            oneOf:
            - "$ref": "#/components/schemas/CampaignConfigObjective"
            - type: 'null'
            description: The campaign objective that determines how Meta optimizes
              delivery.
          bid_strategy:
            oneOf:
            - "$ref": "#/components/schemas/CampaignConfigBidStrategy"
            - type: 'null'
            description: The bidding strategy used to optimize spend for this campaign.
          bid_amount:
            type:
            - integer
            - 'null'
            description: Bid cap amount in cents. Only used when bid_strategy is bid_cap.
            example: 42
          status:
            oneOf:
            - "$ref": "#/components/schemas/CampaignConfigStatus"
            - type: 'null'
            description: The campaign status as set by the advertiser (active or paused).
          special_categories:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            description: Special ad categories required by the platform (e.g., housing,
              employment, credit).
          budget_optimization:
            type:
            - boolean
            - 'null'
            description: Whether campaign budget optimization (CBO) is enabled, allowing
              the platform to distribute budget across ad groups.
          start_time:
            type:
            - string
            - 'null'
            description: The scheduled start time of the campaign (ISO8601).
          end_time:
            type:
            - string
            - 'null'
            description: The scheduled end time of the campaign (ISO8601).
        required:
        - objective
        - bid_strategy
        - bid_amount
        - status
        - special_categories
        - budget_optimization
        - start_time
        - end_time
        description: Meta campaign configuration (objective, budget, bidding, etc.).
          Null for non-Meta campaigns — use `tiktokConfig` for TikTok.
    required:
    - id
    - title
    - status
    - target_country_codes
    - daily_budget
    - platform
    - available_budget
    - created_at
    - updated_at
    - paused_until
    - clicks_count
    - impressions_count
    - purchases_count
    - remaining_balance
    - revenue
    - return_on_ad_spend
    - todays_spend
    - total_credits
    - total_spend
    - product
    - created_by_user
    - payment_method
    - billing_ledger_account
    - config
    description: An advertising campaign running on an external platform or within
      Whop.
  AdCampaignPlatforms:
    type: string
    enum:
    - meta
    - tiktok
    description: The platforms where an ad campaign can run.
  AdCampaignStatuses:
    type: string
    enum:
    - active
    - paused
    - inactive
    - stale
    - pending_refund
    - payment_failed
    - draft
    - in_review
    - flagged
    - importing
    - imported
    description: The status of an ad campaign.
  CampaignConfigBidStrategy:
    type: string
    enum:
    - lowest_cost
    - bid_cap
    - cost_cap
  CampaignConfigObjective:
    type: string
    enum:
    - awareness
    - traffic
    - engagement
    - leads
    - sales
  CampaignConfigStatus:
    type: string
    enum:
    - active
    - paused
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
  "/ad_campaigns/{id}/unpause":
    post:
      tags:
      - Ad campaigns
      operationId: unpauseAdCampaign
      summary: Unpause ad campaign
      description: |-
        Resumes a paused ad campaign.

        Required permissions:
         - `ad_campaign:update`
         - `access_pass:basic:read`
         - `company:balance:read`
      parameters:
      - name: id
        in: path
        required: true
        description: The unique identifier of the ad campaign to unpause.
        schema:
          type: string
          example: adcamp_xxxxxxxxxxx
      x-group-title: Ads
      security:
      - bearerAuth:
        - ad_campaign:update
        - access_pass:basic:read
        - company:balance:read
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/AdCampaign"
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

          const response = await client.adCampaigns.unpause('adcamp_xxxxxxxxxxx');

          console.log(response.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          response = client.ad_campaigns.unpause(
              "adcamp_xxxxxxxxxxx",
          )
          print(response.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          response = whop.ad_campaigns.unpause("adcamp_xxxxxxxxxxx")

          puts(response)
components:
  schemas:
    AdCampaign:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the ad campaign.
          example: adcamp_xxxxxxxxxxx
        title:
          type: string
          description: The title of the ad campaign
        status:
          "$ref": "#/components/schemas/AdCampaignStatuses"
          description: Current status of the campaign (active, paused, or inactive)
        target_country_codes:
          type: array
          items:
            type: string
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          description: Array of ISO3166 country codes for territory targeting
        daily_budget:
          type:
          - number
          - 'null'
          description: Effective daily budget in dollars — sum of ad group budgets
            when set, otherwise campaign-level daily budget
          example: 6.9
        platform:
          oneOf:
          - "$ref": "#/components/schemas/AdCampaignPlatforms"
          - type: 'null'
          description: The external platform this campaign is running on (e.g., meta,
            tiktok).
        available_budget:
          type: number
          description: Available budget in dollars, capped at daily budget minus today's
            spend for daily campaigns
          example: 6.9
        created_at:
          type: string
          format: date-time
          description: The datetime the ad campaign was created.
          example: '2023-12-01T05:00:00.401Z'
        updated_at:
          type: string
          format: date-time
          description: The datetime the ad campaign was last updated.
          example: '2023-12-01T05:00:00.401Z'
        paused_until:
          type:
          - string
          - 'null'
          format: date-time
          description: If temporarily paused, the timestamp when the campaign will
            auto-resume
          example: '2023-12-01T05:00:00.401Z'
        clicks_count:
          type: integer
          description: Number of clicks
          example: 42
        impressions_count:
          type: integer
          description: Number of impressions (views)
          example: 42
        purchases_count:
          type: integer
          description: Number of purchases
          example: 42
        remaining_balance:
          type: number
          description: Remaining balance in dollars
          example: 6.9
        revenue:
          type: number
          description: Total revenue generated from users who converted through this
            campaign
          example: 6.9
        return_on_ad_spend:
          type: number
          description: Return on Ad Spend (ROAS) percentage - revenue generated divided
            by ad spend
          example: 6.9
        todays_spend:
          type: number
          description: Amount spent today in dollars
          example: 6.9
        total_credits:
          type: number
          description: Total credits added to the campaign in dollars
          example: 6.9
        total_spend:
          type: number
          description: Total amount spent on conversions in dollars
          example: 6.9
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
          description: The access pass being promoted. Null for campaigns that don't
            target a specific product.
        created_by_user:
          type: object
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
          description: The user who created the campaign
        payment_method:
          type:
          - object
          - 'null'
          oneOf:
          - type:
            - object
            - 'null'
            properties:
              typename:
                type: string
                const: BasePaymentMethod
                description: The typename of this object
              id:
                type: string
                description: Represents a unique identifier that is Base64 obfuscated.
                  It is often used to refetch an object or as key for a cache. The
                  ID type appears in a JSON response as a String; however, it is not
                  intended to be human-readable. When expected as an input type, any
                  string (such as `"VXNlci0xMA=="`) or integer (such as `4`) input
                  value will be accepted as an ID.
                example: payt_xxxxxxxxxxxxx
              created_at:
                type: string
                format: date-time
                description: The time of the event in ISO 8601 UTC format with millisecond
                  precision
                example: '2023-12-01T05:00:00.401Z'
              payment_method_type:
                "$ref": "#/components/schemas/PaymentMethodTypes"
                description: The type of payment instrument stored on file (e.g.,
                  card, us_bank_account, cashapp, ideal, sepa_debit).
            required:
            - typename
            - id
            - created_at
            - payment_method_type
            description: A saved payment method with no type-specific details available.
            title: BasePaymentMethod
          - type:
            - object
            - 'null'
            properties:
              typename:
                type: string
                const: CardPaymentMethod
                description: The typename of this object
              id:
                type: string
                description: Represents a unique identifier that is Base64 obfuscated.
                  It is often used to refetch an object or as key for a cache. The
                  ID type appears in a JSON response as a String; however, it is not
                  intended to be human-readable. When expected as an input type, any
                  string (such as `"VXNlci0xMA=="`) or integer (such as `4`) input
                  value will be accepted as an ID.
              created_at:
                type: string
                format: date-time
                description: The time of the event in ISO 8601 UTC format with millisecond
                  precision
                example: '2023-12-01T05:00:00.401Z'
              payment_method_type:
                "$ref": "#/components/schemas/PaymentMethodTypes"
                description: The type of payment instrument stored on file (e.g.,
                  card, us_bank_account, cashapp, ideal, sepa_debit).
              card:
                type: object
                properties:
                  brand:
                    oneOf:
                    - "$ref": "#/components/schemas/CardBrands"
                    - type: 'null'
                    description: The card network (e.g., visa, mastercard, amex).
                      Null if the brand could not be determined.
                  last4:
                    type:
                    - string
                    - 'null'
                    description: The last four digits of the card number. Null if
                      not available.
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
                    description: The two-digit expiration year of the card (e.g.,
                      27 for 2027). Null if not available.
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
          - type:
            - object
            - 'null'
            properties:
              typename:
                type: string
                const: UsBankAccountPaymentMethod
                description: The typename of this object
              id:
                type: string
                description: Represents a unique identifier that is Base64 obfuscated.
                  It is often used to refetch an object or as key for a cache. The
                  ID type appears in a JSON response as a String; however, it is not
                  intended to be human-readable. When expected as an input type, any
                  string (such as `"VXNlci0xMA=="`) or integer (such as `4`) input
                  value will be accepted as an ID.
              created_at:
                type: string
                format: date-time
                description: The time of the event in ISO 8601 UTC format with millisecond
                  precision
                example: '2023-12-01T05:00:00.401Z'
              payment_method_type:
                "$ref": "#/components/schemas/PaymentMethodTypes"
                description: The type of payment instrument stored on file (e.g.,
                  card, us_bank_account, cashapp, ideal, sepa_debit).
              us_bank_account:
                type: object
                properties:
                  bank_name:
                    type: string
                    description: The name of the financial institution holding the
                      account.
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
          - type:
            - object
            - 'null'
            properties:
              typename:
                type: string
                const: CashappPaymentMethod
                description: The typename of this object
              id:
                type: string
                description: Represents a unique identifier that is Base64 obfuscated.
                  It is often used to refetch an object or as key for a cache. The
                  ID type appears in a JSON response as a String; however, it is not
                  intended to be human-readable. When expected as an input type, any
                  string (such as `"VXNlci0xMA=="`) or integer (such as `4`) input
                  value will be accepted as an ID.
              created_at:
                type: string
                format: date-time
                description: The time of the event in ISO 8601 UTC format with millisecond
                  precision
                example: '2023-12-01T05:00:00.401Z'
              payment_method_type:
                "$ref": "#/components/schemas/PaymentMethodTypes"
                description: The type of payment instrument stored on file (e.g.,
                  card, us_bank_account, cashapp, ideal, sepa_debit).
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
                    description: The public cashtag handle of the buyer on Cash App.
                      Null if not available.
                    example: "$jacksmith"
                required:
                - buyer_id
                - cashtag
                description: The Cash App-specific details for this payment method,
                  including cashtag and buyer ID.
            required:
            - typename
            - id
            - created_at
            - payment_method_type
            - cashapp
            description: A saved Cash App payment method, including the buyer's cashtag
              and unique identifier.
            title: CashappPaymentMethod
          - type:
            - object
            - 'null'
            properties:
              typename:
                type: string
                const: IdealPaymentMethod
                description: The typename of this object
              id:
                type: string
                description: Represents a unique identifier that is Base64 obfuscated.
                  It is often used to refetch an object or as key for a cache. The
                  ID type appears in a JSON response as a String; however, it is not
                  intended to be human-readable. When expected as an input type, any
                  string (such as `"VXNlci0xMA=="`) or integer (such as `4`) input
                  value will be accepted as an ID.
              created_at:
                type: string
                format: date-time
                description: The time of the event in ISO 8601 UTC format with millisecond
                  precision
                example: '2023-12-01T05:00:00.401Z'
              payment_method_type:
                "$ref": "#/components/schemas/PaymentMethodTypes"
                description: The type of payment instrument stored on file (e.g.,
                  card, us_bank_account, cashapp, ideal, sepa_debit).
              ideal:
                type: object
                properties:
                  bank:
                    type:
                    - string
                    - 'null'
                    description: The name of the customer's bank used for the iDEAL
                      transaction. Null if not available.
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
            description: A saved iDEAL payment method, including the customer's bank
              name and BIC code.
            title: IdealPaymentMethod
          - type:
            - object
            - 'null'
            properties:
              typename:
                type: string
                const: SepaDebitPaymentMethod
                description: The typename of this object
              id:
                type: string
                description: Represents a unique identifier that is Base64 obfuscated.
                  It is often used to refetch an object or as key for a cache. The
                  ID type appears in a JSON response as a String; however, it is not
                  intended to be human-readable. When expected as an input type, any
                  string (such as `"VXNlci0xMA=="`) or integer (such as `4`) input
                  value will be accepted as an ID.
              created_at:
                type: string
                format: date-time
                description: The time of the event in ISO 8601 UTC format with millisecond
                  precision
                example: '2023-12-01T05:00:00.401Z'
              payment_method_type:
                "$ref": "#/components/schemas/PaymentMethodTypes"
                description: The type of payment instrument stored on file (e.g.,
                  card, us_bank_account, cashapp, ideal, sepa_debit).
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
                    description: The last four digits of the IBAN associated with
                      this SEPA account. Null if not available.
                    example: '3000'
                required:
                - bank_code
                - branch_code
                - country
                - last4
                description: The SEPA Direct Debit-specific details for this payment
                  method, including bank code and last four IBAN digits.
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
          description: The payment method used for daily billing (null if using platform
            balance)
        billing_ledger_account:
          type:
          - object
          - 'null'
          properties:
            id:
              type: string
              description: The unique identifier for the ledger account.
              example: ldgr_xxxxxxxxxxxxx
          required:
          - id
          description: The ledger account being charged for platform balance billing
            (null if using card)
        config:
          type:
          - object
          - 'null'
          properties:
            objective:
              oneOf:
              - "$ref": "#/components/schemas/CampaignConfigObjective"
              - type: 'null'
              description: The campaign objective that determines how Meta optimizes
                delivery.
            bid_strategy:
              oneOf:
              - "$ref": "#/components/schemas/CampaignConfigBidStrategy"
              - type: 'null'
              description: The bidding strategy used to optimize spend for this campaign.
            bid_amount:
              type:
              - integer
              - 'null'
              description: Bid cap amount in cents. Only used when bid_strategy is
                bid_cap.
              example: 42
            status:
              oneOf:
              - "$ref": "#/components/schemas/CampaignConfigStatus"
              - type: 'null'
              description: The campaign status as set by the advertiser (active or
                paused).
            special_categories:
              type:
              - array
              - 'null'
              items:
                type: string
                description: Represents textual data as UTF-8 character sequences.
                  This type is most often used by GraphQL to represent free-form human-readable
                  text.
              description: Special ad categories required by the platform (e.g., housing,
                employment, credit).
            budget_optimization:
              type:
              - boolean
              - 'null'
              description: Whether campaign budget optimization (CBO) is enabled,
                allowing the platform to distribute budget across ad groups.
            start_time:
              type:
              - string
              - 'null'
              description: The scheduled start time of the campaign (ISO8601).
            end_time:
              type:
              - string
              - 'null'
              description: The scheduled end time of the campaign (ISO8601).
          required:
          - objective
          - bid_strategy
          - bid_amount
          - status
          - special_categories
          - budget_optimization
          - start_time
          - end_time
          description: Meta campaign configuration (objective, budget, bidding, etc.).
            Null for non-Meta campaigns — use `tiktokConfig` for TikTok.
      required:
      - id
      - title
      - status
      - target_country_codes
      - daily_budget
      - platform
      - available_budget
      - created_at
      - updated_at
      - paused_until
      - clicks_count
      - impressions_count
      - purchases_count
      - remaining_balance
      - revenue
      - return_on_ad_spend
      - todays_spend
      - total_credits
      - total_spend
      - product
      - created_by_user
      - payment_method
      - billing_ledger_account
      - config
      description: An advertising campaign running on an external platform or within
        Whop.
    AdCampaignPlatforms:
      type: string
      enum:
      - meta
      - tiktok
      description: The platforms where an ad campaign can run.
    AdCampaignStatuses:
      type: string
      enum:
      - active
      - paused
      - inactive
      - stale
      - pending_refund
      - payment_failed
      - draft
      - in_review
      - flagged
      - importing
      - imported
      description: The status of an ad campaign.
    CampaignConfigBidStrategy:
      type: string
      enum:
      - lowest_cost
      - bid_cap
      - cost_cap
    CampaignConfigObjective:
      type: string
      enum:
      - awareness
      - traffic
      - engagement
      - leads
      - sales
    CampaignConfigStatus:
      type: string
      enum:
      - active
      - paused
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
```
