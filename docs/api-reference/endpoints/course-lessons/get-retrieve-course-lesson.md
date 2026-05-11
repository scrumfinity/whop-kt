# GET /course_lessons/{id} — Retrieve course lesson

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/course_lessons/{id}`
- **Operation ID:** `retrieveCourseLesson`
- **Tags:** `Course lessons`
- **Required bearer scopes:** `courses:read`

## Description

Retrieves the details of an existing course lesson.

Required permissions:
 - `courses:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `id` | path | yes | `string` | The unique identifier of the lesson to retrieve. | lesn_xxxxxxxxxxxxx |

## Request body

_No request body._

## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `CourseLesson` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/CourseLesson"
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

const lesson = await client.courseLessons.retrieve('lesn_xxxxxxxxxxxxx');

console.log(lesson.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
lesson = client.course_lessons.retrieve(
    "lesn_xxxxxxxxxxxxx",
)
print(lesson.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

lesson = whop.course_lessons.retrieve("lesn_xxxxxxxxxxxxx")

puts(lesson)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  CourseLesson:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the lesson.
        example: lesn_xxxxxxxxxxxxx
      title:
        type: string
        description: The display name of the lesson shown to students. Maximum 120
          characters.
        example: Understanding Candlestick Patterns
      order:
        type: integer
        description: The sort position of this lesson within its parent chapter, starting
          from zero.
        example: 42
      lesson_type:
        "$ref": "#/components/schemas/LessonTypes"
        description: 'The content format of this lesson. One of: text, video, pdf,
          multi, quiz, knowledge_check.'
      visibility:
        "$ref": "#/components/schemas/LessonVisibilities"
        description: 'The visibility setting that controls whether this lesson appears
          to students. One of: visible, hidden.'
      content:
        type:
        - string
        - 'null'
        description: The Markdown content body of the lesson. Null if the lesson has
          no text content.
        example: In this lesson, we will cover the basics of technical analysis...
      days_from_course_start_until_unlock:
        type:
        - integer
        - 'null'
        description: The number of days after a student starts the course before this
          lesson becomes accessible. Null if the lesson is available immediately.
        example: 42
      embed_type:
        oneOf:
        - "$ref": "#/components/schemas/EmbedTypes"
        - type: 'null'
        description: 'The platform type for the embedded video. One of: youtube, loom.
          Null if the lesson has no embed.'
      embed_id:
        type:
        - string
        - 'null'
        description: The external video identifier for embedded video lessons, such
          as a YouTube video ID or Loom share ID. Null if the lesson has no embed.
        example: dQw4w9WgXcQ
      created_at:
        type: string
        format: date-time
        description: The datetime the lesson was created.
        example: '2023-12-01T05:00:00.401Z'
      thumbnail:
        type:
        - object
        - 'null'
        properties:
          url:
            type:
            - string
            - 'null'
            description: A pre-optimized URL for rendering this attachment on the
              client. This should be used for displaying attachments in apps.
            example: https://media.whop.com/abc123/optimized.jpg
        required:
        - url
        description: The thumbnail image displayed on lesson cards and previews. Null
          if no thumbnail has been uploaded.
      video_asset:
        type:
        - object
        - 'null'
        properties:
          id:
            type: string
            description: The unique identifier for the mux asset.
            example: mux_xxxxxxxxxxxxxx
          asset_id:
            type:
            - string
            - 'null'
            description: The Mux-provided ID of the asset
          playback_id:
            type:
            - string
            - 'null'
            description: The public playback ID of the Mux asset
          signed_playback_id:
            type:
            - string
            - 'null'
            description: The signed playback ID of the Mux asset
          status:
            "$ref": "#/components/schemas/MuxAssetStatuses"
            description: The status of the Mux asset
          audio_only:
            type: boolean
            description: Whether this asset contains only audio
          duration_seconds:
            type:
            - integer
            - 'null'
            description: The duration of the video in seconds
            example: 42
          signed_video_playback_token:
            type:
            - string
            - 'null'
            description: The signed video playback token of the Mux asset
          signed_thumbnail_playback_token:
            type:
            - string
            - 'null'
            description: The signed thumbnail playback token of the Mux asset
          signed_storyboard_playback_token:
            type:
            - string
            - 'null'
            description: The signed storyboard playback token of the Mux asset
          created_at:
            type: string
            format: date-time
            description: The datetime the mux asset was created.
            example: '2023-12-01T05:00:00.401Z'
          updated_at:
            type: string
            format: date-time
            description: The datetime the mux asset was last updated.
            example: '2023-12-01T05:00:00.401Z'
          finished_uploading_at:
            type:
            - string
            - 'null'
            format: date-time
            description: The time at which the video finished uploading
            example: '2023-12-01T05:00:00.401Z'
        required:
        - id
        - asset_id
        - playback_id
        - signed_playback_id
        - status
        - audio_only
        - duration_seconds
        - signed_video_playback_token
        - signed_thumbnail_playback_token
        - signed_storyboard_playback_token
        - created_at
        - updated_at
        - finished_uploading_at
        description: The Mux video asset for video-type lessons, used for streaming
          playback. Null if this lesson has no hosted video.
      main_pdf:
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
        description: The primary PDF document for PDF-type lessons. Null if this lesson
          is not a PDF lesson or no PDF has been uploaded.
      assessment_questions:
        type: array
        items:
          type: object
          properties:
            id:
              type: string
              description: The unique identifier for the assessment question.
              example: crsaq_xxxxxxxxxxxx
            question_text:
              type: string
              description: The text of the question
            question_type:
              "$ref": "#/components/schemas/CoursesAssessmentQuestionTypes"
              description: The type of the question
            correct_answer:
              type:
              - string
              - 'null'
              description: The correct answer for the question. Used for short answer
                questions. Only visible to admins (users with courses:update permission)
            order:
              type: integer
              description: The order of the question within its lesson
              example: 42
            created_at:
              type: string
              format: date-time
              description: The datetime the assessment question was created.
              example: '2023-12-01T05:00:00.401Z'
            image:
              type:
              - object
              - 'null'
              properties:
                id:
                  type: string
                  description: Represents a unique identifier that is Base64 obfuscated.
                    It is often used to refetch an object or as key for a cache. The
                    ID type appears in a JSON response as a String; however, it is
                    not intended to be human-readable. When expected as an input type,
                    any string (such as `"VXNlci0xMA=="`) or integer (such as `4`)
                    input value will be accepted as an ID.
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
                    the client. This should be used for displaying attachments in
                    apps.
                  example: https://media.whop.com/abc123/optimized.jpg
              required:
              - id
              - filename
              - content_type
              - url
              description: Optional image attachment for the question
            options:
              type: array
              items:
                type: object
                properties:
                  id:
                    type: string
                    description: The unique identifier for the assessment question
                      option.
                    example: crsaqo_xxxxxxxxxxx
                  option_text:
                    type: string
                    description: The text of the answer option
                  is_correct:
                    type:
                    - boolean
                    - 'null'
                    description: Whether this option is a correct answer. Only visible
                      to admins (users with courses:update permission)
                  order:
                    type: integer
                    description: The order of this option within the question
                    example: 42
                required:
                - id
                - option_text
                - is_correct
                - order
                description: An answer option for a multiple choice or multiple select
                  assessment question
              description: The answer options for multiple choice/select questions
          required:
          - id
          - question_text
          - question_type
          - correct_answer
          - order
          - created_at
          - image
          - options
          description: An assessment question in a course quiz or knowledge check
        description: The list of questions for quiz or knowledge check lessons. Empty
          for non-assessment lesson types.
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
        description: All supplementary files attached to this lesson returned as a
          flat array rather than a paginated connection.
    required:
    - id
    - title
    - order
    - lesson_type
    - visibility
    - content
    - days_from_course_start_until_unlock
    - embed_type
    - embed_id
    - created_at
    - thumbnail
    - video_asset
    - main_pdf
    - assessment_questions
    - attachments
    description: An individual learning unit within a chapter, which can contain text,
      video, PDF, or assessment content.
  CoursesAssessmentQuestionTypes:
    type: string
    enum:
    - short_answer
    - true_false
    - multiple_choice
    - multiple_select
    description: The available types for an assessment question
  EmbedTypes:
    type: string
    enum:
    - youtube
    - loom
    description: The type of embed for a lesson
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
  LessonVisibilities:
    type: string
    enum:
    - visible
    - hidden
    description: The available visibilities for a lesson. Determines how / whether
      a lesson is visible to users.
  MuxAssetStatuses:
    type: string
    enum:
    - uploading
    - created
    - ready
    description: Mux asset statuses
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
  "/course_lessons/{id}":
    get:
      tags:
      - Course lessons
      operationId: retrieveCourseLesson
      summary: Retrieve course lesson
      description: |-
        Retrieves the details of an existing course lesson.

        Required permissions:
         - `courses:read`
      parameters:
      - name: id
        in: path
        required: true
        description: The unique identifier of the lesson to retrieve.
        schema:
          type: string
          example: lesn_xxxxxxxxxxxxx
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
                "$ref": "#/components/schemas/CourseLesson"
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

          const lesson = await client.courseLessons.retrieve('lesn_xxxxxxxxxxxxx');

          console.log(lesson.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          lesson = client.course_lessons.retrieve(
              "lesn_xxxxxxxxxxxxx",
          )
          print(lesson.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          lesson = whop.course_lessons.retrieve("lesn_xxxxxxxxxxxxx")

          puts(lesson)
components:
  schemas:
    CourseLesson:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the lesson.
          example: lesn_xxxxxxxxxxxxx
        title:
          type: string
          description: The display name of the lesson shown to students. Maximum 120
            characters.
          example: Understanding Candlestick Patterns
        order:
          type: integer
          description: The sort position of this lesson within its parent chapter,
            starting from zero.
          example: 42
        lesson_type:
          "$ref": "#/components/schemas/LessonTypes"
          description: 'The content format of this lesson. One of: text, video, pdf,
            multi, quiz, knowledge_check.'
        visibility:
          "$ref": "#/components/schemas/LessonVisibilities"
          description: 'The visibility setting that controls whether this lesson appears
            to students. One of: visible, hidden.'
        content:
          type:
          - string
          - 'null'
          description: The Markdown content body of the lesson. Null if the lesson
            has no text content.
          example: In this lesson, we will cover the basics of technical analysis...
        days_from_course_start_until_unlock:
          type:
          - integer
          - 'null'
          description: The number of days after a student starts the course before
            this lesson becomes accessible. Null if the lesson is available immediately.
          example: 42
        embed_type:
          oneOf:
          - "$ref": "#/components/schemas/EmbedTypes"
          - type: 'null'
          description: 'The platform type for the embedded video. One of: youtube,
            loom. Null if the lesson has no embed.'
        embed_id:
          type:
          - string
          - 'null'
          description: The external video identifier for embedded video lessons, such
            as a YouTube video ID or Loom share ID. Null if the lesson has no embed.
          example: dQw4w9WgXcQ
        created_at:
          type: string
          format: date-time
          description: The datetime the lesson was created.
          example: '2023-12-01T05:00:00.401Z'
        thumbnail:
          type:
          - object
          - 'null'
          properties:
            url:
              type:
              - string
              - 'null'
              description: A pre-optimized URL for rendering this attachment on the
                client. This should be used for displaying attachments in apps.
              example: https://media.whop.com/abc123/optimized.jpg
          required:
          - url
          description: The thumbnail image displayed on lesson cards and previews.
            Null if no thumbnail has been uploaded.
        video_asset:
          type:
          - object
          - 'null'
          properties:
            id:
              type: string
              description: The unique identifier for the mux asset.
              example: mux_xxxxxxxxxxxxxx
            asset_id:
              type:
              - string
              - 'null'
              description: The Mux-provided ID of the asset
            playback_id:
              type:
              - string
              - 'null'
              description: The public playback ID of the Mux asset
            signed_playback_id:
              type:
              - string
              - 'null'
              description: The signed playback ID of the Mux asset
            status:
              "$ref": "#/components/schemas/MuxAssetStatuses"
              description: The status of the Mux asset
            audio_only:
              type: boolean
              description: Whether this asset contains only audio
            duration_seconds:
              type:
              - integer
              - 'null'
              description: The duration of the video in seconds
              example: 42
            signed_video_playback_token:
              type:
              - string
              - 'null'
              description: The signed video playback token of the Mux asset
            signed_thumbnail_playback_token:
              type:
              - string
              - 'null'
              description: The signed thumbnail playback token of the Mux asset
            signed_storyboard_playback_token:
              type:
              - string
              - 'null'
              description: The signed storyboard playback token of the Mux asset
            created_at:
              type: string
              format: date-time
              description: The datetime the mux asset was created.
              example: '2023-12-01T05:00:00.401Z'
            updated_at:
              type: string
              format: date-time
              description: The datetime the mux asset was last updated.
              example: '2023-12-01T05:00:00.401Z'
            finished_uploading_at:
              type:
              - string
              - 'null'
              format: date-time
              description: The time at which the video finished uploading
              example: '2023-12-01T05:00:00.401Z'
          required:
          - id
          - asset_id
          - playback_id
          - signed_playback_id
          - status
          - audio_only
          - duration_seconds
          - signed_video_playback_token
          - signed_thumbnail_playback_token
          - signed_storyboard_playback_token
          - created_at
          - updated_at
          - finished_uploading_at
          description: The Mux video asset for video-type lessons, used for streaming
            playback. Null if this lesson has no hosted video.
        main_pdf:
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
          description: The primary PDF document for PDF-type lessons. Null if this
            lesson is not a PDF lesson or no PDF has been uploaded.
        assessment_questions:
          type: array
          items:
            type: object
            properties:
              id:
                type: string
                description: The unique identifier for the assessment question.
                example: crsaq_xxxxxxxxxxxx
              question_text:
                type: string
                description: The text of the question
              question_type:
                "$ref": "#/components/schemas/CoursesAssessmentQuestionTypes"
                description: The type of the question
              correct_answer:
                type:
                - string
                - 'null'
                description: The correct answer for the question. Used for short answer
                  questions. Only visible to admins (users with courses:update permission)
              order:
                type: integer
                description: The order of the question within its lesson
                example: 42
              created_at:
                type: string
                format: date-time
                description: The datetime the assessment question was created.
                example: '2023-12-01T05:00:00.401Z'
              image:
                type:
                - object
                - 'null'
                properties:
                  id:
                    type: string
                    description: Represents a unique identifier that is Base64 obfuscated.
                      It is often used to refetch an object or as key for a cache.
                      The ID type appears in a JSON response as a String; however,
                      it is not intended to be human-readable. When expected as an
                      input type, any string (such as `"VXNlci0xMA=="`) or integer
                      (such as `4`) input value will be accepted as an ID.
                  filename:
                    type:
                    - string
                    - 'null'
                    description: The original filename of the uploaded attachment,
                      including its file extension.
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
                    description: A pre-optimized URL for rendering this attachment
                      on the client. This should be used for displaying attachments
                      in apps.
                    example: https://media.whop.com/abc123/optimized.jpg
                required:
                - id
                - filename
                - content_type
                - url
                description: Optional image attachment for the question
              options:
                type: array
                items:
                  type: object
                  properties:
                    id:
                      type: string
                      description: The unique identifier for the assessment question
                        option.
                      example: crsaqo_xxxxxxxxxxx
                    option_text:
                      type: string
                      description: The text of the answer option
                    is_correct:
                      type:
                      - boolean
                      - 'null'
                      description: Whether this option is a correct answer. Only visible
                        to admins (users with courses:update permission)
                    order:
                      type: integer
                      description: The order of this option within the question
                      example: 42
                  required:
                  - id
                  - option_text
                  - is_correct
                  - order
                  description: An answer option for a multiple choice or multiple
                    select assessment question
                description: The answer options for multiple choice/select questions
            required:
            - id
            - question_text
            - question_type
            - correct_answer
            - order
            - created_at
            - image
            - options
            description: An assessment question in a course quiz or knowledge check
          description: The list of questions for quiz or knowledge check lessons.
            Empty for non-assessment lesson types.
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
          description: All supplementary files attached to this lesson returned as
            a flat array rather than a paginated connection.
      required:
      - id
      - title
      - order
      - lesson_type
      - visibility
      - content
      - days_from_course_start_until_unlock
      - embed_type
      - embed_id
      - created_at
      - thumbnail
      - video_asset
      - main_pdf
      - assessment_questions
      - attachments
      description: An individual learning unit within a chapter, which can contain
        text, video, PDF, or assessment content.
    CoursesAssessmentQuestionTypes:
      type: string
      enum:
      - short_answer
      - true_false
      - multiple_choice
      - multiple_select
      description: The available types for an assessment question
    EmbedTypes:
      type: string
      enum:
      - youtube
      - loom
      description: The type of embed for a lesson
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
    LessonVisibilities:
      type: string
      enum:
      - visible
      - hidden
      description: The available visibilities for a lesson. Determines how / whether
        a lesson is visible to users.
    MuxAssetStatuses:
      type: string
      enum:
      - uploading
      - created
      - ready
      description: Mux asset statuses
```
