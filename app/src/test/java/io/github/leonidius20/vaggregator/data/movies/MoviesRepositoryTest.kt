package io.github.leonidius20.vaggregator.data.movies

import io.github.leonidius20.vaggregator.data.Resource
import io.github.leonidius20.vaggregator.data.movies.providers.MovieProvider
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class MoviesRepositoryTest {

    @Test
    fun testResultMerging() = runBlocking {
        val fakeProvider1 = object : MovieProvider {
            override suspend fun findMovies(q: String): List<Movie> {
                return listOf(
                    Movie("Star Wars", "15 GB", 2, 2, "https://starwars.com", "Toloka"),
                    Movie("Star Trek", "1 GB", 2, 4, "https://startrek.com", "Toloka"),
                )
            }

            override val name: String get() = "fake1"

        }



        val fakeProvider2 = object : MovieProvider {
            override suspend fun findMovies(q: String): List<Movie> {
                return listOf(
                    Movie("A Star is born", "2.3 GB", 5, 3, "https://google.com", "The Pirate Bay"),
                )
            }

            override val name: String get() = "fake2"

        }

        val repository = MoviesRepository(arrayOf(fakeProvider1, fakeProvider2))

        val actual = repository.findMoves("")

        val predicted = Resource.success(listOf(
            Movie("Star Wars", "15 GB", 2, 2, "https://starwars.com", "Toloka"),
            Movie("Star Trek", "1 GB", 2, 4, "https://startrek.com", "Toloka"),
            Movie("A Star is born", "2.3 GB", 5, 3, "https://google.com", "The Pirate Bay"),
        ))

        Assert.assertEquals(predicted, actual)
    }

    @Test
    fun testWithFaultyProviders() = runBlocking { // some faulty some not
        val fakeProvider1 = object : MovieProvider {
            override suspend fun findMovies(q: String): List<Movie> {
                throw Exception()
            }

            override val name: String get() = "Faulty1"
        }

        val fakeProvider2 = object : MovieProvider {
            override suspend fun findMovies(q: String): List<Movie> {
                throw Exception()
            }

            override val name: String get() = "Faulty2"
        }

        val fakeProvider3 = object : MovieProvider {
            override suspend fun findMovies(q: String): List<Movie> {
                return listOf(
                    Movie("A Star is born", "2.3 GB", 5, 3, "https://google.com", "The Pirate Bay"),
                )
            }

            override val name: String get() = "fake3"
        }

        val repository = MoviesRepository(arrayOf(fakeProvider1, fakeProvider2, fakeProvider3))

        val actual = repository.findMoves("")

        val predicted = Resource.success(listOf(
           Movie("A Star is born", "2.3 GB", 5, 3, "https://google.com", "The Pirate Bay"),
        ), "Couldn't load from Faulty1, Faulty2")

        Assert.assertEquals(predicted, actual)
    }

}