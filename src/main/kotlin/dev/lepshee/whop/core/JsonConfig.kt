package dev.lepshee.whop.core

import kotlinx.serialization.json.Json

/** Shared JSON configuration used for Whop API request and response payloads. */
object JsonConfig {
    /**
     * Kotlinx JSON instance configured for additive API changes.
     *
     * Unknown response fields are ignored, defaults are encoded for request consistency, and nulls
     * are omitted unless a model explicitly needs nullable serialization in a future endpoint.
     */
    val whopJson: Json =
        Json {
            ignoreUnknownKeys = true
            encodeDefaults = true
            explicitNulls = false
        }
}
