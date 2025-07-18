package com.top.shiftestask.userProfileFragment.userProfileInfoRv

import androidx.recyclerview.widget.DiffUtil
import com.top.shiftestask.userProfileFragment.models.UserProfileInfoItem

class UserProfileInfoRvDiffUtil(
    private val oldInfo: List<UserProfileInfoItem>,
    private val newInfo: List<UserProfileInfoItem>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldInfo.size

    override fun getNewListSize(): Int = newInfo.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val newItem = newInfo[newItemPosition]
        val oldItem = oldInfo[oldItemPosition]
        if (oldItem is UserProfileInfoItem.Title && newItem is UserProfileInfoItem.Title) {
            return oldItem.text == newItem.text
        }
        if (newItem is UserProfileInfoItem.Info && oldItem is UserProfileInfoItem.Info) {
            return oldItem.title == newItem.title
        }
        if (newItem is UserProfileInfoItem.Header && oldItem is UserProfileInfoItem.Header) {
            return newItem.login == oldItem.login
        }
        return false
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val newItem = newInfo[newItemPosition]
        val oldItem = oldInfo[oldItemPosition]
        return newItem == oldItem
    }

}