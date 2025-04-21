package org.core.theme.theme.apptheme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import org.core.theme.theme.color.DarkColorPalette
import org.core.theme.theme.color.LightColorPalette
import org.core.theme.theme.color.MyColors

private val LocalColors = staticCompositionLocalOf { LightColorPalette }
private val LocalShapes = staticCompositionLocalOf { CustomShapes }
private var isDarkTheme = false

@Composable
fun GoldMangTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    isDarkTheme = darkTheme
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val myTypography = CustomTypography()
    val myShapes = LocalShapes.current

    CompositionLocalProvider(
        LocalColors provides colors,
        LocalShapes provides myShapes,
    ) {
        MaterialTheme(
            typography = myTypography.typography,
            shapes = myShapes.shapes,
            content = content
        )
    }
}

val GoldMangColor: MyColors = if (isDarkTheme) DarkColorPalette else LightColorPalette

val GoldMangTypography: MyTypography
    @Composable
    get() = CustomTypography()

val GoldMangShape: MyShapes
    @Composable
    @ReadOnlyComposable
    get() = LocalShapes.current
