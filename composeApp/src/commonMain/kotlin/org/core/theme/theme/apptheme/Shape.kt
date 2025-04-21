package org.core.theme.theme.apptheme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import org.core.theme.extensions.sdpC

data class MyShapes(
    val shapes: Shapes,
    val bottomSheet20: CornerBasedShape,
    val bottomSheet24: RoundedCornerShape,
    val bottomSheet42: RoundedCornerShape,
    val round20: RoundedCornerShape,
    val round18: RoundedCornerShape,
    val round16: RoundedCornerShape,
    val round12: RoundedCornerShape,
    val round10: RoundedCornerShape,
    val round8: RoundedCornerShape,
    val round50: RoundedCornerShape,
)

val CustomShapes = MyShapes(
    shapes = Shapes(
        small = RoundedCornerShape(4.sdpC),
        medium = RoundedCornerShape(4.sdpC),
        large = RoundedCornerShape(0.sdpC)
    ),
    bottomSheet20 = RoundedCornerShape(topStart = 20.sdpC, topEnd = 20.sdpC),
    bottomSheet24 = RoundedCornerShape(topStart = 24.sdpC, topEnd = 24.sdpC),
    bottomSheet42 = RoundedCornerShape(topStart = 42.sdpC, topEnd = 42.sdpC),
    round20 = RoundedCornerShape(20.sdpC),
    round18 = RoundedCornerShape(18.sdpC),
    round12 = RoundedCornerShape(12.sdpC),
    round10 = RoundedCornerShape(10.sdpC),
    round8 = RoundedCornerShape(8.sdpC),
    round50 = RoundedCornerShape(50),
    round16 = RoundedCornerShape(16)
)