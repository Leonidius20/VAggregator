package io.github.leonidius20.vaggregator.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.leonidius20.vaggregator.data.MoviesRepository
import io.github.leonidius20.vaggregator.data.providers.ThePirateBayMovie
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {

    private val repository = MoviesRepository()

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }

    // val searchQuery = MutableLiveData<String>()

    val movies = MutableLiveData<List<ThePirateBayMovie>>()

    val text: LiveData<String> = _text

    fun loadMovies(q: String) {
        viewModelScope.launch {
            movies.value = repository.findMoves(q)
        }
    }

}