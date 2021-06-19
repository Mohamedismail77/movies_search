package com.application.moviessearch.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.moviessearch.data.Movie
import com.application.moviessearch.databinding.MovieCardBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class MoviesAdapter(private val onItemClickListener: OnItemClickListener) : PagingDataAdapter<Movie, MoviesAdapter.MovieViewHolder>(MOVIE_COMPARATOR) {

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentMovie = getItem(position)
        if (currentMovie != null) {
            holder.bind(currentMovie)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(MovieCardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    interface OnItemClickListener {
        fun onItemClick(movie: Movie)
    }


    inner class MovieViewHolder(private val movieCardBinding: MovieCardBinding): RecyclerView.ViewHolder(movieCardBinding.root) {

        init {
            movieCardBinding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        onItemClickListener.onItemClick(item)
                    }
                }
            }
        }

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

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem
        }
    }


}