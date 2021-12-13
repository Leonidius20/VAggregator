package io.github.leonidius20.vaggregator.data.videos.providers.dailymotion

import com.google.gson.annotations.SerializedName

data class DailymotionSearchResponse(
    val list: Array<DailymotionVideo>
)

data class DailymotionVideo(
    val id: String,

    @SerializedName("title")
    val name: String,

    @SerializedName("channel")
    val category: String,

    @SerializedName("owner.screenname")
    val uploadedBy: String,

    @SerializedName("created_time")
    val date: Long,

    val description: String,

    val url: String,

    @SerializedName("thumbnail_360_url")
    val thumbnailUrl: String,

)