package org.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import org.cmparchitecture.navigation.SetUpNavGraph
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        KoinContext {
            SetUpNavGraph(
                navController = navController
            )
        }
    }

}