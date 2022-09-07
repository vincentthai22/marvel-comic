package com.dupie.marvelapp

import android.app.Application
import com.dupie.marvelapp.di.Provider

class MarvelApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Provider.application = this
    }
}