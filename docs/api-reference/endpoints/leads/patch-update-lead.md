# PATCH /leads/{id} — Update lead

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `PATCH`
- **Path:** `/leads/{id}`
- **Operation ID:** `updateLead`
- **Tags:** `Leads`
- **Required bearer scopes:** `lead:manage`, `member:email:read`, `access_pass:basic:read`, `member:basic:read`

## Description

Update the metadata or referrer information on an existing lead record.

Required permissions:
 - `lead:manage`
 - `member:email:read`
 - `access_pass:basic:read`
 - `member:basic:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `id` | path | yes | `string` | The unique identifier of the lead to update, starting with 'lead_'. | lead_xxxxxxxxxxxxx |

## Request body

Required: **no**
Content types: `application/json`

### `application/json` request schema

```yaml
type: object
properties:
  metadata:
    type:
    - object
    - 'null'
    additionalProperties: true
    description: A JSON object of custom metadata to set on the lead, replacing any
      existing metadata.
  referrer:
    type:
    - string
    - 'null'
    description: The updated referral URL for the lead, such as 'https://example.com/landing'.
required: []
description: Parameters for UpdateLead
```


## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `Lead` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/Lead"
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

const lead = await client.leads.update('lead_xxxxxxxxxxxxx');

console.log(lead.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
lead = client.leads.update(
    id="lead_xxxxxxxxxxxxx",
)
print(lead.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

lead = whop.leads.update("lead_xxxxxxxxxxxxx")

puts(lead)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  Lead:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the lead.
        example: lead_xxxxxxxxxxxxx
      created_at:
        type: string
        format: date-time
        description: The datetime the lead was created.
        example: '2023-12-01T05:00:00.401Z'
      updated_at:
        type: string
        format: date-time
        description: The datetime the lead was last updated.
        example: '2023-12-01T05:00:00.401Z'
      referrer:
        type:
        - string
        - 'null'
        description: The URL of the page that referred this lead to the company. Null
          if no referrer was captured.
        example: https://twitter.com/whabormarket
      metadata:
        type:
        - object
        - 'null'
        additionalProperties: true
        description: Custom key-value pairs attached to this lead. Null if no metadata
          was provided.
      user:
        type: object
        properties:
          id:
            type: string
            description: The unique identifier for the user.
            example: user_xxxxxxxxxxxxx
          email:
            type:
            - string
            - 'null'
            description: The user's email address. Requires the member:email:read
              permission to access. Null if not authorized.
            example: john.doe@example.com
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
        - email
        - name
        - username
        description: The user account associated with this lead.
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
        required:
        - id
        - title
        description: The product the lead expressed interest in. Null if the lead
          is not associated with a specific product.
      member:
        type:
        - object
        - 'null'
        properties:
          id:
            type: string
            description: The unique identifier for the company member.
        required:
        - id
        description: The company member record if this lead has converted into a paying
          customer. Null if the lead has not converted.
    required:
    - id
    - created_at
    - updated_at
    - referrer
    - metadata
    - user
    - product
    - member
    description: A prospective customer who has expressed interest in a company or
      product but has not yet purchased.
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
  "/leads/{id}":
    patch:
      tags:
      - Leads
      operationId: updateLead
      summary: Update lead
      description: |-
        Update the metadata or referrer information on an existing lead record.

        Required permissions:
         - `lead:manage`
         - `member:email:read`
         - `access_pass:basic:read`
         - `member:basic:read`
      parameters:
      - name: id
        in: path
        required: true
        description: The unique identifier of the lead to update, starting with 'lead_'.
        schema:
          type: string
          example: lead_xxxxxxxxxxxxx
      x-group-title: CRM
      security:
      - bearerAuth:
        - lead:manage
        - member:email:read
        - access_pass:basic:read
        - member:basic:read
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/Lead"
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
                metadata:
                  type:
                  - object
                  - 'null'
                  additionalProperties: true
                  description: A JSON object of custom metadata to set on the lead,
                    replacing any existing metadata.
                referrer:
                  type:
                  - string
                  - 'null'
                  description: The updated referral URL for the lead, such as 'https://example.com/landing'.
              required: []
              description: Parameters for UpdateLead
      x-codeSamples:
      - lang: JavaScript
        source: |-
          import Whop from '@whop/sdk';

          const client = new Whop({
            apiKey: process.env['WHOP_API_KEY'], // This is the default and can be omitted
          });

          const lead = await client.leads.update('lead_xxxxxxxxxxxxx');

          console.log(lead.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          lead = client.leads.update(
              id="lead_xxxxxxxxxxxxx",
          )
          print(lead.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          lead = whop.leads.update("lead_xxxxxxxxxxxxx")

          puts(lead)
components:
  schemas:
    Lead:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the lead.
          example: lead_xxxxxxxxxxxxx
        created_at:
          type: string
          format: date-time
          description: The datetime the lead was created.
          example: '2023-12-01T05:00:00.401Z'
        updated_at:
          type: string
          format: date-time
          description: The datetime the lead was last updated.
          example: '2023-12-01T05:00:00.401Z'
        referrer:
          type:
          - string
          - 'null'
          description: The URL of the page that referred this lead to the company.
            Null if no referrer was captured.
          example: https://twitter.com/whabormarket
        metadata:
          type:
          - object
          - 'null'
          additionalProperties: true
          description: Custom key-value pairs attached to this lead. Null if no metadata
            was provided.
        user:
          type: object
          properties:
            id:
              type: string
              description: The unique identifier for the user.
              example: user_xxxxxxxxxxxxx
            email:
              type:
              - string
              - 'null'
              description: The user's email address. Requires the member:email:read
                permission to access. Null if not authorized.
              example: john.doe@example.com
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
          - email
          - name
          - username
          description: The user account associated with this lead.
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
          required:
          - id
          - title
          description: The product the lead expressed interest in. Null if the lead
            is not associated with a specific product.
        member:
          type:
          - object
          - 'null'
          properties:
            id:
              type: string
              description: The unique identifier for the company member.
          required:
          - id
          description: The company member record if this lead has converted into a
            paying customer. Null if the lead has not converted.
      required:
      - id
      - created_at
      - updated_at
      - referrer
      - metadata
      - user
      - product
      - member
      description: A prospective customer who has expressed interest in a company
        or product but has not yet purchased.
```
