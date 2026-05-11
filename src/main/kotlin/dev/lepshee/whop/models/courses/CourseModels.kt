package dev.lepshee.whop.models.courses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class Course(
    val id: String,
    val title: String? = null,
    val tagline: String? = null,
    @SerialName("cover_image") val coverImage: String? = null,
    val thumbnail: JsonObject? = null,
    val description: String? = null,
    val language: String? = null,
    @SerialName("certificate_after_completion_enabled") val certificateAfterCompletionEnabled: Boolean? = null,
    @SerialName("require_completing_lessons_in_order") val requireCompletingLessonsInOrder: Boolean = false,
    val order: String? = null,
    val visibility: CourseVisibility? = null,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("updated_at") val updatedAt: String? = null,
    val chapters: List<JsonObject> = emptyList(),
)

@Serializable
data class CourseChapter(
    val id: String,
    val title: String,
    val order: Int,
    val lessons: List<JsonObject> = emptyList(),
)

@Serializable
data class CourseLesson(
    val id: String,
    val title: String,
    val order: Int,
    @SerialName("lesson_type") val lessonType: LessonType,
    val visibility: LessonVisibility? = null,
    val content: String? = null,
    @SerialName("days_from_course_start_until_unlock") val daysFromCourseStartUntilUnlock: Int? = null,
    @SerialName("embed_type") val embedType: EmbedType? = null,
    @SerialName("embed_id") val embedId: String? = null,
    @SerialName("created_at") val createdAt: String? = null,
    val thumbnail: JsonObject? = null,
    @SerialName("video_asset") val videoAsset: JsonObject? = null,
    @SerialName("main_pdf") val mainPdf: JsonObject? = null,
    @SerialName("assessment_questions") val assessmentQuestions: List<JsonObject> = emptyList(),
    val attachments: List<JsonObject> = emptyList(),
)

@Serializable
data class CourseLessonInteraction(
    val id: String,
    val completed: Boolean,
    @SerialName("created_at") val createdAt: String? = null,
    val lesson: JsonObject,
    val user: JsonObject,
    val course: JsonObject? = null,
)

@Serializable
data class CreateCourseRequest(
    @SerialName("experience_id") val experienceId: String,
    val title: String,
    @SerialName("certificate_after_completion_enabled") val certificateAfterCompletionEnabled: Boolean? = null,
    val order: String? = null,
    @SerialName("require_completing_lessons_in_order") val requireCompletingLessonsInOrder: Boolean? = null,
    val tagline: String? = null,
    val thumbnail: FileInputWithId? = null,
    val visibility: CourseVisibility? = null,
) {
    init {
        require(experienceId.isNotBlank()) { "Experience ID must not be blank." }
        require(title.isNotBlank()) { "Course title must not be blank." }
        require(order == null || order.isNotBlank()) { "Course order must not be blank." }
        require(tagline == null || tagline.isNotBlank()) { "Course tagline must not be blank." }
    }
}

@Serializable
data class UpdateCourseRequest(
    @SerialName("certificate_after_completion_enabled") val certificateAfterCompletionEnabled: Boolean? = null,
    val chapters: List<UpdateCourseChapterInput>? = null,
    val description: String? = null,
    val language: String? = null,
    val order: String? = null,
    @SerialName("require_completing_lessons_in_order") val requireCompletingLessonsInOrder: Boolean? = null,
    val tagline: String? = null,
    val thumbnail: FileInputWithId? = null,
    val title: String? = null,
    val visibility: CourseVisibility? = null,
) {
    init {
        require(description == null || description.isNotBlank()) { "Course description must not be blank." }
        require(language == null || language.isNotBlank()) { "Course language must not be blank." }
        require(order == null || order.isNotBlank()) { "Course order must not be blank." }
        require(tagline == null || tagline.isNotBlank()) { "Course tagline must not be blank." }
        require(title == null || title.isNotBlank()) { "Course title must not be blank." }
    }
}

@Serializable
data class UpdateCourseChapterInput(
    val id: String,
    val order: Int,
    val title: String,
    val lessons: List<UpdateCourseLessonOrderInput>? = null,
) {
    init {
        require(id.isNotBlank()) { "Course chapter ID must not be blank." }
        require(order >= 0) { "Course chapter order must not be negative." }
        require(title.isNotBlank()) { "Course chapter title must not be blank." }
    }
}

