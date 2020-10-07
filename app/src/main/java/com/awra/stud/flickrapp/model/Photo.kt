package com.awra.stud.flickrapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Photo {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("owner")
    @Expose
    var owner: String? = null

    @SerializedName("secret")
    @Expose
    var secret: String? = null

    @SerializedName("server")
    @Expose
    var server: String? = null

    @SerializedName("farm")
    @Expose
    var farm = 0

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("ispublic")
    @Expose
    var ispublic = 0

    @SerializedName("isfriend")
    @Expose
    var isfriend = 0

    @SerializedName("isfamily")
    @Expose
    var isfamily = 0
}