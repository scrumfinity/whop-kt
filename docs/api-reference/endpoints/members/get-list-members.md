# GET /members — List members

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/members`
- **Operation ID:** `listMember`
- **Tags:** `Members`
- **Required bearer scopes:** `member:basic:read`, `member:email:read`, `member:phone:read`

## Description

Returns a paginated list of members for a company, with extensive filtering by product, plan, status, access level, and more.

Required permissions:
 - `member:basic:read`
 - `member:email:read`
 - `member:phone:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `after` | query | no | `string \| null` | Returns the elements in the list that come after the specified cursor. |  |
| `before` | query | no | `string \| null` | Returns the elements in the list that come before the specified cursor. |  |
| `first` | query | no | `integer \| null` | Returns the first _n_ elements from the list. | 42 |
| `last` | query | no | `integer \| null` | Returns the last _n_ elements from the list. | 42 |
| `company_id` | query | no | `string \| null` | The unique identifier of the company to list members for. | biz_xxxxxxxxxxxxxx |
| `direction` | query | no | `Direction \| null` | The sort direction for results. Defaults to descending. |  |
| `order` | query | no | `MembersSortableColumns \| null` | The column to sort members by, such as creation date or revenue. |  |
| `created_before` | query | no | `string \| null / date-time` | Only return members created before this timestamp. | 2023-12-01T05:00:00.401Z |
| `created_after` | query | no | `string \| null / date-time` | Only return members created after this timestamp. | 2023-12-01T05:00:00.401Z |
| `access_level` | query | no | `AccessLevel \| null` | Filter members by their current access level to the product. |  |
| `product_ids` | query | no | `array<string>` | Filter members to only those belonging to these specific products. |  |
| `plan_ids` | query | no | `array<string>` | Filter members to only those subscribed to these specific plans. |  |
| `user_ids` | query | no | `array<string>` | Filter members to only those matching these specific user identifiers. |  |
| `statuses` | query | no | `array<MemberStatuses>` | Filter members by their current subscription status. |  |
| `promo_code_ids` | query | no | `array<string>` | Filter members to only those who used these specific promo codes. |  |
| `most_recent_actions` | query | no | `array<MemberMostRecentActions>` | Filter members by their most recent activity type. |  |
| `query` | query | no | `string \| null` | Search members by name, username, or email. Email filtering requires the member:email:read permission. |  |

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
      "$ref": "#/components/schemas/MemberListItem"
    description: A list of nodes.
  page_info:
    "$ref": "#/components/schemas/PageInfo"
    description: Information to aid in pagination.
