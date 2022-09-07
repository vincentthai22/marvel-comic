package com.dupie.marvelapp.data

import com.dupie.marvelapp.network.response.ComicDTO
import com.dupie.marvelapp.network.response.ComicResponseDTO
import com.dupie.marvelapp.network.service.MarvelService
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.await

class MarvelRepository(
    val service: MarvelService
) {

    suspend fun fetchComics(): List<ComicDTO> {
        return service.fetchComics().data.results.map { it }
    }

    suspend fun fetchComic(comicId: Int): ComicDTO? {
        return service.fetchComic(comicId).data.results.firstOrNull()
    }

}