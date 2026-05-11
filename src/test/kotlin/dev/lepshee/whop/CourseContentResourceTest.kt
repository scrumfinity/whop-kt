package dev.lepshee.whop

import dev.lepshee.whop.models.courses.AssessmentAnswerRequest
import dev.lepshee.whop.models.courses.CourseVisibility
import dev.lepshee.whop.models.courses.CreateCourseChapterRequest
import dev.lepshee.whop.models.courses.CreateCourseLessonRequest
import dev.lepshee.whop.models.courses.CreateCourseRequest
import dev.lepshee.whop.models.courses.EmbedType
import dev.lepshee.whop.models.courses.FileInputWithId
import dev.lepshee.whop.models.courses.LessonType
import dev.lepshee.whop.models.courses.SubmitAssessmentRequest
import dev.lepshee.whop.models.courses.UpdateCourseChapterRequest
import dev.lepshee.whop.models.courses.UpdateCourseLessonRequest
import dev.lepshee.whop.models.courses.UpdateCourseRequest
import dev.lepshee.whop.resources.ListCourseChaptersRequest
import dev.lepshee.whop.resources.ListCourseLessonInteractionsRequest
import dev.lepshee.whop.resources.ListCourseLessonsRequest
import dev.lepshee.whop.resources.ListCoursesRequest
import dev.lepshee.whop.transport.KtorWhopHttpTransport
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.HttpRequestData
import io.ktor.http.HttpHeaders
import io.ktor.http.content.OutgoingContent
import io.ktor.http.content.TextContent
import io.ktor.http.headersOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CourseContentResourceTest {
    @Test
    fun `course endpoints send documented paths query params and bodies`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val page = whop.courses.list(ListCoursesRequest(companyId = "biz_123", first = 10))
            val created =
                whop.courses.create(
                    CreateCourseRequest(
                        experienceId = "exp_123",
                        title = "Kotlin Course",
                        thumbnail = FileInputWithId("file_123"),
                        visibility = CourseVisibility.Hidden,
                    ),
                )
            whop.courses.retrieve("cors_123")
            whop.courses.update("cors_123", UpdateCourseRequest(title = "Updated"))
            val deleted = whop.courses.delete("cors_123")

            assertEquals("cors_123", page.data.single().id)
            assertEquals("cors_123", created.id)
            assertTrue(deleted)
            assertEquals("/api/v1/courses", requests[0].url.encodedPath)
            assertEquals("biz_123", requests[0].url.parameters["company_id"])
            assertEquals("10", requests[0].url.parameters["first"])
            assertEquals("POST", requests[1].method.value)
            assertEquals("/api/v1/courses", requests[1].url.encodedPath)
            assertTrue(requests[1].bodyText().contains("experience_id"))
            assertTrue(requests[1].bodyText().contains("hidden"))
            assertEquals("/api/v1/courses/cors_123", requests[2].url.encodedPath)
            assertEquals("PATCH", requests[3].method.value)
            assertEquals("/api/v1/courses/cors_123", requests[3].url.encodedPath)
            assertTrue(requests[3].bodyText().contains("Updated"))
            assertEquals("DELETE", requests[4].method.value)
            assertEquals("/api/v1/courses/cors_123", requests[4].url.encodedPath)
        }

    @Test
    fun `course chapter endpoints send documented paths query params and bodies`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val page = whop.courseChapters.list(ListCourseChaptersRequest(courseId = "cors_123", first = 5))
            val created = whop.courseChapters.create(CreateCourseChapterRequest(courseId = "cors_123", title = "Intro"))
            whop.courseChapters.retrieve("chap_123")
            whop.courseChapters.update("chap_123", UpdateCourseChapterRequest(title = "Basics"))
            val deleted = whop.courseChapters.delete("chap_123")

            assertEquals("chap_123", page.data.single().id)
            assertEquals("chap_123", created.id)
            assertTrue(deleted)
            assertEquals("/api/v1/course_chapters", requests[0].url.encodedPath)
            assertEquals("cors_123", requests[0].url.parameters["course_id"])
            assertEquals("5", requests[0].url.parameters["first"])
            assertEquals("POST", requests[1].method.value)
            assertTrue(requests[1].bodyText().contains("course_id"))
            assertEquals("/api/v1/course_chapters/chap_123", requests[2].url.encodedPath)
            assertEquals("PATCH", requests[3].method.value)
            assertTrue(requests[3].bodyText().contains("Basics"))
            assertEquals("DELETE", requests[4].method.value)
        }

    @Test
    fun `course lesson endpoints send documented paths bodies and bodyless actions`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val page = whop.courseLessons.list(ListCourseLessonsRequest(chapterId = "chap_123", first = 3))
            val created =
                whop.courseLessons.create(
                    CreateCourseLessonRequest(
                        chapterId = "chap_123",
                        lessonType = LessonType.Video,
                        embedType = EmbedType.Youtube,
                        embedId = "abc123",
                    ),
                )
            whop.courseLessons.retrieve("lesn_123")
            whop.courseLessons.update("lesn_123", UpdateCourseLessonRequest(title = "Lesson 2"))
            val completed = whop.courseLessons.markAsCompleted("lesn_123")
            val started = whop.courseLessons.start("lesn_123")
            val result =
                whop.courseLessons.submitAssessment(
                    "lesn_123",
                    SubmitAssessmentRequest(
                        answers = listOf(AssessmentAnswerRequest(questionId = "crsaq_123", selectedOptionIds = listOf("crsaqo_123"))),
                    ),
                )
            val deleted = whop.courseLessons.delete("lesn_123")

            assertEquals("lesn_123", page.data.single().id)
            assertEquals("lesn_123", created.id)
            assertTrue(completed)
            assertTrue(started)
            assertEquals("crsar_123", result.id)
            assertTrue(deleted)
            assertEquals("/api/v1/course_lessons", requests[0].url.encodedPath)
            assertEquals("chap_123", requests[0].url.parameters["chapter_id"])
            assertEquals("POST", requests[1].method.value)
            assertTrue(requests[1].bodyText().contains("lesson_type"))
            assertEquals("/api/v1/course_lessons/lesn_123", requests[2].url.encodedPath)
            assertEquals("PATCH", requests[3].method.value)
            assertEquals("/api/v1/course_lessons/lesn_123/mark_as_completed", requests[4].url.encodedPath)
            assertNoTextBody(requests[4])
            assertEquals("/api/v1/course_lessons/lesn_123/start", requests[5].url.encodedPath)
            assertNoTextBody(requests[5])
            assertEquals("/api/v1/course_lessons/lesn_123/submit_assessment", requests[6].url.encodedPath)
            assertTrue(requests[6].bodyText().contains("selected_option_ids"))
            assertEquals("DELETE", requests[7].method.value)
        }

    @Test
    fun `course lesson interaction endpoints send documented paths and filters`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val page =
                whop.courseLessonInteractions.list(
                    ListCourseLessonInteractionsRequest(
                        userId = "user_123",
                        lessonId = "lesn_123",
                        courseId = "cors_123",
                        completed = true,
                        first = 20,
                    ),
                )
            whop.courseLessonInteractions.retrieve("crsli_123")

            assertEquals("crsli_123", page.data.single().id)
            assertEquals("/api/v1/course_lesson_interactions", requests[0].url.encodedPath)
            assertEquals("user_123", requests[0].url.parameters["user_id"])
            assertEquals("lesn_123", requests[0].url.parameters["lesson_id"])
            assertEquals("cors_123", requests[0].url.parameters["course_id"])
            assertEquals("true", requests[0].url.parameters["completed"])
            assertEquals("20", requests[0].url.parameters["first"])
            assertEquals("/api/v1/course_lesson_interactions/crsli_123", requests[1].url.encodedPath)
        }

    @Test
    fun `course auto paging follows forward cursors`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val courses = whop.courses.listAutoPaging(ListCoursesRequest(companyId = "biz_123", first = 1)).toList()

            assertEquals(listOf("cors_123", "cors_456"), courses.map { it.id })
            assertEquals("/api/v1/courses", requests[0].url.encodedPath)
            assertEquals("biz_123", requests[0].url.parameters["company_id"])
            assertEquals("1", requests[0].url.parameters["first"])
            assertEquals("cursor_1", requests[1].url.parameters["after"])
        }

    @Test
    fun `course content path parameters reject path injection characters`() =
        runTest {
            val whop = clientWithEngine(recordingEngine(mutableListOf()))

            assertFailsWith<IllegalArgumentException> { whop.courses.retrieve("cors_123/other") }
            assertFailsWith<IllegalArgumentException> { whop.courseChapters.retrieve("chap_123%2Fother") }
            assertFailsWith<IllegalArgumentException> { whop.courseLessons.start("lesn_123?admin=true") }
            assertFailsWith<IllegalArgumentException> { whop.courseLessonInteractions.retrieve("../crsli_123") }
        }

    @Test
    fun `course content request validation rejects invalid inputs`() {
        assertFailsWith<IllegalArgumentException> { CreateCourseRequest(experienceId = " ", title = "Course") }
        assertFailsWith<IllegalArgumentException> { ListCourseChaptersRequest(courseId = "cors_123", first = 0) }
        assertFailsWith<IllegalArgumentException> {
            CreateCourseLessonRequest(
                chapterId = "chap_123",
                lessonType = LessonType.Text,
                content = "",
            )
        }
        assertFailsWith<IllegalArgumentException> { SubmitAssessmentRequest(emptyList()) }
    }

    private fun recordingEngine(requests: MutableList<HttpRequestData>): MockEngine =
        MockEngine { request ->
            requests += request
            respond(
                defaultJsonResponse(request.url.encodedPath, request.method.value, request.url.parameters["after"]),
                headers = jsonHeaders,
            )
        }

    private fun defaultJsonResponse(
        path: String,
        method: String,
        after: String?,
    ): String =
        when {
            method == "DELETE" -> "true"
            path.endsWith("/mark_as_completed") || path.endsWith("/start") -> "true"
            path.endsWith("/submit_assessment") -> assessmentResultJson()
            path == "/api/v1/courses" && method == "GET" -> coursePageJson(after)
            path == "/api/v1/courses" || path.startsWith("/api/v1/courses/") -> courseJson()
            path == "/api/v1/course_chapters" && method == "GET" -> pageJson(courseChapterJson())
            path == "/api/v1/course_chapters" || path.startsWith("/api/v1/course_chapters/") -> courseChapterJson()
            path == "/api/v1/course_lessons" && method == "GET" -> pageJson(courseLessonJson())
            path == "/api/v1/course_lessons" || path.startsWith("/api/v1/course_lessons/") -> courseLessonJson()
            path == "/api/v1/course_lesson_interactions" -> pageJson(courseLessonInteractionJson(includeCourse = false))
            path.startsWith("/api/v1/course_lesson_interactions/") -> courseLessonInteractionJson(includeCourse = true)
            else -> "{}"
        }

    private fun pageJson(item: String): String =
        """
        {"data":[$item],"page_info":{"has_next_page":false}}
        """.trimIndent()

    private fun coursePageJson(after: String?): String =
        if (after == null) {
            """
            {"data":[${courseJson("cors_123")}],"page_info":{"has_next_page":true,"end_cursor":"cursor_1"}}
            """.trimIndent()
        } else {
            """
            {"data":[${courseJson("cors_456")}],"page_info":{"has_next_page":false}}
            """.trimIndent()
        }

    private fun courseJson(id: String = "cors_123"): String =
        """
        {
          "id":"$id",
          "title":"Kotlin Course",
          "tagline":null,
          "cover_image":null,
          "thumbnail":null,
          "description":null,
          "language":"en",
          "certificate_after_completion_enabled":false,
          "require_completing_lessons_in_order":false,
          "order":"1",
          "visibility":"visible",
          "created_at":"2023-12-01T05:00:00.401Z",
          "updated_at":"2023-12-02T05:00:00.401Z",
          "chapters":[]
        }
        """.trimIndent()

    private fun courseChapterJson(): String =
        """
        {"id":"chap_123","title":"Intro","order":0,"lessons":[]}
        """.trimIndent()

    private fun courseLessonJson(): String =
        """
        {
          "id":"lesn_123",
          "title":"Lesson 1",
          "order":0,
          "lesson_type":"video",
          "visibility":"visible",
          "content":null,
          "days_from_course_start_until_unlock":null,
          "embed_type":"youtube",
          "embed_id":"abc123",
          "created_at":"2023-12-01T05:00:00.401Z",
          "thumbnail":null,
          "video_asset":null,
          "main_pdf":null,
          "assessment_questions":[],
          "attachments":[]
        }
        """.trimIndent()

    private fun courseLessonInteractionJson(includeCourse: Boolean): String {
        val course =
            if (includeCourse) {
                """
                ,"course":{"id":"cors_123","title":"Kotlin Course","experience":{"id":"exp_123"}}
                """.trimIndent()
            } else {
                ""
            }
        return """
            {
              "id":"crsli_123",
              "completed":true,
              "created_at":"2023-12-01T05:00:00.401Z",
              "lesson":{"id":"lesn_123","title":"Lesson 1","chapter":{"id":"chap_123"}},
              "user":{"id":"user_123","name":null,"username":"dean"}$course
            }
            """.trimIndent()
    }

    private fun assessmentResultJson(): String =
        """
        {
          "id":"crsar_123",
          "created_at":"2023-12-01T05:00:00.401Z",
          "updated_at":"2023-12-02T05:00:00.401Z",
          "result_grade":1.0,
          "result_correct":1,
          "result_question_count":1,
          "result_passing_grade":true,
          "result_graded_questions":{},
          "score_percent":100.0,
          "user":{"id":"user_123","name":null,"username":"dean"},
          "lesson":{"id":"lesn_123","title":"Lesson 1"}
        }
        """.trimIndent()

    private fun clientWithEngine(engine: MockEngine): WhopClient {
        val config = WhopClientConfig(apiKey = "test_api_key")
        val httpClient = HttpClient(engine) { expectSuccess = false }
        return WhopClient(config, KtorWhopHttpTransport(config, httpClient))
    }

    private fun HttpRequestData.bodyText(): String = (body as TextContent).text

    private fun assertNoTextBody(request: HttpRequestData) {
        assertFalse(request.body is TextContent)
        assertTrue(request.body is OutgoingContent.NoContent)
    }

    private companion object {
        val jsonHeaders = headersOf(HttpHeaders.ContentType, "application/json")
    }
}
