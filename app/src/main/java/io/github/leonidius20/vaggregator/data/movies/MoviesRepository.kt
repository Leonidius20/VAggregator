package io.github.leonidius20.vaggregator.data.movies

import io.github.leonidius20.vaggregator.data.Resource
import io.github.leonidius20.vaggregator.data.movies.providers.toloka.TolokaRetrofitClient
import io.github.leonidius20.vaggregator.data.movies.providers.tpb.ThePirateBayRetrofitClient

class MoviesRepository {

    private val tpbProvider = ThePirateBayRetrofitClient.api
    private val tolokaProvider = TolokaRetrofitClient.api

    suspend fun findMoves(query: String): Resource<List<Movie>> {
        val movies = mutableListOf<Movie>()

        val faultyServices = mutableListOf<String>()

        try {
            val tpbMovies = tpbProvider.findMovies(query)
            if (!(tpbMovies.size == 1 && tpbMovies[0].id == "0")) { // the "no results returned"
                movies.addAll(tpbMovies.map {
                    Movie(it.name, it.sizeString, it.seeders, it.leechers, it.link, it.provider)
                })
            }
        } catch (e: Exception) {
            e.printStackTrace()
            faultyServices.add("The Pirate Bay")
        }

        try {
            movies.addAll(tolokaProvider.findMovies(query).map {
                Movie(it.name, it.sizeString, it.seeders, it.leechers, it.link, it.provider)
            })
        } catch (e: Exception) {
            e.printStackTrace()
            faultyServices.add("Toloka")
        }

        if (faultyServices.isEmpty()) {
            return Resource.success(movies)
        } else {
            val errorMessage = "Couldn't load from " + faultyServices.joinToString()
            return Resource.success(movies, errorMessage)
        }
    }

}