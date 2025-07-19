package com.top.shiftestask.userProfileFragment.models

import com.top.data.randomUser.models.User
import com.top.shiftestask.R
import com.top.shiftestask.userListFragment.models.toLocationUiState
import com.top.shiftestask.userListFragment.models.toNameUiState
import com.top.shiftestask.userListFragment.models.toPictureUiState
import com.top.shiftestask.utils.isoToDmy

fun User.toUserProfileUiState(): UserProfileUiState {
    val titleContacts = UserProfileInfoItem.Title(R.string.title_user_info_contacts)
    val titleOther = UserProfileInfoItem.Title(R.string.title_user_info_other)
    val header = UserProfileInfoItem.Header(
        name = this.name.toNameUiState(),
        picture = this.picture.toPictureUiState(),
        gender = genderToGenderUiState(this.gender),
        login = "@${this.login.username}",
        localId = this.localId
    )
    val age = UserProfileInfoItem.Info(
        icon = R.drawable.ic_info_age,
        title = R.string.title_user_info_age,
        text = this.dob.age.toString(),
        position = UserProfileInfoItemPosition.FIRST
    )
    val dob = UserProfileInfoItem.Info(
        icon = R.drawable.ic_info_dob,
        title = R.string.title_user_info_dob,
        text = isoToDmy(this.dob.date),
    )
    val location = UserProfileInfoItem.Info(
        icon = R.drawable.ic_info_location,
        title = R.string.title_user_info_location,
        text = this.location.toLocationUiState().toString(),
        position = UserProfileInfoItemPosition.LAST,
        implicitIntentType = ImplicitIntentType.MAPS
    )
    val email = UserProfileInfoItem.Info(
        icon = R.drawable.ic_info_emali,
        title = R.string.title_user_info_email,
        text = this.email,
        position = UserProfileInfoItemPosition.FIRST,
        implicitIntentType = ImplicitIntentType.EMAIL
    )
    val phone = UserProfileInfoItem.Info(
        icon = R.drawable.ic_info_phone,
        title = R.string.title_user_info_phone,
        text = this.phone,
        implicitIntentType = ImplicitIntentType.TEL
    )
    val cell = UserProfileInfoItem.Info(
        icon = R.drawable.ic_info_cell,
        title = R.string.title_user_info_cell,
        text = this.cell,
        position = UserProfileInfoItemPosition.LAST,
        implicitIntentType = ImplicitIntentType.TEL
    )
    val uuid = UserProfileInfoItem.Info(
        icon = R.drawable.ic_info_uuid,
        title = R.string.title_user_info_uuid,
        text = this.login.uuid,
        position = UserProfileInfoItemPosition.FIRST
    )
    val dor = UserProfileInfoItem.Info(
        icon = R.drawable.ic_info_dor,
        title = R.string.title_user_info_dor,
        text = isoToDmy(this.registered.date),
        position = UserProfileInfoItemPosition.LAST
    )
    val infoItems = listOf(
        header, age, dob, location, titleContacts, email, phone, cell, titleOther, uuid, dor
    )
    return UserProfileUiState(infoItems = infoItems)
}

fun genderToGenderUiState(gender: String): GenderUiState {
    return when (gender) {
        "male" -> GenderUiState.MALE
        "female" -> GenderUiState.FEMALE
        else -> GenderUiState.OTHER
    }
}
