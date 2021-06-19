package com.application.moviessearch.di

import com.application.moviessearch.api.MoviesApi
import com.application.moviessearch.data.MoviesPagingSource
import com.application.moviessearch.data.MoviesRepository
import com.application.moviestest.retrofit.ServiceGenerator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMoviesRepository(moviesApi: MoviesApi,moviesPagingSource: MoviesPagingSource): MoviesRepository {
        return MoviesRepository(moviesApi,moviesPagingSource)
    }

    @Singleton
    @Provides
    fun providesPagingSource(apiService:MoviesApi): MoviesPagingSource {
        return MoviesPagingSource(apiService)
    }

    @Singleton
    @Provides
    fun providesMoviesAPi(serviceGenerator: ServiceGenerator):MoviesApi {
        return serviceGenerator.createService(MoviesApi::class.java)
    }



}