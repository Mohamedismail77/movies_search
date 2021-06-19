package com.application.moviestest

import androidx.paging.PagingSource
import androidx.room.*
import com.application.moviessearch.data.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<Movie>)

    @Query("SELECT * FROM movies")
    fun getAllMovies():PagingSource<Int, Movie>


    @Query("SELECT * FROM movies WHERE title LIKE :queryString OR voteAverage LIKE :queryString")
    fun moviesByQuery(queryString: String): PagingSource<Int, Movie>



}