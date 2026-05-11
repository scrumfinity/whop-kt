# GET /course_lesson_interactions/{id} — Retrieve course lesson interaction

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/course_lesson_interactions/{id}`
- **Operation ID:** `retrieveCourseLessonInteraction`
- **Tags:** `Course lesson interactions`
- **Required bearer scopes:** `courses:read`, `course_analytics:read`

## Description

Retrieves the details of an existing course lesson interaction.

Required permissions:
 - `courses:read`
 - `course_analytics:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `id` | path | yes | `string` | The unique identifier of the lesson interaction to retrieve. | crsli_xxxxxxxxxxxx |

## Request body

_No request body._

## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `CourseLessonInteraction` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/CourseLessonInteraction"
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

const courseLessonInteraction = await client.courseLessonInteractions.retrieve(
  'crsli_xxxxxxxxxxxx',
);

console.log(courseLessonInteraction.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
course_lesson_interaction = client.course_lesson_interactions.retrieve(
    "crsli_xxxxxxxxxxxx",
)
print(course_lesson_interaction.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

course_lesson_interaction = whop.course_lesson_interactions.retrieve("crsli_xxxxxxxxxxxx")

puts(course_lesson_interaction)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  CourseLessonInteraction:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the lesson interaction.
        example: crsli_xxxxxxxxxxxx
      completed:
        type: boolean
        description: Whether the user has finished this lesson.
      created_at:
        type: string
        format: date-time
        description: The datetime the lesson interaction was created.
        example: '2023-12-01T05:00:00.401Z'
      lesson:
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
          chapter:
            type: object
            properties:
              id:
                type: string
                description: The unique identifier for the chapter.
                example: chap_xxxxxxxxxxxxx
            required:
            - id
            description: The parent chapter that contains this lesson.
        required:
        - id
        - title
        - chapter
        description: The lesson that this progress record belongs to.
      user:
        type: object
        properties:
          id:
            type: string
            description: The unique identifier for the user.
            example: user_xxxxxxxxxxxxx
          name:
            type:
            - string
            - 'null'
            description: The user's display name shown on their public profile.
            example: John Doe
          username:
            type: string
            description: The user's unique username shown on their public profile.
            example: johndoe42
        required:
        - id
        - name
        - username
        description: The user whose progress is being tracked.
      course:
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
            description: The display name of the course shown to students. Null if
              no title has been set.
            example: Introduction to Technical Analysis
          experience:
            type: object
            properties:
              id:
                type: string
                description: The unique identifier for the experience.
                example: exp_xxxxxxxxxxxxxx
            required:
            - id
            description: The parent experience that this course belongs to.
        required:
        - id
        - title
        - experience
        description: The course that contains the tracked lesson.
    required:
    - id
    - completed
    - created_at
    - lesson
    - user
    - course
    description: A record of a user's progress on a specific lesson, tracking whether
      they have completed it.
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
  "/course_lesson_interactions/{id}":
    get:
      tags:
      - Course lesson interactions
      operationId: retrieveCourseLessonInteraction
      summary: Retrieve course lesson interaction
      description: |-
        Retrieves the details of an existing course lesson interaction.

        Required permissions:
         - `courses:read`
         - `course_analytics:read`
      parameters:
      - name: id
        in: path
        required: true
        description: The unique identifier of the lesson interaction to retrieve.
        schema:
          type: string
          example: crsli_xxxxxxxxxxxx
      x-group-title: Courses
      security:
      - bearerAuth:
        - courses:read
        - course_analytics:read
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/CourseLessonInteraction"
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

          const courseLessonInteraction = await client.courseLessonInteractions.retrieve(
            'crsli_xxxxxxxxxxxx',
          );

          console.log(courseLessonInteraction.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          course_lesson_interaction = client.course_lesson_interactions.retrieve(
              "crsli_xxxxxxxxxxxx",
          )
          print(course_lesson_interaction.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          course_lesson_interaction = whop.course_lesson_interactions.retrieve("crsli_xxxxxxxxxxxx")

          puts(course_lesson_interaction)
components:
  schemas:
    CourseLessonInteraction:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the lesson interaction.
          example: crsli_xxxxxxxxxxxx
        completed:
          type: boolean
          description: Whether the user has finished this lesson.
        created_at:
          type: string
          format: date-time
          description: The datetime the lesson interaction was created.
          example: '2023-12-01T05:00:00.401Z'
        lesson:
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
            chapter:
              type: object
              properties:
                id:
                  type: string
                  description: The unique identifier for the chapter.
                  example: chap_xxxxxxxxxxxxx
              required:
              - id
              description: The parent chapter that contains this lesson.
          required:
          - id
          - title
          - chapter
          description: The lesson that this progress record belongs to.
        user:
          type: object
          properties:
            id:
              type: string
              description: The unique identifier for the user.
              example: user_xxxxxxxxxxxxx
            name:
              type:
              - string
              - 'null'
              description: The user's display name shown on their public profile.
              example: John Doe
            username:
              type: string
              description: The user's unique username shown on their public profile.
              example: johndoe42
          required:
          - id
          - name
          - username
          description: The user whose progress is being tracked.
        course:
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
              description: The display name of the course shown to students. Null
                if no title has been set.
              example: Introduction to Technical Analysis
            experience:
              type: object
              properties:
                id:
                  type: string
                  description: The unique identifier for the experience.
                  example: exp_xxxxxxxxxxxxxx
              required:
              - id
              description: The parent experience that this course belongs to.
          required:
          - id
          - title
          - experience
          description: The course that contains the tracked lesson.
      required:
      - id
      - completed
      - created_at
      - lesson
      - user
      - course
      description: A record of a user's progress on a specific lesson, tracking whether
        they have completed it.
```
