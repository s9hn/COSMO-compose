package kw.team.assesment

sealed interface Question {
    val id: Int
    val assessmentType: AssessmentType
    val question: String
}

class DescriptiveQuestion(
    override val id: Int,
    override val question: String,
) : Question {
    override val assessmentType: AssessmentType = AssessmentType.DESCRIPTIVE
}

class MultipleChoiceQuestion(
    override val id: Int,
    override val question: String,
    val options: List<Option>,
) : Question {
    override val assessmentType: AssessmentType = AssessmentType.MULTIPLE_CHOICE
    private var choice: Option = Option.getIdleOption()

    fun choose(index: Int) {
        choice = options[index]
    }

    fun isRight(answer: Answer): Boolean {
        if (answer is Answer.MultipleChoiceAnswer) {
            return answer.value == choice.value
        }
        return false
    }
}