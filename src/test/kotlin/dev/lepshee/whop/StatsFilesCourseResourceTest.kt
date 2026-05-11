package dev.lepshee.whop

import dev.lepshee.whop.models.common.Direction
import dev.lepshee.whop.models.files.CreateFileRequest
import dev.lepshee.whop.models.files.FileVisibility
import dev.lepshee.whop.resources.DescribeStatsRequest
import dev.lepshee.whop.resources.ListCourseStudentsRequest
import dev.lepshee.whop.resources.MetricStatsRequest
import dev.lepshee.whop.resources.RawStatsRequest
import dev.lepshee.whop.resources.SqlStatsRequest
import dev.lepshee.whop.transport.KtorWhopHttpTransport
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.HttpRequestData
import io.ktor.http.HttpHeaders
import io.ktor.http.content.TextContent
import io.ktor.http.headersOf
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class StatsFilesCourseResourceTest {
    @Test
    fun `stats endpoints send documented paths and query params`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            whop.stats.describe(DescribeStatsRequest(resource = "payments:membership", companyId = "biz_123"))
            whop.stats.metric(
                MetricStatsRequest(
                    resource = "receipts:gross_revenue",
                    granularity = "daily",
                    breakdowns = listOf("currency", "status"),
                    filters = buildJsonObject { put("currency", "usd") },
                    timeZone = "America/New_York",
                ),
            )
            whop.stats.raw(RawStatsRequest(resource = "members", limit = 25, sortDirection = Direction.Desc))
            whop.stats.sql(SqlStatsRequest(resource = "payments:membership", sql = "SELECT * FROM SCOPED_DATA LIMIT 10"))

            assertEquals("/api/v1/stats/describe", requests[0].url.encodedPath)
            assertEquals("payments:membership", requests[0].url.parameters["resource"])
            assertEquals("biz_123", requests[0].url.parameters["company_id"])
            assertEquals("/api/v1/stats/metric", requests[1].url.encodedPath)
            assertEquals(listOf("currency", "status"), requests[1].url.parameters.getAll("breakdowns"))
            assertTrue(
                requests[1]
                    .url
                    .parameters["filters"]
                    .orEmpty()
                    .contains("currency"),
            )
            assertEquals("America/New_York", requests[1].url.parameters["time_zone"])
            assertEquals("/api/v1/stats/raw", requests[2].url.encodedPath)
            assertEquals("25", requests[2].url.parameters["limit"])
            assertEquals("desc", requests[2].url.parameters["sort_direction"])
            assertEquals("/api/v1/stats/sql", requests[3].url.encodedPath)
            assertEquals("SELECT * FROM SCOPED_DATA LIMIT 10", requests[3].url.parameters["sql"])
        }

    @Test
    fun `files endpoints send documented paths and bodies`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val created = whop.files.create(CreateFileRequest(filename = "photo.png", visibility = FileVisibility.Private))
            whop.files.retrieve("file_123")

            assertEquals("file_123", created.id)
            assertEquals("/api/v1/files", requests[0].url.encodedPath)
            assertEquals("POST", requests[0].method.value)
            assertTrue(requests[0].bodyText().contains("photo.png"))
            assertTrue(requests[0].bodyText().contains("private"))
            assertEquals("/api/v1/files/file_123", requests[1].url.encodedPath)
        }

    @Test
    fun `course student endpoints send documented paths and filters`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val page = whop.courseStudents.list(ListCourseStudentsRequest(courseId = "cors_123", keyword = "dean", first = 10))
            whop.courseStudents.retrieve("crst_123")

            assertEquals("crst_123", page.data.single().id)
            assertEquals("/api/v1/course_students", requests[0].url.encodedPath)
            assertEquals("cors_123", requests[0].url.parameters["course_id"])
            assertEquals("dean", requests[0].url.parameters["keyword"])
            assertEquals("10", requests[0].url.parameters["first"])
            assertEquals("/api/v1/course_students/crst_123", requests[1].url.encodedPath)
        }

    @Test
    fun `new path parameters reject path injection characters`() =
        runTest {
            val whop = clientWithEngine(recordingEngine(mutableListOf()))

            assertFailsWith<IllegalArgumentException> { whop.files.retrieve("file_123/other") }
            assertFailsWith<IllegalArgumentException> { whop.courseStudents.retrieve("crst_123%2Fother") }
        }

    private fun recordingEngine(requests: MutableList<HttpRequestData>): MockEngine =
        MockEngine { request ->
            requests += request
            respond(defaultJsonResponse(request.url.encodedPath), headers = jsonHeaders)
        }

    private fun defaultJsonResponse(path: String): String =
        when {
            path.contains("stats") -> """{"typename":"Result","columns":[],"data":[],"debug":null,"node":null,"pagination":null}"""
            path == "/api/v1/files" || path.startsWith("/api/v1/files/") -> fileJson()
            path == "/api/v1/course_students" ->
                """{"data":[${courseStudentJson()}],"page_info":{"has_next_page":false}}"""
            path.startsWith("/api/v1/course_students/") -> courseStudentJson()
            else -> "{}"
        }

    private fun fileJson(): String =
        """{"id":"file_123","upload_status":"pending","filename":"photo.png","content_type":null,"size":null,"url":null,"visibility":"private","upload_url":"https://example.com/upload","upload_headers":{}}"""

    private fun courseStudentJson(): String =
        """{"id":"crst_123","completed_lessons_count":1,"completion_rate":50.0,"total_lessons_count":2,"first_interaction_at":"2023-12-01T05:00:00.401Z","last_interaction_at":"2023-12-02T05:00:00.401Z","user":{"id":"user_123","username":"dean","name":null},"course":{"id":"cors_123","title":"Course","experience":{"id":"exp_123"}}}"""

    private fun clientWithEngine(engine: MockEngine): WhopClient {
        val config = WhopClientConfig(apiKey = "test_api_key")
        val httpClient = HttpClient(engine) { expectSuccess = false }
        return WhopClient(config, KtorWhopHttpTransport(config, httpClient))
    }

    private fun HttpRequestData.bodyText(): String = (body as TextContent).text

    private companion object {
        val jsonHeaders = headersOf(HttpHeaders.ContentType, "application/json")
    }
}
