package com.top.shiftestask.userListFragment.userListRecyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.top.shiftestask.databinding.ItemUserBinding
import com.top.shiftestask.userListFragment.models.UserItemUiState

class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemUserBinding.bind(view)
    fun bind(user: UserItemUiState) {
        Picasso.get().load(user.picture.medium).into(binding.imvUserImage)
        with(binding) {
            tvUserName.text = user.name.toString()
            tvUserPhoneNumber.text = user.cell
            tvUserAddress.text = user.location.toString()
        }
    }
}