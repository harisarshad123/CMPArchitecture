package org.cmparchitecture

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.app.App
import org.di.initKoin

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "CMPArchitecture",
    ) {
        App()
    }
}