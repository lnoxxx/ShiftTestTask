package com.top.shiftestask.userProfileFragment.userProfileInfoRv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.top.shiftestask.R
import com.top.shiftestask.userProfileFragment.models.UserProfileInfoItem
import com.top.shiftestask.userProfileFragment.userProfileInfoRv.viewHolders.UserInfoHeaderViewHolder
import com.top.shiftestask.userProfileFragment.userProfileInfoRv.viewHolders.UserInfoTitleViewHolder
import com.top.shiftestask.userProfileFragment.userProfileInfoRv.viewHolders.UserInfoViewHolder

class UserProfileInfoRvAdapter : RecyclerView.Adapter<ViewHolder>() {
    private val infoList = mutableListOf<UserProfileInfoItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_TITLE -> UserInfoTitleViewHolder(
                inflater.inflate(R.layout.item_user_info_title, parent, false)
            )

            TYPE_INFO -> UserInfoViewHolder(
                inflater.inflate(R.layout.item_user_info, parent, false)
            )

            TYPE_HEADER -> UserInfoHeaderViewHolder(
                inflater.inflate(R.layout.item_user_info_header, parent, false)
            )

            else -> throw IllegalArgumentException("UserProfileInfoRvAdapter unknown view type")
        }
    }

    override fun getItemCount(): Int = infoList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = infoList[position]
        when (holder) {
            is UserInfoTitleViewHolder -> holder.bind(item as UserProfileInfoItem.Title)
            is UserInfoViewHolder -> holder.bind(item as UserProfileInfoItem.Info)
            is UserInfoHeaderViewHolder -> holder.bind(item as UserProfileInfoItem.Header)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = infoList[position]
        return when (item) {
            is UserProfileInfoItem.Info -> TYPE_INFO
            is UserProfileInfoItem.Title -> TYPE_TITLE
            is UserProfileInfoItem.Header -> TYPE_HEADER
        }
    }

    fun updateInfo(newInfo: List<UserProfileInfoItem>) {
        val cb = DiffUtil.calculateDiff(UserProfileInfoRvDiffUtil(infoList, newInfo))
        infoList.clear()
        infoList.addAll(newInfo)
        cb.dispatchUpdatesTo(this)
    }

    companion object {
        const val TYPE_TITLE = 0
        const val TYPE_INFO = 1
        const val TYPE_HEADER = 2
    }
}