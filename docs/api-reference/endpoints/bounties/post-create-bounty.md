# POST /bounties — Create bounty

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `POST`
- **Path:** `/bounties`
- **Operation ID:** `createBounty`
- **Tags:** `Bounties`
- **Required bearer scopes:** `bounty:create`

## Description

Create a new workforce bounty by funding a dedicated bounty pool.

Required permissions:
 - `bounty:create`

## Parameters

_No path, query, header, or cookie parameters._

## Request body

Required: **yes**
Content types: `application/json`

### `application/json` request schema

```yaml
type: object
properties:
  accepted_submissions_limit:
    type:
    - integer
    - 'null'
    description: The number of submissions that can be approved before the bounty
      closes. Defaults to 1.
    example: 42
  allowed_country_codes:
    type:
    - array
    - 'null'
    items:
      type: string
      description: Represents textual data as UTF-8 character sequences. This type
        is most often used by GraphQL to represent free-form human-readable text.
    description: The ISO3166 country codes where this bounty should be visible. Empty
      means globally visible.
  base_unit_amount:
    type: number
    description: The amount paid to each approved submission. The total bounty pool
      funded is this amount times accepted_submissions_limit.
    example: 6.9
  currency:
    "$ref": "#/components/schemas/Currencies"
    description: The currency for the bounty pool funding amount.
  description:
    type: string
    description: The description of the bounty.
  experience_id:
    type:
    - string
    - 'null'
    description: An optional experience to scope the bounty to.
    example: exp_xxxxxxxxxxxxxx
  origin_account_id:
    type:
    - string
    - 'null'
    description: The user (user_*) or company (biz_*) tag whose balance funds this
      bounty pool. Defaults to the requester's personal balance when omitted. The
      requester must be the user themself or an owner/admin of the company.
  post_markdown_content:
    type:
    - string
    - 'null'
    description: Optional markdown body for the anchor forum post. Falls back to the
      bounty description when omitted.
  post_title:
    type:
    - string
    - 'null'
    description: Optional title for the anchor forum post. Falls back to the bounty
      title when omitted.
  title:
    type: string
    description: The title of the bounty.
required:
- base_unit_amount
- currency
- description
- title
description: Parameters for CreateWorkforceBounty
```


## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `Bounty` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/Bounty"
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

const bounty = await client.bounties.create({
  base_unit_amount: 6.9,
  currency: 'usd',
  description: 'description',
  title: 'title',
});

