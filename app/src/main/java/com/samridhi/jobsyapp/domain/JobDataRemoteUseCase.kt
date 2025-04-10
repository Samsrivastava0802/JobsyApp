package com.samridhi.jobsyapp.domain

import com.samridhi.jobsyapp.data.network.models.response.toJobDetailDescription
import com.samridhi.jobsyapp.data.network.models.response.toJobInfo
import com.samridhi.jobsyapp.data.network.repositories.AppRepositories
import com.samridhi.jobsyapp.presentation.jobdetail.JobDescription
import com.samridhi.jobsyapp.presentation.job.JobInfo
import javax.inject.Inject

class JobDataRemoteUseCase @Inject constructor(
    private val appRepositories: AppRepositories
) {
    private val list = mutableListOf<JobDescription>()
    suspend fun invoke(page: Int = 1): List<JobInfo> {
        val data = appRepositories.getJobsData(page = page)
        if (data.isSuccessful && data.body() != null) {
            val jobInformationList = data.body()?.results ?: emptyList()
            list.addAll(jobInformationList.toJobDetailDescription())
            return jobInformationList.toJobInfo()
        }
        else {
            return emptyList()
        }
    }

    fun getJobDetailById(id: String) = list.find { it.id == id }
}