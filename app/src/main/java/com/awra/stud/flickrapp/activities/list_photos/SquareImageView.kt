package com.awra.stud.flickrapp.activities.list_photos

import android.content.Context
import android.util.AttributeSet

class SquareImageView(context: Context, attrs: AttributeSet? = null) :
    androidx.appcompat.widget.AppCompatImageView(context, attrs) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }
}