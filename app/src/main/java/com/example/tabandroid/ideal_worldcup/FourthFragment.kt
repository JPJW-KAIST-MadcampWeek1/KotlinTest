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
import kotlinx.coroutines.*
import kotlin.math.round


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
            ProgrammingLanguage("ReactNative", R.drawable.reactnative),
            ProgrammingLanguage("Swift", R.drawable.swift),
            ProgrammingLanguage("JavaScript", R.drawable.javascript),
            // ... add all eight candidates
        ).toMutableList()
    }

    private fun setupRound() {
        // Based on currentRound, decide how to pick candidates
        roundCandidates = when (currentRound) {
            1 -> candidates.filter { it.rank == 0 }.shuffled().take(2)  // Quarterfinals
            2 -> candidates.filter { it.rank == 1 }.shuffled().take(2)  // Semifinals
            3 -> candidates.filter { it.rank == 2 }.shuffled().take(2)  // Finals
            else -> return
        }

        // Update the UI with the candidates for the current round
        // This is an example for the first two candida
        with(binding) {
            // 첫 번째 후보 이미지 뷰
            imageViewFirstCandidate.apply {
                setImageResource(roundCandidates[0].imageResId)
                textViewFirstCandidate.text = roundCandidates[0].name
                visibility = View.VISIBLE
                scaleX = 1.0f
                scaleY = 1.0f
                setOnClickListener { selectCandidate(roundCandidates[0],roundCandidates[1]) }
            }

            // 두 번째 후보 이미지 뷰
            imageViewSecondCandidate.apply {
                setImageResource(roundCandidates[1].imageResId)
                textViewSecondCandidate.text = roundCandidates[1].name
                visibility = View.VISIBLE
                scaleX = 1.0f
                scaleY = 1.0f
                setOnClickListener { selectCandidate(roundCandidates[1], roundCandidates[0]) }
                //버튼 클릭하면 인수인 selectCandidate가 호출됨
            }
        }
        if (currentRound == 3) {
            val winner = candidates.maxByOrNull { it.rank }
            winner?.let {
//                showWinner(it)
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


        // Check if we have selected enough candidates for this round
//        if (candidates.count { it.rank == currentRound } >= 2) {
//            currentRound++
        if (candidates.size <= 2) {
            // If there are two or less candidates left, it means we're done with this round
            currentRound++
            candidates.forEach { it.rank = currentRound } // Prepare all remaining candidates for the next round
        }

        //여기가 좀 이상한데 currentRound는 지금이 몇강인지 알려주는건데

        // Setup the next round of selection
//        setupRound()
        roundCandidates.filter { it != selectedCandidate }.forEach { candidates.remove(it) }
        // 안뽑은거 지우는거네 //

        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            setupRound() // Setup the next round with delay for animations to complete
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