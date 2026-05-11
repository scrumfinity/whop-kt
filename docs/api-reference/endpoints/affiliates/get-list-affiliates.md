# GET /affiliates — List affiliates

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/affiliates`
- **Operation ID:** `listAffiliate`
- **Tags:** `Affiliates`
- **Required bearer scopes:** `affiliate:basic:read`

## Description

Returns a paginated list of affiliates for the actor in context, with optional filtering by status, search, and sorting.

Required permissions:
 - `affiliate:basic:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `after` | query | no | `string \| null` | Returns the elements in the list that come after the specified cursor. |  |
| `before` | query | no | `string \| null` | Returns the elements in the list that come before the specified cursor. |  |
| `first` | query | no | `integer \| null` | Returns the first _n_ elements from the list. | 42 |
| `last` | query | no | `integer \| null` | Returns the last _n_ elements from the list. | 42 |
| `company_id` | query | yes | `string` | The unique identifier of the company to list affiliates for. | biz_xxxxxxxxxxxxxx |
| `direction` | query | no | `Direction \| null` | The sort direction for results. Defaults to descending. |  |
| `order` | query | no | `AffiliatesSortableColumns \| null` | The field to sort results by. |  |
| `query` | query | no | `string \| null` | Search affiliates by username. |  |
| `status` | query | no | `Status \| null` | Filter by affiliate status (active or archived). |  |

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
      "$ref": "#/components/schemas/AffiliateListItem"
    description: A list of nodes.
  page_info:
    "$ref": "#/components/schemas/PageInfo"
    description: Information to aid in pagination.
