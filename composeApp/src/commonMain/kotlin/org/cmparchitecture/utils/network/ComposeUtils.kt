package org.cmparchitecture.utils.network

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import org.core.theme.theme.apptheme.GoldMangColor

@Composable
fun VerticalSpace(value: Dp) {
    Spacer(modifier = Modifier.height(value))
}

@Composable
fun HorizontalSpace(value: Dp) {
    Spacer(modifier = Modifier.width(value))
}

@Composable
fun getStatusBarHeight(density: Density = LocalDensity.current): Int {
    return WindowInsets.statusBars.getTop(density)
}

@Composable
fun getNavigationBarHeight(density: Density = LocalDensity.current): Int {
    return WindowInsets.navigationBars.getTop(density)
}

@Composable
fun getBottomInsets(): Dp {
    return WindowInsets.safeDrawing
        .only(WindowInsetsSides.Bottom)
        .asPaddingValues().calculateBottomPadding()
}

@Composable
fun getTopInsets(density: Density = LocalDensity.current): Dp {
    return WindowInsets.safeDrawing
        .only(WindowInsetsSides.Top)
        .asPaddingValues().calculateTopPadding()
}


@Composable
fun getOutLineTextFieldColor() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = Color.Green,
    unfocusedBorderColor = GoldMangColor.black,
    unfocusedLabelColor = GoldMangColor.black,
    focusedLabelColor = Color.Green,
    focusedTextColor = GoldMangColor.black,
    unfocusedTextColor = GoldMangColor.black,
)


@Composable
fun getDoneButtonColors() = ButtonDefaults.buttonColors(
    containerColor = Color.Blue,
    contentColor = Color.White,
    disabledContainerColor = Color.Gray,
    disabledContentColor = Color.LightGray
)

@Composable
fun isEditMode() = LocalInspectionMode.current