required:
- data
- page_info
description: The connection type for CompanyMember.
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
for await (const memberListResponse of client.members.list()) {
  console.log(memberListResponse.id);
}
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
page = client.members.list()
page = page.data[0]
print(page.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

page = whop.members.list

puts(page)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  AccessLevel:
    type: string
    enum:
    - no_access
    - admin
    - customer
    description: The access level a given user (or company) has to a product or company.
  Direction:
    type: string
    enum:
    - asc
    - desc
    description: The direction of the sort.
  MemberListItem:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the company member.
      created_at:
        type: string
        format: date-time
        description: The datetime the company member was created.
        example: '2023-12-01T05:00:00.401Z'
      updated_at:
        type: string
        format: date-time
        description: The datetime the company member was last updated.
        example: '2023-12-01T05:00:00.401Z'
      joined_at:
        type: string
        format: date-time
        description: When the member joined the company
        example: '2023-12-01T05:00:00.401Z'
      access_level:
        "$ref": "#/components/schemas/AccessLevel"
        description: The access level of the product member. If its admin, the member
          is an authorized user of the company. If its customer, the member has a
          valid membership to any product on the company. If its no_access, the member
          does not have access to the product.
      status:
        "$ref": "#/components/schemas/MemberStatuses"
        description: The status of the member
      most_recent_action:
        oneOf:
        - "$ref": "#/components/schemas/MemberMostRecentActions"
        - type: 'null'
        description: The most recent action the member has taken.
      most_recent_action_at:
        type:
        - string
        - 'null'
        format: date-time
        description: The time for the most recent action, if applicable.
        example: '2023-12-01T05:00:00.401Z'
      user:
        type:
        - object
        - 'null'
        properties:
          id:
            type: string
            description: The unique identifier for the company member user.
          email:
            type:
            - string
            - 'null'
            description: The digital mailing address of the user.
          name:
            type:
            - string
            - 'null'
            description: The user's full name.
          username:
            type: string
            description: The whop username.
        required:
        - id
        - email
        - name
        - username
        description: The user for this member, if any.
      phone:
        type:
        - string
        - 'null'
        description: The phone number for the member, if available.
      usd_total_spent:
        type: number
        description: How much money this customer has spent on the company's products
          and plans
        example: 6.9
      company_token_balance:
        type: number
        description: The member's token balance for this company
        example: 6.9
    required:
    - id
    - created_at
    - updated_at
    - joined_at
    - access_level
    - status
    - most_recent_action
    - most_recent_action_at
    - user
    - phone
    - usd_total_spent
    - company_token_balance
    description: A member represents a user's relationship with a company on Whop,
      including their access level, status, and spending history.
  MemberMostRecentActions:
    type: string
    enum:
    - canceling
    - churned
    - finished_split_pay
    - paused
    - paid_subscriber
    - paid_once
    - expiring
    - joined
    - drafted
    - left
    - trialing
    - pending_entry
    - renewing
    - past_due
    description: The different most recent actions a member can have.
  MemberStatuses:
    type: string
    enum:
    - drafted
    - joined
    - left
    description: The different statuses a Member can have.
  MembersSortableColumns:
    type: string
    enum:
    - id
    - usd_total_spent
    - created_at
    - joined_at
    - most_recent_action
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
  "/members":
    get:
      tags:
      - Members
      operationId: listMember
      summary: List members
      description: |-
        Returns a paginated list of members for a company, with extensive filtering by product, plan, status, access level, and more.

        Required permissions:
         - `member:basic:read`
         - `member:email:read`
         - `member:phone:read`
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
          description: The unique identifier of the company to list members for.
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
          description: The sort direction for results. Defaults to descending.
        explode: true
        style: form
      - name: order
        in: query
        required: false
        schema:
          oneOf:
          - "$ref": "#/components/schemas/MembersSortableColumns"
          - type: 'null'
          description: The column to sort members by, such as creation date or revenue.
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
          description: Only return members created before this timestamp.
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
          description: Only return members created after this timestamp.
          example: '2023-12-01T05:00:00.401Z'
        explode: true
        style: form
      - name: access_level
        in: query
        required: false
        schema:
          oneOf:
          - "$ref": "#/components/schemas/AccessLevel"
          - type: 'null'
          description: Filter members by their current access level to the product.
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
          description: Filter members to only those belonging to these specific products.
        explode: true
        style: form
      - name: plan_ids
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
          description: Filter members to only those subscribed to these specific plans.
        explode: true
        style: form
      - name: user_ids
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
          description: Filter members to only those matching these specific user identifiers.
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
            "$ref": "#/components/schemas/MemberStatuses"
          description: Filter members by their current subscription status.
        explode: true
        style: form
      - name: promo_code_ids
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
          description: Filter members to only those who used these specific promo
            codes.
        explode: true
        style: form
      - name: most_recent_actions
        in: query
        required: false
        schema:
          type:
          - array
          - 'null'
          items:
            "$ref": "#/components/schemas/MemberMostRecentActions"
          description: Filter members by their most recent activity type.
        explode: true
        style: form
      - name: query
        in: query
        required: false
        schema:
          type:
          - string
          - 'null'
          description: Search members by name, username, or email. Email filtering
            requires the member:email:read permission.
        explode: true
        style: form
      x-group-title: CRM
      security:
      - bearerAuth:
        - member:basic:read
        - member:email:read
        - member:phone:read
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
                      "$ref": "#/components/schemas/MemberListItem"
                    description: A list of nodes.
                  page_info:
                    "$ref": "#/components/schemas/PageInfo"
                    description: Information to aid in pagination.
                required:
                - data
                - page_info
                description: The connection type for CompanyMember.
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
          for await (const memberListResponse of client.members.list()) {
            console.log(memberListResponse.id);
          }
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          page = client.members.list()
          page = page.data[0]
          print(page.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          page = whop.members.list

          puts(page)
components:
  schemas:
    AccessLevel:
      type: string
      enum:
      - no_access
      - admin
      - customer
      description: The access level a given user (or company) has to a product or
        company.
    Direction:
      type: string
      enum:
      - asc
      - desc
      description: The direction of the sort.
    MemberListItem:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the company member.
        created_at:
          type: string
          format: date-time
          description: The datetime the company member was created.
          example: '2023-12-01T05:00:00.401Z'
        updated_at:
          type: string
          format: date-time
          description: The datetime the company member was last updated.
          example: '2023-12-01T05:00:00.401Z'
        joined_at:
          type: string
          format: date-time
          description: When the member joined the company
          example: '2023-12-01T05:00:00.401Z'
        access_level:
          "$ref": "#/components/schemas/AccessLevel"
          description: The access level of the product member. If its admin, the member
            is an authorized user of the company. If its customer, the member has
            a valid membership to any product on the company. If its no_access, the
            member does not have access to the product.
        status:
          "$ref": "#/components/schemas/MemberStatuses"
          description: The status of the member
        most_recent_action:
          oneOf:
          - "$ref": "#/components/schemas/MemberMostRecentActions"
          - type: 'null'
          description: The most recent action the member has taken.
        most_recent_action_at:
          type:
          - string
          - 'null'
          format: date-time
          description: The time for the most recent action, if applicable.
          example: '2023-12-01T05:00:00.401Z'
        user:
          type:
          - object
          - 'null'
          properties:
            id:
              type: string
              description: The unique identifier for the company member user.
            email:
              type:
              - string
              - 'null'
              description: The digital mailing address of the user.
            name:
              type:
              - string
              - 'null'
              description: The user's full name.
            username:
              type: string
              description: The whop username.
          required:
          - id
          - email
          - name
          - username
          description: The user for this member, if any.
        phone:
          type:
          - string
          - 'null'
          description: The phone number for the member, if available.
        usd_total_spent:
          type: number
          description: How much money this customer has spent on the company's products
            and plans
          example: 6.9
        company_token_balance:
          type: number
          description: The member's token balance for this company
          example: 6.9
      required:
      - id
      - created_at
      - updated_at
      - joined_at
      - access_level
      - status
      - most_recent_action
      - most_recent_action_at
      - user
      - phone
      - usd_total_spent
      - company_token_balance
      description: A member represents a user's relationship with a company on Whop,
        including their access level, status, and spending history.
    MemberMostRecentActions:
      type: string
      enum:
      - canceling
      - churned
      - finished_split_pay
      - paused
      - paid_subscriber
      - paid_once
      - expiring
      - joined
      - drafted
      - left
      - trialing
      - pending_entry
      - renewing
      - past_due
      description: The different most recent actions a member can have.
    MemberStatuses:
      type: string
      enum:
      - drafted
      - joined
      - left
      description: The different statuses a Member can have.
    MembersSortableColumns:
      type: string
      enum:
      - id
      - usd_total_spent
      - created_at
      - joined_at
      - most_recent_action
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
