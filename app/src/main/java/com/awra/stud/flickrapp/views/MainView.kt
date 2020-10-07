package com.awra.stud.flickrapp.views

import androidx.paging.PagedList
import com.awra.stud.flickrapp.model.Photo
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface MainView : MvpView {
    @StateStrategyType(value = OneExecutionStateStrategy::class)
    fun showError(error: String)

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun setPhotosPage(photos: PagedList<Photo>)

    @StateStrategyType(value = OneExecutionStateStrategy::class)
    fun showUpdating(updating: Boolean)

}