package io.github.leonidius20.vaggregator.data.videos.providers.dailymotion

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface DailymotionService {

    companion object {
        val BASE_URL = "https://api.dailymotion.com/"

        val instance: DailymotionService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(DailymotionService::class.java)
        }
    }

    // https://api.dailymotion.com/videos?channel=news&search=trump&fields=id,title,channel,owner.screenname,created_time,description,thumbnail_360_url,url

    @GET("videos")
    suspend fun findVideos(
        @Query("search") q: String,
        @Query("channel") category: String,
        @Query("fields") fields: String =
            "id,title,channel,owner.screenname,created_time,description,thumbnail_360_url,url"
    ): DailymotionSearchResponse

}