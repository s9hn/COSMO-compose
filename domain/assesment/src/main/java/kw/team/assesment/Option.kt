package kw.team.assesment

data class Option(
    val value: Int,
    val content: String,
) {
    companion object {
        private const val IDLE_VALUE = "아직 사용자가 선택하지 않았습니다."
        fun getIdleOption(): Option {
            return Option(
                value = 0,
                content = IDLE_VALUE
            )
        }
    }
}