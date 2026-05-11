# Whop API request/response reference

Generated from Whop's official documented OpenAPI source on 2026-05-09T14:37:16Z.

- **Source:** https://app.stainless.com/api/spec/documented/whopsdk/openapi.documented.yml
- **Base URL:** `https://api.whop.com/api/v1`
- **Endpoint Markdown files:** 220
- **Local OpenAPI copy:** [openapi.documented.yml](openapi.documented.yml)

Each endpoint has its own Markdown file with parameters, request body schema, response schemas, SDK examples, referenced schemas, and a complete OpenAPI excerpt. Use the links below to open or download individual endpoint docs.

## Endpoint groups

- [Access Tokens](endpoints/access-tokens/README.md) — 1 endpoint
- [Account Links](endpoints/account-links/README.md) — 1 endpoint
- [Ad Campaigns](endpoints/ad-campaigns/README.md) — 6 endpoints
- [Ad Groups](endpoints/ad-groups/README.md) — 5 endpoints
- [Ads](endpoints/ads/README.md) — 3 endpoints
- [Affiliates](endpoints/affiliates/README.md) — 10 endpoints
- [Ai Chats](endpoints/ai-chats/README.md) — 5 endpoints
- [App Builds](endpoints/app-builds/README.md) — 4 endpoints
- [Apps](endpoints/apps/README.md) — 4 endpoints
- [Authorized Users](endpoints/authorized-users/README.md) — 4 endpoints
- [Bounties](endpoints/bounties/README.md) — 3 endpoints
- [Card Transactions](endpoints/card-transactions/README.md) — 2 endpoints
- [Chat Channels](endpoints/chat-channels/README.md) — 3 endpoints
- [Checkout Configurations](endpoints/checkout-configurations/README.md) — 3 endpoints
- [Companies](endpoints/companies/README.md) — 5 endpoints
- [Company Token Transactions](endpoints/company-token-transactions/README.md) — 3 endpoints
- [Conversions](endpoints/conversions/README.md) — 1 endpoint
- [Course Chapters](endpoints/course-chapters/README.md) — 5 endpoints
- [Course Lesson Interactions](endpoints/course-lesson-interactions/README.md) — 2 endpoints
- [Course Lessons](endpoints/course-lessons/README.md) — 8 endpoints
- [Course Students](endpoints/course-students/README.md) — 2 endpoints
- [Courses](endpoints/courses/README.md) — 5 endpoints
- [Dispute Alerts](endpoints/dispute-alerts/README.md) — 2 endpoints
- [Disputes](endpoints/disputes/README.md) — 4 endpoints
- [Dm Channels](endpoints/dm-channels/README.md) — 5 endpoints
- [Dm Members](endpoints/dm-members/README.md) — 5 endpoints
- [Entries](endpoints/entries/README.md) — 4 endpoints
- [Experiences](endpoints/experiences/README.md) — 8 endpoints
- [Fee Markups](endpoints/fee-markups/README.md) — 3 endpoints
- [Files](endpoints/files/README.md) — 2 endpoints
- [Forum Posts](endpoints/forum-posts/README.md) — 4 endpoints
- [Forums](endpoints/forums/README.md) — 3 endpoints
- [Invoices](endpoints/invoices/README.md) — 8 endpoints
- [Leads](endpoints/leads/README.md) — 4 endpoints
- [Ledger Accounts](endpoints/ledger-accounts/README.md) — 1 endpoint
- [Members](endpoints/members/README.md) — 2 endpoints
- [Memberships](endpoints/memberships/README.md) — 8 endpoints
- [Messages](endpoints/messages/README.md) — 5 endpoints
- [Notifications](endpoints/notifications/README.md) — 1 endpoint
- [Payment Methods](endpoints/payment-methods/README.md) — 2 endpoints
- [Payments](endpoints/payments/README.md) — 7 endpoints
- [Payout Accounts](endpoints/payout-accounts/README.md) — 1 endpoint
- [Payout Methods](endpoints/payout-methods/README.md) — 2 endpoints
- [Plans](endpoints/plans/README.md) — 5 endpoints
- [Products](endpoints/products/README.md) — 5 endpoints
- [Promo Codes](endpoints/promo-codes/README.md) — 4 endpoints
- [Reactions](endpoints/reactions/README.md) — 4 endpoints
- [Refunds](endpoints/refunds/README.md) — 2 endpoints
- [Resolution Center Cases](endpoints/resolution-center-cases/README.md) — 2 endpoints
- [Reviews](endpoints/reviews/README.md) — 2 endpoints
- [Setup Intents](endpoints/setup-intents/README.md) — 2 endpoints
- [Shipments](endpoints/shipments/README.md) — 3 endpoints
- [Stats](endpoints/stats/README.md) — 4 endpoints
- [Support Channels](endpoints/support-channels/README.md) — 3 endpoints
- [Topups](endpoints/topups/README.md) — 1 endpoint
- [Transfers](endpoints/transfers/README.md) — 3 endpoints
- [Users](endpoints/users/README.md) — 4 endpoints
- [Verifications](endpoints/verifications/README.md) — 2 endpoints
- [Webhooks](endpoints/webhooks/README.md) — 5 endpoints
- [Withdrawals](endpoints/withdrawals/README.md) — 3 endpoints

