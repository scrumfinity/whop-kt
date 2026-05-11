package dev.lepshee.whop.models.companytokentransactions

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompanyTokenTransactionListItem(
    val id: String,
    @SerialName("transaction_type") val transactionType: CompanyTokenTransactionType,
    val amount: Double,
    val description: String? = null,
    @SerialName("created_at") val createdAt: String,
    @SerialName("linked_transaction_id") val linkedTransactionId: String? = null,
    @SerialName("idempotency_key") val idempotencyKey: String? = null,
    val user: CompanyTokenTransactionUser,
    val member: CompanyTokenTransactionMember,
    val company: CompanyTokenTransactionCompany,
)

@Serializable
data class CompanyTokenTransaction(
    val id: String,
    @SerialName("transaction_type") val transactionType: CompanyTokenTransactionType,
    val amount: Double,
    val description: String? = null,
    @SerialName("created_at") val createdAt: String,
    @SerialName("linked_transaction_id") val linkedTransactionId: String? = null,
    @SerialName("idempotency_key") val idempotencyKey: String? = null,
    val user: CompanyTokenTransactionUser,
    val member: CompanyTokenTransactionMember,
    val company: CompanyTokenTransactionCompany,
)

@Serializable
data class CompanyTokenTransactionUser(
    val id: String,
    val name: String? = null,
    val username: String,
)

@Serializable
data class CompanyTokenTransactionMember(
    val id: String,
)

@Serializable
data class CompanyTokenTransactionCompany(
    val id: String,
    val title: String,
    val route: String,
)

@Serializable
data class CreateCompanyTokenTransactionRequest(
    val amount: Double,
    @SerialName("company_id") val companyId: String,
    @SerialName("transaction_type") val transactionType: CompanyTokenTransactionType,
    @SerialName("user_id") val userId: String,
    val description: String? = null,
    @SerialName("destination_user_id") val destinationUserId: String? = null,
    @SerialName("idempotency_key") val idempotencyKey: String? = null,
) {
    init {
        require(amount.isFinite() && amount > 0.0) { "Company token transaction amount must be a finite positive number." }
        require(companyId.isNotBlank()) { "Company ID must not be blank." }
        require(userId.isNotBlank()) { "User ID must not be blank." }
        require(description == null || description.isNotBlank()) { "Description must not be blank." }
        require(destinationUserId == null || destinationUserId.isNotBlank()) { "Destination user ID must not be blank." }
        require(idempotencyKey == null || idempotencyKey.isNotBlank()) { "Idempotency key must not be blank." }
        require(transactionType == CompanyTokenTransactionType.Transfer || destinationUserId == null) {
            "Destination user ID is only valid for transfer token transactions."
        }
        require(transactionType != CompanyTokenTransactionType.Transfer || destinationUserId != null) {
            "Destination user ID is required for transfer token transactions."
        }
    }

    companion object {
        fun add(
            amount: Double,
            companyId: String,
            userId: String,
            description: String? = null,
            idempotencyKey: String? = null,
        ): CreateCompanyTokenTransactionRequest =
            CreateCompanyTokenTransactionRequest(
                amount = amount,
                companyId = companyId,
                transactionType = CompanyTokenTransactionType.Add,
                userId = userId,
                description = description,
                idempotencyKey = idempotencyKey,
            )

        fun subtract(
            amount: Double,
            companyId: String,
            userId: String,
            description: String? = null,
            idempotencyKey: String? = null,
        ): CreateCompanyTokenTransactionRequest =
            CreateCompanyTokenTransactionRequest(
                amount = amount,
                companyId = companyId,
                transactionType = CompanyTokenTransactionType.Subtract,
                userId = userId,
                description = description,
                idempotencyKey = idempotencyKey,
            )

        fun transfer(
            amount: Double,
            companyId: String,
            userId: String,
            destinationUserId: String,
            description: String? = null,
            idempotencyKey: String? = null,
        ): CreateCompanyTokenTransactionRequest =
            CreateCompanyTokenTransactionRequest(
                amount = amount,
                companyId = companyId,
                transactionType = CompanyTokenTransactionType.Transfer,
                userId = userId,
                destinationUserId = destinationUserId,
                description = description,
                idempotencyKey = idempotencyKey,
            )
    }
}

@Serializable
enum class CompanyTokenTransactionType {
    @SerialName("add")
    Add,

    @SerialName("subtract")
    Subtract,

    @SerialName("transfer")
    Transfer,
}
