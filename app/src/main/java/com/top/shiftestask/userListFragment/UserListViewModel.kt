package com.top.shiftestask.userListFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.top.data.randomUser.RandomUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val randomUserRepository: RandomUserRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(UserListUiState())
    val uiState get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            randomUserRepository.users.collect { users ->
                val userItemUiStateList = users.map { it.toUserItemUiState() }
                _uiState.emit(UserListUiState(userItemUiStateList))
            }
        }
    }

    fun updateUserList(){
        viewModelScope.launch {
            randomUserRepository.updateUsers()
        }
    }
}