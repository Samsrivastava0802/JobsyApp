package com.samridhi.jobsyapp.data.local.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.samridhi.jobsyapp.data.local.db.dao.JobDao
import com.samridhi.jobsyapp.data.local.db.entity.JobEntity

@Database(entities = [JobEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun jobDao(): JobDao
}