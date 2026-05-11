# GET /ai_chats/{id} — Retrieve ai chat

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/ai_chats/{id}`
- **Operation ID:** `retrieveAiChat`
- **Tags:** `Ai chats`
- **Required bearer scopes:** _None documented_

## Description

Retrieves the details of an existing AI chat.

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `id` | path | yes | `string` | The unique identifier of the AI chat to retrieve. | aich_xxxxxxxxxxxxx |

## Request body

_No request body._

## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `AiChat` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/AiChat"
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

const aiChat = await client.aiChats.retrieve('aich_xxxxxxxxxxxxx');

console.log(aiChat.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
ai_chat = client.ai_chats.retrieve(
    "aich_xxxxxxxxxxxxx",
)
print(ai_chat.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

ai_chat = whop.ai_chats.retrieve("aich_xxxxxxxxxxxxx")

puts(ai_chat)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  AiChat:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the ai chat.
        example: aich_xxxxxxxxxxxxx
      title:
        type:
        - string
        - 'null'
        description: A short descriptive title for this AI chat conversation. Null
          if no title has been set.
        example: Weekly Revenue Analysis
      notification_preference:
        "$ref": "#/components/schemas/AiChatNotificationPreferences"
        description: The notification preference for this AI chat. `all` delivers
          AI chat notifications and badges, while `none` mutes them.
      message_count:
        type: integer
        description: The total number of messages exchanged in this conversation.
        example: 42
      blended_token_usage:
        type: string
        description: The total number of tokens consumed across all messages in this
          conversation.
        example: '1727606400000'
      created_at:
        type: string
        format: date-time
        description: The datetime the ai chat was created.
        example: '2023-12-01T05:00:00.401Z'
      updated_at:
        type: string
        format: date-time
        description: The datetime the ai chat was last updated.
        example: '2023-12-01T05:00:00.401Z'
      last_message_at:
        type:
        - string
        - 'null'
        format: date-time
        description: The timestamp of the most recent message in this conversation.
          Null if no messages have been sent yet.
        example: '2023-12-01T05:00:00.401Z'
      user:
        type: object
        properties:
          id:
            type: string
            description: The unique identifier for the user.
            example: user_xxxxxxxxxxxxx
        required:
        - id
        description: The user who owns this AI chat conversation.
    required:
    - id
    - title
    - notification_preference
    - message_count
    - blended_token_usage
    - created_at
    - updated_at
    - last_message_at
    - user
    description: An AI-powered chat conversation belonging to a user, with optional
      scheduled automation.
  AiChatNotificationPreferences:
    type: string
    enum:
    - all
    - none
    description: The notification preference for an AI chat
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
  "/ai_chats/{id}":
    get:
      tags:
      - Ai chats
      operationId: retrieveAiChat
      summary: Retrieve ai chat
      description: Retrieves the details of an existing AI chat.
      parameters:
      - name: id
        in: path
        required: true
        description: The unique identifier of the AI chat to retrieve.
        schema:
          type: string
          example: aich_xxxxxxxxxxxxx
      x-group-title: AI
      security:
      - bearerAuth: []
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/AiChat"
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

          const aiChat = await client.aiChats.retrieve('aich_xxxxxxxxxxxxx');

          console.log(aiChat.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          ai_chat = client.ai_chats.retrieve(
              "aich_xxxxxxxxxxxxx",
          )
          print(ai_chat.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          ai_chat = whop.ai_chats.retrieve("aich_xxxxxxxxxxxxx")

          puts(ai_chat)
components:
  schemas:
    AiChat:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the ai chat.
          example: aich_xxxxxxxxxxxxx
        title:
          type:
          - string
          - 'null'
          description: A short descriptive title for this AI chat conversation. Null
            if no title has been set.
          example: Weekly Revenue Analysis
        notification_preference:
          "$ref": "#/components/schemas/AiChatNotificationPreferences"
          description: The notification preference for this AI chat. `all` delivers
            AI chat notifications and badges, while `none` mutes them.
        message_count:
          type: integer
          description: The total number of messages exchanged in this conversation.
          example: 42
        blended_token_usage:
          type: string
          description: The total number of tokens consumed across all messages in
            this conversation.
          example: '1727606400000'
        created_at:
          type: string
          format: date-time
          description: The datetime the ai chat was created.
          example: '2023-12-01T05:00:00.401Z'
        updated_at:
          type: string
          format: date-time
          description: The datetime the ai chat was last updated.
          example: '2023-12-01T05:00:00.401Z'
        last_message_at:
          type:
          - string
          - 'null'
          format: date-time
          description: The timestamp of the most recent message in this conversation.
            Null if no messages have been sent yet.
          example: '2023-12-01T05:00:00.401Z'
        user:
          type: object
          properties:
            id:
              type: string
              description: The unique identifier for the user.
              example: user_xxxxxxxxxxxxx
          required:
          - id
          description: The user who owns this AI chat conversation.
      required:
      - id
      - title
      - notification_preference
      - message_count
      - blended_token_usage
      - created_at
      - updated_at
      - last_message_at
      - user
      description: An AI-powered chat conversation belonging to a user, with optional
        scheduled automation.
    AiChatNotificationPreferences:
      type: string
      enum:
      - all
      - none
      description: The notification preference for an AI chat
```
