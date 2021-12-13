package io.github.leonidius20.vaggregator.data.videos.providers

data class YoutubeSearchResponse(

    val items: Array<YoutubeSearchResultItem>

)

data class YoutubeSearchResultItem(

    val snippet: YoutubeResultSnippet

)

data class YoutubeResultSnippet (

    val title: String,
    val publishedAt: String,
    val description: String,
    val channelTitle: String,
    val thumbnails: YoutubeThumbnails,

)

data class YoutubeThumbnails(

    val default: YoutubeThumbnail,

)

data class YoutubeThumbnail(
    val url: String,
    val width: Int,
    val height: Int,
)