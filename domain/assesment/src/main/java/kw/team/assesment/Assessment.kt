package kw.team.assesment

import kw.team.subject.model.Subject

data class Assessment(
    val question: Question,
    val answer: Answer,
    val isSaved: Boolean,
    val subject: Subject,
    val state: State = State.IDLE,
) {
    fun isCorrect() = state == State.CORRECT
    fun isWrong() = state == State.WRONG

    enum class State {
        IDLE,
        CORRECT,
        WRONG,
    }
}
