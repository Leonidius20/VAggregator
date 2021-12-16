package io.github.leonidius20.vaggregator.data.videos

import io.github.leonidius20.vaggregator.data.PieceOfContent

data class Video(
    override val name: String,
    val publishedAt: String,
    override val description: String,
    val channelTitle: String,
    override val provider: String,
    override val link: String,
    override val thumbnailUrl: String,
    override val bigThumbnailUrl: String?,
): PieceOfContent(0, name, description, thumbnailUrl, bigThumbnailUrl, provider, link)