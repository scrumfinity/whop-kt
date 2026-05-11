package dev.lepshee.whop.models.withdrawals

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Request body for `POST /withdrawals`. */
@Serializable
data class CreateWithdrawalRequest(
    /** Amount to withdraw in the specified currency. */
    val amount: Double,
    /** Company to withdraw from. */
    @SerialName("company_id") val companyId: String,
    /** Currency being withdrawn, such as `usd`. */
    val currency: String,
    /** Optional payout method to use for the withdrawal. */
    @SerialName("payout_method_id") val payoutMethodId: String? = null,
    /** Whether the platform covers payout fees. */
    @SerialName("platform_covers_fees") val platformCoversFees: Boolean? = null,
    /** Custom alphanumeric statement descriptor for the withdrawal. */
    @SerialName("statement_descriptor") val statementDescriptor: String? = null,
) {
    init {
        require(amount.isFinite() && amount > 0.0) { "Withdrawal amount must be positive." }
        require(companyId.isNotBlank()) { "Company ID must not be blank." }
        require(currency.isNotBlank()) { "Currency must not be blank." }
        require(payoutMethodId == null || payoutMethodId.isNotBlank()) { "Payout method ID must not be blank." }
        require(statementDescriptor == null || statementDescriptor.isValidWithdrawalStatementDescriptor()) {
            "Statement descriptor must be 5-22 alphanumeric characters."
        }
    }
}

private fun String.isValidWithdrawalStatementDescriptor(): Boolean = length in 5..22 && all(Char::isLetterOrDigit)

/** Withdrawal item returned by list endpoints. */
@Serializable
data class WithdrawalListItem(
    /** Unique withdrawal ID. */
    val id: String,
    /** Current lifecycle status. */
    val status: WithdrawalStatus,
    /** Withdrawal amount in the specified currency. */
    val amount: Double,
    /** Withdrawal currency. */
    val currency: String,
    /** Fee charged for processing this withdrawal. */
    @SerialName("fee_amount") val feeAmount: Double,
    /** How the fee was applied, or null when no fee was charged. */
    @SerialName("fee_type") val feeType: WithdrawalFeeType? = null,
    /** Processing speed selected for the withdrawal. */
    val speed: WithdrawalSpeed,
    /** Creation timestamp. */
    @SerialName("created_at") val createdAt: String,
    /** Additional markup fee charged for the withdrawal. */
    @SerialName("markup_fee") val markupFee: Double,
)

/** Withdrawal returned by create and retrieve endpoints. */
@Serializable
data class Withdrawal(
    /** Unique withdrawal ID. */
    val id: String,
    /** Current lifecycle status. */
    val status: WithdrawalStatus,
    /** Withdrawal amount in the specified currency. */
    val amount: Double,
    /** Withdrawal currency. */
    val currency: String,
    /** Fee charged for processing this withdrawal. */
    @SerialName("fee_amount") val feeAmount: Double,
    /** How the fee was applied, or null when no fee was charged. */
    @SerialName("fee_type") val feeType: WithdrawalFeeType? = null,
    /** Processing speed selected for the withdrawal. */
    val speed: WithdrawalSpeed,
    /** Creation timestamp. */
    @SerialName("created_at") val createdAt: String,
    /** Additional markup fee charged for the withdrawal. */
    @SerialName("markup_fee") val markupFee: Double,
    /** Ledger account from which the funds are sourced. */
    @SerialName("ledger_account") val ledgerAccount: WithdrawalLedgerAccount,
    /** Saved payout destination used for this withdrawal, when present. */
    @SerialName("payout_token") val payoutToken: WithdrawalPayoutToken? = null,
    /** Machine-readable payout error code, when a payout failed. */
    @SerialName("error_code") val errorCode: String? = null,
    /** Human-readable payout error message, when a payout failed. */
    @SerialName("error_message") val errorMessage: String? = null,
    /** Estimated destination availability timestamp. */
    @SerialName("estimated_availability") val estimatedAvailability: String? = null,
    /** ACH trace number when available. */
    @SerialName("trace_code") val traceCode: String? = null,
)

/** Ledger account reference nested in withdrawal responses. */
@Serializable
data class WithdrawalLedgerAccount(
    /** Unique ledger account ID. */
    val id: String,
    /** Company associated with the ledger account, when returned. */
    @SerialName("company_id") val companyId: String? = null,
)

/** Payout destination nested in withdrawal responses. */
@Serializable
data class WithdrawalPayoutToken(
    /** Unique payout token ID. */
    val id: String,
    /** Legal account-holder name, when provided. */
    @SerialName("payer_name") val payerName: String? = null,
    /** User-defined destination label, when set. */
    val nickname: String? = null,
    /** Destination payout currency code. */
    @SerialName("destination_currency_code") val destinationCurrencyCode: String,
    /** Payout token creation timestamp. */
    @SerialName("created_at") val createdAt: String,
)

/** Withdrawal lifecycle statuses. */
@Serializable
enum class WithdrawalStatus {
    @SerialName("requested")
    Requested,

    @SerialName("awaiting_payment")
    AwaitingPayment,

    @SerialName("in_transit")
    InTransit,

    @SerialName("completed")
    Completed,

    @SerialName("failed")
    Failed,

    @SerialName("canceled")
    Canceled,

    @SerialName("denied")
    Denied,
}

/** Withdrawal fee application modes. */
@Serializable
enum class WithdrawalFeeType {
    @SerialName("exclusive")
    Exclusive,

    @SerialName("inclusive")
    Inclusive,
}

/** Withdrawal processing speeds. */
@Serializable
enum class WithdrawalSpeed {
    @SerialName("standard")
    Standard,

    @SerialName("instant")
    Instant,
}
