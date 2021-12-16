package io.github.leonidius20.vaggregator.data.library

import io.github.leonidius20.vaggregator.VAggregatorApp
import io.github.leonidius20.vaggregator.data.PieceOfContent

class LibraryRepository {

    companion object {
        val instance: LibraryRepository by lazy { LibraryRepository() }
    }

    private val contentDao = VAggregatorApp.libraryDb.pieceOfContentDao()

    suspend fun save(pieceOfContent: PieceOfContent) {
        contentDao.save(pieceOfContent)
    }

    suspend fun getAll() = contentDao.getAll()

    suspend fun delete(pieceOfContent: PieceOfContent) {
        contentDao.delete(pieceOfContent)
    }

    suspend fun existsWithId(id: Int) = contentDao.pieceExistsWithId(id)

}