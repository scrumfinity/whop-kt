# PATCH /forum_posts/{id} — Update forum post

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `PATCH`
- **Path:** `/forum_posts/{id}`
- **Operation ID:** `updateForumPost`
- **Tags:** `Forum posts`
- **Required bearer scopes:** _None documented_

## Description

Edit the content, attachments, pinned status, or visibility of an existing forum post or comment.

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `id` | path | yes | `string` | The unique identifier of the forum post to update. |  |

## Request body

Required: **no**
Content types: `application/json`

### `application/json` request schema

```yaml
type: object
properties:
  attachments:
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
    description: A replacement list of file attachments for this post, such as images
      or videos.
  content:
    type:
    - string
    - 'null'
    description: The updated body of the post in Markdown format. For example, 'Check
      out this **update**'. Hidden if the post is paywalled and the viewer has not
      purchased access.
  is_pinned:
    type:
    - boolean
    - 'null'
    description: Whether this post should be pinned to the top of the forum. Only
      top-level posts can be pinned, not comments.
  title:
    type:
    - string
    - 'null'
    description: The updated title of the post, displayed prominently at the top.
      Required for paywalled posts as it remains visible to non-purchasers.
  visibility:
    oneOf:
    - "$ref": "#/components/schemas/ForumPostVisibilityTypes"
    - type: 'null'
    description: Controls who can see this forum post, such as members only or public.
required: []
description: Parameters for UpdateForumPost
```


## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `ForumPost` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/ForumPost"
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

const forumPost = await client.forumPosts.update('id');

