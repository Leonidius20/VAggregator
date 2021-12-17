package io.github.leonidius20.vaggregator.data.movies.providers

import io.github.leonidius20.vaggregator.data.movies.Movie

interface MovieProvider {

    suspend fun findMovies(q: String): List<Movie>

    val name: String

}