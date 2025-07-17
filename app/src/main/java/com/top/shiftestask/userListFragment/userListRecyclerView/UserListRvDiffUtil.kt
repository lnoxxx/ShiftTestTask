package com.top.shiftestask.userListFragment.userListRecyclerView

import androidx.recyclerview.widget.DiffUtil
import com.top.shiftestask.userListFragment.models.UserItemUiState

class UserListRvDiffUtil(
    private val oldUserList: List<UserItemUiState>,
    private val newUserList: List<UserItemUiState>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldUserList.size

    override fun getNewListSize(): Int = newUserList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser = oldUserList[oldItemPosition]
        val newUser = newUserList[newItemPosition]
        return oldUser.localId == newUser.localId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser = oldUserList[oldItemPosition]
        val newUser = newUserList[newItemPosition]
        return oldUser == newUser
    }
}