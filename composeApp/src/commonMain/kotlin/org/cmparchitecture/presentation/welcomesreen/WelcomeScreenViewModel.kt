package org.cmparchitecture.presentation.welcomesreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.cmparchitecture.data.repository.AuthRepo
import org.cmparchitecture.navigation.NavigationAction
import org.cmparchitecture.navigation.Route
import org.core.presentation.base.BaseViewModel

class WelcomeScreenViewModel(private val repo: AuthRepo) : BaseViewModel() {

    var state by mutableStateOf(WelcomeScreenState())
        private set

    fun actionEvent(event: ActionEvent) {
        when (event) {
            is ActionEvent.OnSignOutClick -> {
                viewModelScope.launch {
                    repo.signOut()
                    navigate(NavigationAction.NavigateTo(Route.SignIn, clearBackStack = true))
                    showToast("Signed out successfully")
                }
            }
        }
    }

    sealed class ActionEvent {
        data object OnSignOutClick : ActionEvent()
    }
}