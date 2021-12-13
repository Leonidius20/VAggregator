package io.github.leonidius20.vaggregator.data.videos

import io.github.leonidius20.vaggregator.data.videos.providers.YoutubeService

class VideosRepository {

    private val youtubeProvider = YoutubeService.instance

    suspend fun findVideos(q: String): List<Video> {
        val videos = mutableListOf<Video>()
        // TODO: categories
        videos.addAll(youtubeProvider.findVideos(q, 10).items.map {
            with(it.snippet) {
                Video(title, publishedAt, description, channelTitle, "YouTube", "https://google.com", thumbnails.default.url)
            }
        })

        return videos
    }

}