package com.example.imdbapp.data.domain

import com.example.imdbapp.data.MovieRepository
import com.example.imdbapp.data.model.MovieResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetMoviesUseCaseTest {
    @RelaxedMockK
    private lateinit var movieRepository: MovieRepository

    @RelaxedMockK
    private lateinit var movieResponse: MovieResponse

    @RelaxedMockK
    lateinit var getMoviesUseCase: GetMoviesUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getMoviesUseCase = GetMoviesUseCase()
    }

    @Test
    fun useCaseResponseNotNullBeforeCall() = runBlocking {
        val response = movieResponse
        coEvery { movieRepository.getAllMovies() } returns response
        val result = getMoviesUseCase.invoke()

        assertNotNull(result)
    }
}