# PATCH /forums/{id} — Update forum

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `PATCH`
- **Path:** `/forums/{id}`
- **Operation ID:** `updateForum`
- **Tags:** `Forums`
- **Required bearer scopes:** `forum:moderate`

## Description

Update moderation and notification settings for a forum, such as who can post, who can comment, and email notification preferences.

Required permissions:
 - `forum:moderate`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `id` | path | yes | `string` | The unique identifier of the forum to update. Accepts either an experience ID (e.g. 'exp_xxxxx') or a forum ID. | exp_xxxxxxxxxxxxxx |

## Request body

Required: **no**
Content types: `application/json`

### `application/json` request schema

```yaml
type: object
properties:
  banned_words:
    type:
    - array
    - 'null'
    items:
      type: string
      description: Represents textual data as UTF-8 character sequences. This type
        is most often used by GraphQL to represent free-form human-readable text.
    description: A list of words that are automatically blocked from posts in this
      forum. For example, ['spam', 'scam'].
  banner_image:
    type:
    - object
    - 'null'
    properties:
      id:
        type: string
        description: The ID of an existing file object.
    required:
    - id
    description: The banner image displayed at the top of the forum page. Pass null
      to remove the existing banner.
    title: FileInputWithId
  email_notification_preference:
    oneOf:
    - "$ref": "#/components/schemas/ForumEmailNotificationPreferences"
    - type: 'null'
    description: Controls how email notifications are sent to members when new posts
      are created in this forum.
  who_can_comment:
    oneOf:
    - "$ref": "#/components/schemas/ForumWhoCanCommentTypes"
    - type: 'null'
    description: Controls which roles are allowed to comment on posts in this forum.
  who_can_post:
    oneOf:
    - "$ref": "#/components/schemas/ForumWhoCanPostTypes"
    - type: 'null'
    description: Controls which roles are allowed to create new posts in this forum.
required: []
description: Parameters for UpdateForum
```


## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `Forum` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/Forum"
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

const forum = await client.forums.update('exp_xxxxxxxxxxxxxx');

console.log(forum.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
forum = client.forums.update(
    id="exp_xxxxxxxxxxxxxx",
)
print(forum.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

forum = whop.forums.update("exp_xxxxxxxxxxxxxx")

puts(forum)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  Forum:
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
        description: The parent experience that this forum belongs to.
      who_can_post:
        "$ref": "#/components/schemas/ForumWhoCanPostTypes"
        description: 'The permission level controlling who can create new posts. One
          of: everyone, admins.'
      who_can_comment:
        "$ref": "#/components/schemas/ForumWhoCanCommentTypes"
        description: 'The permission level controlling who can comment on posts. One
          of: everyone, admins.'
      email_notification_preference:
        "$ref": "#/components/schemas/ForumEmailNotificationPreferences"
        description: 'The email notification setting that controls which posts trigger
          email alerts. One of: all_admin_posts, only_weekly_summary, none.'
    required:
    - id
    - experience
    - who_can_post
    - who_can_comment
    - email_notification_preference
    description: A discussion forum where members can create posts, comment, and react,
      belonging to an experience.
  ForumEmailNotificationPreferences:
    type: string
    enum:
    - all_admin_posts
    - only_weekly_summary
    - none
    description: Email notification preference option for a forum feed
  ForumWhoCanCommentTypes:
    type: string
    enum:
    - everyone
    - admins
    description: Who can comment on a forum feed
  ForumWhoCanPostTypes:
    type: string
    enum:
    - everyone
    - admins
    description: Who can post on a forum feed
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
  "/forums/{id}":
    patch:
      tags:
      - Forums
      operationId: updateForum
      summary: Update forum
      description: |-
        Update moderation and notification settings for a forum, such as who can post, who can comment, and email notification preferences.

        Required permissions:
         - `forum:moderate`
      parameters:
      - name: id
        in: path
        required: true
        description: The unique identifier of the forum to update. Accepts either
          an experience ID (e.g. 'exp_xxxxx') or a forum ID.
        schema:
          type: string
          example: exp_xxxxxxxxxxxxxx
      x-group-title: Engagement
      security:
      - bearerAuth:
        - forum:moderate
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/Forum"
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
                banned_words:
                  type:
                  - array
                  - 'null'
                  items:
                    type: string
                    description: Represents textual data as UTF-8 character sequences.
                      This type is most often used by GraphQL to represent free-form
                      human-readable text.
                  description: A list of words that are automatically blocked from
                    posts in this forum. For example, ['spam', 'scam'].
                banner_image:
                  type:
                  - object
                  - 'null'
                  properties:
                    id:
                      type: string
                      description: The ID of an existing file object.
                  required:
                  - id
                  description: The banner image displayed at the top of the forum
                    page. Pass null to remove the existing banner.
                  title: FileInputWithId
                email_notification_preference:
                  oneOf:
                  - "$ref": "#/components/schemas/ForumEmailNotificationPreferences"
                  - type: 'null'
                  description: Controls how email notifications are sent to members
                    when new posts are created in this forum.
                who_can_comment:
                  oneOf:
                  - "$ref": "#/components/schemas/ForumWhoCanCommentTypes"
                  - type: 'null'
                  description: Controls which roles are allowed to comment on posts
                    in this forum.
                who_can_post:
                  oneOf:
                  - "$ref": "#/components/schemas/ForumWhoCanPostTypes"
                  - type: 'null'
                  description: Controls which roles are allowed to create new posts
                    in this forum.
              required: []
              description: Parameters for UpdateForum
      x-codeSamples:
      - lang: JavaScript
        source: |-
          import Whop from '@whop/sdk';

          const client = new Whop({
            apiKey: process.env['WHOP_API_KEY'], // This is the default and can be omitted
          });

          const forum = await client.forums.update('exp_xxxxxxxxxxxxxx');

          console.log(forum.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          forum = client.forums.update(
              id="exp_xxxxxxxxxxxxxx",
          )
          print(forum.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          forum = whop.forums.update("exp_xxxxxxxxxxxxxx")

          puts(forum)
components:
  schemas:
    Forum:
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
          description: The parent experience that this forum belongs to.
        who_can_post:
          "$ref": "#/components/schemas/ForumWhoCanPostTypes"
          description: 'The permission level controlling who can create new posts.
            One of: everyone, admins.'
        who_can_comment:
          "$ref": "#/components/schemas/ForumWhoCanCommentTypes"
          description: 'The permission level controlling who can comment on posts.
            One of: everyone, admins.'
        email_notification_preference:
          "$ref": "#/components/schemas/ForumEmailNotificationPreferences"
          description: 'The email notification setting that controls which posts trigger
            email alerts. One of: all_admin_posts, only_weekly_summary, none.'
      required:
      - id
      - experience
      - who_can_post
      - who_can_comment
      - email_notification_preference
      description: A discussion forum where members can create posts, comment, and
        react, belonging to an experience.
    ForumEmailNotificationPreferences:
      type: string
      enum:
      - all_admin_posts
      - only_weekly_summary
      - none
      description: Email notification preference option for a forum feed
    ForumWhoCanCommentTypes:
      type: string
      enum:
      - everyone
      - admins
      description: Who can comment on a forum feed
    ForumWhoCanPostTypes:
      type: string
      enum:
      - everyone
      - admins
      description: Who can post on a forum feed
```
