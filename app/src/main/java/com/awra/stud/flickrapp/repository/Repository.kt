package com.awra.stud.flickrapp.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.awra.stud.flickrapp.model.Photo

interface Repository<T> {
    fun photos(): LiveData<PagedList<Photo>>
}