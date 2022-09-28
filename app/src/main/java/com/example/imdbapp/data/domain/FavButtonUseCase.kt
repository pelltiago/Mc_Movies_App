package com.example.imdbapp.data.domain

import com.example.imdbapp.data.MovieRepository
import com.example.imdbapp.data.model.MovieResponse

class FavButtonUseCase {
    private val repository = MovieRepository()
    suspend operator fun invoke(): MovieResponse? {
        return repository.getAllMovies()
    }
}