package com.example.tabandroid.ideal_worldcup

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
//import androidx.navigation.fragment.findNavController
import com.example.tabandroid.databinding.FragmentFourthBinding
import com.example.tabandroid.R
import kotlinx.coroutines.*


class FourthFragment : Fragment() {

    private lateinit var binding: FragmentFourthBinding
    private var candidates = mutableListOf<ProgrammingLanguage>()
    private var currentRound = 1
    private  lateinit var roundCandidates: List<ProgrammingLanguage>

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
        showCurrentRoundTitle()
        CoroutineScope(Dispatchers.Main).launch {
            setupRound()
        }
//        setupRound()
    }

    private fun initializeCandidates() {
        candidates = listOf(
            ProgrammingLanguage("C", R.drawable.c),
            ProgrammingLanguage("Java", R.drawable.java),
            ProgrammingLanguage("Cpp", R.drawable.cpp),
            ProgrammingLanguage("Kotlin", R.drawable.kotlin),
            ProgrammingLanguage("Python", R.drawable.python),
            ProgrammingLanguage("Flutter", R.drawable.flutter),
            ProgrammingLanguage("React\nNative", R.drawable.reactnative),
            ProgrammingLanguage("Swift", R.drawable.swift),
            ProgrammingLanguage("JavaScript", R.drawable.javascript),
            // ... add all eight candidates
        ).toMutableList()
    }

    private fun setupRound() {

//        if (currentRound == 4) {
//            val winner = candidates.maxByOrNull { it.rank }
//            winner?.let {
//                showWinner(it)
//            }
//        }
        // Based on currentRound, decide how to pick candidates
        roundCandidates = when (currentRound) {
            1 -> candidates.filter { it.rank == 0 }.shuffled().take(2)  // Quarterfinals
            2 -> candidates.filter { it.rank == 1 }.shuffled().take(2)  // Semifinals
            3 -> candidates.filter { it.rank == 2 }.shuffled().take(2)  // Finals
            4 -> candidates.filter {it.rank == 3}.take(1)
            else -> return
        }
        val roundNameTextView = binding.currentround
        roundNameTextView.text = when (currentRound) {
        1 -> "Quarterfinals"
        2 -> "Semifinals"
        3 -> "Finals"
        else -> "Winner"
        }

        // Update the UI with the candidates for the current round
        // This is an example for the first two candida
        with(binding) {
            when {
                roundCandidates.size >= 2 -> {
                    // Safe to access the first two candidates
                    binding.imageViewFirstCandidate.apply {
                        setImageResource(roundCandidates[0].imageResId)
                        textViewFirstCandidate.text = roundCandidates[0].name
                        visibility = View.VISIBLE
                        scaleX = 1.0f
                        scaleY = 1.0f
                        setOnClickListener { selectCandidate(roundCandidates[0], roundCandidates[1]) }
                    }
                    binding.imageViewSecondCandidate.apply {
                        setImageResource(roundCandidates[1].imageResId)
                        textViewSecondCandidate.text = roundCandidates[1].name
                        visibility = View.VISIBLE
                        scaleX = 1.0f
                        scaleY = 1.0f
                        setOnClickListener { selectCandidate(roundCandidates[1], roundCandidates[0]) }
                    }
                }
                roundCandidates.isNotEmpty() -> {
                    // Only one candidate is available
                    binding.imageViewFirstCandidate.apply {
                        setImageResource(roundCandidates[0].imageResId)
                        textViewFirstCandidate.text = roundCandidates[0].name
                        // Other settings
                    }
                    // Handle the case for imageViewSecondCandidate
                    // For instance, hide it or show a placeholder
                    binding.imageViewSecondCandidate.visibility = View.INVISIBLE
                }
                else -> {
                    // No candidates available, handle this scenario
                    // You might want to show a message or take some other action
                }
            }


        }

    }

    private fun selectCandidate(selectedCandidate: ProgrammingLanguage,inselectedCandidate : ProgrammingLanguage) {
        if (!::roundCandidates.isInitialized) {
            setupRound()
        }
        // Increase rank
        selectedCandidate.rank += 1

        // Animate selection
        animateSelection(binding.imageViewFirstCandidate, selectedCandidate == roundCandidates[0])
        animateSelection(binding.imageViewSecondCandidate, selectedCandidate == roundCandidates[1])

        //위에 코드 필요한 지, 수정해야 하는지에 대해서 고민해보기

        // Remove the selected candidate from the candidates list for the next selection


//        candidates.remove(inselectedCandidate)
//        candidates.remove(selectedCandidate)

        inselectedCandidate.rank = -1

        if (candidates.count { it.rank == 0 } == 8) {
            // If there are two or less candidates left, it means we're done with this round

            showCurrentRoundTitle()
//            candidates.forEach { it.rank = currentRound } // Prepare all remaining candidates for the next round
        }
        // Check if we have selected enough candidates for this round

        if (candidates.count { it.rank == 1 } == 4) {
            // If there are two or less candidates left, it means we're done with this round

            currentRound = 2
            CoroutineScope(Dispatchers.Main).launch {
                delay(500)
                showCurrentRoundTitle()
            }
//            candidates.forEach { it.rank = currentRound } // Prepare all remaining candidates for the next round
        }


        //여기가 좀 이상한데 currentRound는 지금이 몇강인지 알려주는건데
        if (candidates.count { it.rank == 2 } == 2) {
            // If there are two or less candidates left, it means we're done with this round
            currentRound = 3
            CoroutineScope(Dispatchers.Main).launch {
                delay(500)
                showCurrentRoundTitle()
            }
//            candidates.forEach { it.rank = currentRound } // Prepare all remaining candidates for the next round
        }

        if(candidates.count {it.rank == 3} == 1){
            currentRound = 4
        }

        // Setup the next round of selection
//        setupRound()
        roundCandidates.filter { it != selectedCandidate }.forEach { candidates.remove(it) }
        // 안뽑은거 지우는거네 //

        CoroutineScope(Dispatchers.Main).launch {
            if (currentRound == 4) {
                delay(300)
                val winner = candidates.maxByOrNull { it.rank }
                winner?.let {
                    showWinner(it)
                }
            }
            delay(500)
            setupRound() // Setup the next round with delay for animations to complete
        }
    }

    private fun showWinner(winner: ProgrammingLanguage) {
        val intent = Intent(context, WinnerActivity::class.java).apply {
            putExtra("winnerImageView", winner.imageResId)
            putExtra("winnerTextView", winner.name)
        }
        startActivity(intent)
    }

    private fun showCurrentRoundTitle() {
        with(binding) {
            // Increase the text size and make it visible


            // Set all images to GONE to clear the screen
            title.visibility = View.INVISIBLE
            imageViewFirstCandidate.visibility = View.INVISIBLE
            imageViewSecondCandidate.visibility = View.INVISIBLE
            vsImageView.visibility = View.INVISIBLE
            firstFrameLayout.visibility = View.INVISIBLE
            secondFrameLayout.visibility = View.INVISIBLE

            currentround.apply {
                textSize = 40f // Set a larger text size
//                visibility = View.VISIBLE
                alpha = 0f
                animate().alpha(1f).setDuration(200).start() // Fade in animation
            }

            // Assuming you have a view with this ID for the 'vs' ima

            // Delay for 2 seconds before moving to the next round
            CoroutineScope(Dispatchers.Main).launch {
                delay(1800)
                title.visibility = View.VISIBLE
                imageViewFirstCandidate.visibility = View.VISIBLE
                firstFrameLayout.visibility = View.VISIBLE
                imageViewSecondCandidate.visibility = View.VISIBLE
                secondFrameLayout.visibility = View.VISIBLE
                vsImageView.visibility = View.VISIBLE
                currentround.apply { // Fade out animation
                    textSize = 18f // Reset the text size

                }
                currentround.visibility = View.VISIBLE
                // Once the title has faded out, proceed to set up the next round
                setupRound()
            }
        }
    }


    private fun animateSelection(view: ImageView, isSelected: Boolean) {
        if (isSelected) {
            view.animate()
                .scaleX(1.2f)
                .scaleY(1.2f)
                .setDuration(200)
                .start()
        } else {
            // 선택되지 않은 뷰는 사라지게 하지 않고, 다음 라운드를 위해 준비
            view.scaleX = 1.0f
            view.scaleY = 1.0f
            view.visibility = View.VISIBLE // 다음 라운드의 사진을 위해 보이게 설정
        }
//            .withEndAction {
//                if (!isSelected) view.visibility = View.GONE
//            }
    }



//    private fun showWinner(winner: ProgrammingLanguage) {
//        with(binding) {
//            // Assuming you have winnerImageView and winnerTextView in your layout
//            winnerImageView.setImageResource(winner.imageResId)
//            winnerTextView.text = getString(R.string.winner_label, winner.name)
//
//            // Set visibility of the winner views to VISIBLE and others to GONE
//            winnerImageView.visibility = View.VISIBLE
//            winnerTextView.visibility = View.VISIBLE
//            imageViewFirstCandidate.visibility = View.GONE
//            imageViewSecondCandidate.visibility = View.GONE
//            textViewFirstCandidate.visibility = View.GONE
//            textViewSecondCandidate.visibility = View.GONE
//        }
//    }
    }