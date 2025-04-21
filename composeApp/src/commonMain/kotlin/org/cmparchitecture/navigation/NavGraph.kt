package org.cmparchitecture.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.cmparchitecture.presentation.dashboard.DashboardScreen
import org.cmparchitecture.presentation.dashboard.DashboardViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SetUpNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = Modifier.fillMaxSize()
            .background(color = Color.White),
        navController = navController,
        startDestination = Route.DashboardDisplay,

        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(700)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(700)
            )
        }
    ) {
        composable<Route.DashboardDisplay> {
            val viewModel = koinViewModel<DashboardViewModel>()
            DashboardScreen(
                state = viewModel.state
            )
        }
    }
}