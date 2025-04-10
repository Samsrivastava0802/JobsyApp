package com.samridhi.jobsyapp.domain

import com.samridhi.jobsyapp.data.local.db.repositories.SavedJobRepository
import javax.inject.Inject

class IsJobSavedUseCase @Inject constructor(
    private val savedJobRepository: SavedJobRepository
) {
    suspend operator fun invoke(id: String) = savedJobRepository.isJobSaved(id)
}