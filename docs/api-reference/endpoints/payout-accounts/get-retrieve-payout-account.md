# GET /payout_accounts/{id} — Retrieve payout account

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/payout_accounts/{id}`
- **Operation ID:** `retrievePayoutAccount`
- **Tags:** `Payout accounts`
- **Required bearer scopes:** `payout:account:read`

## Description

Retrieves the details of an existing payout account.

Required permissions:
 - `payout:account:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `id` | path | yes | `string` | The unique identifier of the payout account to retrieve. | poact_xxxxxxxxxxxx |

## Request body

_No request body._

## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `PayoutAccount` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/PayoutAccount"
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

const payoutAccount = await client.payoutAccounts.retrieve('poact_xxxxxxxxxxxx');

console.log(payoutAccount.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
payout_account = client.payout_accounts.retrieve(
    "poact_xxxxxxxxxxxx",
)
print(payout_account.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

payout_account = whop.payout_accounts.retrieve("poact_xxxxxxxxxxxx")

puts(payout_account)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  PayoutAccount:
    type: object
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
            description: The date of birth of the business representative in ISO 8601
              format (YYYY-MM-DD).
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
    description: An object representing an account used for payouts.
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
  "/payout_accounts/{id}":
    get:
      tags:
      - Payout accounts
      operationId: retrievePayoutAccount
      summary: Retrieve payout account
      description: |-
        Retrieves the details of an existing payout account.

        Required permissions:
         - `payout:account:read`
      parameters:
      - name: id
        in: path
        required: true
        description: The unique identifier of the payout account to retrieve.
        schema:
          type: string
          example: poact_xxxxxxxxxxxx
      x-group-title: Payouts
      security:
      - bearerAuth:
        - payout:account:read
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/PayoutAccount"
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

          const payoutAccount = await client.payoutAccounts.retrieve('poact_xxxxxxxxxxxx');

          console.log(payoutAccount.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          payout_account = client.payout_accounts.retrieve(
              "poact_xxxxxxxxxxxx",
          )
          print(payout_account.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          payout_account = whop.payout_accounts.retrieve("poact_xxxxxxxxxxxx")

          puts(payout_account)
components:
  schemas:
    PayoutAccount:
      type: object
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
      description: An object representing an account used for payouts.
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
