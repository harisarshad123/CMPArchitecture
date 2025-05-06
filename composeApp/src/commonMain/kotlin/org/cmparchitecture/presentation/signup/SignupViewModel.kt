package org.cmparchitecture.presentation.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.cmparchitecture.data.repository.AuthRepo
import org.cmparchitecture.navigation.NavigationAction
import org.cmparchitecture.navigation.Route
import org.core.presentation.base.BaseViewModel

class SignupViewModel(private val repo: AuthRepo) : BaseViewModel() {
    var state by mutableStateOf(SignupScreenState())
        private set


    fun actionEvent(actionEvent: ActionEvent) {
        when (actionEvent) {
            is ActionEvent.OnEmailChange -> {
                state = state.copy(email = actionEvent.email)
            }

            is ActionEvent.OnPasswordChange -> {
                state = state.copy(password = actionEvent.password)
            }

            is ActionEvent.OnCPasswordChange -> {
                state = state.copy(cPassword = actionEvent.cPassword)
            }

            is ActionEvent.TogglePasswordVisibility -> {
                state = state.copy(passwordVisible = !state.passwordVisible)
            }

            is ActionEvent.ToggleCPasswordVisibility -> {
                state = state.copy(cPasswordVisible = !state.cPasswordVisible)
            }

            is ActionEvent.OnSignUpClick -> {
                signUp()
            }

            is ActionEvent.OnAlreadyAccountClick -> {
                navigate(NavigationAction.PopBackStack)
            }
        }
    }

    private fun signUp() {
        if (
            state.email.text.isNotEmpty() &&
            state.password.text.isNotEmpty() &&
            state.cPassword.text.isNotEmpty() &&
            state.password.text == state.cPassword.text
        ) {
            viewModelScope.launch {
                val isSuccess = repo.signupWithEmail(state.email.text, state.password.text)
                if (isSuccess) {
                    navigate(
                        NavigationAction.NavigateTo(
                            Route.WelcomeDisplay,
                            clearBackStack = true
                        )
                    )
                    showToast("Sign Up Successful")
                } else {
                    showToast("Sign Up Failed")
                }
            }
        } else {
            showToast("Sign Up Failed")
        }
    }

    sealed class ActionEvent {
        data class OnEmailChange(val email: TextFieldValue) : ActionEvent()
        data class OnPasswordChange(val password: TextFieldValue) : ActionEvent()
        data class OnCPasswordChange(val cPassword: TextFieldValue) : ActionEvent()
        data object TogglePasswordVisibility : ActionEvent()
        data object ToggleCPasswordVisibility : ActionEvent()
        data object OnSignUpClick : ActionEvent()
        data object OnAlreadyAccountClick : ActionEvent()
    }
}