@Serializable
data class UpdateCourseLessonOrderInput(
    @SerialName("chapter_id") val chapterId: String,
    val id: String,
    val order: Int,
    val title: String,
) {
    init {
        require(chapterId.isNotBlank()) { "Course chapter ID must not be blank." }
        require(id.isNotBlank()) { "Course lesson ID must not be blank." }
        require(order >= 0) { "Course lesson order must not be negative." }
        require(title.isNotBlank()) { "Course lesson title must not be blank." }
    }
}

@Serializable
data class CreateCourseChapterRequest(
    @SerialName("course_id") val courseId: String,
    val title: String? = null,
) {
    init {
        require(courseId.isNotBlank()) { "Course ID must not be blank." }
        require(title == null || title.isNotBlank()) { "Course chapter title must not be blank." }
    }
}

@Serializable
data class UpdateCourseChapterRequest(
    val title: String,
) {
    init {
        require(title.isNotBlank()) { "Course chapter title must not be blank." }
    }
}

@Serializable
data class CreateCourseLessonRequest(
    @SerialName("chapter_id") val chapterId: String,
    @SerialName("lesson_type") val lessonType: LessonType,
    val content: String? = null,
    @SerialName("days_from_course_start_until_unlock") val daysFromCourseStartUntilUnlock: Int? = null,
    @SerialName("embed_id") val embedId: String? = null,
    @SerialName("embed_type") val embedType: EmbedType? = null,
    val thumbnail: FileInputWithId? = null,
    val title: String? = null,
) {
    init {
        require(chapterId.isNotBlank()) { "Course chapter ID must not be blank." }
        require(content == null || content.isNotBlank()) { "Course lesson content must not be blank." }
        require(daysFromCourseStartUntilUnlock == null || daysFromCourseStartUntilUnlock >= 0) {
            "Days from course start until unlock must not be negative."
        }
        require(embedId == null || embedId.isNotBlank()) { "Course lesson embed ID must not be blank." }
        require(title == null || title.isNotBlank()) { "Course lesson title must not be blank." }
    }
}

@Serializable
data class UpdateCourseLessonRequest(
    @SerialName("assessment_completion_requirement") val assessmentCompletionRequirement: AssessmentCompletionRequirement? = null,
    @SerialName("assessment_questions") val assessmentQuestions: List<AssessmentQuestionInput>? = null,
    val attachments: List<FileInputWithId>? = null,
    val content: String? = null,
    @SerialName("days_from_course_start_until_unlock") val daysFromCourseStartUntilUnlock: Int? = null,
    @SerialName("embed_id") val embedId: String? = null,
    @SerialName("embed_type") val embedType: EmbedType? = null,
    @SerialName("lesson_type") val lessonType: LessonType? = null,
    @SerialName("main_pdf") val mainPdf: FileInputWithId? = null,
    @SerialName("max_attempts") val maxAttempts: Int? = null,
    @SerialName("mux_asset_id") val muxAssetId: String? = null,
    val thumbnail: FileInputWithId? = null,
    val title: String? = null,
    val visibility: LessonVisibility? = null,
) {
    init {
        require(content == null || content.isNotBlank()) { "Course lesson content must not be blank." }
        require(daysFromCourseStartUntilUnlock == null || daysFromCourseStartUntilUnlock >= 0) {
            "Days from course start until unlock must not be negative."
        }
        require(embedId == null || embedId.isNotBlank()) { "Course lesson embed ID must not be blank." }
        require(maxAttempts == null || maxAttempts > 0) { "Maximum attempts must be positive." }
        require(muxAssetId == null || muxAssetId.isNotBlank()) { "Mux asset ID must not be blank." }
        require(title == null || title.isNotBlank()) { "Course lesson title must not be blank." }
    }
}

