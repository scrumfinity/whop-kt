# GET /stats/describe — Describe stats

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `GET`
- **Path:** `/stats/describe`
- **Operation ID:** `describeStats`
- **Tags:** `Stats`
- **Required bearer scopes:** `stats:read`

## Description

Describe available stats schema. Without resource returns root nodes and metrics. With resource returns node columns, associations, and available metrics.

Required permissions:
 - `stats:read`

## Parameters

| Name | In | Required | Type | Description | Example |
|---|---|---:|---|---|---|
| `resource` | query | no | `string \| null` | Resource path using : as separator (e.g., 'receipts', 'payments:membership', 'receipts:gross_revenue'). |  |
| `company_id` | query | no | `string \| null` | Scope query to a specific company. | biz_xxxxxxxxxxxxxx |
| `user_id` | query | no | `string \| null` | Scope query to a specific user. | user_xxxxxxxxxxxxx |

## Request body

_No request body._

## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `object \| null \| object \| null \| object \| null \| object \| null` |
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
oneOf:
- type:
  - object
  - 'null'
  properties:
    typename:
      type: string
      const: DescribeRoot
      description: The typename of this object
    nodes:
      type: array
      items:
        type: string
        description: Represents textual data as UTF-8 character sequences. This type
          is most often used by GraphQL to represent free-form human-readable text.
      description: Available root nodes.
    views:
      type: array
      items:
        type: string
        description: Represents textual data as UTF-8 character sequences. This type
          is most often used by GraphQL to represent free-form human-readable text.
      description: Available API resource views.
    metrics:
      type: array
      items:
        type: object
        properties:
          name:
            type: string
            description: The metric name.
          node_path:
            type: string
            description: The node path this metric operates on.
          supported_engines:
            type: array
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            description: Query engines that support this metric.
        required:
        - name
        - node_path
        - supported_engines
        description: A metric available for querying.
      description: Available metrics.
    debug:
      type:
      - object
      - 'null'
      properties:
        request_id:
          type:
          - string
          - 'null'
          description: Unique request identifier for debugging.
      required:
      - request_id
      description: Debug information.
  required:
  - typename
  - nodes
  - views
  - metrics
  - debug
  description: Root schema description showing available nodes, views, and metrics.
  title: DescribeRoot
- type:
  - object
  - 'null'
  properties:
    typename:
      type: string
      const: DescribeNode
      description: The typename of this object
    node:
      type: string
      description: The node path being described.
    engine:
      type: string
      description: The query engine being used.
    columns:
      type: array
      items:
        type: string
        description: Represents textual data as UTF-8 character sequences. This type
          is most often used by GraphQL to represent free-form human-readable text.
      description: Available columns.
    sortable_columns:
      type: array
      items:
        type: string
        description: Represents textual data as UTF-8 character sequences. This type
          is most often used by GraphQL to represent free-form human-readable text.
      description: Columns that can be used for sorting.
    associations:
      type: array
      items:
        type: object
        properties:
          name:
            type: string
            description: The association name.
          type:
            type: string
            description: The type (belongs_to, has_many, has_one, event, namespace).
          model:
            type:
            - string
            - 'null'
            description: The associated model class name (for model associations).
          path:
            type:
            - string
            - 'null'
            description: The full path (for event associations).
          event_name:
            type:
            - string
            - 'null'
            description: The event name (for event type).
        required:
        - name
        - type
        - model
        - path
        - event_name
        description: An association or child path available for navigation.
      description: Available associations or child paths.
    metrics:
      type: array
      items:
        type: object
        properties:
          name:
            type: string
            description: The metric name.
          node_path:
            type: string
            description: The node path this metric operates on.
          supported_engines:
            type: array
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            description: Query engines that support this metric.
        required:
        - name
        - node_path
        - supported_engines
        description: A metric available for querying.
      description: Available metrics for this node.
    sample:
      type:
      - array
      - 'null'
      items:
        type: object
        additionalProperties: true
        description: Represents untyped JSON
      description: Sample data rows.
    debug:
      type:
      - object
      - 'null'
      properties:
        request_id:
          type:
          - string
          - 'null'
          description: Unique request identifier for debugging.
      required:
      - request_id
      description: Debug information.
  required:
  - typename
  - node
  - engine
  - columns
  - sortable_columns
  - associations
  - metrics
  - sample
  - debug
  description: Description of a node (model) including its columns and associations.
  title: DescribeNode
