package com.example.tabandroid

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class ImageDisplayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_display)

        val imageUrl = intent.getStringExtra("image_url") // Get the image URL from intent extras

        // Load the image into an ImageView using Glide or any other image loading library
        val imageView = findViewById<ImageView>(R.id.largeImageView)
        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.ic_baseline_image_24)
            .into(imageView)
    }
}