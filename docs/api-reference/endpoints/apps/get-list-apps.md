# GET /apps — List apps

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/apps`
- **Operation ID:** `listApp`
- **Tags:** `Apps`
- **Required bearer scopes:** _None documented_

## Description

Returns a paginated list of apps on the Whop platform, with optional filtering by company, type, view support, and search query.

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `app_type` | query | no | `AppTypes \| null` | Filter apps by the type of end-user they are built for, such as consumer or business. |  |
| `company_id` | query | no | `string \| null` | Filter apps to only those created by this company, starting with 'biz_'. | biz_xxxxxxxxxxxxxx |
| `direction` | query | no | `Direction \| null` | The sort direction for results. Accepted values: asc, desc. |  |
| `order` | query | no | `AppOrder \| null` | The field to sort apps by. Defaults to discoverable_at descending, showing the most recently published apps first. |  |
| `query` | query | no | `string \| null` | A search string to filter apps by name, such as 'chat' or 'analytics'. |  |
| `verified_apps_only` | query | no | `boolean \| null` | Whether to only return apps that have been verified by Whop. Useful for populating a featured apps section. |  |
| `view_type` | query | no | `AppViewTypes \| null` | Filter apps to only those supporting a specific view type, such as 'dashboard' or 'hub'. |  |
| `after` | query | no | `string \| null` | Returns the elements in the list that come after the specified cursor. |  |
| `before` | query | no | `string \| null` | Returns the elements in the list that come before the specified cursor. |  |
| `first` | query | no | `integer \| null` | Returns the first _n_ elements from the list. | 42 |
| `last` | query | no | `integer \| null` | Returns the last _n_ elements from the list. | 42 |

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
      "$ref": "#/components/schemas/AppListItem"
    description: A list of nodes.
  page_info:
    "$ref": "#/components/schemas/PageInfo"
    description: Information to aid in pagination.
required:
- data
- page_info
description: The connection type for PublicApp.
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
for await (const appListResponse of client.apps.list()) {
  console.log(appListResponse.id);
}
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
page = client.apps.list()
page = page.data[0]
print(page.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

page = whop.apps.list

puts(page)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  AppListItem:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the app.
        example: app_xxxxxxxxxxxxxx
      name:
        type: string
        description: The display name of this app shown on the app store and in experience
          navigation. Maximum 30 characters.
        example: Courses
      description:
        type:
        - string
        - 'null'
        description: A written description of what this app does, displayed on the
          app store listing page. Null if no description has been set.
        example: A comprehensive analytics dashboard for tracking revenue, members,
          and growth metrics.
      status:
        "$ref": "#/components/schemas/AppStatuses"
        description: The current visibility status of this app on the Whop app store.
          'live' means publicly discoverable, 'unlisted' means accessible only via
          direct link, and 'hidden' means not visible anywhere.
      base_url:
        type:
        - string
        - 'null'
        description: The production base URL where the app is hosted. Null if no base
          URL is configured.
        example: https://myapp.example.com
      domain_id:
        type: string
        description: The unique subdomain identifier for this app's proxied URL on
          the Whop platform. Forms the URL pattern https://{domain_id}.apps.whop.com.
        example: ab1c2d3e4f5g6h7i8j9k
      verified:
        type: boolean
        description: Whether this app has been verified by Whop. Verified apps are
          endorsed by Whop and displayed in the featured apps section of the app store.
      app_type:
        "$ref": "#/components/schemas/AppTypes"
        description: The target audience classification for this app (e.g., 'b2b_app',
          'b2c_app', 'company_app', 'component').
      origin:
        type:
        - string
        - 'null'
        description: The full origin URL for this app's proxied domain (e.g., 'https://myapp.apps.whop.com').
          Null if no proxy domain is configured.
      experience_path:
        type:
        - string
        - 'null'
        description: The URL path template for a specific view of this app, appended
          to the base domain (e.g., '/experiences/[experienceId]'). Null if the specified
          view type is not configured.
        example: "/experiences/[experienceId]"
      discover_path:
        type:
        - string
        - 'null'
        description: The URL path template for a specific view of this app, appended
          to the base domain (e.g., '/experiences/[experienceId]'). Null if the specified
          view type is not configured.
        example: "/experiences/[experienceId]"
      dashboard_path:
        type:
        - string
        - 'null'
        description: The URL path template for a specific view of this app, appended
          to the base domain (e.g., '/experiences/[experienceId]'). Null if the specified
          view type is not configured.
        example: "/experiences/[experienceId]"
      skills_path:
        type:
        - string
        - 'null'
        description: The URL path template for a specific view of this app, appended
          to the base domain (e.g., '/experiences/[experienceId]'). Null if the specified
          view type is not configured.
        example: "/experiences/[experienceId]"
      openapi_path:
        type:
        - string
        - 'null'
        description: The URL path template for a specific view of this app, appended
          to the base domain (e.g., '/experiences/[experienceId]'). Null if the specified
          view type is not configured.
        example: "/experiences/[experienceId]"
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
        description: The company that owns and publishes this app.
      icon:
        type:
        - object
        - 'null'
        properties:
          url:
            type:
            - string
            - 'null'
            description: A pre-optimized URL for rendering this attachment on the
              client. This should be used for displaying attachments in apps.
            example: https://media.whop.com/abc123/optimized.jpg
        required:
        - url
        description: The icon image for this app, displayed on the app store, product
          pages, checkout, and as the default icon for experiences using this app.
      creator:
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
        description: The user who created and owns the company that published this
          app.
    required:
    - id
    - name
    - description
    - status
    - base_url
    - domain_id
    - verified
    - app_type
    - origin
    - experience_path
    - discover_path
    - dashboard_path
    - skills_path
    - openapi_path
    - company
    - icon
    - creator
    description: An app is an integration built on Whop. Apps can serve consumers
      as experiences within products, or serve companies as business tools.
  AppOrder:
    type: string
    enum:
    - created_at
    - discoverable_at
    - total_installs_last_30_days
    - total_installs_last_7_days
    - time_spent
    - time_spent_last_24_hours
    - daily_active_users
    - ai_prompt_count
    - total_ai_cost_usd
    - total_ai_tokens
    - last_ai_prompt_at
    - ai_average_rating
    description: The order to fetch the apps in for discovery.
  AppStatuses:
    type: string
    enum:
    - live
    - unlisted
    - hidden
    description: The status of an experience interface
  AppTypes:
    type: string
    enum:
    - b2b_app
    - b2c_app
    - company_app
    - component
    description: The type of end-user an app is built for
  AppViewTypes:
    type: string
    enum:
    - hub
    - discover
    - dash
    - dashboard
    - analytics
    - skills
    - openapi
    description: The different types of an app view
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
  "/apps":
    get:
      tags:
      - Apps
      operationId: listApp
      summary: List apps
      description: Returns a paginated list of apps on the Whop platform, with optional
        filtering by company, type, view support, and search query.
      parameters:
      - name: app_type
        in: query
        required: false
        schema:
          oneOf:
          - "$ref": "#/components/schemas/AppTypes"
          - type: 'null'
          description: Filter apps by the type of end-user they are built for, such
            as consumer or business.
        explode: true
        style: form
      - name: company_id
        in: query
        required: false
        schema:
          type:
          - string
          - 'null'
          description: Filter apps to only those created by this company, starting
            with 'biz_'.
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
          description: 'The sort direction for results. Accepted values: asc, desc.'
        explode: true
        style: form
      - name: order
        in: query
        required: false
        schema:
          oneOf:
          - "$ref": "#/components/schemas/AppOrder"
          - type: 'null'
          description: The field to sort apps by. Defaults to discoverable_at descending,
            showing the most recently published apps first.
        explode: true
        style: form
      - name: query
        in: query
        required: false
        schema:
          type:
          - string
          - 'null'
          description: A search string to filter apps by name, such as 'chat' or 'analytics'.
        explode: true
        style: form
      - name: verified_apps_only
        in: query
        required: false
        schema:
          type:
          - boolean
          - 'null'
          description: Whether to only return apps that have been verified by Whop.
            Useful for populating a featured apps section.
        explode: true
        style: form
      - name: view_type
        in: query
        required: false
        schema:
          oneOf:
          - "$ref": "#/components/schemas/AppViewTypes"
          - type: 'null'
          description: Filter apps to only those supporting a specific view type,
            such as 'dashboard' or 'hub'.
        explode: true
        style: form
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
      x-group-title: Developer
      security:
      - bearerAuth: []
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
                      "$ref": "#/components/schemas/AppListItem"
                    description: A list of nodes.
                  page_info:
                    "$ref": "#/components/schemas/PageInfo"
                    description: Information to aid in pagination.
                required:
                - data
                - page_info
                description: The connection type for PublicApp.
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
          for await (const appListResponse of client.apps.list()) {
            console.log(appListResponse.id);
          }
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          page = client.apps.list()
          page = page.data[0]
          print(page.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          page = whop.apps.list

          puts(page)
components:
  schemas:
    AppListItem:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the app.
          example: app_xxxxxxxxxxxxxx
        name:
          type: string
          description: The display name of this app shown on the app store and in
            experience navigation. Maximum 30 characters.
          example: Courses
        description:
          type:
          - string
          - 'null'
          description: A written description of what this app does, displayed on the
            app store listing page. Null if no description has been set.
          example: A comprehensive analytics dashboard for tracking revenue, members,
            and growth metrics.
        status:
          "$ref": "#/components/schemas/AppStatuses"
          description: The current visibility status of this app on the Whop app store.
            'live' means publicly discoverable, 'unlisted' means accessible only via
            direct link, and 'hidden' means not visible anywhere.
        base_url:
          type:
          - string
          - 'null'
          description: The production base URL where the app is hosted. Null if no
            base URL is configured.
          example: https://myapp.example.com
        domain_id:
          type: string
          description: The unique subdomain identifier for this app's proxied URL
            on the Whop platform. Forms the URL pattern https://{domain_id}.apps.whop.com.
          example: ab1c2d3e4f5g6h7i8j9k
        verified:
          type: boolean
          description: Whether this app has been verified by Whop. Verified apps are
            endorsed by Whop and displayed in the featured apps section of the app
            store.
        app_type:
          "$ref": "#/components/schemas/AppTypes"
          description: The target audience classification for this app (e.g., 'b2b_app',
            'b2c_app', 'company_app', 'component').
        origin:
          type:
          - string
          - 'null'
          description: The full origin URL for this app's proxied domain (e.g., 'https://myapp.apps.whop.com').
            Null if no proxy domain is configured.
        experience_path:
          type:
          - string
          - 'null'
          description: The URL path template for a specific view of this app, appended
            to the base domain (e.g., '/experiences/[experienceId]'). Null if the
            specified view type is not configured.
          example: "/experiences/[experienceId]"
        discover_path:
          type:
          - string
          - 'null'
          description: The URL path template for a specific view of this app, appended
            to the base domain (e.g., '/experiences/[experienceId]'). Null if the
            specified view type is not configured.
          example: "/experiences/[experienceId]"
        dashboard_path:
          type:
          - string
          - 'null'
          description: The URL path template for a specific view of this app, appended
            to the base domain (e.g., '/experiences/[experienceId]'). Null if the
            specified view type is not configured.
          example: "/experiences/[experienceId]"
        skills_path:
          type:
          - string
          - 'null'
          description: The URL path template for a specific view of this app, appended
            to the base domain (e.g., '/experiences/[experienceId]'). Null if the
            specified view type is not configured.
          example: "/experiences/[experienceId]"
        openapi_path:
          type:
          - string
          - 'null'
          description: The URL path template for a specific view of this app, appended
            to the base domain (e.g., '/experiences/[experienceId]'). Null if the
            specified view type is not configured.
          example: "/experiences/[experienceId]"
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
          description: The company that owns and publishes this app.
        icon:
          type:
          - object
          - 'null'
          properties:
            url:
              type:
              - string
              - 'null'
              description: A pre-optimized URL for rendering this attachment on the
                client. This should be used for displaying attachments in apps.
              example: https://media.whop.com/abc123/optimized.jpg
          required:
          - url
          description: The icon image for this app, displayed on the app store, product
            pages, checkout, and as the default icon for experiences using this app.
        creator:
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
          description: The user who created and owns the company that published this
            app.
      required:
      - id
      - name
      - description
      - status
      - base_url
      - domain_id
      - verified
      - app_type
      - origin
      - experience_path
      - discover_path
      - dashboard_path
      - skills_path
      - openapi_path
      - company
      - icon
      - creator
      description: An app is an integration built on Whop. Apps can serve consumers
        as experiences within products, or serve companies as business tools.
    AppOrder:
      type: string
      enum:
      - created_at
      - discoverable_at
      - total_installs_last_30_days
      - total_installs_last_7_days
      - time_spent
      - time_spent_last_24_hours
      - daily_active_users
      - ai_prompt_count
      - total_ai_cost_usd
      - total_ai_tokens
      - last_ai_prompt_at
      - ai_average_rating
      description: The order to fetch the apps in for discovery.
    AppStatuses:
      type: string
      enum:
      - live
      - unlisted
      - hidden
      description: The status of an experience interface
    AppTypes:
      type: string
      enum:
      - b2b_app
      - b2c_app
      - company_app
      - component
      description: The type of end-user an app is built for
    AppViewTypes:
      type: string
      enum:
      - hub
      - discover
      - dash
      - dashboard
      - analytics
      - skills
      - openapi
      description: The different types of an app view
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
```
