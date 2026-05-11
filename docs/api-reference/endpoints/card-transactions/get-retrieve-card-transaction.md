# GET /card_transactions/{id} — Retrieve card transaction

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/card_transactions/{id}`
- **Operation ID:** `retrieveCardTransaction`
- **Tags:** `Card transactions`
- **Required bearer scopes:** `payout:account:read`

## Description

Retrieves a single card transaction by ID.

Required permissions:
 - `payout:account:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `id` | path | yes | `string` | The card transaction ID. | citx_xxxxxxxxxxxxx |

## Request body

_No request body._

## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `CardTransaction` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/CardTransaction"
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

_No SDK examples provided in the OpenAPI source._

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  CardIssuingTransactionStatus:
    type: string
    enum:
    - pending
    - completed
    - reversed
    - declined
    description: The lifecycle status of a card transaction.
  CardTransaction:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the card transaction.
        example: citx_xxxxxxxxxxxxx
      usd_amount:
        type:
        - number
        - 'null'
        description: The transaction amount in USD.
        example: 6.9
      authorization_method:
        type:
        - string
        - 'null'
        description: How the card was presented or authenticated for the purchase.
        example: ecommerce
      created_at:
        type: string
        format: date-time
        description: The datetime the card transaction was created.
        example: '2023-12-01T05:00:00.401Z'
      card_id:
        type: string
        description: Represents a unique identifier that is Base64 obfuscated. It
          is often used to refetch an object or as key for a cache. The ID type appears
          in a JSON response as a String; however, it is not intended to be human-readable.
          When expected as an input type, any string (such as `"VXNlci0xMA=="`) or
          integer (such as `4`) input value will be accepted as an ID.
      cashback_usd_amount:
        type:
        - number
        - 'null'
        description: The cashback reward amount earned on this transaction, in USD.
        example: 6.9
      currency:
        type:
        - string
        - 'null'
        description: The ISO 4217 currency code for the transaction amount.
        example: USD
      declined_reason:
        type:
        - string
        - 'null'
        description: The issuer-provided reason the transaction was declined.
        example: insufficient_funds
      international:
        type: boolean
        description: Whether the transaction was made with a merchant outside the
          card's home country.
      local_amount:
        type:
        - number
        - 'null'
        description: The transaction amount in the merchant's local currency before
          conversion.
        example: 6.9
      memo:
        type:
        - string
        - 'null'
        description: A user-provided note attached to the transaction.
        example: Team dinner
      merchant_category:
        type:
        - string
        - 'null'
        description: The enriched or raw category label for the merchant.
        example: Software
      merchant_category_code:
        type:
        - string
        - 'null'
        description: The four-digit ISO 18245 merchant category code (MCC).
        example: '5734'
      merchant_icon_url:
        type:
        - string
        - 'null'
        description: A URL to the enriched merchant logo image.
        example: https://logo.clearbit.com/example.com
      merchant_name:
        type:
        - string
        - 'null'
        description: The enriched or raw name of the merchant where the purchase was
          made.
        example: Acme Corporation
      posted_at:
        type:
        - string
        - 'null'
        format: date-time
        description: When the transaction was settled by the card network.
        example: '2023-12-01T05:00:00.401Z'
      status:
        "$ref": "#/components/schemas/CardIssuingTransactionStatus"
        description: The current lifecycle status of the transaction.
      transaction_type:
        type: string
        description: The type of transaction.
        example: spend
    required:
    - id
    - usd_amount
    - authorization_method
    - created_at
    - card_id
    - cashback_usd_amount
    - currency
    - declined_reason
    - international
    - local_amount
    - memo
    - merchant_category
    - merchant_category_code
    - merchant_icon_url
    - merchant_name
    - posted_at
    - status
    - transaction_type
    description: A card transaction record.
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
  "/card_transactions/{id}":
    get:
      tags:
      - Card transactions
      operationId: retrieveCardTransaction
      summary: Retrieve card transaction
      description: |-
        Retrieves a single card transaction by ID.

        Required permissions:
         - `payout:account:read`
      parameters:
      - name: id
        in: path
        required: true
        description: The card transaction ID.
        schema:
          type: string
          example: citx_xxxxxxxxxxxxx
      x-group-title: Cards
      security:
      - bearerAuth:
        - payout:account:read
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/CardTransaction"
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
components:
  schemas:
    CardIssuingTransactionStatus:
      type: string
      enum:
      - pending
      - completed
      - reversed
      - declined
      description: The lifecycle status of a card transaction.
    CardTransaction:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the card transaction.
          example: citx_xxxxxxxxxxxxx
        usd_amount:
          type:
          - number
          - 'null'
          description: The transaction amount in USD.
          example: 6.9
        authorization_method:
          type:
          - string
          - 'null'
          description: How the card was presented or authenticated for the purchase.
          example: ecommerce
        created_at:
          type: string
          format: date-time
          description: The datetime the card transaction was created.
          example: '2023-12-01T05:00:00.401Z'
        card_id:
          type: string
          description: Represents a unique identifier that is Base64 obfuscated. It
            is often used to refetch an object or as key for a cache. The ID type
            appears in a JSON response as a String; however, it is not intended to
            be human-readable. When expected as an input type, any string (such as
            `"VXNlci0xMA=="`) or integer (such as `4`) input value will be accepted
            as an ID.
        cashback_usd_amount:
          type:
          - number
          - 'null'
          description: The cashback reward amount earned on this transaction, in USD.
          example: 6.9
        currency:
          type:
          - string
          - 'null'
          description: The ISO 4217 currency code for the transaction amount.
          example: USD
        declined_reason:
          type:
          - string
          - 'null'
          description: The issuer-provided reason the transaction was declined.
          example: insufficient_funds
        international:
          type: boolean
          description: Whether the transaction was made with a merchant outside the
            card's home country.
        local_amount:
          type:
          - number
          - 'null'
          description: The transaction amount in the merchant's local currency before
            conversion.
          example: 6.9
        memo:
          type:
          - string
          - 'null'
          description: A user-provided note attached to the transaction.
          example: Team dinner
        merchant_category:
          type:
          - string
          - 'null'
          description: The enriched or raw category label for the merchant.
          example: Software
        merchant_category_code:
          type:
          - string
          - 'null'
          description: The four-digit ISO 18245 merchant category code (MCC).
          example: '5734'
        merchant_icon_url:
          type:
          - string
          - 'null'
          description: A URL to the enriched merchant logo image.
          example: https://logo.clearbit.com/example.com
        merchant_name:
          type:
          - string
          - 'null'
          description: The enriched or raw name of the merchant where the purchase
            was made.
          example: Acme Corporation
        posted_at:
          type:
          - string
          - 'null'
          format: date-time
          description: When the transaction was settled by the card network.
          example: '2023-12-01T05:00:00.401Z'
        status:
          "$ref": "#/components/schemas/CardIssuingTransactionStatus"
          description: The current lifecycle status of the transaction.
        transaction_type:
          type: string
          description: The type of transaction.
          example: spend
      required:
      - id
      - usd_amount
      - authorization_method
      - created_at
      - card_id
      - cashback_usd_amount
      - currency
      - declined_reason
      - international
      - local_amount
      - memo
      - merchant_category
      - merchant_category_code
      - merchant_icon_url
      - merchant_name
      - posted_at
      - status
      - transaction_type
      description: A card transaction record.
```
