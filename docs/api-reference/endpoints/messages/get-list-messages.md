# GET /messages — List messages

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/messages`
- **Operation ID:** `listMessage`
- **Tags:** `Messages`
- **Required bearer scopes:** `chat:read`

## Description

Returns a paginated list of messages within a specific experience chat, DM, or group chat channel, sorted by creation time.

Required permissions:
 - `chat:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `after` | query | no | `string \| null` | Returns the elements in the list that come after the specified cursor. |  |
| `before` | query | no | `string \| null` | Returns the elements in the list that come before the specified cursor. |  |
| `first` | query | no | `integer \| null` | Returns the first _n_ elements from the list. | 42 |
| `last` | query | no | `integer \| null` | Returns the last _n_ elements from the list. | 42 |
| `channel_id` | query | yes | `string` | The unique identifier of the channel or experience to list messages for. | exp_xxxxxxxxxxxxxx |
| `direction` | query | no | `Direction \| null` | The sort direction for messages by creation time. Use 'asc' for oldest first or 'desc' for newest first. |  |

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
      "$ref": "#/components/schemas/MessageListItem"
    description: A list of nodes.
  page_info:
    "$ref": "#/components/schemas/PageInfo"
    description: Information to aid in pagination.
required:
- data
- page_info
description: The connection type for DmsPost.
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
for await (const messageListResponse of client.messages.list({ channel_id: 'exp_xxxxxxxxxxxxxx' })) {
  console.log(messageListResponse.id);
}
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
page = client.messages.list(
    channel_id="exp_xxxxxxxxxxxxxx",
)
page = page.data[0]
print(page.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

page = whop.messages.list(channel_id: "exp_xxxxxxxxxxxxxx")

puts(page)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  Direction:
    type: string
    enum:
    - asc
    - desc
    description: The direction of the sort.
  DmsPostTypes:
    type: string
    enum:
    - regular
    - system
    - automated
    description: The types of post
  MessageListItem:
    type: object
    properties:
      id:
        type: string
        description: Represents a unique identifier that is Base64 obfuscated. It
          is often used to refetch an object or as key for a cache. The ID type appears
          in a JSON response as a String; however, it is not intended to be human-readable.
          When expected as an input type, any string (such as `"VXNlci0xMA=="`) or
          integer (such as `4`) input value will be accepted as an ID.
      content:
        type:
        - string
        - 'null'
        description: The message content formatted as Markdown. Null if the message
          has no text content.
        example: Hey, are you available for a **quick call**?
      created_at:
        type: string
        format: date-time
        description: The timestamp when this message was originally created.
        example: '2023-12-01T05:00:00.401Z'
      updated_at:
        type: string
        format: date-time
        description: The timestamp when this message was last modified.
        example: '2023-12-01T05:00:00.401Z'
      poll:
        type:
        - object
        - 'null'
        properties:
          options:
            type:
            - array
            - 'null'
            items:
              type: object
              properties:
                id:
                  type: string
                  description: The unique identifier for the poll option.
                text:
                  type: string
                  description: The text of the poll option
              required:
              - id
              - text
              description: Represents a single poll option
            description: The options for the poll
        required:
        - options
        description: A poll attached to this message. Null if the message does not
          contain a poll.
      replying_to_message_id:
        type:
        - string
        - 'null'
        description: The unique identifier of the message this post is replying to.
          Null if this is not a reply.
      is_edited:
        type: boolean
        description: Whether the message content has been edited after it was originally
          sent.
      is_pinned:
        type: boolean
        description: Whether this message is pinned to the top of the channel for
          easy access.
      message_type:
        "$ref": "#/components/schemas/DmsPostTypes"
        description: 'The classification of this message: regular, system, or automated.'
      mentions:
        type: array
        items:
          type: string
          description: Represents a unique identifier that is Base64 obfuscated. It
            is often used to refetch an object or as key for a cache. The ID type
            appears in a JSON response as a String; however, it is not intended to
            be human-readable. When expected as an input type, any string (such as
            `"VXNlci0xMA=="`) or integer (such as `4`) input value will be accepted
            as an ID.
        description: A list of user IDs that are explicitly mentioned in this message.
      mentions_everyone:
        type: boolean
        description: Whether the message includes an @everyone mention that notifies
          all channel members.
      user:
        type: object
        properties:
          id:
            type: string
            description: The unique identifier for the user.
            example: user_xxxxxxxxxxxxx
          username:
            type: string
            description: The user's unique username shown on their public profile.
            example: johndoe42
          name:
            type:
            - string
            - 'null'
            description: The user's display name shown on their public profile.
            example: John Doe
        required:
        - id
        - username
        - name
        description: The user who authored this message.
      view_count:
        type:
        - integer
        - 'null'
        description: The number of unique views this message has received. Null if
          view tracking is not enabled for this channel.
        example: 42
      reaction_counts:
        type: array
        items:
          type: object
          properties:
            emoji:
              type:
              - string
              - 'null'
              description: The emoji that was used in shortcode format (:heart:)
            count:
              type: integer
              description: The number of users who reacted
              example: 42
          required:
          - emoji
          - count
          description: Represents a reaction count for a feed post
        description: Aggregated reaction counts on this message, filtered to a specific
          reaction type.
      poll_votes:
        type: array
        items:
          type: object
          properties:
            option_id:
              type:
              - string
              - 'null'
              description: The reaction that was used
            count:
              type: integer
              description: The number of users who reacted
              example: 42
          required:
          - option_id
          - count
          description: Represents a reaction count for a feed post
        description: Aggregated reaction counts on this message, filtered to a specific
          reaction type.
    required:
    - id
    - content
    - created_at
    - updated_at
    - poll
    - replying_to_message_id
    - is_edited
    - is_pinned
    - message_type
    - mentions
    - mentions_everyone
    - user
    - view_count
    - reaction_counts
    - poll_votes
    description: A message sent within an experience chat, direct message, or group
      chat.
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
  "/messages":
    get:
      tags:
      - Messages
      operationId: listMessage
      summary: List messages
      description: |-
        Returns a paginated list of messages within a specific experience chat, DM, or group chat channel, sorted by creation time.

        Required permissions:
         - `chat:read`
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
      - name: channel_id
        in: query
        required: true
        schema:
          type: string
          example: exp_xxxxxxxxxxxxxx
          description: The unique identifier of the channel or experience to list
            messages for.
        explode: true
        style: form
      - name: direction
        in: query
        required: false
        schema:
          oneOf:
          - "$ref": "#/components/schemas/Direction"
          - type: 'null'
          description: The sort direction for messages by creation time. Use 'asc'
            for oldest first or 'desc' for newest first.
        explode: true
        style: form
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
                type: object
                properties:
                  data:
                    type: array
                    items:
                      "$ref": "#/components/schemas/MessageListItem"
                    description: A list of nodes.
                  page_info:
                    "$ref": "#/components/schemas/PageInfo"
                    description: Information to aid in pagination.
                required:
                - data
                - page_info
                description: The connection type for DmsPost.
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
          for await (const messageListResponse of client.messages.list({ channel_id: 'exp_xxxxxxxxxxxxxx' })) {
            console.log(messageListResponse.id);
          }
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          page = client.messages.list(
              channel_id="exp_xxxxxxxxxxxxxx",
          )
          page = page.data[0]
          print(page.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          page = whop.messages.list(channel_id: "exp_xxxxxxxxxxxxxx")

          puts(page)
components:
  schemas:
    Direction:
      type: string
      enum:
      - asc
      - desc
      description: The direction of the sort.
    DmsPostTypes:
      type: string
      enum:
      - regular
      - system
      - automated
      description: The types of post
    MessageListItem:
      type: object
      properties:
        id:
          type: string
          description: Represents a unique identifier that is Base64 obfuscated. It
            is often used to refetch an object or as key for a cache. The ID type
            appears in a JSON response as a String; however, it is not intended to
            be human-readable. When expected as an input type, any string (such as
            `"VXNlci0xMA=="`) or integer (such as `4`) input value will be accepted
            as an ID.
        content:
          type:
          - string
          - 'null'
          description: The message content formatted as Markdown. Null if the message
            has no text content.
          example: Hey, are you available for a **quick call**?
        created_at:
          type: string
          format: date-time
          description: The timestamp when this message was originally created.
          example: '2023-12-01T05:00:00.401Z'
        updated_at:
          type: string
          format: date-time
          description: The timestamp when this message was last modified.
          example: '2023-12-01T05:00:00.401Z'
        poll:
          type:
          - object
          - 'null'
          properties:
            options:
              type:
              - array
              - 'null'
              items:
                type: object
                properties:
                  id:
                    type: string
                    description: The unique identifier for the poll option.
                  text:
                    type: string
                    description: The text of the poll option
                required:
                - id
                - text
                description: Represents a single poll option
              description: The options for the poll
          required:
          - options
          description: A poll attached to this message. Null if the message does not
            contain a poll.
        replying_to_message_id:
          type:
          - string
          - 'null'
          description: The unique identifier of the message this post is replying
            to. Null if this is not a reply.
        is_edited:
          type: boolean
          description: Whether the message content has been edited after it was originally
            sent.
        is_pinned:
          type: boolean
          description: Whether this message is pinned to the top of the channel for
            easy access.
        message_type:
          "$ref": "#/components/schemas/DmsPostTypes"
          description: 'The classification of this message: regular, system, or automated.'
        mentions:
          type: array
          items:
            type: string
            description: Represents a unique identifier that is Base64 obfuscated.
              It is often used to refetch an object or as key for a cache. The ID
              type appears in a JSON response as a String; however, it is not intended
              to be human-readable. When expected as an input type, any string (such
              as `"VXNlci0xMA=="`) or integer (such as `4`) input value will be accepted
              as an ID.
          description: A list of user IDs that are explicitly mentioned in this message.
        mentions_everyone:
          type: boolean
          description: Whether the message includes an @everyone mention that notifies
            all channel members.
        user:
          type: object
          properties:
            id:
              type: string
              description: The unique identifier for the user.
              example: user_xxxxxxxxxxxxx
            username:
              type: string
              description: The user's unique username shown on their public profile.
              example: johndoe42
            name:
              type:
              - string
              - 'null'
              description: The user's display name shown on their public profile.
              example: John Doe
          required:
          - id
          - username
          - name
          description: The user who authored this message.
        view_count:
          type:
          - integer
          - 'null'
          description: The number of unique views this message has received. Null
            if view tracking is not enabled for this channel.
          example: 42
        reaction_counts:
          type: array
          items:
            type: object
            properties:
              emoji:
                type:
                - string
                - 'null'
                description: The emoji that was used in shortcode format (:heart:)
              count:
                type: integer
                description: The number of users who reacted
                example: 42
            required:
            - emoji
            - count
            description: Represents a reaction count for a feed post
          description: Aggregated reaction counts on this message, filtered to a specific
            reaction type.
        poll_votes:
          type: array
          items:
            type: object
            properties:
              option_id:
                type:
                - string
                - 'null'
                description: The reaction that was used
              count:
                type: integer
                description: The number of users who reacted
                example: 42
            required:
            - option_id
            - count
            description: Represents a reaction count for a feed post
          description: Aggregated reaction counts on this message, filtered to a specific
            reaction type.
      required:
      - id
      - content
      - created_at
      - updated_at
      - poll
      - replying_to_message_id
      - is_edited
      - is_pinned
      - message_type
      - mentions
      - mentions_everyone
      - user
      - view_count
      - reaction_counts
      - poll_votes
      description: A message sent within an experience chat, direct message, or group
        chat.
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
```
