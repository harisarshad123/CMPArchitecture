package org.cmparchitecture.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {


    @Serializable
    data object DashboardDisplay : Route

    @Serializable
    data object SignUpDisplay : Route

    @Serializable
    data object PopBackStack : Route

    @Serializable
    data object SignIn : Route

    @Serializable
    data object WelcomeScreen : Route

    @Serializable
    data object SplashDisplay : Route

}