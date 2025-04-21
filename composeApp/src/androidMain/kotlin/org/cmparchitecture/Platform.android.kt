package org.cmparchitecture

import android.content.Context
import android.os.Build

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()
actual fun showToast(message: String) {
}

lateinit var appContext: Context
fun initContext(context: Context) {
    appContext = context
}