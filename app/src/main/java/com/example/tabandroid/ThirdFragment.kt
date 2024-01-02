package com.example.tabandroid
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.navigation.fragment.navArgs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tabandroid.databinding.FragmentThirdBinding


/**
 * A simple [Fragment] subclass.
 * Use the [ThirdFragment.newInstance] factory method to
 * create an instance of this fragment.
 */


class ThirdFragment : Fragment() {
    private var _binding: FragmentThirdBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object {
        val IMAGE_REQUEST_CODE = 100
    }


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?



    ): View? {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        displayRecyclerView()
        binding.newimage.setOnClickListener { openGalleryForImage() }
        return binding.root

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val args: SecondFragmentArgs by navArgs()
//
        // Access the MainActivity and call the setup function, make sure that the function is public!
//        if (activity is MainActivity) {
//            (activity as MainActivity).setUpRecyclerView()
//        }


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
                uri ->
                binding.testImage.setImageURI(uri)
            }
        }
    }

    private fun displayRecyclerView() {

        val images = listOf<Image>(
            Image("Images 1", "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg"),
            Image("Images 2", "https://img.freepik.com/free-photo/gray-kitty-with-monochrome-wall-her_23-2148955126.jpg?size=626&ext=jpg"),
            Image("Images 3", "https://img.freepik.com/free-photo/domestic-long-haired-cat-lights-against-black-space_181624-24890.jpg?size=626&ext=jpg"),
            Image("Images 4", "https://img.freepik.com/free-photo/vertical-closeup-shot-fat-white-cat-looking-right-dark_181624-41107.jpg?size=626&ext=jpg"),
            Image("Images 5", "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg"),
            Image("Images 6", "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg"),
            Image("Images 7", "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg"),
            Image("Images 8", "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg"),
            Image("Images 9", "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg"),
            Image("Images 10", "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg"),
            Image("Images 11", "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg"),
            Image("Images 12", "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg"),
            Image("Images 13", "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg"),
            Image("Images 14", "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg"),
            Image("Images 15", "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg"),            Image("Images 4", "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg"),
            Image("Images 16", "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg"),
            Image("Images 17", "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg"),
            Image("Images 18", "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg"),
            Image("Images 19", "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg"),
            Image("Images 20", "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg"),

            )




        val recyclerView = binding.imagesRecyclerView
        val spanCount = 3
        val verticalSpacing = dpToPx(4) // Reduce the vertical spacing here
        recyclerView.layoutManager = GridLayoutManager(requireContext(), spanCount)
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(GridSpacingItemDecoration(spanCount, verticalSpacing, true)) // Adjust the spacing by changing dpToPx value
        recyclerView.adapter = ImageAdapter(requireContext(), images)
    }
    // Helper function to convert dp to pixels
    private fun dpToPx(dp: Int): Int {
        val density = resources.displayMetrics.density
        return (dp * density).toInt()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}