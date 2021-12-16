package io.github.leonidius20.vaggregator.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
open class PieceOfContent(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    open val name: String,

    open val description: String,

    open val thumbnailUrl: String?,

    open val bigThumbnailUrl: String?,

    open val provider: String,

    open val link: String,
)