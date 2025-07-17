package com.top.shiftestask.userListFragment.models

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
) {
    override fun toString(): String {
        return "$title $first $last"
    }
}

data class LocationUiState(
    val streetName: String,
    val streetNumber: String,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
) {
    override fun toString(): String {
        return "$country, $state, $city, $streetName $streetNumber, $postcode"
    }
}

data class PictureUiState(
    val large: String,
    val medium: String,
    val thumbnail: String,
)

enum class UpdateErrorUiState {
    SERVER_ERROR, NETWORK_ERROR, UNKNOWN_ERROR,
}
