# POST /dm_members — Create dm member

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `POST`
- **Path:** `/dm_members`
- **Operation ID:** `createDmMember`
- **Tags:** `Dm members`
- **Required bearer scopes:** `dms:channel:manage`

## Description

Add a new user to an existing DM channel. Only an admin of the channel can add members.

Required permissions:
 - `dms:channel:manage`

## Parameters

_No path, query, header, or cookie parameters._

## Request body

Required: **yes**
Content types: `application/json`

### `application/json` request schema

```yaml
type: object
properties:
  channel_id:
    type: string
    description: The unique identifier of the DM channel to add the new member to.
  user_id:
    type: string
    description: The unique identifier of the user to add to the DM channel. For example,
      'user_xxxxx'.
    example: user_xxxxxxxxxxxxx
required:
- channel_id
- user_id
description: Parameters for CreateDmMember
```


## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `DmMember` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/DmMember"
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

const dmMember = await client.dmMembers.create({
  channel_id: 'channel_id',
  user_id: 'user_xxxxxxxxxxxxx',
});

console.log(dmMember.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
dm_member = client.dm_members.create(
    channel_id="channel_id",
    user_id="user_xxxxxxxxxxxxx",
)
print(dm_member.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

dm_member = whop.dm_members.create(channel_id: "channel_id", user_id: "user_xxxxxxxxxxxxx")

puts(dm_member)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  DmMember:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the entity
      user_id:
        type: string
        description: The unique identifier of the user who holds this channel membership.
      channel_id:
        type: string
        description: The unique identifier of the messaging channel this membership
          belongs to.
      status:
        "$ref": "#/components/schemas/DmsFeedMemberStatuses"
        description: 'The current state of this membership: requested, accepted, hidden,
          closed, or archived.'
      last_viewed_at:
        type:
        - string
        - 'null'
        description: The timestamp when this member last viewed the channel, as milliseconds since the Unix epoch. Null if the member has never viewed the
          channel.
        example: '1727606400000'
      notification_preference:
        "$ref": "#/components/schemas/DmsFeedMemberNotificationPreferences"
        description: 'The notification level for this channel: all, mentions, or none.'
    required:
    - id
    - user_id
    - channel_id
    - status
    - last_viewed_at
    - notification_preference
    description: A user's membership record in a messaging channel, including notification
      preferences and read state.
  DmsFeedMemberNotificationPreferences:
    type: string
    enum:
    - all
    - mentions
    - none
    description: The notification preferences for a DMs feed member
  DmsFeedMemberStatuses:
    type: string
    enum:
    - requested
    - accepted
    - hidden
    - closed
    - archived
    description: " The statuses of a DMs feed member"
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
  "/dm_members":
    post:
      tags:
      - Dm members
      operationId: createDmMember
      summary: Create dm member
      description: |-
        Add a new user to an existing DM channel. Only an admin of the channel can add members.

        Required permissions:
         - `dms:channel:manage`
      parameters: []
      x-group-title: Engagement
      security:
      - bearerAuth:
        - dms:channel:manage
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/DmMember"
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
                channel_id:
                  type: string
                  description: The unique identifier of the DM channel to add the
                    new member to.
                user_id:
                  type: string
                  description: The unique identifier of the user to add to the DM
                    channel. For example, 'user_xxxxx'.
                  example: user_xxxxxxxxxxxxx
              required:
              - channel_id
              - user_id
              description: Parameters for CreateDmMember
      x-codeSamples:
      - lang: JavaScript
        source: |-
          import Whop from '@whop/sdk';

          const client = new Whop({
            apiKey: process.env['WHOP_API_KEY'], // This is the default and can be omitted
          });

          const dmMember = await client.dmMembers.create({
            channel_id: 'channel_id',
            user_id: 'user_xxxxxxxxxxxxx',
          });

          console.log(dmMember.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          dm_member = client.dm_members.create(
              channel_id="channel_id",
              user_id="user_xxxxxxxxxxxxx",
          )
          print(dm_member.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          dm_member = whop.dm_members.create(channel_id: "channel_id", user_id: "user_xxxxxxxxxxxxx")

          puts(dm_member)
components:
  schemas:
    DmMember:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the entity
        user_id:
          type: string
          description: The unique identifier of the user who holds this channel membership.
        channel_id:
          type: string
          description: The unique identifier of the messaging channel this membership
            belongs to.
        status:
          "$ref": "#/components/schemas/DmsFeedMemberStatuses"
          description: 'The current state of this membership: requested, accepted,
            hidden, closed, or archived.'
        last_viewed_at:
          type:
          - string
          - 'null'
          description: The timestamp when this member last viewed the channel, as milliseconds since the Unix epoch. Null if the member has never viewed
            the channel.
          example: '1727606400000'
        notification_preference:
          "$ref": "#/components/schemas/DmsFeedMemberNotificationPreferences"
          description: 'The notification level for this channel: all, mentions, or
            none.'
      required:
      - id
      - user_id
      - channel_id
      - status
      - last_viewed_at
      - notification_preference
      description: A user's membership record in a messaging channel, including notification
        preferences and read state.
    DmsFeedMemberNotificationPreferences:
      type: string
      enum:
      - all
      - mentions
      - none
      description: The notification preferences for a DMs feed member
    DmsFeedMemberStatuses:
      type: string
      enum:
      - requested
      - accepted
      - hidden
      - closed
      - archived
      description: " The statuses of a DMs feed member"
```
