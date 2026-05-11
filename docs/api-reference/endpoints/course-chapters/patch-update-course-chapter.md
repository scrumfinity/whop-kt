# PATCH /course_chapters/{id} — Update course chapter

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `PATCH`
- **Path:** `/course_chapters/{id}`
- **Operation ID:** `updateCourseChapter`
- **Tags:** `Course chapters`
- **Required bearer scopes:** `courses:update`

## Description

Update a chapter's title within a course.

Required permissions:
 - `courses:update`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `id` | path | yes | `string` | The unique identifier of the chapter to update (e.g., "chap_XXXXX"). | chap_xxxxxxxxxxxxx |

## Request body

Required: **yes**
Content types: `application/json`

### `application/json` request schema

```yaml
type: object
properties:
  title:
    type: string
    description: 'The new display title of the chapter (e.g., "Module 1: Introduction").'
required:
- title
description: Parameters for UpdateChapter
```


## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `CourseChapter` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/CourseChapter"
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

const courseChapter = await client.courseChapters.update('chap_xxxxxxxxxxxxx', { title: 'title' });

console.log(courseChapter.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
course_chapter = client.course_chapters.update(
    id="chap_xxxxxxxxxxxxx",
    title="title",
)
print(course_chapter.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

course_chapter = whop.course_chapters.update("chap_xxxxxxxxxxxxx", title: "title")

puts(course_chapter)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  CourseChapter:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the chapter.
        example: chap_xxxxxxxxxxxxx
      title:
        type: string
        description: The display name of the chapter shown to students. Maximum 150
          characters.
        example: Getting Started
      order:
        type: integer
        description: The sort position of this chapter within its parent course, starting
          from zero.
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
            title:
              type: string
              description: The display name of the lesson shown to students. Maximum
                120 characters.
              example: Understanding Candlestick Patterns
            order:
              type: integer
              description: The sort position of this lesson within its parent chapter,
                starting from zero.
              example: 42
          required:
          - id
          - title
          - order
          description: An individual learning unit within a chapter, which can contain
            text, video, PDF, or assessment content.
        description: An ordered list of lessons in this chapter, sorted by display
          position. Hidden lessons are excluded for non-admin users.
    required:
    - id
    - title
    - order
    - lessons
    description: A grouping of related lessons within a course, used to organize content
      into sections.
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
  "/course_chapters/{id}":
    patch:
      tags:
      - Course chapters
      operationId: updateCourseChapter
      summary: Update course chapter
      description: |-
        Update a chapter's title within a course.

        Required permissions:
         - `courses:update`
      parameters:
      - name: id
        in: path
        required: true
        description: The unique identifier of the chapter to update (e.g., "chap_XXXXX").
        schema:
          type: string
          example: chap_xxxxxxxxxxxxx
      x-group-title: Courses
      security:
      - bearerAuth:
        - courses:update
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/CourseChapter"
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
                title:
                  type: string
                  description: 'The new display title of the chapter (e.g., "Module
                    1: Introduction").'
              required:
              - title
              description: Parameters for UpdateChapter
      x-codeSamples:
      - lang: JavaScript
        source: |-
          import Whop from '@whop/sdk';

          const client = new Whop({
            apiKey: process.env['WHOP_API_KEY'], // This is the default and can be omitted
          });

          const courseChapter = await client.courseChapters.update('chap_xxxxxxxxxxxxx', { title: 'title' });

          console.log(courseChapter.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          course_chapter = client.course_chapters.update(
              id="chap_xxxxxxxxxxxxx",
              title="title",
          )
          print(course_chapter.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          course_chapter = whop.course_chapters.update("chap_xxxxxxxxxxxxx", title: "title")

          puts(course_chapter)
components:
  schemas:
    CourseChapter:
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
              title:
                type: string
                description: The display name of the lesson shown to students. Maximum
                  120 characters.
                example: Understanding Candlestick Patterns
              order:
                type: integer
                description: The sort position of this lesson within its parent chapter,
                  starting from zero.
                example: 42
            required:
            - id
            - title
            - order
            description: An individual learning unit within a chapter, which can contain
              text, video, PDF, or assessment content.
          description: An ordered list of lessons in this chapter, sorted by display
            position. Hidden lessons are excluded for non-admin users.
      required:
      - id
      - title
      - order
      - lessons
      description: A grouping of related lessons within a course, used to organize
        content into sections.
```
