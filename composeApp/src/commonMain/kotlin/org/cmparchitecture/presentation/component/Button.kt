package org.cmparchitecture.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.cmparchitecture.utils.extensions.singleClick
import org.core.theme.theme.apptheme.GoldMangColor
import org.core.theme.theme.apptheme.GoldMangTypography
import org.core.theme.utils.sdp
import org.core.theme.utils.ssp

@Composable
fun DoneButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    buttonColors: ButtonColors,
    text: String,
    textColor: Color,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(51.sdp)
            .clip(RoundedCornerShape(50.dp)),
        colors = buttonColors,
        enabled = isEnabled,
        onClick = onClick,
    ) {
        Text(
            text = text,
            style = GoldMangTypography.semiBold.copy(fontSize = 16.ssp, color = textColor)
        )
    }
}


@Composable
fun CustomTextButton(
    isEnabled: Boolean = true,
    modifier: Modifier = Modifier,
    text: String,
    containerColor: Color = if (isEnabled) GoldMangColor.pDark else GoldMangColor.nLighter1,
    textColor: Color = GoldMangColor.white,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.sdp)
            .clip(RoundedCornerShape(12.sdp))
            .background(containerColor)
            .singleClick(enabled = isEnabled) {
                onClick()
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text,
            style = GoldMangTypography.medium.copy(
                color = textColor,
                lineHeight = 18.ssp
            )
        )
    }
}