package com.dupie.marvelapp.ui.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dupie.marvelapp.data.MarvelRepository
import com.dupie.marvelapp.util.imageUrl
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MarvelListViewModel(
    app: Application,
    val repository: MarvelRepository
): AndroidViewModel(app) {

    val listLiveData = MutableLiveData<List<ComicListItem>>()
    val navigateToDetailLD = MutableLiveData<Int?>()

    fun onCreate() {
        viewModelScope.launch {
            val comics = repository.fetchComics()
            listLiveData.postValue(comics.map { ComicListItem(
                it.id,
                it.title,
                it.imageUrl()
            ) })
        }
    }

    fun onComicClicked(comic: ComicListItem) {
        navigateToDetailLD.value = comic.id
        navigateToDetailLD.value = null
    }


}