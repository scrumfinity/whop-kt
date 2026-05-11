# GET /products — List products

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/products`
- **Operation ID:** `listProduct`
- **Tags:** `Products`
- **Required bearer scopes:** `access_pass:basic:read`

## Description

Returns a paginated list of products belonging to a company, with optional filtering by type, visibility, and creation date.

Required permissions:
 - `access_pass:basic:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `after` | query | no | `string \| null` | Returns the elements in the list that come after the specified cursor. |  |
| `before` | query | no | `string \| null` | Returns the elements in the list that come before the specified cursor. |  |
| `first` | query | no | `integer \| null` | Returns the first _n_ elements from the list. | 42 |
| `last` | query | no | `integer \| null` | Returns the last _n_ elements from the list. | 42 |
| `company_id` | query | yes | `string` | The unique identifier of the company to list products for. | biz_xxxxxxxxxxxxxx |
| `product_types` | query | no | `array<AccessPassTypes>` | Filter to only products matching these type classifications. |  |
| `visibilities` | query | no | `array<VisibilityFilter>` | Filter to only products matching these visibility states. |  |
| `order` | query | no | `AccessPassOrder \| null` | The field to sort results by. Defaults to created_at. |  |
| `direction` | query | no | `Direction \| null` | The sort direction for results. Defaults to descending. |  |
| `created_before` | query | no | `string \| null / date-time` | Only return products created before this timestamp. | 2023-12-01T05:00:00.401Z |
| `created_after` | query | no | `string \| null / date-time` | Only return products created after this timestamp. | 2023-12-01T05:00:00.401Z |

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
      "$ref": "#/components/schemas/ProductListItem"
    description: A list of nodes.
  page_info:
    "$ref": "#/components/schemas/PageInfo"
    description: Information to aid in pagination.
