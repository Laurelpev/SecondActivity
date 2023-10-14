package com.pevahouse.msu.geoquiz

//import android.R.attr.button
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
//import android.text.BoringLayout.make
//import android.view.View
//import android.widget.Button
//import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
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
    private  val cheatLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    )
    { result ->
        //handle result
        if (result.resultCode== Activity.RESULT_OK){
            quizViewModel.isCheater =
                result.data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
        }
    }

    private var currentIndex = 0
    private var scoreCounter = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "Got a QuizViewModel:$quizViewModel")


        binding.cheatButton.setOnClickListener {

            quizViewModel.moveToNext()
            updateQuestion()
        }
        binding.cheatButton.setOnClickListener {
            //START ACTIVITY
//            val intent = Intent(this, CheatActivity::class.java)java
            val answerIsTrue = quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)
//            startActivity(intent)
            cheatLauncher.launch(intent)
        }
        binding.trueButton.setOnClickListener {

            checkAnswer(true    )
            binding.trueButton.isEnabled = false
            binding.falseButton.isEnabled = false
        }
        binding.falseButton.setOnClickListener {

            checkAnswer(false)
            binding.falseButton.isEnabled = false
            binding.trueButton.isEnabled = false
        }


        binding.nextButton.setOnClickListener {
            finalQuestion()
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
        val questionTextResId = quizViewModel.currentQuestionText

        binding.questionTextview.setText(questionTextResId)
        binding.falseButton.isEnabled = true
        binding.trueButton.isEnabled = true
    }

    private fun checkAnswer(userAnswer:Boolean){
        val correctAnswer = quizViewModel.currentQuestionAnswer

//    val messageResId = if (userAnswer == correctAnswer){
//            scoreCounter += 1
//            R.string.correct_toast
//
//        } else {
//            R.string.incorrect_toast
//        }
//        Toast.makeText(this, messageResId, Toast.LENGTH_LONG).show()
//
        val messageResId = when {
            quizViewModel.isCheater -> R.string.judgement_toast
            userAnswer == correctAnswer -> R.string.correct_toast
            else -> R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
//left off this thing below and it would only show the correct toast
//        Toast.makeText(this, messageResId, Toast.LENGTH_LONG).show()
        if (userAnswer == correctAnswer) {
            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show()
        }
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
