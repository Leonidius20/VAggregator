package io.github.leonidius20.vaggregator.data.movies.providers.tpb

import io.github.leonidius20.vaggregator.data.movies.Movie
import io.github.leonidius20.vaggregator.data.movies.providers.MovieProvider
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.CharacterIterator
import java.text.StringCharacterIterator
import java.util.*


class ThePirateBayProvider(private val api: ThePirateBayService = getApi()): MovieProvider {

    override val name: String
        get() = "The Pirate Bay"

    companion object {
        fun getApi(): ThePirateBayService {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(ThePirateBayService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ThePirateBayService::class.java)
        }
    }


    override suspend fun findMovies(q: String): List<Movie> {
        val tpbMovies = api.findMovies(q)

        if (tpbMovies.size == 1 && tpbMovies[0].id == "0") { // the "no results returned"
            return emptyList()
        }

        return tpbMovies.map { convertThePirateBay(it) }
    }

    private fun convertThePirateBay(it: ThePirateBayMovie): Movie {
        return Movie(it.name, sizeToString(it.size), it.seeders, it.leechers, it.link, it.provider)
    }

    private fun sizeToString(bytesVal: Long): String {
        var bytes = bytesVal
        if (-1000 < bytes && bytes < 1000) {
            return "$bytes B"
        }
        val ci: CharacterIterator = StringCharacterIterator("kMGTPE")
        while (bytes <= -999950 || bytes >= 999950) {
            bytes /= 1000
            ci.next()
        }

        return String.format(Locale.UK, "%.2f %cB", bytes / 1000.0, ci.current())
    }

}