package org.cmparchitecture.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {


    @Serializable
    data object DashboardDisplay : Route

    @Serializable
    data object PopBackStack : Route

}