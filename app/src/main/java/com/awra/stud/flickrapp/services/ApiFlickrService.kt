package com.awra.stud.flickrapp.services

import com.awra.stud.flickrapp.model.ResultPhotos
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiFlickrService {

    @GET("services/rest/")
    fun getRecentPhotos(
        @Query(value = "method") method: String = METHOD_RECENT,
        @Query(value = "api_key") apiKey: String = API_KEY,
        @Query(value = "format") format: String = "json",
        @Query(value = "nojsoncallback") nojson: Int = 1,
        @Query(value = "page") page: Int = 0
    ): Observable<ResultPhotos>

    @GET("services/rest/")
    fun getQueryData(
        @Query(value = "method") method: String = METHOD_SEARCH,
        @Query(value = "api_key") apiKey: String = API_KEY,
        @Query(value = "format") format: String = "json",
        @Query(value = "nojsoncallback") nojson: Int = 1,
        @Query("text") query: String?
    ): Observable<ResultPhotos>

    companion object {
        const val METHOD_RECENT = "flickr.photos.getRecent"
        const val METHOD_SEARCH = "flickr.photos.search"
        const val API_KEY = "26e83e1921b2d40964324325ff109858"
        const val SECRET = "88c03d77fb5da2d4"
    }
}