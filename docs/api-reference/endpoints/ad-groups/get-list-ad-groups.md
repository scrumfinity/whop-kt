# GET /ad_groups — List ad groups

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/ad_groups`
- **Operation ID:** `listAdGroup`
- **Tags:** `Ad groups`
- **Required bearer scopes:** `ad_campaign:basic:read`

## Description

Returns a paginated list of ad groups scoped by campaign or company, with optional filtering by status and creation date.

Required permissions:
 - `ad_campaign:basic:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `after` | query | no | `string \| null` | Returns the elements in the list that come after the specified cursor. |  |
| `before` | query | no | `string \| null` | Returns the elements in the list that come before the specified cursor. |  |
| `first` | query | no | `integer \| null` | Returns the first _n_ elements from the list. | 42 |
| `last` | query | no | `integer \| null` | Returns the last _n_ elements from the list. | 42 |
| `campaign_id` | query | no | `string \| null` | Filter by campaign. Provide exactly one of campaign_id or company_id. |  |
| `company_id` | query | no | `string \| null` | Filter by company. Provide exactly one of campaign_id or company_id. | biz_xxxxxxxxxxxxxx |
| `created_after` | query | no | `string \| null / date-time` | Only return ad groups created after this timestamp. | 2023-12-01T05:00:00.401Z |
| `created_before` | query | no | `string \| null / date-time` | Only return ad groups created before this timestamp. | 2023-12-01T05:00:00.401Z |
| `query` | query | no | `string \| null` | Case-insensitive substring match against the ad group name. |  |
| `status` | query | no | `ExternalAdGroupStatuses \| null` | Filter ad groups by their current status. |  |

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
      "$ref": "#/components/schemas/AdGroupListItem"
    description: A list of nodes.
  page_info:
    "$ref": "#/components/schemas/PageInfo"
    description: Information to aid in pagination.
