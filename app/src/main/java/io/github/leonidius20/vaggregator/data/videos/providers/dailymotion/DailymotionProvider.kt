package io.github.leonidius20.vaggregator.data.videos.providers.dailymotion

import io.github.leonidius20.vaggregator.data.videos.Video
import io.github.leonidius20.vaggregator.data.videos.VideoCategory
import io.github.leonidius20.vaggregator.data.videos.providers.VideoProvider
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DailymotionProvider(private val api: DailymotionService = getApi()) : VideoProvider {

    override suspend fun findVideos(q: String, category: VideoCategory): List<Video> {
        if (category.dailymotionCategory == null) return emptyList()

        return api.findVideos(q, category.dailymotionCategory).list
            .map { convertToVideo(it) }
    }

    private fun convertToVideo(it: DailymotionVideo): Video {
        return Video(
                    it.name,
                    it.date.toString(),
                    it.description,
                    it.uploadedBy,
                    name,
                    it.url,
                    it.thumbnailUrl,
                    it.thumbnailUrl
                )
    }

    override val name: String
        get() = "Dailymotion"

    companion object {
        private fun getApi(): DailymotionService {
            val retrofit = Retrofit.Builder()
                .baseUrl(DailymotionService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(DailymotionService::class.java)
        }
    }

}