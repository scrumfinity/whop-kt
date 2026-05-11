# POST /course_lessons/{lesson_id}/submit_assessment — Submit assessment course lesson

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `POST`
- **Path:** `/course_lessons/{lesson_id}/submit_assessment`
- **Operation ID:** `submitAssessmentCourseLesson`
- **Tags:** `Course lessons`
- **Required bearer scopes:** _None documented_

## Description

Submit answers for a quiz or knowledge check lesson and receive a graded result.

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `lesson_id` | path | yes | `string` | The unique identifier of the quiz or knowledge check lesson to submit answers for (e.g., "lesn_xxxxxxxxxxxxx"). | lesn_xxxxxxxxxxxxx |

## Request body

Required: **yes**
Content types: `application/json`

### `application/json` request schema

```yaml
type: object
properties:
  answers:
    type: array
    items:
      type: object
      properties:
        answer_text:
          type:
          - string
          - 'null'
          description: The text answer provided by the user (for short answer questions)
        question_id:
          type: string
          description: The ID of the question being answered
          example: crsaq_xxxxxxxxxxxx
        selected_option_ids:
          type:
          - array
          - 'null'
          items:
            type: string
            description: Represents a unique identifier that is Base64 obfuscated.
              It is often used to refetch an object or as key for a cache. The ID
              type appears in a JSON response as a String; however, it is not intended
              to be human-readable. When expected as an input type, any string (such
              as `"VXNlci0xMA=="`) or integer (such as `4`) input value will be accepted
              as an ID.
          description: The IDs of the selected options (for multiple choice/select
            questions)
      required:
      - question_id
      description: Input for a single question's answer in an assessment submission
    description: The list of answers to submit for each assessment question.
required:
- answers
description: Parameters for SubmitAssessment
```


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
  id:
    type: string
    description: The unique identifier for the assessment result.
    example: crsar_xxxxxxxxxxxx
  created_at:
    type: string
    format: date-time
    description: The datetime the assessment result was created.
    example: '2023-12-01T05:00:00.401Z'
  updated_at:
    type: string
    format: date-time
    description: The datetime the assessment result was last updated.
    example: '2023-12-01T05:00:00.401Z'
  result_grade:
    type: number
    description: The grade achieved on the assessment
    example: 6.9
  result_correct:
    type: integer
    description: The number of correct answers
    example: 42
  result_question_count:
    type: integer
    description: The total number of questions in the assessment
    example: 42
  result_passing_grade:
    type: boolean
    description: Whether the user achieved a passing grade
  result_graded_questions:
    type: object
    additionalProperties: true
    description: Array of graded questions with details
  score_percent:
    type: number
    description: The percentage score achieved on the assessment
    example: 6.9
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
    description: The user who took the assessment
  lesson:
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
    required:
    - id
    - title
    description: The lesson this assessment result is for
required:
- id
- created_at
- updated_at
- result_grade
- result_correct
- result_question_count
- result_passing_grade
- result_graded_questions
- score_percent
- user
- lesson
description: The result of a user's assessment attempt
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

const response = await client.courseLessons.submitAssessment('lesn_xxxxxxxxxxxxx', {
  answers: [{ question_id: 'crsaq_xxxxxxxxxxxx' }],
});

