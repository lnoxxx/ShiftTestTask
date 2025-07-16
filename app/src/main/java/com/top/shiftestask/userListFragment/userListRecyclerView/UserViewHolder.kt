package com.top.shiftestask.userListFragment.userListRecyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.top.shiftestask.databinding.ItemUserBinding
import com.top.shiftestask.userListFragment.UserItemUiState

class UserViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemUserBinding.bind(view)
    fun bind(user: UserItemUiState){

    }
}