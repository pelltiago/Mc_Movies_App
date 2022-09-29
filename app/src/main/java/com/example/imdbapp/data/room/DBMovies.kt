package com.example.imdbapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MovieRoom::class],
    version = 1
)

abstract class DBMovies: RoomDatabase() {
    abstract fun daoMovie(): DaoMovie
}