package io.github.leonidius20.vaggregator.data.videos.providers.dailymotion

import retrofit2.http.GET
import retrofit2.http.Query

interface DailymotionService {

    companion object {
        val BASE_URL = "https://api.vimeo.com/"

        val ACCESS_TOKEN = "a6a41583a2fc5eb517106b43226c8c89"
    }

    @GET("categories/{category}/videos")
    suspend fun findVideos(
        @Query("query") q: String,
        category: String,
    ) {

    }

}