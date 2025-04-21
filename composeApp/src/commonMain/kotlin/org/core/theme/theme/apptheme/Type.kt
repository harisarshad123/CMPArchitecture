package org.core.theme.theme.apptheme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import org.core.theme.extensions.textSize

private val parentStyle = TextStyle()

@Composable
fun interBoldStyle() = parentStyle.copy(fontFamily = interFontFamily())

@Composable
fun CustomTypography() = MyTypography(
    typography = Typography(),

    bold36 = interBoldStyle().copy(fontSize = 36.textSize),
    bold28 = interBoldStyle().copy(fontSize = 28.textSize),
    bold = interBoldStyle().copy(fontWeight = FontWeight.Bold),

    regular26 = interBoldStyle().copy(fontSize = 26.textSize),
    regular24 = interBoldStyle().copy(fontSize = 24.textSize),
    regular = interBoldStyle().copy(fontWeight = FontWeight.Normal),

    medium32 = interBoldStyle().copy(fontSize = 32.textSize),
    medium20 = interBoldStyle().copy(fontSize = 20.textSize),
    medium = interBoldStyle().copy(fontWeight = FontWeight.Medium),

    semiBold28 = interBoldStyle().copy(fontSize = 28.textSize),
    semiBold = interBoldStyle().copy(fontWeight = FontWeight.SemiBold)
)