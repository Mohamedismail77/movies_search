package com.application.moviessearch.api

import androidx.viewbinding.BuildConfig
import com.application.moviessearch.data.Movie
import com.application.moviestest.retrofit.Qualifiers
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    companion object{
        const val API_KEY = "21d3fd73c011bff94f9ef84b4924d747"
        const val DEFAULT_LANGUAGE = "en_Us"

    }

    @Qualifiers.Envelope
    @GET("3/movie/popular?api_key=${API_KEY}&language${DEFAULT_LANGUAGE}")
    suspend fun searchPhotos(
        @Query("page") page: Int
    ): List<Movie>?

}