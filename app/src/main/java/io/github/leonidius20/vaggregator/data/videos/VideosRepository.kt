package io.github.leonidius20.vaggregator.data.videos

import io.github.leonidius20.vaggregator.data.Resource
import io.github.leonidius20.vaggregator.data.videos.providers.VideoProvider
import io.github.leonidius20.vaggregator.data.videos.providers.dailymotion.DailymotionProvider
import io.github.leonidius20.vaggregator.data.videos.providers.vimeo.VimeoProvider
import io.github.leonidius20.vaggregator.data.videos.providers.youtube.YoutubeProvider

class VideosRepository(private val providers: Array<VideoProvider> =
    arrayOf(YoutubeProvider(), DailymotionProvider(), VimeoProvider())) {

    suspend fun findVideos(q: String, category: VideoCategory): Resource<List<Video>> {
        val videos = mutableListOf<Video>()

        val faultyServices = mutableListOf<String>()

        for (provider in providers) {
            try {
                videos.addAll(provider.findVideos(q, category))
            } catch (e: Exception) {
                e.printStackTrace()
                faultyServices.add(provider.name)
            }
        }

        if (faultyServices.isEmpty()) {
            return Resource.success(videos)
        } else {
            val errorMessage = "Couldn't load from " + faultyServices.joinToString()
            return Resource.success(videos, errorMessage)
        }
    }

}