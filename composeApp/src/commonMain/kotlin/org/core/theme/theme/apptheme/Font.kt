package org.core.theme.theme.apptheme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import cmparchitecture.composeapp.generated.resources.Res
import cmparchitecture.composeapp.generated.resources.interfont
import org.jetbrains.compose.resources.Font

@Composable
fun interFontFamily() = FontFamily(
    Font(Res.font.interfont, weight = FontWeight.Light),
    Font(Res.font.interfont, weight = FontWeight.Normal),
    Font(Res.font.interfont, weight = FontWeight.Medium),
    Font(Res.font.interfont, weight = FontWeight.SemiBold),
    Font(Res.font.interfont, weight = FontWeight.Bold)
)