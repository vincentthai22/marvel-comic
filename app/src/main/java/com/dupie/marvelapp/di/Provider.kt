package com.dupie.marvelapp.di

import android.app.Application
import com.dupie.marvelapp.BuildConfig
import com.dupie.marvelapp.data.MarvelRepository
import com.dupie.marvelapp.factory.ViewModelFactory
import com.dupie.marvelapp.network.interceptor.ApiKeyInterceptor
import com.dupie.marvelapp.network.service.MarvelService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Provider {

    private val moshi: Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory()).build()

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ApiKeyInterceptor())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.MARVEL_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
        .build()

    private val marvelService = retrofit.create(MarvelService::class.java)

    private val repository = MarvelRepository(marvelService)

    lateinit var application: Application
    val viewModelFactory by lazy {
        ViewModelFactory(application, repository)
    }

}