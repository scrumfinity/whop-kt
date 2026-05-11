# POST /fee_markups — Create fee markup

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `POST`
- **Path:** `/fee_markups`
- **Operation ID:** `createFeeMarkup`
- **Tags:** `Fee markups`
- **Required bearer scopes:** `company:update_child_fees`

## Description

Create or update a fee markup for a company. If a markup for the specified fee type already exists, it will be updated with the new values.

Required permissions:
 - `company:update_child_fees`

## Parameters

_No path, query, header, or cookie parameters._

## Request body

Required: **yes**
Content types: `application/json`

### `application/json` request schema

```yaml
type: object
properties:
  company_id:
    type: string
    description: The unique identifier of the company to create or update the fee
      markup for.
    example: biz_xxxxxxxxxxxxxx
  fee_type:
    "$ref": "#/components/schemas/FeeMarkupTypes"
    description: The type of fee this markup applies to, such as processing or platform
      fees.
  fixed_fee_usd:
    type:
    - number
    - 'null'
    description: The fixed fee amount in USD to charge per transaction. Must be between
      0 and 50.
    example: 6.9
  metadata:
    type:
    - object
    - 'null'
    additionalProperties: true
    description: Custom key-value metadata to attach to this fee markup.
  notes:
    type:
    - string
    - 'null'
    description: Internal notes about this fee markup for record-keeping purposes.
  percentage_fee:
    type:
    - number
    - 'null'
    description: The percentage fee to charge per transaction. Must be between 0 and
      25.
    example: 6.9
required:
- company_id
- fee_type
description: Parameters for UpdateFeeMarkup
```


## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `FeeMarkup` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/FeeMarkup"
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

const feeMarkup = await client.feeMarkups.create({
  company_id: 'biz_xxxxxxxxxxxxxx',
  fee_type: 'crypto_withdrawal_markup',
});

