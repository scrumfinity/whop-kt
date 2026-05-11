# POST /files — Create file

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `POST`
- **Path:** `/files`
- **Operation ID:** `createFile`
- **Tags:** `Files`
- **Required bearer scopes:** _None documented_

## Description

Create a new file record and receive a presigned URL for uploading content to S3.

## Parameters

_No path, query, header, or cookie parameters._

## Request body

Required: **yes**
Content types: `application/json`

### `application/json` request schema

```yaml
type: object
properties:
  filename:
    type: string
    description: The name of the file including its extension (e.g., "photo.png" or
      "document.pdf").
  visibility:
    oneOf:
    - "$ref": "#/components/schemas/FileVisibility"
    - type: 'null'
    description: Controls whether the file is publicly accessible via CDN or requires
      authentication. Defaults to public.
required:
- filename
description: Parameters for CreateFile
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
    description: The unique identifier for the file.
    example: file_xxxxxxxxxxxxx
  upload_status:
    "$ref": "#/components/schemas/UploadStatuses"
    description: The current upload status of the file (e.g., pending, ready).
  filename:
    type:
    - string
    - 'null'
    description: The original filename of the uploaded file, including its file extension.
    example: document.pdf
  content_type:
    type:
    - string
    - 'null'
    description: The MIME type of the uploaded file (e.g., image/jpeg, video/mp4,
      audio/mpeg).
    example: image/jpeg
  size:
    type:
    - string
    - 'null'
    description: The file size in bytes. Null if the file has not finished uploading.
    example: '1727606400000'
  url:
    type:
    - string
    - 'null'
    description: The URL for accessing the file. For public files, this is a permanent
      CDN URL. For private files, this is a signed URL that expires. Null if the file
      has not finished uploading.
  visibility:
    "$ref": "#/components/schemas/FileVisibility"
    description: Whether the file is publicly accessible or requires authentication.
  upload_url:
    type:
    - string
    - 'null'
    description: The presigned URL to upload the file contents to. Only present in
      the response from the create mutation.
    example: https://media.whop.com/uploads/presigned
  upload_headers:
    type:
    - object
    - 'null'
    additionalProperties: true
    description: Headers to include in the upload request. Only present in the response
      from the create mutation.
required:
- id
- upload_status
- filename
- content_type
- size
- url
- visibility
- upload_url
- upload_headers
description: A file that has been uploaded or is pending upload.
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

const file = await client.files.create({ filename: 'filename' });

console.log(file.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
file = client.files.create(
    filename="filename",
)
print(file.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

file = whop.files.create(filename: "filename")

puts(file)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  FileVisibility:
    type: string
    enum:
    - public
    - private
    description: Controls whether an uploaded file is publicly accessible or requires
      authentication to access.
  UploadStatuses:
    type: string
    enum:
    - pending
    - processing
    - ready
    - failed
    description: The upload status of a file
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
  "/files":
    post:
      tags:
      - Files
      operationId: createFile
      summary: Create file
      description: Create a new file record and receive a presigned URL for uploading
        content to S3.
      parameters: []
      x-group-title: Developer
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
                    description: The unique identifier for the file.
                    example: file_xxxxxxxxxxxxx
                  upload_status:
                    "$ref": "#/components/schemas/UploadStatuses"
                    description: The current upload status of the file (e.g., pending,
                      ready).
                  filename:
                    type:
                    - string
                    - 'null'
                    description: The original filename of the uploaded file, including
                      its file extension.
                    example: document.pdf
                  content_type:
                    type:
                    - string
                    - 'null'
                    description: The MIME type of the uploaded file (e.g., image/jpeg,
                      video/mp4, audio/mpeg).
                    example: image/jpeg
                  size:
                    type:
                    - string
                    - 'null'
                    description: The file size in bytes. Null if the file has not
                      finished uploading.
                    example: '1727606400000'
                  url:
                    type:
                    - string
                    - 'null'
                    description: The URL for accessing the file. For public files,
                      this is a permanent CDN URL. For private files, this is a signed
                      URL that expires. Null if the file has not finished uploading.
                  visibility:
                    "$ref": "#/components/schemas/FileVisibility"
                    description: Whether the file is publicly accessible or requires
                      authentication.
                  upload_url:
                    type:
                    - string
                    - 'null'
                    description: The presigned URL to upload the file contents to.
                      Only present in the response from the create mutation.
                    example: https://media.whop.com/uploads/presigned
                  upload_headers:
                    type:
                    - object
                    - 'null'
                    additionalProperties: true
                    description: Headers to include in the upload request. Only present
                      in the response from the create mutation.
                required:
                - id
                - upload_status
                - filename
                - content_type
                - size
                - url
                - visibility
                - upload_url
                - upload_headers
                description: A file that has been uploaded or is pending upload.
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
                filename:
                  type: string
                  description: The name of the file including its extension (e.g.,
                    "photo.png" or "document.pdf").
                visibility:
                  oneOf:
                  - "$ref": "#/components/schemas/FileVisibility"
                  - type: 'null'
                  description: Controls whether the file is publicly accessible via
                    CDN or requires authentication. Defaults to public.
              required:
              - filename
              description: Parameters for CreateFile
      x-codeSamples:
      - lang: JavaScript
        source: |-
          import Whop from '@whop/sdk';

          const client = new Whop({
            apiKey: process.env['WHOP_API_KEY'], // This is the default and can be omitted
          });

          const file = await client.files.create({ filename: 'filename' });

          console.log(file.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          file = client.files.create(
              filename="filename",
          )
          print(file.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          file = whop.files.create(filename: "filename")

          puts(file)
components:
  schemas:
    FileVisibility:
      type: string
      enum:
      - public
      - private
      description: Controls whether an uploaded file is publicly accessible or requires
        authentication to access.
    UploadStatuses:
      type: string
      enum:
      - pending
      - processing
      - ready
      - failed
      description: The upload status of a file
```
