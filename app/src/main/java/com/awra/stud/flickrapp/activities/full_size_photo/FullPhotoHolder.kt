package com.awra.stud.flickrapp.activities.full_size_photo

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.awra.stud.flickrapp.model.Photo
import com.awra.stud.flickrapp.tools.urlPhoto
import com.bumptech.glide.Glide

class FullPhotoHolder(itemView: View) : ViewHolder(itemView) {
    fun bind(photo: Photo?) {
        photo?.let {
            Glide.with(itemView)
                .load(it.urlPhoto())
                .into(itemView as ImageView)
        }
    }
}