package kw.team.main.component

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import kw.team.home.navigation.Home
import kw.team.home.navigation.HomeNavigation
import kw.team.home.navigation.homeScreen
import kw.team.solving.navigation.Solving
import kw.team.solving.navigation.solvingScreen
import kw.team.subject.model.Subject

@Composable
fun MainNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Home,
    ) {
        homeScreen(
            homeNavigation = object : HomeNavigation {
                override fun navigateToSolvingForToday() {
                    navController.navigate(Solving())
                }

                override fun navigateToSolvingBySubject(subject: Subject) {
                    navController.navigate(Solving(subject))
                }

            }
        )
        solvingScreen()
    }
}
