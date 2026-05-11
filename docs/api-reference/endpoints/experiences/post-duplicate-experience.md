# POST /experiences/{id}/duplicate — Duplicate experience

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `POST`
- **Path:** `/experiences/{id}/duplicate`
- **Operation ID:** `duplicateExperience`
- **Tags:** `Experiences`
- **Required bearer scopes:** `experience:create`

## Description

Duplicates an existing experience. The name will be copied, unless provided. The new experience will be attached to the same products as the original experience.
If duplicating a Forum or Chat experience, the new experience will have the same settings as the original experience, e.g. who can post, who can comment, etc.
No content, e.g. posts, messages, lessons from within the original experience will be copied.


Required permissions:
 - `experience:create`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `id` | path | yes | `string` | The unique identifier of the experience to duplicate. | exp_xxxxxxxxxxxxxx |

## Request body

Required: **no**
Content types: `application/json`

### `application/json` request schema

```yaml
type: object
properties:
  name:
    type:
    - string
    - 'null'
    description: The display name for the duplicated experience. Defaults to the original
      experience's name.
required: []
description: Parameters for DuplicateExperience
```


## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `Experience` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/Experience"
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

const experience = await client.experiences.duplicate('exp_xxxxxxxxxxxxxx');

console.log(experience.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
experience = client.experiences.duplicate(
    id="exp_xxxxxxxxxxxxxx",
)
print(experience.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

experience = whop.experiences.duplicate("exp_xxxxxxxxxxxxxx")

puts(experience)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  Experience:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the experience.
        example: exp_xxxxxxxxxxxxxx
      name:
        type: string
        description: The display name of this experience shown to users in the product
          navigation. Maximum 255 characters.
        example: Trading Signals Chat
      order:
        type:
        - string
        - 'null'
        description: The sort position of this experience within its section. Lower
          values appear first. Null if no position has been set.
        example: '1727606400000'
      is_public:
        type: boolean
        description: Whether this experience is publicly visible to all users, including
          those without a membership.
      created_at:
        type: string
        format: date-time
        description: The datetime the experience was created.
        example: '2023-12-01T05:00:00.401Z'
      app:
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
          icon:
            type:
            - object
            - 'null'
            properties:
              url:
                type:
                - string
                - 'null'
                description: A pre-optimized URL for rendering this attachment on
                  the client. This should be used for displaying attachments in apps.
                example: https://media.whop.com/abc123/optimized.jpg
            required:
            - url
            description: The icon image for this app, displayed on the app store,
              product pages, checkout, and as the default icon for experiences using
              this app.
        required:
        - id
        - name
        - icon
        description: The app that powers this experience, defining its interface and
          behavior.
      image:
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
        description: The custom logo image for this experience. Null if no custom
          logo has been uploaded.
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
          route:
            type: string
            description: The URL slug for the company's store page (e.g., 'pickaxe'
              in whop.com/pickaxe).
            example: pickaxe
        required:
        - id
        - title
        - route
        description: The company that owns this experience.
      products:
        type: array
        items:
          type: object
          properties:
            id:
              type: string
              description: The unique identifier for the product.
              example: prod_xxxxxxxxxxxxx
            route:
              type: string
              description: The URL slug used in the product's public link (e.g., 'my-product'
                in whop.com/company/my-product).
              example: pickaxe-analytics
            title:
              type: string
              description: The display name of the product shown to customers on the
                product page and in search results.
              example: Pickaxe Analytics
          required:
          - id
          - route
          - title
          description: A product is a digital good or service sold on Whop. Products
            contain plans for pricing and experiences for content delivery.
        description: The list of products this experience is attached to, which determines
          which customers have access. Empty if the experience is only visible to
          authorized company team members.
    required:
    - id
    - name
    - order
    - is_public
    - created_at
    - app
    - image
    - company
    - products
    description: An experience is a feature or content module within a product, such
      as a chat, course, or custom app.
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
  "/experiences/{id}/duplicate":
    post:
      tags:
      - Experiences
      operationId: duplicateExperience
      summary: Duplicate experience
      description: |-
        Duplicates an existing experience. The name will be copied, unless provided. The new experience will be attached to the same products as the original experience.
        If duplicating a Forum or Chat experience, the new experience will have the same settings as the original experience, e.g. who can post, who can comment, etc.
        No content, e.g. posts, messages, lessons from within the original experience will be copied.


        Required permissions:
         - `experience:create`
      parameters:
      - name: id
        in: path
        required: true
        description: The unique identifier of the experience to duplicate.
        schema:
          type: string
          example: exp_xxxxxxxxxxxxxx
      x-group-title: Engagement
      security:
      - bearerAuth:
        - experience:create
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/Experience"
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
                name:
                  type:
                  - string
                  - 'null'
                  description: The display name for the duplicated experience. Defaults
                    to the original experience's name.
              required: []
              description: Parameters for DuplicateExperience
      x-codeSamples:
      - lang: JavaScript
        source: |-
          import Whop from '@whop/sdk';

          const client = new Whop({
            apiKey: process.env['WHOP_API_KEY'], // This is the default and can be omitted
          });

          const experience = await client.experiences.duplicate('exp_xxxxxxxxxxxxxx');

          console.log(experience.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          experience = client.experiences.duplicate(
              id="exp_xxxxxxxxxxxxxx",
          )
          print(experience.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          experience = whop.experiences.duplicate("exp_xxxxxxxxxxxxxx")

          puts(experience)
components:
  schemas:
    Experience:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the experience.
          example: exp_xxxxxxxxxxxxxx
        name:
          type: string
          description: The display name of this experience shown to users in the product
            navigation. Maximum 255 characters.
          example: Trading Signals Chat
        order:
          type:
          - string
          - 'null'
          description: The sort position of this experience within its section. Lower
            values appear first. Null if no position has been set.
          example: '1727606400000'
        is_public:
          type: boolean
          description: Whether this experience is publicly visible to all users, including
            those without a membership.
        created_at:
          type: string
          format: date-time
          description: The datetime the experience was created.
          example: '2023-12-01T05:00:00.401Z'
        app:
          type: object
          properties:
            id:
              type: string
              description: The unique identifier for the app.
              example: app_xxxxxxxxxxxxxx
            name:
              type: string
              description: The display name of this app shown on the app store and
                in experience navigation. Maximum 30 characters.
              example: Courses
            icon:
              type:
              - object
              - 'null'
              properties:
                url:
                  type:
                  - string
                  - 'null'
                  description: A pre-optimized URL for rendering this attachment on
                    the client. This should be used for displaying attachments in
                    apps.
                  example: https://media.whop.com/abc123/optimized.jpg
              required:
              - url
              description: The icon image for this app, displayed on the app store,
                product pages, checkout, and as the default icon for experiences using
                this app.
          required:
          - id
          - name
          - icon
          description: The app that powers this experience, defining its interface
            and behavior.
        image:
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
          description: The custom logo image for this experience. Null if no custom
            logo has been uploaded.
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
            route:
              type: string
              description: The URL slug for the company's store page (e.g., 'pickaxe'
                in whop.com/pickaxe).
              example: pickaxe
          required:
          - id
          - title
          - route
          description: The company that owns this experience.
        products:
          type: array
          items:
            type: object
            properties:
              id:
                type: string
                description: The unique identifier for the product.
                example: prod_xxxxxxxxxxxxx
              route:
                type: string
                description: The URL slug used in the product's public link (e.g.,
                  'my-product' in whop.com/company/my-product).
                example: pickaxe-analytics
              title:
                type: string
                description: The display name of the product shown to customers on
                  the product page and in search results.
                example: Pickaxe Analytics
            required:
            - id
            - route
            - title
            description: A product is a digital good or service sold on Whop. Products
              contain plans for pricing and experiences for content delivery.
          description: The list of products this experience is attached to, which
            determines which customers have access. Empty if the experience is only
            visible to authorized company team members.
      required:
      - id
      - name
      - order
      - is_public
      - created_at
      - app
      - image
      - company
      - products
      description: An experience is a feature or content module within a product,
        such as a chat, course, or custom app.
```
