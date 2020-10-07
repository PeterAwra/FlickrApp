package com.awra.stud.flickrapp.repository

import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import com.awra.stud.flickrapp.model.Photo
import com.awra.stud.flickrapp.services.ApiFlickrService
import com.awra.stud.flickrapp.tools.log
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.Executors

class RepositoryOnlyNetwork(val service: ApiFlickrService) : Repository<Photo> {
    val livePagedList by lazy {
        LivePagedListBuilder(PhotosDataSource.Factory(service), 1)
            .setFetchExecutor(Executors.newSingleThreadExecutor())
            .build()
    }

    override fun photos() = livePagedList
}

class PhotosDataSource(val service: ApiFlickrService) : PageKeyedDataSource<Int, Photo>() {
    var callbackError: (Throwable?) -> Unit = { "${it?.message}".log() }
    var callbackComplete: () -> Unit = { "complete".log() }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Photo>
    ) {
        service.getRecentPhotos()
            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                result.photos?.photo?.let {
                    callback.onResult(
                        it,
                        0,
                        result?.photos?.total?.toInt() ?: 0,
                        null,
                        1
                    )
                }
            },
                { error -> callbackError(error) },
                { callbackComplete() }
            )

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        service.getRecentPhotos(page = params.key)
            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                result.photos?.photo?.let {
                    callback.onResult(
                        it,
                        result?.photos?.let { if (it.page < it.pages) it.page + 1 else null }
                    )
                }
            },
                { error -> callbackError(error) },
                { callbackComplete() }
            )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        service.getRecentPhotos(page = params.key)
            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                result.photos?.photo?.let {
                    callback.onResult(
                        it,
                        result?.photos?.let { if (it.page > 0) it.page - 1 else null }
                    )
                }
            },
                { error -> callbackError(error) },
                { callbackComplete() }
            )
    }

    class Factory(val service: ApiFlickrService) : DataSource.Factory<Int, Photo>() {
        override fun create(): DataSource<Int, Photo> {
            return PhotosDataSource(service)
        }
    }
}