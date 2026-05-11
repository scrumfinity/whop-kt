# PATCH /ad_groups/{id} — Update ad group

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `PATCH`
- **Path:** `/ad_groups/{id}`
- **Operation ID:** `updateAdGroup`
- **Tags:** `Ad groups`
- **Required bearer scopes:** `ad_campaign:update`, `ad_campaign:basic:read`

## Description

Updates an existing ad group.

Required permissions:
 - `ad_campaign:update`
 - `ad_campaign:basic:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `id` | path | yes | `string` | The unique identifier of the ad group to update. | adgrp_xxxxxxxxxxxx |

## Request body

Required: **no**
Content types: `application/json`

### `application/json` request schema

```yaml
type: object
properties:
  budget:
    type:
    - number
    - 'null'
    description: Budget amount in dollars.
    example: 6.9
  budget_type:
    oneOf:
    - "$ref": "#/components/schemas/AdBudgetTypes"
    - type: 'null'
    description: Whether the budget is daily or lifetime.
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
        description: Bid cap amount in cents. Used when bid_strategy is bid_cap or
          cost_cap.
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
        description: Maximum number of times to show ads to each person in the frequency
          interval.
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
          age_max:
            type:
            - integer
            - 'null'
            description: Maximum age for demographic targeting.
            example: 42
          age_min:
            type:
            - integer
            - 'null'
            description: Minimum age for demographic targeting.
            example: 42
          countries:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            description: ISO 3166-1 alpha-2 country codes to target.
          device_platforms:
            type:
            - array
            - 'null'
            items:
              "$ref": "#/components/schemas/TargetingDevicePlatforms"
            description: Device platforms to target.
          exclude_audience_ids:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            description: Platform audience IDs to exclude.
          genders:
            type:
            - array
            - 'null'
            items:
              "$ref": "#/components/schemas/TargetingGenders"
            description: Genders to target.
          include_audience_ids:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            description: Platform audience IDs to include.
          interest_ids:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            description: Platform-specific interest IDs to target.
          languages:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            description: Language codes to target.
          placement_type:
            oneOf:
            - "$ref": "#/components/schemas/TargetingPlacementTypes"
            - type: 'null'
            description: Placement strategy.
        required: []
        description: Audience targeting settings (demographics, geo, interests, audiences,
          devices).
    required: []
    description: Unified ad group configuration (bidding, optimization, targeting).
  daily_budget:
    type:
    - number
    - 'null'
    description: Daily budget in dollars.
    example: 6.9
  name:
    type:
    - string
    - 'null'
    description: Human-readable ad group name.
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
          status:
            oneOf:
            - "$ref": "#/components/schemas/MetaAdGroupPlatformConfigStatus"
            - type: 'null'
          bid_strategy:
            oneOf:
            - "$ref": "#/components/schemas/MetaAdGroupPlatformConfigBidStrategy"
            - type: 'null'
            description: Meta bid strategy.
          bid_amount:
            type:
            - integer
            - 'null'
            description: Bid amount in cents.
            example: 42
          daily_budget:
            type:
            - integer
            - 'null'
            description: Daily budget in cents.
            example: 42
          daily_min_spend_target:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          daily_spend_cap:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          lifetime_budget:
            type:
            - integer
            - 'null'
            description: Lifetime budget in cents.
            example: 42
          lifetime_min_spend_target:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          lifetime_spend_cap:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          budget_remaining:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          start_time:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          end_time:
            type:
            - string
            - 'null'
            description: End time (ISO8601). Required for lifetime budgets.
          optimization_goal:
            oneOf:
            - "$ref": "#/components/schemas/MetaAdGroupPlatformConfigOptimizationGoal"
            - type: 'null'
            description: What this ad set optimizes for on Meta.
          billing_event:
            oneOf:
            - "$ref": "#/components/schemas/MetaAdGroupPlatformConfigBillingEvent"
            - type: 'null'
            description: How you are billed on Meta.
          attribution_spec:
            type:
            - array
            - 'null'
            items:
              type: object
              properties:
                event_type:
                  type: string
                  description: Attribution event type (e.g., CLICK_THROUGH, VIEW_THROUGH).
                window_days:
                  type: integer
                  description: Attribution window in days (1, 7, 28).
                  example: 42
              required:
              - event_type
              - window_days
              description: Meta conversion attribution window.
            description: Conversion attribution windows.
          attribution_setting:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          destination_type:
            oneOf:
            - "$ref": "#/components/schemas/MetaAdGroupPlatformConfigDestinationType"
            - type: 'null'
            description: Where ads in this ad set direct people.
          promoted_object:
            type:
            - object
            - 'null'
            properties:
              custom_conversion_id:
                type:
                - string
                - 'null'
                description: Custom conversion rule ID (numeric, from Meta Events
                  Manager).
              custom_event_str:
                type:
                - string
                - 'null'
                description: Pixel event name, used when custom_event_type is OTHER.
              custom_event_type:
                type:
                - string
                - 'null'
                description: Custom event type (e.g., PURCHASE, COMPLETE_REGISTRATION,
                  OTHER).
              page_id:
                type:
                - string
                - 'null'
                description: Facebook Page ID.
              pixel_id:
                type:
                - string
                - 'null'
                description: Meta Pixel ID for conversion tracking.
              whatsapp_phone_number:
                type:
                - string
                - 'null'
                description: WhatsApp phone number for messaging campaigns.
            required: []
            description: The object this ad set promotes (pixel, page, etc.).
          whatsapp_phone_number:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          page_id:
            type:
            - string
            - 'null'
            description: Facebook Page ID for this ad set.
          instagram_actor_id:
            type:
            - string
            - 'null'
            description: Instagram account ID for this ad set.
          audience_type:
            type:
            - string
            - 'null'
            description: Audience type for retargeting.
          publisher_platforms:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            description: Platforms to publish on (facebook, instagram, messenger,
              audience_network).
          facebook_positions:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            description: Facebook ad placements (feed, reels, stories, etc.).
          instagram_positions:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            description: Instagram ad placements (stream, story, reels, etc.).
          messenger_positions:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
          audience_network_positions:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
          threads_positions:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
          whatsapp_positions:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
          user_os:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
          user_device:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
          ios_devices:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
          android_devices:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
          geo_regions:
            type:
            - array
            - 'null'
            items:
              type: object
              properties:
                country:
                  type:
                  - string
                  - 'null'
                  description: Country code for this entry.
                key:
                  type: string
                  description: Meta geo target key/ID.
                name:
                  type:
                  - string
                  - 'null'
                  description: Display name.
                radius:
                  type:
                  - integer
                  - 'null'
                  description: Radius in miles (cities only).
                  example: 42
              required:
              - key
              description: A Meta geo target entry (region, city, or zip).
          geo_cities:
            type:
            - array
            - 'null'
            items:
              type: object
              properties:
                country:
                  type:
                  - string
                  - 'null'
                  description: Country code for this entry.
                key:
                  type: string
                  description: Meta geo target key/ID.
                name:
                  type:
                  - string
                  - 'null'
                  description: Display name.
                radius:
                  type:
                  - integer
                  - 'null'
                  description: Radius in miles (cities only).
                  example: 42
              required:
              - key
              description: A Meta geo target entry (region, city, or zip).
          geo_zips:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
          location_types:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
          excluded_geo_locations:
            type:
            - object
            - 'null'
            properties:
              cities:
                type:
                - array
                - 'null'
                items:
                  type: object
                  properties:
                    country:
                      type:
                      - string
                      - 'null'
                      description: Country code for this entry.
                    key:
                      type: string
                      description: Meta geo target key/ID.
                    name:
                      type:
                      - string
                      - 'null'
                      description: Display name.
                    radius:
                      type:
                      - integer
                      - 'null'
                      description: Radius in miles (cities only).
                      example: 42
                  required:
                  - key
                  description: A Meta geo target entry (region, city, or zip).
                description: City targets.
              countries:
                type:
                - array
                - 'null'
                items:
                  type: string
                  description: Represents textual data as UTF-8 character sequences.
                    This type is most often used by GraphQL to represent free-form
                    human-readable text.
                description: ISO 3166-1 alpha-2 country codes.
              location_types:
                type:
                - array
                - 'null'
                items:
                  type: string
                  description: Represents textual data as UTF-8 character sequences.
                    This type is most often used by GraphQL to represent free-form
                    human-readable text.
                description: Location types (home, recent, travel_in).
              regions:
                type:
                - array
                - 'null'
                items:
                  type: object
                  properties:
                    country:
                      type:
                      - string
                      - 'null'
                      description: Country code for this entry.
                    key:
                      type: string
                      description: Meta geo target key/ID.
                    name:
                      type:
                      - string
                      - 'null'
                      description: Display name.
                    radius:
                      type:
                      - integer
                      - 'null'
                      description: Radius in miles (cities only).
                      example: 42
                  required:
                  - key
                  description: A Meta geo target entry (region, city, or zip).
                description: Region/state targets.
              zips:
                type:
                - array
                - 'null'
                items:
                  type: object
                  properties:
                    country:
                      type:
                      - string
                      - 'null'
                      description: Country code for this entry.
                    key:
                      type: string
                      description: Meta geo target key/ID.
                    name:
                      type:
                      - string
                      - 'null'
                      description: Display name.
                    radius:
                      type:
                      - integer
                      - 'null'
                      description: Radius in miles (cities only).
                      example: 42
                  required:
                  - key
                  description: A Meta geo target entry (region, city, or zip).
                description: Zip/postal code targets.
            required: []
            description: Geo locations to exclude.
          geo_locations:
            type:
            - object
            - 'null'
            properties:
              cities:
                type:
                - array
                - 'null'
                items:
                  type: object
                  properties:
                    country:
                      type:
                      - string
                      - 'null'
                      description: Country code for this entry.
                    key:
                      type: string
                      description: Meta geo target key/ID.
                    name:
                      type:
                      - string
                      - 'null'
                      description: Display name.
                    radius:
                      type:
                      - integer
                      - 'null'
                      description: Radius in miles (cities only).
                      example: 42
                  required:
                  - key
                  description: A Meta geo target entry (region, city, or zip).
                description: City targets.
              countries:
                type:
                - array
                - 'null'
                items:
                  type: string
                  description: Represents textual data as UTF-8 character sequences.
                    This type is most often used by GraphQL to represent free-form
                    human-readable text.
                description: ISO 3166-1 alpha-2 country codes.
              location_types:
                type:
                - array
                - 'null'
                items:
                  type: string
                  description: Represents textual data as UTF-8 character sequences.
                    This type is most often used by GraphQL to represent free-form
                    human-readable text.
                description: Location types (home, recent, travel_in).
              regions:
                type:
                - array
                - 'null'
                items:
                  type: object
                  properties:
                    country:
                      type:
                      - string
                      - 'null'
                      description: Country code for this entry.
                    key:
                      type: string
                      description: Meta geo target key/ID.
                    name:
                      type:
                      - string
                      - 'null'
                      description: Display name.
                    radius:
                      type:
                      - integer
                      - 'null'
                      description: Radius in miles (cities only).
                      example: 42
                  required:
                  - key
                  description: A Meta geo target entry (region, city, or zip).
                description: Region/state targets.
              zips:
                type:
                - array
                - 'null'
                items:
                  type: object
                  properties:
                    country:
                      type:
                      - string
                      - 'null'
                      description: Country code for this entry.
                    key:
                      type: string
                      description: Meta geo target key/ID.
                    name:
                      type:
                      - string
                      - 'null'
                      description: Display name.
                    radius:
                      type:
                      - integer
                      - 'null'
                      description: Radius in miles (cities only).
                      example: 42
                  required:
                  - key
                  description: A Meta geo target entry (region, city, or zip).
                description: Zip/postal code targets.
            required: []
            description: Geo targeting (countries, regions, cities, zips).
          targeting_automation:
            type:
            - object
            - 'null'
            properties:
              advantage_audience:
                type:
                - integer
                - 'null'
                description: 0 = off (use exact targeting), 1 = on (let Meta expand
                  audience).
                example: 42
            required: []
            description: Advantage+ audience expansion settings.
          brand_safety_content_filter_levels:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
          is_dynamic_creative:
            type:
            - boolean
            - 'null'
            description: Represents `true` or `false` values.
          dsa_beneficiary:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          dsa_payor:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          pixel_id:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          lead_conversion_location:
            oneOf:
            - "$ref": "#/components/schemas/MetaAdGroupPlatformConfigLeadConversionLocation"
            - type: 'null'
          cost_per_result_goal:
            type:
            - number
            - 'null'
            description: Represents signed double-precision fractional values as specified
              by [IEEE 754](https://en.wikipedia.org/wiki/IEEE_floating_point).
            example: 6.9
          frequency_control_type:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          frequency_control_count:
            type:
            - integer
            - 'null'
            description: Represents non-fractional signed whole numeric values. Int
              can represent values between -(2^31) and 2^31 - 1.
            example: 42
          frequency_control_days:
            type:
            - integer
            - 'null'
            description: Represents non-fractional signed whole numeric values. Int
              can represent values between -(2^31) and 2^31 - 1.
            example: 42
          lead_gen_form_id:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          lead_form_config:
            type:
            - object
            - 'null'
            properties:
              background_image_source:
                type:
                - string
                - 'null'
                description: 'Background image source: from_ad or custom.'
              background_image_url:
                type:
                - string
                - 'null'
                description: URL of custom background image.
              conditional_logic_enabled:
                type:
                - boolean
                - 'null'
                description: Whether conditional logic is enabled for questions.
              context_card_button_text:
                type:
                - string
                - 'null'
                description: CTA button text on the greeting card.
              context_card_content:
                type:
                - array
                - 'null'
                items:
                  type: string
                  description: Represents textual data as UTF-8 character sequences.
                    This type is most often used by GraphQL to represent free-form
                    human-readable text.
                description: Optional greeting card bullet points.
              context_card_style:
                type:
                - string
                - 'null'
                description: 'Greeting layout: PARAGRAPH_STYLE or LIST_STYLE.'
              context_card_title:
                type:
                - string
                - 'null'
                description: Optional greeting card title.
              custom_disclaimer_body:
                type:
                - string
                - 'null'
                description: Custom disclaimer body text.
              custom_disclaimer_checkboxes:
                type:
                - array
                - 'null'
                items:
                  type: object
                  properties:
                    is_checked_by_default:
                      type:
                      - boolean
                      - 'null'
                      description: Whether the checkbox is checked by default.
                    is_required:
                      type:
                      - boolean
                      - 'null'
                      description: Whether the checkbox must be checked to submit.
                    key:
                      type: string
                      description: Unique key for this checkbox.
                    text:
                      type: string
                      description: Label text for the checkbox.
                  required:
                  - key
                  - text
                  description: A consent checkbox for the custom disclaimer section.
                description: Consent checkboxes for the custom disclaimer.
              custom_disclaimer_title:
                type:
                - string
                - 'null'
                description: Custom disclaimer section title.
              form_type:
                type:
                - string
                - 'null'
                description: 'Form type: more_volume, higher_intent, or rich_creative.'
              messenger_enabled:
                type:
                - boolean
                - 'null'
                description: Enable Messenger follow-up after form submission.
              name:
                type: string
                description: Name of the lead form.
              phone_verification_enabled:
                type:
                - boolean
                - 'null'
                description: Require phone number verification via OTP (higher_intent
                  only).
              privacy_policy_link_text:
                type:
                - string
                - 'null'
                description: Custom link text for privacy policy (max 70 chars).
              privacy_policy_url:
                type: string
                description: URL to your privacy policy. Required by Meta.
              question_page_custom_headline:
                type:
                - string
                - 'null'
                description: Custom headline for the questions page.
              questions:
                type: array
                items:
                  type: object
                  properties:
                    conditional_questions_group_id:
                      type:
                      - string
                      - 'null'
                      description: Group ID for conditional question routing.
                    dependent_conditional_questions:
                      type:
                      - array
                      - 'null'
                      items:
                        type: object
                        properties:
                          inline_context:
                            type:
                            - string
                            - 'null'
                            description: Helper text shown below the question.
                          key:
                            type:
                            - string
                            - 'null'
                            description: Unique key for this question.
                          label:
                            type:
                            - string
                            - 'null'
                            description: Custom label for CUSTOM questions.
                          options:
                            type:
                            - array
                            - 'null'
                            items:
                              type: object
                              properties:
                                key:
                                  type: string
                                  description: Unique key for this option.
                                logic:
                                  type:
                                  - object
                                  - 'null'
                                  properties:
                                    target_end_page_index:
                                      type:
                                      - integer
                                      - 'null'
                                      description: Index of the end page to route
                                        to (for submit_form type).
                                      example: 42
                                    target_question_index:
                                      type:
                                      - integer
                                      - 'null'
                                      description: Index of the question to route
                                        to (for go_to_question type).
                                      example: 42
                                    type:
                                      type: string
                                      description: 'Logic type: go_to_question, submit_form,
                                        or close_form.'
                                  required:
                                  - type
                                  description: Conditional logic routing for this
                                    answer option.
                                value:
                                  type: string
                                  description: Display text for this option.
                              required:
                              - key
                              - value
                              description: An answer option for a multiple choice
                                lead form question.
                            description: Answer options for multiple choice questions.
                          type:
                            type: string
                            description: Question type (EMAIL, FULL_NAME, PHONE, CUSTOM,
                              DATE_TIME, etc.).
                        required:
                        - type
                        description: A dependent conditional question (non-recursive
                          to avoid schema recursion).
                      description: Questions shown conditionally based on this question's
                        answer.
                    inline_context:
                      type:
                      - string
                      - 'null'
                      description: Helper text shown below the question.
                    key:
                      type:
                      - string
                      - 'null'
                      description: Unique key for this question.
                    label:
                      type:
                      - string
                      - 'null'
                      description: Custom label for CUSTOM questions.
                    options:
                      type:
                      - array
                      - 'null'
                      items:
                        type: object
                        properties:
                          key:
                            type: string
                            description: Unique key for this option.
                          logic:
                            type:
                            - object
                            - 'null'
                            properties:
                              target_end_page_index:
                                type:
                                - integer
                                - 'null'
                                description: Index of the end page to route to (for
                                  submit_form type).
                                example: 42
                              target_question_index:
                                type:
                                - integer
                                - 'null'
                                description: Index of the question to route to (for
                                  go_to_question type).
                                example: 42
                              type:
                                type: string
                                description: 'Logic type: go_to_question, submit_form,
                                  or close_form.'
                            required:
                            - type
                            description: Conditional logic routing for this answer
                              option.
                          value:
                            type: string
                            description: Display text for this option.
                        required:
                        - key
                        - value
                        description: An answer option for a multiple choice lead form
                          question.
                      description: Answer options for multiple choice CUSTOM questions.
                    question_format:
                      type:
                      - string
                      - 'null'
                      description: 'UI hint: short_answer, multiple_choice, or appointment.'
                    type:
                      type: string
                      description: Question type (EMAIL, FULL_NAME, PHONE, CUSTOM,
                        DATE_TIME, etc.).
                  required:
                  - type
                  description: A question on a Meta lead gen form.
                description: Questions to ask on the form.
              rich_creative_headline:
                type:
                - string
                - 'null'
                description: Headline for rich creative form intro.
              rich_creative_overview:
                type:
                - string
                - 'null'
                description: Overview description for rich creative form intro.
              rich_creative_url:
                type:
                - string
                - 'null'
                description: Uploaded image URL for rich creative form type.
              thank_you_pages:
                type:
                - array
                - 'null'
                items:
                  type: object
                  properties:
                    body:
                      type:
                      - string
                      - 'null'
                      description: Body text for this ending page.
                    business_phone:
                      type:
                      - string
                      - 'null'
                      description: Business phone number for call CTA.
                    button_text:
                      type:
                      - string
                      - 'null'
                      description: Custom button text.
                    button_type:
                      type:
                      - string
                      - 'null'
                      description: 'CTA button type: VIEW_WEBSITE, CALL_BUSINESS,
                        DOWNLOAD.'
                    conditional_question_group_id:
                      type:
                      - string
                      - 'null'
                      description: Question group ID for conditional routing to this
                        page.
                    enable_messenger:
                      type:
                      - boolean
                      - 'null'
                      description: Enable Messenger follow-up.
                    gated_file_url:
                      type:
                      - string
                      - 'null'
                      description: Uploaded file URL for gated content download.
                    link:
                      type:
                      - string
                      - 'null'
                      description: URL the button links to.
                    name:
                      type:
                      - string
                      - 'null'
                      description: Internal name for this ending page.
                    title:
                      type:
                      - string
                      - 'null'
                      description: Headline for this ending page.
                  required: []
                  description: A thank-you / ending page for a Meta lead gen form.
                description: Thank you / ending pages (supports multiple for conditional
                  routing).
            required:
            - name
            - privacy_policy_url
            - questions
            description: Configuration for a Meta lead gen instant form.
          source_adset_id:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          created_time:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          updated_time:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
        required: []
        description: Meta (Facebook/Instagram) ad set configuration.
      tiktok:
        type:
        - object
        - 'null'
        properties:
          budget_mode:
            oneOf:
            - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigBudgetMode"
            - type: 'null'
            description: Budget mode (BUDGET_MODE_DAY, BUDGET_MODE_TOTAL, BUDGET_MODE_DYNAMIC_DAILY_BUDGET).
          schedule_type:
            oneOf:
            - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigScheduleType"
            - type: 'null'
            description: Schedule type (SCHEDULE_START_END, SCHEDULE_FROM_NOW).
          schedule_start_time:
            type:
            - string
            - 'null'
            description: Schedule start time (UTC, YYYY-MM-DD HH:MM:SS).
          schedule_end_time:
            type:
            - string
            - 'null'
            description: Schedule end time (UTC, YYYY-MM-DD HH:MM:SS).
          dayparting:
            type:
            - string
            - 'null'
            description: Ad delivery schedule (48x7 character string).
          optimization_goal:
            oneOf:
            - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigOptimizationGoal"
            - type: 'null'
            description: What this ad group optimizes for on TikTok.
          billing_event:
            oneOf:
            - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigBillingEvent"
            - type: 'null'
            description: How you are billed on TikTok (CPC, CPM, OCPM, CPV).
          bid_type:
            oneOf:
            - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigBidType"
            - type: 'null'
            description: Bidding strategy (BID_TYPE_NO_BID, BID_TYPE_CUSTOM).
          bid_price:
            type:
            - number
            - 'null'
            description: Bid price (cost per result for Cost Cap).
            example: 6.9
          conversion_bid_price:
            type:
            - number
            - 'null'
            description: Target cost per conversion for oCPM.
            example: 6.9
          pacing:
            oneOf:
            - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigPacing"
            - type: 'null'
            description: Budget pacing (PACING_MODE_SMOOTH, PACING_MODE_FAST).
          placement_type:
            oneOf:
            - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigPlacementType"
            - type: 'null'
            description: Placement strategy (PLACEMENT_TYPE_AUTOMATIC, PLACEMENT_TYPE_NORMAL).
          placements:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            description: Placements (PLACEMENT_TIKTOK, PLACEMENT_PANGLE, etc.).
          tiktok_subplacements:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            description: TikTok subplacements (IN_FEED, SEARCH_FEED, etc.).
          frequency:
            type:
            - integer
            - 'null'
            description: Represents non-fractional signed whole numeric values. Int
              can represent values between -(2^31) and 2^31 - 1.
            example: 42
          frequency_schedule:
            type:
            - integer
            - 'null'
            description: Represents non-fractional signed whole numeric values. Int
              can represent values between -(2^31) and 2^31 - 1.
            example: 42
          location_ids:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            description: TikTok location/region IDs for geo targeting.
          excluded_location_ids:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            description: TikTok location/region IDs to exclude.
          pixel_id:
            type:
            - string
            - 'null'
            description: TikTok Pixel ID for conversion tracking.
          optimization_event:
            type:
            - string
            - 'null'
            description: Conversion event (e.g., COMPLETE_PAYMENT).
          app_id:
            type:
            - string
            - 'null'
            description: App ID for app promotion campaigns.
            example: app_xxxxxxxxxxxxxx
          promotion_type:
            type:
            - string
            - 'null'
            description: Promotion type (optimization location).
          instant_form_id:
            type:
            - string
            - 'null'
            description: TikTok instant form ID (set automatically when instant_form_config
              is provided).
          instant_form_config:
            type:
            - object
            - 'null'
            properties:
              button_text:
                type:
                - string
                - 'null'
                description: Submit button text.
              greeting:
                type:
                - string
                - 'null'
                description: Greeting text shown at the top of the form.
              name:
                type:
                - string
                - 'null'
                description: Form name. Auto-generated if omitted.
              privacy_policy_url:
                type: string
                description: URL to your privacy policy.
              questions:
                type: array
                items:
                  type: object
                  properties:
                    field_type:
                      type: string
                      description: Question type (EMAIL, PHONE_NUMBER, NAME, CUSTOM).
                    label:
                      type:
                      - string
                      - 'null'
                      description: Custom label for the question.
                  required:
                  - field_type
                  description: A question for a TikTok instant form.
                description: Form questions (at least one required).
            required:
            - privacy_policy_url
            - questions
            description: Instant form configuration for lead generation campaigns.
          identity_id:
            type:
            - string
            - 'null'
            description: TikTok identity ID for the ad group.
          identity_type:
            type:
            - string
            - 'null'
            description: Identity type (AUTH_CODE, TT_USER, BC_AUTH_TT).
          identity_authorized_bc_id:
            type:
            - string
            - 'null'
            description: Business Center ID for BC_AUTH_TT identity.
          operation_status:
            oneOf:
            - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigOperationStatus"
            - type: 'null'
            description: Initial status (ENABLE, DISABLE).
          creative_material_mode:
            type:
            - string
            - 'null'
            description: Creative delivery strategy.
          age_groups:
            type:
            - array
            - 'null'
            items:
              "$ref": "#/components/schemas/TiktokAgeGroup"
          gender:
            oneOf:
            - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigGender"
            - type: 'null'
          languages:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
          spending_power:
            oneOf:
            - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigSpendingPower"
            - type: 'null'
          audience_ids:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
          excluded_audience_ids:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
          audience_type:
            oneOf:
            - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigAudienceType"
            - type: 'null'
          audience_rule:
            type:
            - object
            - 'null'
            additionalProperties: true
            description: Represents untyped JSON
          interest_category_ids:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
          interest_keyword_ids:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
          actions:
            type:
            - array
            - 'null'
            items:
              type: object
              properties:
                action_category_ids:
                  type:
                  - array
                  - 'null'
                  items:
                    type: string
                    description: Represents textual data as UTF-8 character sequences.
                      This type is most often used by GraphQL to represent free-form
                      human-readable text.
                  description: Behavioral category IDs. Use /tool/action_category/
                    to list them.
                action_period:
                  type:
                  - integer
                  - 'null'
                  description: Lookback window in days. TikTok accepts 7, 15, 30,
                    60, 90, or 180.
                  example: 42
                action_scene:
                  oneOf:
                  - "$ref": "#/components/schemas/TiktokActionScene"
                  - type: 'null'
                  description: Behavior scene (VIDEO_RELATED, CREATOR_RELATED, HASHTAG_RELATED,
                    LIVE_RELATED).
                video_user_actions:
                  type:
                  - array
                  - 'null'
                  items:
                    "$ref": "#/components/schemas/TiktokVideoUserAction"
                  description: Specific video interactions (WATCHED_TO_END, LIKED,
                    COMMENTED, SHARED, FOLLOWED, PROFILE_VISITED).
              required: []
              description: A single TikTok behavioral targeting entry. One category
                of past user behavior (what they did, over what window, on which kind
                of content). See docs/tiktok_api/ad_group.md § actions.
          video_user_actions:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
          operating_systems:
            type:
            - array
            - 'null'
            items:
              "$ref": "#/components/schemas/TiktokOperatingSystem"
          min_android_version:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          min_ios_version:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          device_model_ids:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
          device_price_ranges:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
          network_types:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
          carrier_ids:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
          isp_ids:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
          ios14_targeting:
            oneOf:
            - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigIos14Targeting"
            - type: 'null'
          brand_safety_type:
            oneOf:
            - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigBrandSafetyType"
            - type: 'null'
          category_exclusion_ids:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
          vertical_sensitivity_id:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          inventory_filter_enabled:
            type:
            - boolean
            - 'null'
            description: Represents `true` or `false` values.
          secondary_optimization_event:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          deep_funnel_optimization_status:
            oneOf:
            - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigDeepFunnelOptimizationStatus"
            - type: 'null'
          deep_funnel_event_source:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          deep_funnel_event_source_id:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          click_attribution_window:
            oneOf:
            - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigClickAttributionWindow"
            - type: 'null'
          view_attribution_window:
            oneOf:
            - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigViewAttributionWindow"
            - type: 'null'
          engaged_view_attribution_window:
            oneOf:
            - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigEngagedViewAttributionWindow"
            - type: 'null'
          attribution_event_count:
            oneOf:
            - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigAttributionEventCount"
            - type: 'null'
          pangle_block_app_ids:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
          pangle_audience_package_include_ids:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
          pangle_audience_package_exclude_ids:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
          shopping_ads_retargeting_type:
            oneOf:
            - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigShoppingAdsRetargetingType"
            - type: 'null'
          shopping_ads_retargeting_actions_days:
            type:
            - integer
            - 'null'
            description: Represents non-fractional signed whole numeric values. Int
              can represent values between -(2^31) and 2^31 - 1.
            example: 42
          product_source:
            oneOf:
            - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigProductSource"
            - type: 'null'
          product_set_id:
            type:
            - string
            - 'null'
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          comment_disabled:
            type:
            - boolean
            - 'null'
            description: Represents `true` or `false` values.
          video_download_disabled:
            type:
            - boolean
            - 'null'
            description: Represents `true` or `false` values.
          contextual_tag_ids:
            type:
            - array
            - 'null'
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
        required: []
        description: TikTok ad group configuration.
    required: []
    description: Platform-specific ad group configuration.
  status:
    oneOf:
    - "$ref": "#/components/schemas/ExternalAdGroupStatuses"
    - type: 'null'
    description: Status of the ad group.
required: []
description: Parameters for UpdateAdGroupV2
```


## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `AdGroup` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/AdGroup"
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

const adGroup = await client.adGroups.update('adgrp_xxxxxxxxxxxx');

console.log(adGroup.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
ad_group = client.ad_groups.update(
    id="adgrp_xxxxxxxxxxxx",
)
print(ad_group.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

ad_group = whop.ad_groups.update("adgrp_xxxxxxxxxxxx")

puts(ad_group)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  AdBudgetTypes:
    type: string
    enum:
    - daily
    - lifetime
    description: The budget type for an ad campaign or ad group.
  AdCampaignPlatforms:
    type: string
    enum:
    - meta
    - tiktok
    description: The platforms where an ad campaign can run.
  AdCampaignStatuses:
    type: string
    enum:
    - active
    - paused
    - inactive
    - stale
    - pending_refund
    - payment_failed
    - draft
    - in_review
    - flagged
    - importing
    - imported
    description: The status of an ad campaign.
  AdGroup:
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
      ad_campaign:
        type: object
        properties:
          id:
            type: string
            description: The unique identifier for the ad campaign.
            example: adcamp_xxxxxxxxxxx
          title:
            type: string
            description: The title of the ad campaign
          platform:
            oneOf:
            - "$ref": "#/components/schemas/AdCampaignPlatforms"
            - type: 'null'
            description: The external platform this campaign is running on (e.g.,
              meta, tiktok).
          status:
            "$ref": "#/components/schemas/AdCampaignStatuses"
            description: Current status of the campaign (active, paused, or inactive)
        required:
        - id
        - title
        - platform
        - status
        description: The parent ad campaign
    required:
    - id
    - name
    - status
    - daily_budget
    - created_at
    - updated_at
    - platform_config
    - config
    - ad_campaign
    description: An external ad group (ad set) belonging to an ad campaign
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
  MetaAdGroupPlatformConfigLeadConversionLocation:
    type: string
    enum:
    - website
    - instant_forms
    - messenger
    - instagram
    - calls
    - app
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
  TiktokActionScene:
    type: string
    enum:
    - VIDEO_RELATED
    - CREATOR_RELATED
    - HASHTAG_RELATED
    - LIVE_RELATED
    description: The category of TikTok content a behavioral targeting rule applies
      to. See docs/tiktok_api/ad_group.md § actions.
  TiktokAdGroupPlatformConfigAttributionEventCount:
    type: string
    enum:
    - UNSET
    - EVERY
    - ONCE
  TiktokAdGroupPlatformConfigAudienceType:
    type: string
    enum:
    - NORMAL
    - SMART_INTERESTS_BEHAVIORS
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
  TiktokAdGroupPlatformConfigBrandSafetyType:
    type: string
    enum:
    - NO_BRAND_SAFETY
    - STANDARD_INVENTORY
    - LIMITED_INVENTORY
    - FULL_INVENTORY
    - EXPANDED_INVENTORY
  TiktokAdGroupPlatformConfigBudgetMode:
    type: string
    enum:
    - BUDGET_MODE_DAY
    - BUDGET_MODE_TOTAL
    - BUDGET_MODE_DYNAMIC_DAILY_BUDGET
  TiktokAdGroupPlatformConfigClickAttributionWindow:
    type: string
    enum:
    - 'OFF'
    - ONE_DAY
    - SEVEN_DAYS
    - FOURTEEN_DAYS
    - TWENTY_EIGHT_DAYS
  TiktokAdGroupPlatformConfigDeepFunnelOptimizationStatus:
    type: string
    enum:
    - 'ON'
    - 'OFF'
  TiktokAdGroupPlatformConfigEngagedViewAttributionWindow:
    type: string
    enum:
    - 'OFF'
    - ONE_DAY
    - SEVEN_DAYS
  TiktokAdGroupPlatformConfigGender:
    type: string
    enum:
    - GENDER_UNLIMITED
    - GENDER_MALE
    - GENDER_FEMALE
  TiktokAdGroupPlatformConfigIos14Targeting:
    type: string
    enum:
    - UNSET
    - IOS14_MINUS
    - IOS14_PLUS
    - ALL
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
  TiktokAdGroupPlatformConfigProductSource:
    type: string
    enum:
    - CATALOG
    - STORE
    - SHOWCASE
  TiktokAdGroupPlatformConfigScheduleType:
    type: string
    enum:
    - SCHEDULE_START_END
    - SCHEDULE_FROM_NOW
  TiktokAdGroupPlatformConfigShoppingAdsRetargetingType:
    type: string
    enum:
    - 'OFF'
    - LAB1
    - LAB2
    - LAB3
    - LAB4
    - LAB5
  TiktokAdGroupPlatformConfigSpendingPower:
    type: string
    enum:
    - ALL
    - HIGH
  TiktokAdGroupPlatformConfigViewAttributionWindow:
    type: string
    enum:
    - 'OFF'
    - ONE_DAY
    - SEVEN_DAYS
  TiktokAgeGroup:
    type: string
    enum:
    - AGE_13_17
    - AGE_18_24
    - AGE_25_34
    - AGE_35_44
    - AGE_45_54
    - AGE_55_100
    description: Age groups targetable on TikTok. See docs/tiktok_api/ad_group.md
      § age_groups.
  TiktokOperatingSystem:
    type: string
    enum:
    - ANDROID
    - IOS
    description: Device operating systems targetable on TikTok. See docs/tiktok_api/ad_group.md
      § operating_systems.
  TiktokVideoUserAction:
    type: string
    enum:
    - WATCHED_TO_END
    - LIKED
    - COMMENTED
    - SHARED
    - FOLLOWED
    - PROFILE_VISITED
    description: Specific past video interactions used for behavioral targeting. See
      docs/tiktok_api/ad_group.md § actions.video_user_actions.
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
  "/ad_groups/{id}":
    patch:
      tags:
      - Ad groups
      operationId: updateAdGroup
      summary: Update ad group
      description: |-
        Updates an existing ad group.

        Required permissions:
         - `ad_campaign:update`
         - `ad_campaign:basic:read`
      parameters:
      - name: id
        in: path
        required: true
        description: The unique identifier of the ad group to update.
        schema:
          type: string
          example: adgrp_xxxxxxxxxxxx
      x-group-title: Ads
      security:
      - bearerAuth:
        - ad_campaign:update
        - ad_campaign:basic:read
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/AdGroup"
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
                budget:
                  type:
                  - number
                  - 'null'
                  description: Budget amount in dollars.
                  example: 6.9
                budget_type:
                  oneOf:
                  - "$ref": "#/components/schemas/AdBudgetTypes"
                  - type: 'null'
                  description: Whether the budget is daily or lifetime.
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
                      description: Bid cap amount in cents. Used when bid_strategy
                        is bid_cap or cost_cap.
                      example: 42
                    optimization_goal:
                      oneOf:
                      - "$ref": "#/components/schemas/AdGroupConfigOptimizationGoal"
                      - type: 'null'
                      description: What the ad group optimizes for (e.g., conversions,
                        link_clicks, reach).
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
                      description: Scheduled end time (ISO8601). Required for lifetime
                        budgets.
                    pacing:
                      oneOf:
                      - "$ref": "#/components/schemas/AdGroupConfigPacing"
                      - type: 'null'
                      description: 'Budget pacing: standard (even) or accelerated
                        (fast).'
                    frequency_cap:
                      type:
                      - integer
                      - 'null'
                      description: Maximum number of times to show ads to each person
                        in the frequency interval.
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
                        age_max:
                          type:
                          - integer
                          - 'null'
                          description: Maximum age for demographic targeting.
                          example: 42
                        age_min:
                          type:
                          - integer
                          - 'null'
                          description: Minimum age for demographic targeting.
                          example: 42
                        countries:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                          description: ISO 3166-1 alpha-2 country codes to target.
                        device_platforms:
                          type:
                          - array
                          - 'null'
                          items:
                            "$ref": "#/components/schemas/TargetingDevicePlatforms"
                          description: Device platforms to target.
                        exclude_audience_ids:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                          description: Platform audience IDs to exclude.
                        genders:
                          type:
                          - array
                          - 'null'
                          items:
                            "$ref": "#/components/schemas/TargetingGenders"
                          description: Genders to target.
                        include_audience_ids:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                          description: Platform audience IDs to include.
                        interest_ids:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                          description: Platform-specific interest IDs to target.
                        languages:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                          description: Language codes to target.
                        placement_type:
                          oneOf:
                          - "$ref": "#/components/schemas/TargetingPlacementTypes"
                          - type: 'null'
                          description: Placement strategy.
                      required: []
                      description: Audience targeting settings (demographics, geo,
                        interests, audiences, devices).
                  required: []
                  description: Unified ad group configuration (bidding, optimization,
                    targeting).
                daily_budget:
                  type:
                  - number
                  - 'null'
                  description: Daily budget in dollars.
                  example: 6.9
                name:
                  type:
                  - string
                  - 'null'
                  description: Human-readable ad group name.
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
                        status:
                          oneOf:
                          - "$ref": "#/components/schemas/MetaAdGroupPlatformConfigStatus"
                          - type: 'null'
                        bid_strategy:
                          oneOf:
                          - "$ref": "#/components/schemas/MetaAdGroupPlatformConfigBidStrategy"
                          - type: 'null'
                          description: Meta bid strategy.
                        bid_amount:
                          type:
                          - integer
                          - 'null'
                          description: Bid amount in cents.
                          example: 42
                        daily_budget:
                          type:
                          - integer
                          - 'null'
                          description: Daily budget in cents.
                          example: 42
                        daily_min_spend_target:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        daily_spend_cap:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        lifetime_budget:
                          type:
                          - integer
                          - 'null'
                          description: Lifetime budget in cents.
                          example: 42
                        lifetime_min_spend_target:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        lifetime_spend_cap:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        budget_remaining:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        start_time:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        end_time:
                          type:
                          - string
                          - 'null'
                          description: End time (ISO8601). Required for lifetime budgets.
                        optimization_goal:
                          oneOf:
                          - "$ref": "#/components/schemas/MetaAdGroupPlatformConfigOptimizationGoal"
                          - type: 'null'
                          description: What this ad set optimizes for on Meta.
                        billing_event:
                          oneOf:
                          - "$ref": "#/components/schemas/MetaAdGroupPlatformConfigBillingEvent"
                          - type: 'null'
                          description: How you are billed on Meta.
                        attribution_spec:
                          type:
                          - array
                          - 'null'
                          items:
                            type: object
                            properties:
                              event_type:
                                type: string
                                description: Attribution event type (e.g., CLICK_THROUGH,
                                  VIEW_THROUGH).
                              window_days:
                                type: integer
                                description: Attribution window in days (1, 7, 28).
                                example: 42
                            required:
                            - event_type
                            - window_days
                            description: Meta conversion attribution window.
                          description: Conversion attribution windows.
                        attribution_setting:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        destination_type:
                          oneOf:
                          - "$ref": "#/components/schemas/MetaAdGroupPlatformConfigDestinationType"
                          - type: 'null'
                          description: Where ads in this ad set direct people.
                        promoted_object:
                          type:
                          - object
                          - 'null'
                          properties:
                            custom_conversion_id:
                              type:
                              - string
                              - 'null'
                              description: Custom conversion rule ID (numeric, from
                                Meta Events Manager).
                            custom_event_str:
                              type:
                              - string
                              - 'null'
                              description: Pixel event name, used when custom_event_type
                                is OTHER.
                            custom_event_type:
                              type:
                              - string
                              - 'null'
                              description: Custom event type (e.g., PURCHASE, COMPLETE_REGISTRATION,
                                OTHER).
                            page_id:
                              type:
                              - string
                              - 'null'
                              description: Facebook Page ID.
                            pixel_id:
                              type:
                              - string
                              - 'null'
                              description: Meta Pixel ID for conversion tracking.
                            whatsapp_phone_number:
                              type:
                              - string
                              - 'null'
                              description: WhatsApp phone number for messaging campaigns.
                          required: []
                          description: The object this ad set promotes (pixel, page,
                            etc.).
                        whatsapp_phone_number:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        page_id:
                          type:
                          - string
                          - 'null'
                          description: Facebook Page ID for this ad set.
                        instagram_actor_id:
                          type:
                          - string
                          - 'null'
                          description: Instagram account ID for this ad set.
                        audience_type:
                          type:
                          - string
                          - 'null'
                          description: Audience type for retargeting.
                        publisher_platforms:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                          description: Platforms to publish on (facebook, instagram,
                            messenger, audience_network).
                        facebook_positions:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                          description: Facebook ad placements (feed, reels, stories,
                            etc.).
                        instagram_positions:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                          description: Instagram ad placements (stream, story, reels,
                            etc.).
                        messenger_positions:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                        audience_network_positions:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                        threads_positions:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                        whatsapp_positions:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                        user_os:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                        user_device:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                        ios_devices:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                        android_devices:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                        geo_regions:
                          type:
                          - array
                          - 'null'
                          items:
                            type: object
                            properties:
                              country:
                                type:
                                - string
                                - 'null'
                                description: Country code for this entry.
                              key:
                                type: string
                                description: Meta geo target key/ID.
                              name:
                                type:
                                - string
                                - 'null'
                                description: Display name.
                              radius:
                                type:
                                - integer
                                - 'null'
                                description: Radius in miles (cities only).
                                example: 42
                            required:
                            - key
                            description: A Meta geo target entry (region, city, or
                              zip).
                        geo_cities:
                          type:
                          - array
                          - 'null'
                          items:
                            type: object
                            properties:
                              country:
                                type:
                                - string
                                - 'null'
                                description: Country code for this entry.
                              key:
                                type: string
                                description: Meta geo target key/ID.
                              name:
                                type:
                                - string
                                - 'null'
                                description: Display name.
                              radius:
                                type:
                                - integer
                                - 'null'
                                description: Radius in miles (cities only).
                                example: 42
                            required:
                            - key
                            description: A Meta geo target entry (region, city, or
                              zip).
                        geo_zips:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                        location_types:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                        excluded_geo_locations:
                          type:
                          - object
                          - 'null'
                          properties:
                            cities:
                              type:
                              - array
                              - 'null'
                              items:
                                type: object
                                properties:
                                  country:
                                    type:
                                    - string
                                    - 'null'
                                    description: Country code for this entry.
                                  key:
                                    type: string
                                    description: Meta geo target key/ID.
                                  name:
                                    type:
                                    - string
                                    - 'null'
                                    description: Display name.
                                  radius:
                                    type:
                                    - integer
                                    - 'null'
                                    description: Radius in miles (cities only).
                                    example: 42
                                required:
                                - key
                                description: A Meta geo target entry (region, city,
                                  or zip).
                              description: City targets.
                            countries:
                              type:
                              - array
                              - 'null'
                              items:
                                type: string
                                description: Represents textual data as UTF-8 character
                                  sequences. This type is most often used by GraphQL
                                  to represent free-form human-readable text.
                              description: ISO 3166-1 alpha-2 country codes.
                            location_types:
                              type:
                              - array
                              - 'null'
                              items:
                                type: string
                                description: Represents textual data as UTF-8 character
                                  sequences. This type is most often used by GraphQL
                                  to represent free-form human-readable text.
                              description: Location types (home, recent, travel_in).
                            regions:
                              type:
                              - array
                              - 'null'
                              items:
                                type: object
                                properties:
                                  country:
                                    type:
                                    - string
                                    - 'null'
                                    description: Country code for this entry.
                                  key:
                                    type: string
                                    description: Meta geo target key/ID.
                                  name:
                                    type:
                                    - string
                                    - 'null'
                                    description: Display name.
                                  radius:
                                    type:
                                    - integer
                                    - 'null'
                                    description: Radius in miles (cities only).
                                    example: 42
                                required:
                                - key
                                description: A Meta geo target entry (region, city,
                                  or zip).
                              description: Region/state targets.
                            zips:
                              type:
                              - array
                              - 'null'
                              items:
                                type: object
                                properties:
                                  country:
                                    type:
                                    - string
                                    - 'null'
                                    description: Country code for this entry.
                                  key:
                                    type: string
                                    description: Meta geo target key/ID.
                                  name:
                                    type:
                                    - string
                                    - 'null'
                                    description: Display name.
                                  radius:
                                    type:
                                    - integer
                                    - 'null'
                                    description: Radius in miles (cities only).
                                    example: 42
                                required:
                                - key
                                description: A Meta geo target entry (region, city,
                                  or zip).
                              description: Zip/postal code targets.
                          required: []
                          description: Geo locations to exclude.
                        geo_locations:
                          type:
                          - object
                          - 'null'
                          properties:
                            cities:
                              type:
                              - array
                              - 'null'
                              items:
                                type: object
                                properties:
                                  country:
                                    type:
                                    - string
                                    - 'null'
                                    description: Country code for this entry.
                                  key:
                                    type: string
                                    description: Meta geo target key/ID.
                                  name:
                                    type:
                                    - string
                                    - 'null'
                                    description: Display name.
                                  radius:
                                    type:
                                    - integer
                                    - 'null'
                                    description: Radius in miles (cities only).
                                    example: 42
                                required:
                                - key
                                description: A Meta geo target entry (region, city,
                                  or zip).
                              description: City targets.
                            countries:
                              type:
                              - array
                              - 'null'
                              items:
                                type: string
                                description: Represents textual data as UTF-8 character
                                  sequences. This type is most often used by GraphQL
                                  to represent free-form human-readable text.
                              description: ISO 3166-1 alpha-2 country codes.
                            location_types:
                              type:
                              - array
                              - 'null'
                              items:
                                type: string
                                description: Represents textual data as UTF-8 character
                                  sequences. This type is most often used by GraphQL
                                  to represent free-form human-readable text.
                              description: Location types (home, recent, travel_in).
                            regions:
                              type:
                              - array
                              - 'null'
                              items:
                                type: object
                                properties:
                                  country:
                                    type:
                                    - string
                                    - 'null'
                                    description: Country code for this entry.
                                  key:
                                    type: string
                                    description: Meta geo target key/ID.
                                  name:
                                    type:
                                    - string
                                    - 'null'
                                    description: Display name.
                                  radius:
                                    type:
                                    - integer
                                    - 'null'
                                    description: Radius in miles (cities only).
                                    example: 42
                                required:
                                - key
                                description: A Meta geo target entry (region, city,
                                  or zip).
                              description: Region/state targets.
                            zips:
                              type:
                              - array
                              - 'null'
                              items:
                                type: object
                                properties:
                                  country:
                                    type:
                                    - string
                                    - 'null'
                                    description: Country code for this entry.
                                  key:
                                    type: string
                                    description: Meta geo target key/ID.
                                  name:
                                    type:
                                    - string
                                    - 'null'
                                    description: Display name.
                                  radius:
                                    type:
                                    - integer
                                    - 'null'
                                    description: Radius in miles (cities only).
                                    example: 42
                                required:
                                - key
                                description: A Meta geo target entry (region, city,
                                  or zip).
                              description: Zip/postal code targets.
                          required: []
                          description: Geo targeting (countries, regions, cities,
                            zips).
                        targeting_automation:
                          type:
                          - object
                          - 'null'
                          properties:
                            advantage_audience:
                              type:
                              - integer
                              - 'null'
                              description: 0 = off (use exact targeting), 1 = on (let
                                Meta expand audience).
                              example: 42
                          required: []
                          description: Advantage+ audience expansion settings.
                        brand_safety_content_filter_levels:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                        is_dynamic_creative:
                          type:
                          - boolean
                          - 'null'
                          description: Represents `true` or `false` values.
                        dsa_beneficiary:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        dsa_payor:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        pixel_id:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        lead_conversion_location:
                          oneOf:
                          - "$ref": "#/components/schemas/MetaAdGroupPlatformConfigLeadConversionLocation"
                          - type: 'null'
                        cost_per_result_goal:
                          type:
                          - number
                          - 'null'
                          description: Represents signed double-precision fractional
                            values as specified by [IEEE 754](https://en.wikipedia.org/wiki/IEEE_floating_point).
                          example: 6.9
                        frequency_control_type:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        frequency_control_count:
                          type:
                          - integer
                          - 'null'
                          description: Represents non-fractional signed whole numeric
                            values. Int can represent values between -(2^31) and 2^31
                            - 1.
                          example: 42
                        frequency_control_days:
                          type:
                          - integer
                          - 'null'
                          description: Represents non-fractional signed whole numeric
                            values. Int can represent values between -(2^31) and 2^31
                            - 1.
                          example: 42
                        lead_gen_form_id:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        lead_form_config:
                          type:
                          - object
                          - 'null'
                          properties:
                            background_image_source:
                              type:
                              - string
                              - 'null'
                              description: 'Background image source: from_ad or custom.'
                            background_image_url:
                              type:
                              - string
                              - 'null'
                              description: URL of custom background image.
                            conditional_logic_enabled:
                              type:
                              - boolean
                              - 'null'
                              description: Whether conditional logic is enabled for
                                questions.
                            context_card_button_text:
                              type:
                              - string
                              - 'null'
                              description: CTA button text on the greeting card.
                            context_card_content:
                              type:
                              - array
                              - 'null'
                              items:
                                type: string
                                description: Represents textual data as UTF-8 character
                                  sequences. This type is most often used by GraphQL
                                  to represent free-form human-readable text.
                              description: Optional greeting card bullet points.
                            context_card_style:
                              type:
                              - string
                              - 'null'
                              description: 'Greeting layout: PARAGRAPH_STYLE or LIST_STYLE.'
                            context_card_title:
                              type:
                              - string
                              - 'null'
                              description: Optional greeting card title.
                            custom_disclaimer_body:
                              type:
                              - string
                              - 'null'
                              description: Custom disclaimer body text.
                            custom_disclaimer_checkboxes:
                              type:
                              - array
                              - 'null'
                              items:
                                type: object
                                properties:
                                  is_checked_by_default:
                                    type:
                                    - boolean
                                    - 'null'
                                    description: Whether the checkbox is checked by
                                      default.
                                  is_required:
                                    type:
                                    - boolean
                                    - 'null'
                                    description: Whether the checkbox must be checked
                                      to submit.
                                  key:
                                    type: string
                                    description: Unique key for this checkbox.
                                  text:
                                    type: string
                                    description: Label text for the checkbox.
                                required:
                                - key
                                - text
                                description: A consent checkbox for the custom disclaimer
                                  section.
                              description: Consent checkboxes for the custom disclaimer.
                            custom_disclaimer_title:
                              type:
                              - string
                              - 'null'
                              description: Custom disclaimer section title.
                            form_type:
                              type:
                              - string
                              - 'null'
                              description: 'Form type: more_volume, higher_intent,
                                or rich_creative.'
                            messenger_enabled:
                              type:
                              - boolean
                              - 'null'
                              description: Enable Messenger follow-up after form submission.
                            name:
                              type: string
                              description: Name of the lead form.
                            phone_verification_enabled:
                              type:
                              - boolean
                              - 'null'
                              description: Require phone number verification via OTP
                                (higher_intent only).
                            privacy_policy_link_text:
                              type:
                              - string
                              - 'null'
                              description: Custom link text for privacy policy (max
                                70 chars).
                            privacy_policy_url:
                              type: string
                              description: URL to your privacy policy. Required by
                                Meta.
                            question_page_custom_headline:
                              type:
                              - string
                              - 'null'
                              description: Custom headline for the questions page.
                            questions:
                              type: array
                              items:
                                type: object
                                properties:
                                  conditional_questions_group_id:
                                    type:
                                    - string
                                    - 'null'
                                    description: Group ID for conditional question
                                      routing.
                                  dependent_conditional_questions:
                                    type:
                                    - array
                                    - 'null'
                                    items:
                                      type: object
                                      properties:
                                        inline_context:
                                          type:
                                          - string
                                          - 'null'
                                          description: Helper text shown below the
                                            question.
                                        key:
                                          type:
                                          - string
                                          - 'null'
                                          description: Unique key for this question.
                                        label:
                                          type:
                                          - string
                                          - 'null'
                                          description: Custom label for CUSTOM questions.
                                        options:
                                          type:
                                          - array
                                          - 'null'
                                          items:
                                            type: object
                                            properties:
                                              key:
                                                type: string
                                                description: Unique key for this option.
                                              logic:
                                                type:
                                                - object
                                                - 'null'
                                                properties:
                                                  target_end_page_index:
                                                    type:
                                                    - integer
                                                    - 'null'
                                                    description: Index of the end
                                                      page to route to (for submit_form
                                                      type).
                                                    example: 42
                                                  target_question_index:
                                                    type:
                                                    - integer
                                                    - 'null'
                                                    description: Index of the question
                                                      to route to (for go_to_question
                                                      type).
                                                    example: 42
                                                  type:
                                                    type: string
                                                    description: 'Logic type: go_to_question,
                                                      submit_form, or close_form.'
                                                required:
                                                - type
                                                description: Conditional logic routing
                                                  for this answer option.
                                              value:
                                                type: string
                                                description: Display text for this
                                                  option.
                                            required:
                                            - key
                                            - value
                                            description: An answer option for a multiple
                                              choice lead form question.
                                          description: Answer options for multiple
                                            choice questions.
                                        type:
                                          type: string
                                          description: Question type (EMAIL, FULL_NAME,
                                            PHONE, CUSTOM, DATE_TIME, etc.).
                                      required:
                                      - type
                                      description: A dependent conditional question
                                        (non-recursive to avoid schema recursion).
                                    description: Questions shown conditionally based
                                      on this question's answer.
                                  inline_context:
                                    type:
                                    - string
                                    - 'null'
                                    description: Helper text shown below the question.
                                  key:
                                    type:
                                    - string
                                    - 'null'
                                    description: Unique key for this question.
                                  label:
                                    type:
                                    - string
                                    - 'null'
                                    description: Custom label for CUSTOM questions.
                                  options:
                                    type:
                                    - array
                                    - 'null'
                                    items:
                                      type: object
                                      properties:
                                        key:
                                          type: string
                                          description: Unique key for this option.
                                        logic:
                                          type:
                                          - object
                                          - 'null'
                                          properties:
                                            target_end_page_index:
                                              type:
                                              - integer
                                              - 'null'
                                              description: Index of the end page to
                                                route to (for submit_form type).
                                              example: 42
                                            target_question_index:
                                              type:
                                              - integer
                                              - 'null'
                                              description: Index of the question to
                                                route to (for go_to_question type).
                                              example: 42
                                            type:
                                              type: string
                                              description: 'Logic type: go_to_question,
                                                submit_form, or close_form.'
                                          required:
                                          - type
                                          description: Conditional logic routing for
                                            this answer option.
                                        value:
                                          type: string
                                          description: Display text for this option.
                                      required:
                                      - key
                                      - value
                                      description: An answer option for a multiple
                                        choice lead form question.
                                    description: Answer options for multiple choice
                                      CUSTOM questions.
                                  question_format:
                                    type:
                                    - string
                                    - 'null'
                                    description: 'UI hint: short_answer, multiple_choice,
                                      or appointment.'
                                  type:
                                    type: string
                                    description: Question type (EMAIL, FULL_NAME,
                                      PHONE, CUSTOM, DATE_TIME, etc.).
                                required:
                                - type
                                description: A question on a Meta lead gen form.
                              description: Questions to ask on the form.
                            rich_creative_headline:
                              type:
                              - string
                              - 'null'
                              description: Headline for rich creative form intro.
                            rich_creative_overview:
                              type:
                              - string
                              - 'null'
                              description: Overview description for rich creative
                                form intro.
                            rich_creative_url:
                              type:
                              - string
                              - 'null'
                              description: Uploaded image URL for rich creative form
                                type.
                            thank_you_pages:
                              type:
                              - array
                              - 'null'
                              items:
                                type: object
                                properties:
                                  body:
                                    type:
                                    - string
                                    - 'null'
                                    description: Body text for this ending page.
                                  business_phone:
                                    type:
                                    - string
                                    - 'null'
                                    description: Business phone number for call CTA.
                                  button_text:
                                    type:
                                    - string
                                    - 'null'
                                    description: Custom button text.
                                  button_type:
                                    type:
                                    - string
                                    - 'null'
                                    description: 'CTA button type: VIEW_WEBSITE, CALL_BUSINESS,
                                      DOWNLOAD.'
                                  conditional_question_group_id:
                                    type:
                                    - string
                                    - 'null'
                                    description: Question group ID for conditional
                                      routing to this page.
                                  enable_messenger:
                                    type:
                                    - boolean
                                    - 'null'
                                    description: Enable Messenger follow-up.
                                  gated_file_url:
                                    type:
                                    - string
                                    - 'null'
                                    description: Uploaded file URL for gated content
                                      download.
                                  link:
                                    type:
                                    - string
                                    - 'null'
                                    description: URL the button links to.
                                  name:
                                    type:
                                    - string
                                    - 'null'
                                    description: Internal name for this ending page.
                                  title:
                                    type:
                                    - string
                                    - 'null'
                                    description: Headline for this ending page.
                                required: []
                                description: A thank-you / ending page for a Meta
                                  lead gen form.
                              description: Thank you / ending pages (supports multiple
                                for conditional routing).
                          required:
                          - name
                          - privacy_policy_url
                          - questions
                          description: Configuration for a Meta lead gen instant form.
                        source_adset_id:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        created_time:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        updated_time:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                      required: []
                      description: Meta (Facebook/Instagram) ad set configuration.
                    tiktok:
                      type:
                      - object
                      - 'null'
                      properties:
                        budget_mode:
                          oneOf:
                          - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigBudgetMode"
                          - type: 'null'
                          description: Budget mode (BUDGET_MODE_DAY, BUDGET_MODE_TOTAL,
                            BUDGET_MODE_DYNAMIC_DAILY_BUDGET).
                        schedule_type:
                          oneOf:
                          - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigScheduleType"
                          - type: 'null'
                          description: Schedule type (SCHEDULE_START_END, SCHEDULE_FROM_NOW).
                        schedule_start_time:
                          type:
                          - string
                          - 'null'
                          description: Schedule start time (UTC, YYYY-MM-DD HH:MM:SS).
                        schedule_end_time:
                          type:
                          - string
                          - 'null'
                          description: Schedule end time (UTC, YYYY-MM-DD HH:MM:SS).
                        dayparting:
                          type:
                          - string
                          - 'null'
                          description: Ad delivery schedule (48x7 character string).
                        optimization_goal:
                          oneOf:
                          - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigOptimizationGoal"
                          - type: 'null'
                          description: What this ad group optimizes for on TikTok.
                        billing_event:
                          oneOf:
                          - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigBillingEvent"
                          - type: 'null'
                          description: How you are billed on TikTok (CPC, CPM, OCPM,
                            CPV).
                        bid_type:
                          oneOf:
                          - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigBidType"
                          - type: 'null'
                          description: Bidding strategy (BID_TYPE_NO_BID, BID_TYPE_CUSTOM).
                        bid_price:
                          type:
                          - number
                          - 'null'
                          description: Bid price (cost per result for Cost Cap).
                          example: 6.9
                        conversion_bid_price:
                          type:
                          - number
                          - 'null'
                          description: Target cost per conversion for oCPM.
                          example: 6.9
                        pacing:
                          oneOf:
                          - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigPacing"
                          - type: 'null'
                          description: Budget pacing (PACING_MODE_SMOOTH, PACING_MODE_FAST).
                        placement_type:
                          oneOf:
                          - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigPlacementType"
                          - type: 'null'
                          description: Placement strategy (PLACEMENT_TYPE_AUTOMATIC,
                            PLACEMENT_TYPE_NORMAL).
                        placements:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                          description: Placements (PLACEMENT_TIKTOK, PLACEMENT_PANGLE,
                            etc.).
                        tiktok_subplacements:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                          description: TikTok subplacements (IN_FEED, SEARCH_FEED,
                            etc.).
                        frequency:
                          type:
                          - integer
                          - 'null'
                          description: Represents non-fractional signed whole numeric
                            values. Int can represent values between -(2^31) and 2^31
                            - 1.
                          example: 42
                        frequency_schedule:
                          type:
                          - integer
                          - 'null'
                          description: Represents non-fractional signed whole numeric
                            values. Int can represent values between -(2^31) and 2^31
                            - 1.
                          example: 42
                        location_ids:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                          description: TikTok location/region IDs for geo targeting.
                        excluded_location_ids:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                          description: TikTok location/region IDs to exclude.
                        pixel_id:
                          type:
                          - string
                          - 'null'
                          description: TikTok Pixel ID for conversion tracking.
                        optimization_event:
                          type:
                          - string
                          - 'null'
                          description: Conversion event (e.g., COMPLETE_PAYMENT).
                        app_id:
                          type:
                          - string
                          - 'null'
                          description: App ID for app promotion campaigns.
                          example: app_xxxxxxxxxxxxxx
                        promotion_type:
                          type:
                          - string
                          - 'null'
                          description: Promotion type (optimization location).
                        instant_form_id:
                          type:
                          - string
                          - 'null'
                          description: TikTok instant form ID (set automatically when
                            instant_form_config is provided).
                        instant_form_config:
                          type:
                          - object
                          - 'null'
                          properties:
                            button_text:
                              type:
                              - string
                              - 'null'
                              description: Submit button text.
                            greeting:
                              type:
                              - string
                              - 'null'
                              description: Greeting text shown at the top of the form.
                            name:
                              type:
                              - string
                              - 'null'
                              description: Form name. Auto-generated if omitted.
                            privacy_policy_url:
                              type: string
                              description: URL to your privacy policy.
                            questions:
                              type: array
                              items:
                                type: object
                                properties:
                                  field_type:
                                    type: string
                                    description: Question type (EMAIL, PHONE_NUMBER,
                                      NAME, CUSTOM).
                                  label:
                                    type:
                                    - string
                                    - 'null'
                                    description: Custom label for the question.
                                required:
                                - field_type
                                description: A question for a TikTok instant form.
                              description: Form questions (at least one required).
                          required:
                          - privacy_policy_url
                          - questions
                          description: Instant form configuration for lead generation
                            campaigns.
                        identity_id:
                          type:
                          - string
                          - 'null'
                          description: TikTok identity ID for the ad group.
                        identity_type:
                          type:
                          - string
                          - 'null'
                          description: Identity type (AUTH_CODE, TT_USER, BC_AUTH_TT).
                        identity_authorized_bc_id:
                          type:
                          - string
                          - 'null'
                          description: Business Center ID for BC_AUTH_TT identity.
                        operation_status:
                          oneOf:
                          - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigOperationStatus"
                          - type: 'null'
                          description: Initial status (ENABLE, DISABLE).
                        creative_material_mode:
                          type:
                          - string
                          - 'null'
                          description: Creative delivery strategy.
                        age_groups:
                          type:
                          - array
                          - 'null'
                          items:
                            "$ref": "#/components/schemas/TiktokAgeGroup"
                        gender:
                          oneOf:
                          - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigGender"
                          - type: 'null'
                        languages:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                        spending_power:
                          oneOf:
                          - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigSpendingPower"
                          - type: 'null'
                        audience_ids:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                        excluded_audience_ids:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                        audience_type:
                          oneOf:
                          - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigAudienceType"
                          - type: 'null'
                        audience_rule:
                          type:
                          - object
                          - 'null'
                          additionalProperties: true
                          description: Represents untyped JSON
                        interest_category_ids:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                        interest_keyword_ids:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                        actions:
                          type:
                          - array
                          - 'null'
                          items:
                            type: object
                            properties:
                              action_category_ids:
                                type:
                                - array
                                - 'null'
                                items:
                                  type: string
                                  description: Represents textual data as UTF-8 character
                                    sequences. This type is most often used by GraphQL
                                    to represent free-form human-readable text.
                                description: Behavioral category IDs. Use /tool/action_category/
                                  to list them.
                              action_period:
                                type:
                                - integer
                                - 'null'
                                description: Lookback window in days. TikTok accepts
                                  7, 15, 30, 60, 90, or 180.
                                example: 42
                              action_scene:
                                oneOf:
                                - "$ref": "#/components/schemas/TiktokActionScene"
                                - type: 'null'
                                description: Behavior scene (VIDEO_RELATED, CREATOR_RELATED,
                                  HASHTAG_RELATED, LIVE_RELATED).
                              video_user_actions:
                                type:
                                - array
                                - 'null'
                                items:
                                  "$ref": "#/components/schemas/TiktokVideoUserAction"
                                description: Specific video interactions (WATCHED_TO_END,
                                  LIKED, COMMENTED, SHARED, FOLLOWED, PROFILE_VISITED).
                            required: []
                            description: A single TikTok behavioral targeting entry.
                              One category of past user behavior (what they did, over
                              what window, on which kind of content). See docs/tiktok_api/ad_group.md
                              § actions.
                        video_user_actions:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                        operating_systems:
                          type:
                          - array
                          - 'null'
                          items:
                            "$ref": "#/components/schemas/TiktokOperatingSystem"
                        min_android_version:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        min_ios_version:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        device_model_ids:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                        device_price_ranges:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                        network_types:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                        carrier_ids:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                        isp_ids:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                        ios14_targeting:
                          oneOf:
                          - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigIos14Targeting"
                          - type: 'null'
                        brand_safety_type:
                          oneOf:
                          - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigBrandSafetyType"
                          - type: 'null'
                        category_exclusion_ids:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                        vertical_sensitivity_id:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        inventory_filter_enabled:
                          type:
                          - boolean
                          - 'null'
                          description: Represents `true` or `false` values.
                        secondary_optimization_event:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        deep_funnel_optimization_status:
                          oneOf:
                          - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigDeepFunnelOptimizationStatus"
                          - type: 'null'
                        deep_funnel_event_source:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        deep_funnel_event_source_id:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        click_attribution_window:
                          oneOf:
                          - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigClickAttributionWindow"
                          - type: 'null'
                        view_attribution_window:
                          oneOf:
                          - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigViewAttributionWindow"
                          - type: 'null'
                        engaged_view_attribution_window:
                          oneOf:
                          - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigEngagedViewAttributionWindow"
                          - type: 'null'
                        attribution_event_count:
                          oneOf:
                          - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigAttributionEventCount"
                          - type: 'null'
                        pangle_block_app_ids:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                        pangle_audience_package_include_ids:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                        pangle_audience_package_exclude_ids:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                        shopping_ads_retargeting_type:
                          oneOf:
                          - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigShoppingAdsRetargetingType"
                          - type: 'null'
                        shopping_ads_retargeting_actions_days:
                          type:
                          - integer
                          - 'null'
                          description: Represents non-fractional signed whole numeric
                            values. Int can represent values between -(2^31) and 2^31
                            - 1.
                          example: 42
                        product_source:
                          oneOf:
                          - "$ref": "#/components/schemas/TiktokAdGroupPlatformConfigProductSource"
                          - type: 'null'
                        product_set_id:
                          type:
                          - string
                          - 'null'
                          description: Represents textual data as UTF-8 character
                            sequences. This type is most often used by GraphQL to
                            represent free-form human-readable text.
                        comment_disabled:
                          type:
                          - boolean
                          - 'null'
                          description: Represents `true` or `false` values.
                        video_download_disabled:
                          type:
                          - boolean
                          - 'null'
                          description: Represents `true` or `false` values.
                        contextual_tag_ids:
                          type:
                          - array
                          - 'null'
                          items:
                            type: string
                            description: Represents textual data as UTF-8 character
                              sequences. This type is most often used by GraphQL to
                              represent free-form human-readable text.
                      required: []
                      description: TikTok ad group configuration.
                  required: []
                  description: Platform-specific ad group configuration.
                status:
                  oneOf:
                  - "$ref": "#/components/schemas/ExternalAdGroupStatuses"
                  - type: 'null'
                  description: Status of the ad group.
              required: []
              description: Parameters for UpdateAdGroupV2
      x-codeSamples:
      - lang: JavaScript
        source: |-
          import Whop from '@whop/sdk';

          const client = new Whop({
            apiKey: process.env['WHOP_API_KEY'], // This is the default and can be omitted
          });

          const adGroup = await client.adGroups.update('adgrp_xxxxxxxxxxxx');

          console.log(adGroup.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          ad_group = client.ad_groups.update(
              id="adgrp_xxxxxxxxxxxx",
          )
          print(ad_group.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          ad_group = whop.ad_groups.update("adgrp_xxxxxxxxxxxx")

          puts(ad_group)
components:
  schemas:
    AdBudgetTypes:
      type: string
      enum:
      - daily
      - lifetime
      description: The budget type for an ad campaign or ad group.
    AdCampaignPlatforms:
      type: string
      enum:
      - meta
      - tiktok
      description: The platforms where an ad campaign can run.
    AdCampaignStatuses:
      type: string
      enum:
      - active
      - paused
      - inactive
      - stale
      - pending_refund
      - payment_failed
      - draft
      - in_review
      - flagged
      - importing
      - imported
      description: The status of an ad campaign.
    AdGroup:
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
        ad_campaign:
          type: object
          properties:
            id:
              type: string
              description: The unique identifier for the ad campaign.
              example: adcamp_xxxxxxxxxxx
            title:
              type: string
              description: The title of the ad campaign
            platform:
              oneOf:
              - "$ref": "#/components/schemas/AdCampaignPlatforms"
              - type: 'null'
              description: The external platform this campaign is running on (e.g.,
                meta, tiktok).
            status:
              "$ref": "#/components/schemas/AdCampaignStatuses"
              description: Current status of the campaign (active, paused, or inactive)
          required:
          - id
          - title
          - platform
          - status
          description: The parent ad campaign
      required:
      - id
      - name
      - status
      - daily_budget
      - created_at
      - updated_at
      - platform_config
      - config
      - ad_campaign
      description: An external ad group (ad set) belonging to an ad campaign
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
    MetaAdGroupPlatformConfigLeadConversionLocation:
      type: string
      enum:
      - website
      - instant_forms
      - messenger
      - instagram
      - calls
      - app
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
    TiktokActionScene:
      type: string
      enum:
      - VIDEO_RELATED
      - CREATOR_RELATED
      - HASHTAG_RELATED
      - LIVE_RELATED
      description: The category of TikTok content a behavioral targeting rule applies
        to. See docs/tiktok_api/ad_group.md § actions.
    TiktokAdGroupPlatformConfigAttributionEventCount:
      type: string
      enum:
      - UNSET
      - EVERY
      - ONCE
    TiktokAdGroupPlatformConfigAudienceType:
      type: string
      enum:
      - NORMAL
      - SMART_INTERESTS_BEHAVIORS
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
    TiktokAdGroupPlatformConfigBrandSafetyType:
      type: string
      enum:
      - NO_BRAND_SAFETY
      - STANDARD_INVENTORY
      - LIMITED_INVENTORY
      - FULL_INVENTORY
      - EXPANDED_INVENTORY
    TiktokAdGroupPlatformConfigBudgetMode:
      type: string
      enum:
      - BUDGET_MODE_DAY
      - BUDGET_MODE_TOTAL
      - BUDGET_MODE_DYNAMIC_DAILY_BUDGET
    TiktokAdGroupPlatformConfigClickAttributionWindow:
      type: string
      enum:
      - 'OFF'
      - ONE_DAY
      - SEVEN_DAYS
      - FOURTEEN_DAYS
      - TWENTY_EIGHT_DAYS
    TiktokAdGroupPlatformConfigDeepFunnelOptimizationStatus:
      type: string
      enum:
      - 'ON'
      - 'OFF'
    TiktokAdGroupPlatformConfigEngagedViewAttributionWindow:
      type: string
      enum:
      - 'OFF'
      - ONE_DAY
      - SEVEN_DAYS
    TiktokAdGroupPlatformConfigGender:
      type: string
      enum:
      - GENDER_UNLIMITED
      - GENDER_MALE
      - GENDER_FEMALE
    TiktokAdGroupPlatformConfigIos14Targeting:
      type: string
      enum:
      - UNSET
      - IOS14_MINUS
      - IOS14_PLUS
      - ALL
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
    TiktokAdGroupPlatformConfigProductSource:
      type: string
      enum:
      - CATALOG
      - STORE
      - SHOWCASE
    TiktokAdGroupPlatformConfigScheduleType:
      type: string
      enum:
      - SCHEDULE_START_END
      - SCHEDULE_FROM_NOW
    TiktokAdGroupPlatformConfigShoppingAdsRetargetingType:
      type: string
      enum:
      - 'OFF'
      - LAB1
      - LAB2
      - LAB3
      - LAB4
      - LAB5
    TiktokAdGroupPlatformConfigSpendingPower:
      type: string
      enum:
      - ALL
      - HIGH
    TiktokAdGroupPlatformConfigViewAttributionWindow:
      type: string
      enum:
      - 'OFF'
      - ONE_DAY
      - SEVEN_DAYS
    TiktokAgeGroup:
      type: string
      enum:
      - AGE_13_17
      - AGE_18_24
      - AGE_25_34
      - AGE_35_44
      - AGE_45_54
      - AGE_55_100
      description: Age groups targetable on TikTok. See docs/tiktok_api/ad_group.md
        § age_groups.
    TiktokOperatingSystem:
      type: string
      enum:
      - ANDROID
      - IOS
      description: Device operating systems targetable on TikTok. See docs/tiktok_api/ad_group.md
        § operating_systems.
    TiktokVideoUserAction:
      type: string
      enum:
      - WATCHED_TO_END
      - LIKED
      - COMMENTED
      - SHARED
      - FOLLOWED
      - PROFILE_VISITED
      description: Specific past video interactions used for behavioral targeting.
        See docs/tiktok_api/ad_group.md § actions.video_user_actions.
```
