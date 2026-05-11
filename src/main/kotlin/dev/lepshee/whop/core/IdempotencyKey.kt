package dev.lepshee.whop.core

/** Validated wrapper for idempotency keys used by mutating operations. */
@JvmInline
value class IdempotencyKey(
    val value: String,
) {
    init {
        require(value.isNotBlank()) { "Idempotency key must not be blank." }
    }
}
