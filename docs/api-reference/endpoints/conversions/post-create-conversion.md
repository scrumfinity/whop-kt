# POST /conversions — Create conversion

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `POST`
- **Path:** `/conversions`
- **Operation ID:** `createConversion`
- **Tags:** `Conversions`
- **Required bearer scopes:** `event:create`

## Description

Track a conversion or engagement event for a company.

Required permissions:
 - `event:create`

## Parameters

_No path, query, header, or cookie parameters._

## Request body

Required: **yes**
Content types: `application/json`

### `application/json` request schema

```yaml
type: object
properties:
  action_source:
    oneOf:
    - "$ref": "#/components/schemas/EventActionSource"
    - type: 'null'
    description: Where the event originated.
  company_id:
    type: string
    description: The company to associate with this event.
    example: biz_xxxxxxxxxxxxxx
  context:
    type:
    - object
    - 'null'
    properties:
      fbclid:
        type:
        - string
        - 'null'
        description: Facebook click ID.
      fbp:
        type:
        - string
        - 'null'
        description: Facebook browser pixel ID.
      ga:
        type:
        - string
        - 'null'
        description: Google Analytics client ID.
      gclid:
        type:
        - string
        - 'null'
        description: Google click ID.
      ig_sid:
        type:
        - string
        - 'null'
        description: Instagram session ID.
      ttclid:
        type:
        - string
        - 'null'
        description: TikTok click ID.
      ttp:
        type:
        - string
        - 'null'
        description: TikTok pixel ID.
      ip_address:
        type:
        - string
        - 'null'
        description: IP address.
      user_agent:
        type:
        - string
        - 'null'
        description: Browser user agent string.
      ad_campaign_id:
        type:
        - string
        - 'null'
        description: Ad campaign ID.
      ad_id:
        type:
        - string
        - 'null'
        description: Ad ID.
      ad_set_id:
        type:
        - string
        - 'null'
        description: Ad set ID.
      utm_campaign:
        type:
        - string
        - 'null'
        description: UTM campaign parameter.
      utm_content:
        type:
        - string
        - 'null'
        description: UTM content parameter.
      utm_id:
        type:
        - string
        - 'null'
        description: UTM ID parameter.
      utm_medium:
        type:
        - string
        - 'null'
        description: UTM medium parameter.
      utm_source:
        type:
        - string
        - 'null'
        description: UTM source parameter.
      utm_term:
        type:
        - string
        - 'null'
        description: UTM term parameter.
    required: []
    description: Tracking and attribution context.
  currency:
    oneOf:
    - "$ref": "#/components/schemas/Currencies"
    - type: 'null'
    description: ISO 4217 currency code.
  custom_name:
    type:
    - string
    - 'null'
    description: Custom event name when event_name is 'custom'.
  event_id:
    type:
    - string
    - 'null'
    description: Client-provided identifier for deduplication. Generated if omitted.
    example: evnt_xxxxxxxxxxxxx
  event_name:
    "$ref": "#/components/schemas/EventName"
    description: The type of event.
  event_time:
    type:
    - string
    - 'null'
    format: date-time
    description: When the event occurred. Defaults to now.
    example: '2023-12-01T05:00:00.401Z'
  plan_id:
    type:
    - string
    - 'null'
    description: The plan associated with the event.
    example: plan_xxxxxxxxxxxxx
  product_id:
    type:
    - string
    - 'null'
    description: The product associated with the event.
    example: prod_xxxxxxxxxxxxx
  referrer_url:
    type:
    - string
    - 'null'
    description: The referring URL.
  url:
    type:
    - string
    - 'null'
    description: The URL where the event occurred.
  user:
    type:
    - object
    - 'null'
    properties:
      anonymous_id:
        type:
        - string
        - 'null'
        description: An anonymous identifier for the user.
      external_id:
        type:
        - string
        - 'null'
        description: An external identifier for the user.
      member_id:
        type:
        - string
        - 'null'
        description: The Whop member ID.
        example: mber_xxxxxxxxxxxxx
      membership_id:
        type:
        - string
        - 'null'
        description: The Whop membership ID.
        example: mem_xxxxxxxxxxxxxx
      user_id:
        type:
        - string
        - 'null'
        description: The Whop user ID.
        example: user_xxxxxxxxxxxxx
      birthdate:
        type:
        - string
        - 'null'
        description: Date of birth (YYYY-MM-DD).
        example: '1990-01-15'
      city:
        type:
        - string
        - 'null'
        description: City.
      country:
        type:
        - string
        - 'null'
        description: Country.
      email:
        type:
        - string
        - 'null'
        description: Email address.
      first_name:
        type:
        - string
        - 'null'
        description: First name.
      gender:
        oneOf:
        - "$ref": "#/components/schemas/EventGender"
        - type: 'null'
        description: Gender.
      last_name:
        type:
        - string
        - 'null'
        description: Last name.
      name:
        type:
        - string
        - 'null'
        description: Full display name.
      phone:
        type:
        - string
        - 'null'
        description: Phone number.
      postal_code:
        type:
        - string
        - 'null'
        description: Postal code.
      state:
        type:
        - string
        - 'null'
        description: State or region.
      username:
        type:
        - string
        - 'null'
        description: Username.
    required: []
    description: User identity and profile data.
  value:
    type:
    - number
    - 'null'
    description: Monetary value associated with the event.
    example: 6.9
required:
- company_id
- event_name
description: Parameters for CreateConversion
```


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
  id:
    type: string
    description: The unique identifier for the conversion
