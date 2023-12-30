package com.example.tabandroid.ideal_worldcup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
//import androidx.navigation.fragment.findNavController
import com.example.tabandroid.databinding.FragmentFourthBinding
import com.example.tabandroid.R



/**
 * A simple [Fragment] subclass.
 * Use the [FourthFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FourthFragment : Fragment() {

    private lateinit var binding: FragmentFourthBinding
    private var candidates = mutableListOf<ProgrammingLanguage>()
    private var currentRound = 1
    private lateinit var roundCandidates: List<ProgrammingLanguage>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFourthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeCandidates()
        setupRound()
    }

    private fun initializeCandidates() {
        candidates = listOf(
            ProgrammingLanguage("C", R.drawable.c),
            ProgrammingLanguage("Java", R.drawable.java),
            ProgrammingLanguage("Cpp", R.drawable.cpp),
            ProgrammingLanguage("Kotlin", R.drawable.kotlin),
            ProgrammingLanguage("Python", R.drawable.python),
            ProgrammingLanguage("Flutter", R.drawable.flutter),
            ProgrammingLanguage("ReactNative", R.drawable.reactnative),
            ProgrammingLanguage("Swift", R.drawable.swift),
            ProgrammingLanguage("JavaScript", R.drawable.javascript),
            // ... add all eight candidates
        ).toMutableList()
    }

    private fun setupRound() {
        // Based on currentRound, decide how to pick candidates
        val roundCandidates = when (currentRound) {
            1 -> candidates.shuffled().take(2)  // Quarterfinals
            2 -> candidates.filter { it.rank >= 1 }.shuffled().take(2)  // Semifinals
            3 -> candidates.filter { it.rank >= 2 }.shuffled().take(2)  // Finals
            else -> return
        }

        // Update the UI with the candidates for the current round
        // This is an example for the first two candidates
        with(binding) {
            imageViewFirstCandidate.setImageResource(roundCandidates[0].imageResId)
            textViewFirstCandidate.text = roundCandidates[0].name
            imageViewSecondCandidate.setImageResource(roundCandidates[1].imageResId)
            textViewSecondCandidate.text = roundCandidates[1].name

            // Set click listeners for the images
            imageViewFirstCandidate.setOnClickListener { selectCandidate(roundCandidates[0]) }
            imageViewSecondCandidate.setOnClickListener { selectCandidate(roundCandidates[1]) }
        }
    }

    private fun selectCandidate(selectedCandidate: ProgrammingLanguage) {
        // Increase rank
        selectedCandidate.rank += 1

        // Animate selection
        animateSelection(binding.imageViewFirstCandidate, selectedCandidate == roundCandidates[0])
        animateSelection(binding.imageViewSecondCandidate, selectedCandidate == roundCandidates[1])

        // Remove the selected candidate from the candidates list for the next selection
        candidates.remove(selectedCandidate)

        // Check if we have selected enough candidates for this round
        if (candidates.count { it.rank == currentRound } >= 2) {
            currentRound++
        } else if (candidates.size <= 2) {
            // If there are two or less candidates left, it means we're done with this round
            currentRound++
            candidates.forEach { it.rank = currentRound } // Prepare all remaining candidates for the next round
        }

        // Setup the next round of selection
        setupRound()
    }

    private fun animateSelection(view: ImageView, isSelected: Boolean) {
        view.animate()
            .scaleX(if (isSelected) 1.2f else 0f)
            .scaleY(if (isSelected) 1.2f else 0f)
            .setDuration(500)
            .withEndAction {
                if (!isSelected) view.visibility = View.GONE
            }
            .start()
    }



}
