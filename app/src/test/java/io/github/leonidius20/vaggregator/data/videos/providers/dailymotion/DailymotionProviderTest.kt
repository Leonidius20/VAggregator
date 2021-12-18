package io.github.leonidius20.vaggregator.data.videos.providers.dailymotion

import io.github.leonidius20.vaggregator.data.videos.Video
import io.github.leonidius20.vaggregator.data.videos.VideoCategory
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class DailymotionProviderTest {

    val provider = DailymotionProvider(object : DailymotionService {
        override suspend fun findVideos(q: String, category: String, fields: String): DailymotionSearchResponse {
            return DailymotionSearchResponse(arrayOf(
                DailymotionVideo("fs", "Video1", "auto", "channel1", 36, "Description", "url", "thumb_url")
            ))
        }

    })

    @Test
    fun testNullCategory() = runBlocking {
        val category = VideoCategory.DOCUMENTARY
        Assert.assertNull(category.dailymotionCategory)

        val actual = provider.findVideos("", category)

        Assert.assertEquals(emptyList<Video>(), actual)
    }

    @Test
    fun conversionTest() = runBlocking {
        val category = VideoCategory.AUTO
        Assert.assertNotNull(category.dailymotionCategory)

        val expected = listOf(
            Video("Video1", "36", "Description", "channel1", "Dailymotion", "url", "thumb_url", "thumb_url")
        )

        Assert.assertEquals(expected, provider.findVideos("", category))
    }

}