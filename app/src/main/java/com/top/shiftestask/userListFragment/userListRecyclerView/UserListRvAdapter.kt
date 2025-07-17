package com.top.shiftestask.userListFragment.userListRecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.top.shiftestask.R
import com.top.shiftestask.userListFragment.models.UserItemUiState

class UserListRvAdapter : RecyclerView.Adapter<UserViewHolder>() {
    private val userList = mutableListOf<UserItemUiState>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    fun updateUserList(newUsers: List<UserItemUiState>) {
        val diffResult = DiffUtil.calculateDiff(UserListRvDiffUtil(userList, newUsers))
        userList.clear()
        userList.addAll(newUsers)
        diffResult.dispatchUpdatesTo(this)
    }

}