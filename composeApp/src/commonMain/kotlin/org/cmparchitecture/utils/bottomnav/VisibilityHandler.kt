package org.cmparchitecture.utils.bottomnav

interface VisibilityHandler {
    fun shouldShow(route: String?): Boolean
}

class DefaultBottomNavVisibilityHandler : VisibilityHandler {
    override fun shouldShow(route: String?): Boolean {
        return when (route.orEmpty().substringAfterLast(".")) {
//            Route.DashboardDisplay.toString() -> true
            else -> false
        }
    }
}

class DefaultFabButtonVisibilityHandler : VisibilityHandler {
    override fun shouldShow(route: String?): Boolean {
        return when (route.orEmpty().substringAfterLast(".")) {
//            Route.DashboardDisplay.toString() -> true
            else -> false
        }
    }
}