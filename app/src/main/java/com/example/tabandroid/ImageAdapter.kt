package com.example.tabandroid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

class ImageAdapter(private val context: Context, private val images: List<Image>) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    // ViewHolder and other methods...


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_images, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentImage = images[position]

        // Load image using Glide in the ViewHolder's ImageView
        Glide.with(context)
            .load(currentImage.imageSrc)
            .placeholder(R.drawable.ic_baseline_image_24)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image) // Replace with your ImageView ID
    }
}