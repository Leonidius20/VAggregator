package io.github.leonidius20.vaggregator.data.library

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.leonidius20.vaggregator.data.PieceOfContent

@Database(entities = [PieceOfContent::class], version = 1, exportSchema = false)
abstract class ContentDatabase: RoomDatabase() {

    abstract fun pieceOfContentDao(): PieceOfContentDao

}