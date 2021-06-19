package com.application.moviessearch.di

import android.content.Context
import com.application.moviessearch.api.MoviesApi
import com.application.moviessearch.data.MoviesPagingSource
import com.application.moviessearch.data.MoviesRepository
import com.application.moviestest.MovieDB
import com.application.moviestest.retrofit.ServiceGenerator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMoviesRepository(movieDB: MovieDB, moviesPagingSource: MoviesPagingSource): MoviesRepository {
        return MoviesRepository(movieDB,moviesPagingSource)
    }

    @Singleton
    @Provides
    fun providesPagingSource(apiService:MoviesApi,movieDB: MovieDB): MoviesPagingSource {
        return MoviesPagingSource(apiService,movieDB)
    }

    @Singleton
    @Provides
    fun providesMoviesAPi(serviceGenerator: ServiceGenerator):MoviesApi {
        return serviceGenerator.createService(MoviesApi::class.java)
    }


    @Singleton
    @Provides
    fun provideMovieDB(@ApplicationContext context: Context): MovieDB {
        return MovieDB.getInstance(context)
    }

}