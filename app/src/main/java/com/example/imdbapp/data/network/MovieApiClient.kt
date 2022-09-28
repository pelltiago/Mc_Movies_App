package com.example.imdbapp.data.network

import com.example.imdbapp.data.model.MovieResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiClient {
    @GET("movie/popular?api_key=865ddb3ffc428072298c15ca1494ee21")
    suspend fun getAllMovies(): Response<MovieResponse>

    @GET("movie?api_key=865ddb3ffc428072298c15ca1494ee21&query")
    suspend fun searchMovies(@Query("query") name: String): Response<MovieResponse>
}