console.log(forumPost.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
forum_post = client.forum_posts.update(
    id="id",
)
print(forum_post.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

forum_post = whop.forum_posts.update("id")

puts(forum_post)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  ForumPost:
    type: object
    properties:
      id:
        type: string
        description: Represents a unique identifier that is Base64 obfuscated. It
          is often used to refetch an object or as key for a cache. The ID type appears
          in a JSON response as a String; however, it is not intended to be human-readable.
          When expected as an input type, any string (such as `"VXNlci0xMA=="`) or
          integer (such as `4`) input value will be accepted as an ID.
      title:
        type:
        - string
        - 'null'
        description: The headline of the forum post. Null if the post has no title.
        example: Weekly Market Analysis - February 2025
      content:
        type:
        - string
        - 'null'
        description: The body of the forum post in Markdown format. Null if the post
          is paywalled and the current user does not have access.
        example: |-
          ## My Strategy

          Here are the key steps...
      created_at:
        type: string
        format: date-time
        description: The time this post was created, as an ISO 8601 timestamp.
        example: '2023-12-01T05:00:00.401Z'
      updated_at:
        type: string
        format: date-time
        description: The time this post was last updated, as an ISO 8601 timestamp.
        example: '2023-12-01T05:00:00.401Z'
      is_edited:
        type: boolean
        description: Whether this post has been edited after its initial creation.
      is_poster_admin:
        type: boolean
        description: Whether the author of this post is an admin of the company that
          owns the forum.
      is_pinned:
        type: boolean
        description: Whether this post is pinned to the top of the forum feed.
      parent_id:
        type:
        - string
        - 'null'
        description: The unique identifier of the parent post. Null if this is a top-level
          post.
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
        description: The user who authored this forum post.
      attachments:
        type: array
        items:
          type: object
          properties:
            id:
              type: string
              description: Represents a unique identifier that is Base64 obfuscated.
                It is often used to refetch an object or as key for a cache. The ID
                type appears in a JSON response as a String; however, it is not intended
                to be human-readable. When expected as an input type, any string (such
                as `"VXNlci0xMA=="`) or integer (such as `4`) input value will be
                accepted as an ID.
            filename:
              type:
              - string
              - 'null'
              description: The original filename of the uploaded attachment, including
                its file extension.
              example: document.pdf
            content_type:
              type:
              - string
              - 'null'
              description: The MIME type of the uploaded file (e.g., image/jpeg, video/mp4,
                audio/mpeg).
              example: image/jpeg
            url:
              type:
              - string
              - 'null'
              description: A pre-optimized URL for rendering this attachment on the
                client. This should be used for displaying attachments in apps.
              example: https://media.whop.com/abc123/optimized.jpg
          required:
          - id
          - filename
          - content_type
          - url
          description: Represents an image attachment
        description: All file attachments on this post, such as images, documents,
          and videos.
      view_count:
        type:
        - integer
        - 'null'
        description: The total number of times this post has been viewed by users.
        example: 42
      like_count:
        type:
        - integer
        - 'null'
        description: The total number of like reactions this post has received.
        example: 42
      comment_count:
        type: integer
        description: The total number of direct comments on this post.
        example: 42
    required:
    - id
    - title
    - content
    - created_at
    - updated_at
    - is_edited
    - is_poster_admin
    - is_pinned
    - parent_id
    - user
    - attachments
    - view_count
    - like_count
    - comment_count
    description: A post or comment in a forum feed, supporting rich text, attachments,
      polls, and reactions.
  ForumPostVisibilityTypes:
    type: string
    enum:
    - members_only
    - globally_visible
    description: The visibility types for forum posts
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
  "/forum_posts/{id}":
    patch:
      tags:
      - Forum posts
      operationId: updateForumPost
      summary: Update forum post
      description: Edit the content, attachments, pinned status, or visibility of
        an existing forum post or comment.
      parameters:
      - name: id
        in: path
        required: true
        description: The unique identifier of the forum post to update.
        schema:
          type: string
      x-group-title: Engagement
      security:
      - bearerAuth: []
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/ForumPost"
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
                attachments:
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
                  description: A replacement list of file attachments for this post,
                    such as images or videos.
                content:
                  type:
                  - string
                  - 'null'
                  description: The updated body of the post in Markdown format. For
                    example, 'Check out this **update**'. Hidden if the post is paywalled
                    and the viewer has not purchased access.
                is_pinned:
                  type:
                  - boolean
                  - 'null'
                  description: Whether this post should be pinned to the top of the
                    forum. Only top-level posts can be pinned, not comments.
                title:
                  type:
                  - string
                  - 'null'
                  description: The updated title of the post, displayed prominently
                    at the top. Required for paywalled posts as it remains visible
                    to non-purchasers.
                visibility:
                  oneOf:
                  - "$ref": "#/components/schemas/ForumPostVisibilityTypes"
                  - type: 'null'
                  description: Controls who can see this forum post, such as members
                    only or public.
              required: []
              description: Parameters for UpdateForumPost
      x-codeSamples:
      - lang: JavaScript
        source: |-
          import Whop from '@whop/sdk';

          const client = new Whop({
            apiKey: process.env['WHOP_API_KEY'], // This is the default and can be omitted
          });

          const forumPost = await client.forumPosts.update('id');

          console.log(forumPost.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          forum_post = client.forum_posts.update(
              id="id",
          )
          print(forum_post.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          forum_post = whop.forum_posts.update("id")

          puts(forum_post)
components:
  schemas:
    ForumPost:
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
        title:
          type:
          - string
          - 'null'
          description: The headline of the forum post. Null if the post has no title.
          example: Weekly Market Analysis - February 2025
        content:
          type:
          - string
          - 'null'
          description: The body of the forum post in Markdown format. Null if the
            post is paywalled and the current user does not have access.
          example: |-
            ## My Strategy

            Here are the key steps...
        created_at:
          type: string
          format: date-time
          description: The time this post was created, as an ISO 8601 timestamp.
          example: '2023-12-01T05:00:00.401Z'
        updated_at:
          type: string
          format: date-time
          description: The time this post was last updated, as an ISO 8601 timestamp.
          example: '2023-12-01T05:00:00.401Z'
        is_edited:
          type: boolean
          description: Whether this post has been edited after its initial creation.
        is_poster_admin:
          type: boolean
          description: Whether the author of this post is an admin of the company
            that owns the forum.
        is_pinned:
          type: boolean
          description: Whether this post is pinned to the top of the forum feed.
        parent_id:
          type:
          - string
          - 'null'
          description: The unique identifier of the parent post. Null if this is a
            top-level post.
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
          description: The user who authored this forum post.
        attachments:
          type: array
          items:
            type: object
            properties:
              id:
                type: string
                description: Represents a unique identifier that is Base64 obfuscated.
                  It is often used to refetch an object or as key for a cache. The
                  ID type appears in a JSON response as a String; however, it is not
                  intended to be human-readable. When expected as an input type, any
                  string (such as `"VXNlci0xMA=="`) or integer (such as `4`) input
                  value will be accepted as an ID.
              filename:
                type:
                - string
                - 'null'
                description: The original filename of the uploaded attachment, including
                  its file extension.
                example: document.pdf
              content_type:
                type:
                - string
                - 'null'
                description: The MIME type of the uploaded file (e.g., image/jpeg,
                  video/mp4, audio/mpeg).
                example: image/jpeg
              url:
                type:
                - string
                - 'null'
                description: A pre-optimized URL for rendering this attachment on
                  the client. This should be used for displaying attachments in apps.
                example: https://media.whop.com/abc123/optimized.jpg
            required:
            - id
            - filename
            - content_type
            - url
            description: Represents an image attachment
          description: All file attachments on this post, such as images, documents,
            and videos.
        view_count:
          type:
          - integer
          - 'null'
          description: The total number of times this post has been viewed by users.
          example: 42
        like_count:
          type:
          - integer
          - 'null'
          description: The total number of like reactions this post has received.
          example: 42
        comment_count:
          type: integer
          description: The total number of direct comments on this post.
          example: 42
      required:
      - id
      - title
      - content
      - created_at
      - updated_at
      - is_edited
      - is_poster_admin
      - is_pinned
      - parent_id
      - user
      - attachments
      - view_count
      - like_count
      - comment_count
      description: A post or comment in a forum feed, supporting rich text, attachments,
        polls, and reactions.
    ForumPostVisibilityTypes:
      type: string
      enum:
      - members_only
      - globally_visible
      description: The visibility types for forum posts
```
