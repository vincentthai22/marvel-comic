package com.dupie.marvelapp.data

import com.dupie.marvelapp.network.response.ComicResponseDTO
import com.dupie.marvelapp.network.service.MarvelService
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.await

class MarvelRepository(
    val service: MarvelService
) {

    suspend fun fetchComics(): ComicResponseDTO {
        return service.fetchComics()
    }

}