package com.example.imdbapp.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MovieRoom {

    @PrimaryKey
    var title: String
    var posterPath: String? = null

    constructor(title: String, posterPath: String?) {
        this.title = title
        this.posterPath = posterPath
    }

}