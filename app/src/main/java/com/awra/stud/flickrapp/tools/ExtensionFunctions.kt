package com.awra.stud.flickrapp.tools

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.awra.stud.flickrapp.model.Photo

fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun String.log() {
    Log.d("Logger", this)
}

fun Photo.urlPhoto() =
    "https://farm${farm}.staticflickr.com/${server}/${id}_${secret}.jpg"

fun Photo.urlPhoto(maxSize: Int) =
    "https://farm${farm}.staticflickr.com/${server}/${id}_${secret}_${SizeSuffixPhoto.suffix(maxSize).key}.jpg"

