# GET /shipments — List shipments

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/shipments`
- **Operation ID:** `listShipment`
- **Tags:** `Shipments`
- **Required bearer scopes:** `shipment:basic:read`, `payment:basic:read`

## Description

Returns a paginated list of shipments, with optional filtering by payment, company, or user.

Required permissions:
 - `shipment:basic:read`
 - `payment:basic:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `after` | query | no | `string \| null` | Returns the elements in the list that come after the specified cursor. |  |
| `before` | query | no | `string \| null` | Returns the elements in the list that come before the specified cursor. |  |
| `first` | query | no | `integer \| null` | Returns the first _n_ elements from the list. | 42 |
| `last` | query | no | `integer \| null` | Returns the last _n_ elements from the list. | 42 |
| `payment_id` | query | no | `string \| null` | Filter shipments to only those associated with this specific payment. | pay_xxxxxxxxxxxxxx |
| `company_id` | query | no | `string \| null` | Filter shipments to only those belonging to this company. | biz_xxxxxxxxxxxxxx |
| `user_id` | query | no | `string \| null` | Filter shipments to only those for this specific user. | user_xxxxxxxxxxxxx |

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
      "$ref": "#/components/schemas/ShipmentListItem"
    description: A list of nodes.
  page_info:
    "$ref": "#/components/schemas/PageInfo"
    description: Information to aid in pagination.
