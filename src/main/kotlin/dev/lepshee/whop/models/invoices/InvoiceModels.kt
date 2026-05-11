package dev.lepshee.whop.models.invoices

import dev.lepshee.whop.models.common.Visibility
import dev.lepshee.whop.models.plans.CustomField
import dev.lepshee.whop.models.plans.PaymentMethodConfiguration
import dev.lepshee.whop.models.plans.PlanType
import dev.lepshee.whop.models.products.ReleaseMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

@Serializable
data class InvoiceListItem(
    val id: String,
    @SerialName("created_at") val createdAt: String,
    val status: InvoiceStatus,
    val number: String,
    @SerialName("due_date") val dueDate: String? = null,
    @SerialName("email_address") val emailAddress: String? = null,
    @SerialName("fetch_invoice_token") val fetchInvoiceToken: String,
    @SerialName("current_plan") val currentPlan: InvoiceCurrentPlan,
    val user: InvoiceUser? = null,
)

@Serializable
data class Invoice(
    val id: String,
    @SerialName("created_at") val createdAt: String,
    val status: InvoiceStatus,
    val number: String,
    @SerialName("due_date") val dueDate: String? = null,
    @SerialName("email_address") val emailAddress: String? = null,
    @SerialName("fetch_invoice_token") val fetchInvoiceToken: String,
    @SerialName("current_plan") val currentPlan: InvoiceCurrentPlan,
    val user: InvoiceUser? = null,
)

@Serializable
data class InvoiceCurrentPlan(
    val id: String,
    @SerialName("formatted_price") val formattedPrice: String,
    val currency: String,
)

@Serializable
data class InvoiceUser(
    val id: String,
    val name: String? = null,
    val username: String,
)

@Serializable
data class CreateInvoiceRequest(
    @SerialName("company_id") val companyId: String,
    val plan: InvoicePlanRequest,
    @SerialName("collection_method") val collectionMethod: InvoiceCollectionMethod,
    val product: InvoiceProductInput? = null,
    @SerialName("product_id") val productId: String? = null,
    @SerialName("customer_name") val customerName: JsonElement? = null,
    @SerialName("email_address") val emailAddress: JsonElement? = null,
    @SerialName("member_id") val memberId: JsonElement? = null,
    @SerialName("due_date") val dueDate: JsonElement? = null,
    @SerialName("automatically_finalizes_at") val automaticallyFinalizesAt: JsonElement? = null,
    @SerialName("billing_address") val billingAddress: JsonElement? = null,
    @SerialName("charge_buyer_fee") val chargeBuyerFee: JsonElement? = null,
    @SerialName("line_items") val lineItems: List<InvoiceLineItem>? = null,
    @SerialName("mailing_address_id") val mailingAddressId: JsonElement? = null,
    @SerialName("payment_method_id") val paymentMethodId: JsonElement? = null,
    @SerialName("payment_token_id") val paymentTokenId: JsonElement? = null,
    @SerialName("save_as_draft") val saveAsDraft: JsonElement? = null,
    @SerialName("subscription_billing_anchor_at") val subscriptionBillingAnchorAt: JsonElement? = null,
) {
    init {
        require(companyId.isNotBlank()) { "Company ID must not be blank." }
        require((product == null) != (productId == null)) { "Invoice requires exactly one of inline product or productId." }
        require(productId == null || productId.isNotBlank()) { "Product ID must not be blank." }
        requireOptionalText(customerName, "Customer name")
        requireOptionalText(emailAddress, "Email address")
        requireOptionalText(memberId, "Member ID")
        requireOptionalText(dueDate, "Due date")
        requireOptionalText(automaticallyFinalizesAt, "Automatically finalizes at")
        requireOptionalText(mailingAddressId, "Mailing address ID")
        requireOptionalText(paymentMethodId, "Payment method ID")
        requireOptionalText(paymentTokenId, "Payment token ID")
        requireOptionalText(subscriptionBillingAnchorAt, "Subscription billing anchor at")
        requireOptionalBoolean(chargeBuyerFee, "Charge buyer fee")
        requireOptionalBoolean(saveAsDraft, "Save as draft")
        require(lineItems == null || lineItems.isNotEmpty()) { "Invoice line items must not be empty when provided." }
        require(!(billingAddress != null && billingAddress != JsonNull && mailingAddressId != null && mailingAddressId != JsonNull)) {
            "Billing address cannot be used with mailingAddressId."
        }
        if (!saveAsDraft.isTrue()) {
            require(dueDate.isPresent()) { "Due date is required unless saveAsDraft is true." }
            require(memberId.isPresent() || customerName.isPresent() && emailAddress.isPresent()) {
                "Invoice requires memberId or both customerName and emailAddress unless saveAsDraft is true."
            }
            if (collectionMethod == InvoiceCollectionMethod.ChargeAutomatically) {
                require(paymentMethodId.isPresent()) { "Payment method ID is required for automatic invoice collection." }
                require(paymentTokenId.isPresent()) { "Payment token ID is required for automatic invoice collection." }
            }
        }
    }

    companion object {
        fun text(value: String): JsonPrimitive {
            require(value.isNotBlank()) { "Invoice text values must not be blank." }
            return JsonPrimitive(value)
        }

        fun bool(value: Boolean): JsonPrimitive = JsonPrimitive(value)

        fun billingAddress(value: InvoiceBillingAddress): JsonObject = value.toJsonObject()

        val clear: JsonNull = JsonNull
    }
}