console.log(response.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
response = client.course_lessons.submit_assessment(
    lesson_id="lesn_xxxxxxxxxxxxx",
    answers=[{
        "question_id": "crsaq_xxxxxxxxxxxx"
    }],
)
print(response.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

response = whop.course_lessons.submit_assessment("lesn_xxxxxxxxxxxxx", answers: [{question_id: "crsaq_xxxxxxxxxxxx"}])

puts(response)
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
  "/course_lessons/{lesson_id}/submit_assessment":
    post:
      tags:
      - Course lessons
      operationId: submitAssessmentCourseLesson
      summary: Submit assessment course lesson
      description: Submit answers for a quiz or knowledge check lesson and receive
        a graded result.
      parameters:
      - name: lesson_id
        in: path
        required: true
        description: The unique identifier of the quiz or knowledge check lesson to
          submit answers for (e.g., "lesn_xxxxxxxxxxxxx").
        schema:
          type: string
          example: lesn_xxxxxxxxxxxxx
      x-group-title: Courses
      security:
      - bearerAuth: []
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                type: object
                properties:
                  id:
                    type: string
                    description: The unique identifier for the assessment result.
                    example: crsar_xxxxxxxxxxxx
                  created_at:
                    type: string
                    format: date-time
                    description: The datetime the assessment result was created.
                    example: '2023-12-01T05:00:00.401Z'
                  updated_at:
                    type: string
                    format: date-time
                    description: The datetime the assessment result was last updated.
                    example: '2023-12-01T05:00:00.401Z'
                  result_grade:
                    type: number
                    description: The grade achieved on the assessment
                    example: 6.9
                  result_correct:
                    type: integer
                    description: The number of correct answers
                    example: 42
                  result_question_count:
                    type: integer
                    description: The total number of questions in the assessment
                    example: 42
                  result_passing_grade:
                    type: boolean
                    description: Whether the user achieved a passing grade
                  result_graded_questions:
                    type: object
                    additionalProperties: true
                    description: Array of graded questions with details
                  score_percent:
                    type: number
                    description: The percentage score achieved on the assessment
                    example: 6.9
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
                        description: The user's display name shown on their public
                          profile.
                        example: John Doe
                      username:
                        type: string
                        description: The user's unique username shown on their public
                          profile.
                        example: johndoe42
                    required:
                    - id
                    - name
                    - username
                    description: The user who took the assessment
                  lesson:
                    type: object
                    properties:
                      id:
                        type: string
                        description: The unique identifier for the lesson.
                        example: lesn_xxxxxxxxxxxxx
                      title:
                        type: string
                        description: The display name of the lesson shown to students.
                          Maximum 120 characters.
                        example: Understanding Candlestick Patterns
                    required:
                    - id
                    - title
                    description: The lesson this assessment result is for
                required:
                - id
                - created_at
                - updated_at
                - result_grade
                - result_correct
                - result_question_count
                - result_passing_grade
                - result_graded_questions
                - score_percent
                - user
                - lesson
                description: The result of a user's assessment attempt
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
                answers:
                  type: array
                  items:
                    type: object
                    properties:
                      answer_text:
                        type:
                        - string
                        - 'null'
                        description: The text answer provided by the user (for short
                          answer questions)
                      question_id:
                        type: string
                        description: The ID of the question being answered
                        example: crsaq_xxxxxxxxxxxx
                      selected_option_ids:
                        type:
                        - array
                        - 'null'
                        items:
                          type: string
                          description: Represents a unique identifier that is Base64
                            obfuscated. It is often used to refetch an object or as
                            key for a cache. The ID type appears in a JSON response
                            as a String; however, it is not intended to be human-readable.
                            When expected as an input type, any string (such as `"VXNlci0xMA=="`)
                            or integer (such as `4`) input value will be accepted
                            as an ID.
                        description: The IDs of the selected options (for multiple
                          choice/select questions)
                    required:
                    - question_id
                    description: Input for a single question's answer in an assessment
                      submission
                  description: The list of answers to submit for each assessment question.
              required:
              - answers
              description: Parameters for SubmitAssessment
      x-codeSamples:
      - lang: JavaScript
        source: |-
          import Whop from '@whop/sdk';

          const client = new Whop({
            apiKey: process.env['WHOP_API_KEY'], // This is the default and can be omitted
          });

          const response = await client.courseLessons.submitAssessment('lesn_xxxxxxxxxxxxx', {
            answers: [{ question_id: 'crsaq_xxxxxxxxxxxx' }],
          });

          console.log(response.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          response = client.course_lessons.submit_assessment(
              lesson_id="lesn_xxxxxxxxxxxxx",
              answers=[{
                  "question_id": "crsaq_xxxxxxxxxxxx"
              }],
          )
          print(response.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          response = whop.course_lessons.submit_assessment("lesn_xxxxxxxxxxxxx", answers: [{question_id: "crsaq_xxxxxxxxxxxx"}])

          puts(response)
```
