package com.awra.stud.flickrapp.presenters

import android.content.Context
import android.content.Intent
import androidx.paging.PagedList
import com.awra.stud.flickrapp.activities.full_size_photo.FullSizePhotoActivity
import com.awra.stud.flickrapp.model.Photo
import com.awra.stud.flickrapp.repository.Repository
import com.awra.stud.flickrapp.views.MainView
import io.reactivex.rxjava3.core.Observable
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MainPresenter(val repository: Repository<Photo>) : MvpPresenter<MainView>() {

    private val observerPhotos = androidx.lifecycle.Observer<PagedList<Photo>> {
        viewState.setPhotosPage(it)
    }

    fun update() {}

    override fun onFirstViewAttach() {
        repository.photos().observeForever(observerPhotos)
    }

    fun setQuery(observable: Observable<String>) {
/*        observable.debounce(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .map { it.trim() }
            .filter { it.length > 3 }
            .distinct()
            .switchMap { service.getQueryData(query = it) }
            .map { it.photos?.photo }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                it?.let {
                    viewState.setPhotos(it)
                }
            }*/
    }

    override fun onDestroy() {
        super.onDestroy()
        repository.photos().removeObserver(observerPhotos)
    }

    fun openPhoto(context: Context, position: Int) {
        context.startActivity(
            Intent(context, FullSizePhotoActivity::class.java)
                .putExtra("POSITION_PHOTO", position)
        )
    }
}
