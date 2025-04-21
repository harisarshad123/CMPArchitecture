package org.cmparchitecture.utils.extensions

import androidx.compose.ui.text.intl.Locale

fun String.isAndroid() = this.contains("android", ignoreCase = true)
fun String.isiOS() = this.contains("ios", ignoreCase = true)
fun String.isDesktop() = this.contains("desktop", ignoreCase = true)

fun String.takeInitials(): String {
    return this.split(" ")
        .take(2)
        .mapNotNull { it.firstOrNull()?.uppercase() }
        .joinToString("")
}

fun String.isValidEmail(): Boolean {
    return Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$").matches(this)
}


fun String.toTitleCase(): String =
    lowercase()
        .split(" ")
        .joinToString(" ") { it.replaceFirstChar { ch -> ch.uppercase() } }