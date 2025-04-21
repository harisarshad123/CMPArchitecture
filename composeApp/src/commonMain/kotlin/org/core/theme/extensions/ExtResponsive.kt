package org.core.theme.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.core.theme.config.ThemeConfig
import kotlin.math.sqrt


fun findPercentage(of: Int, total: Int): Int {
    return ((of.toFloat() / 100f) * total.toFloat()).toInt()
}

fun percentOf(of: Int, total: Int): Int {
    return ((of.toFloat() / total.toFloat()) * 100f).toInt()
}

private const val DESIGN_WIDTH = 412F
private const val DESIGN_HEIGHT = 917F
private const val DESIGN_DIAGONAL = 1010.82

var screenWidth = 420
var screenHeight = 720

//Horizontal
//@SuppressLint("NewApi")
@Composable
fun Int.hdp(): Dp {
    val (screenWidth, screenHeight) = getScreenSize()
    return (this * screenWidth / DESIGN_WIDTH).dp
}

//Vertical
//@SuppressLint("NewApi")
@Composable
fun Int.vdp(): Dp {
    val (screenWidthDp, screenHeight) = getScreenSize()
    return (this * screenHeight / DESIGN_HEIGHT).dp
}

val Int.sdpC: Dp
    get() {
        if (ThemeConfig.IS_RESPONSIVE.not()) return this.dp
        if (screenHeight > 700) return this.dp

        val diagonalSize =
            sqrt((screenWidth.toDouble() * screenWidth) + (screenHeight * screenHeight))

        val percentage =
            this / DESIGN_DIAGONAL * 100.0 // 795.2 is the diagonal size of design screen

        return (diagonalSize * percentage / 100.0).dp
    }

val Int.textSize: TextUnit
    get() {
        if (ThemeConfig.IS_RESPONSIVE.not()) return this.sp

        if (screenHeight > 700) return this.sp

        val diagonalSize =
            sqrt((screenWidth.toDouble() * screenWidth) + (screenHeight * screenHeight))

        val percentage =
            this / DESIGN_DIAGONAL * 100.0 // 795.2 is the diagonal size of design screen

        return (diagonalSize * percentage / 100.0).sp
    }

//@SuppressLint("NewApi")
@Composable
private fun getScreenSize(): Pair<Int, Int> {
    return Pair(screenWidth, screenHeight)
}