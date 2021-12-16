package io.github.leonidius20.vaggregator.data.library

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.github.leonidius20.vaggregator.data.PieceOfContent

@Dao
interface PieceOfContentDao {

    @Query("select * from PieceOfContent")
    suspend fun getAll(): List<PieceOfContent>

    @Query("select exists(select * from PieceOfContent where id = :id)")
    suspend fun pieceExistsWithId(id: Int): Boolean

    @Insert
    suspend fun save(pieceOfContent: PieceOfContent)

    @Delete
    suspend fun delete(pieceOfContent: PieceOfContent)

}