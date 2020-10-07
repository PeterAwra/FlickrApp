package com.awra.stud.flickrapp.activities.list_photos

import android.content.res.Resources
import android.os.Bundle
import android.widget.SearchView
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.awra.stud.flickrapp.App
import com.awra.stud.flickrapp.R
import com.awra.stud.flickrapp.model.Photo
import com.awra.stud.flickrapp.presenters.MainPresenter
import com.awra.stud.flickrapp.provides.PresenterProvider
import com.awra.stud.flickrapp.tools.showToast
import com.awra.stud.flickrapp.views.MainView
import io.reactivex.rxjava3.core.Observable
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {
    init {
        App.dagger.inject(this)
    }

    @Inject
    lateinit var adapter: PhotosAdapter

    @Inject
    lateinit var presenterProvider: PresenterProvider<MainPresenter>

    @ProvidePresenter
    fun providePresenter(): MainPresenter {
        return presenterProvider.get()
    }

    @InjectPresenter
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        setSupportActionBar(main_toolbar)


    }

    private fun initViews() {
        srl.setOnRefreshListener { presenter.update() }
        adapter.sizeItem = calculateSizeHolder()
        adapter.clickListener = { presenter.openPhoto(this@MainActivity, it) }
        rv_photos.adapter = adapter
        et_search_query.setOnSearchClickListener {
            presenter.setQuery(et_search_query.createObservable())
        }
    }

    override fun showError(error: String) {
        srl.isRefreshing = false
        showToast(error)
    }

    override fun setPhotosPage(photos: PagedList<Photo>) {
        adapter.submitList(photos)
    }

    override fun showUpdating(updating: Boolean) {
        srl.isRefreshing = updating
    }

    private fun calculateSizeHolder(): Int {
        return Resources.getSystem().displayMetrics.widthPixels / (rv_photos.layoutManager as GridLayoutManager).spanCount
    }
}

fun SearchView.createObservable() =
    Observable.create<String> {
        this.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    it.onNext(query)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    it.onNext(newText)
                    return true
                }
            }
        )
    }

