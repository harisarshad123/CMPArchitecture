package org.cmparchitecture.presentation.signup.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

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