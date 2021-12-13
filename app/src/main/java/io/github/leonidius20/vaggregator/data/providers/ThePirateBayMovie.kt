package io.github.leonidius20.vaggregator.data.providers

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

// TODO: extend some BaseMovie
@Parcelize
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

) : Parcelable {

    public val sizeString get() = "%.2f MB".format(size / 1048576F)

}
