package kw.team.assesment

sealed interface Answer {
    val assessmentType: AssessmentType

    class MultipleChoiceAnswer(
        val value: Int,
        override val assessmentType: AssessmentType = AssessmentType.MULTIPLE_CHOICE,
    ) : Answer

    class DescriptiveAnswer(
        val value: String,
        override val assessmentType: AssessmentType = AssessmentType.DESCRIPTIVE,
    ) : Answer
}