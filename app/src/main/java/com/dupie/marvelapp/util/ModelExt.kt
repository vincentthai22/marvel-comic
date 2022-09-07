package com.dupie.marvelapp.util

import com.dupie.marvelapp.network.response.ComicDTO

fun ComicDTO.imageUrl() =
    image?.firstOrNull()?.let {
        "${it.path}.${it.extension}"
    } ?: thumbnail.let {
        "${it.path}.${it.extension}"
    }