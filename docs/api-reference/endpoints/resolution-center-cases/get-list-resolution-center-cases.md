# GET /resolution_center_cases — List resolution center cases

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/resolution_center_cases`
- **Operation ID:** `listResolutionCenterCase`
- **Tags:** `Resolution center cases`
- **Required bearer scopes:** `payment:resolution_center_case:read`

## Description

Returns a paginated list of resolution center cases, with optional filtering by company, status, and creation date.

Required permissions:
 - `payment:resolution_center_case:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `after` | query | no | `string \| null` | Returns the elements in the list that come after the specified cursor. |  |
| `before` | query | no | `string \| null` | Returns the elements in the list that come before the specified cursor. |  |
| `first` | query | no | `integer \| null` | Returns the first _n_ elements from the list. | 42 |
| `last` | query | no | `integer \| null` | Returns the last _n_ elements from the list. | 42 |
| `company_id` | query | no | `string \| null` | The unique identifier of the company to list resolution center cases for. | biz_xxxxxxxxxxxxxx |
| `direction` | query | no | `Direction \| null` | The sort direction. |  |
| `statuses` | query | no | `array<ResolutionCenterCaseStatuses>` | Filter by resolution center case status. |  |
| `created_before` | query | no | `string \| null / date-time` | Only return cases created before this timestamp. | 2023-12-01T05:00:00.401Z |
| `created_after` | query | no | `string \| null / date-time` | Only return cases created after this timestamp. | 2023-12-01T05:00:00.401Z |

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
      "$ref": "#/components/schemas/ResolutionCenterCaseListItem"
    description: A list of nodes.
  page_info:
    "$ref": "#/components/schemas/PageInfo"
    description: Information to aid in pagination.
