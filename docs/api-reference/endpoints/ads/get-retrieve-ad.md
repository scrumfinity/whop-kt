# GET /ads/{id} — Retrieve ad

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/ads/{id}`
- **Operation ID:** `retrieveAd`
- **Tags:** `Ads`
- **Required bearer scopes:** `ad_campaign:basic:read`

## Description

Retrieve an ad by its unique identifier.

Required permissions:
 - `ad_campaign:basic:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `id` | path | yes | `string` | The unique identifier of the ad. | xad_xxxxxxxxxxxxxx |

## Request body

_No request body._

## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `Ad` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/Ad"
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

const ad = await client.ads.retrieve('xad_xxxxxxxxxxxxxx');

console.log(ad.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
ad = client.ads.retrieve(
    "xad_xxxxxxxxxxxxxx",
)
print(ad.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

ad = whop.ads.retrieve("xad_xxxxxxxxxxxxxx")

puts(ad)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  Ad:
    type: object
    properties:
      id:
        type: string
        description: Unique identifier for the ad.
        example: xad_xxxxxxxxxxxxxx
      status:
        "$ref": "#/components/schemas/ExternalAdStatuses"
        description: Current status of the ad.
      created_at:
        type: string
        format: date-time
        description: When the ad was created.
        example: '2023-12-01T05:00:00.401Z'
      updated_at:
        type: string
        format: date-time
        description: When the ad was last updated.
        example: '2023-12-01T05:00:00.401Z'
      platform_config:
        type:
        - object
        - 'null'
        oneOf:
        - type:
          - object
          - 'null'
          properties:
            typename:
              type: string
              const: MetaAdPlatformConfigType
              description: The typename of this object
            platform:
              "$ref": "#/components/schemas/AdCampaignPlatforms"
              description: The ad platform.
            name:
              type:
              - string
              - 'null'
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            link_url:
              type:
              - string
              - 'null'
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            url_tags:
              type:
              - string
              - 'null'
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            primary_text:
              type:
              - string
              - 'null'
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            headline:
              type:
              - string
              - 'null'
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            call_to_action_type:
              oneOf:
              - "$ref": "#/components/schemas/MetaAdPlatformConfigCallToActionType"
              - type: 'null'
            page_id:
              type:
              - string
              - 'null'
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            instagram_actor_id:
              type:
              - string
              - 'null'
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
          required:
          - typename
          - platform
          - name
          - link_url
          - url_tags
          - primary_text
          - headline
          - call_to_action_type
          - page_id
          - instagram_actor_id
          description: Meta (Facebook/Instagram) ad configuration.
          title: MetaAdPlatformConfigType
        - type:
          - object
          - 'null'
          properties:
            typename:
              type: string
              const: TiktokAdPlatformConfigType
              description: The typename of this object
            platform:
              "$ref": "#/components/schemas/AdCampaignPlatforms"
              description: The ad platform.
            ad_name:
              type:
              - string
              - 'null'
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            ad_format:
              oneOf:
              - "$ref": "#/components/schemas/TiktokAdPlatformConfigAdFormat"
              - type: 'null'
            identity_type:
              oneOf:
              - "$ref": "#/components/schemas/TiktokAdPlatformConfigIdentityType"
              - type: 'null'
            identity_id:
              type:
              - string
              - 'null'
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            identity_authorized_bc_id:
              type:
              - string
              - 'null'
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            video_id:
              type:
              - string
              - 'null'
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            image_ids:
              type:
              - array
              - 'null'
              items:
                type: string
                description: Represents textual data as UTF-8 character sequences.
                  This type is most often used by GraphQL to represent free-form human-readable
                  text.
            ad_text:
              type:
              - string
              - 'null'
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            call_to_action:
              oneOf:
              - "$ref": "#/components/schemas/TiktokCallToAction"
              - type: 'null'
            landing_page_url:
              type:
              - string
              - 'null'
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
          required:
          - typename
          - platform
          - ad_name
          - ad_format
          - identity_type
          - identity_id
          - identity_authorized_bc_id
          - video_id
          - image_ids
          - ad_text
          - call_to_action
          - landing_page_url
          description: TikTok ad configuration.
          title: TiktokAdPlatformConfigType
        discriminator:
          propertyName: typename
        description: Typed platform-specific configuration.
      external_ad_group:
        type: object
        properties:
          id:
            type: string
            description: The unique identifier for the external ad group.
            example: adgrp_xxxxxxxxxxxx
          name:
            type:
            - string
            - 'null'
            description: Human-readable ad group name
          status:
            "$ref": "#/components/schemas/ExternalAdGroupStatuses"
            description: Current operational status of the ad group
        required:
        - id
        - name
        - status
        description: The parent ad group.
      external_ad_creative_set:
        type:
        - object
        - 'null'
        properties:
          id:
            type: string
            description: The unique identifier for the external ad creative set.
            example: adcs_xxxxxxxxxxxxx
        required:
        - id
        description: The creative set used by this ad.
    required:
    - id
    - status
    - created_at
    - updated_at
    - platform_config
    - external_ad_group
    - external_ad_creative_set
    description: An ad belonging to an ad group
  AdCampaignPlatforms:
    type: string
    enum:
    - meta
    - tiktok
    description: The platforms where an ad campaign can run.
  ExternalAdGroupStatuses:
    type: string
    enum:
    - active
    - paused
    - inactive
    - in_review
    - rejected
    - flagged
    description: The status of an external ad group.
  ExternalAdStatuses:
    type: string
    enum:
    - active
    - paused
    - inactive
    - in_review
    - rejected
    - flagged
    description: The status of an external ad.
  MetaAdPlatformConfigCallToActionType:
    type: string
    enum:
    - LEARN_MORE
    - SHOP_NOW
    - SIGN_UP
    - SUBSCRIBE
    - GET_STARTED
    - BOOK_NOW
    - APPLY_NOW
    - CONTACT_US
    - DOWNLOAD
    - ORDER_NOW
    - BUY_NOW
    - GET_QUOTE
    - MESSAGE_PAGE
    - WHATSAPP_MESSAGE
    - INSTAGRAM_MESSAGE
    - CALL_NOW
    - GET_DIRECTIONS
    - SEND_UPDATES
    - GET_OFFER
    - WATCH_MORE
    - LISTEN_NOW
    - PLAY_GAME
    - OPEN_LINK
    - NO_BUTTON
    - GET_OFFER_VIEW
    - GET_EVENT_TICKETS
    - SEE_MENU
    - REQUEST_TIME
    - EVENT_RSVP
    - SEE_DETAILS
    - VIEW_INSTAGRAM_PROFILE
  TiktokAdPlatformConfigAdFormat:
    type: string
    enum:
    - SINGLE_IMAGE
    - SINGLE_VIDEO
    - CAROUSEL_ADS
    - CATALOG_CAROUSEL
    - LIVE_CONTENT
  TiktokAdPlatformConfigIdentityType:
    type: string
    enum:
    - CUSTOMIZED_USER
    - AUTH_CODE
    - TT_USER
    - BC_AUTH_TT
  TiktokCallToAction:
    type: string
    enum:
    - LEARN_MORE
    - DOWNLOAD
    - SHOP_NOW
    - SIGN_UP
    - CONTACT_US
    - APPLY_NOW
    - BOOK_NOW
    - PLAY_GAME
    - WATCH_NOW
    - READ_MORE
    - VIEW_NOW
    - GET_QUOTE
    - ORDER_NOW
    - INSTALL_NOW
    - GET_SHOWTIMES
    - LISTEN_NOW
    - INTERESTED
    - SUBSCRIBE
    - GET_TICKETS_NOW
    - EXPERIENCE_NOW
    - PRE_ORDER_NOW
    - VISIT_STORE
    description: TikTok call-to-action button text. See docs/tiktok_api/ad.md § call_to_action.
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
  "/ads/{id}":
    get:
      tags:
      - Ads
      operationId: retrieveAd
      summary: Retrieve ad
      description: |-
        Retrieve an ad by its unique identifier.

        Required permissions:
         - `ad_campaign:basic:read`
      parameters:
      - name: id
        in: path
        required: true
        description: The unique identifier of the ad.
        schema:
          type: string
          example: xad_xxxxxxxxxxxxxx
      x-group-title: Ads
      security:
      - bearerAuth:
        - ad_campaign:basic:read
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/Ad"
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

          const ad = await client.ads.retrieve('xad_xxxxxxxxxxxxxx');

          console.log(ad.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          ad = client.ads.retrieve(
              "xad_xxxxxxxxxxxxxx",
          )
          print(ad.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          ad = whop.ads.retrieve("xad_xxxxxxxxxxxxxx")

          puts(ad)
components:
  schemas:
    Ad:
      type: object
      properties:
        id:
          type: string
          description: Unique identifier for the ad.
          example: xad_xxxxxxxxxxxxxx
        status:
          "$ref": "#/components/schemas/ExternalAdStatuses"
          description: Current status of the ad.
        created_at:
          type: string
          format: date-time
          description: When the ad was created.
          example: '2023-12-01T05:00:00.401Z'
        updated_at:
          type: string
          format: date-time
          description: When the ad was last updated.
          example: '2023-12-01T05:00:00.401Z'
        platform_config:
          type:
          - object
          - 'null'
          oneOf:
          - type:
            - object
            - 'null'
            properties:
              typename:
                type: string
                const: MetaAdPlatformConfigType
                description: The typename of this object
              platform:
                "$ref": "#/components/schemas/AdCampaignPlatforms"
                description: The ad platform.
              name:
                type:
                - string
                - 'null'
                description: Represents textual data as UTF-8 character sequences.
                  This type is most often used by GraphQL to represent free-form human-readable
                  text.
              link_url:
                type:
                - string
                - 'null'
                description: Represents textual data as UTF-8 character sequences.
                  This type is most often used by GraphQL to represent free-form human-readable
                  text.
              url_tags:
                type:
                - string
                - 'null'
                description: Represents textual data as UTF-8 character sequences.
                  This type is most often used by GraphQL to represent free-form human-readable
                  text.
              primary_text:
                type:
                - string
                - 'null'
                description: Represents textual data as UTF-8 character sequences.
                  This type is most often used by GraphQL to represent free-form human-readable
                  text.
              headline:
                type:
                - string
                - 'null'
                description: Represents textual data as UTF-8 character sequences.
                  This type is most often used by GraphQL to represent free-form human-readable
                  text.
              call_to_action_type:
                oneOf:
                - "$ref": "#/components/schemas/MetaAdPlatformConfigCallToActionType"
                - type: 'null'
              page_id:
                type:
                - string
                - 'null'
                description: Represents textual data as UTF-8 character sequences.
                  This type is most often used by GraphQL to represent free-form human-readable
                  text.
              instagram_actor_id:
                type:
                - string
                - 'null'
                description: Represents textual data as UTF-8 character sequences.
                  This type is most often used by GraphQL to represent free-form human-readable
                  text.
            required:
            - typename
            - platform
            - name
            - link_url
            - url_tags
            - primary_text
            - headline
            - call_to_action_type
            - page_id
            - instagram_actor_id
            description: Meta (Facebook/Instagram) ad configuration.
            title: MetaAdPlatformConfigType
          - type:
            - object
            - 'null'
            properties:
              typename:
                type: string
                const: TiktokAdPlatformConfigType
                description: The typename of this object
              platform:
                "$ref": "#/components/schemas/AdCampaignPlatforms"
                description: The ad platform.
              ad_name:
                type:
                - string
                - 'null'
                description: Represents textual data as UTF-8 character sequences.
                  This type is most often used by GraphQL to represent free-form human-readable
                  text.
              ad_format:
                oneOf:
                - "$ref": "#/components/schemas/TiktokAdPlatformConfigAdFormat"
                - type: 'null'
              identity_type:
                oneOf:
                - "$ref": "#/components/schemas/TiktokAdPlatformConfigIdentityType"
                - type: 'null'
              identity_id:
                type:
                - string
                - 'null'
                description: Represents textual data as UTF-8 character sequences.
                  This type is most often used by GraphQL to represent free-form human-readable
                  text.
              identity_authorized_bc_id:
                type:
                - string
                - 'null'
                description: Represents textual data as UTF-8 character sequences.
                  This type is most often used by GraphQL to represent free-form human-readable
                  text.
              video_id:
                type:
                - string
                - 'null'
                description: Represents textual data as UTF-8 character sequences.
                  This type is most often used by GraphQL to represent free-form human-readable
                  text.
              image_ids:
                type:
                - array
                - 'null'
                items:
                  type: string
                  description: Represents textual data as UTF-8 character sequences.
                    This type is most often used by GraphQL to represent free-form
                    human-readable text.
              ad_text:
                type:
                - string
                - 'null'
                description: Represents textual data as UTF-8 character sequences.
                  This type is most often used by GraphQL to represent free-form human-readable
                  text.
              call_to_action:
                oneOf:
                - "$ref": "#/components/schemas/TiktokCallToAction"
                - type: 'null'
              landing_page_url:
                type:
                - string
                - 'null'
                description: Represents textual data as UTF-8 character sequences.
                  This type is most often used by GraphQL to represent free-form human-readable
                  text.
            required:
            - typename
            - platform
            - ad_name
            - ad_format
            - identity_type
            - identity_id
            - identity_authorized_bc_id
            - video_id
            - image_ids
            - ad_text
            - call_to_action
            - landing_page_url
            description: TikTok ad configuration.
            title: TiktokAdPlatformConfigType
          discriminator:
            propertyName: typename
          description: Typed platform-specific configuration.
        external_ad_group:
          type: object
          properties:
            id:
              type: string
              description: The unique identifier for the external ad group.
              example: adgrp_xxxxxxxxxxxx
            name:
              type:
              - string
              - 'null'
              description: Human-readable ad group name
            status:
              "$ref": "#/components/schemas/ExternalAdGroupStatuses"
              description: Current operational status of the ad group
          required:
          - id
          - name
          - status
          description: The parent ad group.
        external_ad_creative_set:
          type:
          - object
          - 'null'
          properties:
            id:
              type: string
              description: The unique identifier for the external ad creative set.
              example: adcs_xxxxxxxxxxxxx
          required:
          - id
          description: The creative set used by this ad.
      required:
      - id
      - status
      - created_at
      - updated_at
      - platform_config
      - external_ad_group
      - external_ad_creative_set
      description: An ad belonging to an ad group
    AdCampaignPlatforms:
      type: string
      enum:
      - meta
      - tiktok
      description: The platforms where an ad campaign can run.
    ExternalAdGroupStatuses:
      type: string
      enum:
      - active
      - paused
      - inactive
      - in_review
      - rejected
      - flagged
      description: The status of an external ad group.
    ExternalAdStatuses:
      type: string
      enum:
      - active
      - paused
      - inactive
      - in_review
      - rejected
      - flagged
      description: The status of an external ad.
    MetaAdPlatformConfigCallToActionType:
      type: string
      enum:
      - LEARN_MORE
      - SHOP_NOW
      - SIGN_UP
      - SUBSCRIBE
      - GET_STARTED
      - BOOK_NOW
      - APPLY_NOW
      - CONTACT_US
      - DOWNLOAD
      - ORDER_NOW
      - BUY_NOW
      - GET_QUOTE
      - MESSAGE_PAGE
      - WHATSAPP_MESSAGE
      - INSTAGRAM_MESSAGE
      - CALL_NOW
      - GET_DIRECTIONS
      - SEND_UPDATES
      - GET_OFFER
      - WATCH_MORE
      - LISTEN_NOW
      - PLAY_GAME
      - OPEN_LINK
      - NO_BUTTON
      - GET_OFFER_VIEW
      - GET_EVENT_TICKETS
      - SEE_MENU
      - REQUEST_TIME
      - EVENT_RSVP
      - SEE_DETAILS
      - VIEW_INSTAGRAM_PROFILE
    TiktokAdPlatformConfigAdFormat:
      type: string
      enum:
      - SINGLE_IMAGE
      - SINGLE_VIDEO
      - CAROUSEL_ADS
      - CATALOG_CAROUSEL
      - LIVE_CONTENT
    TiktokAdPlatformConfigIdentityType:
      type: string
      enum:
      - CUSTOMIZED_USER
      - AUTH_CODE
      - TT_USER
      - BC_AUTH_TT
    TiktokCallToAction:
      type: string
      enum:
      - LEARN_MORE
      - DOWNLOAD
      - SHOP_NOW
      - SIGN_UP
      - CONTACT_US
      - APPLY_NOW
      - BOOK_NOW
      - PLAY_GAME
      - WATCH_NOW
      - READ_MORE
      - VIEW_NOW
      - GET_QUOTE
      - ORDER_NOW
      - INSTALL_NOW
      - GET_SHOWTIMES
      - LISTEN_NOW
      - INTERESTED
      - SUBSCRIBE
      - GET_TICKETS_NOW
      - EXPERIENCE_NOW
      - PRE_ORDER_NOW
      - VISIT_STORE
      description: TikTok call-to-action button text. See docs/tiktok_api/ad.md §
        call_to_action.
```
