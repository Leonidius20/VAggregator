package io.github.leonidius20.vaggregator.data

import io.github.leonidius20.vaggregator.data.providers.ThePirateBayMovie
import io.github.leonidius20.vaggregator.data.providers.ThePirateBayRetrofitClient
import io.github.leonidius20.vaggregator.data.providers.ThePirateBayService

class MoviesRepository {

    private val tpbProvider: ThePirateBayService = ThePirateBayRetrofitClient.instance.api

    // TODO: return list of generic movies not TPB ones
    suspend fun findMoves(query: String): List<ThePirateBayMovie> {
        return tpbProvider.findMovies(query)
    }

}