required:
- id
description: A tracked conversion event
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

const conversion = await client.conversions.create({
  company_id: 'biz_xxxxxxxxxxxxxx',
  event_name: 'lead',
});

console.log(conversion.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
conversion = client.conversions.create(
    company_id="biz_xxxxxxxxxxxxxx",
    event_name="lead",
)
print(conversion.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

conversion = whop.conversions.create(company_id: "biz_xxxxxxxxxxxxxx", event_name: :lead)

puts(conversion)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
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
  EventActionSource:
    type: string
    enum:
    - email
    - website
    - app
    - phone_call
    - chat
    - physical_store
    - system_generated
    - business_messaging
    - other
    description: The channel where an event originated
  EventGender:
    type: string
    enum:
    - male
    - female
    description: Gender
  EventName:
    type: string
    enum:
    - lead
    - submit_application
    - contact
    - complete_registration
    - schedule
    - custom
    description: The type of conversion or engagement event
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
  "/conversions":
    post:
      tags:
      - Conversions
      operationId: createConversion
      summary: Create conversion
      description: |-
        Track a conversion or engagement event for a company.

        Required permissions:
         - `event:create`
      parameters: []
      x-group-title: Ads
      security:
      - bearerAuth:
        - event:create
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                type: object
                properties:
                  id:
                    type: string
                    description: The unique identifier for the conversion
                required:
                - id
                description: A tracked conversion event
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
                action_source:
                  oneOf:
                  - "$ref": "#/components/schemas/EventActionSource"
                  - type: 'null'
                  description: Where the event originated.
                company_id:
                  type: string
                  description: The company to associate with this event.
                  example: biz_xxxxxxxxxxxxxx
                context:
                  type:
                  - object
                  - 'null'
                  properties:
                    fbclid:
                      type:
                      - string
                      - 'null'
                      description: Facebook click ID.
                    fbp:
                      type:
                      - string
                      - 'null'
                      description: Facebook browser pixel ID.
                    ga:
                      type:
                      - string
                      - 'null'
                      description: Google Analytics client ID.
                    gclid:
                      type:
                      - string
                      - 'null'
                      description: Google click ID.
                    ig_sid:
                      type:
                      - string
                      - 'null'
                      description: Instagram session ID.
                    ttclid:
                      type:
                      - string
                      - 'null'
                      description: TikTok click ID.
                    ttp:
                      type:
                      - string
                      - 'null'
                      description: TikTok pixel ID.
                    ip_address:
                      type:
                      - string
                      - 'null'
                      description: IP address.
                    user_agent:
                      type:
                      - string
                      - 'null'
                      description: Browser user agent string.
                    ad_campaign_id:
                      type:
                      - string
                      - 'null'
                      description: Ad campaign ID.
                    ad_id:
                      type:
                      - string
                      - 'null'
                      description: Ad ID.
                    ad_set_id:
                      type:
                      - string
                      - 'null'
                      description: Ad set ID.
                    utm_campaign:
                      type:
                      - string
                      - 'null'
                      description: UTM campaign parameter.
                    utm_content:
                      type:
                      - string
                      - 'null'
                      description: UTM content parameter.
                    utm_id:
                      type:
                      - string
                      - 'null'
                      description: UTM ID parameter.
                    utm_medium:
                      type:
                      - string
                      - 'null'
                      description: UTM medium parameter.
                    utm_source:
                      type:
                      - string
                      - 'null'
                      description: UTM source parameter.
                    utm_term:
                      type:
                      - string
                      - 'null'
                      description: UTM term parameter.
                  required: []
                  description: Tracking and attribution context.
                currency:
                  oneOf:
                  - "$ref": "#/components/schemas/Currencies"
                  - type: 'null'
                  description: ISO 4217 currency code.
                custom_name:
                  type:
                  - string
                  - 'null'
                  description: Custom event name when event_name is 'custom'.
                event_id:
                  type:
                  - string
                  - 'null'
                  description: Client-provided identifier for deduplication. Generated
                    if omitted.
                  example: evnt_xxxxxxxxxxxxx
                event_name:
                  "$ref": "#/components/schemas/EventName"
                  description: The type of event.
                event_time:
                  type:
                  - string
                  - 'null'
                  format: date-time
                  description: When the event occurred. Defaults to now.
                  example: '2023-12-01T05:00:00.401Z'
                plan_id:
                  type:
                  - string
                  - 'null'
                  description: The plan associated with the event.
                  example: plan_xxxxxxxxxxxxx
                product_id:
                  type:
                  - string
                  - 'null'
                  description: The product associated with the event.
                  example: prod_xxxxxxxxxxxxx
                referrer_url:
                  type:
                  - string
                  - 'null'
                  description: The referring URL.
                url:
                  type:
                  - string
                  - 'null'
                  description: The URL where the event occurred.
                user:
                  type:
                  - object
                  - 'null'
                  properties:
                    anonymous_id:
                      type:
                      - string
                      - 'null'
                      description: An anonymous identifier for the user.
                    external_id:
                      type:
                      - string
                      - 'null'
                      description: An external identifier for the user.
                    member_id:
                      type:
                      - string
                      - 'null'
                      description: The Whop member ID.
                      example: mber_xxxxxxxxxxxxx
                    membership_id:
                      type:
                      - string
                      - 'null'
                      description: The Whop membership ID.
                      example: mem_xxxxxxxxxxxxxx
                    user_id:
                      type:
                      - string
                      - 'null'
                      description: The Whop user ID.
                      example: user_xxxxxxxxxxxxx
                    birthdate:
                      type:
                      - string
                      - 'null'
                      description: Date of birth (YYYY-MM-DD).
                      example: '1990-01-15'
                    city:
                      type:
                      - string
                      - 'null'
                      description: City.
                    country:
                      type:
                      - string
                      - 'null'
                      description: Country.
                    email:
                      type:
                      - string
                      - 'null'
                      description: Email address.
                    first_name:
                      type:
                      - string
                      - 'null'
                      description: First name.
                    gender:
                      oneOf:
                      - "$ref": "#/components/schemas/EventGender"
                      - type: 'null'
                      description: Gender.
                    last_name:
                      type:
                      - string
                      - 'null'
                      description: Last name.
                    name:
                      type:
                      - string
                      - 'null'
                      description: Full display name.
                    phone:
                      type:
                      - string
                      - 'null'
                      description: Phone number.
                    postal_code:
                      type:
                      - string
                      - 'null'
                      description: Postal code.
                    state:
                      type:
                      - string
                      - 'null'
                      description: State or region.
                    username:
                      type:
                      - string
                      - 'null'
                      description: Username.
                  required: []
                  description: User identity and profile data.
                value:
                  type:
                  - number
                  - 'null'
                  description: Monetary value associated with the event.
                  example: 6.9
              required:
              - company_id
              - event_name
              description: Parameters for CreateConversion
      x-codeSamples:
      - lang: JavaScript
        source: |-
          import Whop from '@whop/sdk';

          const client = new Whop({
            apiKey: process.env['WHOP_API_KEY'], // This is the default and can be omitted
          });

          const conversion = await client.conversions.create({
            company_id: 'biz_xxxxxxxxxxxxxx',
            event_name: 'lead',
          });

          console.log(conversion.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          conversion = client.conversions.create(
              company_id="biz_xxxxxxxxxxxxxx",
              event_name="lead",
          )
          print(conversion.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          conversion = whop.conversions.create(company_id: "biz_xxxxxxxxxxxxxx", event_name: :lead)

          puts(conversion)
components:
  schemas:
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
    EventActionSource:
      type: string
      enum:
      - email
      - website
      - app
      - phone_call
      - chat
      - physical_store
      - system_generated
      - business_messaging
      - other
      description: The channel where an event originated
    EventGender:
      type: string
      enum:
      - male
      - female
      description: Gender
    EventName:
      type: string
      enum:
      - lead
      - submit_application
      - contact
      - complete_registration
      - schedule
      - custom
      description: The type of conversion or engagement event
```
