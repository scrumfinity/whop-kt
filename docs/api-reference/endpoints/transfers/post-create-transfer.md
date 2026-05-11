# POST /transfers — Create transfer

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `POST`
- **Path:** `/transfers`
- **Operation ID:** `createTransfer`
- **Tags:** `Transfers`
- **Required bearer scopes:** `payout:transfer_funds`

## Description

Transfer funds between two ledger accounts, such as from a company balance to a user balance.

Required permissions:
 - `payout:transfer_funds`

## Parameters

_No path, query, header, or cookie parameters._

## Request body

Required: **yes**
Content types: `application/json`

### `application/json` request schema

```yaml
type: object
properties:
  amount:
    type: number
    description: The amount to transfer in the specified currency. For example, 25.00
      for $25.00 USD.
    example: 6.9
  currency:
    "$ref": "#/components/schemas/Currencies"
    description: The currency of the transfer amount, such as 'usd'.
  destination_id:
    type: string
    description: The identifier of the account receiving the funds. Accepts a user
      ID ('user_xxx'), company ID ('biz_xxx'), or ledger account ID ('ldgr_xxx').
  idempotence_key:
    type:
    - string
    - 'null'
    description: A unique key to prevent duplicate transfers. Use a UUID or similar
      unique string.
  metadata:
    type:
    - object
    - 'null'
    additionalProperties: true
    description: A JSON object of custom metadata to attach to the transfer for tracking
      purposes.
  notes:
    type:
    - string
    - 'null'
    description: A short note describing the transfer, up to 50 characters.
  origin_id:
    type: string
    description: The identifier of the account sending the funds. Accepts a user ID
      ('user_xxx'), company ID ('biz_xxx'), or ledger account ID ('ldgr_xxx').
required:
- amount
- currency
- destination_id
- origin_id
description: Parameters for TransferFunds
title: TransferFundsInputWithOriginId
```


## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `Transfer` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/Transfer"
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

const transfer = await client.transfers.create({
  amount: 6.9,
  currency: 'usd',
  destination_id: 'destination_id',
  origin_id: 'origin_id',
});

