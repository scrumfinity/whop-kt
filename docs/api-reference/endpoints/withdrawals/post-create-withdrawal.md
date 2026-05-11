# POST /withdrawals — Create withdrawal

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `POST`
- **Path:** `/withdrawals`
- **Operation ID:** `createWithdrawal`
- **Tags:** `Withdrawals`
- **Required bearer scopes:** `payout:withdraw_funds`, `payout:destination:read`

## Description

Creates a withdrawal request for a ledger account

Required permissions:
 - `payout:withdraw_funds`
 - `payout:destination:read`

## Parameters

_No path, query, header, or cookie parameters._

## Request body

Required: **yes**
Content types: `application/json`

### `application/json` request schema

```yaml
type: object
properties:
  amount:
    type: number
    description: The amount to withdraw in the specified currency
    example: 6.9
  company_id:
    type: string
    description: The ID of the company to withdraw from.
    example: biz_xxxxxxxxxxxxxx
  currency:
    "$ref": "#/components/schemas/Currencies"
    description: The currency that is being withdrawn.
  payout_method_id:
    type:
    - string
    - 'null'
    description: The ID of the payout method to use for the withdrawal.
  platform_covers_fees:
    type:
    - boolean
    - 'null'
    description: Whether the platform covers the payout fees instead of the connected
      account.
  statement_descriptor:
    type:
    - string
    - 'null'
    description: Custom statement descriptor for the withdrawal. Must be between 5
      and 22 characters and contain only alphanumeric characters.
required:
- amount
- company_id
- currency
description: Parameters for CreateLedgerWithdrawal
title: CreateLedgerWithdrawalInputAndCompanyId
```


## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `Withdrawal` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/Withdrawal"
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

const withdrawal = await client.withdrawals.create({
  amount: 6.9,
  company_id: 'biz_xxxxxxxxxxxxxx',
  currency: 'usd',
});

