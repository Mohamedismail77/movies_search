package com.application.moviessearch.ui

import androidx.recyclerview.widget.RecyclerView
import com.application.moviessearch.data.Movie
import com.application.moviessearch.databinding.MovieCardBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class MovieViewHolder(private val movieCardBinding: MovieCardBinding):RecyclerView.ViewHolder(movieCardBinding.root) {



    fun bind (movie:Movie) {
        movieCardBinding.apply{
            movieTitle.text = movie.title
            movieRate.text = "${movie.voteAverage}"
            releaseData.text = movie.releaseDate
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(moviePoster)
        }
    }

}