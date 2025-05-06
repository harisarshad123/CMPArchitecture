package org.cmparchitecture.utils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import org.cmparchitecture.utils.extensions.screenHeight
import org.cmparchitecture.utils.extensions.screenWidth

val Int.sdp: Dp
    @Composable
    get() = this.sdpGet()

val Int.ssp: TextUnit
    @Composable get() = this.sspGet()

@Composable
fun Int.sdpGet(): Dp {
    val minValue = kotlin.math.min(getScreenHeight(), getScreenWidth())
    val ratio = minValue / 412.0
    return (this * ratio).dp
}

@Composable
fun Int.sspGet(): TextUnit {
    return this.textSdp(density = LocalDensity.current)
}

@Composable
private fun Int.textSdp(density: Density): TextUnit = with(density) {
    this@textSdp.sdp.toSp()
}

@Composable
fun getScreenWidth(): Float {

    return screenWidth.toFloat()
}

@Composable
fun getScreenHeight(): Float {
    return screenHeight.toFloat()
}

var mainScaffoldPadding: PaddingValues = PaddingValues()


@Composable
fun Int.pxToDp(): Dp {
    val density = LocalDensity.current
    return with(density) { this@pxToDp.toDp() }
}

@Composable
fun Int.pxToSdp(): Dp {
    return this.pxToDp().value.toInt().sdp
}