
package com.test.bostatask.common.utils

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView


class SpaceItemDecoration(private val mSpace: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        outRect.left = mSpace
        outRect.right = mSpace
        outRect.bottom = mSpace
        outRect.top=mSpace
    }
}