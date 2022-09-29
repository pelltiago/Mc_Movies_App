package com.example.imdbapp.ui.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.imdbapp.data.model.Results
import com.example.imdbapp.data.room.DBMovies
import com.example.imdbapp.data.room.MovieRoom
import com.example.imdbapp.databinding.ActivityMainBinding
import com.example.imdbapp.ui.MainAdapter
import com.example.imdbapp.ui.viewmodel.MovieViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), MainAdapter.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private val movieViewModel: MovieViewModel by viewModels()
    private lateinit var room : DBMovies

    override fun onBackPressed() {}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        movieViewModel.onCreate()
        initListeners()
        setupRecyclerView()
        room = Room.databaseBuilder(this, DBMovies::class.java, "MovieRoom").build()
    }

    private fun initListeners() {
        binding.searchview.setOnQueryTextListener(object :
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.linearshimmer.isVisible = true
                binding.rvMovies.isVisible = false
                movieViewModel.searchMovie(query!!)
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.linearshimmer.isVisible = false
                    setupRecyclerView()
                    binding.rvMovies.isVisible = true
                }, 4000)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        binding.btnFav.setOnClickListener {

            val favIntent = Intent(this, FavsActivity::class.java)
            startActivity(favIntent)

        }
    }

    private fun setupRecyclerView() {
        binding.rvMovies.layoutManager = LinearLayoutManager(this)
        movieViewModel.movieModel.observe(this, Observer { result ->
            binding.rvMovies.adapter =
                result?.results?.toList()?.let { MainAdapter(this, it, this) }
        })
    }

    override fun onClick(movie: Results) {
        Toast.makeText(this, "Pelicula ${movie.title} agregada a favoritos", Toast.LENGTH_LONG)
            .show()
        lifecycleScope.launch {
            room.daoMovie().addMovieRoom(MovieRoom(movie.title!!, movie.posterPath))
        }
    }
}

