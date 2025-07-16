package com.top.data.randomUser.randomUserDataSource.remote

import kotlinx.serialization.Serializable

data class RandomUserResponse(
    val results: List<UserNetwork>,
    val info: InfoNetwork
)

@Serializable
data class UserNetwork(
    val gender: String,
    val name: NameNetwork,
    val location: LocationNetwork,
    val email: String,
    val login: LoginNetwork,
    val dob: DateOfBirthNetwork,
    val registered: RegisteredNetwork,
    val phone: String,
    val cell: String,
    val id: IdNetwork,
    val picture: PictureNetwork,
    val nat: String
)

@Serializable
data class NameNetwork(
    val title: String,
    val first: String,
    val last: String,
)

@Serializable
data class LocationNetwork(
    val street: StreetNetwork,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val coordinates: CoordinatesNetwork,
    val timezone: TimezoneNetwork,
)

@Serializable
data class StreetNetwork(
    val number: Int,
    val name: String,
)

@Serializable
data class CoordinatesNetwork(
    val latitude: String,
    val longitude: String,
)

@Serializable
data class TimezoneNetwork(
    val offset: String,
    val description: String,
)

@Serializable
data class LoginNetwork(
    val uuid: String,
    val username: String,
    val password: String,
    val salt: String,
    val md5: String,
    val sha1: String,
    val sha256: String,
)

@Serializable
data class DateOfBirthNetwork(
    val date: String,
    val age: Int,
)

@Serializable
data class RegisteredNetwork(
    val date: String,
    val age: Int,
)

@Serializable
data class IdNetwork(
    val name: String,
    val value: String,
)

@Serializable
data class PictureNetwork(
    val large: String,
    val medium: String,
    val thumbnail: String,
)

data class InfoNetwork(
    val seed: String,
    val resultsCount: Int,
    val page: Int,
    val version: String,
)