- type:
  - object
  - 'null'
  properties:
    typename:
      type: string
      const: DescribeMetric
      description: The typename of this object
    metric:
      type: string
      description: The metric name.
    node:
      type: string
      description: The node path this metric operates on.
    engine:
      type: string
      description: The query engine being used.
    timestamp_column:
      type: string
      description: The timestamp column used for time filtering.
    supported_engines:
      type: array
      items:
        type: string
        description: Represents textual data as UTF-8 character sequences. This type
          is most often used by GraphQL to represent free-form human-readable text.
      description: Query engines that support this metric.
    filterable_columns:
      type: array
      items:
        type: string
        description: Represents textual data as UTF-8 character sequences. This type
          is most often used by GraphQL to represent free-form human-readable text.
      description: Columns that can be used for filtering.
    breakdownable_columns:
      type: array
      items:
        type: string
        description: Represents textual data as UTF-8 character sequences. This type
          is most often used by GraphQL to represent free-form human-readable text.
      description: Columns that can be used for breakdowns.
    sql:
      type:
      - string
      - 'null'
      description: The generated SQL query.
    debug:
      type:
      - object
      - 'null'
      properties:
        request_id:
          type:
          - string
          - 'null'
          description: Unique request identifier for debugging.
      required:
      - request_id
      description: Debug information.
  required:
  - typename
  - metric
  - node
  - engine
  - timestamp_column
  - supported_engines
  - filterable_columns
  - breakdownable_columns
  - sql
  - debug
  description: Description of a metric including its configuration and SQL.
  title: DescribeMetric
- type:
  - object
  - 'null'
  properties:
    typename:
      type: string
      const: DescribeView
      description: The typename of this object
    view:
      type: string
      description: The view name being described.
    resource:
      type: string
      description: The API resource name.
    model:
      type: string
      description: The underlying model class.
    engine:
      type: string
      description: The query engine being used.
    columns:
      type: array
      items:
        type: string
        description: Represents textual data as UTF-8 character sequences. This type
          is most often used by GraphQL to represent free-form human-readable text.
      description: Available columns.
    sortable_columns:
      type: array
      items:
        type: string
        description: Represents textual data as UTF-8 character sequences. This type
          is most often used by GraphQL to represent free-form human-readable text.
      description: Columns that can be used for sorting.
    associations:
      type: array
      items:
        type: object
        properties:
          name:
            type: string
            description: The association name.
          type:
            type: string
            description: The type (belongs_to, has_many, has_one, event, namespace).
          model:
            type:
            - string
            - 'null'
            description: The associated model class name (for model associations).
          path:
            type:
            - string
            - 'null'
            description: The full path (for event associations).
          event_name:
            type:
            - string
            - 'null'
            description: The event name (for event type).
        required:
        - name
        - type
        - model
        - path
        - event_name
        description: An association or child path available for navigation.
      description: Available associations.
    metrics:
      type: array
      items:
        type: object
        properties:
          name:
            type: string
            description: The metric name.
          node_path:
            type: string
            description: The node path this metric operates on.
          supported_engines:
            type: array
            items:
              type: string
              description: Represents textual data as UTF-8 character sequences. This
                type is most often used by GraphQL to represent free-form human-readable
                text.
            description: Query engines that support this metric.
        required:
        - name
        - node_path
        - supported_engines
        description: A metric available for querying.
      description: Available metrics.
    sample:
      type:
      - array
      - 'null'
      items:
        type: object
        additionalProperties: true
        description: Represents untyped JSON
      description: Sample data rows.
    debug:
      type:
      - object
      - 'null'
      properties:
        request_id:
          type:
          - string
          - 'null'
          description: Unique request identifier for debugging.
      required:
      - request_id
      description: Debug information.
  required:
  - typename
  - view
  - resource
  - model
  - engine
  - columns
  - sortable_columns
  - associations
  - metrics
  - sample
  - debug
  description: Description of an API resource view including its columns and associations.
  title: DescribeView
discriminator:
  propertyName: typename
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

const response = await client.stats.describe();

console.log(response);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
response = client.stats.describe()
print(response)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

