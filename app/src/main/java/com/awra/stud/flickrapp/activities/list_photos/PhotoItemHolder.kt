package com.awra.stud.flickrapp.activities.list_photos

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.awra.stud.flickrapp.model.Photo
import com.awra.stud.flickrapp.tools.urlPhoto
import com.bumptech.glide.Glide
import com.google.android.material.circularreveal.CircularRevealFrameLayout
import kotlinx.android.synthetic.main.item_photo.view.*

class PhotoItemHolder(itemView: View, val maxSize: Int) : RecyclerView.ViewHolder(itemView) {
    fun bind(photo: Photo?) {


        photo?.let {
            Glide.with(itemView.iv_item_photo).load(it.urlPhoto(maxSize))
                .into(itemView.iv_item_photo)
        }?:itemView.iv_item_photo.setImageDrawable(CircularProgressDrawable(itemView.context))

    }
}