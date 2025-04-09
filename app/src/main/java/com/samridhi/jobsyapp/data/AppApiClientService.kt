package com.samridhi.jobsyapp.data

import com.samridhi.jobsyapp.data.models.response.JobDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AppApiClientService {
    @GET("common/jobs")
    suspend fun searchJobs(
        @Query("page") page: Int
    ): Response<JobDetails>
}