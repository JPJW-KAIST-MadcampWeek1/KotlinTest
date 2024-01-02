package com.example.tabandroid.ideal_worldcup

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tabandroid.MainActivity
import com.example.tabandroid.R

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        // Assuming you're using a FragmentManager to handle fragments
        val startButton: ImageView = findViewById(R.id.startButton)
        startButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("loadFragment", "FourthFragment") // Put an extra to indicate which fragment to load
        }
            startActivity(intent)
            finish()
        }
    }
}