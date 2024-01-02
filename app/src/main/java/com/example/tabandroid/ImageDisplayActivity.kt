package com.example.tabandroid

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide

class EnlargedImageFragment : DialogFragment() {

    private var imageUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imageUrl = it.getString(ARG_IMAGE_URI)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_image_display, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Load the image using the imageUrl into an ImageView in the fragment
        val imageView = view.findViewById<ImageView>(R.id.largeImageView)
        // Load the image from imageUrl into imageView (You can use a library like Glide or Picasso for this)
        Glide.with(requireContext())
            .load(imageUrl)
            .placeholder(R.drawable.whiteimg)
            .error(R.drawable.vgg16_dropout0_epoch20_accuracy)
            .into(imageView)

        // Close the dialog on click
        imageView.setOnClickListener {
            dismiss()
        }
    }

    companion object {
        private const val ARG_IMAGE_URI = "image_uri"

        @JvmStatic
        fun newInstance(imageUri: Uri) =
            EnlargedImageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_IMAGE_URI, imageUri.toString())
                }
            }
    }
}