package io.github.leonidius20.vaggregator.data.movies.providers.toloka

import com.google.gson.annotations.SerializedName


data class TolokaMovie(

    val id: Int,

    val link: String,

    @SerializedName("title")
    val name: String,

    @SerializedName("forum_name")
    val forumName: String,

    @SerializedName("forum_parent")
    val forumParent: String,

    @SerializedName("comments")
    val commentsNum: Int,

    @SerializedName("size")
    val sizeString: String,

    val seeders: Int,

    val leechers: Int,

    val complete: Int, // idk what it is

) {

    val provider: String get() =  "Toloka"

}