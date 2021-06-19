package com.application.moviessearch.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.application.moviessearch.data.Movie
import com.application.moviessearch.data.MoviesRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

class MoviesListViewModel @ViewModelInject constructor (private val moviesRepository: MoviesRepository):ViewModel() {


    private var currentSearchResult: Flow<PagingData<Movie>>? = null


    fun getMoviesList(): Flow<PagingData<Movie>> {
        val newResult: Flow<PagingData<Movie>> = moviesRepository.getPopularMoviesList()
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }

    fun searchMovie(query: CharSequence):Flow<PagingData<Movie>> {
        val newResult: Flow<PagingData<Movie>> = moviesRepository.getMoviesByQuery(query.toString())
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }

}