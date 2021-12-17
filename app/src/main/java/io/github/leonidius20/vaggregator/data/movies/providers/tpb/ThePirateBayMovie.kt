package io.github.leonidius20.vaggregator.data.movies.providers.tpb

import com.google.gson.annotations.SerializedName
import java.net.URLEncoder


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

) {

    val provider: String get() = "The Pirate Bay"

    val link: String get() = "magnet:?xt=urn:btih:${infoHash}&dn=${URLEncoder.encode(name)}&tr=udp%3A%2F%2Ftracker.coppersurfer.tk" +
            "%3A6969%2Fannounce&tr=udp%3A%2F%2F9.rarbg.to%3A2920%2Fannounce&tr=udp%3" +
            "A%2F%2Ftracker.opentrackr.org%3A1337&tr=udp%3A%2F%2Ftracker.internetwar" +
            "riors.net%3A1337%2Fannounce&tr=udp%3A%2F%2Ftracker.leechers-paradise.or" +
            "g%3A6969%2Fannounce&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969%2Fann" +
            "ounce&tr=udp%3A%2F%2Ftracker.pirateparty.gr%3A6969%2Fannounce&tr=udp%3A" +
            "%2F%2Ftracker.cyberia.is%3A6969%2Fannounce"

}