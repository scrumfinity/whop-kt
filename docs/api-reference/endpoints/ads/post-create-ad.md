# POST /ads — Create ad

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `POST`
- **Path:** `/ads`
- **Operation ID:** `createAd`
- **Tags:** `Ads`
- **Required bearer scopes:** `ad_campaign:create`

## Description

Create an ad within an ad group.

Required permissions:
 - `ad_campaign:create`

## Parameters

_No path, query, header, or cookie parameters._

## Request body

Required: **yes**
Content types: `application/json`

### `application/json` request schema

```yaml
type: object
properties:
  ad_group_id:
    type: string
    description: The unique identifier of the ad group to create this ad in.
  creative_set_id:
    type:
    - string
    - 'null'
    description: The unique identifier of the creative set to use.
  existing_instagram_media_id:
    type:
    - string
    - 'null'
    description: ID of an existing Instagram media item to use as the ad creative
      (instead of a creative set or Facebook post).
  existing_post_id:
    type:
    - string
    - 'null'
    description: ID of an existing Facebook post to use as the ad creative (instead
      of a creative set).
  platform_config:
    type:
    - object
    - 'null'
    properties:
      meta:
        type:
        - object
        - 'null'
        properties:
          name:
            type:
            - string
            - 'null'
            description: Ad name.
          link_url:
            type:
            - string
            - 'null'
            description: Destination URL.
          url_tags:
            type:
            - string
            - 'null'
            description: URL query parameters appended to the destination link.
          primary_text:
            type:
            - string
            - 'null'
            description: Primary text of the ad creative (legacy single-value).
          headline:
            type:
            - string
            - 'null'
            description: Headline of the ad creative (legacy single-value).
          description:
            type:
            - string
            - 'null'
            description: Description of the ad creative (legacy single-value).
          primary_texts:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            description: Up to 5 primary-text variants, rendered via Meta asset_feed_spec.
          headlines:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            description: Up to 5 headline variants, rendered via Meta asset_feed_spec.
          descriptions:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            description: Up to 5 description variants, rendered via Meta asset_feed_spec.
          call_to_action_type:
            oneOf:
            - "$ref": "#/components/schemas/MetaAdPlatformConfigCallToActionType"
            - type: 'null'
            description: Call-to-action button type.
          page_id:
            type:
            - string
            - 'null'
            description: Unique identifier of the Facebook Page.
          instagram_actor_id:
            type:
            - string
            - 'null'
            description: Unique identifier of the Instagram account.
          existing_post_id:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          existing_instagram_media_id:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          multi_advertiser_enrollment:
            oneOf:
            - "$ref": "#/components/schemas/MetaAdPlatformConfigMultiAdvertiserEnrollment"
            - type: 'null'
          carousel_cards:
            type:
            - array
            - 'null'
            items:
              type: object
              properties:
                call_to_action_type:
                  type:
                  - string
                  - 'null'
                  description: CTA button type (e.g., SHOP_NOW, LEARN_MORE).
                description:
                  type:
                  - string
                  - 'null'
                  description: Card description (max 30 chars recommended).
                link:
                  type:
                  - string
                  - 'null'
                  description: Destination URL for this card (defaults to ad destination).
                name:
                  type:
                  - string
                  - 'null'
                  description: Card title (max 35 chars recommended).
              required: []
              description: Per-card configuration for a carousel ad.
            description: Per-card carousel config.
          lead_form_config:
            type:
            - object
            - 'null'
            additionalProperties: true
            description: Lead generation form configuration (JSON).
          page_welcome_message:
            type:
            - object
            - 'null'
            additionalProperties: true
            description: Messenger welcome message / ice-breaker template (JSON).
        required: []
        description: Configuration for Meta (Facebook/Instagram) ads.
      tiktok:
        type:
        - object
        - 'null'
        properties:
          ad_name:
            type:
            - string
            - 'null'
            description: Ad name.
          access_pass_tag:
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
          ad_format:
            oneOf:
            - "$ref": "#/components/schemas/TiktokAdPlatformConfigAdFormat"
            - type: 'null'
            description: Ad format (SINGLE_IMAGE, SINGLE_VIDEO, CAROUSEL_ADS, CATALOG_CAROUSEL,
              LIVE_CONTENT).
          identity_type:
            oneOf:
            - "$ref": "#/components/schemas/TiktokAdPlatformConfigIdentityType"
            - type: 'null'
            description: Identity type.
          identity_id:
            type:
            - string
            - 'null'
            description: Unique identifier of the identity.
          identity_authorized_bc_id:
            type:
            - string
            - 'null'
            description: Business Center ID (required when identity_type is BC_AUTH_TT).
          video_id:
            type:
            - string
            - 'null'
            description: Unique identifier of the video.
          image_ids:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            description: Unique identifiers of the images.
          tiktok_item_id:
            type:
            - string
            - 'null'
            description: TikTok item ID for Spark Ads (promotes an organic post).
          ad_text:
            type:
            - string
            - 'null'
            description: Ad copy (single variant).
          ad_texts:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            description: Ad copy variants for search ads (up to 5).
          call_to_action_enabled:
            type:
            - boolean
            - 'null'
            description: Represents `true` or `false` values.
          call_to_action_mode:
            oneOf:
            - "$ref": "#/components/schemas/TiktokCallToActionMode"
            - type: 'null'
          call_to_action:
            oneOf:
            - "$ref": "#/components/schemas/TiktokCallToAction"
            - type: 'null'
            description: Call-to-action type.
          call_to_action_id:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          landing_page_url:
            type:
            - string
            - 'null'
            description: Landing page URL.
          page_id:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          deeplink:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          deeplink_type:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          cpp_url:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          utm_params:
            type:
            - array
            - 'null'
            items:
              type: object
              additionalProperties: true
              description: Represents untyped JSON
          catalog_id:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          product_specific_type:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          item_group_ids:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
          product_set_id:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          sku_ids:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
          showcase_products:
            type:
            - array
            - 'null'
            items:
              type: object
              additionalProperties: true
              description: Represents untyped JSON
          shopping_ads_video_package_id:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          dark_post_status:
            oneOf:
            - "$ref": "#/components/schemas/TiktokAdPlatformConfigDarkPostStatus"
            - type: 'null'
          promotional_music_disabled:
            type:
            - boolean
            - 'null'
            description: Represents `true` or `false` values.
          item_duet_status:
            oneOf:
            - "$ref": "#/components/schemas/TiktokAdPlatformConfigItemDuetStatus"
            - type: 'null'
          item_stitch_status:
            oneOf:
            - "$ref": "#/components/schemas/TiktokAdPlatformConfigItemStitchStatus"
            - type: 'null'
          music_id:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          carousel_image_index:
            type:
            - integer
            - 'null'
            description: Represents non-fractional signed whole numeric values. Int
              can represent values between -(2^31) and 2^31 - 1.
            example: 42
          card_id:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          automate_creative_enabled:
            type:
            - boolean
            - 'null'
            description: Represents `true` or `false` values.
          creative_auto_enhancement_strategy_list:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
          vertical_video_strategy:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          dynamic_format:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          tracking_pixel_id:
            type:
            - string
            - 'null'
            description: TikTok pixel ID used for conversion tracking on this ad.
            example: trpx_xxxxxxxxxxxxx
          tracking_app_id:
            type:
            - string
            - 'null'
            description: TikTok MMP-tracked app ID.
          tracking_offline_event_set_ids:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            description: Offline event set IDs for attribution.
          tracking_message_event_set_id:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          impression_tracking_url:
            type:
            - string
            - 'null'
            description: Third-party impression tracker URL.
          click_tracking_url:
            type:
            - string
            - 'null'
            description: Third-party click tracker URL.
          video_view_tracking_url:
            type:
            - string
            - 'null'
            description: Third-party video-view tracker URL.
          brand_safety_postbid_partner:
            oneOf:
            - "$ref": "#/components/schemas/TiktokBrandSafetyPartner"
            - type: 'null'
            description: Post-bid brand safety vendor.
          brand_safety_vast_url:
            type:
            - string
            - 'null'
            description: VAST URL for brand safety measurement.
          viewability_postbid_partner:
            oneOf:
            - "$ref": "#/components/schemas/TiktokViewabilityPartner"
            - type: 'null'
            description: Post-bid viewability measurement partner.
          viewability_vast_url:
            type:
            - string
            - 'null'
            description: VAST URL for viewability measurement.
          aigc_disclosure_type:
            oneOf:
            - "$ref": "#/components/schemas/TiktokAigcDisclosure"
            - type: 'null'
            description: Whether the creative contains AI-generated content.
          disclaimer_type:
            oneOf:
            - "$ref": "#/components/schemas/TiktokDisclaimer"
            - type: 'null'
            description: Disclaimer mode for regulated industries.
          disclaimer_text:
            type:
            - string
            - 'null'
            description: Plain text shown when disclaimer_type is DISCLAIMER_TEXT
              / DISCLAIMER_WITH_URL.
          disclaimer_clickable_texts:
            type:
            - array
            - 'null'
            items:
              type: object
              additionalProperties: true
              description: Represents untyped JSON
            description: Clickable disclaimer segments (text + url).
          auto_disclaimer_types:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            description: Automatic disclaimer categories (e.g., FINANCE, ALCOHOL).
          creative_authorized:
            type:
            - boolean
            - 'null'
            description: Whether the creator has authorized the use of this creative
              for paid promotion (Spark Ads).
          fallback_type:
            oneOf:
            - "$ref": "#/components/schemas/TiktokFallback"
            - type: 'null'
            description: Destination fallback when a deferred deeplink fails.
          deeplink_format_type:
            oneOf:
            - "$ref": "#/components/schemas/TiktokDeeplinkFormat"
            - type: 'null'
            description: How the deeplink is resolved (DEEPLINK / DEFERRED_DEEPLINK).
          deeplink_utm_params:
            type:
            - array
            - 'null'
            items:
              type: object
              additionalProperties: true
              description: Represents untyped JSON
            description: UTM params appended to the deeplink.
          shopping_ads_fallback_type:
            oneOf:
            - "$ref": "#/components/schemas/TiktokShoppingAdsFallback"
            - type: 'null'
            description: Fallback destination for shopping ads.
          end_card_cta:
            type:
            - string
            - 'null'
            description: End-card CTA text for video ads.
          product_display_field_list:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            description: Fields displayed on dynamic product cards.
          dynamic_destination:
            type:
            - string
            - 'null'
            description: Dynamic destination strategy for shopping ads.
        required: []
        description: Configuration for TikTok ads.
    required: []
    description: Platform-specific configuration. Must match the campaign platform.
  status:
    oneOf:
    - "$ref": "#/components/schemas/ExternalAdStatuses"
    - type: 'null'
    description: Initial status (defaults to active).
required:
- ad_group_id
description: Parameters for CreateAd
```


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