required:
- data
- page_info
description: The connection type for Affiliate.
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
for await (const affiliateListResponse of client.affiliates.list({
  company_id: 'biz_xxxxxxxxxxxxxx',
})) {
  console.log(affiliateListResponse.id);
}
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
page = client.affiliates.list(
    company_id="biz_xxxxxxxxxxxxxx",
)
page = page.data[0]
print(page.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

page = whop.affiliates.list(company_id: "biz_xxxxxxxxxxxxxx")

puts(page)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  AffiliateListItem:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the affiliate.
        example: aff_xxxxxxxxxxxxxx
      status:
        oneOf:
        - "$ref": "#/components/schemas/Status"
        - type: 'null'
        description: The status of the affiliate
      created_at:
        type: string
        format: date-time
        description: The datetime the affiliate was created.
        example: '2023-12-01T05:00:00.401Z'
      updated_at:
        type: string
        format: date-time
        description: The datetime the affiliate was last updated.
        example: '2023-12-01T05:00:00.401Z'
      total_referrals_count:
        type: integer
        description: The total referrals of the affiliate
        example: 42
      total_referral_earnings_usd:
        type: string
        description: The total commission earnings paid to this affiliate, formatted
          as a USD currency string
      total_overrides_count:
        type: integer
        description: The total count of all overrides for this affiliate
        example: 42
      customer_retention_rate:
        type: string
        description: The percentage of referred customers who are still active members
      customer_retention_rate_ninety_days:
        type: string
        description: The percentage of referred customers who remained active over
          the last 90 days
      monthly_recurring_revenue_usd:
        type: string
        description: The monthly recurring revenue generated by this affiliate's referrals,
          formatted as a USD currency string
      total_revenue_usd:
        type: string
        description: The total revenue generated from this affiliate's referrals,
          formatted as a USD currency string
      active_members_count:
        type: integer
        description: The total active members of the affiliate
        example: 42
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
            description: The display name set on the user's Whop profile. Null if
              the user has not set a name.
            example: Jane Smith
          username:
            type:
            - string
            - 'null'
            description: The unique username chosen by the user for their Whop profile.
              Null if the user has not set a username.
            example: janesmith
        required:
        - id
        - name
        - username
        description: The user attached to this affiliate
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
        required:
        - id
        - title
        description: The company attached to this affiliate
    required:
    - id
    - status
    - created_at
    - updated_at
    - total_referrals_count
    - total_referral_earnings_usd
    - total_overrides_count
    - customer_retention_rate
    - customer_retention_rate_ninety_days
    - monthly_recurring_revenue_usd
    - total_revenue_usd
    - active_members_count
    - user
    - company
    description: An affiliate tracks a user's referral performance and commission
      earnings for a company, including retention rates, revenue metrics, and payout
      configurations.
  AffiliatesSortableColumns:
    type: string
    enum:
    - id
    - created_at
    - cached_total_referrals
    - cached_total_rewards
    description: Which columns can be used to sort.
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
  Status:
    type: string
    enum:
    - active
    - archived
    - deleted
    description: Statuses for resources
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
  "/affiliates":
    get:
      tags:
      - Affiliates
      operationId: listAffiliate
      summary: List affiliates
      description: |-
        Returns a paginated list of affiliates for the actor in context, with optional filtering by status, search, and sorting.

        Required permissions:
         - `affiliate:basic:read`
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
          description: The unique identifier of the company to list affiliates for.
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
          - "$ref": "#/components/schemas/AffiliatesSortableColumns"
          - type: 'null'
          description: The field to sort results by.
        explode: true
        style: form
      - name: query
        in: query
        required: false
        schema:
          type:
          - string
          - 'null'
          description: Search affiliates by username.
        explode: true
        style: form
      - name: status
        in: query
        required: false
        schema:
          oneOf:
          - "$ref": "#/components/schemas/Status"
          - type: 'null'
          description: Filter by affiliate status (active or archived).
        explode: true
        style: form
      x-group-title: CRM
      security:
      - bearerAuth:
        - affiliate:basic:read
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
                      "$ref": "#/components/schemas/AffiliateListItem"
                    description: A list of nodes.
                  page_info:
                    "$ref": "#/components/schemas/PageInfo"
                    description: Information to aid in pagination.
                required:
                - data
                - page_info
                description: The connection type for Affiliate.
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
          for await (const affiliateListResponse of client.affiliates.list({
            company_id: 'biz_xxxxxxxxxxxxxx',
          })) {
            console.log(affiliateListResponse.id);
          }
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          page = client.affiliates.list(
              company_id="biz_xxxxxxxxxxxxxx",
          )
          page = page.data[0]
          print(page.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          page = whop.affiliates.list(company_id: "biz_xxxxxxxxxxxxxx")

          puts(page)
components:
  schemas:
    AffiliateListItem:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the affiliate.
          example: aff_xxxxxxxxxxxxxx
        status:
          oneOf:
          - "$ref": "#/components/schemas/Status"
          - type: 'null'
          description: The status of the affiliate
        created_at:
          type: string
          format: date-time
          description: The datetime the affiliate was created.
          example: '2023-12-01T05:00:00.401Z'
        updated_at:
          type: string
          format: date-time
          description: The datetime the affiliate was last updated.
          example: '2023-12-01T05:00:00.401Z'
        total_referrals_count:
          type: integer
          description: The total referrals of the affiliate
          example: 42
        total_referral_earnings_usd:
          type: string
          description: The total commission earnings paid to this affiliate, formatted
            as a USD currency string
        total_overrides_count:
          type: integer
          description: The total count of all overrides for this affiliate
          example: 42
        customer_retention_rate:
          type: string
          description: The percentage of referred customers who are still active members
        customer_retention_rate_ninety_days:
          type: string
          description: The percentage of referred customers who remained active over
            the last 90 days
        monthly_recurring_revenue_usd:
          type: string
          description: The monthly recurring revenue generated by this affiliate's
            referrals, formatted as a USD currency string
        total_revenue_usd:
          type: string
          description: The total revenue generated from this affiliate's referrals,
            formatted as a USD currency string
        active_members_count:
          type: integer
          description: The total active members of the affiliate
          example: 42
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
              description: The display name set on the user's Whop profile. Null if
                the user has not set a name.
              example: Jane Smith
            username:
              type:
              - string
              - 'null'
              description: The unique username chosen by the user for their Whop profile.
                Null if the user has not set a username.
              example: janesmith
          required:
          - id
          - name
          - username
          description: The user attached to this affiliate
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
          required:
          - id
          - title
          description: The company attached to this affiliate
      required:
      - id
      - status
      - created_at
      - updated_at
      - total_referrals_count
      - total_referral_earnings_usd
      - total_overrides_count
      - customer_retention_rate
      - customer_retention_rate_ninety_days
      - monthly_recurring_revenue_usd
      - total_revenue_usd
      - active_members_count
      - user
      - company
      description: An affiliate tracks a user's referral performance and commission
        earnings for a company, including retention rates, revenue metrics, and payout
        configurations.
    AffiliatesSortableColumns:
      type: string
      enum:
      - id
      - created_at
      - cached_total_referrals
      - cached_total_rewards
      description: Which columns can be used to sort.
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
    Status:
      type: string
      enum:
      - active
      - archived
      - deleted
      description: Statuses for resources
```
