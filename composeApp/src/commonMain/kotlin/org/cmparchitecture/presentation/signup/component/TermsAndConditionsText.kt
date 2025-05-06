package org.cmparchitecture.presentation.signup.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

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