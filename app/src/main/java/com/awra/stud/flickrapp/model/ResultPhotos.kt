package com.awra.stud.flickrapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResultPhotos(
    @SerializedName("photos")
    @Expose
    var photos: Photos? = null,

    @SerializedName("stat")
    @Expose
    var stat: String? = null
)