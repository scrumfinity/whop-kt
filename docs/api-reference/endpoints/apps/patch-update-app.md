# PATCH /apps/{id} — Update app

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `PATCH`
- **Path:** `/apps/{id}`
- **Operation ID:** `updateApp`
- **Tags:** `Apps`
- **Required bearer scopes:** `developer:update_app`, `developer:manage_api_key`

## Description

Update the settings, metadata, or status of an existing app on the Whop developer platform.

Required permissions:
 - `developer:update_app`
 - `developer:manage_api_key`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `id` | path | yes | `string` | The unique identifier of the app to update, starting with 'app_'. | app_xxxxxxxxxxxxxx |

## Request body

Required: **no**
Content types: `application/json`

### `application/json` request schema

```yaml
type: object
properties:
  app_store_description:
    type:
    - string
    - 'null'
    description: The detailed description shown on the app store's in-depth app view
      page.
  app_type:
    oneOf:
    - "$ref": "#/components/schemas/AppTypes"
    - type: 'null'
    description: The type of end-user the app is built for, such as consumer or business.
  base_url:
    type:
    - string
    - 'null'
    description: The base production URL where the app is hosted, such as 'https://myapp.example.com'.
    example: https://example.com/path
  dashboard_path:
    type:
    - string
    - 'null'
    description: The URL path for the company dashboard view of the app, such as '/dashboard'.
  description:
    type:
    - string
    - 'null'
    description: A short description of the app shown in listings and search results.
  discover_path:
    type:
    - string
    - 'null'
    description: The URL path for the discover view of the app, such as '/discover'.
  experience_path:
    type:
    - string
    - 'null'
    description: The URL path for the member-facing hub view of the app, such as '/experiences/[experienceId]'.
  icon:
    type:
    - object
    - 'null'
    properties:
      id:
        type: string
        description: The ID of an existing file object.
    required:
    - id
    description: The icon image for the app, used in listings and navigation.
    title: FileInputWithId
  name:
    type:
    - string
    - 'null'
    description: The display name for the app, shown to users on the app store and
      product pages.
  oauth_client_type:
    oneOf:
    - "$ref": "#/components/schemas/AppOauthClientTypes"
    - type: 'null'
    description: How this app authenticates at the OAuth token endpoint.
  openapi_path:
    type:
    - string
    - 'null'
    description: The URL path to the OpenAPI spec file of the app, such as '/assets/openapi.json'.
  redirect_uris:
    type:
    - array
    - 'null'
    items:
      type: string
      description: Represents textual data as UTF-8 character sequences. This type
        is most often used by GraphQL to represent free-form human-readable text.
    description: The whitelisted OAuth callback URLs that users are redirected to
      after authorizing the app
  required_scopes:
    type:
    - array
    - 'null'
    items:
      "$ref": "#/components/schemas/AppValidScopes"
    description: The permission scopes the app will request from users when they install
      it.
  skills_path:
    type:
    - string
    - 'null'
    description: The URL path to the skills directory of the app, such as '/assets/skills/'.
  status:
    oneOf:
    - "$ref": "#/components/schemas/AppStatuses"
    - type: 'null'
    description: Controls the app's visibility. Set to 'live' to publish on Whop discovery
      (requires name, icon, and description). Set to 'unlisted' to hide from discovery
      while keeping the app accessible via direct link.
required: []
description: Parameters for UpdateApp
```


## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `App` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/App"
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

const app = await client.apps.update('app_xxxxxxxxxxxxxx');

