package com.an2t.androidpaginglibrarystepbystepimplementationguide

import android.content.ContentValues.TAG
import android.util.Log
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


interface NetworkService {

    @GET("everything?q=sports&apiKey=aa67d8d98c8e4ad1b4f16dbd5f3be348")
    fun getNews(@Query("page") page: Int, @Query("pageSize") pageSize: Int): Single<Response>

    companion object {
        fun getService(): NetworkService {


            val log = HttpLoggingInterceptor { message -> Log.e(TAG, message) }
                    .setLevel(HttpLoggingInterceptor.Level.BODY)

            val c = OkHttpClient.Builder()
                    .addInterceptor(log)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .build()

            val retrofit = Retrofit.Builder()
                    .baseUrl("https://newsapi.org/v2/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(c)
                    .build()
            return retrofit.create(NetworkService::class.java)
        }
    }

}