package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.view.View
import android.widget.EditText

import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import com.example.quizapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

import java.util.logging.Handler


class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var auth:FirebaseAuth
    private var BASE_URL = "https://opentdb.com"
    private var tagg = "tageeee"
    private lateinit var question_textView:TextView
    private lateinit var currentQuestion:String
    private lateinit var currentQuestionOptions:MutableList<String>
    private lateinit var questionsAndOptionsClass:QuestionDataClass
    private var numberOfCorrectOptionsClicked = 0
    private var numberOfInCorrectOptonsClicked = 0
    private var questionAndOptionNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        //INITIALIZING WITH 0 BECAUSE IT WILL HELP WHEN USER WILL CLICK ON PLAY AGAIN BUTTON
        numberOfCorrectOptionsClicked=0
        numberOfInCorrectOptonsClicked=0
        questionAndOptionNumber=0
        question_textView = findViewById(R.id.question_textView)
         questionsAndOptionsClass = QuestionDataClass()
        //PREPARING DATA
        questionsAndOptionsClass.prepareData("10")


        var handler = android.os.Handler(Looper.getMainLooper())
        handler.postDelayed({
             currentQuestion = questionsAndOptionsClass.getCurrentQuestion(0)
             currentQuestionOptions = questionsAndOptionsClass.getQuestionOptions(0)
            question_textView.text = currentQuestion
            binding.option1TextView.text = currentQuestionOptions[0]
            binding.option2TextView.text = currentQuestionOptions[1]
            binding.option3TextView.text = currentQuestionOptions[2]
            binding.option4TextView.text = currentQuestionOptions[3]
        },10000)

        binding.option1TextView.setOnClickListener {
            binding.option2TextView.isEnabled = false
            binding.option3TextView.isEnabled = false
            binding.option4TextView.isEnabled = false
            checkIsOptionClickedCorrectOrNot(it as TextView)
        }
        binding.option2TextView.setOnClickListener {
            binding.option1TextView.isEnabled = false
            binding.option3TextView.isEnabled = false
            binding.option4TextView.isEnabled = false
            checkIsOptionClickedCorrectOrNot(it as TextView)
        }

        binding.option3TextView.setOnClickListener {
            binding.option2TextView.isEnabled = false
            binding.option1TextView.isEnabled = false
            binding.option4TextView.isEnabled = false
            checkIsOptionClickedCorrectOrNot(it as TextView)
        }
        binding.option4TextView.setOnClickListener {
            binding.option2TextView.isEnabled = false
            binding.option3TextView.isEnabled = false
            binding.option1TextView.isEnabled = false
            checkIsOptionClickedCorrectOrNot(it as TextView)
        }


    }
    fun checkIsOptionClickedCorrectOrNot(textView:TextView){
        var correctOption = questionsAndOptionsClass.getCorrectOption(questionAndOptionNumber)
        if(textView.text.toString() == correctOption){
            Toast.makeText(this,"You are correct",Toast.LENGTH_SHORT).show()
            numberOfCorrectOptionsClicked += 1
            textView.setBackgroundResource(R.drawable.style_for_clicking_correct_option)
        }else{
            Toast.makeText(this,"You are wrong",Toast.LENGTH_SHORT).show()
            textView.setBackgroundResource(R.drawable.style_for_clicking_wrong_option)
            numberOfInCorrectOptonsClicked += 1
        }
        var handler = android.os.Handler(Looper.getMainLooper())
        handler.postDelayed({
               textView.setBackgroundResource(R.drawable.textview_style)
               changeQuestionAndOptions()
        },1000)
    }

    private fun changeQuestionAndOptions() {
        questionAndOptionNumber += 1
        if(questionAndOptionNumber == 9){
            var intent = Intent(this,show_result_activity::class.java)
            intent.putExtra("numberOfInCorrectOptions",numberOfInCorrectOptonsClicked)
            intent.putExtra("numberOfCorrectOptions",numberOfCorrectOptionsClicked)
            startActivity(intent)
        }
        binding.option2TextView.isEnabled = true
        binding.option1TextView.isEnabled = true
        binding.option4TextView.isEnabled = true
        binding.option3TextView.isEnabled = true
        currentQuestion = questionsAndOptionsClass.getCurrentQuestion(questionAndOptionNumber)
        currentQuestionOptions = questionsAndOptionsClass.getQuestionOptions(questionAndOptionNumber)
        question_textView.text = currentQuestion
        binding.option1TextView.text = currentQuestionOptions[0]
        binding.option2TextView.text = currentQuestionOptions[1]
        binding.option3TextView.text = currentQuestionOptions[2]
        binding.option4TextView.text = currentQuestionOptions[3]
        var correctOption = questionsAndOptionsClass.getCorrectOption(questionAndOptionNumber)
    }


}