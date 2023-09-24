package com.pevahouse.msu.geoquiz

//import android.R.attr.button
import android.graphics.Color
import android.os.Bundle
//import android.text.BoringLayout.make
//import android.view.View
//import android.widget.Button
//import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.pevahouse.msu.geoquiz.databinding.ActivityMainBinding
//import java.text.DecimalFormat

//import kotlin.coroutines.jvm.internal.CompletedContinuation.context


private const val TAG = "MainActivity"

//had an error with Activity main binding but once I resynced with gradle it all fixed it

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val questionBank = listOf(
        Question(R.string.question_australia, answer = true),
        Question(R.string.question_oceans, answer = true),
        Question(R.string.question_mideast, answer = true),
        Question(R.string.question_africa, answer = true),
        Question(R.string.question_americas, answer = true),
        Question(R.string.question_asia, answer = true)

    )
    private var currentIndex = 0
    private var scoreCounter = 0
    private var qSize = questionBank.size
    private var scoreCalc = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Log.d(TAG, "onCreate (Bundle?) called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.trueButton.setOnClickListener {
            val snackBar = Snackbar.make(
                it,
                "Correct",
                Snackbar.LENGTH_LONG
            )
            snackBar.show()
            scoreCounter += 1
            binding.trueButton.isEnabled = false
            binding.falseButton.isEnabled = false
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
            //well that was more simple than I thought
            //I dislike the binding aspect, it just does not make sense to me and I dislike not having variables to look towards.
            binding.falseButton.isEnabled = false
            binding.trueButton.isEnabled = false
        }


        binding.nextButton.setOnClickListener {
            finalQuestion()
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()


//            updateQuestion()
//            finalQuestion()
        }
        binding.previousButton.setOnClickListener {
            currentIndex = (currentIndex - 1) % questionBank.size
            if (currentIndex <= 0) {
                currentIndex = 0
            }
            updateQuestion()
        }

        binding.questionTextview.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
            //this line makes it crash when it runs
//            btnView.isEnabled = !(btnView.isEnabled)
        }


//        these are making the app crash repeatedly
//       btnView.setOnClickListener {
//            Toast.makeText(this,"clicked",Toast.LENGTH_SHORT).show()
//       }
//
//        tvMain.setOnClickListener {
//            btnView.isEnabled = !(btnView.isEnabled)
//        }


        updateQuestion()
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextview.setText(questionTextResId)
        binding.falseButton.isEnabled = true
        binding.trueButton.isEnabled = true
    }

//    private fun isQuizOver(): Boolean{
//        return currentIndex >= qSize
//    }
private fun finalQuestion() {
    //bizarre issue??? if i put -1 on it works fine but executes early (obviously) but without it it doesnt run at all
   //doing +0, -0, -1 +1, nothing else works

    //ok, to be honest, had ChatGPT to help me figure out the indexing that was put into it and figured it out
    if (currentIndex == qSize-1){
        val scoreCalc = (scoreCounter.toDouble() / questionBank.size.toDouble()) * 100
        val formattedScore = "%.1f".format(scoreCalc) // Format the score as a string with 2 decimal places

        // Display the formatted score in a Toast
        Toast.makeText(this, formattedScore, Toast.LENGTH_SHORT).show()
        scoreCounter = 0
    }
}

//    if (currentIndex == questionBank.size - 1) { // Check if it's the last question
//        // Compute the score as the number of right answers divided by the total number of questions
//        val scoreCalc = (scoreCounter.toDouble() / questionBank.size.toDouble()) * 100
//
//        // Format the score as a string with one decimal place and add "%" to it
//        val formattedScore = "%.1f %".format(scoreCalc)
//
//        // Display the formatted score in a Toast message
//        Toast.makeText(this, formattedScore, Toast.LENGTH_SHORT).show()
//
//        // Reset the score counter to 0 for the next quiz
//        scoreCounter = 0
//    }



//    perhaps these are causing too much stress? No that wasnt it, but still keeping them off

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
//fun onTextChanged(charSequence: CharSequence?, i: Int, i1: Int, i2: Int) {
//    button.setEnabled(falseButton() || trueButton())
//}

}