package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.courses.CourseStudent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for course enrollment analytics. */
class CourseStudentsResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun list(
        request: ListCourseStudentsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<CourseStudent> = http.get("course_students", request.toQueryParameters(), options)

    suspend fun retrieve(
        courseStudentId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): CourseStudent {
        val id = pathParameter(courseStudentId, "Course student ID")
        return http.get("course_students/$id", options = options)
    }

    fun listAutoPaging(
        request: ListCourseStudentsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<CourseStudent> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

data class ListCourseStudentsRequest(
    val courseId: String,
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val keyword: String? = null,
) {
    init {
        require(courseId.isNotBlank()) { "Course ID must not be blank." }
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
        require(keyword == null || keyword.isNotBlank()) { "Keyword must not be blank." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            add("course_id" to courseId)
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
            keyword?.let { add("keyword" to it) }
        }
}