@Serializable
data class UpdateInvoiceRequest(
    @SerialName("automatically_finalizes_at") val automaticallyFinalizesAt: JsonElement? = null,
    @SerialName("billing_address") val billingAddress: JsonElement? = null,
    @SerialName("charge_buyer_fee") val chargeBuyerFee: JsonElement? = null,
    @SerialName("collection_method") val collectionMethod: JsonElement? = null,
    @SerialName("customer_name") val customerName: JsonElement? = null,
    @SerialName("due_date") val dueDate: JsonElement? = null,
    @SerialName("email_address") val emailAddress: JsonElement? = null,
    @SerialName("line_items") val lineItems: List<InvoiceLineItem>? = null,
    @SerialName("mailing_address_id") val mailingAddressId: JsonElement? = null,
    @SerialName("member_id") val memberId: JsonElement? = null,
    @SerialName("payment_method_id") val paymentMethodId: JsonElement? = null,
    val plan: JsonElement? = null,
    @SerialName("product_id") val productId: JsonElement? = null,
    @SerialName("subscription_billing_anchor_at") val subscriptionBillingAnchorAt: JsonElement? = null,
) {
    init {
        requireOptionalText(automaticallyFinalizesAt, "Automatically finalizes at")
        requireOptionalText(customerName, "Customer name")
        requireOptionalText(dueDate, "Due date")
        requireOptionalText(emailAddress, "Email address")
        requireOptionalText(mailingAddressId, "Mailing address ID")
        requireOptionalText(memberId, "Member ID")
        requireOptionalText(paymentMethodId, "Payment method ID")
        requireOptionalText(productId, "Product ID")
        requireOptionalText(subscriptionBillingAnchorAt, "Subscription billing anchor at")
        requireOptionalBoolean(chargeBuyerFee, "Charge buyer fee")
        require(plan == null || plan == JsonNull || plan is JsonObject) { "Plan must be encoded from InvoicePlanRequest." }
        require(
            collectionMethod == null || collectionMethod == JsonNull || collectionMethod is JsonPrimitive && collectionMethod.isString,
        ) { "Collection method must be an InvoiceCollectionMethod value." }
    }

    companion object {
        fun text(value: String): JsonPrimitive = CreateInvoiceRequest.text(value)

        fun bool(value: Boolean): JsonPrimitive = JsonPrimitive(value)

        fun collectionMethod(value: InvoiceCollectionMethod): JsonPrimitive = JsonPrimitive(value.serialValue)

        fun billingAddress(value: InvoiceBillingAddress): JsonObject = value.toJsonObject()

        val clear: JsonNull = JsonNull
    }
}

@Serializable
data class InvoiceProductInput(
    val title: String,
    @SerialName("product_tax_code_id") val productTaxCodeId: JsonElement? = null,
) {
    init {
        require(title.isNotBlank()) { "Product title must not be blank." }
        requireOptionalText(productTaxCodeId, "Product tax code ID")
    }
}

@Serializable
data class InvoicePlanRequest(
    @SerialName("billing_period") val billingPeriod: Int? = null,
    @SerialName("custom_fields") val customFields: List<CustomField>? = null,
    val description: JsonElement? = null,
    @SerialName("expiration_days") val expirationDays: Int? = null,
    @SerialName("initial_price") val initialPrice: Double? = null,
    @SerialName("internal_notes") val internalNotes: JsonElement? = null,
    @SerialName("legacy_payment_method_controls") val legacyPaymentMethodControls: Boolean? = null,
    @SerialName("payment_method_configuration") val paymentMethodConfiguration: PaymentMethodConfiguration? = null,
    @SerialName("plan_type") val planType: PlanType? = null,
    @SerialName("release_method") val releaseMethod: ReleaseMethod? = null,
    @SerialName("renewal_price") val renewalPrice: Double? = null,
    val stock: Int? = null,
    @SerialName("trial_period_days") val trialPeriodDays: Int? = null,
    @SerialName("unlimited_stock") val unlimitedStock: Boolean? = null,
    val visibility: Visibility? = null,
) {
    init {
        require(billingPeriod == null || billingPeriod > 0) { "Billing period must be positive." }
        require(expirationDays == null || expirationDays > 0) { "Expiration days must be positive." }
        require(initialPrice == null || initialPrice >= 0.0) { "Initial price must not be negative." }
        require(renewalPrice == null || renewalPrice >= 0.0) { "Renewal price must not be negative." }
        require(stock == null || stock >= 0) { "Stock must not be negative." }
        require(trialPeriodDays == null || trialPeriodDays >= 0) { "Trial period days must not be negative." }
        requireOptionalText(description, "Description")
        requireOptionalText(internalNotes, "Internal notes")
    }
}

