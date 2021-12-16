package io.github.leonidius20.vaggregator.data.videos

import io.github.leonidius20.vaggregator.data.Resource
import io.github.leonidius20.vaggregator.data.videos.providers.dailymotion.DailymotionService
import io.github.leonidius20.vaggregator.data.videos.providers.vimeo.VimeoService
import io.github.leonidius20.vaggregator.data.videos.providers.youtube.YoutubeService

class VideosRepository {

    private val youtubeProvider = YoutubeService.instance
    private val dailymotionProvider = DailymotionService.instance
    private val vimeoProvider = VimeoService.instance

    suspend fun findVideos(q: String, category: VideoCategory): Resource<List<Video>> {
        val videos = mutableListOf<Video>()

        val faultyServices = mutableListOf<String>()

        if (category.youtubeId != null) {
            try {
                val youtubeVideos = youtubeProvider.findVideos(q, category.youtubeId).items
                    .map {
                        val link = "https://www.youtube.com/watch?v=${it.id.videoId}"
                        with(it.snippet) {
                            Video(
                                title, publishedAt, description, channelTitle, "YouTube",
                                link, thumbnails.default.url, thumbnails.high.url
                            )
                        }
                    }
                videos.addAll(youtubeVideos)
            } catch (e: Exception) {
                e.printStackTrace()
                faultyServices.add("Youtube")
            }
        }

        if (category.dailymotionCategory != null) {
            try {
                val dailymotionVids = dailymotionProvider
                    .findVideos(q, category.dailymotionCategory).list
                    .map {
                        Video(
                            it.name,
                            it.date.toString(),
                            it.description,
                            it.uploadedBy,
                            "Dailymotion",
                            it.url,
                            it.thumbnailUrl,
                            it.thumbnailUrl
                        )
                }
                videos.addAll(dailymotionVids)
            } catch (e: Exception) {
                e.printStackTrace()
                faultyServices.add("Dailymotion")
            }
        }

        if (category.vimeoCategory != null) {
            try {
                throw Exception("Vimeo API is broken, we need to wait until they fix it")
            } catch (e: Exception) {
                // e.printStackTrace()
                // faultyServices.add("Vimeo")
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