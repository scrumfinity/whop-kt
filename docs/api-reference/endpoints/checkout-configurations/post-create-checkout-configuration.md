# POST /checkout_configurations — Create checkout configuration

Generated from Whop's official documented OpenAPI source: [openapi.documented.yml](../../openapi.documented.yml).

- **Base URL:** `https://api.whop.com/api/v1`
- **Method:** `POST`
- **Path:** `/checkout_configurations`
- **Operation ID:** `createCheckoutConfiguration`
- **Tags:** `Checkout configurations`
- **Required bearer scopes:** `checkout_configuration:create`, `plan:create`, `access_pass:create`, `access_pass:update`, `checkout_configuration:basic:read`

## Description

Creates a new checkout configuration

Required permissions:
 - `checkout_configuration:create`
 - `plan:create`
 - `access_pass:create`
 - `access_pass:update`
 - `checkout_configuration:basic:read`

## Parameters

_No path, query, header, or cookie parameters._

## Request body

Required: **yes**
Content types: `application/json`

### `application/json` request schema

```yaml
oneOf:
- type: object
  properties:
    plan:
      type: object
      properties:
        company_id:
          type: string
          description: The company the plan should be created for.
          example: biz_xxxxxxxxxxxxxx
        adaptive_pricing_enabled:
          type:
          - boolean
          - 'null'
          description: Whether this plan accepts local currency payments via adaptive
            pricing.
        application_fee_amount:
          type:
          - number
          - 'null'
          description: The application fee amount collected by the platform from this
            connected account. Provided as a number in dollars (e.g., 5.00 for $5.00).
            Must be less than the total payment amount. Only valid for connected accounts
            with a parent company.
          example: 6.9
        billing_period:
          type:
          - integer
          - 'null'
          description: The interval in days at which the plan charges (renewal plans).
            For example, 30 for monthly billing.
          example: 42
        currency:
          "$ref": "#/components/schemas/Currencies"
          description: The respective currency identifier for the plan.
        custom_fields:
          type:
          - array
          - 'null'
          items:
            type: object
            properties:
              field_type:
                "$ref": "#/components/schemas/CustomFieldTypes"
                description: The type of the custom field.
              id:
                type:
                - string
                - 'null'
                description: The ID of the custom field (if being updated)
              name:
                type: string
                description: The name of the custom field.
              order:
                type:
                - integer
                - 'null'
                description: The order of the field.
                example: 42
              placeholder:
                type:
                - string
                - 'null'
                description: The placeholder value of the field.
              required:
                type:
                - boolean
                - 'null'
                description: Whether or not the field is required.
            required:
            - field_type
            - name
          description: An array of custom field objects.
        description:
          type:
          - string
          - 'null'
          description: The description of the plan.
        expiration_days:
          type:
          - integer
          - 'null'
          description: The number of days until the membership expires (for expiration-based
            plans). For example, 365 for a one-year access pass.
          example: 42
        force_create_new_plan:
          type:
          - boolean
          - 'null'
          description: Whether to force the creation of a new plan even if one with
            the same attributes already exists.
        image:
          type:
          - object
          - 'null'
          properties:
            id:
              type: string
              description: The ID of an existing file object.
          required:
          - id
          description: An image for the plan. This will be visible on the product
            page to customers.
          title: FileInputWithId
        initial_price:
          type:
          - number
          - 'null'
          description: An additional amount charged upon first purchase. Provided
            as a number in dollars (e.g., 10.00 for $10.00).
          example: 6.9
        internal_notes:
          type:
          - string
          - 'null'
          description: A personal description or notes section for the business.
        override_tax_type:
          oneOf:
          - "$ref": "#/components/schemas/TaxTypes"
          - type: 'null'
          description: An override for tax to specify for this specific plan.
        payment_method_configuration:
          type:
          - object
          - 'null'
          properties:
            disabled:
              type: array
              items:
                "$ref": "#/components/schemas/PaymentMethodTypes"
              description: An array of payment method identifiers that are explicitly
                disabled. Only applies if the include_platform_defaults is true.
            enabled:
              type: array
              items:
                "$ref": "#/components/schemas/PaymentMethodTypes"
              description: An array of payment method identifiers that are explicitly
                enabled. This means these payment methods will be shown on checkout.
                Example use case is to only enable a specific payment method like
                cashapp, or extending the platform defaults with additional methods.
            include_platform_defaults:
              type:
              - boolean
              - 'null'
              description: Whether Whop's platform default payment method enablement
                settings are included in this configuration. The full list of default
                payment methods can be found in the documentation at docs.whop.com/payments.
          required:
          - disabled
          - enabled
          description: The explicit payment method configuration for the plan. If
            not provided, the platform or company's defaults will apply.
        plan_type:
          oneOf:
          - "$ref": "#/components/schemas/PlanTypes"
          - type: 'null'
          description: Indicates if the plan is a one time payment or recurring.
        product:
          type:
          - object
          - 'null'
          properties:
            collect_shipping_address:
              type:
              - boolean
              - 'null'
              description: Whether or not to collect shipping information at checkout
                from the customer.
            custom_statement_descriptor:
              type:
              - string
              - 'null'
              description: 'The custom statement descriptor for the product i.e. WHOP*SPORTS,
                must be between 5 and 22 characters, contain at least one letter,
                and not contain any of the following characters: <, >, \, '', "'
            description:
              type:
              - string
              - 'null'
              description: A written description of the product.
            external_identifier:
              type: string
              description: A unique ID used to find or create a product. When provided
                during creation, we will look for an existing product with this external
                identifier — if found, it will be updated; otherwise, a new product
                will be created.
            global_affiliate_percentage:
              type:
              - number
              - 'null'
              description: The percentage of the revenue that goes to the global affiliate
                program.
              example: 6.9
            global_affiliate_status:
              oneOf:
              - "$ref": "#/components/schemas/GlobalAffiliateStatuses"
              - type: 'null'
              description: The status of the global affiliate program for this product.
            headline:
              type:
              - string
              - 'null'
              description: The headline of the product.
            product_tax_code_id:
              type:
              - string
              - 'null'
              description: The ID of the product tax code to apply to this product.
              example: ptc_xxxxxxxxxxxxxx
            redirect_purchase_url:
              type:
              - string
              - 'null'
              description: The URL to redirect the customer to after a purchase.
            route:
              type:
              - string
              - 'null'
              description: The route of the product.
            title:
              type: string
              description: The title of the product.
            visibility:
              oneOf:
              - "$ref": "#/components/schemas/Visibility"
              - type: 'null'
              description: This product will/will not be displayed publicly - default
                hidden.
          required:
          - external_identifier
          - title
          description: Pass this object to create a new product for this plan. We
            will use the product external identifier to find or create an existing
            product.
        product_id:
          type:
          - string
          - 'null'
          description: The product the plan is related to. Either this or product
            is required.
          example: prod_xxxxxxxxxxxxx
        release_method:
          oneOf:
          - "$ref": "#/components/schemas/ReleaseMethod"
          - type: 'null'
          description: This is the release method the business uses to sell this plan.
        renewal_price:
          type:
          - number
          - 'null'
          description: The amount the customer is charged every billing period. Provided
            as a number in dollars (e.g., 9.99 for $9.99/period).
          example: 6.9
        split_pay_required_payments:
          type:
          - integer
          - 'null'
          description: The number of payments required before pausing the subscription.
          example: 42
        stock:
          type:
          - integer
          - 'null'
          description: The number of units available for purchase. If not provided,
            stock is unlimited.
          example: 42
        title:
          type:
          - string
          - 'null'
          description: The title of the plan. This will be visible on the product
            page to customers.
        trial_period_days:
          type:
          - integer
          - 'null'
          description: The number of free trial days added before a renewal plan.
          example: 42
        visibility:
          oneOf:
          - "$ref": "#/components/schemas/Visibility"
          - type: 'null'
          description: Shows or hides the plan from public/business view.
      required:
      - company_id
      - currency
      description: The plan attributes to create a new plan inline for this checkout
        configuration.
    affiliate_code:
      type:
      - string
      - 'null'
      description: An affiliate tracking code to attribute the checkout to a specific
        affiliate.
    allow_promo_codes:
      type:
      - boolean
      - 'null'
      description: Whether the checkout should show the promo code input field and
        accept promo codes. Defaults to true.
    checkout_styling:
      type:
      - object
      - 'null'
      properties:
        background_color:
          type:
          - string
          - 'null'
          description: 'A hex color code for the checkout page background, applied
            to the order summary panel (e.g. #F4F4F5).'
        border_style:
          oneOf:
          - "$ref": "#/components/schemas/CheckoutShapes"
          - type: 'null'
          description: The border style for buttons and inputs.
        button_color:
          type:
          - string
          - 'null'
          description: 'A hex color code for the button color (e.g. #FF5733).'
        font_family:
          oneOf:
          - "$ref": "#/components/schemas/CheckoutFonts"
          - type: 'null'
          description: The font family for the checkout page.
      required: []
      description: Checkout styling overrides for this session. Overrides plan and
        company defaults.
    currency:
      oneOf:
      - "$ref": "#/components/schemas/Currencies"
      - type: 'null'
      description: The currency for the checkout when in setup mode. Controls which
        currency-specific payment methods are available. Defaults to USD when in setup
        mode.
    metadata:
      type:
      - object
      - 'null'
      additionalProperties: true
      description: Custom key-value metadata to attach to the checkout configuration.
    mode:
      type: string
      const: payment
    payment_method_configuration:
      type:
      - object
      - 'null'
      properties:
        disabled:
          type: array
          items:
            "$ref": "#/components/schemas/PaymentMethodTypes"
          description: An array of payment method identifiers that are explicitly
            disabled. Only applies if the include_platform_defaults is true.
        enabled:
          type: array
          items:
            "$ref": "#/components/schemas/PaymentMethodTypes"
          description: An array of payment method identifiers that are explicitly
            enabled. This means these payment methods will be shown on checkout. Example
            use case is to only enable a specific payment method like cashapp, or
            extending the platform defaults with additional methods.
        include_platform_defaults:
          type:
          - boolean
          - 'null'
          description: Whether Whop's platform default payment method enablement settings
            are included in this configuration. The full list of default payment methods
            can be found in the documentation at docs.whop.com/payments.
      required:
      - disabled
      - enabled
      description: The explicit payment method configuration for the checkout session.
        Only applies to setup mode. If not provided, the platform or company defaults
        will apply.
    redirect_url:
      type:
      - string
      - 'null'
      description: The URL to redirect the user to after checkout is completed.
    source_url:
      type:
      - string
      - 'null'
      description: The URL of the page where the checkout is being initiated from.
  required:
  - plan
  description: Autogenerated input type of CreateCheckoutSession
  title: CreateCheckoutSessionInputModePaymentWithPlan
- type: object
  properties:
    plan_id:
      type: string
      description: The unique identifier of an existing plan to use for this checkout
        configuration.
      example: plan_xxxxxxxxxxxxx
    affiliate_code:
      type:
      - string
      - 'null'
      description: An affiliate tracking code to attribute the checkout to a specific
        affiliate.
    allow_promo_codes:
      type:
      - boolean
      - 'null'
      description: Whether the checkout should show the promo code input field and
        accept promo codes. Defaults to true.
    checkout_styling:
      type:
      - object
      - 'null'
      properties:
        background_color:
          type:
          - string
          - 'null'
          description: 'A hex color code for the checkout page background, applied
            to the order summary panel (e.g. #F4F4F5).'
        border_style:
          oneOf:
          - "$ref": "#/components/schemas/CheckoutShapes"
          - type: 'null'
          description: The border style for buttons and inputs.
        button_color:
          type:
          - string
          - 'null'
          description: 'A hex color code for the button color (e.g. #FF5733).'
        font_family:
          oneOf:
          - "$ref": "#/components/schemas/CheckoutFonts"
          - type: 'null'
          description: The font family for the checkout page.
      required: []
      description: Checkout styling overrides for this session. Overrides plan and
        company defaults.
    currency:
      oneOf:
      - "$ref": "#/components/schemas/Currencies"
      - type: 'null'
      description: The currency for the checkout when in setup mode. Controls which
        currency-specific payment methods are available. Defaults to USD when in setup
        mode.
    metadata:
      type:
      - object
      - 'null'
      additionalProperties: true
      description: Custom key-value metadata to attach to the checkout configuration.
    mode:
      type: string
      const: payment
    payment_method_configuration:
      type:
      - object
      - 'null'
      properties:
        disabled:
          type: array
          items:
            "$ref": "#/components/schemas/PaymentMethodTypes"
          description: An array of payment method identifiers that are explicitly
            disabled. Only applies if the include_platform_defaults is true.
        enabled:
          type: array
          items:
            "$ref": "#/components/schemas/PaymentMethodTypes"
          description: An array of payment method identifiers that are explicitly
            enabled. This means these payment methods will be shown on checkout. Example
            use case is to only enable a specific payment method like cashapp, or
            extending the platform defaults with additional methods.
        include_platform_defaults:
          type:
          - boolean
          - 'null'
          description: Whether Whop's platform default payment method enablement settings
            are included in this configuration. The full list of default payment methods
            can be found in the documentation at docs.whop.com/payments.
      required:
      - disabled
      - enabled
      description: The explicit payment method configuration for the checkout session.
        Only applies to setup mode. If not provided, the platform or company defaults
        will apply.
    redirect_url:
      type:
      - string
      - 'null'
      description: The URL to redirect the user to after checkout is completed.
    source_url:
      type:
      - string
      - 'null'
      description: The URL of the page where the checkout is being initiated from.
  required:
  - plan_id
  description: Autogenerated input type of CreateCheckoutSession
  title: CreateCheckoutSessionInputModePaymentWithPlanId
- type: object
  properties:
    company_id:
      type: string
      description: The unique identifier of the company to create the checkout configuration
        for. Only required in setup mode.
      example: biz_xxxxxxxxxxxxxx
    allow_promo_codes:
      type:
      - boolean
      - 'null'
      description: Whether the checkout should show the promo code input field and
        accept promo codes. Defaults to true.
    checkout_styling:
      type:
      - object
      - 'null'
      properties:
        background_color:
          type:
          - string
          - 'null'
          description: 'A hex color code for the checkout page background, applied
            to the order summary panel (e.g. #F4F4F5).'
        border_style:
          oneOf:
          - "$ref": "#/components/schemas/CheckoutShapes"
          - type: 'null'
          description: The border style for buttons and inputs.
        button_color:
          type:
          - string
          - 'null'
          description: 'A hex color code for the button color (e.g. #FF5733).'
        font_family:
          oneOf:
          - "$ref": "#/components/schemas/CheckoutFonts"
          - type: 'null'
          description: The font family for the checkout page.
      required: []
      description: Checkout styling overrides for this session. Overrides plan and
        company defaults.
    currency:
      oneOf:
      - "$ref": "#/components/schemas/Currencies"
      - type: 'null'
      description: The currency for the checkout when in setup mode. Controls which
        currency-specific payment methods are available. Defaults to USD when in setup
        mode.
    metadata:
      type:
      - object
      - 'null'
      additionalProperties: true
      description: Custom key-value metadata to attach to the checkout configuration.
    mode:
      type: string
      const: setup
    payment_method_configuration:
      type:
      - object
      - 'null'
      properties:
        disabled:
          type: array
          items:
            "$ref": "#/components/schemas/PaymentMethodTypes"
          description: An array of payment method identifiers that are explicitly
            disabled. Only applies if the include_platform_defaults is true.
        enabled:
          type: array
          items:
            "$ref": "#/components/schemas/PaymentMethodTypes"
          description: An array of payment method identifiers that are explicitly
            enabled. This means these payment methods will be shown on checkout. Example
            use case is to only enable a specific payment method like cashapp, or
            extending the platform defaults with additional methods.
        include_platform_defaults:
          type:
          - boolean
          - 'null'
          description: Whether Whop's platform default payment method enablement settings
            are included in this configuration. The full list of default payment methods
            can be found in the documentation at docs.whop.com/payments.
      required:
      - disabled
      - enabled
      description: The explicit payment method configuration for the checkout session.
        Only applies to setup mode. If not provided, the platform or company defaults
        will apply.
    redirect_url:
      type:
      - string
      - 'null'
      description: The URL to redirect the user to after checkout is completed.
    source_url:
      type:
      - string
      - 'null'
      description: The URL of the page where the checkout is being initiated from.
  required:
  - mode
  - company_id
  description: Autogenerated input type of CreateCheckoutSession
  title: CreateCheckoutSessionInputModeSetup
type: object
description: Parameters for CreateCheckoutSession
```


