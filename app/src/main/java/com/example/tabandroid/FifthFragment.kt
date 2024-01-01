package com.example.tabandroid

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.tabandroid.databinding.FragmentFifthBinding
//import androidx.navigation.fragment.findNavController



/**
 * A simple [Fragment] subclass.
 * Use the [FifthFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FifthFragment : Fragment() {
    private var _binding: FragmentFifthBinding? = null



    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val questions = arrayOf("What team does Shohei Ohtani play for?",
        "What is Jaewon's MBTI?", "What is Jinsuk's dream car?")

    private val options = arrayOf(arrayOf("New York Yankees", "Los Angeles Angels", "Los Angeles Dodgers"),
        arrayOf("ENTP", "ESFP", "ENFP"), arrayOf("Genesis G90", "BMW i7", "Porsche Taycan"))

    private val correctAnswers = arrayOf(2, 1, 2)

    private var currentQuestionIndex = 0
    private var score = 0


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?



    ): View? {



        _binding = FragmentFifthBinding.inflate(inflater, container, false)

        displayStart()

        binding.startButton.setOnClickListener { displayQuestions() }

        binding.FirstButton.setOnClickListener{
            checkAnswer(0)
        }
        binding.SecondButton.setOnClickListener{
            checkAnswer(1)
        }
        binding.ThirdButton.setOnClickListener {
            checkAnswer(2)
        }

        binding.restartButton.setOnClickListener{
            restartQuiz()
        }
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

    private fun correctButtonColors(buttonIndex: Int) {
        when(buttonIndex) {
            0 -> binding.FirstButton.setBackgroundColor(Color.GREEN)
            1 -> binding.SecondButton.setBackgroundColor(Color.GREEN)
            2 -> binding.ThirdButton.setBackgroundColor(Color.GREEN)
        }
    }

    private fun wrongButtonColors(buttonIndex: Int) {
        when (buttonIndex) {
            0 -> binding.FirstButton.setBackgroundColor(Color.RED)
            1 -> binding.SecondButton.setBackgroundColor(Color.RED)
            2 -> binding.ThirdButton.setBackgroundColor(Color.RED)
        }
    }

    private fun resetButtonColors() {
        binding.FirstButton.setBackgroundColor(Color.rgb(50,59,96))
        binding.SecondButton.setBackgroundColor(Color.rgb(50,59,96))
        binding.ThirdButton.setBackgroundColor(Color.rgb(50,59,96))
    }

//    private fun showResults() {
//        Toast.makeText(requireContext(), "Your score: $score out of ${questions.size}", Toast.LENGTH_LONG).show()
//        binding.restartButton.isEnabled = true
//    }

    private fun displayStart() {


        binding.quizTitle.visibility = View.VISIBLE
        binding.quizImage.visibility = View.VISIBLE
//        binding.codinganim.visibility = View.VISIBLE
        binding.startButton.visibility = View.VISIBLE
        binding.resultText.visibility = View.GONE
        binding.progressCircle.visibility = View.GONE
        binding.questionText.visibility = View.GONE
        binding.FirstButton.visibility = View.GONE
        binding.SecondButton.visibility = View.GONE
        binding.ThirdButton.visibility = View.GONE
        binding.restartButton.visibility = View.GONE
    }

    private fun displayQuestions() {
        binding.quizTitle.visibility = View.GONE
        binding.quizImage.visibility = View.GONE
        binding.startButton.visibility = View.GONE
        binding.questionText.visibility = View.VISIBLE
        binding.FirstButton.visibility = View.VISIBLE
        binding.SecondButton.visibility = View.VISIBLE
        binding.ThirdButton.visibility = View.VISIBLE
        binding.restartButton.visibility = View.GONE
        // This shows what questions are going to be displayed
        binding.questionText.text = questions[currentQuestionIndex]
        binding.FirstButton.text = options[currentQuestionIndex][0]
        binding.SecondButton.text = options[currentQuestionIndex][1]
        binding.ThirdButton.text = options[currentQuestionIndex][2]
        resetButtonColors()

    }

    private fun checkAnswer(selectedAnswerIndex: Int) {
        val correctAnswerIndex = correctAnswers[currentQuestionIndex]

        if(selectedAnswerIndex == correctAnswerIndex) {
            score++
            correctButtonColors(selectedAnswerIndex)

        }else {
            wrongButtonColors(selectedAnswerIndex)
            correctButtonColors(correctAnswerIndex)
        }
        if(currentQuestionIndex < questions.size - 1 ){
            currentQuestionIndex++
            binding.questionText.postDelayed({displayQuestions()}, 1000)

        }
        // Going to else means that we have finished all of our questions
        else {
            showResults()
        }

    }

    private fun restartQuiz(){
        currentQuestionIndex = 0
        score = 0

        binding.resultText.visibility = View.GONE
        binding.progressCircle.visibility = View.GONE
//        binding.FirstButton.visibility = View.VISIBLE
//        binding.SecondButton.visibility = View.VISIBLE
//        binding.ThirdButton.visibility = View.VISIBLE
        displayStart()
        binding.restartButton.isEnabled = false
    }

    private fun showResults() {

        binding.progressCircle.progress = score
        binding.restartButton.visibility = View.VISIBLE
        binding.resultText.visibility = View.VISIBLE
        binding.progressCircle.visibility = View.VISIBLE
        binding.questionText.text = "Results!"
        binding.restartButton.isEnabled = true
        binding.resultText.text = "You are $score out of ${questions.size}"
        binding.FirstButton.visibility = View.GONE
        binding.SecondButton.visibility = View.GONE
        binding.ThirdButton.visibility = View.GONE

        // update the resultView with the result details

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}