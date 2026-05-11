# POST /authorized_users — Create authorized user

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `POST`
- **Path:** `/authorized_users`
- **Operation ID:** `createAuthorizedUser`
- **Tags:** `Authorized users`
- **Required bearer scopes:** `authorized_user:create`, `member:email:read`

## Description

Add a new authorized user to a company.

Required permissions:
 - `authorized_user:create`
 - `member:email:read`

## Parameters

_No path, query, header, or cookie parameters._

## Request body

Required: **yes**
Content types: `application/json`

### `application/json` request schema

```yaml
type: object
properties:
  company_id:
    type: string
    description: The ID of the company to add the authorized user to.
    example: biz_xxxxxxxxxxxxxx
  role:
    "$ref": "#/components/schemas/AuthorizedUserRoles"
    description: 'The role to assign to the authorized user within the company. Supported
      roles: ''moderator'', ''sales_manager''.'
  send_emails:
    type:
    - boolean
    - 'null'
    description: Whether to send notification emails to the user on creation.
  user_id:
    type: string
    description: The ID of the user to add as an authorized user.
    example: user_xxxxxxxxxxxxx
required:
- company_id
- role
- user_id
description: Parameters for CreateAuthorizedUser
```


## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `AuthorizedUser` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/AuthorizedUser"
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

const authorizedUser = await client.authorizedUsers.create({
  company_id: 'biz_xxxxxxxxxxxxxx',
  role: 'owner',
  user_id: 'user_xxxxxxxxxxxxx',
});

console.log(authorizedUser.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
authorized_user = client.authorized_users.create(
    company_id="biz_xxxxxxxxxxxxxx",
    role="owner",
    user_id="user_xxxxxxxxxxxxx",
)
print(authorized_user.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

authorized_user = whop.authorized_users.create(
  company_id: "biz_xxxxxxxxxxxxxx",
  role: :owner,
  user_id: "user_xxxxxxxxxxxxx"
)

puts(authorized_user)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  AuthorizedUser:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the authorized user.
        example: ausr_xxxxxxxxxxxxx
      role:
        "$ref": "#/components/schemas/AuthorizedUserRoles"
        description: The permission role assigned to this authorized user within the
          company.
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
          email:
            type:
            - string
            - 'null'
            description: The user's email address. Requires the member:email:read
              permission to access. Null if not authorized.
            example: john.doe@example.com
        required:
        - id
        - name
        - username
        - email
        description: The user account linked to this authorized user record.
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
        description: The company this authorized user has access to.
    required:
    - id
    - role
    - user
    - company
    description: A user who has been granted administrative access to manage a company's
      dashboard and settings.
  AuthorizedUserRoles:
    type: string
    enum:
    - owner
    - admin
    - sales_manager
    - moderator
    - advertiser
    - app_manager
    - support
    - manager
    - custom
    description: Possible roles an authorized user can have
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
  "/authorized_users":
    post:
      tags:
      - Authorized users
      operationId: createAuthorizedUser
      summary: Create authorized user
      description: |-
        Add a new authorized user to a company.

        Required permissions:
         - `authorized_user:create`
         - `member:email:read`
      parameters: []
      x-group-title: Identity
      security:
      - bearerAuth:
        - authorized_user:create
        - member:email:read
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/AuthorizedUser"
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
                company_id:
                  type: string
                  description: The ID of the company to add the authorized user to.
                  example: biz_xxxxxxxxxxxxxx
                role:
                  "$ref": "#/components/schemas/AuthorizedUserRoles"
                  description: 'The role to assign to the authorized user within the
                    company. Supported roles: ''moderator'', ''sales_manager''.'
                send_emails:
                  type:
                  - boolean
                  - 'null'
                  description: Whether to send notification emails to the user on
                    creation.
                user_id:
                  type: string
                  description: The ID of the user to add as an authorized user.
                  example: user_xxxxxxxxxxxxx
              required:
              - company_id
              - role
              - user_id
              description: Parameters for CreateAuthorizedUser
      x-codeSamples:
      - lang: JavaScript
        source: |-
          import Whop from '@whop/sdk';

          const client = new Whop({
            apiKey: process.env['WHOP_API_KEY'], // This is the default and can be omitted
          });

          const authorizedUser = await client.authorizedUsers.create({
            company_id: 'biz_xxxxxxxxxxxxxx',
            role: 'owner',
            user_id: 'user_xxxxxxxxxxxxx',
          });

          console.log(authorizedUser.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          authorized_user = client.authorized_users.create(
              company_id="biz_xxxxxxxxxxxxxx",
              role="owner",
              user_id="user_xxxxxxxxxxxxx",
          )
          print(authorized_user.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          authorized_user = whop.authorized_users.create(
            company_id: "biz_xxxxxxxxxxxxxx",
            role: :owner,
            user_id: "user_xxxxxxxxxxxxx"
          )

          puts(authorized_user)
components:
  schemas:
    AuthorizedUser:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the authorized user.
          example: ausr_xxxxxxxxxxxxx
        role:
          "$ref": "#/components/schemas/AuthorizedUserRoles"
          description: The permission role assigned to this authorized user within
            the company.
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
            email:
              type:
              - string
              - 'null'
              description: The user's email address. Requires the member:email:read
                permission to access. Null if not authorized.
              example: john.doe@example.com
          required:
          - id
          - name
          - username
          - email
          description: The user account linked to this authorized user record.
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
          description: The company this authorized user has access to.
      required:
      - id
      - role
      - user
      - company
      description: A user who has been granted administrative access to manage a company's
        dashboard and settings.
    AuthorizedUserRoles:
      type: string
      enum:
      - owner
      - admin
      - sales_manager
      - moderator
      - advertiser
      - app_manager
      - support
      - manager
      - custom
      description: Possible roles an authorized user can have
```
