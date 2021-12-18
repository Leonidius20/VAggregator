package io.github.leonidius20.vaggregator.data.videos.providers

import io.github.leonidius20.vaggregator.data.videos.Video
import io.github.leonidius20.vaggregator.data.videos.VideoCategory

/**
 * @param CT type of category argument for request (int or string)
 */
interface VideoProvider {

    suspend fun findVideos(q: String, category: VideoCategory): List<Video>

    val name: String

}