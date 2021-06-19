package com.application.moviessearch.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movies")
data class Movie(
    @field:SerializedName("backdrop_path") val backdropPath: String?,
    @PrimaryKey val id: Long,
    @field:SerializedName("original_title")val originalTitle: String,
    val overview: String,
    @field:SerializedName("poster_path")val posterPath: String?,
    @field:SerializedName("release_date")val releaseDate: String,
    val title: String,
    @field:SerializedName("vote_average")val voteAverage: Double,
    ): Parcelable