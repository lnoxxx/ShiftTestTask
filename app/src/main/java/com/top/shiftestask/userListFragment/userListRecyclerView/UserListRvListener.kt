package com.top.shiftestask.userListFragment.userListRecyclerView

import android.view.View

interface UserListRvListener {
    fun onClickUser(localId: Int, imageView: View)
}