required:
- data
- page_info
description: The connection type for PublicAccessPass.
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
for await (const productListItem of client.products.list({ company_id: 'biz_xxxxxxxxxxxxxx' })) {
  console.log(productListItem.id);
}
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
page = client.products.list(
    company_id="biz_xxxxxxxxxxxxxx",
)
page = page.data[0]
print(page.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

page = whop.products.list(company_id: "biz_xxxxxxxxxxxxxx")

puts(page)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  AccessPassOrder:
    type: string
    enum:
    - active_memberships_count
    - created_at
    - usd_gmv
    - usd_gmv_30_days
    description: The ways a relation of AccessPasses can be ordered
  AccessPassTypes:
    type: string
    enum:
    - regular
    - app
    - experience_upsell
    - api_only
    description: The different types an product can be. Only use 'regular'. The rest
      are for internal use
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
  ProductListItem:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the product.
        example: prod_xxxxxxxxxxxxx
      title:
        type: string
        description: The display name of the product shown to customers on the product
          page and in search results.
        example: Pickaxe Analytics
      visibility:
        "$ref": "#/components/schemas/Visibility"
        description: Controls whether the product is visible to customers. When set
          to 'hidden', the product is only accessible via direct link.
      headline:
        type:
        - string
        - 'null'
        description: A short marketing headline displayed prominently on the product's
          product page.
        example: Real-time data analytics for creators
      verified:
        type: boolean
        description: Whether this company has been verified by Whop's trust and safety
          team.
      created_at:
        type: string
        format: date-time
        description: The datetime the product was created.
        example: '2023-12-01T05:00:00.401Z'
      updated_at:
        type: string
        format: date-time
        description: The datetime the product was last updated.
        example: '2023-12-01T05:00:00.401Z'
      member_count:
        type: integer
        description: The number of users who currently hold an active membership to
          this product. Returns 0 if the company has disabled public member counts.
        example: 42
      route:
        type: string
        description: The URL slug used in the product's public link (e.g., 'my-product'
          in whop.com/company/my-product).
        example: pickaxe-analytics
      published_reviews_count:
        type: integer
        description: The total number of published customer reviews for this product's
          company.
        example: 42
      external_identifier:
        type:
        - string
        - 'null'
        description: A unique identifier used to create or update products via the
          API. When provided on product creation endpoints, an existing product with
          this identifier will be updated instead of creating a new one.
        example: ext_prod_12345
    required:
    - id
    - title
    - visibility
    - headline
    - verified
    - created_at
    - updated_at
    - member_count
    - route
    - published_reviews_count
    - external_identifier
    description: A product is a digital good or service sold on Whop. Products contain
      plans for pricing and experiences for content delivery.
  Visibility:
    type: string
    enum:
    - visible
    - hidden
    - archived
    - quick_link
    description: Visibility of a resource
  VisibilityFilter:
    type: string
    enum:
    - visible
    - hidden
    - archived
    - quick_link
    - all
    - not_quick_link
    - not_archived
    description: The different levels of visibility for resources
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
  "/products":
    get:
      tags:
      - Products
      operationId: listProduct
      summary: List products
      description: |-
        Returns a paginated list of products belonging to a company, with optional filtering by type, visibility, and creation date.

        Required permissions:
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
          description: The unique identifier of the company to list products for.
          example: biz_xxxxxxxxxxxxxx
        explode: true
        style: form
      - name: product_types
        in: query
        required: false
        schema:
          type:
          - array
          - 'null'
          items:
            "$ref": "#/components/schemas/AccessPassTypes"
          description: Filter to only products matching these type classifications.
        explode: true
        style: form
      - name: visibilities
        in: query
        required: false
        schema:
          type:
          - array
          - 'null'
          items:
            "$ref": "#/components/schemas/VisibilityFilter"
          description: Filter to only products matching these visibility states.
        explode: true
        style: form
      - name: order
        in: query
        required: false
        schema:
          oneOf:
          - "$ref": "#/components/schemas/AccessPassOrder"
          - type: 'null'
          description: The field to sort results by. Defaults to created_at.
        explode: true
        style: form
      - name: direction
        in: query
        required: false
        schema:
          oneOf:
          - "$ref": "#/components/schemas/Direction"
          - type: 'null'
          description: The sort direction for results. Defaults to descending.
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
          description: Only return products created before this timestamp.
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
          description: Only return products created after this timestamp.
          example: '2023-12-01T05:00:00.401Z'
        explode: true
        style: form
      x-group-title: Payins
      security:
      - bearerAuth:
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
                      "$ref": "#/components/schemas/ProductListItem"
                    description: A list of nodes.
                  page_info:
                    "$ref": "#/components/schemas/PageInfo"
                    description: Information to aid in pagination.
                required:
                - data
                - page_info
                description: The connection type for PublicAccessPass.
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
          for await (const productListItem of client.products.list({ company_id: 'biz_xxxxxxxxxxxxxx' })) {
            console.log(productListItem.id);
          }
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          page = client.products.list(
              company_id="biz_xxxxxxxxxxxxxx",
          )
          page = page.data[0]
          print(page.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          page = whop.products.list(company_id: "biz_xxxxxxxxxxxxxx")

          puts(page)
components:
  schemas:
    AccessPassOrder:
      type: string
      enum:
      - active_memberships_count
      - created_at
      - usd_gmv
      - usd_gmv_30_days
      description: The ways a relation of AccessPasses can be ordered
    AccessPassTypes:
      type: string
      enum:
      - regular
      - app
      - experience_upsell
      - api_only
      description: The different types an product can be. Only use 'regular'. The
        rest are for internal use
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
    ProductListItem:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the product.
          example: prod_xxxxxxxxxxxxx
        title:
          type: string
          description: The display name of the product shown to customers on the product
            page and in search results.
          example: Pickaxe Analytics
        visibility:
          "$ref": "#/components/schemas/Visibility"
          description: Controls whether the product is visible to customers. When
            set to 'hidden', the product is only accessible via direct link.
        headline:
          type:
          - string
          - 'null'
          description: A short marketing headline displayed prominently on the product's
            product page.
          example: Real-time data analytics for creators
        verified:
          type: boolean
          description: Whether this company has been verified by Whop's trust and
            safety team.
        created_at:
          type: string
          format: date-time
          description: The datetime the product was created.
          example: '2023-12-01T05:00:00.401Z'
        updated_at:
          type: string
          format: date-time
          description: The datetime the product was last updated.
          example: '2023-12-01T05:00:00.401Z'
        member_count:
          type: integer
          description: The number of users who currently hold an active membership
            to this product. Returns 0 if the company has disabled public member counts.
          example: 42
        route:
          type: string
          description: The URL slug used in the product's public link (e.g., 'my-product'
            in whop.com/company/my-product).
          example: pickaxe-analytics
        published_reviews_count:
          type: integer
          description: The total number of published customer reviews for this product's
            company.
          example: 42
        external_identifier:
          type:
          - string
          - 'null'
          description: A unique identifier used to create or update products via the
            API. When provided on product creation endpoints, an existing product
            with this identifier will be updated instead of creating a new one.
          example: ext_prod_12345
      required:
      - id
      - title
      - visibility
      - headline
      - verified
      - created_at
      - updated_at
      - member_count
      - route
      - published_reviews_count
      - external_identifier
      description: A product is a digital good or service sold on Whop. Products contain
        plans for pricing and experiences for content delivery.
    Visibility:
      type: string
      enum:
      - visible
      - hidden
      - archived
      - quick_link
      description: Visibility of a resource
    VisibilityFilter:
      type: string
      enum:
      - visible
      - hidden
      - archived
      - quick_link
      - all
      - not_quick_link
      - not_archived
      description: The different levels of visibility for resources
```
