package com.cebrailyilmaz.quizapp

data class Question (
    val id: Int,
    val image: Int,
    val question: String,
    val firstOption: String,
    val secondOption: String,
    val thirdOption: String,
    val fourthOption: String,
    val answer: Int
)