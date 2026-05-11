package dev.lepshee.whop

import dev.lepshee.whop.core.JsonConfig
import dev.lepshee.whop.models.bounties.Bounty
import dev.lepshee.whop.models.bounties.BountyStatus
import dev.lepshee.whop.models.bounties.CreateBountyRequest
import dev.lepshee.whop.models.checkout.CheckoutConfiguration
import dev.lepshee.whop.models.checkout.CheckoutMode
import dev.lepshee.whop.models.checkout.CheckoutPlan
import dev.lepshee.whop.models.checkout.CreateCheckoutConfigurationRequest
import dev.lepshee.whop.models.common.Money
import dev.lepshee.whop.models.companies.Company
import dev.lepshee.whop.models.companies.CreateCompanyRequest
import dev.lepshee.whop.models.companies.ImageReference
import dev.lepshee.whop.models.companies.UpdateCompanyRequest
import dev.lepshee.whop.models.conversions.Conversion
import dev.lepshee.whop.models.conversions.ConversionActionSource
import dev.lepshee.whop.models.conversions.ConversionContext
import dev.lepshee.whop.models.conversions.ConversionEventName
import dev.lepshee.whop.models.conversions.ConversionGender
import dev.lepshee.whop.models.conversions.ConversionUser
import dev.lepshee.whop.models.conversions.CreateConversionRequest
import dev.lepshee.whop.models.feemarkups.CreateFeeMarkupRequest
import dev.lepshee.whop.models.feemarkups.FeeMarkupType
import dev.lepshee.whop.models.invoices.CreateInvoiceRequest
import dev.lepshee.whop.models.invoices.InvoiceCollectionMethod
import dev.lepshee.whop.models.invoices.InvoicePlanRequest
import dev.lepshee.whop.models.invoices.InvoiceProductInput
import dev.lepshee.whop.models.invoices.UpdateInvoiceRequest
import dev.lepshee.whop.models.ledgeraccounts.LedgerAccount
import dev.lepshee.whop.models.ledgeraccounts.LedgerType
import dev.lepshee.whop.models.memberships.CancelMembershipRequest
import dev.lepshee.whop.models.memberships.Membership
import dev.lepshee.whop.models.memberships.MembershipCancellationMode
import dev.lepshee.whop.models.memberships.UpdateMembershipRequest
import dev.lepshee.whop.models.payments.CreatePaymentPlan
import dev.lepshee.whop.models.payments.CreatePaymentProduct
import dev.lepshee.whop.models.payments.CreatePaymentRequest
import dev.lepshee.whop.models.payments.Payment
import dev.lepshee.whop.models.payments.RefundPaymentRequest
import dev.lepshee.whop.models.plans.CreatePlanRequest
import dev.lepshee.whop.models.plans.Plan
import dev.lepshee.whop.models.plans.PlanType
import dev.lepshee.whop.models.products.Product
import dev.lepshee.whop.models.products.ProductPlanOptions
import dev.lepshee.whop.models.products.ReleaseMethod
import dev.lepshee.whop.models.promocodes.CreatePromoCodeRequest
import dev.lepshee.whop.models.promocodes.PromoCode
import dev.lepshee.whop.models.promocodes.PromoCodeStatus
import dev.lepshee.whop.models.promocodes.PromoType
import dev.lepshee.whop.models.resolutioncentercases.ResolutionCenterCase
import dev.lepshee.whop.models.resolutioncentercases.ResolutionCenterCaseStatus
import dev.lepshee.whop.models.shipments.CreateShipmentRequest
import dev.lepshee.whop.models.shipments.Shipment
import dev.lepshee.whop.models.shipments.ShipmentStatus
import dev.lepshee.whop.models.topups.CreateTopupRequest
import dev.lepshee.whop.models.topups.Topup
import dev.lepshee.whop.models.topups.TopupStatus
import dev.lepshee.whop.models.transfers.CreateTransferRequest
import dev.lepshee.whop.models.webhooks.CreateWebhookRequest
import dev.lepshee.whop.models.webhooks.WebhookApiVersion
import dev.lepshee.whop.models.webhooks.WebhookEvent
import dev.lepshee.whop.models.withdrawals.CreateWithdrawalRequest
import dev.lepshee.whop.models.withdrawals.Withdrawal
import dev.lepshee.whop.models.withdrawals.WithdrawalStatus
import dev.lepshee.whop.oauth.OAuthCallback
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class RequestModelSerializationTest {
    @Test
    fun `uncovered request models serialize documented snake case fields`() {
        val metadata = buildJsonObject { put("tier", "vip") }

        assertJsonContains(UpdateCompanyRequest(bannerImage = ImageReference("file_123")), "banner_image")
        assertJsonContains(CreateCompanyRequest(title = "Acme", sendCustomerEmails = true), "send_customer_emails")
        assertJsonContains(UpdateMembershipRequest(metadata), "metadata")
        assertJsonContains(CancelMembershipRequest(MembershipCancellationMode.AtPeriodEnd), "at_period_end")
        assertJsonContains(CreateTransferRequest(10.0, "usd", "biz_dest", "biz_origin", "transfer_123"), "idempotence_key")
        assertJsonContains(CreatePaymentRequest("biz_123", "mem_123", "pm_123", planId = "plan_123"), "payment_method_id")
        assertJsonContains(RefundPaymentRequest(partialAmount = 6.9), "partial_amount")
        assertJsonContains(CreateShipmentRequest("biz_123", "pay_123", "9400111899223456789012"), "tracking_code")
        assertJsonContains(CreateTopupRequest(6.9, "biz_123", "usd", "payt_123"), "payment_method_id")
        assertJsonContains(CreateWithdrawalRequest(6.9, "biz_123", "usd", payoutMethodId = "pout_123"), "payout_method_id")
        assertJsonContains(CreateBountyRequest(6.9, "usd", "Review videos", "Video bounty"), "base_unit_amount")
        assertJsonContains(
            CreateConversionRequest(
                companyId = "biz_123",
                eventName = ConversionEventName.CompleteRegistration,
                actionSource = ConversionActionSource.Website,
                context = ConversionContext(ipAddress = "203.0.113.10", utmCampaign = "spring"),
                user = ConversionUser(firstName = "Jane", gender = ConversionGender.Female),
            ),
            "action_source",
        )
        assertJsonContains(
            CreatePromoCodeRequest(
                amountOff = 25.0,
                baseCurrency = "usd",
                code = "SAVE25",
                companyId = "biz_123",
                newUsersOnly = true,
                promoDurationMonths = 3,
                promoType = PromoType.Percentage,
            ),
            "promo_duration_months",
        )
        assertJsonContains(CreateWebhookRequest("https://example.com", WebhookApiVersion.V5), "api_version")
        assertJsonContains(CreateFeeMarkupRequest("biz_123", FeeMarkupType.CryptoWithdrawalMarkup), "fee_type")
        assertJsonContains(
            CreateInvoiceRequest(
                companyId = "biz_123",
                plan = InvoicePlanRequest(initialPrice = 20.0),
                collectionMethod = InvoiceCollectionMethod.SendInvoice,
                product = InvoiceProductInput("VIP"),
                customerName = CreateInvoiceRequest.text("Jane Buyer"),
                emailAddress = CreateInvoiceRequest.text("jane@example.com"),
                dueDate = CreateInvoiceRequest.text("2024-02-01T00:00:00Z"),
            ),
            "collection_method",
        )
    }

    @Test
    fun `nullable request fields can be omitted or explicitly cleared when documented`() {
        assertEquals("{}", JsonConfig.whopJson.encodeToString(CancelMembershipRequest()))
        assertEquals("{}", JsonConfig.whopJson.encodeToString(UpdateMembershipRequest()))

        val clearMetadata = JsonConfig.whopJson.encodeToString(UpdateMembershipRequest(JsonNull))
        assertTrue(clearMetadata.contains("\"metadata\":null"), clearMetadata)

        val clearFeeMarkup =
            JsonConfig.whopJson.encodeToString(
                CreateFeeMarkupRequest(
                    companyId = "biz_123",
                    feeType = FeeMarkupType.CryptoWithdrawalMarkup,
                    percentageFee = CreateFeeMarkupRequest.clear,
                ),
            )
        assertTrue(clearFeeMarkup.contains("\"percentage_fee\":null"), clearFeeMarkup)

        val clearInvoice = JsonConfig.whopJson.encodeToString(UpdateInvoiceRequest(customerName = UpdateInvoiceRequest.clear))
        assertTrue(clearInvoice.contains("\"customer_name\":null"), clearInvoice)
    }

    @Test
    fun `webhook create serializes documented boolean child resource events and enum events`() {
        val json =
            JsonConfig.whopJson.encodeToString(
                CreateWebhookRequest(
                    url = "https://example.com/webhooks",
                    childResourceEvents = true,
                    events = listOf(WebhookEvent.PaymentSucceeded),
                ),
            )

        assertTrue(json.contains("\"child_resource_events\":true"), json)
        assertTrue(json.contains("payment.succeeded"), json)
    }

    @Test
    fun `response models parse documented fields exposed by sdk`() {
        val checkout =
            JsonConfig.whopJson.decodeFromString<CheckoutConfiguration>(
                """
                {"id":"chc_123","metadata":null,"mode":"payment","currency":"usd","plan":{},"allow_promo_codes":true}
                """.trimIndent(),
            )
        assertEquals(CheckoutMode.PAYMENT, checkout.mode)
        assertEquals("usd", checkout.currency)

        val payment =
            JsonConfig.whopJson.decodeFromString<Payment>(
                """
                {"id":"pay_123","substatus":"succeeded","refundable":true,"retryable":false,"voidable":false,"final_amount":20.0,"plan":{},"product":{},"payment_method":{},"disputes":[{}]}
                """.trimIndent(),
            )
        assertEquals("succeeded", payment.substatus)
        assertEquals(20.0, payment.finalAmount)
        assertTrue(payment.refundable == true)
        assertEquals(1, payment.disputes?.size)

        val product =
            JsonConfig.whopJson.decodeFromString<Product>(
                """
                {"id":"prod_123","published_reviews_count":2,"custom_statement_descriptor":"WHOP*VIP","company":{},"gallery_images":[{}]}
                """.trimIndent(),
            )
        assertEquals(2, product.publishedReviewsCount)
        assertEquals(1, product.galleryImages.size)

        val plan =
            JsonConfig.whopJson.decodeFromString<Plan>(
                """
                {"id":"plan_123","company":{},"product":{},"split_pay_required_payments":2,"adaptive_pricing_enabled":true,"tax_type":"inclusive","collect_tax":true,"custom_fields":[]}
                """.trimIndent(),
            )
        assertEquals(2, plan.splitPayRequiredPayments)
        assertTrue(plan.collectTax == true)

        val membership =
            JsonConfig.whopJson.decodeFromString<Membership>(
                """
                {"id":"mem_123","renewal_period_start":"2026-01-01T00:00:00Z","cancel_at_period_end":false,"currency":"usd","custom_field_responses":[{}]}
                """.trimIndent(),
            )
        assertEquals("usd", membership.currency)
        assertEquals(1, membership.customFieldResponses.size)

        val company =
            JsonConfig.whopJson.decodeFromString<Company>(
                """
                {"id":"biz_123","published_reviews_count":4,"affiliate_instructions":"Share us","featured_affiliate_product":{}}
                """.trimIndent(),
            )
        assertEquals(4, company.publishedReviewsCount)

        val ledgerAccount =
            JsonConfig.whopJson.decodeFromString<LedgerAccount>(
                """
                {"id":"ldgr_123","balances":[{"balance":1.0,"currency":"usd","pending_balance":0.0,"reserve_balance":0.0}],"transfer_fee":null,"payout_account_details":null,"payments_approval_status":null,"ledger_type":"pool","owner":{"typename":"User","id":"user_123","name":null,"username":"jane"}}
                """.trimIndent(),
            )
        assertEquals(LedgerType.Pool, ledgerAccount.ledgerType)

        val resolutionCenterCase =
            JsonConfig.whopJson.decodeFromString<ResolutionCenterCase>(
                """
                {"id":"reso_123","status":"customer_won","issue":"forgot_to_cancel","created_at":"2024-01-01T00:00:00Z","updated_at":"2024-01-02T00:00:00Z","due_date":null,"customer_appealed":false,"merchant_appealed":false,"customer_response_actions":[],"merchant_response_actions":[],"company":null,"user":{"id":"user_123","name":null,"username":"jane"},"platform_response_actions":[],"payment":{"id":"pay_123","currency":null,"created_at":"2024-01-01T00:00:00Z","paid_at":null,"total":20.0,"subtotal":null},"member":null,"resolution_events":[]}
                """.trimIndent(),
            )
        assertEquals(ResolutionCenterCaseStatus.CustomerWon, resolutionCenterCase.status)

        val shipment =
            JsonConfig.whopJson.decodeFromString<Shipment>(
                """
                {"id":"ship_123","created_at":"2023-12-01T05:00:00.401Z","status":"delivered","substatus":null,"tracking_code":"9400111899223456789012","updated_at":"2023-12-02T05:00:00.401Z","carrier":"usps","service":null,"delivery_estimate":null,"payment":{"id":"pay_123"}}
                """.trimIndent(),
            )
        assertEquals(ShipmentStatus.Delivered, shipment.status)
        assertEquals("pay_123", shipment.payment?.id)

        val topup =
            JsonConfig.whopJson.decodeFromString<Topup>(
                """
                {"id":"pay_123","status":"paid","created_at":"2023-12-01T05:00:00.401Z","paid_at":null,"currency":"usd","total":6.9,"failure_message":null}
                """.trimIndent(),
            )
        assertEquals(TopupStatus.Paid, topup.status)
        assertEquals(6.9, topup.total)

        val withdrawal =
            JsonConfig.whopJson.decodeFromString<Withdrawal>(
                """
                {"id":"wdrl_123","status":"requested","amount":6.9,"currency":"usd","fee_amount":0.5,"fee_type":null,"speed":"standard","created_at":"2023-12-01T05:00:00.401Z","markup_fee":0.25,"ledger_account":{"id":"ldgr_123","company_id":null},"payout_token":null,"error_code":null,"error_message":null,"estimated_availability":null,"trace_code":null}
                """.trimIndent(),
            )
        assertEquals(WithdrawalStatus.Requested, withdrawal.status)
        assertEquals("ldgr_123", withdrawal.ledgerAccount.id)

        val bounty =
            JsonConfig.whopJson.decodeFromString<Bounty>(
                """
                {"id":"bnty_123","title":"Video bounty","description":"Review videos","status":"archived","total_available":6.9,"total_paid":0.0,"currency":"usd","bounty_type":"workforce","vote_threshold":42,"created_at":"2023-12-01T05:00:00.401Z","updated_at":"2023-12-02T05:00:00.401Z"}
                """.trimIndent(),
            )
        assertEquals(BountyStatus.Archived, bounty.status)
        assertEquals(42, bounty.voteThreshold)

        val conversion =
            JsonConfig.whopJson.decodeFromString<Conversion>(
                """
                {"id":"cnv_123"}
                """.trimIndent(),
            )
        assertEquals("cnv_123", conversion.id)

        val promoCode =
            JsonConfig.whopJson.decodeFromString<PromoCode>(
                """
                {"id":"promo_123","amount_off":25.0,"currency":"usd","churned_users_only":false,"code":"SAVE25","created_at":"2024-01-01T00:00:00Z","existing_memberships_only":false,"duration":"repeating","expires_at":null,"new_users_only":true,"promo_duration_months":3,"one_per_customer":true,"product":{"id":"prod_123","title":"VIP"},"promo_type":"percentage","status":"active","stock":100,"unlimited_stock":false,"uses":2,"company":{"id":"biz_123","title":"Acme"}}
                """.trimIndent(),
            )
        assertEquals(PromoCodeStatus.Active, promoCode.status)
        assertEquals(PromoType.Percentage, promoCode.promoType)
        assertEquals("biz_123", promoCode.company.id)
    }

    @Test
    fun `checkout request models documented one of variants`() {
        assertJsonContains(CreateCheckoutConfigurationRequest(planId = "plan_123"), "plan_id")
        assertJsonContains(CreateCheckoutConfigurationRequest(mode = CheckoutMode.SETUP, companyId = "biz_123"), "setup")

        assertFailsWith<IllegalArgumentException> {
            CreateCheckoutConfigurationRequest(
                plan = CheckoutPlan.oneTime("biz_123", Money("usd", 2_000)),
                planId = "plan_123",
            )
        }
        assertFailsWith<IllegalArgumentException> { CreateCheckoutConfigurationRequest(mode = CheckoutMode.SETUP) }
    }

    @Test
    fun `payment request models documented one of variants`() {
        val inlinePayment =
            CreatePaymentRequest(
                companyId = "biz_123",
                memberId = "mber_123",
                paymentMethodId = "payt_123",
                plan =
                    CreatePaymentPlan(
                        currency = "usd",
                        planType = PlanType.OneTime,
                        initialPrice = 20.0,
                        product = CreatePaymentProduct(externalIdentifier = "sku_123", title = "VIP"),
                    ),
            )

        val json = JsonConfig.whopJson.encodeToString(inlinePayment)
        assertTrue(json.contains("\"plan\""), json)
        assertTrue(json.contains("external_identifier"), json)

        assertFailsWith<IllegalArgumentException> {
            CreatePaymentRequest("biz_123", "mber_123", "payt_123", planId = "plan_123", plan = CreatePaymentPlan("usd"))
        }
        assertFailsWith<IllegalArgumentException> { CreatePaymentRequest("biz_123", "mber_123", "payt_123") }
    }

    @Test
    fun `money helpers produce documented decimal price payloads from minor units`() {
        val price = Money(currency = "usd", amount = 2_000)

        assertEquals(20.0, price.toMajorUnits())
        assertEquals(20.0, CheckoutPlan.oneTime("biz_123", price).initialPrice)
        assertEquals(20.0, CreatePlanRequest.oneTime("biz_123", "prod_123", price).initialPrice)
        assertEquals(20.0, ProductPlanOptions.oneTime(price, ReleaseMethod.BuyNow).initialPrice)
        assertJsonContains(CheckoutPlan.renewal("biz_123", price), "initial_price")
    }

    @Test
    fun `oauth callback verifies state before code exchange`() {
        val callback = OAuthCallback(code = "code_123", state = "state_123")

        assertEquals(callback, callback.requireState("state_123"))
        assertFailsWith<IllegalArgumentException> { callback.requireState("different_state") }
    }

    private inline fun <reified T> assertJsonContains(
        value: T,
        expected: String,
    ) {
        val json = JsonConfig.whopJson.encodeToString(value)
        assertTrue(json.contains(expected), json)
    }
}
