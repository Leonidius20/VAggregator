package io.github.leonidius20.vaggregator.ui.movie_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.leonidius20.vaggregator.data.Movie

class MovieDetailsViewModel: ViewModel() {

    val selectedMovie = MutableLiveData<Movie>()

    fun select(movie: Movie) { selectedMovie.value = movie }

}