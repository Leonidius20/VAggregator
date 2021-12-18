package io.github.leonidius20.vaggregator.data.videos.providers.vimeo

import io.github.leonidius20.vaggregator.data.videos.Video
import io.github.leonidius20.vaggregator.data.videos.VideoCategory
import io.github.leonidius20.vaggregator.data.videos.providers.VideoProvider
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class VimeoProvider(private val api: VimeoService = getApi()): VideoProvider {

    override suspend fun findVideos(q: String, category: VideoCategory): List<Video> {
        if (category.vimeoCategory == null) return emptyList()
        return emptyList() // TODO
    }

    private fun convertToVideo(): Video {
        TODO()
    }

    override val name: String
        get() = "Vimeo"

    companion object {
        private fun getApi(): VimeoService {
            val retrofit = Retrofit.Builder()
                .baseUrl(VimeoService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(VimeoService::class.java)
        }
    }

}