package com.example.quizapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizDataInterface {
   //?amount=10&type=multiple
    @GET("/api.php")
    suspend fun getQuizData(
        @Query("amount") amount : String,
        @Query("type") type : String,
    ):Response<QuizDataMainClass>

}