package com.example.myapplication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.AppsRepository
import com.example.myapplication.domain.model.AppDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AppsUiState(
    val apps: List<AppDomain> = emptyList(),
    val isLoading: Boolean = false
)

@HiltViewModel
class AppsViewModel @Inject constructor(
    // Подставляется AppsRepositoryImpl через DI
    private val repository: AppsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AppsUiState(isLoading = true))
    val uiState: StateFlow<AppsUiState> = _uiState.asStateFlow()

    private val _messages = Channel<String>(Channel.BUFFERED)
    val messages = _messages.receiveAsFlow()

    init {
        loadApps()
    }

    private fun loadApps() {
        viewModelScope.launch {
            _uiState.value = AppsUiState(isLoading = true)
            val apps = repository.getApps()
            _uiState.value = AppsUiState(apps = apps, isLoading = false)
        }
    }

    fun onToolbarClick() {
        viewModelScope.launch {
            _messages.send("Меню пока в разработке 🙂")
        }
    }
}
