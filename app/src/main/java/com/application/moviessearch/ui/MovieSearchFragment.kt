package com.application.moviessearch.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.application.moviessearch.R
import com.application.moviessearch.data.Movie
import com.application.moviessearch.databinding.FragmentMovieSearchBinding
import com.application.moviessearch.databinding.FragmentMoviesListBinding
import com.application.moviestest.MoviesLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MovieSearchFragment : Fragment(R.layout.fragment_movie_search),MoviesAdapter.OnItemClickListener,
    TextWatcher {

    private val moviesListViewModel by viewModels<MoviesListViewModel>()

    private var _binding: FragmentMovieSearchBinding? = null
    private val binding get() = _binding!!

    private var queryTextChangedJob: Job? = null
    private var lastQuery = ""
    private var adapter:MoviesAdapter = MoviesAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMovieSearchBinding.bind(view)


        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.itemAnimator = null
            recyclerView.adapter = adapter
        }

        binding.searchQuery.addTextChangedListener(this)

    }



    override fun onItemClick(movie: Movie) {
        val action = MovieSearchFragmentDirections.actionMovieSearchFragmentToMovieDetailsFragment(movie)
        findNavController().navigate(action)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s != null) {
                binding.recyclerView.scrollToPosition(0)
                queryTextChangedJob?.cancel()
                queryTextChangedJob = CoroutineScope(Dispatchers.Main).launch {
                    delay(500)
                    if (!(lastQuery.equals(s) || s.isBlank())) {
                        moviesListViewModel.searchMovie(s).collect {
                            adapter.submitData(it)
                        }
                    }
                }
            }
    }

    override fun afterTextChanged(s: Editable?) {
    }

}