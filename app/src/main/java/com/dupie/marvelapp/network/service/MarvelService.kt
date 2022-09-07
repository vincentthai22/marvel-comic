package com.dupie.marvelapp.network.service

import com.dupie.marvelapp.network.response.ComicResponseDTO
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelService {

    @GET("/v1/public/comics")
    suspend fun fetchComics(): ComicResponseDTO

    @GET("/v1/public/comics/{id}")
    suspend fun fetchComic(@Path("id") comicId: String): Call<Response>

}