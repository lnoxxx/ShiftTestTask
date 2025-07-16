package com.top.data.randomUser.models

import com.top.data.randomUser.randomUserDataSource.local.RandomUserEntity
import com.top.data.randomUser.randomUserDataSource.remote.CoordinatesNetwork
import com.top.data.randomUser.randomUserDataSource.remote.DateOfBirthNetwork
import com.top.data.randomUser.randomUserDataSource.remote.IdNetwork
import com.top.data.randomUser.randomUserDataSource.remote.LocationNetwork
import com.top.data.randomUser.randomUserDataSource.remote.LoginNetwork
import com.top.data.randomUser.randomUserDataSource.remote.NameNetwork
import com.top.data.randomUser.randomUserDataSource.remote.PictureNetwork
import com.top.data.randomUser.randomUserDataSource.remote.RegisteredNetwork
import com.top.data.randomUser.randomUserDataSource.remote.StreetNetwork
import com.top.data.randomUser.randomUserDataSource.remote.TimezoneNetwork

fun RandomUserEntity.toUser(): User {
    return User(
        gender = this.user.gender,
        name = this.user.name.toName(),
        location = this.user.location.toLocation(),
        email = this.user.email,
        login = this.user.login.toLogin(),
        dob = this.user.dob.toDateOfBirth(),
        registered = this.user.registered.toRegistered(),
        phone = this.user.phone,
        cell = this.user.cell,
        id = this.user.id.toId(),
        picture = this.user.picture.toPicture(),
        nat = this.user.nat,
        localId = this.id.toInt()
    )
}

fun NameNetwork.toName(): Name {
    return Name(
        title = this.title,
        first = this.first,
        last = this.last
    )
}

fun LocationNetwork.toLocation(): Location {
    return Location(
        street = this.street.toStreet(),
        city = this.city,
        state = this.state,
        country = this.country,
        postcode = this.postcode,
        coordinates = this.coordinates.toCoordinates(),
        timezone = this.timezone.toTimezone()
    )
}

fun StreetNetwork.toStreet(): Street {
    return Street(
        number = this.number,
        name = this.name
    )
}

fun CoordinatesNetwork.toCoordinates(): Coordinates {
    return Coordinates(
        latitude = this.latitude,
        longitude = this.longitude
    )
}

fun TimezoneNetwork.toTimezone(): Timezone {
    return Timezone(
        offset = this.offset,
        description = this.description
    )
}

fun LoginNetwork.toLogin(): Login {
    return Login(
        uuid = this.uuid,
        username = this.username,
        password = this.password,
        salt = this.salt,
        md5 = this.md5,
        sha1 = this.sha1,
        sha256 = this.sha256
    )
}

fun DateOfBirthNetwork.toDateOfBirth(): DateOfBirth {
    return DateOfBirth(
        date = this.date,
        age = this.age
    )
}

fun RegisteredNetwork.toRegistered(): Registered {
    return Registered(
        date = this.date,
        age = this.age
    )
}

fun IdNetwork.toId(): Id {
    return Id(
        name = this.name,
        value = this.value
    )
}

fun PictureNetwork.toPicture(): Picture {
    return Picture(
        large = this.large,
        medium = this.medium,
        thumbnail = this.thumbnail
    )
}