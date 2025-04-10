package com.samridhi.jobsyapp.domain

import com.samridhi.jobsyapp.data.local.db.dao.JobDao
import com.samridhi.jobsyapp.data.local.db.repositories.SavedJobRepository
import javax.inject.Inject

class GetAllSavedJobUseCase @Inject constructor(
    private val savedJobRepository: SavedJobRepository
) {
    suspend operator fun invoke() = savedJobRepository.getAllSavedJob()
}