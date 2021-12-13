package io.github.leonidius20.vaggregator.data.videos

import io.github.leonidius20.vaggregator.data.videos.providers.dailymotion.DailymotionService
import io.github.leonidius20.vaggregator.data.videos.providers.youtube.YoutubeService

class VideosRepository {

    private val youtubeProvider = YoutubeService.instance
    private val dailymotionProvider = DailymotionService.instance

    suspend fun findVideos(q: String): List<Video> {
        val videos = mutableListOf<Video>()
        // TODO: categories
        videos.addAll(youtubeProvider.findVideos(q, 10).items.map {
            val link = "https://www.youtube.com/watch?v=${it.id.videoId}"
            with(it.snippet) {
                Video(title, publishedAt, description, channelTitle, "YouTube",
                    link, thumbnails.default.url)
            }
        })

        val dailymotionVids = dailymotionProvider.findVideos(q, "animals").list.map {
            Video(it.name,
                // TODO: fix date
                it.date.toString(), it.description, it.uploadedBy, "Dailymotion", it.url, it.thumbnailUrl)
        }
        videos.addAll(dailymotionVids)

        return videos
    }

}