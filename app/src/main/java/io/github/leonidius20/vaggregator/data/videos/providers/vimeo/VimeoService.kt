package io.github.leonidius20.vaggregator.data.videos.providers.vimeo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface VimeoService {

    companion object {
        val BASE_URL = "https://api.vimeo.com/"

        val ACCESS_TOKEN = "a6a41583a2fc5eb517106b43226c8c89"

        val instance: VimeoService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(VimeoService::class.java)
        }

    }

    @GET("categories/{category}/videos")
    suspend fun findVideos(
        @Query("query") q: String,
        @Path("category") category: String,
        @Header("Authorization") token: String = ACCESS_TOKEN,
    )

}