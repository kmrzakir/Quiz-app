package com.example.quizapp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class QuestionDataClass {
    private  var dataBody:QuizDataMainClass? = null
    private var BASE_URL = "https://opentdb.com"
     fun prepareData(amount:String){
        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuizDataInterface::class.java)

        GlobalScope.launch (Dispatchers.IO){
            var api = retrofit.getQuizData(amount,"multiple")
             withContext(Dispatchers.Main){
                 dataBody = api.body()
             }
        }
    }

    fun getCurrentQuestion(index:Int):String{
        var currentQuestion = dataBody!!.results[index].question
        return currentQuestion
    }
    fun getQuestionOptions(index:Int):MutableList<String>{
        val options = mutableListOf<String>()
        var correctOption = dataBody!!.results[index].correct_answer
        var wrongOption1 = dataBody!!.results[index].incorrect_answers[0]
        var wrongOption2 = dataBody!!.results[index].incorrect_answers[1]
        var wrongOption3 = dataBody!!.results[index].incorrect_answers[2]

        options.add(correctOption)
        options.add(wrongOption1)
        options.add(wrongOption2)
        options.add(wrongOption3)
        options.shuffle()
        return options

    }

    fun getCorrectOption(index:Int):String{

        var currentCorrectOption = dataBody!!.results[index].correct_answer
        return currentCorrectOption

    }

}