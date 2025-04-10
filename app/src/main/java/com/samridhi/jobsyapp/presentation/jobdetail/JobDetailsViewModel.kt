package com.samridhi.jobsyapp.presentation.jobdetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samridhi.jobsyapp.data.local.db.entity.JobEntity
import com.samridhi.jobsyapp.domain.IsJobSavedUseCase
import com.samridhi.jobsyapp.domain.JobDataRemoteUseCase
import com.samridhi.jobsyapp.domain.SaveUnSaveJobUseCase
import com.samridhi.jobsyapp.navigation.AppArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobDetailsViewModel @Inject constructor(
    private val jobDataRemoteUseCase: JobDataRemoteUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val isJobSavedUseCase: IsJobSavedUseCase,
    private val saveUnSaveJobUseCase: SaveUnSaveJobUseCase
) : ViewModel() {

    private val id: String = savedStateHandle[AppArgs.ARG_JOB_ID] ?: ""

    var uiState by mutableStateOf(JobDetailsUiState())
        private set

    init {
        getJobInfo()
    }

    private fun getJobInfo() {
        viewModelScope.launch {
            jobDataRemoteUseCase.getJobDetailById(id)?.let {
                uiState = uiState.copy(
                    details = it,
                    save = isJobSavedUseCase(id)
                )
            }
        }
    }

    private fun handleSaveUnSave() {
        viewModelScope.launch {
            if (uiState.save) {
                saveUnSaveJobUseCase.unSaveJob(id)
            } else {
                saveUnSaveJobUseCase.saveJob(
                    JobEntity(
                        id = id,
                        title = uiState.details?.title ?: "",
                        location = uiState.details?.location ?: "",
                        salary = uiState.details?.salary ?: "",
                        experience = uiState.details?.experience ?: ""
                    )
                )
            }
            uiState = uiState.copy(save = !uiState.save)
        }

    }

    fun onEvent(event: JobDetailsEvent) {
        when (event) {
            JobDetailsEvent.OnSaveOrUnSave -> {
                handleSaveUnSave()
            }
        }
    }
}

data class JobDetailsUiState(
    val details: JobDescription? = null,
    val save: Boolean = false
)

data class JobDescription(
    val id: String = "",
    val title: String = "",
    val companyName: String = "",
    val location: String = "",
    val qualifications: String = "",
    val salary: String = "",
    val experience: String = "",
    val qualification: String = "",
    val feesCharged: String = "",
    val openingsCount: String = "",
    val jobRole: String = "",
    val jobHours: String = "",
    val jobCategory: String = "",
    val customLink: String = "",
    val views: String = "",
    val shares: String = ""
)

sealed class JobDetailsEvent {
    data object OnSaveOrUnSave : JobDetailsEvent()
}