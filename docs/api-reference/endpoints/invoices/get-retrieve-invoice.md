# GET /invoices/{id} — Retrieve invoice

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/invoices/{id}`
- **Operation ID:** `retrieveInvoice`
- **Tags:** `Invoices`
- **Required bearer scopes:** `invoice:basic:read`

## Description

Retrieves the details of an existing invoice.

Required permissions:
 - `invoice:basic:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `id` | path | yes | `string` | The unique identifier of the invoice, or a secure token. | inv_xxxxxxxxxxxxxx |

## Request body

_No request body._

## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `Invoice` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/Invoice"
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

const invoice = await client.invoices.retrieve('inv_xxxxxxxxxxxxxx');

console.log(invoice.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
invoice = client.invoices.retrieve(
    "inv_xxxxxxxxxxxxxx",
)
print(invoice.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

invoice = whop.invoices.retrieve("inv_xxxxxxxxxxxxxx")

puts(invoice)
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
  Invoice:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the invoice.
        example: inv_xxxxxxxxxxxxxx
      created_at:
        type: string
        format: date-time
        description: The datetime the invoice was created.
        example: '2023-12-01T05:00:00.401Z'
      status:
        "$ref": "#/components/schemas/InvoiceStatuses"
        description: The current payment status of the invoice, such as draft, open,
          paid, or void.
      number:
        type: string
        description: The sequential invoice number for display purposes.
        example: "#0001"
      due_date:
        type:
        - string
        - 'null'
        format: date-time
        description: The deadline by which payment is expected. Null if the invoice
          is collected automatically.
        example: '2023-12-01T05:00:00.401Z'
      email_address:
        type:
        - string
        - 'null'
        description: The email address of the customer this invoice is addressed to.
          Null if no email is on file.
        example: customer@example.com
      fetch_invoice_token:
        type: string
        description: A signed token that allows fetching invoice data publicly without
          authentication.
        example: eyJhbGciOiJIUzI1NiJ9...
      current_plan:
        type: object
        properties:
          id:
            type: string
            description: The unique identifier for the plan.
            example: plan_xxxxxxxxxxxxx
          formatted_price:
            type: string
            description: The formatted price (including currency) for the plan.
            example: "$10.00"
          currency:
            "$ref": "#/components/schemas/Currencies"
            description: The currency used for all prices on this plan (e.g., 'usd',
              'eur'). All monetary amounts on the plan are denominated in this currency.
        required:
        - id
        - formatted_price
        - currency
        description: The plan that this invoice charges for.
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
        required:
        - id
        - name
        - username
        description: The user this invoice is addressed to. Null if the user account
          has been removed.
    required:
    - id
    - created_at
    - status
    - number
    - due_date
    - email_address
    - fetch_invoice_token
    - current_plan
    - user
    description: An invoice represents an itemized bill sent by a company to a customer
      for a specific product and plan, tracking the amount owed, due date, and payment
      status.
  InvoiceStatuses:
    type: string
    enum:
    - draft
    - open
    - paid
    - past_due
    - uncollectible
    - void
    description: The different statuses an invoice can be in
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
  "/invoices/{id}":
    get:
      tags:
      - Invoices
      operationId: retrieveInvoice
      summary: Retrieve invoice
      description: |-
        Retrieves the details of an existing invoice.

        Required permissions:
         - `invoice:basic:read`
      parameters:
      - name: id
        in: path
        required: true
        description: The unique identifier of the invoice, or a secure token.
        schema:
          type: string
          example: inv_xxxxxxxxxxxxxx
      x-group-title: Payins
      security:
      - bearerAuth:
        - invoice:basic:read
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/Invoice"
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

          const invoice = await client.invoices.retrieve('inv_xxxxxxxxxxxxxx');

          console.log(invoice.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          invoice = client.invoices.retrieve(
              "inv_xxxxxxxxxxxxxx",
          )
          print(invoice.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          invoice = whop.invoices.retrieve("inv_xxxxxxxxxxxxxx")

          puts(invoice)
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
    Invoice:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the invoice.
          example: inv_xxxxxxxxxxxxxx
        created_at:
          type: string
          format: date-time
          description: The datetime the invoice was created.
          example: '2023-12-01T05:00:00.401Z'
        status:
          "$ref": "#/components/schemas/InvoiceStatuses"
          description: The current payment status of the invoice, such as draft, open,
            paid, or void.
        number:
          type: string
          description: The sequential invoice number for display purposes.
          example: "#0001"
        due_date:
          type:
          - string
          - 'null'
          format: date-time
          description: The deadline by which payment is expected. Null if the invoice
            is collected automatically.
          example: '2023-12-01T05:00:00.401Z'
        email_address:
          type:
          - string
          - 'null'
          description: The email address of the customer this invoice is addressed
            to. Null if no email is on file.
          example: customer@example.com
        fetch_invoice_token:
          type: string
          description: A signed token that allows fetching invoice data publicly without
            authentication.
          example: eyJhbGciOiJIUzI1NiJ9...
        current_plan:
          type: object
          properties:
            id:
              type: string
              description: The unique identifier for the plan.
              example: plan_xxxxxxxxxxxxx
            formatted_price:
              type: string
              description: The formatted price (including currency) for the plan.
              example: "$10.00"
            currency:
              "$ref": "#/components/schemas/Currencies"
              description: The currency used for all prices on this plan (e.g., 'usd',
                'eur'). All monetary amounts on the plan are denominated in this currency.
          required:
          - id
          - formatted_price
          - currency
          description: The plan that this invoice charges for.
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
          required:
          - id
          - name
          - username
          description: The user this invoice is addressed to. Null if the user account
            has been removed.
      required:
      - id
      - created_at
      - status
      - number
      - due_date
      - email_address
      - fetch_invoice_token
      - current_plan
      - user
      description: An invoice represents an itemized bill sent by a company to a customer
        for a specific product and plan, tracking the amount owed, due date, and payment
        status.
    InvoiceStatuses:
      type: string
      enum:
      - draft
      - open
      - paid
      - past_due
      - uncollectible
      - void
      description: The different statuses an invoice can be in
```
