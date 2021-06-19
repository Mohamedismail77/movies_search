package com.application.moviessearch.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.application.moviessearch.api.MoviesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val apiService: MoviesApi,private val moviesPagingSource: MoviesPagingSource) {

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



}