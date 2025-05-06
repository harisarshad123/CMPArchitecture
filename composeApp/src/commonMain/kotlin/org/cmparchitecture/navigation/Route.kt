package org.cmparchitecture.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {

    @Serializable
    data object SignUpDisplay : Route

    @Serializable
    data object SignIn : Route

    @Serializable
    data object WelcomeDisplay : Route

    @Serializable
    data object SplashDisplay : Route

}