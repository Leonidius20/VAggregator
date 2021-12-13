package io.github.leonidius20.vaggregator.data.movies.providers.toloka

import retrofit2.http.GET
import retrofit2.http.Query

interface TolokaService {

    companion object {
        val BASE_URL = "https://toloka.to/"
    }

    @GET("api.php")
    suspend fun findMovies(@Query("search") q: String): List<TolokaMovie>

}