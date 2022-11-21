package com.e444er.dataapi.remote

import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url.newBuilder().addQueryParameter("api_key", TMDB_API_KEY).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }

    companion object {
        const val TMDB_API_KEY = "271236134afbbdcd24c3caaaab027824"
    }
}
