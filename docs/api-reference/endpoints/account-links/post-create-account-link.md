# POST /account_links — Create account link

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `POST`
- **Path:** `/account_links`
- **Operation ID:** `createAccountLink`
- **Tags:** `Account links`
- **Required bearer scopes:** _None documented_

## Description

Generate a URL that directs a sub-merchant to their account portal, such as the hosted payouts dashboard or the KYC onboarding flow.

## Parameters

_No path, query, header, or cookie parameters._

## Request body

Required: **yes**
Content types: `application/json`

### `application/json` request schema

```yaml
type: object
properties:
  company_id:
    type: string
    description: The unique identifier of the company to generate the link for, starting
      with 'biz_'. Must be a sub-merchant of the API key's company.
    example: biz_xxxxxxxxxxxxxx
  refresh_url:
    type: string
    description: The URL to redirect the user to if the session expires and needs
      to be re-authenticated, such as 'https://example.com/refresh'.
  return_url:
    type: string
    description: The URL to redirect the user to when they want to return to your
      site, such as 'https://example.com/return'.
  use_case:
    "$ref": "#/components/schemas/AccountLinkUseCases"
    description: The purpose of the account link, such as hosted payouts portal or
      hosted KYC onboarding.
required:
- company_id
- refresh_url
- return_url
- use_case
description: Parameters for CreateAccountLink
```


## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `AccountLink` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/AccountLink"
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

const accountLink = await client.accountLinks.create({
  company_id: 'biz_xxxxxxxxxxxxxx',
  refresh_url: 'refresh_url',
  return_url: 'return_url',
  use_case: 'account_onboarding',
});

console.log(accountLink.expires_at);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
account_link = client.account_links.create(
    company_id="biz_xxxxxxxxxxxxxx",
    refresh_url="refresh_url",
    return_url="return_url",
    use_case="account_onboarding",
)
print(account_link.expires_at)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

account_link = whop.account_links.create(
  company_id: "biz_xxxxxxxxxxxxxx",
  refresh_url: "refresh_url",
  return_url: "return_url",
  use_case: :account_onboarding
)

puts(account_link)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  AccountLink:
    type: object
    properties:
      url:
        type: string
        description: The temporary URL to redirect the user to for account access.
          Expires at the time specified by expires_at.
        example: https://whop.com/payouts/biz_xxxxxxxxx/verify
      expires_at:
        type: string
        format: date-time
        description: The timestamp after which this account link URL is no longer
          valid.
        example: '2023-12-01T05:00:00.401Z'
    required:
    - url
    - expires_at
    description: A temporary, time-limited URL that grants a user access to an external
      account management page.
  AccountLinkUseCases:
    type: string
    enum:
    - account_onboarding
    - payouts_portal
    description: The different use cases for generating an account link.
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
  "/account_links":
    post:
      tags:
      - Account links
      operationId: createAccountLink
      summary: Create account link
      description: Generate a URL that directs a sub-merchant to their account portal,
        such as the hosted payouts dashboard or the KYC onboarding flow.
      parameters: []
      x-group-title: Developer
      security:
      - bearerAuth: []
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/AccountLink"
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
                company_id:
                  type: string
                  description: The unique identifier of the company to generate the
                    link for, starting with 'biz_'. Must be a sub-merchant of the
                    API key's company.
                  example: biz_xxxxxxxxxxxxxx
                refresh_url:
                  type: string
                  description: The URL to redirect the user to if the session expires
                    and needs to be re-authenticated, such as 'https://example.com/refresh'.
                return_url:
                  type: string
                  description: The URL to redirect the user to when they want to return
                    to your site, such as 'https://example.com/return'.
                use_case:
                  "$ref": "#/components/schemas/AccountLinkUseCases"
                  description: The purpose of the account link, such as hosted payouts
                    portal or hosted KYC onboarding.
              required:
              - company_id
              - refresh_url
              - return_url
              - use_case
              description: Parameters for CreateAccountLink
      x-codeSamples:
      - lang: JavaScript
        source: |-
          import Whop from '@whop/sdk';

          const client = new Whop({
            apiKey: process.env['WHOP_API_KEY'], // This is the default and can be omitted
          });

          const accountLink = await client.accountLinks.create({
            company_id: 'biz_xxxxxxxxxxxxxx',
            refresh_url: 'refresh_url',
            return_url: 'return_url',
            use_case: 'account_onboarding',
          });

          console.log(accountLink.expires_at);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          account_link = client.account_links.create(
              company_id="biz_xxxxxxxxxxxxxx",
              refresh_url="refresh_url",
              return_url="return_url",
              use_case="account_onboarding",
          )
          print(account_link.expires_at)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          account_link = whop.account_links.create(
            company_id: "biz_xxxxxxxxxxxxxx",
            refresh_url: "refresh_url",
            return_url: "return_url",
            use_case: :account_onboarding
          )

          puts(account_link)
components:
  schemas:
    AccountLink:
      type: object
      properties:
        url:
          type: string
          description: The temporary URL to redirect the user to for account access.
            Expires at the time specified by expires_at.
          example: https://whop.com/payouts/biz_xxxxxxxxx/verify
        expires_at:
          type: string
          format: date-time
          description: The timestamp after which this account link URL is no longer
            valid.
          example: '2023-12-01T05:00:00.401Z'
      required:
      - url
      - expires_at
      description: A temporary, time-limited URL that grants a user access to an external
        account management page.
    AccountLinkUseCases:
      type: string
      enum:
      - account_onboarding
      - payouts_portal
      description: The different use cases for generating an account link.
```
