package io.github.leonidius20.vaggregator.ui.videos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.leonidius20.vaggregator.data.videos.Video
import io.github.leonidius20.vaggregator.data.videos.VideoCategory
import io.github.leonidius20.vaggregator.data.videos.VideosRepository
import kotlinx.coroutines.launch

class VideosViewModel : ViewModel() {

    private val repository = VideosRepository()

    val videos = MutableLiveData<List<Video>>()

    var selectedCategory = VideoCategory.PETS_AND_ANIMALS

    fun loadVideos(q: String, category: VideoCategory) { // TODO: add category
        viewModelScope.launch {
            videos.value = repository.findVideos(q, category)
        }
    }

}