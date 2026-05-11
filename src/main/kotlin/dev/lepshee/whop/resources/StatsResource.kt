package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.models.common.Direction
import kotlinx.serialization.json.JsonObject

/** Operations for Whop stats schema and query endpoints. */
class StatsResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun describe(
        request: DescribeStatsRequest =
            DescribeStatsRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): JsonObject = http.get("stats/describe", request.toQueryParameters(), options)

    suspend fun metric(
        request: MetricStatsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): JsonObject = http.get("stats/metric", request.toQueryParameters(), options)

    suspend fun raw(
        request: RawStatsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): JsonObject = http.get("stats/raw", request.toQueryParameters(), options)

    suspend fun sql(
        request: SqlStatsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): JsonObject = http.get("stats/sql", request.toQueryParameters(), options)
}

data class DescribeStatsRequest(
    val resource: String? = null,
    val companyId: String? = null,
    val userId: String? = null,
) {
    init {
        require(resource == null || resource.isNotBlank()) { "Resource must not be blank." }
        require(companyId == null || companyId.isNotBlank()) { "Company ID must not be blank." }
        require(userId == null || userId.isNotBlank()) { "User ID must not be blank." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            resource?.let { add("resource" to it) }
            companyId?.let { add("company_id" to it) }
            userId?.let { add("user_id" to it) }
        }
}

data class MetricStatsRequest(
    val resource: String,
    val granularity: String? = null,
    val breakdowns: List<String> = emptyList(),
    val filters: JsonObject? = null,
    val timeZone: String? = null,
    val from: String? = null,
    val to: String? = null,
    val companyId: String? = null,
    val userId: String? = null,
) {
    init {
        require(resource.isNotBlank()) { "Resource must not be blank." }
        require(granularity == null || granularity.isNotBlank()) { "Granularity must not be blank." }
        require(breakdowns.all(String::isNotBlank)) { "Breakdowns must not be blank." }
        require(timeZone == null || timeZone.isNotBlank()) { "Time zone must not be blank." }
        require(companyId == null || companyId.isNotBlank()) { "Company ID must not be blank." }
        require(userId == null || userId.isNotBlank()) { "User ID must not be blank." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            add("resource" to resource)
            granularity?.let { add("granularity" to it) }
            breakdowns.forEach { add("breakdowns" to it) }
            filters?.let { add("filters" to it.toString()) }
            timeZone?.let { add("time_zone" to it) }
            from?.let { add("from" to it) }
            to?.let { add("to" to it) }
            companyId?.let { add("company_id" to it) }
            userId?.let { add("user_id" to it) }
        }
}

data class RawStatsRequest(
    val resource: String,
    val from: String? = null,
    val to: String? = null,
    val limit: Int? = null,
    val cursor: String? = null,
    val sort: String? = null,
    val sortDirection: Direction? = null,
    val companyId: String? = null,
    val userId: String? = null,
) {
    init {
        require(resource.isNotBlank()) { "Resource must not be blank." }
        require(limit == null || limit > 0) { "Limit must be positive." }
        require(limit == null || limit <= 10_000) { "Limit must be 10000 or fewer." }
        require(cursor == null || cursor.isNotBlank()) { "Cursor must not be blank." }
        require(sort == null || sort.isNotBlank()) { "Sort must not be blank." }
        require(companyId == null || companyId.isNotBlank()) { "Company ID must not be blank." }
        require(userId == null || userId.isNotBlank()) { "User ID must not be blank." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        rawQueryParameters(
            resource = resource,
            from = from,
            to = to,
            limit = limit,
            cursor = cursor,
            sort = sort,
            sortDirection = sortDirection,
            companyId = companyId,
            userId = userId,
        )
}

data class SqlStatsRequest(
    val resource: String,
    val sql: String,
    val from: String? = null,
    val to: String? = null,
    val limit: Int? = null,
    val cursor: String? = null,
    val sort: String? = null,
    val sortDirection: Direction? = null,
    val companyId: String? = null,
    val userId: String? = null,
) {
    init {
        require(resource.isNotBlank()) { "Resource must not be blank." }
        require(sql.isNotBlank()) { "SQL must not be blank." }
        require(limit == null || limit > 0) { "Limit must be positive." }
        require(limit == null || limit <= 10_000) { "Limit must be 10000 or fewer." }
        require(cursor == null || cursor.isNotBlank()) { "Cursor must not be blank." }
        require(sort == null || sort.isNotBlank()) { "Sort must not be blank." }
        require(companyId == null || companyId.isNotBlank()) { "Company ID must not be blank." }
        require(userId == null || userId.isNotBlank()) { "User ID must not be blank." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            addAll(
                rawQueryParameters(
                    resource = resource,
                    from = from,
                    to = to,
                    limit = limit,
                    cursor = cursor,
                    sort = sort,
                    sortDirection = sortDirection,
                    companyId = companyId,
                    userId = userId,
                ),
            )
            add("sql" to sql)
        }
}

private fun rawQueryParameters(
    resource: String,
    from: String?,
    to: String?,
    limit: Int?,
    cursor: String?,
    sort: String?,
    sortDirection: Direction?,
    companyId: String?,
    userId: String?,
): List<Pair<String, String>> =
    buildList {
        add("resource" to resource)
        from?.let { add("from" to it) }
        to?.let { add("to" to it) }
        limit?.let { add("limit" to it.toString()) }
        cursor?.let { add("cursor" to it) }
        sort?.let { add("sort" to it) }
        sortDirection?.let {
            add(
                "sort_direction" to if (it == Direction.Asc) "asc" else "desc",
            )
        }
        companyId?.let { add("company_id" to it) }
        userId?.let { add("user_id" to it) }
    }
