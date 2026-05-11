# POST /disputes/{id}/update_evidence — Update evidence

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `POST`
- **Path:** `/disputes/{id}/update_evidence`
- **Operation ID:** `updateEvidenceDispute`
- **Tags:** `Disputes`
- **Required bearer scopes:** `payment:dispute`, `plan:basic:read`, `access_pass:basic:read`, `company:basic:read`, `payment:basic:read`, `member:email:read`, `member:basic:read`, `member:phone:read`

## Description

Update a dispute with evidence data to attempt to win the dispute.

Required permissions:
 - `payment:dispute`
 - `plan:basic:read`
 - `access_pass:basic:read`
 - `company:basic:read`
 - `payment:basic:read`
 - `member:email:read`
 - `member:basic:read`
 - `member:phone:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `id` | path | yes | `string` | The unique identifier of the dispute to update. | dspt_xxxxxxxxxxxxx |

## Request body

Required: **no**
Content types: `application/json`

### `application/json` request schema

```yaml
type: object
properties:
  access_activity_log:
    type:
    - string
    - 'null'
    description: An IP access activity log showing the customer used the service.
  billing_address:
    type:
    - string
    - 'null'
    description: The billing address associated with the customer's payment method.
  cancellation_policy_disclosure:
    type:
    - string
    - 'null'
    description: The company's cancellation policy text to submit as evidence.
  customer_email_address:
    type:
    - string
    - 'null'
    description: The email address of the customer associated with the disputed payment.
  customer_name:
    type:
    - string
    - 'null'
    description: The full name of the customer associated with the disputed payment.
  notes:
    type:
    - string
    - 'null'
    description: Additional notes or context to submit as part of the dispute evidence.
  product_description:
    type:
    - string
    - 'null'
    description: A description of the product or service that was provided to the
      customer.
  refund_policy_disclosure:
    type:
    - string
    - 'null'
    description: The company's refund policy text to submit as evidence.
  refund_refusal_explanation:
    type:
    - string
    - 'null'
    description: An explanation of why the refund request was refused.
  service_date:
    type:
    - string
    - 'null'
    description: The date when the product or service was delivered to the customer.
  cancellation_policy_attachment:
    type:
    - object
    - 'null'
    properties:
      id:
        type: string
        description: The ID of an existing file object.
    required:
    - id
    description: A file upload containing the company's cancellation policy document.
    title: FileInputWithId
  customer_communication_attachment:
    type:
    - object
    - 'null'
    properties:
      id:
        type: string
        description: The ID of an existing file object.
    required:
    - id
    description: A file upload containing evidence of customer communication. Must
      be a JPEG, PNG, GIF, or PDF.
    title: FileInputWithId
  refund_policy_attachment:
    type:
    - object
    - 'null'
    properties:
      id:
        type: string
        description: The ID of an existing file object.
    required:
    - id
    description: A file upload containing the company's refund policy document.
    title: FileInputWithId
  uncategorized_attachment:
    type:
    - object
    - 'null'
    properties:
      id:
        type: string
        description: The ID of an existing file object.
    required:
    - id
    description: A file upload for evidence that does not fit into the other categories.
    title: FileInputWithId
