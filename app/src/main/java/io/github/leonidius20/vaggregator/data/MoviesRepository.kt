package io.github.leonidius20.vaggregator.data

import io.github.leonidius20.vaggregator.data.providers.toloka.TolokaRetrofitClient
import io.github.leonidius20.vaggregator.data.providers.tpb.ThePirateBayRetrofitClient

class MoviesRepository {

    private val tpbProvider = ThePirateBayRetrofitClient.api
    private val tolokaProvider = TolokaRetrofitClient.api

    suspend fun findMoves(query: String): List<Movie> {
        val movies = mutableListOf<Movie>()
        movies.addAll(tpbProvider.findMovies(query).map {
            Movie(it.name, it.sizeString, it.seeders, it.leechers, it.link, it.provider)
        })
        movies.addAll(tolokaProvider.findMovies(query).map {
            Movie(it.name, it.sizeString, it.seeders, it.leechers, it.link, it.provider)
        })
        return movies
    }

}