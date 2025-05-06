package org.cmparchitecture.presentation.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cmparchitecture.composeapp.generated.resources.Res
import cmparchitecture.composeapp.generated.resources.don_t_have_account
import cmparchitecture.composeapp.generated.resources.ic_facebook
import cmparchitecture.composeapp.generated.resources.ic_google
import cmparchitecture.composeapp.generated.resources.ic_visibility
import cmparchitecture.composeapp.generated.resources.ic_visibility_off
import cmparchitecture.composeapp.generated.resources.icons8_apple
import cmparchitecture.composeapp.generated.resources.sign_up
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import org.cmparchitecture.navigation.NavigationAction
import org.core.presentation.base.BaseViewModel
import org.core.theme.utils.sdp
import org.core.theme.utils.ssp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SignInScreen(
    state: SignInScreenState = SignInScreenState(),
    navigation: (NavigationAction) -> Unit = {},
    actionEvent: (SignInViewModel.ActionEvent) -> Unit = {},
    baseUIEvents: SharedFlow<BaseViewModel.BaseViewModelEvents> = MutableSharedFlow()
) {
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    val focusRequester1 = remember { FocusRequester() }
    val focusRequester2 = remember { FocusRequester() }

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(scrollState)
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(24.dp))

        // Logo
        Box(
            modifier = Modifier
                .size(60.dp)
                .background(Color.Blue, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text("LOGO", color = Color.White, fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(16.dp))

        Text(text = state.title, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.padding(top = 10.dp))
        Text("1M+ people choose us. Join now!", fontSize = 14.sp, color = Color.Gray)

        Spacer(Modifier.height(16.dp))
        Text("Or, you can sign in by:", color = Color.Gray)

        Spacer(Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            SocialButton(icon = painterResource(Res.drawable.ic_google), "G")  // Google
            SocialButton(
                icon = painterResource(Res.drawable.ic_facebook),
                "F"
            )  // Facebook
            SocialButton(icon = painterResource(Res.drawable.icons8_apple), "A")  // Apple
        }

        Spacer(Modifier.height(24.dp))
        CustomInputField(
            label = { Text("Email") },
            value = state.email,
            onValueChange = { actionEvent.invoke(SignInViewModel.ActionEvent.OnEmailChange(it)) },
            focusRequester = focusRequester1,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = { focusRequester2.requestFocus() } // Move focus to next field on "Next"
            ))

        Spacer(Modifier.height(12.dp))
        CustomInputField(
            label = { Text("Password") },
            value = state.password,
            onValueChange = { actionEvent.invoke(SignInViewModel.ActionEvent.OnPasswordChange(it)) },
            focusRequester = focusRequester2,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            ),
            trailingIcon = {
                IconButton(onClick = { actionEvent.invoke(SignInViewModel.ActionEvent.TogglePasswordVisibility) }) {
                    Icon(
                        painter = painterResource(
                            if (state.passwordVisible) Res.drawable.ic_visibility
                            else Res.drawable.ic_visibility_off
                        ),
                        contentDescription = if (state.passwordVisible) "Hide password" else "Show password"
                    )
                }
            }
        )

        Spacer(Modifier.height(15.dp))
        Button(
            onClick = { actionEvent.invoke(SignInViewModel.ActionEvent.OnSignInClick) },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6B5F))
        ) {
            Text("Sign In", color = Color.White, fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(10.dp))
//        SignInText { actionEvent.invoke(SignInViewModel.ActionEvent.OnSignUpClick) }
        Row {
            Text(
                text = stringResource(Res.string.don_t_have_account),
                color = Color.Black,
                fontSize = 11.ssp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.width(2.sdp))
            Text(
                modifier = Modifier.clickable {
                    actionEvent.invoke(SignInViewModel.ActionEvent.OnSignUpClick)
                },
                text = stringResource(Res.string.sign_up),
                color = Color.Blue,
                fontSize = 11.ssp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun SocialButton(
    icon: Painter,
    contentDescription: String,
    iconTint: Color = Color.Unspecified,
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .background(Color.LightGray, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = icon,
            contentDescription = contentDescription,
            tint = iconTint
        )
    }
}

@Composable
fun CustomInputField(
    label: @Composable () -> Unit,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    focusRequester: FocusRequester? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = PasswordVisualTransformation(),
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester ?: FocusRequester.Default),
        singleLine = true,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon
    )
}

@Composable
fun SignInText(onSignUpClick: () -> Unit) {
    val annotatedText = buildAnnotatedString {
        append("You don't have an account?")
        append(" ")

        pushStringAnnotation(
            tag = "SIGN_UP",
            annotation = "sign_up"
        )
        withStyle(
            style = SpanStyle(
                color = Color(0xFFFF6B5F),
                fontWeight = FontWeight.Bold
            )
        ) {
            append("Sign up")
        }
        pop()
    }

    ClickableText(
        text = annotatedText,
        onClick = { offset ->
            annotatedText.getStringAnnotations(tag = "SIGN_UP", start = offset, end = offset)
                .firstOrNull()?.let {
                    onSignUpClick()
                }
        },
        style = TextStyle(
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

