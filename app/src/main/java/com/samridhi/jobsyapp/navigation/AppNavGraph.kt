package com.samridhi.jobsyapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.samridhi.jobsyapp.presentation.bookmark.BookmarksScreen
import com.samridhi.jobsyapp.presentation.homescreen.HomeScreen
import com.samridhi.jobsyapp.presentation.jobdetail.JobDetailScreen
import com.samridhi.jobsyapp.presentation.job.JobScreen


@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: String = AppScreen.OtherScreenFlow.route,
    navActions: AppNavigationActions
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        otherGraph(
            route = AppScreen.OtherScreenFlow.route,
            navActions = navActions
        )
    }
}

@Composable
fun AppHomeNavigationGraph(
    navController: NavHostController,
    onAction: (HomeScreenActions) -> Unit,
    startDestination: String = AppHomeFlow.JobScreen.route,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(AppHomeFlow.JobScreen.route) {
            JobScreen(onAction = onAction)
        }
        composable(AppHomeFlow.BookmarkScreen.route) {
            BookmarksScreen()
        }
    }
}

fun NavGraphBuilder.otherGraph(
    route: String,
    navActions: AppNavigationActions
) {
    navigation(
        startDestination = AppScreen.HomeScreen.route,
        route = route
    ) {
        composable(
            route = AppScreen.HomeScreen.route
        ) {
            HomeScreen(navActions::navigateFromHomeScreen)
        }
        composable(
            route = AppScreen.JobDetailScreen.route,
            arguments = listOf(
                navArgument(AppArgs.ARG_JOB_ID) { type = NavType.StringType }
            )
        ) {
            JobDetailScreen()
        }
    }
}