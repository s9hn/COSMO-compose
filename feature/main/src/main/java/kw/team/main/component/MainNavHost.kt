package kw.team.main.component

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import kw.team.home.navigation.Home
import kw.team.home.navigation.homeScreen

@Composable
fun MainNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Home,
    ) {
        homeScreen()
    }
}
