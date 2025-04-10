package com.samridhi.jobsyapp.presentation.job

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samridhi.jobsyapp.domain.GetAllSavedJobUseCase
import com.samridhi.jobsyapp.domain.JobDataRemoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobScreenViewModel @Inject constructor(
    private val jobDataRemoteUseCase: JobDataRemoteUseCase,
    private val getAllSavedJobUseCase: GetAllSavedJobUseCase
) : ViewModel() {

    var uiState by mutableStateOf(JobScreenUiState())
        private set

    var uiSideEffect by mutableStateOf<JobScreenSideEffects>(JobScreenSideEffects.NoEffect)

    private var currentPage = 1

    init {
        loadJobDetails(currentPage)
    }


    private fun loadJobDetails(page: Int, isLoadMore: Boolean = false) {
        viewModelScope.launch {
            val savedData = getAllSavedJobUseCase()
            Log.d("lion22", "loadJobDetails: $savedData")
            val result = jobDataRemoteUseCase.invoke(page)
            if (isLoadMore) delay(600)
            uiState = uiState.copy(
                items = if (isLoadMore) uiState.items + result else result,
                screenState = ScreenState.DEFAULT,
                isLoadingMore = false
            )
        }
    }


    fun onEvent(event: JobScreenEvent) {
        when (event) {
            is JobScreenEvent.OnClickJobItem -> {
                uiSideEffect = JobScreenSideEffects.OpenJobDetailScreen(event.id)
            }

            JobScreenEvent.OnLoadMore -> {
                uiState = uiState.copy(isLoadingMore = true)
                loadJobDetails(currentPage + 1, isLoadMore = true)
            }
        }
    }

    fun resetSideEffects() {
        uiSideEffect = JobScreenSideEffects.NoEffect
    }
}

data class JobScreenUiState(
    val screenState: ScreenState = ScreenState.LOADING,
    val items: List<JobInfo> = emptyList(),
    val isLoadingMore: Boolean = false
)


enum class ScreenState {
    LOADING,
    EMPTY,
    DEFAULT
}

data class JobInfo(
    val id: String,
    val title: String,
    val location: String,
    val salary: String,
    val phoneData: String
)

sealed class JobScreenEvent {
    data class OnClickJobItem(val id: String) : JobScreenEvent()
    data object OnLoadMore : JobScreenEvent()
}

sealed class JobScreenSideEffects {
    data object NoEffect : JobScreenSideEffects()
    data class OpenJobDetailScreen(val id: String) : JobScreenSideEffects()
}




