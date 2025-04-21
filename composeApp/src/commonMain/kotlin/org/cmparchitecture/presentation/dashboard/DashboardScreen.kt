package org.cmparchitecture.presentation.dashboard

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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cmparchitecture.composeapp.generated.resources.Res
import cmparchitecture.composeapp.generated.resources.ic_facebook
import cmparchitecture.composeapp.generated.resources.ic_google
import cmparchitecture.composeapp.generated.resources.icons8_apple
import org.jetbrains.compose.resources.painterResource

@Composable
fun DashboardScreen(
    state: DashboardScreenState = DashboardScreenState()
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("Male") }

    val scrollState = rememberScrollState()

    val focusRequester1 = remember { FocusRequester() }
    val focusRequester2 = remember { FocusRequester() }
    val focusRequester3 = remember { FocusRequester() }

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

        Text("Sign up", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.padding(top = 10.dp))
        Text("1M+ people choose us. Join now!", fontSize = 14.sp, color = Color.Gray)

        Spacer(Modifier.height(16.dp))
        Text("Or, you can sign up by:", color = Color.Gray)

        Spacer(Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            SocialButton(icon = painterResource(Res.drawable.ic_google), "G")  // Google
            SocialButton(icon = painterResource(Res.drawable.ic_facebook), "F")  // Facebook
            SocialButton(icon = painterResource(Res.drawable.icons8_apple), "A")  // Apple
        }

        Spacer(Modifier.height(24.dp))
        CustomInputField(
            label = "First name",
            value = firstName,
            onValueChange = { firstName = it },
            focusRequester = focusRequester1,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = { focusRequester2.requestFocus() } // Move focus to next field on "Next"
            )
        )

        Spacer(Modifier.height(12.dp))
        CustomInputField(
            label = "Last name",
            value = lastName,
            onValueChange = { lastName = it },
            focusRequester = focusRequester2,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = { focusRequester3.requestFocus() }
            )
        )

        Spacer(Modifier.height(12.dp))
        CustomInputField(
            label = "Date of birth",
            value = dob,
            onValueChange = { dob = it },
            focusRequester = focusRequester3,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    submitForm(firstName, lastName)
                }
            )
        )

        Spacer(Modifier.height(12.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            GenderRadio("Male", gender) { gender = it }
            Spacer(Modifier.width(16.dp))
            GenderRadio("Female", gender) { gender = it }
        }

        Spacer(Modifier.height(10.dp))
        Button(
            onClick = { /* Handle next */ },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6B5F))
        ) {
            Text("Next step", color = Color.White, fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(20.dp))
        TermsAndConditionsText()

        Spacer(Modifier.height(5.dp))
        SignInText()
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
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    focusRequester: FocusRequester? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth()
            .focusRequester(focusRequester ?: FocusRequester.Default),
        singleLine = true,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions
    )
}

@Composable
fun GenderRadio(option: String, selected: String, onSelected: (String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onSelected(option) }
    ) {
        RadioButton(selected = selected == option, onClick = { onSelected(option) })
        Text(option)
    }
}

@Composable
fun TermsAndConditionsText() {
    Text(
        buildAnnotatedString {
            append("By signing up, you've agree to the ")
            withStyle(SpanStyle(color = Color(0xFFFF6B5F), fontWeight = FontWeight.Bold)) {
                append("Terms & conditions")
            }
            append(" and ")
            withStyle(SpanStyle(color = Color(0xFFFF6B5F), fontWeight = FontWeight.Bold)) {
                append("Privacy policy")
            }
        },
        fontSize = 12.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
fun SignInText() {
    Text(
        buildAnnotatedString {
            append("Are you one of us? Let's ")
            withStyle(SpanStyle(color = Color(0xFFFF6B5F), fontWeight = FontWeight.Bold)) {
                append("Sign in")
            }
        },
        fontSize = 12.sp,
        textAlign = TextAlign.Center
    )
}

fun submitForm(firstName: String, lastName: String) {
    println("Form submitted with First Name: $firstName and Last Name: $lastName")
}