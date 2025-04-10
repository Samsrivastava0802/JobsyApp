package com.samridhi.jobsyapp.data.network.repositories

import com.samridhi.jobsyapp.data.network.AppApiClientService
import javax.inject.Inject

class AppRepositories @Inject constructor(
    private val service: AppApiClientService
){
    suspend fun getJobsData(page: Int) = service.searchJobs(page = page)
}