console.log(bounty.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
bounty = client.bounties.create(
    base_unit_amount=6.9,
    currency="usd",
    description="description",
    title="title",
)
print(bounty.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

bounty = whop.bounties.create(base_unit_amount: 6.9, currency: :usd, description: "description", title: "title")

puts(bounty)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  Bounty:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the bounty.
        example: bnty_xxxxxxxxxxxxx
      title:
        type: string
        description: The title of the bounty.
      description:
        type: string
        description: The description of the bounty.
      status:
        "$ref": "#/components/schemas/Statuses"
        description: The current lifecycle status of the bounty.
      total_available:
        type: number
        description: The total amount currently funded in the bounty pool for payout.
        example: 6.9
      total_paid:
        type: number
        description: The total amount paid out for this bounty.
        example: 6.9
      currency:
        "$ref": "#/components/schemas/Currencies"
        description: The currency used for the bounty funds.
      bounty_type:
        "$ref": "#/components/schemas/BountyTypes"
        description: The underlying bounty implementation type.
      vote_threshold:
        type: integer
        description: The number of watcher votes required before the submission can
          resolve.
        example: 42
      created_at:
        type: string
        format: date-time
        description: The datetime the bounty was created.
        example: '2023-12-01T05:00:00.401Z'
      updated_at:
        type: string
        format: date-time
        description: The datetime the bounty was last updated.
        example: '2023-12-01T05:00:00.401Z'
    required:
    - id
    - title
    - description
    - status
    - total_available
    - total_paid
    - currency
    - bounty_type
    - vote_threshold
    - created_at
    - updated_at
    description: A privately accessible bounty.
  BountyTypes:
    type: string
    enum:
    - classic
    - user_funded
    - workforce
    description: The available bounty implementation types.
  Currencies:
    type: string
    enum:
    - usd
    - sgd
    - inr
    - aud
    - brl
    - cad
    - dkk
    - eur
    - nok
    - gbp
    - sek
    - chf
    - hkd
    - huf
    - jpy
    - mxn
    - myr
    - pln
    - czk
    - nzd
    - aed
    - eth
    - ape
    - cop
    - ron
    - thb
    - bgn
    - idr
    - dop
    - php
    - try
    - krw
    - twd
    - vnd
    - pkr
    - clp
    - uyu
    - ars
    - zar
    - dzd
    - tnd
    - mad
    - kes
    - kwd
    - jod
    - all
    - xcd
    - amd
    - bsd
    - bhd
    - bob
    - bam
    - khr
    - crc
    - xof
    - egp
    - etb
    - gmd
    - ghs
    - gtq
    - gyd
    - ils
    - jmd
    - mop
    - mga
    - mur
    - mdl
    - mnt
    - nad
    - ngn
    - mkd
    - omr
    - pyg
    - pen
    - qar
    - rwf
    - sar
    - rsd
    - lkr
    - tzs
    - ttd
    - uzs
    - rub
    - btc
    - cny
    - usdt
    - kzt
    description: The available currencies on the platform
  Statuses:
    type: string
    enum:
    - published
    - archived
    description: The available bounty statuses to choose from.
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
  "/bounties":
    post:
      tags:
      - Bounties
      operationId: createBounty
      summary: Create bounty
      description: |-
        Create a new workforce bounty by funding a dedicated bounty pool.

        Required permissions:
         - `bounty:create`
      parameters: []
      x-group-title: Bounties
      security:
      - bearerAuth:
        - bounty:create
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/Bounty"
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
                accepted_submissions_limit:
                  type:
                  - integer
                  - 'null'
                  description: The number of submissions that can be approved before
                    the bounty closes. Defaults to 1.
                  example: 42
                allowed_country_codes:
                  type:
                  - array
                  - 'null'
                  items:
                    type: string
                    description: Represents textual data as UTF-8 character sequences.
                      This type is most often used by GraphQL to represent free-form
                      human-readable text.
                  description: The ISO3166 country codes where this bounty should
                    be visible. Empty means globally visible.
                base_unit_amount:
                  type: number
                  description: The amount paid to each approved submission. The total
                    bounty pool funded is this amount times accepted_submissions_limit.
                  example: 6.9
                currency:
                  "$ref": "#/components/schemas/Currencies"
                  description: The currency for the bounty pool funding amount.
                description:
                  type: string
                  description: The description of the bounty.
                experience_id:
                  type:
                  - string
                  - 'null'
                  description: An optional experience to scope the bounty to.
                  example: exp_xxxxxxxxxxxxxx
                origin_account_id:
                  type:
                  - string
                  - 'null'
                  description: The user (user_*) or company (biz_*) tag whose balance
                    funds this bounty pool. Defaults to the requester's personal balance
                    when omitted. The requester must be the user themself or an owner/admin
                    of the company.
                post_markdown_content:
                  type:
                  - string
                  - 'null'
                  description: Optional markdown body for the anchor forum post. Falls
                    back to the bounty description when omitted.
                post_title:
                  type:
                  - string
                  - 'null'
                  description: Optional title for the anchor forum post. Falls back
                    to the bounty title when omitted.
                title:
                  type: string
                  description: The title of the bounty.
              required:
              - base_unit_amount
              - currency
              - description
              - title
              description: Parameters for CreateWorkforceBounty
      x-codeSamples:
      - lang: JavaScript
        source: |-
          import Whop from '@whop/sdk';

          const client = new Whop({
            apiKey: process.env['WHOP_API_KEY'], // This is the default and can be omitted
          });

          const bounty = await client.bounties.create({
            base_unit_amount: 6.9,
            currency: 'usd',
            description: 'description',
            title: 'title',
          });

          console.log(bounty.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          bounty = client.bounties.create(
              base_unit_amount=6.9,
              currency="usd",
              description="description",
              title="title",
          )
          print(bounty.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          bounty = whop.bounties.create(base_unit_amount: 6.9, currency: :usd, description: "description", title: "title")

          puts(bounty)
components:
  schemas:
    Bounty:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the bounty.
          example: bnty_xxxxxxxxxxxxx
        title:
          type: string
          description: The title of the bounty.
        description:
          type: string
          description: The description of the bounty.
        status:
          "$ref": "#/components/schemas/Statuses"
          description: The current lifecycle status of the bounty.
        total_available:
          type: number
          description: The total amount currently funded in the bounty pool for payout.
          example: 6.9
        total_paid:
          type: number
          description: The total amount paid out for this bounty.
          example: 6.9
        currency:
          "$ref": "#/components/schemas/Currencies"
          description: The currency used for the bounty funds.
        bounty_type:
          "$ref": "#/components/schemas/BountyTypes"
          description: The underlying bounty implementation type.
        vote_threshold:
          type: integer
          description: The number of watcher votes required before the submission
            can resolve.
          example: 42
        created_at:
          type: string
          format: date-time
          description: The datetime the bounty was created.
          example: '2023-12-01T05:00:00.401Z'
        updated_at:
          type: string
          format: date-time
          description: The datetime the bounty was last updated.
          example: '2023-12-01T05:00:00.401Z'
      required:
      - id
      - title
      - description
      - status
      - total_available
      - total_paid
      - currency
      - bounty_type
      - vote_threshold
      - created_at
      - updated_at
      description: A privately accessible bounty.
    BountyTypes:
      type: string
      enum:
      - classic
      - user_funded
      - workforce
      description: The available bounty implementation types.
    Currencies:
      type: string
      enum:
      - usd
      - sgd
      - inr
      - aud
      - brl
      - cad
      - dkk
      - eur
      - nok
      - gbp
      - sek
      - chf
      - hkd
      - huf
      - jpy
      - mxn
      - myr
      - pln
      - czk
      - nzd
      - aed
      - eth
      - ape
      - cop
      - ron
      - thb
      - bgn
      - idr
      - dop
      - php
      - try
      - krw
      - twd
      - vnd
      - pkr
      - clp
      - uyu
      - ars
      - zar
      - dzd
      - tnd
      - mad
      - kes
      - kwd
      - jod
      - all
      - xcd
      - amd
      - bsd
      - bhd
      - bob
      - bam
      - khr
      - crc
      - xof
      - egp
      - etb
      - gmd
      - ghs
      - gtq
      - gyd
      - ils
      - jmd
      - mop
      - mga
      - mur
      - mdl
      - mnt
      - nad
      - ngn
      - mkd
      - omr
      - pyg
      - pen
      - qar
      - rwf
      - sar
      - rsd
      - lkr
      - tzs
      - ttd
      - uzs
      - rub
      - btc
      - cny
      - usdt
      - kzt
      description: The available currencies on the platform
    Statuses:
      type: string
      enum:
      - published
      - archived
      description: The available bounty statuses to choose from.
```
