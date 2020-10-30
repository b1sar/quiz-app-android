package com.cebrailyilmaz.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.cebrailyilmaz.quizapp.result.Result
import java.time.LocalDate

class QuizQuestionActivity : AppCompatActivity() {
    private var mCurrentPosition: Int = 1
    private lateinit var mQuestionList: List<Question>
    private var mSelectedOptionIndex: Int = -1
    private var mResult: Int = 0
    private lateinit var mUsername: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        mUsername = intent.getStringExtra(Constants.USERNAME) ?: "Invalid username"

        mQuestionList = Constants.getQuestions()
        setQuestion()
        val buton: Button = findViewById(R.id.btn_submit_question)
        buton.setOnClickListener(onButtonSubmitQuestion())
    }

    private fun setQuestion() {
        val question = mQuestionList[mCurrentPosition-1]
        val tv_question: TextView = findViewById(R.id.tv_question)
        val tv_image: ImageView = findViewById(R.id.img_question_image)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val tv_progressStatus: TextView = findViewById(R.id.progressBar_position)
        val tv_option1: TextView = findViewById(R.id.tv_option_first)
        val tv_option2: TextView = findViewById(R.id.tv_option_second)
        val tv_option3: TextView = findViewById(R.id.tv_option_third)
        val tv_option4: TextView = findViewById(R.id.tv_option_fourth)

        makeAllOptionsDefaultLooking()

        tv_question.text = question.question
        tv_image.setImageResource(question.image)

        progressBar.progress = mCurrentPosition
        progressBar.max = mQuestionList.size
        tv_progressStatus.text = "$mCurrentPosition/${mQuestionList!!.size}"

        tv_option1.text = question.firstOption
        tv_option2.text = question.secondOption
        tv_option3.text = question.thirdOption
        tv_option4.text = question.fourthOption

        //this function could be called several times, so setting the listeners here everytime its called
        // may not be a wanted thing
        tv_option1.setOnClickListener(onOptionClicked())
        tv_option2.setOnClickListener(onOptionClicked())
        tv_option3.setOnClickListener(onOptionClicked())
        tv_option4.setOnClickListener(onOptionClicked())
    }

    fun makeAllOptionsDefaultLooking() {
        val options: ArrayList<TextView> = arrayListOf(
            findViewById(R.id.tv_option_first),
            findViewById(R.id.tv_option_second),
            findViewById(R.id.tv_option_third),
            findViewById(R.id.tv_option_fourth)
        )
        options.forEach {
            it.setTextColor(Color.parseColor("#7A8089"))
            it.typeface = Typeface.DEFAULT
            it.background = ContextCompat.getDrawable(this, R.drawable.default_option_bg)
         }
    }

    private fun onOptionClicked(): View.OnClickListener? {
        return object : View.OnClickListener {
            override fun onClick(v: View?) {
                makeAllOptionsDefaultLooking()
                when(v?.id) {
                    R.id.tv_option_first -> setSelectedOption(findViewById(R.id.tv_option_first), 1)
                    R.id.tv_option_second -> setSelectedOption(findViewById(R.id.tv_option_second), 2)
                    R.id.tv_option_third -> setSelectedOption(findViewById(R.id.tv_option_third), 3)
                    R.id.tv_option_fourth -> setSelectedOption(findViewById(R.id.tv_option_fourth), 4)
                }
            }

        }
    }

    fun setSelectedOption(tv: TextView, selectedOptionIndex: Int) {
        mSelectedOptionIndex = selectedOptionIndex
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_bg)
    }

    private fun onButtonNextQuestion(): View.OnClickListener? {
        return object : View.OnClickListener {
            override fun onClick(v: View?) {

                //if it is not the last question
                  //-> change the listener of the button
                //if it is the last question
                  //-> Show the result page
                next()
            }
        }
    }

    private fun onButtonSubmitQuestion(): View.OnClickListener? {
        return object : View.OnClickListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onClick(v: View?) {
               checkAnswer()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun checkAnswer() {
        val theButton:Button = findViewById(R.id.btn_submit_question)

        val options: ArrayList<TextView> = arrayListOf(
            findViewById(R.id.tv_option_first),
            findViewById(R.id.tv_option_second),
            findViewById(R.id.tv_option_third),
            findViewById(R.id.tv_option_fourth)
        )

        val currentQuestion:Question = mQuestionList[mCurrentPosition-1]
        if (mSelectedOptionIndex == -1) {
            Log.i( "Tag?", "No option selected, please select an option")
            Toast.makeText(this, "No option is selected", Toast.LENGTH_SHORT).show()
            return
        }
        //always show the correct answer
        answerOptionView(options[currentQuestion.answer-1], R.drawable.correct_option_bg)
        theButton.setOnClickListener(onButtonNextQuestion())

        //then if the selected answer is incorrect, show as incorrect
        if (currentQuestion.answer != mSelectedOptionIndex) {
            //answer is incorrect
            answerOptionView(options[mSelectedOptionIndex-1], R.drawable.incorrect_option_bg)
        } else {
            mResult++
        }

        if (mCurrentPosition == mQuestionList.size) {
            theButton.setText("Finish")
            //Create the QuizResult
            val quizResult = Result(1, LocalDate.now(), mUsername, mResult, mQuestionList.size)
            Constants.resultHistory.add(quizResult)

            //new intent
            val theIntent = Intent(this, ResultActivity::class.java)
            theIntent.putExtra(Constants.USERNAME, mUsername)
            theIntent.putExtra(Constants.CORRECT_ANSWER_COUNT, mResult)
            theIntent.putExtra(Constants.TOTAL_QUESTION_COUNT, mQuestionList.size)
            startActivity(theIntent)
            finish()
        } else {
            theButton.setText("Go to next question")
        }

    }

    private fun next() {
        val buton:Button = findViewById(R.id.btn_submit_question)
        if(mCurrentPosition <= mQuestionList.size-1) {
            mCurrentPosition++
            mSelectedOptionIndex = -1
            setQuestion()

            buton.setOnClickListener(onButtonSubmitQuestion())
            buton.text = "Submit"
        } else {
            //in the next step, instead of showing the toast message, this will start the result activity
            //with the results put extra
            Toast.makeText(this, "This was the last question", Toast.LENGTH_SHORT).show()
        }
    }

    private fun answerOptionView(tv: TextView, drawable: Int) {
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, drawable)
    }
    private fun makeCorrect(tv: TextView) {
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.correct_option_bg)
    }
    private fun makeIncorrect(tv: TextView) {
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.incorrect_option_bg)
    }
}

