package io.github.leonidius20.vaggregator.ui.library

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.leonidius20.vaggregator.data.library.LibraryRepository

class LibraryViewModel : ViewModel() {

    private val repository = LibraryRepository.instance

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}