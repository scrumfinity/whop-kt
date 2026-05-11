# GET /companies/{id} — Retrieve company

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/companies/{id}`
- **Operation ID:** `retrieveCompany`
- **Tags:** `Companies`
- **Required bearer scopes:** `company:basic:read`

## Description

Retrieves the details of an existing company.

Required permissions:
 - `company:basic:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `id` | path | yes | `string` | The unique identifier or route slug of the company. | biz_xxxxxxxxxxxxxx |

## Request body

_No request body._

## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `Company` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/Company"
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

const company = await client.companies.retrieve('biz_xxxxxxxxxxxxxx');

console.log(company.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
company = client.companies.retrieve(
    "biz_xxxxxxxxxxxxxx",
)
print(company.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

company = whop.companies.retrieve("biz_xxxxxxxxxxxxxx")

puts(company)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  Company:
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
      description:
        type:
        - string
        - 'null'
        description: A promotional pitch written by the company creator, displayed
          to potential customers on the store page.
        example: Learn the fundamentals of data analytics with hands-on projects.
      verified:
        type: boolean
        description: Whether this company has been verified by Whop's trust and safety
          team.
      send_customer_emails:
        type: boolean
        description: Whether Whop sends transactional emails (receipts, updates) to
          customers on behalf of this company.
      created_at:
        type: string
        format: date-time
        description: The datetime the company was created.
        example: '2023-12-01T05:00:00.401Z'
      updated_at:
        type: string
        format: date-time
        description: The datetime the company was last updated.
        example: '2023-12-01T05:00:00.401Z'
      member_count:
        type: integer
        description: The total number of users who currently hold active memberships
          across all of this company's products.
        example: 42
      owner_user:
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
        description: The user who owns and has full administrative control over this
          company.
      route:
        type: string
        description: The URL slug for the company's store page (e.g., 'pickaxe' in
          whop.com/pickaxe).
        example: pickaxe
      logo:
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
        description: The company's logo.
      published_reviews_count:
        type: integer
        description: The total number of published customer reviews across all products
          for this company.
        example: 42
      metadata:
        type:
        - object
        - 'null'
        additionalProperties: true
        description: A key-value JSON object of custom metadata for this company,
          managed by the platform that created the account.
      target_audience:
        type:
        - string
        - 'null'
        description: The target audience for the company. Null if not set.
      social_links:
        type: array
        items:
          type: object
          properties:
            id:
              type: string
              description: The unique identifier for the social link.
              example: soci_xxxxxxxxxxxxx
            url:
              type: string
              description: The URL of the social media profile or external link.
              example: https://x.com/whop
            website:
              "$ref": "#/components/schemas/SocialLinkWebsites"
              description: The website
          required:
          - id
          - url
          - website
          description: A social link attached to a resource on the site.
        description: The list of social media accounts and external links associated
          with this company.
      affiliate_instructions:
        type:
        - string
        - 'null'
        description: Guidelines and instructions provided to affiliates explaining
          how to promote this company's products.
        example: Share your unique link on social media to earn 20% commission.
      featured_affiliate_product:
        type:
        - object
        - 'null'
        properties:
          id:
            type: string
            description: The unique identifier for the product.
            example: prod_xxxxxxxxxxxxx
          name:
            type: string
            description: The display name of the product shown to customers. Maximum
              50 characters.
        required:
        - id
        - name
        description: The product featured for affiliates to promote on this company's
          affiliate page. Null if none is configured.
    required:
    - id
    - title
    - description
    - verified
    - send_customer_emails
    - created_at
    - updated_at
    - member_count
    - owner_user
    - route
    - logo
    - published_reviews_count
    - metadata
    - target_audience
    - social_links
    - affiliate_instructions
    - featured_affiliate_product
    description: A company is a seller on Whop. Companies own products, manage members,
      and receive payouts.
  SocialLinkWebsites:
    type: string
    enum:
    - x
    - instagram
    - facebook
    - tiktok
    - youtube
    - linkedin
    - twitch
    - website
    - custom
    description: The different websites you can have social links for
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
  "/companies/{id}":
    get:
      tags:
      - Companies
      operationId: retrieveCompany
      summary: Retrieve company
      description: |-
        Retrieves the details of an existing company.

        Required permissions:
         - `company:basic:read`
      parameters:
      - name: id
        in: path
        required: true
        description: The unique identifier or route slug of the company.
        schema:
          type: string
          example: biz_xxxxxxxxxxxxxx
      x-group-title: Identity
      security:
      - bearerAuth:
        - company:basic:read
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/Company"
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

          const company = await client.companies.retrieve('biz_xxxxxxxxxxxxxx');

          console.log(company.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          company = client.companies.retrieve(
              "biz_xxxxxxxxxxxxxx",
          )
          print(company.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          company = whop.companies.retrieve("biz_xxxxxxxxxxxxxx")

          puts(company)
components:
  schemas:
    Company:
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
        description:
          type:
          - string
          - 'null'
          description: A promotional pitch written by the company creator, displayed
            to potential customers on the store page.
          example: Learn the fundamentals of data analytics with hands-on projects.
        verified:
          type: boolean
          description: Whether this company has been verified by Whop's trust and
            safety team.
        send_customer_emails:
          type: boolean
          description: Whether Whop sends transactional emails (receipts, updates)
            to customers on behalf of this company.
        created_at:
          type: string
          format: date-time
          description: The datetime the company was created.
          example: '2023-12-01T05:00:00.401Z'
        updated_at:
          type: string
          format: date-time
          description: The datetime the company was last updated.
          example: '2023-12-01T05:00:00.401Z'
        member_count:
          type: integer
          description: The total number of users who currently hold active memberships
            across all of this company's products.
          example: 42
        owner_user:
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
          description: The user who owns and has full administrative control over
            this company.
        route:
          type: string
          description: The URL slug for the company's store page (e.g., 'pickaxe'
            in whop.com/pickaxe).
          example: pickaxe
        logo:
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
          description: The company's logo.
        published_reviews_count:
          type: integer
          description: The total number of published customer reviews across all products
            for this company.
          example: 42
        metadata:
          type:
          - object
          - 'null'
          additionalProperties: true
          description: A key-value JSON object of custom metadata for this company,
            managed by the platform that created the account.
        target_audience:
          type:
          - string
          - 'null'
          description: The target audience for the company. Null if not set.
        social_links:
          type: array
          items:
            type: object
            properties:
              id:
                type: string
                description: The unique identifier for the social link.
                example: soci_xxxxxxxxxxxxx
              url:
                type: string
                description: The URL of the social media profile or external link.
                example: https://x.com/whop
              website:
                "$ref": "#/components/schemas/SocialLinkWebsites"
                description: The website
            required:
            - id
            - url
            - website
            description: A social link attached to a resource on the site.
          description: The list of social media accounts and external links associated
            with this company.
        affiliate_instructions:
          type:
          - string
          - 'null'
          description: Guidelines and instructions provided to affiliates explaining
            how to promote this company's products.
          example: Share your unique link on social media to earn 20% commission.
        featured_affiliate_product:
          type:
          - object
          - 'null'
          properties:
            id:
              type: string
              description: The unique identifier for the product.
              example: prod_xxxxxxxxxxxxx
            name:
              type: string
              description: The display name of the product shown to customers. Maximum
                50 characters.
          required:
          - id
          - name
          description: The product featured for affiliates to promote on this company's
            affiliate page. Null if none is configured.
      required:
      - id
      - title
      - description
      - verified
      - send_customer_emails
      - created_at
      - updated_at
      - member_count
      - owner_user
      - route
      - logo
      - published_reviews_count
      - metadata
      - target_audience
      - social_links
      - affiliate_instructions
      - featured_affiliate_product
      description: A company is a seller on Whop. Companies own products, manage members,
        and receive payouts.
    SocialLinkWebsites:
      type: string
      enum:
      - x
      - instagram
      - facebook
      - tiktok
      - youtube
      - linkedin
      - twitch
      - website
      - custom
      description: The different websites you can have social links for
```