required: []
description: Parameters for UpdateDispute
```


## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `Dispute` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/Dispute"
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

const dispute = await client.disputes.updateEvidence('dspt_xxxxxxxxxxxxx');

console.log(dispute.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
dispute = client.disputes.update_evidence(
    id="dspt_xxxxxxxxxxxxx",
)
print(dispute.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

dispute = whop.disputes.update_evidence("dspt_xxxxxxxxxxxxx")

puts(dispute)
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
  Dispute:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the dispute.
        example: dspt_xxxxxxxxxxxxx
      amount:
        type: number
        description: The disputed amount in the specified currency, formatted as a
          decimal.
        example: 6.9
      currency:
        "$ref": "#/components/schemas/Currencies"
        description: The three-letter ISO currency code for the disputed amount.
      status:
        "$ref": "#/components/schemas/DisputeStatuses"
        description: The current status of the dispute lifecycle, such as needs_response,
          under_review, won, or lost.
      editable:
        type:
        - boolean
        - 'null'
        description: Whether the dispute evidence can still be edited and submitted.
          Returns true only when the dispute status requires a response.
      created_at:
        type:
        - string
        - 'null'
        format: date-time
        description: The datetime the dispute was created.
        example: '2023-12-01T05:00:00.401Z'
      visa_rdr:
        type: boolean
        description: Whether the dispute was automatically resolved through Visa Rapid
          Dispute Resolution (RDR).
      needs_response_by:
        type:
        - string
        - 'null'
        format: date-time
        description: The deadline by which dispute evidence must be submitted. Null
          if no response deadline is set.
        example: '2023-12-01T05:00:00.401Z'
      reason:
        type:
        - string
        - 'null'
        description: A human-readable reason for the dispute.
        example: Product Not Received
      plan:
        type:
        - object
        - 'null'
        properties:
          id:
            type: string
            description: The unique identifier for the plan.
            example: plan_xxxxxxxxxxxxx
        required:
        - id
        description: The plan associated with the disputed payment. Null if the dispute
          is not linked to a specific plan.
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
        description: The product associated with the disputed payment. Null if the
          dispute is not linked to a specific product.
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
        required:
        - id
        - title
        description: The company that the dispute was filed against.
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
        - user
        - member
        - membership
        description: The original payment that was disputed.
      access_activity_log:
        type:
        - string
        - 'null'
        description: A log of IP-based access activity for the customer on Whop, submitted
          as evidence in the dispute.
        example: 192.168.1.1 - 2024-01-15 12:00:00 UTC
      billing_address:
        type:
        - string
        - 'null'
        description: The customer's billing address from their payment details, submitted
          as evidence in the dispute.
        example: 123 Main St, New York, NY 10001
      cancellation_policy_disclosure:
        type:
        - string
        - 'null'
        description: A text disclosure describing the company's cancellation policy,
          submitted as dispute evidence.
        example: All sales are final. No refunds after 30 days.
      customer_email_address:
        type:
        - string
        - 'null'
        description: The customer's email address from their payment details, included
          in the evidence packet sent to the payment processor. Editable before submission.
        example: customer@example.com
      customer_name:
        type:
        - string
        - 'null'
        description: The customer's full name from their payment details, included
          in the evidence packet sent to the payment processor. Editable before submission.
        example: Jane Doe
      notes:
        type:
        - string
        - 'null'
        description: Additional freeform notes submitted by the company as part of
          the dispute evidence.
        example: Customer used the product for 3 months before disputing.
      product_description:
        type:
        - string
        - 'null'
        description: A description of the product or service provided, submitted as
          dispute evidence.
        example: Monthly subscription to premium analytics dashboard.
      refund_policy_disclosure:
        type:
        - string
        - 'null'
        description: A text disclosure describing the company's refund policy, submitted
          as dispute evidence.
        example: Refunds available within 14 days of purchase.
      refund_refusal_explanation:
        type:
        - string
        - 'null'
        description: An explanation from the company for why a refund was refused,
          submitted as dispute evidence.
        example: The customer exceeded the refund window by 60 days.
      service_date:
        type:
        - string
        - 'null'
        description: The date when the product or service was delivered to the customer,
          submitted as dispute evidence.
        example: '2024-01-15'
      cancellation_policy_attachment:
        type:
        - object
        - 'null'
        properties:
          id:
            type: string
            description: Represents a unique identifier that is Base64 obfuscated.
              It is often used to refetch an object or as key for a cache. The ID
              type appears in a JSON response as a String; however, it is not intended
              to be human-readable. When expected as an input type, any string (such
              as `"VXNlci0xMA=="`) or integer (such as `4`) input value will be accepted
              as an ID.
          filename:
            type:
            - string
            - 'null'
            description: The original filename of the uploaded attachment, including
              its file extension.
            example: document.pdf
          content_type:
            type:
            - string
            - 'null'
            description: The MIME type of the uploaded file (e.g., image/jpeg, video/mp4,
              audio/mpeg).
            example: image/jpeg
          url:
            type:
            - string
            - 'null'
            description: A pre-optimized URL for rendering this attachment on the
              client. This should be used for displaying attachments in apps.
            example: https://media.whop.com/abc123/optimized.jpg
        required:
        - id
        - filename
        - content_type
        - url
        description: The cancellation policy document uploaded as dispute evidence.
          Null if no cancellation policy has been provided.
      customer_communication_attachment:
        type:
        - object
        - 'null'
        properties:
          id:
            type: string
            description: Represents a unique identifier that is Base64 obfuscated.
              It is often used to refetch an object or as key for a cache. The ID
              type appears in a JSON response as a String; however, it is not intended
              to be human-readable. When expected as an input type, any string (such
              as `"VXNlci0xMA=="`) or integer (such as `4`) input value will be accepted
              as an ID.
          filename:
            type:
            - string
            - 'null'
            description: The original filename of the uploaded attachment, including
              its file extension.
            example: document.pdf
          content_type:
            type:
            - string
            - 'null'
            description: The MIME type of the uploaded file (e.g., image/jpeg, video/mp4,
              audio/mpeg).
            example: image/jpeg
          url:
            type:
            - string
            - 'null'
            description: A pre-optimized URL for rendering this attachment on the
              client. This should be used for displaying attachments in apps.
            example: https://media.whop.com/abc123/optimized.jpg
        required:
        - id
        - filename
        - content_type
        - url
        description: Evidence of customer communication or product usage, uploaded
          as a dispute attachment. Null if not provided.
      refund_policy_attachment:
        type:
        - object
        - 'null'
        properties:
          id:
            type: string
            description: Represents a unique identifier that is Base64 obfuscated.
              It is often used to refetch an object or as key for a cache. The ID
              type appears in a JSON response as a String; however, it is not intended
              to be human-readable. When expected as an input type, any string (such
              as `"VXNlci0xMA=="`) or integer (such as `4`) input value will be accepted
              as an ID.
          filename:
            type:
            - string
            - 'null'
            description: The original filename of the uploaded attachment, including
              its file extension.
            example: document.pdf
          content_type:
            type:
            - string
            - 'null'
            description: The MIME type of the uploaded file (e.g., image/jpeg, video/mp4,
              audio/mpeg).
            example: image/jpeg
          url:
            type:
            - string
            - 'null'
            description: A pre-optimized URL for rendering this attachment on the
              client. This should be used for displaying attachments in apps.
            example: https://media.whop.com/abc123/optimized.jpg
        required:
        - id
        - filename
        - content_type
        - url
        description: The refund policy document uploaded as dispute evidence. Null
          if no refund policy has been provided.
      uncategorized_attachment:
        type:
        - object
        - 'null'
        properties:
          id:
            type: string
            description: Represents a unique identifier that is Base64 obfuscated.
              It is often used to refetch an object or as key for a cache. The ID
              type appears in a JSON response as a String; however, it is not intended
              to be human-readable. When expected as an input type, any string (such
              as `"VXNlci0xMA=="`) or integer (such as `4`) input value will be accepted
              as an ID.
          filename:
            type:
            - string
            - 'null'
            description: The original filename of the uploaded attachment, including
              its file extension.
            example: document.pdf
          content_type:
            type:
            - string
            - 'null'
            description: The MIME type of the uploaded file (e.g., image/jpeg, video/mp4,
              audio/mpeg).
            example: image/jpeg
          url:
            type:
            - string
            - 'null'
            description: A pre-optimized URL for rendering this attachment on the
              client. This should be used for displaying attachments in apps.
            example: https://media.whop.com/abc123/optimized.jpg
        required:
        - id
        - filename
        - content_type
        - url
        description: An additional attachment that does not fit into the standard
          evidence categories. Null if not provided.
    required:
    - id
    - amount
    - currency
    - status
    - editable
    - created_at
    - visa_rdr
    - needs_response_by
    - reason
    - plan
    - product
    - company
    - payment
    - access_activity_log
    - billing_address
    - cancellation_policy_disclosure
    - customer_email_address
    - customer_name
    - notes
    - product_description
    - refund_policy_disclosure
    - refund_refusal_explanation
    - service_date
    - cancellation_policy_attachment
    - customer_communication_attachment
    - refund_policy_attachment
    - uncategorized_attachment
    description: A dispute is a chargeback or payment challenge filed against a company,
      including evidence and response status.
  DisputeStatuses:
    type: string
    enum:
    - warning_needs_response
    - warning_under_review
    - warning_closed
    - needs_response
    - under_review
    - won
    - lost
    - closed
    - other
    description: The possible statuses of a dispute
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
  "/disputes/{id}/update_evidence":
    post:
      tags:
      - Disputes
      operationId: updateEvidenceDispute
      summary: Update evidence
      description: |-
        Update a dispute with evidence data to attempt to win the dispute.

        Required permissions:
         - `payment:dispute`
         - `plan:basic:read`
         - `access_pass:basic:read`
         - `company:basic:read`
         - `payment:basic:read`
         - `member:email:read`
         - `member:basic:read`
         - `member:phone:read`
      parameters:
      - name: id
        in: path
        required: true
        description: The unique identifier of the dispute to update.
        schema:
          type: string
          example: dspt_xxxxxxxxxxxxx
      x-group-title: Payins
      security:
      - bearerAuth:
        - payment:dispute
        - plan:basic:read
        - access_pass:basic:read
        - company:basic:read
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
                "$ref": "#/components/schemas/Dispute"
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
                access_activity_log:
                  type:
                  - string
                  - 'null'
                  description: An IP access activity log showing the customer used
                    the service.
                billing_address:
                  type:
                  - string
                  - 'null'
                  description: The billing address associated with the customer's
                    payment method.
                cancellation_policy_disclosure:
                  type:
                  - string
                  - 'null'
                  description: The company's cancellation policy text to submit as
                    evidence.
                customer_email_address:
                  type:
                  - string
                  - 'null'
                  description: The email address of the customer associated with the
                    disputed payment.
                customer_name:
                  type:
                  - string
                  - 'null'
                  description: The full name of the customer associated with the disputed
                    payment.
                notes:
                  type:
                  - string
                  - 'null'
                  description: Additional notes or context to submit as part of the
                    dispute evidence.
                product_description:
                  type:
                  - string
                  - 'null'
                  description: A description of the product or service that was provided
                    to the customer.
                refund_policy_disclosure:
                  type:
                  - string
                  - 'null'
                  description: The company's refund policy text to submit as evidence.
                refund_refusal_explanation:
                  type:
                  - string
                  - 'null'
                  description: An explanation of why the refund request was refused.
                service_date:
                  type:
                  - string
                  - 'null'
                  description: The date when the product or service was delivered
                    to the customer.
                cancellation_policy_attachment:
                  type:
                  - object
                  - 'null'
                  properties:
                    id:
                      type: string
                      description: The ID of an existing file object.
                  required:
                  - id
                  description: A file upload containing the company's cancellation
                    policy document.
                  title: FileInputWithId
                customer_communication_attachment:
                  type:
                  - object
                  - 'null'
                  properties:
                    id:
                      type: string
                      description: The ID of an existing file object.
                  required:
                  - id
                  description: A file upload containing evidence of customer communication.
                    Must be a JPEG, PNG, GIF, or PDF.
                  title: FileInputWithId
                refund_policy_attachment:
                  type:
                  - object
                  - 'null'
                  properties:
                    id:
                      type: string
                      description: The ID of an existing file object.
                  required:
                  - id
                  description: A file upload containing the company's refund policy
                    document.
                  title: FileInputWithId
                uncategorized_attachment:
                  type:
                  - object
                  - 'null'
                  properties:
                    id:
                      type: string
                      description: The ID of an existing file object.
                  required:
                  - id
                  description: A file upload for evidence that does not fit into the
                    other categories.
                  title: FileInputWithId
              required: []
              description: Parameters for UpdateDispute
      x-codeSamples:
      - lang: JavaScript
        source: |-
          import Whop from '@whop/sdk';

          const client = new Whop({
            apiKey: process.env['WHOP_API_KEY'], // This is the default and can be omitted
          });

          const dispute = await client.disputes.updateEvidence('dspt_xxxxxxxxxxxxx');

          console.log(dispute.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          dispute = client.disputes.update_evidence(
              id="dspt_xxxxxxxxxxxxx",
          )
          print(dispute.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          dispute = whop.disputes.update_evidence("dspt_xxxxxxxxxxxxx")

          puts(dispute)
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
    Dispute:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the dispute.
          example: dspt_xxxxxxxxxxxxx
        amount:
          type: number
          description: The disputed amount in the specified currency, formatted as
            a decimal.
          example: 6.9
        currency:
          "$ref": "#/components/schemas/Currencies"
          description: The three-letter ISO currency code for the disputed amount.
        status:
          "$ref": "#/components/schemas/DisputeStatuses"
          description: The current status of the dispute lifecycle, such as needs_response,
            under_review, won, or lost.
        editable:
          type:
          - boolean
          - 'null'
          description: Whether the dispute evidence can still be edited and submitted.
            Returns true only when the dispute status requires a response.
        created_at:
          type:
          - string
          - 'null'
          format: date-time
          description: The datetime the dispute was created.
          example: '2023-12-01T05:00:00.401Z'
        visa_rdr:
          type: boolean
          description: Whether the dispute was automatically resolved through Visa
            Rapid Dispute Resolution (RDR).
        needs_response_by:
          type:
          - string
          - 'null'
          format: date-time
          description: The deadline by which dispute evidence must be submitted. Null
            if no response deadline is set.
          example: '2023-12-01T05:00:00.401Z'
        reason:
          type:
          - string
          - 'null'
          description: A human-readable reason for the dispute.
          example: Product Not Received
        plan:
          type:
          - object
          - 'null'
          properties:
            id:
              type: string
              description: The unique identifier for the plan.
              example: plan_xxxxxxxxxxxxx
          required:
          - id
          description: The plan associated with the disputed payment. Null if the
            dispute is not linked to a specific plan.
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
          description: The product associated with the disputed payment. Null if the
            dispute is not linked to a specific product.
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
          required:
          - id
          - title
          description: The company that the dispute was filed against.
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
          - user
          - member
          - membership
          description: The original payment that was disputed.
        access_activity_log:
          type:
          - string
          - 'null'
          description: A log of IP-based access activity for the customer on Whop,
            submitted as evidence in the dispute.
          example: 192.168.1.1 - 2024-01-15 12:00:00 UTC
        billing_address:
          type:
          - string
          - 'null'
          description: The customer's billing address from their payment details,
            submitted as evidence in the dispute.
          example: 123 Main St, New York, NY 10001
        cancellation_policy_disclosure:
          type:
          - string
          - 'null'
          description: A text disclosure describing the company's cancellation policy,
            submitted as dispute evidence.
          example: All sales are final. No refunds after 30 days.
        customer_email_address:
          type:
          - string
          - 'null'
          description: The customer's email address from their payment details, included
            in the evidence packet sent to the payment processor. Editable before
            submission.
          example: customer@example.com
        customer_name:
          type:
          - string
          - 'null'
          description: The customer's full name from their payment details, included
            in the evidence packet sent to the payment processor. Editable before
            submission.
          example: Jane Doe
        notes:
          type:
          - string
          - 'null'
          description: Additional freeform notes submitted by the company as part
            of the dispute evidence.
          example: Customer used the product for 3 months before disputing.
        product_description:
          type:
          - string
          - 'null'
          description: A description of the product or service provided, submitted
            as dispute evidence.
          example: Monthly subscription to premium analytics dashboard.
        refund_policy_disclosure:
          type:
          - string
          - 'null'
          description: A text disclosure describing the company's refund policy, submitted
            as dispute evidence.
          example: Refunds available within 14 days of purchase.
        refund_refusal_explanation:
          type:
          - string
          - 'null'
          description: An explanation from the company for why a refund was refused,
            submitted as dispute evidence.
          example: The customer exceeded the refund window by 60 days.
        service_date:
          type:
          - string
          - 'null'
          description: The date when the product or service was delivered to the customer,
            submitted as dispute evidence.
          example: '2024-01-15'
        cancellation_policy_attachment:
          type:
          - object
          - 'null'
          properties:
            id:
              type: string
              description: Represents a unique identifier that is Base64 obfuscated.
                It is often used to refetch an object or as key for a cache. The ID
                type appears in a JSON response as a String; however, it is not intended
                to be human-readable. When expected as an input type, any string (such
                as `"VXNlci0xMA=="`) or integer (such as `4`) input value will be
                accepted as an ID.
            filename:
              type:
              - string
              - 'null'
              description: The original filename of the uploaded attachment, including
                its file extension.
              example: document.pdf
            content_type:
              type:
              - string
              - 'null'
              description: The MIME type of the uploaded file (e.g., image/jpeg, video/mp4,
                audio/mpeg).
              example: image/jpeg
            url:
              type:
              - string
              - 'null'
              description: A pre-optimized URL for rendering this attachment on the
                client. This should be used for displaying attachments in apps.
              example: https://media.whop.com/abc123/optimized.jpg
          required:
          - id
          - filename
          - content_type
          - url
          description: The cancellation policy document uploaded as dispute evidence.
            Null if no cancellation policy has been provided.
        customer_communication_attachment:
          type:
          - object
          - 'null'
          properties:
            id:
              type: string
              description: Represents a unique identifier that is Base64 obfuscated.
                It is often used to refetch an object or as key for a cache. The ID
                type appears in a JSON response as a String; however, it is not intended
                to be human-readable. When expected as an input type, any string (such
                as `"VXNlci0xMA=="`) or integer (such as `4`) input value will be
                accepted as an ID.
            filename:
              type:
              - string
              - 'null'
              description: The original filename of the uploaded attachment, including
                its file extension.
              example: document.pdf
            content_type:
              type:
              - string
              - 'null'
              description: The MIME type of the uploaded file (e.g., image/jpeg, video/mp4,
                audio/mpeg).
              example: image/jpeg
            url:
              type:
              - string
              - 'null'
              description: A pre-optimized URL for rendering this attachment on the
                client. This should be used for displaying attachments in apps.
              example: https://media.whop.com/abc123/optimized.jpg
          required:
          - id
          - filename
          - content_type
          - url
          description: Evidence of customer communication or product usage, uploaded
            as a dispute attachment. Null if not provided.
        refund_policy_attachment:
          type:
          - object
          - 'null'
          properties:
            id:
              type: string
              description: Represents a unique identifier that is Base64 obfuscated.
                It is often used to refetch an object or as key for a cache. The ID
                type appears in a JSON response as a String; however, it is not intended
                to be human-readable. When expected as an input type, any string (such
                as `"VXNlci0xMA=="`) or integer (such as `4`) input value will be
                accepted as an ID.
            filename:
              type:
              - string
              - 'null'
              description: The original filename of the uploaded attachment, including
                its file extension.
              example: document.pdf
            content_type:
              type:
              - string
              - 'null'
              description: The MIME type of the uploaded file (e.g., image/jpeg, video/mp4,
                audio/mpeg).
              example: image/jpeg
            url:
              type:
              - string
              - 'null'
              description: A pre-optimized URL for rendering this attachment on the
                client. This should be used for displaying attachments in apps.
              example: https://media.whop.com/abc123/optimized.jpg
          required:
          - id
          - filename
          - content_type
          - url
          description: The refund policy document uploaded as dispute evidence. Null
            if no refund policy has been provided.
        uncategorized_attachment:
          type:
          - object
          - 'null'
          properties:
            id:
              type: string
              description: Represents a unique identifier that is Base64 obfuscated.
                It is often used to refetch an object or as key for a cache. The ID
                type appears in a JSON response as a String; however, it is not intended
                to be human-readable. When expected as an input type, any string (such
                as `"VXNlci0xMA=="`) or integer (such as `4`) input value will be
                accepted as an ID.
            filename:
              type:
              - string
              - 'null'
              description: The original filename of the uploaded attachment, including
                its file extension.
              example: document.pdf
            content_type:
              type:
              - string
              - 'null'
              description: The MIME type of the uploaded file (e.g., image/jpeg, video/mp4,
                audio/mpeg).
              example: image/jpeg
            url:
              type:
              - string
              - 'null'
              description: A pre-optimized URL for rendering this attachment on the
                client. This should be used for displaying attachments in apps.
              example: https://media.whop.com/abc123/optimized.jpg
          required:
          - id
          - filename
          - content_type
          - url
          description: An additional attachment that does not fit into the standard
            evidence categories. Null if not provided.
      required:
      - id
      - amount
      - currency
      - status
      - editable
      - created_at
      - visa_rdr
      - needs_response_by
      - reason
      - plan
      - product
      - company
      - payment
      - access_activity_log
      - billing_address
      - cancellation_policy_disclosure
      - customer_email_address
      - customer_name
      - notes
      - product_description
      - refund_policy_disclosure
      - refund_refusal_explanation
      - service_date
      - cancellation_policy_attachment
      - customer_communication_attachment
      - refund_policy_attachment
      - uncategorized_attachment
      description: A dispute is a chargeback or payment challenge filed against a
        company, including evidence and response status.
    DisputeStatuses:
      type: string
      enum:
      - warning_needs_response
      - warning_under_review
      - warning_closed
      - needs_response
      - under_review
      - won
      - lost
      - closed
      - other
      description: The possible statuses of a dispute
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
```