console.log(withdrawal.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
withdrawal = client.withdrawals.create(
    amount=6.9,
    company_id="biz_xxxxxxxxxxxxxx",
    currency="usd",
)
print(withdrawal.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

withdrawal = whop.withdrawals.create(amount: 6.9, company_id: "biz_xxxxxxxxxxxxxx", currency: :usd)

puts(withdrawal)
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
  PayoutErrorCodes:
    type: string
    enum:
    - account_closed
    - account_does_not_exist
    - account_information_invalid
    - account_number_invalid_region
    - account_frozen
    - account_lookup_failed
    - account_not_found
    - amount_out_of_bounds
    - attributes_not_validated
    - b2b_payments_prohibited
    - bank_statement_required
    - compliance_review
    - currency_not_supported
    - deposit_canceled
    - deposit_failed
    - deposit_rejected
    - destination_unavailable
    - exceeded_account_limit
    - expired_quote
    - generic_payout_error
    - technical_problem
    - identification_number_invalid
    - invalid_account_number
    - invalid_bank_code
    - invalid_beneficiary
    - invalid_mailing_address
    - invalid_branch_number
    - invalid_branch_code
    - invalid_phone_number
    - invalid_routing_number
    - invalid_swift_code
    - invalid_company_details
    - manual_cancelation
    - misc_error
    - missing_city_and_country
    - missing_phone_number
    - missing_remittance_info
    - payee_name_invalid
    - receiving_account_locked
    - rejected_by_compliance
    - rtp_not_supported
    - non_transaction_account
    - source_token_insufficient_funds
    - ssn_invalid
    - wallet_screenshot_required
    - unsupported_region
    - payout_provider_timeout
    description: The different error codes a payout can be in.
  Withdrawal:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the withdrawal.
        example: wdrl_xxxxxxxxxxxxx
      status:
        "$ref": "#/components/schemas/WithdrawalStatus"
        description: The computed lifecycle status of the withdrawal, accounting for
          the state of associated payouts (e.g., 'requested', 'in_transit', 'completed',
          'failed').
      amount:
        type: number
        description: The withdrawal amount as a decimal number in the specified currency
          (e.g., 100.00 for $100.00 USD).
        example: 6.9
      currency:
        "$ref": "#/components/schemas/Currencies"
        description: The three-letter ISO currency code for this withdrawal (e.g.,
          'usd', 'eur').
      fee_amount:
        type: number
        description: The fee charged for processing this withdrawal, in the same currency
          as the withdrawal amount.
        example: 6.9
      fee_type:
        oneOf:
        - "$ref": "#/components/schemas/WithdrawalFeeTypes"
        - type: 'null'
        description: How the fee was applied to the withdrawal. 'exclusive' means
          the fee was added on top (user receives the full requested amount). 'inclusive'
          means the fee was deducted from the withdrawal (user receives less than
          requested). Null if no fee was charged.
      speed:
        "$ref": "#/components/schemas/WithdrawalSpeeds"
        description: The processing speed selected for this withdrawal ('standard'
          or 'instant').
      created_at:
        type: string
        format: date-time
        description: The datetime the withdrawal was created.
        example: '2023-12-01T05:00:00.401Z'
      markup_fee:
        type: number
        description: An additional markup fee charged for the withdrawal, in the same
          currency as the withdrawal amount. Only applies to platform accounts using
          Whop Rails.
        example: 6.9
      ledger_account:
        type: object
        properties:
          id:
            type: string
            description: The unique identifier for the ledger account.
            example: ldgr_xxxxxxxxxxxxx
          company_id:
            type:
            - string
            - 'null'
            description: Represents a unique identifier that is Base64 obfuscated.
              It is often used to refetch an object or as key for a cache. The ID
              type appears in a JSON response as a String; however, it is not intended
              to be human-readable. When expected as an input type, any string (such
              as `"VXNlci0xMA=="`) or integer (such as `4`) input value will be accepted
              as an ID.
        required:
        - id
        - company_id
        description: The ledger account from which the withdrawal funds are sourced.
      payout_token:
        type:
        - object
        - 'null'
        properties:
          id:
            type: string
            description: The unique identifier for the payout token.
            example: potk_xxxxxxxxxxxxx
          payer_name:
            type:
            - string
            - 'null'
            description: The legal name of the account holder receiving payouts. Null
              if not provided.
            example: Acme Corp LLC
          nickname:
            type:
            - string
            - 'null'
            description: A user-defined label to help identify this payout destination.
              Not sent to the provider. Null if no nickname has been set.
            example: My Business Account
          destination_currency_code:
            type: string
            description: The three-letter ISO currency code that payouts are delivered
              in for this destination.
            example: USD
          created_at:
            type: string
            format: date-time
            description: The datetime the payout token was created.
            example: '2023-12-01T05:00:00.401Z'
        required:
        - id
        - payer_name
        - nickname
        - destination_currency_code
        - created_at
        description: The saved payout destination used for this withdrawal (e.g.,
          a bank account or PayPal address). Null if no payout token was used.
      error_code:
        oneOf:
        - "$ref": "#/components/schemas/PayoutErrorCodes"
        - type: 'null'
        description: A machine-readable error code describing why the payout failed.
          Null if no error occurred.
      error_message:
        type:
        - string
        - 'null'
        description: A human-readable message describing why the payout failed. Null
          if no error occurred.
        example: Destination bank account is invalid.
      estimated_availability:
        type:
        - string
        - 'null'
        format: date-time
        description: The estimated time at which the funds become available in the
          destination account. Null if no estimate is available. As an ISO 8601 timestamp.
        example: '2023-12-01T05:00:00.401Z'
      trace_code:
        type:
        - string
        - 'null'
        description: The ACH trace number for tracking the payout through the banking
          network. Null if not available or not an ACH transaction.
        example: '021000021234567'
    required:
    - id
    - status
    - amount
    - currency
    - fee_amount
    - fee_type
    - speed
    - created_at
    - markup_fee
    - ledger_account
    - payout_token
    - error_code
    - error_message
    - estimated_availability
    - trace_code
    description: A withdrawal represents a request to transfer funds from a ledger
      account to an external payout method.
  WithdrawalFeeTypes:
    type: string
    enum:
    - exclusive
    - inclusive
    description: The different fee types for a withdrawal.
  WithdrawalSpeeds:
    type: string
    enum:
    - standard
    - instant
    description: The different speeds of withdrawals
  WithdrawalStatus:
    type: string
    enum:
    - requested
    - awaiting_payment
    - in_transit
    - completed
    - failed
    - canceled
    - denied
    description: The status of a withdrawal request
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
  "/withdrawals":
    post:
      tags:
      - Withdrawals
      operationId: createWithdrawal
      summary: Create withdrawal
      description: |-
        Creates a withdrawal request for a ledger account

        Required permissions:
         - `payout:withdraw_funds`
         - `payout:destination:read`
      parameters: []
      x-group-title: Payouts
      security:
      - bearerAuth:
        - payout:withdraw_funds
        - payout:destination:read
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/Withdrawal"
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
                amount:
                  type: number
                  description: The amount to withdraw in the specified currency
                  example: 6.9
                company_id:
                  type: string
                  description: The ID of the company to withdraw from.
                  example: biz_xxxxxxxxxxxxxx
                currency:
                  "$ref": "#/components/schemas/Currencies"
                  description: The currency that is being withdrawn.
                payout_method_id:
                  type:
                  - string
                  - 'null'
                  description: The ID of the payout method to use for the withdrawal.
                platform_covers_fees:
                  type:
                  - boolean
                  - 'null'
                  description: Whether the platform covers the payout fees instead
                    of the connected account.
                statement_descriptor:
                  type:
                  - string
                  - 'null'
                  description: Custom statement descriptor for the withdrawal. Must
                    be between 5 and 22 characters and contain only alphanumeric characters.
              required:
              - amount
              - company_id
              - currency
              description: Parameters for CreateLedgerWithdrawal
              title: CreateLedgerWithdrawalInputAndCompanyId
      x-codeSamples:
      - lang: JavaScript
        source: |-
          import Whop from '@whop/sdk';

          const client = new Whop({
            apiKey: process.env['WHOP_API_KEY'], // This is the default and can be omitted
          });

          const withdrawal = await client.withdrawals.create({
            amount: 6.9,
            company_id: 'biz_xxxxxxxxxxxxxx',
            currency: 'usd',
          });

          console.log(withdrawal.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          withdrawal = client.withdrawals.create(
              amount=6.9,
              company_id="biz_xxxxxxxxxxxxxx",
              currency="usd",
          )
          print(withdrawal.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          withdrawal = whop.withdrawals.create(amount: 6.9, company_id: "biz_xxxxxxxxxxxxxx", currency: :usd)

          puts(withdrawal)
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
    PayoutErrorCodes:
      type: string
      enum:
      - account_closed
      - account_does_not_exist
      - account_information_invalid
      - account_number_invalid_region
      - account_frozen
      - account_lookup_failed
      - account_not_found
      - amount_out_of_bounds
      - attributes_not_validated
      - b2b_payments_prohibited
      - bank_statement_required
      - compliance_review
      - currency_not_supported
      - deposit_canceled
      - deposit_failed
      - deposit_rejected
      - destination_unavailable
      - exceeded_account_limit
      - expired_quote
      - generic_payout_error
      - technical_problem
      - identification_number_invalid
      - invalid_account_number
      - invalid_bank_code
      - invalid_beneficiary
      - invalid_mailing_address
      - invalid_branch_number
      - invalid_branch_code
      - invalid_phone_number
      - invalid_routing_number
      - invalid_swift_code
      - invalid_company_details
      - manual_cancelation
      - misc_error
      - missing_city_and_country
      - missing_phone_number
      - missing_remittance_info
      - payee_name_invalid
      - receiving_account_locked
      - rejected_by_compliance
      - rtp_not_supported
      - non_transaction_account
      - source_token_insufficient_funds
      - ssn_invalid
      - wallet_screenshot_required
      - unsupported_region
      - payout_provider_timeout
      description: The different error codes a payout can be in.
    Withdrawal:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the withdrawal.
          example: wdrl_xxxxxxxxxxxxx
        status:
          "$ref": "#/components/schemas/WithdrawalStatus"
          description: The computed lifecycle status of the withdrawal, accounting
            for the state of associated payouts (e.g., 'requested', 'in_transit',
            'completed', 'failed').
        amount:
          type: number
          description: The withdrawal amount as a decimal number in the specified
            currency (e.g., 100.00 for $100.00 USD).
          example: 6.9
        currency:
          "$ref": "#/components/schemas/Currencies"
          description: The three-letter ISO currency code for this withdrawal (e.g.,
            'usd', 'eur').
        fee_amount:
          type: number
          description: The fee charged for processing this withdrawal, in the same
            currency as the withdrawal amount.
          example: 6.9
        fee_type:
          oneOf:
          - "$ref": "#/components/schemas/WithdrawalFeeTypes"
          - type: 'null'
          description: How the fee was applied to the withdrawal. 'exclusive' means
            the fee was added on top (user receives the full requested amount). 'inclusive'
            means the fee was deducted from the withdrawal (user receives less than
            requested). Null if no fee was charged.
        speed:
          "$ref": "#/components/schemas/WithdrawalSpeeds"
          description: The processing speed selected for this withdrawal ('standard'
            or 'instant').
        created_at:
          type: string
          format: date-time
          description: The datetime the withdrawal was created.
          example: '2023-12-01T05:00:00.401Z'
        markup_fee:
          type: number
          description: An additional markup fee charged for the withdrawal, in the
            same currency as the withdrawal amount. Only applies to platform accounts
            using Whop Rails.
          example: 6.9
        ledger_account:
          type: object
          properties:
            id:
              type: string
              description: The unique identifier for the ledger account.
              example: ldgr_xxxxxxxxxxxxx
            company_id:
              type:
              - string
              - 'null'
              description: Represents a unique identifier that is Base64 obfuscated.
                It is often used to refetch an object or as key for a cache. The ID
                type appears in a JSON response as a String; however, it is not intended
                to be human-readable. When expected as an input type, any string (such
                as `"VXNlci0xMA=="`) or integer (such as `4`) input value will be
                accepted as an ID.
          required:
          - id
          - company_id
          description: The ledger account from which the withdrawal funds are sourced.
        payout_token:
          type:
          - object
          - 'null'
          properties:
            id:
              type: string
              description: The unique identifier for the payout token.
              example: potk_xxxxxxxxxxxxx
            payer_name:
              type:
              - string
              - 'null'
              description: The legal name of the account holder receiving payouts.
                Null if not provided.
              example: Acme Corp LLC
            nickname:
              type:
              - string
              - 'null'
              description: A user-defined label to help identify this payout destination.
                Not sent to the provider. Null if no nickname has been set.
              example: My Business Account
            destination_currency_code:
              type: string
              description: The three-letter ISO currency code that payouts are delivered
                in for this destination.
              example: USD
            created_at:
              type: string
              format: date-time
              description: The datetime the payout token was created.
              example: '2023-12-01T05:00:00.401Z'
          required:
          - id
          - payer_name
          - nickname
          - destination_currency_code
          - created_at
          description: The saved payout destination used for this withdrawal (e.g.,
            a bank account or PayPal address). Null if no payout token was used.
        error_code:
          oneOf:
          - "$ref": "#/components/schemas/PayoutErrorCodes"
          - type: 'null'
          description: A machine-readable error code describing why the payout failed.
            Null if no error occurred.
        error_message:
          type:
          - string
          - 'null'
          description: A human-readable message describing why the payout failed.
            Null if no error occurred.
          example: Destination bank account is invalid.
        estimated_availability:
          type:
          - string
          - 'null'
          format: date-time
          description: The estimated time at which the funds become available in the
            destination account. Null if no estimate is available. As an ISO 8601 timestamp.
          example: '2023-12-01T05:00:00.401Z'
        trace_code:
          type:
          - string
          - 'null'
          description: The ACH trace number for tracking the payout through the banking
            network. Null if not available or not an ACH transaction.
          example: '021000021234567'
      required:
      - id
      - status
      - amount
      - currency
      - fee_amount
      - fee_type
      - speed
      - created_at
      - markup_fee
      - ledger_account
      - payout_token
      - error_code
      - error_message
      - estimated_availability
      - trace_code
      description: A withdrawal represents a request to transfer funds from a ledger
        account to an external payout method.
    WithdrawalFeeTypes:
      type: string
      enum:
      - exclusive
      - inclusive
      description: The different fee types for a withdrawal.
    WithdrawalSpeeds:
      type: string
      enum:
      - standard
      - instant
      description: The different speeds of withdrawals
    WithdrawalStatus:
      type: string
      enum:
      - requested
      - awaiting_payment
      - in_transit
      - completed
      - failed
      - canceled
      - denied
      description: The status of a withdrawal request
```
