package com.dupie.marvelapp.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dupie.marvelapp.R
import com.dupie.marvelapp.data.MarvelRepository
import com.dupie.marvelapp.util.imageUrl
import kotlinx.coroutines.launch

class MarvelDetailViewModel(
    val app: Application,
    val repo: MarvelRepository
): AndroidViewModel(app) {

    val uiStateLD = MutableLiveData<DetailUiState>()

    fun onCreate(comicId: Int) {
        viewModelScope.launch {
            repo.fetchComic(comicId)?.let {
                uiStateLD.postValue(
                    DetailUiState(
                        it.imageUrl(),
                        it.title,
                        it.description ?: app.getString(R.string.no_desc)
                    )
                )
            }
        }
    }

    data class DetailUiState(
        val coverImageUrl: String,
        val comicTitle: String,
        val comicDescription: String
    )

}