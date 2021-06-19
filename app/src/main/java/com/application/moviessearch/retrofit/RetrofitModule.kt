package com.application.moviestest.retrofit

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun providesServiceGenerator(retrofit: Retrofit): ServiceGenerator {
        return ServiceGenerator(retrofit)
    }

    @Provides
    @Singleton
    fun providesRetrofitClient(
        envelopResponseConverter: EnvelopResponseConverter
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(envelopResponseConverter)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesEnvelopResponseConverter(): EnvelopResponseConverter {
        return EnvelopResponseConverter()
    }

}