package com.example.imdbapp.data.domain

import com.example.imdbapp.data.MovieRepository
import com.example.imdbapp.data.model.MovieModel
import com.example.imdbapp.data.model.MovieResponse
import com.example.imdbapp.ui.view.MainActivity

class GetMoviesUseCase() {
    private val repository = MovieRepository()
    suspend operator fun invoke():MovieResponse? {
        return repository.getAllMovies()
    }
    
}