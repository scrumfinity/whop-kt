package dev.lepshee.whop.resources

import dev.lepshee.whop.WhopRequestOptions
import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopPage
import dev.lepshee.whop.core.pathParameter
import dev.lepshee.whop.models.forums.CreateForumPostRequest
import dev.lepshee.whop.models.forums.ForumPost
import dev.lepshee.whop.models.forums.ForumPostListItem
import dev.lepshee.whop.models.forums.UpdateForumPostRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Operations for forum posts and comments. */
class ForumPostsResource internal constructor(
    private val http: WhopHttpExecutor,
) {
    suspend fun list(
        request: ListForumPostsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): WhopPage<ForumPostListItem> = http.get("forum_posts", request.toQueryParameters(), options)

    suspend fun create(
        request: CreateForumPostRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): ForumPost = http.post("forum_posts", request, options)

    suspend fun retrieve(
        forumPostId: String,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): ForumPost {
        val id = pathParameter(forumPostId, "Forum post ID")
        return http.get("forum_posts/$id", options = options)
    }

    suspend fun update(
        forumPostId: String,
        request: UpdateForumPostRequest =
            UpdateForumPostRequest(),
        options: WhopRequestOptions = WhopRequestOptions(),
    ): ForumPost {
        val id = pathParameter(forumPostId, "Forum post ID")
        return http.patch("forum_posts/$id", request, options)
    }

    fun listAutoPaging(
        request: ListForumPostsRequest,
        options: WhopRequestOptions = WhopRequestOptions(),
    ): Flow<ForumPostListItem> =
        flow {
            var cursor: String? = request.after
            do {
                val page = list(request.copy(after = cursor), options)
                page.data.forEach { emit(it) }
                cursor = page.pageInfo.endCursor
            } while (page.pageInfo.hasNextPage && cursor != null)
        }
}

data class ListForumPostsRequest(
    val experienceId: String,
    val after: String? = null,
    val before: String? = null,
    val first: Int? = null,
    val last: Int? = null,
    val includeBountyAnchors: Boolean? = null,
    val parentId: String? = null,
    val pinned: Boolean? = null,
) {
    init {
        require(experienceId.isNotBlank()) { "Experience ID must not be blank." }
        require(after == null || after.isNotBlank()) { "After cursor must not be blank." }
        require(before == null || before.isNotBlank()) { "Before cursor must not be blank." }
        require(first == null || first > 0) { "First page size must be positive." }
        require(last == null || last > 0) { "Last page size must be positive." }
        require(parentId == null || parentId.isNotBlank()) { "Parent post ID must not be blank." }
    }

    fun toQueryParameters(): List<Pair<String, String>> =
        buildList {
            add("experience_id" to experienceId)
            after?.let { add("after" to it) }
            before?.let { add("before" to it) }
            first?.let { add("first" to it.toString()) }
            last?.let { add("last" to it.toString()) }
            includeBountyAnchors?.let { add("include_bounty_anchors" to it.toString()) }
            parentId?.let { add("parent_id" to it) }
            pinned?.let { add("pinned" to it.toString()) }
        }
}
