package com.top.shiftestask.userProfileFragment.userProfileInfoRv

import com.top.shiftestask.userProfileFragment.models.ImplicitIntentType

interface UserProfileInfoRvListener {
    fun onClickInfoItem(data: String, implicitIntentType: ImplicitIntentType)
}