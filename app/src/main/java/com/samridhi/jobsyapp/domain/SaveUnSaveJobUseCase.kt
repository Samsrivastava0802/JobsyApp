package com.samridhi.jobsyapp.domain

import com.samridhi.jobsyapp.data.local.db.entity.JobEntity
import com.samridhi.jobsyapp.data.local.db.repositories.SavedJobRepository
import javax.inject.Inject

class SaveUnSaveJobUseCase @Inject constructor(
    private val savedJobRepository: SavedJobRepository
) {
    suspend fun saveJob(entity: JobEntity) = savedJobRepository.saveJob(entity)
    suspend fun unSaveJob(id: String) = savedJobRepository.unSaveJob(id)
}