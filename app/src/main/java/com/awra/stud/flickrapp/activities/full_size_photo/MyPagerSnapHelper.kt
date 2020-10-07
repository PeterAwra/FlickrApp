package com.awra.stud.flickrapp.activities.full_size_photo

import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView

class MyPagerSnapHelper : PagerSnapHelper() {
    var currentPositionCallback: (Int) -> Unit = {}
    override fun findTargetSnapPosition(
        layoutManager: RecyclerView.LayoutManager?,
        velocityX: Int,
        velocityY: Int
    ): Int {
        return super.findTargetSnapPosition(layoutManager, velocityX, velocityY)
            .also { currentPositionCallback(it) }
    }
}