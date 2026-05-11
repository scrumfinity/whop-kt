package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.experiences.CreateExperienceRequest
import dev.lepshee.whop.models.experiences.DuplicateExperienceRequest
import dev.lepshee.whop.models.experiences.Experience
import dev.lepshee.whop.models.experiences.ExperienceListItem
import dev.lepshee.whop.models.experiences.ExperienceProductRequest
import dev.lepshee.whop.models.experiences.UpdateExperienceRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for product experiences. */
class ExperiencesResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    /** Lists experiences for a company using Whop cursor pagination. */
    suspend fun list(
        request: ListExperiencesRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<ExperienceListItem> = http.get("experiences", request.toQueryParameters(), options)

    /** Creates a new experience for a company. */
    suspend fun create(
        request: CreateExperienceRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Experience = http.post("experiences", request, options)

    /** Retrieves an experience by ID. */
    suspend fun retrieve(
        experienceId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Experience {
        val id = pathParameter(experienceId, "Experience ID")
        return http.get("experiences/$id", options = options)
    }

    /** Updates mutable experience fields. */
    suspend fun update(
        experienceId: String,
        request: UpdateExperienceRequest =
            UpdateExperienceRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Experience {
        val id = pathParameter(experienceId, "Experience ID")
        return http.patch("experiences/$id", request, options)
    }

    /** Deletes an experience by ID and returns Whop's boolean deletion result. */
    suspend fun delete(
        experienceId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Boolean {
        val id = pathParameter(experienceId, "Experience ID")
        return http.delete("experiences/$id", options = options)
    }

    /** Attaches an experience to a product. */
    suspend fun attach(
        experienceId: String,
        request: ExperienceProductRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Experience {
        val id = pathParameter(experienceId, "Experience ID")
        return http.post("experiences/$id/attach", request, options)
    }

    /** Detaches an experience from a product. */
    suspend fun detach(
        experienceId: String,
        request: ExperienceProductRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Experience {
        val id = pathParameter(experienceId, "Experience ID")
        return http.post("experiences/$id/detach", request, options)
    }

    /** Duplicates an experience. */
    suspend fun duplicate(
        experienceId: String,
        request: DuplicateExperienceRequest =
            DuplicateExperienceRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Experience {
        val id = pathParameter(experienceId, "Experience ID")
        return http.post("experiences/$id/duplicate", request, options)
    }

    /** Streams experiences across forward pages by following `page_info.end_cursor`. */
    fun listAutoPaging(
        request: ListExperiencesRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<ExperienceListItem> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

/** Query parameters for listing experiences. */
data class ListExperiencesRequest(
    val companyId: String,
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val productId: String? = null,
    val appId: String? = null,
    val createdBefore: String? = null,
    val createdAfter: String? = null,
) {
    init {
        require(companyId.isNotBlank()) { "Company ID must not be blank." }
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
        require(productId == null || productId.isNotBlank()) { "Product ID must not be blank." }
        require(appId == null || appId.isNotBlank()) { "App ID must not be blank." }
        require(createdBefore == null || createdBefore.isNotBlank()) { "Created-before value must not be blank." }
        require(createdAfter == null || createdAfter.isNotBlank()) { "Created-after value must not be blank." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            add("company_id" to companyId)
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
            productId?.let { add("product_id" to it) }
            appId?.let { add("app_id" to it) }
            createdBefore?.let { add("created_before" to it) }
            createdAfter?.let { add("created_after" to it) }
        }
}