@Serializable
data class AssessmentCompletionRequirement(
    @SerialName("minimum_grade_percent") val minimumGradePercent: Double? = null,
    @SerialName("minimum_questions_correct") val minimumQuestionsCorrect: Int? = null,
) {
    init {
        require(minimumGradePercent == null || minimumGradePercent in 0.0..100.0) {
            "Minimum grade percent must be between 0 and 100."
        }
        require(minimumQuestionsCorrect == null || minimumQuestionsCorrect > 0) {
            "Minimum questions correct must be positive."
        }
        require(minimumGradePercent == null || minimumQuestionsCorrect == null) {
            "Specify either minimum grade percent or minimum questions correct, not both."
        }
    }
}

@Serializable
data class AssessmentQuestionInput(
    @SerialName("correct_answer") val correctAnswer: String,
    @SerialName("question_text") val questionText: String,
    @SerialName("question_type") val questionType: CourseAssessmentQuestionType,
    val id: String? = null,
    val image: FileInputWithId? = null,
    val options: List<AssessmentQuestionOptionInput>? = null,
) {
    init {
        require(correctAnswer.isNotBlank()) { "Assessment correct answer must not be blank." }
        require(questionText.isNotBlank()) { "Assessment question text must not be blank." }
        require(id == null || id.isNotBlank()) { "Assessment question ID must not be blank." }
    }
}

@Serializable
data class AssessmentQuestionOptionInput(
    @SerialName("is_correct") val isCorrect: Boolean,
    @SerialName("option_text") val optionText: String,
    val id: String? = null,
) {
    init {
        require(optionText.isNotBlank()) { "Assessment option text must not be blank." }
        require(id == null || id.isNotBlank()) { "Assessment option ID must not be blank." }
    }
}

@Serializable
data class SubmitAssessmentRequest(
    val answers: List<AssessmentAnswerRequest>,
) {
    init {
        require(answers.isNotEmpty()) { "Assessment answers must not be empty." }
    }
}

@Serializable
data class AssessmentAnswerRequest(
    @SerialName("question_id") val questionId: String,
    @SerialName("answer_text") val answerText: String? = null,
    @SerialName("selected_option_ids") val selectedOptionIds: List<String>? = null,
) {
    init {
        require(questionId.isNotBlank()) { "Assessment question ID must not be blank." }
        require(answerText == null || answerText.isNotBlank()) { "Assessment answer text must not be blank." }
        require(selectedOptionIds == null || selectedOptionIds.all { it.isNotBlank() }) {
            "Selected option IDs must not be blank."
        }
    }
}

@Serializable
data class AssessmentResult(
    val id: String,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("updated_at") val updatedAt: String? = null,
    @SerialName("result_grade") val resultGrade: Double,
    @SerialName("result_correct") val resultCorrect: Int,
    @SerialName("result_question_count") val resultQuestionCount: Int,
    @SerialName("result_passing_grade") val resultPassingGrade: Boolean,
    @SerialName("result_graded_questions") val resultGradedQuestions: JsonObject,
    @SerialName("score_percent") val scorePercent: Double,
    val user: JsonObject,
    val lesson: JsonObject,
)

@Serializable
data class FileInputWithId(
    val id: String,
) {
    init {
        require(id.isNotBlank()) { "File ID must not be blank." }
    }
}

@Serializable
enum class CourseVisibility {
    @SerialName("visible")
    Visible,

    @SerialName("hidden")
    Hidden,
}

@Serializable
enum class LessonType {
    @SerialName("text")
    Text,

    @SerialName("video")
    Video,

    @SerialName("pdf")
    Pdf,

    @SerialName("multi")
    Multi,

    @SerialName("quiz")
    Quiz,

    @SerialName("knowledge_check")
    KnowledgeCheck,
}

@Serializable
enum class LessonVisibility {
    @SerialName("visible")
    Visible,

    @SerialName("hidden")
    Hidden,
}

@Serializable
enum class EmbedType {
    @SerialName("youtube")
    Youtube,

    @SerialName("loom")
    Loom,
}

@Serializable
enum class CourseAssessmentQuestionType {
    @SerialName("short_answer")
    ShortAnswer,

    @SerialName("true_false")
    TrueFalse,

    @SerialName("multiple_choice")
    MultipleChoice,

    @SerialName("multiple_select")
    MultipleSelect,
}
