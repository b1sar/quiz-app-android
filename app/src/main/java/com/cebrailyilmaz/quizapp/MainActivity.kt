package com.cebrailyilmaz.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        val nameED: TextInputEditText = findViewById(R.id.et_name)
        val startButton: Button = findViewById(R.id.btn_start)
        startButton.setOnClickListener(View.OnClickListener {
            if (nameED.text.toString().isEmpty()) {
                Toast.makeText(this, "Please enter a name!", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, QuizQuestionActivity::class.java)
                intent.putExtra(Constants.USERNAME, nameED.text.toString().trim())
                startActivity(intent)
                finish()
            }
        })
    }
}