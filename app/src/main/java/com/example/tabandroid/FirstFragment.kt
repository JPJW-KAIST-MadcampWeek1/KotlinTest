package com.example.tabandroid
 import android.widget.Button

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
//import androidx.navigation.fragment.findNavController
import com.example.tabandroid.databinding.FragmentFirstBinding
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import android.widget.PopupMenu
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.Exception

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    lateinit var bottomNav : BottomNavigationView

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    // This is the function that increments the counter on screen
//    private fun countMe(view: View) {
//        // Get the text view
//        val showCountTextView = view.findViewById<TextView>(R.id.textview_first)
//        // Get the value of the text view.
//        val countString = showCountTextView.text.toString()
//        // Convert value to a number and increment it
//        var count = countString.toInt() // Convert to Int or default to 0 if not a valid number
//        count++
//        // Display the new value in the text view.
//        showCountTextView.text = count.toString()
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        // 1. This is the random_button function -> Goes to another fragment
//        view.findViewById<Button>(R.id.random_button).setOnClickListener {
//
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }

        // 2. This is the Toast button function -> Pops up a text
//        view.findViewById<Button>(R.id.toast_button).setOnClickListener {
//            // create a Toast with some text, to appear for a short time
//            val myToast = Toast.makeText(context, "Hello Toast!", Toast.LENGTH_SHORT)
//            // show the Toast
//            myToast.show()
//        }

//        view.findViewById<Button>(R.id.toast_button).setOnClickListener {
//            // create a Toast with some text, to appear for a short time
//            findNavController().navigate(R.id.action_FirstFragment_to_thirdFragment)
//            }

        // This is the count_button function -> Increments the counter on the screen , calls a function
//        view.findViewById<Button>(R.id.count_button).setOnClickListener {
//            countMe(view)
//        }

//        view.findViewById<Button>(R.id.count_button).setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_fourthFragment)
//        }


//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}