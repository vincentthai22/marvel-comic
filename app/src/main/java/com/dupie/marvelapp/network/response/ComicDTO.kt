package com.dupie.marvelapp.network.response

data class ComicDTO(
    val id: Int,
    val title: String,
    val description: String?,
    val thumbnail: ThumbnailDTO
) {
}