required:
- data
- page_info
description: The connection type for ExternalAdGroup.
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
for await (const adGroupListResponse of client.adGroups.list()) {
  console.log(adGroupListResponse.id);
}
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
page = client.ad_groups.list()
page = page.data[0]
print(page.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

page = whop.ad_groups.list

puts(page)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  AdCampaignPlatforms:
    type: string
    enum:
    - meta
    - tiktok
    description: The platforms where an ad campaign can run.
  AdGroupConfigBidStrategy:
    type: string
    enum:
    - lowest_cost
    - bid_cap
    - cost_cap
  AdGroupConfigBillingEvent:
    type: string
    enum:
    - impressions
    - clicks
    - optimized_cpm
    - video_views
  AdGroupConfigOptimizationGoal:
    type: string
    enum:
    - conversions
    - link_clicks
    - landing_page_views
    - reach
    - impressions
    - app_installs
    - video_views
    - lead_generation
    - value
    - page_likes
    - conversations
    - ad_recall_lift
    - two_second_continuous_video_views
    - post_engagement
    - event_responses
    - reminders_set
    - quality_lead
  AdGroupConfigPacing:
    type: string
    enum:
    - standard
    - accelerated
  AdGroupListItem:
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
      daily_budget:
        type:
        - number
        - 'null'
        description: Daily budget in dollars (nil for lifetime budgets)
        example: 6.9
      created_at:
        type: string
        format: date-time
        description: The datetime the external ad group was created.
        example: '2023-12-01T05:00:00.401Z'
      updated_at:
        type: string
        format: date-time
        description: The datetime the external ad group was last updated.
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
              const: MetaAdGroupPlatformConfigType
              description: The typename of this object
            platform:
              "$ref": "#/components/schemas/AdCampaignPlatforms"
              description: The ad platform (meta, tiktok).
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
            status:
              oneOf:
              - "$ref": "#/components/schemas/MetaAdGroupPlatformConfigStatus"
              - type: 'null'
            bid_strategy:
              oneOf:
              - "$ref": "#/components/schemas/MetaAdGroupPlatformConfigBidStrategy"
              - type: 'null'
            bid_amount:
              type:
              - integer
              - 'null'
              description: Represents non-fractional signed whole numeric values.
                Int can represent values between -(2^31) and 2^31 - 1.
              example: 42
            daily_budget:
              type:
              - integer
              - 'null'
              description: Represents non-fractional signed whole numeric values.
                Int can represent values between -(2^31) and 2^31 - 1.
              example: 42
            lifetime_budget:
              type:
              - integer
              - 'null'
              description: Represents non-fractional signed whole numeric values.
                Int can represent values between -(2^31) and 2^31 - 1.
              example: 42
            end_time:
              type:
              - string
              - 'null'
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            optimization_goal:
              oneOf:
              - "$ref": "#/components/schemas/MetaAdGroupPlatformConfigOptimizationGoal"
              - type: 'null'
            billing_event:
              oneOf:
              - "$ref": "#/components/schemas/MetaAdGroupPlatformConfigBillingEvent"
              - type: 'null'
            destination_type:
              oneOf:
              - "$ref": "#/components/schemas/MetaAdGroupPlatformConfigDestinationType"
              - type: 'null'
            promoted_object:
              type:
              - object
              - 'null'
              additionalProperties: true
              description: Represents untyped JSON
            attribution_spec:
              type:
              - array
              - 'null'
              items:
                type: object
                additionalProperties: true
                description: Represents untyped JSON
            publisher_platforms:
              type:
              - array
              - 'null'
              items:
                type: string
                description: Represents textual data as UTF-8 character sequences.
                  This type is most often used by GraphQL to represent free-form human-readable
                  text.
            facebook_positions:
              type:
              - array
              - 'null'
              items:
                type: string
                description: Represents textual data as UTF-8 character sequences.
                  This type is most often used by GraphQL to represent free-form human-readable
                  text.
            instagram_positions:
              type:
              - array
              - 'null'
              items:
                type: string
                description: Represents textual data as UTF-8 character sequences.
                  This type is most often used by GraphQL to represent free-form human-readable
                  text.
            geo_locations:
              type:
              - object
              - 'null'
              additionalProperties: true
              description: Represents untyped JSON
            excluded_geo_locations:
              type:
              - object
              - 'null'
              additionalProperties: true
              description: Represents untyped JSON
            targeting_automation:
              type:
              - object
              - 'null'
              additionalProperties: true
              description: Represents untyped JSON
          required:
          - typename
          - platform
          - page_id
          - instagram_actor_id
          - status
          - bid_strategy
          - bid_amount
          - daily_budget
          - lifetime_budget
          - end_time
          - optimization_goal
          - billing_event
          - destination_type
          - promoted_object
          - attribution_spec
          - publisher_platforms
          - facebook_positions
          - instagram_positions
          - geo_locations
          - excluded_geo_locations
          - targeting_automation
          description: Meta (Facebook/Instagram) ad set configuration.
          title: MetaAdGroupPlatformConfigType
        - type:
          - object
          - 'null'
          properties:
            typename:
              type: string
              const: TiktokAdGroupPlatformConfigType
              description: The typename of this object
            platform:
              "$ref": "#/components/schemas/AdCampaignPlatforms"
              description: The ad platform (meta, tiktok).
            budget_mode:
              oneOf:
              - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigBudgetMode"
              - type: 'null'
            schedule_type:
              oneOf:
              - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigScheduleType"
              - type: 'null'
            schedule_start_time:
              type:
              - string
              - 'null'
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            schedule_end_time:
              type:
              - string
              - 'null'
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            optimization_goal:
              oneOf:
              - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigOptimizationGoal"
              - type: 'null'
            billing_event:
              oneOf:
              - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigBillingEvent"
              - type: 'null'
            bid_type:
              oneOf:
              - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigBidType"
              - type: 'null'
            bid_price:
              type:
              - number
              - 'null'
              description: Represents signed double-precision fractional values as
                specified by [IEEE 754](https://en.wikipedia.org/wiki/IEEE_floating_point).
              example: 6.9
            conversion_bid_price:
              type:
              - number
              - 'null'
              description: Represents signed double-precision fractional values as
                specified by [IEEE 754](https://en.wikipedia.org/wiki/IEEE_floating_point).
              example: 6.9
            pacing:
              oneOf:
              - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigPacing"
              - type: 'null'
            placement_type:
              oneOf:
              - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigPlacementType"
              - type: 'null'
            placements:
              type:
              - array
              - 'null'
              items:
                type: string
                description: Represents textual data as UTF-8 character sequences.
                  This type is most often used by GraphQL to represent free-form human-readable
                  text.
            pixel_id:
              type:
              - string
              - 'null'
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            optimization_event:
              type:
              - string
              - 'null'
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            identity_id:
              type:
              - string
              - 'null'
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            identity_type:
              type:
              - string
              - 'null'
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            operation_status:
              oneOf:
              - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigOperationStatus"
              - type: 'null'
          required:
          - typename
          - platform
          - budget_mode
          - schedule_type
          - schedule_start_time
          - schedule_end_time
          - optimization_goal
          - billing_event
          - bid_type
          - bid_price
          - conversion_bid_price
          - pacing
          - placement_type
          - placements
          - pixel_id
          - optimization_event
          - identity_id
          - identity_type
          - operation_status
          description: TikTok ad group configuration.
          title: TiktokAdGroupPlatformConfigType
        discriminator:
          propertyName: typename
        description: Typed platform-specific configuration. Use inline fragments (...
          on MetaAdGroupPlatformConfigType).
      config:
        type:
        - object
        - 'null'
        properties:
          bid_strategy:
            oneOf:
            - "$ref": "#/components/schemas/AdGroupConfigBidStrategy"
            - type: 'null'
            description: 'Bid strategy: lowest_cost, bid_cap, or cost_cap.'
          bid_amount:
            type:
            - integer
            - 'null'
            description: Bid cap amount in cents. Used when bid_strategy is bid_cap
              or cost_cap.
            example: 42
          optimization_goal:
            oneOf:
            - "$ref": "#/components/schemas/AdGroupConfigOptimizationGoal"
            - type: 'null'
            description: What the ad group optimizes for (e.g., conversions, link_clicks,
              reach).
          billing_event:
            oneOf:
            - "$ref": "#/components/schemas/AdGroupConfigBillingEvent"
            - type: 'null'
            description: How you are billed (e.g., impressions, clicks).
          start_time:
            type:
            - string
            - 'null'
            description: Scheduled start time (ISO8601).
          end_time:
            type:
            - string
            - 'null'
            description: Scheduled end time (ISO8601). Required for lifetime budgets.
          pacing:
            oneOf:
            - "$ref": "#/components/schemas/AdGroupConfigPacing"
            - type: 'null'
            description: 'Budget pacing: standard (even) or accelerated (fast).'
          frequency_cap:
            type:
            - integer
            - 'null'
            description: Maximum number of times to show ads to each person in the
              frequency interval.
            example: 42
          frequency_cap_interval_days:
            type:
            - integer
            - 'null'
            description: Number of days for the frequency cap interval.
            example: 42
          targeting:
            type:
            - object
            - 'null'
            properties:
              countries:
                type:
                - array
                - 'null'
                items:
                  type: string
                  description: Represents textual data as UTF-8 character sequences.
                    This type is most often used by GraphQL to represent free-form
                    human-readable text.
                description: ISO 3166-1 alpha-2 country codes targeted.
              age_min:
                type:
                - integer
                - 'null'
                description: Minimum age for demographic targeting.
                example: 42
              age_max:
                type:
                - integer
                - 'null'
                description: Maximum age for demographic targeting.
                example: 42
              genders:
                type:
                - array
                - 'null'
                items:
                  "$ref": "#/components/schemas/TargetingGenders"
                description: Genders targeted.
              languages:
                type:
                - array
                - 'null'
                items:
                  type: string
                  description: Represents textual data as UTF-8 character sequences.
                    This type is most often used by GraphQL to represent free-form
                    human-readable text.
                description: Language codes targeted.
              placement_type:
                oneOf:
                - "$ref": "#/components/schemas/TargetingPlacementTypes"
                - type: 'null'
                description: Placement strategy.
              device_platforms:
                type:
                - array
                - 'null'
                items:
                  "$ref": "#/components/schemas/TargetingDevicePlatforms"
                description: Device platforms targeted.
              interest_ids:
                type:
                - array
                - 'null'
                items:
                  type: string
                  description: Represents textual data as UTF-8 character sequences.
                    This type is most often used by GraphQL to represent free-form
                    human-readable text.
                description: Platform-specific interest IDs targeted.
              include_audience_ids:
                type:
                - array
                - 'null'
                items:
                  type: string
                  description: Represents textual data as UTF-8 character sequences.
                    This type is most often used by GraphQL to represent free-form
                    human-readable text.
                description: Platform audience IDs included.
              exclude_audience_ids:
                type:
                - array
                - 'null'
                items:
                  type: string
                  description: Represents textual data as UTF-8 character sequences.
                    This type is most often used by GraphQL to represent free-form
                    human-readable text.
                description: Platform audience IDs excluded.
            required:
            - countries
            - age_min
            - age_max
            - genders
            - languages
            - placement_type
            - device_platforms
            - interest_ids
            - include_audience_ids
            - exclude_audience_ids
            description: Audience targeting settings (demographics, geo, interests,
              audiences, devices).
        required:
        - bid_strategy
        - bid_amount
        - optimization_goal
        - billing_event
        - start_time
        - end_time
        - pacing
        - frequency_cap
        - frequency_cap_interval_days
        - targeting
        description: Unified ad group configuration (platform-agnostic)
    required:
    - id
    - name
    - status
    - daily_budget
    - created_at
    - updated_at
    - platform_config
    - config
    description: An external ad group (ad set) belonging to an ad campaign
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
  MetaAdGroupPlatformConfigBidStrategy:
    type: string
    enum:
    - LOWEST_COST_WITHOUT_CAP
    - LOWEST_COST_WITH_BID_CAP
    - COST_CAP
    - LOWEST_COST_WITH_MIN_ROAS
  MetaAdGroupPlatformConfigBillingEvent:
    type: string
    enum:
    - APP_INSTALLS
    - CLICKS
    - IMPRESSIONS
    - LINK_CLICKS
    - NONE
    - OFFER_CLAIMS
    - PAGE_LIKES
    - POST_ENGAGEMENT
    - THRUPLAY
    - PURCHASE
    - LISTING_INTERACTION
  MetaAdGroupPlatformConfigDestinationType:
    type: string
    enum:
    - UNDEFINED
    - WEBSITE
    - APP
    - FACEBOOK
    - MESSENGER
    - WHATSAPP
    - INSTAGRAM_DIRECT
    - INSTAGRAM_PROFILE
    - PHONE_CALL
    - SHOP_AUTOMATIC
    - APPLINKS_AUTOMATIC
    - ON_AD
    - ON_POST
    - ON_VIDEO
    - ON_PAGE
    - ON_EVENT
    - MESSAGING_MESSENGER_WHATSAPP
    - MESSAGING_INSTAGRAM_DIRECT_MESSENGER
    - MESSAGING_INSTAGRAM_DIRECT_WHATSAPP
    - MESSAGING_INSTAGRAM_DIRECT_MESSENGER_WHATSAPP
    - INSTAGRAM_PROFILE_AND_FACEBOOK_PAGE
    - FACEBOOK_PAGE
    - INSTAGRAM_LIVE
    - FACEBOOK_LIVE
    - IMAGINE
    - LEAD_FROM_IG_DIRECT
    - LEAD_FROM_MESSENGER
    - WEBSITE_AND_LEAD_FORM
    - WEBSITE_AND_PHONE_CALL
    - BROADCAST_CHANNEL
  MetaAdGroupPlatformConfigOptimizationGoal:
    type: string
    enum:
    - NONE
    - APP_INSTALLS
    - AD_RECALL_LIFT
    - ENGAGED_USERS
    - EVENT_RESPONSES
    - IMPRESSIONS
    - LEAD_GENERATION
    - QUALITY_LEAD
    - LINK_CLICKS
    - OFFSITE_CONVERSIONS
    - PAGE_LIKES
    - POST_ENGAGEMENT
    - QUALITY_CALL
    - REACH
    - LANDING_PAGE_VIEWS
    - VISIT_INSTAGRAM_PROFILE
    - VALUE
    - THRUPLAY
    - DERIVED_EVENTS
    - APP_INSTALLS_AND_OFFSITE_CONVERSIONS
    - CONVERSATIONS
    - IN_APP_VALUE
    - MESSAGING_PURCHASE_CONVERSION
    - SUBSCRIBERS
    - REMINDERS_SET
    - MEANINGFUL_CALL_ATTEMPT
    - PROFILE_VISIT
    - PROFILE_AND_PAGE_ENGAGEMENT
    - TWO_SECOND_CONTINUOUS_VIDEO_VIEWS
    - ENGAGED_REACH
    - ENGAGED_PAGE_VIEWS
    - MESSAGING_DEEP_CONVERSATION_AND_FOLLOW
    - ADVERTISER_SILOED_VALUE
    - AUTOMATIC_OBJECTIVE
    - MESSAGING_APPOINTMENT_CONVERSION
  MetaAdGroupPlatformConfigStatus:
    type: string
    enum:
    - ACTIVE
    - PAUSED
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
  TargetingDevicePlatforms:
    type: string
    enum:
    - mobile
    - desktop
    description: Device platform targeting options.
  TargetingGenders:
    type: string
    enum:
    - male
    - female
    - all
    description: Gender targeting options.
  TargetingPlacementTypes:
    type: string
    enum:
    - automatic
    - manual
    description: Placement strategy for ad delivery.
  TiktokAdGroupPlatformConfigBidType:
    type: string
    enum:
    - BID_TYPE_NO_BID
    - BID_TYPE_CUSTOM
  TiktokAdGroupPlatformConfigBillingEvent:
    type: string
    enum:
    - CPC
    - CPM
    - OCPM
    - CPV
  TiktokAdGroupPlatformConfigBudgetMode:
    type: string
    enum:
    - BUDGET_MODE_DAY
    - BUDGET_MODE_TOTAL
    - BUDGET_MODE_DYNAMIC_DAILY_BUDGET
  TiktokAdGroupPlatformConfigOperationStatus:
    type: string
    enum:
    - ENABLE
    - DISABLE
  TiktokAdGroupPlatformConfigOptimizationGoal:
    type: string
    enum:
    - CLICK
    - CONVERT
    - INSTALL
    - IN_APP_EVENT
    - REACH
    - SHOW
    - VIDEO_VIEW
    - ENGAGED_VIEW
    - ENGAGED_VIEW_FIFTEEN
    - LEAD_GENERATION
    - PREFERRED_LEAD
    - CONVERSATION
    - FOLLOWERS
    - PROFILE_VIEWS
    - PAGE_VISIT
    - VALUE
    - AUTOMATIC_VALUE_OPTIMIZATION
    - TRAFFIC_LANDING_PAGE_VIEW
    - DESTINATION_VISIT
    - MT_LIVE_ROOM
    - PRODUCT_CLICK_IN_LIVE
  TiktokAdGroupPlatformConfigPacing:
    type: string
    enum:
    - PACING_MODE_SMOOTH
    - PACING_MODE_FAST
  TiktokAdGroupPlatformConfigPlacementType:
    type: string
    enum:
    - PLACEMENT_TYPE_AUTOMATIC
    - PLACEMENT_TYPE_NORMAL
  TiktokAdGroupPlatformConfigScheduleType:
    type: string
    enum:
    - SCHEDULE_START_END
    - SCHEDULE_FROM_NOW
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
  "/ad_groups":
    get:
      tags:
      - Ad groups
      operationId: listAdGroup
      summary: List ad groups
      description: |-
        Returns a paginated list of ad groups scoped by campaign or company, with optional filtering by status and creation date.

        Required permissions:
         - `ad_campaign:basic:read`
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
      - name: campaign_id
        in: query
        required: false
        schema:
          type:
          - string
          - 'null'
          description: Filter by campaign. Provide exactly one of campaign_id or company_id.
        explode: true
        style: form
      - name: company_id
        in: query
        required: false
        schema:
          type:
          - string
          - 'null'
          description: Filter by company. Provide exactly one of campaign_id or company_id.
          example: biz_xxxxxxxxxxxxxx
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
          description: Only return ad groups created after this timestamp.
          example: '2023-12-01T05:00:00.401Z'
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
          description: Only return ad groups created before this timestamp.
          example: '2023-12-01T05:00:00.401Z'
        explode: true
        style: form
      - name: query
        in: query
        required: false
        schema:
          type:
          - string
          - 'null'
          description: Case-insensitive substring match against the ad group name.
        explode: true
        style: form
      - name: status
        in: query
        required: false
        schema:
          oneOf:
          - "$ref": "#/components/schemas/ExternalAdGroupStatuses"
          - type: 'null'
          description: Filter ad groups by their current status.
        explode: true
        style: form
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
                type: object
                properties:
                  data:
                    type: array
                    items:
                      "$ref": "#/components/schemas/AdGroupListItem"
                    description: A list of nodes.
                  page_info:
                    "$ref": "#/components/schemas/PageInfo"
                    description: Information to aid in pagination.
                required:
                - data
                - page_info
                description: The connection type for ExternalAdGroup.
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
          for await (const adGroupListResponse of client.adGroups.list()) {
            console.log(adGroupListResponse.id);
          }
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          page = client.ad_groups.list()
          page = page.data[0]
          print(page.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          page = whop.ad_groups.list

          puts(page)
components:
  schemas:
    AdCampaignPlatforms:
      type: string
      enum:
      - meta
      - tiktok
      description: The platforms where an ad campaign can run.
    AdGroupConfigBidStrategy:
      type: string
      enum:
      - lowest_cost
      - bid_cap
      - cost_cap
    AdGroupConfigBillingEvent:
      type: string
      enum:
      - impressions
      - clicks
      - optimized_cpm
      - video_views
    AdGroupConfigOptimizationGoal:
      type: string
      enum:
      - conversions
      - link_clicks
      - landing_page_views
      - reach
      - impressions
      - app_installs
      - video_views
      - lead_generation
      - value
      - page_likes
      - conversations
      - ad_recall_lift
      - two_second_continuous_video_views
      - post_engagement
      - event_responses
      - reminders_set
      - quality_lead
    AdGroupConfigPacing:
      type: string
      enum:
      - standard
      - accelerated
    AdGroupListItem:
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
        daily_budget:
          type:
          - number
          - 'null'
          description: Daily budget in dollars (nil for lifetime budgets)
          example: 6.9
        created_at:
          type: string
          format: date-time
          description: The datetime the external ad group was created.
          example: '2023-12-01T05:00:00.401Z'
        updated_at:
          type: string
          format: date-time
          description: The datetime the external ad group was last updated.
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
                const: MetaAdGroupPlatformConfigType
                description: The typename of this object
              platform:
                "$ref": "#/components/schemas/AdCampaignPlatforms"
                description: The ad platform (meta, tiktok).
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
              status:
                oneOf:
                - "$ref": "#/components/schemas/MetaAdGroupPlatformConfigStatus"
                - type: 'null'
              bid_strategy:
                oneOf:
                - "$ref": "#/components/schemas/MetaAdGroupPlatformConfigBidStrategy"
                - type: 'null'
              bid_amount:
                type:
                - integer
                - 'null'
                description: Represents non-fractional signed whole numeric values.
                  Int can represent values between -(2^31) and 2^31 - 1.
                example: 42
              daily_budget:
                type:
                - integer
                - 'null'
                description: Represents non-fractional signed whole numeric values.
                  Int can represent values between -(2^31) and 2^31 - 1.
                example: 42
              lifetime_budget:
                type:
                - integer
                - 'null'
                description: Represents non-fractional signed whole numeric values.
                  Int can represent values between -(2^31) and 2^31 - 1.
                example: 42
              end_time:
                type:
                - string
                - 'null'
                description: Represents textual data as UTF-8 character sequences.
                  This type is most often used by GraphQL to represent free-form human-readable
                  text.
              optimization_goal:
                oneOf:
                - "$ref": "#/components/schemas/MetaAdGroupPlatformConfigOptimizationGoal"
                - type: 'null'
              billing_event:
                oneOf:
                - "$ref": "#/components/schemas/MetaAdGroupPlatformConfigBillingEvent"
                - type: 'null'
              destination_type:
                oneOf:
                - "$ref": "#/components/schemas/MetaAdGroupPlatformConfigDestinationType"
                - type: 'null'
              promoted_object:
                type:
                - object
                - 'null'
                additionalProperties: true
                description: Represents untyped JSON
              attribution_spec:
                type:
                - array
                - 'null'
                items:
                  type: object
                  additionalProperties: true
                  description: Represents untyped JSON
              publisher_platforms:
                type:
                - array
                - 'null'
                items:
                  type: string
                  description: Represents textual data as UTF-8 character sequences.
                    This type is most often used by GraphQL to represent free-form
                    human-readable text.
              facebook_positions:
                type:
                - array
                - 'null'
                items:
                  type: string
                  description: Represents textual data as UTF-8 character sequences.
                    This type is most often used by GraphQL to represent free-form
                    human-readable text.
              instagram_positions:
                type:
                - array
                - 'null'
                items:
                  type: string
                  description: Represents textual data as UTF-8 character sequences.
                    This type is most often used by GraphQL to represent free-form
                    human-readable text.
              geo_locations:
                type:
                - object
                - 'null'
                additionalProperties: true
                description: Represents untyped JSON
              excluded_geo_locations:
                type:
                - object
                - 'null'
                additionalProperties: true
                description: Represents untyped JSON
              targeting_automation:
                type:
                - object
                - 'null'
                additionalProperties: true
                description: Represents untyped JSON
            required:
            - typename
            - platform
            - page_id
            - instagram_actor_id
            - status
            - bid_strategy
            - bid_amount
            - daily_budget
            - lifetime_budget
            - end_time
            - optimization_goal
            - billing_event
            - destination_type
            - promoted_object
            - attribution_spec
            - publisher_platforms
            - facebook_positions
            - instagram_positions
            - geo_locations
            - excluded_geo_locations
            - targeting_automation
            description: Meta (Facebook/Instagram) ad set configuration.
            title: MetaAdGroupPlatformConfigType
          - type:
            - object
            - 'null'
            properties:
              typename:
                type: string
                const: TiktokAdGroupPlatformConfigType
                description: The typename of this object
              platform:
                "$ref": "#/components/schemas/AdCampaignPlatforms"
                description: The ad platform (meta, tiktok).
              budget_mode:
                oneOf:
                - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigBudgetMode"
                - type: 'null'
              schedule_type:
                oneOf:
                - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigScheduleType"
                - type: 'null'
              schedule_start_time:
                type:
                - string
                - 'null'
                description: Represents textual data as UTF-8 character sequences.
                  This type is most often used by GraphQL to represent free-form human-readable
                  text.
              schedule_end_time:
                type:
                - string
                - 'null'
                description: Represents textual data as UTF-8 character sequences.
                  This type is most often used by GraphQL to represent free-form human-readable
                  text.
              optimization_goal:
                oneOf:
                - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigOptimizationGoal"
                - type: 'null'
              billing_event:
                oneOf:
                - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigBillingEvent"
                - type: 'null'
              bid_type:
                oneOf:
                - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigBidType"
                - type: 'null'
              bid_price:
                type:
                - number
                - 'null'
                description: Represents signed double-precision fractional values
                  as specified by [IEEE 754](https://en.wikipedia.org/wiki/IEEE_floating_point).
                example: 6.9
              conversion_bid_price:
                type:
                - number
                - 'null'
                description: Represents signed double-precision fractional values
                  as specified by [IEEE 754](https://en.wikipedia.org/wiki/IEEE_floating_point).
                example: 6.9
              pacing:
                oneOf:
                - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigPacing"
                - type: 'null'
              placement_type:
                oneOf:
                - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigPlacementType"
                - type: 'null'
              placements:
                type:
                - array
                - 'null'
                items:
                  type: string
                  description: Represents textual data as UTF-8 character sequences.
                    This type is most often used by GraphQL to represent free-form
                    human-readable text.
              pixel_id:
                type:
                - string
                - 'null'
                description: Represents textual data as UTF-8 character sequences.
                  This type is most often used by GraphQL to represent free-form human-readable
                  text.
              optimization_event:
                type:
                - string
                - 'null'
                description: Represents textual data as UTF-8 character sequences.
                  This type is most often used by GraphQL to represent free-form human-readable
                  text.
              identity_id:
                type:
                - string
                - 'null'
                description: Represents textual data as UTF-8 character sequences.
                  This type is most often used by GraphQL to represent free-form human-readable
                  text.
              identity_type:
                type:
                - string
                - 'null'
                description: Represents textual data as UTF-8 character sequences.
                  This type is most often used by GraphQL to represent free-form human-readable
                  text.
              operation_status:
                oneOf:
                - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigOperationStatus"
                - type: 'null'
            required:
            - typename
            - platform
            - budget_mode
            - schedule_type
            - schedule_start_time
            - schedule_end_time
            - optimization_goal
            - billing_event
            - bid_type
            - bid_price
            - conversion_bid_price
            - pacing
            - placement_type
            - placements
            - pixel_id
            - optimization_event
            - identity_id
            - identity_type
            - operation_status
            description: TikTok ad group configuration.
            title: TiktokAdGroupPlatformConfigType
          discriminator:
            propertyName: typename
          description: Typed platform-specific configuration. Use inline fragments
            (... on MetaAdGroupPlatformConfigType).
        config:
          type:
          - object
          - 'null'
          properties:
            bid_strategy:
              oneOf:
              - "$ref": "#/components/schemas/AdGroupConfigBidStrategy"
              - type: 'null'
              description: 'Bid strategy: lowest_cost, bid_cap, or cost_cap.'
            bid_amount:
              type:
              - integer
              - 'null'
              description: Bid cap amount in cents. Used when bid_strategy is bid_cap
                or cost_cap.
              example: 42
            optimization_goal:
              oneOf:
              - "$ref": "#/components/schemas/AdGroupConfigOptimizationGoal"
              - type: 'null'
              description: What the ad group optimizes for (e.g., conversions, link_clicks,
                reach).
            billing_event:
              oneOf:
              - "$ref": "#/components/schemas/AdGroupConfigBillingEvent"
              - type: 'null'
              description: How you are billed (e.g., impressions, clicks).
            start_time:
              type:
              - string
              - 'null'
              description: Scheduled start time (ISO8601).
            end_time:
              type:
              - string
              - 'null'
              description: Scheduled end time (ISO8601). Required for lifetime budgets.
            pacing:
              oneOf:
              - "$ref": "#/components/schemas/AdGroupConfigPacing"
              - type: 'null'
              description: 'Budget pacing: standard (even) or accelerated (fast).'
            frequency_cap:
              type:
              - integer
              - 'null'
              description: Maximum number of times to show ads to each person in the
                frequency interval.
              example: 42
            frequency_cap_interval_days:
              type:
              - integer
              - 'null'
              description: Number of days for the frequency cap interval.
              example: 42
            targeting:
              type:
              - object
              - 'null'
              properties:
                countries:
                  type:
                  - array
                  - 'null'
                  items:
                    type: string
                    description: Represents textual data as UTF-8 character sequences.
                      This type is most often used by GraphQL to represent free-form
                      human-readable text.
                  description: ISO 3166-1 alpha-2 country codes targeted.
                age_min:
                  type:
                  - integer
                  - 'null'
                  description: Minimum age for demographic targeting.
                  example: 42
                age_max:
                  type:
                  - integer
                  - 'null'
                  description: Maximum age for demographic targeting.
                  example: 42
                genders:
                  type:
                  - array
                  - 'null'
                  items:
                    "$ref": "#/components/schemas/TargetingGenders"
                  description: Genders targeted.
                languages:
                  type:
                  - array
                  - 'null'
                  items:
                    type: string
                    description: Represents textual data as UTF-8 character sequences.
                      This type is most often used by GraphQL to represent free-form
                      human-readable text.
                  description: Language codes targeted.
                placement_type:
                  oneOf:
                  - "$ref": "#/components/schemas/TargetingPlacementTypes"
                  - type: 'null'
                  description: Placement strategy.
                device_platforms:
                  type:
                  - array
                  - 'null'
                  items:
                    "$ref": "#/components/schemas/TargetingDevicePlatforms"
                  description: Device platforms targeted.
                interest_ids:
                  type:
                  - array
                  - 'null'
                  items:
                    type: string
                    description: Represents textual data as UTF-8 character sequences.
                      This type is most often used by GraphQL to represent free-form
                      human-readable text.
                  description: Platform-specific interest IDs targeted.
                include_audience_ids:
                  type:
                  - array
                  - 'null'
                  items:
                    type: string
                    description: Represents textual data as UTF-8 character sequences.
                      This type is most often used by GraphQL to represent free-form
                      human-readable text.
                  description: Platform audience IDs included.
                exclude_audience_ids:
                  type:
                  - array
                  - 'null'
                  items:
                    type: string
                    description: Represents textual data as UTF-8 character sequences.
                      This type is most often used by GraphQL to represent free-form
                      human-readable text.
                  description: Platform audience IDs excluded.
              required:
              - countries
              - age_min
              - age_max
              - genders
              - languages
              - placement_type
              - device_platforms
              - interest_ids
              - include_audience_ids
              - exclude_audience_ids
              description: Audience targeting settings (demographics, geo, interests,
                audiences, devices).
          required:
          - bid_strategy
          - bid_amount
          - optimization_goal
          - billing_event
          - start_time
          - end_time
          - pacing
          - frequency_cap
          - frequency_cap_interval_days
          - targeting
          description: Unified ad group configuration (platform-agnostic)
      required:
      - id
      - name
      - status
      - daily_budget
      - created_at
      - updated_at
      - platform_config
      - config
      description: An external ad group (ad set) belonging to an ad campaign
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
    MetaAdGroupPlatformConfigBidStrategy:
      type: string
      enum:
      - LOWEST_COST_WITHOUT_CAP
      - LOWEST_COST_WITH_BID_CAP
      - COST_CAP
      - LOWEST_COST_WITH_MIN_ROAS
    MetaAdGroupPlatformConfigBillingEvent:
      type: string
      enum:
      - APP_INSTALLS
      - CLICKS
      - IMPRESSIONS
      - LINK_CLICKS
      - NONE
      - OFFER_CLAIMS
      - PAGE_LIKES
      - POST_ENGAGEMENT
      - THRUPLAY
      - PURCHASE
      - LISTING_INTERACTION
    MetaAdGroupPlatformConfigDestinationType:
      type: string
      enum:
      - UNDEFINED
      - WEBSITE
      - APP
      - FACEBOOK
      - MESSENGER
      - WHATSAPP
      - INSTAGRAM_DIRECT
      - INSTAGRAM_PROFILE
      - PHONE_CALL
      - SHOP_AUTOMATIC
      - APPLINKS_AUTOMATIC
      - ON_AD
      - ON_POST
      - ON_VIDEO
      - ON_PAGE
      - ON_EVENT
      - MESSAGING_MESSENGER_WHATSAPP
      - MESSAGING_INSTAGRAM_DIRECT_MESSENGER
      - MESSAGING_INSTAGRAM_DIRECT_WHATSAPP
      - MESSAGING_INSTAGRAM_DIRECT_MESSENGER_WHATSAPP
      - INSTAGRAM_PROFILE_AND_FACEBOOK_PAGE
      - FACEBOOK_PAGE
      - INSTAGRAM_LIVE
      - FACEBOOK_LIVE
      - IMAGINE
      - LEAD_FROM_IG_DIRECT
      - LEAD_FROM_MESSENGER
      - WEBSITE_AND_LEAD_FORM
      - WEBSITE_AND_PHONE_CALL
      - BROADCAST_CHANNEL
    MetaAdGroupPlatformConfigOptimizationGoal:
      type: string
      enum:
      - NONE
      - APP_INSTALLS
      - AD_RECALL_LIFT
      - ENGAGED_USERS
      - EVENT_RESPONSES
      - IMPRESSIONS
      - LEAD_GENERATION
      - QUALITY_LEAD
      - LINK_CLICKS
      - OFFSITE_CONVERSIONS
      - PAGE_LIKES
      - POST_ENGAGEMENT
      - QUALITY_CALL
      - REACH
      - LANDING_PAGE_VIEWS
      - VISIT_INSTAGRAM_PROFILE
      - VALUE
      - THRUPLAY
      - DERIVED_EVENTS
      - APP_INSTALLS_AND_OFFSITE_CONVERSIONS
      - CONVERSATIONS
      - IN_APP_VALUE
      - MESSAGING_PURCHASE_CONVERSION
      - SUBSCRIBERS
      - REMINDERS_SET
      - MEANINGFUL_CALL_ATTEMPT
      - PROFILE_VISIT
      - PROFILE_AND_PAGE_ENGAGEMENT
      - TWO_SECOND_CONTINUOUS_VIDEO_VIEWS
      - ENGAGED_REACH
      - ENGAGED_PAGE_VIEWS
      - MESSAGING_DEEP_CONVERSATION_AND_FOLLOW
      - ADVERTISER_SILOED_VALUE
      - AUTOMATIC_OBJECTIVE
      - MESSAGING_APPOINTMENT_CONVERSION
    MetaAdGroupPlatformConfigStatus:
      type: string
      enum:
      - ACTIVE
      - PAUSED
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
    TargetingDevicePlatforms:
      type: string
      enum:
      - mobile
      - desktop
      description: Device platform targeting options.
    TargetingGenders:
      type: string
      enum:
      - male
      - female
      - all
      description: Gender targeting options.
    TargetingPlacementTypes:
      type: string
      enum:
      - automatic
      - manual
      description: Placement strategy for ad delivery.
    TiktokAdGroupPlatformConfigBidType:
      type: string
      enum:
      - BID_TYPE_NO_BID
      - BID_TYPE_CUSTOM
    TiktokAdGroupPlatformConfigBillingEvent:
      type: string
      enum:
      - CPC
      - CPM
      - OCPM
      - CPV
    TiktokAdGroupPlatformConfigBudgetMode:
      type: string
      enum:
      - BUDGET_MODE_DAY
      - BUDGET_MODE_TOTAL
      - BUDGET_MODE_DYNAMIC_DAILY_BUDGET
    TiktokAdGroupPlatformConfigOperationStatus:
      type: string
      enum:
      - ENABLE
      - DISABLE
    TiktokAdGroupPlatformConfigOptimizationGoal:
      type: string
      enum:
      - CLICK
      - CONVERT
      - INSTALL
      - IN_APP_EVENT
      - REACH
      - SHOW
      - VIDEO_VIEW
      - ENGAGED_VIEW
      - ENGAGED_VIEW_FIFTEEN
      - LEAD_GENERATION
      - PREFERRED_LEAD
      - CONVERSATION
      - FOLLOWERS
      - PROFILE_VIEWS
      - PAGE_VISIT
      - VALUE
      - AUTOMATIC_VALUE_OPTIMIZATION
      - TRAFFIC_LANDING_PAGE_VIEW
      - DESTINATION_VISIT
      - MT_LIVE_ROOM
      - PRODUCT_CLICK_IN_LIVE
    TiktokAdGroupPlatformConfigPacing:
      type: string
      enum:
      - PACING_MODE_SMOOTH
      - PACING_MODE_FAST
    TiktokAdGroupPlatformConfigPlacementType:
      type: string
      enum:
      - PLACEMENT_TYPE_AUTOMATIC
      - PLACEMENT_TYPE_NORMAL
    TiktokAdGroupPlatformConfigScheduleType:
      type: string
      enum:
      - SCHEDULE_START_END
      - SCHEDULE_FROM_NOW
```
