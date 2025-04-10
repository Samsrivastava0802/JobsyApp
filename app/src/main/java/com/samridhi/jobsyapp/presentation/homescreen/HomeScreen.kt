package com.samridhi.jobsyapp.presentation.homescreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.samridhi.jobsyapp.components.BottomNavigation
import com.samridhi.jobsyapp.navigation.AppHomeFlow
import com.samridhi.jobsyapp.navigation.AppHomeNavigationGraph
import com.samridhi.jobsyapp.navigation.HomeScreenActions

@Composable
fun HomeScreen(
    onAction: (HomeScreenActions) -> Unit
) {
    val bottomNavController = rememberNavController()
    val currentRoute = bottomNavController.currentBackStackEntryAsState().value?.destination?.route
        ?: AppHomeFlow.JobScreen.route
    Scaffold(
        bottomBar = {
            BottomNavigation(
                route = currentRoute,
                onClick = {
                    if (currentRoute != it) {
                        bottomNavController.navigate(it) {
                            popUpTo(bottomNavController.graph.startDestinationId)
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        MainScreenContent(
            modifier = Modifier.padding(innerPadding),
            screenContent = {
                AppHomeNavigationGraph(
                    navController = bottomNavController,
                    onAction = {
                        onAction(it)
                    }
                )
            }
        )
    }
}

@Composable
fun MainScreenContent(
    modifier: Modifier = Modifier,
    screenContent: @Composable () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        screenContent()
    }
}

