package kw.team.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import kw.team.home.HomeRoute

@Serializable
data object Home

fun NavGraphBuilder.homeScreen(
) {
    composable<Home> {
        HomeRoute()
    }
}
