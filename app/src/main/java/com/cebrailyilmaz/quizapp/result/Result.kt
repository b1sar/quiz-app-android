package com.cebrailyilmaz.quizapp.result

import java.time.LocalDate

data class Result(
    val id: Int,
    val date: LocalDate,
    val username: String,
    val correctAnswerCount:Int,
    val totalAnswerCount: Int
)