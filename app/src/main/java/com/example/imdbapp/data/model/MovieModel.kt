package com.example.imdbapp.data.model

import com.google.gson.annotations.SerializedName

data class MovieModel(@SerializedName("quote") val name: String,
                      @SerializedName("author") val description: String, val image: String)
