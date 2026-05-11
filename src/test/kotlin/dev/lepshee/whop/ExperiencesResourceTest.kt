package dev.lepshee.whop

import dev.lepshee.whop.models.experiences.CreateExperienceRequest
import dev.lepshee.whop.models.experiences.DuplicateExperienceRequest
import dev.lepshee.whop.models.experiences.ExperienceAccessLevel
import dev.lepshee.whop.models.experiences.ExperienceLogoInput
import dev.lepshee.whop.models.experiences.ExperienceProductRequest
import dev.lepshee.whop.models.experiences.UpdateExperienceRequest
import dev.lepshee.whop.resources.ListExperiencesRequest
import dev.lepshee.whop.transport.KtorWhopHttpTransport
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.HttpRequestData
import io.ktor.http.HttpHeaders
import io.ktor.http.content.TextContent
import io.ktor.http.headersOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class ExperiencesResourceTest {
    @Test
    fun `experience endpoints send documented paths query params bodies and actions`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val page =
                whop.experiences.list(
                    ListExperiencesRequest(
                        companyId = "biz_123",
                        after = "cursor_0",
                        before = "cursor_before",
                        first = 10,
                        last = 20,
                        productId = "prod_123",
                        appId = "app_123",
                        createdBefore = "2023-12-31T05:00:00.401Z",
                        createdAfter = "2023-12-01T05:00:00.401Z",
                    ),
                )
            val created =
                whop.experiences.create(
                    CreateExperienceRequest(
                        appId = "app_123",
                        companyId = "biz_123",
                        isPublic = true,
                        logo = ExperienceLogoInput(id = "file_123"),
                        name = "Signals Chat",
                        sectionId = "sect_123",
                    ),
                )
            val retrieved = whop.experiences.retrieve("exp_123")
            val updated =
                whop.experiences.update(
                    "exp_123",
                    UpdateExperienceRequest(
                        accessLevel = ExperienceAccessLevel.Private,
                        isPublic = false,
                        logo = ExperienceLogoInput(id = "file_456"),
                        name = "Private Signals",
                        order = "1727606400000",
                        sectionId = "sect_456",
                    ),
                )
            val attached = whop.experiences.attach("exp_123", ExperienceProductRequest(productId = "prod_123"))
            val detached = whop.experiences.detach("exp_123", ExperienceProductRequest(productId = "prod_123"))
            val duplicated = whop.experiences.duplicate("exp_123", DuplicateExperienceRequest(name = "Signals Copy"))
            val defaultDuplicated = whop.experiences.duplicate("exp_123")
            val deleted = whop.experiences.delete("exp_123")

            assertEquals("exp_456", page.data.single().id)
            assertEquals(
                "Courses",
                page.data
                    .single()
                    .app
                    .name,
            )
            assertEquals(
                "pickaxe",
                page.data
                    .single()
                    .company
                    .route,
            )
            assertEquals("exp_123", created.id)
            assertEquals(listOf("prod_123"), retrieved.products.map { it.id })
            assertEquals("Pickaxe Analytics", retrieved.products.single().title)
            assertEquals("exp_123", updated.id)
            assertEquals("exp_123", attached.id)
            assertEquals("exp_123", detached.id)
            assertEquals("exp_123", duplicated.id)
            assertEquals("exp_123", defaultDuplicated.id)
            assertTrue(deleted)

            assertEquals("/api/v1/experiences", requests[0].url.encodedPath)
            assertEquals("biz_123", requests[0].url.parameters["company_id"])
            assertEquals("cursor_0", requests[0].url.parameters["after"])
            assertEquals("cursor_before", requests[0].url.parameters["before"])
            assertEquals("10", requests[0].url.parameters["first"])
            assertEquals("20", requests[0].url.parameters["last"])
            assertEquals("prod_123", requests[0].url.parameters["product_id"])
            assertEquals("app_123", requests[0].url.parameters["app_id"])
            assertEquals("2023-12-31T05:00:00.401Z", requests[0].url.parameters["created_before"])
            assertEquals("2023-12-01T05:00:00.401Z", requests[0].url.parameters["created_after"])

            assertEquals("POST", requests[1].method.value)
            assertEquals("/api/v1/experiences", requests[1].url.encodedPath)
            assertTrue(requests[1].bodyText().contains("app_id"))
            assertTrue(requests[1].bodyText().contains("company_id"))
            assertTrue(requests[1].bodyText().contains("is_public"))
            assertTrue(requests[1].bodyText().contains("logo"))
            assertTrue(requests[1].bodyText().contains("section_id"))
            assertTrue(requests[1].bodyText().contains("file_123"))

            assertEquals("GET", requests[2].method.value)
            assertEquals("/api/v1/experiences/exp_123", requests[2].url.encodedPath)
            assertEquals("PATCH", requests[3].method.value)
            assertEquals("/api/v1/experiences/exp_123", requests[3].url.encodedPath)
            assertTrue(requests[3].bodyText().contains("access_level"))
            assertTrue(requests[3].bodyText().contains("private"))
            assertTrue(requests[3].bodyText().contains("1727606400000"))

            assertEquals("POST", requests[4].method.value)
            assertEquals("/api/v1/experiences/exp_123/attach", requests[4].url.encodedPath)
            assertTrue(requests[4].bodyText().contains("product_id"))
            assertEquals("/api/v1/experiences/exp_123/detach", requests[5].url.encodedPath)
            assertTrue(requests[5].bodyText().contains("prod_123"))
            assertEquals("/api/v1/experiences/exp_123/duplicate", requests[6].url.encodedPath)
            assertTrue(requests[6].bodyText().contains("Signals Copy"))
            assertEquals("{}", requests[7].bodyText())
            assertEquals("DELETE", requests[8].method.value)
            assertEquals("/api/v1/experiences/exp_123", requests[8].url.encodedPath)
        }

    @Test
    fun `experience auto paging follows forward cursors`() =
        runTest {
            val requests = mutableListOf<HttpRequestData>()
            val whop = clientWithEngine(recordingEngine(requests))

            val experiences = whop.experiences.listAutoPaging(ListExperiencesRequest(companyId = "biz_123", first = 1)).toList()

            assertEquals(listOf("exp_123", "exp_456"), experiences.map { it.id })
            assertEquals("/api/v1/experiences", requests[0].url.encodedPath)
            assertEquals("biz_123", requests[0].url.parameters["company_id"])
            assertEquals("1", requests[0].url.parameters["first"])
            assertEquals("cursor_1", requests[1].url.parameters["after"])
        }

    @Test
    fun `experience path parameters reject path injection characters`() =
        runTest {
            val whop = clientWithEngine(recordingEngine(mutableListOf()))

            assertFailsWith<IllegalArgumentException> { whop.experiences.retrieve("exp_123/other") }
            assertFailsWith<IllegalArgumentException> { whop.experiences.update("exp_123%2Fother") }
            assertFailsWith<IllegalArgumentException> { whop.experiences.delete("../exp_123") }
            assertFailsWith<IllegalArgumentException> {
                whop.experiences.attach("exp_123?admin=true", ExperienceProductRequest("prod_123"))
            }
            assertFailsWith<IllegalArgumentException> {
                whop.experiences.detach("exp_123#fragment", ExperienceProductRequest("prod_123"))
            }
            assertFailsWith<IllegalArgumentException> { whop.experiences.duplicate(".") }
        }

    @Test
    fun `experience request validation rejects invalid inputs`() {
        assertFailsWith<IllegalArgumentException> { ListExperiencesRequest(companyId = " ") }
        assertFailsWith<IllegalArgumentException> { ListExperiencesRequest(companyId = "biz_123", after = "") }
        assertFailsWith<IllegalArgumentException> { ListExperiencesRequest(companyId = "biz_123", first = 0) }
        assertFailsWith<IllegalArgumentException> { ListExperiencesRequest(companyId = "biz_123", productId = " ") }
        assertFailsWith<IllegalArgumentException> { ListExperiencesRequest(companyId = "biz_123", appId = "") }
        assertFailsWith<IllegalArgumentException> { ListExperiencesRequest(companyId = "biz_123", createdBefore = " ") }
        assertFailsWith<IllegalArgumentException> { CreateExperienceRequest(appId = " ", companyId = "biz_123") }
        assertFailsWith<IllegalArgumentException> { CreateExperienceRequest(appId = "app_123", companyId = "") }
        assertFailsWith<IllegalArgumentException> { CreateExperienceRequest(appId = "app_123", companyId = "biz_123", name = " ") }
        assertFailsWith<IllegalArgumentException> { ExperienceLogoInput(id = "") }
        assertFailsWith<IllegalArgumentException> { UpdateExperienceRequest(order = "") }
        assertFailsWith<IllegalArgumentException> { UpdateExperienceRequest(sectionId = " ") }
        assertFailsWith<IllegalArgumentException> { ExperienceProductRequest(productId = " ") }
        assertFailsWith<IllegalArgumentException> { DuplicateExperienceRequest(name = "") }
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
            path == "/api/v1/experiences" && method == "GET" -> experiencePageJson(after)
            path == "/api/v1/experiences" || path.startsWith("/api/v1/experiences/") -> experienceJson()
            else -> "{}"
        }

    private fun experiencePageJson(after: String?): String =
        if (after == null) {
            """
            {"data":[${experienceListItemJson("exp_123")}],"page_info":{"has_next_page":true,"end_cursor":"cursor_1"}}
            """.trimIndent()
        } else {
            """
            {"data":[${experienceListItemJson("exp_456")}],"page_info":{"has_next_page":false}}
            """.trimIndent()
        }

    private fun experienceListItemJson(id: String = "exp_123"): String =
        """
        {
          "id":"$id",
          "name":"Trading Signals Chat",
          "order":"1727606400000",
          "is_public":true,
          "created_at":"2023-12-01T05:00:00.401Z",
          "app":{"id":"app_123","name":"Courses","icon":{"url":null}},
          "image":null,
          "company":{"id":"biz_123","title":"Pickaxe","route":"pickaxe"}
        }
        """.trimIndent()

    private fun experienceJson(): String =
        """
        {
          "id":"exp_123",
          "name":"Trading Signals Chat",
          "order":"1727606400000",
          "is_public":true,
          "created_at":"2023-12-01T05:00:00.401Z",
          "app":{"id":"app_123","name":"Courses","icon":{"url":"https://media.whop.com/icon.jpg"}},
          "image":{"url":null},
          "company":{"id":"biz_123","title":"Pickaxe","route":"pickaxe"},
          "products":[{"id":"prod_123","route":"pickaxe-analytics","title":"Pickaxe Analytics"}]
        }
        """.trimIndent()

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
