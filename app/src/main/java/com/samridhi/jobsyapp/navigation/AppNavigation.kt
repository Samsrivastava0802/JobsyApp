package com.samridhi.jobsyapp.navigation

import com.samridhi.jobsyapp.util.addRouteArgument


object AppArgs {
    const val ARG_JOB_ID = "job_id"
}

sealed class AppScreen(val name: String, val route: String) {
    data object HomeScreen : AppScreen("home", "home")
    data object JobDetailScreen : AppScreen("jobDetail", "jobDetail"
        .addRouteArgument(AppArgs.ARG_JOB_ID)
    )
    data object OtherScreenFlow: AppScreen("other", "other")
}

sealed class AppHomeFlow(val name: String, val route: String) {
    data object JobScreen : AppHomeFlow("job", "job")
    data object BookmarkScreen : AppHomeFlow("bookmark", "bookmark")
}