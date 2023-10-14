package com.pevahouse.msu.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.ExtractedTextRequest
import android.widget.Toast
import androidx.activity.viewModels
import com.pevahouse.msu.geoquiz.databinding.ActivityCheatBinding
const val EXTRA_ANSWER_SHOWN = "com.pevahouse.msu.geoquiz.answer_shown"
private const val EXTRA_ANSWER_IS_TRUE =
    "com.pevahouse.msu.geoquiz.answer_is_true"

class CheatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheatBinding

    private var answerIsTrue = false;
    private val cheatViewModel: CheatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_cheat)
        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        binding.showAnswerButton.setOnClickListener {
            val answerText = when {
                answerIsTrue -> R.string.true_button
                else -> R.string.false_button
            }
            binding.answerTextView.setText(answerText)
            cheatViewModel.isAnswerShown = true // Save the state
            setAnswerShownResult(true)
        }
        if (cheatViewModel.isAnswerShown) {
            showAnswer()
        } else {
            binding.showAnswerButton.setOnClickListener {
                val answerText = when {
                    answerIsTrue -> R.string.true_button
                    else -> R.string.false_button
                }
                binding.answerTextView.setText(answerText)
                cheatViewModel.isAnswerShown = true // Save the state
                setAnswerShownResult(true)
            }
        }
    }
    private fun setAnswerShownResult(isAnswerShown: Boolean){
        val data = Intent().apply{
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data)
    }
    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
    private fun showAnswer() {
        val answerText = when {
            answerIsTrue -> R.string.true_button
            else -> R.string.false_button
        }
        binding.answerTextView.setText(answerText)
        setAnswerShownResult(true)
    }
}