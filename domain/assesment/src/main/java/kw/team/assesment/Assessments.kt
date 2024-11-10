package kw.team.assesment

data class Assessments(
    val values: List<Assessment>,
    val assessmentsType: AssessmentsType,
) {
    private var curIndex = FIRST
    val current get() = values[curIndex]

    fun next(): Assessment {
        if (isEnd()) throw IllegalStateException("다음 문제가 없습니다.")
        return values[curIndex++]
    }

    fun previous(): Assessment {
        if (isFirst()) throw IllegalStateException("이전 문제가 없습니다.")
        return values[curIndex--]
    }

    fun getWrongs(): List<Assessment> {
        return values.filter { it.isWrong() }
    }

    fun isFirst(): Boolean = curIndex == FIRST
    fun isEnd(): Boolean = curIndex >= values.size

    companion object {
        private const val FIRST = 0
    }
}
