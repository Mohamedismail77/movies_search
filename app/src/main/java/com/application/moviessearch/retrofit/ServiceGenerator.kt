package com.application.moviestest.retrofit

import retrofit2.Retrofit
import javax.inject.Inject

class ServiceGenerator @Inject constructor(private val retrofit: Retrofit) {
    fun <S> createService(serviceClass: Class<S>?): S {
        return retrofit.create(serviceClass)
    }

    companion object {
        lateinit var retrofitInstance: Retrofit
    }

    init {
        retrofitInstance = retrofit
    }
}