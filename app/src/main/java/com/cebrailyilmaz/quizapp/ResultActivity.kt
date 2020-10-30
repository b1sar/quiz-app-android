package com.cebrailyilmaz.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.cebrailyilmaz.quizapp.result.ResultHistoryActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tv_score: TextView = findViewById(R.id.tv_score)
        val tv_username: TextView = findViewById(R.id.tv_username)

        val username = intent.getStringExtra(Constants.USERNAME)
        val totalQuestionCount = intent.getIntExtra(Constants.TOTAL_QUESTION_COUNT, -1)
        val correctAnswerCount = intent.getIntExtra(Constants.CORRECT_ANSWER_COUNT, -1)

        tv_score.text = "Your score is $correctAnswerCount out of $totalQuestionCount questions!"
        tv_username.text = username

        val playAgainButton: Button = findViewById(R.id.btn_play_again)
        playAgainButton.setOnClickListener(
            { startActivity(Intent(this, ResultHistoryActivity::class.java)) }
        )
    }
}