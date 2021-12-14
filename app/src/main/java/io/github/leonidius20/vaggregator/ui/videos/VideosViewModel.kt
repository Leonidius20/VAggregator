package io.github.leonidius20.vaggregator.ui.videos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.leonidius20.vaggregator.data.Resource
import io.github.leonidius20.vaggregator.data.videos.Video
import io.github.leonidius20.vaggregator.data.videos.VideoCategory
import io.github.leonidius20.vaggregator.data.videos.VideosRepository
import kotlinx.coroutines.launch

class VideosViewModel : ViewModel() {

    private val repository = VideosRepository()

    val videos = MutableLiveData<Resource<List<Video>>>()

    var selectedCategory = VideoCategory.PETS_AND_ANIMALS

    fun loadVideos(q: String, category: VideoCategory) {
        videos.value = Resource.loading(null)
        viewModelScope.launch {
            try {
                videos.value = Resource.success(repository.findVideos(
                    q, category))
            } catch (e: Exception) {
                Resource.error(e.message!!, null)
            }

        }
    }

}