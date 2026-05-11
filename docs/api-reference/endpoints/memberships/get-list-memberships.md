# GET /memberships — List memberships

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/memberships`
- **Operation ID:** `listMembership`
- **Tags:** `Memberships`
- **Required bearer scopes:** `member:basic:read`, `member:email:read`

## Description

Returns a paginated list of memberships, with optional filtering by product, plan, status, and user.

Required permissions:
 - `member:basic:read`
 - `member:email:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `after` | query | no | `string \| null` | Returns the elements in the list that come after the specified cursor. |  |
| `before` | query | no | `string \| null` | Returns the elements in the list that come before the specified cursor. |  |
| `first` | query | no | `integer \| null` | Returns the first _n_ elements from the list. | 42 |
| `last` | query | no | `integer \| null` | Returns the last _n_ elements from the list. | 42 |
| `company_id` | query | no | `string \| null` | The unique identifier of the company to list memberships for. Required when using an API key. | biz_xxxxxxxxxxxxxx |
| `direction` | query | no | `Direction \| null` | The sort direction for results. Defaults to descending. |  |
| `order` | query | no | `MembershipsSortableColumns \| null` | The field to sort results by. Null uses the default sort order. |  |
| `product_ids` | query | no | `array<string>` | Filter to only memberships belonging to these product identifiers. |  |
| `statuses` | query | no | `array<MembershipStatus>` | Filter to only memberships matching these statuses. |  |
| `cancel_options` | query | no | `array<CancelOptions>` | Filter to only memberships matching these cancellation reasons. |  |
| `plan_ids` | query | no | `array<string>` | Filter to only memberships belonging to these plan identifiers. |  |
| `user_ids` | query | no | `array<string>` | Filter to only memberships belonging to these user identifiers. |  |
| `promo_code_ids` | query | no | `array<string>` | Filter to only memberships that used these promo code identifiers. |  |
| `created_before` | query | no | `string \| null / date-time` | Only return memberships created before this timestamp. | 2023-12-01T05:00:00.401Z |
| `created_after` | query | no | `string \| null / date-time` | Only return memberships created after this timestamp. | 2023-12-01T05:00:00.401Z |

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
      "$ref": "#/components/schemas/MembershipListItem"
    description: A list of nodes.
  page_info:
    "$ref": "#/components/schemas/PageInfo"
    description: Information to aid in pagination.