console.log(app.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
app = client.apps.update(
    id="app_xxxxxxxxxxxxxx",
)
print(app.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

app = whop.apps.update("app_xxxxxxxxxxxxxx")

puts(app)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  App:
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
      requested_permissions:
        type: array
        items:
          type: object
          properties:
            permission_action:
              type: object
              properties:
                action:
                  type: string
                  description: The identifier of the action.
                name:
                  type: string
                  description: The human readable name of the action.
              required:
              - action
              - name
              description: The action that the app will request off of users when
                a user installs the app.
            is_required:
              type: boolean
              description: Whether the action is required for the app to function.
            justification:
              type: string
              description: The reason for requesting the action.
          required:
          - permission_action
          - is_required
          - justification
          description: A permission that the app requests from the admin of a company
            during the oauth flow.
        description: The list of permissions this app requests when installed, including
          both required and optional permissions with justifications.
      stats:
        type:
        - object
        - 'null'
        properties:
          dau:
            type: integer
            description: The number of unique users who have spent time in this app
              in the last 24 hours. Returns 0 if no usage data is available.
            example: 42
          mau:
            type: integer
            description: The number of unique users who have spent time in this app
              in the last 28 days. Returns 0 if no usage data is available.
            example: 42
          time_spent_last24_hours:
            type: integer
            description: The total time, in seconds, that all users have spent in
              this app over the last 24 hours. Returns 0 if no usage data is available.
            example: 42
          wau:
            type: integer
            description: The number of unique users who have spent time in this app
              in the last 7 days. Returns 0 if no usage data is available.
            example: 42
        required:
        - dau
        - mau
        - time_spent_last24_hours
        - wau
        description: Aggregate usage statistics for this app, including daily, weekly,
          and monthly active user counts.
      api_key:
        type:
        - object
        - 'null'
        properties:
          id:
            type: string
            description: The unique identifier for the private api key.
          token:
            type: string
            description: This is the API key used to authenticate requests
          created_at:
            type: string
            format: date-time
            description: The datetime the private api key was created.
            example: '2023-12-01T05:00:00.401Z'
        required:
        - id
        - token
        - created_at
        description: The API key used to authenticate requests on behalf of this app.
          Null if no API key has been generated. Requires the 'developer:manage_api_key'
          permission.
      redirect_uris:
        type: array
        items:
          type: string
          description: Represents textual data as UTF-8 character sequences. This
            type is most often used by GraphQL to represent free-form human-readable
            text.
        description: The whitelisted OAuth callback URLs that users are redirected
          to after authorizing the app.
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
    - requested_permissions
    - stats
    - api_key
    - redirect_uris
    description: An app is an integration built on Whop. Apps can serve consumers
      as experiences within products, or serve companies as business tools.
  AppOauthClientTypes:
    type: string
    enum:
    - public
    - confidential
    description: How this app authenticates at the OAuth token endpoint.
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
  AppValidScopes:
    type: string
    const: read_user
    description: These are the scopes an app can request on behalf of a user
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
  "/apps/{id}":
    patch:
      tags:
      - Apps
      operationId: updateApp
      summary: Update app
      description: |-
        Update the settings, metadata, or status of an existing app on the Whop developer platform.

        Required permissions:
         - `developer:update_app`
         - `developer:manage_api_key`
      parameters:
      - name: id
        in: path
        required: true
        description: The unique identifier of the app to update, starting with 'app_'.
        schema:
          type: string
          example: app_xxxxxxxxxxxxxx
      x-group-title: Developer
      security:
      - bearerAuth:
        - developer:update_app
        - developer:manage_api_key
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/App"
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
        required: false
        content:
          application/json:
            schema:
              type: object
              properties:
                app_store_description:
                  type:
                  - string
                  - 'null'
                  description: The detailed description shown on the app store's in-depth
                    app view page.
                app_type:
                  oneOf:
                  - "$ref": "#/components/schemas/AppTypes"
                  - type: 'null'
                  description: The type of end-user the app is built for, such as
                    consumer or business.
                base_url:
                  type:
                  - string
                  - 'null'
                  description: The base production URL where the app is hosted, such
                    as 'https://myapp.example.com'.
                  example: https://example.com/path
                dashboard_path:
                  type:
                  - string
                  - 'null'
                  description: The URL path for the company dashboard view of the
                    app, such as '/dashboard'.
                description:
                  type:
                  - string
                  - 'null'
                  description: A short description of the app shown in listings and
                    search results.
                discover_path:
                  type:
                  - string
                  - 'null'
                  description: The URL path for the discover view of the app, such
                    as '/discover'.
                experience_path:
                  type:
                  - string
                  - 'null'
                  description: The URL path for the member-facing hub view of the
                    app, such as '/experiences/[experienceId]'.
                icon:
                  type:
                  - object
                  - 'null'
                  properties:
                    id:
                      type: string
                      description: The ID of an existing file object.
                  required:
                  - id
                  description: The icon image for the app, used in listings and navigation.
                  title: FileInputWithId
                name:
                  type:
                  - string
                  - 'null'
                  description: The display name for the app, shown to users on the
                    app store and product pages.
                oauth_client_type:
                  oneOf:
                  - "$ref": "#/components/schemas/AppOauthClientTypes"
                  - type: 'null'
                  description: How this app authenticates at the OAuth token endpoint.
                openapi_path:
                  type:
                  - string
                  - 'null'
                  description: The URL path to the OpenAPI spec file of the app, such
                    as '/assets/openapi.json'.
                redirect_uris:
                  type:
                  - array
                  - 'null'
                  items:
                    type: string
                    description: Represents textual data as UTF-8 character sequences.
                      This type is most often used by GraphQL to represent free-form
                      human-readable text.
                  description: The whitelisted OAuth callback URLs that users are
                    redirected to after authorizing the app
                required_scopes:
                  type:
                  - array
                  - 'null'
                  items:
                    "$ref": "#/components/schemas/AppValidScopes"
                  description: The permission scopes the app will request from users
                    when they install it.
                skills_path:
                  type:
                  - string
                  - 'null'
                  description: The URL path to the skills directory of the app, such
                    as '/assets/skills/'.
                status:
                  oneOf:
                  - "$ref": "#/components/schemas/AppStatuses"
                  - type: 'null'
                  description: Controls the app's visibility. Set to 'live' to publish
                    on Whop discovery (requires name, icon, and description). Set
                    to 'unlisted' to hide from discovery while keeping the app accessible
                    via direct link.
              required: []
              description: Parameters for UpdateApp
      x-codeSamples:
      - lang: JavaScript
        source: |-
          import Whop from '@whop/sdk';

          const client = new Whop({
            apiKey: process.env['WHOP_API_KEY'], // This is the default and can be omitted
          });

          const app = await client.apps.update('app_xxxxxxxxxxxxxx');

          console.log(app.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          app = client.apps.update(
              id="app_xxxxxxxxxxxxxx",
          )
          print(app.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          app = whop.apps.update("app_xxxxxxxxxxxxxx")

          puts(app)
components:
  schemas:
    App:
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
        requested_permissions:
          type: array
          items:
            type: object
            properties:
              permission_action:
                type: object
                properties:
                  action:
                    type: string
                    description: The identifier of the action.
                  name:
                    type: string
                    description: The human readable name of the action.
                required:
                - action
                - name
                description: The action that the app will request off of users when
                  a user installs the app.
              is_required:
                type: boolean
                description: Whether the action is required for the app to function.
              justification:
                type: string
                description: The reason for requesting the action.
            required:
            - permission_action
            - is_required
            - justification
            description: A permission that the app requests from the admin of a company
              during the oauth flow.
          description: The list of permissions this app requests when installed, including
            both required and optional permissions with justifications.
        stats:
          type:
          - object
          - 'null'
          properties:
            dau:
              type: integer
              description: The number of unique users who have spent time in this
                app in the last 24 hours. Returns 0 if no usage data is available.
              example: 42
            mau:
              type: integer
              description: The number of unique users who have spent time in this
                app in the last 28 days. Returns 0 if no usage data is available.
              example: 42
            time_spent_last24_hours:
              type: integer
              description: The total time, in seconds, that all users have spent in
                this app over the last 24 hours. Returns 0 if no usage data is available.
              example: 42
            wau:
              type: integer
              description: The number of unique users who have spent time in this
                app in the last 7 days. Returns 0 if no usage data is available.
              example: 42
          required:
          - dau
          - mau
          - time_spent_last24_hours
          - wau
          description: Aggregate usage statistics for this app, including daily, weekly,
            and monthly active user counts.
        api_key:
          type:
          - object
          - 'null'
          properties:
            id:
              type: string
              description: The unique identifier for the private api key.
            token:
              type: string
              description: This is the API key used to authenticate requests
            created_at:
              type: string
              format: date-time
              description: The datetime the private api key was created.
              example: '2023-12-01T05:00:00.401Z'
          required:
          - id
          - token
          - created_at
          description: The API key used to authenticate requests on behalf of this
            app. Null if no API key has been generated. Requires the 'developer:manage_api_key'
            permission.
        redirect_uris:
          type: array
          items:
            type: string
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          description: The whitelisted OAuth callback URLs that users are redirected
            to after authorizing the app.
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
      - requested_permissions
      - stats
      - api_key
      - redirect_uris
      description: An app is an integration built on Whop. Apps can serve consumers
        as experiences within products, or serve companies as business tools.
    AppOauthClientTypes:
      type: string
      enum:
      - public
      - confidential
      description: How this app authenticates at the OAuth token endpoint.
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
    AppValidScopes:
      type: string
      const: read_user
      description: These are the scopes an app can request on behalf of a user
```
