# GET /affiliates/{id}/overrides — List overrides

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/affiliates/{id}/overrides`
- **Operation ID:** `listOverridesAffiliate`
- **Tags:** `Affiliates`
- **Required bearer scopes:** `affiliate:basic:read`

## Description

Returns a paginated list of overrides for an affiliate.

Required permissions:
 - `affiliate:basic:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `id` | path | yes | `string` | The affiliate ID. | aff_xxxxxxxxxxxxxx |
| `after` | query | no | `string \| null` | Returns the elements in the list that come after the specified cursor. |  |
| `before` | query | no | `string \| null` | Returns the elements in the list that come before the specified cursor. |  |
| `first` | query | no | `integer \| null` | Returns the first _n_ elements from the list. | 42 |
| `last` | query | no | `integer \| null` | Returns the last _n_ elements from the list. | 42 |
| `override_type` | query | no | `AffiliateOverrideRoles \| null` | Filter by override type (standard or rev_share). |  |

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
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the affiliate override.
          example: affov_xxxxxxxxxxxx
        override_type:
          "$ref": "#/components/schemas/AffiliateOverrideRoles"
          description: The type of override (standard or rev_share).
        commission_type:
          "$ref": "#/components/schemas/AffiliatePayoutTypes"
          description: The type of commission (percentage or flat_fee).
        commission_value:
          type: number
          description: The commission amount. A percentage (1-100) when commission_type
            is percentage, or a dollar amount when flat_fee.
          example: 6.9
        applies_to_payments:
          oneOf:
          - "$ref": "#/components/schemas/AffiliateAppliesToPayments"
          - type: 'null'
          description: Whether the commission applies to the first payment only or
            all payments (standard overrides only).
        plan_id:
          type:
          - string
          - 'null'
          description: The plan ID (for standard overrides).
          example: plan_xxxxxxxxxxxxx
        product_id:
          type:
          - string
          - 'null'
          description: The product ID (for rev-share overrides).
        applies_to_products:
          oneOf:
          - "$ref": "#/components/schemas/AffiliateAppliesToProducts"
          - type: 'null'
          description: Whether this rev-share override applies to a single product
            or all products (rev-share only).
        revenue_basis:
          oneOf:
          - "$ref": "#/components/schemas/AffiliateRevenueBases"
          - type: 'null'
          description: The revenue calculation basis (pre_fees or post_fees).
        product_direct_link:
          type:
          - string
          - 'null'
          description: The product page direct link for referrals (standard overrides
            only).
        checkout_direct_link:
          type:
          - string
          - 'null'
          description: The checkout direct link for referrals (standard overrides
            only).
        total_referral_earnings_usd:
          type: number
          description: The total earnings paid to this affiliate for referrals to
            this specific plan, in USD.
          example: 6.9
      required:
      - id
      - override_type
      - commission_type
      - commission_value
      - applies_to_payments
      - plan_id
      - product_id
      - applies_to_products
      - revenue_basis
      - product_direct_link
      - checkout_direct_link
      - total_referral_earnings_usd
      description: A commission configuration for an affiliate, defining payout terms
        for a specific plan or revenue share
    description: A list of nodes.
  page_info:
    "$ref": "#/components/schemas/PageInfo"
    description: Information to aid in pagination.
