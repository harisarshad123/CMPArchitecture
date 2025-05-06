package org.cmparchitecture.presentation.welcomesreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import org.cmparchitecture.navigation.NavigationAction
import org.cmparchitecture.presentation.component.BaseButton
import org.cmparchitecture.utils.sdp
import org.core.presentation.base.BaseScreen
import org.core.presentation.base.BaseViewModel

@Composable
fun WelcomeScreen(
    navigation: (NavigationAction) -> Unit = {},
    state: WelcomeScreenState = WelcomeScreenState(),
    actionEvent: (WelcomeScreenViewModel.ActionEvent) -> Unit = {},
    baseUIEvents: SharedFlow<BaseViewModel.BaseViewModelEvents> = MutableSharedFlow()
) {
    BaseScreen(
        baseUIEvents = baseUIEvents,
        navigation = navigation
    ){
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = state.title,
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }

            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.sdp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                BaseButton(
                    text = "Sign Out",
                    onClick = {
                        actionEvent.invoke(WelcomeScreenViewModel.ActionEvent.OnSignOutClick)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enable = true,
                    backgroundColor = Color.Blue,
                    textColor = Color.White,
                )
            }
        }
    }

}