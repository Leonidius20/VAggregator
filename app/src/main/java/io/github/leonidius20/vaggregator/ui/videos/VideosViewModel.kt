package io.github.leonidius20.vaggregator.ui.videos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.leonidius20.vaggregator.data.videos.Video
import io.github.leonidius20.vaggregator.data.videos.VideosRepository
import kotlinx.coroutines.launch

class VideosViewModel : ViewModel() {

    private val repository = VideosRepository()

    val videos = MutableLiveData<List<Video>>()

    fun loadVideos(q: String) { // TODO: add category
        viewModelScope.launch {
            videos.value = repository.findVideos(q)
        }
    }

}