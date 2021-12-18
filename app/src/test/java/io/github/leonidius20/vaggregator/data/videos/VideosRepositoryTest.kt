package io.github.leonidius20.vaggregator.data.videos

import io.github.leonidius20.vaggregator.data.Resource
import io.github.leonidius20.vaggregator.data.videos.providers.VideoProvider
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class VideosRepositoryTest {

    @Test
    fun testResultMerging() = runBlocking {
        val fakeProvider1 = object : VideoProvider {
            override suspend fun findVideos(q: String, category: VideoCategory): List<Video> {
                return listOf(
                    Video("Star Wars", "12", "Desc", "Channel1", name, "", "", ""),
                    Video("Star Trek", "55", "Desc2", "Channel2", name, "https://startrek.com", "", ""),
                )
            }

            override val name: String get() = "fake1"

        }

        val fakeProvider2 = object : VideoProvider {
            override suspend fun findVideos(q: String, category: VideoCategory): List<Video> {
                return listOf(
                    Video("Some Video", "12", "Desc", "Channel3", name, "", "", ""),
                )
            }

            override val name: String get() = "fake2"

        }

        val repository = VideosRepository(arrayOf(fakeProvider1, fakeProvider2))

        val actual = repository.findVideos("", VideoCategory.PETS_AND_ANIMALS)

        val predicted = Resource.success(listOf(
            Video("Star Wars", "12", "Desc", "Channel1", "fake1", "", "", ""),
            Video("Star Trek", "55", "Desc2", "Channel2", "fake1", "https://startrek.com", "", ""),
            Video("Some Video", "12", "Desc", "Channel3", "fake2", "", "", ""),
        ))

        Assert.assertEquals(predicted, actual)
    }

    @Test
    fun testWithFaultyProviders() = runBlocking { // some faulty some not
        val fakeProvider1 = object : VideoProvider {
            override suspend fun findVideos(q: String, category: VideoCategory): List<Video> {
                throw Exception()
            }

            override val name: String get() = "Faulty1"
        }

        val fakeProvider2 = object : VideoProvider {
            override suspend fun findVideos(q: String, category: VideoCategory): List<Video> {
                throw Exception()
            }

            override val name: String get() = "Faulty2"
        }

        val fakeProvider3 = object : VideoProvider {
            override suspend fun findVideos(q: String, category: VideoCategory): List<Video> {
                return listOf(
                    Video("Some Video", "12", "Desc", "Channel3", "fake2", "", "", ""),
                )
            }

            override val name: String get() = "fake3"
        }

        val repository = VideosRepository(arrayOf(fakeProvider1, fakeProvider2, fakeProvider3))

        val actual = repository.findVideos("", VideoCategory.AUTO)

        val predicted = Resource.success(listOf(
            Video("Some Video", "12", "Desc", "Channel3", "fake2", "", "", ""),
        ), "Couldn't load from Faulty1, Faulty2")

        Assert.assertEquals(predicted, actual)
    }

}