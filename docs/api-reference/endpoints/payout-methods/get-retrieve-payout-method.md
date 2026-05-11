# GET /payout_methods/{id} — Retrieve payout method

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/payout_methods/{id}`
- **Operation ID:** `retrievePayoutMethod`
- **Tags:** `Payout methods`
- **Required bearer scopes:** `payout:destination:read`

## Description

Retrieves the details of an existing payout method.

Required permissions:
 - `payout:destination:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `id` | path | yes | `string` | The unique identifier of the payout method to retrieve. | potk_xxxxxxxxxxxxx |

## Request body

_No request body._

## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `PayoutMethod` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/PayoutMethod"
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

const payoutMethod = await client.payoutMethods.retrieve('potk_xxxxxxxxxxxxx');

console.log(payoutMethod.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
payout_method = client.payout_methods.retrieve(
    "potk_xxxxxxxxxxxxx",
)
print(payout_method.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

payout_method = whop.payout_methods.retrieve("potk_xxxxxxxxxxxxx")

puts(payout_method)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  PayoutDestinationCategory:
    type: string
    enum:
    - crypto
    - rtp
    - next_day_bank
    - bank_wire
    - digital_wallet
    - unknown
    description: The category of a payout destination.
  PayoutMethod:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the payout token.
        example: potk_xxxxxxxxxxxxx
      created_at:
        type: string
        format: date-time
        description: The datetime the payout token was created.
        example: '2023-12-01T05:00:00.401Z'
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
        description: The company associated with this payout destination. Null if
          not linked to a specific company.
      nickname:
        type:
        - string
        - 'null'
        description: A user-defined label to help identify this payout destination.
          Not sent to the provider. Null if no nickname has been set.
        example: My Business Account
      currency:
        type: string
        description: The three-letter ISO currency code that payouts are delivered
          in for this destination.
        example: USD
      is_default:
        type: boolean
        description: Whether this is the default payout destination for the associated
          payout account.
      account_reference:
        type:
        - string
        - 'null'
        description: A masked identifier for the payout destination, such as the last
          four digits of a bank account or an email address. Null if no reference
          is available.
        example: "****1234"
      institution_name:
        type:
        - string
        - 'null'
        description: The name of the bank or financial institution receiving payouts.
          Null if not applicable or not provided.
        example: Chase Bank
      destination:
        type:
        - object
        - 'null'
        properties:
          country_code:
            type: string
            description: The country code of the payout destination
          category:
            "$ref": "#/components/schemas/PayoutDestinationCategory"
            description: The category of the payout destination
          name:
            type: string
            description: The name of the payer associated with the payout destination
        required:
        - country_code
        - category
        - name
        description: The payout destination configuration linked to this token. Null
          if not yet configured.
    required:
    - id
    - created_at
    - company
    - nickname
    - currency
    - is_default
    - account_reference
    - institution_name
    - destination
    description: A configured payout destination where a user receives earned funds,
      such as a bank account or digital wallet.
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
  "/payout_methods/{id}":
    get:
      tags:
      - Payout methods
      operationId: retrievePayoutMethod
      summary: Retrieve payout method
      description: |-
        Retrieves the details of an existing payout method.

        Required permissions:
         - `payout:destination:read`
      parameters:
      - name: id
        in: path
        required: true
        description: The unique identifier of the payout method to retrieve.
        schema:
          type: string
          example: potk_xxxxxxxxxxxxx
      x-group-title: Payouts
      security:
      - bearerAuth:
        - payout:destination:read
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/PayoutMethod"
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

          const payoutMethod = await client.payoutMethods.retrieve('potk_xxxxxxxxxxxxx');

          console.log(payoutMethod.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          payout_method = client.payout_methods.retrieve(
              "potk_xxxxxxxxxxxxx",
          )
          print(payout_method.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          payout_method = whop.payout_methods.retrieve("potk_xxxxxxxxxxxxx")

          puts(payout_method)
components:
  schemas:
    PayoutDestinationCategory:
      type: string
      enum:
      - crypto
      - rtp
      - next_day_bank
      - bank_wire
      - digital_wallet
      - unknown
      description: The category of a payout destination.
    PayoutMethod:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the payout token.
          example: potk_xxxxxxxxxxxxx
        created_at:
          type: string
          format: date-time
          description: The datetime the payout token was created.
          example: '2023-12-01T05:00:00.401Z'
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
          description: The company associated with this payout destination. Null if
            not linked to a specific company.
        nickname:
          type:
          - string
          - 'null'
          description: A user-defined label to help identify this payout destination.
            Not sent to the provider. Null if no nickname has been set.
          example: My Business Account
        currency:
          type: string
          description: The three-letter ISO currency code that payouts are delivered
            in for this destination.
          example: USD
        is_default:
          type: boolean
          description: Whether this is the default payout destination for the associated
            payout account.
        account_reference:
          type:
          - string
          - 'null'
          description: A masked identifier for the payout destination, such as the
            last four digits of a bank account or an email address. Null if no reference
            is available.
          example: "****1234"
        institution_name:
          type:
          - string
          - 'null'
          description: The name of the bank or financial institution receiving payouts.
            Null if not applicable or not provided.
          example: Chase Bank
        destination:
          type:
          - object
          - 'null'
          properties:
            country_code:
              type: string
              description: The country code of the payout destination
            category:
              "$ref": "#/components/schemas/PayoutDestinationCategory"
              description: The category of the payout destination
            name:
              type: string
              description: The name of the payer associated with the payout destination
          required:
          - country_code
          - category
          - name
          description: The payout destination configuration linked to this token.
            Null if not yet configured.
      required:
      - id
      - created_at
      - company
      - nickname
      - currency
      - is_default
      - account_reference
      - institution_name
      - destination
      description: A configured payout destination where a user receives earned funds,
        such as a bank account or digital wallet.
```