required:
- data
- page_info
description: The connection type for PublicMembership.
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
for await (const membershipListResponse of client.memberships.list()) {
  console.log(membershipListResponse.id);
}
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
page = client.memberships.list()
page = page.data[0]
print(page.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

page = whop.memberships.list

puts(page)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  CancelOptions:
    type: string
    enum:
    - too_expensive
    - switching
    - missing_features
    - technical_issues
    - bad_experience
    - other
    - testing
    description: The different reasons a user can choose for why they are canceling
      their membership.
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
  MembershipListItem:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the membership.
        example: mem_xxxxxxxxxxxxxx
      status:
        "$ref": "#/components/schemas/MembershipStatus"
        description: The current lifecycle status of the membership (e.g., active,
          trialing, past_due, canceled, expired, completed).
      created_at:
        type: string
        format: date-time
        description: The datetime the membership was created.
        example: '2023-12-01T05:00:00.401Z'
      joined_at:
        type:
        - string
        - 'null'
        format: date-time
        description: The time the user first joined the company associated with this
          membership. As an ISO 8601 timestamp. Null if the member record does not exist.
        example: '2023-12-01T05:00:00.401Z'
      updated_at:
        type: string
        format: date-time
        description: The datetime the membership was last updated.
        example: '2023-12-01T05:00:00.401Z'
      manage_url:
        type:
        - string
        - 'null'
        description: The URL where the customer can view and manage this membership,
          including cancellation and plan changes. Null if no member record exists.
        example: https://whop.com/billing/manage/mem_abc123
      member:
        type:
        - object
        - 'null'
        properties:
          id:
            type: string
            description: The unique identifier for the member.
            example: mber_xxxxxxxxxxxxx
        required:
        - id
        description: The member record linking the user to the company for this membership.
          Null if the member record has not been created yet.
      user:
        type:
        - object
        - 'null'
        properties:
          id:
            type: string
            description: The unique identifier for the user.
            example: user_xxxxxxxxxxxxx
          username:
            type: string
            description: The user's unique username shown on their public profile.
            example: johndoe42
          name:
            type:
            - string
            - 'null'
            description: The user's display name shown on their public profile.
            example: John Doe
          email:
            type:
            - string
            - 'null'
            description: The user's email address. Requires the member:email:read
              permission to access. Null if not authorized.
            example: john.doe@example.com
        required:
        - id
        - username
        - name
        - email
        description: The user who owns this membership. Null if the user account has
          been deleted.
      renewal_period_start:
        type:
        - string
        - 'null'
        format: date-time
        description: The start of the current billing period for this recurring membership.
          As an ISO 8601 timestamp. Null if the membership is not recurring.
        example: '2023-12-01T05:00:00.401Z'
      renewal_period_end:
        type:
        - string
        - 'null'
        format: date-time
        description: The end of the current billing period for this recurring membership.
          As an ISO 8601 timestamp. Null if the membership is not recurring.
        example: '2023-12-01T05:00:00.401Z'
      cancel_at_period_end:
        type: boolean
        description: Whether this membership is set to cancel at the end of the current
          billing cycle. Only applies to memberships with a recurring plan.
      cancel_option:
        oneOf:
        - "$ref": "#/components/schemas/CancelOptions"
        - type: 'null'
        description: The category selected for why the member canceled (e.g. too_expensive,
          switching, missing_features).
      cancellation_reason:
        type:
        - string
        - 'null'
        description: Free-text explanation provided by the customer when canceling.
          Null if the customer did not provide a reason.
        example: I found a better alternative.
      canceled_at:
        type:
        - string
        - 'null'
        format: date-time
        description: The time the customer initiated cancellation of this membership.
          As an ISO 8601 timestamp. Null if the membership has not been canceled.
        example: '2023-12-01T05:00:00.401Z'
      currency:
        oneOf:
        - "$ref": "#/components/schemas/Currencies"
        - type: 'null'
        description: The three-letter ISO currency code for this membership's billing.
          Null if the membership is free.
      company:
        type: object
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
        description: The company this membership belongs to.
      plan:
        type: object
        properties:
          id:
            type: string
            description: The unique identifier for the plan.
            example: plan_xxxxxxxxxxxxx
        required:
        - id
        description: The plan the customer purchased to create this membership.
      promo_code:
        type:
        - object
        - 'null'
        properties:
          id:
            type: string
            description: The unique identifier for the promo code.
            example: promo_xxxxxxxxxxxx
        required:
        - id
        description: The promotional code currently applied to this membership's billing.
          Null if no promo code is active.
      product:
        type: object
        properties:
          id:
            type: string
            description: The unique identifier for the product.
            example: prod_xxxxxxxxxxxxx
          title:
            type: string
            description: The display name of the product shown to customers on the
              product page and in search results.
            example: Pickaxe Analytics
        required:
        - id
        - title
        description: The product this membership grants access to.
      license_key:
        type:
        - string
        - 'null'
        description: The software license key associated with this membership. Only
          present if the product includes a Whop Software Licensing experience. Null
          otherwise.
        example: A1B2C3-D4E5F6-G7H8I9
      metadata:
        type:
        - object
        - 'null'
        additionalProperties: true
        description: Custom key-value pairs for the membership (commonly used for
          software licensing, e.g., HWID). Max 50 keys, 500 chars per key, 5000 chars
          per value.
      payment_collection_paused:
        type: boolean
        description: Whether recurring payment collection for this membership is temporarily
          paused by the company.
      checkout_configuration_id:
        type:
        - string
        - 'null'
        description: The ID of the checkout session/configuration that produced this
          membership, if any. Use this to map memberships back to the checkout configuration
          that created them.
    required:
    - id
    - status
    - created_at
    - joined_at
    - updated_at
    - manage_url
    - member
    - user
    - renewal_period_start
    - renewal_period_end
    - cancel_at_period_end
    - cancel_option
    - cancellation_reason
    - canceled_at
    - currency
    - company
    - plan
    - promo_code
    - product
    - license_key
    - metadata
    - payment_collection_paused
    - checkout_configuration_id
    description: A membership represents an active relationship between a user and
      a product. It tracks the user's access, billing status, and renewal schedule.
  MembershipStatus:
    type: string
    enum:
    - trialing
    - active
    - past_due
    - completed
    - canceled
    - expired
    - unresolved
    - drafted
    - canceling
    description: The status of a membership
  MembershipsSortableColumns:
    type: string
    enum:
    - id
    - created_at
    - status
    - canceled_at
    - date_joined
    - total_spend
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
  "/memberships":
    get:
      tags:
      - Memberships
      operationId: listMembership
      summary: List memberships
      description: |-
        Returns a paginated list of memberships, with optional filtering by product, plan, status, and user.

        Required permissions:
         - `member:basic:read`
         - `member:email:read`
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
          description: The unique identifier of the company to list memberships for.
            Required when using an API key.
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
          - "$ref": "#/components/schemas/MembershipsSortableColumns"
          - type: 'null'
          description: The field to sort results by. Null uses the default sort order.
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
          description: Filter to only memberships belonging to these product identifiers.
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
            "$ref": "#/components/schemas/MembershipStatus"
          description: Filter to only memberships matching these statuses.
        explode: true
        style: form
      - name: cancel_options
        in: query
        required: false
        schema:
          type:
          - array
          - 'null'
          items:
            "$ref": "#/components/schemas/CancelOptions"
          description: Filter to only memberships matching these cancellation reasons.
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
          description: Filter to only memberships belonging to these plan identifiers.
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
          description: Filter to only memberships belonging to these user identifiers.
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
          description: Filter to only memberships that used these promo code identifiers.
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
          description: Only return memberships created before this timestamp.
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
          description: Only return memberships created after this timestamp.
          example: '2023-12-01T05:00:00.401Z'
        explode: true
        style: form
      x-group-title: CRM
      security:
      - bearerAuth:
        - member:basic:read
        - member:email:read
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
                      "$ref": "#/components/schemas/MembershipListItem"
                    description: A list of nodes.
                  page_info:
                    "$ref": "#/components/schemas/PageInfo"
                    description: Information to aid in pagination.
                required:
                - data
                - page_info
                description: The connection type for PublicMembership.
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
          for await (const membershipListResponse of client.memberships.list()) {
            console.log(membershipListResponse.id);
          }
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          page = client.memberships.list()
          page = page.data[0]
          print(page.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          page = whop.memberships.list

          puts(page)
components:
  schemas:
    CancelOptions:
      type: string
      enum:
      - too_expensive
      - switching
      - missing_features
      - technical_issues
      - bad_experience
      - other
      - testing
      description: The different reasons a user can choose for why they are canceling
        their membership.
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
    MembershipListItem:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the membership.
          example: mem_xxxxxxxxxxxxxx
        status:
          "$ref": "#/components/schemas/MembershipStatus"
          description: The current lifecycle status of the membership (e.g., active,
            trialing, past_due, canceled, expired, completed).
        created_at:
          type: string
          format: date-time
          description: The datetime the membership was created.
          example: '2023-12-01T05:00:00.401Z'
        joined_at:
          type:
          - string
          - 'null'
          format: date-time
          description: The time the user first joined the company associated with
            this membership. As an ISO 8601 timestamp. Null if the member record does not
            exist.
          example: '2023-12-01T05:00:00.401Z'
        updated_at:
          type: string
          format: date-time
          description: The datetime the membership was last updated.
          example: '2023-12-01T05:00:00.401Z'
        manage_url:
          type:
          - string
          - 'null'
          description: The URL where the customer can view and manage this membership,
            including cancellation and plan changes. Null if no member record exists.
          example: https://whop.com/billing/manage/mem_abc123
        member:
          type:
          - object
          - 'null'
          properties:
            id:
              type: string
              description: The unique identifier for the member.
              example: mber_xxxxxxxxxxxxx
          required:
          - id
          description: The member record linking the user to the company for this
            membership. Null if the member record has not been created yet.
        user:
          type:
          - object
          - 'null'
          properties:
            id:
              type: string
              description: The unique identifier for the user.
              example: user_xxxxxxxxxxxxx
            username:
              type: string
              description: The user's unique username shown on their public profile.
              example: johndoe42
            name:
              type:
              - string
              - 'null'
              description: The user's display name shown on their public profile.
              example: John Doe
            email:
              type:
              - string
              - 'null'
              description: The user's email address. Requires the member:email:read
                permission to access. Null if not authorized.
              example: john.doe@example.com
          required:
          - id
          - username
          - name
          - email
          description: The user who owns this membership. Null if the user account
            has been deleted.
        renewal_period_start:
          type:
          - string
          - 'null'
          format: date-time
          description: The start of the current billing period for this recurring
            membership. As an ISO 8601 timestamp. Null if the membership is not recurring.
          example: '2023-12-01T05:00:00.401Z'
        renewal_period_end:
          type:
          - string
          - 'null'
          format: date-time
          description: The end of the current billing period for this recurring membership.
            As an ISO 8601 timestamp. Null if the membership is not recurring.
          example: '2023-12-01T05:00:00.401Z'
        cancel_at_period_end:
          type: boolean
          description: Whether this membership is set to cancel at the end of the
            current billing cycle. Only applies to memberships with a recurring plan.
        cancel_option:
          oneOf:
          - "$ref": "#/components/schemas/CancelOptions"
          - type: 'null'
          description: The category selected for why the member canceled (e.g. too_expensive,
            switching, missing_features).
        cancellation_reason:
          type:
          - string
          - 'null'
          description: Free-text explanation provided by the customer when canceling.
            Null if the customer did not provide a reason.
          example: I found a better alternative.
        canceled_at:
          type:
          - string
          - 'null'
          format: date-time
          description: The time the customer initiated cancellation of this membership.
            As an ISO 8601 timestamp. Null if the membership has not been canceled.
          example: '2023-12-01T05:00:00.401Z'
        currency:
          oneOf:
          - "$ref": "#/components/schemas/Currencies"
          - type: 'null'
          description: The three-letter ISO currency code for this membership's billing.
            Null if the membership is free.
        company:
          type: object
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
          description: The company this membership belongs to.
        plan:
          type: object
          properties:
            id:
              type: string
              description: The unique identifier for the plan.
              example: plan_xxxxxxxxxxxxx
          required:
          - id
          description: The plan the customer purchased to create this membership.
        promo_code:
          type:
          - object
          - 'null'
          properties:
            id:
              type: string
              description: The unique identifier for the promo code.
              example: promo_xxxxxxxxxxxx
          required:
          - id
          description: The promotional code currently applied to this membership's
            billing. Null if no promo code is active.
        product:
          type: object
          properties:
            id:
              type: string
              description: The unique identifier for the product.
              example: prod_xxxxxxxxxxxxx
            title:
              type: string
              description: The display name of the product shown to customers on the
                product page and in search results.
              example: Pickaxe Analytics
          required:
          - id
          - title
          description: The product this membership grants access to.
        license_key:
          type:
          - string
          - 'null'
          description: The software license key associated with this membership. Only
            present if the product includes a Whop Software Licensing experience.
            Null otherwise.
          example: A1B2C3-D4E5F6-G7H8I9
        metadata:
          type:
          - object
          - 'null'
          additionalProperties: true
          description: Custom key-value pairs for the membership (commonly used for
            software licensing, e.g., HWID). Max 50 keys, 500 chars per key, 5000
            chars per value.
        payment_collection_paused:
          type: boolean
          description: Whether recurring payment collection for this membership is
            temporarily paused by the company.
        checkout_configuration_id:
          type:
          - string
          - 'null'
          description: The ID of the checkout session/configuration that produced
            this membership, if any. Use this to map memberships back to the checkout
            configuration that created them.
      required:
      - id
      - status
      - created_at
      - joined_at
      - updated_at
      - manage_url
      - member
      - user
      - renewal_period_start
      - renewal_period_end
      - cancel_at_period_end
      - cancel_option
      - cancellation_reason
      - canceled_at
      - currency
      - company
      - plan
      - promo_code
      - product
      - license_key
      - metadata
      - payment_collection_paused
      - checkout_configuration_id
      description: A membership represents an active relationship between a user and
        a product. It tracks the user's access, billing status, and renewal schedule.
    MembershipStatus:
      type: string
      enum:
      - trialing
      - active
      - past_due
      - completed
      - canceled
      - expired
      - unresolved
      - drafted
      - canceling
      description: The status of a membership
    MembershipsSortableColumns:
      type: string
      enum:
      - id
      - created_at
      - status
      - canceled_at
      - date_joined
      - total_spend
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
