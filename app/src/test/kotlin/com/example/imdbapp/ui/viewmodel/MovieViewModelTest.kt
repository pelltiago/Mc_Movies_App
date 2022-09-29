package com.example.imdbapp.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.imdbapp.data.domain.GetMoviesUseCase
import com.example.imdbapp.data.domain.SearchMoviesUseCase
import com.example.imdbapp.data.model.MovieResponse
import com.example.imdbapp.data.model.Results
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieViewModelTest{

    @RelaxedMockK
    lateinit var getMoviesUseCase: GetMoviesUseCase

    @RelaxedMockK
    lateinit var searchMoviesUseCase: SearchMoviesUseCase

    private lateinit var movieViewModel: MovieViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        movieViewModel = MovieViewModel()
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun whenViewModelIsCreatedGetAllMovies() = runTest {
        val movieResponse = MovieResponse(1, arrayListOf(Results()),1,1)
        coEvery { getMoviesUseCase() } returns movieResponse
        movieViewModel.onCreate()
        assert(movieViewModel.movieModel.value == movieResponse)
    }
}