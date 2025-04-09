package com.samridhi.jobsyapp.domain

import com.samridhi.jobsyapp.data.repositories.AppRepositories
import javax.inject.Inject

class JobDataUseCase @Inject constructor(
    private val appRepositories: AppRepositories
) {
    suspend operator fun invoke(page: Int = 1) = appRepositories.getJobsData(page = page)
}