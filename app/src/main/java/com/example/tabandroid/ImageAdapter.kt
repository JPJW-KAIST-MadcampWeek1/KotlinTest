package com.example.tabandroid

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ImageAdapter(private val imagesList: List<Uri>) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image)
    }
    private var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(imageUrl: Uri)

    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_images, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val currentImage = imagesList[position]
        holder.imageView.setImageURI(currentImage)
        holder.itemView.setOnClickListener {
            listener?.onItemClick(currentImage)
        }
    }

    override fun getItemCount(): Int {
        return imagesList.size
    }
}