package org.core.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.core.presentation.base.BaseViewModel

class MainViewModel : BaseViewModel() {

    var state by mutableStateOf(MainViewModelState())
        private set


    fun actionEvent(event: ActionEvent) {
        when (event) {
            is ActionEvent.BottomBarVisibility -> {
                bottomNavBarVisibility(event.isVisible)
            }

            is ActionEvent.OnFabButtonClick -> {
//                navigate(NavigationAction.NavigateTo(Route.AddTransaction))
            }

            is ActionEvent.BottomFabVisibility -> {
                bottomFabVisibility(event.isVisible)
            }
        }
    }

    fun bottomNavBarVisibility(value: Boolean) {
        state = state.copy(isBottomNavVisible = value)
    }

    fun bottomFabVisibility(value: Boolean) {
        state = state.copy(isFabBtnVisible = value)
    }

    sealed class ActionEvent {
        data class BottomBarVisibility(val isVisible: Boolean) : ActionEvent()
        data class BottomFabVisibility(val isVisible: Boolean) : ActionEvent()
        data object OnFabButtonClick : ActionEvent()
    }
}