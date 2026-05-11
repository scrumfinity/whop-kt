# POST /app_builds — Create app build

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `POST`
- **Path:** `/app_builds`
- **Operation ID:** `createAppBuild`
- **Tags:** `App builds`
- **Required bearer scopes:** `developer:manage_builds`

## Description

Upload a new build artifact for an app. The build must include a compiled code bundle for the specified platform.

Required permissions:
 - `developer:manage_builds`

## Parameters

_No path, query, header, or cookie parameters._

## Request body

Required: **yes**
Content types: `application/json`

### `application/json` request schema

```yaml
type: object
properties:
  ai_prompt_id:
    type:
    - string
    - 'null'
    description: The identifier of the AI prompt that generated this build, if applicable.
    example: prmt_xxxxxxxxxxxxx
  app_id:
    type:
    - string
    - 'null'
    description: The unique identifier of the app to create the build for. Defaults
      to the app associated with the current API key.
    example: app_xxxxxxxxxxxxxx
  attachment:
    type: object
    properties:
      id:
        type: string
        description: The ID of an existing file object.
    required:
    - id
    description: The build file to upload. For iOS and Android, this should be a .zip
      archive containing a main_js_bundle.hbc file and an optional assets folder.
      For web, this should be a JavaScript file.
    title: FileInputWithId
  checksum:
    type: string
    description: A client-generated checksum of the build file, used to verify file
      integrity when unpacked on a device.
  platform:
    "$ref": "#/components/schemas/AppBuildPlatforms"
    description: 'The target platform for the build. Accepted values: ios, android,
      web.'
  supported_app_view_types:
    type:
    - array
    - 'null'
    items:
      "$ref": "#/components/schemas/AppViewTypes"
    description: The view types this build supports. A build can support multiple
      view types but should only list the ones its code implements.
required:
- attachment
- checksum
- platform
description: Parameters for CreateAppBuild
```


## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `AppBuild` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/AppBuild"
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

const appBuild = await client.appBuilds.create({
  attachment: { id: 'file_xxxxxxxxxxxxx' },
  checksum: 'checksum',
  platform: 'ios',
});

