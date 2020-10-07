package com.awra.stud.flickrapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Photos {
    @SerializedName("page")
    @Expose
    var page = 0

    @SerializedName("pages")
    @Expose
    var pages = 0

    @SerializedName("perpage")
    @Expose
    var perpage = 0

    @SerializedName("total")
    @Expose
    var total: String? = null

    @SerializedName("photo")
    @Expose
    var photo: List<Photo>? = null
}