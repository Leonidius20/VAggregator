package io.github.leonidius20.vaggregator.data.providers

import com.google.gson.annotations.SerializedName

// TODO: extend some BaseMovie
data class ThePirateBayMovie(

    val id: String,

    val name: String,

    @SerializedName("info_hash")
    val infoHash: String,

    val leechers: Int,

    val seeders: Int,

    @SerializedName("num_files")
    val numFiles: Int,

    val size: Long,

    val username: String,

    val added: Long,

    val status: String,

    val category: Int,

    val imdb: String,

)