console.log(feeMarkup.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
fee_markup = client.fee_markups.create(
    company_id="biz_xxxxxxxxxxxxxx",
    fee_type="crypto_withdrawal_markup",
)
print(fee_markup.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

fee_markup = whop.fee_markups.create(company_id: "biz_xxxxxxxxxxxxxx", fee_type: :crypto_withdrawal_markup)

puts(fee_markup)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  FeeMarkup:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the fee markup.
      fee_type:
        "$ref": "#/components/schemas/FeeMarkupTypes"
        description: The category of fee this markup applies to.
      percentage_fee:
        type:
        - number
        - 'null'
        description: A percentage-based fee charged per transaction. Ranges from 0
          to 25. Null if no percentage fee is configured.
        example: 6.9
      fixed_fee_usd:
        type:
        - number
        - 'null'
        description: A flat fee charged per transaction, in USD. Ranges from 0 to
          50. Null if no fixed fee is configured.
        example: 6.9
      notes:
        type:
        - string
        - 'null'
        description: Internal notes about this fee markup, visible only to administrators.
          Null if no notes have been added.
        example: Approved by finance team on 2024-01-15
      created_at:
        type: string
        format: date-time
        description: The datetime the fee markup was created.
        example: '2023-12-01T05:00:00.401Z'
      updated_at:
        type: string
        format: date-time
        description: The datetime the fee markup was last updated.
        example: '2023-12-01T05:00:00.401Z'
    required:
    - id
    - fee_type
    - percentage_fee
    - fixed_fee_usd
    - notes
    - created_at
    - updated_at
    description: A fee markup configuration that defines additional charges applied
      to transactions for a platform's connected accounts.
  FeeMarkupTypes:
    type: string
    enum:
    - crypto_withdrawal_markup
    - rtp_withdrawal_markup
    - next_day_bank_withdrawal_markup
    - bank_wire_withdrawal_markup
    - digital_wallet_withdrawal_markup
    description: The types of fee markups that can be configured
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
  "/fee_markups":
    post:
      tags:
      - Fee markups
      operationId: createFeeMarkup
      summary: Create fee markup
      description: |-
        Create or update a fee markup for a company. If a markup for the specified fee type already exists, it will be updated with the new values.

        Required permissions:
         - `company:update_child_fees`
      parameters: []
      x-group-title: Identity
      security:
      - bearerAuth:
        - company:update_child_fees
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/FeeMarkup"
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
                company_id:
                  type: string
                  description: The unique identifier of the company to create or update
                    the fee markup for.
                  example: biz_xxxxxxxxxxxxxx
                fee_type:
                  "$ref": "#/components/schemas/FeeMarkupTypes"
                  description: The type of fee this markup applies to, such as processing
                    or platform fees.
                fixed_fee_usd:
                  type:
                  - number
                  - 'null'
                  description: The fixed fee amount in USD to charge per transaction.
                    Must be between 0 and 50.
                  example: 6.9
                metadata:
                  type:
                  - object
                  - 'null'
                  additionalProperties: true
                  description: Custom key-value metadata to attach to this fee markup.
                notes:
                  type:
                  - string
                  - 'null'
                  description: Internal notes about this fee markup for record-keeping
                    purposes.
                percentage_fee:
                  type:
                  - number
                  - 'null'
                  description: The percentage fee to charge per transaction. Must
                    be between 0 and 25.
                  example: 6.9
              required:
              - company_id
              - fee_type
              description: Parameters for UpdateFeeMarkup
      x-codeSamples:
      - lang: JavaScript
        source: |-
          import Whop from '@whop/sdk';

          const client = new Whop({
            apiKey: process.env['WHOP_API_KEY'], // This is the default and can be omitted
          });

          const feeMarkup = await client.feeMarkups.create({
            company_id: 'biz_xxxxxxxxxxxxxx',
            fee_type: 'crypto_withdrawal_markup',
          });

          console.log(feeMarkup.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          fee_markup = client.fee_markups.create(
              company_id="biz_xxxxxxxxxxxxxx",
              fee_type="crypto_withdrawal_markup",
          )
          print(fee_markup.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          fee_markup = whop.fee_markups.create(company_id: "biz_xxxxxxxxxxxxxx", fee_type: :crypto_withdrawal_markup)

          puts(fee_markup)
components:
  schemas:
    FeeMarkup:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the fee markup.
        fee_type:
          "$ref": "#/components/schemas/FeeMarkupTypes"
          description: The category of fee this markup applies to.
        percentage_fee:
          type:
          - number
          - 'null'
          description: A percentage-based fee charged per transaction. Ranges from
            0 to 25. Null if no percentage fee is configured.
          example: 6.9
        fixed_fee_usd:
          type:
          - number
          - 'null'
          description: A flat fee charged per transaction, in USD. Ranges from 0 to
            50. Null if no fixed fee is configured.
          example: 6.9
        notes:
          type:
          - string
          - 'null'
          description: Internal notes about this fee markup, visible only to administrators.
            Null if no notes have been added.
          example: Approved by finance team on 2024-01-15
        created_at:
          type: string
          format: date-time
          description: The datetime the fee markup was created.
          example: '2023-12-01T05:00:00.401Z'
        updated_at:
          type: string
          format: date-time
          description: The datetime the fee markup was last updated.
          example: '2023-12-01T05:00:00.401Z'
      required:
      - id
      - fee_type
      - percentage_fee
      - fixed_fee_usd
      - notes
      - created_at
      - updated_at
      description: A fee markup configuration that defines additional charges applied
        to transactions for a platform's connected accounts.
    FeeMarkupTypes:
      type: string
      enum:
      - crypto_withdrawal_markup
      - rtp_withdrawal_markup
      - next_day_bank_withdrawal_markup
      - bank_wire_withdrawal_markup
      - digital_wallet_withdrawal_markup
      description: The types of fee markups that can be configured
```
