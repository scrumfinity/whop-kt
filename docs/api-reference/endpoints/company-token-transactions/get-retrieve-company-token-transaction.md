# GET /company_token_transactions/{id} — Retrieve company token transaction

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/company_token_transactions/{id}`
- **Operation ID:** `retrieveCompanyTokenTransaction`
- **Tags:** `Company token transactions`
- **Required bearer scopes:** `company_token_transaction:read`, `member:basic:read`, `company:basic:read`

## Description

Retrieves the details of an existing company token transaction.

Required permissions:
 - `company_token_transaction:read`
 - `member:basic:read`
 - `company:basic:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `id` | path | yes | `string` | The unique identifier of the token transaction to retrieve. | ctt_xxxxxxxxxxxxxx |

## Request body

_No request body._

## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `CompanyTokenTransaction` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/CompanyTokenTransaction"
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

const companyTokenTransaction = await client.companyTokenTransactions.retrieve('ctt_xxxxxxxxxxxxxx');

console.log(companyTokenTransaction.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
company_token_transaction = client.company_token_transactions.retrieve(
    "ctt_xxxxxxxxxxxxxx",
)
print(company_token_transaction.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

company_token_transaction = whop.company_token_transactions.retrieve("ctt_xxxxxxxxxxxxxx")

puts(company_token_transaction)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  CompanyTokenTransaction:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the company token transaction.
      transaction_type:
        "$ref": "#/components/schemas/CompanyTokenTransactionTypes"
        description: The direction of this token transaction (add, subtract, or transfer).
      amount:
        type: number
        description: The token amount for this transaction. Always a positive value
          regardless of transaction type.
        example: 6.9
      description:
        type:
        - string
        - 'null'
        description: Free-text description explaining the reason for this token transaction.
          Null if no description was provided.
        example: Reward for completing onboarding
      created_at:
        type: string
        format: date-time
        description: The datetime the company token transaction was created.
        example: '2023-12-01T05:00:00.401Z'
      linked_transaction_id:
        type:
        - string
        - 'null'
        description: The ID of the corresponding transaction on the other side of
          a transfer. Null if this is not a transfer transaction.
      idempotency_key:
        type:
        - string
        - 'null'
        description: A unique key used to prevent duplicate transactions when retrying
          API requests. Null if no idempotency key was provided.
        example: txn_reward_usr_123_2024
      user:
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
        description: The user whose token balance was affected by this transaction.
      member:
        type: object
        properties:
          id:
            type: string
            description: The unique identifier for the company member.
        required:
        - id
        description: The member whose token balance was affected by this transaction.
      company:
        type: object
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
        description: The company whose token balance this transaction affects.
    required:
    - id
    - transaction_type
    - amount
    - description
    - created_at
    - linked_transaction_id
    - idempotency_key
    - user
    - member
    - company
    description: A token transaction records a credit or debit to a member's token
      balance within a company, including transfers between members.
  CompanyTokenTransactionTypes:
    type: string
    enum:
    - add
    - subtract
    - transfer
    description: The type of token transaction
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
  "/company_token_transactions/{id}":
    get:
      tags:
      - Company token transactions
      operationId: retrieveCompanyTokenTransaction
      summary: Retrieve company token transaction
      description: |-
        Retrieves the details of an existing company token transaction.

        Required permissions:
         - `company_token_transaction:read`
         - `member:basic:read`
         - `company:basic:read`
      parameters:
      - name: id
        in: path
        required: true
        description: The unique identifier of the token transaction to retrieve.
        schema:
          type: string
          example: ctt_xxxxxxxxxxxxxx
      x-group-title: CRM
      security:
      - bearerAuth:
        - company_token_transaction:read
        - member:basic:read
        - company:basic:read
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/CompanyTokenTransaction"
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

          const companyTokenTransaction = await client.companyTokenTransactions.retrieve('ctt_xxxxxxxxxxxxxx');

          console.log(companyTokenTransaction.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          company_token_transaction = client.company_token_transactions.retrieve(
              "ctt_xxxxxxxxxxxxxx",
          )
          print(company_token_transaction.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          company_token_transaction = whop.company_token_transactions.retrieve("ctt_xxxxxxxxxxxxxx")

          puts(company_token_transaction)
components:
  schemas:
    CompanyTokenTransaction:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the company token transaction.
        transaction_type:
          "$ref": "#/components/schemas/CompanyTokenTransactionTypes"
          description: The direction of this token transaction (add, subtract, or
            transfer).
        amount:
          type: number
          description: The token amount for this transaction. Always a positive value
            regardless of transaction type.
          example: 6.9
        description:
          type:
          - string
          - 'null'
          description: Free-text description explaining the reason for this token
            transaction. Null if no description was provided.
          example: Reward for completing onboarding
        created_at:
          type: string
          format: date-time
          description: The datetime the company token transaction was created.
          example: '2023-12-01T05:00:00.401Z'
        linked_transaction_id:
          type:
          - string
          - 'null'
          description: The ID of the corresponding transaction on the other side of
            a transfer. Null if this is not a transfer transaction.
        idempotency_key:
          type:
          - string
          - 'null'
          description: A unique key used to prevent duplicate transactions when retrying
            API requests. Null if no idempotency key was provided.
          example: txn_reward_usr_123_2024
        user:
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
          description: The user whose token balance was affected by this transaction.
        member:
          type: object
          properties:
            id:
              type: string
              description: The unique identifier for the company member.
          required:
          - id
          description: The member whose token balance was affected by this transaction.
        company:
          type: object
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
          description: The company whose token balance this transaction affects.
      required:
      - id
      - transaction_type
      - amount
      - description
      - created_at
      - linked_transaction_id
      - idempotency_key
      - user
      - member
      - company
      description: A token transaction records a credit or debit to a member's token
        balance within a company, including transfers between members.
    CompanyTokenTransactionTypes:
      type: string
      enum:
      - add
      - subtract
      - transfer
      description: The type of token transaction
```
