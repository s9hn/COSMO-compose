package com.team.cosmo_compose.ui.quiz.model

import androidx.compose.runtime.Stable

@Stable
data class QuizModel(
    val question: String,
    val choices: List<String>,
    val category: String,
    val questionType: String,
    val subject: String,
) {

    fun getQuestionInfo(): String = "$category · $subject · $questionType"
}

val Fixture = QuizModel(
    question = "운영체제에서 프로세스 관리의 주요 목적은 무엇인가요?",
    choices = listOf("프로세스 생성 및 종료", "메모리 할당 및 해제", "파일 시스템 관리", "네트워크 관리"),
    category = "오늘 학습하기",
    questionType = "객관식",
    subject = "운영체제"
)