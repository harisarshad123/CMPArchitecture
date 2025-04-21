package org.cmparchitecture.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.cmparchitecture.utils.network.VerticalSpace
import org.core.theme.theme.apptheme.GoldMangColor
import org.core.theme.theme.apptheme.GoldMangTypography
import org.core.theme.utils.sdp
import org.core.theme.utils.ssp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun MessageDialog(
    icon: DrawableResource, // Resource ID of the icon
    title: String,
    message: String,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier.padding(16.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(resource = icon),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(48.dp)
                        .padding(bottom = 8.dp)
                )

                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = onDismiss) {
                    Text("OK")
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageBottomSheet(
    icon: @Composable () -> Unit,
    title: String,
    message: String,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = GoldMangColor.white
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.sdp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Icon at the top
            icon()

            VerticalSpace(10.sdp)

            // Title
            Text(
                text = title,
                style = GoldMangTypography.semiBold.copy(
                    fontSize = 28.ssp,
                    color = GoldMangColor.textBlack
                ),
            )

            VerticalSpace(8.sdp)

            // Message
            Text(
                text = message,
                style = GoldMangTypography.regular.copy(
                    fontSize = 16.ssp,
                    color = GoldMangColor.textLighter,
                    textAlign = TextAlign.Center
                ),
            )

            VerticalSpace(20.sdp)

            // OK Button
            CustomTextButton(
                containerColor = GoldMangColor.black,
                text = "Ok",
                onClick = onDismiss
            )

            VerticalSpace(20.sdp)
        }
    }
}