const ad = await client.ads.create({ ad_group_id: 'ad_group_id' });

console.log(ad.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
ad = client.ads.create(
    ad_group_id="ad_group_id",
)
print(ad.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

ad = whop.ads.create(ad_group_id: "ad_group_id")

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
  MetaAdPlatformConfigMultiAdvertiserEnrollment:
    type: string
    enum:
    - OPT_IN
    - OPT_OUT
  TiktokAdPlatformConfigAdFormat:
    type: string
    enum:
    - SINGLE_IMAGE
    - SINGLE_VIDEO
    - CAROUSEL_ADS
    - CATALOG_CAROUSEL
    - LIVE_CONTENT
  TiktokAdPlatformConfigDarkPostStatus:
    type: string
    enum:
    - 'ON'
    - 'OFF'
  TiktokAdPlatformConfigIdentityType:
    type: string
    enum:
    - CUSTOMIZED_USER
    - AUTH_CODE
    - TT_USER
    - BC_AUTH_TT
  TiktokAdPlatformConfigItemDuetStatus:
    type: string
    enum:
    - ENABLE
    - DISABLE
  TiktokAdPlatformConfigItemStitchStatus:
    type: string
    enum:
    - ENABLE
    - DISABLE
  TiktokAigcDisclosure:
    type: string
    enum:
    - UNSET
    - CONTAINS_AIGC
    - IS_AIGC
    - NOT_AIGC
    description: Whether the ad creative is AI-generated content. See docs/tiktok_api/ad.md
      § aigc_disclosure_type.
  TiktokBrandSafetyPartner:
    type: string
    enum:
    - UNSET
    - IAS
    - DOUBLE_VERIFY
    - OPEN_SLATE
    - ZEFR
    description: Post-bid brand-safety vendor. See docs/tiktok_api/ad.md § brand_safety_postbid_partner.
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
  TiktokCallToActionMode:
    type: string
    enum:
    - STANDARD
    - DYNAMIC
    description: How the call-to-action text is chosen. STANDARD uses a single fixed
      CTA; DYNAMIC lets TikTok rotate through a set of CTAs to maximize performance.
  TiktokDeeplinkFormat:
    type: string
    enum:
    - UNSET
    - DEEPLINK
    - DEFERRED_DEEPLINK
    description: How the ad's deeplink is resolved. See docs/tiktok_api/ad.md § deeplink_format_type.
  TiktokDisclaimer:
    type: string
    enum:
    - NONE
    - DISCLAIMER_TEXT
    - DISCLAIMER_WITH_URL
    description: Ad disclaimer mode. See docs/tiktok_api/ad.md § disclaimer_type.
  TiktokFallback:
    type: string
    enum:
    - UNSET
    - APP_STORE
    - LANDING_PAGE
    description: Destination fallback when a deferred deeplink cannot open the app.
      See docs/tiktok_api/ad.md § fallback_type.
  TiktokShoppingAdsFallback:
    type: string
    enum:
    - UNSET
    - LANDING_PAGE
    - STORE
    description: Fallback destination for shopping ads when the primary target is
      unavailable. See docs/tiktok_api/ad.md § shopping_ads_fallback_type.
  TiktokViewabilityPartner:
    type: string
    enum:
    - UNSET
    - IAS
    - DOUBLE_VERIFY
    - MOAT
    description: Post-bid viewability measurement partner. See docs/tiktok_api/ad.md
      § viewability_postbid_partner.
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
  "/ads":
    post:
      tags:
      - Ads
      operationId: createAd
      summary: Create ad
      description: |-
        Create an ad within an ad group.

        Required permissions:
         - `ad_campaign:create`
      parameters: []
      x-group-title: Ads
      security:
      - bearerAuth:
        - ad_campaign:create
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
      requestBody:
        required: true
        content:
          application/json:
            schema:
              type: object
              properties:
                ad_group_id:
                  type: string
                  description: The unique identifier of the ad group to create this
                    ad in.
                creative_set_id:
                  type:
                  - string
                  - 'null'
                  description: The unique identifier of the creative set to use.
                existing_instagram_media_id:
                  type:
                  - string
                  - 'null'
                  description: ID of an existing Instagram media item to use as the
                    ad creative (instead of a creative set or Facebook post).
                existing_post_id:
                  type:
                  - string
                  - 'null'
                  description: ID of an existing Facebook post to use as the ad creative
                    (instead of a creative set).
                platform_config:
                  type:
                  - object
                  - 'null'
                  properties:
                    meta:
                      type:
                      - object
                      - 'null'
                      properties:
                        name:
                          type:
                          - string
                          - 'null'
                          description: Ad name.
                        link_url:
                          type:
                          - string
                          - 'null'
                          description: Destination URL.
                        url_tags:
                          type:
                          - string
                          - 'null'
                          description: URL query parameters appended to the destination
                            link.
                        primary_text:
                          type:
                          - string
                          - 'null'
                          description: Primary text of the ad creative (legacy single-value).
                        headline:
                          type:
                          - string
                          - 'null'
                          description: Headline of the ad creative (legacy single-value).
                        description:
                          type:
                          - string
                          - 'null'
                          description: Description of the ad creative (legacy single-value).
                        primary_texts:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                          description: Up to 5 primary-text variants, rendered via
                            Meta asset_feed_spec.
                        headlines:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                          description: Up to 5 headline variants, rendered via Meta
                            asset_feed_spec.
                        descriptions:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                          description: Up to 5 description variants, rendered via
                            Meta asset_feed_spec.
                        call_to_action_type:
                          oneOf:
                          - "$ref": "#/components/schemas/MetaAdPlatformConfigCallToActionType"
                          - type: 'null'
                          description: Call-to-action button type.
                        page_id:
                          type:
                          - string
                          - 'null'
                          description: Unique identifier of the Facebook Page.
                        instagram_actor_id:
                          type:
                          - string
                          - 'null'
                          description: Unique identifier of the Instagram account.
                        existing_post_id:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        existing_instagram_media_id:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        multi_advertiser_enrollment:
                          oneOf:
                          - "$ref": "#/components/schemas/MetaAdPlatformConfigMultiAdvertiserEnrollment"
                          - type: 'null'
                        carousel_cards:
                          type:
                          - array
                          - 'null'
                          items:
                            type: object
                            properties:
                              call_to_action_type:
                                type:
                                - string
                                - 'null'
                                description: CTA button type (e.g., SHOP_NOW, LEARN_MORE).
                              description:
                                type:
                                - string
                                - 'null'
                                description: Card description (max 30 chars recommended).
                              link:
                                type:
                                - string
                                - 'null'
                                description: Destination URL for this card (defaults
                                  to ad destination).
                              name:
                                type:
                                - string
                                - 'null'
                                description: Card title (max 35 chars recommended).
                            required: []
                            description: Per-card configuration for a carousel ad.
                          description: Per-card carousel config.
                        lead_form_config:
                          type:
                          - object
                          - 'null'
                          additionalProperties: true
                          description: Lead generation form configuration (JSON).
                        page_welcome_message:
                          type:
                          - object
                          - 'null'
                          additionalProperties: true
                          description: Messenger welcome message / ice-breaker template
                            (JSON).
                      required: []
                      description: Configuration for Meta (Facebook/Instagram) ads.
                    tiktok:
                      type:
                      - object
                      - 'null'
                      properties:
                        ad_name:
                          type:
                          - string
                          - 'null'
                          description: Ad name.
                        access_pass_tag:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        link_url:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        ad_format:
                          oneOf:
                          - "$ref": "#/components/schemas/TiktokAdPlatformConfigAdFormat"
                          - type: 'null'
                          description: Ad format (SINGLE_IMAGE, SINGLE_VIDEO, CAROUSEL_ADS,
                            CATALOG_CAROUSEL, LIVE_CONTENT).
                        identity_type:
                          oneOf:
                          - "$ref": "#/components/schemas/TiktokAdPlatformConfigIdentityType"
                          - type: 'null'
                          description: Identity type.
                        identity_id:
                          type:
                          - string
                          - 'null'
                          description: Unique identifier of the identity.
                        identity_authorized_bc_id:
                          type:
                          - string
                          - 'null'
                          description: Business Center ID (required when identity_type
                            is BC_AUTH_TT).
                        video_id:
                          type:
                          - string
                          - 'null'
                          description: Unique identifier of the video.
                        image_ids:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                          description: Unique identifiers of the images.
                        tiktok_item_id:
                          type:
                          - string
                          - 'null'
                          description: TikTok item ID for Spark Ads (promotes an organic
                            post).
                        ad_text:
                          type:
                          - string
                          - 'null'
                          description: Ad copy (single variant).
                        ad_texts:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                          description: Ad copy variants for search ads (up to 5).
                        call_to_action_enabled:
                          type:
                          - boolean
                          - 'null'
                          description: Represents `true` or `false` values.
                        call_to_action_mode:
                          oneOf:
                          - "$ref": "#/components/schemas/TiktokCallToActionMode"
                          - type: 'null'
                        call_to_action:
                          oneOf:
                          - "$ref": "#/components/schemas/TiktokCallToAction"
                          - type: 'null'
                          description: Call-to-action type.
                        call_to_action_id:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        landing_page_url:
                          type:
                          - string
                          - 'null'
                          description: Landing page URL.
                        page_id:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        deeplink:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        deeplink_type:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        cpp_url:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        utm_params:
                          type:
                          - array
                          - 'null'
                          items:
                            type: object
                            additionalProperties: true
                            description: Represents untyped JSON
                        catalog_id:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        product_specific_type:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        item_group_ids:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                        product_set_id:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        sku_ids:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                        showcase_products:
                          type:
                          - array
                          - 'null'
                          items:
                            type: object
                            additionalProperties: true
                            description: Represents untyped JSON
                        shopping_ads_video_package_id:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        dark_post_status:
                          oneOf:
                          - "$ref": "#/components/schemas/TiktokAdPlatformConfigDarkPostStatus"
                          - type: 'null'
                        promotional_music_disabled:
                          type:
                          - boolean
                          - 'null'
                          description: Represents `true` or `false` values.
                        item_duet_status:
                          oneOf:
                          - "$ref": "#/components/schemas/TiktokAdPlatformConfigItemDuetStatus"
                          - type: 'null'
                        item_stitch_status:
                          oneOf:
                          - "$ref": "#/components/schemas/TiktokAdPlatformConfigItemStitchStatus"
                          - type: 'null'
                        music_id:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        carousel_image_index:
                          type:
                          - integer
                          - 'null'
                          description: Represents non-fractional signed whole numeric
                            values. Int can represent values between -(2^31) and 2^31
                            - 1.
                          example: 42
                        card_id:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        automate_creative_enabled:
                          type:
                          - boolean
                          - 'null'
                          description: Represents `true` or `false` values.
                        creative_auto_enhancement_strategy_list:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                        vertical_video_strategy:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        dynamic_format:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        tracking_pixel_id:
                          type:
                          - string
                          - 'null'
                          description: TikTok pixel ID used for conversion tracking
                            on this ad.
                          example: trpx_xxxxxxxxxxxxx
                        tracking_app_id:
                          type:
                          - string
                          - 'null'
                          description: TikTok MMP-tracked app ID.
                        tracking_offline_event_set_ids:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                          description: Offline event set IDs for attribution.
                        tracking_message_event_set_id:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        impression_tracking_url:
                          type:
                          - string
                          - 'null'
                          description: Third-party impression tracker URL.
                        click_tracking_url:
                          type:
                          - string
                          - 'null'
                          description: Third-party click tracker URL.
                        video_view_tracking_url:
                          type:
                          - string
                          - 'null'
                          description: Third-party video-view tracker URL.
                        brand_safety_postbid_partner:
                          oneOf:
                          - "$ref": "#/components/schemas/TiktokBrandSafetyPartner"
                          - type: 'null'
                          description: Post-bid brand safety vendor.
                        brand_safety_vast_url:
                          type:
                          - string
                          - 'null'
                          description: VAST URL for brand safety measurement.
                        viewability_postbid_partner:
                          oneOf:
                          - "$ref": "#/components/schemas/TiktokViewabilityPartner"
                          - type: 'null'
                          description: Post-bid viewability measurement partner.
                        viewability_vast_url:
                          type:
                          - string
                          - 'null'
                          description: VAST URL for viewability measurement.
                        aigc_disclosure_type:
                          oneOf:
                          - "$ref": "#/components/schemas/TiktokAigcDisclosure"
                          - type: 'null'
                          description: Whether the creative contains AI-generated
                            content.
                        disclaimer_type:
                          oneOf:
                          - "$ref": "#/components/schemas/TiktokDisclaimer"
                          - type: 'null'
                          description: Disclaimer mode for regulated industries.
                        disclaimer_text:
                          type:
                          - string
                          - 'null'
                          description: Plain text shown when disclaimer_type is DISCLAIMER_TEXT
                            / DISCLAIMER_WITH_URL.
                        disclaimer_clickable_texts:
                          type:
                          - array
                          - 'null'
                          items:
                            type: object
                            additionalProperties: true
                            description: Represents untyped JSON
                          description: Clickable disclaimer segments (text + url).
                        auto_disclaimer_types:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                          description: Automatic disclaimer categories (e.g., FINANCE,
                            ALCOHOL).
                        creative_authorized:
                          type:
                          - boolean
                          - 'null'
                          description: Whether the creator has authorized the use
                            of this creative for paid promotion (Spark Ads).
                        fallback_type:
                          oneOf:
                          - "$ref": "#/components/schemas/TiktokFallback"
                          - type: 'null'
                          description: Destination fallback when a deferred deeplink
                            fails.
                        deeplink_format_type:
                          oneOf:
                          - "$ref": "#/components/schemas/TiktokDeeplinkFormat"
                          - type: 'null'
                          description: How the deeplink is resolved (DEEPLINK / DEFERRED_DEEPLINK).
                        deeplink_utm_params:
                          type:
                          - array
                          - 'null'
                          items:
                            type: object
                            additionalProperties: true
                            description: Represents untyped JSON
                          description: UTM params appended to the deeplink.
                        shopping_ads_fallback_type:
                          oneOf:
                          - "$ref": "#/components/schemas/TiktokShoppingAdsFallback"
                          - type: 'null'
                          description: Fallback destination for shopping ads.
                        end_card_cta:
                          type:
                          - string
                          - 'null'
                          description: End-card CTA text for video ads.
                        product_display_field_list:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                          description: Fields displayed on dynamic product cards.
                        dynamic_destination:
                          type:
                          - string
                          - 'null'
                          description: Dynamic destination strategy for shopping ads.
                      required: []
                      description: Configuration for TikTok ads.
                  required: []
                  description: Platform-specific configuration. Must match the campaign
                    platform.
                status:
                  oneOf:
                  - "$ref": "#/components/schemas/ExternalAdStatuses"
                  - type: 'null'
                  description: Initial status (defaults to active).
              required:
              - ad_group_id
              description: Parameters for CreateAd
      x-codeSamples:
      - lang: JavaScript
        source: |-
          import Whop from '@whop/sdk';

          const client = new Whop({
            apiKey: process.env['WHOP_API_KEY'], // This is the default and can be omitted
          });

          const ad = await client.ads.create({ ad_group_id: 'ad_group_id' });

          console.log(ad.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          ad = client.ads.create(
              ad_group_id="ad_group_id",
          )
          print(ad.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          ad = whop.ads.create(ad_group_id: "ad_group_id")

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
    MetaAdPlatformConfigMultiAdvertiserEnrollment:
      type: string
      enum:
      - OPT_IN
      - OPT_OUT
    TiktokAdPlatformConfigAdFormat:
      type: string
      enum:
      - SINGLE_IMAGE
      - SINGLE_VIDEO
      - CAROUSEL_ADS
      - CATALOG_CAROUSEL
      - LIVE_CONTENT
    TiktokAdPlatformConfigDarkPostStatus:
      type: string
      enum:
      - 'ON'
      - 'OFF'
    TiktokAdPlatformConfigIdentityType:
      type: string
      enum:
      - CUSTOMIZED_USER
      - AUTH_CODE
      - TT_USER
      - BC_AUTH_TT
    TiktokAdPlatformConfigItemDuetStatus:
      type: string
      enum:
      - ENABLE
      - DISABLE
    TiktokAdPlatformConfigItemStitchStatus:
      type: string
      enum:
      - ENABLE
      - DISABLE
    TiktokAigcDisclosure:
      type: string
      enum:
      - UNSET
      - CONTAINS_AIGC
      - IS_AIGC
      - NOT_AIGC
      description: Whether the ad creative is AI-generated content. See docs/tiktok_api/ad.md
        § aigc_disclosure_type.
    TiktokBrandSafetyPartner:
      type: string
      enum:
      - UNSET
      - IAS
      - DOUBLE_VERIFY
      - OPEN_SLATE
      - ZEFR
      description: Post-bid brand-safety vendor. See docs/tiktok_api/ad.md § brand_safety_postbid_partner.
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
    TiktokCallToActionMode:
      type: string
      enum:
      - STANDARD
      - DYNAMIC
      description: How the call-to-action text is chosen. STANDARD uses a single fixed
        CTA; DYNAMIC lets TikTok rotate through a set of CTAs to maximize performance.
    TiktokDeeplinkFormat:
      type: string
      enum:
      - UNSET
      - DEEPLINK
      - DEFERRED_DEEPLINK
      description: How the ad's deeplink is resolved. See docs/tiktok_api/ad.md §
        deeplink_format_type.
    TiktokDisclaimer:
      type: string
      enum:
      - NONE
      - DISCLAIMER_TEXT
      - DISCLAIMER_WITH_URL
      description: Ad disclaimer mode. See docs/tiktok_api/ad.md § disclaimer_type.
    TiktokFallback:
      type: string
      enum:
      - UNSET
      - APP_STORE
      - LANDING_PAGE
      description: Destination fallback when a deferred deeplink cannot open the app.
        See docs/tiktok_api/ad.md § fallback_type.
    TiktokShoppingAdsFallback:
      type: string
      enum:
      - UNSET
      - LANDING_PAGE
      - STORE
      description: Fallback destination for shopping ads when the primary target is
        unavailable. See docs/tiktok_api/ad.md § shopping_ads_fallback_type.
    TiktokViewabilityPartner:
      type: string
      enum:
      - UNSET
      - IAS
      - DOUBLE_VERIFY
      - MOAT
      description: Post-bid viewability measurement partner. See docs/tiktok_api/ad.md
        § viewability_postbid_partner.
```
