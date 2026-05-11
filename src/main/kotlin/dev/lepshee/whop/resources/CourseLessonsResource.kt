package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.courses.AssessmentResult
import dev.lepshee.whop.models.courses.CourseLesson
import dev.lepshee.whop.models.courses.CreateCourseLessonRequest
import dev.lepshee.whop.models.courses.SubmitAssessmentRequest
import dev.lepshee.whop.models.courses.UpdateCourseLessonRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for course lessons. */
class CourseLessonsResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun create(
        request: CreateCourseLessonRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): CourseLesson = http.post("course_lessons", request, options)

    suspend fun retrieve(
        courseLessonId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): CourseLesson {
        val id = pathParameter(courseLessonId, "Course lesson ID")
        return http.get("course_lessons/$id", options = options)
    }

    suspend fun update(
        courseLessonId: String,
        request: UpdateCourseLessonRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): CourseLesson {
        val id = pathParameter(courseLessonId, "Course lesson ID")
        return http.patch("course_lessons/$id", request, options)
    }

    suspend fun delete(
        courseLessonId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Boolean {
        val id = pathParameter(courseLessonId, "Course lesson ID")
        return http.delete("course_lessons/$id", options = options)
    }

    suspend fun list(
        request: ListCourseLessonsRequest = ListCourseLessonsRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<CourseLesson> = http.get("course_lessons", request.toQueryParameters(), options)

    suspend fun markAsCompleted(
        courseLessonId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Boolean {
        val id = pathParameter(courseLessonId, "Course lesson ID")
        return http.post("course_lessons/$id/mark_as_completed", options)
    }

    suspend fun start(
        courseLessonId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Boolean {
        val id = pathParameter(courseLessonId, "Course lesson ID")
        return http.post("course_lessons/$id/start", options)
    }

    suspend fun submitAssessment(
        courseLessonId: String,
        request: SubmitAssessmentRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): AssessmentResult {
        val id = pathParameter(courseLessonId, "Course lesson ID")
        return http.post("course_lessons/$id/submit_assessment", request, options)
    }

    fun listAutoPaging(
        request: ListCourseLessonsRequest = ListCourseLessonsRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<CourseLesson> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

data class ListCourseLessonsRequest(
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val courseId: String? = null,
    val chapterId: String? = null,
) {
    init {
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
        require(courseId == null || courseId.isNotBlank()) { "Course ID must not be blank." }
        require(chapterId == null || chapterId.isNotBlank()) { "Course chapter ID must not be blank." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
            courseId?.let { add("course_id" to it) }
            chapterId?.let { add("chapter_id" to it) }
        }
}
