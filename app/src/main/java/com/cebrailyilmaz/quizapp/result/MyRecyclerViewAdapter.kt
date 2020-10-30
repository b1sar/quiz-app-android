package com.cebrailyilmaz.quizapp.result

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cebrailyilmaz.quizapp.Question
import com.cebrailyilmaz.quizapp.R

class MyRecyclerViewAdapter(val context: Context, val resultQuestionList: List<Result>) : RecyclerView.Adapter<MyRecyclerViewAdapter.ResultViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        return ResultViewHolder(
            LayoutInflater.from(context).inflate(R.layout.result_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val currentQuestionResult = resultQuestionList[position]
        holder.tv_id.text = currentQuestionResult.id.toString()
        holder.tv_date.text = currentQuestionResult.date.toString()
        holder.tv_username.text = currentQuestionResult.username
        holder.tv_correctCount.text = currentQuestionResult.correctAnswerCount.toString()
        holder.tv_totalAnswerCount.text = currentQuestionResult.totalAnswerCount.toString()
    }

    override fun getItemCount(): Int {
        return resultQuestionList.size
    }

    class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_id:TextView = itemView.findViewById(R.id.result_id)
        var tv_date:TextView = itemView.findViewById(R.id.result_date)
        var tv_username: TextView = itemView.findViewById(R.id.result_username)
        var tv_correctCount: TextView = itemView.findViewById(R.id.result_correct_answer_count)
        var tv_totalAnswerCount: TextView = itemView.findViewById(R.id.result_total_answer_count)
    }
}
