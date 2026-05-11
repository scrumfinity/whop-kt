package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.courses.Course
import dev.lepshee.whop.models.courses.CreateCourseRequest
import dev.lepshee.whop.models.courses.UpdateCourseRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for courses. */
class CoursesResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun create(
        request: CreateCourseRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Course = http.post("courses", request, options)

    suspend fun retrieve(
        courseId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Course {
        val id = pathParameter(courseId, "Course ID")
        return http.get("courses/$id", options = options)
    }

    suspend fun update(
        courseId: String,
        request: UpdateCourseRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Course {
        val id = pathParameter(courseId, "Course ID")
        return http.patch("courses/$id", request, options)
    }

    suspend fun delete(
        courseId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Boolean {
        val id = pathParameter(courseId, "Course ID")
        return http.delete("courses/$id", options = options)
    }

    suspend fun list(
        request: ListCoursesRequest = ListCoursesRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<Course> = http.get("courses", request.toQueryParameters(), options)

    fun listAutoPaging(
        request: ListCoursesRequest = ListCoursesRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<Course> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

data class ListCoursesRequest(
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val experienceId: String? = null,
    val companyId: String? = null,
) {
    init {
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
        require(experienceId == null || experienceId.isNotBlank()) { "Experience ID must not be blank." }
        require(companyId == null || companyId.isNotBlank()) { "Company ID must not be blank." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
            experienceId?.let { add("experience_id" to it) }
            companyId?.let { add("company_id" to it) }
        }
}
