package org.cmparchitecture.utils.extensions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.cmparchitecture.utils.sdp

@Composable
fun Modifier.safeDrawingPaddingTop(): Modifier {
    return this.padding(
        WindowInsets.safeDrawing
            .only(WindowInsetsSides.Top)
            .asPaddingValues()
    )
}


@Composable
fun Modifier.hideKeyboardOnTap(): Modifier {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    return this.pointerInput(Unit) {
        detectTapGestures(
            onTap = {
                keyboardController?.hide()
                focusManager.clearFocus(true)
            }
        )
    }
}

@Composable
fun Modifier.singleClick(
    delayMillis: Long = 700,
    enabled: Boolean = true,
    onClick: () -> Unit,
): Modifier {
    var isClickEnabled by remember(enabled) { mutableStateOf(enabled) }
    val scope = rememberCoroutineScope()

    return this.then(
        Modifier.clickable(enabled = isClickEnabled) {
            if (isClickEnabled) {
                isClickEnabled = false
                onClick()
                scope.launch {
                    delay(delayMillis)
                    isClickEnabled = true
                }
            }
        }
    )
}

@Composable
fun Modifier.takeCareOfMaxWidth()= this.widthIn(max = 402.sdp)

