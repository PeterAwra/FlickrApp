package com.awra.stud.flickrapp.activities.full_size_photo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.awra.stud.flickrapp.R
import com.awra.stud.flickrapp.model.Photo
import com.awra.stud.flickrapp.tools.urlPhoto

class FullPhotoAdapter() :
    PagedListAdapter<Photo, FullPhotoHolder>(object : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo) = oldItem.id.equals(newItem.id)

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo) =
            oldItem.urlPhoto() == newItem.urlPhoto()
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FullPhotoHolder {
        return FullPhotoHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.full_photo_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FullPhotoHolder, position: Int) {
        holder.bind(getItem(position))
    }

    public override fun getItem(position: Int): Photo? {
        return try {
            super.getItem(position)
        } catch (e: IndexOutOfBoundsException) {
            null
        }
    }
}

