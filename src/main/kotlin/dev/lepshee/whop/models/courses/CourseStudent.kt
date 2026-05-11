package dev.lepshee.whop.models.courses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class CourseStudent(
    val id: String,
    @SerialName("completed_lessons_count") val completedLessonsCount: Int,
    @SerialName("completion_rate") val completionRate: Double,
    @SerialName("total_lessons_count") val totalLessonsCount: Int,
    @SerialName("first_interaction_at") val firstInteractionAt: String,
    @SerialName("last_interaction_at") val lastInteractionAt: String,
    val user: JsonObject,
    val course: JsonObject? = null,
)
