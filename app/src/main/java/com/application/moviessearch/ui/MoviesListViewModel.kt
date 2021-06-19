package com.application.moviessearch.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.application.moviessearch.data.Movie
import com.application.moviessearch.data.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesListViewModel @ViewModelInject constructor (private val moviesRepository: MoviesRepository):ViewModel() {


    private var currentSearchResult: Flow<PagingData<Movie>>? = null

    fun getMoviesList(): Flow<PagingData<Movie>> {
        val newResult: Flow<PagingData<Movie>> = moviesRepository.getPopularMoviesList()
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }

}