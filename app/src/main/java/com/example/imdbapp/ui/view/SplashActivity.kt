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
        val sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        val email = sharedPreferences.getString("email", null)
        if (email.isNullOrEmpty()) {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        } else {
            val mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
        }
    }
}