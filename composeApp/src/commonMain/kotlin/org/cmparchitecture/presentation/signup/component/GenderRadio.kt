package org.cmparchitecture.presentation.signup.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun GenderRadio(option: String, selected: String, onSelected: (String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onSelected(option) }
    ) {
        RadioButton(selected = selected == option, onClick = { onSelected(option) })
        Text(option)
    }
}