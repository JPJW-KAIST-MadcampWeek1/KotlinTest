package com.example.tabandroid.ideal_worldcup

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tabandroid.R
import com.example.tabandroid.databinding.ActivityWinnerBinding
import com.example.tabandroid.databinding.FragmentFourthBinding


class WinnerActivity : AppCompatActivity() {
    val binding by lazy { ActivityWinnerBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityWinnerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the data passed from the previous activity
//        binding.winnerImageView = intent.getIntExtra("winner_image_res_id", 0)
        binding.winnerTextView.text = intent.getStringExtra("winnerTextView")
        binding.winnerImageView.setImageResource(intent.getIntExtra("winnerImageView",0))

        // Find the views in the layout
//        val imageView: ImageView = findViewById(R.id.winnerImageView)
//        val textView: TextView = findViewById(R.id.winnerTextView)
//
//        // Set the image and name of the winner
//        if (winnerImageResId != 0) {
//            imageView.setImageResource(winnerImageResId)
//        }
//        textView.text = winnerName

        }
    }
