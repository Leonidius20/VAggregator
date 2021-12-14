package io.github.leonidius20.vaggregator.data

abstract class PieceOfContent {

    abstract val name: String

    abstract val description: String

    abstract val thumbnailUrl: String?

    abstract val bigThumbnailUrl: String?

    abstract val provider: String

    abstract val link: String

}


