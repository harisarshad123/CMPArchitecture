package org.cmparchitecture.presentation.signin

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

class SignInViewModel(private val repo: AuthRepo) : BaseViewModel() {

    var state by mutableStateOf(SignInScreenState())
        private set

    fun actionEvent(actionEvent: ActionEvent) {
        when (actionEvent) {
            is ActionEvent.OnEmailChange -> {
                state = state.copy(email = actionEvent.email)
            }

            is ActionEvent.OnPasswordChange -> {
                state = state.copy(password = actionEvent.password)
            }

            is ActionEvent.TogglePasswordVisibility -> {
                state = state.copy(passwordVisible = !state.passwordVisible)
            }

            is ActionEvent.OnSignInClick -> signIn()
            is ActionEvent.OnSignUpClick -> {
                navigate(NavigationAction.NavigateTo(Route.SignUpDisplay))
            }
        }
    }

    private fun signIn() {
        if (state.email.text.isNotEmpty() && state.password.text.isNotEmpty()) {
            viewModelScope.launch {
                val isSuccess = repo.signInWithEmail(state.email.text, state.password.text)
                if (isSuccess) {
                    navigate(
                        NavigationAction.NavigateTo(
                            Route.WelcomeDisplay,
                            clearBackStack = true
                        )
                    )
                    showToast("Signed in successfully")
                } else {
                    showToast("Invalid email or password")
                }
            }
        } else {
            showToast("Email and password cannot be empty")
        }
    }

    sealed class ActionEvent {
        data class OnEmailChange(val email: TextFieldValue) : ActionEvent()
        data class OnPasswordChange(val password: TextFieldValue) : ActionEvent()
        data object TogglePasswordVisibility : ActionEvent()
        data object OnSignInClick : ActionEvent()
        data object OnSignUpClick : ActionEvent()
    }
}