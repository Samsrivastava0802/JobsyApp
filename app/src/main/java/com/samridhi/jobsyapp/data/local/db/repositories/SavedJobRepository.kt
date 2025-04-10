package com.samridhi.jobsyapp.data.local.db.repositories

import com.samridhi.jobsyapp.data.local.db.dao.JobDao
import com.samridhi.jobsyapp.data.local.db.entity.JobEntity
import javax.inject.Inject

class SavedJobRepository
@Inject constructor(
    private val jobDao: JobDao
) {
    suspend fun getAllSavedJob() = jobDao.getAllSavedJob()
    suspend fun saveJob(jobEntity: JobEntity) = jobDao.saveJob(jobEntity)
    suspend fun unSaveJob(id: String) = jobDao.unSaveJob(id)
    suspend fun isJobSaved(id: String) = jobDao.isJobSaved(id)
}