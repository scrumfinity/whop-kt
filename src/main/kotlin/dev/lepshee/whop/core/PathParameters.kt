package dev.lepshee.whop.core

/** Validates caller-provided values before they are interpolated into API paths. */
internal fun pathParameter(
    value: String,
    name: String,
): String {
    require(value.isNotBlank()) { "$name must not be blank." }
    require(value.none(Char::isISOControl)) { "$name must not contain control characters." }
    require('/' !in value && '?' !in value && '#' !in value) { "$name must be a single path segment." }
    require(!value.contains("%2f", ignoreCase = true) && !value.contains("%5c", ignoreCase = true)) {
        "$name must not contain encoded path separators."
    }
    require(value != "." && value != "..") { "$name must not be a relative path segment." }
    return value
}
