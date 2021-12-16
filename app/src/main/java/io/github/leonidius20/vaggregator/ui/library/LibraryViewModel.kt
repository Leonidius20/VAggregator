package io.github.leonidius20.vaggregator.ui.library

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.leonidius20.vaggregator.data.PieceOfContent
import io.github.leonidius20.vaggregator.data.library.LibraryRepository
import kotlinx.coroutines.launch

class LibraryViewModel : ViewModel() {

    private val repository = LibraryRepository.instance

    val data = MutableLiveData<List<PieceOfContent>>()

    fun loadData() {
        viewModelScope.launch {
            data.value = repository.getAll()
        }
    }
}