package org.cmparchitecture.utils.bottomnav

import cmparchitecture.composeapp.generated.resources.Res
import cmparchitecture.composeapp.generated.resources.compose_multiplatform
import org.cmparchitecture.navigation.Route
import org.jetbrains.compose.resources.DrawableResource

data class BottomNavigationItem(
    val text: String,
    val icon: DrawableResource,
    val selectedIcon: DrawableResource,
    val route: Route
)

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        text = "Dashboard",
        icon = Res.drawable.compose_multiplatform,
        selectedIcon = Res.drawable.compose_multiplatform,
        route = Route.DashboardDisplay,
    ),
//
//    BottomNavigationItem(
//        text = "Settings",
//        icon = Res.drawable.ic_settings,
//        selectedIcon = Res.drawable.ic_settings,
//        route = Route.Login
//    ),
)