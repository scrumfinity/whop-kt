# GET /chat_channels/{id} — Retrieve chat channel

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/chat_channels/{id}`
- **Operation ID:** `retrieveChatChannel`
- **Tags:** `Chat channels`
- **Required bearer scopes:** `chat:read`

## Description

Retrieves the details of an existing chat channel.

Required permissions:
 - `chat:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `id` | path | yes | `string` | The unique identifier of the chat channel or experience to retrieve. | exp_xxxxxxxxxxxxxx |

## Request body

_No request body._

## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `ChatChannel` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/ChatChannel"
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

const chatChannel = await client.chatChannels.retrieve('exp_xxxxxxxxxxxxxx');

console.log(chatChannel.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
chat_channel = client.chat_channels.retrieve(
    "exp_xxxxxxxxxxxxxx",
)
print(chat_channel.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

chat_channel = whop.chat_channels.retrieve("exp_xxxxxxxxxxxxxx")

puts(chat_channel)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  ChatChannel:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the entity
      experience:
        type: object
        properties:
          id:
            type: string
            description: The unique identifier for the experience.
            example: exp_xxxxxxxxxxxxxx
          name:
            type: string
            description: The display name of this experience shown to users in the
              product navigation. Maximum 255 characters.
            example: Trading Signals Chat
        required:
        - id
        - name
        description: The experience this chat feed is attached to.
      ban_media:
        type: boolean
        description: Whether media uploads such as images and videos are blocked in
          this chat.
      ban_urls:
        type: boolean
        description: Whether URL links are blocked from being posted in this chat.
      who_can_post:
        "$ref": "#/components/schemas/WhoCanPostTypes"
        description: The permission level controlling which users can send messages
          in this chat.
      who_can_react:
        "$ref": "#/components/schemas/WhoCanReactTypes"
        description: The permission level controlling which users can add reactions
          in this chat.
      user_posts_cooldown_seconds:
        type:
        - integer
        - 'null'
        description: The minimum number of seconds a user must wait between consecutive
          messages. Null if no cooldown is enforced.
        example: 42
      banned_words:
        type: array
        items:
          type: string
          description: Represents textual data as UTF-8 character sequences. This
            type is most often used by GraphQL to represent free-form human-readable
            text.
        description: A list of words that are automatically filtered from messages
          in this chat.
    required:
    - id
    - experience
    - ban_media
    - ban_urls
    - who_can_post
    - who_can_react
    - user_posts_cooldown_seconds
    - banned_words
    description: A real-time chat feed attached to an experience, with configurable
      moderation and posting permissions.
  WhoCanPostTypes:
    type: string
    enum:
    - everyone
    - admins
    description: Who can post on a chat feed
  WhoCanReactTypes:
    type: string
    enum:
    - everyone
    - no_one
    description: Who can react on a chat feed
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
  "/chat_channels/{id}":
    get:
      tags:
      - Chat channels
      operationId: retrieveChatChannel
      summary: Retrieve chat channel
      description: |-
        Retrieves the details of an existing chat channel.

        Required permissions:
         - `chat:read`
      parameters:
      - name: id
        in: path
        required: true
        description: The unique identifier of the chat channel or experience to retrieve.
        schema:
          type: string
          example: exp_xxxxxxxxxxxxxx
      x-group-title: Engagement
      security:
      - bearerAuth:
        - chat:read
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/ChatChannel"
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

          const chatChannel = await client.chatChannels.retrieve('exp_xxxxxxxxxxxxxx');

          console.log(chatChannel.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          chat_channel = client.chat_channels.retrieve(
              "exp_xxxxxxxxxxxxxx",
          )
          print(chat_channel.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          chat_channel = whop.chat_channels.retrieve("exp_xxxxxxxxxxxxxx")

          puts(chat_channel)
components:
  schemas:
    ChatChannel:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the entity
        experience:
          type: object
          properties:
            id:
              type: string
              description: The unique identifier for the experience.
              example: exp_xxxxxxxxxxxxxx
            name:
              type: string
              description: The display name of this experience shown to users in the
                product navigation. Maximum 255 characters.
              example: Trading Signals Chat
          required:
          - id
          - name
          description: The experience this chat feed is attached to.
        ban_media:
          type: boolean
          description: Whether media uploads such as images and videos are blocked
            in this chat.
        ban_urls:
          type: boolean
          description: Whether URL links are blocked from being posted in this chat.
        who_can_post:
          "$ref": "#/components/schemas/WhoCanPostTypes"
          description: The permission level controlling which users can send messages
            in this chat.
        who_can_react:
          "$ref": "#/components/schemas/WhoCanReactTypes"
          description: The permission level controlling which users can add reactions
            in this chat.
        user_posts_cooldown_seconds:
          type:
          - integer
          - 'null'
          description: The minimum number of seconds a user must wait between consecutive
            messages. Null if no cooldown is enforced.
          example: 42
        banned_words:
          type: array
          items:
            type: string
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          description: A list of words that are automatically filtered from messages
            in this chat.
      required:
      - id
      - experience
      - ban_media
      - ban_urls
      - who_can_post
      - who_can_react
      - user_posts_cooldown_seconds
      - banned_words
      description: A real-time chat feed attached to an experience, with configurable
        moderation and posting permissions.
    WhoCanPostTypes:
      type: string
      enum:
      - everyone
      - admins
      description: Who can post on a chat feed
    WhoCanReactTypes:
      type: string
      enum:
      - everyone
      - no_one
      description: Who can react on a chat feed
```
