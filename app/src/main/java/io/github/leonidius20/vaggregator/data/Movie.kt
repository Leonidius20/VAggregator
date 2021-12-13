package io.github.leonidius20.vaggregator.data

data class Movie(

    val name: String,

    val sizeString: String,

    val seeders: Int,

    val leechers: Int,

    val link: String,

    val provider: String,

)