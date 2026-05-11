# GET /ad_campaigns — List ad campaigns

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/ad_campaigns`
- **Operation ID:** `listAdCampaign`
- **Tags:** `Ad campaigns`
- **Required bearer scopes:** `ad_campaign:basic:read`, `access_pass:basic:read`

## Description

Returns a paginated list of ad campaigns for a company, with optional filtering by status, and creation date.

Required permissions:
 - `ad_campaign:basic:read`
 - `access_pass:basic:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `after` | query | no | `string \| null` | Returns the elements in the list that come after the specified cursor. |  |
| `before` | query | no | `string \| null` | Returns the elements in the list that come before the specified cursor. |  |
| `first` | query | no | `integer \| null` | Returns the first _n_ elements from the list. | 42 |
| `last` | query | no | `integer \| null` | Returns the last _n_ elements from the list. | 42 |
| `company_id` | query | yes | `string` | The unique identifier of the company to list ad campaigns for. | biz_xxxxxxxxxxxxxx |
| `status` | query | no | `AdCampaignStatuses \| null` | Filter ad campaigns by their current status (e.g., active, paused, completed). |  |
| `created_before` | query | no | `string \| null / date-time` | Only return ad campaigns created before this timestamp. | 2023-12-01T05:00:00.401Z |
| `created_after` | query | no | `string \| null / date-time` | Only return ad campaigns created after this timestamp. | 2023-12-01T05:00:00.401Z |
| `query` | query | no | `string \| null` | Case-insensitive substring match against the campaign title. |  |

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
      "$ref": "#/components/schemas/AdCampaignListItem"
    description: A list of nodes.
  page_info:
    "$ref": "#/components/schemas/PageInfo"
    description: Information to aid in pagination.
