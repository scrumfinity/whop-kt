# PATCH /products/{id} — Update product

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `PATCH`
- **Path:** `/products/{id}`
- **Operation ID:** `updateProduct`
- **Tags:** `Products`
- **Required bearer scopes:** `access_pass:update`, `access_pass:basic:read`

## Description

Update a product's title, description, visibility, and other settings.

Required permissions:
 - `access_pass:update`
 - `access_pass:basic:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `id` | path | yes | `string` | The unique identifier of the product to update. | prod_xxxxxxxxxxxxx |

## Request body

Required: **no**
Content types: `application/json`

### `application/json` request schema

```yaml
type: object
properties:
  collect_shipping_address:
    type:
    - boolean
    - 'null'
    description: Whether the checkout flow collects a shipping address from the customer.
  custom_cta:
    oneOf:
    - "$ref": "#/components/schemas/CustomCtas"
    - type: 'null'
    description: The call-to-action button label displayed on the product's purchase
      page (e.g., join, buy, subscribe).
  custom_cta_url:
    type:
    - string
    - 'null'
    description: A URL that the call-to-action button links to instead of the default
      checkout flow.
  custom_statement_descriptor:
    type:
    - string
    - 'null'
    description: A custom text label that appears on the customer's bank statement.
      Must be 5-22 characters, contain at least one letter, and not contain <, >,
      \, ', or " characters.
  description:
    type:
    - string
    - 'null'
    description: A written description of the product displayed on its product page.
  gallery_images:
    type:
    - array
    - 'null'
    items:
      type: object
      properties:
        id:
          type: string
          description: The ID of an existing file object.
      required:
      - id
      description: Input for an attachment
      title: FileInputWithId
    description: The gallery images for the product.
  global_affiliate_percentage:
    type:
    - number
    - 'null'
    description: The commission rate as a percentage that affiliates earn through
      the global affiliate program.
    example: 6.9
  global_affiliate_status:
    oneOf:
    - "$ref": "#/components/schemas/GlobalAffiliateStatuses"
    - type: 'null'
    description: The enrollment status of this product in the global affiliate program.
  headline:
    type:
    - string
    - 'null'
    description: A short marketing headline displayed prominently on the product page.
  member_affiliate_percentage:
    type:
    - number
    - 'null'
    description: The commission rate as a percentage that members earn through the
      member affiliate program.
    example: 6.9
  member_affiliate_status:
    oneOf:
    - "$ref": "#/components/schemas/GlobalAffiliateStatuses"
    - type: 'null'
    description: The enrollment status of this product in the member affiliate program.
  product_tax_code_id:
    type:
    - string
    - 'null'
    description: The unique identifier of the tax classification code to apply to
      this product.
    example: ptc_xxxxxxxxxxxxxx
  redirect_purchase_url:
    type:
    - string
    - 'null'
    description: A URL to redirect the customer to after completing a purchase.
  route:
    type:
    - string
    - 'null'
    description: The URL slug for the product's public link.
  send_welcome_message:
    type:
    - boolean
    - 'null'
    description: Whether to send an automated welcome message via support chat when
      a user joins this product.
  store_page_config:
    type:
    - object
    - 'null'
    properties:
      custom_cta:
        type:
        - string
        - 'null'
        description: Custom call-to-action text for the product's store page.
      show_price:
        type:
        - boolean
        - 'null'
        description: Whether or not to show the price on the product's store page.
    required: []
    description: Layout and display configuration for this product on the company's
      store page.
  title:
    type:
    - string
    - 'null'
    description: The display name of the product. Maximum 80 characters.
  visibility:
    oneOf:
    - "$ref": "#/components/schemas/Visibility"
    - type: 'null'
    description: Whether the product is visible to customers. When set to hidden,
      the product is only accessible via direct link.