console.log(transfer.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
transfer = client.transfers.create(
    amount=6.9,
    currency="usd",
    destination_id="destination_id",
    origin_id="origin_id",
)
print(transfer.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

transfer = whop.transfers.create(
  amount: 6.9,
  currency: :usd,
  destination_id: "destination_id",
  origin_id: "origin_id"
)

puts(transfer)
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
  Transfer:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the credit transaction transfer.
        example: ctt_xxxxxxxxxxxxxx
      amount:
        type: number
        description: The transfer amount in the currency specified by the currency
          field. For example, 10.43 represents $10.43 USD.
        example: 6.9
      currency:
        "$ref": "#/components/schemas/Currencies"
        description: The currency in which this transfer amount is denominated.
      created_at:
        type: string
        format: date-time
        description: The datetime the credit transaction transfer was created.
        example: '2023-12-01T05:00:00.401Z'
      fee_amount:
        type:
        - number
        - 'null'
        description: The flat fee amount deducted from this transfer, in the transfer's
          currency. Null if no flat fee was applied.
        example: 6.9
      notes:
        type:
        - string
        - 'null'
        description: A free-text note attached to this transfer by the sender. Null
          if no note was provided.
        example: Monthly revenue share payout
      metadata:
        type:
        - object
        - 'null'
        additionalProperties: true
        description: Custom key-value pairs attached to this transfer. Maximum 50
          keys, 500 characters per key, 5000 characters per value.
      origin_ledger_account_id:
        type: string
        description: The unique identifier of the ledger account that sent the funds.
        example: ldgr_xxxxxxxxxxxxx
      destination_ledger_account_id:
        type: string
        description: The unique identifier of the ledger account receiving the funds.
        example: ldgr_xxxxxxxxxxxxx
      origin:
        type: object
        oneOf:
        - type:
          - object
          - 'null'
          properties:
            typename:
              type: string
              const: User
              description: The typename of this object
            id:
              type: string
              description: The unique identifier for the user.
              example: user_xxxxxxxxxxxxx
            name:
              type:
              - string
              - 'null'
              description: The user's display name shown on their public profile.
              example: John Doe
            username:
              type: string
              description: The user's unique username shown on their public profile.
              example: johndoe42
          required:
          - typename
          - id
          - name
          - username
          description: A user account on Whop. Contains profile information, identity
            details, and social connections.
          title: User
        - type:
          - object
          - 'null'
          properties:
            typename:
              type: string
              const: Company
              description: The typename of this object
            id:
              type: string
              description: The unique identifier for the company.
              example: biz_xxxxxxxxxxxxxx
            route:
              type: string
              description: The URL slug for the company's store page (e.g., 'pickaxe'
                in whop.com/pickaxe).
              example: pickaxe
            title:
              type: string
              description: The display name of the company shown to customers.
              example: Pickaxe
          required:
          - typename
          - id
          - route
          - title
          description: A company is a seller on Whop. Companies own products, manage
            members, and receive payouts.
          title: Company
        discriminator:
          propertyName: typename
        description: The entity that sent the transferred funds.
      destination:
        type: object
        oneOf:
        - type:
          - object
          - 'null'
          properties:
            typename:
              type: string
              const: User
              description: The typename of this object
            id:
              type: string
              description: The unique identifier for the user.
              example: user_xxxxxxxxxxxxx
            name:
              type:
              - string
              - 'null'
              description: The user's display name shown on their public profile.
              example: John Doe
            username:
              type: string
              description: The user's unique username shown on their public profile.
              example: johndoe42
          required:
          - typename
          - id
          - name
          - username
          description: A user account on Whop. Contains profile information, identity
            details, and social connections.
          title: User
        - type:
          - object
          - 'null'
          properties:
            typename:
              type: string
              const: Company
              description: The typename of this object
            id:
              type: string
              description: The unique identifier for the company.
              example: biz_xxxxxxxxxxxxxx
            route:
              type: string
              description: The URL slug for the company's store page (e.g., 'pickaxe'
                in whop.com/pickaxe).
              example: pickaxe
            title:
              type: string
              description: The display name of the company shown to customers.
              example: Pickaxe
          required:
          - typename
          - id
          - route
          - title
          description: A company is a seller on Whop. Companies own products, manage
            members, and receive payouts.
          title: Company
        discriminator:
          propertyName: typename
        description: The entity receiving the transferred funds.
    required:
    - id
    - amount
    - currency
    - created_at
    - fee_amount
    - notes
    - metadata
    - origin_ledger_account_id
    - destination_ledger_account_id
    - origin
    - destination
    description: A transfer of credit between two ledger accounts.
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
  "/transfers":
    post:
      tags:
      - Transfers
      operationId: createTransfer
      summary: Create transfer
      description: |-
        Transfer funds between two ledger accounts, such as from a company balance to a user balance.

        Required permissions:
         - `payout:transfer_funds`
      parameters: []
      x-group-title: Payouts
      security:
      - bearerAuth:
        - payout:transfer_funds
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/Transfer"
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
                amount:
                  type: number
                  description: The amount to transfer in the specified currency. For
                    example, 25.00 for $25.00 USD.
                  example: 6.9
                currency:
                  "$ref": "#/components/schemas/Currencies"
                  description: The currency of the transfer amount, such as 'usd'.
                destination_id:
                  type: string
                  description: The identifier of the account receiving the funds.
                    Accepts a user ID ('user_xxx'), company ID ('biz_xxx'), or ledger
                    account ID ('ldgr_xxx').
                idempotence_key:
                  type:
                  - string
                  - 'null'
                  description: A unique key to prevent duplicate transfers. Use a
                    UUID or similar unique string.
                metadata:
                  type:
                  - object
                  - 'null'
                  additionalProperties: true
                  description: A JSON object of custom metadata to attach to the transfer
                    for tracking purposes.
                notes:
                  type:
                  - string
                  - 'null'
                  description: A short note describing the transfer, up to 50 characters.
                origin_id:
                  type: string
                  description: The identifier of the account sending the funds. Accepts
                    a user ID ('user_xxx'), company ID ('biz_xxx'), or ledger account
                    ID ('ldgr_xxx').
              required:
              - amount
              - currency
              - destination_id
              - origin_id
              description: Parameters for TransferFunds
              title: TransferFundsInputWithOriginId
      x-codeSamples:
      - lang: JavaScript
        source: |-
          import Whop from '@whop/sdk';

          const client = new Whop({
            apiKey: process.env['WHOP_API_KEY'], // This is the default and can be omitted
          });

          const transfer = await client.transfers.create({
            amount: 6.9,
            currency: 'usd',
            destination_id: 'destination_id',
            origin_id: 'origin_id',
          });

          console.log(transfer.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          transfer = client.transfers.create(
              amount=6.9,
              currency="usd",
              destination_id="destination_id",
              origin_id="origin_id",
          )
          print(transfer.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          transfer = whop.transfers.create(
            amount: 6.9,
            currency: :usd,
            destination_id: "destination_id",
            origin_id: "origin_id"
          )

          puts(transfer)
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
    Transfer:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the credit transaction transfer.
          example: ctt_xxxxxxxxxxxxxx
        amount:
          type: number
          description: The transfer amount in the currency specified by the currency
            field. For example, 10.43 represents $10.43 USD.
          example: 6.9
        currency:
          "$ref": "#/components/schemas/Currencies"
          description: The currency in which this transfer amount is denominated.
        created_at:
          type: string
          format: date-time
          description: The datetime the credit transaction transfer was created.
          example: '2023-12-01T05:00:00.401Z'
        fee_amount:
          type:
          - number
          - 'null'
          description: The flat fee amount deducted from this transfer, in the transfer's
            currency. Null if no flat fee was applied.
          example: 6.9
        notes:
          type:
          - string
          - 'null'
          description: A free-text note attached to this transfer by the sender. Null
            if no note was provided.
          example: Monthly revenue share payout
        metadata:
          type:
          - object
          - 'null'
          additionalProperties: true
          description: Custom key-value pairs attached to this transfer. Maximum 50
            keys, 500 characters per key, 5000 characters per value.
        origin_ledger_account_id:
          type: string
          description: The unique identifier of the ledger account that sent the funds.
          example: ldgr_xxxxxxxxxxxxx
        destination_ledger_account_id:
          type: string
          description: The unique identifier of the ledger account receiving the funds.
          example: ldgr_xxxxxxxxxxxxx
        origin:
          type: object
          oneOf:
          - type:
            - object
            - 'null'
            properties:
              typename:
                type: string
                const: User
                description: The typename of this object
              id:
                type: string
                description: The unique identifier for the user.
                example: user_xxxxxxxxxxxxx
              name:
                type:
                - string
                - 'null'
                description: The user's display name shown on their public profile.
                example: John Doe
              username:
                type: string
                description: The user's unique username shown on their public profile.
                example: johndoe42
            required:
            - typename
            - id
            - name
            - username
            description: A user account on Whop. Contains profile information, identity
              details, and social connections.
            title: User
          - type:
            - object
            - 'null'
            properties:
              typename:
                type: string
                const: Company
                description: The typename of this object
              id:
                type: string
                description: The unique identifier for the company.
                example: biz_xxxxxxxxxxxxxx
              route:
                type: string
                description: The URL slug for the company's store page (e.g., 'pickaxe'
                  in whop.com/pickaxe).
                example: pickaxe
              title:
                type: string
                description: The display name of the company shown to customers.
                example: Pickaxe
            required:
            - typename
            - id
            - route
            - title
            description: A company is a seller on Whop. Companies own products, manage
              members, and receive payouts.
            title: Company
          discriminator:
            propertyName: typename
          description: The entity that sent the transferred funds.
        destination:
          type: object
          oneOf:
          - type:
            - object
            - 'null'
            properties:
              typename:
                type: string
                const: User
                description: The typename of this object
              id:
                type: string
                description: The unique identifier for the user.
                example: user_xxxxxxxxxxxxx
              name:
                type:
                - string
                - 'null'
                description: The user's display name shown on their public profile.
                example: John Doe
              username:
                type: string
                description: The user's unique username shown on their public profile.
                example: johndoe42
            required:
            - typename
            - id
            - name
            - username
            description: A user account on Whop. Contains profile information, identity
              details, and social connections.
            title: User
          - type:
            - object
            - 'null'
            properties:
              typename:
                type: string
                const: Company
                description: The typename of this object
              id:
                type: string
                description: The unique identifier for the company.
                example: biz_xxxxxxxxxxxxxx
              route:
                type: string
                description: The URL slug for the company's store page (e.g., 'pickaxe'
                  in whop.com/pickaxe).
                example: pickaxe
              title:
                type: string
                description: The display name of the company shown to customers.
                example: Pickaxe
            required:
            - typename
            - id
            - route
            - title
            description: A company is a seller on Whop. Companies own products, manage
              members, and receive payouts.
            title: Company
          discriminator:
            propertyName: typename
          description: The entity receiving the transferred funds.
      required:
      - id
      - amount
      - currency
      - created_at
      - fee_amount
      - notes
      - metadata
      - origin_ledger_account_id
      - destination_ledger_account_id
      - origin
      - destination
      description: A transfer of credit between two ledger accounts.
```
