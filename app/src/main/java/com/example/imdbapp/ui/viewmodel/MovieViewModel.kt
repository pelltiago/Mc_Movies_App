package com.example.imdbapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbapp.data.domain.GetMoviesUseCase
import com.example.imdbapp.data.domain.SearchMoviesUseCase
import com.example.imdbapp.data.model.MovieModel
import com.example.imdbapp.data.model.MovieResponse
import kotlinx.coroutines.launch

class MovieViewModel() : ViewModel() {
    val movieModel = MutableLiveData<MovieResponse?>()
    var getMoviesUseCase = GetMoviesUseCase()
    var searchMoviesUseCase = SearchMoviesUseCase()
    fun onCreate() {
        viewModelScope.launch {
            val result = getMoviesUseCase.invoke()
            movieModel.postValue(result)
        }
    }

    fun searchMovie(name: String) {
        viewModelScope.launch {
            val result = searchMoviesUseCase.invoke(name)
            movieModel.postValue(result)
        }
    }
}

