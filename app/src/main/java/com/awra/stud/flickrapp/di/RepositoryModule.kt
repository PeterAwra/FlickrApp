package com.awra.stud.flickrapp.di

import com.awra.stud.flickrapp.model.Photo
import com.awra.stud.flickrapp.repository.Repository
import com.awra.stud.flickrapp.repository.RepositoryOnlyNetwork
import com.awra.stud.flickrapp.services.ApiFlickrService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class RepositoryModule {

    @Provides
    @Singleton
    fun getRepository(service: ApiFlickrService): Repository<Photo> =
        RepositoryOnlyNetwork(service)
}