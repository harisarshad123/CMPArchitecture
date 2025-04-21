package org.core.presentation.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import org.cmparchitecture.navigation.NavigationAction
import org.cmparchitecture.presentation.component.MessageBottomSheet
import org.cmparchitecture.showToast
import org.cmparchitecture.utils.extensions.safeDrawingPaddingTop
import org.core.theme.theme.apptheme.GoldMangColor
import org.core.theme.utils.sdp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun BaseScreen(
    modifier: Modifier = Modifier,
    baseUIEvents: SharedFlow<BaseViewModel.BaseViewModelEvents>,
    backGroundImg: DrawableResource? = null,
    bgColor: Color? = null,
    safePadding: Boolean = true,
    navigation: (NavigationAction) -> Unit = {},
    content: @Composable BoxScope.() -> Unit
) {
    var isLoading by remember { mutableStateOf(false) }
    var showAlertBottomSheet by remember { mutableStateOf(false) }
    var alertMsg by remember { mutableStateOf("") }

    backGroundImg?.let {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(backGroundImg),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = bgColor ?: Color.Transparent)
            .then(if (safePadding) Modifier.safeDrawingPaddingTop() else Modifier)
    ) {
        content()

        if (showAlertBottomSheet) {
            MessageBottomSheet(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Info, // Change to Icons.Default.Error for error
                        contentDescription = "Success",
                        tint = Color.Black,
                        modifier = Modifier.size(50.sdp)
                    )
                },
                title = "Alert",
                message = alertMsg,
                onDismiss = {
                    showAlertBottomSheet = false
                }
            )
        }

        if (isLoading) {
            IndeterminateCircularIndicator(
                modifier = Modifier.size(40.sdp).align(Alignment.Center)
            )
        }
    }



    LaunchedEffect(Unit) {

        baseUIEvents.collectLatest { event ->
            when (event) {
                is BaseViewModel.BaseViewModelEvents.ShowError -> {
                    //design ur ui
                    showAlertBottomSheet = true
                    alertMsg = event.msg

                }

                is BaseViewModel.BaseViewModelEvents.ShowToast -> {
                    showToast(event.msg)
                    showAlertBottomSheet = true
                    alertMsg = event.msg
                }

                is BaseViewModel.BaseViewModelEvents.ShowLoader -> {
                    isLoading = event.show
                }

                is BaseViewModel.BaseViewModelEvents.Navigate -> {
                    navigation(event.route)
                }
            }
        }
    }
}

@Composable
fun IndeterminateCircularIndicator(
    modifier: Modifier = Modifier,
) {
    CircularProgressIndicator(
        modifier = modifier.width(50.sdp),
        color = GoldMangColor.black,
        trackColor = GoldMangColor.white,
    )
}
