package com.example.imdbapp.data

import android.util.Log
import com.example.imdbapp.core.RetrofitHelper
import com.example.imdbapp.core.RetrofitHelperSearch
import com.example.imdbapp.data.model.MovieResponse
import com.example.imdbapp.data.network.MovieApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieService {
    private val retrofit = RetrofitHelper.getRetrofit()
    private val retrofitSearch = RetrofitHelperSearch.getRetrofitSearch()
    suspend fun getMovies(): MovieResponse? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(MovieApiClient::class.java).getAllMovies()
            Log.e("log get all movies", response.body().toString())
            response.body()
        }
    }
    suspend fun searchMovies(name : String): MovieResponse? {
        return withContext(Dispatchers.IO) {
            val response = retrofitSearch.create(MovieApiClient::class.java).searchMovies(name)
            Log.e("log search", response.body().toString())
            response.body()
        }
    }
}