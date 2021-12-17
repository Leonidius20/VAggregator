package io.github.leonidius20.vaggregator.ui.videos

import io.github.leonidius20.vaggregator.data.PieceOfContent
import io.github.leonidius20.vaggregator.data.Resource
import io.github.leonidius20.vaggregator.data.videos.VideoCategory
import io.github.leonidius20.vaggregator.data.videos.VideosRepository
import io.github.leonidius20.vaggregator.ui.base.search.BaseSearchViewModel

class VideosViewModel : BaseSearchViewModel() {

    private val repository = VideosRepository()

    var selectedCategory = VideoCategory.PETS_AND_ANIMALS

    override suspend fun obtainData(): Resource<List<PieceOfContent>> {
        return repository.findVideos(searchQuery, selectedCategory)
    }

}