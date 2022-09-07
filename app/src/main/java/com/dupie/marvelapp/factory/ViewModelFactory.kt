package com.dupie.marvelapp.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.dupie.marvelapp.data.MarvelRepository
import com.dupie.marvelapp.ui.list.MarvelListViewModel

class ViewModelFactory(
    val app: Application,
    val repository: MarvelRepository
    ): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass == MarvelListViewModel::class.java) {
            return MarvelListViewModel(app, repository) as T
        }

        return super.create(modelClass)
    }

}