package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.courses.CourseLessonInteraction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for course lesson interaction analytics. */
class CourseLessonInteractionsResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun list(
        request: ListCourseLessonInteractionsRequest = ListCourseLessonInteractionsRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<CourseLessonInteraction> = http.get("course_lesson_interactions", request.toQueryParameters(), options)

    suspend fun retrieve(
        courseLessonInteractionId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): CourseLessonInteraction {
        val id = pathParameter(courseLessonInteractionId, "Course lesson interaction ID")
        return http.get("course_lesson_interactions/$id", options = options)
    }

    fun listAutoPaging(
        request: ListCourseLessonInteractionsRequest = ListCourseLessonInteractionsRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<CourseLessonInteraction> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

data class ListCourseLessonInteractionsRequest(
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val userId: String? = null,
    val lessonId: String? = null,
    val courseId: String? = null,
    val completed: Boolean? = null,
) {
    init {
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
        require(userId == null || userId.isNotBlank()) { "User ID must not be blank." }
        require(lessonId == null || lessonId.isNotBlank()) { "Course lesson ID must not be blank." }
        require(courseId == null || courseId.isNotBlank()) { "Course ID must not be blank." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
            userId?.let { add("user_id" to it) }
            lessonId?.let { add("lesson_id" to it) }
            courseId?.let { add("course_id" to it) }
            completed?.let { add("completed" to it.toString()) }
        }
}
