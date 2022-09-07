package com.dupie.marvelapp.network.interceptor

import com.dupie.marvelapp.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ApiKeyInterceptor: Interceptor {

    companion object {
        private const val API_KEY_QUERY_PARAM = "apikey"
        private const val TS_QUERY_PARAM = "ts"
        private const val HASH_QUERY_PARAM = "hash"

        private const val HARDCODED_TS = "1"
        private const val HARDCODED_HASH = "91767a232d3a1d983c61aaf600e689d5"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        val url: HttpUrl = request.url().newBuilder()
            .addQueryParameter(API_KEY_QUERY_PARAM, BuildConfig.MARVEL_API_KEY)
            .addQueryParameter(TS_QUERY_PARAM, HARDCODED_TS) //using these static values for simplicity
            .addQueryParameter(HASH_QUERY_PARAM, HARDCODED_HASH)
            .build()

        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}