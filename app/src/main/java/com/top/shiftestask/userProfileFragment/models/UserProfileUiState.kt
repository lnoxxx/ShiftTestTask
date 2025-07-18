package com.top.shiftestask.userProfileFragment.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.top.shiftestask.userListFragment.models.NameUiState
import com.top.shiftestask.userListFragment.models.PictureUiState

data class UserProfileUiState(
    val name: NameUiState,
    val picture: PictureUiState,
    val gender: GenderUiState,
    val username: String,
    val infoItems: List<UserProfileInfoItem>
)

enum class GenderUiState {
    MALE, FEMALE, OTHER,
}

sealed interface UserProfileInfoItem {
    data class Title(@StringRes val text: Int) : UserProfileInfoItem
    data class Info(
        @DrawableRes val icon: Int,
        @StringRes val title: Int,
        val text: String,
        val position: UserProfileInfoItemPosition = UserProfileInfoItemPosition.DEFAULT,
    ) : UserProfileInfoItem
}

enum class UserProfileInfoItemPosition {
    FIRST, LAST, DEFAULT
}