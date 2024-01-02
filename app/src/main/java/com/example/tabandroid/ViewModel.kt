package com.example.tabandroid

import android.app.Application
import android.content.Context
import com.example.tabandroid.R
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

class ImageViewModel(application: Application) : AndroidViewModel(application) {
    val imagesList: MutableList<Uri> = mutableListOf()
    init {
        // Example: Adding drawables to imagesList
        val drawableResources = listOf(
//            R.drawable.cat1,
            R.drawable.cat2,
//            R.drawable.cat3,
//            R.drawable.cat4,
//            R.drawable.cat5,
//            R.drawable.cat6,
//            R.drawable.cat7,
//            R.drawable.cat8,
//            R.drawable.cat9,
//            R.drawable.cat10,
//            R.drawable.cat11,
//            R.drawable.cat12,
//            R.drawable.cat13,
//            R.drawable.cat14,
//            R.drawable.cat15,
//            R.drawable.cat16,
//            R.drawable.cat17,
//            R.drawable.cat18,
//            R.drawable.cat19,
//            R.drawable.cat20,
            R.drawable.cat2,
            R.drawable.cat2,
            R.drawable.cat2,
            R.drawable.cat2,
            R.drawable.cat2,
            R.drawable.cat2,
            R.drawable.cat2,
            R.drawable.cat2,
            R.drawable.cat2,
            R.drawable.cat2,
            R.drawable.cat2,
            R.drawable.cat2,
            R.drawable.cat2,
            R.drawable.cat2,
            R.drawable.cat2,
            R.drawable.cat2,
            R.drawable.cat2,
            R.drawable.cat2,
            R.drawable.cat2,




            // Add more drawable resource IDs here
        )

        // Convert drawable resource IDs to URIs and add them to imagesList
        drawableResources.forEach { resourceId ->
            val uri = getUriFromDrawable(application, resourceId)
            imagesList.add(uri)
        }
    }

    private fun getUriFromDrawable(context: Context, drawableId: Int): Uri {
        return Uri.parse("android.resource://${context.packageName}/$drawableId")
    }



}