package com.application.moviestest

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.application.moviessearch.data.Movie


@Database(
    entities = [Movie::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDB : RoomDatabase() {

    abstract fun moviesDao(): MovieDao

    companion object {

        @Volatile
        private var INSTANCE: MovieDB? = null

        fun getInstance(context: Context): MovieDB =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                MovieDB::class.java, "movies.db")
                .build()
    }
}