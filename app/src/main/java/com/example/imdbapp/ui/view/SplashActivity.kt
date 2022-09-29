package com.example.imdbapp.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.imdbapp.R

class SplashActivity : AppCompatActivity() {

    override fun onBackPressed() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.SplashTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mainNavigation()
    }

    private fun mainNavigation() {
            val mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
        }
    }
