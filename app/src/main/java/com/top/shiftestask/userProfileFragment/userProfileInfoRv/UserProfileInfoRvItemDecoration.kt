package com.top.shiftestask.userProfileFragment.userProfileInfoRv

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class UserProfileInfoRvItemDecoration(
    private val marginHorizontal: Int,
    private val marginVertical: Int,
): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            bottom = marginVertical
            left = marginHorizontal
            right = marginHorizontal
        }
    }
}