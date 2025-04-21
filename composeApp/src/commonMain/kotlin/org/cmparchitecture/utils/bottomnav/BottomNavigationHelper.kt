package goldmang.utils.bottomnav

import org.cmparchitecture.utils.bottomnav.DefaultBottomNavVisibilityHandler
import org.cmparchitecture.utils.bottomnav.DefaultFabButtonVisibilityHandler
import org.core.presentation.viewmodel.MainViewModel

fun handleBottomNavAndFabVisibility(
    currentRoute: String?,
    mainViewModel: MainViewModel,
) {
    val shouldShowNav = DefaultBottomNavVisibilityHandler().shouldShow(currentRoute)
    val shouldShowFab = DefaultFabButtonVisibilityHandler().shouldShow(currentRoute)
    mainViewModel.bottomNavBarVisibility(shouldShowNav)
    mainViewModel.bottomFabVisibility(shouldShowFab)
}