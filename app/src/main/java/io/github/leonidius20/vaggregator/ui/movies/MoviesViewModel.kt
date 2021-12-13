package io.github.leonidius20.vaggregator.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.leonidius20.vaggregator.data.movies.Movie
import io.github.leonidius20.vaggregator.data.movies.MoviesRepository
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {

    private val repository = MoviesRepository()



    // val searchQuery = MutableLiveData<String>()

    val movies = MutableLiveData<List<Movie>>()



    fun loadMovies(q: String) {
        viewModelScope.launch {
            movies.value = repository.findMoves(q)
        }
    }

}