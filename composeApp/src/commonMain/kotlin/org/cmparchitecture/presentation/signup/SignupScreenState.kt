package org.cmparchitecture.presentation.signup

import androidx.compose.ui.text.input.TextFieldValue

data class SignupScreenState(
    val title: String = "Sign Up Screen",
    val email: TextFieldValue = TextFieldValue(),
    val password: TextFieldValue = TextFieldValue(),
    val cPassword: TextFieldValue = TextFieldValue(),
    var passwordVisible: Boolean = false,
    var cPasswordVisible: Boolean = false,
    val signUp: String = "Sign Up"
)
