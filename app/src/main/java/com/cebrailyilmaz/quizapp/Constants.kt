package com.cebrailyilmaz.quizapp

import com.cebrailyilmaz.quizapp.result.Result

object Constants {
    const val USERNAME = "com.cebrailyilmaz.quizapp.USERNAME"
    const val CORRECT_ANSWER_COUNT = "com.cebrailyilmaz.quizapp.CORRECT_ANSWER_COUNT"
    const val TOTAL_QUESTION_COUNT = "com.cebrailyilmaz.quizapp.TOTAL_QUESTION_COUNT"
    val resultHistory: ArrayList<Result> = ArrayList()

    fun getQuestions(): List<Question> {
        val questionList = ArrayList<Question>()

        val q1 = Question(1,
            R.drawable.ic_flag_of_argentina,
            "What country this flag belongs to?",
            "Argentina",
            "Turkey",
            "USA",
            "France",
            1
        )

        val q2 = Question(2,
            R.drawable.ic_flag_of_australia,
            "What country this flag belongs to?",
            "Argentina",
            "Turkey",
            "USA",
            "Australia",
            4
        )

        val q3 = Question(3,
            R.drawable.ic_flag_of_denmark,
            "What country this flag belongs to?",
            "Denmark",
            "Turkey",
            "USA",
            "France",
            1
        )

        val q4 = Question(4,
            R.drawable.ic_flag_of_new_zealand,
            "What country this flag belongs to?",
            "Australia",
            "New Zeland",
            "USA",
            "France",
            2
        )

        val q5 = Question(5,
            R.drawable.ic_flag_of_germany,
            "What country this flag belongs to?",
            "Spain",
            "Belgium",
            "Germany",
            "Brazil",
            3
        )

        questionList.add(q1)
        questionList.add(q2)
        questionList.add(q3)
        questionList.add(q4)
        questionList.add(q5)
        return questionList
    }
}