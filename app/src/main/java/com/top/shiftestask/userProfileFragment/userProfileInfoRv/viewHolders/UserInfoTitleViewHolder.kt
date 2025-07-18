package com.top.shiftestask.userProfileFragment.userProfileInfoRv.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.top.shiftestask.databinding.ItemUserInfoTitleBinding
import com.top.shiftestask.userProfileFragment.models.UserProfileInfoItem

class UserInfoTitleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemUserInfoTitleBinding.bind(view)
    fun bind(title: UserProfileInfoItem.Title) {
        binding.tvTitle.text = itemView.context.getString(title.text)
    }
}