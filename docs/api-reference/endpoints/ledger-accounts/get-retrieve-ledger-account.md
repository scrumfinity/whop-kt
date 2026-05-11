# GET /ledger_accounts/{id} — Retrieve ledger account

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/ledger_accounts/{id}`
- **Operation ID:** `retrieveLedgerAccount`
- **Tags:** `Ledger accounts`
- **Required bearer scopes:** `company:balance:read`, `payout:account:read`

## Description

Retrieves the details of an existing ledger account.

Required permissions:
 - `company:balance:read`
 - `payout:account:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `id` | path | yes | `string` | The identifier to look up the ledger account. Accepts a user ID ('user_xxx'), company ID ('biz_xxx'), or ledger account ID ('ldgr_xxx'). | ldgr_xxxxxxxxxxxxx |

## Request body

_No request body._

## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `LedgerAccount` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/LedgerAccount"
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

const ledgerAccount = await client.ledgerAccounts.retrieve('ldgr_xxxxxxxxxxxxx');

console.log(ledgerAccount.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
ledger_account = client.ledger_accounts.retrieve(
    "ldgr_xxxxxxxxxxxxx",
)
print(ledger_account.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

ledger_account = whop.ledger_accounts.retrieve("ldgr_xxxxxxxxxxxxx")

puts(ledger_account)
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
  LedgerAccount:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the ledger account.
        example: ldgr_xxxxxxxxxxxxx
      balances:
        type: array
        items:
          type: object
          properties:
            balance:
              type: number
              description: The amount of the balance.
              example: 6.9
            currency:
              "$ref": "#/components/schemas/Currencies"
              description: The currency of the balance.
            pending_balance:
              type: number
              description: The amount of the balance that is pending.
              example: 6.9
            reserve_balance:
              type: number
              description: The amount of the balance that is reserved.
              example: 6.9
          required:
          - balance
          - currency
          - pending_balance
          - reserve_balance
          description: A cached balance for a LedgerAccount in respect to a currency.
        description: The balances associated with the account.
      transfer_fee:
        type:
        - number
        - 'null'
        description: The fee for transfers, if applicable.
        example: 6.9
      payout_account_details:
        type:
        - object
        - 'null'
        properties:
          id:
            type: string
            description: The unique identifier for the payout account.
            example: poact_xxxxxxxxxxxx
          status:
            oneOf:
            - "$ref": "#/components/schemas/PayoutAccountCalculatedStatuses"
            - type: 'null'
            description: The granular calculated status of the payout account reflecting
              its current KYC and withdrawal readiness state.
          business_name:
            type:
            - string
            - 'null'
            description: The company's legal name
          phone:
            type:
            - string
            - 'null'
            description: The business representative's phone
          email:
            type:
            - string
            - 'null'
            description: The email address of the representative
          business_representative:
            type:
            - object
            - 'null'
            properties:
              date_of_birth:
                type:
                - string
                - 'null'
                description: The date of birth of the business representative in ISO
                  8601 format (YYYY-MM-DD).
              first_name:
                type:
                - string
                - 'null'
                description: The first name of the business representative.
              middle_name:
                type:
                - string
                - 'null'
                description: The middle name of the business representative.
              last_name:
                type:
                - string
                - 'null'
                description: The last name of the business representative.
            required:
            - date_of_birth
            - first_name
            - middle_name
            - last_name
            description: The business representative for this payout account
          address:
            type:
            - object
            - 'null'
            properties:
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
              country:
                type:
                - string
                - 'null'
                description: The country of the address.
            required:
            - line1
            - line2
            - city
            - postal_code
            - state
            - country
            description: The physical address associated with this payout account
          latest_verification:
            type:
            - object
            - 'null'
            properties:
              id:
                type: string
                description: The numeric id of the verification record.
                example: verf_xxxxxxxxxxxxx
              status:
                "$ref": "#/components/schemas/VerificationStatuses"
                description: The current status of this verification session.
              last_error_code:
                oneOf:
                - "$ref": "#/components/schemas/VerificationErrorCodes"
                - type: 'null'
                description: The most recent error code returned during verification.
                  Null if no error has occurred.
              last_error_reason:
                type:
                - string
                - 'null'
                description: A human-readable explanation of the most recent verification
                  error. Null if no error has occurred.
                example: Document image was too blurry to read.
            required:
            - id
            - status
            - last_error_code
            - last_error_reason
            description: The latest verification for the connected account.
        required:
        - id
        - status
        - business_name
        - phone
        - email
        - business_representative
        - address
        - latest_verification
        description: The payout account associated with the LedgerAccount, if any.
      payments_approval_status:
        oneOf:
        - "$ref": "#/components/schemas/PaymentsApprovalStatuses"
        - type: 'null'
        description: The status of payments approval for the ledger account.
      ledger_type:
        "$ref": "#/components/schemas/LedgerTypes"
        description: The type of ledger account.
      owner:
        type: object
        oneOf:
        - type:
          - object
          - 'null'
          properties:
            typename:
              type: string
              const: User
              description: The typename of this object
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
          - typename
          - id
          - name
          - username
          description: A user account on Whop. Contains profile information, identity
            details, and social connections.
          title: User
        - type:
          - object
          - 'null'
          properties:
            typename:
              type: string
              const: Company
              description: The typename of this object
            id:
              type: string
              description: The unique identifier for the company.
              example: biz_xxxxxxxxxxxxxx
            route:
              type: string
              description: The URL slug for the company's store page (e.g., 'pickaxe'
                in whop.com/pickaxe).
              example: pickaxe
            title:
              type: string
              description: The display name of the company shown to customers.
              example: Pickaxe
          required:
          - typename
          - id
          - route
          - title
          description: A company is a seller on Whop. Companies own products, manage
            members, and receive payouts.
          title: Company
        discriminator:
          propertyName: typename
        description: The owner of the ledger account.
    required:
    - id
    - balances
    - transfer_fee
    - payout_account_details
    - payments_approval_status
    - ledger_type
    - owner
    description: A ledger account represents a financial account on Whop that can
      hold many balances.
  LedgerTypes:
    type: string
    enum:
    - primary
    - pool
    description: The types of ledgers that can be created.
  PaymentsApprovalStatuses:
    type: string
    enum:
    - pending
    - approved
    - monitoring
    - rejected
    description: The different approval statuses an account can have.
  PayoutAccountCalculatedStatuses:
    type: string
    enum:
    - connected
    - disabled
    - action_required
    - pending_verification
    - verification_failed
    - not_started
    description: The granular calculated statuses reflecting payout account KYC and
      withdrawal readiness.
  VerificationErrorCodes:
    type: string
    enum:
    - abandoned
    - consent_declined
    - country_not_supported
    - device_not_supported
    - document_expired
    - document_type_not_supported
    - document_unverified_other
    - email_unverified_other
    - email_verification_declined
    - id_number_insufficient_document_data
    - id_number_mismatch
    - id_number_unverified_other
    - phone_unverified_other
    - phone_verification_declined
    - selfie_document_missing_photo
    - selfie_face_mismatch
    - selfie_manipulated
    - selfie_unverified_other
    - under_supported_age
    description: An error code for a verification attempt.
  VerificationStatuses:
    type: string
    enum:
    - requires_input
    - processing
    - verified
    - canceled
    - created
    - started
    - submitted
    - approved
    - declined
    - resubmission_requested
    - expired
    - abandoned
    - review
    - action_required
    description: A status for a verification.
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
  "/ledger_accounts/{id}":
    get:
      tags:
      - Ledger accounts
      operationId: retrieveLedgerAccount
      summary: Retrieve ledger account
      description: |-
        Retrieves the details of an existing ledger account.

        Required permissions:
         - `company:balance:read`
         - `payout:account:read`
      parameters:
      - name: id
        in: path
        required: true
        description: The identifier to look up the ledger account. Accepts a user
          ID ('user_xxx'), company ID ('biz_xxx'), or ledger account ID ('ldgr_xxx').
        schema:
          type: string
          example: ldgr_xxxxxxxxxxxxx
      x-group-title: Payouts
      security:
      - bearerAuth:
        - company:balance:read
        - payout:account:read
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/LedgerAccount"
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

          const ledgerAccount = await client.ledgerAccounts.retrieve('ldgr_xxxxxxxxxxxxx');

          console.log(ledgerAccount.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          ledger_account = client.ledger_accounts.retrieve(
              "ldgr_xxxxxxxxxxxxx",
          )
          print(ledger_account.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          ledger_account = whop.ledger_accounts.retrieve("ldgr_xxxxxxxxxxxxx")

          puts(ledger_account)
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
    LedgerAccount:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the ledger account.
          example: ldgr_xxxxxxxxxxxxx
        balances:
          type: array
          items:
            type: object
            properties:
              balance:
                type: number
                description: The amount of the balance.
                example: 6.9
              currency:
                "$ref": "#/components/schemas/Currencies"
                description: The currency of the balance.
              pending_balance:
                type: number
                description: The amount of the balance that is pending.
                example: 6.9
              reserve_balance:
                type: number
                description: The amount of the balance that is reserved.
                example: 6.9
            required:
            - balance
            - currency
            - pending_balance
            - reserve_balance
            description: A cached balance for a LedgerAccount in respect to a currency.
          description: The balances associated with the account.
        transfer_fee:
          type:
          - number
          - 'null'
          description: The fee for transfers, if applicable.
          example: 6.9
        payout_account_details:
          type:
          - object
          - 'null'
          properties:
            id:
              type: string
              description: The unique identifier for the payout account.
              example: poact_xxxxxxxxxxxx
            status:
              oneOf:
              - "$ref": "#/components/schemas/PayoutAccountCalculatedStatuses"
              - type: 'null'
              description: The granular calculated status of the payout account reflecting
                its current KYC and withdrawal readiness state.
            business_name:
              type:
              - string
              - 'null'
              description: The company's legal name
            phone:
              type:
              - string
              - 'null'
              description: The business representative's phone
            email:
              type:
              - string
              - 'null'
              description: The email address of the representative
            business_representative:
              type:
              - object
              - 'null'
              properties:
                date_of_birth:
                  type:
                  - string
                  - 'null'
                  description: The date of birth of the business representative in
                    ISO 8601 format (YYYY-MM-DD).
                first_name:
                  type:
                  - string
                  - 'null'
                  description: The first name of the business representative.
                middle_name:
                  type:
                  - string
                  - 'null'
                  description: The middle name of the business representative.
                last_name:
                  type:
                  - string
                  - 'null'
                  description: The last name of the business representative.
              required:
              - date_of_birth
              - first_name
              - middle_name
              - last_name
              description: The business representative for this payout account
            address:
              type:
              - object
              - 'null'
              properties:
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
                country:
                  type:
                  - string
                  - 'null'
                  description: The country of the address.
              required:
              - line1
              - line2
              - city
              - postal_code
              - state
              - country
              description: The physical address associated with this payout account
            latest_verification:
              type:
              - object
              - 'null'
              properties:
                id:
                  type: string
                  description: The numeric id of the verification record.
                  example: verf_xxxxxxxxxxxxx
                status:
                  "$ref": "#/components/schemas/VerificationStatuses"
                  description: The current status of this verification session.
                last_error_code:
                  oneOf:
                  - "$ref": "#/components/schemas/VerificationErrorCodes"
                  - type: 'null'
                  description: The most recent error code returned during verification.
                    Null if no error has occurred.
                last_error_reason:
                  type:
                  - string
                  - 'null'
                  description: A human-readable explanation of the most recent verification
                    error. Null if no error has occurred.
                  example: Document image was too blurry to read.
              required:
              - id
              - status
              - last_error_code
              - last_error_reason
              description: The latest verification for the connected account.
          required:
          - id
          - status
          - business_name
          - phone
          - email
          - business_representative
          - address
          - latest_verification
          description: The payout account associated with the LedgerAccount, if any.
        payments_approval_status:
          oneOf:
          - "$ref": "#/components/schemas/PaymentsApprovalStatuses"
          - type: 'null'
          description: The status of payments approval for the ledger account.
        ledger_type:
          "$ref": "#/components/schemas/LedgerTypes"
          description: The type of ledger account.
        owner:
          type: object
          oneOf:
          - type:
            - object
            - 'null'
            properties:
              typename:
                type: string
                const: User
                description: The typename of this object
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
            - typename
            - id
            - name
            - username
            description: A user account on Whop. Contains profile information, identity
              details, and social connections.
            title: User
          - type:
            - object
            - 'null'
            properties:
              typename:
                type: string
                const: Company
                description: The typename of this object
              id:
                type: string
                description: The unique identifier for the company.
                example: biz_xxxxxxxxxxxxxx
              route:
                type: string
                description: The URL slug for the company's store page (e.g., 'pickaxe'
                  in whop.com/pickaxe).
                example: pickaxe
              title:
                type: string
                description: The display name of the company shown to customers.
                example: Pickaxe
            required:
            - typename
            - id
            - route
            - title
            description: A company is a seller on Whop. Companies own products, manage
              members, and receive payouts.
            title: Company
          discriminator:
            propertyName: typename
          description: The owner of the ledger account.
      required:
      - id
      - balances
      - transfer_fee
      - payout_account_details
      - payments_approval_status
      - ledger_type
      - owner
      description: A ledger account represents a financial account on Whop that can
        hold many balances.
    LedgerTypes:
      type: string
      enum:
      - primary
      - pool
      description: The types of ledgers that can be created.
    PaymentsApprovalStatuses:
      type: string
      enum:
      - pending
      - approved
      - monitoring
      - rejected
      description: The different approval statuses an account can have.
    PayoutAccountCalculatedStatuses:
      type: string
      enum:
      - connected
      - disabled
      - action_required
      - pending_verification
      - verification_failed
      - not_started
      description: The granular calculated statuses reflecting payout account KYC
        and withdrawal readiness.
    VerificationErrorCodes:
      type: string
      enum:
      - abandoned
      - consent_declined
      - country_not_supported
      - device_not_supported
      - document_expired
      - document_type_not_supported
      - document_unverified_other
      - email_unverified_other
      - email_verification_declined
      - id_number_insufficient_document_data
      - id_number_mismatch
      - id_number_unverified_other
      - phone_unverified_other
      - phone_verification_declined
      - selfie_document_missing_photo
      - selfie_face_mismatch
      - selfie_manipulated
      - selfie_unverified_other
      - under_supported_age
      description: An error code for a verification attempt.
    VerificationStatuses:
      type: string
      enum:
      - requires_input
      - processing
      - verified
      - canceled
      - created
      - started
      - submitted
      - approved
      - declined
      - resubmission_requested
      - expired
      - abandoned
      - review
      - action_required
      description: A status for a verification.
```