console.log(appBuild.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
app_build = client.app_builds.create(
    attachment={
        "id": "file_xxxxxxxxxxxxx"
    },
    checksum="checksum",
    platform="ios",
)
print(app_build.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

app_build = whop.app_builds.create(attachment: {id: "file_xxxxxxxxxxxxx"}, checksum: "checksum", platform: :ios)

puts(app_build)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  AppBuild:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the app build.
        example: apbu_xxxxxxxxxxxxx
      platform:
        "$ref": "#/components/schemas/AppBuildPlatforms"
        description: The target platform for this build.
      file_url:
        type: string
        description: A URL to download the app build as a .zip archive.
        example: https://cdn.whop.com/builds/abc123.zip
      created_at:
        type: string
        format: date-time
        description: The datetime the app build was created.
        example: '2023-12-01T05:00:00.401Z'
      status:
        "$ref": "#/components/schemas/AppBuildStatuses"
        description: The current review status of this build.
      checksum:
        type: string
        description: A SHA-256 hash of the uploaded build file, generated by the client
          and used to verify file integrity.
        example: e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855
      supported_app_view_types:
        type: array
        items:
          "$ref": "#/components/schemas/AppViewTypes"
        description: The list of view types this build supports, as declared by the
          developer.
      review_message:
        type:
        - string
        - 'null'
        description: Feedback from the reviewer explaining why the build was rejected.
          Null if the build has not been reviewed or was approved.
        example: App crashes on launch. Please fix and resubmit.
      is_production:
        type: boolean
        description: Whether this build is the currently active production build for
          its platform.
    required:
    - id
    - platform
    - file_url
    - created_at
    - status
    - checksum
    - supported_app_view_types
    - review_message
    - is_production
    description: A versioned build artifact for a Whop React Native App, submitted
      for review and deployment to a specific platform.
  AppBuildPlatforms:
    type: string
    enum:
    - ios
    - android
    - web
    description: The different platforms an app build can target.
  AppBuildStatuses:
    type: string
    enum:
    - draft
    - pending
    - approved
    - rejected
    description: The different statuses an AppBuild can be in.
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
  "/app_builds":
    post:
      tags:
      - App builds
      operationId: createAppBuild
      summary: Create app build
      description: |-
        Upload a new build artifact for an app. The build must include a compiled code bundle for the specified platform.

        Required permissions:
         - `developer:manage_builds`
      parameters: []
      x-group-title: Developer
      security:
      - bearerAuth:
        - developer:manage_builds
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/AppBuild"
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
        required: true
        content:
          application/json:
            schema:
              type: object
              properties:
                ai_prompt_id:
                  type:
                  - string
                  - 'null'
                  description: The identifier of the AI prompt that generated this
                    build, if applicable.
                  example: prmt_xxxxxxxxxxxxx
                app_id:
                  type:
                  - string
                  - 'null'
                  description: The unique identifier of the app to create the build
                    for. Defaults to the app associated with the current API key.
                  example: app_xxxxxxxxxxxxxx
                attachment:
                  type: object
                  properties:
                    id:
                      type: string
                      description: The ID of an existing file object.
                  required:
                  - id
                  description: The build file to upload. For iOS and Android, this
                    should be a .zip archive containing a main_js_bundle.hbc file
                    and an optional assets folder. For web, this should be a JavaScript
                    file.
                  title: FileInputWithId
                checksum:
                  type: string
                  description: A client-generated checksum of the build file, used
                    to verify file integrity when unpacked on a device.
                platform:
                  "$ref": "#/components/schemas/AppBuildPlatforms"
                  description: 'The target platform for the build. Accepted values:
                    ios, android, web.'
                supported_app_view_types:
                  type:
                  - array
                  - 'null'
                  items:
                    "$ref": "#/components/schemas/AppViewTypes"
                  description: The view types this build supports. A build can support
                    multiple view types but should only list the ones its code implements.
              required:
              - attachment
              - checksum
              - platform
              description: Parameters for CreateAppBuild
      x-codeSamples:
      - lang: JavaScript
        source: |-
          import Whop from '@whop/sdk';

          const client = new Whop({
            apiKey: process.env['WHOP_API_KEY'], // This is the default and can be omitted
          });

          const appBuild = await client.appBuilds.create({
            attachment: { id: 'file_xxxxxxxxxxxxx' },
            checksum: 'checksum',
            platform: 'ios',
          });

          console.log(appBuild.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          app_build = client.app_builds.create(
              attachment={
                  "id": "file_xxxxxxxxxxxxx"
              },
              checksum="checksum",
              platform="ios",
          )
          print(app_build.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          app_build = whop.app_builds.create(attachment: {id: "file_xxxxxxxxxxxxx"}, checksum: "checksum", platform: :ios)

          puts(app_build)
components:
  schemas:
    AppBuild:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the app build.
          example: apbu_xxxxxxxxxxxxx
        platform:
          "$ref": "#/components/schemas/AppBuildPlatforms"
          description: The target platform for this build.
        file_url:
          type: string
          description: A URL to download the app build as a .zip archive.
          example: https://cdn.whop.com/builds/abc123.zip
        created_at:
          type: string
          format: date-time
          description: The datetime the app build was created.
          example: '2023-12-01T05:00:00.401Z'
        status:
          "$ref": "#/components/schemas/AppBuildStatuses"
          description: The current review status of this build.
        checksum:
          type: string
          description: A SHA-256 hash of the uploaded build file, generated by the
            client and used to verify file integrity.
          example: e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855
        supported_app_view_types:
          type: array
          items:
            "$ref": "#/components/schemas/AppViewTypes"
          description: The list of view types this build supports, as declared by
            the developer.
        review_message:
          type:
          - string
          - 'null'
          description: Feedback from the reviewer explaining why the build was rejected.
            Null if the build has not been reviewed or was approved.
          example: App crashes on launch. Please fix and resubmit.
        is_production:
          type: boolean
          description: Whether this build is the currently active production build
            for its platform.
      required:
      - id
      - platform
      - file_url
      - created_at
      - status
      - checksum
      - supported_app_view_types
      - review_message
      - is_production
      description: A versioned build artifact for a Whop React Native App, submitted
        for review and deployment to a specific platform.
    AppBuildPlatforms:
      type: string
      enum:
      - ios
      - android
      - web
      description: The different platforms an app build can target.
    AppBuildStatuses:
      type: string
      enum:
      - draft
      - pending
      - approved
      - rejected
      description: The different statuses an AppBuild can be in.
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
```
