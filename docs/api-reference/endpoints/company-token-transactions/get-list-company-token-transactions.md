# GET /company_token_transactions — List company token transactions

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/company_token_transactions`
- **Operation ID:** `listCompanyTokenTransaction`
- **Tags:** `Company token transactions`
- **Required bearer scopes:** `company_token_transaction:read`, `member:basic:read`, `company:basic:read`

## Description

Returns a paginated list of token transactions for a user or company, depending on the authenticated actor, with optional filtering by user and transaction type.

Required permissions:
 - `company_token_transaction:read`
 - `member:basic:read`
 - `company:basic:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `after` | query | no | `string \| null` | Returns the elements in the list that come after the specified cursor. |  |
| `before` | query | no | `string \| null` | Returns the elements in the list that come before the specified cursor. |  |
| `first` | query | no | `integer \| null` | Returns the first _n_ elements from the list. | 42 |
| `last` | query | no | `integer \| null` | Returns the last _n_ elements from the list. | 42 |
| `company_id` | query | yes | `string` | The unique identifier of the company to list token transactions for. | biz_xxxxxxxxxxxxxx |
| `user_id` | query | no | `string \| null` | Filter transactions to only those involving this specific user. | user_xxxxxxxxxxxxx |
| `transaction_type` | query | no | `CompanyTokenTransactionTypes \| null` | Filter transactions by type. |  |

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
      "$ref": "#/components/schemas/CompanyTokenTransactionListItem"
    description: A list of nodes.
  page_info:
    "$ref": "#/components/schemas/PageInfo"
    description: Information to aid in pagination.
required:
- data
- page_info
description: The connection type for CompanyTokenTransaction.
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
for await (const companyTokenTransactionListResponse of client.companyTokenTransactions.list({
  company_id: 'biz_xxxxxxxxxxxxxx',
})) {
  console.log(companyTokenTransactionListResponse.id);
}
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
page = client.company_token_transactions.list(
    company_id="biz_xxxxxxxxxxxxxx",
)
page = page.data[0]
print(page.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

page = whop.company_token_transactions.list(company_id: "biz_xxxxxxxxxxxxxx")

puts(page)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  CompanyTokenTransactionListItem:
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
  "/company_token_transactions":
    get:
      tags:
      - Company token transactions
      operationId: listCompanyTokenTransaction
      summary: List company token transactions
      description: |-
        Returns a paginated list of token transactions for a user or company, depending on the authenticated actor, with optional filtering by user and transaction type.

        Required permissions:
         - `company_token_transaction:read`
         - `member:basic:read`
         - `company:basic:read`
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
      - name: company_id
        in: query
        required: true
        schema:
          type: string
          description: The unique identifier of the company to list token transactions
            for.
          example: biz_xxxxxxxxxxxxxx
        explode: true
        style: form
      - name: user_id
        in: query
        required: false
        schema:
          type:
          - string
          - 'null'
          description: Filter transactions to only those involving this specific user.
          example: user_xxxxxxxxxxxxx
        explode: true
        style: form
      - name: transaction_type
        in: query
        required: false
        schema:
          oneOf:
          - "$ref": "#/components/schemas/CompanyTokenTransactionTypes"
          - type: 'null'
          description: Filter transactions by type.
        explode: true
        style: form
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
                type: object
                properties:
                  data:
                    type: array
                    items:
                      "$ref": "#/components/schemas/CompanyTokenTransactionListItem"
                    description: A list of nodes.
                  page_info:
                    "$ref": "#/components/schemas/PageInfo"
                    description: Information to aid in pagination.
                required:
                - data
                - page_info
                description: The connection type for CompanyTokenTransaction.
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
          for await (const companyTokenTransactionListResponse of client.companyTokenTransactions.list({
            company_id: 'biz_xxxxxxxxxxxxxx',
          })) {
            console.log(companyTokenTransactionListResponse.id);
          }
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          page = client.company_token_transactions.list(
              company_id="biz_xxxxxxxxxxxxxx",
          )
          page = page.data[0]
          print(page.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          page = whop.company_token_transactions.list(company_id: "biz_xxxxxxxxxxxxxx")

          puts(page)
components:
  schemas:
    CompanyTokenTransactionListItem:
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
```
