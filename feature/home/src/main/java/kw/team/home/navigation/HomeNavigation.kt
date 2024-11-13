package kw.team.home.navigation

import androidx.compose.runtime.Stable
import kw.team.subject.model.Subject

@Stable
interface HomeNavigation {
    fun navigateToSolvingForToday()
    fun navigateToSolvingBySubject(subject: Subject)
}