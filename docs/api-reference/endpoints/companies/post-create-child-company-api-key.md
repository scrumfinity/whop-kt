# POST /companies/{parent_company_id}/api_keys — Create child company API key

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `POST`
- **Path:** `/companies/{parent_company_id}/api_keys`
- **Operation ID:** `createApiKeyCompany`
- **Tags:** `Companies`
- **Required bearer scopes:** _None documented_

## Description

Create an API key for a connected account (child company) owned by a parent company.

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `parent_company_id` | path | yes | `string` | The unique identifier of the parent platform company (e.g. 'biz_xxx'). | biz_xxxxxxxxxxxxxx |

## Request body

Required: **yes**
Content types: `application/json`

### `application/json` request schema

```yaml
type: object
properties:
  child_company_id:
    type: string
    description: The unique identifier of the connected account to create the API
      key for (e.g. 'biz_xxx').
    example: biz_yyyyyyyyyyyyyy
  name:
    type:
    - string
    - 'null'
    description: A human-readable name for the API key, such as 'Production API Key'.
  permissions:
    type:
    - array
    - 'null'
    items:
      type: object
      properties:
        actions:
          type: array
          items:
            type: string
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          description: Actions covered by this statement
        grant:
          type: boolean
          description: Whether the actions are granted or denied
        resources:
          type: array
          items:
            type: string
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          description: Resource identifiers. Can look like 'biz_xxxx' or 'biz_xxx|pass_*|exp_xxx'
            or 'biz_xxx|app_xxx' or 'biz_xxx|pass_xxx|exp_xxx' or 'biz_xxx|pass_xxx'
            or 'biz_xxx|pass_*'
      required:
      - actions
      - grant
      - resources
      description: Input for a single permissions statement
    description: Granular permission statements defining which actions this API key
      can perform. Either permissions or role must be provided.
  role:
    oneOf:
    - "$ref": "#/components/schemas/PermissionSystemRoles"
    - type: 'null'
    description: A system role to inherit permissions from (e.g. owner, admin, moderator).
      Either role or permissions must be provided.
required:
- child_company_id
description: Parameters for CreateChildCompanyApiKey
```


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
  id:
    type: string
    description: The unique identifier for the authorized api key.
  name:
    type:
    - string
    - 'null'
    description: A user set name to identify an API key
  secret_key:
    type: string
    description: The secret key used to authenticate requests. Only returned at creation
      time.
required:
- id
- name
- secret_key
description: An API key created for a child company, including the one-time secret
  key.
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

const response = await client.companies.createAPIKey('biz_xxxxxxxxxxxxxx', {
  child_company_id: 'biz_yyyyyyyyyyyyyy',
});

console.log(response.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
response = client.companies.create_api_key(
    parent_company_id="biz_xxxxxxxxxxxxxx",
    child_company_id="biz_yyyyyyyyyyyyyy",
)
print(response.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

response = whop.companies.create_api_key("biz_xxxxxxxxxxxxxx", child_company_id: "biz_yyyyyyyyyyyyyy")

puts(response)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  PermissionSystemRoles:
    type: string
    enum:
    - owner
    - admin
    - moderator
    - sales_manager
    - advertiser
    description: The different system roles that can be assigned.
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
  "/companies/{parent_company_id}/api_keys":
    post:
      tags:
      - Companies
      operationId: createApiKeyCompany
      summary: Create child company API key
      description: Create an API key for a connected account (child company) owned
        by a parent company.
      parameters:
      - name: parent_company_id
        in: path
        required: true
        description: The unique identifier of the parent platform company (e.g. 'biz_xxx').
        schema:
          type: string
          example: biz_xxxxxxxxxxxxxx
      x-group-title: Identity
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
                  id:
                    type: string
                    description: The unique identifier for the authorized api key.
                  name:
                    type:
                    - string
                    - 'null'
                    description: A user set name to identify an API key
                  secret_key:
                    type: string
                    description: The secret key used to authenticate requests. Only
                      returned at creation time.
                required:
                - id
                - name
                - secret_key
                description: An API key created for a child company, including the
                  one-time secret key.
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
                child_company_id:
                  type: string
                  description: The unique identifier of the connected account to create
                    the API key for (e.g. 'biz_xxx').
                  example: biz_yyyyyyyyyyyyyy
                name:
                  type:
                  - string
                  - 'null'
                  description: A human-readable name for the API key, such as 'Production
                    API Key'.
                permissions:
                  type:
                  - array
                  - 'null'
                  items:
                    type: object
                    properties:
                      actions:
                        type: array
                        items:
                          type: string
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        description: Actions covered by this statement
                      grant:
                        type: boolean
                        description: Whether the actions are granted or denied
                      resources:
                        type: array
                        items:
                          type: string
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        description: Resource identifiers. Can look like 'biz_xxxx'
                          or 'biz_xxx|pass_*|exp_xxx' or 'biz_xxx|app_xxx' or 'biz_xxx|pass_xxx|exp_xxx'
                          or 'biz_xxx|pass_xxx' or 'biz_xxx|pass_*'
                    required:
                    - actions
                    - grant
                    - resources
                    description: Input for a single permissions statement
                  description: Granular permission statements defining which actions
                    this API key can perform. Either permissions or role must be provided.
                role:
                  oneOf:
                  - "$ref": "#/components/schemas/PermissionSystemRoles"
                  - type: 'null'
                  description: A system role to inherit permissions from (e.g. owner,
                    admin, moderator). Either role or permissions must be provided.
              required:
              - child_company_id
              description: Parameters for CreateChildCompanyApiKey
      x-codeSamples:
      - lang: JavaScript
        source: |-
          import Whop from '@whop/sdk';

          const client = new Whop({
            apiKey: process.env['WHOP_API_KEY'], // This is the default and can be omitted
          });

          const response = await client.companies.createAPIKey('biz_xxxxxxxxxxxxxx', {
            child_company_id: 'biz_yyyyyyyyyyyyyy',
          });

          console.log(response.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          response = client.companies.create_api_key(
              parent_company_id="biz_xxxxxxxxxxxxxx",
              child_company_id="biz_yyyyyyyyyyyyyy",
          )
          print(response.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          response = whop.companies.create_api_key("biz_xxxxxxxxxxxxxx", child_company_id: "biz_yyyyyyyyyyyyyy")

          puts(response)
components:
  schemas:
    PermissionSystemRoles:
      type: string
      enum:
      - owner
      - admin
      - moderator
      - sales_manager
      - advertiser
      description: The different system roles that can be assigned.
```
