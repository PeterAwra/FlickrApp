package com.awra.stud.flickrapp.di

import com.awra.stud.flickrapp.activities.full_size_photo.FullSizePhotoActivity
import com.awra.stud.flickrapp.activities.list_photos.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [RepositoryModule::class, PreseneterModule::class, PhotoActivityModule::class, FullPhotoActivityModule::class])
@Singleton
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: FullSizePhotoActivity)
}


