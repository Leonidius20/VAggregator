package io.github.leonidius20.vaggregator.ui.movie_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.leonidius20.vaggregator.data.PieceOfContent

class MovieDetailsViewModel: ViewModel() {

    val selectedMovie = MutableLiveData<PieceOfContent>()

    fun select(movie: PieceOfContent) { selectedMovie.value = movie }

}