package com.team.cosmo_compose.ui.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.team.cosmo_compose.ui.home.HomeScreen
import com.team.cosmo_compose.ui.home.navigation.HomeRoute.ROUTE

fun NavGraphBuilder.homeScreen(
    onQuizClick: () -> Unit,
) {
    composable(route = ROUTE) {
        HomeScreen(
            onQuizClick = onQuizClick,
        )
    }
}

object HomeRoute {
    const val ROUTE = "home"
}
