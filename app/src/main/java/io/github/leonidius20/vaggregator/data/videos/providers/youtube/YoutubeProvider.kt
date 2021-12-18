package io.github.leonidius20.vaggregator.data.videos.providers.youtube

import io.github.leonidius20.vaggregator.data.videos.Video
import io.github.leonidius20.vaggregator.data.videos.VideoCategory
import io.github.leonidius20.vaggregator.data.videos.providers.VideoProvider
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class YoutubeProvider(private val api: YoutubeService = getApi()): VideoProvider {

    override suspend fun findVideos(q: String, category: VideoCategory): List<Video> {
        if (category.youtubeId == null) return emptyList()

        return api.findVideos(q, category.youtubeId).items
            .map { convertToVideo(it) }
    }

    private fun convertToVideo(it: YoutubeSearchResultItem): Video {
        val link = "https://www.youtube.com/watch?v=${it.id.videoId}"
        with(it.snippet) {
            return Video(
                title, publishedAt, description, channelTitle,
                this@YoutubeProvider.name, link,
                thumbnails.default.url, thumbnails.high.url
            )
        }
    }

    override val name: String
        get() = "YouTube"

    companion object {
        private fun getApi(): YoutubeService {
            val retrofit = Retrofit.Builder()
                .baseUrl(YoutubeService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(YoutubeService::class.java)
        }
    }

}