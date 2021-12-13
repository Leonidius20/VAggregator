package io.github.leonidius20.vaggregator.data.movies.providers.tpb

import retrofit2.http.GET
import retrofit2.http.Query

interface ThePirateBayService {

    companion object {
        val BASE_URL = "https://apibay.org/"
    }

    @GET("q.php")
    suspend fun findMovies(@Query("q") q: String, @Query("cat") category: Int = 200): List<ThePirateBayMovie>

}