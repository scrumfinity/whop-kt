# POST /shipments — Create shipment

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `POST`
- **Path:** `/shipments`
- **Operation ID:** `createShipment`
- **Tags:** `Shipments`
- **Required bearer scopes:** `shipment:create`, `payment:basic:read`

## Description

Create a new shipment with a tracking code for a specific payment within a company.

Required permissions:
 - `shipment:create`
 - `payment:basic:read`

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
    description: The unique identifier of the company to create the shipment for,
      starting with 'biz_'.
    example: biz_xxxxxxxxxxxxxx
  payment_id:
    type: string
    description: The unique identifier of the payment to associate the shipment with.
    example: pay_xxxxxxxxxxxxxx
  tracking_code:
    type: string
    description: The carrier tracking code for the shipment, such as a USPS, UPS,
      or FedEx tracking number.
required:
- company_id
- payment_id
- tracking_code
description: Parameters for CreateShipment
```


## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `Shipment` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/Shipment"
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

const shipment = await client.shipments.create({
  company_id: 'biz_xxxxxxxxxxxxxx',
  payment_id: 'pay_xxxxxxxxxxxxxx',
  tracking_code: 'tracking_code',
});

console.log(shipment.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
shipment = client.shipments.create(
    company_id="biz_xxxxxxxxxxxxxx",
    payment_id="pay_xxxxxxxxxxxxxx",
    tracking_code="tracking_code",
)
print(shipment.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

shipment = whop.shipments.create(
  company_id: "biz_xxxxxxxxxxxxxx",
  payment_id: "pay_xxxxxxxxxxxxxx",
  tracking_code: "tracking_code"
)

puts(shipment)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  Shipment:
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
    post:
      tags:
      - Shipments
      operationId: createShipment
      summary: Create shipment
      description: |-
        Create a new shipment with a tracking code for a specific payment within a company.

        Required permissions:
         - `shipment:create`
         - `payment:basic:read`
      parameters: []
      x-group-title: CRM
      security:
      - bearerAuth:
        - shipment:create
        - payment:basic:read
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/Shipment"
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
                  description: The unique identifier of the company to create the
                    shipment for, starting with 'biz_'.
                  example: biz_xxxxxxxxxxxxxx
                payment_id:
                  type: string
                  description: The unique identifier of the payment to associate the
                    shipment with.
                  example: pay_xxxxxxxxxxxxxx
                tracking_code:
                  type: string
                  description: The carrier tracking code for the shipment, such as
                    a USPS, UPS, or FedEx tracking number.
              required:
              - company_id
              - payment_id
              - tracking_code
              description: Parameters for CreateShipment
      x-codeSamples:
      - lang: JavaScript
        source: |-
          import Whop from '@whop/sdk';

          const client = new Whop({
            apiKey: process.env['WHOP_API_KEY'], // This is the default and can be omitted
          });

          const shipment = await client.shipments.create({
            company_id: 'biz_xxxxxxxxxxxxxx',
            payment_id: 'pay_xxxxxxxxxxxxxx',
            tracking_code: 'tracking_code',
          });

          console.log(shipment.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          shipment = client.shipments.create(
              company_id="biz_xxxxxxxxxxxxxx",
              payment_id="pay_xxxxxxxxxxxxxx",
              tracking_code="tracking_code",
          )
          print(shipment.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          shipment = whop.shipments.create(
            company_id: "biz_xxxxxxxxxxxxxx",
            payment_id: "pay_xxxxxxxxxxxxxx",
            tracking_code: "tracking_code"
          )

          puts(shipment)
components:
  schemas:
    Shipment:
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
