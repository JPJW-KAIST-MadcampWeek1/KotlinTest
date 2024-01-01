package com.example.tabandroid.ideal_worldcup

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.tabandroid.MainActivity
import com.example.tabandroid.R
import com.example.tabandroid.ThirdFragment
import com.example.tabandroid.databinding.ActivityWinnerBinding
import com.example.tabandroid.databinding.FragmentFourthBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.timer


class WinnerActivity : AppCompatActivity() {
    val binding by lazy { ActivityWinnerBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityWinnerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        // Get the data passed from the previous activity
//        binding.winnerImageView = intent.getIntExtra("winner_image_res_id", 0)
        binding.winnerTextView.text = intent.getStringExtra("winnerTextView")
        binding.winnerImageView.setImageResource(intent.getIntExtra("winnerImageView",0))

        binding.restartButton.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("finished","true")
            startActivity(intent)
        }

        }
    }
