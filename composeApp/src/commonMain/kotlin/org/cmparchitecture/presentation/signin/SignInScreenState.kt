package org.cmparchitecture.presentation.signin

import androidx.compose.ui.text.input.TextFieldValue

data class SignInScreenState(
    val title: String = "Sign In Screen",
    val email: TextFieldValue = TextFieldValue(),
    val password: TextFieldValue = TextFieldValue(),
    var passwordVisible: Boolean = false,
    val signIn: String = "Sign In"
)
