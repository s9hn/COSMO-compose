package com.team.cosmo_compose.ui.quiz.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.team.cosmo_compose.ui.quiz.QuizScreen
import com.team.cosmo_compose.ui.quiz.navigation.QuizRoute.ROUTE

fun NavGraphBuilder.quizScreen(
    onBackClick: () -> Unit,
) {
    composable(route = ROUTE) {
        QuizScreen(onBackClick = onBackClick)
    }
}

fun NavController.navigateToQuiz() {
    navigate(ROUTE)
}

object QuizRoute {
    const val ROUTE = "quiz"
}
