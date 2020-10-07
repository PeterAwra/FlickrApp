package com.awra.stud.flickrapp

import android.app.Application
import com.awra.stud.flickrapp.di.AppComponent
import com.awra.stud.flickrapp.di.DaggerAppComponent

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        dagger = DaggerAppComponent.create()
    }

    companion object {
        lateinit var dagger: AppComponent
    }
}