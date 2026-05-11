# GET /invoices — List invoices

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/invoices`
- **Operation ID:** `listInvoice`
- **Tags:** `Invoices`
- **Required bearer scopes:** `invoice:basic:read`

## Description

Returns a paginated list of invoices for a company, with optional filtering by product, status, collection method, and creation date.

Required permissions:
 - `invoice:basic:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `after` | query | no | `string \| null` | Returns the elements in the list that come after the specified cursor. |  |
| `before` | query | no | `string \| null` | Returns the elements in the list that come before the specified cursor. |  |
| `first` | query | no | `integer \| null` | Returns the first _n_ elements from the list. | 42 |
| `last` | query | no | `integer \| null` | Returns the last _n_ elements from the list. | 42 |
| `company_id` | query | no | `string \| null` | The unique identifier of the company to list invoices for. | biz_xxxxxxxxxxxxxx |
| `direction` | query | no | `Direction \| null` | The sort direction for ordering results, either ascending or descending. |  |
| `product_ids` | query | no | `array<string>` | Filter invoices to only those associated with these specific product identifiers. |  |
| `collection_methods` | query | no | `array<InvoiceCollectionMethods>` | Filter invoices by their collection method. |  |
| `statuses` | query | no | `array<InvoiceStatuses>` | Filter invoices by their current status. |  |
| `order` | query | no | `InvoicesSortableColumns \| null` | The field to order results by, such as creation date or due date. |  |
| `created_before` | query | no | `string \| null / date-time` | Only return invoices created before this timestamp. | 2023-12-01T05:00:00.401Z |
| `created_after` | query | no | `string \| null / date-time` | Only return invoices created after this timestamp. | 2023-12-01T05:00:00.401Z |

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
      "$ref": "#/components/schemas/InvoiceListItem"
    description: A list of nodes.
  page_info:
    "$ref": "#/components/schemas/PageInfo"
    description: Information to aid in pagination.
required:
- data
- page_info
description: The connection type for PublicInvoice.
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
for await (const invoiceListItem of client.invoices.list()) {
  console.log(invoiceListItem.id);
}
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
page = client.invoices.list()
page = page.data[0]
print(page.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

page = whop.invoices.list

puts(page)
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
  Direction:
    type: string
    enum:
    - asc
    - desc
    description: The direction of the sort.
  InvoiceCollectionMethods:
    type: string
    enum:
    - send_invoice
    - charge_automatically
    description: The method of collection for an invoice.
  InvoiceListItem:
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
  InvoicesSortableColumns:
    type: string
    enum:
    - id
    - created_at
    - due_date
    description: Which columns can be used to sort.
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
  "/invoices":
    get:
      tags:
      - Invoices
      operationId: listInvoice
      summary: List invoices
      description: |-
        Returns a paginated list of invoices for a company, with optional filtering by product, status, collection method, and creation date.

        Required permissions:
         - `invoice:basic:read`
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
        required: false
        schema:
          type:
          - string
          - 'null'
          description: The unique identifier of the company to list invoices for.
          example: biz_xxxxxxxxxxxxxx
        explode: true
        style: form
      - name: direction
        in: query
        required: false
        schema:
          oneOf:
          - "$ref": "#/components/schemas/Direction"
          - type: 'null'
          description: The sort direction for ordering results, either ascending or
            descending.
        explode: true
        style: form
      - name: product_ids
        in: query
        required: false
        schema:
          type:
          - array
          - 'null'
          items:
            type: string
            description: Represents a unique identifier that is Base64 obfuscated.
              It is often used to refetch an object or as key for a cache. The ID
              type appears in a JSON response as a String; however, it is not intended
              to be human-readable. When expected as an input type, any string (such
              as `"VXNlci0xMA=="`) or integer (such as `4`) input value will be accepted
              as an ID.
          description: Filter invoices to only those associated with these specific
            product identifiers.
        explode: true
        style: form
      - name: collection_methods
        in: query
        required: false
        schema:
          type:
          - array
          - 'null'
          items:
            "$ref": "#/components/schemas/InvoiceCollectionMethods"
          description: Filter invoices by their collection method.
        explode: true
        style: form
      - name: statuses
        in: query
        required: false
        schema:
          type:
          - array
          - 'null'
          items:
            "$ref": "#/components/schemas/InvoiceStatuses"
          description: Filter invoices by their current status.
        explode: true
        style: form
      - name: order
        in: query
        required: false
        schema:
          oneOf:
          - "$ref": "#/components/schemas/InvoicesSortableColumns"
          - type: 'null'
          description: The field to order results by, such as creation date or due
            date.
        explode: true
        style: form
      - name: created_before
        in: query
        required: false
        schema:
          type:
          - string
          - 'null'
          format: date-time
          description: Only return invoices created before this timestamp.
          example: '2023-12-01T05:00:00.401Z'
        explode: true
        style: form
      - name: created_after
        in: query
        required: false
        schema:
          type:
          - string
          - 'null'
          format: date-time
          description: Only return invoices created after this timestamp.
          example: '2023-12-01T05:00:00.401Z'
        explode: true
        style: form
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
                type: object
                properties:
                  data:
                    type: array
                    items:
                      "$ref": "#/components/schemas/InvoiceListItem"
                    description: A list of nodes.
                  page_info:
                    "$ref": "#/components/schemas/PageInfo"
                    description: Information to aid in pagination.
                required:
                - data
                - page_info
                description: The connection type for PublicInvoice.
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
          for await (const invoiceListItem of client.invoices.list()) {
            console.log(invoiceListItem.id);
          }
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          page = client.invoices.list()
          page = page.data[0]
          print(page.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          page = whop.invoices.list

          puts(page)
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
    Direction:
      type: string
      enum:
      - asc
      - desc
      description: The direction of the sort.
    InvoiceCollectionMethods:
      type: string
      enum:
      - send_invoice
      - charge_automatically
      description: The method of collection for an invoice.
    InvoiceListItem:
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
    InvoicesSortableColumns:
      type: string
      enum:
      - id
      - created_at
      - due_date
      description: Which columns can be used to sort.
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
