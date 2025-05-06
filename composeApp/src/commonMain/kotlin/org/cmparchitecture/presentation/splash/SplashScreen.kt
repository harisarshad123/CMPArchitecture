package org.cmparchitecture.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import cmparchitecture.composeapp.generated.resources.Res
import cmparchitecture.composeapp.generated.resources.compose_multiplatform
import cmparchitecture.composeapp.generated.resources.screen_bg
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import org.app.Greeting
import org.cmparchitecture.navigation.NavigationAction
import org.core.presentation.base.BaseViewModel
import org.core.theme.utils.sdp
import org.jetbrains.compose.resources.painterResource

@Composable
fun SplashScreen(
    state: SplashScreenState = SplashScreenState(),
    actionEvent: (SplashViewModel.ActionEvent) -> Unit = {},
    navigation: (NavigationAction) -> Unit = {},
    baseUIEvents: SharedFlow<BaseViewModel.BaseViewModelEvents> = MutableSharedFlow()
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(Res.drawable.screen_bg),
            contentDescription = "Login Screen Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val greeting = remember { Greeting().greet() }
            Image(painterResource(Res.drawable.compose_multiplatform), contentDescription = null)
            Text("Compose: $greeting")
            Spacer(modifier = Modifier.height(3.sdp))
            Text(state.title)

            LaunchedEffect(Unit) {
                delay(3000)
                actionEvent.invoke(SplashViewModel.ActionEvent.OnSplashFinished)
            }
        }
    }
}

