package com.pevahouse.msu.geoquiz

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.pevahouse.msu.geoquiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
//    private lateinit var trueButton : Button
//    private lateinit var falseButton : Button

    private val questionBank = listOf(
        //the curse of the comma, had minor issues due to forgetting the comma between questions
        Question(R.string.question_australia, answer = true),
        Question(R.string.question_oceans, answer = true),
        Question(R.string.question_mideast, answer = true),
        Question(R.string.question_africa, answer = true),
        Question(R.string.question_americas, answer = true),
        Question(R.string.question_asia, answer = true)

    )
    private var currentIndex = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setting IDs to correlate with buttons
//        trueButton = findViewById(R.id.true_button)
//        falseButton = findViewById(R.id.false_button)


        binding.trueButton.setOnClickListener {
            val snackBar = Snackbar.make(
                it,
                "Correct",
                Snackbar.LENGTH_LONG
            )
            snackBar.show()
        }
        binding.falseButton.setOnClickListener {
            val snackBar = Snackbar.make(
                it,
                "Incorrect",
                Snackbar.LENGTH_LONG
            )
            snackBar.setTextColor(Color.BLACK)
            snackBar.setBackgroundTint(Color.RED)
            snackBar.show()
        }


        binding.nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            //used parenthesis instead of brackets, broke it momentarily
//            val questionTextResId = questionBank[currentIndex].textResId
//            binding.questionTextview.setText(questionTextResId)
            updateQuestion()
        }
        binding.previousButton.setOnClickListener {
            currentIndex = (currentIndex - 1) % questionBank.size
            // used the if statement to attempt to prevent the crashing when previous was hit
            //on the first question
            //did not want to loop back around because I feel as if that harms the continuity of the quiz
            if (currentIndex <= 0) {
                currentIndex = 0
            }
            updateQuestion()
        }

        binding.questionTextview.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }



        updateQuestion()
//        val questionTextResId = questionBank[currentIndex].textResId
//
//        //overload resolution ambiguity error
//        //decided to swap and look at other things after typing out few times and reviewing resources
//        //Saw Question had error, looked, i had int not Int, fixed EVERYTHING.
//        binding.questionTextview.setText(questionTextResId)
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextview.setText(questionTextResId)
    }
}