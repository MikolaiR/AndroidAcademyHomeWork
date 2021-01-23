package com.example.androidacademyhomework.data.network

import com.example.androidacademyhomework.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val url = original.url.newBuilder()
            .addQueryParameter("api_key", BuildConfig.MOVIE_DATABASE_API_KEY).build()
        original = original.newBuilder().url(url).build()
        return chain.proceed(original)
    }
}