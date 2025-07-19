package com.top.shiftestask.userProfileFragment.userProfileInfoRv.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.top.shiftestask.R
import com.top.shiftestask.databinding.ItemUserInfoBinding
import com.top.shiftestask.userProfileFragment.models.UserProfileInfoItem
import com.top.shiftestask.userProfileFragment.models.UserProfileInfoItemPosition
import com.top.shiftestask.userProfileFragment.userProfileInfoRv.UserProfileInfoRvListener

class UserInfoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemUserInfoBinding.bind(view)
    fun bind(info: UserProfileInfoItem.Info, listener: UserProfileInfoRvListener) {
        val background = when (info.position) {
            UserProfileInfoItemPosition.FIRST -> R.drawable.shape_info_item_first
            UserProfileInfoItemPosition.LAST -> R.drawable.shape_info_item_last
            UserProfileInfoItemPosition.DEFAULT -> R.drawable.shape_info_item_default
        }
        with(binding) {
            tvTitle.text = itemView.context.getString(info.title)
            tvInfo.text = info.text
            ivInfoIcon.setImageResource(info.icon)
            clMain.setBackgroundResource(background)
        }
        itemView.setOnClickListener {
            listener.onClickInfoItem(info.text, info.implicitIntentType)
        }
    }
}