package com.team.cosmo_compose.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.team.cosmo_compose.ui.home.navigation.HomeRoute
import com.team.cosmo_compose.ui.home.navigation.homeScreen
import com.team.cosmo_compose.ui.quiz.navigation.navigateToQuiz
import com.team.cosmo_compose.ui.quiz.navigation.quizScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = HomeRoute.ROUTE,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        homeScreen(onQuizClick = navController::navigateToQuiz)
        quizScreen(onBackClick = navController::popBackStack)
    }
}