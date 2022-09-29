package com.example.imdbapp.data

import android.util.Log
import com.example.imdbapp.data.model.MovieProvider
import com.example.imdbapp.data.model.MovieResponse

class MovieRepository {
    private val api = MovieService()

    suspend fun getAllMovies():MovieResponse?{
        val response = api.getMovies()
        if (response != null) {
            MovieProvider.movies = response
        }
        return response
    }

    suspend fun searchMovieByName(name: String) : MovieResponse? {
        val response = api.searchMovies(name)
        if (response != null) {
            MovieProvider.movies = response
        }
        return response
    }
}