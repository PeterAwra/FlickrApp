package com.awra.stud.flickrapp.activities.list_photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.awra.stud.flickrapp.R
import com.awra.stud.flickrapp.model.Photo
import com.awra.stud.flickrapp.tools.urlPhoto

class PhotosAdapter() :
    PagedListAdapter<Photo, PhotoItemHolder>(object : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo) = oldItem.id.equals(newItem.id)

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo) =
            oldItem.urlPhoto() == newItem.urlPhoto()
    }) {
    var clickListener: (position: Int) -> Unit = {}
    var sizeItem = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoItemHolder {
        return PhotoItemHolder(
            itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.item_photo,
                parent,
                false
            ),
            maxSize = sizeItem
        ).also { vh ->
            vh.itemView.setOnClickListener {
                if (vh.adapterPosition != RecyclerView.NO_POSITION)
                    clickListener(
                        vh.adapterPosition
                    )
            }
        }
    }

    override fun onBindViewHolder(holder: PhotoItemHolder, position: Int) {
        holder.bind(getItem(position))
    }
}