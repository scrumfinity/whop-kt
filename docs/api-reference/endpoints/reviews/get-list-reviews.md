# GET /reviews — List reviews

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/reviews`
- **Operation ID:** `listReview`
- **Tags:** `Reviews`
- **Required bearer scopes:** _None documented_

## Description

Returns a paginated list of customer reviews for a specific product, with optional filtering by star rating and creation date.

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `after` | query | no | `string \| null` | Returns the elements in the list that come after the specified cursor. |  |
| `before` | query | no | `string \| null` | Returns the elements in the list that come before the specified cursor. |  |
| `first` | query | no | `integer \| null` | Returns the first _n_ elements from the list. | 42 |
| `last` | query | no | `integer \| null` | Returns the last _n_ elements from the list. | 42 |
| `product_id` | query | yes | `string` | The unique identifier of the product to list reviews for. | prod_xxxxxxxxxxxxx |
| `min_stars` | query | no | `integer \| null` | The minimum star rating to include in results, from 1 to 5 inclusive. | 1 |
| `max_stars` | query | no | `integer \| null` | The maximum star rating to include in results, from 1 to 5 inclusive. | 5 |
| `created_before` | query | no | `string \| null / date-time` | Only return reviews created before this timestamp. | 2023-12-01T05:00:00.401Z |
| `created_after` | query | no | `string \| null / date-time` | Only return reviews created after this timestamp. | 2023-12-01T05:00:00.401Z |

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
      "$ref": "#/components/schemas/ReviewListItem"
    description: A list of nodes.
  page_info:
    "$ref": "#/components/schemas/PageInfo"
    description: Information to aid in pagination.
required:
- data
- page_info
description: The connection type for Review.
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
for await (const reviewListResponse of client.reviews.list({ product_id: 'prod_xxxxxxxxxxxxx' })) {
  console.log(reviewListResponse.id);
}
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
page = client.reviews.list(
    product_id="prod_xxxxxxxxxxxxx",
)
page = page.data[0]
print(page.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

page = whop.reviews.list(product_id: "prod_xxxxxxxxxxxxx")

puts(page)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
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
  ReviewListItem:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the review.
        example: rev_xxxxxxxxxxxxxx
      title:
        type:
        - string
        - 'null'
        description: A short summary title for the review. Null if the reviewer did
          not provide one.
        example: Amazing community and tools
      description:
        type:
        - string
        - 'null'
        description: The body text of the review containing the user's detailed feedback.
          Returns an empty string if no description was provided.
        example: Great product, really helped me grow my audience.
      stars:
        type: integer
        description: The star rating given by the reviewer, from 1 to 5.
        minimum: 1
        maximum: 5
        example: 5
      status:
        "$ref": "#/components/schemas/ReviewStatus"
        description: The current moderation status of the review.
      paid_for_product:
        type:
        - boolean
        - 'null'
        description: Whether the reviewer paid for the product. Null if the payment
          status is unknown.
      created_at:
        type: string
        format: date-time
        description: The datetime the review was created.
        example: '2023-12-01T05:00:00.401Z'
      updated_at:
        type: string
        format: date-time
        description: The datetime the review was last updated.
        example: '2023-12-01T05:00:00.401Z'
      published_at:
        type:
        - string
        - 'null'
        format: date-time
        description: The timestamp of when the review was published. Null if the review
          has not been published yet.
        example: '2023-12-01T05:00:00.401Z'
      joined_at:
        type:
        - string
        - 'null'
        format: date-time
        description: The timestamp of when the reviewer first joined the product.
          Null if unknown.
        example: '2023-12-01T05:00:00.401Z'
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
        required:
        - id
        - name
        - username
        description: The user account of the person who wrote this review.
      attachments:
        type: array
        items:
          type: object
          properties:
            id:
              type: string
              description: Represents a unique identifier that is Base64 obfuscated.
                It is often used to refetch an object or as key for a cache. The ID
                type appears in a JSON response as a String; however, it is not intended
                to be human-readable. When expected as an input type, any string (such
                as `"VXNlci0xMA=="`) or integer (such as `4`) input value will be
                accepted as an ID.
            filename:
              type:
              - string
              - 'null'
              description: The original filename of the uploaded attachment, including
                its file extension.
              example: document.pdf
            content_type:
              type:
              - string
              - 'null'
              description: The MIME type of the uploaded file (e.g., image/jpeg, video/mp4,
                audio/mpeg).
              example: image/jpeg
            url:
              type:
              - string
              - 'null'
              description: A pre-optimized URL for rendering this attachment on the
                client. This should be used for displaying attachments in apps.
              example: https://media.whop.com/abc123/optimized.jpg
          required:
          - id
          - filename
          - content_type
          - url
          description: Represents an image attachment
        description: A list of files and media attached to the review.
    required:
    - id
    - title
    - description
    - stars
    - status
    - paid_for_product
    - created_at
    - updated_at
    - published_at
    - joined_at
    - user
    - attachments
    description: A user-submitted review of a company, including a star rating and
      optional text feedback.
  ReviewStatus:
    type: string
    enum:
    - pending
    - published
    - removed
    description: The statuses a review can have
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
  "/reviews":
    get:
      tags:
      - Reviews
      operationId: listReview
      summary: List reviews
      description: Returns a paginated list of customer reviews for a specific product,
        with optional filtering by star rating and creation date.
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
      - name: product_id
        in: query
        required: true
        schema:
          type: string
          description: The unique identifier of the product to list reviews for.
          example: prod_xxxxxxxxxxxxx
        explode: true
        style: form
      - name: min_stars
        in: query
        required: false
        schema:
          type:
          - integer
          - 'null'
          description: The minimum star rating to include in results, from 1 to 5
            inclusive.
          minimum: 1
          maximum: 5
          example: 1
        explode: true
        style: form
      - name: max_stars
        in: query
        required: false
        schema:
          type:
          - integer
          - 'null'
          description: The maximum star rating to include in results, from 1 to 5
            inclusive.
          minimum: 1
          maximum: 5
          example: 5
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
          description: Only return reviews created before this timestamp.
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
          description: Only return reviews created after this timestamp.
          example: '2023-12-01T05:00:00.401Z'
        explode: true
        style: form
      x-group-title: CRM
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
                      "$ref": "#/components/schemas/ReviewListItem"
                    description: A list of nodes.
                  page_info:
                    "$ref": "#/components/schemas/PageInfo"
                    description: Information to aid in pagination.
                required:
                - data
                - page_info
                description: The connection type for Review.
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
          for await (const reviewListResponse of client.reviews.list({ product_id: 'prod_xxxxxxxxxxxxx' })) {
            console.log(reviewListResponse.id);
          }
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          page = client.reviews.list(
              product_id="prod_xxxxxxxxxxxxx",
          )
          page = page.data[0]
          print(page.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          page = whop.reviews.list(product_id: "prod_xxxxxxxxxxxxx")

          puts(page)
components:
  schemas:
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
    ReviewListItem:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the review.
          example: rev_xxxxxxxxxxxxxx
        title:
          type:
          - string
          - 'null'
          description: A short summary title for the review. Null if the reviewer
            did not provide one.
          example: Amazing community and tools
        description:
          type:
          - string
          - 'null'
          description: The body text of the review containing the user's detailed
            feedback. Returns an empty string if no description was provided.
          example: Great product, really helped me grow my audience.
        stars:
          type: integer
          description: The star rating given by the reviewer, from 1 to 5.
          minimum: 1
          maximum: 5
          example: 5
        status:
          "$ref": "#/components/schemas/ReviewStatus"
          description: The current moderation status of the review.
        paid_for_product:
          type:
          - boolean
          - 'null'
          description: Whether the reviewer paid for the product. Null if the payment
            status is unknown.
        created_at:
          type: string
          format: date-time
          description: The datetime the review was created.
          example: '2023-12-01T05:00:00.401Z'
        updated_at:
          type: string
          format: date-time
          description: The datetime the review was last updated.
          example: '2023-12-01T05:00:00.401Z'
        published_at:
          type:
          - string
          - 'null'
          format: date-time
          description: The timestamp of when the review was published. Null if the
            review has not been published yet.
          example: '2023-12-01T05:00:00.401Z'
        joined_at:
          type:
          - string
          - 'null'
          format: date-time
          description: The timestamp of when the reviewer first joined the product.
            Null if unknown.
          example: '2023-12-01T05:00:00.401Z'
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
          required:
          - id
          - name
          - username
          description: The user account of the person who wrote this review.
        attachments:
          type: array
          items:
            type: object
            properties:
              id:
                type: string
                description: Represents a unique identifier that is Base64 obfuscated.
                  It is often used to refetch an object or as key for a cache. The
                  ID type appears in a JSON response as a String; however, it is not
                  intended to be human-readable. When expected as an input type, any
                  string (such as `"VXNlci0xMA=="`) or integer (such as `4`) input
                  value will be accepted as an ID.
              filename:
                type:
                - string
                - 'null'
                description: The original filename of the uploaded attachment, including
                  its file extension.
                example: document.pdf
              content_type:
                type:
                - string
                - 'null'
                description: The MIME type of the uploaded file (e.g., image/jpeg,
                  video/mp4, audio/mpeg).
                example: image/jpeg
              url:
                type:
                - string
                - 'null'
                description: A pre-optimized URL for rendering this attachment on
                  the client. This should be used for displaying attachments in apps.
                example: https://media.whop.com/abc123/optimized.jpg
            required:
            - id
            - filename
            - content_type
            - url
            description: Represents an image attachment
          description: A list of files and media attached to the review.
      required:
      - id
      - title
      - description
      - stars
      - status
      - paid_for_product
      - created_at
      - updated_at
      - published_at
      - joined_at
      - user
      - attachments
      description: A user-submitted review of a company, including a star rating and
        optional text feedback.
    ReviewStatus:
      type: string
      enum:
      - pending
      - published
      - removed
      description: The statuses a review can have
```
