package com.dupie.marvelapp.ui.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dupie.marvelapp.data.MarvelRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MarvelListViewModel(
    app: Application,
    val repository: MarvelRepository
): AndroidViewModel(app) {

    fun onCreate() {
        viewModelScope.launch {
            val body = repository.fetchComics()
            body
        }

    }


}