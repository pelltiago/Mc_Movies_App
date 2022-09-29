package com.example.imdbapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DaoMovie {

    @Query("SELECT * FROM MovieRoom")
    suspend fun getMoviesRoom() : List<MovieRoom>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieRoom(movie: MovieRoom)

}