package com.samridhi.jobsyapp.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.samridhi.jobsyapp.presentation.bookmark.BookmarkInfo

@Entity(tableName = "job_data")
data class JobEntity(
    @PrimaryKey val id: String,
    val title: String,
    val location: String,
    val salary: String,
    val experience: String
)


fun List<JobEntity>.toBookMarkData(): List<BookmarkInfo> {
    return this.map {
        BookmarkInfo(
            id = it.id,
            title = it.title,
            salary = it.salary,
            experience = it.experience,
            location = it.location
        )
    }
}