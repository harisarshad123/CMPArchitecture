package org.cmparchitecture.presentation.signup.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun SignUpText(onSigninClick: () -> Unit) {
    val annotatedText = buildAnnotatedString {
        append("Are you one of us? Let's ")
        append(" ")

        pushStringAnnotation(
            tag = "SIGN_IN",
            annotation = "sign in"
        )
        withStyle(
            style = SpanStyle(
                color = Color(0xFFFF6B5F),
                fontWeight = FontWeight.Bold
            )
        ) {
            append("Sign in")
        }
        pop()
    }

    ClickableText(
        text = annotatedText,
        onClick = { offset ->
            annotatedText.getStringAnnotations(tag = "SIGN_IN", start = offset, end = offset)
                .firstOrNull()?.let {
                    onSigninClick()
                }
        },
        style = TextStyle(
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        ),
        modifier = Modifier.fillMaxWidth()
    )
}