response = whop.stats.describe

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
  "/stats/describe":
    get:
      tags:
      - Stats
      operationId: describeStats
      summary: Describe stats
      description: |-
        Describe available stats schema. Without resource returns root nodes and metrics. With resource returns node columns, associations, and available metrics.

        Required permissions:
         - `stats:read`
      parameters:
      - name: resource
        in: query
        required: false
        schema:
          type:
          - string
          - 'null'
          description: 'Resource path using : as separator (e.g., ''receipts'', ''payments:membership'',
            ''receipts:gross_revenue'').'
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
                oneOf:
                - type:
                  - object
                  - 'null'
                  properties:
                    typename:
                      type: string
                      const: DescribeRoot
                      description: The typename of this object
                    nodes:
                      type: array
                      items:
                        type: string
                        description: Represents textual data as UTF-8 character sequences.
                          This type is most often used by GraphQL to represent free-form
                          human-readable text.
                      description: Available root nodes.
                    views:
                      type: array
                      items:
                        type: string
                        description: Represents textual data as UTF-8 character sequences.
                          This type is most often used by GraphQL to represent free-form
                          human-readable text.
                      description: Available API resource views.
                    metrics:
                      type: array
                      items:
                        type: object
                        properties:
                          name:
                            type: string
                            description: The metric name.
                          node_path:
                            type: string
                            description: The node path this metric operates on.
                          supported_engines:
                            type: array
                            items:
                              type: string
                              description: Represents textual data as UTF-8 character
                                sequences. This type is most often used by GraphQL
                                to represent free-form human-readable text.
                            description: Query engines that support this metric.
                        required:
                        - name
                        - node_path
                        - supported_engines
                        description: A metric available for querying.
                      description: Available metrics.
                    debug:
                      type:
                      - object
                      - 'null'
                      properties:
                        request_id:
                          type:
                          - string
                          - 'null'
                          description: Unique request identifier for debugging.
                      required:
                      - request_id
                      description: Debug information.
                  required:
                  - typename
                  - nodes
                  - views
                  - metrics
                  - debug
                  description: Root schema description showing available nodes, views,
                    and metrics.
                  title: DescribeRoot
                - type:
                  - object
                  - 'null'
                  properties:
                    typename:
                      type: string
                      const: DescribeNode
                      description: The typename of this object
                    node:
                      type: string
                      description: The node path being described.
                    engine:
                      type: string
                      description: The query engine being used.
                    columns:
                      type: array
                      items:
                        type: string
                        description: Represents textual data as UTF-8 character sequences.
                          This type is most often used by GraphQL to represent free-form
                          human-readable text.
                      description: Available columns.
                    sortable_columns:
                      type: array
                      items:
                        type: string
                        description: Represents textual data as UTF-8 character sequences.
                          This type is most often used by GraphQL to represent free-form
                          human-readable text.
                      description: Columns that can be used for sorting.
                    associations:
                      type: array
                      items:
                        type: object
                        properties:
                          name:
                            type: string
                            description: The association name.
                          type:
                            type: string
                            description: The type (belongs_to, has_many, has_one,
                              event, namespace).
                          model:
                            type:
                            - string
                            - 'null'
                            description: The associated model class name (for model
                              associations).
                          path:
                            type:
                            - string
                            - 'null'
                            description: The full path (for event associations).
                          event_name:
                            type:
                            - string
                            - 'null'
                            description: The event name (for event type).
                        required:
                        - name
                        - type
                        - model
                        - path
                        - event_name
                        description: An association or child path available for navigation.
                      description: Available associations or child paths.
                    metrics:
                      type: array
                      items:
                        type: object
                        properties:
                          name:
                            type: string
                            description: The metric name.
                          node_path:
                            type: string
                            description: The node path this metric operates on.
                          supported_engines:
                            type: array
                            items:
                              type: string
                              description: Represents textual data as UTF-8 character
                                sequences. This type is most often used by GraphQL
                                to represent free-form human-readable text.
                            description: Query engines that support this metric.
                        required:
                        - name
                        - node_path
                        - supported_engines
                        description: A metric available for querying.
                      description: Available metrics for this node.
                    sample:
                      type:
                      - array
                      - 'null'
                      items:
                        type: object
                        additionalProperties: true
                        description: Represents untyped JSON
                      description: Sample data rows.
                    debug:
                      type:
                      - object
                      - 'null'
                      properties:
                        request_id:
                          type:
                          - string
                          - 'null'
                          description: Unique request identifier for debugging.
                      required:
                      - request_id
                      description: Debug information.
                  required:
                  - typename
                  - node
                  - engine
                  - columns
                  - sortable_columns
                  - associations
                  - metrics
                  - sample
                  - debug
                  description: Description of a node (model) including its columns
                    and associations.
                  title: DescribeNode
                - type:
                  - object
                  - 'null'
                  properties:
                    typename:
                      type: string
                      const: DescribeMetric
                      description: The typename of this object
                    metric:
                      type: string
                      description: The metric name.
                    node:
                      type: string
                      description: The node path this metric operates on.
                    engine:
                      type: string
                      description: The query engine being used.
                    timestamp_column:
                      type: string
                      description: The timestamp column used for time filtering.
                    supported_engines:
                      type: array
                      items:
                        type: string
                        description: Represents textual data as UTF-8 character sequences.
                          This type is most often used by GraphQL to represent free-form
                          human-readable text.
                      description: Query engines that support this metric.
                    filterable_columns:
                      type: array
                      items:
                        type: string
                        description: Represents textual data as UTF-8 character sequences.
                          This type is most often used by GraphQL to represent free-form
                          human-readable text.
                      description: Columns that can be used for filtering.
                    breakdownable_columns:
                      type: array
                      items:
                        type: string
                        description: Represents textual data as UTF-8 character sequences.
                          This type is most often used by GraphQL to represent free-form
                          human-readable text.
                      description: Columns that can be used for breakdowns.
                    sql:
                      type:
                      - string
                      - 'null'
                      description: The generated SQL query.
                    debug:
                      type:
                      - object
                      - 'null'
                      properties:
                        request_id:
                          type:
                          - string
                          - 'null'
                          description: Unique request identifier for debugging.
                      required:
                      - request_id
                      description: Debug information.
                  required:
                  - typename
                  - metric
                  - node
                  - engine
                  - timestamp_column
                  - supported_engines
                  - filterable_columns
                  - breakdownable_columns
                  - sql
                  - debug
                  description: Description of a metric including its configuration
                    and SQL.
                  title: DescribeMetric
                - type:
                  - object
                  - 'null'
                  properties:
                    typename:
                      type: string
                      const: DescribeView
                      description: The typename of this object
                    view:
                      type: string
                      description: The view name being described.
                    resource:
                      type: string
                      description: The API resource name.
                    model:
                      type: string
                      description: The underlying model class.
                    engine:
                      type: string
                      description: The query engine being used.
                    columns:
                      type: array
                      items:
                        type: string
                        description: Represents textual data as UTF-8 character sequences.
                          This type is most often used by GraphQL to represent free-form
                          human-readable text.
                      description: Available columns.
                    sortable_columns:
                      type: array
                      items:
                        type: string
                        description: Represents textual data as UTF-8 character sequences.
                          This type is most often used by GraphQL to represent free-form
                          human-readable text.
                      description: Columns that can be used for sorting.
                    associations:
                      type: array
                      items:
                        type: object
                        properties:
                          name:
                            type: string
                            description: The association name.
                          type:
                            type: string
                            description: The type (belongs_to, has_many, has_one,
                              event, namespace).
                          model:
                            type:
                            - string
                            - 'null'
                            description: The associated model class name (for model
                              associations).
                          path:
                            type:
                            - string
                            - 'null'
                            description: The full path (for event associations).
                          event_name:
                            type:
                            - string
                            - 'null'
                            description: The event name (for event type).
                        required:
                        - name
                        - type
                        - model
                        - path
                        - event_name
                        description: An association or child path available for navigation.
                      description: Available associations.
                    metrics:
                      type: array
                      items:
                        type: object
                        properties:
                          name:
                            type: string
                            description: The metric name.
                          node_path:
                            type: string
                            description: The node path this metric operates on.
                          supported_engines:
                            type: array
                            items:
                              type: string
                              description: Represents textual data as UTF-8 character
                                sequences. This type is most often used by GraphQL
                                to represent free-form human-readable text.
                            description: Query engines that support this metric.
                        required:
                        - name
                        - node_path
                        - supported_engines
                        description: A metric available for querying.
                      description: Available metrics.
                    sample:
                      type:
                      - array
                      - 'null'
                      items:
                        type: object
                        additionalProperties: true
                        description: Represents untyped JSON
                      description: Sample data rows.
                    debug:
                      type:
                      - object
                      - 'null'
                      properties:
                        request_id:
                          type:
                          - string
                          - 'null'
                          description: Unique request identifier for debugging.
                      required:
                      - request_id
                      description: Debug information.
                  required:
                  - typename
                  - view
                  - resource
                  - model
                  - engine
                  - columns
                  - sortable_columns
                  - associations
                  - metrics
                  - sample
                  - debug
                  description: Description of an API resource view including its columns
                    and associations.
                  title: DescribeView
                discriminator:
                  propertyName: typename
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

          const response = await client.stats.describe();

          console.log(response);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          response = client.stats.describe()
          print(response)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          response = whop.stats.describe

          puts(response)
```
