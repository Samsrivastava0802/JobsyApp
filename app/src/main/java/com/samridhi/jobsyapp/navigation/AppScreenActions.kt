package com.samridhi.jobsyapp.navigation


sealed class HomeScreenActions {
    data class OpenJobScreen(
        val id: String
    ) : HomeScreenActions()

//    data object OpenJobScreen : HomeScreenActions()
//    data object OpenBookmarkScreen : HomeScreenActions()
}