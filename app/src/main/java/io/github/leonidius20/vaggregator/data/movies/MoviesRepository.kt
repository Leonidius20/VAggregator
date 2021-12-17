package io.github.leonidius20.vaggregator.data.movies

import io.github.leonidius20.vaggregator.data.Resource
import io.github.leonidius20.vaggregator.data.movies.providers.MovieProvider
import io.github.leonidius20.vaggregator.data.movies.providers.toloka.TolokaProvider
import io.github.leonidius20.vaggregator.data.movies.providers.tpb.ThePirateBayProvider

class MoviesRepository(private val providers: Array<MovieProvider> =
    arrayOf(ThePirateBayProvider(), TolokaProvider())) {

    suspend fun findMoves(query: String): Resource<List<Movie>> {
        val movies = mutableListOf<Movie>()

        val faultyServices = mutableListOf<String>()

        for (provider in providers) {
            try {
                movies.addAll(provider.findMovies(query))
            } catch (e: Exception) {
                e.printStackTrace()
                faultyServices.add(provider.name)
            }
        }

        if (faultyServices.isEmpty()) {
            return Resource.success(movies)
        } else {
            val errorMessage = "Couldn't load from " + faultyServices.joinToString()
            return Resource.success(movies, errorMessage)
        }
    }

}