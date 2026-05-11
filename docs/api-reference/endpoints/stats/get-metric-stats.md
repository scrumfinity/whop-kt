# GET /stats/metric — Metric stats

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/stats/metric`
- **Operation ID:** `metricStats`
- **Tags:** `Stats`
- **Required bearer scopes:** `stats:read`

## Description

Query an aggregated metric. Returns data grouped by period with optional breakdowns.

Required permissions:
 - `stats:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `resource` | query | yes | `string` | Metric resource using : as separator (e.g., 'receipts:gross_revenue', 'members:new_users'). | receipts:gross_revenue |
| `granularity` | query | no | `string \| null` | Time granularity (daily, weekly, monthly). |  |
| `breakdowns` | query | no | `array<string>` | Columns to break down the metric by. |  |
| `filters` | query | no | `object \| null` | Key-value pairs to filter the data. |  |
| `time_zone` | query | no | `string \| null` | IANA timezone for period bucketing (e.g. 'America/New_York'). Defaults to UTC. Only applies to ClickHouse metrics. |  |
| `from` | query | no | `string \| null / date-time` | Start of time range (ISO 8601 timestamp). | 2023-12-01T05:00:00.401Z |
| `to` | query | no | `string \| null / date-time` | End of time range (ISO 8601 timestamp). | 2023-12-01T05:00:00.401Z |
| `company_id` | query | no | `string \| null` | Scope query to a specific company. | biz_xxxxxxxxxxxxxx |
| `user_id` | query | no | `string \| null` | Scope query to a specific user. | user_xxxxxxxxxxxxx |

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
  columns:
    type:
    - array
    - 'null'
    items:
      type: string
      description: Represents textual data as UTF-8 character sequences. This type
        is most often used by GraphQL to represent free-form human-readable text.
    description: Column names in the order they appear in each data row.
  data:
    type:
    - array
    - 'null'
    items:
      type: object
      additionalProperties: true
      description: Represents untyped JSON
    description: Array of data rows, where each row is an array of values matching
      the columns order.
  debug:
    type:
    - object
    - 'null'
    properties:
      engine:
        type:
        - string
        - 'null'
        description: The query engine used.
      request_id:
        type:
        - string
        - 'null'
        description: Unique request identifier for debugging.
      sql:
        type:
        - string
        - 'null'
        description: The generated SQL query (with IDs sanitized).
    required:
    - engine
    - request_id
    - sql
    description: Debug information including engine and SQL.
  node:
    type:
    - string
    - 'null'
    description: The node path that was queried.
  pagination:
    type:
    - object
    - 'null'
    properties:
      next_cursor:
        type:
        - string
        - 'null'
        description: Cursor for the next page of results.
    required:
    - next_cursor
    description: Pagination information.
  typename:
    type: string
    const: Result
    description: The typename of this object
required:
- columns
- data
- debug
- node
- pagination
- typename
description: Result from a stats query (raw, metric, or SQL).
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

const response = await client.stats.queryMetric({ resource: 'receipts:gross_revenue' });

console.log(response.columns);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
response = client.stats.query_metric(
    resource="receipts:gross_revenue",
)
print(response.columns)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

response = whop.stats.query_metric(resource: "receipts:gross_revenue")

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
  "/stats/metric":
    get:
      tags:
      - Stats
      operationId: metricStats
      summary: Metric stats
      description: |-
        Query an aggregated metric. Returns data grouped by period with optional breakdowns.

        Required permissions:
         - `stats:read`
      parameters:
      - name: resource
        in: query
        required: true
        schema:
          type: string
          example: 'receipts:gross_revenue'
          description: 'Metric resource using : as separator (e.g., ''receipts:gross_revenue'',
            ''members:new_users'').'
        explode: true
        style: form
      - name: granularity
        in: query
        required: false
        schema:
          type:
          - string
          - 'null'
          description: Time granularity (daily, weekly, monthly).
        explode: true
        style: form
      - name: breakdowns
        in: query
        required: false
        schema:
          type:
          - array
          - 'null'
          items:
            type: string
            description: Represents textual data as UTF-8 character sequences. This
              type is most often used by GraphQL to represent free-form human-readable
              text.
          description: Columns to break down the metric by.
        explode: true
        style: form
      - name: filters
        in: query
        required: false
        schema:
          type:
          - object
          - 'null'
          additionalProperties: true
          description: Key-value pairs to filter the data.
        explode: true
        style: deepObject
      - name: time_zone
        in: query
        required: false
        schema:
          type:
          - string
          - 'null'
          description: IANA timezone for period bucketing (e.g. 'America/New_York').
            Defaults to UTC. Only applies to ClickHouse metrics.
        explode: true
        style: form
      - name: from
        in: query
        required: false
        schema:
          type:
          - string
          - 'null'
          format: date-time
          description: Start of time range (ISO 8601 timestamp).
          example: '2023-12-01T05:00:00.401Z'
        explode: true
        style: form
      - name: to
        in: query
        required: false
        schema:
          type:
          - string
          - 'null'
          format: date-time
          description: End of time range (ISO 8601 timestamp).
          example: '2023-12-01T05:00:00.401Z'
        explode: true
        style: form
      - name: company_id
        in: query
        required: false
        schema:
          type:
          - string
          - 'null'
          description: Scope query to a specific company.
          example: biz_xxxxxxxxxxxxxx
        explode: true
        style: form
      - name: user_id
        in: query
        required: false
        schema:
          type:
          - string
          - 'null'
          description: Scope query to a specific user.
          example: user_xxxxxxxxxxxxx
        explode: true
        style: form
      x-group-title: Stats
      security:
      - bearerAuth:
        - stats:read
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                type: object
                properties:
                  columns:
                    type:
                    - array
                    - 'null'
                    items:
                      type: string
                      description: Represents textual data as UTF-8 character sequences.
                        This type is most often used by GraphQL to represent free-form
                        human-readable text.
                    description: Column names in the order they appear in each data
                      row.
                  data:
                    type:
                    - array
                    - 'null'
                    items:
                      type: object
                      additionalProperties: true
                      description: Represents untyped JSON
                    description: Array of data rows, where each row is an array of
                      values matching the columns order.
                  debug:
                    type:
                    - object
                    - 'null'
                    properties:
                      engine:
                        type:
                        - string
                        - 'null'
                        description: The query engine used.
                      request_id:
                        type:
                        - string
                        - 'null'
                        description: Unique request identifier for debugging.
                      sql:
                        type:
                        - string
                        - 'null'
                        description: The generated SQL query (with IDs sanitized).
                    required:
                    - engine
                    - request_id
                    - sql
                    description: Debug information including engine and SQL.
                  node:
                    type:
                    - string
                    - 'null'
                    description: The node path that was queried.
                  pagination:
                    type:
                    - object
                    - 'null'
                    properties:
                      next_cursor:
                        type:
                        - string
                        - 'null'
                        description: Cursor for the next page of results.
                    required:
                    - next_cursor
                    description: Pagination information.
                  typename:
                    type: string
                    const: Result
                    description: The typename of this object
                required:
                - columns
                - data
                - debug
                - node
                - pagination
                - typename
                description: Result from a stats query (raw, metric, or SQL).
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

          const response = await client.stats.queryMetric({ resource: 'receipts:gross_revenue' });

          console.log(response.columns);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          response = client.stats.query_metric(
              resource="receipts:gross_revenue",
          )
          print(response.columns)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          response = whop.stats.query_metric(resource: "receipts:gross_revenue")

          puts(response)
```
