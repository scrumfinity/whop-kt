package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.files.CreateFileRequest
import dev.lepshee.whop.models.files.WhopFile

/** Operations for Whop file records and presigned upload URLs. */
class FilesResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun create(
        request: CreateFileRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopFile = http.post("files", request, options)

    suspend fun retrieve(
        fileId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopFile {
        val id = pathParameter(fileId, "File ID")
        return http.get("files/$id", options = options)
    }
}
