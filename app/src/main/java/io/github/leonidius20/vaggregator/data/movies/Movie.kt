package io.github.leonidius20.vaggregator.data.movies

import io.github.leonidius20.vaggregator.data.PieceOfContent

data class Movie(

    override val name: String,

    val sizeString: String,

    val seeders: Int,

    val leechers: Int,

    override val link: String,

    override val provider: String,

): PieceOfContent(0, name, "Size: ${sizeString}", null, null, provider, link) {

    override val description: String
        get() = "Size: ${sizeString}"

    override val thumbnailUrl: String?
        get() = null

    override val bigThumbnailUrl: String?
        get() = null

}