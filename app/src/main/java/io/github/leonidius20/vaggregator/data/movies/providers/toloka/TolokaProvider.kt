package io.github.leonidius20.vaggregator.data.movies.providers.toloka

import io.github.leonidius20.vaggregator.data.movies.Movie
import io.github.leonidius20.vaggregator.data.movies.providers.MovieProvider
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TolokaProvider(private val api: TolokaService = getApi()): MovieProvider {

    override val name: String
        get() = "Toloka"

     // for testing purposes
    companion object {
         private fun getApi(): TolokaService {
             val retrofit = Retrofit.Builder()
                 .baseUrl(TolokaService.BASE_URL)
                 .addConverterFactory(GsonConverterFactory.create())
                 .build()
             return retrofit.create(TolokaService::class.java)
         }
    }


    override suspend fun findMovies(q: String): List<Movie> {
        return api.findMovies(q).map { convertToloka(it) }
    }

    private fun convertToloka(it: TolokaMovie): Movie {
        return Movie(it.name, it.sizeString, it.seeders, it.leechers, it.link, it.provider)
    }

}