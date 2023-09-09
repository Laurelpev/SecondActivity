package com.pevahouse.msu.geoquiz

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {


    private lateinit var trueButton : Button
    private lateinit var falseButton : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setting IDs to correlate with buttons
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)

        //Creating a listener to wait for a click with some toast
        /*trueButton.setOnClickListener {view: View ->
            Toast.makeText(
            //make reference back to file
            this,
            R.string.true_button,
                //had some issues with the following toast, struggled, googled, struggled had forgotten the comma
            Toast.LENGTH_SHORT)
            .show()}

        falseButton.setOnClickListener { view: View ->
            Toast.makeText(
                this,
                R.string.false_button,
                Toast.LENGTH_SHORT)
                .show()
        }*/

        trueButton.setOnClickListener {
            val snackBar = Snackbar.make(
                it,
                //do what I say not what I do (Hard coded no no)
                "Correct",
                Snackbar.LENGTH_LONG
            )
            snackBar.show()
        }
        falseButton.setOnClickListener {
            val snackBar = Snackbar.make(
                it,
                //do what I say not what I do (Hard coded no no)
                "Incorrect",
                Snackbar.LENGTH_LONG
            )
            snackBar.setTextColor(Color.BLACK)
            snackBar.setBackgroundTint(Color.RED)
            snackBar.show()
        }
    }
}