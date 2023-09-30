package com.pevahouse.msu.geoquiz
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
private const val TAG = "QuizViewModel"
const val CURRENT_INDEX_KEY = "CURRENT"



//first issue was the colon view model, must have not seen you add that at first
class QuizViewModel(private val savedStateHandle:SavedStateHandle):ViewModel() {

    /*init{
        Log.d(TAG, "QuizViewModel instance created")
    }
    override fun onCleared(){
        super.onCleared()
        Log.d(TAG, "QuizViewModel instance about to be destroyed")
    }*/

//realized I had these just all set as true
    private val questionBank = listOf(
        Question(R.string.question_australia, answer = true),
        Question(R.string.question_oceans, answer = true),
        Question(R.string.question_mideast, answer = false),
        Question(R.string.question_africa, answer = false),
        Question(R.string.question_americas, answer = true),
        Question(R.string.question_asia, answer = true)
    )
    private var currentIndex:Int
    get() = savedStateHandle.get(CURRENT_INDEX_KEY)?: 0
    set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)


    val currentQuestionAnswer: Boolean
    get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
    get() = questionBank[currentIndex].textResId

    val currentQuestionIndex: Int
        get() = currentIndex

    val currentQuestionBankSize
    get() = questionBank.size

    fun moveToNext(){
        currentIndex= (currentIndex + 1) % questionBank.size

    }
    fun moveToLast(){
        currentIndex = (currentIndex - 1) % questionBank.size
        if (currentIndex <= 0) {
            currentIndex = 0
        }

    }
}