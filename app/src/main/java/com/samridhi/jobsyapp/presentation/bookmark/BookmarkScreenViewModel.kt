package com.samridhi.jobsyapp.presentation.bookmark

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samridhi.jobsyapp.data.local.db.entity.toBookMarkData
import com.samridhi.jobsyapp.domain.GetAllSavedJobUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BookmarkScreenViewModel @Inject constructor(
    private val getAllSavedJobUseCase: GetAllSavedJobUseCase
) : ViewModel() {

    var uiState by mutableStateOf(BookmarkUiState())
        private set

    init {
        getLocalSavedData()
    }

    private fun getLocalSavedData() {
        viewModelScope.launch {
            uiState = uiState.copy(items = getAllSavedJobUseCase.invoke().toBookMarkData())
        }
    }
}


data class BookmarkUiState(
    val items: List<BookmarkInfo> = emptyList()
)

data class BookmarkInfo(
    val id: String = "",
    val title: String = "",
    val location: String = "",
    val salary: String = "",
    val experience: String = ""
)