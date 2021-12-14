package io.github.leonidius20.vaggregator.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.leonidius20.vaggregator.data.Resource
import io.github.leonidius20.vaggregator.data.movies.Movie
import io.github.leonidius20.vaggregator.data.movies.MoviesRepository
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {

    private val repository = MoviesRepository()

    val movies = MutableLiveData<Resource<List<Movie>>>()

    fun loadMovies(q: String) {
        movies.value = Resource.loading(null)
        viewModelScope.launch {
            try {
                movies.value = Resource.success(repository.findMoves(q))
            } catch (e: Exception) {
                movies.value = Resource.error(e.message!!, null)
            }
        }
    }

}