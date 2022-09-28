package com.example.imdbapp.ui.view

import android.graphics.Movie
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.imdbapp.R
import com.example.imdbapp.data.model.MovieFavModel
import com.example.imdbapp.data.model.Results
import com.example.imdbapp.databinding.ActivityFavsBinding
import com.example.imdbapp.databinding.ActivityMainBinding
import com.example.imdbapp.ui.FavsAdapter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class FavsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavsBinding
                private val list: ArrayList<Any> = arrayListOf()
private val favList: MutableList<MovieFavModel>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavsBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val db = FirebaseFirestore.getInstance()

        db.collection("users").document(intent.getStringExtra("userMail")!!).collection("moviesFav").document("OcZj9JZTzX4AqlTdIUQW")
            .get().addOnSuccessListener {


                    Log.e("peli", it.toString())
                    list.add(it.get("title") as String)
                    list.add(it.get("posterPath") as String)
                    Log.e("EL BUENO", list.toString())
                    Log.e("EL BUENO2", list.first().toString())


        binding.rvFavs.adapter = favList?.let { FavsAdapter(this,
            list.toList() as List<MovieFavModel>
        ) }
            }
    }
}