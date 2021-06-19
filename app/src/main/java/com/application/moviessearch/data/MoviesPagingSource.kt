package com.application.moviessearch.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.application.moviessearch.api.MoviesApi
import com.application.moviessearch.data.MoviesRepository.Companion.MAX_API_PAGES
import com.application.moviessearch.data.MoviesRepository.Companion.PAGE_SIZE
import com.application.moviestest.MovieDB
import retrofit2.HttpException
import java.io.IOException

private const val MOVIE_DB_STARTING_INDEX_PAGE = 1


class MoviesPagingSource(
        private val apiService: MoviesApi,
        private val movieDB: MovieDB
    ): PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: MOVIE_DB_STARTING_INDEX_PAGE

        return try {
            val moviesPage = apiService.searchPhotos(page) ?: emptyList()
            val nextKey = if (moviesPage.isEmpty() || page == MAX_API_PAGES) {
                null
            } else {
                // initial load size = 3 * NETWORK_PAGE_SIZE
                // ensure we're not requesting duplicating items, at the 2nd request
                page + (params.loadSize / PAGE_SIZE)
            }
            movieDB.moviesDao().insertAll(moviesPage)
            LoadResult.Page(
                    data = moviesPage,
                    prevKey = if(page == MOVIE_DB_STARTING_INDEX_PAGE) null else page -1,
                    nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                    ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}