## All endpoints

| Endpoint | Operation | Group | Markdown |
|---|---|---|---|
| `POST /access_tokens` | Create access token | `access-tokens` | [Download Markdown](endpoints/access-tokens/post-create-access-token.md) |
| `POST /account_links` | Create account link | `account-links` | [Download Markdown](endpoints/account-links/post-create-account-link.md) |
| `GET /ad_campaigns` | List ad campaigns | `ad-campaigns` | [Download Markdown](endpoints/ad-campaigns/get-list-ad-campaigns.md) |
| `POST /ad_campaigns` | Create ad campaign | `ad-campaigns` | [Download Markdown](endpoints/ad-campaigns/post-create-ad-campaign.md) |
| `GET /ad_campaigns/{id}` | Retrieve ad campaign | `ad-campaigns` | [Download Markdown](endpoints/ad-campaigns/get-retrieve-ad-campaign.md) |
| `PATCH /ad_campaigns/{id}` | Update ad campaign | `ad-campaigns` | [Download Markdown](endpoints/ad-campaigns/patch-update-ad-campaign.md) |
| `POST /ad_campaigns/{id}/pause` | Pause ad campaign | `ad-campaigns` | [Download Markdown](endpoints/ad-campaigns/post-pause-ad-campaign.md) |
| `POST /ad_campaigns/{id}/unpause` | Unpause ad campaign | `ad-campaigns` | [Download Markdown](endpoints/ad-campaigns/post-unpause-ad-campaign.md) |
| `GET /ad_groups` | List ad groups | `ad-groups` | [Download Markdown](endpoints/ad-groups/get-list-ad-groups.md) |
| `POST /ad_groups` | Create ad group | `ad-groups` | [Download Markdown](endpoints/ad-groups/post-create-ad-group.md) |
| `DELETE /ad_groups/{id}` | Delete ad group | `ad-groups` | [Download Markdown](endpoints/ad-groups/delete-delete-ad-group.md) |
| `GET /ad_groups/{id}` | Retrieve ad group | `ad-groups` | [Download Markdown](endpoints/ad-groups/get-retrieve-ad-group.md) |
| `PATCH /ad_groups/{id}` | Update ad group | `ad-groups` | [Download Markdown](endpoints/ad-groups/patch-update-ad-group.md) |
| `GET /ads` | List ads | `ads` | [Download Markdown](endpoints/ads/get-list-ads.md) |
| `POST /ads` | Create ad | `ads` | [Download Markdown](endpoints/ads/post-create-ad.md) |
| `GET /ads/{id}` | Retrieve ad | `ads` | [Download Markdown](endpoints/ads/get-retrieve-ad.md) |
| `GET /affiliates` | List affiliates | `affiliates` | [Download Markdown](endpoints/affiliates/get-list-affiliates.md) |
| `POST /affiliates` | Create affiliate | `affiliates` | [Download Markdown](endpoints/affiliates/post-create-affiliate.md) |
| `GET /affiliates/{id}` | Retrieve affiliate | `affiliates` | [Download Markdown](endpoints/affiliates/get-retrieve-affiliate.md) |
| `POST /affiliates/{id}/archive` | Archive affiliate | `affiliates` | [Download Markdown](endpoints/affiliates/post-archive-affiliate.md) |
| `GET /affiliates/{id}/overrides` | List overrides | `affiliates` | [Download Markdown](endpoints/affiliates/get-list-overrides.md) |
| `POST /affiliates/{id}/overrides` | Create override | `affiliates` | [Download Markdown](endpoints/affiliates/post-create-override.md) |
| `DELETE /affiliates/{id}/overrides/{override_id}` | Delete override | `affiliates` | [Download Markdown](endpoints/affiliates/delete-delete-override.md) |
| `GET /affiliates/{id}/overrides/{override_id}` | Retrieve override | `affiliates` | [Download Markdown](endpoints/affiliates/get-retrieve-override.md) |
| `PATCH /affiliates/{id}/overrides/{override_id}` | Update override | `affiliates` | [Download Markdown](endpoints/affiliates/patch-update-override.md) |
| `POST /affiliates/{id}/unarchive` | Unarchive affiliate | `affiliates` | [Download Markdown](endpoints/affiliates/post-unarchive-affiliate.md) |
| `GET /ai_chats` | List ai chats | `ai-chats` | [Download Markdown](endpoints/ai-chats/get-list-ai-chats.md) |
| `POST /ai_chats` | Create ai chat | `ai-chats` | [Download Markdown](endpoints/ai-chats/post-create-ai-chat.md) |
| `DELETE /ai_chats/{id}` | Delete ai chat | `ai-chats` | [Download Markdown](endpoints/ai-chats/delete-delete-ai-chat.md) |
| `GET /ai_chats/{id}` | Retrieve ai chat | `ai-chats` | [Download Markdown](endpoints/ai-chats/get-retrieve-ai-chat.md) |
| `PATCH /ai_chats/{id}` | Update ai chat | `ai-chats` | [Download Markdown](endpoints/ai-chats/patch-update-ai-chat.md) |
| `GET /app_builds` | List app builds | `app-builds` | [Download Markdown](endpoints/app-builds/get-list-app-builds.md) |
| `POST /app_builds` | Create app build | `app-builds` | [Download Markdown](endpoints/app-builds/post-create-app-build.md) |
| `GET /app_builds/{id}` | Retrieve app build | `app-builds` | [Download Markdown](endpoints/app-builds/get-retrieve-app-build.md) |
| `POST /app_builds/{id}/promote` | Promote app build | `app-builds` | [Download Markdown](endpoints/app-builds/post-promote-app-build.md) |
| `GET /apps` | List apps | `apps` | [Download Markdown](endpoints/apps/get-list-apps.md) |
| `POST /apps` | Create app | `apps` | [Download Markdown](endpoints/apps/post-create-app.md) |
| `GET /apps/{id}` | Retrieve app | `apps` | [Download Markdown](endpoints/apps/get-retrieve-app.md) |
| `PATCH /apps/{id}` | Update app | `apps` | [Download Markdown](endpoints/apps/patch-update-app.md) |
| `GET /authorized_users` | List authorized users | `authorized-users` | [Download Markdown](endpoints/authorized-users/get-list-authorized-users.md) |
| `POST /authorized_users` | Create authorized user | `authorized-users` | [Download Markdown](endpoints/authorized-users/post-create-authorized-user.md) |
| `DELETE /authorized_users/{id}` | Delete authorized user | `authorized-users` | [Download Markdown](endpoints/authorized-users/delete-delete-authorized-user.md) |
| `GET /authorized_users/{id}` | Retrieve authorized user | `authorized-users` | [Download Markdown](endpoints/authorized-users/get-retrieve-authorized-user.md) |
| `GET /bounties` | List bounties | `bounties` | [Download Markdown](endpoints/bounties/get-list-bounties.md) |
| `POST /bounties` | Create bounty | `bounties` | [Download Markdown](endpoints/bounties/post-create-bounty.md) |
| `GET /bounties/{id}` | Retrieve bounty | `bounties` | [Download Markdown](endpoints/bounties/get-retrieve-bounty.md) |
| `GET /card_transactions` | List card transactions | `card-transactions` | [Download Markdown](endpoints/card-transactions/get-list-card-transactions.md) |
| `GET /card_transactions/{id}` | Retrieve card transaction | `card-transactions` | [Download Markdown](endpoints/card-transactions/get-retrieve-card-transaction.md) |
| `GET /chat_channels` | List chat channels | `chat-channels` | [Download Markdown](endpoints/chat-channels/get-list-chat-channels.md) |
| `GET /chat_channels/{id}` | Retrieve chat channel | `chat-channels` | [Download Markdown](endpoints/chat-channels/get-retrieve-chat-channel.md) |
| `PATCH /chat_channels/{id}` | Update chat channel | `chat-channels` | [Download Markdown](endpoints/chat-channels/patch-update-chat-channel.md) |
| `GET /checkout_configurations` | List checkout configurations | `checkout-configurations` | [Download Markdown](endpoints/checkout-configurations/get-list-checkout-configurations.md) |
| `POST /checkout_configurations` | Create checkout configuration | `checkout-configurations` | [Download Markdown](endpoints/checkout-configurations/post-create-checkout-configuration.md) |
| `GET /checkout_configurations/{id}` | Retrieve checkout configuration | `checkout-configurations` | [Download Markdown](endpoints/checkout-configurations/get-retrieve-checkout-configuration.md) |
| `GET /companies` | List companies | `companies` | [Download Markdown](endpoints/companies/get-list-companies.md) |
| `POST /companies` | Create company | `companies` | [Download Markdown](endpoints/companies/post-create-company.md) |
| `GET /companies/{id}` | Retrieve company | `companies` | [Download Markdown](endpoints/companies/get-retrieve-company.md) |
| `PATCH /companies/{id}` | Update company | `companies` | [Download Markdown](endpoints/companies/patch-update-company.md) |
| `POST /companies/{parent_company_id}/api_keys` | Create child company API key | `companies` | [Download Markdown](endpoints/companies/post-create-child-company-api-key.md) |
| `GET /company_token_transactions` | List company token transactions | `company-token-transactions` | [Download Markdown](endpoints/company-token-transactions/get-list-company-token-transactions.md) |
| `POST /company_token_transactions` | Create company token transaction | `company-token-transactions` | [Download Markdown](endpoints/company-token-transactions/post-create-company-token-transaction.md) |
| `GET /company_token_transactions/{id}` | Retrieve company token transaction | `company-token-transactions` | [Download Markdown](endpoints/company-token-transactions/get-retrieve-company-token-transaction.md) |
| `POST /conversions` | Create conversion | `conversions` | [Download Markdown](endpoints/conversions/post-create-conversion.md) |
| `GET /course_chapters` | List course chapters | `course-chapters` | [Download Markdown](endpoints/course-chapters/get-list-course-chapters.md) |
| `POST /course_chapters` | Create course chapter | `course-chapters` | [Download Markdown](endpoints/course-chapters/post-create-course-chapter.md) |
| `DELETE /course_chapters/{id}` | Delete course chapter | `course-chapters` | [Download Markdown](endpoints/course-chapters/delete-delete-course-chapter.md) |
| `GET /course_chapters/{id}` | Retrieve course chapter | `course-chapters` | [Download Markdown](endpoints/course-chapters/get-retrieve-course-chapter.md) |
| `PATCH /course_chapters/{id}` | Update course chapter | `course-chapters` | [Download Markdown](endpoints/course-chapters/patch-update-course-chapter.md) |
| `GET /course_lesson_interactions` | List course lesson interactions | `course-lesson-interactions` | [Download Markdown](endpoints/course-lesson-interactions/get-list-course-lesson-interactions.md) |
| `GET /course_lesson_interactions/{id}` | Retrieve course lesson interaction | `course-lesson-interactions` | [Download Markdown](endpoints/course-lesson-interactions/get-retrieve-course-lesson-interaction.md) |
| `GET /course_lessons` | List course lessons | `course-lessons` | [Download Markdown](endpoints/course-lessons/get-list-course-lessons.md) |
| `POST /course_lessons` | Create course lesson | `course-lessons` | [Download Markdown](endpoints/course-lessons/post-create-course-lesson.md) |
| `DELETE /course_lessons/{id}` | Delete course lesson | `course-lessons` | [Download Markdown](endpoints/course-lessons/delete-delete-course-lesson.md) |
| `GET /course_lessons/{id}` | Retrieve course lesson | `course-lessons` | [Download Markdown](endpoints/course-lessons/get-retrieve-course-lesson.md) |
| `PATCH /course_lessons/{id}` | Update course lesson | `course-lessons` | [Download Markdown](endpoints/course-lessons/patch-update-course-lesson.md) |
| `POST /course_lessons/{lesson_id}/mark_as_completed` | Mark as completed course lesson | `course-lessons` | [Download Markdown](endpoints/course-lessons/post-mark-as-completed-course-lesson.md) |
| `POST /course_lessons/{lesson_id}/start` | Start course lesson | `course-lessons` | [Download Markdown](endpoints/course-lessons/post-start-course-lesson.md) |
| `POST /course_lessons/{lesson_id}/submit_assessment` | Submit assessment course lesson | `course-lessons` | [Download Markdown](endpoints/course-lessons/post-submit-assessment-course-lesson.md) |
| `GET /course_students` | List course students | `course-students` | [Download Markdown](endpoints/course-students/get-list-course-students.md) |
| `GET /course_students/{id}` | Retrieve course student | `course-students` | [Download Markdown](endpoints/course-students/get-retrieve-course-student.md) |
| `GET /courses` | List courses | `courses` | [Download Markdown](endpoints/courses/get-list-courses.md) |
| `POST /courses` | Create course | `courses` | [Download Markdown](endpoints/courses/post-create-course.md) |
| `DELETE /courses/{id}` | Delete course | `courses` | [Download Markdown](endpoints/courses/delete-delete-course.md) |
| `GET /courses/{id}` | Retrieve course | `courses` | [Download Markdown](endpoints/courses/get-retrieve-course.md) |
| `PATCH /courses/{id}` | Update course | `courses` | [Download Markdown](endpoints/courses/patch-update-course.md) |
| `GET /dispute_alerts` | List dispute alerts | `dispute-alerts` | [Download Markdown](endpoints/dispute-alerts/get-list-dispute-alerts.md) |
| `GET /dispute_alerts/{id}` | Retrieve dispute alert | `dispute-alerts` | [Download Markdown](endpoints/dispute-alerts/get-retrieve-dispute-alert.md) |
| `GET /disputes` | List disputes | `disputes` | [Download Markdown](endpoints/disputes/get-list-disputes.md) |
| `GET /disputes/{id}` | Retrieve dispute | `disputes` | [Download Markdown](endpoints/disputes/get-retrieve-dispute.md) |
| `POST /disputes/{id}/submit_evidence` | Submit evidence | `disputes` | [Download Markdown](endpoints/disputes/post-submit-evidence.md) |
| `POST /disputes/{id}/update_evidence` | Update evidence | `disputes` | [Download Markdown](endpoints/disputes/post-update-evidence.md) |
| `GET /dm_channels` | List dm channels | `dm-channels` | [Download Markdown](endpoints/dm-channels/get-list-dm-channels.md) |
| `POST /dm_channels` | Create dm channel | `dm-channels` | [Download Markdown](endpoints/dm-channels/post-create-dm-channel.md) |
| `DELETE /dm_channels/{id}` | Delete dm channel | `dm-channels` | [Download Markdown](endpoints/dm-channels/delete-delete-dm-channel.md) |
| `GET /dm_channels/{id}` | Retrieve dm channel | `dm-channels` | [Download Markdown](endpoints/dm-channels/get-retrieve-dm-channel.md) |
| `PATCH /dm_channels/{id}` | Update dm channel | `dm-channels` | [Download Markdown](endpoints/dm-channels/patch-update-dm-channel.md) |
| `GET /dm_members` | List dm members | `dm-members` | [Download Markdown](endpoints/dm-members/get-list-dm-members.md) |
| `POST /dm_members` | Create dm member | `dm-members` | [Download Markdown](endpoints/dm-members/post-create-dm-member.md) |
| `DELETE /dm_members/{id}` | Delete dm member | `dm-members` | [Download Markdown](endpoints/dm-members/delete-delete-dm-member.md) |
| `GET /dm_members/{id}` | Retrieve dm member | `dm-members` | [Download Markdown](endpoints/dm-members/get-retrieve-dm-member.md) |
| `PATCH /dm_members/{id}` | Update dm member | `dm-members` | [Download Markdown](endpoints/dm-members/patch-update-dm-member.md) |
| `GET /entries` | List entries | `entries` | [Download Markdown](endpoints/entries/get-list-entries.md) |
| `GET /entries/{id}` | Retrieve entry | `entries` | [Download Markdown](endpoints/entries/get-retrieve-entry.md) |
| `POST /entries/{id}/approve` | Approve entry | `entries` | [Download Markdown](endpoints/entries/post-approve-entry.md) |
| `POST /entries/{id}/deny` | Deny entry | `entries` | [Download Markdown](endpoints/entries/post-deny-entry.md) |
| `GET /experiences` | List experiences | `experiences` | [Download Markdown](endpoints/experiences/get-list-experiences.md) |
| `POST /experiences` | Create experience | `experiences` | [Download Markdown](endpoints/experiences/post-create-experience.md) |
| `DELETE /experiences/{id}` | Delete experience | `experiences` | [Download Markdown](endpoints/experiences/delete-delete-experience.md) |
| `GET /experiences/{id}` | Retrieve experience | `experiences` | [Download Markdown](endpoints/experiences/get-retrieve-experience.md) |
| `PATCH /experiences/{id}` | Update experience | `experiences` | [Download Markdown](endpoints/experiences/patch-update-experience.md) |
| `POST /experiences/{id}/attach` | Attach experience | `experiences` | [Download Markdown](endpoints/experiences/post-attach-experience.md) |
| `POST /experiences/{id}/detach` | Detach experience | `experiences` | [Download Markdown](endpoints/experiences/post-detach-experience.md) |
| `POST /experiences/{id}/duplicate` | Duplicate experience | `experiences` | [Download Markdown](endpoints/experiences/post-duplicate-experience.md) |
| `GET /fee_markups` | List fee markups | `fee-markups` | [Download Markdown](endpoints/fee-markups/get-list-fee-markups.md) |
| `POST /fee_markups` | Create fee markup | `fee-markups` | [Download Markdown](endpoints/fee-markups/post-create-fee-markup.md) |
| `DELETE /fee_markups/{id}` | Delete fee markup | `fee-markups` | [Download Markdown](endpoints/fee-markups/delete-delete-fee-markup.md) |
| `POST /files` | Create file | `files` | [Download Markdown](endpoints/files/post-create-file.md) |
| `GET /files/{id}` | Retrieve file | `files` | [Download Markdown](endpoints/files/get-retrieve-file.md) |
| `GET /forum_posts` | List forum posts | `forum-posts` | [Download Markdown](endpoints/forum-posts/get-list-forum-posts.md) |
| `POST /forum_posts` | Create forum post | `forum-posts` | [Download Markdown](endpoints/forum-posts/post-create-forum-post.md) |
| `GET /forum_posts/{id}` | Retrieve forum post | `forum-posts` | [Download Markdown](endpoints/forum-posts/get-retrieve-forum-post.md) |
| `PATCH /forum_posts/{id}` | Update forum post | `forum-posts` | [Download Markdown](endpoints/forum-posts/patch-update-forum-post.md) |
| `GET /forums` | List forums | `forums` | [Download Markdown](endpoints/forums/get-list-forums.md) |
| `GET /forums/{id}` | Retrieve forum | `forums` | [Download Markdown](endpoints/forums/get-retrieve-forum.md) |
| `PATCH /forums/{id}` | Update forum | `forums` | [Download Markdown](endpoints/forums/patch-update-forum.md) |
| `GET /invoices` | List invoices | `invoices` | [Download Markdown](endpoints/invoices/get-list-invoices.md) |
| `POST /invoices` | Create invoice | `invoices` | [Download Markdown](endpoints/invoices/post-create-invoice.md) |
| `DELETE /invoices/{id}` | Delete invoice | `invoices` | [Download Markdown](endpoints/invoices/delete-delete-invoice.md) |
| `GET /invoices/{id}` | Retrieve invoice | `invoices` | [Download Markdown](endpoints/invoices/get-retrieve-invoice.md) |
| `PATCH /invoices/{id}` | Update invoice | `invoices` | [Download Markdown](endpoints/invoices/patch-update-invoice.md) |
| `POST /invoices/{id}/mark_paid` | Mark paid invoice | `invoices` | [Download Markdown](endpoints/invoices/post-mark-paid-invoice.md) |
| `POST /invoices/{id}/mark_uncollectible` | Mark uncollectible invoice | `invoices` | [Download Markdown](endpoints/invoices/post-mark-uncollectible-invoice.md) |
| `POST /invoices/{id}/void` | Void invoice | `invoices` | [Download Markdown](endpoints/invoices/post-void-invoice.md) |
| `GET /leads` | List leads | `leads` | [Download Markdown](endpoints/leads/get-list-leads.md) |
| `POST /leads` | Create lead | `leads` | [Download Markdown](endpoints/leads/post-create-lead.md) |
| `GET /leads/{id}` | Retrieve lead | `leads` | [Download Markdown](endpoints/leads/get-retrieve-lead.md) |
| `PATCH /leads/{id}` | Update lead | `leads` | [Download Markdown](endpoints/leads/patch-update-lead.md) |
| `GET /ledger_accounts/{id}` | Retrieve ledger account | `ledger-accounts` | [Download Markdown](endpoints/ledger-accounts/get-retrieve-ledger-account.md) |
| `GET /members` | List members | `members` | [Download Markdown](endpoints/members/get-list-members.md) |
| `GET /members/{id}` | Retrieve member | `members` | [Download Markdown](endpoints/members/get-retrieve-member.md) |
| `GET /memberships` | List memberships | `memberships` | [Download Markdown](endpoints/memberships/get-list-memberships.md) |
| `GET /memberships/{id}` | Retrieve membership | `memberships` | [Download Markdown](endpoints/memberships/get-retrieve-membership.md) |
| `PATCH /memberships/{id}` | Update membership | `memberships` | [Download Markdown](endpoints/memberships/patch-update-membership.md) |
| `POST /memberships/{id}/add_free_days` | Add free days membership | `memberships` | [Download Markdown](endpoints/memberships/post-add-free-days-membership.md) |
| `POST /memberships/{id}/cancel` | Cancel membership | `memberships` | [Download Markdown](endpoints/memberships/post-cancel-membership.md) |
| `POST /memberships/{id}/pause` | Pause membership | `memberships` | [Download Markdown](endpoints/memberships/post-pause-membership.md) |
| `POST /memberships/{id}/resume` | Resume membership | `memberships` | [Download Markdown](endpoints/memberships/post-resume-membership.md) |
| `POST /memberships/{id}/uncancel` | Uncancel membership | `memberships` | [Download Markdown](endpoints/memberships/post-uncancel-membership.md) |
| `GET /messages` | List messages | `messages` | [Download Markdown](endpoints/messages/get-list-messages.md) |
| `POST /messages` | Create message | `messages` | [Download Markdown](endpoints/messages/post-create-message.md) |
| `DELETE /messages/{id}` | Delete message | `messages` | [Download Markdown](endpoints/messages/delete-delete-message.md) |
| `GET /messages/{id}` | Retrieve message | `messages` | [Download Markdown](endpoints/messages/get-retrieve-message.md) |
| `PATCH /messages/{id}` | Update message | `messages` | [Download Markdown](endpoints/messages/patch-update-message.md) |
| `POST /notifications` | Create notification | `notifications` | [Download Markdown](endpoints/notifications/post-create-notification.md) |
| `GET /payment_methods` | List payment methods | `payment-methods` | [Download Markdown](endpoints/payment-methods/get-list-payment-methods.md) |
| `GET /payment_methods/{id}` | Retrieve payment method | `payment-methods` | [Download Markdown](endpoints/payment-methods/get-retrieve-payment-method.md) |
| `GET /payments` | List payments | `payments` | [Download Markdown](endpoints/payments/get-list-payments.md) |
| `POST /payments` | Create payment | `payments` | [Download Markdown](endpoints/payments/post-create-payment.md) |
| `GET /payments/{id}` | Retrieve payment | `payments` | [Download Markdown](endpoints/payments/get-retrieve-payment.md) |
| `GET /payments/{id}/fees` | List fees | `payments` | [Download Markdown](endpoints/payments/get-list-fees.md) |
| `POST /payments/{id}/refund` | Refund payment | `payments` | [Download Markdown](endpoints/payments/post-refund-payment.md) |
| `POST /payments/{id}/retry` | Retry payment | `payments` | [Download Markdown](endpoints/payments/post-retry-payment.md) |
| `POST /payments/{id}/void` | Void payment | `payments` | [Download Markdown](endpoints/payments/post-void-payment.md) |
| `GET /payout_accounts/{id}` | Retrieve payout account | `payout-accounts` | [Download Markdown](endpoints/payout-accounts/get-retrieve-payout-account.md) |
| `GET /payout_methods` | List payout methods | `payout-methods` | [Download Markdown](endpoints/payout-methods/get-list-payout-methods.md) |
| `GET /payout_methods/{id}` | Retrieve payout method | `payout-methods` | [Download Markdown](endpoints/payout-methods/get-retrieve-payout-method.md) |
| `GET /plans` | List plans | `plans` | [Download Markdown](endpoints/plans/get-list-plans.md) |
| `POST /plans` | Create plan | `plans` | [Download Markdown](endpoints/plans/post-create-plan.md) |
| `DELETE /plans/{id}` | Delete plan | `plans` | [Download Markdown](endpoints/plans/delete-delete-plan.md) |
| `GET /plans/{id}` | Retrieve plan | `plans` | [Download Markdown](endpoints/plans/get-retrieve-plan.md) |
| `PATCH /plans/{id}` | Update plan | `plans` | [Download Markdown](endpoints/plans/patch-update-plan.md) |
| `GET /products` | List products | `products` | [Download Markdown](endpoints/products/get-list-products.md) |
| `POST /products` | Create product | `products` | [Download Markdown](endpoints/products/post-create-product.md) |
| `DELETE /products/{id}` | Delete product | `products` | [Download Markdown](endpoints/products/delete-delete-product.md) |
| `GET /products/{id}` | Retrieve product | `products` | [Download Markdown](endpoints/products/get-retrieve-product.md) |
| `PATCH /products/{id}` | Update product | `products` | [Download Markdown](endpoints/products/patch-update-product.md) |
| `GET /promo_codes` | List promo codes | `promo-codes` | [Download Markdown](endpoints/promo-codes/get-list-promo-codes.md) |
| `POST /promo_codes` | Create promo code | `promo-codes` | [Download Markdown](endpoints/promo-codes/post-create-promo-code.md) |
| `DELETE /promo_codes/{id}` | Delete promo code | `promo-codes` | [Download Markdown](endpoints/promo-codes/delete-delete-promo-code.md) |
| `GET /promo_codes/{id}` | Retrieve promo code | `promo-codes` | [Download Markdown](endpoints/promo-codes/get-retrieve-promo-code.md) |
| `GET /reactions` | List reactions | `reactions` | [Download Markdown](endpoints/reactions/get-list-reactions.md) |
| `POST /reactions` | Create reaction | `reactions` | [Download Markdown](endpoints/reactions/post-create-reaction.md) |
| `DELETE /reactions/{id}` | Delete reaction | `reactions` | [Download Markdown](endpoints/reactions/delete-delete-reaction.md) |
| `GET /reactions/{id}` | Retrieve reaction | `reactions` | [Download Markdown](endpoints/reactions/get-retrieve-reaction.md) |
| `GET /refunds` | List refunds | `refunds` | [Download Markdown](endpoints/refunds/get-list-refunds.md) |
| `GET /refunds/{id}` | Retrieve refund | `refunds` | [Download Markdown](endpoints/refunds/get-retrieve-refund.md) |
| `GET /resolution_center_cases` | List resolution center cases | `resolution-center-cases` | [Download Markdown](endpoints/resolution-center-cases/get-list-resolution-center-cases.md) |
| `GET /resolution_center_cases/{id}` | Retrieve resolution center case | `resolution-center-cases` | [Download Markdown](endpoints/resolution-center-cases/get-retrieve-resolution-center-case.md) |
| `GET /reviews` | List reviews | `reviews` | [Download Markdown](endpoints/reviews/get-list-reviews.md) |
| `GET /reviews/{id}` | Retrieve review | `reviews` | [Download Markdown](endpoints/reviews/get-retrieve-review.md) |
| `GET /setup_intents` | List setup intents | `setup-intents` | [Download Markdown](endpoints/setup-intents/get-list-setup-intents.md) |
| `GET /setup_intents/{id}` | Retrieve setup intent | `setup-intents` | [Download Markdown](endpoints/setup-intents/get-retrieve-setup-intent.md) |
| `GET /shipments` | List shipments | `shipments` | [Download Markdown](endpoints/shipments/get-list-shipments.md) |
| `POST /shipments` | Create shipment | `shipments` | [Download Markdown](endpoints/shipments/post-create-shipment.md) |
| `GET /shipments/{id}` | Retrieve shipment | `shipments` | [Download Markdown](endpoints/shipments/get-retrieve-shipment.md) |
| `GET /stats/describe` | Describe stats | `stats` | [Download Markdown](endpoints/stats/get-describe-stats.md) |
| `GET /stats/metric` | Metric stats | `stats` | [Download Markdown](endpoints/stats/get-metric-stats.md) |
| `GET /stats/raw` | Raw stats | `stats` | [Download Markdown](endpoints/stats/get-raw-stats.md) |
| `GET /stats/sql` | Sql stats | `stats` | [Download Markdown](endpoints/stats/get-sql-stats.md) |
| `GET /support_channels` | List support channels | `support-channels` | [Download Markdown](endpoints/support-channels/get-list-support-channels.md) |
| `POST /support_channels` | Create support channel | `support-channels` | [Download Markdown](endpoints/support-channels/post-create-support-channel.md) |
| `GET /support_channels/{id}` | Retrieve support channel | `support-channels` | [Download Markdown](endpoints/support-channels/get-retrieve-support-channel.md) |
| `POST /topups` | Create topup | `topups` | [Download Markdown](endpoints/topups/post-create-topup.md) |
| `GET /transfers` | List transfers | `transfers` | [Download Markdown](endpoints/transfers/get-list-transfers.md) |
| `POST /transfers` | Create transfer | `transfers` | [Download Markdown](endpoints/transfers/post-create-transfer.md) |
| `GET /transfers/{id}` | Retrieve transfer | `transfers` | [Download Markdown](endpoints/transfers/get-retrieve-transfer.md) |
| `GET /users` | List users | `users` | [Download Markdown](endpoints/users/get-list-users.md) |
| `GET /users/{id}` | Retrieve user | `users` | [Download Markdown](endpoints/users/get-retrieve-user.md) |
| `PATCH /users/{id}` | Update user | `users` | [Download Markdown](endpoints/users/patch-update-user.md) |
| `GET /users/{id}/access/{resource_id}` | Check access | `users` | [Download Markdown](endpoints/users/get-check-access.md) |
| `GET /verifications` | List verifications | `verifications` | [Download Markdown](endpoints/verifications/get-list-verifications.md) |
| `GET /verifications/{id}` | Retrieve verification | `verifications` | [Download Markdown](endpoints/verifications/get-retrieve-verification.md) |
| `GET /webhooks` | List webhooks | `webhooks` | [Download Markdown](endpoints/webhooks/get-list-webhooks.md) |
| `POST /webhooks` | Create webhook | `webhooks` | [Download Markdown](endpoints/webhooks/post-create-webhook.md) |
| `DELETE /webhooks/{id}` | Delete webhook | `webhooks` | [Download Markdown](endpoints/webhooks/delete-delete-webhook.md) |
| `GET /webhooks/{id}` | Retrieve webhook | `webhooks` | [Download Markdown](endpoints/webhooks/get-retrieve-webhook.md) |
| `PATCH /webhooks/{id}` | Update webhook | `webhooks` | [Download Markdown](endpoints/webhooks/patch-update-webhook.md) |
| `GET /withdrawals` | List withdrawals | `withdrawals` | [Download Markdown](endpoints/withdrawals/get-list-withdrawals.md) |
| `POST /withdrawals` | Create withdrawal | `withdrawals` | [Download Markdown](endpoints/withdrawals/post-create-withdrawal.md) |
| `GET /withdrawals/{id}` | Retrieve withdrawal | `withdrawals` | [Download Markdown](endpoints/withdrawals/get-retrieve-withdrawal.md) |
