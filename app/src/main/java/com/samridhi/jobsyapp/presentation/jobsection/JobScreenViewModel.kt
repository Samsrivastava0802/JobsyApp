package com.samridhi.jobsyapp.presentation.jobsection

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samridhi.jobsyapp.data.models.response.toJobInfo
import com.samridhi.jobsyapp.domain.JobDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobScreenViewModel @Inject constructor(
    val jobDataUseCase: JobDataUseCase
) : ViewModel() {

    var uiState by mutableStateOf(JobScreenUiState())
        private set

    private var currentPage = 1

    init {
        jobDetails()
    }

    private fun jobDetails() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoadingMore = true)
            val response = jobDataUseCase.invoke(currentPage)
            if (response.isSuccessful) {
                response.body()?.let {
                    uiState = uiState.copy(items = it.results.toJobInfo())
                }
            }
        }
    }


}

data class JobScreenUiState(
    val items: List<JobInfo> = emptyList(),
    val isLoadingMore: Boolean = false
)

data class JobInfo(
    val id : String,
    val title : String,
    val location : String,
    val salary : String,
    val phoneData : String
)




