package com.samridhi.jobsyapp.data.repositories

import com.samridhi.jobsyapp.data.AppApiClientService
import javax.inject.Inject

class AppRepositories @Inject constructor(
    private val service: AppApiClientService
){
    suspend fun getJobsData(page : Int) = service.searchJobs(page = page)
}