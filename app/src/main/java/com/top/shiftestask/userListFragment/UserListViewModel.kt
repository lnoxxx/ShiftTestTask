package com.top.shiftestask.userListFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.top.data.randomUser.RandomUserRepository
import com.top.data.randomUser.models.UpdateUsersResult
import com.top.shiftestask.userListFragment.models.UpdateErrorUiState
import com.top.shiftestask.userListFragment.models.UserListUiState
import com.top.shiftestask.userListFragment.models.toUserItemUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val randomUserRepository: RandomUserRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(UserListUiState())
    val uiState = _uiState.asStateFlow()

    private val _error = MutableSharedFlow<UpdateErrorUiState?>()
    val error = _error.asSharedFlow()

    private var updateJob: Job? = null

    fun updateUserList() {
        updateJob?.cancel()
        updateJob = viewModelScope.launch {
            changeUpdateState(true)
            val updateResult = randomUserRepository.updateUsers()
            val updateError = when (updateResult) {
                UpdateUsersResult.SUCCESS -> null
                UpdateUsersResult.SERVER_ERROR -> UpdateErrorUiState.SERVER_ERROR
                UpdateUsersResult.NETWORK_ERROR -> UpdateErrorUiState.NETWORK_ERROR
                UpdateUsersResult.UNKNOWN_ERROR -> UpdateErrorUiState.UNKNOWN_ERROR
            }
            _error.emit(updateError)
            changeUpdateState(false)
        }
    }

    init {
        viewModelScope.launch {
            randomUserRepository.users.collect { users ->
                val userItemUiStateList = users.map { it.toUserItemUiState() }
                val newUiState = _uiState.value.copy(
                    users = userItemUiStateList,
                    isUpdating = false
                )
                _uiState.emit(newUiState)
                if (userItemUiStateList.isEmpty()) updateUserList()
            }
        }
    }

    private suspend fun changeUpdateState(isUpdating: Boolean) {
        val newUiState = _uiState.value.copy(isUpdating = isUpdating)
        _uiState.emit(newUiState)
    }

}