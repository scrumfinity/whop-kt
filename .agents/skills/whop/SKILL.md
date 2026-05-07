---
name: Whop
description: Use when building payment infrastructure, managing creator platforms, accepting payments across 195 countries, handling payouts and transfers, building apps that integrate with Whop's ecosystem, or managing memberships and subscriptions. Agents should reach for this skill when working with Whop's API, SDKs, webhooks, OAuth flows, or embedded components.
metadata:
    mintlify-proj: whop
    version: "1.0"
---

# Whop Skill Reference

## Product summary

Whop is a payment and creator platform that enables businesses to accept payments (100+ methods across 195 countries), manage memberships and subscriptions, handle payouts and transfers, and build custom apps. Agents use Whop to programmatically create checkouts, manage products, process payments, handle webhooks, authenticate users, and build embedded experiences. Key files: API keys live in the [Developer Dashboard](https://whop.com/dashboard/developer); SDKs available for TypeScript, Python, and Ruby. Primary docs: https://docs.whop.com

## When to use

Reach for this skill when:
- Building or integrating payment checkout flows (one-time or recurring)
- Creating a platform with sub-merchants or connected accounts
- Setting up webhooks to handle payment, membership, or entry events
- Authenticating users via OAuth or verifying iframe tokens
- Building Whop apps (Experience View for members, Dashboard View for creators)
- Managing products, plans, memberships, or payouts programmatically
- Handling refunds, disputes, or payment method management
- Embedding payout portals or payment components in custom applications
- Integrating with third-party platforms (HubSpot, GoHighLevel, Zapier, etc.)

## Quick reference

### API Keys and Authentication

| Type | Use case | Scope |
|------|----------|-------|
| Company API Key | Access your own company data, manage connected accounts | Company-level operations |
| App API Key | Build apps that access installed companies' data | Multi-company (installed apps only) |
| OAuth Token | User-scoped access, "Sign in with Whop" | Individual user permissions |
| Iframe User Token | Verify users inside Whop app iframes | Passed in `x-whop-user-token` header |

### SDK Installation

```bash
# TypeScript/JavaScript
npm install @whop/sdk

# Python
pip install whop-sdk

# Ruby
gem install whop_sdk
```

### Core API Endpoints

| Resource | Key endpoints |
|----------|---------------|
| Payments | `POST /payments`, `GET /payments`, `POST /payments/{id}/refund` |
| Checkout | `POST /checkout_configurations`, `GET /checkout_configurations` |
| Plans | `POST /plans`, `GET /plans`, `PATCH /plans/{id}` |
| Products | `POST /products`, `GET /products`, `PATCH /products/{id}` |
| Memberships | `GET /memberships`, `PATCH /memberships/{id}`, `POST /memberships/{id}/cancel` |
| Transfers | `POST /transfers`, `GET /transfers` |
| Webhooks | `POST /webhooks`, `GET /webhooks`, `DELETE /webhooks/{id}` |
| Companies | `POST /companies`, `GET /companies`, `PATCH /companies/{id}` |

### Webhook Events

| Event | Trigger | Use for |
|-------|---------|---------|
| `payment.succeeded` | Payment processed successfully | Fulfill orders, grant access |
| `membership.activated` | User joins a product | Grant member access |
| `membership.deactivated` | Membership ends (cancel, failed payment) | Revoke access |
| `entry.created` | User joins a waitlist | Capture leads |
| `refund.created` | Payment refunded | Update inventory, logs |
| `dispute.created` | Chargeback initiated | Alert and investigate |

## Decision guidance

### Checkout approach

| Approach | Effort | Customization | Best for |
|----------|--------|---------------|----------|
| Checkout link | Low | Limited | Quick setup, sharing links |
| Embedded checkout | Medium | Full control | Custom UX, dynamic pricing |
| iOS SDK | Medium | Native iOS | Lower fees (2.7% + $0.30 vs 15–30%) |

**Choose checkout link** if you need a simple, shareable URL. **Choose embedded checkout** if you need custom styling, dynamic pricing, or full control over the experience. **Choose iOS SDK** if building native iOS apps to reduce payment processing fees.

### Authentication approach

| Approach | Use case | Token lifetime |
|----------|----------|-----------------|
| Company API Key | Server-to-server, your own company | No expiry |
| App API Key | Multi-company apps | No expiry |
| OAuth + PKCE | User login, delegated access | 1 hour (refresh available) |
| Iframe token | Whop app users | Short-lived, auto-refreshed |

**Use Company API Key** for backend operations on your own company. **Use App API Key** when building apps installed on other companies. **Use OAuth** for "Sign in with Whop" or user-delegated access. **Use iframe token** inside Whop app iframes (automatically provided).

### App view selection

| View | Audience | Appears in | Examples |
|------|----------|-----------|----------|
| Experience View | Members/customers | Whop sidebar | Quizzes, courses, games, content libraries |
| Dashboard View | Creators/admins | Creator dashboard | Analytics, marketing tools, member management |

**Use Experience View** for consumer-facing features members interact with. **Use Dashboard View** for business tools that help creators manage operations.

## Workflow

### 1. Accept payments with embedded checkout

1. **Get your company ID** from Dashboard > Settings
2. **Create a checkout configuration** on your server with `checkoutConfigurations.create()`, passing `company_id`, `plan` (with `initial_price` and `plan_type`), and optional `metadata`
3. **Render the checkout** in React with `<WhopCheckoutEmbed>` or in HTML with the loader script and data attributes
4. **Handle the return URL** — check the `status` query parameter (success/error) and remount checkout on error
5. **Listen for webhooks** — set up `payment.succeeded` webhook to fulfill orders on your server
6. **Verify webhook signatures** — use `whopsdk.webhooks.unwrap()` to validate and parse events

### 2. Set up webhooks

1. **Create webhook in dashboard** — go to Developer tab, click "Create Webhook"
2. **Enter URL and select events** — choose the events you want (e.g., `payment.succeeded`, `membership.activated`)
3. **Store the webhook secret** — copy it and set as `WHOP_WEBHOOK_SECRET` environment variable
4. **Initialize SDK with secret** — pass `webhook_key: btoa(process.env.WHOP_WEBHOOK_SECRET)` to Whop client
5. **Handle POST requests** — use `whopsdk.webhooks.unwrap(requestBody, { headers })` to verify and parse
6. **Respond with 2xx quickly** — do minimum work (verify + enqueue), then return 200; run fulfillment in background tasks
7. **Make handlers idempotent** — use `webhook-id` header as dedup key since events may be delivered multiple times

### 3. Build a Whop app

1. **Create app in dashboard** — go to Developer Dashboard, click "Create app", get your `app_id` and `client_secret`
2. **Configure views** — set Experience View path (`/experiences/[experienceId]`) or Dashboard View path (`/dashboard/[companyId]`)
3. **Request permissions** — go to Permissions tab, add required scopes with justification
4. **Verify user token** — call `whopsdk.verifyUserToken(headers)` to get `userId` on each request
5. **Check access** — call `whopsdk.users.checkAccess(resourceId, { id: userId })` to verify access level
6. **Gate content** — return 403 or "Access denied" if user lacks required access
7. **Install app** — visit `https://whop.com/apps/app_xxxxxxxxx/install` to test on your company

### 4. Manage payouts and transfers

1. **Set up Whop Payments** — go to Dashboard > Balances, click "Set up Whop Payments", select country, complete KYC
2. **Add payout method** — add bank account or other supported option
3. **Create transfers** — use `transfers.create()` to move funds between companies (origin_id → destination_id)
4. **Withdraw funds** — go to Dashboard > Balances, click "Withdraw", enter amount, confirm
5. **Set automatic payouts** — click three dots on Balances page, set frequency and minimum reserve
6. **Monitor status** — check withdrawal status in Dashboard or via `GET /withdrawals` API

### 5. Implement OAuth flow

1. **Create OAuth app** — go to Developer Dashboard, add redirect URIs (exact match required), copy `client_id` and `client_secret`
2. **Generate PKCE parameters** — create `code_verifier`, `code_challenge`, `state`, and `nonce` on client
3. **Redirect to authorize** — send user to `https://api.whop.com/oauth/authorize?response_type=code&client_id=...&code_challenge=...&state=...`
4. **Handle callback** — verify `state` matches, exchange `code` for tokens at `https://api.whop.com/oauth/token`
5. **Store tokens** — save `access_token`, `refresh_token`, and `expires_in`; add `obtained_at` timestamp
6. **Use access token** — initialize Whop SDK with `apiKey: accessToken`
7. **Refresh before expiry** — refresh 5 minutes before expiry using `refresh_token`
8. **Revoke on logout** — POST to `https://api.whop.com/oauth/revoke` with `refresh_token` to invalidate

## Common gotchas

- **Webhook signature verification is mandatory** — always call `whopsdk.webhooks.unwrap()` before trusting the payload; a bad signature raises an error
- **Respond to webhooks in under 30 seconds** — Whop retries on non-2xx responses or timeouts; use background tasks for long operations
- **Make webhook handlers idempotent** — events may be delivered multiple times; use `webhook-id` as a dedup key
- **Checkout configuration is session-scoped** — each checkout config is single-use; create a new one for each customer
- **OAuth tokens expire after 1 hour** — always check expiry and refresh before using; store `obtained_at` to calculate expiry
- **Iframe token is only on same-origin requests** — the `x-whop-user-token` header only appears on requests to your app's origin; reverse-proxy cross-domain API calls
- **Company API keys are not scoped to users** — they have full company access; use OAuth for user-scoped operations
- **Permissions are required even for your own company** — apps must request and be approved for permissions to work consistently
- **Access levels are not granular** — `checkAccess` returns `customer`, `admin`, or `no_access`; implement custom role logic in your app
- **Payout methods vary by country** — not all countries support all payout options; check supported countries before creating transfers
- **Refunds reduce your balance immediately** — refunds are deducted from your available balance, not pending balance
- **Disputes can reverse payments** — chargebacks and disputes can reverse successful payments; monitor `dispute.created` webhooks

## Verification checklist

Before submitting work with Whop:

- [ ] API key is stored in environment variables, never hardcoded
- [ ] Webhook secret is base64-encoded before passing to SDK
- [ ] Webhook handler verifies signature with `whopsdk.webhooks.unwrap()`
- [ ] Webhook handler returns 2xx status within 30 seconds
- [ ] Webhook handler is idempotent (uses `webhook-id` for dedup)
- [ ] Checkout configuration is created server-side, not client-side
- [ ] Checkout return URL is set and handles both success and error states
- [ ] OAuth state parameter is verified before exchanging code
- [ ] OAuth tokens are stored securely (not in localStorage for sensitive apps)
- [ ] OAuth access token is refreshed before expiry (5-minute buffer)
- [ ] User token is verified on every iframe request with `verifyUserToken()`
- [ ] Access is checked with `checkAccess()` before rendering gated content
- [ ] Permissions are requested in the Permissions tab before publishing
- [ ] App is tested on localhost with the dev proxy before production
- [ ] Error handling covers network failures, expired tokens, and permission denials
- [ ] Payout methods are verified to be supported in target countries

## Resources

- **Comprehensive navigation**: https://docs.whop.com/llms.txt — page-by-page reference for all documentation
- **API Reference**: https://docs.whop.com/api-reference/getting-started — all endpoints, request/response schemas, required permissions
- **Getting Started**: https://docs.whop.com/developer/api/getting-started — walkthrough of core use cases (checkout, transfers, KYC, chat)

---

> For additional documentation and navigation, see: https://docs.whop.com/llms.txt