required:
- data
- page_info
description: The connection type for AdCampaign.
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
for await (const adCampaignListResponse of client.adCampaigns.list({
  company_id: 'biz_xxxxxxxxxxxxxx',
})) {
  console.log(adCampaignListResponse.id);
}
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
page = client.ad_campaigns.list(
    company_id="biz_xxxxxxxxxxxxxx",
)
page = page.data[0]
print(page.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

page = whop.ad_campaigns.list(company_id: "biz_xxxxxxxxxxxxxx")

puts(page)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  AdCampaignListItem:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the ad campaign.
        example: adcamp_xxxxxxxxxxx
      title:
        type: string
        description: The title of the ad campaign
      status:
        "$ref": "#/components/schemas/AdCampaignStatuses"
        description: Current status of the campaign (active, paused, or inactive)
      target_country_codes:
        type: array
        items:
          type: string
          description: Represents textual data as UTF-8 character sequences. This
            type is most often used by GraphQL to represent free-form human-readable
            text.
        description: Array of ISO3166 country codes for territory targeting
      daily_budget:
        type:
        - number
        - 'null'
        description: Effective daily budget in dollars — sum of ad group budgets when
          set, otherwise campaign-level daily budget
        example: 6.9
      platform:
        oneOf:
        - "$ref": "#/components/schemas/AdCampaignPlatforms"
        - type: 'null'
        description: The external platform this campaign is running on (e.g., meta,
          tiktok).
      available_budget:
        type: number
        description: Available budget in dollars, capped at daily budget minus today's
          spend for daily campaigns
        example: 6.9
      created_at:
        type: string
        format: date-time
        description: The datetime the ad campaign was created.
        example: '2023-12-01T05:00:00.401Z'
      updated_at:
        type: string
        format: date-time
        description: The datetime the ad campaign was last updated.
        example: '2023-12-01T05:00:00.401Z'
      paused_until:
        type:
        - string
        - 'null'
        format: date-time
        description: If temporarily paused, the timestamp when the campaign will auto-resume
        example: '2023-12-01T05:00:00.401Z'
      clicks_count:
        type: integer
        description: Number of clicks
        example: 42
      impressions_count:
        type: integer
        description: Number of impressions (views)
        example: 42
      purchases_count:
        type: integer
        description: Number of purchases
        example: 42
      remaining_balance:
        type: number
        description: Remaining balance in dollars
        example: 6.9
      revenue:
        type: number
        description: Total revenue generated from users who converted through this
          campaign
        example: 6.9
      return_on_ad_spend:
        type: number
        description: Return on Ad Spend (ROAS) percentage - revenue generated divided
          by ad spend
        example: 6.9
      todays_spend:
        type: number
        description: Amount spent today in dollars
        example: 6.9
      total_credits:
        type: number
        description: Total credits added to the campaign in dollars
        example: 6.9
      total_spend:
        type: number
        description: Total amount spent on conversions in dollars
        example: 6.9
      product:
        type:
        - object
        - 'null'
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
          route:
            type: string
            description: The URL slug used in the product's public link (e.g., 'my-product'
              in whop.com/company/my-product).
            example: pickaxe-analytics
        required:
        - id
        - title
        - route
        description: The access pass being promoted. Null for campaigns that don't
          target a specific product.
    required:
    - id
    - title
    - status
    - target_country_codes
    - daily_budget
    - platform
    - available_budget
    - created_at
    - updated_at
    - paused_until
    - clicks_count
    - impressions_count
    - purchases_count
    - remaining_balance
    - revenue
    - return_on_ad_spend
    - todays_spend
    - total_credits
    - total_spend
    - product
    description: An advertising campaign running on an external platform or within
      Whop.
  AdCampaignPlatforms:
    type: string
    enum:
    - meta
    - tiktok
    description: The platforms where an ad campaign can run.
  AdCampaignStatuses:
    type: string
    enum:
    - active
    - paused
    - inactive
    - stale
    - pending_refund
    - payment_failed
    - draft
    - in_review
    - flagged
    - importing
    - imported
    description: The status of an ad campaign.
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
  "/ad_campaigns":
    get:
      tags:
      - Ad campaigns
      operationId: listAdCampaign
      summary: List ad campaigns
      description: |-
        Returns a paginated list of ad campaigns for a company, with optional filtering by status, and creation date.

        Required permissions:
         - `ad_campaign:basic:read`
         - `access_pass:basic:read`
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
          description: The unique identifier of the company to list ad campaigns for.
          example: biz_xxxxxxxxxxxxxx
        explode: true
        style: form
      - name: status
        in: query
        required: false
        schema:
          oneOf:
          - "$ref": "#/components/schemas/AdCampaignStatuses"
          - type: 'null'
          description: Filter ad campaigns by their current status (e.g., active,
            paused, completed).
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
          description: Only return ad campaigns created before this timestamp.
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
          description: Only return ad campaigns created after this timestamp.
          example: '2023-12-01T05:00:00.401Z'
        explode: true
        style: form
      - name: query
        in: query
        required: false
        schema:
          type:
          - string
          - 'null'
          description: Case-insensitive substring match against the campaign title.
        explode: true
        style: form
      x-group-title: Ads
      security:
      - bearerAuth:
        - ad_campaign:basic:read
        - access_pass:basic:read
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
                      "$ref": "#/components/schemas/AdCampaignListItem"
                    description: A list of nodes.
                  page_info:
                    "$ref": "#/components/schemas/PageInfo"
                    description: Information to aid in pagination.
                required:
                - data
                - page_info
                description: The connection type for AdCampaign.
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
          for await (const adCampaignListResponse of client.adCampaigns.list({
            company_id: 'biz_xxxxxxxxxxxxxx',
          })) {
            console.log(adCampaignListResponse.id);
          }
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          page = client.ad_campaigns.list(
              company_id="biz_xxxxxxxxxxxxxx",
          )
          page = page.data[0]
          print(page.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          page = whop.ad_campaigns.list(company_id: "biz_xxxxxxxxxxxxxx")

          puts(page)
components:
  schemas:
    AdCampaignListItem:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the ad campaign.
          example: adcamp_xxxxxxxxxxx
        title:
          type: string
          description: The title of the ad campaign
        status:
          "$ref": "#/components/schemas/AdCampaignStatuses"
          description: Current status of the campaign (active, paused, or inactive)
        target_country_codes:
          type: array
          items:
            type: string
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          description: Array of ISO3166 country codes for territory targeting
        daily_budget:
          type:
          - number
          - 'null'
          description: Effective daily budget in dollars — sum of ad group budgets
            when set, otherwise campaign-level daily budget
          example: 6.9
        platform:
          oneOf:
          - "$ref": "#/components/schemas/AdCampaignPlatforms"
          - type: 'null'
          description: The external platform this campaign is running on (e.g., meta,
            tiktok).
        available_budget:
          type: number
          description: Available budget in dollars, capped at daily budget minus today's
            spend for daily campaigns
          example: 6.9
        created_at:
          type: string
          format: date-time
          description: The datetime the ad campaign was created.
          example: '2023-12-01T05:00:00.401Z'
        updated_at:
          type: string
          format: date-time
          description: The datetime the ad campaign was last updated.
          example: '2023-12-01T05:00:00.401Z'
        paused_until:
          type:
          - string
          - 'null'
          format: date-time
          description: If temporarily paused, the timestamp when the campaign will
            auto-resume
          example: '2023-12-01T05:00:00.401Z'
        clicks_count:
          type: integer
          description: Number of clicks
          example: 42
        impressions_count:
          type: integer
          description: Number of impressions (views)
          example: 42
        purchases_count:
          type: integer
          description: Number of purchases
          example: 42
        remaining_balance:
          type: number
          description: Remaining balance in dollars
          example: 6.9
        revenue:
          type: number
          description: Total revenue generated from users who converted through this
            campaign
          example: 6.9
        return_on_ad_spend:
          type: number
          description: Return on Ad Spend (ROAS) percentage - revenue generated divided
            by ad spend
          example: 6.9
        todays_spend:
          type: number
          description: Amount spent today in dollars
          example: 6.9
        total_credits:
          type: number
          description: Total credits added to the campaign in dollars
          example: 6.9
        total_spend:
          type: number
          description: Total amount spent on conversions in dollars
          example: 6.9
        product:
          type:
          - object
          - 'null'
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
            route:
              type: string
              description: The URL slug used in the product's public link (e.g., 'my-product'
                in whop.com/company/my-product).
              example: pickaxe-analytics
          required:
          - id
          - title
          - route
          description: The access pass being promoted. Null for campaigns that don't
            target a specific product.
      required:
      - id
      - title
      - status
      - target_country_codes
      - daily_budget
      - platform
      - available_budget
      - created_at
      - updated_at
      - paused_until
      - clicks_count
      - impressions_count
      - purchases_count
      - remaining_balance
      - revenue
      - return_on_ad_spend
      - todays_spend
      - total_credits
      - total_spend
      - product
      description: An advertising campaign running on an external platform or within
        Whop.
    AdCampaignPlatforms:
      type: string
      enum:
      - meta
      - tiktok
      description: The platforms where an ad campaign can run.
    AdCampaignStatuses:
      type: string
      enum:
      - active
      - paused
      - inactive
      - stale
      - pending_refund
      - payment_failed
      - draft
      - in_review
      - flagged
      - importing
      - imported
      description: The status of an ad campaign.
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
