package org.cmparchitecture.utils.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import org.cmparchitecture.utils.network.getBottomInsets
import org.cmparchitecture.utils.network.getTopInsets

@Composable
fun Dp.addTopInset(): Dp {
    return this + getTopInsets()
}

@Composable
fun Dp.addBottomInset(): Dp {
    return this + getBottomInsets()
}

//fun Long?.toFormattedDateTime(): Pair<String, String> {
//    if (this == null) return Pair("", "") // Handle null case
//
//    // Convert milliseconds to LocalDateTime
//    val dateTime = Clock.System.now()
//        .toInstant(TimeZone.currentSystemDefault())
//        .plus(this, DateTimeUnit.MILLISECOND, TimeZone.currentSystemDefault())
//        .toLocalDateTime(TimeZone.currentSystemDefault())
//
//    // Format date as "01 Jan 1997"
//    val formattedDate = "${dateTime.date.dayOfMonth.toString().padStart(2, '0')} " +
//            "${dateTime.date.month.name.lowercase().replaceFirstChar { it.uppercase() }} " +
//            "${dateTime.date.year}"
//
//    // Format time as "03:45 PM"
//    val hour = if (dateTime.time.hour % 12 == 0) 12 else dateTime.time.hour % 12
//    val amPm = if (dateTime.time.hour < 12) "AM" else "PM"
//    val formattedTime = String.format("%02d:%02d %s", hour, dateTime.time.minute, amPm)
//
//    return Pair(formattedDate, formattedTime)
//}