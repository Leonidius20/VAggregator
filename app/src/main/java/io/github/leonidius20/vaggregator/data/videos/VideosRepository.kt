package io.github.leonidius20.vaggregator.data.videos

import io.github.leonidius20.vaggregator.data.videos.providers.dailymotion.DailymotionService
import io.github.leonidius20.vaggregator.data.videos.providers.youtube.YoutubeService

class VideosRepository {

    private val youtubeProvider = YoutubeService.instance
    private val dailymotionProvider = DailymotionService.instance

    suspend fun findVideos(q: String, category: VideoCategory): List<Video> {
        val videos = mutableListOf<Video>()

        if (category.youtubeId != null) {
            val youtubeVideos = youtubeProvider.findVideos(q, category.youtubeId).items
                .map {
                    val link = "https://www.youtube.com/watch?v=${it.id.videoId}"
                    with(it.snippet) {
                        Video(title, publishedAt, description, channelTitle, "YouTube",
                            link, thumbnails.default.url)
                }
            }

            videos.addAll(youtubeVideos)
        }

        if (category.dailymotionCategory != null) {
            val dailymotionVids = dailymotionProvider.findVideos(q, category.dailymotionCategory).list.map {
                Video(it.name,
                    // TODO: fix date
                    it.date.toString(), it.description, it.uploadedBy, "Dailymotion", it.url, it.thumbnailUrl)
            }
            videos.addAll(dailymotionVids)
        }

        if (category.vimeoCategory != null) {
            // TODO
        }

        return videos
    }

}