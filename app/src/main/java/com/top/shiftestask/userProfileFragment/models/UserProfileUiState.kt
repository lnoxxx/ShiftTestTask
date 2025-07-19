package com.top.shiftestask.userProfileFragment.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.top.shiftestask.userListFragment.models.NameUiState
import com.top.shiftestask.userListFragment.models.PictureUiState

data class UserProfileUiState(
    val infoItems: List<UserProfileInfoItem>
)

sealed interface UserProfileInfoItem {
    data class Header(
        val name: NameUiState,
        val picture: PictureUiState,
        val gender: GenderUiState,
        val login: String,
        val localId: Int,
    ) : UserProfileInfoItem

    data class Title(
        @StringRes val text: Int
    ) : UserProfileInfoItem

    data class Info(
        @DrawableRes val icon: Int,
        @StringRes val title: Int,
        val text: String,
        val position: UserProfileInfoItemPosition = UserProfileInfoItemPosition.DEFAULT,
        val implicitIntentType: ImplicitIntentType = ImplicitIntentType.NONE
    ) : UserProfileInfoItem
}

enum class GenderUiState {
    MALE, FEMALE, OTHER,
}

enum class UserProfileInfoItemPosition {
    FIRST, LAST, DEFAULT
}

enum class LoadUserDataErrorUiState {
    NOT_FOUND, UNKNOWN
}

enum class ImplicitIntentType {
    EMAIL, MAPS, TEL, NONE
}
