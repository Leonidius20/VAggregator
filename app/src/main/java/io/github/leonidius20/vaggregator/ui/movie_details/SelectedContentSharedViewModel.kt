package io.github.leonidius20.vaggregator.ui.movie_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.leonidius20.vaggregator.data.PieceOfContent
import io.github.leonidius20.vaggregator.data.library.LibraryRepository
import kotlinx.coroutines.launch

/**
 * A ViewModel used to share the selected video or movie
 * between Video/MovieFragment and MovieDetailsFragment
 */
class SelectedContentSharedViewModel: ViewModel() {

    private val libraryRepository = LibraryRepository.instance

    val selectedMovie = MutableLiveData<PieceOfContent>()

    val isContentSavedToLibrary = MutableLiveData<Boolean>()

    fun select(movie: PieceOfContent) {
        selectedMovie.value = movie
        viewModelScope.launch {
            isContentSavedToLibrary.value = checkIfContentSaved()
        }
    }

    private suspend fun checkIfContentSaved() =
        libraryRepository.existsWithId(selectedMovie.value!!.id)

    fun removeFromLibrary() {
        viewModelScope.launch {
            libraryRepository.delete(selectedMovie.value!!)
            isContentSavedToLibrary.value = false
        }
    }

    fun saveToLibrary() {
        viewModelScope.launch {
            libraryRepository.save(selectedMovie.value!!)
            isContentSavedToLibrary.value = true
        }
    }

}