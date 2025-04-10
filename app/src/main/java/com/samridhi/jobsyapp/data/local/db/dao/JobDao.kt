package com.samridhi.jobsyapp.data.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.samridhi.jobsyapp.data.local.db.entity.JobEntity

@Dao
interface JobDao {
    @Query("SELECT * FROM job_data")
    suspend fun getAllSavedJob(): List<JobEntity>
    @Query("DELETE FROM job_data WHERE id = :jobId")
    suspend fun unSaveJob(jobId: String): Int
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveJob(job: JobEntity)
    @Query("SELECT EXISTS(SELECT 1 FROM job_data WHERE id = :jobId)")
    suspend fun isJobSaved(jobId: String): Boolean
}