package com.awra.stud.flickrapp.provides

import com.awra.stud.flickrapp.model.Photo
import com.awra.stud.flickrapp.presenters.MainPresenter
import com.awra.stud.flickrapp.repository.Repository
import javax.inject.Inject

interface PresenterProvider<T> {
    fun get(): T
}

class MainPresenterProvider @Inject constructor(val repository: Repository<Photo>) :
    PresenterProvider<MainPresenter> {
    override fun get(): MainPresenter {
        return MainPresenter(repository)
    }
}
