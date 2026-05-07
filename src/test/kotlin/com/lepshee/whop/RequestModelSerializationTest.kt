package com.lepshee.whop

import com.lepshee.whop.core.JsonConfig
import com.lepshee.whop.models.checkout.CheckoutPlan
import com.lepshee.whop.models.common.Money
import com.lepshee.whop.models.companies.CreateCompanyRequest
import com.lepshee.whop.models.companies.ImageReference
import com.lepshee.whop.models.companies.UpdateCompanyRequest
import com.lepshee.whop.models.memberships.CancelMembershipRequest
import com.lepshee.whop.models.memberships.MembershipCancellationMode
import com.lepshee.whop.models.memberships.UpdateMembershipRequest
import com.lepshee.whop.models.payments.CreatePaymentRequest
import com.lepshee.whop.models.payments.RefundPaymentRequest
import com.lepshee.whop.models.plans.CreatePlanRequest
import com.lepshee.whop.models.products.ProductPlanOptions
import com.lepshee.whop.models.products.ReleaseMethod
import com.lepshee.whop.models.transfers.CreateTransferRequest
import com.lepshee.whop.models.webhooks.CreateWebhookRequest
import com.lepshee.whop.models.webhooks.WebhookApiVersion
import com.lepshee.whop.oauth.OAuthCallback
import kotlinx.serialization.encodeToString
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
        assertJsonContains(CreateTransferRequest(1000, "usd", "biz_dest", "biz_origin", "transfer_123"), "idempotence_key")
        assertJsonContains(CreatePaymentRequest("biz_123", "mem_123", "pm_123", planId = "plan_123"), "payment_method_id")
        assertJsonContains(RefundPaymentRequest(partialAmount = 500), "partial_amount")
        assertJsonContains(CreateWebhookRequest("https://example.com", WebhookApiVersion.V5), "api_version")
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
