package com.example.imdbapp.ui.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imdbapp.data.model.MovieFavModel
import com.example.imdbapp.data.model.Results
import com.example.imdbapp.databinding.ActivityMainBinding
import com.example.imdbapp.ui.MainAdapter
import com.example.imdbapp.ui.viewmodel.MovieViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class MainActivity : AppCompatActivity(), MainAdapter.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private val movieViewModel: MovieViewModel by viewModels()
    private val db = FirebaseFirestore.getInstance()

    override fun onBackPressed() {}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        movieViewModel.onCreate()
        setupRecyclerView()
        initListeners()
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

        binding.btnLogout.setOnClickListener {
            logout()
        }
        binding.btnFav.setOnClickListener {

            val sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
            val email = sharedPreferences.getString("email", null)

            val favIntent = Intent(this, FavsActivity::class.java).putExtra("userMail", email)
            startActivity(favIntent)

        }
    }

    private fun logout() {
        FirebaseAuth.getInstance().signOut()
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply {
            putString("email", null)
        }.apply()
        val loginIntent = Intent(this, LoginActivity::class.java)
        startActivity(loginIntent)
    }

    private fun setupRecyclerView() {
        binding.rvMovies.layoutManager = LinearLayoutManager(this)
        movieViewModel.movieModel.observe(this, Observer { result ->
            binding.rvMovies.adapter = result?.results?.toList()?.let { MainAdapter(this, it, this) }
        })
    }

    override fun onClick(movie: Results) {
        Toast.makeText(this, "Pelicula ${movie.title} agregada a favoritos", Toast.LENGTH_LONG).show()
       }
    }

