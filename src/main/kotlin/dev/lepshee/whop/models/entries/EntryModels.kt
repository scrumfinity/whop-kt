package dev.lepshee.whop.models.entries

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class Entry(
    val id: String,
    val plan: JsonObject? = null,
    val product: JsonObject? = null,
    val status: EntryStatus,
    val user: JsonObject,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("custom_field_responses") val customFieldResponses: List<JsonObject>? = null,
)

@Serializable
data class AsyncJob(
    @SerialName("job_id") val jobId: String,
)

@Serializable
enum class EntryStatus {
    @SerialName("drafted")
    Drafted,

    @SerialName("pending")
    Pending,

    @SerialName("approved")
    Approved,

    @SerialName("denied")
    Denied,

    @SerialName("any")
    Any,
}

enum class EntriesOrder {
    Id,
    CreatedAt,
}
