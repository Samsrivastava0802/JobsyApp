package com.samridhi.jobsyapp.navigation

import androidx.navigation.NavController
import com.samridhi.jobsyapp.util.withArg

class AppNavigationActions(
    private val navController: NavController,
    private val onFinish: () -> Unit
) {
    fun back() {
        navController.popBackStack()
    }

    private fun finishActivity() {
        onFinish()
    }

    fun navigateFromHomeScreen(actions: HomeScreenActions) {
        when (actions) {
            is HomeScreenActions.OpenJobScreen -> {
                navController.navigate(
                    AppScreen.JobDetailScreen.name
                        .withArg(AppArgs.ARG_JOB_ID, actions.id)
                )
            }
        }
    }

}