## Responses

| Status | Description | Content types | Schema |
|---|---|---|---|
| `200` | A successful response | `application/json` | `CheckoutConfiguration` |
| `400` | Bad request | `application/json` | `object` |
| `401` | Unauthorized | `application/json` | `object` |
| `403` | Forbidden | `application/json` | `object` |
| `404` | Not found | `application/json` | `object` |
| `422` | Verification required | `application/json` | `object` |
| `500` | Internal server error | `application/json` | `object` |

### `200` response schemas

#### `application/json`

```yaml
"$ref": "#/components/schemas/CheckoutConfiguration"
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

const checkoutConfiguration = await client.checkoutConfigurations.create({
  plan: { company_id: 'biz_xxxxxxxxxxxxxx', currency: 'usd' },
});

console.log(checkoutConfiguration.id);
```

### Python

```python
import os
from whop_sdk import Whop

client = Whop(
    api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
)
checkout_configuration = client.checkout_configurations.create(
    plan={
        "company_id": "biz_xxxxxxxxxxxxxx",
        "currency": "usd",
    },
)
print(checkout_configuration.id)
```

### Ruby

```ruby
require "whop_sdk"

whop = WhopSDK::Client.new(api_key: "My API Key")

checkout_configuration = whop.checkout_configurations.create(body: {plan: {company_id: "biz_xxxxxxxxxxxxxx", currency: :usd}})

puts(checkout_configuration)
```

