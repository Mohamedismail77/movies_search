package com.application.moviessearch.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.application.moviessearch.R
import com.application.moviessearch.databinding.FragmentMovieDetailsBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    private val args by navArgs<MovieDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMovieDetailsBinding.bind(view)

        binding.apply {
            val movie = args.movie

            Glide.with(this@MovieDetailsFragment)
                .load("https://image.tmdb.org/t/p/w500${movie.backdropPath}")
                .error(R.drawable.ic_error)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.isVisible = false
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.isVisible = false
                        movieTitle.isVisible = true
                        movieRate.isVisible = true
                        description.isVisible = true
                        releaseData.isVisible = true
                        return false
                    }
                })
                .into(imageView)

            movieTitle.text = movie.originalTitle
            movieRate.text = "${movie.voteAverage}"
            description.text = movie.overview
            releaseData.text = movie.releaseDate

        }

    }

}