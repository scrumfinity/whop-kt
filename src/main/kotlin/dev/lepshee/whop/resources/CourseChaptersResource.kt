package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.courses.CourseChapter
import dev.lepshee.whop.models.courses.CreateCourseChapterRequest
import dev.lepshee.whop.models.courses.UpdateCourseChapterRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for course chapters. */
class CourseChaptersResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun create(
        request: CreateCourseChapterRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): CourseChapter = http.post("course_chapters", request, options)

    suspend fun retrieve(
        courseChapterId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): CourseChapter {
        val id = pathParameter(courseChapterId, "Course chapter ID")
        return http.get("course_chapters/$id", options = options)
    }

    suspend fun update(
        courseChapterId: String,
        request: UpdateCourseChapterRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): CourseChapter {
        val id = pathParameter(courseChapterId, "Course chapter ID")
        return http.patch("course_chapters/$id", request, options)
    }

    suspend fun delete(
        courseChapterId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Boolean {
        val id = pathParameter(courseChapterId, "Course chapter ID")
        return http.delete("course_chapters/$id", options = options)
    }

    suspend fun list(
        request: ListCourseChaptersRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<CourseChapter> = http.get("course_chapters", request.toQueryParameters(), options)

    fun listAutoPaging(
        request: ListCourseChaptersRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<CourseChapter> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

data class ListCourseChaptersRequest(
    val courseId: String,
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
) {
    init {
        require(courseId.isNotBlank()) { "Course ID must not be blank." }
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            add("course_id" to courseId)
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
        }
}
