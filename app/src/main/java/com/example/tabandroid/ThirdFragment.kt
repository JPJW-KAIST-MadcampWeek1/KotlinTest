package com.example.tabandroid
import androidx.navigation.fragment.navArgs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
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


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?



    ): View? {



        _binding = FragmentThirdBinding.inflate(inflater, container, false)


        return binding.root

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val args: SecondFragmentArgs by navArgs()
//
//        val count = args.myArg
//        val countText = getString(R.string.random_heading, count)
//        view.findViewById<TextView>(R.id.textview_header).text = countText
//        val random = java.util.Random()
//        var randomNumber = 0
//        if (count > 0) {
//            randomNumber = random.nextInt(count + 1)
//        }
//        view.findViewById<TextView>(R.id.textview_random).text = randomNumber.toString()
//
//        binding.buttonThird.setOnClickListener {
//            findNavController().navigate(R.id.action_thirdFragment_to_FirstFragment)
//        }
        // Access the MainActivity and call the setup function, make sure that the function is public!
        if (activity is MainActivity) {
            (activity as MainActivity).setUpRecyclerView()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}