package org.app

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.cmparchitecture.utils.bottomnav.handleBottomNavAndFabVisibility
import org.cmparchitecture.navigation.Route
import org.cmparchitecture.navigation.SetUpNavGraph
import org.cmparchitecture.utils.bottomnav.bottomNavigationItems
import org.cmparchitecture.utils.extensions.defaultHorizontalPadding
import org.core.presentation.viewmodel.MainViewModel
import org.core.theme.utils.sdp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.mp.KoinPlatform.getKoin

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
//        val mainViewModel = koinViewModel<MainViewModel>()
        val mainViewModel: MainViewModel = getKoin().get()

        // Observe navigation changes
        val currentBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = currentBackStackEntry?.destination?.route

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                if (mainViewModel.state.isBottomNavVisible) {
                    BottomNavigationView(
                        onClick = { route ->
                            navController.navigate(route)
                        }
                    )
                }
            },
            floatingActionButton = {
                AnimatedVisibility(
                    visible = mainViewModel.state.isFabBtnVisible,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    CustomFloatingActionButton(
                        expandable = true,
                        fabIcon = Icons.Filled.Add,
                        navController = navController,
                        onFabClick = {}
                    )
                }

            },
            floatingActionButtonPosition = FabPosition.End
        )
        {
            val bottomPadding = it.calculateBottomPadding()

            Box(
                modifier = if (mainViewModel.state.isBottomNavVisible)
                    Modifier.padding(bottom = bottomPadding)
                else Modifier
            ) {
                SetUpNavGraph(
                    navController = navController
                )
            }
        }

        LaunchedEffect(currentRoute) {
            handleBottomNavAndFabVisibility(currentRoute, mainViewModel)
        }
    }

}

@Composable
private fun BottomNavigationView(
    onClick: (Route) -> Unit
) {
    var selectedItem by remember { mutableIntStateOf(0) }
    NavigationBar {
        bottomNavigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        if (selectedItem == index)
                            painterResource(item.selectedIcon)
                        else painterResource(
                            item.icon
                        ),
                        contentDescription = item.text
                    )
                },
                label = { Text(item.text) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    onClick(item.route)
                }
            )
        }
    }
}

@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(
        viewModelStoreOwner = parentEntry
    )
}

// Custom fab that allows for displaying extended content
@Composable
fun CustomFloatingActionButton(
    expandable: Boolean,
    fabIcon: ImageVector,
    onFabClick: () -> Unit,
    navController: NavHostController,
) {

    val interactionSource = remember { MutableInteractionSource() }
    var isExpanded by remember { mutableStateOf(false) }
    if (!expandable) { // Close the expanded fab if you change to non expandable nav destination
        isExpanded = false
    }

    val fabSize = 64.dp
    val expandedFabWidth by animateDpAsState(
        targetValue = if (isExpanded) 200.dp else fabSize,
        animationSpec = spring(dampingRatio = 3f)
    )
    val expandedFabHeight by animateDpAsState(
        targetValue = if (isExpanded) 58.dp else fabSize,
        animationSpec = spring(dampingRatio = 3f)
    )

    Column {

        // ExpandedBox over the FAB
        Box(
            modifier = Modifier
                .offset(y = (25).dp)
                .size(
                    width = expandedFabWidth,
                    height = (animateDpAsState(
                        if (isExpanded) 225.dp else 0.dp,
                        animationSpec = spring(dampingRatio = 4f)
                    )).value
                )
                .background(
                    MaterialTheme.colorScheme.surfaceContainer,
                    shape = RoundedCornerShape(18.dp)
                )
        ) {
            // Customize the content of the expanded box as needed

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .defaultHorizontalPadding()
                    .padding(top = 15.sdp),
                verticalArrangement = Arrangement.spacedBy(8.sdp)
            ) {
                AssistChip(
                    modifier = Modifier.height(40.sdp).fillMaxWidth(),
                    onClick = {
//                        navController.navigate(Route.AddTransaction(type = TransactionType.INCOMING.name))
                    },
                    border = AssistChipDefaults.assistChipBorder(
                        enabled = false
                    ),
                    label = { Text("Incoming") },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Add,
                            contentDescription = "Localized description",
                            Modifier.size(AssistChipDefaults.IconSize)
                        )
                    }
                )
                AssistChip(
                    modifier = Modifier.height(40.sdp).fillMaxWidth(),
                    onClick = {},
                    border = AssistChipDefaults.assistChipBorder(
                        enabled = false
                    ),
                    label = { Text("Outgoing") },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Add,
                            contentDescription = "Localized description",
                            Modifier.size(AssistChipDefaults.IconSize)
                        )
                    }
                )
            }

        }

        FloatingActionButton(
            onClick = {
                onFabClick()
                if (expandable) {
                    isExpanded = !isExpanded
                }
            },
            modifier = Modifier
                .align(Alignment.End)
                .width(expandedFabWidth)
                .height(expandedFabHeight),
            shape = RoundedCornerShape(18.sdp),
            interactionSource = interactionSource,
            elevation = FloatingActionButtonDefaults.elevation(0.dp),

            ) {

            Icon(
                imageVector = fabIcon,
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .offset(
                        x = animateDpAsState(
                            if (isExpanded) -70.dp else 0.dp,
                            animationSpec = spring(dampingRatio = 3f)
                        ).value
                    )
            )

            Text(
                text = "Close Menu",
                softWrap = false,
                modifier = Modifier
                    .offset(
                        x = animateDpAsState(
                            if (isExpanded) 10.dp else 50.dp,
                            animationSpec = spring(dampingRatio = 3f)
                        ).value
                    )
                    .alpha(
                        animateFloatAsState(
                            targetValue = if (isExpanded) 1f else 0f,
                            animationSpec = tween(
                                durationMillis = if (isExpanded) 350 else 100,
                                delayMillis = if (isExpanded) 100 else 0,
                                easing = EaseIn
                            )
                        ).value
                    )
            )
        }
    }
}