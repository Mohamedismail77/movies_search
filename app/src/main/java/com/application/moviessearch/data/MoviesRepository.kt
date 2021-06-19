package com.application.moviessearch.data

import androidx.paging.*
import com.application.moviessearch.api.MoviesApi
import com.application.moviestest.MovieDB
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val movieDB: MovieDB,
                                           private val moviesPagingSource: MoviesPagingSource
                                           ) {

    companion object{
        const val PAGE_SIZE = 20
        const val MAX_API_PAGES = 1000
    }

    fun getPopularMoviesList(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                maxSize = MAX_API_PAGES
            ),
            pagingSourceFactory = {moviesPagingSource}
        ).flow
    }


    fun getMoviesByQuery(query:String):Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                maxSize = MAX_API_PAGES
            ),
            pagingSourceFactory = {if (query.isBlank()) movieDB.moviesDao().getAllMovies() else movieDB.moviesDao().moviesByQuery(query)}
        ).flow
    }


}