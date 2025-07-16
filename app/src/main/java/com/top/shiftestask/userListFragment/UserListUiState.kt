package com.top.shiftestask.userListFragment

data class UserListUiState(
    val users: List<UserItemUiState> = listOf(),
    val isUpdating: Boolean = false,
)

data class UserItemUiState(
    val name: NameUiState,
    val location: LocationUiState,
    val cell: String,
    val picture: PictureUiState,
    val localId: Int,
)

data class NameUiState(
    val title: String,
    val first: String,
    val last: String,
)

data class LocationUiState(
    val streetName: String,
    val streetNumber: String,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
)

data class PictureUiState(
    val large: String,
    val medium: String,
    val thumbnail: String,
)
