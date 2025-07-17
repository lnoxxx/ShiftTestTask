package com.top.shiftestask.userListFragment.models

import com.top.data.randomUser.models.Location
import com.top.data.randomUser.models.Name
import com.top.data.randomUser.models.Picture
import com.top.data.randomUser.models.User

fun User.toUserItemUiState(): UserItemUiState {
    return UserItemUiState(
        name = this.name.toNameUiState(),
        location = this.location.toLocationUiState(),
        cell = this.cell,
        picture = this.picture.toPictureUiState(),
        localId = this.localId,
    )
}

fun Name.toNameUiState(): NameUiState {
    return NameUiState(
        title = this.title,
        first = this.first,
        last = this.last,
    )
}

fun Location.toLocationUiState(): LocationUiState {
    return LocationUiState(
        streetName = this.street.name,
        streetNumber = this.street.number.toString(),
        city = this.city,
        state = this.state,
        country = this.country,
        postcode = this.postcode,
    )
}

fun Picture.toPictureUiState(): PictureUiState {
    return PictureUiState(
        large = this.large,
        medium = this.medium,
        thumbnail = this.thumbnail,
    )
}