required: []
description: Parameters for UpdateAccessPass
```


## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `Product` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/Product"
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

const product = await client.products.update('prod_xxxxxxxxxxxxx');

console.log(product.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
product = client.products.update(
    id="prod_xxxxxxxxxxxxx",
)
print(product.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

product = whop.products.update("prod_xxxxxxxxxxxxx")

puts(product)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  CustomCtas:
    type: string
    enum:
    - get_access
    - join
    - order_now
    - shop_now
    - call_now
    - donate_now
    - contact_us
    - sign_up
    - subscribe
    - purchase
    - get_offer
    - apply_now
    - complete_order
    description: The different types of custom CTAs that can be selected.
  GlobalAffiliateStatuses:
    type: string
    enum:
    - enabled
    - disabled
    description: The different statuses of the global affiliate program for a product.
  Product:
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
      description:
        type:
        - string
        - 'null'
        description: A brief summary of what the product offers, displayed on product
          pages and search results.
        example: Track your revenue, members, and growth in real time.
      custom_cta:
        "$ref": "#/components/schemas/CustomCtas"
        description: The call-to-action button label displayed on the product's purchase
          page (e.g., 'join', 'buy', 'subscribe').
      custom_cta_url:
        type:
        - string
        - 'null'
        description: An optional URL that the call-to-action button links to instead
          of the default checkout flow. Null if no custom URL is set.
        example: https://example.com/signup
      custom_statement_descriptor:
        type:
        - string
        - 'null'
        description: A custom text label that appears on the customer's bank or credit
          card statement for purchases of this product. Maximum 22 characters, including
          the required prefix WHOP*.
        example: PICKAXE
      global_affiliate_percentage:
        type:
        - number
        - 'null'
        description: The commission rate (as a percentage) that affiliates earn on
          sales through the Whop marketplace global affiliate program. Null if the
          program is not active.
        example: 6.9
      global_affiliate_status:
        "$ref": "#/components/schemas/GlobalAffiliateStatuses"
        description: The enrollment status of this product in the Whop marketplace
          global affiliate program.
      member_affiliate_percentage:
        type:
        - number
        - 'null'
        description: The commission rate (as a percentage) that existing members earn
          when referring new customers through the member affiliate program. Null
          if the program is not active.
        example: 6.9
      member_affiliate_status:
        "$ref": "#/components/schemas/GlobalAffiliateStatuses"
        description: The enrollment status of this product in the member affiliate
          program.
      gallery_images:
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
            url:
              type:
              - string
              - 'null'
              description: A pre-optimized URL for rendering this attachment on the
                client. This should be used for displaying attachments in apps.
              example: https://media.whop.com/abc123/optimized.jpg
          required:
          - id
          - url
          description: Represents an image attachment
        description: The gallery images for this product, ordered by position.
      product_tax_code:
        type:
        - object
        - 'null'
        properties:
          id:
            type: string
            description: The unique identifier for the product tax code.
            example: ptc_xxxxxxxxxxxxxx
          name:
            type: string
            description: The human-readable name of this tax classification (e.g.,
              'Digital - SaaS').
            example: Digital - SaaS
          product_type:
            "$ref": "#/components/schemas/ProductTaxCodeProductTypes"
            description: The broad product category this tax code covers (e.g., physical
              goods, digital services).
        required:
        - id
        - name
        - product_type
        description: The tax classification code applied to purchases of this product
          for sales tax calculation. Null if no tax code is assigned.
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
        description: The user who owns the company that sells this product.
      company:
        type: object
        properties:
          id:
            type: string
            description: The unique identifier for the company.
            example: biz_xxxxxxxxxxxxxx
          route:
            type: string
            description: The URL slug for the company's store page (e.g., 'pickaxe'
              in whop.com/pickaxe).
            example: pickaxe
          title:
            type: string
            description: The display name of the company shown to customers.
            example: Pickaxe
        required:
        - id
        - route
        - title
        description: The company this product belongs to.
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
    - description
    - custom_cta
    - custom_cta_url
    - custom_statement_descriptor
    - global_affiliate_percentage
    - global_affiliate_status
    - member_affiliate_percentage
    - member_affiliate_status
    - gallery_images
    - product_tax_code
    - owner_user
    - company
    description: A product is a digital good or service sold on Whop. Products contain
      plans for pricing and experiences for content delivery.
  ProductTaxCodeProductTypes:
    type: string
    enum:
    - physical
    - digital
    - services
    description: The product_type of the ProductTaxCode
  Visibility:
    type: string
    enum:
    - visible
    - hidden
    - archived
    - quick_link
    description: Visibility of a resource
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
  "/products/{id}":
    patch:
      tags:
      - Products
      operationId: updateProduct
      summary: Update product
      description: |-
        Update a product's title, description, visibility, and other settings.

        Required permissions:
         - `access_pass:update`
         - `access_pass:basic:read`
      parameters:
      - name: id
        in: path
        required: true
        description: The unique identifier of the product to update.
        schema:
          type: string
          example: prod_xxxxxxxxxxxxx
      x-group-title: Payins
      security:
      - bearerAuth:
        - access_pass:update
        - access_pass:basic:read
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/Product"
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
                collect_shipping_address:
                  type:
                  - boolean
                  - 'null'
                  description: Whether the checkout flow collects a shipping address
                    from the customer.
                custom_cta:
                  oneOf:
                  - "$ref": "#/components/schemas/CustomCtas"
                  - type: 'null'
                  description: The call-to-action button label displayed on the product's
                    purchase page (e.g., join, buy, subscribe).
                custom_cta_url:
                  type:
                  - string
                  - 'null'
                  description: A URL that the call-to-action button links to instead
                    of the default checkout flow.
                custom_statement_descriptor:
                  type:
                  - string
                  - 'null'
                  description: A custom text label that appears on the customer's
                    bank statement. Must be 5-22 characters, contain at least one
                    letter, and not contain <, >, \, ', or " characters.
                description:
                  type:
                  - string
                  - 'null'
                  description: A written description of the product displayed on its
                    product page.
                gallery_images:
                  type:
                  - array
                  - 'null'
                  items:
                    type: object
                    properties:
                      id:
                        type: string
                        description: The ID of an existing file object.
                    required:
                    - id
                    description: Input for an attachment
                    title: FileInputWithId
                  description: The gallery images for the product.
                global_affiliate_percentage:
                  type:
                  - number
                  - 'null'
                  description: The commission rate as a percentage that affiliates
                    earn through the global affiliate program.
                  example: 6.9
                global_affiliate_status:
                  oneOf:
                  - "$ref": "#/components/schemas/GlobalAffiliateStatuses"
                  - type: 'null'
                  description: The enrollment status of this product in the global
                    affiliate program.
                headline:
                  type:
                  - string
                  - 'null'
                  description: A short marketing headline displayed prominently on
                    the product page.
                member_affiliate_percentage:
                  type:
                  - number
                  - 'null'
                  description: The commission rate as a percentage that members earn
                    through the member affiliate program.
                  example: 6.9
                member_affiliate_status:
                  oneOf:
                  - "$ref": "#/components/schemas/GlobalAffiliateStatuses"
                  - type: 'null'
                  description: The enrollment status of this product in the member
                    affiliate program.
                product_tax_code_id:
                  type:
                  - string
                  - 'null'
                  description: The unique identifier of the tax classification code
                    to apply to this product.
                  example: ptc_xxxxxxxxxxxxxx
                redirect_purchase_url:
                  type:
                  - string
                  - 'null'
                  description: A URL to redirect the customer to after completing
                    a purchase.
                route:
                  type:
                  - string
                  - 'null'
                  description: The URL slug for the product's public link.
                send_welcome_message:
                  type:
                  - boolean
                  - 'null'
                  description: Whether to send an automated welcome message via support
                    chat when a user joins this product.
                store_page_config:
                  type:
                  - object
                  - 'null'
                  properties:
                    custom_cta:
                      type:
                      - string
                      - 'null'
                      description: Custom call-to-action text for the product's store
                        page.
                    show_price:
                      type:
                      - boolean
                      - 'null'
                      description: Whether or not to show the price on the product's
                        store page.
                  required: []
                  description: Layout and display configuration for this product on
                    the company's store page.
                title:
                  type:
                  - string
                  - 'null'
                  description: The display name of the product. Maximum 80 characters.
                visibility:
                  oneOf:
                  - "$ref": "#/components/schemas/Visibility"
                  - type: 'null'
                  description: Whether the product is visible to customers. When set
                    to hidden, the product is only accessible via direct link.
              required: []
              description: Parameters for UpdateAccessPass
      x-codeSamples:
      - lang: JavaScript
        source: |-
          import Whop from '@whop/sdk';

          const client = new Whop({
            apiKey: process.env['WHOP_API_KEY'], // This is the default and can be omitted
          });

          const product = await client.products.update('prod_xxxxxxxxxxxxx');

          console.log(product.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          product = client.products.update(
              id="prod_xxxxxxxxxxxxx",
          )
          print(product.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          product = whop.products.update("prod_xxxxxxxxxxxxx")

          puts(product)
components:
  schemas:
    CustomCtas:
      type: string
      enum:
      - get_access
      - join
      - order_now
      - shop_now
      - call_now
      - donate_now
      - contact_us
      - sign_up
      - subscribe
      - purchase
      - get_offer
      - apply_now
      - complete_order
      description: The different types of custom CTAs that can be selected.
    GlobalAffiliateStatuses:
      type: string
      enum:
      - enabled
      - disabled
      description: The different statuses of the global affiliate program for a product.
    Product:
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
        description:
          type:
          - string
          - 'null'
          description: A brief summary of what the product offers, displayed on product
            pages and search results.
          example: Track your revenue, members, and growth in real time.
        custom_cta:
          "$ref": "#/components/schemas/CustomCtas"
          description: The call-to-action button label displayed on the product's
            purchase page (e.g., 'join', 'buy', 'subscribe').
        custom_cta_url:
          type:
          - string
          - 'null'
          description: An optional URL that the call-to-action button links to instead
            of the default checkout flow. Null if no custom URL is set.
          example: https://example.com/signup
        custom_statement_descriptor:
          type:
          - string
          - 'null'
          description: A custom text label that appears on the customer's bank or
            credit card statement for purchases of this product. Maximum 22 characters,
            including the required prefix WHOP*.
          example: PICKAXE
        global_affiliate_percentage:
          type:
          - number
          - 'null'
          description: The commission rate (as a percentage) that affiliates earn
            on sales through the Whop marketplace global affiliate program. Null if
            the program is not active.
          example: 6.9
        global_affiliate_status:
          "$ref": "#/components/schemas/GlobalAffiliateStatuses"
          description: The enrollment status of this product in the Whop marketplace
            global affiliate program.
        member_affiliate_percentage:
          type:
          - number
          - 'null'
          description: The commission rate (as a percentage) that existing members
            earn when referring new customers through the member affiliate program.
            Null if the program is not active.
          example: 6.9
        member_affiliate_status:
          "$ref": "#/components/schemas/GlobalAffiliateStatuses"
          description: The enrollment status of this product in the member affiliate
            program.
        gallery_images:
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
              url:
                type:
                - string
                - 'null'
                description: A pre-optimized URL for rendering this attachment on
                  the client. This should be used for displaying attachments in apps.
                example: https://media.whop.com/abc123/optimized.jpg
            required:
            - id
            - url
            description: Represents an image attachment
          description: The gallery images for this product, ordered by position.
        product_tax_code:
          type:
          - object
          - 'null'
          properties:
            id:
              type: string
              description: The unique identifier for the product tax code.
              example: ptc_xxxxxxxxxxxxxx
            name:
              type: string
              description: The human-readable name of this tax classification (e.g.,
                'Digital - SaaS').
              example: Digital - SaaS
            product_type:
              "$ref": "#/components/schemas/ProductTaxCodeProductTypes"
              description: The broad product category this tax code covers (e.g.,
                physical goods, digital services).
          required:
          - id
          - name
          - product_type
          description: The tax classification code applied to purchases of this product
            for sales tax calculation. Null if no tax code is assigned.
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
          description: The user who owns the company that sells this product.
        company:
          type: object
          properties:
            id:
              type: string
              description: The unique identifier for the company.
              example: biz_xxxxxxxxxxxxxx
            route:
              type: string
              description: The URL slug for the company's store page (e.g., 'pickaxe'
                in whop.com/pickaxe).
              example: pickaxe
            title:
              type: string
              description: The display name of the company shown to customers.
              example: Pickaxe
          required:
          - id
          - route
          - title
          description: The company this product belongs to.
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
      - description
      - custom_cta
      - custom_cta_url
      - custom_statement_descriptor
      - global_affiliate_percentage
      - global_affiliate_status
      - member_affiliate_percentage
      - member_affiliate_status
      - gallery_images
      - product_tax_code
      - owner_user
      - company
      description: A product is a digital good or service sold on Whop. Products contain
        plans for pricing and experiences for content delivery.
    ProductTaxCodeProductTypes:
      type: string
      enum:
      - physical
      - digital
      - services
      description: The product_type of the ProductTaxCode
    Visibility:
      type: string
      enum:
      - visible
      - hidden
      - archived
      - quick_link
      description: Visibility of a resource
```
