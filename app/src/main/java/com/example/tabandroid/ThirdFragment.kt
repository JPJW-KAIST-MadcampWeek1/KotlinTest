package com.example.tabandroid
import android.app.Activity
import android.content.Intent
import android.net.Uri

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels

import androidx.recyclerview.widget.GridLayoutManager
import com.example.tabandroid.databinding.FragmentThirdBinding


/**
 * A simple [Fragment] subclass.
 * Use the [ThirdFragment.newInstance] factory method to
 * create an instance of this fragment.
 */


class ThirdFragment : Fragment(), ImageAdapter.OnItemClickListener {
    private var _binding: FragmentThirdBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var imageAdapter: ImageAdapter
    private val viewModel: ImageViewModel by viewModels()

    companion object {
        val IMAGE_REQUEST_CODE = 100
    }


    override fun onCreateView(


        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
//        displayRecyclerView()
        println("Create View")

        // Set up RecycelrView
        if(!::imageAdapter.isInitialized) {
            imageAdapter = ImageAdapter(viewModel.imagesList)
            binding.imagesRecyclerView.apply {
                layoutManager = GridLayoutManager(requireContext(), 3)
                adapter = imageAdapter
            }
        }

        binding.newimage.setOnClickListener { openGalleryForImage() }


        return binding.root

    }

    override fun onItemClick(imageUrl: Uri) {
        val enlargedImageFragment = EnlargedImageFragment.newInstance(imageUrl)
        enlargedImageFragment.show(parentFragmentManager, "EnlargedFragment")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val args: SecondFragmentArgs by navArgs()
        imageAdapter.setOnItemClickListener(this)

    }




    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val selectedImageUri: Uri?= data?.data

            selectedImageUri?.let {
               viewModel.imagesList.add(it)
                imageAdapter.notifyDataSetChanged()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        println("Destroy View")
    }


}