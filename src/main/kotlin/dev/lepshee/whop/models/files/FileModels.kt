package dev.lepshee.whop.models.files

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class WhopFile(
    val id: String,
    @SerialName("upload_status") val uploadStatus: UploadStatus,
    val filename: String? = null,
    @SerialName("content_type") val contentType: String? = null,
    val size: String? = null,
    val url: String? = null,
    val visibility: FileVisibility,
    @SerialName("upload_url") val uploadUrl: String? = null,
    @SerialName("upload_headers") val uploadHeaders: JsonObject? = null,
)

@Serializable
data class CreateFileRequest(
    val filename: String,
    val visibility: FileVisibility? = null,
) {
    init {
        require(filename.isNotBlank()) { "Filename must not be blank." }
    }
}

@Serializable
enum class FileVisibility {
    @SerialName("public")
    Public,

    @SerialName("private")
    Private,
}

@Serializable
enum class UploadStatus {
    @SerialName("pending")
    Pending,

    @SerialName("processing")
    Processing,

    @SerialName("ready")
    Ready,

    @SerialName("failed")
    Failed,
}
