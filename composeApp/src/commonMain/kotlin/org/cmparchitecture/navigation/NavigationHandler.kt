package org.cmparchitecture.navigation

import androidx.navigation.NavHostController


sealed class NavigationAction {
    data object PopBackStack : NavigationAction()
    data class NavigateTo(val route: Route, val clearBackStack: Boolean = false) : NavigationAction()
}

fun handleNavigation(
    action: NavigationAction,
    navController: NavHostController,
) {
    when (action) {
        NavigationAction.PopBackStack -> {
            navController.popBackStack()
        }

        is NavigationAction.NavigateTo -> {
            if (action.clearBackStack) {
                navController.navigate(action.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            } else {
                navController.navigate(action.route)
            }
        }
    }
}