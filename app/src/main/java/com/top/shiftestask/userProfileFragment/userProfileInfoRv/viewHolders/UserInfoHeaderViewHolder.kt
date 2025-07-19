package com.top.shiftestask.userProfileFragment.userProfileInfoRv.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.top.shiftestask.R
import com.top.shiftestask.databinding.ItemUserInfoHeaderBinding
import com.top.shiftestask.userProfileFragment.models.GenderUiState
import com.top.shiftestask.userProfileFragment.models.UserProfileInfoItem
import com.top.shiftestask.utils.CircleTransformation
import com.top.shiftestask.utils.getImageTransitionName

class UserInfoHeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemUserInfoHeaderBinding.bind(view)
    fun bind(header: UserProfileInfoItem.Header) {
        Picasso.get()
            .load(header.picture.medium)
            .transform(CircleTransformation())
            .into(binding.imvUserImage)
        val genderRes = when (header.gender) {
            GenderUiState.MALE -> R.drawable.ic_info_gender_male
            GenderUiState.FEMALE -> R.drawable.ic_info_gender_female
            GenderUiState.OTHER -> R.drawable.ic_info_gender_other
        }
        with(binding) {
            tvUserLogin.text = header.login
            tvUserName.text = header.name.toString()
            imvUserImage.transitionName = getImageTransitionName(header.localId)
            imvGender.setImageResource(genderRes)
        }
    }
}