required:
- data
- page_info
description: The connection type for PublicResolution.
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
for await (const resolutionCenterCaseListResponse of client.resolutionCenterCases.list()) {
  console.log(resolutionCenterCaseListResponse.id);
}
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
page = client.resolution_center_cases.list()
page = page.data[0]
print(page.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

page = whop.resolution_center_cases.list

puts(page)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  Direction:
    type: string
    enum:
    - asc
    - desc
    description: The direction of the sort.
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
  ResolutionCenterCaseCustomerResponses:
    type: string
    enum:
    - respond
    - appeal
    - withdraw
    description: The types of responses a customer can make to a resolution.
  ResolutionCenterCaseIssueTypes:
    type: string
    enum:
    - forgot_to_cancel
    - item_not_received
    - significantly_not_as_described
    - unauthorized_transaction
    - product_unacceptable
    description: The different types of issues a resolution can be
  ResolutionCenterCaseListItem:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the resolution.
        example: reso_xxxxxxxxxxxxx
      status:
        "$ref": "#/components/schemas/ResolutionCenterCaseStatuses"
        description: The current status of the resolution case, indicating which party
          needs to respond or if the case is closed.
      issue:
        "$ref": "#/components/schemas/ResolutionCenterCaseIssueTypes"
        description: The category of the dispute.
      created_at:
        type: string
        format: date-time
        description: The datetime the resolution was created.
        example: '2023-12-01T05:00:00.401Z'
      updated_at:
        type: string
        format: date-time
        description: The datetime the resolution was last updated.
        example: '2023-12-01T05:00:00.401Z'
      due_date:
        type:
        - string
        - 'null'
        format: date-time
        description: The deadline by which the next response is required. Null if
          no deadline is currently active. As an ISO 8601 timestamp.
        example: '2023-12-01T05:00:00.401Z'
      customer_appealed:
        type: boolean
        description: Whether the customer has filed an appeal after the initial resolution
          decision.
      merchant_appealed:
        type: boolean
        description: Whether the merchant has filed an appeal after the initial resolution
          decision.
      customer_response_actions:
        type: array
        items:
          "$ref": "#/components/schemas/ResolutionCenterCaseCustomerResponses"
        description: The list of actions currently available to the customer.
      merchant_response_actions:
        type: array
        items:
          "$ref": "#/components/schemas/ResolutionCenterCaseMerchantResponses"
        description: The list of actions currently available to the merchant.
      company:
        type:
        - object
        - 'null'
        properties:
          id:
            type: string
            description: The unique identifier for the company.
            example: biz_xxxxxxxxxxxxxx
          title:
            type: string
            description: The display name of the company shown to customers.
            example: Pickaxe
        required:
        - id
        - title
        description: The company involved in this resolution case. Null if the company
          no longer exists.
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
        description: The customer (buyer) who filed this resolution case.
      payment:
        type: object
        properties:
          id:
            type: string
            description: The unique identifier for the payment.
            example: pay_xxxxxxxxxxxxxx
        required:
        - id
        description: The payment record that is the subject of this resolution case.
    required:
    - id
    - status
    - issue
    - created_at
    - updated_at
    - due_date
    - customer_appealed
    - merchant_appealed
    - customer_response_actions
    - merchant_response_actions
    - company
    - user
    - payment
    description: A resolution center case is a dispute or support case between a user
      and a company, tracking the issue, status, and outcome.
  ResolutionCenterCaseMerchantResponses:
    type: string
    enum:
    - accept
    - deny
    - request_more_info
    - appeal
    - respond
    description: The types of responses a merchant can make to a resolution.
  ResolutionCenterCaseStatuses:
    type: string
    enum:
    - merchant_response_needed
    - customer_response_needed
    - merchant_info_needed
    - customer_info_needed
    - under_platform_review
    - customer_won
    - merchant_won
    - customer_withdrew
    description: The statuses a resolution object can have
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
  "/resolution_center_cases":
    get:
      tags:
      - Resolution center cases
      operationId: listResolutionCenterCase
      summary: List resolution center cases
      description: |-
        Returns a paginated list of resolution center cases, with optional filtering by company, status, and creation date.

        Required permissions:
         - `payment:resolution_center_case:read`
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
          description: The unique identifier of the company to list resolution center
            cases for.
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
          description: The sort direction.
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
            "$ref": "#/components/schemas/ResolutionCenterCaseStatuses"
          description: Filter by resolution center case status.
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
          description: Only return cases created before this timestamp.
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
          description: Only return cases created after this timestamp.
          example: '2023-12-01T05:00:00.401Z'
        explode: true
        style: form
      x-group-title: Payins
      security:
      - bearerAuth:
        - payment:resolution_center_case:read
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
                      "$ref": "#/components/schemas/ResolutionCenterCaseListItem"
                    description: A list of nodes.
                  page_info:
                    "$ref": "#/components/schemas/PageInfo"
                    description: Information to aid in pagination.
                required:
                - data
                - page_info
                description: The connection type for PublicResolution.
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
          for await (const resolutionCenterCaseListResponse of client.resolutionCenterCases.list()) {
            console.log(resolutionCenterCaseListResponse.id);
          }
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          page = client.resolution_center_cases.list()
          page = page.data[0]
          print(page.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          page = whop.resolution_center_cases.list

          puts(page)
components:
  schemas:
    Direction:
      type: string
      enum:
      - asc
      - desc
      description: The direction of the sort.
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
    ResolutionCenterCaseCustomerResponses:
      type: string
      enum:
      - respond
      - appeal
      - withdraw
      description: The types of responses a customer can make to a resolution.
    ResolutionCenterCaseIssueTypes:
      type: string
      enum:
      - forgot_to_cancel
      - item_not_received
      - significantly_not_as_described
      - unauthorized_transaction
      - product_unacceptable
      description: The different types of issues a resolution can be
    ResolutionCenterCaseListItem:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the resolution.
          example: reso_xxxxxxxxxxxxx
        status:
          "$ref": "#/components/schemas/ResolutionCenterCaseStatuses"
          description: The current status of the resolution case, indicating which
            party needs to respond or if the case is closed.
        issue:
          "$ref": "#/components/schemas/ResolutionCenterCaseIssueTypes"
          description: The category of the dispute.
        created_at:
          type: string
          format: date-time
          description: The datetime the resolution was created.
          example: '2023-12-01T05:00:00.401Z'
        updated_at:
          type: string
          format: date-time
          description: The datetime the resolution was last updated.
          example: '2023-12-01T05:00:00.401Z'
        due_date:
          type:
          - string
          - 'null'
          format: date-time
          description: The deadline by which the next response is required. Null if
            no deadline is currently active. As an ISO 8601 timestamp.
          example: '2023-12-01T05:00:00.401Z'
        customer_appealed:
          type: boolean
          description: Whether the customer has filed an appeal after the initial
            resolution decision.
        merchant_appealed:
          type: boolean
          description: Whether the merchant has filed an appeal after the initial
            resolution decision.
        customer_response_actions:
          type: array
          items:
            "$ref": "#/components/schemas/ResolutionCenterCaseCustomerResponses"
          description: The list of actions currently available to the customer.
        merchant_response_actions:
          type: array
          items:
            "$ref": "#/components/schemas/ResolutionCenterCaseMerchantResponses"
          description: The list of actions currently available to the merchant.
        company:
          type:
          - object
          - 'null'
          properties:
            id:
              type: string
              description: The unique identifier for the company.
              example: biz_xxxxxxxxxxxxxx
            title:
              type: string
              description: The display name of the company shown to customers.
              example: Pickaxe
          required:
          - id
          - title
          description: The company involved in this resolution case. Null if the company
            no longer exists.
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
          description: The customer (buyer) who filed this resolution case.
        payment:
          type: object
          properties:
            id:
              type: string
              description: The unique identifier for the payment.
              example: pay_xxxxxxxxxxxxxx
          required:
          - id
          description: The payment record that is the subject of this resolution case.
      required:
      - id
      - status
      - issue
      - created_at
      - updated_at
      - due_date
      - customer_appealed
      - merchant_appealed
      - customer_response_actions
      - merchant_response_actions
      - company
      - user
      - payment
      description: A resolution center case is a dispute or support case between a
        user and a company, tracking the issue, status, and outcome.
    ResolutionCenterCaseMerchantResponses:
      type: string
      enum:
      - accept
      - deny
      - request_more_info
      - appeal
      - respond
      description: The types of responses a merchant can make to a resolution.
    ResolutionCenterCaseStatuses:
      type: string
      enum:
      - merchant_response_needed
      - customer_response_needed
      - merchant_info_needed
      - customer_info_needed
      - under_platform_review
      - customer_won
      - merchant_won
      - customer_withdrew
      description: The statuses a resolution object can have
```
