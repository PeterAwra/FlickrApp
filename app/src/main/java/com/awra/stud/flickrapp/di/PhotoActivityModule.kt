package com.awra.stud.flickrapp.di

import com.awra.stud.flickrapp.activities.full_size_photo.FullPhotoAdapter
import com.awra.stud.flickrapp.activities.list_photos.PhotosAdapter
import dagger.Module
import dagger.Provides

@Module
class PhotoActivityModule {
    @Provides
    fun getAdapter(): PhotosAdapter =
        PhotosAdapter()


}

@Module
class FullPhotoActivityModule {
    @Provides
    fun getAdapter(): FullPhotoAdapter =
        FullPhotoAdapter()
}