# POST /ai_chats — Create ai chat

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `POST`
- **Path:** `/ai_chats`
- **Operation ID:** `createAiChat`
- **Tags:** `Ai chats`
- **Required bearer scopes:** `ai_chat:create`

## Description

Create a new AI chat thread and send the first message to the AI agent.

Required permissions:
 - `ai_chat:create`

## Parameters

_No path, query, header, or cookie parameters._

## Request body

Required: **yes**
Content types: `application/json`

### `application/json` request schema

```yaml
type: object
properties:
  current_company_id:
    type:
    - string
    - 'null'
    description: The unique identifier of the company to set as context for the AI
      chat (e.g., "biz_XXXXX").
  message_attachments:
    type:
    - array
    - 'null'
    items:
      type: object
      properties:
        id:
          type: string
          description: The ID of an existing file object.
      required:
      - id
      description: Input for an attachment
      title: FileInputWithId
    description: A list of previously uploaded file attachments to include with the
      first message.
  message_source:
    oneOf:
    - "$ref": "#/components/schemas/AiChatMessageSourceTypes"
    - type: 'null'
    description: The source of the message.
  message_text:
    type: string
    description: The text content of the first message to send to the AI agent.
  suggestion_type:
    type:
    - string
    - 'null'
    description: The type of suggestion prompt that was clicked, when message_source
      is 'suggestion'.
  title:
    type:
    - string
    - 'null'
    description: An optional display title for the AI chat thread (e.g., "Help with
      billing").
required:
- message_text
description: Parameters for CreateAiChat
```


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

const aiChat = await client.aiChats.create({ message_text: 'message_text' });

console.log(aiChat.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
ai_chat = client.ai_chats.create(
    message_text="message_text",
)
print(ai_chat.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

ai_chat = whop.ai_chats.create(message_text: "message_text")

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
  AiChatMessageSourceTypes:
    type: string
    enum:
    - manual
    - suggestion
    - link
    description: The source of an AI chat message
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
  "/ai_chats":
    post:
      tags:
      - Ai chats
      operationId: createAiChat
      summary: Create ai chat
      description: |-
        Create a new AI chat thread and send the first message to the AI agent.

        Required permissions:
         - `ai_chat:create`
      parameters: []
      x-group-title: AI
      security:
      - bearerAuth:
        - ai_chat:create
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
      requestBody:
        required: true
        content:
          application/json:
            schema:
              type: object
              properties:
                current_company_id:
                  type:
                  - string
                  - 'null'
                  description: The unique identifier of the company to set as context
                    for the AI chat (e.g., "biz_XXXXX").
                message_attachments:
                  type:
                  - array
                  - 'null'
                  items:
                    type: object
                    properties:
                      id:
                        type: string
                        description: The ID of an existing file object.
                    required:
                    - id
                    description: Input for an attachment
                    title: FileInputWithId
                  description: A list of previously uploaded file attachments to include
                    with the first message.
                message_source:
                  oneOf:
                  - "$ref": "#/components/schemas/AiChatMessageSourceTypes"
                  - type: 'null'
                  description: The source of the message.
                message_text:
                  type: string
                  description: The text content of the first message to send to the
                    AI agent.
                suggestion_type:
                  type:
                  - string
                  - 'null'
                  description: The type of suggestion prompt that was clicked, when
                    message_source is 'suggestion'.
                title:
                  type:
                  - string
                  - 'null'
                  description: An optional display title for the AI chat thread (e.g.,
                    "Help with billing").
              required:
              - message_text
              description: Parameters for CreateAiChat
      x-codeSamples:
      - lang: JavaScript
        source: |-
          import Whop from '@whop/sdk';

          const client = new Whop({
            apiKey: process.env['WHOP_API_KEY'], // This is the default and can be omitted
          });

          const aiChat = await client.aiChats.create({ message_text: 'message_text' });

          console.log(aiChat.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          ai_chat = client.ai_chats.create(
              message_text="message_text",
          )
          print(ai_chat.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          ai_chat = whop.ai_chats.create(message_text: "message_text")

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
    AiChatMessageSourceTypes:
      type: string
      enum:
      - manual
      - suggestion
      - link
      description: The source of an AI chat message
    AiChatNotificationPreferences:
      type: string
      enum:
      - all
      - none
      description: The notification preference for an AI chat
```
