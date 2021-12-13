package io.github.leonidius20.vaggregator.data.videos.providers.youtube

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeService {

    companion object {
        val BASE_URL = "https://www.googleapis.com/youtube/v3/"
        val API_KEY = "AIzaSyCsEVQztb23MzF4ekBYB9PUUVThTICjW-U"

        // TODO: Hilt dependency injection?
        val instance: YoutubeService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(YoutubeService::class.java)
        }

    }

    @GET("search")
    suspend fun findVideos(
        @Query("q") q: String,
        @Query("videoCategoryId") videoCategoryId: Int,
        @Query("part") part: String = "snippet",
        @Query("type") type: String = "video",
        @Query("key") key: String = API_KEY
    ): YoutubeSearchResponse

    // ttps://www.googleapis.com/youtube/v3/search?part=snippet&q=bieber&type=video&videoCategoryId=10&key={YOUR_API_KEY}
}