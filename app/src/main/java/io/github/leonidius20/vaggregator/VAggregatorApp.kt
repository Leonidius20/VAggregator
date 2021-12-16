package io.github.leonidius20.vaggregator

import android.app.Application
import androidx.room.Room
import io.github.leonidius20.vaggregator.data.library.ContentDatabase

class VAggregatorApp: Application() {

    companion object {
        lateinit var libraryDb: ContentDatabase
    }

    override fun onCreate() {
        super.onCreate()
        libraryDb = Room.databaseBuilder(
            applicationContext,
            ContentDatabase::class.java, "library-database"
        ).build()
    }

}