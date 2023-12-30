package com.example.tabandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
//import androidx.navigation.fragment.findNavController
import com.example.tabandroid.databinding.FragmentFourthBinding



/**
 * A simple [Fragment] subclass.
 * Use the [FourthFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FourthFragment : Fragment() {
    private var _binding: FragmentFourthBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?



    ): View? {



        _binding = FragmentFourthBinding.inflate(inflater, container, false)


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
//        binding.buttonFourth.setOnClickListener {
//            findNavController().navigate(R.id.action_fourthFragment_to_FirstFragment)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}