## Referenced schemas

The schemas below are included so this endpoint file can be read offline without chasing `$ref` targets.

```yaml
schemas:
  CheckoutConfiguration:
    type: object
    properties:
      id:
        type: string
        description: The unique identifier for the checkout session.
        example: ch_xxxxxxxxxxxxxxx
      company_id:
        type: string
        description: The ID of the company to use for the checkout configuration
      mode:
        "$ref": "#/components/schemas/CheckoutModes"
        description: The mode of the checkout session.
      currency:
        oneOf:
        - "$ref": "#/components/schemas/Currencies"
        - type: 'null'
        description: The currency to use for the configuration when in 'setup' mode.
          This is used to target which currency specific payment methods are available.
          If not provided, it will default to 'usd' when in setup mode.
      plan:
        type:
        - object
        - 'null'
        properties:
          id:
            type: string
            description: The unique identifier for the plan.
            example: plan_xxxxxxxxxxxxx
          visibility:
            "$ref": "#/components/schemas/Visibility"
            description: Controls whether the plan is visible to customers. When set
              to 'hidden', the plan is only accessible via direct link.
          plan_type:
            "$ref": "#/components/schemas/PlanTypes"
            description: 'The billing model for this plan: ''renewal'' for recurring
              subscriptions or ''one_time'' for single payments.'
          release_method:
            "$ref": "#/components/schemas/ReleaseMethod"
            description: 'The method used to sell this plan: ''buy_now'' for immediate
              purchase or ''waitlist'' for waitlist-based access.'
          currency:
            "$ref": "#/components/schemas/Currencies"
            description: The currency used for all prices on this plan (e.g., 'usd',
              'eur'). All monetary amounts on the plan are denominated in this currency.
          billing_period:
            type:
            - integer
            - 'null'
            description: The number of days between each recurring charge. Null for
              one-time plans. For example, 30 for monthly or 365 for annual billing.
            example: 42
          expiration_days:
            type:
            - integer
            - 'null'
            description: The number of days until the membership expires (for expiration-based
              plans). For example, 365 for a one-year access pass.
            example: 42
          initial_price:
            type: number
            description: The initial purchase price in the plan's base_currency (e.g.,
              49.99 for $49.99). For one-time plans, this is the full price. For renewal
              plans, this is charged on top of the first renewal_price.
            example: 6.9
          renewal_price:
            type: number
            description: The recurring price charged every billing_period in the plan's
              base_currency (e.g., 9.99 for $9.99/period). Zero for one-time plans.
            example: 6.9
          trial_period_days:
            type:
            - integer
            - 'null'
            description: The number of free trial days before the first charge on
              a renewal plan. Null if no trial is configured or the current user has
              already used a trial for this plan.
            example: 42
          adaptive_pricing_enabled:
            type: boolean
            description: Whether the creator has turned on adaptive pricing for this
              plan. Raw setting — does not check processor compatibility or feature
              flags.
        required:
        - id
        - visibility
        - plan_type
        - release_method
        - currency
        - billing_period
        - expiration_days
        - initial_price
        - renewal_price
        - trial_period_days
        - adaptive_pricing_enabled
        description: The plan to use for the checkout configuration
      affiliate_code:
        type:
        - string
        - 'null'
        description: The affiliate code to use for the checkout configuration
      metadata:
        type:
        - object
        - 'null'
        additionalProperties: true
        description: The metadata to use for the checkout configuration
      redirect_url:
        type:
        - string
        - 'null'
        description: The URL to redirect the user to after the checkout configuration
          is created
      purchase_url:
        type: string
        description: A URL you can send to customers to complete a checkout. It looks
          like `/checkout/plan_xxxx?session={id}`
      allow_promo_codes:
        type: boolean
        description: Whether the checkout configuration allows promo codes. When false,
          the promo code input is hidden and promo codes are rejected.
      payment_method_configuration:
        type:
        - object
        - 'null'
        properties:
          enabled:
            type: array
            items:
              "$ref": "#/components/schemas/PaymentMethodTypes"
            description: An array of payment method identifiers that are explicitly
              enabled. This means these payment methods will be shown on checkout.
              Example use case is to only enable a specific payment method like cashapp,
              or extending the platform defaults with additional methods.
          disabled:
            type: array
            items:
              "$ref": "#/components/schemas/PaymentMethodTypes"
            description: An array of payment method identifiers that are explicitly
              disabled. Only applies if the include_platform_defaults is true.
          include_platform_defaults:
            type: boolean
            description: Whether Whop's platform default payment method enablement
              settings are included in this configuration. The full list of default
              payment methods can be found in the documentation at docs.whop.com/payments.
        required:
        - enabled
        - disabled
        - include_platform_defaults
        description: The explicit payment method configuration for the session, if
          any. This currently only works in 'setup' mode. Use the plan's payment_method_configuration
          for payment method.
    required:
    - id
    - company_id
    - mode
    - currency
    - plan
    - affiliate_code
    - metadata
    - redirect_url
    - purchase_url
    - allow_promo_codes
    - payment_method_configuration
    description: A checkout configuration is a reusable configuration for a checkout,
      including the plan, affiliate, and custom metadata. Payments and memberships
      created from a checkout session inherit its metadata.
  CheckoutFonts:
    type: string
    enum:
    - system
    - roboto
    - open_sans
    description: The different font families available for checkout pages.
  CheckoutModes:
    type: string
    enum:
    - payment
    - setup
    description: The different modes a checkout can be set to.
  CheckoutShapes:
    type: string
    enum:
    - rounded
    - pill
    - rectangular
    description: The different border-radius styles available for checkout pages.
  Currencies:
    type: string
    enum:
    - usd
    - sgd
    - inr
    - aud
    - brl
    - cad
    - dkk
    - eur
    - nok
    - gbp
    - sek
    - chf
    - hkd
    - huf
    - jpy
    - mxn
    - myr
    - pln
    - czk
    - nzd
    - aed
    - eth
    - ape
    - cop
    - ron
    - thb
    - bgn
    - idr
    - dop
    - php
    - try
    - krw
    - twd
    - vnd
    - pkr
    - clp
    - uyu
    - ars
    - zar
    - dzd
    - tnd
    - mad
    - kes
    - kwd
    - jod
    - all
    - xcd
    - amd
    - bsd
    - bhd
    - bob
    - bam
    - khr
    - crc
    - xof
    - egp
    - etb
    - gmd
    - ghs
    - gtq
    - gyd
    - ils
    - jmd
    - mop
    - mga
    - mur
    - mdl
    - mnt
    - nad
    - ngn
    - mkd
    - omr
    - pyg
    - pen
    - qar
    - rwf
    - sar
    - rsd
    - lkr
    - tzs
    - ttd
    - uzs
    - rub
    - btc
    - cny
    - usdt
    - kzt
    description: The available currencies on the platform
  CustomFieldTypes:
    type: string
    const: text
    description: The type of the custom field.
  GlobalAffiliateStatuses:
    type: string
    enum:
    - enabled
    - disabled
    description: The different statuses of the global affiliate program for a product.
  PaymentMethodTypes:
    type: string
    enum:
    - acss_debit
    - affirm
    - afterpay_clearpay
    - alipay
    - alma
    - amazon_pay
    - apple
    - apple_pay
    - au_bank_transfer
    - au_becs_debit
    - bacs_debit
    - bancolombia
    - bancontact
    - billie
    - bizum
    - blik
    - boleto
    - bre_b
    - ca_bank_transfer
    - capchase_pay
    - card
    - card_installments_three
    - card_installments_six
    - card_installments_twelve
    - cashapp
    - claritypay
    - coinbase
    - crypto
    - custom
    - customer_balance
    - demo_pay
    - efecty
    - eps
    - eu_bank_transfer
    - fpx
    - gb_bank_transfer
    - giropay
    - google_pay
    - gopay
    - grabpay
    - id_bank_transfer
    - ideal
    - interac
    - kakao_pay
    - klarna
    - klarna_pay_now
    - konbini
    - kr_card
    - kr_market
    - kriya
    - kueski
    - link
    - mb_way
    - m_pesa
    - mercado_pago
    - mobilepay
    - mondu
    - multibanco
    - naver_pay
    - nequi
    - netbanking
    - ng_bank
    - ng_bank_transfer
    - ng_card
    - ng_market
    - ng_ussd
    - ng_wallet
    - nz_bank_account
    - oxxo
    - p24
    - pago_efectivo
    - pse
    - pay_by_bank
    - payco
    - paynow
    - paypal
    - paypay
    - payto
    - pix
    - platform_balance
    - promptpay
    - qris
    - rechnung
    - revolut_pay
    - samsung_pay
    - satispay
    - scalapay
    - sencillito
    - sepa_debit
    - sequra
    - servipag
    - sezzle
    - shop_pay
    - shopeepay
    - sofort
    - south_korea_market
    - spei
    - splitit
    - sunbit
    - swish
    - tamara
    - twint
    - upi
    - us_bank_account
    - us_bank_transfer
    - venmo
    - vipps
    - webpay
    - wechat_pay
    - yape
    - zip
    - coinflow
    - unknown
    description: The different types of payment methods that can be used.
  PlanTypes:
    type: string
    enum:
    - renewal
    - one_time
    description: The type of plan that can be attached to a product
  ReleaseMethod:
    type: string
    enum:
    - buy_now
    - waitlist
    description: The methods of how a plan can be released.
  TaxTypes:
    type: string
    enum:
    - inclusive
    - exclusive
    - unspecified
    description: Whether or not the tax is included in a plan's price (or if it hasn't
      been set up)
  Visibility:
    type: string
    enum:
    - visible
    - hidden
    - archived
    - quick_link
    description: Visibility of a resource
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
  "/checkout_configurations":
    post:
      tags:
      - Checkout configurations
      operationId: createCheckoutConfiguration
      summary: Create checkout configuration
      description: |-
        Creates a new checkout configuration

        Required permissions:
         - `checkout_configuration:create`
         - `plan:create`
         - `access_pass:create`
         - `access_pass:update`
         - `checkout_configuration:basic:read`
      parameters: []
      x-group-title: Payins
      security:
      - bearerAuth:
        - checkout_configuration:create
        - plan:create
        - access_pass:create
        - access_pass:update
        - checkout_configuration:basic:read
      responses:
        '200':
          description: A successful response
          content:
            application/json:
              schema:
                "$ref": "#/components/schemas/CheckoutConfiguration"
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
              oneOf:
              - type: object
                properties:
                  plan:
                    type: object
                    properties:
                      company_id:
                        type: string
                        description: The company the plan should be created for.
                        example: biz_xxxxxxxxxxxxxx
                      adaptive_pricing_enabled:
                        type:
                        - boolean
                        - 'null'
                        description: Whether this plan accepts local currency payments
                          via adaptive pricing.
                      application_fee_amount:
                        type:
                        - number
                        - 'null'
                        description: The application fee amount collected by the platform
                          from this connected account. Provided as a number in dollars
                          (e.g., 5.00 for $5.00). Must be less than the total payment
                          amount. Only valid for connected accounts with a parent
                          company.
                        example: 6.9
                      billing_period:
                        type:
                        - integer
                        - 'null'
                        description: The interval in days at which the plan charges
                          (renewal plans). For example, 30 for monthly billing.
                        example: 42
                      currency:
                        "$ref": "#/components/schemas/Currencies"
                        description: The respective currency identifier for the plan.
                      custom_fields:
                        type:
                        - array
                        - 'null'
                        items:
                          type: object
                          properties:
                            field_type:
                              "$ref": "#/components/schemas/CustomFieldTypes"
                              description: The type of the custom field.
                            id:
                              type:
                              - string
                              - 'null'
                              description: The ID of the custom field (if being updated)
                            name:
                              type: string
                              description: The name of the custom field.
                            order:
                              type:
                              - integer
                              - 'null'
                              description: The order of the field.
                              example: 42
                            placeholder:
                              type:
                              - string
                              - 'null'
                              description: The placeholder value of the field.
                            required:
                              type:
                              - boolean
                              - 'null'
                              description: Whether or not the field is required.
                          required:
                          - field_type
                          - name
                        description: An array of custom field objects.
                      description:
                        type:
                        - string
                        - 'null'
                        description: The description of the plan.
                      expiration_days:
                        type:
                        - integer
                        - 'null'
                        description: The number of days until the membership expires
                          (for expiration-based plans). For example, 365 for a one-year
                          access pass.
                        example: 42
                      force_create_new_plan:
                        type:
                        - boolean
                        - 'null'
                        description: Whether to force the creation of a new plan even
                          if one with the same attributes already exists.
                      image:
                        type:
                        - object
                        - 'null'
                        properties:
                          id:
                            type: string
                            description: The ID of an existing file object.
                        required:
                        - id
                        description: An image for the plan. This will be visible on
                          the product page to customers.
                        title: FileInputWithId
                      initial_price:
                        type:
                        - number
                        - 'null'
                        description: An additional amount charged upon first purchase.
                          Provided as a number in dollars (e.g., 10.00 for $10.00).
                        example: 6.9
                      internal_notes:
                        type:
                        - string
                        - 'null'
                        description: A personal description or notes section for the
                          business.
                      override_tax_type:
                        oneOf:
                        - "$ref": "#/components/schemas/TaxTypes"
                        - type: 'null'
                        description: An override for tax to specify for this specific
                          plan.
                      payment_method_configuration:
                        type:
                        - object
                        - 'null'
                        properties:
                          disabled:
                            type: array
                            items:
                              "$ref": "#/components/schemas/PaymentMethodTypes"
                            description: An array of payment method identifiers that
                              are explicitly disabled. Only applies if the include_platform_defaults
                              is true.
                          enabled:
                            type: array
                            items:
                              "$ref": "#/components/schemas/PaymentMethodTypes"
                            description: An array of payment method identifiers that
                              are explicitly enabled. This means these payment methods
                              will be shown on checkout. Example use case is to only
                              enable a specific payment method like cashapp, or extending
                              the platform defaults with additional methods.
                          include_platform_defaults:
                            type:
                            - boolean
                            - 'null'
                            description: Whether Whop's platform default payment method
                              enablement settings are included in this configuration.
                              The full list of default payment methods can be found
                              in the documentation at docs.whop.com/payments.
                        required:
                        - disabled
                        - enabled
                        description: The explicit payment method configuration for
                          the plan. If not provided, the platform or company's defaults
                          will apply.
                      plan_type:
                        oneOf:
                        - "$ref": "#/components/schemas/PlanTypes"
                        - type: 'null'
                        description: Indicates if the plan is a one time payment or
                          recurring.
                      product:
                        type:
                        - object
                        - 'null'
                        properties:
                          collect_shipping_address:
                            type:
                            - boolean
                            - 'null'
                            description: Whether or not to collect shipping information
                              at checkout from the customer.
                          custom_statement_descriptor:
                            type:
                            - string
                            - 'null'
                            description: 'The custom statement descriptor for the
                              product i.e. WHOP*SPORTS, must be between 5 and 22 characters,
                              contain at least one letter, and not contain any of
                              the following characters: <, >, \, '', "'
                          description:
                            type:
                            - string
                            - 'null'
                            description: A written description of the product.
                          external_identifier:
                            type: string
                            description: A unique ID used to find or create a product.
                              When provided during creation, we will look for an existing
                              product with this external identifier — if found, it
                              will be updated; otherwise, a new product will be created.
                          global_affiliate_percentage:
                            type:
                            - number
                            - 'null'
                            description: The percentage of the revenue that goes to
                              the global affiliate program.
                            example: 6.9
                          global_affiliate_status:
                            oneOf:
                            - "$ref": "#/components/schemas/GlobalAffiliateStatuses"
                            - type: 'null'
                            description: The status of the global affiliate program
                              for this product.
                          headline:
                            type:
                            - string
                            - 'null'
                            description: The headline of the product.
                          product_tax_code_id:
                            type:
                            - string
                            - 'null'
                            description: The ID of the product tax code to apply to
                              this product.
                            example: ptc_xxxxxxxxxxxxxx
                          redirect_purchase_url:
                            type:
                            - string
                            - 'null'
                            description: The URL to redirect the customer to after
                              a purchase.
                          route:
                            type:
                            - string
                            - 'null'
                            description: The route of the product.
                          title:
                            type: string
                            description: The title of the product.
                          visibility:
                            oneOf:
                            - "$ref": "#/components/schemas/Visibility"
                            - type: 'null'
                            description: This product will/will not be displayed publicly
                              - default hidden.
                        required:
                        - external_identifier
                        - title
                        description: Pass this object to create a new product for
                          this plan. We will use the product external identifier to
                          find or create an existing product.
                      product_id:
                        type:
                        - string
                        - 'null'
                        description: The product the plan is related to. Either this
                          or product is required.
                        example: prod_xxxxxxxxxxxxx
                      release_method:
                        oneOf:
                        - "$ref": "#/components/schemas/ReleaseMethod"
                        - type: 'null'
                        description: This is the release method the business uses
                          to sell this plan.
                      renewal_price:
                        type:
                        - number
                        - 'null'
                        description: The amount the customer is charged every billing
                          period. Provided as a number in dollars (e.g., 9.99 for
                          $9.99/period).
                        example: 6.9
                      split_pay_required_payments:
                        type:
                        - integer
                        - 'null'
                        description: The number of payments required before pausing
                          the subscription.
                        example: 42
                      stock:
                        type:
                        - integer
                        - 'null'
                        description: The number of units available for purchase. If
                          not provided, stock is unlimited.
                        example: 42
                      title:
                        type:
                        - string
                        - 'null'
                        description: The title of the plan. This will be visible on
                          the product page to customers.
                      trial_period_days:
                        type:
                        - integer
                        - 'null'
                        description: The number of free trial days added before a
                          renewal plan.
                        example: 42
                      visibility:
                        oneOf:
                        - "$ref": "#/components/schemas/Visibility"
                        - type: 'null'
                        description: Shows or hides the plan from public/business
                          view.
                    required:
                    - company_id
                    - currency
                    description: The plan attributes to create a new plan inline for
                      this checkout configuration.
                  affiliate_code:
                    type:
                    - string
                    - 'null'
                    description: An affiliate tracking code to attribute the checkout
                      to a specific affiliate.
                  allow_promo_codes:
                    type:
                    - boolean
                    - 'null'
                    description: Whether the checkout should show the promo code input
                      field and accept promo codes. Defaults to true.
                  checkout_styling:
                    type:
                    - object
                    - 'null'
                    properties:
                      background_color:
                        type:
                        - string
                        - 'null'
                        description: 'A hex color code for the checkout page background,
                          applied to the order summary panel (e.g. #F4F4F5).'
                      border_style:
                        oneOf:
                        - "$ref": "#/components/schemas/CheckoutShapes"
                        - type: 'null'
                        description: The border style for buttons and inputs.
                      button_color:
                        type:
                        - string
                        - 'null'
                        description: 'A hex color code for the button color (e.g.
                          #FF5733).'
                      font_family:
                        oneOf:
                        - "$ref": "#/components/schemas/CheckoutFonts"
                        - type: 'null'
                        description: The font family for the checkout page.
                    required: []
                    description: Checkout styling overrides for this session. Overrides
                      plan and company defaults.
                  currency:
                    oneOf:
                    - "$ref": "#/components/schemas/Currencies"
                    - type: 'null'
                    description: The currency for the checkout when in setup mode.
                      Controls which currency-specific payment methods are available.
                      Defaults to USD when in setup mode.
                  metadata:
                    type:
                    - object
                    - 'null'
                    additionalProperties: true
                    description: Custom key-value metadata to attach to the checkout
                      configuration.
                  mode:
                    type: string
                    const: payment
                  payment_method_configuration:
                    type:
                    - object
                    - 'null'
                    properties:
                      disabled:
                        type: array
                        items:
                          "$ref": "#/components/schemas/PaymentMethodTypes"
                        description: An array of payment method identifiers that are
                          explicitly disabled. Only applies if the include_platform_defaults
                          is true.
                      enabled:
                        type: array
                        items:
                          "$ref": "#/components/schemas/PaymentMethodTypes"
                        description: An array of payment method identifiers that are
                          explicitly enabled. This means these payment methods will
                          be shown on checkout. Example use case is to only enable
                          a specific payment method like cashapp, or extending the
                          platform defaults with additional methods.
                      include_platform_defaults:
                        type:
                        - boolean
                        - 'null'
                        description: Whether Whop's platform default payment method
                          enablement settings are included in this configuration.
                          The full list of default payment methods can be found in
                          the documentation at docs.whop.com/payments.
                    required:
                    - disabled
                    - enabled
                    description: The explicit payment method configuration for the
                      checkout session. Only applies to setup mode. If not provided,
                      the platform or company defaults will apply.
                  redirect_url:
                    type:
                    - string
                    - 'null'
                    description: The URL to redirect the user to after checkout is
                      completed.
                  source_url:
                    type:
                    - string
                    - 'null'
                    description: The URL of the page where the checkout is being initiated
                      from.
                required:
                - plan
                description: Autogenerated input type of CreateCheckoutSession
                title: CreateCheckoutSessionInputModePaymentWithPlan
              - type: object
                properties:
                  plan_id:
                    type: string
                    description: The unique identifier of an existing plan to use
                      for this checkout configuration.
                    example: plan_xxxxxxxxxxxxx
                  affiliate_code:
                    type:
                    - string
                    - 'null'
                    description: An affiliate tracking code to attribute the checkout
                      to a specific affiliate.
                  allow_promo_codes:
                    type:
                    - boolean
                    - 'null'
                    description: Whether the checkout should show the promo code input
                      field and accept promo codes. Defaults to true.
                  checkout_styling:
                    type:
                    - object
                    - 'null'
                    properties:
                      background_color:
                        type:
                        - string
                        - 'null'
                        description: 'A hex color code for the checkout page background,
                          applied to the order summary panel (e.g. #F4F4F5).'
                      border_style:
                        oneOf:
                        - "$ref": "#/components/schemas/CheckoutShapes"
                        - type: 'null'
                        description: The border style for buttons and inputs.
                      button_color:
                        type:
                        - string
                        - 'null'
                        description: 'A hex color code for the button color (e.g.
                          #FF5733).'
                      font_family:
                        oneOf:
                        - "$ref": "#/components/schemas/CheckoutFonts"
                        - type: 'null'
                        description: The font family for the checkout page.
                    required: []
                    description: Checkout styling overrides for this session. Overrides
                      plan and company defaults.
                  currency:
                    oneOf:
                    - "$ref": "#/components/schemas/Currencies"
                    - type: 'null'
                    description: The currency for the checkout when in setup mode.
                      Controls which currency-specific payment methods are available.
                      Defaults to USD when in setup mode.
                  metadata:
                    type:
                    - object
                    - 'null'
                    additionalProperties: true
                    description: Custom key-value metadata to attach to the checkout
                      configuration.
                  mode:
                    type: string
                    const: payment
                  payment_method_configuration:
                    type:
                    - object
                    - 'null'
                    properties:
                      disabled:
                        type: array
                        items:
                          "$ref": "#/components/schemas/PaymentMethodTypes"
                        description: An array of payment method identifiers that are
                          explicitly disabled. Only applies if the include_platform_defaults
                          is true.
                      enabled:
                        type: array
                        items:
                          "$ref": "#/components/schemas/PaymentMethodTypes"
                        description: An array of payment method identifiers that are
                          explicitly enabled. This means these payment methods will
                          be shown on checkout. Example use case is to only enable
                          a specific payment method like cashapp, or extending the
                          platform defaults with additional methods.
                      include_platform_defaults:
                        type:
                        - boolean
                        - 'null'
                        description: Whether Whop's platform default payment method
                          enablement settings are included in this configuration.
                          The full list of default payment methods can be found in
                          the documentation at docs.whop.com/payments.
                    required:
                    - disabled
                    - enabled
                    description: The explicit payment method configuration for the
                      checkout session. Only applies to setup mode. If not provided,
                      the platform or company defaults will apply.
                  redirect_url:
                    type:
                    - string
                    - 'null'
                    description: The URL to redirect the user to after checkout is
                      completed.
                  source_url:
                    type:
                    - string
                    - 'null'
                    description: The URL of the page where the checkout is being initiated
                      from.
                required:
                - plan_id
                description: Autogenerated input type of CreateCheckoutSession
                title: CreateCheckoutSessionInputModePaymentWithPlanId
              - type: object
                properties:
                  company_id:
                    type: string
                    description: The unique identifier of the company to create the
                      checkout configuration for. Only required in setup mode.
                    example: biz_xxxxxxxxxxxxxx
                  allow_promo_codes:
                    type:
                    - boolean
                    - 'null'
                    description: Whether the checkout should show the promo code input
                      field and accept promo codes. Defaults to true.
                  checkout_styling:
                    type:
                    - object
                    - 'null'
                    properties:
                      background_color:
                        type:
                        - string
                        - 'null'
                        description: 'A hex color code for the checkout page background,
                          applied to the order summary panel (e.g. #F4F4F5).'
                      border_style:
                        oneOf:
                        - "$ref": "#/components/schemas/CheckoutShapes"
                        - type: 'null'
                        description: The border style for buttons and inputs.
                      button_color:
                        type:
                        - string
                        - 'null'
                        description: 'A hex color code for the button color (e.g.
                          #FF5733).'
                      font_family:
                        oneOf:
                        - "$ref": "#/components/schemas/CheckoutFonts"
                        - type: 'null'
                        description: The font family for the checkout page.
                    required: []
                    description: Checkout styling overrides for this session. Overrides
                      plan and company defaults.
                  currency:
                    oneOf:
                    - "$ref": "#/components/schemas/Currencies"
                    - type: 'null'
                    description: The currency for the checkout when in setup mode.
                      Controls which currency-specific payment methods are available.
                      Defaults to USD when in setup mode.
                  metadata:
                    type:
                    - object
                    - 'null'
                    additionalProperties: true
                    description: Custom key-value metadata to attach to the checkout
                      configuration.
                  mode:
                    type: string
                    const: setup
                  payment_method_configuration:
                    type:
                    - object
                    - 'null'
                    properties:
                      disabled:
                        type: array
                        items:
                          "$ref": "#/components/schemas/PaymentMethodTypes"
                        description: An array of payment method identifiers that are
                          explicitly disabled. Only applies if the include_platform_defaults
                          is true.
                      enabled:
                        type: array
                        items:
                          "$ref": "#/components/schemas/PaymentMethodTypes"
                        description: An array of payment method identifiers that are
                          explicitly enabled. This means these payment methods will
                          be shown on checkout. Example use case is to only enable
                          a specific payment method like cashapp, or extending the
                          platform defaults with additional methods.
                      include_platform_defaults:
                        type:
                        - boolean
                        - 'null'
                        description: Whether Whop's platform default payment method
                          enablement settings are included in this configuration.
                          The full list of default payment methods can be found in
                          the documentation at docs.whop.com/payments.
                    required:
                    - disabled
                    - enabled
                    description: The explicit payment method configuration for the
                      checkout session. Only applies to setup mode. If not provided,
                      the platform or company defaults will apply.
                  redirect_url:
                    type:
                    - string
                    - 'null'
                    description: The URL to redirect the user to after checkout is
                      completed.
                  source_url:
                    type:
                    - string
                    - 'null'
                    description: The URL of the page where the checkout is being initiated
                      from.
                required:
                - mode
                - company_id
                description: Autogenerated input type of CreateCheckoutSession
                title: CreateCheckoutSessionInputModeSetup
              type: object
              description: Parameters for CreateCheckoutSession
      x-codeSamples:
      - lang: JavaScript
        source: |-
          import Whop from '@whop/sdk';

          const client = new Whop({
            apiKey: process.env['WHOP_API_KEY'], // This is the default and can be omitted
          });

          const checkoutConfiguration = await client.checkoutConfigurations.create({
            plan: { company_id: 'biz_xxxxxxxxxxxxxx', currency: 'usd' },
          });

          console.log(checkoutConfiguration.id);
      - lang: Python
        source: |-
          import os
          from whop_sdk import Whop

          client = Whop(
              api_key=os.environ.get("WHOP_API_KEY"),  # This is the default and can be omitted
          )
          checkout_configuration = client.checkout_configurations.create(
              plan={
                  "company_id": "biz_xxxxxxxxxxxxxx",
                  "currency": "usd",
              },
          )
          print(checkout_configuration.id)
      - lang: Ruby
        source: |-
          require "whop_sdk"

          whop = WhopSDK::Client.new(api_key: "My API Key")

          checkout_configuration = whop.checkout_configurations.create(body: {plan: {company_id: "biz_xxxxxxxxxxxxxx", currency: :usd}})

          puts(checkout_configuration)
components:
  schemas:
    CheckoutConfiguration:
      type: object
      properties:
        id:
          type: string
          description: The unique identifier for the checkout session.
          example: ch_xxxxxxxxxxxxxxx
        company_id:
          type: string
          description: The ID of the company to use for the checkout configuration
        mode:
          "$ref": "#/components/schemas/CheckoutModes"
          description: The mode of the checkout session.
        currency:
          oneOf:
          - "$ref": "#/components/schemas/Currencies"
          - type: 'null'
          description: The currency to use for the configuration when in 'setup' mode.
            This is used to target which currency specific payment methods are available.
            If not provided, it will default to 'usd' when in setup mode.
        plan:
          type:
          - object
          - 'null'
          properties:
            id:
              type: string
              description: The unique identifier for the plan.
              example: plan_xxxxxxxxxxxxx
            visibility:
              "$ref": "#/components/schemas/Visibility"
              description: Controls whether the plan is visible to customers. When
                set to 'hidden', the plan is only accessible via direct link.
            plan_type:
              "$ref": "#/components/schemas/PlanTypes"
              description: 'The billing model for this plan: ''renewal'' for recurring
                subscriptions or ''one_time'' for single payments.'
            release_method:
              "$ref": "#/components/schemas/ReleaseMethod"
              description: 'The method used to sell this plan: ''buy_now'' for immediate
                purchase or ''waitlist'' for waitlist-based access.'
            currency:
              "$ref": "#/components/schemas/Currencies"
              description: The currency used for all prices on this plan (e.g., 'usd',
                'eur'). All monetary amounts on the plan are denominated in this currency.
            billing_period:
              type:
              - integer
              - 'null'
              description: The number of days between each recurring charge. Null
                for one-time plans. For example, 30 for monthly or 365 for annual
                billing.
              example: 42
            expiration_days:
              type:
              - integer
              - 'null'
              description: The number of days until the membership expires (for expiration-based
                plans). For example, 365 for a one-year access pass.
              example: 42
            initial_price:
              type: number
              description: The initial purchase price in the plan's base_currency
                (e.g., 49.99 for $49.99). For one-time plans, this is the full price.
                For renewal plans, this is charged on top of the first renewal_price.
              example: 6.9
            renewal_price:
              type: number
              description: The recurring price charged every billing_period in the
                plan's base_currency (e.g., 9.99 for $9.99/period). Zero for one-time
                plans.
              example: 6.9
            trial_period_days:
              type:
              - integer
              - 'null'
              description: The number of free trial days before the first charge on
                a renewal plan. Null if no trial is configured or the current user
                has already used a trial for this plan.
              example: 42
            adaptive_pricing_enabled:
              type: boolean
              description: Whether the creator has turned on adaptive pricing for
                this plan. Raw setting — does not check processor compatibility or
                feature flags.
          required:
          - id
          - visibility
          - plan_type
          - release_method
          - currency
          - billing_period
          - expiration_days
          - initial_price
          - renewal_price
          - trial_period_days
          - adaptive_pricing_enabled
          description: The plan to use for the checkout configuration
        affiliate_code:
          type:
          - string
          - 'null'
          description: The affiliate code to use for the checkout configuration
        metadata:
          type:
          - object
          - 'null'
          additionalProperties: true
          description: The metadata to use for the checkout configuration
        redirect_url:
          type:
          - string
          - 'null'
          description: The URL to redirect the user to after the checkout configuration
            is created
        purchase_url:
          type: string
          description: A URL you can send to customers to complete a checkout. It
            looks like `/checkout/plan_xxxx?session={id}`
        allow_promo_codes:
          type: boolean
          description: Whether the checkout configuration allows promo codes. When
            false, the promo code input is hidden and promo codes are rejected.
        payment_method_configuration:
          type:
          - object
          - 'null'
          properties:
            enabled:
              type: array
              items:
                "$ref": "#/components/schemas/PaymentMethodTypes"
              description: An array of payment method identifiers that are explicitly
                enabled. This means these payment methods will be shown on checkout.
                Example use case is to only enable a specific payment method like
                cashapp, or extending the platform defaults with additional methods.
            disabled:
              type: array
              items:
                "$ref": "#/components/schemas/PaymentMethodTypes"
              description: An array of payment method identifiers that are explicitly
                disabled. Only applies if the include_platform_defaults is true.
            include_platform_defaults:
              type: boolean
              description: Whether Whop's platform default payment method enablement
                settings are included in this configuration. The full list of default
                payment methods can be found in the documentation at docs.whop.com/payments.
          required:
          - enabled
          - disabled
          - include_platform_defaults
          description: The explicit payment method configuration for the session,
            if any. This currently only works in 'setup' mode. Use the plan's payment_method_configuration
            for payment method.
      required:
      - id
      - company_id
      - mode
      - currency
      - plan
      - affiliate_code
      - metadata
      - redirect_url
      - purchase_url
      - allow_promo_codes
      - payment_method_configuration
      description: A checkout configuration is a reusable configuration for a checkout,
        including the plan, affiliate, and custom metadata. Payments and memberships
        created from a checkout session inherit its metadata.
    CheckoutFonts:
      type: string
      enum:
      - system
      - roboto
      - open_sans
      description: The different font families available for checkout pages.
    CheckoutModes:
      type: string
      enum:
      - payment
      - setup
      description: The different modes a checkout can be set to.
    CheckoutShapes:
      type: string
      enum:
      - rounded
      - pill
      - rectangular
      description: The different border-radius styles available for checkout pages.
    Currencies:
      type: string
      enum:
      - usd
      - sgd
      - inr
      - aud
      - brl
      - cad
      - dkk
      - eur
      - nok
      - gbp
      - sek
      - chf
      - hkd
      - huf
      - jpy
      - mxn
      - myr
      - pln
      - czk
      - nzd
      - aed
      - eth
      - ape
      - cop
      - ron
      - thb
      - bgn
      - idr
      - dop
      - php
      - try
      - krw
      - twd
      - vnd
      - pkr
      - clp
      - uyu
      - ars
      - zar
      - dzd
      - tnd
      - mad
      - kes
      - kwd
      - jod
      - all
      - xcd
      - amd
      - bsd
      - bhd
      - bob
      - bam
      - khr
      - crc
      - xof
      - egp
      - etb
      - gmd
      - ghs
      - gtq
      - gyd
      - ils
      - jmd
      - mop
      - mga
      - mur
      - mdl
      - mnt
      - nad
      - ngn
      - mkd
      - omr
      - pyg
      - pen
      - qar
      - rwf
      - sar
      - rsd
      - lkr
      - tzs
      - ttd
      - uzs
      - rub
      - btc
      - cny
      - usdt
      - kzt
      description: The available currencies on the platform
    CustomFieldTypes:
      type: string
      const: text
      description: The type of the custom field.
    GlobalAffiliateStatuses:
      type: string
      enum:
      - enabled
      - disabled
      description: The different statuses of the global affiliate program for a product.
    PaymentMethodTypes:
      type: string
      enum:
      - acss_debit
      - affirm
      - afterpay_clearpay
      - alipay
      - alma
      - amazon_pay
      - apple
      - apple_pay
      - au_bank_transfer
      - au_becs_debit
      - bacs_debit
      - bancolombia
      - bancontact
      - billie
      - bizum
      - blik
      - boleto
      - bre_b
      - ca_bank_transfer
      - capchase_pay
      - card
      - card_installments_three
      - card_installments_six
      - card_installments_twelve
      - cashapp
      - claritypay
      - coinbase
      - crypto
      - custom
      - customer_balance
      - demo_pay
      - efecty
      - eps
      - eu_bank_transfer
      - fpx
      - gb_bank_transfer
      - giropay
      - google_pay
      - gopay
      - grabpay
      - id_bank_transfer
      - ideal
      - interac
      - kakao_pay
      - klarna
      - klarna_pay_now
      - konbini
      - kr_card
      - kr_market
      - kriya
      - kueski
      - link
      - mb_way
      - m_pesa
      - mercado_pago
      - mobilepay
      - mondu
      - multibanco
      - naver_pay
      - nequi
      - netbanking
      - ng_bank
      - ng_bank_transfer
      - ng_card
      - ng_market
      - ng_ussd
      - ng_wallet
      - nz_bank_account
      - oxxo
      - p24
      - pago_efectivo
      - pse
      - pay_by_bank
      - payco
      - paynow
      - paypal
      - paypay
      - payto
      - pix
      - platform_balance
      - promptpay
      - qris
      - rechnung
      - revolut_pay
      - samsung_pay
      - satispay
      - scalapay
      - sencillito
      - sepa_debit
      - sequra
      - servipag
      - sezzle
      - shop_pay
      - shopeepay
      - sofort
      - south_korea_market
      - spei
      - splitit
      - sunbit
      - swish
      - tamara
      - twint
      - upi
      - us_bank_account
      - us_bank_transfer
      - venmo
      - vipps
      - webpay
      - wechat_pay
      - yape
      - zip
      - coinflow
      - unknown
      description: The different types of payment methods that can be used.
    PlanTypes:
      type: string
      enum:
      - renewal
      - one_time
      description: The type of plan that can be attached to a product
    ReleaseMethod:
      type: string
      enum:
      - buy_now
      - waitlist
      description: The methods of how a plan can be released.
    TaxTypes:
      type: string
      enum:
      - inclusive
      - exclusive
      - unspecified
      description: Whether or not the tax is included in a plan's price (or if it
        hasn't been set up)
    Visibility:
      type: string
      enum:
      - visible
      - hidden
      - archived
      - quick_link
      description: Visibility of a resource
```
