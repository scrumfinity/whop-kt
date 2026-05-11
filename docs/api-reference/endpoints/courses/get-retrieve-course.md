# GET /courses/{id} — Retrieve course

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/courses/{id}`
- **Operation ID:** `retrieveCourse`
- **Tags:** `Courses`
- **Required bearer scopes:** `courses:read`

## Description

Retrieves the details of an existing course.

Required permissions:
 - `courses:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `id` | path | yes | `string` | The unique identifier of the course to retrieve. | cors_xxxxxxxxxxxxx |

## Request body

_No request body._

## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `Course` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/Course"
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

const course = await client.courses.retrieve('cors_xxxxxxxxxxxxx');

console.log(course.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
course = client.courses.retrieve(
    "cors_xxxxxxxxxxxxx",
)
print(course.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

course = whop.courses.retrieve("cors_xxxxxxxxxxxxx")

puts(course)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  Course:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the course.
        example: cors_xxxxxxxxxxxxx
      title:
        type:
        - string
        - 'null'
        description: The display name of the course shown to students. Null if no
          title has been set.
        example: Introduction to Technical Analysis
      tagline:
        type:
        - string
        - 'null'
        description: A short marketing tagline displayed beneath the course title.
          Null if no tagline has been set.
        example: Master the fundamentals in 30 days
      cover_image:
        type:
        - string
        - 'null'
        description: The URL of the course cover image shown on preview cards. Null
          if no cover image has been uploaded.
        example: https://assets.whop.com/images/course-cover.jpg
      thumbnail:
        type:
        - object
        - 'null'
        properties:
          id:
            type: string
            description: Represents a unique identifier that is Base64 obfuscated.
              It is often used to refetch an object or as key for a cache. The ID
              type appears in a JSON response as a String; however, it is not intended
              to be human-readable. When expected as an input type, any string (such
              as `"VXNlci0xMA=="`) or integer (such as `4`) input value will be accepted
              as an ID.
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
          optimized_url:
            type:
            - string
            - 'null'
            description: A pre-optimized URL for rendering this attachment on the
              client. This should be used for displaying attachments in apps.
            example: https://media.whop.com/abc123/optimized.jpg
          source_url:
            type:
            - string
            - 'null'
            description: The original source URL of the attachment, such as a direct
              link to S3. This should never be displayed on the client and should
              always be passed through an Imgproxy transformer.
            example: https://media.whop.com/abc123/original.jpg
        required:
        - id
        - filename
        - content_type
        - optimized_url
        - source_url
        description: The thumbnail image displayed on course cards and previews. Null
          if no thumbnail has been uploaded.
      description:
        type:
        - string
        - 'null'
        description: A brief summary of the course content and objectives. Null if
          no description has been set.
        example: Learn advanced trading strategies from industry experts.
      language:
        "$ref": "#/components/schemas/Languages"
        description: 'The spoken language of the video content, used to generate accurate
          closed captions. One of: en, es, it, pt, de, fr, pl, ru, nl, ca, tr, sv,
          uk, no, fi, sk, el, cs, hr, da, ro, bg.'
      certificate_after_completion_enabled:
        type:
        - boolean
        - 'null'
        description: Whether students receive a PDF certificate after completing all
          lessons in this course. Null if the setting has not been configured.
      require_completing_lessons_in_order:
        type: boolean
        description: Whether students must complete each lesson sequentially before
          advancing to the next one.
      order:
        type: string
        description: The sort position of this course within its parent experience,
          as a decimal for flexible ordering.
        example: '1727606400000'
      visibility:
        "$ref": "#/components/schemas/CourseVisibilities"
        description: 'The visibility setting that controls whether this course appears
          to students. One of: visible, hidden.'
      created_at:
        type: string
        format: date-time
        description: The datetime the course was created.
        example: '2023-12-01T05:00:00.401Z'
      updated_at:
        type: string
        format: date-time
        description: The datetime the course was last updated.
        example: '2023-12-01T05:00:00.401Z'
      chapters:
        type: array
        items:
          type: object
          properties:
            id:
              type: string
              description: The unique identifier for the chapter.
              example: chap_xxxxxxxxxxxxx
            title:
              type: string
              description: The display name of the chapter shown to students. Maximum
                150 characters.
              example: Getting Started
            order:
              type: integer
              description: The sort position of this chapter within its parent course,
                starting from zero.
              example: 42
            lessons:
              type: array
              items:
                type: object
                properties:
                  id:
                    type: string
                    description: The unique identifier for the lesson.
                    example: lesn_xxxxxxxxxxxxx
                  lesson_type:
                    "$ref": "#/components/schemas/LessonTypes"
                    description: 'The content format of this lesson. One of: text,
                      video, pdf, multi, quiz, knowledge_check.'
                  title:
                    type: string
                    description: The display name of the lesson shown to students.
                      Maximum 120 characters.
                    example: Understanding Candlestick Patterns
                  order:
                    type: integer
                    description: The sort position of this lesson within its parent
                      chapter, starting from zero.
                    example: 42
                  video_asset:
                    type:
                    - object
                    - 'null'
                    properties:
                      signed_playback_id:
                        type:
                        - string
                        - 'null'
                        description: The signed playback ID of the Mux asset
                      signed_thumbnail_playback_token:
                        type:
                        - string
                        - 'null'
                        description: The signed thumbnail playback token of the Mux
                          asset
                      duration_seconds:
                        type:
                        - integer
                        - 'null'
                        description: The duration of the video in seconds
                        example: 42
                    required:
                    - signed_playback_id
                    - signed_thumbnail_playback_token
                    - duration_seconds
                    description: The Mux video asset for video-type lessons, used
                      for streaming playback. Null if this lesson has no hosted video.
                  thumbnail:
                    type:
                    - object
                    - 'null'
                    properties:
                      url:
                        type:
                        - string
                        - 'null'
                        description: A pre-optimized URL for rendering this attachment
                          on the client. This should be used for displaying attachments
                          in apps.
                        example: https://media.whop.com/abc123/optimized.jpg
                    required:
                    - url
                    description: The thumbnail image displayed on lesson cards and
                      previews. Null if no thumbnail has been uploaded.
                required:
                - id
                - lesson_type
                - title
                - order
                - video_asset
                - thumbnail
                description: An individual learning unit within a chapter, which can
                  contain text, video, PDF, or assessment content.
              description: An ordered list of lessons in this chapter, sorted by display
                position. Hidden lessons are excluded for non-admin users.
          required:
          - id
          - title
          - order
          - lessons
          description: A grouping of related lessons within a course, used to organize
            content into sections.
        description: An ordered list of all chapters in this course, sorted by their
          display position.
    required:
    - id
    - title
    - tagline
    - cover_image
    - thumbnail
    - description
    - language
    - certificate_after_completion_enabled
    - require_completing_lessons_in_order
    - order
    - visibility
    - created_at
    - updated_at
    - chapters
    description: A structured learning module containing chapters and lessons, belonging
      to an experience.
  CourseVisibilities:
    type: string
    enum:
    - visible
    - hidden
    description: The available visibilities for a course. Determines how / whether
      a course is visible to users.
  Languages:
    type: string
    enum:
    - en
    - es
    - it
    - pt
    - de
    - fr
    - pl
    - ru
    - nl
    - ca
    - tr
    - sv
    - uk
    - 'no'
    - fi
    - sk
    - el
    - cs
    - hr
    - da
    - ro
    - bg
    description: The available languages for a course
  LessonTypes:
    type: string
    enum:
    - text
    - video
    - pdf
    - multi
    - quiz
    - knowledge_check
    description: The available types for a lesson
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
  "/courses/{id}":
    get:
      tags:
      - Courses
      operationId: retrieveCourse
      summary: Retrieve course
      description: |-
        Retrieves the details of an existing course.

        Required permissions:
         - `courses:read`
      parameters:
      - name: id
        in: path
        required: true
        description: The unique identifier of the course to retrieve.
        schema:
          type: string
          example: cors_xxxxxxxxxxxxx
      x-group-title: Courses
      security:
      - bearerAuth:
        - courses:read
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/Course"
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

          const course = await client.courses.retrieve('cors_xxxxxxxxxxxxx');

          console.log(course.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          course = client.courses.retrieve(
              "cors_xxxxxxxxxxxxx",
          )
          print(course.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          course = whop.courses.retrieve("cors_xxxxxxxxxxxxx")

          puts(course)
components:
  schemas:
    Course:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the course.
          example: cors_xxxxxxxxxxxxx
        title:
          type:
          - string
          - 'null'
          description: The display name of the course shown to students. Null if no
            title has been set.
          example: Introduction to Technical Analysis
        tagline:
          type:
          - string
          - 'null'
          description: A short marketing tagline displayed beneath the course title.
            Null if no tagline has been set.
          example: Master the fundamentals in 30 days
        cover_image:
          type:
          - string
          - 'null'
          description: The URL of the course cover image shown on preview cards. Null
            if no cover image has been uploaded.
          example: https://assets.whop.com/images/course-cover.jpg
        thumbnail:
          type:
          - object
          - 'null'
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
            optimized_url:
              type:
              - string
              - 'null'
              description: A pre-optimized URL for rendering this attachment on the
                client. This should be used for displaying attachments in apps.
              example: https://media.whop.com/abc123/optimized.jpg
            source_url:
              type:
              - string
              - 'null'
              description: The original source URL of the attachment, such as a direct
                link to S3. This should never be displayed on the client and should
                always be passed through an Imgproxy transformer.
              example: https://media.whop.com/abc123/original.jpg
          required:
          - id
          - filename
          - content_type
          - optimized_url
          - source_url
          description: The thumbnail image displayed on course cards and previews.
            Null if no thumbnail has been uploaded.
        description:
          type:
          - string
          - 'null'
          description: A brief summary of the course content and objectives. Null
            if no description has been set.
          example: Learn advanced trading strategies from industry experts.
        language:
          "$ref": "#/components/schemas/Languages"
          description: 'The spoken language of the video content, used to generate
            accurate closed captions. One of: en, es, it, pt, de, fr, pl, ru, nl,
            ca, tr, sv, uk, no, fi, sk, el, cs, hr, da, ro, bg.'
        certificate_after_completion_enabled:
          type:
          - boolean
          - 'null'
          description: Whether students receive a PDF certificate after completing
            all lessons in this course. Null if the setting has not been configured.
        require_completing_lessons_in_order:
          type: boolean
          description: Whether students must complete each lesson sequentially before
            advancing to the next one.
        order:
          type: string
          description: The sort position of this course within its parent experience,
            as a decimal for flexible ordering.
          example: '1727606400000'
        visibility:
          "$ref": "#/components/schemas/CourseVisibilities"
          description: 'The visibility setting that controls whether this course appears
            to students. One of: visible, hidden.'
        created_at:
          type: string
          format: date-time
          description: The datetime the course was created.
          example: '2023-12-01T05:00:00.401Z'
        updated_at:
          type: string
          format: date-time
          description: The datetime the course was last updated.
          example: '2023-12-01T05:00:00.401Z'
        chapters:
          type: array
          items:
            type: object
            properties:
              id:
                type: string
                description: The unique identifier for the chapter.
                example: chap_xxxxxxxxxxxxx
              title:
                type: string
                description: The display name of the chapter shown to students. Maximum
                  150 characters.
                example: Getting Started
              order:
                type: integer
                description: The sort position of this chapter within its parent course,
                  starting from zero.
                example: 42
              lessons:
                type: array
                items:
                  type: object
                  properties:
                    id:
                      type: string
                      description: The unique identifier for the lesson.
                      example: lesn_xxxxxxxxxxxxx
                    lesson_type:
                      "$ref": "#/components/schemas/LessonTypes"
                      description: 'The content format of this lesson. One of: text,
                        video, pdf, multi, quiz, knowledge_check.'
                    title:
                      type: string
                      description: The display name of the lesson shown to students.
                        Maximum 120 characters.
                      example: Understanding Candlestick Patterns
                    order:
                      type: integer
                      description: The sort position of this lesson within its parent
                        chapter, starting from zero.
                      example: 42
                    video_asset:
                      type:
                      - object
                      - 'null'
                      properties:
                        signed_playback_id:
                          type:
                          - string
                          - 'null'
                          description: The signed playback ID of the Mux asset
                        signed_thumbnail_playback_token:
                          type:
                          - string
                          - 'null'
                          description: The signed thumbnail playback token of the
                            Mux asset
                        duration_seconds:
                          type:
                          - integer
                          - 'null'
                          description: The duration of the video in seconds
                          example: 42
                      required:
                      - signed_playback_id
                      - signed_thumbnail_playback_token
                      - duration_seconds
                      description: The Mux video asset for video-type lessons, used
                        for streaming playback. Null if this lesson has no hosted
                        video.
                    thumbnail:
                      type:
                      - object
                      - 'null'
                      properties:
                        url:
                          type:
                          - string
                          - 'null'
                          description: A pre-optimized URL for rendering this attachment
                            on the client. This should be used for displaying attachments
                            in apps.
                          example: https://media.whop.com/abc123/optimized.jpg
                      required:
                      - url
                      description: The thumbnail image displayed on lesson cards and
                        previews. Null if no thumbnail has been uploaded.
                  required:
                  - id
                  - lesson_type
                  - title
                  - order
                  - video_asset
                  - thumbnail
                  description: An individual learning unit within a chapter, which
                    can contain text, video, PDF, or assessment content.
                description: An ordered list of lessons in this chapter, sorted by
                  display position. Hidden lessons are excluded for non-admin users.
            required:
            - id
            - title
            - order
            - lessons
            description: A grouping of related lessons within a course, used to organize
              content into sections.
          description: An ordered list of all chapters in this course, sorted by their
            display position.
      required:
      - id
      - title
      - tagline
      - cover_image
      - thumbnail
      - description
      - language
      - certificate_after_completion_enabled
      - require_completing_lessons_in_order
      - order
      - visibility
      - created_at
      - updated_at
      - chapters
      description: A structured learning module containing chapters and lessons, belonging
        to an experience.
    CourseVisibilities:
      type: string
      enum:
      - visible
      - hidden
      description: The available visibilities for a course. Determines how / whether
        a course is visible to users.
    Languages:
      type: string
      enum:
      - en
      - es
      - it
      - pt
      - de
      - fr
      - pl
      - ru
      - nl
      - ca
      - tr
      - sv
      - uk
      - 'no'
      - fi
      - sk
      - el
      - cs
      - hr
      - da
      - ro
      - bg
      description: The available languages for a course
    LessonTypes:
      type: string
      enum:
      - text
      - video
      - pdf
      - multi
      - quiz
      - knowledge_check
      description: The available types for a lesson
```
