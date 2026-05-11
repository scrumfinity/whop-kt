# GET /members/{id} — Retrieve member

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/members/{id}`
- **Operation ID:** `retrieveMember`
- **Tags:** `Members`
- **Required bearer scopes:** `member:basic:read`, `member:email:read`, `member:phone:read`

## Description

Retrieves the details of an existing member.

Required permissions:
 - `member:basic:read`
 - `member:email:read`
 - `member:phone:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `id` | path | yes | `string` | The unique identifier of the member to retrieve. | mber_xxxxxxxxxxxxx |

## Request body

_No request body._

## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `Member` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/Member"
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

const member = await client.members.retrieve('mber_xxxxxxxxxxxxx');

console.log(member.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
member = client.members.retrieve(
    "mber_xxxxxxxxxxxxx",
)
print(member.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

member = whop.members.retrieve("mber_xxxxxxxxxxxxx")

puts(member)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  AccessLevel:
    type: string
    enum:
    - no_access
    - admin
    - customer
    description: The access level a given user (or company) has to a product or company.
  Member:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the company member.
      created_at:
        type: string
        format: date-time
        description: The datetime the company member was created.
        example: '2023-12-01T05:00:00.401Z'
      updated_at:
        type: string
        format: date-time
        description: The datetime the company member was last updated.
        example: '2023-12-01T05:00:00.401Z'
      joined_at:
        type: string
        format: date-time
        description: When the member joined the company
        example: '2023-12-01T05:00:00.401Z'
      access_level:
        "$ref": "#/components/schemas/AccessLevel"
        description: The access level of the product member. If its admin, the member
          is an authorized user of the company. If its customer, the member has a
          valid membership to any product on the company. If its no_access, the member
          does not have access to the product.
      status:
        "$ref": "#/components/schemas/MemberStatuses"
        description: The status of the member
      most_recent_action:
        oneOf:
        - "$ref": "#/components/schemas/MemberMostRecentActions"
        - type: 'null'
        description: The most recent action the member has taken.
      most_recent_action_at:
        type:
        - string
        - 'null'
        format: date-time
        description: The time for the most recent action, if applicable.
        example: '2023-12-01T05:00:00.401Z'
      user:
        type:
        - object
        - 'null'
        properties:
          id:
            type: string
            description: The unique identifier for the company member user.
          email:
            type:
            - string
            - 'null'
            description: The digital mailing address of the user.
          name:
            type:
            - string
            - 'null'
            description: The user's full name.
          username:
            type: string
            description: The whop username.
        required:
        - id
        - email
        - name
        - username
        description: The user for this member, if any.
      phone:
        type:
        - string
        - 'null'
        description: The phone number for the member, if available.
      usd_total_spent:
        type: number
        description: How much money this customer has spent on the company's products
          and plans
        example: 6.9
      company_token_balance:
        type: number
        description: The member's token balance for this company
        example: 6.9
      company:
        type: object
        properties:
          id:
            type: string
            description: The unique identifier for the company.
            example: biz_xxxxxxxxxxxxxx
          title:
            type: string
            description: The written name of the company.
          route:
            type: string
            description: The slug/route of the company on the Whop site.
        required:
        - id
        - title
        - route
        description: The company for the member.
    required:
    - id
    - created_at
    - updated_at
    - joined_at
    - access_level
    - status
    - most_recent_action
    - most_recent_action_at
    - user
    - phone
    - usd_total_spent
    - company_token_balance
    - company
    description: A member represents a user's relationship with a company on Whop,
      including their access level, status, and spending history.
  MemberMostRecentActions:
    type: string
    enum:
    - canceling
    - churned
    - finished_split_pay
    - paused
    - paid_subscriber
    - paid_once
    - expiring
    - joined
    - drafted
    - left
    - trialing
    - pending_entry
    - renewing
    - past_due
    description: The different most recent actions a member can have.
  MemberStatuses:
    type: string
    enum:
    - drafted
    - joined
    - left
    description: The different statuses a Member can have.
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
  "/members/{id}":
    get:
      tags:
      - Members
      operationId: retrieveMember
      summary: Retrieve member
      description: |-
        Retrieves the details of an existing member.

        Required permissions:
         - `member:basic:read`
         - `member:email:read`
         - `member:phone:read`
      parameters:
      - name: id
        in: path
        required: true
        description: The unique identifier of the member to retrieve.
        schema:
          type: string
          example: mber_xxxxxxxxxxxxx
      x-group-title: CRM
      security:
      - bearerAuth:
        - member:basic:read
        - member:email:read
        - member:phone:read
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/Member"
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

          const member = await client.members.retrieve('mber_xxxxxxxxxxxxx');

          console.log(member.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          member = client.members.retrieve(
              "mber_xxxxxxxxxxxxx",
          )
          print(member.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          member = whop.members.retrieve("mber_xxxxxxxxxxxxx")

          puts(member)
components:
  schemas:
    AccessLevel:
      type: string
      enum:
      - no_access
      - admin
      - customer
      description: The access level a given user (or company) has to a product or
        company.
    Member:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the company member.
        created_at:
          type: string
          format: date-time
          description: The datetime the company member was created.
          example: '2023-12-01T05:00:00.401Z'
        updated_at:
          type: string
          format: date-time
          description: The datetime the company member was last updated.
          example: '2023-12-01T05:00:00.401Z'
        joined_at:
          type: string
          format: date-time
          description: When the member joined the company
          example: '2023-12-01T05:00:00.401Z'
        access_level:
          "$ref": "#/components/schemas/AccessLevel"
          description: The access level of the product member. If its admin, the member
            is an authorized user of the company. If its customer, the member has
            a valid membership to any product on the company. If its no_access, the
            member does not have access to the product.
        status:
          "$ref": "#/components/schemas/MemberStatuses"
          description: The status of the member
        most_recent_action:
          oneOf:
          - "$ref": "#/components/schemas/MemberMostRecentActions"
          - type: 'null'
          description: The most recent action the member has taken.
        most_recent_action_at:
          type:
          - string
          - 'null'
          format: date-time
          description: The time for the most recent action, if applicable.
          example: '2023-12-01T05:00:00.401Z'
        user:
          type:
          - object
          - 'null'
          properties:
            id:
              type: string
              description: The unique identifier for the company member user.
            email:
              type:
              - string
              - 'null'
              description: The digital mailing address of the user.
            name:
              type:
              - string
              - 'null'
              description: The user's full name.
            username:
              type: string
              description: The whop username.
          required:
          - id
          - email
          - name
          - username
          description: The user for this member, if any.
        phone:
          type:
          - string
          - 'null'
          description: The phone number for the member, if available.
        usd_total_spent:
          type: number
          description: How much money this customer has spent on the company's products
            and plans
          example: 6.9
        company_token_balance:
          type: number
          description: The member's token balance for this company
          example: 6.9
        company:
          type: object
          properties:
            id:
              type: string
              description: The unique identifier for the company.
              example: biz_xxxxxxxxxxxxxx
            title:
              type: string
              description: The written name of the company.
            route:
              type: string
              description: The slug/route of the company on the Whop site.
          required:
          - id
          - title
          - route
          description: The company for the member.
      required:
      - id
      - created_at
      - updated_at
      - joined_at
      - access_level
      - status
      - most_recent_action
      - most_recent_action_at
      - user
      - phone
      - usd_total_spent
      - company_token_balance
      - company
      description: A member represents a user's relationship with a company on Whop,
        including their access level, status, and spending history.
    MemberMostRecentActions:
      type: string
      enum:
      - canceling
      - churned
      - finished_split_pay
      - paused
      - paid_subscriber
      - paid_once
      - expiring
      - joined
      - drafted
      - left
      - trialing
      - pending_entry
      - renewing
      - past_due
      description: The different most recent actions a member can have.
    MemberStatuses:
      type: string
      enum:
      - drafted
      - joined
      - left
      description: The different statuses a Member can have.
```
