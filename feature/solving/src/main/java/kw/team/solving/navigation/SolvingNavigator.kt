package kw.team.solving.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import kw.team.solving.SolvingScreen
import kw.team.subject.model.Subject

@Serializable
data class Solving(val subject: Subject? = null)

fun NavGraphBuilder.solvingScreen() {
    composable<Solving> {backStackEntry ->
        val subject: Solving = backStackEntry.toRoute<Solving>()
        SolvingScreen(subject.subject)
    }
}