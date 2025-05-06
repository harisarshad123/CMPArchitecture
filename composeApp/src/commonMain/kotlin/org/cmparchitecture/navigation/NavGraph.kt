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
import org.cmparchitecture.presentation.dashboard.DashboardViewModel
import org.cmparchitecture.presentation.dashboard.SignUpScreen
import org.cmparchitecture.presentation.signin.SignInScreen
import org.cmparchitecture.presentation.signin.SignInViewModel
import org.cmparchitecture.presentation.splash.SplashScreen
import org.cmparchitecture.presentation.splash.SplashViewModel
import org.cmparchitecture.presentation.welcomesreen.WelcomeScreen
import org.cmparchitecture.presentation.welcomesreen.WelcomeScreenViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SetUpNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = Modifier.fillMaxSize()
            .background(color = Color.White),
        navController = navController,
        startDestination = Route.SignIn,

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
        composable<Route.SignUpDisplay> {
            val viewModel = koinViewModel<DashboardViewModel>()
            SignUpScreen(
                state = viewModel.state,
                actionEvent = viewModel::actionEvent,
                navigation = {
                    handleNavigation(
                        action = it,
                        navController = navController
                    )
                },
                baseUIEvents = viewModel.baseUIEvents
            )
        }

        composable<Route.SignIn> {
            val viewModel = koinViewModel<SignInViewModel>()
            SignInScreen(
                state = viewModel.state,
                actionEvent = viewModel::actionEvent,
                navigation = {
                    handleNavigation(
                        action = it,
                        navController = navController
                    )
                },
                baseUIEvents = viewModel.baseUIEvents
            )
        }

        composable<Route.WelcomeScreen> {
            val viewModel = koinViewModel<WelcomeScreenViewModel>()
            WelcomeScreen(
                state = viewModel.state,
                actionEvent = viewModel::actionEvent,
                navigation = {
                    handleNavigation(
                        action = it,
                        navController = navController
                    )
                },
                baseUIEvents = viewModel.baseUIEvents
            )
        }

        composable<Route.SplashDisplay> {
            val viewModel = koinViewModel<SplashViewModel>()
            SplashScreen(
                state = viewModel.state,
                actionEvent = viewModel::actionEvent,
                navigation = {
                    handleNavigation(
                        action = it,
                        navController = navController
                    )
                },
                baseUIEvents = viewModel.baseUIEvents,
            )
        }
    }
}