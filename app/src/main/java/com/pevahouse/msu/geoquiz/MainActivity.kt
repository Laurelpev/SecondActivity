package com.pevahouse.msu.geoquiz

//import android.R.attr.button
import android.graphics.Color
import android.os.Bundle
import android.util.Log
//import android.text.BoringLayout.make
//import android.view.View
//import android.widget.Button
//import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.pevahouse.msu.geoquiz.databinding.ActivityMainBinding
//import java.text.DecimalFormat

//import kotlin.coroutines.jvm.internal.CompletedContinuation.context


private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val quizViewModel:QuizViewModel by viewModels()

    /*private val questionBank = listOf(
        Question(R.string.question_australia, answer = true),
        Question(R.string.question_oceans, answer = true),
        Question(R.string.question_mideast, answer = true),
        Question(R.string.question_africa, answer = true),
        Question(R.string.question_americas, answer = true),
        Question(R.string.question_asia, answer = true)

    )*/
    private var currentIndex = 0
    private var scoreCounter = 0
    //I had instantiated a variable for quiz size here calling from the view model, littl to say did not like it


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Log.d(TAG, "onCreate (Bundle?) called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "Got a QuizViewModel:$quizViewModel")

        binding.trueButton.setOnClickListener {

            checkAnswer(true    )
//            scoreCounter += 1
            binding.trueButton.isEnabled = false
            binding.falseButton.isEnabled = false
        }
        binding.falseButton.setOnClickListener {
//            val snackBar = Snackbar.make(
//                it,
//                "Incorrect",
//                Snackbar.LENGTH_LONG
//            )
//            snackBar.setTextColor(Color.BLACK)
//            snackBar.setBackgroundTint(Color.RED)
//            snackBar.show()
            checkAnswer(false)
            binding.falseButton.isEnabled = false
            binding.trueButton.isEnabled = false
        }


        binding.nextButton.setOnClickListener {
            finalQuestion()
//            currentIndex = (currentIndex + 1) % questionBank.size
            quizViewModel.moveToNext()
            currentIndex =+ 1

            updateQuestion()


        }
        binding.previousButton.setOnClickListener {
            quizViewModel.moveToLast()
            currentIndex -= 1
            updateQuestion()
        }

        binding.questionTextview.setOnClickListener {
            quizViewModel.moveToNext()
            currentIndex =+ 1
            updateQuestion()
        }




        updateQuestion()
    }

    private fun updateQuestion() {
//        val questionTextResId = questionBank[currentIndex].textResId
        val questionTextResId = quizViewModel.currentQuestionText

        binding.questionTextview.setText(questionTextResId)
        binding.falseButton.isEnabled = true
        binding.trueButton.isEnabled = true
    }

//at some poitn did not add this functionality
    private fun checkAnswer(userAnswer:Boolean){
//        val correctAnswer = questionBank[currentIndex].answer
    val correctAnswer = quizViewModel.currentQuestionAnswer

    val messageResId = if (userAnswer == correctAnswer){
            scoreCounter += 1
            R.string.correct_toast

        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_LONG).show()
    }

private fun finalQuestion() {
    if (quizViewModel.currentQuestionIndex == quizViewModel.currentQuestionBankSize - 1){
        val scoreCalc = (scoreCounter.toDouble() / quizViewModel.currentQuestionBankSize.toDouble()) * 100
        val formattedScore = "%.1f".format(scoreCalc)

        Toast.makeText(this, formattedScore, Toast.LENGTH_SHORT).show()
        scoreCounter = 0
    }
}


//    override fun onStart(){
//        super.onStart()
//        Log.d(TAG, "onStart Called")
//    }
//    override fun onResume(){
//        super.onResume()
//        Log.d(TAG, "onResume Called")
//    }
//    override fun onPause(){
//        super.onPause()
//        Log.d(TAG, "onPause() Called")
//    }
//    override fun onStop(){
//        super.onStop()
//        Log.d(TAG, "onStop() Called")
//    }
//    override fun onDestroy(){
//        super.onDestroy()
//        Log.d(TAG, "onDestroy Called")
//    }

//}

}