required:
- data
- page_info
description: The connection type for AffiliateOverride.
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
for await (const overrideListResponse of client.affiliates.overrides.list('aff_xxxxxxxxxxxxxx')) {
  console.log(overrideListResponse.id);
}
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
page = client.affiliates.overrides.list(
    id="aff_xxxxxxxxxxxxxx",
)
page = page.data[0]
print(page.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

page = whop.affiliates.overrides.list("aff_xxxxxxxxxxxxxx")

puts(page)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  AffiliateAppliesToPayments:
    type: string
    enum:
    - first_payment
    - all_payments
    description: Whether the affiliate commission applies to the first payment or
      all payments
  AffiliateAppliesToProducts:
    type: string
    enum:
    - single_product
    - all_products
    description: Whether a rev-share override applies to a single product or all products
  AffiliateOverrideRoles:
    type: string
    enum:
    - standard
    - rev_share
    description: The role of an affiliate override (standard or rev_share)
  AffiliatePayoutTypes:
    type: string
    enum:
    - percentage
    - flat_fee
    description: The types of payouts an affiliate can have
  AffiliateRevenueBases:
    type: string
    enum:
    - pre_fees
    - post_fees
    description: The calculation method for affiliate rev-share percentages
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
  "/affiliates/{id}/overrides":
    get:
      tags:
      - Affiliates
      operationId: listOverridesAffiliate
      summary: List overrides
      description: |-
        Returns a paginated list of overrides for an affiliate.

        Required permissions:
         - `affiliate:basic:read`
      parameters:
      - name: id
        in: path
        required: true
        description: The affiliate ID.
        schema:
          type: string
          example: aff_xxxxxxxxxxxxxx
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
      - name: override_type
        in: query
        required: false
        schema:
          oneOf:
          - "$ref": "#/components/schemas/AffiliateOverrideRoles"
          - type: 'null'
          description: Filter by override type (standard or rev_share).
        explode: true
        style: form
      x-group-title: CRM
      security:
      - bearerAuth:
        - affiliate:basic:read
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
                      type: object
                      properties:
                        id:
                          type: string
                          description: The unique identifier for the affiliate override.
                          example: affov_xxxxxxxxxxxx
                        override_type:
                          "$ref": "#/components/schemas/AffiliateOverrideRoles"
                          description: The type of override (standard or rev_share).
                        commission_type:
                          "$ref": "#/components/schemas/AffiliatePayoutTypes"
                          description: The type of commission (percentage or flat_fee).
                        commission_value:
                          type: number
                          description: The commission amount. A percentage (1-100)
                            when commission_type is percentage, or a dollar amount
                            when flat_fee.
                          example: 6.9
                        applies_to_payments:
                          oneOf:
                          - "$ref": "#/components/schemas/AffiliateAppliesToPayments"
                          - type: 'null'
                          description: Whether the commission applies to the first
                            payment only or all payments (standard overrides only).
                        plan_id:
                          type:
                          - string
                          - 'null'
                          description: The plan ID (for standard overrides).
                          example: plan_xxxxxxxxxxxxx
                        product_id:
                          type:
                          - string
                          - 'null'
                          description: The product ID (for rev-share overrides).
                        applies_to_products:
                          oneOf:
                          - "$ref": "#/components/schemas/AffiliateAppliesToProducts"
                          - type: 'null'
                          description: Whether this rev-share override applies to
                            a single product or all products (rev-share only).
                        revenue_basis:
                          oneOf:
                          - "$ref": "#/components/schemas/AffiliateRevenueBases"
                          - type: 'null'
                          description: The revenue calculation basis (pre_fees or
                            post_fees).
                        product_direct_link:
                          type:
                          - string
                          - 'null'
                          description: The product page direct link for referrals
                            (standard overrides only).
                        checkout_direct_link:
                          type:
                          - string
                          - 'null'
                          description: The checkout direct link for referrals (standard
                            overrides only).
                        total_referral_earnings_usd:
                          type: number
                          description: The total earnings paid to this affiliate for
                            referrals to this specific plan, in USD.
                          example: 6.9
                      required:
                      - id
                      - override_type
                      - commission_type
                      - commission_value
                      - applies_to_payments
                      - plan_id
                      - product_id
                      - applies_to_products
                      - revenue_basis
                      - product_direct_link
                      - checkout_direct_link
                      - total_referral_earnings_usd
                      description: A commission configuration for an affiliate, defining
                        payout terms for a specific plan or revenue share
                    description: A list of nodes.
                  page_info:
                    "$ref": "#/components/schemas/PageInfo"
                    description: Information to aid in pagination.
                required:
                - data
                - page_info
                description: The connection type for AffiliateOverride.
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
          for await (const overrideListResponse of client.affiliates.overrides.list('aff_xxxxxxxxxxxxxx')) {
            console.log(overrideListResponse.id);
          }
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          page = client.affiliates.overrides.list(
              id="aff_xxxxxxxxxxxxxx",
          )
          page = page.data[0]
          print(page.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          page = whop.affiliates.overrides.list("aff_xxxxxxxxxxxxxx")

          puts(page)
components:
  schemas:
    AffiliateAppliesToPayments:
      type: string
      enum:
      - first_payment
      - all_payments
      description: Whether the affiliate commission applies to the first payment or
        all payments
    AffiliateAppliesToProducts:
      type: string
      enum:
      - single_product
      - all_products
      description: Whether a rev-share override applies to a single product or all
        products
    AffiliateOverrideRoles:
      type: string
      enum:
      - standard
      - rev_share
      description: The role of an affiliate override (standard or rev_share)
    AffiliatePayoutTypes:
      type: string
      enum:
      - percentage
      - flat_fee
      description: The types of payouts an affiliate can have
    AffiliateRevenueBases:
      type: string
      enum:
      - pre_fees
      - post_fees
      description: The calculation method for affiliate rev-share percentages
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
```
