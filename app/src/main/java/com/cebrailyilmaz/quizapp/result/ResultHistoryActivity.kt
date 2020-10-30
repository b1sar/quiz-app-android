package com.cebrailyilmaz.quizapp.result

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cebrailyilmaz.quizapp.Constants
import com.cebrailyilmaz.quizapp.R

class ResultHistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_history)

        val recyclerView: RecyclerView = findViewById(R.id.result_recyclerview)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val itemAdapter = MyRecyclerViewAdapter(this, Constants.resultHistory)

        recyclerView.adapter = itemAdapter
    }
}