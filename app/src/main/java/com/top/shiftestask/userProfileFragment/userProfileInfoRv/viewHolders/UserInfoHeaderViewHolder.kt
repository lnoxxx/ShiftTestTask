package com.top.shiftestask.userProfileFragment.userProfileInfoRv.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.top.shiftestask.databinding.ItemUserInfoHeaderBinding
import com.top.shiftestask.userProfileFragment.models.UserProfileInfoItem

class UserInfoHeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemUserInfoHeaderBinding.bind(view)
    fun bind(header: UserProfileInfoItem.Header) {
        Picasso.get().load(header.picture.medium).into(binding.imvUserImage)
        with(binding) {
            tvUserLogin.text = header.login
            tvUserName.text = header.name.toString()
        }
    }
}