@Serializable
data class InvoiceBillingAddress(
    val city: JsonElement? = null,
    val country: JsonElement? = null,
    val line1: JsonElement? = null,
    val line2: JsonElement? = null,
    val name: JsonElement? = null,
    val phone: JsonElement? = null,
    @SerialName("postal_code") val postalCode: JsonElement? = null,
    val state: JsonElement? = null,
    @SerialName("tax_id_type") val taxIdType: JsonElement? = null,
    @SerialName("tax_id_value") val taxIdValue: JsonElement? = null,
) {
    init {
        listOf(city, country, line1, line2, name, phone, postalCode, state, taxIdType, taxIdValue).forEach { value ->
            require(value == null || value == JsonNull || value is JsonPrimitive && value.isString && value.content.isNotBlank()) {
                "Billing address fields must be non-blank strings or explicit null."
            }
        }
    }

    fun toJsonObject(): JsonObject =
        buildJsonObject {
            city?.let { put("city", it) }
            country?.let { put("country", it) }
            line1?.let { put("line1", it) }
            line2?.let { put("line2", it) }
            name?.let { put("name", it) }
            phone?.let { put("phone", it) }
            postalCode?.let { put("postal_code", it) }
            state?.let { put("state", it) }
            taxIdType?.let { put("tax_id_type", it) }
            taxIdValue?.let { put("tax_id_value", it) }
        }
}

@Serializable
data class InvoiceLineItem(
    val label: String,
    val quantity: Double? = null,
    @SerialName("unit_price") val unitPrice: Double,
) {
    init {
        require(label.isNotBlank()) { "Invoice line item label must not be blank." }
        require(quantity == null || quantity > 0.0) { "Invoice line item quantity must be positive." }
        require(unitPrice >= 0.0) { "Invoice line item unit price must not be negative." }
    }
}

@Serializable
enum class InvoiceCollectionMethod {
    @SerialName("send_invoice")
    SendInvoice,

    @SerialName("charge_automatically")
    ChargeAutomatically,
}

@Serializable
enum class InvoiceStatus {
    @SerialName("draft")
    Draft,

    @SerialName("open")
    Open,

    @SerialName("paid")
    Paid,

    @SerialName("past_due")
    PastDue,

    @SerialName("uncollectible")
    Uncollectible,

    @SerialName("void")
    Void,
}

@Serializable
enum class InvoicesSortableColumn {
    @SerialName("id")
    Id,

    @SerialName("created_at")
    CreatedAt,

    @SerialName("due_date")
    DueDate,
}

internal val InvoiceCollectionMethod.serialValue: String get() =
    when (this) {
        InvoiceCollectionMethod.SendInvoice -> "send_invoice"
        InvoiceCollectionMethod.ChargeAutomatically -> "charge_automatically"
    }

internal val InvoiceStatus.serialValue: String get() =
    when (this) {
        InvoiceStatus.Draft -> "draft"
        InvoiceStatus.Open -> "open"
        InvoiceStatus.Paid -> "paid"
        InvoiceStatus.PastDue -> "past_due"
        InvoiceStatus.Uncollectible -> "uncollectible"
        InvoiceStatus.Void -> "void"
    }

internal val InvoicesSortableColumn.serialValue: String get() =
    when (this) {
        InvoicesSortableColumn.Id -> "id"
        InvoicesSortableColumn.CreatedAt -> "created_at"
        InvoicesSortableColumn.DueDate -> "due_date"
    }

private fun requireOptionalText(
    value: JsonElement?,
    fieldName: String,
) {
    if (value == null || value == JsonNull) return
    require(value is JsonPrimitive && value.isString && value.content.isNotBlank()) { "$fieldName must not be blank." }
}

private fun requireOptionalBoolean(
    value: JsonElement?,
    fieldName: String,
) {
    if (value == null || value == JsonNull) return
    require(value is JsonPrimitive && !value.isString && value.booleanOrNull != null) { "$fieldName must be a boolean." }
}

private fun JsonElement?.isPresent(): Boolean = this != null && this != JsonNull

private fun JsonElement?.isTrue(): Boolean = this is JsonPrimitive && !isString && booleanOrNull == true
