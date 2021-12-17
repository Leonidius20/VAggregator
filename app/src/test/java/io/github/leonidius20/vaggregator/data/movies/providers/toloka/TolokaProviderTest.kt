package io.github.leonidius20.vaggregator.data.movies.providers.toloka

import io.github.leonidius20.vaggregator.data.movies.Movie
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class TolokaProviderTest {

    var toloka: TolokaProvider = TolokaProvider(object : TolokaService {

        override suspend fun findMovies(q: String): List<TolokaMovie> {
            return listOf(
                TolokaMovie(0, "https://starwars.com", "Star Wars", "Movies", "All", 420, "15 GB", 2, 2, 0),
                TolokaMovie(0, "https://startrek.com", "Star Trek", "Movies", "All", 40, "1 GB", 2, 4, 1),
            )
        }

    })

    @Test
    fun testConversion() = runBlocking {
        val converted = toloka.findMovies("any")

        val predicted = listOf(
            Movie("Star Wars", "15 GB", 2, 2, "https://starwars.com", "Toloka"),
            Movie("Star Trek", "1 GB", 2, 4, "https://startrek.com", "Toloka"),
        )

        assertEquals(predicted, converted)
    }

}