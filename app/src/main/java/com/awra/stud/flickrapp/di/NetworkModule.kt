package com.awra.stud.flickrapp.di

import com.awra.stud.flickrapp.services.ApiFlickrService
import com.awra.stud.flickrapp.tools.log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {
    @Provides
    fun client(): OkHttpClient = OkHttpClient.Builder()
        .callTimeout(5, TimeUnit.SECONDS)
        .addInterceptor {
            it.request().apply { "${method()}  ${url()}".log() }
            it.proceed(it.request())
        }
        .build()

    @Provides
    fun retrofit(client: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://www.flickr.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

    @Provides
    fun getGson(): Gson = GsonBuilder()
        .setLenient()
        .create()

    @Provides
    fun getApiFlickrService(retrofit: Retrofit): ApiFlickrService =
        retrofit.create(ApiFlickrService::class.java)
}

