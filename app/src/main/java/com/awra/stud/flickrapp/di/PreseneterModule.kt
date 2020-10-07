package com.awra.stud.flickrapp.di

import com.awra.stud.flickrapp.presenters.MainPresenter
import com.awra.stud.flickrapp.provides.MainPresenterProvider
import com.awra.stud.flickrapp.provides.PresenterProvider
import dagger.Binds
import dagger.Module

@Module
abstract class PreseneterModule {
    @Binds
    abstract fun providesMain(pro: MainPresenterProvider): PresenterProvider<MainPresenter>
}