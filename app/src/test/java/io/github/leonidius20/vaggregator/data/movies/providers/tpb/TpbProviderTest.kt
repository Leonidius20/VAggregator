package io.github.leonidius20.vaggregator.data.movies.providers.tpb

import io.github.leonidius20.vaggregator.data.movies.Movie
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class TpbProviderTest {

    @Test
    fun testConversion() = runBlocking {
        val tpbProvider = ThePirateBayProvider(object : ThePirateBayService {
            override suspend fun findMovies(q: String, category: Int): List<ThePirateBayMovie> {
                return listOf(
                    ThePirateBayMovie("id",  "Star Wars", "X57648t", 2, 2, 2, 6765234887, "h", 0, "", 0, "0"), //6gb
                    ThePirateBayMovie("id2", "Star Trek", "785yvervtner", 2, 4, 1,2454765, "y", 1, "", 9, "0") // 2mb
                )
            }

        })

        val converted = tpbProvider.findMovies("any")

        fun getMagnet(infoHash: String, nameEncoded: String): String {
            return "magnet:?xt=urn:btih:${infoHash}&dn=${nameEncoded}&tr=udp%3A%2F%2Ftracker.coppersurfer.tk" +
                    "%3A6969%2Fannounce&tr=udp%3A%2F%2F9.rarbg.to%3A2920%2Fannounce&tr=udp%3" +
                    "A%2F%2Ftracker.opentrackr.org%3A1337&tr=udp%3A%2F%2Ftracker.internetwar" +
                    "riors.net%3A1337%2Fannounce&tr=udp%3A%2F%2Ftracker.leechers-paradise.or" +
                    "g%3A6969%2Fannounce&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969%2Fann" +
                    "ounce&tr=udp%3A%2F%2Ftracker.pirateparty.gr%3A6969%2Fannounce&tr=udp%3A" +
                    "%2F%2Ftracker.cyberia.is%3A6969%2Fannounce"
        }

        val predicted = listOf(
            Movie("Star Wars", "6.77 GB", 2, 2, getMagnet("X57648t", "Star+Wars"), "The Pirate Bay"),
            Movie("Star Trek", "2.45 MB", 4, 2, getMagnet("785yvervtner", "Star+Trek"), "The Pirate Bay"),
        )

        Assert.assertEquals(predicted, converted)
    }

    @Test
    fun testEmptyResponse() = runBlocking {
        val tpbProvider = ThePirateBayProvider(object : ThePirateBayService {
            override suspend fun findMovies(q: String, category: Int): List<ThePirateBayMovie> {
                return listOf(
                    ThePirateBayMovie("0",  "No results returned", "0000000000000000000000000000000000000000", 0, 0, 0, 0, "", 0, "member", 0, "")
                )
            }

        })

        assert(tpbProvider.findMovies("f").isEmpty())
    }

}