package org.cmparchitecture.presentation.splash

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import org.cmparchitecture.navigation.NavigationAction
import org.cmparchitecture.navigation.Route
import org.cmparchitecture.utils.PlatformUtils
import org.core.presentation.base.BaseViewModel

class SplashViewModel : BaseViewModel() {

    var state by mutableStateOf(SplashScreenState())
        private set

    private val currentUser = if (PlatformUtils.isAndroid()) Firebase.auth.currentUser else null
    private val startPoint = if (currentUser != null) Route.WelcomeDisplay else Route.SignIn

    fun actionEvent(actionEvent: ActionEvent) {
        when (actionEvent) {
            is ActionEvent.OnSplashFinished -> {
                navigate(NavigationAction.NavigateTo(startPoint, clearBackStack = true))
            }
        }
    }

    sealed class ActionEvent {
        data object OnSplashFinished : ActionEvent()
    }
}