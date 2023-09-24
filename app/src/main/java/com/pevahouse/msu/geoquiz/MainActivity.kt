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
            binding.falseButton.isEnabled = false
            binding.trueButton.isEnabled = false
        }


        binding.nextButton.setOnClickListener {
            finalQuestion()
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()


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
        }




        updateQuestion()
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextview.setText(questionTextResId)
        binding.falseButton.isEnabled = true
        binding.trueButton.isEnabled = true
    }

private fun finalQuestion() {
    if (currentIndex == qSize-1){
        val scoreCalc = (scoreCounter.toDouble() / questionBank.size.toDouble()) * 100
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