package com.top.shiftestask.userProfileFragment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.top.data.randomUser.RandomUserRepository
import com.top.shiftestask.userProfileFragment.models.LoadUserDataErrorUiState
import com.top.shiftestask.userProfileFragment.models.UserProfileUiState
import com.top.shiftestask.userProfileFragment.models.toUserProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val randomUserRepository: RandomUserRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow<UserProfileUiState?>(null)
    val uiState = _uiState.asStateFlow()

    private val _error = MutableSharedFlow<LoadUserDataErrorUiState>()
    val error = _error.asSharedFlow()

    private var fetchJob: Job? = null

    fun fetchUserData(id: Int) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            try {
                val user = randomUserRepository.getUserById(id)
                if (user != null) {
                    _uiState.emit(user.toUserProfileUiState())
                } else {
                    _error.emit(LoadUserDataErrorUiState.NOT_FOUND)
                }
            } catch (e: Exception) {
                Log.e("UserProfileViewModel (Unknown)", e.toString())
                _error.emit(LoadUserDataErrorUiState.UNKNOWN)
            }
        }
    }
}