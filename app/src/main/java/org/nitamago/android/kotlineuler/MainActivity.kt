package org.nitamago.android.kotlineuler

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun problem001(view: View) {
        val answerArea: TextView = findViewById(R.id.answerArea)

        answerArea.text = "100"
    }

}
