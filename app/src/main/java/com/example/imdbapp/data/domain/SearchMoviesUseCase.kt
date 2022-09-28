package com.example.imdbapp.data.domain

import com.example.imdbapp.data.MovieRepository
import com.example.imdbapp.data.model.MovieResponse

class SearchMoviesUseCase() {
        private val repository = MovieRepository()
        suspend operator fun invoke(name: String): MovieResponse? {
            return repository.searchMovieByName(name)
        }
}