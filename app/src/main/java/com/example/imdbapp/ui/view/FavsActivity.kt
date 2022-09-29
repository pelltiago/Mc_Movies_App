package com.example.imdbapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.imdbapp.data.room.DBMovies
import com.example.imdbapp.data.room.MovieRoom
import com.example.imdbapp.databinding.ActivityFavsBinding
import com.example.imdbapp.ui.FavsAdapter
import kotlinx.coroutines.runBlocking

class FavsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavsBinding
    private lateinit var room: DBMovies
    private var listMoviesFav: List<MovieRoom>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavsBinding.inflate(layoutInflater)

        setContentView(binding.root)
        room = Room.databaseBuilder(this, DBMovies::class.java, "MovieRoom").build()
       runBlocking {
            listMoviesFav = room.daoMovie().getMoviesRoom()
        }

        binding.rvFavs.layoutManager = LinearLayoutManager(this)
        binding.rvFavs.adapter = listMoviesFav?.let { FavsAdapter(this, it) }
    }
}