required:
- data
- page_info
description: The connection type for Shipment.
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
for await (const shipmentListResponse of client.shipments.list()) {
  console.log(shipmentListResponse.id);
}
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
page = client.shipments.list()
page = page.data[0]
print(page.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

page = whop.shipments.list

puts(page)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
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
  ShipmentCarriers:
    type: string
    enum:
    - accurate
    - amazon_mws
    - amazon_shipping
    - apc
    - asendia_usa
    - australia_post
    - axlehire_v3
    - better_trucks
    - canada_post
    - canpar
    - columbus_last_mile
    - chronopost
    - cloud_sort
    - courier_express
    - couriers_please
    - cs_logistics
    - dai_post
    - deutsche_post_uk
    - deutsche_post
    - dhl_ecommerce_asia
    - dhl_ecs
    - dhl_express
    - dhl_paket
    - door_dash
    - dpd_nl
    - dpd_uk
    - dpd
    - epost_global
    - estafeta
    - evri
    - fastway
    - fedex_cross_border
    - fedex_default
    - fedex_mailview
    - fedex_smartpost
    - fedex
    - first_choice
    - first_mile
    - flexport
    - gio
    - gio_express
    - gso
    - hailify
    - henry
    - interlink_express
    - jet
    - kuroneko_yamato
    - la_post
    - lasership_v2
    - loomis_express
    - lso
    - ontrac
    - optima
    - osm_worldwide
    - parcelforce
    - parcll
    - passport_global
    - post_nl
    - purolator
    - quick
    - royal_mail
    - omni_parcel
    - sendle
    - sf_express
    - smart_kargo
    - sonic
    - spee_dee
    - swyft
    - tforce
    - uds
    - ups_iparcel
    - ups_mail_innovations
    - ups
    - usps
    - veho
    - yanwen
    description: The carrier of a shipment
  ShipmentListItem:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the shipment.
        example: ship_xxxxxxxxxxxxx
      created_at:
        type: string
        format: date-time
        description: The datetime the shipment was created.
        example: '2023-12-01T05:00:00.401Z'
      status:
        "$ref": "#/components/schemas/ShipmentStatuses"
        description: The current delivery status of this shipment.
      substatus:
        oneOf:
        - "$ref": "#/components/schemas/ShipmentSubstatuses"
        - type: 'null'
        description: A more granular status providing additional detail about the
          shipment's current state. Null if no substatus applies.
      tracking_code:
        type: string
        description: The carrier-assigned tracking number used to look up shipment
          progress.
        example: '9400111899223456789012'
      updated_at:
        type: string
        format: date-time
        description: The datetime the shipment was last updated.
        example: '2023-12-01T05:00:00.401Z'
      carrier:
        "$ref": "#/components/schemas/ShipmentCarriers"
        description: The shipping carrier responsible for delivering this shipment.
      service:
        type:
        - string
        - 'null'
        description: The shipping service level used for this shipment. Null if the
          carrier does not specify a service tier.
        example: Priority
      delivery_estimate:
        type:
        - string
        - 'null'
        format: date-time
        description: The estimated delivery date for this shipment. Null if the carrier
          has not provided an estimate.
        example: '2023-12-01T05:00:00.401Z'
      payment:
        type:
        - object
        - 'null'
        properties:
          id:
            type: string
            description: The unique identifier for the payment.
            example: pay_xxxxxxxxxxxxxx
        required:
        - id
        description: The payment associated with this shipment. Null if the payment
          has been deleted or is inaccessible.
    required:
    - id
    - created_at
    - status
    - substatus
    - tracking_code
    - updated_at
    - carrier
    - service
    - delivery_estimate
    - payment
    description: A physical shipment associated with a payment, including carrier
      details and tracking information.
  ShipmentStatuses:
    type: string
    enum:
    - unknown
    - pre_transit
    - in_transit
    - out_for_delivery
    - delivered
    - available_for_pickup
    - return_to_sender
    - failure
    - cancelled
    - error
    description: The status of a shipment
  ShipmentSubstatuses:
    type: string
    enum:
    - address_correction
    - arrived_at_destination
    - arrived_at_facility
    - arrived_at_pickup_location
    - awaiting_information
    - substatus_cancelled
    - damaged
    - delayed
    - delivery_exception
    - departed_facility
    - departed_origin_facility
    - expired
    - substatus_failure
    - held
    - substatus_in_transit
    - label_created
    - lost
    - missorted
    - substatus_out_for_delivery
    - received_at_destination_facility
    - received_at_origin_facility
    - refused
    - return
    - status_update
    - transferred_to_destination_carrier
    - transit_exception
    - substatus_unknown
    - weather_delay
    description: The substatus of a shipment
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
  "/shipments":
    get:
      tags:
      - Shipments
      operationId: listShipment
      summary: List shipments
      description: |-
        Returns a paginated list of shipments, with optional filtering by payment, company, or user.

        Required permissions:
         - `shipment:basic:read`
         - `payment:basic:read`
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
      - name: payment_id
        in: query
        required: false
        schema:
          type:
          - string
          - 'null'
          description: Filter shipments to only those associated with this specific
            payment.
          example: pay_xxxxxxxxxxxxxx
        explode: true
        style: form
      - name: company_id
        in: query
        required: false
        schema:
          type:
          - string
          - 'null'
          description: Filter shipments to only those belonging to this company.
          example: biz_xxxxxxxxxxxxxx
        explode: true
        style: form
      - name: user_id
        in: query
        required: false
        schema:
          type:
          - string
          - 'null'
          description: Filter shipments to only those for this specific user.
          example: user_xxxxxxxxxxxxx
        explode: true
        style: form
      x-group-title: CRM
      security:
      - bearerAuth:
        - shipment:basic:read
        - payment:basic:read
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
                      "$ref": "#/components/schemas/ShipmentListItem"
                    description: A list of nodes.
                  page_info:
                    "$ref": "#/components/schemas/PageInfo"
                    description: Information to aid in pagination.
                required:
                - data
                - page_info
                description: The connection type for Shipment.
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
          for await (const shipmentListResponse of client.shipments.list()) {
            console.log(shipmentListResponse.id);
          }
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          page = client.shipments.list()
          page = page.data[0]
          print(page.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          page = whop.shipments.list

          puts(page)
components:
  schemas:
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
    ShipmentCarriers:
      type: string
      enum:
      - accurate
      - amazon_mws
      - amazon_shipping
      - apc
      - asendia_usa
      - australia_post
      - axlehire_v3
      - better_trucks
      - canada_post
      - canpar
      - columbus_last_mile
      - chronopost
      - cloud_sort
      - courier_express
      - couriers_please
      - cs_logistics
      - dai_post
      - deutsche_post_uk
      - deutsche_post
      - dhl_ecommerce_asia
      - dhl_ecs
      - dhl_express
      - dhl_paket
      - door_dash
      - dpd_nl
      - dpd_uk
      - dpd
      - epost_global
      - estafeta
      - evri
      - fastway
      - fedex_cross_border
      - fedex_default
      - fedex_mailview
      - fedex_smartpost
      - fedex
      - first_choice
      - first_mile
      - flexport
      - gio
      - gio_express
      - gso
      - hailify
      - henry
      - interlink_express
      - jet
      - kuroneko_yamato
      - la_post
      - lasership_v2
      - loomis_express
      - lso
      - ontrac
      - optima
      - osm_worldwide
      - parcelforce
      - parcll
      - passport_global
      - post_nl
      - purolator
      - quick
      - royal_mail
      - omni_parcel
      - sendle
      - sf_express
      - smart_kargo
      - sonic
      - spee_dee
      - swyft
      - tforce
      - uds
      - ups_iparcel
      - ups_mail_innovations
      - ups
      - usps
      - veho
      - yanwen
      description: The carrier of a shipment
    ShipmentListItem:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the shipment.
          example: ship_xxxxxxxxxxxxx
        created_at:
          type: string
          format: date-time
          description: The datetime the shipment was created.
          example: '2023-12-01T05:00:00.401Z'
        status:
          "$ref": "#/components/schemas/ShipmentStatuses"
          description: The current delivery status of this shipment.
        substatus:
          oneOf:
          - "$ref": "#/components/schemas/ShipmentSubstatuses"
          - type: 'null'
          description: A more granular status providing additional detail about the
            shipment's current state. Null if no substatus applies.
        tracking_code:
          type: string
          description: The carrier-assigned tracking number used to look up shipment
            progress.
          example: '9400111899223456789012'
        updated_at:
          type: string
          format: date-time
          description: The datetime the shipment was last updated.
          example: '2023-12-01T05:00:00.401Z'
        carrier:
          "$ref": "#/components/schemas/ShipmentCarriers"
          description: The shipping carrier responsible for delivering this shipment.
        service:
          type:
          - string
          - 'null'
          description: The shipping service level used for this shipment. Null if
            the carrier does not specify a service tier.
          example: Priority
        delivery_estimate:
          type:
          - string
          - 'null'
          format: date-time
          description: The estimated delivery date for this shipment. Null if the
            carrier has not provided an estimate.
          example: '2023-12-01T05:00:00.401Z'
        payment:
          type:
          - object
          - 'null'
          properties:
            id:
              type: string
              description: The unique identifier for the payment.
              example: pay_xxxxxxxxxxxxxx
          required:
          - id
          description: The payment associated with this shipment. Null if the payment
            has been deleted or is inaccessible.
      required:
      - id
      - created_at
      - status
      - substatus
      - tracking_code
      - updated_at
      - carrier
      - service
      - delivery_estimate
      - payment
      description: A physical shipment associated with a payment, including carrier
        details and tracking information.
    ShipmentStatuses:
      type: string
      enum:
      - unknown
      - pre_transit
      - in_transit
      - out_for_delivery
      - delivered
      - available_for_pickup
      - return_to_sender
      - failure
      - cancelled
      - error
      description: The status of a shipment
    ShipmentSubstatuses:
      type: string
      enum:
      - address_correction
      - arrived_at_destination
      - arrived_at_facility
      - arrived_at_pickup_location
      - awaiting_information
      - substatus_cancelled
      - damaged
      - delayed
      - delivery_exception
      - departed_facility
      - departed_origin_facility
      - expired
      - substatus_failure
      - held
      - substatus_in_transit
      - label_created
      - lost
      - missorted
      - substatus_out_for_delivery
      - received_at_destination_facility
      - received_at_origin_facility
      - refused
      - return
      - status_update
      - transferred_to_destination_carrier
      - transit_exception
      - substatus_unknown
      - weather_delay
      description: The substatus of a shipment
```
